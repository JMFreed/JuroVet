package lists;

import main.MainFrame;

public class ListEventHistorical extends List {

    // CONSTRUCTOR
    public ListEventHistorical(MainFrame mainFrame, String[] columnNames, String[][] data) {
        super(mainFrame, columnNames, data);
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(8)); // Hide DocumentGUID
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(2)); // Hide Time
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0)); // Hide EventGUID
    }

}
