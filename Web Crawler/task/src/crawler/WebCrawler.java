package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WebCrawler extends JFrame {
    private final HtmlCodeViewer htmlCodeViewer = new HtmlCodeViewer();

    final Dimension dimension = new Dimension(850, 500);
    final ImageIcon icon = new ImageIcon("Web Crawler/task/src/crawler/webCrawler.png");

    private final transient ActionListener runButtonActionListener = e -> {
        htmlCodeViewer.getHtmlTextArea()
                .setText(HTMLCodeDownloader.getCode(htmlCodeViewer.getUrlTextField().getText()));
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
        add(htmlCodeViewer, BorderLayout.CENTER);
        htmlCodeViewer.getRunButton().addActionListener(runButtonActionListener);
    }
}