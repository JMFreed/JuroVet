package calendars;

import javax.swing.*;
import javax.swing.table.*;

import main.MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

class CalendarFrame extends JFrame {

    // FIELDS
    private static final String[] dayList = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
    private static final String[] monthList = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };
    private static JLabel lblMonth;
    private static JButton btnPrev, btnNext, btnToday;
    private static JComboBox<String> cmbYear;
    private static DefaultTableModel mtblCalendar;
    private static JTable tblCalendar;
    private static JScrollPane stblCalendar;
    private static JPanel pnlCalendar;
    private static int realYear, realMonth, realDay, currentYear, currentMonth;
    private MainFrame mainFrame;

    // CONSTRUCTOR
    CalendarFrame(MainFrame mainFrame) {
        super("JuroVet: Calendar");
        this.mainFrame = mainFrame;
        Container pane = getContentPane(); pane.setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480,350);
        setLocation(mainFrame.getWidth()/2 - this.getWidth()/2, mainFrame.getHeight()/2 - this.getHeight()/2);
        setResizable(false);
        setVisible(true);

        // CREATE CONTROLS
        lblMonth = new JLabel ("January");
        cmbYear = new JComboBox<>(); cmbYear.addActionListener(new cmbYear_Action());
        btnPrev = new JButton ("<<"); btnPrev.addActionListener(new btnPrev_Action());
        btnNext = new JButton (">>"); btnNext.addActionListener(new btnNext_Action());
        btnToday = new JButton("TODAY"); btnToday.addActionListener(new btnToday_Action());
        mtblCalendar = new DefaultTableModel(); mtblCalendar.setColumnCount(7); mtblCalendar.setRowCount(6);
        mtblCalendar.setDataVector(new Object[mtblCalendar.getRowCount()][mtblCalendar.getColumnCount()], dayList);
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        pnlCalendar = new JPanel(null);

        // SET BOUNDS
        pnlCalendar.setBounds(0, 0, this.getWidth(), this.getHeight());
        stblCalendar.setBounds(10, 50, this.getWidth() - 100, this.getHeight() - 100);
        cmbYear.setBounds(stblCalendar.getWidth()/4 - lblMonth.getPreferredSize().width/4, 25, 80, 20);
        lblMonth.setBounds(stblCalendar.getWidth()/4-lblMonth.getPreferredSize().width/4, 0, 100, 25);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(340, 25, 50, 25);
        btnToday.setBounds(190, 5, 80, 30);

        // ADD CONTROLS TO PANE
        pane.add(pnlCalendar); pnlCalendar.add(lblMonth); pnlCalendar.add(cmbYear);
        pnlCalendar.add(btnPrev); pnlCalendar.add(btnNext); pnlCalendar.add(btnToday);
        pnlCalendar.add(stblCalendar);

        // GET CURRENT MONTH AND YEAR
        GregorianCalendar cal = new GregorianCalendar();
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        currentMonth = realMonth; currentYear = realYear;

        tblCalendar.getParent().setBackground(tblCalendar.getBackground());
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);
        tblCalendar.setColumnSelectionAllowed(true); tblCalendar.setRowSelectionAllowed(true);
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCalendar.setRowHeight(38);

        // ADD YEARS TO THE COMBO BOX
        for (int i=realYear-30; i<=realYear+30; i++) {
            cmbYear.addItem(String.valueOf(i));
        }

        // REFRESH CALENDAR
        refreshCalendar (realMonth, realYear);
    }

    // ACCESSORS
    MainFrame getMainFrame() { return this.mainFrame; }
    static int getYear() {
        return currentYear;
    }
    static int getMonth() {
        return currentMonth;
    }
    static int getRealDay() {return realDay; }
    static int getRealMonth() {return realMonth; }
    static int getRealYear() {return realYear; }
    String[] getDayList() { return dayList; }
    JTable getTblCalendar() { return tblCalendar; }

    
    private static void refreshCalendar(int month, int year){
        int numberOfDays, startOfMonth;
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);}
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);}
        lblMonth.setText(monthList[month]);
        lblMonth.setBounds(stblCalendar.getWidth()/4-lblMonth.getPreferredSize().width/4, 0, 100, 25);
        cmbYear.setSelectedItem(String.valueOf(year));

        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }

        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        startOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);

        // DRAW CALENDAR
        for (int i=1; i<=numberOfDays; i++){
            int row = (i+startOfMonth-2)/7;
            int column = (i+startOfMonth-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }
    }

    static class btnPrev_Action implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (currentMonth == 0) {
                currentMonth = 11;
                currentYear -= 1;
            }
            else {
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    
    static class btnNext_Action implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (currentMonth == 11) {
                currentMonth = 0;
                currentYear += 1;
            }
            else {
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class btnToday_Action implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            currentMonth = realMonth;
            currentYear = realYear;
            refreshCalendar(currentMonth, currentYear);
        }
    }

    static class cmbYear_Action implements ActionListener {
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
}