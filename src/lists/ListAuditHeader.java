package lists;

import main.MainFrame;

public class ListAuditHeader extends List {

    // CONSTRUCTOR
    public ListAuditHeader(MainFrame mainFrame, String[] arrStrColumnNames, String[][] arrStrData) {
        super(mainFrame, arrStrColumnNames, arrStrData);
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0)); // Hide HeaderGUID column
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(3)); // Hide InvoiceRef column
    }
}
