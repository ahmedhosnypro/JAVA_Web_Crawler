package crawler;

import javax.swing.*;
import java.awt.*;

public class WebCrawler extends JFrame {
    private final HtmlCodeViewer htmlCodeViewer = new HtmlCodeViewer();

    final Dimension dimension = new Dimension(450, 450);
    final ImageIcon icon = new ImageIcon("Web Crawler/task/src/crawler/webCrawler.png");

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
    }
}