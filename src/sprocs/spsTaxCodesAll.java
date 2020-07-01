package sprocs;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

import main.MainFrame;

public class spsTaxCodesAll {

    public static Map<String, Double> getAllTaxCodes(MainFrame mainFrame) {
        Map<String, Double> taxCodes = new TreeMap<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc("exec spsTaxCodesAll");
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                String taxCode = rs.getString("TaxCode").substring(1);
                double taxRate = rs.getInt("TaxRate");
                // FORMAT T01, T02, T10, T14
                if (taxCode.length() == 1) { taxCodes.put("T0" + taxCode, taxRate); }
                else { taxCodes.put("T" + taxCode, taxRate); }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return taxCodes;
    }
}
