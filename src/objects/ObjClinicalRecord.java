package objects;

public class ObjClinicalRecord {

    private String orderDetailGUID;
    private String dateStart;
    private String employee;
    private String orderDetailText;
    private String invoiceDate;
    private String printed;
    private String grossAmount;
    private String orderGrossAmount;
    private String orderGUID;

    public ObjClinicalRecord(String orderDetailGUID, String dateStart, String employee, String orderDetailText,
                             String invoiceDate, String printed, String grossAmount, String orderGrossAmount, String orderGUID) {

        this.orderDetailGUID = orderDetailGUID;
        this.dateStart = dateStart;
        this.employee = employee;
        this.orderDetailText = orderDetailText;
        this.invoiceDate = invoiceDate;
        this.printed = printed;
        this.grossAmount = grossAmount;
        this.orderGrossAmount = orderGrossAmount;
        this.orderGUID = orderGUID;
    }

    // ACCESSORS

    public String getOrderDetailGUID() {
        return orderDetailGUID;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getEmployee() {
        return employee;
    }

    public String getOrderDetailText() {
        return orderDetailText;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getPrinted() {
        return printed;
    }

    public String getGrossAmount() {
        return grossAmount;
    }

    public String getOrderGrossAmount() {
        return orderGrossAmount;
    }

    public String getOrderGUID() {
        return orderGUID;
    }
}
