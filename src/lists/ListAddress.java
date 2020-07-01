package lists;

import javax.swing.*;

import main.MainFrame;
import managers.ActionManager;
import objects.ObjAddress;
import objects.ObjCustomer;

import java.awt.*;
import java.util.ArrayList;

public class ListAddress extends List {
	
	private JInternalFrame frame;
    public final static String[] columnNames = { "AddressID", "Address", "Postcode" };
    
    private final JButton searchButton = new JButton ("Search");
    private final JButton editButton = new JButton("Edit");
    private final JButton newAddressButton = new JButton ("New Address...");
    private final JButton closeButton = new JButton("Close");

    public ListAddress(MainFrame mainFrame, String[][] arrStrData) {
        super(mainFrame, columnNames, arrStrData);
        // Hide AddressGUID column
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0));
        
        this.frame = new JInternalFrame(
        		"Addresses: " + this.getData().length + " records found", 
        		false, false, false,false);
        
        this.frame.getContentPane().add(this);
        mainFrame.showSelectedFrame(frame);
        
        // Set button actions
        searchButton.setAction(ActionManager.getInstance(mainFrame).getShowSearchAddressFrameAction());
        searchButton.setText("Search");
        closeButton.setAction(ActionManager.getInstance(mainFrame).getHideAddressListAction());
        closeButton.setText("Close");

        // Add controls to the panel
        this.getTableScrollPane().setBounds(0,0,mainFrame.getWidth() - 40, mainFrame.getHeight() - 200);
        add(this.getTableScrollPane()); 
        add(searchButton); 
        add(editButton);
        add(newAddressButton); 
        add(closeButton);
    }
    
    
    public ListAddress(JPanel panel, String[] columns, String[][] data) {
        super(panel, columnNames, data);
        // Hide AddressGUID column
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0));
    }
    
    public JInternalFrame getFrame() { return this.frame; }
    public JButton getSearchButton() { return this.searchButton; }
    public JButton getCloseButton() { return this.closeButton; }
    
    public void updateAddressData(ArrayList<ObjAddress> addressList) {
        String[][] data = new String[addressList.size()][columnNames.length];
        int i = 0;
        while ( i < addressList.size() ) {
            for (ObjAddress address : addressList) {
                data[i][0] = address.getAddressGUID();
                data[i][1] = address.getAddressFull();
                data[i][2] = address.getPostcode();
                i++;
            }
        }
        this.data = data;
        getTableModel().getDataVector().removeAllElements();
        for (int j=0; j<data.length; j++) {
        	getTableModel().addRow(data[j]);
        }
        getTable().setModel(getTableModel());
        getFrame().setTitle("Addresses: " + data.length + " records found");
    }
}
