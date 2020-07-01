package sprocs;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

import dateconverter.DateConverter;
import main.MainFrame;
import objects.ObjEventHistorical;

public class spsEventsByCustomerGUID {
	

    public static ArrayList<ObjEventHistorical> findHistoricalEvents(MainFrame mainFrame, String cusGUID) {
        ArrayList<ObjEventHistorical> eventList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsEventsByCustomerGUIDHistorical " +
            		"@CustomerGUID = '" + cusGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();
            populateObjEventHistoricalArrayList(eventList, rs);
            
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public static ArrayList<ObjEventHistorical> findFutureEvents(MainFrame mainFrame, String cusGUID) {
        ArrayList<ObjEventHistorical> eventList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsEventsByCustomerGUIDFuture " +
            		"@CustomerGUID = '" + cusGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();
            populateObjEventHistoricalArrayList(eventList, rs);
            
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return eventList;
    }

    public static ArrayList<ObjEventHistorical> findCustomerNotes(MainFrame mainFrame, String cusGUID) {
        ArrayList<ObjEventHistorical> eventList = new ArrayList<>();
        ResultSet rs = null;
        try {
            mainFrame.getDBManager().executeSproc(
            		"exec spsEventsByCustomerGUIDNotes " +
            		"@CustomerGUID = '" + cusGUID + "'");
            
            rs = mainFrame.getDBManager().getResults();
            populateObjEventHistoricalArrayList(eventList, rs);
            
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return eventList;
    }
    
    public static void populateObjEventHistoricalArrayList(ArrayList<ObjEventHistorical> eventList, ResultSet rs) throws SQLException, ParseException {
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
    }

}
