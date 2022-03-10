/*
 * Created by JFormDesigner on Thu Mar 10 03:37:16 EET 2022
 */

package crawler;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author unknown
 */
public class TitleTable extends JScrollPane {
    public TitleTable() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        titlesTable = new JTable();

        //======== this ========
        setBackground(Color.black);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setForeground(Color.black);
        setFocusable(false);

        //---- titlesTable ----
        titlesTable.setBackground(Color.black);
        titlesTable.setForeground(Color.white);
        titlesTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "URL", "Title"
            }
        ));
        titlesTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        titlesTable.setName("TitlesTable");
        titlesTable.setGridColor(Color.black);
        titlesTable.setEnabled(false);
        setViewportView(titlesTable);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTable titlesTable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


    public JTable getTitlesTable() {
        return titlesTable;
    }
}
