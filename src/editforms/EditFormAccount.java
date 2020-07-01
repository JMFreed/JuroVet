package editforms;

import javax.imageio.ImageIO;
import javax.swing.*;

import dateconverter.DateConverter;
import lists.ListAddress;
import lists.ListFrame;
import main.MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import searchforms.TextFieldHighlighter;
import sprocs.*;
import calendars.*;
import objects.*;

public class EditFormAccount extends EditForm {

	private static MainFrame mainFrame;
    private EditFrame frame;

    // ANIMAL DETAILS 1 PANEL COMPONENTS
    private final JLabel accNameLabel = new JLabel("Animal name");
    private final JLabel accWarningLabel = new JLabel("Warning");
    private final JLabel accDateBirthLabel = new JLabel("Date of birth");
    private final JLabel accSpeciesLabel = new JLabel("Species");
    private final JLabel accGenderLabel = new JLabel("Gender");
    private final JLabel accBreedLabel = new JLabel("Breed");
    private final JLabel accColourLabel = new JLabel("Colour");
    private final JLabel accDateNeuteredLabel = new JLabel("Date Neutered");
    private final JLabel accDateDeceasedLabel = new JLabel("Date Deceased");
    private final JLabel accDefNomCodeNameLabel = new JLabel("Nominal Code Name");
    
    private final JTextField accNameField = new JTextField();
    private final JTextField accWarningField = new JTextField();
    private final JTextField accDateBirthField = new JTextField();
    private final JComboBox<String> accSpeciesField = new JComboBox<>();
    private final JComboBox<String> accGenderField = new JComboBox<>();
    private final JComboBox<String> accBreedField = new JComboBox<>();
    private final JComboBox<String> accColourField = new JComboBox<>();
    private final JTextField accDateNeuteredField = new JTextField();
    private final JTextField accDateDeceasedField = new JTextField();
    private final JComboBox<String> accDefNomCodeNameField = new JComboBox<>();

    // ANIMAL DETAILS 2 PANEL COMPONENTS
    private final JTextField accPedigreeNameField = new JTextField();
    private final JTextField accSireField = new JTextField();
    private final JTextField accDamField = new JTextField();
    private final JTextField accIdentificationNumberField = new JTextField();
    private final JTextField accHeightField = new JTextField();
    private final JTextField accWeightField = new JTextField();
    private final JComboBox<String> accUsualVetField = new JComboBox<>();

    // KEPT AT PANEL COMPONENTS
    private String accAddressGUID;
    private final JTextArea accAddressField = new JTextArea();
    private final JTextField findAddressField = new JTextField ();

    // DISCOUNT PANEL COMPONENTS
    private final JTextField accDiscountRateField = new JTextField();
    private final JComboBox<String> accDiscountTypeField = new JComboBox<>();
    private final JTextField accSettlementDueDaysField = new JTextField();
    private final JTextField accSettlementDiscRateField = new JTextField();
    private final JTextField accPaymentDueDaysField = new JTextField();

    // DEFAULT PANEL COMPONENTS
    private final JComboBox<String> accDefTaxCodeField = new JComboBox<>();
    private final JComboBox<String> accCurrencyTypeField = new JComboBox<>();
    private final JComboBox<String> accDeptNameField = new JComboBox<>();
    
    // TEXT FIELDS
    private final JTextField[] textFields = { 
    		accNameField, accWarningField, accPedigreeNameField, accSireField,
            accDamField, accIdentificationNumberField, accHeightField, accWeightField, 
            findAddressField, accDiscountRateField, accSettlementDueDaysField, 
            accSettlementDiscRateField, accPaymentDueDaysField 
            };


    public EditFormAccount(MainFrame mainFrame, EditFrame frame) {
    	super(mainFrame, frame);
    	this.mainFrame = mainFrame;
    }
    
