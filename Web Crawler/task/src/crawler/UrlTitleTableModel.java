package crawler;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Objects;

public class UrlTitleTableModel extends AbstractTableModel {
    ArrayList<String> columns;
    ArrayList<ArrayList<String>> data;

    public UrlTitleTableModel(ArrayList<String> columns, ArrayList<ArrayList<String>> data) {
        this.columns = columns;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        if (Objects.nonNull(columns)) {
            return data.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (Objects.nonNull(columns)) {
            return columns.size();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (Objects.nonNull(data) && rowIndex < data.size() && columnIndex < columns.size()) {
            return data.get(rowIndex).get(columnIndex);
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (Objects.nonNull(columns)) {
            return columns.get(columnIndex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (Objects.nonNull(data)) {
            data.get(rowIndex).set(columnIndex, (String) aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
}
