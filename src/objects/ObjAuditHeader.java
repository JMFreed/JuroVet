package objects;

public class ObjAuditHeader {

    private String headerGUID;
    private String tranType;
    private String tranDate;
    private String invoiceRef;
    private String invoiceRefGUID;
    private String details;
    private String grossAmount;
    private String debit;
    private String credit;
    private String outstanding;

    public ObjAuditHeader(String headerGUID, String tranType, String tranDate,
                          String invoiceRef, String invoiceRefGUID, String details,
                          String grossAmount, String outstanding) {
        this.headerGUID = headerGUID;
        this.tranType = tranType;
        this.tranDate = tranDate;
        this.invoiceRef = invoiceRef;
        this.invoiceRefGUID = invoiceRefGUID;
        this.details = details;
        this.grossAmount = grossAmount;
        this.outstanding = outstanding;
    }

    public ObjAuditHeader(String headerGUID, String tranType, String tranDate,
                          String invoiceRef, String invoiceRefGUID, String details,
                          String grossAmount, String debit, String credit, String outstanding) {
        this.headerGUID = headerGUID;
        this.tranType = tranType;
        this.tranDate = tranDate;
        this.invoiceRef = invoiceRef;
        this.invoiceRefGUID = invoiceRefGUID;
        this.details = details;
        this.grossAmount = grossAmount;
        this.debit = debit;
        this.credit = credit;
        this.outstanding = outstanding;
    }

    // ACCESSORS
    public String getHeaderGUID() {
        return headerGUID;
    }
    public String getTranType() {
        return tranType;
    }
    public String getTranDate() {
        return tranDate;
    }
    public String getInvoiceRef() {
        return invoiceRef;
    }
    public String getInvoiceRefGUID() {
        return invoiceRefGUID;
    }
    public String getDetails() {
        return details;
    }
    public String getGrossAmount() {
        return grossAmount;
    }
    public String getDebit() { 
    	return debit; 
	}
    public String getCredit() { 
    	return credit; 
	}
    public String getOutstanding() {
        return outstanding;
    }
}
