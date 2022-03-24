package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler extends JFrame {
    JTextField urlTextField = new JTextField();
    JToggleButton runToggleButton = new JToggleButton();
    JTextField workersTextField = new JTextField();
    JTextField depthTextField = new JTextField();
    JCheckBox depthCheckBox = new JCheckBox();
    JTextField timeLimitTextField = new JTextField();
    JCheckBox timeLimitCheckBox = new JCheckBox();
    JLabel elapsedTimeLabel = new JLabel();
    JLabel parsedPagesLabel = new JLabel();
    JTextField exportUrlTextField = new JTextField();
    JButton exportButton = new JButton();
    ///////////////////////////////////////////////////////////////
    final Dimension dimension = new Dimension(700, 500);
    final ImageIcon icon = new ImageIcon("Web Crawler/task/src/crawler/webCrawler.png");
    ////////////////////////////////////////////////////////////////
    private static final String titleRegex = "<title>.+?</title>";
    private static final String linkRegex = "href=\"[^\"]*+\"";
    private static final Pattern titlePattern = Pattern.compile(titleRegex, Pattern.CASE_INSENSITIVE);
    private static final Pattern linkPattern = Pattern.compile(linkRegex, Pattern.CASE_INSENSITIVE);
    //////////////////////////////////////////////////////////////////
    static Set<String> urlSet = new HashSet<>();
    static SortedMap<String, String> linkTitleMap = new TreeMap<>();
    static Integer depth;
    static int parsedPages = 0;
    /////////////////////////////////////////////////////////////////////////////
    private final transient ActionListener runButtonActionListener = e -> {
        runToggleButton.setSelected(true);
        runToggleButton.setText("Stop");
        String url = urlTextField.getText();
        if (!urlSet.isEmpty()) {
            urlSet = new HashSet<>();
        }
        if (!linkTitleMap.isEmpty()) {
            linkTitleMap = new TreeMap<>();
        }
        parsedPages = 0;
        urlSet.add(url);
        try {
            depth = Integer.parseInt(depthTextField.getText());
        } catch (Exception ignored) {
        }

        if (Objects.isNull(depth)) {
            depth = Integer.MAX_VALUE;
        }
        try {
            parsePage(url, 1);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        parsedPagesLabel.setText(String.valueOf(parsedPages));
        runToggleButton.setSelected(false);
        runToggleButton.setText("Run");
    };
    private final transient ActionListener saveButtonActionListener = e -> {
        File file = new File(exportUrlTextField.getText());
        try (FileWriter writer = new FileWriter(file)) {
            var entrySet = linkTitleMap.entrySet();
            int index = 0;
            for (var entry : entrySet) {
                if (index == entrySet.size() - 1) {
                    writer.write((entry.getKey() + "\n" + entry.getValue()));
                } else {
                    writer.write((entry.getKey() + "\n" + entry.getValue() + "\n"));
                }
                index++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    };

    /////////////////////////////////////////////////////////////////////////////
    public WebCrawler() {
        setTitle("Web Crawler");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setIconImage(icon.getImage());
        setBackground(Color.BLACK);

        initComponents();
        setVisible(true);
    }

    /////////////////////////////////////////////////////////////////////////////
    private void initComponents() {
        addLeftSpacer();
        addLeftLabels();
        addRightComponents();


        runToggleButton.addActionListener(runButtonActionListener);
        exportButton.addActionListener(saveButtonActionListener);
    }

    /////////////////////////////////////////////////////////////////////////////
    private void addLeftSpacer() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(20, 448));
        add(leftPanel);
    }

    /////////////////////////////////////////////////////////////////////////////
    private void addLeftLabels() {
        //labels
        JPanel labelsPanel = new JPanel();
        JLabel startUrlLabel = new JLabel();
        JLabel workersLabel = new JLabel();
        JLabel depthLabel = new JLabel();
        JLabel timeLabel = new JLabel();
        JLabel elapsedLabel = new JLabel();
        JLabel parsedLabel = new JLabel();
        JLabel exportLabel = new JLabel();

        Dimension labelsPanelDimension = new Dimension(120, 448);
        labelsPanel.setPreferredSize(labelsPanelDimension);
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.add(new JPanel(null));


        Dimension labelDimension = new Dimension(120, 32);
        //---- startUrlLabel ----
        startUrlLabel.setText("Start URL:");
        startUrlLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(startUrlLabel);
        labelsPanel.add(new JPanel(null));

        //---- workersLabel ----
        workersLabel.setText("Workers:");
        workersLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(workersLabel);
        labelsPanel.add(new JPanel(null));

        //---- depthLabel ----
        depthLabel.setText(" Maximum depth:");
        depthLabel.setRequestFocusEnabled(false);
        depthLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(depthLabel);
        labelsPanel.add(new JPanel(null));

        //---- timeLabel ----
        timeLabel.setText("Time limit:");
        timeLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(timeLabel);
        labelsPanel.add(new JPanel(null));

        //---- elapsedLabel ----
        elapsedLabel.setText("Elapsed time:");
        elapsedLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(elapsedLabel);
        labelsPanel.add(new JPanel(null));

        //---- parsedLabel ----
        parsedLabel.setText("Parsed Pages:");
        parsedLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(parsedLabel);
        labelsPanel.add(new JPanel(null));

        //---- exportLabel ----
        exportLabel.setText("Export:");
        exportLabel.setPreferredSize(new Dimension(labelDimension));
        labelsPanel.add(exportLabel);
        labelsPanel.add(new JPanel(null));

        add(labelsPanel);
    }

    /////////////////////////////////////////////////////////////////////////////
    private void addRightComponents() {
        //panel containing right components
        JPanel rightPanel = new JPanel();
        JPanel urlPanel = new JPanel();
        JPanel workersPanel = new JPanel();
        JPanel depthPanel = new JPanel();
        JPanel timePanel = new JPanel();
        JPanel elapsedPanel = new JPanel();
        JPanel parsedPanel = new JPanel();
        JPanel exportPanel = new JPanel();
        JLabel secondsLabel = new JLabel();
        //dimension
        Dimension rightPanelDimension = new Dimension(560, 448);
        Dimension buttonDimension = new Dimension(80, 55);
        Dimension verticalPanelDimension = new Dimension(560, 64);

        rightPanel.setPreferredSize(rightPanelDimension);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        //======== urlPanel ========
        urlPanel.setPreferredSize(verticalPanelDimension);
        urlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- urlTextField ----
        urlTextField.setName("UrlTextField");
        urlTextField.setPreferredSize(new Dimension(410, 64));
        urlTextField.setText("https://www.google.com/");
        urlPanel.add(urlTextField);
        //---- runToggleButton ----
        runToggleButton.setText("Run");
        runToggleButton.setPreferredSize(buttonDimension);
        runToggleButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        runToggleButton.setName("RunButton");
        urlPanel.add(runToggleButton);
        rightPanel.add(urlPanel);

        //======== workersPanel ========
        workersPanel.setPreferredSize(verticalPanelDimension);
        workersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- workersTextField ----
        workersTextField.setPreferredSize(new Dimension(520, 64));
        workersPanel.add(workersTextField);

        rightPanel.add(workersPanel);

        //======== depthPanel ========
        depthPanel.setPreferredSize(verticalPanelDimension);
        depthPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- depthTextField ----
        depthTextField.setName("DepthTextField");
        depthTextField.setPreferredSize(new Dimension(430, 64));
        depthPanel.add(depthTextField);
        //---- depthCheckBox ----
        depthCheckBox.setText("text");
        depthCheckBox.setPreferredSize(new Dimension(60, 64));
        depthCheckBox.setName("DepthCheckBox");
        depthPanel.add(depthCheckBox);

        rightPanel.add(depthPanel);

        //======== timePanel ========
        timePanel.setPreferredSize(verticalPanelDimension);
        timePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- timeLimitTextField ----
        timeLimitTextField.setPreferredSize(new Dimension(360, 64));
        timePanel.add(timeLimitTextField);
        //---- secondsLabel ----
        secondsLabel.setText("seconds");
        secondsLabel.setPreferredSize(new Dimension(60, 64));
        timePanel.add(secondsLabel);
        //---- timeLimitCheckBox ----
        timeLimitCheckBox.setText("text");
        timeLimitCheckBox.setPreferredSize(new Dimension(60, 64));
        timePanel.add(timeLimitCheckBox);

        rightPanel.add(timePanel);

        //======== elapsedPanel ========
        elapsedPanel.setPreferredSize(verticalPanelDimension);
        elapsedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- elapsedTimeLabel ----
        elapsedTimeLabel.setText("0:00");
        elapsedTimeLabel.setPreferredSize(new Dimension(60, 64));
        elapsedPanel.add(elapsedTimeLabel);

        rightPanel.add(elapsedPanel);

        //======== parsedPanel ========
        parsedPanel.setPreferredSize(verticalPanelDimension);
        parsedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- parsedPagesLabel ----
        parsedPagesLabel.setText("0");
        parsedPagesLabel.setPreferredSize(new Dimension(60, 64));
        parsedPagesLabel.setName("ParsedLabel");
        parsedPanel.add(parsedPagesLabel);

        rightPanel.add(parsedPanel);

        //======== exportPanel ========
        exportPanel.setPreferredSize(verticalPanelDimension);
        exportPanel.setInheritsPopupMenu(true);
        exportPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        //---- exportUrlTextField ----
        exportUrlTextField.setPreferredSize(new Dimension(410, 64));
        exportUrlTextField.setName("ExportUrlTextField");
        exportUrlTextField.setText("Web Crawler/task/src/crawler/links.txt");
        exportPanel.add(exportUrlTextField);
        //---- exportButton ----
        exportButton.setText("Save");
        exportButton.setName("ExportButton");
        exportButton.setPreferredSize(new Dimension(80, 64));
        exportPanel.add(exportButton);
        rightPanel.add(exportPanel);

        add(rightPanel);
    }

    /////////////////////////////////////////////////////////////////////////////
    public static void parsePage(String link, int linkDepth) throws URISyntaxException {
        String htmlCode = getHtmlCode(link);
        String title = getPageTitle(htmlCode);
        parsedPages++;
        linkTitleMap.put(link, title);
        if (linkDepth < depth) {
            URI uri = new URI(link);
            HashSet<String> links = getPageHtmlLinks(uri, htmlCode);
            for (var l : links) {
                parsePage(l, linkDepth + 1);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    static String getHtmlCode(String link) {
        HttpClient httpClient = HttpClient.newHttpClient();

        URI uri = URI.create(link);

        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "We cannot access the site. Please, try later.";
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    static String getPageTitle(String htmlCode) {
        Matcher titleMatcher = titlePattern.matcher(htmlCode);
        if (titleMatcher.find()) {
            return titleMatcher.group().replaceAll("<[^>]+>", "");
        }
        return "";
    }

    /////////////////////////////////////////////////////////////////////////////
    private static HashSet<String> getPageHtmlLinks(URI mainUri, String htmlCode) {
        HashSet<String> htmlLinks = new HashSet<>();

        Matcher hrefMatcher = linkPattern.matcher(htmlCode);

        Pattern hrefPattern = Pattern.compile("href=", Pattern.CASE_INSENSITIVE);
        while (hrefMatcher.find()) {
            String link = hrefMatcher.group();

            Matcher hrefPropMatcher = hrefPattern.matcher(link);
            if (hrefPropMatcher.find()) {
                link = link.substring(hrefPropMatcher.end() + 1, link.length() - 1);
            }

            if (isAbsoluteLink(link) && isHtmlLink(link)) {
                if (!urlSet.contains(link)) {
                    urlSet.add(link);
                    htmlLinks.add(link);
                }
            } else {
                String absolutePath = completeRelativeUrl(mainUri, link);
                if (isHtmlLink(absolutePath)) {
                    if (!urlSet.contains(absolutePath)) {
                        urlSet.add(absolutePath);
                        htmlLinks.add(absolutePath);
                    }
                }
            }
        }
        return htmlLinks;
    }

    /////////////////////////////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////////////////////////////
    private static boolean isAbsoluteLink(String url) {
        return (url.indexOf("://") > 0 || url.indexOf("//") == 0);
    }

    /////////////////////////////////////////////////////////////////////////////
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