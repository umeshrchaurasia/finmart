package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model;

import io.realm.RealmObject;

public class LstPackageDetailsEntity extends RealmObject {
    /**
     * PackCode : 71
     * PackName : Basic Profile
     * MRP : 913
     * OfferPrice : 620
     * Fasting : N
     * VisitType : CV
     * cnt : 40
     * Gender : B
     * Age : 0-100
     */

    private String PackCode;
    private String PackName;
    private int MRP;
    private int OfferPrice;
    private String Fasting;
    private String VisitType;
    private String cnt;
    private String Gender;

    public LstPackageDetailsEntity() {
        this.isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;

    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    private String Age;
    private boolean isVisible;

    public String getPackCode() {
        return PackCode;
    }

    public void setPackCode(String PackCode) {
        this.PackCode = PackCode;
    }

    public String getPackName() {
        return PackName;
    }

    public void setPackName(String PackName) {
        this.PackName = PackName;
    }

    public int getMRP() {
        return MRP;
    }

    public void setMRP(int MRP) {
        this.MRP = MRP;
    }

    public int getOfferPrice() {
        return OfferPrice;
    }

    public void setOfferPrice(int OfferPrice) {
        this.OfferPrice = OfferPrice;
    }

    public String getFasting() {
        return Fasting;
    }

    public void setFasting(String Fasting) {
        this.Fasting = Fasting;
    }

    public String getVisitType() {
        return VisitType;
    }

    public void setVisitType(String VisitType) {
        this.VisitType = VisitType;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }
}