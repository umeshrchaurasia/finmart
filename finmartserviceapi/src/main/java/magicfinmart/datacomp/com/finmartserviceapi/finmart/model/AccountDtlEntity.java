package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AccountDtlEntity extends RealmObject {


    /**
     * FBAID : 6
     * FBACode : null
     * FBAType : null
     * FirstName : SANDIP
     * MiddName : KUMAR
     * LastName : YOGI
     * FullName : SANDIP YOGI
     * Gender : M
     * DOB : 1984-10-08 00:00:00
     * Mobile_1 : 44445555666
     * Mobile_2 : null
     * EmailID : abc@gmail.com
     * Address_1 : null
     * Address_2 : null
     * Address_3 : null
     * PinCode : null
     * City : null
     * StateID : null
     * JoinDate : 2016-06-01 00:00:00
     * IntrType : C
     * IntrCode : D
     * FBAStat : A
     * StatDate : 2016-01-01 00:00:00
     * LeadID : 0
     * Remarks : ok
     * SMID : 1
     * RewaPoin : null
     * IsCustIdRequ : 1
     * CreaOn : 2017-05-31 15:13:09
     * Designation : RupeeBoss
     * EditEmailId : abc@gmail.com
     * EditMobiNumb : 44445555666
     * Amount : null
     * PaidDate : null
     * PayStat : S
     * CustID : 680123
     * ProfPictName : null
     * GstNo : null
     * FBAPan : null
     * IsFoc : null
     * IsBlocked : null
     * CityId : null
     * created_by : null
     * created_on : null
     * salescode : null
     * FBARID : 1
     * POSPName : SHAN SAYYED
     * Posp_PAN : HDJSK5567B
     * Posp_Aadhaar : 464664698959
     * Posp_BankAcNo : GDHDJDKSL
     * Posp_Account_Type : SAVINGS
     * Posp_BankName : ABHYUDAYA COOPERATIVE BANK LIMITED
     * Posp_BankBranch : MITHAKHALI
     * Posp_IFSC : ABHY0065202
     * Posp_MICR : 380065002
     * POSPStat : 6
     * POSPCode : null
     * POSPFrom : null
     * LoanName : Home LOAN
     * Loan_PAN : HDJSK5567B
     * Loan_Aadhaar : 464664698959
     * Loan_BankAcNo : 145789656
     * Loan_Account_Type : SAVING
     * Loan_BankName : IDFC BANK
     * Loan_BankBranch : kurla
     * LoanStat : null
     * Loan_IFSC : ABHY0065202
     * Loan_MICR : 380065002
     * OtheName : SAMDIAS
     * Other_PAN : XKDJH5678
     * Other_Aadhaar : 499994977979
     * Other_BankAcNo : VSHSJS
     * Other_Account_Type : SAVINGS
     * Other_BankName : ABHYUDAYA COOPERATIVE BANK LIMITED
     * Other_BankBranch : MANEKCHOWK
     * Other_IFSC : ABHY0065203
     * Other_MICR : 380065003
     * POSPNo : 274
     * LoanID : 23335
     * Posp_ServiceTaxNo : null
     * ProdID : null
     * SuppAgenId : 1101
     * Posp_DOB : null
     * Posp_Gender : null
     * Posp_Mobile1 : 9768952387
     * Posp_Mobile2 : null
     * Posp_Email : jitendrap@insuremagic.com
     * Posp_Address1 : null
     * Posp_Address2 : null
     * Posp_Address3 : null
     * Posp_PinCode : null
     * Posp_City : null
     * Posp_StatID : null
     * Posp_ChanPartCode : null
     * POSPRegiDate : null
     * DisplayEmail : null
     * DisplayPhoneNo : null
     * DisplayDesignation : null
     */
    @PrimaryKey
    private int FBAID;
    private String FBACode;
    private String FBAType;
    private String FirstName;
    private String MiddName;
    private String LastName;
    private String FullName;
    private String Gender;
    private String DOB;
    private String Mobile_1;
    private String Mobile_2;
    private String EmailID;
    private String Address_1;
    private String Address_2;
    private String Address_3;
    private String PinCode;
    private String City;
    private String StateID;
    private String JoinDate;
    private String IntrType;
    private String IntrCode;
    private String FBAStat;

    private String StateName;
    private String LeadID;
    private String Remarks;
    private String SMID;
    private String RewaPoin;
    private String IsCustIdRequ;
    private String CreaOn;
    private String Designation;
    private String EditEmailId;
    private String EditMobiNumb;
    private String Amount;
    private String PaidDate;
    private String PayStat;
    private String CustID;
    private String ProfPictName;
    private String GstNo;
    private String FBAPan;
  //  private String IsFoc;
    private String IsBlocked;
    private String CityId;
    private String created_by;
    private String created_on;
    private String salescode;
    private int FBARID;
    private String POSPName;
    private String Posp_PAN;
    private String Posp_Aadhaar;
    private String Posp_BankAcNo;
    private String Posp_Account_Type;
    private String Posp_BankName;
    private String Posp_BankBranch;
    private String Posp_IFSC;
    private String Posp_MICR;
    private String POSPStat;
    private String POSPCode;
    private String POSPFrom;
    private String LoanName;
    private String Loan_PAN;
    private String Loan_Aadhaar;
    private String Loan_BankAcNo;
    private String Loan_Account_Type;
    private String Loan_BankName;
    private String Loan_BankBranch;
    private String LoanStat;
    private String Loan_IFSC;
    private String Loan_MICR;
    private String Loan_BankCity;
    private String OtheName;
    private String Other_PAN;
    private String Other_Aadhaar;
    private String Other_BankAcNo;
    private String Other_Account_Type;
    private String Other_BankName;
    private String Other_BankBranch;
    private String Other_IFSC;
    private String Other_MICR;
    private String POSPNo;
    private String LoanID;
    private String Posp_ServiceTaxNo;
    private String ProdID;
    private String SuppAgenId;
    private String Posp_DOB;
    private String Posp_Gender;
    private String Posp_Mobile1;
    private String Posp_Mobile2;
    private String Posp_Email;
    private String Posp_Address1;
    private String Posp_Address2;
    private String Posp_Address3;
    private String Posp_PinCode;
    private String Posp_City;
    private String Posp_StatID;
    private String Posp_ChanPartCode;
    private String POSPRegiDate;
    private String DisplayEmail;
    private String DisplayPhoneNo;
    private String DisplayDesignation;
    private RealmList<DocAvailableEntity> doc_available;
    /**
     * FBAID : 3
     * StatDate : 1900-01-01 00:00:00
     * FBARID : 2
     * doc_available : [{"DocType":1,"FileName":"1519378976465.png","DocName":"Profile"},{"DocType":2,"FileName":"140","DocName":"Photograph"},{"DocType":3,"FileName":"23","DocName":"PAN"},{"DocType":4,"FileName":"","DocName":"Cancelled Check"},{"DocType":5,"FileName":"","DocName":"Aadhar"}]
     */



    public String getLoan_BankCity() {
        return Loan_BankCity;
    }

    public void setLoan_BankCity(String loan_BankCity) {
        Loan_BankCity = loan_BankCity;
    }


    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }

    public String getFBACode() {
        return FBACode;
    }

    public void setFBACode(String FBACode) {
        this.FBACode = FBACode;
    }

    public String getFBAType() {
        return FBAType;
    }

    public void setFBAType(String FBAType) {
        this.FBAType = FBAType;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMiddName() {
        return MiddName;
    }

    public void setMiddName(String MiddName) {
        this.MiddName = MiddName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getMobile_1() {
        return Mobile_1;
    }

    public void setMobile_1(String Mobile_1) {
        this.Mobile_1 = Mobile_1;
    }

    public String getMobile_2() {
        return Mobile_2;
    }

    public void setMobile_2(String Mobile_2) {
        this.Mobile_2 = Mobile_2;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }


    public String getAddress_1() {
        return Address_1;
    }

    public void setAddress_1(String Address_1) {
        this.Address_1 = Address_1;
    }

    public String getAddress_2() {
        return Address_2;
    }

    public void setAddress_2(String Address_2) {
        this.Address_2 = Address_2;
    }

    public String getAddress_3() {
        return Address_3;
    }

    public void setAddress_3(String Address_3) {
        this.Address_3 = Address_3;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String PinCode) {
        this.PinCode = PinCode;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getStateID() {
        return StateID;
    }

    public void setStateID(String StateID) {
        this.StateID = StateID;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String JoinDate) {
        this.JoinDate = JoinDate;
    }

    public String getIntrType() {
        return IntrType;
    }

    public void setIntrType(String IntrType) {
        this.IntrType = IntrType;
    }

    public String getIntrCode() {
        return IntrCode;
    }

    public void setIntrCode(String IntrCode) {
        this.IntrCode = IntrCode;
    }

    public String getFBAStat() {
        return FBAStat;
    }

    public void setFBAStat(String FBAStat) {
        this.FBAStat = FBAStat;
    }


    public String getLeadID() {
        return LeadID;
    }

    public void setLeadID(String LeadID) {
        this.LeadID = LeadID;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getSMID() {
        return SMID;
    }

    public void setSMID(String SMID) {
        this.SMID = SMID;
    }

    public String getRewaPoin() {
        return RewaPoin;
    }

    public void setRewaPoin(String RewaPoin) {
        this.RewaPoin = RewaPoin;
    }

    public String getIsCustIdRequ() {
        return IsCustIdRequ;
    }

    public void setIsCustIdRequ(String IsCustIdRequ) {
        this.IsCustIdRequ = IsCustIdRequ;
    }

    public String getCreaOn() {
        return CreaOn;
    }

    public void setCreaOn(String CreaOn) {
        this.CreaOn = CreaOn;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getEditEmailId() {
        return EditEmailId;
    }

    public void setEditEmailId(String EditEmailId) {
        this.EditEmailId = EditEmailId;
    }

    public String getEditMobiNumb() {
        return EditMobiNumb;
    }

    public void setEditMobiNumb(String EditMobiNumb) {
        this.EditMobiNumb = EditMobiNumb;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getPaidDate() {
        return PaidDate;
    }

    public void setPaidDate(String PaidDate) {
        this.PaidDate = PaidDate;
    }

    public String getPayStat() {
        return PayStat;
    }

    public void setPayStat(String PayStat) {
        this.PayStat = PayStat;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String CustID) {
        this.CustID = CustID;
    }

    public String getProfPictName() {
        return ProfPictName;
    }

    public void setProfPictName(String ProfPictName) {
        this.ProfPictName = ProfPictName;
    }

    public String getGstNo() {
        return GstNo;
    }

    public void setGstNo(String GstNo) {
        this.GstNo = GstNo;
    }

    public String getFBAPan() {
        return FBAPan;
    }

    public void setFBAPan(String FBAPan) {
        this.FBAPan = FBAPan;
    }

//    public String getIsFoc() {
//        return IsFoc;
//    }
//
//    public void setIsFoc(String IsFoc) {
//        this.IsFoc = IsFoc;
//    }

    public String getIsBlocked() {
        return IsBlocked;
    }

    public void setIsBlocked(String IsBlocked) {
        this.IsBlocked = IsBlocked;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String CityId) {
        this.CityId = CityId;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getSalescode() {
        return salescode;
    }

    public void setSalescode(String salescode) {
        this.salescode = salescode;
    }

    public int getFBARID() {
        return FBARID;
    }

    public void setFBARID(int FBARID) {
        this.FBARID = FBARID;
    }

    public String getPOSPName() {
        return POSPName;
    }

    public void setPOSPName(String POSPName) {
        this.POSPName = POSPName;
    }

    public String getPosp_PAN() {
        return Posp_PAN;
    }

    public void setPosp_PAN(String Posp_PAN) {
        this.Posp_PAN = Posp_PAN;
    }

    public String getPosp_Aadhaar() {
        return Posp_Aadhaar;
    }

    public void setPosp_Aadhaar(String Posp_Aadhaar) {
        this.Posp_Aadhaar = Posp_Aadhaar;
    }

    public String getPosp_BankAcNo() {
        return Posp_BankAcNo;
    }

    public void setPosp_BankAcNo(String Posp_BankAcNo) {
        this.Posp_BankAcNo = Posp_BankAcNo;
    }

    public String getPosp_Account_Type() {
        return Posp_Account_Type;
    }

    public void setPosp_Account_Type(String Posp_Account_Type) {
        this.Posp_Account_Type = Posp_Account_Type;
    }

    public String getPosp_BankName() {
        return Posp_BankName;
    }

    public void setPosp_BankName(String Posp_BankName) {
        this.Posp_BankName = Posp_BankName;
    }

    public String getPosp_BankBranch() {
        return Posp_BankBranch;
    }

    public void setPosp_BankBranch(String Posp_BankBranch) {
        this.Posp_BankBranch = Posp_BankBranch;
    }

    public String getPosp_IFSC() {
        return Posp_IFSC;
    }

    public void setPosp_IFSC(String Posp_IFSC) {
        this.Posp_IFSC = Posp_IFSC;
    }

    public String getPosp_MICR() {
        return Posp_MICR;
    }

    public void setPosp_MICR(String Posp_MICR) {
        this.Posp_MICR = Posp_MICR;
    }

    public String getPOSPStat() {
        return POSPStat;
    }

    public void setPOSPStat(String POSPStat) {
        this.POSPStat = POSPStat;
    }

    public String getPOSPCode() {
        return POSPCode;
    }

    public void setPOSPCode(String POSPCode) {
        this.POSPCode = POSPCode;
    }

    public String getPOSPFrom() {
        return POSPFrom;
    }

    public void setPOSPFrom(String POSPFrom) {
        this.POSPFrom = POSPFrom;
    }

    public String getLoanName() {
        return LoanName;
    }

    public void setLoanName(String LoanName) {
        this.LoanName = LoanName;
    }

    public String getLoan_PAN() {
        return Loan_PAN;
    }

    public void setLoan_PAN(String Loan_PAN) {
        this.Loan_PAN = Loan_PAN;
    }

    public String getLoan_Aadhaar() {
        return Loan_Aadhaar;
    }

    public void setLoan_Aadhaar(String Loan_Aadhaar) {
        this.Loan_Aadhaar = Loan_Aadhaar;
    }

    public String getLoan_BankAcNo() {
        return Loan_BankAcNo;
    }

    public void setLoan_BankAcNo(String Loan_BankAcNo) {
        this.Loan_BankAcNo = Loan_BankAcNo;
    }

    public String getLoan_Account_Type() {
        return Loan_Account_Type;
    }

    public void setLoan_Account_Type(String Loan_Account_Type) {
        this.Loan_Account_Type = Loan_Account_Type;
    }

    public String getLoan_BankName() {
        return Loan_BankName;
    }

    public void setLoan_BankName(String Loan_BankName) {
        this.Loan_BankName = Loan_BankName;
    }

    public String getLoan_BankBranch() {
        return Loan_BankBranch;
    }

    public void setLoan_BankBranch(String Loan_BankBranch) {
        this.Loan_BankBranch = Loan_BankBranch;
    }

    public String getLoanStat() {
        return LoanStat;
    }

    public void setLoanStat(String LoanStat) {
        this.LoanStat = LoanStat;
    }

    public String getLoan_IFSC() {
        return Loan_IFSC;
    }

    public void setLoan_IFSC(String Loan_IFSC) {
        this.Loan_IFSC = Loan_IFSC;
    }

    public String getLoan_MICR() {
        return Loan_MICR;
    }

    public void setLoan_MICR(String Loan_MICR) {
        this.Loan_MICR = Loan_MICR;
    }

    public String getOtheName() {
        return OtheName;
    }

    public void setOtheName(String OtheName) {
        this.OtheName = OtheName;
    }

    public String getOther_PAN() {
        return Other_PAN;
    }

    public void setOther_PAN(String Other_PAN) {
        this.Other_PAN = Other_PAN;
    }

    public String getOther_Aadhaar() {
        return Other_Aadhaar;
    }

    public void setOther_Aadhaar(String Other_Aadhaar) {
        this.Other_Aadhaar = Other_Aadhaar;
    }

    public String getOther_BankAcNo() {
        return Other_BankAcNo;
    }

    public void setOther_BankAcNo(String Other_BankAcNo) {
        this.Other_BankAcNo = Other_BankAcNo;
    }

    public String getOther_Account_Type() {
        return Other_Account_Type;
    }

    public void setOther_Account_Type(String Other_Account_Type) {
        this.Other_Account_Type = Other_Account_Type;
    }

    public String getOther_BankName() {
        return Other_BankName;
    }

    public void setOther_BankName(String Other_BankName) {
        this.Other_BankName = Other_BankName;
    }

    public String getOther_BankBranch() {
        return Other_BankBranch;
    }

    public void setOther_BankBranch(String Other_BankBranch) {
        this.Other_BankBranch = Other_BankBranch;
    }

    public String getOther_IFSC() {
        return Other_IFSC;
    }

    public void setOther_IFSC(String Other_IFSC) {
        this.Other_IFSC = Other_IFSC;
    }

    public String getOther_MICR() {
        return Other_MICR;
    }

    public void setOther_MICR(String Other_MICR) {
        this.Other_MICR = Other_MICR;
    }

    public String getPOSPNo() {
        return POSPNo;
    }

    public void setPOSPNo(String POSPNo) {
        this.POSPNo = POSPNo;
    }

    public String getLoanID() {
        return LoanID;
    }

    public void setLoanID(String LoanID) {
        this.LoanID = LoanID;
    }

    public String getPosp_ServiceTaxNo() {
        return Posp_ServiceTaxNo;
    }

    public void setPosp_ServiceTaxNo(String Posp_ServiceTaxNo) {
        this.Posp_ServiceTaxNo = Posp_ServiceTaxNo;
    }

    public String getProdID() {
        return ProdID;
    }

    public void setProdID(String ProdID) {
        this.ProdID = ProdID;
    }

    public String getSuppAgenId() {
        return SuppAgenId;
    }

    public void setSuppAgenId(String SuppAgenId) {
        this.SuppAgenId = SuppAgenId;
    }

    public String getPosp_DOB() {
        return Posp_DOB;
    }

    public void setPosp_DOB(String Posp_DOB) {
        this.Posp_DOB = Posp_DOB;
    }

    public String getPosp_Gender() {
        return Posp_Gender;
    }

    public void setPosp_Gender(String Posp_Gender) {
        this.Posp_Gender = Posp_Gender;
    }

    public String getPosp_Mobile1() {
        return Posp_Mobile1;
    }

    public void setPosp_Mobile1(String Posp_Mobile1) {
        this.Posp_Mobile1 = Posp_Mobile1;
    }

    public String getPosp_Mobile2() {
        return Posp_Mobile2;
    }

    public void setPosp_Mobile2(String Posp_Mobile2) {
        this.Posp_Mobile2 = Posp_Mobile2;
    }

    public String getPosp_Email() {
        return Posp_Email;
    }

    public void setPosp_Email(String Posp_Email) {
        this.Posp_Email = Posp_Email;
    }

    public String getPosp_Address1() {
        return Posp_Address1;
    }

    public void setPosp_Address1(String Posp_Address1) {
        this.Posp_Address1 = Posp_Address1;
    }

    public String getPosp_Address2() {
        return Posp_Address2;
    }

    public void setPosp_Address2(String Posp_Address2) {
        this.Posp_Address2 = Posp_Address2;
    }

    public String getPosp_Address3() {
        return Posp_Address3;
    }

    public void setPosp_Address3(String Posp_Address3) {
        this.Posp_Address3 = Posp_Address3;
    }

    public String getPosp_PinCode() {
        return Posp_PinCode;
    }

    public void setPosp_PinCode(String Posp_PinCode) {
        this.Posp_PinCode = Posp_PinCode;
    }

    public String getPosp_City() {
        return Posp_City;
    }

    public void setPosp_City(String Posp_City) {
        this.Posp_City = Posp_City;
    }

    public String getPosp_StatID() {
        return Posp_StatID;
    }

    public void setPosp_StatID(String Posp_StatID) {
        this.Posp_StatID = Posp_StatID;
    }

    public String getPosp_ChanPartCode() {
        return Posp_ChanPartCode;
    }

    public void setPosp_ChanPartCode(String Posp_ChanPartCode) {
        this.Posp_ChanPartCode = Posp_ChanPartCode;
    }

    public String getPOSPRegiDate() {
        return POSPRegiDate;
    }

    public void setPOSPRegiDate(String POSPRegiDate) {
        this.POSPRegiDate = POSPRegiDate;
    }

    public String getDisplayEmail() {
        return DisplayEmail;
    }

    public void setDisplayEmail(String DisplayEmail) {
        this.DisplayEmail = DisplayEmail;
    }

    public String getDisplayPhoneNo() {
        return DisplayPhoneNo;
    }

    public void setDisplayPhoneNo(String DisplayPhoneNo) {
        this.DisplayPhoneNo = DisplayPhoneNo;
    }

    public String getDisplayDesignation() {
        return DisplayDesignation;
    }

    public void setDisplayDesignation(String DisplayDesignation) {
        this.DisplayDesignation = DisplayDesignation;
    }

    public RealmList<DocAvailableEntity> getDoc_available() {
        return doc_available;
    }

    public void setDoc_available(RealmList<DocAvailableEntity> doc_available) {
        this.doc_available = doc_available;
    }


}