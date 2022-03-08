package crawler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTMLCodeDownloader {
    private HTMLCodeDownloader() {
        throw new IllegalStateException("HTMLCodeDownloader class");
    }

    public static String getCode(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();

        URI firstWebSiteAddress = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(firstWebSiteAddress)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "We cannot access the site. Please, try later.";
        }
    }
}
