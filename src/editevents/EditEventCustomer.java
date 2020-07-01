package editevents;

import main.MainFrame;

public class EditEventCustomer {

    private String cusCustomerGUID;
    private String cusLastName;
    private String cusTitle;
    private String cusInitials;
    private String cusFirstName;
    private String cusAddressGUID;
    private String cusHomePhone;
    private String cusWorkPhone;
    private String cusMobilePhone;
    private String cusFax;
    private String cusEmail;
    private String cusOccupation;
    private String cusLastResults;
    private String cusCreditRating;
    private String cusAnalysis1;
    private String cusAnalysis2;
    private String cusAnalysis3;
    private String cusDeptNumber;
    private String cusDeptName;
    private String cusStatusNumber;
    private String cusStatusText;
    private String cusDefTaxCode;
    private String cusDefNomCode;
    private String cusDefNomCodeGUID;
    private String cusVatRegNumber;
    private String cusCurrencyType;
    private String cusDiscountType;
    private String cusDiscountRate;
    private String cusSettlementDiscRate;
    private String cusSettlementDueDays;
    private String cusPaymentDueDays;
    private String cusCreditLimit;
    private String cusAccountOnHold;
    private String cusTerms;
    private String cusRestrictMail;
    private String cusTermsAgreed;
    private String cusCreditAppliedFor;
    private String cusCreditAppReceived;
    private String cusDateCreditAppReceived;
    private String cusNextCreditRev;
    private String cusLastCreditRev;
    private String cusCanCharge;
    private String cusBureauCode;
	
    private MainFrame mainFrame;
    
    public EditEventCustomer(MainFrame mainFrame) {
    	this.mainFrame = mainFrame;
    }

    // CONSTRUCTOR
    public EditEventCustomer(String cusCustomerGUID, String cusLastName, String cusTitle,
                      String cusInitials, String cusFirstName, String cusAddressGUID,
                      String cusHomePhone, String cusWorkPhone, String cusMobilePhone, String cusFax,
                      String cusEmail, String cusOccupation, String cusLastResults,
                      String cusCreditRating, String cusAnalysis1, String cusAnalysis2, String cusAnalysis3,
                      String cusDeptNumber, String cusDeptName,
                      String cusStatusNumber, String cusStatusText, String cusDefTaxCode,
                      String cusDefNomCode, String cusDefNomCodeGUID, String cusVatRegNumber,
                      String cusCurrencyType, String cusDiscountType, String cusDiscountRate,
                      String cusSettlementDiscRate, String cusSettlementDueDays, String cusPaymentDueDays,
                      String cusCreditLimit, String cusAccountOnHold, String cusTerms,
                      String cusRestrictMail, String cusTermsAgreed, String cusCreditAppliedFor,
                      String cusCreditAppReceived, String cusDateCreditAppReceived, String cusNextCreditRev,
                      String cusLastCreditRev, String cusCanCharge, String cusBureauCode) {

        this.cusCustomerGUID = cusCustomerGUID;
        this.cusLastName = cusLastName;
        this.cusTitle = cusTitle;
        this.cusInitials = cusInitials;
        this.cusFirstName = cusFirstName;
        this.cusAddressGUID = cusAddressGUID;
        this.cusHomePhone = cusHomePhone;
        this.cusWorkPhone = cusWorkPhone;
        this.cusMobilePhone = cusMobilePhone;
        this.cusFax = cusFax;
        this.cusEmail = cusEmail;
        this.cusOccupation = cusOccupation;
        this.cusLastResults = cusLastResults;
        this.cusCreditRating = cusCreditRating;
        this.cusAnalysis1 = cusAnalysis1;
        this.cusAnalysis2 = cusAnalysis2;
        this.cusAnalysis3 = cusAnalysis3;
        this.cusDeptNumber = cusDeptNumber;
        this.cusDeptName = cusDeptName;
        this.cusStatusNumber = cusStatusNumber;
        this.cusStatusText = cusStatusText;
        this.cusDefTaxCode = cusDefTaxCode;
        this.cusDefNomCode = cusDefNomCode;
        this.cusDefNomCodeGUID = cusDefNomCodeGUID;
        this.cusVatRegNumber = cusVatRegNumber;
        this.cusCurrencyType = cusCurrencyType;
        this.cusDiscountType = cusDiscountType;
        this.cusDiscountRate = cusDiscountRate;
        this.cusSettlementDiscRate = cusSettlementDiscRate;
        this.cusSettlementDueDays = cusSettlementDueDays;
        this.cusPaymentDueDays = cusPaymentDueDays;
        this.cusCreditLimit = cusCreditLimit;
        this.cusAccountOnHold = cusAccountOnHold;
        this.cusTerms = cusTerms;
        this.cusRestrictMail = cusRestrictMail;
        this.cusTermsAgreed = cusTermsAgreed;
        this.cusCreditAppliedFor = cusCreditAppliedFor;
        this.cusCreditAppReceived = cusCreditAppReceived;
        this.cusDateCreditAppReceived = cusDateCreditAppReceived;
        this.cusNextCreditRev = cusNextCreditRev;
        this.cusLastCreditRev = cusLastCreditRev;
        this.cusCanCharge = cusCanCharge;
        this.cusBureauCode = cusBureauCode;
    }


