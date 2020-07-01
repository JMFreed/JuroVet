package managers;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import lists.*;
import main.*;
import menubars.*;
import objects.*;
import sprocs.*;
import customerrecord.CustomerRecordFrame;
import dateconverter.DateConverter;
import editevents.EditEventCustomer;
import editforms.EditFormCustomer;

public class ActionManager {
	
	private MainFrame mainFrame;
	
	private static ActionManager instance = null;
	
	private ExitProgramAction exitProgramAction = new ExitProgramAction("Exit", new Integer(KeyEvent.VK_Q), 
			KeyStroke.getKeyStroke(KeyEvent.VK_Q, java.awt.Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(), false));
	
	// SEARCH FORM ACTIONS
	private ShowSearchCustomerFrameAction showSearchCustomerFrameAction = new ShowSearchCustomerFrameAction("Search customer");
	private ShowSearchAddressFrameAction showSearchAddressFrameAction = new ShowSearchAddressFrameAction("Search address");
	private HideSearchFrameAction hideSearchFrameAction = new HideSearchFrameAction(mainFrame, "Close");
	
	// SEARCH ACTIONS
	private SearchCustomerAction searchCustomerAction = new SearchCustomerAction("Search");
	private SearchAddressAction searchAddressAction = new SearchAddressAction("Search");
	private CreateCustomerRecordAction createCustomerRecordAction = new CreateCustomerRecordAction();
	
	// LIST ACTIONS
	private ShowListCustomerAction showCustomerListAction = new ShowListCustomerAction("Show");
	private HideListCustomerAction hideCustomerListAction = new HideListCustomerAction("Close");
	private ShowListAddressAction showAddressListAction = new ShowListAddressAction("Show");
	private HideListAddressAction hideAddressListAction = new HideListAddressAction("Close");
	
	
	
	// Constructor prevents instantiation
	protected ActionManager(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
	}
	  
	public static ActionManager getInstance(MainFrame mainFrame) {
		if (instance == null)
			instance = new ActionManager(mainFrame);
		return instance;
	}
	
	/*
	 * ACCESSORS
	 */
	
	public MainFrame getMainFrame() { return mainFrame; }
	public ExitProgramAction getExitProgramAction() { return exitProgramAction; }
	
	// SEARCH FORM ACTION ACCESSORS	
	public HideSearchFrameAction getHideSearchFrameAction() { return hideSearchFrameAction; }
	public ShowSearchCustomerFrameAction getShowSearchCustomerFrameAction() { return showSearchCustomerFrameAction; }
	public ShowSearchAddressFrameAction getShowSearchAddressFrameAction() { return showSearchAddressFrameAction; }

	
	// EXECUTE SPROC ACTION ACCESSORS
	public SearchCustomerAction getSearchCustomerAction() { return searchCustomerAction; }
	public SearchAddressAction getSearchAddressAction() { return searchAddressAction; }
	public CreateCustomerRecordAction getCreateCustomerRecordAction() { return createCustomerRecordAction; }
	
	// LIST ACTION ACCESSORS
	public ShowListCustomerAction getShowCustomerListAction() { return showCustomerListAction; }
	public HideListCustomerAction getHideCustomerListAction() { return hideCustomerListAction; }
	public ShowListAddressAction getShowAddressListAction() { return showAddressListAction; }
	public HideListAddressAction getHideAddressListAction() { return hideAddressListAction; }
	
	
	
	
	abstract class JurovetAction extends AbstractAction {
		
		public JurovetAction(String text) {
			super(text);
		}
		
		public JurovetAction(String text, Integer mnemonic, KeyStroke accelerator) {
			super(text);
			putValue(MNEMONIC_KEY, mnemonic);
			putValue(ACCELERATOR_KEY, accelerator);
		}
	}
	
	
	
	protected class ExitProgramAction extends JurovetAction {
		
		public ExitProgramAction(String text, Integer mnemonic, KeyStroke accelerator) {
			super(text, mnemonic, accelerator);
		}

		public void actionPerformed(ActionEvent e) {
			int action = JOptionPane.showConfirmDialog(mainFrame, "Exit JuroVet?",
	            "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
			if (action == JOptionPane.OK_OPTION) {
				System.exit(0);
			}
	    }
	}
	
