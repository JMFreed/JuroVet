package sprocs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjClinicalRecord;

public class spsClinicalRecords {

    public static ArrayList<ObjClinicalRecord> findClinicalRecordOrders(MainFrame mainFrame, String accountGUID) {
        ArrayList<ObjClinicalRecord> listClinicalRecords = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsOrdersXOrderDetailsByAccountGUID " +
        			"@AccountGUID = '" + accountGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                listClinicalRecords.add(new ObjClinicalRecord(
                		rs.getString("OrderDetailGUID"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("DateStart")), 
                		rs.getString("Employee"),
                		rs.getString("OrderDetailText"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("InvoiceDate")), 
                		rs.getString("Printed"),
                		rs.getString("Gross"), 
                		rs.getString("OrderGross"), 
                		rs.getString("OrderGUID")
                ));
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return listClinicalRecords;
    }
}
