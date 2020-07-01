package sprocs;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

import main.MainFrame;

public class spsDiscountTypes {

    public static Map<Integer, String> getDiscountTypes(MainFrame mainFrame) {
        Map<Integer, String>  breedList = new TreeMap<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery("SELECT * FROM tblDiscountTypeConstants");
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                int discTypeID = rs.getInt("dtcDiscountTypeID");
                String discDescription = rs.getString("dtcDescription");

                breedList.put(discTypeID, discDescription);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return breedList;
    }
}
