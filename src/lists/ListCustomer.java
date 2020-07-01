package lists;

import javax.swing.*;


import main.MainFrame;

import managers.ActionManager;
import objects.ObjCustomer;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListCustomer extends List {

	private MainFrame mainFrame;
    private JInternalFrame frame;
    
    public final static String[] columnNames = {
    		"CustomerID", "Customer", "Address", "Postcode", 
    		"Telephone", "Animal", "Balance", "AccountGUID"};
    
    private final JButton searchButton = new JButton ("Search");
    private final JButton editButton = new JButton("Edit");
    private final JButton newCustomerButton = new JButton ("New Customer...");
    private final JButton closeButton = new JButton("Close");
    
    public ListCustomer(MainFrame mainFrame, String[][] arrStrData) {
        super(mainFrame, columnNames, arrStrData);
        setTableSize(mainFrame.getWidth() - 40, mainFrame.getHeight() - 200);
        // Hide customerGUID column
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(0));
        // Hide accountGUID column
        this.getTableColumnModel().removeColumn(this.getTableColumnModel().getColumn(6));
        // Set address column size
        this.getTableColumnModel().getColumn(1).setPreferredWidth(400);
        
        this.frame = new JInternalFrame("Customers: " + this.getData().length + " records found",
                                    false, false, false,false);
        
        this.frame.getContentPane().add(this);
        mainFrame.showSelectedFrame(frame);
        
        // SET ACTIONS
        getTable().addMouseListener(ActionManager.getInstance(mainFrame).getCreateCustomerRecordAction());
        
        searchButton.setAction(ActionManager.getInstance(mainFrame).getShowSearchCustomerFrameAction());
        searchButton.setText("Search");
        closeButton.setAction(ActionManager.getInstance(mainFrame).getHideCustomerListAction());
        closeButton.setText("Close");
        
        // Add controls to the panel
        this.getTableScrollPane().setBounds(0,0,mainFrame.getWidth() - 40, mainFrame.getHeight() - 200);
        add(this.getTableScrollPane()); 
        add(searchButton); 
        add(editButton);
        add(newCustomerButton); 
        add(closeButton);
    }

    public JInternalFrame getFrame() { return this.frame; }
    public JButton getSearchButton() { return this.searchButton; }
    public JButton getCloseButton() { return this.closeButton; }
    
    /*
     * updateCustomerData
     * @params
     * customerList : ArrayList containing ObjCustomer objects
     * When new search performed, grab the data, put into an array
     * Remove all data from ListCustomer table, replace with new data
     */
    public void updateCustomerData(ArrayList<ObjCustomer> customerList) {
        String[][] data = new String[customerList.size()][columnNames.length];
        int i = 0;
        while ( i < customerList.size() ) {
            for (ObjCustomer customer : customerList) {
                data[i][0] = customer.getCustomerGUID();
                data[i][1] = customer.getCustomer();
                data[i][2] = customer.getAddress();
                data[i][3] = customer.getPostcode();
                data[i][4] = customer.getHomePhone();
                data[i][5] = customer.getAnimal();
                data[i][6] = String.format("%.2f", Double.parseDouble(customer.getBalance()));
                data[i][7] = customer.getAccountGUID();
                i++;
            }
        }
        this.data = data;
        getTableModel().getDataVector().removeAllElements();
        for (int j=0; j<data.length; j++) {
        	getTableModel().addRow(data[j]);
        }
        getTable().setModel(getTableModel());
        getFrame().setTitle("Customers: " + data.length + " records found");
    }

}
