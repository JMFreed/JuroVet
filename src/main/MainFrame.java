package main;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;

import customerrecord.CustomerRecordFrame;
import managers.*;
import menubars.*;
import searchevents.*;
import searchforms.*;
import lists.*;
import editforms.*;
import editevents.*;

public class MainFrame extends JFrame {
	
	/*
	 * Multiple Document Interface
	 * All modules appear in the MainFrame
	 * Controls which modules are open/closed
	 * MenuBar changes depending on which module is currently active
	 */
	
	private static MainFrame instance;
	
	private final ArrayList<JInternalFrame> frameList = new ArrayList<JInternalFrame>();
	
	private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	//Managers
	private final DatabaseManager dbManager = DatabaseManager.getInstance(this);
	private final ActionManager actionManager = ActionManager.getInstance(this);
	
	//Menu bars
	private final MainFrameMenuBar mainMenuBar = new MainFrameMenuBar(this);
	private final ListCustomerMenuBar listCustomerMenuBar = new ListCustomerMenuBar(this);
	private final ListAddressMenuBar listAddressMenuBar = new ListAddressMenuBar(this);
	
	//Search forms
	private final SearchFrame searchFrame = new SearchFrame(this);
	private final SearchFormCustomer searchFormCustomer = new SearchFormCustomer(this);
	private final SearchFormAddress searchFormAddress = new SearchFormAddress(this);
	
	//Edit forms
	private final EditFrame editFrame = new EditFrame(this);
	private final EditFormCustomer editFormCustomer = new EditFormCustomer(this, editFrame);
	private final EditFormAccount editFormAccount = new EditFormAccount(this, editFrame);
	
	// Search events
	private final SearchEventCustomer searchEventCustomer = new SearchEventCustomer(this, "","","","");
	private final SearchEventAddress searchEventAddress = new SearchEventAddress(this,"","");
	
	//Edit events
	private EditEventCustomer editEventCustomer;
	private EditEventAccount editEventAccount;
	
	// Lists
	private ListCustomer listCustomer;
	private ListAddress listAddress;
	
	// Customer record objects
	private CustomerRecordFrame customerRecordFrame;

	
	protected MainFrame() {
		super("JuroVet");		
		
		// Instantiate edit events
		this.editEventCustomer = null;
		this.editEventAccount = null;
		
		// Instantiate object lists
		this.listCustomer = null;
		this.listAddress = null;
		
		// Instantiate customer record frame
		this.customerRecordFrame = null;
		
		setJMenuBar(this.mainMenuBar);
		setPreferredSize(new Dimension( (int)(screenSize.getWidth()/1.2), (int)(screenSize.getHeight()/1.2)));
		setMinimumSize(new Dimension( (int)(screenSize.getWidth()/2), (int)(screenSize.getHeight()/2) ));
		setSize(getPreferredSize());
        setLocation((int)screenSize.getWidth()/2 - this.getWidth()/2, (int)screenSize.getHeight()/2 - this.getHeight()/2);
		setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	
    public void showSelectedFrame(JInternalFrame selectedFrame) {
        add(selectedFrame);
        getFrameList().add(selectedFrame);
        for (JInternalFrame frame : getFrameList()) {
            frame.setVisible(false);
        }
        selectedFrame.pack();
        selectedFrame.setVisible(true);
    }

    public void removeSelectedFrame(JInternalFrame selectedFrame) {
        selectedFrame.setVisible(false);
        this.remove(selectedFrame);
        this.getFrameList().remove(selectedFrame);
        if (getFrameList().size() > 0) {
            for (JInternalFrame frame : getFrameList()) {
                frame.setVisible(false);
            }
            getFrameList().get(getFrameList().size() - 1).setVisible(true);
        }
    }
	
	/*
	 * MANAGER ACCESSORS
	 */
    
    private ArrayList<JInternalFrame> getFrameList() { return this.frameList; }
	
	public DatabaseManager getDBManager() { return this.dbManager; }
	
	private ActionManager getActionManager() { return this.actionManager; }
	
	
	/*
	 * MENUBAR ACCESSORS
	 */
	
	public MainFrameMenuBar getMainMenuBar() { return this.mainMenuBar; }
	
	public ListCustomerMenuBar getListCustomerMenuBar() { return this.listCustomerMenuBar; }
	
	public ListAddressMenuBar getListAddressMenuBar() { return this.listAddressMenuBar; }
	
	
	/*
	 * SEARCH FORM ACCESSORS
	 */
	
	public SearchFrame getSearchFrame() { return this.searchFrame; }
	
	public SearchFormCustomer getSearchFormCustomer() { return this.searchFormCustomer; }
	
	public SearchFormAddress getSearchFormAddress() { return this.searchFormAddress; }
	
	/*
	 * EDIT FORM ACCESSORS
	 */
	
	public EditFrame getEditFrame() { return this.editFrame; }
	
	public EditFormCustomer getEditFormCustomer() { return this.editFormCustomer; }
	
	public EditFormAccount getEditFormAccount() { return this.editFormAccount; }
	
	
	/*
	 * SEARCH EVENT ACCESSORS
	 */
	
	public SearchEventCustomer getSearchEventCustomer() { return this.searchEventCustomer; }
	
	public SearchEventAddress getSearchEventAddress() { return this.searchEventAddress; }
	
	/*
	 * EDIT EVENT ACCESSORS
	 */
	
	public EditEventCustomer getEditEventCustomer() { return this.editEventCustomer; }
	
	public EditEventAccount getEditEventAccount() { return this.editEventAccount; }
	
	/*
	 * LIST ACCESSORS
	 */
	
	public ListCustomer getListCustomer() { return this.listCustomer; }
	
	public ListAddress getListAddress() { return this.listAddress; }
	
	
	/*
	 * CUSTOMER RECORD FRAME COMPONENT ACCESSORS
	 */
	
	public CustomerRecordFrame getCustomerRecordFrame() { return this.customerRecordFrame; }
	

	/*
	 * MUTATORS
	 */
	
	public void setListCustomer(ListCustomer newListCustomer) { this.listCustomer = newListCustomer; }
	
	public void setListAddress(ListAddress newListAddress) { this.listAddress = newListAddress; }
	
	public void refreshCustomerRecordFrame(String[] newCustomerDetails, String[] newAccountDetails) {
		getCustomerRecordFrame().setCustomerDetails(newCustomerDetails);
		getCustomerRecordFrame().setAccountDetails(newAccountDetails);
	}
	
	public void setCustomerRecordFrame(CustomerRecordFrame customerRecordFrame) {
		this.customerRecordFrame = customerRecordFrame;
	}
	
	
}
