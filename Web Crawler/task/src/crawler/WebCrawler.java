package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class WebCrawler extends JFrame {
    private final HtmlCodeViewer htmlCodeViewer = new HtmlCodeViewer();
    private final TitleTable titleTable = new TitleTable();

    final Dimension dimension = new Dimension(850, 500);
    final ImageIcon icon = new ImageIcon("Web Crawler/task/src/crawler/webCrawler.png");


    private final transient ActionListener runButtonActionListener = e -> {
        //get url from text field
        String url = htmlCodeViewer.getUrlTextField().getText();

        //create table model and set it to JTable and update ui
        UrlTitleTableModel urlTitleTableModel = null;
        try {
            urlTitleTableModel = UrlExtractor.collectAllLinkTitles(url);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        assert urlTitleTableModel != null;
        titleTable.getTitlesTable()
                .setModel(urlTitleTableModel);
        titleTable.getTitlesTable().updateUI();

        //update JPanel title
        String htmlCode = UrlExtractor.getHtmlCode(url);
        //htmlCodeViewer.getHtmlTextArea().setText(htmlCode);
        htmlCodeViewer.getTitleLabel().setText(UrlExtractor.getTitle(htmlCode));
    };

    public WebCrawler() {
        setTitle("Web Crawler");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setLayout(new BorderLayout());
        setIconImage(icon.getImage());
        setBackground(Color.BLACK);
        getRootPane().putClientProperty("JRootPane.titleBarBackground", Color.BLACK);
        getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);

        initComponents();
        setVisible(true);
    }

    void initComponents() {
        add(htmlCodeViewer, BorderLayout.NORTH);
        htmlCodeViewer.getRunButton().addActionListener(runButtonActionListener);

        add(titleTable, BorderLayout.CENTER);
    }
}