package objects;

public class ObjEventHistorical {

    // FIELDS
    private String evtEventGUID;
    private String evtStartDate;
    private String evtStartTime;
    private String evtAccount;
    private String evtRegarding;
    private String evtEventType;
    private String evtStatus;
    private String evtForUserID;
    private String evtDocumentGUID;

    // CONSTRUCTOR
    public ObjEventHistorical(String evtEventGUID, String evtStartDate, String evtStartTime,
                    String evtAccount, String evtRegarding, String evtEventType,
                    String evtStatus, String evtForUserID, String evtDocumentGUID) {
        this.evtEventGUID = evtEventGUID;
        this.evtStartDate = evtStartDate;
        this.evtStartTime = evtStartTime;
        this.evtAccount = evtAccount;
        this.evtRegarding = evtRegarding;
        this.evtEventType = evtEventType;
        this.evtStatus = evtStatus;
        this.evtForUserID = evtForUserID;
        this.evtDocumentGUID = evtDocumentGUID;
    }

    // ACCESSORS
    public String getEvtEventGUID() { 
    	return evtEventGUID; 
	}
    public String getEvtStartDate() {
        return evtStartDate;
    }
    public String getEvtStartTime() {
        return evtStartTime;
    }
    public String getEvtAccount() {
        return evtAccount;
    }
    public String getEvtRegarding() {
        return evtRegarding;
    }
    public String getEvtEventType() {
        return evtEventType;
    }
    public String getEvtStatus() {
        return evtStatus;
    }
    public String getEvtForUserID() {
        return evtForUserID;
    }
    public String getEvtDocumentGUID() {
        return evtDocumentGUID;
    }
}
