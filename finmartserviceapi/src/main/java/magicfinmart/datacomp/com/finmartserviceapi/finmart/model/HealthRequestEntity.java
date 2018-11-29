package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HealthRequestEntity implements Parcelable {
    /**
     * HealthRequestId : 13
     * crn : 2132132
     * CityID : 1020
     * ContactEmail : pramod.parit@rupeeboss.com
     * ContactMobile : 9930089092
     * ContactName : pramod parit
     * DeductibleAmount : 0
     * ExistingCustomerReferenceID : 0
     * HealthType : Health
     * MaritalStatusID : 1
     * PolicyFor : Family
     * PolicyTermYear : 1
     * ProductID : 2
     * SessionID : 0
     * SourceType : APP
     * SumInsured : 300000
     * SupportsAgentID : 2
     * FBAID : 123
     * agent_source : App
     * Quote_Application_Status : Q
     * conversion_date : null
     * created_date : 2018-02-09T05:23:51.000Z
     * updated_date : null
     * isActive : 1
     * MemberList : [{"HealthMemberListId":"37","MemberDOB":"07-06-1984","MemberGender":"M","MemberNumber":"1","MemberTypeID":"1","HealthRequestId":"13"},{"HealthMemberListId":"38","MemberDOB":"07-06-1989","MemberGender":"F","MemberNumber":"2","MemberTypeID":"2","HealthRequestId":"13"},{"HealthMemberListId":"39","MemberDOB":"29-04-2017","MemberGender":"F","MemberNumber":"5","MemberTypeID":"3","HealthRequestId":"13"}]
     */

    private int HealthRequestId;
    private String crn;
    private int CityID;
    private String ContactEmail;
    private String ContactMobile;
    private String ContactName;
    private int DeductibleAmount;
    private int ExistingCustomerReferenceID;
    private String HealthType;
    private int MaritalStatusID;
    private String PolicyFor;
    private int PolicyTermYear;
    private int ProductID;
    private int SessionID;
    private String SourceType;
    private String SumInsured;
    private int SupportsAgentID;
    private int FBAID;
    private String agent_source;
    private String Quote_Application_Status;
    private String conversion_date;
    private String created_date;
    private String updated_date;
    private int isActive;
    private List<MemberListEntity> MemberList;
    private int selectedPrevInsID;
    private int pincode;
    private String PBStatus;
    private int StatusPercent;

    public int getStatusPercent() {
        return StatusPercent;
    }

    public void setStatusPercent(int statusPercent) {
        StatusPercent = statusPercent;
    }

    public String getPBStatus() {
        return PBStatus;
    }

    public void setPBStatus(String PBStatus) {
        this.PBStatus = PBStatus;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public int getSelectedPrevInsID() {
        return selectedPrevInsID;
    }

    public void setSelectedPrevInsID(int selectedPrevInsID) {
        this.selectedPrevInsID = selectedPrevInsID;
    }

    public int getHealthRequestId() {
        return HealthRequestId;
    }

    public void setHealthRequestId(int HealthRequestId) {
        this.HealthRequestId = HealthRequestId;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public int getCityID() {
        return CityID;
    }

    public void setCityID(int CityID) {
        this.CityID = CityID;
    }

    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String ContactEmail) {
        this.ContactEmail = ContactEmail;
    }

    public String getContactMobile() {
        return ContactMobile;
    }

    public void setContactMobile(String ContactMobile) {
        this.ContactMobile = ContactMobile;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public int getDeductibleAmount() {
        return DeductibleAmount;
    }

    public void setDeductibleAmount(int DeductibleAmount) {
        this.DeductibleAmount = DeductibleAmount;
    }

    public int getExistingCustomerReferenceID() {
        return ExistingCustomerReferenceID;
    }

    public void setExistingCustomerReferenceID(int ExistingCustomerReferenceID) {
        this.ExistingCustomerReferenceID = ExistingCustomerReferenceID;
    }

    public String getHealthType() {
        return HealthType;
    }

    public void setHealthType(String HealthType) {
        this.HealthType = HealthType;
    }

    public int getMaritalStatusID() {
        return MaritalStatusID;
    }

    public void setMaritalStatusID(int MaritalStatusID) {
        this.MaritalStatusID = MaritalStatusID;
    }

    public String getPolicyFor() {
        return PolicyFor;
    }

    public void setPolicyFor(String PolicyFor) {
        this.PolicyFor = PolicyFor;
    }

    public int getPolicyTermYear() {
        return PolicyTermYear;
    }

    public void setPolicyTermYear(int PolicyTermYear) {
        this.PolicyTermYear = PolicyTermYear;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getSessionID() {
        return SessionID;
    }

    public void setSessionID(int SessionID) {
        this.SessionID = SessionID;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String SourceType) {
        this.SourceType = SourceType;
    }

    public String getSumInsured() {
        return SumInsured;
    }

    public void setSumInsured(String SumInsured) {
        this.SumInsured = SumInsured;
    }

    public int getSupportsAgentID() {
        return SupportsAgentID;
    }

    public void setSupportsAgentID(int SupportsAgentID) {
        this.SupportsAgentID = SupportsAgentID;
    }

    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }

    public String getAgent_source() {
        return agent_source;
    }

    public void setAgent_source(String agent_source) {
        this.agent_source = agent_source;
    }

    public String getQuote_Application_Status() {
        return Quote_Application_Status;
    }

    public void setQuote_Application_Status(String Quote_Application_Status) {
        this.Quote_Application_Status = Quote_Application_Status;
    }

    public String getConversion_date() {
        return conversion_date;
    }

    public void setConversion_date(String conversion_date) {
        this.conversion_date = conversion_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public List<MemberListEntity> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<MemberListEntity> MemberList) {
        this.MemberList = MemberList;
    }


    public HealthRequestEntity() {

        CityID = 0;
        ContactEmail = "";
        ContactMobile = "";
        ContactName = "";
        DeductibleAmount = 0;
        ExistingCustomerReferenceID = 0;
        HealthType = "Health";
        MaritalStatusID = 1;
        PolicyFor = "";
        PolicyTermYear = 1;
        ProductID = 2;
        SessionID = 0;
        SourceType = "APP";
        SumInsured = "";
        SupportsAgentID = 2;
        MemberList = null;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.HealthRequestId);
        dest.writeString(this.crn);
        dest.writeInt(this.CityID);
        dest.writeString(this.ContactEmail);
        dest.writeString(this.ContactMobile);
        dest.writeString(this.ContactName);
        dest.writeInt(this.DeductibleAmount);
        dest.writeInt(this.ExistingCustomerReferenceID);
        dest.writeString(this.HealthType);
        dest.writeInt(this.MaritalStatusID);
        dest.writeString(this.PolicyFor);
        dest.writeInt(this.PolicyTermYear);
        dest.writeInt(this.ProductID);
        dest.writeInt(this.SessionID);
        dest.writeString(this.SourceType);
        dest.writeString(this.SumInsured);
        dest.writeInt(this.SupportsAgentID);
        dest.writeInt(this.FBAID);
        dest.writeString(this.agent_source);
        dest.writeString(this.Quote_Application_Status);
        dest.writeString(this.conversion_date);
        dest.writeString(this.created_date);
        dest.writeString(this.updated_date);
        dest.writeInt(this.isActive);
        dest.writeTypedList(this.MemberList);
        dest.writeInt(this.selectedPrevInsID);
        dest.writeInt(this.pincode);
        dest.writeString(this.PBStatus);
        dest.writeInt(this.StatusPercent);
    }

    protected HealthRequestEntity(Parcel in) {
        this.HealthRequestId = in.readInt();
        this.crn = in.readString();
        this.CityID = in.readInt();
        this.ContactEmail = in.readString();
        this.ContactMobile = in.readString();
        this.ContactName = in.readString();
        this.DeductibleAmount = in.readInt();
        this.ExistingCustomerReferenceID = in.readInt();
        this.HealthType = in.readString();
        this.MaritalStatusID = in.readInt();
        this.PolicyFor = in.readString();
        this.PolicyTermYear = in.readInt();
        this.ProductID = in.readInt();
        this.SessionID = in.readInt();
        this.SourceType = in.readString();
        this.SumInsured = in.readString();
        this.SupportsAgentID = in.readInt();
        this.FBAID = in.readInt();
        this.agent_source = in.readString();
        this.Quote_Application_Status = in.readString();
        this.conversion_date = in.readString();
        this.created_date = in.readString();
        this.updated_date = in.readString();
        this.isActive = in.readInt();
        this.MemberList = in.createTypedArrayList(MemberListEntity.CREATOR);
        this.selectedPrevInsID = in.readInt();
        this.pincode = in.readInt();
        this.PBStatus = in.readString();
        this.StatusPercent = in.readInt();
    }

    public static final Parcelable.Creator<HealthRequestEntity> CREATOR = new Parcelable.Creator<HealthRequestEntity>() {
        @Override
        public HealthRequestEntity createFromParcel(Parcel source) {
            return new HealthRequestEntity(source);
        }

        @Override
        public HealthRequestEntity[] newArray(int size) {
            return new HealthRequestEntity[size];
        }
    };
}