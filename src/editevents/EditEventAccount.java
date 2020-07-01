package editevents;

import java.util.Date;

import main.MainFrame;

public class EditEventAccount {

    // FIELDS
    private String accAccountGUID;
    private String accCustomerGUID;
    private String accCustomerSageID;
    private String accName;
    private String accWarning;
    private String accSpeciesID;
    private String accSpecies;
    private String accBreedID;
    private String accBreed;
    private String accColourID;
    private String accColour;
    private String accAnimalSex;
    private String accDateBirth;
    private String accHerdNumber;
    private String accIdentificationNumber;
    private String accPedigreeName;
    private String accSire;
    private String accDam;
    private String accDateAcquired;
    private String accNeutered;
    private String accDateNeutered;
    private String accSold;
    private String accDateSold;
    private String accDeceased;
    private String accDateDeceased;
    private String accAccountType;
    private String accHeight;
    private String accWeight;
    private String accUsualVet;
    private String accAddressGUID;
    private String accAnalysis1;
    private String accAnalysis2;
    private String accAnalysis3;
    private String accDeptNumber;
    private String accDeptName;
    private String accDefTaxCode;
    private String accDefNomCode;
    private String accDefNomCodeGUID;
    private String accCurrencyType;
    private String accDiscountType;
    private String accDiscountRate;
    private String accSettlementDiscRate;
    private String accSettlementDueDays;
    private String accPaymentDueDays;
	
    private MainFrame mainFrame;


    public EditEventAccount(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
    
    public EditEventAccount(
    		String accAccountGUID, String accCustomerGUID, String accCustomerSageID,
    		String accName, String accWarning, String accSpeciesID, String accSpecies,
    		String accBreedID, String accBreed, String accColourID, String accColour,
    		String accAnimalSex, String accDateBirth, String accHerdNumber,
    		String accIdentificationNumber, String accPedigreeName, String accSire, String accDam,
    		String accDateAcquired, String accNeutered, String accDateNeutered, String accSold,
    		String accDateSold, String accDeceased, String accDateDeceased, String accAccountType,
    		String accHeight, String accWeight, String accUsualVet, String accAddressGUID,
    		String accAnalysis1, String accAnalysis2, String accAnalysis3, String accDeptNumber,
    		String accDeptName, String accDefTaxCode, String accDefNomCode, String accDefNomCodeGUID,
    		String accCurrencyType, String accDiscountType, String accDiscountRate, 
    		String accSettlementDiscRate, String accSettlementDueDays, String accPaymentDueDays ) {

        this.accAccountGUID = accAccountGUID;
        this.accCustomerGUID = accCustomerGUID;
        this.accCustomerSageID = accCustomerSageID;
        this.accName = accName;
        this.accWarning = accWarning;
        this.accSpeciesID = accSpeciesID;
        this.accSpecies = accSpecies;
        this.accBreedID = accBreedID;
        this.accBreed = accBreed;
        this.accColourID = accColourID;
        this.accColour = accColour;
        this.accAnimalSex = accAnimalSex;
        this.accDateBirth = accDateBirth;
        this.accHerdNumber = accHerdNumber;
        this.accIdentificationNumber = accIdentificationNumber;
        this.accPedigreeName = accPedigreeName;
        this.accSire = accSire;
        this.accDam = accDam;
        this.accDateAcquired = accDateAcquired;
        this.accNeutered = accNeutered;
        this.accDateNeutered = accDateNeutered;
        this.accSold = accSold;
        this.accDateSold = accDateSold;
        this.accDeceased = accDeceased;
        this.accDateDeceased = accDateDeceased;
        this.accAccountType = accAccountType;
        this.accHeight = accHeight;
        this.accWeight = accWeight;
        this.accUsualVet = accUsualVet;
        this.accAddressGUID = accAddressGUID;
        this.accAnalysis1 = accAnalysis1;
        this.accAnalysis2 = accAnalysis2;
        this.accAnalysis3 = accAnalysis3;
        this.accDeptNumber = accDeptNumber;
        this.accDeptName = accDeptName;
        this.accDefTaxCode = accDefTaxCode;
        this.accDefNomCode = accDefNomCode;
        this.accDefNomCodeGUID = accDefNomCodeGUID;
        this.accCurrencyType = accCurrencyType;
        this.accDiscountType = accDiscountType;
        this.accDiscountRate = accDiscountRate;
        this.accSettlementDiscRate = accSettlementDiscRate;
        this.accSettlementDueDays = accSettlementDueDays;
        this.accPaymentDueDays = accPaymentDueDays;
    }

	// ACCESSORS
    String getAccAccountGUID() { return accAccountGUID; }
    String getAccCustomerGUID() { return accCustomerGUID; }
    String getAccCustomerSageID() { return accCustomerSageID; }
    String getAccName() { return accName; }
    String getAccWarning() { return accWarning; }
    String getAccSpeciesID() { return accSpeciesID; }
    String getAccSpecies() { return accSpecies; }
    String getAccBreedID() { return accBreedID; }
    String getAccBreed() { return accBreed; }
    String getAccColourID() { return accColourID; }
    String getAccColour() { return accColour; }
    String getAccAnimalSex() { return accAnimalSex; }
    String getAccDateBirth() { return accDateBirth; }
    String getAccHerdNumber() { return accHerdNumber; }
    String getAccIdentificationNumber() { return accIdentificationNumber; }
    String getAccPedigreeName() { return accPedigreeName; }
    String getAccSire() { return accSire; }
    String getAccDam() { return accDam; }
    String getAccDateAcquired() { return accDateAcquired; }
    String getAccNeutered() { return accNeutered; }
    String getAccDateNeutered() { return accDateNeutered; }
    String getAccSold() { return accSold; }
    String getAccDateSold() { return accDateSold; }
    String getAccDeceased() { return accDeceased; }
    String getAccDateDeceased() { return accDateDeceased; }
    String getAccAccountType() { return accAccountType; }
    String getAccHeight() { return accHeight; }
    String getAccWeight() { return accWeight; }
    String getAccUsualVet() { return accUsualVet; }
    String getAccAddressGUID() { return accAddressGUID; }
    String getAccAnalysis1() { return accAnalysis1; }
    String getAccAnalysis2() { return accAnalysis2; }
    String getAccAnalysis3() { return accAnalysis3; }
    String getAccDeptNumber() { return accDeptNumber; }
    String getAccDeptName() { return accDeptName; }
    String getAccDefTaxCode() { return accDefTaxCode; }
    String getAccDefNomCode() { return accDefNomCode; }
    String getAccDefNomCodeGUID() { return accDefNomCodeGUID; }
    String getAccCurrencyType() { return accCurrencyType; }
    String getAccDiscountType() { return accDiscountType; }
    String getAccDiscountRate() { return accDiscountRate; }
    String getAccSettlementDiscRate() { return accSettlementDiscRate; }
    String getAccSettlementDueDays() { return accSettlementDueDays; }
    String getAccPaymentDueDays() { return accPaymentDueDays; }
}
