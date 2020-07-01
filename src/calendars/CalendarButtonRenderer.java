package calendars;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import static calendars.CalendarFrame.*;

import java.awt.*;

public class CalendarButtonRenderer extends JButton implements TableCellRenderer {

    public CalendarButtonRenderer() { 
    	setOpaque(true); 
    	}

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        // Set weekend days to be pink
        if (column == 0 || column == 6){
            setBackground(new Color(255, 220, 220));
        }
        // Set weekdays to be white
        else {
            setBackground(new Color(255, 255, 255));
        }
        // Set today to be yellow
        if (value != null && !(value.equals(""))) {
            if (Integer.parseInt(value.toString()) == getRealDay() &&
                    getMonth() == getRealMonth() &&
                    getYear() == getRealYear()) {
                setBackground(new Color(255, 255, 150));
            }
        }
        if (value == null) { setText(""); }
        else { setText(value.toString()); }
        return this;
    }
}