/*
 * Created by JFormDesigner on Tue Mar 08 03:33:34 EET 2022
 */

package crawler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class HtmlCodeViewer extends JPanel {
    public HtmlCodeViewer() {
        initComponents();
    }

    private void run() {
        // TODO add your code here
    }

    private void run(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        urlPanel = new JPanel();
        urlTextField = new JTextField();
        runButton = new JButton();
        textAreaScrollPane = new JScrollPane();
        htmlTextArea = new JTextArea();

        //======== this ========
        setPreferredSize(new Dimension(700, 350));
        setMinimumSize(new Dimension(700, 350));
        setLayout(new BorderLayout(0, 2));

        //======== urlPanel ========
        {
            urlPanel.setMinimumSize(new Dimension(730, 50));
            urlPanel.setPreferredSize(new Dimension(730, 50));
            urlPanel.setBackground(Color.black);
            urlPanel.setForeground(Color.white);
            urlPanel.setName("urlPanel");
            urlPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            urlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

            //---- urlTextField ----
            urlTextField.setName("UrlTextField");
            urlTextField.setCaretColor(new Color(0, 204, 204));
            urlTextField.setForeground(Color.white);
            urlTextField.setSelectedTextColor(Color.lightGray);
            urlTextField.setDisabledTextColor(new Color(153, 153, 153));
            urlTextField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            urlTextField.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
            urlTextField.setPreferredSize(new Dimension(700, 45));
            urlTextField.setMinimumSize(new Dimension(700, 45));
            urlTextField.setHorizontalAlignment(SwingConstants.LEFT);
            urlTextField.setText("https://www.ahmedhosny.com");
            urlTextField.setToolTipText("URL");
            urlTextField.setSelectionEnd(26);
            urlTextField.setSelectionStart(8);
            urlTextField.setBorder(null);
            urlTextField.setBackground(Color.black);
            urlTextField.setMargin(new Insets(0, 5, 0, 0));
            urlPanel.add(urlTextField);

            //---- runButton ----
            runButton.setText("Get text!");
            runButton.setPreferredSize(new Dimension(100, 30));
            runButton.setMaximumSize(new Dimension(100, 30));
            runButton.setMinimumSize(new Dimension(100, 30));
            runButton.setBorder(null);
            runButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            runButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            runButton.setDefaultCapable(false);
            runButton.setName("RunButton");
            runButton.setHorizontalTextPosition(SwingConstants.CENTER);
            runButton.addActionListener(e -> {
			run(e);
			run();
		});
            urlPanel.add(runButton);
        }
        add(urlPanel, BorderLayout.PAGE_START);

        //======== textAreaScrollPane ========
        {
            textAreaScrollPane.setViewportBorder(null);
            textAreaScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            textAreaScrollPane.setMinimumSize(new Dimension(300, 300));
            textAreaScrollPane.setPreferredSize(new Dimension(300, 300));
            textAreaScrollPane.setBackground(new Color(65, 53, 53));
            textAreaScrollPane.setToolTipText("HTML Code");
            textAreaScrollPane.setAutoscrolls(true);
            textAreaScrollPane.setBorder(null);
            textAreaScrollPane.setAlignmentX(5.0F);
            textAreaScrollPane.setAlignmentY(5.0F);

            //---- htmlTextArea ----
            htmlTextArea.setBackground(Color.black);
            htmlTextArea.setToolTipText("HTML code?");
            htmlTextArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            htmlTextArea.setForeground(Color.white);
            htmlTextArea.setTabSize(4);
            htmlTextArea.setText("HTML code?");
            htmlTextArea.setDisabledTextColor(new Color(153, 153, 153));
            htmlTextArea.setSelectedTextColor(Color.lightGray);
            htmlTextArea.setCaretColor(new Color(0, 204, 204));
            htmlTextArea.setLineWrap(true);
            htmlTextArea.setEnabled(false);
            htmlTextArea.setName("HtmlTextArea");
            htmlTextArea.setAlignmentX(5.0F);
            htmlTextArea.setAlignmentY(5.0F);
            textAreaScrollPane.setViewportView(htmlTextArea);
        }
        add(textAreaScrollPane, BorderLayout.CENTER);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        textAreaScrollPane.getVerticalScrollBar().setBackground(Color.pink);
        textAreaScrollPane.getHorizontalScrollBar().setBackground(Color.pink);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel urlPanel;
    private JTextField urlTextField;
    private transient JButton runButton;
    private JScrollPane textAreaScrollPane;
    private JTextArea htmlTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JButton getRunButton() {
        return runButton;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }

    public JTextArea getHtmlTextArea() {
        return htmlTextArea;
    }
}
