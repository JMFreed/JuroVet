package customerrecord;

import javax.swing.*;

import main.MainFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

class CustomerPane extends JPanel {

    // FIELDS
    private CustomerDetailsPanel cusDetailsPnl;
    private CustomerTabbedPanel cusTabbedPnl;
    private JButton newButton;
    private JButton editButton;

    // CONSTRUCTOR
    CustomerPane(MainFrame mainFrame, CustomerRecordFrame frame, String[] customerDetails) {
        this.cusDetailsPnl = new CustomerDetailsPanel(mainFrame, customerDetails);
        cusDetailsPnl.setMinimumSize(new Dimension(frame.getWidth()-100, frame.getHeight()-50));
        add(cusDetailsPnl, BorderLayout.NORTH);
        this.cusTabbedPnl = new CustomerTabbedPanel(mainFrame, frame, this, customerDetails[0]);
        cusTabbedPnl.setMinimumSize(new Dimension(frame.getWidth()-100, frame.getHeight()-50));
        add(cusTabbedPnl, BorderLayout.CENTER);
        setVisible(true);


        this.newButton = new JButton("New Account...");

        this.editButton = new JButton("Edit Customer...");
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
        	mainFrame.removeSelectedFrame(frame);
        	mainFrame.showSelectedFrame(mainFrame.getListCustomer().getFrame());
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx=0;
        gc.gridy=0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(cusDetailsPnl, gc);

        gc.gridx=0;
        gc.gridy=1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(cusTabbedPnl, gc);

        // BUTTON POSITIONS
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx= 0;
        gc.gridy= 2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(newButton, gc);

        gc.gridx=0;
        gc.gridy=2;
        gc.fill=GridBagConstraints.NONE;
        gc.anchor=GridBagConstraints.CENTER;
        add(editButton, gc);

        gc.gridx=2;
        gc.gridy=2;
        gc.fill=GridBagConstraints.NONE;
        gc.anchor=GridBagConstraints.LINE_END;
        add(closeButton, gc);
    }

    // ACCESSORS
    CustomerDetailsPanel getCusDetailsPnl() {
        return cusDetailsPnl;
    }
    CustomerTabbedPanel getCusTabbedPnl() {
        return cusTabbedPnl;
    }

    JButton getNewButton() { return this.newButton; }
    JButton getEditButton() { return this.editButton; }

    ActionListener[] getNewButtonActionListener() { return this.newButton.getActionListeners(); }
    ActionListener[] getEditButtonActionListener() { return this.editButton.getActionListeners(); }
}