    // CONSTRUCTOR
    public EditFormAccount(MainFrame mainFrame, EditFrame frame, String[] accountDetails) {
    	super(mainFrame, frame);
    	this.mainFrame = mainFrame;

        // CREATE TABBED PANE AND PANELS
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel animalDetails1Pnl = new JPanel();
        JPanel animalDetails2Pnl = new JPanel();
        JPanel keptAtPnl = new JPanel();
        JPanel discountsPnl = new JPanel();
        JPanel defaultsPnl = new JPanel();

        for (JTextField field : textFields) { new TextFieldHighlighter(field); }

        /////////////////////////////////////////////////
        // ANIMAL DETAILS 1 PANEL
        /////////////////////////////////////////////////

        accNameField.setText(accountDetails[3]);
        accWarningField.setText(accountDetails[4]);
        accDateBirthField.setEditable(false); accDateBirthField.setText(accountDetails[12]);
        accDateNeuteredField.setEditable(false); accDateNeuteredField.setText(accountDetails[20]);
        accDateDeceasedField.setEditable(false); accDateDeceasedField.setText(accountDetails[24]);

        Map<String, Integer> species = spsAnimalCharacteristics.getAllSpecies(mainFrame);
        Iterator iterSpecies = species.entrySet().iterator();
        while(iterSpecies.hasNext()) {
            Map.Entry pair = (Map.Entry)iterSpecies.next();
            accSpeciesField.addItem(pair.getKey().toString());
            iterSpecies.remove();
        }

        accSpeciesField.addItem("unknown");
        accSpeciesField.setSelectedItem(accountDetails[6]);

        Map<String, Integer> initBreeds = spsAnimalCharacteristics.getAllBreeds(mainFrame, Integer.parseInt(accountDetails[5]));
        Iterator iterInitBreeds = initBreeds.entrySet().iterator();
        while ( iterInitBreeds.hasNext() ) {
            Map.Entry pair = (Map.Entry) iterInitBreeds.next();
            accBreedField.addItem(pair.getKey().toString());
            iterInitBreeds.remove();
        }
        accBreedField.setSelectedItem(accountDetails[8]);

        Map<String, Integer> initColours = spsAnimalCharacteristics.getAnimalColours(mainFrame, Integer.parseInt(accountDetails[5]));
        Iterator iterInitColour = initColours.entrySet().iterator();
        while ( iterInitColour.hasNext() ) {
            Map.Entry pair = (Map.Entry) iterInitColour.next();
            accColourField.addItem(pair.getKey().toString());
            iterInitColour.remove();
        }
        accColourField.setSelectedItem(accountDetails[10]);

        // WHEN SPECIES SELECTED, REPOPULATE BREED AND COLOUR JCOMBOBOXES
        accSpeciesField.addActionListener(e -> {
            if (accSpeciesField.getSelectedItem().equals("unknown")) {
                resetSpeciesBreedColourFields();
            }
            else {
                try {
                    String speciesID = spsAnimalCharacteristics.getSpeciesID(mainFrame, Objects.requireNonNull(accSpeciesField.getSelectedItem()).toString());
                    accBreedField.removeAllItems(); // RESET BREED COMBOBOX
                    accColourField.removeAllItems(); // RESET COLOUR COMBOBOX

                    Map<String, Integer> breeds = spsAnimalCharacteristics.getAllBreeds(mainFrame, Integer.parseInt(speciesID));
                    Iterator iterBreed = breeds.entrySet().iterator();
                    while ( iterBreed.hasNext() ) {
                        Map.Entry pair = (Map.Entry) iterBreed.next();
                        accBreedField.addItem(pair.getKey().toString());
                        iterBreed.remove();
                    }
                    accBreedField.addItem("unknown");

                    Map<String, Integer> colours = spsAnimalCharacteristics.getAnimalColours(mainFrame, Integer.parseInt(speciesID));
                    Iterator iterColour = colours.entrySet().iterator();
                    while ( iterColour.hasNext() ) {
                        Map.Entry pair = (Map.Entry) iterColour.next();
                        accColourField.addItem(pair.getKey().toString());
                        iterColour.remove();
                    }
                    accColourField.addItem("unknown");

                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }
            }
        });

        String[] genders = {"M", "MN", "F", "FN", "U"};
        for (String gender: genders) { accGenderField.addItem(gender); }
        accGenderField.setSelectedItem(accountDetails[11]);

        // POPULATE NOMINAL CODE NAME JCOMBOBOX
        Map<Integer, String> nomCodes = spsNominalCode.getAccountNomCodeNames(mainFrame);
        Iterator iterNomCodes = nomCodes.entrySet().iterator();
        while(iterNomCodes.hasNext()) {
            Map.Entry pair = (Map.Entry)iterNomCodes.next();
            int nomCode = (Integer) pair.getKey();
            // MAKE SURE ALL NOMINAL CODES HAVE FOUR DIGIT FORMAT
            if (nomCode < 10) { accDefNomCodeNameField.addItem("000" + pair.getKey() + " - " + pair.getValue()); }
            else if (nomCode < 100) { accDefNomCodeNameField.addItem("00" + pair.getKey() + " - " + pair.getValue()); }
            else if (nomCode < 1000) { accDefNomCodeNameField.addItem("0" + pair.getKey() + " - " + pair.getValue()); }
            else { accDefNomCodeNameField.addItem("" + pair.getKey() + " - " + pair.getValue()); }
            iterNomCodes.remove();
        }

        accDefNomCodeNameField.setSelectedItem("" + Integer.parseInt(accountDetails[36]) + " - " + spsNominalCode.getNomCodeName(mainFrame, Integer.parseInt(accountDetails[36])));

        animalDetails1Pnl.setPreferredSize(getPanelDim());
        animalDetails1Pnl.setLayout(new GridBagLayout());
        GridBagConstraints aniDet1PnlGC = new GridBagConstraints();

        JLabel[] animalDetails1PnlLabels = {accNameLabel, accWarningLabel, accDateBirthLabel, accGenderLabel,
                accSpeciesLabel, accBreedLabel, accColourLabel, accDateNeuteredLabel,
                accDateDeceasedLabel, accDefNomCodeNameLabel };

        Component[] animalDetails1PnlFields = {accNameField, accWarningField, accDateBirthField, accGenderField,
                accSpeciesField, accBreedField, accColourField, accDateNeuteredField,
                accDateDeceasedField, accDefNomCodeNameField };

        for (Component field : animalDetails1PnlFields) { field.setPreferredSize(getFieldDim()); }
        accDefNomCodeNameField.setPreferredSize(new Dimension(250, 20));

        for (int i = 0; i < animalDetails1PnlLabels.length; i++) {
            aniDet1PnlGC.weightx = 0.01;
            aniDet1PnlGC.weighty = 0.01;

            aniDet1PnlGC.gridx = 0;
            aniDet1PnlGC.gridy = i;
            aniDet1PnlGC.fill = GridBagConstraints.NONE;
            aniDet1PnlGC.anchor = GridBagConstraints.LINE_START;
            animalDetails1Pnl.add(animalDetails1PnlLabels[i], aniDet1PnlGC);

            aniDet1PnlGC.gridx = 1;
            aniDet1PnlGC.gridy = i;
            aniDet1PnlGC.fill = GridBagConstraints.NONE;
            aniDet1PnlGC.anchor = GridBagConstraints.LINE_START;
            animalDetails1Pnl.add(animalDetails1PnlFields[i], aniDet1PnlGC);
        }

        JButton accDateBirthButton = new JButton();
        JButton accDateNeuteredButton = new JButton();
        JButton accDateDeceasedButton = new JButton();

        JButton[] animalDetailsPnlButtons = { accDateBirthButton, accDateNeuteredButton, accDateDeceasedButton };

        for (JButton button : animalDetailsPnlButtons) {
            button.setPreferredSize(new Dimension(20,20));
            Image img = null;
			try {
				img = ImageIO.read(new File("C:\\Java\\JuroVet\\images\\calendar.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            button.setIcon(new ImageIcon(img));
        }

        // ON CLICK, BRINGS UP CALENDAR, SELECT DATE, DISPOSE CALENDAR
        accDateBirthButton.addActionListener(e -> new CalendarDialog(mainFrame, accDateBirthField));
        accDateNeuteredButton.addActionListener(e -> new CalendarDialog(mainFrame, accDateNeuteredField));
        accDateDeceasedButton.addActionListener(e -> new CalendarDialog(mainFrame, accDateDeceasedField));

        aniDet1PnlGC.weightx = 0.01;
        aniDet1PnlGC.weighty = 0.01;

        aniDet1PnlGC.gridx = 1;
        aniDet1PnlGC.gridy = 2;
        aniDet1PnlGC.fill = GridBagConstraints.NONE;
        aniDet1PnlGC.anchor = GridBagConstraints.CENTER;
        animalDetails1Pnl.add(accDateBirthButton, aniDet1PnlGC);

        aniDet1PnlGC.gridx = 1;
        aniDet1PnlGC.gridy = 7;
        aniDet1PnlGC.fill = GridBagConstraints.NONE;
        aniDet1PnlGC.anchor = GridBagConstraints.CENTER;
        animalDetails1Pnl.add(accDateNeuteredButton, aniDet1PnlGC);

        aniDet1PnlGC.gridx = 1;
        aniDet1PnlGC.gridy = 8;
        aniDet1PnlGC.fill = GridBagConstraints.NONE;
        aniDet1PnlGC.anchor = GridBagConstraints.CENTER;
        animalDetails1Pnl.add(accDateDeceasedButton, aniDet1PnlGC);

        tabbedPane.add("Animal Details 1", animalDetails1Pnl);


        /////////////////////////////////////////////////
        // ANIMAL DETAILS 2 PANEL
        /////////////////////////////////////////////////

        JLabel accPedigreeNameLabel = new JLabel("Pedigree name");
        JLabel accSireLabel = new JLabel("Sire");
        JLabel accDamLabel = new JLabel("Dam");
        JLabel accIdentificationNumberLabel = new JLabel("ID Number");
        JLabel accHeightLabel = new JLabel("Height (cm) ");
        JLabel accWeightLabel = new JLabel("Weight (kg) ");
        JLabel accUsualVetLabel = new JLabel("Usual Vet");

        accPedigreeNameField.setText(accountDetails[15]);
        accSireField.setText(accountDetails[16]);
        accDamField.setText(accountDetails[17]);
        accIdentificationNumberField.setText(accountDetails[14]);
        accHeightField.setText(accountDetails[26]);
        accWeightField.setText(accountDetails[27]);

        animalDetails2Pnl.setPreferredSize(getPanelDim());
        animalDetails2Pnl.setLayout(new GridBagLayout());
        GridBagConstraints aniDet2PnlGC = new GridBagConstraints();

        // POPULATE USUAL VET JCOMBOBOX
        ArrayList<String> users = spsUserNames.getAllVets(mainFrame);
        for (String user : users) { accUsualVetField.addItem(user); }
        accUsualVetField.addItem("AADA");
        accUsualVetField.setSelectedItem(accountDetails[28]);

        JLabel[] animalDetails2PnlLabels = {accPedigreeNameLabel, accSireLabel, accDamLabel,
                accIdentificationNumberLabel, accHeightLabel, accWeightLabel, accUsualVetLabel};

        Component[] animalDetails2PnlFields = {accPedigreeNameField, accSireField, accDamField,
                accIdentificationNumberField, accHeightField, accWeightField, accUsualVetField};

        for (Component field : animalDetails2PnlFields) { field.setPreferredSize(getFieldDim()); }
        accHeightField.setPreferredSize(new Dimension(50, 20));
        accWeightField.setPreferredSize(new Dimension(50, 20));

        for (int i = 0; i < animalDetails2PnlLabels.length; i++) {
            aniDet2PnlGC.weightx = 0.01;
            aniDet2PnlGC.weighty = 0.01;

            aniDet2PnlGC.gridx = 0;
            aniDet2PnlGC.gridy = i;
            aniDet2PnlGC.fill = GridBagConstraints.NONE;
            aniDet2PnlGC.anchor = GridBagConstraints.LINE_START;
            animalDetails2Pnl.add(animalDetails2PnlLabels[i], aniDet2PnlGC);

            aniDet2PnlGC.gridx = 1;
            aniDet2PnlGC.gridy = i;
            aniDet2PnlGC.fill = GridBagConstraints.NONE;
            aniDet2PnlGC.anchor = GridBagConstraints.LINE_START;
            animalDetails2Pnl.add(animalDetails2PnlFields[i], aniDet2PnlGC);
        }

        tabbedPane.add("Animal Details 2", animalDetails2Pnl);

        /////////////////////////////////////////////////
        // KEPT AT PANEL
        /////////////////////////////////////////////////

        JLabel accAddressLabel = new JLabel("Address");
        JButton findAddressButton = new JButton("Find address...");

        keptAtPnl.setPreferredSize(getPanelDim());
        keptAtPnl.setLayout(new GridBagLayout());
        GridBagConstraints keptAtPnlGC = new GridBagConstraints();

        accAddressField.setEditable(false);
        accAddressField.setPreferredSize(new Dimension(200,100));
        accAddressField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));

        String[] address = spsAddress.findAddressByGUID(mainFrame, accountDetails[29]);
        for (int i=0; i<address.length; i++) { if (address[i]==null) { address[i]=""; } };
        String fullAddress = address[0] + "\n" + address[1] + "\n" +
                            address[2] + "\n" + address[3] + "\n" +
                            address[4] + "\n" + address[5];
        accAddressField.setText(fullAddress);
        findAddressField.setPreferredSize(getFieldDim());
        findAddressButton.addActionListener(e -> {
            if (findAddressField.getText().length() > 2) {
                ArrayList<ObjAddress> addressList = null;
				try {
					addressList = spsAddress.findAddressByName(mainFrame, findAddressField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                String[] columns = {"AddressGUID", "AddressLine1", "AddressLine2", "AddressLine3", "AddressLine4", "Postcode", "Country"};
                String[][] data = new String[addressList.size()][columns.length];
                int i = 0;
                while ( i < addressList.size()) {
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
                                setAccAddressGUID(data[selectedRow][0]);
                                accAddressField.setText(data[selectedRow][1] + "\n" + data[selectedRow][2] + "\n" +
                                        data[selectedRow][3] + "\n" + data[selectedRow][4] + "\n" +
                                        data[selectedRow][5] + "\n" + data[selectedRow][6]);
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

        keptAtPnlGC.weightx = 0.01;
        keptAtPnlGC.weighty = 0.01;

        keptAtPnlGC.gridx = 0;
        keptAtPnlGC.gridy = 0;
        keptAtPnlGC.fill = GridBagConstraints.NONE;
        keptAtPnlGC.anchor = GridBagConstraints.LINE_START;
        keptAtPnl.add(accAddressLabel, keptAtPnlGC);

        keptAtPnlGC.gridx = 1;
        keptAtPnlGC.gridy = 0;
        keptAtPnlGC.fill = GridBagConstraints.NONE;
        keptAtPnlGC.anchor = GridBagConstraints.LINE_START;
        keptAtPnl.add(accAddressField, keptAtPnlGC);

        keptAtPnlGC.gridx = 0;
        keptAtPnlGC.gridy = 1;
        keptAtPnlGC.fill = GridBagConstraints.NONE;
        keptAtPnlGC.anchor = GridBagConstraints.LINE_START;
        keptAtPnl.add(findAddressButton, keptAtPnlGC);

        keptAtPnlGC.gridx = 1;
        keptAtPnlGC.gridy = 1;
        keptAtPnlGC.fill = GridBagConstraints.NONE;
        keptAtPnlGC.anchor = GridBagConstraints.LINE_START;
        keptAtPnl.add(findAddressField, keptAtPnlGC);

        tabbedPane.add("Kept At", keptAtPnl);


        /////////////////////////////////////////////////
        // DISCOUNTS PANEL
        /////////////////////////////////////////////////

        JLabel accDiscountRateLabel = new JLabel("Discount Rate");
        JLabel accDiscountTypeLabel = new JLabel("Additional Discount");
        JLabel accSettlementDueDaysLabel = new JLabel("Settlement Due Days");
        JLabel accSettlementDiscRateLabel = new JLabel("Settlement Discount");
        JLabel accPaymentDueDaysLabel = new JLabel("Payment Due Days");

        Map<Integer, String> discountTypes = spsDiscountTypes.getDiscountTypes(mainFrame);
        Iterator iterDiscTypes = discountTypes.entrySet().iterator();
        while(iterDiscTypes.hasNext()) {
            Map.Entry pair = (Map.Entry)iterDiscTypes.next();
            accDiscountTypeField.addItem("" + pair.getKey() + " - " + pair.getValue());
            iterDiscTypes.remove();
        }
        accDiscountTypeField.setSelectedIndex(Integer.parseInt(accountDetails[40]));
        accDiscountRateField.setText(accountDetails[41]);
        accSettlementDueDaysField.setText(accountDetails[43]);
        accSettlementDiscRateField.setText(accountDetails[42]);
        accPaymentDueDaysField.setText(accountDetails[44]);

        discountsPnl.setPreferredSize(getPanelDim());
        discountsPnl.setLayout(new GridBagLayout());
        GridBagConstraints discPnlGC = new GridBagConstraints();

        JLabel[] discountsPnlLabels = {accDiscountRateLabel, accDiscountTypeLabel, accSettlementDueDaysLabel,
                accSettlementDiscRateLabel, accPaymentDueDaysLabel};

        Component[] discountsPnlFields = {accDiscountRateField, accDiscountTypeField, accSettlementDueDaysField,
                accSettlementDiscRateField, accPaymentDueDaysField};

        for (Component field : discountsPnlFields) { field.setPreferredSize(getFieldDim()); }

        for (int i = 0; i < discountsPnlLabels.length; i++) {
            discPnlGC.weightx = 0.01;
            discPnlGC.weighty = 0.01;

            discPnlGC.gridx = 0;
            discPnlGC.gridy = i;
            discPnlGC.fill = GridBagConstraints.NONE;
            discPnlGC.anchor = GridBagConstraints.LINE_START;
            discountsPnl.add(discountsPnlLabels[i], discPnlGC);

            discPnlGC.gridx = 1;
            discPnlGC.gridy = i;
            discPnlGC.fill = GridBagConstraints.NONE;
            discPnlGC.anchor = GridBagConstraints.LINE_START;
            discountsPnl.add(discountsPnlFields[i], discPnlGC);
        }

        tabbedPane.add("Discounts", discountsPnl);


        /////////////////////////////////////////////////
        // DEFAULTS PANEL
        /////////////////////////////////////////////////

        JLabel accDefTaxCodeLabel = new JLabel("Tax Code");
        JLabel accCurrencyTypeLabel = new JLabel("Currency Type");
        JLabel accDeptNameLabel = new JLabel("Dept Name");

        Map<String, Double> taxCodes = spsTaxCodesAll.getAllTaxCodes(mainFrame);
        Iterator iterTaxCode = taxCodes.entrySet().iterator();
        while(iterTaxCode.hasNext()) {
            Map.Entry pair = (Map.Entry)iterTaxCode.next();
            accDefTaxCodeField.addItem("" + pair.getKey() + " - " + pair.getValue());
            iterTaxCode.remove();
        }

        ArrayList<String> currencyTypes = spsCurrencyTypesAll.getAllCurrencyTypes(mainFrame);
        for (String currency : currencyTypes) { accCurrencyTypeField.addItem(currency); }

        ArrayList<String> deptNames = spsDepartmentsAll.getAllDepartments(mainFrame);
        for (String dept : deptNames) { accDeptNameField.addItem(dept); }

        accDefTaxCodeField.setSelectedItem(accountDetails[35]);
        accCurrencyTypeField.setSelectedItem(accountDetails[39]);
        accDeptNameField.setSelectedIndex(Integer.parseInt(accountDetails[33]));


        defaultsPnl.setPreferredSize(getPanelDim());
        defaultsPnl.setLayout(new GridBagLayout());
        GridBagConstraints defaultPnlGC = new GridBagConstraints();

        JLabel[] defaultsPnlLabels = {accDefTaxCodeLabel, accCurrencyTypeLabel, accDeptNameLabel};

        Component[] defaultsPnlFields = {accDefTaxCodeField, accCurrencyTypeField, accDeptNameField};

        for (Component field : defaultsPnlFields) { field.setPreferredSize(getFieldDim()); }

        for (int i = 0; i < defaultsPnlLabels.length; i++) {
            defaultPnlGC.weightx = 0.01;
            defaultPnlGC.weighty = 0.01;

            defaultPnlGC.gridx = 0;
            defaultPnlGC.gridy = i;
            defaultPnlGC.fill = GridBagConstraints.NONE;
            defaultPnlGC.anchor = GridBagConstraints.LINE_START;
            defaultsPnl.add(defaultsPnlLabels[i], defaultPnlGC);

            defaultPnlGC.gridx = 1;
            defaultPnlGC.gridy = i;
            defaultPnlGC.fill = GridBagConstraints.NONE;
            defaultPnlGC.anchor = GridBagConstraints.LINE_START;
            defaultsPnl.add(defaultsPnlFields[i], defaultPnlGC);
        }

        tabbedPane.add("Defaults", defaultsPnl);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        add(tabbedPane, BorderLayout.CENTER);

    }

    String setJComboBox(JComboBox field) {
        if (field.getSelectedItem() != null) {
            return field.getSelectedItem().toString();
        }
        return "unknown";
    }

    String setSpeciesID(JComboBox speciesField) {
        if (speciesField.getSelectedItem() != null &&
                (!speciesField.getSelectedItem().toString().equals("unknown"))) {
            return spsAnimalCharacteristics.getSpeciesID(mainFrame, speciesField.getSelectedItem().toString());
        }
        return "0";
    }

    String setBreedID(JComboBox breedField, JComboBox speciesField) {
        if (breedField.getSelectedItem() != null &&
                speciesField.getSelectedItem() != null &&
                (!speciesField.getSelectedItem().toString().equals("unknown"))) {
            return spsAnimalCharacteristics.getBreedID(mainFrame, breedField.getSelectedItem().toString(), speciesField.getSelectedItem().toString());
        }
        return "0";
    }

    String setColourID(JComboBox colourField, JComboBox speciesField) {
        if (colourField.getSelectedItem() != null &&
                speciesField.getSelectedItem() != null &&
                (!speciesField.getSelectedItem().toString().equals("unknown"))) {
            return spsAnimalCharacteristics.getColourID(mainFrame, colourField.getSelectedItem().toString(), speciesField.getSelectedItem().toString());
        }
        return "0";
    }

    String dateIsSet(JTextField field) throws ParseException {
        if (DateConverter.ddMMyyyyValidDate(field.getText())) {
            return "1";
        }
        return "0";
    }

    private void resetSpeciesBreedColourFields() {
        accBreedField.removeAllItems();
        accColourField.removeAllItems(); // REMOVE ALL ITEMS FROM JCOMBOBOXES
        accBreedField.addItem("unknown");
        accBreedField.setSelectedItem("unknown");
        accColourField.addItem("unknown");
        accColourField.setSelectedItem("unknown");
    }

    // ACCESSORS
    public EditFrame getFrame() { return this.frame; }

    JTextField getAccNameField() {
        return accNameField;
    }

    JTextField getAccWarningField() {
        return accWarningField;
    }

    JTextField getAccDateBirthField() {
        return accDateBirthField;
    }

    JComboBox getAccSpeciesField() {
        return accSpeciesField;
    }

    JComboBox getAccGenderField() {
        return accGenderField;
    }

    JComboBox getAccBreedField() {
        return accBreedField;
    }

    JComboBox getAccColourField() {
        return accColourField;
    }

    JTextField getAccDateNeuteredField() {
        return accDateNeuteredField;
    }

    JTextField getAccDateDeceasedField() {
        return accDateDeceasedField;
    }

    JComboBox getAccDefNomCodeNameField() {
        return accDefNomCodeNameField;
    }

    JTextField getAccPedigreeNameField() {
        return accPedigreeNameField;
    }

    JTextField getAccSireField() {
        return accSireField;
    }

    JTextField getAccDamField() {
        return accDamField;
    }

    JTextField getAccIdentificationNumberField() {
        return accIdentificationNumberField;
    }

    JTextField getAccHeightField() {
        return accHeightField;
    }

    JTextField getAccWeightField() {
        return accWeightField;
    }

    JComboBox getAccUsualVetField() {
        return accUsualVetField;
    }

    JTextField getAccDiscountRateField() {
        return accDiscountRateField;
    }

    JComboBox getAccDiscountTypeField() {
        return accDiscountTypeField;
    }

    JTextField getAccSettlementDueDaysField() {
        return accSettlementDueDaysField;
    }

    JTextField getAccSettlementDiscRateField() {
        return accSettlementDiscRateField;
    }

    JTextField getAccPaymentDueDaysField() {
        return accPaymentDueDaysField;
    }

    JComboBox getAccDefTaxCodeField() {
        return accDefTaxCodeField;
    }

    JComboBox getAccCurrencyTypeField() {
        return accCurrencyTypeField;
    }

    JComboBox getAccDeptNameField() {
        return accDeptNameField;
    }


    String getAccAddressGUID() { return accAddressGUID; }
    private void setAccAddressGUID(String adrGUID) { this.accAddressGUID = adrGUID; }

}