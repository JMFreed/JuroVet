package calendars;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

class CalendarButtonEditor extends DefaultCellEditor {

    private CalendarFrame calFrame;
    private JButton button;
    private String label;
    private boolean isPushed;

    CalendarButtonEditor(CalendarFrame calFrame, JCheckBox checkBox) {
        super(checkBox);
        this.calFrame = calFrame;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        if (value == null) { label = ""; }
        else { label = value.toString(); }

        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
//        if (isPushed && !(label.equals(""))) {
//            ArrayList<ObjEvent> eventList = spsEventsByDate.findEventsByDate(getDateTime());
//            if (eventList.size() < 1) {
//                JOptionPane.showMessageDialog(null, "No events to show");
//            } else {
//                calFrame.getMainFrame().setDiaryFrame(new DiaryFrame(calFrame.getMainFrame(), getDay(), getDate(true), eventList));
//            	calFrame.dispose();
//            }
//        }
        isPushed = false;
        return label;
    }

    private String getDateTime() { return getDate(false) + " 00:00:00.000"; }

    public String getDate(boolean isStrMonth) {
    	int year = CalendarFrame.getYear();
        int month = CalendarFrame.getMonth();
        String strMonth = "";
        String strLabel = "";
        if (!(label.equals("")) && !(isStrMonth)) {
            if (month < 9 && Integer.parseInt(label) < 10) { return "" + year + "-0" + (month + 1) + "-0" + label; }
            else if (month < 9 && Integer.parseInt(label) >= 10) { return "" + year + "-0" + (month + 1) + "-" + label; }
            else if (month >= 9 && Integer.parseInt(label) < 10) { return "" + year + "-" + (month + 1) + "-0" + label; }
            else { return "" + year + "-" + (month + 1) + "-" + label; }
        }
        else if (!(label.contentEquals("")) && isStrMonth) {
        	switch(month) {
        	case 0:
        		strMonth = "January";
        		break;
        	case 1:
        		strMonth = "February";
        		break;
        	case 2:
        		strMonth = "March";
        		break;
        	case 3:
        		strMonth = "April";
        		break;
        	case 4:
        		strMonth = "May";
        		break;
        	case 5:
        		strMonth = "June";
        		break;
        	case 6:
        		strMonth = "July";
        		break;
        	case 7:
        		strMonth = "August";
        		break;
        	case 8:
        		strMonth = "September";
        		break;
        	case 9:
        		strMonth = "October";
        		break;
        	case 10:
        		strMonth = "November";
        		break;
        	case 11:
        		strMonth = "December";
        		break;
        	}
        	switch(label) {
        	case "1":
        	case "21":
        		strLabel += label + "st";
        		break;
        	case "2":
        	case "22":
        		strLabel += label + "nd";
        		break;
        	case "3":
        	case "23":
        		strLabel += label + "rd";
        		break;
        	default:
        		strLabel += label + "th";
        		break;
        	}
        	if (Integer.parseInt(label) < 10) { return strLabel + " " + strMonth + " " + year; }
            else { return strLabel + " " + strMonth + " " + year; }
        }
        return null;
    }
    
    public String getDay() {
    	int row = 0;
    	String day = "";
    	GregorianCalendar cal = new GregorianCalendar(CalendarFrame.getYear(), CalendarFrame.getMonth(), 1);
        int numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int startOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);
    	
        if (isPushed && !(label.equals(""))) {
        	row = (Integer.parseInt(label) + startOfMonth-2)%7;
    	}
        switch(row) {
        case 0:
        	day = "Sunday";
        	break;
        case 1:
        	day = "Monday";
        	break;
        case 2:
        	day = "Tuesday";
        	break;
        case 3:
        	day = "Wednesday";
        	break;
        case 4:
        	day = "Thursday";
        	break;
        case 5:
        	day = "Friday";
        	break;
        case 6:
        	day = "Saturday";
        	break;
        }
//        System.out.println(day);
        return day;
        
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}