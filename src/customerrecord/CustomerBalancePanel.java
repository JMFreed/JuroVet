package customerrecord;

import javax.swing.*;
import javax.swing.border.Border;

import sprocs.spsAccountStatus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomerBalancePanel extends JPanel {
	
    private final JLabel currentLabel = new JLabel ("Current");
    private final JLabel day14Label = new JLabel ("14 days");
    private final JLabel day28Label = new JLabel ("28 days");
    private final JLabel day42Label = new JLabel ("42 days");
    private final JLabel olderLabel = new JLabel ("Older");
    private final JLabel inProgressLabel = new JLabel ("In Progress");
    private final JLabel balanceLabel = new JLabel ("Balance");

    // CONSTRUCTOR
    CustomerBalancePanel(CustomerRecordFrame frame, String[] customerDetails) {
    	
    	JTextField currentField = new JTextField(customerDetails[50], 5);
        JTextField day14Field = new JTextField(customerDetails[51], 5);
        JTextField day28Field = new JTextField(customerDetails[52], 5);
        JTextField day42Field = new JTextField(customerDetails[53], 5);
        JTextField olderField = new JTextField(customerDetails[54], 5);
        JTextField inProgressField = new JTextField(customerDetails[46], 5);
        JTextField balanceField = new JTextField(customerDetails[47], 5);
        
        JTextField[] textFields = { 
        		currentField, day14Field, day28Field, day42Field,
                olderField, inProgressField, balanceField 
                };

        for (JTextField field : textFields) {
        	field.setMinimumSize(new Dimension(40,20));
            field.setEditable(false);
        }

        // CHANGE COLOUR OF BALANCE FIELD DEPENDING ON ACCOUNT STATUS
        int accStatus = Integer.parseInt(customerDetails[21]);
        int creditRating = spsAccountStatus.findCreditRatingByAccountStatus(frame.getMainFrame(), accStatus);
        if (creditRating == 1) { balanceField.setBackground(Color.GREEN); }
        else if (creditRating == 2) { balanceField.setBackground(Color.ORANGE); }
        else if (creditRating == 3) { balanceField.setBackground(Color.RED); balanceField.setForeground(Color.WHITE); }
        else { balanceField.setBackground(Color.LIGHT_GRAY); }

        Border innerBorder = BorderFactory.createTitledBorder("Customer Balance");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel[] labels = { currentLabel, day14Label, day28Label, day42Label,
                             olderLabel, inProgressLabel, balanceLabel };

        JTextField[] fields = { currentField, day14Field, day28Field, day42Field,
                olderField, inProgressField, balanceField };

        for (int i=0; i<labels.length;i++) {
            gc.weightx = 0.01;
            gc.weighty = 0.01;

            gc.gridx= i*2;
            gc.gridy= 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(labels[i], gc);

            gc.gridx= (i*2) + 1;
            gc.gridy= 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LAST_LINE_START;
            add(fields[i], gc);
        }
    }
}
