package menubars;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.MainFrame;
import managers.ActionManager;

public class ListCustomerMenuBar extends JMenuBar {
	
	/*
	 * When ListCustomer in view, MainFrame menu bar changes
	 * Menu bar changes specifically to the module for searching, creating and editing customers
	 * When the ListCustomer frame is closed, MainFrame reverts to default MainFrameMenuBar
	 */
	
	private MainFrame mainFrame;
	
	// File menu
	private final JMenu fileMenu = new JMenu("File");
	private final JMenuItem searchCustomerItem = new JMenuItem("Search Customer...");
	private final JMenuItem newCustomerItem = new JMenuItem("New Customer...");
	private final JMenuItem openCustomerRecordItem = new JMenuItem("Open Customer Record");
	private final JMenuItem closeActiveModuleItem = new JMenuItem("Close Active Module");
	private final JMenuItem reportsItem = new JMenuItem("Reports");
	private final JMenuItem logOffItem = new JMenuItem("Log Off");
	private final JMenuItem exitItem = new JMenuItem("Exit");
	
	
    public ListCustomerMenuBar(MainFrame mainFrame) {

    	this.mainFrame = mainFrame;
    	
    	searchCustomerItem.setAction(ActionManager.getInstance(mainFrame).getShowSearchCustomerFrameAction());
    	searchCustomerItem.setText("Search Customer...");
    	fileMenu.add(searchCustomerItem);
    	
    	fileMenu.add(newCustomerItem);
    	fileMenu.add(openCustomerRecordItem);
    	
    	closeActiveModuleItem.setAction(ActionManager.getInstance(mainFrame).getHideCustomerListAction());
    	closeActiveModuleItem.setText("Close Active Module");
    	fileMenu.add(closeActiveModuleItem);
        
    	fileMenu.add(reportsItem);
        fileMenu.addSeparator();
        JMenuItem logOffItem = new JMenuItem("Log Off");
        fileMenu.add(logOffItem);
        fileMenu.addSeparator();
        
        exitItem.setAction(ActionManager.getInstance(mainFrame).getExitProgramAction());
        fileMenu.add(exitItem);

        add(fileMenu);
    }


	public MainFrame getMainFrame() {
		return mainFrame;
	}


	public JMenu getFileMenu() {
		return fileMenu;
	}


	public JMenuItem getSearchCustomerItem() {
		return searchCustomerItem;
	}


	public JMenuItem getNewCustomerItem() {
		return newCustomerItem;
	}


	public JMenuItem getOpenCustomerRecordItem() {
		return openCustomerRecordItem;
	}


	public JMenuItem getCloseActiveModuleItem() {
		return closeActiveModuleItem;
	}


	public JMenuItem getReportsItem() {
		return reportsItem;
	}


	public JMenuItem getLogOffItem() {
		return logOffItem;
	}


	public JMenuItem getExitItem() {
		return exitItem;
	}
    
    

}
