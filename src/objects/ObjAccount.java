package objects;

public class ObjAccount {

    // FIELDS
    private String accAccountGUID;
    private String accCustomerGUID;
    private String accAnimalName;
    private String accSpecies;
    private String accBreed;
    private String accAnimalSex;
    private String accDateBirth;
    private String accBalance;

    // CONSTRUCTOR
    public ObjAccount(String accAccountGUID, String accCustomerGUID, String accAnimalName,
                      String accSpecies, String accBreed, String accAnimalSex,
                      String accDateBirth, String accBalance) {
        this.accAccountGUID = accAccountGUID;
        this.accCustomerGUID = accCustomerGUID;
        this.accAnimalName = accAnimalName;
        this.accSpecies = accSpecies;
        this.accBreed = accBreed;
        this.accAnimalSex = accAnimalSex;
        this.accDateBirth = accDateBirth;
        this.accBalance = accBalance;
    }

    // ACCESSORS
    public String getAccAccountGUID() { return accAccountGUID; }
    public String getAccCustomerGUID() { return accCustomerGUID; }
    public String getAccAnimalName() { return accAnimalName; }
    public String getAccSpecies() { return accSpecies; }
    public String getAccBreed() { return accBreed; }
    public String getAccAnimalSex() { return accAnimalSex; }
    public String getAccDateBirth() { return accDateBirth; }
    public String getAccBalance() { return accBalance; }

}