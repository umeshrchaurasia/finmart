package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class ZohoClassificationEntity  extends RealmObject {
    /**
     * ID : 1
     * QuerID : 21
     * Description : Proposal Link
     */

    private int ID;
    private int QuerID;
    private String Description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuerID() {
        return QuerID;
    }

    public void setQuerID(int QuerID) {
        this.QuerID = QuerID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}