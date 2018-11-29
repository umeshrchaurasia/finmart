package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajeev Ranjan on 19/09/2018.
 */

public class HomeLoanApplyRequestEntity implements Parcelable  {

    /**
     * name :
     * gender :
     * occupation :
     * dob :
     * loanEMI :
     * pan :
     * mobileNo :
     * loanAmnt :
     * emailId :
     * loanTenure : 0
     * empCode :
     * applnSource :
     * rbaSource :
     * bankId : 0
     * brokerId : 0
     * quoteId : 0
     * productId : 0
     * pinCode :
     * applnId : 0
     * dc_fba_reg :
     * isApplnComplete : 0
     * isApplnConfirm : 0
     * fbA_Reg_Id :
     * quote_id :
     */

    private String name;
    private String gender;
    private String occupation;
    private String dob;
    private String loanEMI;
    private String pan;
    private String mobileNo;
    private String loanAmnt;
    private String emailId;
    private int loanTenure;
    private String empCode;
    private String applnSource;

    private int bankId;
    private int brokerId;
    private int quoteId;
    private int productId;
    private String pinCode;
    private int applnId;
    private String dc_fba_reg;
    private int isApplnComplete;
    private int isApplnConfirm;
    private String FBA_Reg_Id;
    private String Quote_id;
    private String RBASource;

    private String city;


    protected HomeLoanApplyRequestEntity(Parcel in) {
        name = in.readString();
        gender = in.readString();
        occupation = in.readString();
        dob = in.readString();
        loanEMI = in.readString();
        pan = in.readString();
        mobileNo = in.readString();
        loanAmnt = in.readString();
        emailId = in.readString();
        loanTenure = in.readInt();
        empCode = in.readString();
        applnSource = in.readString();
        bankId = in.readInt();
        brokerId = in.readInt();
        quoteId = in.readInt();
        productId = in.readInt();
        pinCode = in.readString();
        applnId = in.readInt();
        dc_fba_reg = in.readString();
        isApplnComplete = in.readInt();
        isApplnConfirm = in.readInt();
        FBA_Reg_Id = in.readString();
        Quote_id = in.readString();
        RBASource = in.readString();
        city=in.readString();
    }

    public static final Creator<HomeLoanApplyRequestEntity> CREATOR = new Creator<HomeLoanApplyRequestEntity>() {
        @Override
        public HomeLoanApplyRequestEntity createFromParcel(Parcel in) {
            return new HomeLoanApplyRequestEntity(in);
        }

        @Override
        public HomeLoanApplyRequestEntity[] newArray(int size) {
            return new HomeLoanApplyRequestEntity[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLoanEMI() {
        return loanEMI;
    }

    public void setLoanEMI(String loanEMI) {
        this.loanEMI = loanEMI;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getLoanAmnt() {
        return loanAmnt;
    }

    public void setLoanAmnt(String loanAmnt) {
        this.loanAmnt = loanAmnt;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getApplnSource() {
        return applnSource;
    }

    public void setApplnSource(String applnSource) {
        this.applnSource = applnSource;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public int getApplnId() {
        return applnId;
    }

    public void setApplnId(int applnId) {
        this.applnId = applnId;
    }

    public String getDc_fba_reg() {
        return dc_fba_reg;
    }

    public void setDc_fba_reg(String dc_fba_reg) {
        this.dc_fba_reg = dc_fba_reg;
    }

    public int getIsApplnComplete() {
        return isApplnComplete;
    }

    public void setIsApplnComplete(int isApplnComplete) {
        this.isApplnComplete = isApplnComplete;
    }

    public int getIsApplnConfirm() {
        return isApplnConfirm;
    }

    public void setIsApplnConfirm(int isApplnConfirm) {
        this.isApplnConfirm = isApplnConfirm;
    }

    public String getFBA_Reg_Id() {
        return FBA_Reg_Id;
    }

    public void setFBA_Reg_Id(String FBA_Reg_Id) {
        this.FBA_Reg_Id = FBA_Reg_Id;
    }

    public String getQuote_id() {
        return Quote_id;
    }

    public void setQuote_id(String quote_id) {
        Quote_id = quote_id;
    }

    public String getRBASource() {
        return RBASource;
    }

    public void setRBASource(String RBASource) {
        this.RBASource = RBASource;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public HomeLoanApplyRequestEntity() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(occupation);
        dest.writeString(dob);
        dest.writeString(loanEMI);
        dest.writeString(pan);
        dest.writeString(mobileNo);
        dest.writeString(loanAmnt);
        dest.writeString(emailId);
        dest.writeInt(loanTenure);
        dest.writeString(empCode);
        dest.writeString(applnSource);
        dest.writeInt(bankId);
        dest.writeInt(brokerId);
        dest.writeInt(quoteId);
        dest.writeInt(productId);
        dest.writeString(pinCode);
        dest.writeInt(applnId);
        dest.writeString(dc_fba_reg);
        dest.writeInt(isApplnComplete);
        dest.writeInt(isApplnConfirm);
        dest.writeString(FBA_Reg_Id);
        dest.writeString(Quote_id);
        dest.writeString(RBASource);
        dest.writeString(city);
    }
}
