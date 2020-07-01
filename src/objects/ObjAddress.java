package objects;

public class ObjAddress {

    // FIELDS
    private String addressGUID;
    private String addressFull;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressLine4;
    private String addressLine5;
    private String postcode;
    private String country;

    public ObjAddress(String addressGUID, String address, String postcode) {
        this.addressGUID = addressGUID;
        this.addressFull = address;
        this.postcode = postcode;
    }

    // Accessors

    public String getAddressGUID() { return addressGUID; }

    public String getAddressFull() { return addressFull; }

    public String getAddressLine1() { return addressLine1; }
    
    public String getAddressLine2() { return addressLine2; }
    
    public String getAddressLine3() { return addressLine3; }
    
    public String getAddressLine4() { return addressLine4; }
    
    public String getAddressLine5() { return addressLine5; }
    
    public String getPostcode() { return postcode; }
    
    public String getCountry() { return country; }
}
