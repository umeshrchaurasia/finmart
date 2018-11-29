package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BenefitsEntity implements Parcelable {
    /**
     * DisplayName :
     * ProdBeneID : 0
     * BeneID : 1
     * BeneDesc : Room Rent Limit
     * Benefit : No Limit
     */

    private String DisplayName;
    private int ProdBeneID;
    private int BeneID;
    private String BeneDesc;
    private String Benefit;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    public int getProdBeneID() {
        return ProdBeneID;
    }

    public void setProdBeneID(int ProdBeneID) {
        this.ProdBeneID = ProdBeneID;
    }

    public int getBeneID() {
        return BeneID;
    }

    public void setBeneID(int BeneID) {
        this.BeneID = BeneID;
    }

    public String getBeneDesc() {
        return BeneDesc;
    }

    public void setBeneDesc(String BeneDesc) {
        this.BeneDesc = BeneDesc;
    }

    public String getBenefit() {
        return Benefit;
    }

    public void setBenefit(String Benefit) {
        this.Benefit = Benefit;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DisplayName);
        dest.writeInt(this.ProdBeneID);
        dest.writeInt(this.BeneID);
        dest.writeString(this.BeneDesc);
        dest.writeString(this.Benefit);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public BenefitsEntity() {
    }

    protected BenefitsEntity(Parcel in) {
        this.DisplayName = in.readString();
        this.ProdBeneID = in.readInt();
        this.BeneID = in.readInt();
        this.BeneDesc = in.readString();
        this.Benefit = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<BenefitsEntity> CREATOR = new Parcelable.Creator<BenefitsEntity>() {
        @Override
        public BenefitsEntity createFromParcel(Parcel source) {
            return new BenefitsEntity(source);
        }

        @Override
        public BenefitsEntity[] newArray(int size) {
            return new BenefitsEntity[size];
        }
    };
}