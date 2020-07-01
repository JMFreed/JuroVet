package customerrecord;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lists.ListAccount;
import lists.ListAddress;
import lists.ListAuditHeader;
import lists.ListCustomerGroup;
import lists.ListEventHistorical;
import main.MainFrame;
import objects.ObjAccount;
import objects.ObjAddress;
import objects.ObjAuditHeader;
import objects.ObjCustomerGroup;
import objects.ObjEventHistorical;
import sprocs.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

class CustomerTabbedPanel extends JPanel {

    private MainFrame mainFrame;
    private CustomerRecordFrame frame;
    private CustomerPane customerPane;
    private JTabbedPane tabbedPane;
    private String[] customerDetails;
    private final AccountRecordPanel accRecPnl = new AccountRecordPanel("accRecPnl");
    private final CustomerRecordPanel evtHistPnl = new CustomerRecordPanel("evtHistPnl");
    private final CustomerRecordPanel evtFuturePnl = new CustomerRecordPanel("evtFuturePnl");
    private final CustomerRecordPanel groupPnl = new CustomerRecordPanel("groupPnl");
    private final CustomerRecordPanel cusNotesPnl = new CustomerRecordPanel("cusNotesPnl");
    private final CustomerRecordPanel cusXAddressPnl = new CustomerRecordPanel("cusXAddressPnl");
    private final CustomerRecordPanel outMonPnl = new CustomerRecordPanel("outMonPnl");
    private final CustomerRecordPanel finHistPnl = new CustomerRecordPanel("finHistPnl");
    private final Dimension tblDim = new Dimension(600, 200);

    CustomerTabbedPanel(MainFrame mainFrame, CustomerRecordFrame frame, CustomerPane customerPane, String cusGUID) {

        super(new GridLayout(1, 1));
        this.mainFrame = mainFrame;
        this.frame = frame;
        this.customerPane = customerPane;
        try {
			this.customerDetails = spsCustomer.findCustomerByGUID(mainFrame, cusGUID);
		} catch (SQLException | ParseException e1) {
			e1.printStackTrace();
		}
        this.tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Account Records", accRecPnl);
        tabbedPane.addTab("Event History", evtHistPnl);
        tabbedPane.addTab("Future Events", evtFuturePnl);
        tabbedPane.addTab("Groups", groupPnl);
        tabbedPane.addTab("Client Notes", cusNotesPnl);
        tabbedPane.addTab("Previous Addresses", cusXAddressPnl);
        tabbedPane.addTab("Outstanding Monies", outMonPnl);
        tabbedPane.addTab("Financial History", finHistPnl);

        accRecPnl.makeTable(populateAccountRecords(cusGUID));
        accRecPnl.setExists(true);
        accRecPnl.add(accRecPnl.getTable());


        JPanel[] panels = {accRecPnl, evtHistPnl, evtFuturePnl, groupPnl,
                cusNotesPnl, cusXAddressPnl, outMonPnl, finHistPnl};

        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addChangeListener(e -> {
            CustomerRecordPanel selectedPanel = (CustomerRecordPanel) tabbedPane.getSelectedComponent();
            if (!selectedPanel.exists()) {
                if (selectedPanel == evtHistPnl) { selectedPanel.makeTable(populateEventHistory(cusGUID)); }
                if (selectedPanel == evtFuturePnl) { selectedPanel.makeTable(populateEventFuture(cusGUID)); }
                if (selectedPanel == groupPnl) { selectedPanel.makeTable(populateCustomerGroups(cusGUID)); }
                if (selectedPanel == cusNotesPnl) { selectedPanel.makeTable(populateCustomerNotes(cusGUID)); }
                if (selectedPanel == cusXAddressPnl) { selectedPanel.makeTable(populateCustomerXAddresses(cusGUID)); }
                if (selectedPanel == outMonPnl) { selectedPanel.makeTable(populateOutstandingMonies(cusGUID)); }
                if (selectedPanel == finHistPnl) { selectedPanel.makeTable(populateFinancialHistory(cusGUID)); }
            }
            for (JPanel panel : panels) {
                panel.setMinimumSize(new Dimension(800, 300));
                panel.setVisible(false);
            }
            setButtons(selectedPanel);
            selectedPanel.setVisible(true);
        });

    }

