package calendars;

import javax.swing.*;
import java.awt.*;

class CalendarDateSelector extends DefaultCellEditor {

    // FIELDS
    private CalendarDialog calFrame;
    private JButton button;
    private JTextField field;
    private String label;
    private boolean isPushed;

    // CONSTRUCTOR
    CalendarDateSelector(CalendarDialog calFrame, JCheckBox checkBox, JTextField field) {
        super(checkBox);
        this.calFrame = calFrame;
        this.field = field;
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
        if (isPushed && !(label.equals(""))) {
            field.setText(getDate());
            calFrame.dispose();
        }
        isPushed = false;
        return label;
    }

    public String getDate() {
        if (!(label.equals(""))) {
            int year = CalendarDialog.getYear();
            int month = CalendarDialog.getMonth();
            if (month < 9 && Integer.parseInt(label) < 10) {
                return "0" + label + "-0" + (month + 1) + "-" + year;
            } else if (month < 9 && Integer.parseInt(label) >= 10) {
                return "" + label + "-0" + (month + 1) + "-" + year;
            } else if (month >= 9 && Integer.parseInt(label) < 10) {
                return "0" + label + "-" + (month + 1) + "-" + year;
            } else {
                return "" + label + "-" + (month + 1) + "-" + year;
            }
        }
        return null;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}