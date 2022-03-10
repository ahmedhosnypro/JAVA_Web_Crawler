package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class WebCrawler extends JFrame {
    private final UrlParserPanel htmlCodeViewer = new UrlParserPanel();
    private final TitleTable titleTable = new TitleTable();
    private final ExportPanel exportPanel = new ExportPanel();

    final Dimension dimension = new Dimension(850, 500);
    final ImageIcon icon = new ImageIcon("Web Crawler/task/src/crawler/webCrawler.png");

    SwingWorker<String, String> parserWorker = new SwingWorker<>() {
        @Override
        protected String doInBackground() throws Exception {
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
            htmlCodeViewer.getTitleLabel().setText(UrlExtractor.getTitle(htmlCode));
            return null;
        }
    };
    private final transient ActionListener runButtonActionListener = e -> {
//        parserWorker.execute();
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
        htmlCodeViewer.getTitleLabel().setText(UrlExtractor.getTitle(htmlCode));
    };
    private final transient ActionListener saveButtonActionListener = e -> {
        File file = new File(exportPanel.getExportUrlTextField().getText());
        try (FileWriter writer = new FileWriter(file)) {
            for (var entry : UrlExtractor.getUrlTitlesMap().entrySet()) {
                writer.write(entry.getKey() + "\n" + entry.getValue() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        add(exportPanel, BorderLayout.SOUTH);
        exportPanel.getExportButton().addActionListener(saveButtonActionListener);
    }
}