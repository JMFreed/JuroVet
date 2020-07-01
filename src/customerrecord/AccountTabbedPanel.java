package customerrecord;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import lists.*;
import main.MainFrame;
import objects.*;
import sprocs.*;

class AccountTabbedPanel extends JPanel {

    // FIELDS
	private MainFrame mainFrame;
    private AccountPane accountPane;
    private JTabbedPane tabbedPane;
    private String accountGUID;
    private final CustomerRecordPanel clinRecPnl = new CustomerRecordPanel("clinRecPnl");
    private final CustomerRecordPanel evtHistPnl = new CustomerRecordPanel("evtHistPnl");
    private final CustomerRecordPanel evtFuturePnl = new CustomerRecordPanel("evtFuturePnl");
    private final CustomerRecordPanel clientNotesPnl = new CustomerRecordPanel("clientNotesPnl");
    private final CustomerRecordPanel invHistPnl = new CustomerRecordPanel("invHistPnl");
    private final CustomerRecordPanel estHistPnl = new CustomerRecordPanel("estHistPnl");
    private final CustomerRecordPanel keptAtPnl = new CustomerRecordPanel("keptAtPnl");
    private final CustomerRecordPanel insurancePnl = new CustomerRecordPanel("insurancePnl");
    private final Dimension tblDim = new Dimension(600, 200);

    // CONSTRUCTOR
    AccountTabbedPanel(MainFrame mainFrame, AccountPane accountPane, String accountGUID) {

        super(new GridLayout(1,1));
        this.mainFrame = mainFrame;
        this.accountPane = accountPane;
        this.accountGUID = accountGUID;
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clinical Record", clinRecPnl);
        tabbedPane.addTab("Event History", evtHistPnl);
        tabbedPane.addTab("Future Events", evtFuturePnl);
        tabbedPane.addTab("Client Notes", clientNotesPnl);
        tabbedPane.addTab("Invoice History", invHistPnl);
        tabbedPane.addTab("Estimate History", estHistPnl);
        tabbedPane.addTab("Kept At", keptAtPnl);
        tabbedPane.addTab("Insurance", insurancePnl);

        clinRecPnl.makeTable(populateClinicalRecords());
        clinRecPnl.setExists(true);
        clinRecPnl.add(clinRecPnl.getTable());

        CustomerRecordPanel[] panels = { clinRecPnl, evtHistPnl, evtFuturePnl, clientNotesPnl,
                invHistPnl, estHistPnl, keptAtPnl, insurancePnl };

        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addChangeListener(e -> {
            CustomerRecordPanel selectedPanel = (CustomerRecordPanel) tabbedPane.getSelectedComponent();
            if (!selectedPanel.exists()) {
                if (selectedPanel == evtHistPnl) { selectedPanel.makeTable(populateEventHistory()); }
                if (selectedPanel == evtFuturePnl) { selectedPanel.makeTable(populateEventFuture()); }
                if (selectedPanel == clientNotesPnl) { selectedPanel.makeTable(populateAccountNotes()); }
                if (selectedPanel == invHistPnl) { selectedPanel.makeTable(populateInvoiceHistory()); }
                if (selectedPanel == estHistPnl) { selectedPanel.makeTable(populateInvoiceHistory()); }
                if (selectedPanel == keptAtPnl) { selectedPanel.makeTable(populateAccountXAddresses()); }
                if (selectedPanel == insurancePnl) { selectedPanel.makeTable(populateFinancialHistory()); }
            }
            for (CustomerRecordPanel panel : panels) {
                panel.setMinimumSize(new Dimension(800,300));
                panel.setVisible(false);
            }
            setButtons(selectedPanel);
            selectedPanel.setVisible(true);
        });
    }
    
