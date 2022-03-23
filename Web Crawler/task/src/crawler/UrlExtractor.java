package crawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtractor {
    private static final String titleRegex = "<title>.+?</title>";
    private static final String linkRegex = "href=\"[^\"]*+\"";
    private static final Pattern titlePattern = Pattern.compile(titleRegex, Pattern.CASE_INSENSITIVE);
    private static final Pattern linkPattern = Pattern.compile(linkRegex, Pattern.CASE_INSENSITIVE);

    private UrlExtractor() {
        throw new IllegalStateException("HTMLCodeDownloader class");
    }

    public static void  collectAllLinkTitles(String url) throws URISyntaxException {
        SortedMap<String, String> urlTitlesMap = new TreeMap<>();
        //get main url html code
        String mainUrlHtmlCode = getHtmlCode(url);

        //get main url title
        String mainUrlTitle = getTitle(mainUrlHtmlCode);
        urlTitlesMap.put(url, mainUrlTitle);

        //get all html links
        URI uri = new URI(url);
        SortedSet<String> htmlLinks = getAllHtmlLinks(uri, mainUrlHtmlCode);

        //get titles for all links
        for (var link : htmlLinks) {
            String htmlCode = getHtmlCode(link);
            String title = getTitle(htmlCode);

            urlTitlesMap.put(link, title);
        }
    }

    static String getHtmlCode(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();

        URI firstWebSiteAddress = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder().uri(firstWebSiteAddress).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "We cannot access the site. Please, try later.";
        }
    }

    static String getTitle(String htmlCode) {
        Matcher titleMatcher = titlePattern.matcher(htmlCode);
        if (titleMatcher.find()) {
            return titleMatcher.group().replaceAll("<[^>]+>", "");
        }
        return "";
    }

    private static SortedSet<String> getAllHtmlLinks(URI mainUri, String htmlCode) {
        SortedSet<String> htmlLinks = new TreeSet<>();

        Matcher hrefMatcher = linkPattern.matcher(htmlCode);

        Pattern hrefPattern = Pattern.compile("href=", Pattern.CASE_INSENSITIVE);
        while (hrefMatcher.find()) {
            String link = hrefMatcher.group();

            Matcher hrefPropMatcher = hrefPattern.matcher(link);
            if (hrefPropMatcher.find()) {
                link = link.substring(hrefPropMatcher.end() + 1, link.length() - 1);
            }

            if (isUrlAbsolute(link) && isHtmlLink(link)) {
                htmlLinks.add(link);
            } else {
                String absolutePath = completeRelativeUrl(mainUri, link);
                if (isHtmlLink(absolutePath)) {
                    htmlLinks.add(absolutePath);
                }
            }
        }
        return htmlLinks;
    }

    private static boolean isHtmlLink(String link) {
        HttpClient httpClient = HttpClient.newHttpClient();

        URI uri = URI.create(link);

        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            List<String> contentTypeValues = response.headers().allValues("Content-Type");

            return contentTypeValues.contains("text/html");
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isUrlAbsolute(String url) {
        return (url.indexOf("://") > 0 || url.indexOf("//") == 0);
    }

    private static String completeRelativeUrl(URI uri, String relativePath) {
        StringBuilder absolutePath = new StringBuilder();

        //add protocol + host
        absolutePath.append(uri.getScheme()).append("://").append(uri.getHost());
        //add port
        if (uri.getPort() != -1) {
            absolutePath.append(":").append(uri.getPort());
        }
        //add path
        String uriPath = uri.getPath();
        if (relativePath.startsWith("/")) {     //remove last / of main url path if the relative path start with /
            uriPath = uriPath.substring(0, uriPath.lastIndexOf("/"));
        } else {
            uriPath = uriPath.substring(0, uriPath.lastIndexOf("/") + 1);
        }
        while (relativePath.startsWith("../")) {     //go back one level in main path if relative path indicating this
            if (relativePath.startsWith("/")) {
                uriPath = uriPath.substring(0, uriPath.lastIndexOf("/"));
            } else {
                uriPath = uriPath.substring(0, uriPath.lastIndexOf("/"));
                uriPath = uriPath.substring(0, uriPath.lastIndexOf("/") + 1);
            }
            relativePath = relativePath.replace("../", "");
        }

        absolutePath.append(uriPath).append(relativePath);
        return absolutePath.toString();
    }
}
