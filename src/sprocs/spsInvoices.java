package sprocs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjInvoice;

public class spsInvoices {

    public static ArrayList<ObjInvoice> findInvoicesByAccountGUID(MainFrame mainFrame, String accGUID) {
        ArrayList<ObjInvoice> invoiceList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsInvoicesByAccountGUID " +
            		"@AccountGUID = '" + accGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                invoiceList.add(new ObjInvoice(
                		rs.getString("InvoiceGUID"), 
                		rs.getString("InvoiceRef"), 
                		rs.getString("InvoiceOrCredit"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("InvoiceDate")), 
                		rs.getString("InvoiceNet"),
                		rs.getString("InvoiceVat"), 
                		rs.getString("InvoiceGross"), 
                		rs.getString("Printed"), 
                		rs.getString("PrintedCode")
                    ));
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }
}
