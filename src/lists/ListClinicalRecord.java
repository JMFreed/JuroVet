package lists;

import main.MainFrame;

public class ListClinicalRecord extends List {

    // CONSTRUCTOR
    public ListClinicalRecord(MainFrame mainFrame, String[] columnNames, String[][] data) {
        super(mainFrame, columnNames, data);
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0)); // Hide EventGUID
    }
}
