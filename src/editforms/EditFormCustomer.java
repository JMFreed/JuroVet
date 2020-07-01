package editforms;

import javax.swing.*;
import javax.swing.border.Border;

import customerrecord.CustomerRecordFrame;
import lists.ListAddress;
import lists.ListFrame;
import main.MainFrame;
import objects.ObjAddress;
import searchforms.TextFieldHighlighter;
import sprocs.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class EditFormCustomer extends EditForm {

    private EditFrame frame;
    
    private String[] customerDetails;
    
    private final JLabel cusSageCustomerIDLabel= new JLabel("Sage Customer ID *");
    private final JLabel cusLastNameLabel = new JLabel ("Last name *");
    private final JLabel cusTitleLabel = new JLabel ("Title *");
    private final JLabel cusInitialsLabel = new JLabel ("Initials");
    private final JLabel cusFirstNameLabel = new JLabel ("First name");
    private final JLabel cusAddressLabel = new JLabel("Address *");
    private final JLabel cusAddressPostcodeLabel = new JLabel ("Postcode *");
    private final JLabel cusAddressCountryLabel = new JLabel ("Country *");
    private final JLabel cusHomePhoneLabel= new JLabel("Home phone");
    private final JLabel cusWorkPhoneLabel= new JLabel("Work phone");
    private final JLabel cusMobilePhoneLabel= new JLabel("Mobile phone");
    private final JLabel cusFaxLabel = new JLabel("Fax");
    private final JLabel cusEmailLabel= new JLabel("Email");
    private final JLabel cusOccupationLabel= new JLabel("Occupation");
    private final JLabel cusDeptNameLabel= new JLabel("Dept name");
    private final JLabel cusCurrencyTypeLabel = new JLabel("Currency type");

    private String cusCustomerGUID;
    private final JTextField cusSageCustomerIDField = new JTextField();
    private final JTextField cusLastNameField = new JTextField();
    private ArrayList<String> titles = new ArrayList<String>();
    private final JComboBox<String> cusTitleField = new JComboBox<>();
    private final JTextField cusInitialsField = new JTextField();
    private final JTextField cusFirstNameField = new JTextField();

    private String cusAddressGUID;
    private final JTextArea cusAddressField = new JTextArea();
    private final JTextField cusAddressPostcodeField = new JTextField();
    private final JTextField cusAddressCountryField = new JTextField();
    private final JTextField findAddressField = new JTextField ();
    private final JButton findAddressButton = new JButton("Find address...");

    private final JTextField cusHomePhoneField = new JTextField();
    private final JTextField cusWorkPhoneField = new JTextField();
    private final JTextField cusMobilePhoneField = new JTextField();
    private final JTextField cusFaxField = new JTextField();
    private final JTextField cusEmailField = new JTextField();
    private final JTextField cusOccupationField = new JTextField();

    private final JComboBox<String> cusDeptNameField = new JComboBox<>();
    private final JComboBox<String> cusCurrencyTypeField = new JComboBox<>();

    public EditFormCustomer(MainFrame mainFrame, EditFrame frame) {
    	super(mainFrame, frame);
    	this.frame = frame;
    }
    
    // CONSTRUCTOR
    public EditFormCustomer(MainFrame mainFrame, EditFrame frame, CustomerRecordFrame cusRecFrame, String[] customerDetails) {
    	super(mainFrame, frame);
        this.frame = frame;
        this.customerDetails = customerDetails;
        
        Dimension fieldDim = new Dimension(200, 20);
        this.cusCustomerGUID = customerDetails[0];
        cusSageCustomerIDField.setText(customerDetails[1]);
        cusLastNameField.setText(customerDetails[3]);
        this.titles = spsCustomer.getAllCustomerTitles(mainFrame);
        for (String title : titles) { cusTitleField.addItem(title); }
        cusTitleField.setSelectedItem(customerDetails[4]);
        cusInitialsField.setText(customerDetails[5]);
        cusFirstNameField.setText(customerDetails[6]);

        this.cusAddressGUID = customerDetails[7];
        String[] cusAddress = spsAddress.findAddressByGUID(mainFrame, customerDetails[7]);
        cusAddressField.setEditable(false); cusAddressField.setText(cusAddress[0] + "\n" + cusAddress[1] + "\n" +
                                                                    cusAddress[2] + "\n" + cusAddress[3]);
        cusAddressPostcodeField.setEditable(false); cusAddressPostcodeField.setText(cusAddress[4]);
        cusAddressCountryField.setEditable(false); cusAddressCountryField.setText(cusAddress[5]);

        cusHomePhoneField.setText(customerDetails[8]);
        cusWorkPhoneField.setText(customerDetails[9]);
        cusMobilePhoneField.setText(customerDetails[10]);
        cusFaxField.setText(customerDetails[11]);
        cusEmailField.setText(customerDetails[12]);
        cusOccupationField.setText(customerDetails[13]);

        ArrayList<String> deptNames = spsDepartmentsAll.getAllDepartments(mainFrame);
        for (String dept : deptNames) { cusDeptNameField.addItem(dept); }
        cusDeptNameField.setSelectedItem(customerDetails[20]);

        ArrayList<String> currencyTypes = spsCurrencyTypesAll.getAllCurrencyTypes(mainFrame);
        for (String currency : currencyTypes) { cusCurrencyTypeField.addItem(currency); }
        cusCurrencyTypeField.setSelectedIndex(Integer.parseInt(customerDetails[28]) - 1);

        findAddressButton.addActionListener(e -> {
            if (findAddressField.getText().length() > 2) {
                ArrayList<ObjAddress> addressList = null;
				try {
					addressList = spsAddress.findAddressByName(mainFrame, findAddressField.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
                String[] columns = {"AddressGUID", "AddressLine1", "AddressLine2", "AddressLine3", "AddressLine4", "Postcode", "Country"};
                String[][] data = new String[addressList.size()][columns.length];
                int i = 0;
                while ( i < addressList.size() ) {
                    for (ObjAddress objAddress : addressList) {
                        data[i][0] = objAddress.getAddressLine1();
                        data[i][1] = objAddress.getAddressLine2();
                        data[i][2] = objAddress.getAddressLine3();
                        data[i][3] = objAddress.getAddressLine4();
                        data[i][4] = objAddress.getAddressLine5();
                        data[i][5] = objAddress.getPostcode();
                        data[i][6] = objAddress.getCountry();
                        i++;
                    }
                }
                if (addressList.size() < 1) {
                    JOptionPane.showMessageDialog(frame, "No addresses found");
                    findAddressField.setText("");
                } else {
                    ListFrame findAddressFrame = new ListFrame(new ListAddress(mainFrame, data), mainFrame);
                    findAddressFrame.getList().getTable().addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent me) {
                            if (me.getClickCount() == 2) { // On double-click
                                int selectedRow = findAddressFrame.getList().getTable().getSelectedRow();
                                cusAddressGUID = data[selectedRow][0];
                                cusAddressField.setText(data[selectedRow][1] + "\n" +
                                        data[selectedRow][2] + "\n" +
                                        data[selectedRow][3] + "\n" +
                                        data[selectedRow][4]);
                                cusAddressPostcodeField.setText(data[selectedRow][5]);
                                cusAddressCountryField.setText(data[selectedRow][6]);
                                findAddressFrame.dispose();
                            }
                        }
                    });
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Not enough search information");
            }
        });

        JButton createButton = new JButton("Update");

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> frame.setVisible(false));

        Border innerBorder = BorderFactory.createTitledBorder("New customer record");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // GRIDBAG LAYOUT FOR CUSTOMER
        JLabel[] customerLabels = {cusSageCustomerIDLabel, cusLastNameLabel, cusTitleLabel, cusInitialsLabel,
                cusFirstNameLabel, cusAddressLabel, cusAddressPostcodeLabel, cusAddressCountryLabel,
                cusHomePhoneLabel, cusWorkPhoneLabel, cusMobilePhoneLabel, cusFaxLabel,
                cusEmailLabel, cusOccupationLabel , cusDeptNameLabel, cusCurrencyTypeLabel };

        Component[] customerFields = {cusSageCustomerIDField, cusLastNameField, cusTitleField, cusInitialsField,
                cusFirstNameField, cusAddressField, cusAddressPostcodeField, cusAddressCountryField,
                cusHomePhoneField, cusWorkPhoneField, cusMobilePhoneField, cusFaxField,
                cusEmailField, cusOccupationField, cusDeptNameField, cusCurrencyTypeField };

        JTextField[] textFields = { cusSageCustomerIDField, cusLastNameField, cusInitialsField, cusFirstNameField,
                cusHomePhoneField, cusWorkPhoneField, cusMobilePhoneField,
                cusFaxField, cusEmailField, cusOccupationField, findAddressField };

        for (JTextField field : textFields) { new TextFieldHighlighter(field); }

        for (Component comp : customerFields) { comp.setPreferredSize(fieldDim); }
        cusAddressField.setPreferredSize(new Dimension(200,80));
        cusAddressField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

        // INITIALIZE GRIDBAG FIELDS
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        for (int i=0; i<customerLabels.length; i++) {
            gc.weightx = 0.5;
            gc.weighty = 0.01;

            gc.gridx = 0;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(customerLabels[i], gc);

            gc.gridx = 1;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(customerFields[i], gc);
        }

        gc.gridx = 5;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("* Required fields"), gc);

        gc.gridx = 5;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_START;
        add(findAddressButton, gc);

        gc.gridx = 6;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_START;
        findAddressField.setPreferredSize(fieldDim);
        add(findAddressField, gc);

        gc.gridx = 5;
        gc.gridy = 30;
        gc.anchor = GridBagConstraints.LINE_START;
        add(createButton, gc);

        gc.gridx = 6;
        gc.gridy = 30;
        gc.anchor = GridBagConstraints.LINE_START;
        add(closeButton, gc);
    }
    
    /*
     * ACCESSORS
     */
    
	public EditFrame getFrame() {
		return frame;
	}
	
	public String[] getCustomerDetails() {
		return customerDetails;
	}

	public String getCusCustomerGUID() {
		return cusCustomerGUID;
	}

	public JTextField getCusSageCustomerIDField() {
		return cusSageCustomerIDField;
	}

	public JTextField getCusLastNameField() {
		return cusLastNameField;
	}

	public ArrayList<String> getTitles() {
		return titles;
	}

	public JComboBox<String> getCusTitleField() {
		return cusTitleField;
	}

	public JTextField getCusInitialsField() {
		return cusInitialsField;
	}

	public JTextField getCusFirstNameField() {
		return cusFirstNameField;
	}

	public String getCusAddressGUID() {
		return cusAddressGUID;
	}

	public JTextArea getCusAddressField() {
		return cusAddressField;
	}

	public JTextField getCusAddressPostcodeField() {
		return cusAddressPostcodeField;
	}

	public JTextField getCusAddressCountryField() {
		return cusAddressCountryField;
	}

	public JTextField getFindAddressField() {
		return findAddressField;
	}

	public JButton getFindAddressButton() {
		return findAddressButton;
	}

	public JTextField getCusHomePhoneField() {
		return cusHomePhoneField;
	}

	public JTextField getCusWorkPhoneField() {
		return cusWorkPhoneField;
	}

	public JTextField getCusMobilePhoneField() {
		return cusMobilePhoneField;
	}

	public JTextField getCusFaxField() {
		return cusFaxField;
	}

	public JTextField getCusEmailField() {
		return cusEmailField;
	}

	public JTextField getCusOccupationField() {
		return cusOccupationField;
	}

	public JComboBox<String> getCusDeptNameField() {
		return cusDeptNameField;
	}

	public JComboBox<String> getCusCurrencyTypeField() {
		return cusCurrencyTypeField;
	}
    
    
}
