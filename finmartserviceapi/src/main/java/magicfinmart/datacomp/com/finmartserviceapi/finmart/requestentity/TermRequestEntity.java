package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;

/**
 * Created by Nilesh Birhade on 05-04-2018.
 */

public class TermRequestEntity implements Parcelable {

    /**
     * PolicyTerm : 20
     * InsuredGender : M
     * Is_TabaccoUser : false
     * SumAssured : 10000000
     * InsuredDOB : 13-03-1985
     * PaymentModeValue : 1
     * PolicyCommencementDate : 24-03-2018
     * CityName : Mumbai
     * State : Maharashtra
     * PlanTaken : Life
     * Frequency : Annual
     * DeathBenefitOption : Lump-Sum
     * PPT : 20
     * IncomeTerm : 20
     * MonthlyIncome : 25000
     * LumpsumAmount : 10000000
     * IncreaseIncomePercentage : 5
     * IncreaseSAPercentage :
     * ADBPercentage : 100
     * CISA :
     * LumpsumBSAProp :
     * ADBSA :
     * TypeOfLife :
     * ATPDSA :
     * HCBSA :
     * WOP :
     * PaymentOptn :
     * MaritalStatus :
     * PremiumPaymentOption :
     * ServiceTaxNotApplicable :
     * CIBenefit :
     * ADHB :
     * InsurerId : 39
     * SessionID :
     * Existing_ProductInsuranceMapping_Id :
     * ContactName : Xzb xhxh
     * ContactEmail : finmarttest@gmail.com
     * ContactMobile :
     * SupportsAgentID : 1682
     */
    private String pincode;
    private String PolicyTerm;
    private String InsuredGender;
    private String Is_TabaccoUser;
    private String SumAssured;
    private String InsuredDOB;
    private String PaymentModeValue;
    /**
     * FBAID :
     */

    private String FBAID;

    /**
     * LumpsumPercentage : 0
     */


    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


    private String PolicyCommencementDate;
    private String CityName;
    private String State;
    private String PlanTaken;
    private String Frequency;
    private String DeathBenefitOption;
    private String PPT;
    private String IncomeTerm;

    //HDFC Specific Parameters
    private String MonthlyIncome;
    private String LumpsumAmount;
    private String IncreaseIncomePercentage;
    private String IncreaseSAPercentage;
    private String ADBPercentage;

    //Edelweiss Specific Parameters
    private String CISA;
    private String LumpsumBSAProp;
    private String ADBSA;
    private String TypeOfLife;
    private String ATPDSA;
    private String HCBSA;
    private String WOP;
    private String PaymentOptn;


    // ICICI Specific Paramaeters
    private String MaritalStatus;
    private String PremiumPaymentOption;
    private String ServiceTaxNotApplicable;
    private String CIBenefit;
    private String ADHB;
    private String LumpsumPercentage;

    private int InsurerId;
    private String SessionID;
    private String Existing_ProductInsuranceMapping_Id;
    private String ContactName;
    private String ContactEmail;
    private String ContactMobile;
    private String SupportsAgentID;

    private String created_date;
    private String crn;


