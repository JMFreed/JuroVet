package sprocs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjAccount;

public class spsAccount {
	
	public static final String[] columnNames = {
			"AccountGUID", "CustomerGUID", "AccountName", "Warning", "SpeciesID", "Species",
			"BreedID", "Breed", "ColourID", "Colour", "AnimalSex", "DateBirth", "HerdNumber",
			"IdentificationNumber", "PedigreeName", "Sire", "Dam", "DateAcquired", "Neutered",
			"DateNeutered", "Sold", "DateSold", "Deceased", "DateDeceased", "AccountType",
			"Height", "Weight", "UsualVet", "AddressGUID", "Analysis1", "Analysis2", "Analysis3",
			"DeptNumber", "DeptName", "DefTaxCode", "DefNomCode", "DefNomCodeGUID", "DefNomCodeName",
			"CurrencyType", "DiscountType", "DiscountRate", "SettlementDiscRate", "SettlementDueDays",
			"PaymentDueDays", "WorkInProgress", "Balance", "Created", "Modified"
			};
	
    public static String[] findAccountByGUID(MainFrame mainFrame, String accountGUID) throws SQLException, ParseException {
    	
    	String[] accountDetails = new String[columnNames.length];
    	ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsAccountByPk " +
            		"@AccountGUID = '" + accountGUID + "'");

            rs = mainFrame.getDBManager().getResults();
            while (rs.next()) {
            	for (int i=0; i<columnNames.length; i++) {
            		accountDetails[i] = rs.getString("" + columnNames[i]);
            	}
            }
            for (int i=0; i<accountDetails.length; i++) {
            	if (DateConverter.yyyyMMddValidDate("" + accountDetails[i])) {
            		accountDetails[i] = DateConverter.yyyyMMddToddMMyyyy(accountDetails[i]);
            	}
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return accountDetails;
    }
    
    
    // RETURN LIST OF ACCOUNTS BELONGING TO CUSTOMER
    public static ArrayList<ObjAccount> findAccountsByCustomerGUID(MainFrame mainFrame, String customerGUID) {
        ArrayList<ObjAccount> accountList = new ArrayList<ObjAccount>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc (
            		"exec spsAccountsByCustomerGUID " +
            		"@CustomerGUID = '" + customerGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                accountList.add(new ObjAccount(
                		rs.getString("AccountGUID"), 
                		rs.getString("CustomerGUID"), 
                		rs.getString("Animal"), 
                		rs.getString("Species"),
                		rs.getString("Breed"), 
                		rs.getString("AnimalSex"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("DateBirth")), 
                		rs.getString("Balance")
                    ));

            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return accountList;
    }

}
