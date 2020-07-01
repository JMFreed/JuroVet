package lists;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import main.MainFrame;

import java.awt.*;

public abstract class List extends JPanel {
	
	/*
	 * Superclass for showing list of customers, accounts, addresses, products, invoices, appointments
	 */
	protected MainFrame mainFrame;
    protected String[] columnNames;
    protected String[][] data;
    protected DefaultTableModel tableModel;
    protected JTable table;
    protected TableColumnModel tableColumnModel;
    protected JScrollPane tableScrollPane;

    // CONSTRUCTOR
    public List(MainFrame mainFrame, String[] columnNames, String[][] data) {
    	this.mainFrame = mainFrame;
    	this.columnNames = columnNames;
        this.data = data;
        this.tableModel = new DefaultTableModel(data, columnNames)
        
        // Make table read-only
        { public boolean isCellEditable (int row, int column) {
            return false;
        }};

        this.table = new JTable (tableModel);
        this.getTable().setShowGrid(false);
        setTableSize(mainFrame.getWidth() - 40, mainFrame.getHeight() - 200);
        this.tableColumnModel = table.getColumnModel();
        table.setFillsViewportHeight(true);
        table.setLayout(new BorderLayout());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.tableScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.repaint();
        add(tableScrollPane);
        setVisible(true);
    }
    
    
    public List(JPanel panel, String[] columnNames, String[][] data) {
    	this.columnNames = columnNames;
        this.data = data;
        this.tableModel = new DefaultTableModel(data, columnNames)
        
        // Make table read-only
        { public boolean isCellEditable (int row, int column) {
            return false;
        }};

        this.table = new JTable (tableModel);
        this.getTable().setShowGrid(false);
        setTableSize(panel.getWidth() - 40, panel.getHeight() - 200);
        this.tableColumnModel = table.getColumnModel();
        table.setFillsViewportHeight(true);
        table.setLayout(new BorderLayout());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        this.tableScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.repaint();
        add(tableScrollPane);
        setVisible(true);
    }

    // ACCESSORS
    public String[] getColumnNames() { return this.columnNames; }
    public String[][] getData() { return data; }
    public DefaultTableModel getTableModel() { return this.tableModel; }
    public JTable getTable() { return table; }
    public TableColumnModel getTableColumnModel() { return tableColumnModel; }
    public JScrollPane getTableScrollPane() { return tableScrollPane; }
    
    public void setData(String[][] newData) { this.data = newData; }
    
    public void setTableSize(int x, int y) {
        table.setPreferredScrollableViewportSize(new Dimension(x, y));
    }
}
