package sprocs;

import java.sql.*;

import editevents.EditEventCustomer;
import main.MainFrame;

public class spuCustomer {

public static boolean updateCustomerDetails(MainFrame mainFrame, EditEventCustomer event) {

        try {
        	mainFrame.getDBManager().executeSproc(
        			String.format(
                            "exec spuCustomer " +
                            "@CustomerGUID='%s', " +
                            "@LastName='%s', " +
                            "@Title='%s', " +
                            "@Initials='%s', " +
                            "@FirstName='%s', " +
                            "@AddressGUID='%s', " +
                            "@HomePhone='%s', " +
                            "@WorkPhone='%s', " +
                            "@MobilePhone='%s', " +
                            "@Fax='%s', " +
                            "@Email='%s', " +
                            "@Occupation='%s', " +
                            "@LastResults='%s', " +
                            "@CreditRating=%d, " +
                            "@Analysis1='', " +
                            "@Analysis2='', " +
                            "@Analysis3='', " +
                            "@DeptNumber=%d, " +
                            "@DeptName='%s', " +
                            "@StatusNumber=%d, " +
                            "@StatusText='%s', " +
                            "@DefTaxCode=%d, " +
                            "@DefNomCode='%s', " +
                            "@DefNomCodeGUID='%s', " +
                            "@VatRegNumber='%s', " +
                            "@CurrencyType=%d, " +
                            "@DiscountType=%d, " +
                            "@DiscountRate=%f, " +
                            "@SettlementDiscRate=%f, " +
                            "@SettlementDueDays=%d, " +
                            "@PaymentDueDays=%d, " +
                            "@CreditLimit=%f, " +
                            "@AccountOnHold=%d, " +
                            "@Terms='%s', " +
                            "@RestrictMail=%d, " +
                            "@TermsAgreed=%d, " +
                            "@CreditAppliedFor='%s', " +
                            "@CreditAppReceived=%d, " +
                            "@DateCreditAppReceived='%s', " +
                            "@NextCreditRev='%s', " +
                            "@LastCreditRev='%s', " +
                            "@CanCharge=%d, " +
                            "@BureauCode=%d, " +
                            "@ReturnString=''",
                            event.getCusCustomerGUID(),
                            event.getCusLastName(),
                            event.getCusTitle(),
                            event.getCusInitials(),
                            event.getCusFirstName(),
                            event.getCusAddressGUID(),
                            event.getCusHomePhone(),
                            event.getCusWorkPhone(),
                            event.getCusMobilePhone(),
                            event.getCusFax(),
                            event.getCusEmail(),
                            event.getCusOccupation(),
                            event.getCusLastResults(),
                            Integer.parseInt(event.getCusCreditRating()),
                            Integer.parseInt(event.getCusDeptNumber()),
                            event.getCusDeptName(),
                            Integer.parseInt(event.getCusStatusNumber()),
                            event.getCusStatusText(),
                            Integer.parseInt(event.getCusDefTaxCode()),
                            event.getCusDefNomCode(),
                            event.getCusDefNomCodeGUID(),
                            event.getCusVatRegNumber(),
                            Integer.parseInt(event.getCusCurrencyType().substring(0,2).trim()),
                            Integer.parseInt(event.getCusDiscountType()),
                            Double.parseDouble(event.getCusDiscountRate()),
                            Double.parseDouble(event.getCusSettlementDiscRate()),
                            Integer.parseInt(event.getCusSettlementDueDays()),
                            Integer.parseInt(event.getCusPaymentDueDays()),
                            Double.parseDouble(event.getCusCreditLimit()),
                            Integer.parseInt(event.getCusAccountOnHold()),
                            event.getCusTerms(),
                            Integer.parseInt(event.getCusRestrictMail()),
                            Integer.parseInt(event.getCusTermsAgreed()),
                            event.getCusCreditAppliedFor(),
                            Integer.parseInt(event.getCusCreditAppReceived()),
                            event.getCusDateCreditAppReceived(),
                            event.getCusNextCreditRev(),
                            event.getCusLastCreditRev(),
                            Integer.parseInt(event.getCusCanCharge()),
                            Integer.parseInt(event.getCusBureauCode())
                    ));
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateEvent(EditEventCustomer event) {
        if (event.getCusCustomerGUID().length() !=36) { return false; }
        if (event.getCusLastName().length() < 2 ) { return false; }
        if (event.getCusTitle() == null) { return false; }
        if (event.getCusAddressGUID().length() != 36) { return false; }
        return true;
    }
}