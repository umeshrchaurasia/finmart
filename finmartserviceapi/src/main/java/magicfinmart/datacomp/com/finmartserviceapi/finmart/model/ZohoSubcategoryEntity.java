package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class ZohoSubcategoryEntity  extends RealmObject {
    /**
     * QuerType : Notification Problem
     * QuerID : 1
     * CateCode : 1
     */

    private String QuerType;
    private int QuerID;
    private String CateCode;

    public String getQuerType() {
        return QuerType;
    }

    public void setQuerType(String QuerType) {
        this.QuerType = QuerType;
    }

    public int getQuerID() {
        return QuerID;
    }

    public void setQuerID(int QuerID) {
        this.QuerID = QuerID;
    }

    public String getCateCode() {
        return CateCode;
    }

    public void setCateCode(String CateCode) {
        this.CateCode = CateCode;
    }
}