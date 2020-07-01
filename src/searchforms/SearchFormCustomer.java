package searchforms;

import javax.swing.*;

import main.MainFrame;
import managers.ActionManager;

import java.awt.*;
import java.awt.event.FocusListener;

public class SearchFormCustomer extends SearchForm {

	private MainFrame mainFrame;
	
    private final JLabel cusSurnameLabel = new JLabel("Surname: ");
    private final JLabel cusAddressLabel = new JLabel ("Address: ");
    private final JLabel cusTelephoneLabel = new JLabel ("Telephone: ");
    private final JLabel cusAccountLabel = new JLabel ("Animal: ");
    
    private final JLabel[] formLabels = { cusSurnameLabel, cusAddressLabel, cusTelephoneLabel, cusAccountLabel };

	
    private final JTextField cusSurnameField = new JTextField(10);
    private final JTextField cusAddressField = new JTextField(10);
    private final JTextField cusTelephoneField = new JTextField(10);
    private final JTextField cusAccountField = new JTextField(10);
    
    private final JTextField[] formFields = { cusSurnameField, cusAddressField, cusTelephoneField, cusAccountField };

    // CONSTRUCTOR
    public SearchFormCustomer(MainFrame mainFrame) {
        super(mainFrame, "Search customer");
        searchButton.setAction(ActionManager.getInstance(mainFrame).getSearchCustomerAction());
        
        // If the customer surname field is empty, then cannot search for an animal
        cusSurnameField.addPropertyChangeListener(e -> {
        	if (cusSurnameField.getText().length() < 1) {
        		cusAccountField.setText("");
        		cusAccountField.setEditable(false);
        	}
        	else {
        		cusAccountField.setEditable(true);
        	}
        });
        
        // Add textfield highlighter to all search fields
        for (JTextField field : formFields) {
        	new TextFieldHighlighter(field);
        }
        
        
        for (int i = 0; i < formLabels.length; i++) {
            getGC().weightx = 1;
            getGC().weighty = 0.01;

            getGC().gridx = 0;
            getGC().gridy = i;
            getGC().fill = GridBagConstraints.NONE;
            getGC().anchor = GridBagConstraints.LINE_START;
            add(formLabels[i], getGC());

            getGC().gridx = 1;
            getGC().gridy = i;
            getGC().fill = GridBagConstraints.NONE;
            getGC().anchor = GridBagConstraints.LINE_START;
            add(formFields[i], getGC());
        }

    }

    public void clearFields() {
        for (JTextField field : formFields) {
        	field.setText("");
        }
    }
    
    public boolean allFieldsEmpty() {
    	if (getCusSurnameField().getText().isEmpty() &&
    			getCusAddressField().getText().isEmpty() &&
    			getCusTelephoneField().getText().isEmpty() &&
    			getCusAccountField().getText().isEmpty()) {
    		return true;
    	}
    	return false;
    }
    
    public JButton getSearchButton() {
    	return searchButton;
    }

	public JTextField getCusSurnameField() {
		return cusSurnameField;
	}

	public JTextField getCusAddressField() {
		return cusAddressField;
	}

	public JTextField getCusTelephoneField() {
		return cusTelephoneField;
	}

	public JTextField getCusAccountField() {
		return cusAccountField;
	}

    
}
