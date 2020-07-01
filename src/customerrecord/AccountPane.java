package customerrecord;

import javax.swing.*;

import editforms.EditFrame;
import main.MainFrame;
import sprocs.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

class AccountPane extends JPanel {

    // FIELDS
    private MainFrame mainFrame;
    private String[] accountDetails;
    private AccountDetailsPanel accountDetailsPnl;
    private AccountTabbedPanel accountTabbedPnl;
    private JButton newButton;
    private JButton editButton;

    // CONSTRUCTOR
    AccountPane(MainFrame mainFrame, CustomerRecordFrame frame, String[] accountDetails) {
        this.mainFrame = mainFrame;
        this.accountDetails = accountDetails;
        this.accountDetailsPnl = new AccountDetailsPanel(accountDetails);
        accountDetailsPnl.setMinimumSize(new Dimension(1000, 200));
        add(accountDetailsPnl, BorderLayout.NORTH);
        this.accountTabbedPnl = new AccountTabbedPanel(mainFrame, this, accountDetails[0]);
        accountTabbedPnl.setMinimumSize(new Dimension(1000, 200));
        add(accountTabbedPnl, BorderLayout.CENTER);
        setVisible(true);

        this.newButton = new JButton("New Invoice...");
        newButton.addActionListener(e -> {
            System.out.println("New invoice button clicked");
        });

        this.editButton = new JButton("Edit Account...");
        editButton.addActionListener(event -> {
            new EditFrame(mainFrame);
        });

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
        add(accountDetailsPnl, gc);

        gc.gridx=0;
        gc.gridy=1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(accountTabbedPnl, gc);

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
    public MainFrame getMainFrame() { 
    	return this.mainFrame; 
    	}
    
    public AccountDetailsPanel getCusAccDetailsPnl() {
        return accountDetailsPnl;
    }
    
    public AccountTabbedPanel getCusAccTabbedPnl() {
        return accountTabbedPnl;
    }

    JButton getNewButton() { return this.newButton; }
    JButton getEditButton() { return this.editButton; }

    ActionListener[] getNewButtonActionListener() { return this.newButton.getActionListeners(); }
    ActionListener[] getEditButtonActionListener() { return this.editButton.getActionListeners(); }

}