    private void setButtons(CustomerRecordPanel selectedPanel) {
        if (accountPane.getNewButtonActionListener().length > 0) {
            accountPane.getNewButton().removeActionListener(accountPane.getNewButtonActionListener()[0]); }
        if (accountPane.getEditButtonActionListener().length > 0) {
            accountPane.getEditButton().removeActionListener(accountPane.getEditButtonActionListener()[0]); }
        if (selectedPanel == clinRecPnl) {
            accountPane.getNewButton().setText("New Invoice...");
            accountPane.getNewButton().addActionListener(e -> System.out.println("New Invoice button clicked"));
            accountPane.getEditButton().setText("Edit Account...");
            
//            accountPane.getEditButton().addActionListener(e -> {
//                String[] accountDetails = spsAccountByGUID.findAccountByGUID(mainFrame, accountPane.getCusAccDetailsPnl().getAccDetails()[0]);
//                try { new EditFrame(accountPane.getMainFrame(), accountDetails); }
//                catch (IOException ioe) { ioe.printStackTrace(); } });
        }
        if (selectedPanel == evtHistPnl) {
            accountPane.getNewButton().setText("Search Event...");
            accountPane.getNewButton().addActionListener(e -> System.out.println("Search Event button clicked"));
            accountPane.getEditButton().setText("Edit Event...");
            accountPane.getEditButton().addActionListener(e -> System.out.println("Edit Event button clicked"));
        }
        if (selectedPanel == evtFuturePnl) {
            accountPane.getNewButton().setText("New Event...");
            accountPane.getNewButton().addActionListener(e -> System.out.println("New Event button clicked"));
            accountPane.getEditButton().setText("Edit Event...");
            accountPane.getEditButton().addActionListener(e -> System.out.println("Edit Event button clicked"));
        }
        if (selectedPanel == clientNotesPnl) {
            accountPane.getNewButton().setText("New Note...");
            accountPane.getNewButton().addActionListener(e1 -> System.out.println("New note button clicked"));
            accountPane.getEditButton().setText("Edit Note...");
            accountPane.getEditButton().addActionListener(e1 -> System.out.println("Edit note button clicked"));
        }
        if (selectedPanel == invHistPnl) {
            accountPane.getNewButton().setText("Search Invoice...");
            accountPane.getNewButton().addActionListener(e1 -> System.out.println("Search Invoice button clicked"));
            accountPane.getEditButton().setText("Edit Invoice...");
            accountPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Invoice button clicked"));
        }
        if (selectedPanel == estHistPnl) {
            accountPane.getNewButton().setVisible(true);
            accountPane.getEditButton().setVisible(true);
        }
        if (selectedPanel == keptAtPnl) {
            accountPane.getNewButton().setText("New Address...");
            accountPane.getNewButton().addActionListener(e1 -> System.out.println("New Address button clicked"));
            accountPane.getEditButton().setText("Edit Address...");
            accountPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Address button clicked"));
        }
        if (selectedPanel == insurancePnl) {
            accountPane.getNewButton().setText("New Insurance...");
            accountPane.getNewButton().addActionListener(e1 -> System.out.println("New Insurance button clicked"));
            accountPane.getEditButton().setText("Edit Insurance...");
            accountPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Insurance button clicked"));
        }
    }

    // ACCESSORS
    private String getAccGUID() { return accountGUID; }
    JTabbedPane getTabbedPane() { return tabbedPane; }
    CustomerRecordPanel getClinRecPnl() { return clinRecPnl; }

    CustomerRecordPanel[] getCusRecPnls() {
        return new CustomerRecordPanel[] { clinRecPnl, evtHistPnl, evtFuturePnl, clientNotesPnl,
                invHistPnl, estHistPnl, keptAtPnl, insurancePnl }; }

    // MUTATORS
    void setAccGUID(String accGUID) { this.accountGUID = accGUID; }

    // POPULATE CLINICAL RECORDS TABLE
    ListClinicalRecord populateClinicalRecords() {
        ArrayList<ObjClinicalRecord> clinRecords = spsClinicalRecords.findClinicalRecordOrders(mainFrame, accountGUID);
        String[] columns = { "Order Detail GUID", "Date", "User", "Details", "Invoiced", "Printed", "Line Gross", "Sub Total" };
        String[][] data = new String[clinRecords.size()][columns.length];
        int j = 0;
        while ( j < clinRecords.size() ) {
            for (ObjClinicalRecord clinRec : clinRecords) {
                data[j][0] = clinRec.getOrderDetailGUID();
                data[j][1] = clinRec.getDateStart();
                data[j][2] = clinRec.getEmployee();
                data[j][3] = clinRec.getOrderDetailText();
                data[j][4] = clinRec.getInvoiceDate();
                data[j][5] = clinRec.getPrinted();
                data[j][6] = clinRec.getGrossAmount();
                data[j][7] = clinRec.getOrderGrossAmount();
                j++;
            }
        }
        ListClinicalRecord clinRec = new ListClinicalRecord(mainFrame, columns, data);
        clinRec.getTable().setPreferredScrollableViewportSize(tblDim);
        return clinRec;
    }

    // CREATE 2D ARRAY OF HISTORICAL EVENTS DATA
    private String[][] makeEventData(ArrayList<ObjEventHistorical> events, String[] columns) {
        String[][] data = new String[events.size()][columns.length];
        int j=0;
        while ( j<events.size() ) {
            for (ObjEventHistorical event : events) {
                data[j][0] = event.getEvtEventGUID();
                data[j][1] = event.getEvtStartDate();
                data[j][2] = event.getEvtStartTime();
                data[j][3] = event.getEvtAccount();
                data[j][4] = event.getEvtRegarding();
                data[j][5] = event.getEvtEventType();
                data[j][6] = event.getEvtStatus();
                data[j][7] = event.getEvtForUserID();
                data[j][8] = event.getEvtDocumentGUID();
                j++;
            }
        }
        return data;
    }

    // CREATE 2D ARRAY OF AUDIT HEADER DATA
    private String[][] makeAuditData(ArrayList<ObjAuditHeader> auditHeaders, String[] columns) {
        String[][] data = new String[auditHeaders.size()][columns.length];
        int j = 0;
        while ( j < auditHeaders.size() ) {
            for (ObjAuditHeader objAuditHeader : auditHeaders) {
                data[j][0] = objAuditHeader.getHeaderGUID();
                data[j][1] = objAuditHeader.getTranType();
                data[j][2] = objAuditHeader.getTranDate();
                data[j][3] = objAuditHeader.getInvoiceRef();
                data[j][4] = objAuditHeader.getInvoiceRefGUID();
                data[j][5] = objAuditHeader.getDetails();
                data[j][6] = objAuditHeader.getGrossAmount();
                data[j][7] = objAuditHeader.getOutstanding();
                j++;
            }
        }
        return data;
    }

