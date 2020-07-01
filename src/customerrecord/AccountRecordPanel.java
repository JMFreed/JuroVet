package customerrecord;

import lists.ListAccount;

public class AccountRecordPanel extends CustomerRecordPanel {

    // FIELDS
    private String name;
    private ListAccount accList;
    private boolean existFlag;

    // CONSTRUCTOR
    AccountRecordPanel(String name) {
        super(name);
        this.name = name;
        this.accList = null;
        this.existFlag = false;
    }

    // ACCESSORS
    @Override
    public String getName() { return name; }
    ListAccount getTable() { return accList; }
    boolean exists() { return existFlag; }

    // MUTATORS
    void makeTable(ListAccount accList) { this.accList = accList; }
    void setExists(boolean exists) { this.existFlag = exists; }
}