    public TermRequestEntity(Context context) {
        LoginResponseEntity loginResponseEntity = new DBPersistanceController(context).getUserData();
        this.FBAID = String.valueOf(loginResponseEntity.getFBAId());
        if (loginResponseEntity.getPOSPNo() != null && !loginResponseEntity.getPOSPNo().equals(""))
            this.SupportsAgentID = loginResponseEntity.getPOSPNo();
        else
            this.SupportsAgentID = "5";
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getPolicyTerm() {
        return PolicyTerm;
    }

    public void setPolicyTerm(String PolicyTerm) {
        this.PolicyTerm = PolicyTerm;
    }

    public String getInsuredGender() {
        return InsuredGender;
    }

    public void setInsuredGender(String InsuredGender) {
        this.InsuredGender = InsuredGender;
    }

    public String getIs_TabaccoUser() {
        return Is_TabaccoUser;
    }

    public void setIs_TabaccoUser(String Is_TabaccoUser) {
        this.Is_TabaccoUser = Is_TabaccoUser;
    }

    public String getSumAssured() {
        return SumAssured;
    }

    public void setSumAssured(String SumAssured) {
        this.SumAssured = SumAssured;
    }

    public String getInsuredDOB() {
        return InsuredDOB;
    }

    public void setInsuredDOB(String InsuredDOB) {
        this.InsuredDOB = InsuredDOB;
    }

    public String getPaymentModeValue() {
        return PaymentModeValue;
    }

    public void setPaymentModeValue(String PaymentModeValue) {
        this.PaymentModeValue = PaymentModeValue;
    }

    public String getPolicyCommencementDate() {
        return PolicyCommencementDate;
    }

    public void setPolicyCommencementDate(String PolicyCommencementDate) {
        this.PolicyCommencementDate = PolicyCommencementDate;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getPlanTaken() {
        return PlanTaken;
    }

    public void setPlanTaken(String PlanTaken) {
        this.PlanTaken = PlanTaken;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String Frequency) {
        this.Frequency = Frequency;
    }

    public String getDeathBenefitOption() {
        return DeathBenefitOption;
    }

    public void setDeathBenefitOption(String DeathBenefitOption) {
        this.DeathBenefitOption = DeathBenefitOption;
    }

    public String getPPT() {
        return PPT;
    }

    public void setPPT(String PPT) {
        this.PPT = PPT;
    }

    public String getIncomeTerm() {
        return IncomeTerm;
    }

    public void setIncomeTerm(String IncomeTerm) {
        this.IncomeTerm = IncomeTerm;
    }

    public String getMonthlyIncome() {
        return MonthlyIncome;
    }

    public void setMonthlyIncome(String MonthlyIncome) {
        this.MonthlyIncome = MonthlyIncome;
    }

    public String getLumpsumAmount() {
        return LumpsumAmount;
    }

    public void setLumpsumAmount(String LumpsumAmount) {
        this.LumpsumAmount = LumpsumAmount;
    }

    public String getIncreaseIncomePercentage() {
        return IncreaseIncomePercentage;
    }

    public void setIncreaseIncomePercentage(String IncreaseIncomePercentage) {
        this.IncreaseIncomePercentage = IncreaseIncomePercentage;
    }

    public String getIncreaseSAPercentage() {
        return IncreaseSAPercentage;
    }

    public void setIncreaseSAPercentage(String IncreaseSAPercentage) {
        this.IncreaseSAPercentage = IncreaseSAPercentage;
    }

    public String getADBPercentage() {
        return ADBPercentage;
    }

    public void setADBPercentage(String ADBPercentage) {
        this.ADBPercentage = ADBPercentage;
    }

    public String getCISA() {
        return CISA;
    }

    public void setCISA(String CISA) {
        this.CISA = CISA;
    }

    public String getLumpsumBSAProp() {
        return LumpsumBSAProp;
    }

    public void setLumpsumBSAProp(String LumpsumBSAProp) {
        this.LumpsumBSAProp = LumpsumBSAProp;
    }

    public String getADBSA() {
        return ADBSA;
    }

    public void setADBSA(String ADBSA) {
        this.ADBSA = ADBSA;
    }

    public String getTypeOfLife() {
        return TypeOfLife;
    }

    public void setTypeOfLife(String TypeOfLife) {
        this.TypeOfLife = TypeOfLife;
    }

    public String getATPDSA() {
        return ATPDSA;
    }

    public void setATPDSA(String ATPDSA) {
        this.ATPDSA = ATPDSA;
    }

    public String getHCBSA() {
        return HCBSA;
    }

    public void setHCBSA(String HCBSA) {
        this.HCBSA = HCBSA;
    }

    public String getWOP() {
        return WOP;
    }

    public void setWOP(String WOP) {
        this.WOP = WOP;
    }

    public String getPaymentOptn() {
        return PaymentOptn;
    }

    public void setPaymentOptn(String PaymentOptn) {
        this.PaymentOptn = PaymentOptn;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String MaritalStatus) {
        this.MaritalStatus = MaritalStatus;
    }

    public String getPremiumPaymentOption() {
        return PremiumPaymentOption;
    }

    public void setPremiumPaymentOption(String PremiumPaymentOption) {
        this.PremiumPaymentOption = PremiumPaymentOption;
    }

    public String getServiceTaxNotApplicable() {
        return ServiceTaxNotApplicable;
    }

    public void setServiceTaxNotApplicable(String ServiceTaxNotApplicable) {
        this.ServiceTaxNotApplicable = ServiceTaxNotApplicable;
    }

    public String getCIBenefit() {
        return CIBenefit;
    }

    public void setCIBenefit(String CIBenefit) {
        this.CIBenefit = CIBenefit;
    }

    public String getADHB() {
        return ADHB;
    }

    public void setADHB(String ADHB) {
        this.ADHB = ADHB;
    }

    public int getInsurerId() {
        return InsurerId;
    }

    public void setInsurerId(int InsurerId) {
        this.InsurerId = InsurerId;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public String getExisting_ProductInsuranceMapping_Id() {
        return Existing_ProductInsuranceMapping_Id;
    }

    public void setExisting_ProductInsuranceMapping_Id(String Existing_ProductInsuranceMapping_Id) {
        this.Existing_ProductInsuranceMapping_Id = Existing_ProductInsuranceMapping_Id;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
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

    public String getSupportsAgentID() {
        return SupportsAgentID;
    }

    public void setSupportsAgentID(String SupportsAgentID) {
        this.SupportsAgentID = SupportsAgentID;
    }

    public String getLumpsumPercentage() {
        return LumpsumPercentage;
    }

    public void setLumpsumPercentage(String LumpsumPercentage) {
        this.LumpsumPercentage = LumpsumPercentage;
    }


    public String getFBAID() {
        return FBAID;
    }

    public void setFBAID(String FBAID) {
        this.FBAID = FBAID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pincode);
        dest.writeString(this.PolicyTerm);
        dest.writeString(this.InsuredGender);
        dest.writeString(this.Is_TabaccoUser);
        dest.writeString(this.SumAssured);
        dest.writeString(this.InsuredDOB);
        dest.writeString(this.PaymentModeValue);
        dest.writeString(this.FBAID);
        dest.writeString(this.PolicyCommencementDate);
        dest.writeString(this.CityName);
        dest.writeString(this.State);
        dest.writeString(this.PlanTaken);
        dest.writeString(this.Frequency);
        dest.writeString(this.DeathBenefitOption);
        dest.writeString(this.PPT);
        dest.writeString(this.IncomeTerm);
        dest.writeString(this.MonthlyIncome);
        dest.writeString(this.LumpsumAmount);
        dest.writeString(this.IncreaseIncomePercentage);
        dest.writeString(this.IncreaseSAPercentage);
        dest.writeString(this.ADBPercentage);
        dest.writeString(this.CISA);
        dest.writeString(this.LumpsumBSAProp);
        dest.writeString(this.ADBSA);
        dest.writeString(this.TypeOfLife);
        dest.writeString(this.ATPDSA);
        dest.writeString(this.HCBSA);
        dest.writeString(this.WOP);
        dest.writeString(this.PaymentOptn);
        dest.writeString(this.MaritalStatus);
        dest.writeString(this.PremiumPaymentOption);
        dest.writeString(this.ServiceTaxNotApplicable);
        dest.writeString(this.CIBenefit);
        dest.writeString(this.ADHB);
        dest.writeString(this.LumpsumPercentage);
        dest.writeInt(this.InsurerId);
        dest.writeString(this.SessionID);
        dest.writeString(this.Existing_ProductInsuranceMapping_Id);
        dest.writeString(this.ContactName);
        dest.writeString(this.ContactEmail);
        dest.writeString(this.ContactMobile);
        dest.writeString(this.SupportsAgentID);
        dest.writeString(this.created_date);
        dest.writeString(this.crn);
    }

    protected TermRequestEntity(Parcel in) {
        this.pincode = in.readString();
        this.PolicyTerm = in.readString();
        this.InsuredGender = in.readString();
        this.Is_TabaccoUser = in.readString();
        this.SumAssured = in.readString();
        this.InsuredDOB = in.readString();
        this.PaymentModeValue = in.readString();
        this.FBAID = in.readString();
        this.PolicyCommencementDate = in.readString();
        this.CityName = in.readString();
        this.State = in.readString();
        this.PlanTaken = in.readString();
        this.Frequency = in.readString();
        this.DeathBenefitOption = in.readString();
        this.PPT = in.readString();
        this.IncomeTerm = in.readString();
        this.MonthlyIncome = in.readString();
        this.LumpsumAmount = in.readString();
        this.IncreaseIncomePercentage = in.readString();
        this.IncreaseSAPercentage = in.readString();
        this.ADBPercentage = in.readString();
        this.CISA = in.readString();
        this.LumpsumBSAProp = in.readString();
        this.ADBSA = in.readString();
        this.TypeOfLife = in.readString();
        this.ATPDSA = in.readString();
        this.HCBSA = in.readString();
        this.WOP = in.readString();
        this.PaymentOptn = in.readString();
        this.MaritalStatus = in.readString();
        this.PremiumPaymentOption = in.readString();
        this.ServiceTaxNotApplicable = in.readString();
        this.CIBenefit = in.readString();
        this.ADHB = in.readString();
        this.LumpsumPercentage = in.readString();
        this.InsurerId = in.readInt();
        this.SessionID = in.readString();
        this.Existing_ProductInsuranceMapping_Id = in.readString();
        this.ContactName = in.readString();
        this.ContactEmail = in.readString();
        this.ContactMobile = in.readString();
        this.SupportsAgentID = in.readString();
        this.created_date = in.readString();
        this.crn = in.readString();
    }

    public static final Parcelable.Creator<TermRequestEntity> CREATOR = new Parcelable.Creator<TermRequestEntity>() {
        @Override
        public TermRequestEntity createFromParcel(Parcel source) {
            return new TermRequestEntity(source);
        }

        @Override
        public TermRequestEntity[] newArray(int size) {
            return new TermRequestEntity[size];
        }
    };
}
