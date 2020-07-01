package menubars;

import javax.swing.*;

import main.MainFrame;
import managers.ActionManager;


public class MainFrameMenuBar extends JMenuBar {
	
	/*
	 * Default menu bar
	 * Gives user access to all modules
	 * When all modules are closed, the MainFrame will revert to this JMenuBar
	 */
	
	private MainFrame mainFrame;
	
	// File menu
	private final JMenu fileMenu = new JMenu("File");
	private final JMenu openItemMenu = new JMenu("Open");
	private final JMenuItem textEditorItem = new JMenuItem("Text editor");
	private final JMenuItem imageJItem = new JMenuItem("ImageJ");
	private final JMenuItem reportsItem = new JMenuItem("Reports");
	private final JMenuItem logOffItem = new JMenuItem("Log off");
	private final JMenuItem exitItem = new JMenuItem("Exit");

	// View menu
	private final JMenu viewMenu = new JMenu("View");
	private final JMenuItem calendarItem = new JMenuItem("Calendar");
	private final JMenuItem appointmentsItem = new JMenuItem("Appointments");
	
	// Search menu
	private final JMenu searchMenu = new JMenu("Search");
	private final JMenuItem searchCustomerItem = new JMenuItem ("Customer");
	private final JMenuItem searchAddressItem = new JMenuItem("Address");
	private final JMenuItem searchProductItem = new JMenuItem("Product");
	private final JMenuItem searchDocumentItem = new JMenuItem("Documents");
	
	// Settings menu
	private final JMenu settingsMenu = new JMenu("Settings");
	private final JMenuItem accessRightsItem = new JMenuItem ("Access Rights...");
	private final JMenuItem changePasswordItem = new JMenuItem("Change Password...");
	
	
	public MainFrameMenuBar(MainFrame mainFrame) {
		
		super();
		
		this.mainFrame = mainFrame;
		
		fileMenu.add(openItemMenu);
		openItemMenu.add(textEditorItem);
        openItemMenu.add(imageJItem);
        fileMenu.add(reportsItem);
        fileMenu.addSeparator();
        fileMenu.add(logOffItem);
        fileMenu.addSeparator();
        exitItem.setAction(ActionManager.getInstance(mainFrame).getExitProgramAction());
        fileMenu.add(exitItem);

        viewMenu.add(calendarItem);
        viewMenu.add(appointmentsItem);

        searchCustomerItem.setAction(ActionManager.getInstance(mainFrame).getShowSearchCustomerFrameAction());
        searchCustomerItem.setText("Customer");
        searchMenu.add(searchCustomerItem);
        
        searchAddressItem.setAction(ActionManager.getInstance(mainFrame).getShowSearchAddressFrameAction());
        searchAddressItem.setText("Address");
        searchMenu.add(searchAddressItem);
        
        searchMenu.add(searchProductItem);
        searchMenu.add(searchDocumentItem);

        settingsMenu.add(accessRightsItem);
        settingsMenu.add(changePasswordItem);

        add(fileMenu);
        add(viewMenu);
        add(searchMenu);
        add(settingsMenu);
    }
	
	private MainFrame getMainFrame() {
		return this.mainFrame;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenu getOpenItemMenu() {
		return openItemMenu;
	}

	public JMenuItem getTextEditorItem() {
		return textEditorItem;
	}

	public JMenuItem getImageJItem() {
		return imageJItem;
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

	public JMenu getViewMenu() {
		return viewMenu;
	}

	public JMenuItem getCalendarItem() {
		return calendarItem;
	}

	public JMenuItem getAppointmentsItem() {
		return appointmentsItem;
	}

	public JMenu getSearchMenu() {
		return searchMenu;
	}

	public JMenuItem getSearchCustomerItem() {
		return searchCustomerItem;
	}

	public JMenuItem getSearchAddressItem() {
		return searchAddressItem;
	}

	public JMenuItem getSearchProductItem() {
		return searchProductItem;
	}

	public JMenuItem getSearchDocumentItem() {
		return searchDocumentItem;
	}

	public JMenu getSettingsMenu() {
		return settingsMenu;
	}

	public JMenuItem getAccessRightsItem() {
		return accessRightsItem;
	}

	public JMenuItem getChangePasswordItem() {
		return changePasswordItem;
	}
	
	

}
