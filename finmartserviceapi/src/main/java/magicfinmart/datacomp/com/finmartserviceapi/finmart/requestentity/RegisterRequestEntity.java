package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

/**
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public class RegisterRequestEntity {

    /**
     * Link : null
     * ParentId : 3
     */

    private String ParentId;

    public String getReferedby_code() {
        return referedby_code;
    }

    public void setReferedby_code(String referedby_code) {
        this.referedby_code = referedby_code;
    }

    /**
     * FBAID : 0
     * FBALiveID : 0
     * FirstName : Interstallar
     * LastName : DATACOMP
     * DOB : 18-Nov-1986
     * Gender : M
     * Mobile_1 : 9702943935
     * Mobile_2 :
     * EmailId : jfff59@fgggh.vbm
     * CustID : 0
     * FBAPan :
     * GSTNumb :
     * Address_1 :
     * Address_2 :
     * Address_3 :
     * PinCode : 421201
     * City : THANE
     * StateID : 21
     * State : MAHARASHTRA
     * IsLic : 1
     * LIC_Comp : BAJAJ
     * LIC_Comp_ID : 2
     * IsGic : 1
     * GIC_Comp : ICICI
     * GIC_Comp_ID : 8
     * IsHealth : 1
     * Health_Comp : EXP-CREDIT
     * Health_Comp_ID : 15
     * MF : 1
     * MF_Comp :
     * Stock : 1
     * Stock_Comp :
     * Postal : 1
     * Postal_Comp :
     * Bonds : 1
     * Bonds_Comp :
     * Posp_FirstName :
     * Posp_LastName :
     * Posp_PAN :
     * Posp_Aadhaar :
     * Posp_BankAcNo :
     * Posp_Account_Type :
     * Posp_IFSC :
     * Posp_MICR :
     * Posp_BankName :
     * Posp_BankID : 0
     * Posp_BankBranch :
     * Posp_BankCity :
     * Posp_ServiceTaxNo :
     * Posp_DOB :
     * Posp_Gender :
     * Posp_Mobile1 :
     * Posp_Mobile2 :
     * Posp_Email :
     * Posp_Address1 :
     * Posp_Address2 :
     * Posp_Address3 :
     * Posp_PinCode :
     * Posp_City :
     * Posp_StatID :
     * Posp_ChanPartCode :
     * Loan_FirstName :
     * Loan_LastName :
     * Loan_PAN :
     * Loan_Aadhaar :
     * Loan_BankAcNo :
     * Loan_Account_Type : SAVINGS
     * Loan_IFSC :
     * Loan_MICR :
     * Loan_BankName :
     * Loan_BankID : 0
     * Loan_BankBranch :
     * Loan_BankCity :
     * Other_FirstName :
     * Other_LastName :
     * Other_PAN :
     * Other_Aadhaar :
     * Other_BankAcNo :
     * Other_Account_Type :
     * Other_IFSC :
     * Other_MICR :
     * Other_BankName :
     * Other_BankID : 0
     * Other_BankBranch :
     * Other_BankCity :
     * Type :
     * StatActi :
     * FBAStat : R
     * SMID : 0
     * <p>
     * SM_Name :
     * POSPID : 0
     * BrokID : 0
     * IsFOC :
     * password :
     */

    String referedby_code;
    private int FBAID;
    private int FBALiveID;

    private String FBA_Designation;
    private String FirstName;
    private String LastName;
    private String DOB;
    private String Gender;
    private String Mobile_1;
    private String Mobile_2;
    private String EmailId;
    private int CustID;
    private String FBAPan;
    private String GSTNumb;
    private String Address_1;
    private String Address_2;
    private String Address_3;
    private String PinCode;
    private String City;
    private String StateID;
    private String State;
    private String IsLic;
    private String LIC_Comp;
    private String LIC_Comp_ID;
    private String IsGic;
    private String GIC_Comp;
    private String GIC_Comp_ID;
    private String IsHealth;
    private String Health_Comp;
    private String Health_Comp_ID;
    private String MF;
    private String MF_Comp;
    private String Stock;
    private String Stock_Comp;
    private String Postal;
    private String Postal_Comp;
    private String Bonds;
    private String Bonds_Comp;
    private String Posp_FirstName;
    private String Posp_LastName;
    private String Posp_PAN;
    private String Posp_Aadhaar;
    private String Posp_BankAcNo;
    private String Posp_Account_Type;
    private String Posp_IFSC;
    private String Posp_MICR;
    private String Posp_BankName;
    private int Posp_BankID;
    private String Posp_BankBranch;
    private String Posp_BankCity;
    private String Posp_ServiceTaxNo;
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
    private String Loan_FirstName;
    private String Loan_LastName;
    private String Loan_PAN;
    private String Loan_Aadhaar;
    private String Loan_BankAcNo;
    private String Loan_Account_Type;
    private String Loan_IFSC;
    private String Loan_MICR;
    private String Loan_BankName;
    private int Loan_BankID;
    private String Loan_BankBranch;
    private String Loan_BankCity;
    private String Other_FirstName;
    private String Other_LastName;
    private String Other_PAN;
    private String Other_Aadhaar;
    private String Other_BankAcNo;
    private String Other_Account_Type;
    private String Other_IFSC;
    private String Other_MICR;
    private String Other_BankName;
    private int Other_BankID;
    private String Other_BankBranch;
    private String Other_BankCity;
    private String Type;
    private String StatActi;
    private String FBAStat;
    private int SMID;
    private String SM_Name;
    private int POSPID;
    private int BrokID;
    private String IsFOC;
    private String password;
    private String DisplayEmail;
    private String DisplayPhoneNo;
    private String DisplayDesignation;
    /**
     * Link :
     */

    private String Link;

    //nilesh
    private String VersionCode;
    private String AppSource;

    public String getAppSource() {
        return AppSource;
    }

    public void setAppSource(String appSource) {
        AppSource = appSource;
    }

    public RegisterRequestEntity() {
        this.FBAID = 0;
        this.FBALiveID = 0;
        FBA_Designation = "";
        FirstName = "";
        LastName = "";
        this.DOB = "";
        Gender = "";
        Mobile_1 = "";
        Mobile_2 = "";
        EmailId = "";
        CustID = 0;
        this.FBAPan = "";
        this.GSTNumb = "";
        Address_1 = "";
        Address_2 = "";
        Address_3 = "";
        PinCode = "";
        City = "";
        StateID = "";
        State = "";
        IsLic = "";
        this.LIC_Comp = "";
        this.LIC_Comp_ID = "";
        IsGic = "";
        this.GIC_Comp = "";
        this.GIC_Comp_ID = "";
        IsHealth = "";
        Health_Comp = "";
        Health_Comp_ID = "";
        this.MF = "";
        this.MF_Comp = "";
        Stock = "";
        Stock_Comp = "";
        Postal = "";
        Postal_Comp = "";
        Bonds = "";
        Bonds_Comp = "";
        Posp_FirstName = "";
        Posp_LastName = "";
        Posp_PAN = "";
        Posp_Aadhaar = "";
        Posp_BankAcNo = "";
        Posp_Account_Type = "";
        Posp_IFSC = "";
        Posp_MICR = "";
        Posp_BankName = "";
        Posp_BankID = 0;
        Posp_BankBranch = "";
        Posp_BankCity = "";
        Posp_ServiceTaxNo = "";
        Posp_DOB = "";
        Posp_Gender = "";
        Posp_Mobile1 = "";
        Posp_Mobile2 = "";
        Posp_Email = "";
        Posp_Address1 = "";
        Posp_Address2 = "";
        Posp_Address3 = "";
        Posp_PinCode = "";
        Posp_City = "";
        Posp_StatID = "";
        Posp_ChanPartCode = "";
        Loan_FirstName = "";
        Loan_LastName = "";
        Loan_PAN = "";
        Loan_Aadhaar = "";
        Loan_BankAcNo = "";
        Loan_Account_Type = "";
        Loan_IFSC = "";
        Loan_MICR = "";
        Loan_BankName = "";
        Loan_BankID = 0;
        Loan_BankBranch = "";
        Loan_BankCity = "";
        Other_FirstName = "";
        Other_LastName = "";
        Other_PAN = "";
        Other_Aadhaar = "";
        Other_BankAcNo = "";
        Other_Account_Type = "";
        Other_IFSC = "";
        Other_MICR = "";
        Other_BankName = "";
        Other_BankID = 0;
        Other_BankBranch = "";
        Other_BankCity = "";
        Type = "";
        StatActi = "";
        this.FBAStat = "";
        this.SMID = 0;
        this.SM_Name = "";
        this.POSPID = 0;
        BrokID = 0;
        IsFOC = "";
        this.password = "";
        DisplayEmail = "";
        DisplayPhoneNo = "";
        DisplayDesignation = "";
        VersionCode = "";
        ParentId="0";
    }


    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String versionCode) {
        VersionCode = versionCode;
    }


    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }

    public int getFBALiveID() {
        return FBALiveID;
    }

    public void setFBALiveID(int FBALiveID) {
        this.FBALiveID = FBALiveID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
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

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public int getCustID() {
        return CustID;
    }

    public void setCustID(int CustID) {
        this.CustID = CustID;
    }

    public String getFBAPan() {
        return FBAPan;
    }

    public void setFBAPan(String FBAPan) {
        this.FBAPan = FBAPan;
    }

    public String getGSTNumb() {
        return GSTNumb;
    }

    public void setGSTNumb(String GSTNumb) {
        this.GSTNumb = GSTNumb;
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

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getIsLic() {
        return IsLic;
    }

    public void setIsLic(String IsLic) {
        this.IsLic = IsLic;
    }

    public String getLIC_Comp() {
        return LIC_Comp;
    }

    public void setLIC_Comp(String LIC_Comp) {
        this.LIC_Comp = LIC_Comp;
    }

    public String getLIC_Comp_ID() {
        return LIC_Comp_ID;
    }

    public void setLIC_Comp_ID(String LIC_Comp_ID) {
        this.LIC_Comp_ID = LIC_Comp_ID;
    }

    public String getIsGic() {
        return IsGic;
    }

    public void setIsGic(String IsGic) {
        this.IsGic = IsGic;
    }

    public String getGIC_Comp() {
        return GIC_Comp;
    }

    public void setGIC_Comp(String GIC_Comp) {
        this.GIC_Comp = GIC_Comp;
    }

    public String getGIC_Comp_ID() {
        return GIC_Comp_ID;
    }

    public void setGIC_Comp_ID(String GIC_Comp_ID) {
        this.GIC_Comp_ID = GIC_Comp_ID;
    }

    public String getIsHealth() {
        return IsHealth;
    }

    public void setIsHealth(String IsHealth) {
        this.IsHealth = IsHealth;
    }

    public String getHealth_Comp() {
        return Health_Comp;
    }

    public void setHealth_Comp(String Health_Comp) {
        this.Health_Comp = Health_Comp;
    }

    public String getHealth_Comp_ID() {
        return Health_Comp_ID;
    }

    public void setHealth_Comp_ID(String Health_Comp_ID) {
        this.Health_Comp_ID = Health_Comp_ID;
    }

    public String getMF() {
        return MF;
    }

    public void setMF(String MF) {
        this.MF = MF;
    }

    public String getMF_Comp() {
        return MF_Comp;
    }

    public void setMF_Comp(String MF_Comp) {
        this.MF_Comp = MF_Comp;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String Stock) {
        this.Stock = Stock;
    }

    public String getStock_Comp() {
        return Stock_Comp;
    }

    public void setStock_Comp(String Stock_Comp) {
        this.Stock_Comp = Stock_Comp;
    }

    public String getPostal() {
        return Postal;
    }

    public void setPostal(String Postal) {
        this.Postal = Postal;
    }

    public String getPostal_Comp() {
        return Postal_Comp;
    }

    public void setPostal_Comp(String Postal_Comp) {
        this.Postal_Comp = Postal_Comp;
    }

    public String getBonds() {
        return Bonds;
    }

    public void setBonds(String Bonds) {
        this.Bonds = Bonds;
    }

    public String getBonds_Comp() {
        return Bonds_Comp;
    }

    public void setBonds_Comp(String Bonds_Comp) {
        this.Bonds_Comp = Bonds_Comp;
    }

    public String getPosp_FirstName() {
        return Posp_FirstName;
    }

    public void setPosp_FirstName(String Posp_FirstName) {
        this.Posp_FirstName = Posp_FirstName;
    }

    public String getPosp_LastName() {
        return Posp_LastName;
    }

    public void setPosp_LastName(String Posp_LastName) {
        this.Posp_LastName = Posp_LastName;
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

    public String getPosp_BankName() {
        return Posp_BankName;
    }

    public void setPosp_BankName(String Posp_BankName) {
        this.Posp_BankName = Posp_BankName;
    }

    public int getPosp_BankID() {
        return Posp_BankID;
    }

    public void setPosp_BankID(int Posp_BankID) {
        this.Posp_BankID = Posp_BankID;
    }

    public String getPosp_BankBranch() {
        return Posp_BankBranch;
    }

    public void setPosp_BankBranch(String Posp_BankBranch) {
        this.Posp_BankBranch = Posp_BankBranch;
    }

    public String getPosp_BankCity() {
        return Posp_BankCity;
    }

    public void setPosp_BankCity(String Posp_BankCity) {
        this.Posp_BankCity = Posp_BankCity;
    }

    public String getPosp_ServiceTaxNo() {
        return Posp_ServiceTaxNo;
    }

    public void setPosp_ServiceTaxNo(String Posp_ServiceTaxNo) {
        this.Posp_ServiceTaxNo = Posp_ServiceTaxNo;
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

    public String getLoan_FirstName() {
        return Loan_FirstName;
    }

    public void setLoan_FirstName(String Loan_FirstName) {
        this.Loan_FirstName = Loan_FirstName;
    }

    public String getLoan_LastName() {
        return Loan_LastName;
    }

    public void setLoan_LastName(String Loan_LastName) {
        this.Loan_LastName = Loan_LastName;
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

    public String getLoan_BankName() {
        return Loan_BankName;
    }

    public void setLoan_BankName(String Loan_BankName) {
        this.Loan_BankName = Loan_BankName;
    }

    public int getLoan_BankID() {
        return Loan_BankID;
    }

    public void setLoan_BankID(int Loan_BankID) {
        this.Loan_BankID = Loan_BankID;
    }

    public String getLoan_BankBranch() {
        return Loan_BankBranch;
    }

    public void setLoan_BankBranch(String Loan_BankBranch) {
        this.Loan_BankBranch = Loan_BankBranch;
    }

    public String getLoan_BankCity() {
        return Loan_BankCity;
    }

    public void setLoan_BankCity(String Loan_BankCity) {
        this.Loan_BankCity = Loan_BankCity;
    }

    public String getOther_FirstName() {
        return Other_FirstName;
    }

    public void setOther_FirstName(String Other_FirstName) {
        this.Other_FirstName = Other_FirstName;
    }

    public String getOther_LastName() {
        return Other_LastName;
    }

    public void setOther_LastName(String Other_LastName) {
        this.Other_LastName = Other_LastName;
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

    public String getOther_BankName() {
        return Other_BankName;
    }

    public void setOther_BankName(String Other_BankName) {
        this.Other_BankName = Other_BankName;
    }

    public int getOther_BankID() {
        return Other_BankID;
    }

    public void setOther_BankID(int Other_BankID) {
        this.Other_BankID = Other_BankID;
    }

    public String getOther_BankBranch() {
        return Other_BankBranch;
    }

    public void setOther_BankBranch(String Other_BankBranch) {
        this.Other_BankBranch = Other_BankBranch;
    }

    public String getOther_BankCity() {
        return Other_BankCity;
    }

    public void setOther_BankCity(String Other_BankCity) {
        this.Other_BankCity = Other_BankCity;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getStatActi() {
        return StatActi;
    }

    public void setStatActi(String StatActi) {
        this.StatActi = StatActi;
    }

    public String getFBAStat() {
        return FBAStat;
    }

    public void setFBAStat(String FBAStat) {
        this.FBAStat = FBAStat;
    }

    public int getSMID() {
        return SMID;
    }

    public void setSMID(int SMID) {
        this.SMID = SMID;
    }

    public String getSM_Name() {
        return SM_Name;
    }

    public void setSM_Name(String SM_Name) {
        this.SM_Name = SM_Name;
    }

    public int getPOSPID() {
        return POSPID;
    }

    public void setPOSPID(int POSPID) {
        this.POSPID = POSPID;
    }

    public int getBrokID() {
        return BrokID;
    }

    public void setBrokID(int BrokID) {
        this.BrokID = BrokID;
    }

    public String getIsFOC() {
        return IsFOC;
    }

    public void setIsFOC(String IsFOC) {
        this.IsFOC = IsFOC;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFBA_Designation() {
        return FBA_Designation;
    }

    public void setFBA_Designation(String FBA_Designation) {
        this.FBA_Designation = FBA_Designation;
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

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String ParentId) {
        this.ParentId = ParentId;
    }
}
