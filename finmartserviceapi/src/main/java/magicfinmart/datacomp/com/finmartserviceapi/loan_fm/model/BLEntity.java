package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BLEntity implements Parcelable {

    /**
     * Bank_Id : 33
     * Bank_Code : KOTAK MAHINDRA
     * Bank_Name : KOTAK MAHINDRA BANK
     * roi : 11.49
     * pf : 2.00
     * pf_type : Percentage
     * Bank_Logo : http://erp.rupeeboss.com/Banklogo/kotak.png
     * processingfee : 10000
     * roi_type : Fixed
     */

    private int Bank_Id;
    private String Bank_Code;
    private String Bank_Name;
    private String roi;
    private String pf;
    private String pf_type;
    private String Bank_Logo;
    private double processingfee;
    private String roi_type;

    private double drop_emi;

    public int getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(int bank_Id) {
        Bank_Id = bank_Id;
    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String bank_Code) {
        Bank_Code = bank_Code;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String bank_Name) {
        Bank_Name = bank_Name;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPf_type() {
        return pf_type;
    }

    public void setPf_type(String pf_type) {
        this.pf_type = pf_type;
    }

    public String getBank_Logo() {
        return Bank_Logo;
    }

    public void setBank_Logo(String bank_Logo) {
        Bank_Logo = bank_Logo;
    }

    public double getProcessingfee() {
        return processingfee;
    }

    public void setProcessingfee(double processingfee) {
        this.processingfee = processingfee;
    }

    public String getRoi_type() {
        return roi_type;
    }

    public void setRoi_type(String roi_type) {
        this.roi_type = roi_type;
    }

    public double getdrop_emi() {
        return drop_emi;
    }

    public void setdrop_emi(double drop_emi) {
        this.processingfee = drop_emi;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Bank_Id);
        dest.writeString(this.Bank_Code);
        dest.writeString(this.Bank_Name);
        dest.writeString(this.roi);
        dest.writeString(this.pf);
        dest.writeString(this.pf_type);
        dest.writeString(this.Bank_Logo);
        dest.writeDouble(this.processingfee);
        dest.writeString(this.roi_type);
        dest.writeDouble(this.drop_emi);
    }

    public BLEntity() {
    }

    protected BLEntity(Parcel in) {
        this.Bank_Id = in.readInt();
        this.Bank_Code = in.readString();
        this.Bank_Name = in.readString();
        this.roi = in.readString();
        this.pf = in.readString();
        this.pf_type = in.readString();
        this.Bank_Logo = in.readString();
        this.processingfee = in.readDouble();
        this.roi_type = in.readString();
        this.drop_emi = in.readDouble();
    }

    public static final Parcelable.Creator<BLEntity> CREATOR = new Parcelable.Creator<BLEntity>() {
        @Override
        public BLEntity createFromParcel(Parcel source) {
            return new BLEntity(source);
        }

        @Override
        public BLEntity[] newArray(int size) {
            return new BLEntity[size];
        }
    };
}