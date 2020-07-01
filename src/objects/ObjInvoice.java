package objects;

public class ObjInvoice {

    // FIELDS
    private String invGUID;
    private String invRef;
    private String invOrCredit;
    private String invDate;
    private String invNet;
    private String invVAT;
    private String invGross;
    private String invPrinted;
    private String invPrintedCode;


    // CONSTRUCTOR

    public ObjInvoice(String invGUID, String invRef, String invOrCredit,
                      String invDate, String invNet, String invVAT,
                      String invGross, String invPrinted, String invPrintedCode) {
        this.invGUID = invGUID;
        this.invRef = invRef;
        this.invOrCredit = invOrCredit;
        this.invDate = invDate;
        this.invNet = invNet;
        this.invVAT = invVAT;
        this.invGross = invGross;
        this.invPrinted = invPrinted;
        this.invPrintedCode = invPrintedCode;
    }

    public String getInvGUID() {
        return invGUID;
    }

    public String getInvRef() {
        return invRef;
    }

    public String getInvOrCredit() {
        return invOrCredit;
    }

    public String getInvDate() {
        return invDate;
    }

    public String getInvNet() {
        return invNet;
    }

    public String getInvVAT() {
        return invVAT;
    }

    public String getInvGross() {
        return invGross;
    }

    public String getInvPrinted() {
        return invPrinted;
    }

    public String getInvPrintedCode() {
        return invPrintedCode;
    }
}
