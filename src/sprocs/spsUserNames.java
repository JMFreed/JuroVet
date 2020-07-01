package sprocs;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import main.MainFrame;

public class spsUserNames {

    // RETURNS USER NAME
    public static String findUsernameByUserID(MainFrame mainFrame, String userID) {
        String username = null;
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsUserByPk " +
        			"@UserID = '" + userID + "'");
        	
            rs = mainFrame.getDBManager().getResults();

            while ( rs.next() ) {
                username = rs.getString("LastName") + ", " + rs.getString("FirstName");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return username;
    }

    // RETURNS LIST OF USERS THAT HAVEN'T LEFT AND WERE EMPLOYED ON THE DATE
    public static HashMap<String, String> getUsersCurrentDay(MainFrame mainFrame, String date) {
    	HashMap<String, String> usernameList = new HashMap<String, String>();
        ResultSet rs = null;

        try {
        	mainFrame.getDBManager().executeSproc("exec spsUsersCurrentForValueLists");
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                usernameList.put(rs.getString("usrUserID"), rs.getString("usrUser"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usernameList;
    }


    // RETURNS LIST OF USER WITHIN A PARTICULAR GROUP E.G. VETS, NURSES, ADMIN
    public static ArrayList<String> getUsersCurrentDay(MainFrame mainFrame, String date, String userGroup) {
        ArrayList<String> usernameList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT DISTINCT usrLastName, usrFirstName FROM tblUsers " +
                    "WHERE ((usrDateEmployment<'" + date + "' AND usrDateLeft > '" + date + "')" +
                    "OR (usrDateEmployment<'" + date + "' AND usrDateLeft IS NULL)) AND " +
                    "(usrUserGroupID='" + userGroup + "')" );
            
        	rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                usernameList.add(rs.getString("usrLastName") + ", " + rs.getString("usrFirstName"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usernameList;
    }

    public static ArrayList<String> getAllVets(MainFrame mainFrame) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        ArrayList<String> vetList = new ArrayList<>();

        String query = "SELECT DISTINCT usrUserID FROM tblUsers " +
                "WHERE (usrDateEmployment<'" + strDate + "' AND usrDateLeft > '" + strDate + "'" +
                "OR usrDateEmployment<'" + strDate + "' AND usrDateLeft IS NULL) AND usrUserGroupID='VET'";

        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT DISTINCT usrUserID FROM tblUsers " +
        	        "WHERE (usrDateEmployment<'" + strDate + "' AND usrDateLeft > '" + strDate + "'" +
        	        "OR usrDateEmployment<'" + strDate + "' AND usrDateLeft IS NULL) AND usrUserGroupID='VET'" );

        	rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                vetList.add(rs.getString("usrUserID"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vetList;
    }



    // RETURNS LIST OF USER GROUPS
    public static ArrayList<String> getUserGroups(MainFrame mainFrame) {
        ArrayList<String> groupList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery("SELECT DISTINCT usrUserGroupID FROM tblUsers");
        	rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                groupList.add(rs.getString("usrUserGroupID"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return groupList;
    }
    
}
