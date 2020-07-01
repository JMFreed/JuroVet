package objects;

public class ObjCustomerGroup {

    // FIELDS
    private String customerGUID;
    private String groupGUID;
    private String groupName;

    // CONSTRUCTOR
    public ObjCustomerGroup(String customerGUID, String groupGUID, String groupName) {
        this.customerGUID = customerGUID;
        this.groupGUID = groupGUID;
        this.groupName = groupName;
    }

    // ACCESSORS
    public String getCustomerGUID() { return customerGUID; }
    public String getGroupGUID() { return groupGUID; }
    public String getGroupName() { return groupName; }
}
