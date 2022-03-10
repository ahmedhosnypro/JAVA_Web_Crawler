/*
 * Created by JFormDesigner on Thu Mar 10 04:46:50 EET 2022
 */

package crawler;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class ExportPanel extends JPanel {
    public ExportPanel() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        exportLabelPanel = new JPanel();
        hSpacer = new JPanel(null);
        exportLabel = new JLabel();
        exportUrlTextField = new JTextField();
        exportButton = new JButton();

        //======== this ========
        setBackground(Color.black);
        setForeground(Color.white);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setPreferredSize(new Dimension(700, 50));
        setMinimumSize(new Dimension(700, 50));
        setMaximumSize(new Dimension(700, 50));
        setLayout(new BorderLayout());

        //======== exportLabelPanel ========
        {
            exportLabelPanel.setBackground(Color.black);
            exportLabelPanel.setForeground(Color.white);
            exportLabelPanel.setLayout(new FlowLayout());

            //---- hSpacer ----
            hSpacer.setBackground(Color.black);
            hSpacer.setForeground(Color.white);
            exportLabelPanel.add(hSpacer);

            //---- exportLabel ----
            exportLabel.setText("Export:");
            exportLabel.setBackground(Color.black);
            exportLabel.setForeground(Color.white);
            exportLabel.setHorizontalAlignment(SwingConstants.LEFT);
            exportLabel.setMaximumSize(new Dimension(50, 40));
            exportLabel.setMinimumSize(new Dimension(50, 40));
            exportLabel.setPreferredSize(new Dimension(50, 40));
            exportLabelPanel.add(exportLabel);
        }
        add(exportLabelPanel, BorderLayout.WEST);

        //---- exportUrlTextField ----
        exportUrlTextField.setBackground(new Color(0, 125, 132));
        exportUrlTextField.setForeground(Color.white);
        exportUrlTextField.setHorizontalAlignment(SwingConstants.LEFT);
        exportUrlTextField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        exportUrlTextField.setMaximumSize(new Dimension(500, 40));
        exportUrlTextField.setMinimumSize(new Dimension(500, 40));
        exportUrlTextField.setPreferredSize(new Dimension(500, 40));
        exportUrlTextField.setName("ExportUrlTextField");
        exportUrlTextField.setCaretColor(Color.black);
        exportUrlTextField.setMargin(new Insets(2, 10, 2, 2));
        add(exportUrlTextField, BorderLayout.CENTER);

        //---- exportButton ----
        exportButton.setText("Save");
        exportButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        exportButton.setName("ExportButton");
        exportButton.setBackground(Color.pink);
        exportButton.setForeground(Color.white);
        add(exportButton, BorderLayout.EAST);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel exportLabelPanel;
    private JPanel hSpacer;
    private JLabel exportLabel;
    private JTextField exportUrlTextField;
    private JButton exportButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public JTextField getExportUrlTextField() {
        return exportUrlTextField;
    }

    public JButton getExportButton() {
        return exportButton;
    }
}
