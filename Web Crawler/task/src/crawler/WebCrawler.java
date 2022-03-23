package crawler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    final Dimension dimension = new Dimension(700, 500);
    final ImageIcon icon = new ImageIcon("Web Crawler/task/src/crawler/webCrawler.png");

    SwingWorker<String, String> parserWorker = new SwingWorker<>() {
        @Override
        protected String doInBackground() throws Exception {
            //get url from text field
            String url = urlTextField.getText();
            return null;
        }
    };
    private final transient ActionListener runButtonActionListener = e -> {
        if (runToggleButton.isSelected()) {
            runToggleButton.setSelected(true);
            runToggleButton.setText("Stop");
            parserWorker.execute();
            //get url from text field
            String url = urlTextField.getText();
            int workers = Integer.parseInt(workersTextField.getText());
        } else {
            runToggleButton.setSelected(false);
            runToggleButton.setText("Run");
        }
    };
    private final transient ActionListener saveButtonActionListener = e -> {
        File file = new File(exportUrlTextField.getText());
        try (FileWriter writer = new FileWriter(file)) {
//            for (var entry : UrlExtractor.getUrlTitlesMap().entrySet()) {
//                writer.write(entry.getKey() + "\n" + entry.getValue() + "\n");
//            }
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
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setIconImage(icon.getImage());
        setBackground(Color.BLACK);

        initComponents();
        setVisible(true);
    }


    private void initComponents() {
        addLeftSpacer();
        addLeftLabels();
        addRightComponents();


        runToggleButton.addActionListener(runButtonActionListener);
        exportButton.addActionListener(saveButtonActionListener);
    }

    private void addLeftSpacer() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(20, 448));
        add(leftPanel);
    }

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
        urlTextField.setText("http://info.cern.ch/hypertext/WWW/TheProject.html");
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
        exportPanel.add(exportUrlTextField);
        //---- exportButton ----
        exportButton.setText("Save");
        exportButton.setName("ExportButton");
        exportButton.setPreferredSize(new Dimension(80, 64));
        exportPanel.add(exportButton);
        rightPanel.add(exportPanel);

        add(rightPanel);
    }
}