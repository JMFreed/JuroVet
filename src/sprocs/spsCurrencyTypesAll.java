package sprocs;

import java.sql.*;
import java.util.ArrayList;

import main.MainFrame;

public class spsCurrencyTypesAll {

    public static ArrayList<String> getAllCurrencyTypes(MainFrame mainFrame) {
        ArrayList<String> currencyTypes = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc("exec spsCurrencyTypesForValueLists");
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                String currencyTypeID = rs.getString("CurrencyTypeID");
                String currency = rs.getString("CurrencyType");

                currencyTypes.add(currencyTypeID + " - " + currency);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return currencyTypes;
    }
}
