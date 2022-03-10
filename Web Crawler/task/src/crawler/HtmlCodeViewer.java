/*
 * Created by JFormDesigner on Tue Mar 08 03:33:34 EET 2022
 */

package crawler;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class HtmlCodeViewer extends JPanel {
    public HtmlCodeViewer() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        urlTextField = new JTextField();
        runButton = new JButton();
        title = new JLabel();
        titleLabel = new JLabel();
        textAreaScrollPane = new JScrollPane();
        htmlTextArea = new JTextArea();

        //======== this ========
        setPreferredSize(new Dimension(700, 50));
        setMinimumSize(new Dimension(700, 50));
        setMaximumSize(new Dimension(700, 50));
        setBackground(Color.black);
        setForeground(Color.white);
        setLayout(new GridLayout(2, 2));

        //---- urlTextField ----
        urlTextField.setName("UrlTextField");
        urlTextField.setCaretColor(new Color(0, 204, 204));
        urlTextField.setForeground(Color.white);
        urlTextField.setDisabledTextColor(new Color(153, 153, 153));
        urlTextField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        urlTextField.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
        urlTextField.setPreferredSize(new Dimension(700, 45));
        urlTextField.setMinimumSize(new Dimension(700, 45));
        urlTextField.setHorizontalAlignment(SwingConstants.LEFT);
        urlTextField.setText("http://info.cern.ch/hypertext/WWW/TheProject.html");
        urlTextField.setToolTipText("URL");
        urlTextField.setSelectionEnd(49);
        urlTextField.setSelectionStart(7);
        urlTextField.setBorder(null);
        urlTextField.setBackground(Color.black);
        urlTextField.setMargin(new Insets(0, 5, 0, 0));
        urlTextField.setMaximumSize(new Dimension(700, 50));
        add(urlTextField);

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
        runButton.setFocusable(false);
        runButton.setBackground(Color.pink);
        runButton.setForeground(Color.white);
        add(runButton);

        //---- title ----
        title.setText("Title:    ");
        title.setMaximumSize(new Dimension(50, 50));
        title.setMinimumSize(new Dimension(50, 50));
        title.setPreferredSize(new Dimension(37, 50));
        title.setForeground(Color.white);
        add(title);

        //---- titleLabel ----
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        titleLabel.setBorder(null);
        titleLabel.setPreferredSize(new Dimension(720, 50));
        titleLabel.setMinimumSize(new Dimension(720, 50));
        titleLabel.setMaximumSize(new Dimension(0, 50));
        titleLabel.setName("TitleLabel");
        titleLabel.setForeground(Color.white);
        add(titleLabel);

        //======== textAreaScrollPane ========
        {
            textAreaScrollPane.setViewportBorder(null);
            textAreaScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            textAreaScrollPane.setMinimumSize(new Dimension(730, 300));
            textAreaScrollPane.setPreferredSize(new Dimension(730, 300));
            textAreaScrollPane.setBackground(new Color(65, 53, 53));
            textAreaScrollPane.setToolTipText("HTML Code");
            textAreaScrollPane.setAutoscrolls(true);
            textAreaScrollPane.setBorder(null);
            textAreaScrollPane.setAlignmentX(5.0F);
            textAreaScrollPane.setAlignmentY(5.0F);
            textAreaScrollPane.setVisible(false);

            //---- htmlTextArea ----
            htmlTextArea.setBackground(Color.black);
            htmlTextArea.setToolTipText("HTML code?");
            htmlTextArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            htmlTextArea.setForeground(Color.white);
            htmlTextArea.setTabSize(4);
            htmlTextArea.setText("HTML code?");
            htmlTextArea.setDisabledTextColor(new Color(153, 153, 153));
            htmlTextArea.setCaretColor(new Color(0, 204, 204));
            htmlTextArea.setLineWrap(true);
            htmlTextArea.setName("HtmlTextArea");
            htmlTextArea.setAlignmentX(5.0F);
            htmlTextArea.setAlignmentY(5.0F);
            htmlTextArea.setBorder(null);
            htmlTextArea.setMargin(new Insets(5, 5, 5, 5));
            htmlTextArea.setSelectedTextColor(new Color(102, 255, 255));
            htmlTextArea.setEnabled(false);
            textAreaScrollPane.setViewportView(htmlTextArea);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        textAreaScrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
        textAreaScrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField urlTextField;
    private transient JButton runButton;
    private JLabel title;
    private JLabel titleLabel;
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

    public JLabel getTitleLabel() {
        return titleLabel;
    }

}
