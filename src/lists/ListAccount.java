package lists;

import main.MainFrame;

public class ListAccount extends List {

    // CONSTRUCTOR
    public ListAccount(MainFrame mainFrame, String[] arrStrColumnNames, String[][] arrStrData) {
        super(mainFrame, arrStrColumnNames, arrStrData);
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0)); // Hide AccountGUID column
    }
}