    private void setButtons(CustomerRecordPanel selectedPanel) {
        if (customerPane.getNewButtonActionListener().length > 0) {
            customerPane.getNewButton().removeActionListener(customerPane.getNewButtonActionListener()[0]); }
        if (customerPane.getEditButtonActionListener().length > 0) {
            customerPane.getEditButton().removeActionListener(customerPane.getEditButtonActionListener()[0]); }
        if (selectedPanel == accRecPnl) {
            customerPane.getNewButton().setText("New Account...");
//            customerPane.getNewButton().addActionListener(e -> {
//                try { mainFrame.setNewAccountForm
//                            (new NewAccountForm(mainFrame, new NewAccountFrame(mainFrame, customerDetails))); } 
//                catch (IOException ex) { ex.printStackTrace(); }
//            });
            customerPane.getEditButton().setText("Edit Customer...");
//            customerPane.getEditButton().addActionListener(e ->
//                    mainFrame.showSelectedFrame(new EditCustomerFrame(mainFrame, frame, customerDetails)));
        }
        if (selectedPanel == evtHistPnl) {
            customerPane.getNewButton().setText("Search Event...");
            customerPane.getNewButton().addActionListener(e -> System.out.println("Search Event button clicked"));
            customerPane.getEditButton().setText("Edit Event...");
            customerPane.getEditButton().addActionListener(e -> System.out.println("Edit Event button clicked"));
        }
        if (selectedPanel == evtFuturePnl) {
            customerPane.getNewButton().setText("New Event...");
            customerPane.getNewButton().addActionListener(e -> System.out.println("New Event button clicked"));
            customerPane.getEditButton().setText("Edit Event...");
            customerPane.getEditButton().addActionListener(e -> System.out.println("Edit Event button clicked"));
        }
        if (selectedPanel == groupPnl) {
            customerPane.getNewButton().setText("New Group...");
            customerPane.getNewButton().addActionListener(e1 -> System.out.println("New Group button clicked"));
            customerPane.getEditButton().setText("Edit Group...");
            customerPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Group button clicked"));
        }
        if (selectedPanel == cusNotesPnl) {
            customerPane.getNewButton().setText("New Note...");
            customerPane.getNewButton().addActionListener(e1 -> System.out.println("New Note button clicked"));
            customerPane.getEditButton().setText("Edit Note...");
            customerPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Note button clicked"));
        }
        if (selectedPanel == cusXAddressPnl) {
            customerPane.getNewButton().setText("New Address...");
            customerPane.getNewButton().addActionListener(e1 -> System.out.println("New Address button clicked"));
            customerPane.getEditButton().setText("Edit Address...");
            customerPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Address button clicked"));
        }
        if (selectedPanel == outMonPnl) {
            customerPane.getNewButton().setText("New Invoice...");
            customerPane.getNewButton().addActionListener(e1 -> System.out.println("New Invoice button clicked"));
            customerPane.getEditButton().setText("Edit Invoice...");
            customerPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Invoice button clicked"));
        }
        if (selectedPanel == finHistPnl) {
            customerPane.getNewButton().setText("Search Invoice...");
            customerPane.getNewButton().addActionListener(e1 -> System.out.println("Search Invoice button clicked"));
            customerPane.getEditButton().setText("Edit Invoice...");
            customerPane.getEditButton().addActionListener(e1 -> System.out.println("Edit Invoice button clicked"));
        }
    }
    
