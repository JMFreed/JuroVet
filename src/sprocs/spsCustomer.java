package sprocs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjCustomer;
import objects.ObjCustomerGroup;
import searchevents.SearchEventCustomer;

public class spsCustomer {
	
	public static final String[] columnNames = { 
			"CustomerGUID", "SageCustomerID", "Customer", "LastName", "Title", "Initials", "FirstName", 
			"AddressGUID", "HomePhone", "WorkPhone", "MobilePhone",
			"Fax", "Email", "Occupation", "LastResults", "CreditRating", 
			"Analysis1", "Analysis2", "Analysis3", "DeptNumber", "DeptName", "StatusNumber",
			"StatusText", "DefTaxCode", "DefNomCode", "DefNomCodeGUID", "NominalCodeName",
			"VatRegNumber", "CurrencyType", "DiscountType", "SettlementDiscRate", "SettlementDueDays",
			"PaymentDueDays", "CreditLimit", "AccountOnHold", "Terms", "RestrictMail", "TermsAgreed",
			"CreditAppliedFor", "CreditAppReceived", "DateCreditAppReceived", "NextCreditRev", 
			"LastCreditRev", "CanCharge", "BureauCode", "LastInvDate", "WorkInProgress", "Balance",
			"AgedFuture", "AgedBalance", "AgedCurrent", "Aged30", "Aged60", "Aged90", "AgedOlder",
			"TurnoverMTD", "TurnoverYTD", "PriorYear", "InvoiceBF", "InvoiceCF", "CreditBF", "CreditCF",
			"PaymentBF", "PaymentCF", "CompanyID", "Created", "Modified" 
			};
	
	public static ArrayList<ObjCustomer> findCustomerBySearch(MainFrame mainFrame, SearchEventCustomer event) throws SQLException {
    	ArrayList<ObjCustomer> customerList = new ArrayList<ObjCustomer>();
    	ResultSet rs = null;
    	try {
    		mainFrame.getDBManager().executeSproc(
    			"exec spsCustomerSearch " +
                "@LastName='" + event.getCusSurname() + "', " +
                "@SageCustomerID='', " +
                "@Address='" + event.getCusAddress() + "', " +
                "@Telephone='" + event.getCusTelephone() + "', " +
                "@AccountName='" + event.getCusAccount() + "'");

    		rs = mainFrame.getDBManager().getResults();
    		while (rs.next()) {
    			ObjCustomer customer = new ObjCustomer(
    					rs.getString("CustomerGUID"), 
    					rs.getString("Customer"),
    					rs.getString("Address"),
    					rs.getString("Postcode"),
    					rs.getString("Homephone"),
    					rs.getString("Balance"),
    					rs.getString("Animal"),
    					rs.getString("AccountGUID"));
    			customerList.add(customer);
    		}
    	} 
    	catch (SQLException | ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return customerList;
    }

    // Search customer by their GUID, return array of all their details
    public static String[] findCustomerByGUID(MainFrame mainFrame, String customerGUID) throws SQLException, ParseException {
    	
        String[] customerDetails = new String[columnNames.length];
    	ResultSet rs = null;
        try {
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            mainFrame.getDBManager().executeSproc(
            		"exec spsCustomerByPk " +
            		"@CustomerGUID= '" + customerGUID + "', " +
            		"@Date= '" + date + "'");

            rs = mainFrame.getDBManager().getResults();
            while (rs.next()) {
            	for (int i=0; i<columnNames.length; i++) {
            		customerDetails[i] = rs.getString(columnNames[i]);
            	}
            }
            for (int i=0; i<customerDetails.length; i++) {
            	if (DateConverter.yyyyMMddValidDate("" + customerDetails[i])) {
            		customerDetails[i] = DateConverter.yyyyMMddToddMMyyyy(customerDetails[i]);
            	}
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customerDetails;
    }
    
    
    public static String findCustomerBySageID(MainFrame mainFrame, String customerGUID) throws SQLException {
        String customerSageID = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT cusSageCustomerID " + 
        			"FROM tblCustomers " + 
        			"WHERE cusCustomerGUID = '" + customerGUID + "'");

        	rs = mainFrame.getDBManager().getResults();
            while ( rs.next() ) {
                customerSageID = rs.getString("cusSageCustomerID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customerSageID;
    }
    
    
    public static ArrayList<String> getAllCustomerTitles(MainFrame mainFrame) {
        ArrayList<String> customerTitles = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc("exec spsTitlesForValueLists");
            rs = mainFrame.getDBManager().getResults();
            while (rs.next()) {
                String title = rs.getString("Title");
                customerTitles.add(title);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customerTitles;
    }
    
    
    public static ArrayList<ObjCustomerGroup> getCustomerGroups (MainFrame mainFrame, String cusGUID) {
        ArrayList<ObjCustomerGroup> cusGroupList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsGroupXCustomersByCustomerGUID " +
        			"@CustomerGUID = '" + cusGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                cusGroupList.add(new ObjCustomerGroup(
                		rs.getString("CustomerGUID"), 
                		rs.getString("GroupGUID"), 
                		rs.getString("GroupName")
            		));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cusGroupList;
    }

}
