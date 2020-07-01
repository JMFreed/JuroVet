package customerrecord;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AccountDetailsPanel extends JPanel {
	
	private String[] accountDetails;
	private JTextField[] fields;

    private final JLabel accNameLabel = new JLabel("Name:");
    private final JLabel accDOBLabel = new JLabel ("Date of Birth:");
    private final JLabel accSpeciesLabel = new JLabel ("Species:");
    private final JLabel accBreedLabel = new JLabel("Breed");
    private final JLabel accColourLabel = new JLabel ("Colour:");
    private final JLabel accNeuteredLabel = new JLabel ("Neutered:");
    private final JLabel accHeightLabel = new JLabel("Height:");
    private final JLabel accWeightLabel = new JLabel ("Weight:");
    private final JLabel accUsualVetLabel = new JLabel("Usual Vet");
    private final JLabel accNominalCodeLabel = new JLabel ("N/C");

    // CONSTRUCTOR
    AccountDetailsPanel(String[] accountDetails) {

        this.accountDetails = accountDetails;
        
        JTextField accNameField = new JTextField();
        JTextField accDOBField = new JTextField();
        JTextField accSpeciesField = new JTextField();
        JTextField accBreedField = new JTextField();
        JTextField accColourField = new JTextField();
        JTextField accNeuteredField = new JTextField();
        JTextField accHeightField = new JTextField();
        JTextField accWeightField = new JTextField();
        JTextField accUsualVetField = new JTextField();
        JTextField accNominalCodeField = new JTextField();
        
        this.fields = new JTextField[] { accNameField, accDOBField, accSpeciesField, accBreedField,
                accColourField, accNeuteredField, accHeightField, accWeightField,
                accUsualVetField, accNominalCodeField };
        
        setSize(new Dimension(800,300));

        // SET THE TEXT OF THE ACCOUNT DETAILS FIELDS
        populateAccDetailsPnl(fields);

        Border innerBorder = BorderFactory.createTitledBorder("Account Details");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        JLabel[] labelsLeft = { accNameLabel, accDOBLabel, accSpeciesLabel,
                                accBreedLabel, accColourLabel, accNeuteredLabel };

        JTextField[] fieldsLeft = { accNameField, accDOBField, accSpeciesField,
                                    accBreedField, accColourField, accNeuteredField };

        for (int i=0; i<labelsLeft.length;i++) {
            gc.weightx = 1;
            gc.weighty = 0.01;

            gc.gridx=0;
            gc.gridy=i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(labelsLeft[i], gc);

            gc.gridx=2;
            gc.gridy=i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LAST_LINE_START;
            add(fieldsLeft[i], gc);
            fieldsLeft[i].setEditable(false);
        }

        JLabel[] labelsRight = { accHeightLabel, accWeightLabel,
                                accUsualVetLabel, accNominalCodeLabel };

        JTextField[] fieldsRight = { accHeightField, accWeightField,
                                    accUsualVetField, accNominalCodeField };

        for (int i=0; i<fieldsRight.length; i++) {
            gc.weightx = 1;
            gc.weighty = 0.01;

            gc.gridx = 4;
            gc.gridy = i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(labelsRight[i], gc);

            gc.gridx=6;
            gc.gridy=i;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            add(fieldsRight[i], gc);
            fieldsRight[i].setEditable(false);
        }
    }

    // ACCESSORS
    String[] getAccDetails() { return this.accountDetails; }
    public JTextField[] getFields() { return this.fields; }

    // MUTATORS
    void setAccDetails(String[] newDetails) { this.accountDetails = newDetails; }

    // POPULATE ACCOUNT DETAILS PANEL
    void populateAccDetailsPnl(JTextField[] fields) {
        fields[0].setText(accountDetails[3]);
        fields[1].setText(accountDetails[12]);
        fields[2].setText(accountDetails[6]);
        fields[3].setText(accountDetails[8]);
        fields[4].setText(accountDetails[10]);
        fields[5].setText(accountDetails[19]);
        fields[6].setText(accountDetails[26]);
        fields[7].setText(accountDetails[27]);
        fields[8].setText(accountDetails[28]);
        fields[9].setText(accountDetails[36]);
        for (JTextField field : fields) { field.setPreferredSize(new Dimension(200,20)); }
    }
}
