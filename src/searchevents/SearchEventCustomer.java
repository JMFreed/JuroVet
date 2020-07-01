package searchevents;

import java.util.EventObject;

import searchforms.SearchFormCustomer;

public class SearchEventCustomer extends EventObject {

    // FIELDS
    private String cusSurname;
    private String cusAddress;
    private String cusTelephone;
    private String cusAccount;

    // CONSTRUCTOR
    public SearchEventCustomer(Object source, String cusSurname, String cusAddress, String cusTelephone, String cusAccount) {
        super(source);
        this.cusSurname = cusSurname;
        this.cusAddress = cusAddress;
        this.cusTelephone = cusTelephone;
        this.cusAccount = cusAccount;
    }

    // ACCESSORS
    public String getCusSurname() { return cusSurname; }
    public String getCusAddress() { return cusAddress; }
    public String getCusTelephone() { return cusTelephone; }
    public String getCusAccount() { return cusAccount; }
    
    public void updateInformation(SearchFormCustomer form) {
    	clearInformation();
    	this.cusSurname = form.getCusSurnameField().getText();
    	this.cusAddress = form.getCusAddressField().getText();
    	this.cusTelephone = form.getCusTelephoneField().getText();
    	this.cusAccount = form.getCusAccountField().getText();
    }
    
    public void clearInformation() {
    	this.cusSurname = "";
    	this.cusAddress = "";
    	this.cusTelephone = "";
    	this.cusAccount = "";
    }

}
