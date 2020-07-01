package sprocs;

import java.sql.*;
import java.util.ArrayList;

import main.MainFrame;

public class spsDepartmentsAll {

    public static ArrayList<String> getAllDepartments(MainFrame mainFrame) {
        ArrayList<String> departments = new ArrayList<>();
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT dptDepartmentName FROM tblDepartments " +
        			"WHERE dptDepartmentName IS NOT NULL");

            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                String department = rs.getString("dptDepartmentName");

                departments.add(department);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public static String getDeptNumber(MainFrame mainFrame, String deptName) {
        String deptNumber = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT dptDepartmentID FROM tblDepartments " +
        			"WHERE dptDepartmentName = '" + deptName + "'");

            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                deptNumber = rs.getString("dptDepartmentID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deptNumber;
    }
}
