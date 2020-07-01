package menubars;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.MainFrame;
import managers.ActionManager;

public class ListAddressMenuBar extends JMenuBar {
	
	/*
	 * When ListCustomer in view, MainFrame menu bar changes
	 * Menu bar changes specifically to the module for searching, creating and editing customers
	 * When the ListCustomer frame is closed, MainFrame reverts to default MainFrameMenuBar
	 */
	
	private MainFrame mainFrame;
	
	// File menu
	private final JMenu fileMenu = new JMenu("File");
	private final JMenuItem searchAddressItem = new JMenuItem("Search Address...");
	private final JMenuItem newAddressItem = new JMenuItem("New Address...");
	private final JMenuItem closeActiveModuleItem = new JMenuItem("Close Active Module");
	private final JMenuItem reportsItem = new JMenuItem("Reports");
	private final JMenuItem logOffItem = new JMenuItem("Log Off");
	private final JMenuItem exitItem = new JMenuItem("Exit");
	
	
    public ListAddressMenuBar(MainFrame mainFrame) {

    	this.mainFrame = mainFrame;
    	
    	searchAddressItem.setAction(ActionManager.getInstance(mainFrame).getShowSearchAddressFrameAction());
    	searchAddressItem.setText("Search Address...");
    	fileMenu.add(searchAddressItem);
    	
    	fileMenu.add(newAddressItem);
    	
    	closeActiveModuleItem.setAction(ActionManager.getInstance(mainFrame).getHideAddressListAction());
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
		return searchAddressItem;
	}


	public JMenuItem getNewCustomerItem() {
		return newAddressItem;
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
