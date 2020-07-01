package customerrecord;

import javax.swing.*;

import java.awt.Dimension;
import java.util.ArrayList;

public class CustomerRecordPanel extends JPanel {

    // FIELDS
    private String name;
    private ArrayList<Object> arrList;
    private JPanel panel;
    private boolean existFlag;

    // CONSTRUCTOR
    CustomerRecordPanel(String name) {
        this.name = name;
        this.panel = null;
        this.existFlag = false;
    }

    // ACCESSORS
    @Override
    public String getName() { return name; }
    JPanel getTable() { return panel; }
    boolean exists() { return existFlag; }
    ArrayList<Object> getArrList() { return arrList; }

    // MUTATORS
    void makeTable(JPanel panel) {
        this.panel = panel;
        this.add(panel);
        this.setExists(true);
    }

    void setExists(boolean exists) { this.existFlag = exists; }
    void setArrList(ArrayList<Object> newArrList) { this.arrList = newArrList; }
}
