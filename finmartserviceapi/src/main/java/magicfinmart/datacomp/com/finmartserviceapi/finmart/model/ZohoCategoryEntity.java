package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class ZohoCategoryEntity extends RealmObject {
    /**
     * CateCode : 1
     * CateName : FBA App
     * TranTypeID : 1
     */

    private String CateCode;
    private String CateName;
    private int TranTypeID;

    public String getCateCode() {
        return CateCode;
    }

    public void setCateCode(String CateCode) {
        this.CateCode = CateCode;
    }

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String CateName) {
        this.CateName = CateName;
    }

    public int getTranTypeID() {
        return TranTypeID;
    }

    public void setTranTypeID(int TranTypeID) {
        this.TranTypeID = TranTypeID;
    }
}