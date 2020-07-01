package sprocs;

import java.sql.*;
import java.util.ArrayList;

import main.MainFrame;
import objects.ObjAddress;
import searchevents.SearchEventAddress;

public class spsAddress {
	
	/*
	 * findAddressByName
	 * Parameters: SearchEventAddress
	 * returns array list of ObjAddresses
	 * used to populate ListAddress table when searching for an address
	 */
	public static ArrayList<ObjAddress> findAddressByName(MainFrame mainFrame, SearchEventAddress event) throws SQLException {
        ArrayList<ObjAddress> addressList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsAddressesBySearch " + 
        			"@Address = '" + event.getAddress() + "'");
        	
    		rs = mainFrame.getDBManager().getResults();
            while ( rs.next() ) {
                ObjAddress objAddress = new ObjAddress(
                		rs.getString("AddressGUID"),
                		rs.getString("Address"),
                		rs.getString("Country"));
                addressList.add(objAddress);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressList;
    }
	
	
	/*
	 * findAddressByName
	 * Parameters: String address
	 * returns array list of ObjAddresses
	 * used to populate ListAddress table when searching for an address
	 */
	public static ArrayList<ObjAddress> findAddressByName(MainFrame mainFrame, String address) throws SQLException {
        ArrayList<ObjAddress> addressList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsAddressesBySearch " + 
        			"@Address = '" + address + "'");
        	
    		rs = mainFrame.getDBManager().getResults();
            while ( rs.next() ) {
                ObjAddress objAddress = new ObjAddress(
                		rs.getString("AddressGUID"),
                		rs.getString("Address"),
                		rs.getString("Country"));
                addressList.add(objAddress);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressList;
    }
	
	
	/*
	 * findAddressByPostcode
	 * Parameters: SearchEventAddress
	 * returns array list of ObjAddresses
	 * used to populate ListAddress table when searching for an address
	 */
	public static ArrayList<ObjAddress> findAddressByPostcode(MainFrame mainFrame, SearchEventAddress event) throws SQLException {
        ArrayList<ObjAddress> addressList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsAddressesBySearch " + 
        			"@Address = '" + event.getPostcode() + "'");
        	
    		rs = mainFrame.getDBManager().getResults();
            while ( rs.next() ) {
                ObjAddress objAddress = new ObjAddress(
                		rs.getString("AddressGUID"),
                		rs.getString("Address"),
                		rs.getString("Country"));
                addressList.add(objAddress);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressList;
    }
	
	
	/*
	 * findAddressByGUID
	 * Parameters: String addressGUID
	 * returns String[] of address details
	 * used to fill in textfield when creating/editing customer
	 */
    public static String[] findAddressByGUID(MainFrame mainFrame, String addressGUID) {
    	String[] columnNames = {
    			"Address1", "Address2", "Address3", 
    			"Address4", "Address5", "Country"
    			};
    	String[] addressLines = new String[columnNames.length];
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsAddressByPk " + 
        			"@AddressGUID = '" + addressGUID + "'");
        	
        	rs = mainFrame.getDBManager().getResults();

            while ( rs.next() ) {
                for (int i=0; i<columnNames.length; i++) {
                	addressLines[i] = rs.getString(columnNames[i]);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressLines;
    }
    
    
    public static ArrayList<ObjAddress> findCustomerXAddresses(MainFrame mainFrame, String customerGUID) {
        ArrayList<ObjAddress> addressList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsCustomerXAddressesByCustomerGUID " +
            		"@CustomerGuid = '" + customerGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while ( rs.next() ) {
                addressList.add(new ObjAddress(
                		rs.getString("AddressGUID"),
                		rs.getString("Address"),
                		rs.getString("Postcode")
                		));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    public static ArrayList<ObjAddress> findAccountXAddresses(MainFrame mainFrame, String accountGUID) {
        ArrayList<ObjAddress> addressList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsAccountXAddressesByAccountGUID " +
        			"@AccountGUID = '" + accountGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while ( rs.next() ) {
               addressList.add(new ObjAddress(
                		rs.getString("AddressGUID"),
                		rs.getString("Address"),
                		rs.getString("Postcode")
                		));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return addressList;
    }

}
