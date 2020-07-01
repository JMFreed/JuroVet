package sprocs;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

import main.MainFrame;

public class spsNominalCode {

    // RETURNS KEY-VALUE MAP OF ALL NOMINAL CODES
    public static Map<Integer, String> getAllNomCodeNames(MainFrame mainFrame) {
        Map<Integer, String> nomCodeNames = new TreeMap<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery("exec spsNominalCodesForValueLists");
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                String nomCodeName = rs.getString("NominalCodeName");
                int nomCodeRef = rs.getInt("NominalCodeRef");

                nomCodeNames.put(nomCodeRef, nomCodeName);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nomCodeNames;
    }

    // RETURNS KEY-VALUE MAP OF NOMINAL CODES FOR CREATING OR EDITING ACCOUNTS
    public static Map<Integer, String> getAccountNomCodeNames(MainFrame mainFrame) {
        Map<Integer, String> nomCodeNames = new TreeMap<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery("exec spsNominalCodesForValueLists");
        	rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                String nomCodeName = rs.getString("NominalCodeName");
                int nomCodeRef = rs.getInt("NominalCodeRef");

                if (nomCodeRef >= 4000 && nomCodeRef <= 4002) {
                    nomCodeNames.put(nomCodeRef, nomCodeName);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nomCodeNames;
    }



    public static String getNomCodeName(MainFrame mainFrame, int nomCode) {
        String nomCodeName = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT ncdNominalCodeName FROM tblNominalCodes " +
        			"WHERE ncdNominalCodeRef = " + nomCode);
        	
        	rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                nomCodeName = rs.getString("ncdNominalCodeName");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nomCodeName;
    }

    public static String getNomCode(MainFrame mainFrame, String nomCodeName) {
        String nomCode = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT ncdNominalCoderef FROM tblNominalCodes " +
        			"WHERE ncdNominalCodeName = '" + nomCodeName + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                nomCode = rs.getString("ncdNominalCodeRef");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nomCode;
    }

    
    public static String getNomCodeGUID(MainFrame mainFrame, int nomCodeRef) {
        String nomCodeGUID = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT ncdNominalCodeGUID FROM tblNominalCodes " +
        			"WHERE ncdNominalCodeRef = " + nomCodeRef);
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                nomCodeGUID = rs.getString("ncdNominalCodeGUID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nomCodeGUID;
    }
    

    public static String getNomCodeGUID(MainFrame mainFrame, String nomCodeName) {
        String nomCodeGUID = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT ncdNominalCodeGUID FROM tblNominalCodes " +
        			"WHERE ncdNominalCodeName = '" + nomCodeName + "'");

            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                nomCodeGUID = rs.getString("ncdNominalCodeGUID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nomCodeGUID;
    }
}