    void refreshAccRecPnl(String cusGUID) {
        accRecPnl.remove(accRecPnl.getTable());
        accRecPnl.makeTable(populateAccountRecords(cusGUID));
        accRecPnl.setExists(true);
        accRecPnl.add(accRecPnl.getTable());
        addCusRecPnlActionListener();
    }

    private void addCusRecPnlActionListener() {
        getAccRecPnl().getTable().getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    AccountPane accountPane = mainFrame.getCustomerRecordFrame().getCusAccPane();
                    AccountDetailsPanel cusAccDetailsPnl = accountPane.getCusAccDetailsPnl();
                    int selectedRow = getAccRecPnl().getTable().getTable().getSelectedRow();
                    String accountGUID = getAccRecPnl().getTable().getData()[selectedRow][0];
                    String[] accountDetails = null;
					try {
						accountDetails = spsAccount.findAccountByGUID(mainFrame, accountGUID);
					} catch (SQLException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    cusAccDetailsPnl.setAccDetails(accountDetails);
                    accountPane.getCusAccTabbedPnl().setAccGUID(accountGUID);
                    cusAccDetailsPnl.populateAccDetailsPnl(cusAccDetailsPnl.getFields());
                    CustomerRecordPanel[] panels = accountPane.getCusAccTabbedPnl().getCusRecPnls();
                    for (CustomerRecordPanel panel : panels) {
                        panel.setExists(false);
                        if (panel.getTable() != null) {
                            panel.remove(panel.getTable());
                        }
                    }
                    CustomerRecordPanel cusClinRecPnl = accountPane.getCusAccTabbedPnl().getClinRecPnl();
                    cusClinRecPnl.makeTable(accountPane.getCusAccTabbedPnl().populateClinicalRecords());
                    cusClinRecPnl.setExists(true);
                    cusClinRecPnl.getTable().setMinimumSize(new Dimension(800, 300));
                    accountPane.getCusAccTabbedPnl().getTabbedPane().setSelectedIndex(0);
                    mainFrame.getCustomerRecordFrame().getTabbedPane().setSelectedIndex(1);
                }
            }
        });
    }

    private ListAccount populateAccountRecords(String cusGUID) {
        ArrayList<ObjAccount> accountList = spsAccount.findAccountsByCustomerGUID(mainFrame, cusGUID);
        String[] columns = {"AccountGUID", "Animal", "Species", "Breed", "Sex", "DateBirth", "Balance"};
        String[][] data = new String[accountList.size()][columns.length];
        int j = 0;
        while ( j < accountList.size() ) {
            for (ObjAccount objAccount : accountList) {
                data[j][0] = objAccount.getAccAccountGUID();
                data[j][1] = objAccount.getAccAnimalName();
                data[j][2] = objAccount.getAccSpecies();
                data[j][3] = objAccount.getAccBreed();
                data[j][4] = objAccount.getAccAnimalSex();
                data[j][5] = objAccount.getAccDateBirth();
                data[j][6] = objAccount.getAccBalance();
                j++;
            }
        }
        ListAccount accList = new ListAccount(mainFrame, columns, data);
        accList.getTable().setPreferredScrollableViewportSize(tblDim);
        return accList;
    }

    private ListEventHistorical populateEventHistory(String cusGUID) {
        ArrayList<ObjEventHistorical> eventHistoricals = spsEventsByCustomerGUID.findHistoricalEvents(mainFrame, cusGUID);
        return makeListEvents(eventHistoricals);
    }

    private ListEventHistorical populateEventFuture(String cusGUID) {
        ArrayList<ObjEventHistorical> eventFuture = spsEventsByCustomerGUID.findFutureEvents(mainFrame, cusGUID);
        return makeListEvents(eventFuture);
    }

    private ListEventHistorical populateCustomerNotes(String cusGUID) {
        ArrayList<ObjEventHistorical> customerNotes = spsEventsByCustomerGUID.findCustomerNotes(mainFrame, cusGUID);
        return makeListEvents(customerNotes);
    }

    private ListAddress populateCustomerXAddresses(String cusGUID) {
        ArrayList<ObjAddress> xAddressList = spsAddress.findCustomerXAddresses(mainFrame, cusGUID);
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
        ListAddress adrList = new ListAddress(cusXAddressPnl, columns, data);
        adrList.getTable().setPreferredScrollableViewportSize(tblDim);
        return adrList;
    }

    private ListAuditHeader populateOutstandingMonies(String cusGUID) {
        ArrayList<ObjAuditHeader> outAudHeadList = spsAuditHeader.getOutstandingAuditHeaders(mainFrame, cusGUID);
        return makeListAuditHeaders(outAudHeadList);
    }

    private ListAuditHeader populateFinancialHistory(String cusGUID) {
        ArrayList<ObjAuditHeader> finHistList = spsAuditHeader.getPaidAuditHeaders(mainFrame, cusGUID);
        return makeListAuditHeaders(finHistList);
    }

    private ListCustomerGroup populateCustomerGroups(String cusGUID) {
        ArrayList<ObjCustomerGroup> cusGroupList = spsCustomer.getCustomerGroups(mainFrame, cusGUID);
        String[] columns = { "Group Name" };
        String[][] data = new String[cusGroupList.size()][columns.length];
        int j = 0;
        while ( j < cusGroupList.size() ) {
            for (ObjCustomerGroup cusGroup : cusGroupList) {
                data[j][0] = cusGroup.getGroupName();
                j++;
            }
        }
        ListCustomerGroup cusGroups = new ListCustomerGroup(mainFrame, columns, data);
        cusGroups.getTable().setPreferredScrollableViewportSize(tblDim);
        return cusGroups;
    }

    // ACCESSORS
    AccountRecordPanel getAccRecPnl() { return accRecPnl; }

    // MAKE LIST OF HISTORICAL EVENTS
    private ListEventHistorical makeListEvents(ArrayList<ObjEventHistorical> eventList) {
        String[] columns = { "EventGUID", "Date", "Time", "Account", "Regarding",
                "EventType", "Status", "User", "DocumentGUID" };
        String[][] data = new String[eventList.size()][columns.length];
        int j = 0;
        while ( j < eventList.size() ) {
            for (ObjEventHistorical event : eventList) {
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
        ListEventHistorical events = new ListEventHistorical(mainFrame, columns, data);
        events.getTable().setPreferredScrollableViewportSize(tblDim);
        return events;
    }

    // MAKE LIST OF AUDIT HEADERS
    private ListAuditHeader makeListAuditHeaders(ArrayList<ObjAuditHeader> auditHeaderList) {
        String[] columns = { "HeaderGUID", "Transaction Type", "Transaction Date", "Invoice Reference",
                "Invoice Ref GUID", "Details", "Gross Amount", "Debit", "Credit", "Outstanding" };
        String[][] data = new String[auditHeaderList.size()][columns.length];
        int j = 0;
        while ( j < auditHeaderList.size() ) {
            for (ObjAuditHeader objAuditHeader : auditHeaderList) {
                data[j][0] = objAuditHeader.getHeaderGUID();
                data[j][1] = objAuditHeader.getTranType();
                data[j][2] = objAuditHeader.getTranDate();
                data[j][3] = objAuditHeader.getInvoiceRef();
                data[j][4] = objAuditHeader.getInvoiceRefGUID();
                data[j][5] = objAuditHeader.getDetails();
                data[j][6] = objAuditHeader.getGrossAmount();
                data[j][7] = objAuditHeader.getDebit();
                data[j][8] = objAuditHeader.getCredit();
                data[j][9] = objAuditHeader.getOutstanding();
                j++;
            }
        }
        ListAuditHeader auditHeaders = new ListAuditHeader(mainFrame, columns, data);
        auditHeaders.getTable().setPreferredScrollableViewportSize(tblDim);
        return auditHeaders;
    }
}
