package crawler;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        //implementation 'com.formdev:flatlaf:2.0.2'
        FlatLightLaf.setup(); //setting the look and feel
        JFrame.setDefaultLookAndFeelDecorated(true);

        new WebCrawler();
    }
}
