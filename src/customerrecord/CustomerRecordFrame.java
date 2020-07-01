package customerrecord;

import javax.swing.*;

import main.MainFrame;
import sprocs.spsAccount;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public class CustomerRecordFrame extends JInternalFrame {

    // FIELDS
	private MainFrame mainFrame;
	private String[] customerDetails;
	private String[] accountDetails;
    private CustomerPane customerPane;
    private AccountPane accountPane;
    private JTabbedPane tabbedPane;

    // CONSTRUCTOR
    public CustomerRecordFrame(MainFrame mainFrame, String[] customerDetails, String[] accountDetails) {

        super("JuroVet: Customer Record", false, false, false, false);
        this.mainFrame = mainFrame;
        this.customerDetails = customerDetails;
        this.accountDetails = accountDetails;
        
        Container pane = getContentPane();
        this.tabbedPane = new JTabbedPane();
        
        this.customerPane = new CustomerPane(mainFrame, this, customerDetails);
        tabbedPane.addTab("Customer", customerPane);
        
		this.accountPane = new AccountPane(mainFrame, this, accountDetails);
        tabbedPane.addTab("Account", accountPane);

        CustomerBalancePanel cusBalancePnl = new CustomerBalancePanel(this, customerDetails);

        addCusRecPnlActionListener();

        pane.add(tabbedPane, BorderLayout.CENTER);
        pane.add(cusBalancePnl, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    // ACCESSORS
    public CustomerPane getCusPane() { 
    	return customerPane; 
	}
    
    public AccountPane getCusAccPane() { 
    	return accountPane; 
	}
    JTabbedPane getTabbedPane() { return tabbedPane; }

    public void addCusRecPnlActionListener() {
        customerPane.getCusTabbedPnl().getAccRecPnl().getTable().getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) { // On double-click
                    int selectedRow = customerPane.getCusTabbedPnl().getAccRecPnl().getTable().getTable().getSelectedRow(); // Get selected row
                    String accountGUID = customerPane.getCusTabbedPnl().getAccRecPnl().getTable().getData()[selectedRow][0]; // Find accountGUID
                    String[] accountDetails = null;
					try {
						accountDetails = spsAccount.findAccountByGUID(mainFrame, accountGUID);
					} catch (SQLException | ParseException e) {
						e.printStackTrace();
					} // Get account details
                    accountPane.getCusAccDetailsPnl().setAccDetails(accountDetails); // set CustomerAccountPane String[] accountDetails field
                    accountPane.getCusAccTabbedPnl().setAccGUID(accountGUID);
                    accountPane.getCusAccDetailsPnl().populateAccDetailsPnl(accountPane.getCusAccDetailsPnl().getFields()); // populate account details panel
                    CustomerRecordPanel[] panels = accountPane.getCusAccTabbedPnl().getCusRecPnls(); // Get every CustomerRecordPanel in CustomerAccountTabbedPanel
                    for (CustomerRecordPanel panel : panels) { // For each CustomerRecordPanel
                        panel.setExists(false); // Reset flag
                        if (panel.getTable() != null) {
                            panel.remove(panel.getTable());
                        }
                    }
                    accountPane.getCusAccTabbedPnl().getClinRecPnl().makeTable(
                    		accountPane.getCusAccTabbedPnl().populateClinicalRecords());
                    accountPane.getCusAccTabbedPnl().getClinRecPnl().setExists(true);
                    accountPane.getCusAccTabbedPnl().getClinRecPnl().getTable().setMinimumSize(new Dimension(800, 300));
                    tabbedPane.setSelectedIndex(1);
                    getCusAccPane().getCusAccTabbedPnl().getTabbedPane().setSelectedIndex(0);
                }
            }
        });
    }
    
    public MainFrame getMainFrame() {
    	return this.mainFrame;
    }
    
    public void setCustomerDetails(String[] newCustomerDetails) {
    	this.customerDetails = newCustomerDetails;
    	repaint();
    }
    
    public void setAccountDetails(String[] newAccountDetails) {
    	this.accountDetails = newAccountDetails;
    	repaint();
    }
}