    // POPULATE EVENT HISTORY TABLE
    private ListEventHistorical populateEventHistory() {
        ArrayList<ObjEventHistorical> eventHistoricals = spsEventsByAccountGUID.findHistoricalEvents(mainFrame, this.getAccGUID());
        String[] columns = { "EventGUID", "Date", "Time", "Account", "Regarding",
                "EventType", "Status", "User", "DocumentGUID" };
        String[][] data = makeEventData(eventHistoricals, columns);
        ListEventHistorical eventHist = new ListEventHistorical(mainFrame, columns, data);
        eventHist.getTable().setPreferredScrollableViewportSize(tblDim);
        return eventHist;
    }

    // POPULATE FUTURE EVENTS TABLE
    private ListEventHistorical populateEventFuture() {
        ArrayList<ObjEventHistorical> eventFuture = spsEventsByAccountGUID.findFutureEvents(mainFrame, this.getAccGUID());
        String[] columns = { "EventGUID", "Date", "Time", "Account", "Regarding",
                "EventType", "Status", "User", "DocumentGUID" };
        String[][] data = makeEventData(eventFuture, columns);
        ListEventHistorical eventHist = new ListEventHistorical(mainFrame, columns, data);
        eventHist.getTable().setPreferredScrollableViewportSize(tblDim);
        return eventHist;
    }

    // POPULATE ACCOUNT NOTES TABLE
    private ListEventHistorical populateAccountNotes() {
        ArrayList<ObjEventHistorical> customerNotes = spsEventsByAccountGUID.findAccountNotes(mainFrame, this.getAccGUID());
        String[] columns = { "EventGUID", "Date", "Time", "Account", "Regarding",
                "EventType", "Status", "User", "DocumentGUID" };
        String[][] data = makeEventData(customerNotes, columns);
        ListEventHistorical eventHist = new ListEventHistorical(mainFrame, columns, data);
        eventHist.getTable().setPreferredScrollableViewportSize(tblDim);
        return eventHist;
    }

    // POPULATE INVOICE HISTORY TABLE
    private ListInvoice populateInvoiceHistory() {
        ArrayList<ObjInvoice> invoiceList = spsInvoices.findInvoicesByAccountGUID(mainFrame, this.getAccGUID());
        String[] columns = { "Date", "Invoice Reference", "Gross", "VAT", "Net", "Printed"};
        String[][] data = new String[invoiceList.size()][columns.length];
        int j = 0;
        while ( j < invoiceList.size() ) {
            for (ObjInvoice objInvoice : invoiceList) {
                data[j][0] = objInvoice.getInvDate();
                data[j][1] = objInvoice.getInvRef();
                data[j][2] = objInvoice.getInvGross();
                data[j][3] = objInvoice.getInvVAT();
                data[j][4] = objInvoice.getInvNet();
                data[j][5] = objInvoice.getInvPrinted();
                j++;
            }
        }
        ListInvoice invHist = new ListInvoice(mainFrame, columns, data);
        invHist.getTable().setPreferredScrollableViewportSize(tblDim);
        return invHist;
    }

    // POPULATE TABLE OF ADDRESSES THE ACCOUNT HAS BEEN KEPT AT PREVIOUSLY
    private ListAddress populateAccountXAddresses() {
        ArrayList<ObjAddress> xAddressList = spsAddress.findAccountXAddresses(mainFrame, this.getAccGUID());
        String[] columns = { "AddressGUID", "Address", "Postcode" };
        String[][] data = new String[xAddressList.size()][columns.length];
        int j = 0;
        while ( j < xAddressList.size() ) {
            for (ObjAddress objAddress : xAddressList) {
                data[j][0] = objAddress.getAddressGUID();
                data[j][1] = objAddress.getAddressFull();
                data[j][2] = objAddress.getPostcode();
                j++;
            }
        }
        ListAddress addList = new ListAddress(keptAtPnl, columns, data);
        addList.getTable().setPreferredScrollableViewportSize(tblDim);
        return addList;
    }

    private ListAuditHeader populateFinancialHistory() {
        ArrayList<ObjAuditHeader> outAudHeadList = spsAuditHeader.getPaidAuditHeaders(mainFrame, this.getAccGUID());
        String[] columns = { "HeaderGUID", "Transaction Type", "Transaction Date", "Invoice Reference",
                "Invoice Ref GUID", "Details", "Gross Amount", "Debit", "Credit", "Outstanding" };
        String[][] data = makeAuditData(outAudHeadList, columns);
        ListAuditHeader finHist = new ListAuditHeader(mainFrame, columns, data);
        finHist.getTable().setPreferredScrollableViewportSize(tblDim);
        return finHist;
    }

}
