package sprocs;

import javax.swing.*;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjEventHistorical;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class spsEventsByAccountGUID {

    public static ArrayList<ObjEventHistorical> findHistoricalEvents(MainFrame mainFrame, String accGUID) {
        ArrayList<ObjEventHistorical> eventList = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc(
        			"exec spsEventsByAccountGUIDHistorical " +
        			"@AccountGUID = '" + accGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                eventList.add(new ObjEventHistorical(
                		rs.getString("EventGUID"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("StartDate")), 
                		rs.getString("StartTime"),
                		rs.getString("Account"), 
                		rs.getString("Regarding"), 
                		rs.getString("EventType"),
                		rs.getString("Status"), 
                		rs.getString("ForUserID"), 
                		rs.getString("DocumentGUID")));
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public static ArrayList<ObjEventHistorical> findFutureEvents(MainFrame mainFrame, String accGUID) {
        ArrayList<ObjEventHistorical> eventList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsEventsByAccountGUIDFuture " +
            		"@AccountGUID = '" + accGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                eventList.add(new ObjEventHistorical(
                		rs.getString("EventGUID"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("StartDate")), 
                		rs.getString("StartTime"),
                		rs.getString("Account"), 
                		rs.getString("Regarding"), 
                		rs.getString("EventType"),
                		rs.getString("Status"), 
                		rs.getString("ForUserID"), 
                		rs.getString("DocumentGUID")
            		));
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public static ArrayList<ObjEventHistorical> findAccountNotes(MainFrame mainFrame, String accGUID) {
        ArrayList<ObjEventHistorical> eventList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsEventsByAccountGUIDNotes " +
            		"@AccountGUID = '" + accGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                eventList.add(new ObjEventHistorical(
                		rs.getString("EventGUID"), 
                		DateConverter.yyyyMMddToddMMyyyy("" + rs.getString("StartDate")), 
                		rs.getString("StartTime"),
                		rs.getString("Account"), 
                		rs.getString("Regarding"), 
                		rs.getString("EventType"),
                		rs.getString("Status"), 
                		rs.getString("ForUserID"), 
                		rs.getString("DocumentGUID")
                    ));
            }
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return eventList;
    }
}
