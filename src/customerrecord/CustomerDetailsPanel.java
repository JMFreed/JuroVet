package customerrecord;

import javax.swing.*;
import javax.swing.border.Border;

import main.MainFrame;
import sprocs.spsAddress;

import java.awt.*;

class CustomerDetailsPanel extends JPanel {
	
	private static final Dimension textFieldDim = new Dimension(250,20);

	 private final JLabel cusNameLabel = new JLabel("Customer:");
	 private final JLabel cusFirstNameLabel = new JLabel("First name:");
	 private final JLabel cusOccupationLabel = new JLabel("Occupation:");
	 private final JLabel cusFirstRegLabel = new JLabel("First registered");
	 private final JLabel cusTelephoneLabel = new JLabel("Telephone:");
	 private final JLabel cusMobileLabel = new JLabel("Mobile:");
	 private final JLabel cusEmailLabel = new JLabel("Email:");
	 private final JLabel cusACStatusLabel = new JLabel("A/C status:");
	 private final JLabel cusAddressLabel = new JLabel("Address");
	
	private JTextField cusNameField;
	private JTextField cusFirstNameField;
	private JTextField cusOccupationField;
	private JTextField cusFirstRegField;
	private JTextField cusTelephoneField;
	private JTextField cusMobileField;
	private JTextField cusEmailField;
	private JTextField cusACStatusField;
	private JTextField cusAddressLine1Field;
	private JTextField cusAddressLine2Field;
	private JTextField cusAddressLine3Field;
	private JTextField cusAddressLine4Field;
	private JTextField cusAddressLine5Field;
	private JTextField cusAddressLine6Field;

    // CONSTRUCTOR
    CustomerDetailsPanel(MainFrame mainFrame, String[] customerDetails) {
        // RETURN ARRAY OF ADDRESS DETAILS
        String[] addressDetails = spsAddress.findAddressByGUID(mainFrame, customerDetails[7]);
        
        this.cusNameField = new JTextField(customerDetails[3] + ", " + customerDetails[4] + " " + customerDetails[5], 15);
        this.cusFirstNameField = new JTextField(customerDetails[6], 15);
        this.cusOccupationField = new JTextField(customerDetails[13], 15);
        this.cusFirstRegField = new JTextField(customerDetails[66], 15);
        this.cusTelephoneField = new JTextField(customerDetails[8], 15);
        this.cusMobileField = new JTextField(customerDetails[10], 15);
        this.cusEmailField = new JTextField(customerDetails[12], 15);
        this.cusACStatusField = new JTextField(customerDetails[22], 15);
        this.cusAddressLine1Field = new JTextField(addressDetails[0], 15);
        this.cusAddressLine2Field = new JTextField(addressDetails[1], 15);
        this.cusAddressLine3Field = new JTextField(addressDetails[2], 15);
        this.cusAddressLine4Field = new JTextField(addressDetails[3], 15);
        this.cusAddressLine5Field = new JTextField(addressDetails[4], 15);
        this.cusAddressLine6Field = new JTextField(addressDetails[5], 15);

        Border innerBorder = BorderFactory.createTitledBorder("Customer Details");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel[] labelsLeft = {
        		cusNameLabel, cusFirstNameLabel, cusOccupationLabel, cusFirstRegLabel };

        JTextField[] fieldsLeft = { 
        		cusNameField, cusFirstNameField, cusOccupationField, cusFirstRegField };

        for (JTextField field : fieldsLeft) {
            field.setMinimumSize(textFieldDim);
        }

        for (int i = 0; i < labelsLeft.length; i++) {
            gc.weightx = 1;
            gc.weighty = 0.01;

            gc.gridx = 0;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(labelsLeft[i], gc);

            gc.gridx = 1;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LAST_LINE_START;
            add(fieldsLeft[i], gc);
            fieldsLeft[i].setEditable(false);
        }
        
        JLabel[] labelsCenter = { 
        		cusTelephoneLabel, cusMobileLabel, cusEmailLabel, cusACStatusLabel };
        
        JTextField[] fieldsCenter = { 
        		cusTelephoneField, cusMobileField, cusEmailField, cusACStatusField };
        
        for (JTextField field : fieldsCenter) {
            field.setMinimumSize(textFieldDim);
        }

        for (int i = 0; i < labelsCenter.length; i++) {
            gc.weightx = 1;
            gc.weighty = 0.01;

            gc.gridx = 2;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(labelsCenter[i], gc);

            gc.gridx = 3;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LAST_LINE_START;
            add(fieldsCenter[i], gc);
            fieldsCenter[i].setEditable(false);
        }

        JTextField[] fieldsRight = {
        		cusAddressLine1Field, cusAddressLine2Field, cusAddressLine3Field, 
        		cusAddressLine4Field, cusAddressLine5Field, cusAddressLine6Field };

        for (JTextField field : fieldsRight) {
            field.setMinimumSize(textFieldDim);
        }

        gc.weightx = 1;
        gc.weighty = 0.01;

        gc.gridx = 4;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(cusAddressLabel, gc);

        for (int i = 0; i < fieldsRight.length; i++) {
            gc.weightx = 1;
            gc.weighty = 0.01;

            gc.gridx = 5;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(fieldsRight[i], gc);
            fieldsRight[i].setEditable(false);
        }
    }

    // MUTATORS
    void setCusDetails(String cusName, String cusFirstName, String cusOccupation, String cusFirstReg,
                              String cusTelephone, String cusMobile, String cusEmail, String cusACStatus,
                              String cusAddressLine1, String cusAddressLine2, String cusAddressLine3,
                              String cusAddressLine4, String cusAddressLine5, String cusAddressCountry) {

        this.cusNameField.setText(cusName);
        this.cusFirstNameField.setText(cusFirstName);
        this.cusOccupationField.setText(cusOccupation);
        this.cusFirstRegField.setText(cusFirstReg);
        this.cusTelephoneField.setText(cusTelephone);
        this.cusMobileField.setText(cusMobile);
        this.cusEmailField.setText(cusEmail);
        this.cusACStatusField.setText(cusACStatus);
        this.cusAddressLine1Field.setText(cusAddressLine1);
        this.cusAddressLine2Field.setText(cusAddressLine2);
        this.cusAddressLine3Field.setText(cusAddressLine3);
        this.cusAddressLine4Field.setText(cusAddressLine4);
        this.cusAddressLine5Field.setText(cusAddressLine5);
        this.cusAddressLine6Field.setText(cusAddressCountry);
    }
}
