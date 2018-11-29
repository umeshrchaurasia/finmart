package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PendingCasesEntity implements Parcelable {
    /**
     * Id : 296
     * CustomerName : danial
     * Category : Car
     * qatype : Q
     * ApplnStatus : 0
     * mobile : 9673484578
     * quotetype : MOI
     * created_date : 2018-02-19T12:21:35.000Z
     * pendingdays : 0
     */

    private int Id;
    private String CustomerName;
    private String Category;
    private String qatype;
    private String ApplnStatus;
    private String mobile;
    private String quotetype;
    private String created_date;
    private int pendingdays;
    private String BankImage;
    /**
     * BankImage : null
     * cdate : 2018-08-18 01:17:13
     */

    private String cdate;
    private String ApplicationNo;


    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getQatype() {
        return qatype;
    }

    public void setQatype(String qatype) {
        this.qatype = qatype;
    }

    public String getApplnStatus() {
        return ApplnStatus;
    }

    public void setApplnStatus(String ApplnStatus) {
        this.ApplnStatus = ApplnStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQuotetype() {
        return quotetype;
    }

    public void setQuotetype(String quotetype) {
        this.quotetype = quotetype;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getPendingdays() {
        return pendingdays;
    }

    public void setPendingdays(int pendingdays) {
        this.pendingdays = pendingdays;
    }

    public String getBankImage() {
        return BankImage;
    }

    public void setBankImage(String bankImage) {
        BankImage = bankImage;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getApplicationNo() {
        return ApplicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        ApplicationNo = applicationNo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.CustomerName);
        dest.writeString(this.Category);
        dest.writeString(this.qatype);
        dest.writeString(this.ApplnStatus);
        dest.writeString(this.mobile);
        dest.writeString(this.quotetype);
        dest.writeString(this.created_date);
        dest.writeInt(this.pendingdays);
        dest.writeString(this.BankImage);
        dest.writeString(this.cdate);
        dest.writeString(this.ApplicationNo);
    }

    public PendingCasesEntity() {
    }

    protected PendingCasesEntity(Parcel in) {
        this.Id = in.readInt();
        this.CustomerName = in.readString();
        this.Category = in.readString();
        this.qatype = in.readString();
        this.ApplnStatus = in.readString();
        this.mobile = in.readString();
        this.quotetype = in.readString();
        this.created_date = in.readString();
        this.pendingdays = in.readInt();
        this.BankImage = in.readString();
        this.cdate = in.readString();
        this.ApplicationNo = in.readString();
    }

    public static final Parcelable.Creator<PendingCasesEntity> CREATOR = new Parcelable.Creator<PendingCasesEntity>() {
        @Override
        public PendingCasesEntity createFromParcel(Parcel source) {
            return new PendingCasesEntity(source);
        }

        @Override
        public PendingCasesEntity[] newArray(int size) {
            return new PendingCasesEntity[size];
        }
    };
}