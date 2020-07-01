package lists;

import main.MainFrame;

public class ListInvoice extends List {

    // CONSTRUCTOR
    public ListInvoice(MainFrame mainFrame, String[] columnNames, String[][] data) {
        super(mainFrame, columnNames, data);
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0)); // Remove invoiceGUID column
    }
}
