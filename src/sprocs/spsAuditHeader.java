package sprocs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjAuditHeader;

public class spsAuditHeader {
	

    public static ArrayList<ObjAuditHeader> getAccAuditHeaders (MainFrame mainFrame, String accGUID) {
        ArrayList<ObjAuditHeader> accAuditHeadersList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsAuditHeadersByAccountGUID " +
            		"@AccountGUID = '" + accGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                accAuditHeadersList.add(new ObjAuditHeader(
                		rs.getString("HeaderGUID"), 
                		rs.getString("TranType"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("TranDate")), 
                		rs.getString("InvRef"), 
                		rs.getString("InvRefGUID"),
                		rs.getString("Details"), 
                		rs.getString("GrossAmount"), 
                		rs.getString("Outstanding")
            		));

            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return accAuditHeadersList;
    }

    public static ArrayList<ObjAuditHeader> getOutstandingAuditHeaders (MainFrame mainFrame, String cusGUID) {
        ArrayList<ObjAuditHeader> accAuditHeadersList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsAuditHeadersByAccountRefGUID " +
            		"@AccountRefGUID = '" + cusGUID + "', " +
            		"@OutstandingOnly = 1");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                accAuditHeadersList.add(new ObjAuditHeader(
                		rs.getString("HeaderGUID"), 
                		rs.getString("TranType"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("TranDate")), 
                		rs.getString("InvRef"), 
                		rs.getString("InvRefGUID"),
                		rs.getString("Details"), 
                		rs.getString("GrossAmount"), 
                		rs.getString("Outstanding")
            		));

            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return accAuditHeadersList;
    }

    public static ArrayList<ObjAuditHeader> getPaidAuditHeaders (MainFrame mainFrame, String cusGUID) {
        ArrayList<ObjAuditHeader> accAuditHeadersList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsAuditHeadersByAccountRefGUID " +
            		"@AccountRefGUID = '" + cusGUID + "', " +
            		"@OutstandingOnly = 0");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                accAuditHeadersList.add(new ObjAuditHeader(
                		rs.getString("HeaderGUID"), 
                		rs.getString("TranType"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("TranDate")), 
                		rs.getString("InvRef"), 
                		rs.getString("InvRefGUID"),
                		rs.getString("Details"), 
                		rs.getString("GrossAmount"), 
                		rs.getString("Debit"), 
                		rs.getString("Credit"), 
                		rs.getString("Outstanding")
            		));

            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return accAuditHeadersList;
    }

}
