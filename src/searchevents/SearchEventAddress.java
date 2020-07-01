package searchevents;

import java.util.EventObject;

import searchforms.SearchFormAddress;
import searchforms.SearchFormCustomer;

public class SearchEventAddress extends EventObject {

    // FIELDS
    private String address;
    private String postcode;

    // CONSTRUCTOR
    public SearchEventAddress (Object source, String address, String postcode) {
        super(source);
        this.address = address;
        this.postcode = postcode;
    }

    // ACCESSORS
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    
    
    public void updateInformation(SearchFormAddress form) {
    	this.address = form.getAddressField().getText();
    	this.postcode = form.getPostcodeField().getText();
    }
}
