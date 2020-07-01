package objects;

public class ObjCustomer {

    // FIELDS
    private String customerGUID;
    private String customerSageID;
    private String customer;
    private String address;
    private String postcode;
    private String homePhone;
    private String balance;
    private String animal;
    private String accountGUID;

    // CONSTRUCTORS
    public ObjCustomer(String customerGUID, String customerSageID, String customer, String address, String postcode,
                    String homePhone, String balance, String animal, String accountGUID) {
        this.customerGUID = customerGUID;
        this.customerSageID = customerSageID;
        this.customer = customer;
        this.address = address;
        this.postcode = postcode;
        this.homePhone = homePhone;
        this.balance = balance;
        this.animal = animal;
        this.accountGUID = accountGUID;
    }
    
    public ObjCustomer(String customerGUID, String customer, String address, String postcode,
            String homePhone, String balance, String animal, String accountGUID) {
		this.customerGUID = customerGUID;
		this.customer = customer;
		this.address = address;
		this.postcode = postcode;
		this.homePhone = homePhone;
		this.balance = balance;
		this.animal = animal;
		this.accountGUID = accountGUID;
    }

    // ACCESSORS
    public String getCustomerGUID() {return customerGUID; }
    public String getCustomerSageID() {return customerSageID; }
    public String getCustomer() { return customer; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getHomePhone() { return homePhone; }
    public String getBalance() { return balance; }
    public String getAnimal() { return animal; }
    public String getAccountGUID() { return accountGUID; }

}