	/*
     _______. __    __    ______   ____    __    ____   	 __    __   __   _______   _______ 
    /       ||  |  |  |  /  __  \  \   \  /  \  /   /  		|  |  |  | |  | |       \ |   ____|
   |   (----`|  |__|  | |  |  |  |  \   \/    \/   /   		|  |__|  | |  | |  .--.  ||  |__   
    \   \    |   __   | |  |  |  |   \            /    		|   __   | |  | |  |  |  ||   __| 
.----)   |   |  |  |  | |  `--'  |    \    /\    /     		|  |  |  | |  | |  '--'  ||  |____
|_______/    |__|  |__|  \______/      \__/  \__/      		|__|  |__| |__| |_______/ |_______|
                                   
     _______. _______     ___      .______        ______  __    __  
    /       ||   ____|   /   \     |   _  \      /      ||  |  |  | 
   |   (----`|  |__     /  ^  \    |  |_)  |    |  ,----'|  |__|  | 
    \   \    |   __|   /  /_\  \   |      /     |  |     |   __   | 
.----)   |   |  |____ /  _____  \  |  |\  \----.|  `----.|  |  |  | 
|_______/    |_______/__/     \__\ | _| `._____| \______||__|  |__| 
                                                                    
 _______   ______   .______      .___  ___.      _______.
|   ____| /  __  \  |   _  \     |   \/   |     /       |
|  |__   |  |  |  | |  |_)  |    |  \  /  |    |   (----`
|   __|  |  |  |  | |      /     |  |\/|  |     \   \    
|  |     |  `--'  | |  |\  \----.|  |  |  | .----)   |   
|__|      \______/  | _| `._____||__|  |__| |_______/    
                                                       
	 */

	
	protected class HideSearchFrameAction extends JurovetAction {
		
		public HideSearchFrameAction(MainFrame mainFrame, String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getSearchFrame().setVisible(false);
		}
	}
	
	
	protected class ShowSearchCustomerFrameAction extends JurovetAction {
		
		public ShowSearchCustomerFrameAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getSearchFormCustomer().clearFields();
			mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormCustomer());
			if (mainFrame.getListCustomer() == null) {
				String[][] data = new String[0][mainFrame.getListCustomer().columnNames.length];
				mainFrame.setListCustomer(new ListCustomer(mainFrame, data));
			}
			mainFrame.getListCustomer().getFrame().setVisible(true);
			mainFrame.setJMenuBar(mainFrame.getListCustomerMenuBar());
			mainFrame.getSearchFrame().setVisible(true);
		}
	}
	
	
	protected class ShowSearchAddressFrameAction extends JurovetAction {
		
		public ShowSearchAddressFrameAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getSearchFormAddress().clearFields();
			mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormAddress());
			if (mainFrame.getListAddress() == null) {
				String[][] data = new String[0][mainFrame.getListAddress().columnNames.length];
				mainFrame.setListAddress(new ListAddress(mainFrame, data));
			}
			mainFrame.getListAddress().getFrame().setVisible(true);
			mainFrame.setJMenuBar(mainFrame.getListAddressMenuBar());
			mainFrame.getSearchFrame().setVisible(true);
		}
	}
	
	
	/*
     _______. __    __    ______   ____    __    ____ 		 __    __   __   _______   _______ 
    /       ||  |  |  |  /  __  \  \   \  /  \  /   / 		|  |  |  | |  | |       \ |   ____|
   |   (----`|  |__|  | |  |  |  |  \   \/    \/   /  		|  |__|  | |  | |  .--.  ||  |__   
    \   \    |   __   | |  |  |  |   \            /   		|   __   | |  | |  |  |  ||   __| 
.----)   |   |  |  |  | |  `--'  |    \    /\    /    		|  |  |  | |  | |  '--'  ||  |____
|_______/    |__|  |__|  \______/      \__/  \__/     		|__|  |__| |__| |_______/ |_______|
                                   
 __       __       _______.___________.    _______.
|  |     |  |     /       |           |   /       |
|  |     |  |    |   (----`---|  |----`  |   (----`
|  |     |  |     \   \       |  |        \   \    
|  `----.|  | .----)   |      |  |    .----)   |   
|_______||__| |_______/       |__|    |_______/     
                                                                         
	 */
	
	
	protected class ShowListCustomerAction extends JurovetAction {
		
		public ShowListCustomerAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getListCustomer().getFrame().setVisible(true);
			mainFrame.setJMenuBar(mainFrame.getListCustomerMenuBar());
		}
	}
	
	
	protected class HideListCustomerAction extends JurovetAction {
		
		public HideListCustomerAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getListCustomer().getFrame().setVisible(false);
			mainFrame.setJMenuBar(mainFrame.getMainMenuBar());
		}
	}
	
	
	
	protected class ShowListAddressAction extends JurovetAction {
		
		public ShowListAddressAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getListAddress().getFrame().setVisible(true);
			mainFrame.setJMenuBar(mainFrame.getListAddressMenuBar());
		}
	}
	
	
	
	protected class HideListAddressAction extends JurovetAction {

		public HideListAddressAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			mainFrame.getListAddress().getFrame().setVisible(false);
			mainFrame.setJMenuBar(mainFrame.getMainMenuBar());
		}
	}
	
	
	/*
     _______. _______     ___      .______        ______  __    __  
    /       ||   ____|   /   \     |   _  \      /      ||  |  |  | 
   |   (----`|  |__     /  ^  \    |  |_)  |    |  ,----'|  |__|  | 
    \   \    |   __|   /  /_\  \   |      /     |  |     |   __   | 
.----)   |   |  |____ /  _____  \  |  |\  \----.|  `----.|  |  |  | 
|_______/    |_______/__/     \__\ | _| `._____| \______||__|  |__| 
                                                                    
 _______   ______   .______      .___  ___. 
|   ____| /  __  \  |   _  \     |   \/   | 
|  |__   |  |  |  | |  |_)  |    |  \  /  | 
|   __|  |  |  |  | |      /     |  |\/|  | 
|  |     |  `--'  | |  |\  \----.|  |  |  | 
|__|      \______/  | _| `._____||__|  |__| 
                                            
     ___       ______ .___________. __    ______   .__   __.      _______.
    /   \     /      ||           ||  |  /  __  \  |  \ |  |     /       |
   /  ^  \   |  ,----'`---|  |----`|  | |  |  |  | |   \|  |    |   (----`
  /  /_\  \  |  |         |  |     |  | |  |  |  | |  . `  |     \   \    
 /  _____  \ |  `----.    |  |     |  | |  `--'  | |  |\   | .----)   |   
/__/     \__\ \______|    |__|     |__|  \______/  |__| \__| |_______/     
                                                                          
	 */
	
	protected class SearchCustomerAction extends JurovetAction {
		
		public SearchCustomerAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			long startTime = System.nanoTime();
			if (mainFrame.getSearchFormCustomer().allFieldsEmpty()) {
				JOptionPane.showMessageDialog(mainFrame, "Not enough search information");
				return;
			}
			mainFrame.getSearchEventCustomer().updateInformation(mainFrame.getSearchFormCustomer());
			ArrayList<ObjCustomer> customerList = null;
			try {
				customerList = spsCustomer.findCustomerBySearch(mainFrame, mainFrame.getSearchEventCustomer());
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			mainFrame.getListCustomer().updateCustomerData(customerList);
			mainFrame.getSearchFrame().setVisible(false);
			long endTime = System.nanoTime();
			System.out.println("Execution time: " + (endTime - startTime)/1000000 + " ms");	
		}
	}
	
	
	
	protected class SearchAddressAction extends JurovetAction {

		public SearchAddressAction(String text) {
			super(text);
		}
		
		public void actionPerformed(ActionEvent e) {
			long startTime = System.nanoTime();
			if (mainFrame.getSearchFormAddress().allFieldsEmpty()) {
				JOptionPane.showMessageDialog(mainFrame, "Not enough search information");
				return;
			}
			mainFrame.getSearchEventAddress().updateInformation(mainFrame.getSearchFormAddress());
			ArrayList<ObjAddress> addressList = null;
			try {
				if (mainFrame.getSearchEventAddress().getAddress().length() > 0) {
					addressList = spsAddress.findAddressByName(mainFrame, mainFrame.getSearchEventAddress());
				}
				else if (mainFrame.getSearchEventAddress().getPostcode().length() > 0) {
					addressList = spsAddress.findAddressByPostcode(mainFrame, mainFrame.getSearchEventAddress());
				}
				mainFrame.getListAddress().updateAddressData(addressList);
				mainFrame.getSearchFrame().setVisible(false);
				long endTime = System.nanoTime();
				System.out.println("Execution time: " + (endTime - startTime)/1000000 + " ms");
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
	/*
  ______  __    __       _______.___________.  ______   .___  ___.  _______  .______
 /      ||  |  |  |     /       |           | /  __  \  |   \/   | |   ____| |   _  \  
|  ,----'|  |  |  |    |   (----`---|  |----`|  |  |  | |  \  /  | |  |__    |  |_)  |
|  |     |  |  |  |     \   \       |  |     |  |  |  | |  |\/|  | |   __|   |      / 
|  `----.|  `--'  | .----)   |      |  |     |  `--'  | |  |  |  | |  |____  |  |\  \----.
 \______| \______/  |_______/       |__|      \______/  |__|  |__| |_______| | _| `._____|
             
.______       _______   ______   ______   .______       _______  
|   _  \     |   ____| /      | /  __  \  |   _  \     |       \ 
|  |_)  |    |  |__   |  ,----'|  |  |  | |  |_)  |    |  .--.  |
|      /     |   __|  |  |     |  |  |  | |      /     |  |  |  |
|  |\  \----.|  |____ |  `----.|  `--'  | |  |\  \----.|  '--'  |
| _| `._____||_______| \______| \______/  | _| `._____||_______/ 
                                                                 
     ___       ______ .___________. __    ______   .__   __.      _______.
    /   \     /      ||           ||  |  /  __  \  |  \ |  |     /       |
   /  ^  \   |  ,----'`---|  |----`|  | |  |  |  | |   \|  |    |   (----`
  /  /_\  \  |  |         |  |     |  | |  |  |  | |  . `  |     \   \    
 /  _____  \ |  `----.    |  |     |  | |  `--'  | |  |\   | .----)   |   
/__/     \__\ \______|    |__|     |__|  \______/  |__| \__| |_______/ 
	 */
	
	
	protected class CreateCustomerRecordAction extends MouseAdapter {
		
		public CreateCustomerRecordAction() {
			super();
		}
	    public void mouseClicked(MouseEvent me) {
	        if (me.getClickCount() == 2) {
	        	ListCustomer listCustomer = mainFrame.getListCustomer();
	            int selectedRow = listCustomer.getTable().getSelectedRow();
	            if (selectedRow >= 0) {
	                String customerGUID = listCustomer.getData()[selectedRow][0];
	                String accountGUID = listCustomer.getData()[selectedRow][7];
	                String[] customerDetails = new String[spsCustomer.columnNames.length];
	                String[] accountDetails = new String[spsAccount.columnNames.length];
	                try {
						customerDetails = spsCustomer.findCustomerByGUID(mainFrame, customerGUID);
						accountDetails = spsAccount.findAccountByGUID(mainFrame, accountGUID);
					} catch (SQLException | ParseException e1) {
						e1.printStackTrace();
					}
	                mainFrame.setCustomerRecordFrame(new CustomerRecordFrame(mainFrame, customerDetails, accountDetails));
	                mainFrame.showSelectedFrame(mainFrame.getCustomerRecordFrame());
	            }
	        }
		}
	}
	
	
	
	/*
 _______  _______   __  .___________.
|   ____||       \ |  | |           |
|  |__   |  .--.  ||  | `---|  |----`
|   __|  |  |  |  ||  |     |  |     
|  |____ |  '--'  ||  |     |  |     
|_______||_______/ |__|     |__|     
                                     
 _______   ______   .______      .___  ___. 
|   ____| /  __  \  |   _  \     |   \/   | 
|  |__   |  |  |  | |  |_)  |    |  \  /  | 
|   __|  |  |  |  | |      /     |  |\/|  | 
|  |     |  `--'  | |  |\  \----.|  |  |  | 
|__|      \______/  | _| `._____||__|  |__| 
                                            
     ___       ______ .___________. __    ______   .__   __.      _______.
    /   \     /      ||           ||  |  /  __  \  |  \ |  |     /       |
   /  ^  \   |  ,----'`---|  |----`|  | |  |  |  | |   \|  |    |   (----`
  /  /_\  \  |  |         |  |     |  | |  |  |  | |  . `  |     \   \    
 /  _____  \ |  `----.    |  |     |  | |  `--'  | |  |\   | .----)   |   
/__/     \__\ \______|    |__|     |__|  \______/  |__| \__| |_______/  
	 */
	
	
	protected class EditCustomerAction extends JurovetAction {
		
		public EditCustomerAction(MainFrame mainFrame, String text) {
			super(text);
		}
		
	    public void actionPerformed(ActionEvent e) {

	    	EditFormCustomer form = mainFrame.getEditFormCustomer();
	        EditEventCustomer newEvent = null;
	        try {
	            newEvent = new EditEventCustomer(
	            				"" + form.getCusCustomerGUID(),
	                            "" + form.getCusLastNameField().getText(),
	                            "" + form.getCusTitleField().getSelectedItem(),
	                            "" + form.getCusInitialsField().getText(),
	                            "" + form.getCusFirstNameField().getText(),
	                            "" + form.getCusAddressGUID(),
	                            "" + form.getCusHomePhoneField().getText(),
	                            "" + form.getCusWorkPhoneField().getText(),
	                            "" + form.getCusMobilePhoneField().getText(),
	                            "" + form.getCusFaxField().getText(),
	                            "" + form.getCusEmailField().getText(),
	                            "" + form.getCusOccupationField().getText(),
	                            "" + form.getCustomerDetails()[14],
	                            "" + form.getCustomerDetails()[15],
	                            "","","",
	                            "" + spsDepartmentsAll.getDeptNumber(mainFrame, form.getCusDeptNameField().getSelectedItem().toString()),
	                            "" + form.getCusDeptNameField().getSelectedItem().toString(),
	                            "" + form.getCustomerDetails()[21],
	                            "" + form.getCustomerDetails()[22],
	                            "" + form.getCustomerDetails()[23],
	                            "" + form.getCustomerDetails()[24],
	                            "" + form.getCustomerDetails()[25],
	                            "" + form.getCustomerDetails()[27],
	                            "" + form.getCusCurrencyTypeField().getSelectedItem().toString(),
	                            "" + form.getCustomerDetails()[29],
	                            "" + form.getCustomerDetails()[30],
	                            "" + form.getCustomerDetails()[31],
	                            "" + form.getCustomerDetails()[32],
	                            "" + form.getCustomerDetails()[33],
	                            "" + form.getCustomerDetails()[34],
	                            "" + form.getCustomerDetails()[35],
	                            "" + form.getCustomerDetails()[36],
	                            "" + form.getCustomerDetails()[37],
	                            "" + form.getCustomerDetails()[38],
	                            "" + DateConverter.ddMMyyyyToyyyyMMdd(form.getCustomerDetails()[39]),
	                            "" + form.getCustomerDetails()[40],
	                            "" + DateConverter.ddMMyyyyToyyyyMMdd(form.getCustomerDetails()[41]),
	                            "" + DateConverter.ddMMyyyyToyyyyMMdd(form.getCustomerDetails()[42]),
	                            "" + DateConverter.ddMMyyyyToyyyyMMdd(form.getCustomerDetails()[43]),
	                            "" + form.getCustomerDetails()[44],
	                            "" + form.getCustomerDetails()[45]
	                    );
	        } catch (ParseException ex) {
	            ex.printStackTrace();
	        }

	        // System.out.println(newEvent.toString());
	        if (spuCustomer.validateEvent(newEvent)) {
	            int action = JOptionPane.showConfirmDialog(mainFrame, "Update customer?",
	                    "Confirm save", JOptionPane.OK_CANCEL_OPTION);
	            if (action == JOptionPane.OK_OPTION) {
	                if (spuCustomer.updateCustomerDetails(mainFrame, newEvent)) {
	                    JOptionPane.showMessageDialog(new JPanel(), "Customer Updated", "JuroVet",
	                            JOptionPane.INFORMATION_MESSAGE);
	                    mainFrame.getEditFrame().setVisible(false);
//	                    String[] accDetails = spsAccount.findNewCustomerFirstAccount(cusSageCustomerIDField.getText());
//	                    String[] cusDetails = spsCustomer.findCustomerByGUID(accDetails[1]);
//	                    mainFrame.setCusRecFrame(new CustomerRecordFrame(mainFrame, cusDetails, accDetails));
//	                    mainFrame.showSelectedFrame(mainFrame.getCusRecFrame());
	                } else {
	                    JOptionPane.showMessageDialog(new JPanel(), "Error updating customer", "JuroVet",
	                            JOptionPane.WARNING_MESSAGE);
	                }
	            }
	        }
	        else {
	            JPanel warningPanel = new JPanel();
	            JOptionPane.showMessageDialog(warningPanel, "Error in required fields", "JuroVet",
	                        JOptionPane.WARNING_MESSAGE);
	            }
	        }
	}
	
	
	
	
	
	
}