    // ACCESSORS
    public String getCusCustomerGUID() { return cusCustomerGUID; }
    public String getCusLastName() { return cusLastName; }
    public String getCusTitle() { return cusTitle; }
    public String getCusInitials() { return cusInitials; }
    public String getCusFirstName() { return cusFirstName; }
    public String getCusAddressGUID() { return cusAddressGUID; }
    public String getCusHomePhone() { return cusHomePhone; }
    public String getCusWorkPhone() { return cusWorkPhone; }
    public String getCusMobilePhone() { return cusMobilePhone; }
    public String getCusFax() { return cusFax; }
    public String getCusEmail() { return cusEmail; }
    public String getCusOccupation() { return cusOccupation; }
    public String getCusLastResults() { return cusLastResults; }
    public String getCusCreditRating() { return cusCreditRating; }
    public String getCusAnalysis1() { return cusAnalysis1; }
    public String getCusAnalysis2() { return cusAnalysis2; }
    public String getCusAnalysis3() { return cusAnalysis3; }
    public String getCusDeptNumber() { return cusDeptNumber; }
    public String getCusDeptName() { return cusDeptName; }
    public String getCusStatusNumber() { return cusStatusNumber; }
    public String getCusStatusText() { return cusStatusText; }
    public String getCusDefTaxCode() { return cusDefTaxCode; }
    public String getCusDefNomCode() { return cusDefNomCode; }
    public String getCusDefNomCodeGUID() { return cusDefNomCodeGUID; }
    public String getCusVatRegNumber() { return cusVatRegNumber; }
    public String getCusCurrencyType() { return cusCurrencyType; }
    public String getCusDiscountType() { return cusDiscountType; }
    public String getCusDiscountRate() { return cusDiscountRate; }
    public String getCusSettlementDiscRate() { return cusSettlementDiscRate; }
    public String getCusSettlementDueDays() { return cusSettlementDueDays; }
    public String getCusPaymentDueDays() { return cusPaymentDueDays; }
    public String getCusCreditLimit() { return cusCreditLimit; }
    public String getCusAccountOnHold() { return cusAccountOnHold; }
    public String getCusTerms() { return cusTerms; }
    public String getCusRestrictMail() { return cusRestrictMail; }
    public String getCusTermsAgreed() { return cusTermsAgreed; }
    public String getCusCreditAppliedFor() { return cusCreditAppliedFor; }
    public String getCusCreditAppReceived() { return cusCreditAppReceived; }
    public String getCusDateCreditAppReceived() { return cusDateCreditAppReceived; }
    public String getCusNextCreditRev() { return cusNextCreditRev; }
    public String getCusLastCreditRev() { return cusLastCreditRev; }
    public String getCusCanCharge() { return cusCanCharge; }
    public String getCusBureauCode() { return cusBureauCode; }
}
