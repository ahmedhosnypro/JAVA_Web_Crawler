/*
 * Created by JFormDesigner on Tue Mar 08 03:33:34 EET 2022
 */

package crawler;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class HtmlCodeViewer extends JScrollPane {
    public HtmlCodeViewer() {
        initComponents();
    }

    private void initComponents() {
        getVerticalScrollBar().setBackground(Color.pink);
        getHorizontalScrollBar().setBackground(Color.pink);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textArea = new JTextArea();

        //======== this ========
        setViewportBorder(null);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setMinimumSize(new Dimension(300, 300));
        setPreferredSize(new Dimension(300, 300));
        setBackground(Color.black);
        setToolTipText("HTML Code");
        setAutoscrolls(true);
        setBorder(null);

        //---- textArea ----
        textArea.setBackground(Color.black);
        textArea.setToolTipText("HTML code?");
        textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textArea.setForeground(Color.white);
        textArea.setTabSize(4);
        textArea.setText("HTML code?");
        textArea.setDisabledTextColor(new Color(153, 153, 153));
        textArea.setSelectedTextColor(Color.lightGray);
        textArea.setCaretColor(new Color(0, 204, 204));
        textArea.setLineWrap(true);
        textArea.setEnabled(false);
        textArea.setName("TextArea");
        setViewportView(textArea);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextArea textArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
