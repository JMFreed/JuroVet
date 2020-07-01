package searchforms;

import javax.swing.*;
import javax.swing.border.Border;

import main.MainFrame;
import managers.ActionManager;

import java.awt.*;


public class SearchFormAddress extends SearchForm {

    private final JLabel addressLabel = new JLabel ("Address: ");
    private final JLabel postcodeLabel = new JLabel("Postcode: ");
    
    private final JLabel[] formLabels = { addressLabel, postcodeLabel };
	
	private final JTextField addressField = new JTextField(10);
    private final JTextField postcodeField = new JTextField(10);
    
    private final JTextField[] formFields = { addressField, postcodeField };
	
    // CONSTRUCTOR
    public SearchFormAddress(MainFrame mainFrame) {
        super(mainFrame, "Search Address");
        searchButton.setAction(ActionManager.getInstance(mainFrame).getSearchAddressAction());
        
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
    
	public JTextField getAddressField() {
		return addressField;
	}

	public JTextField getPostcodeField() {
		return postcodeField;
	}

	public boolean allFieldsEmpty() {
		if (getAddressField().getText().isEmpty() &&
				getPostcodeField().getText().isEmpty()) {
			return true;
		}
		return false;
	}

}
