package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoginResponseEntity extends RealmObject {
    /**
     * FBAId : 3
     * FullName : Jitendra Patel
     * UserName : finmartlive@gmail.com
     * EmailID : finmartlive@gmail.com
     * MobiNumb1 : 8286115005
     * LiveURL : 1
     * LastloginDate : 2018-01-22T13:34:43.000Z
     * Validfrom : 2099-03-31T00:00:00.000Z
     * RewardPoint :
     * IsFirstLogin : 0
     * IsDemo : 0
     * FSMFullname : Jitendra Patel
     * FSMEmail : finmartlive@gmail.com
     * FSMMobile : 8286115005
     * FBACode :
     * Designation : YOUR COMPLETE FINANCIAL ADVISOR
     * ProfPictName : null
     * FSMDesig : Your FinMart sales manager
     * RegMACAddr : 70:0b:c0:eb:79:89
     * SuppMobiNumb :
     * SuppEmailId :
     * FBAStatus : R
     * POSPStatus : 6
     * UserType : FBA
     * SuppAgenId : 1682
     * EditEmailId : finmartlive@gmail.com
     * EditMobiNumb : 8286115005
     * EditDesig : YOUR COMPLETE FINANCIAL ADVISOR
     * EditProfPictName :
     * LoanId : 23335
     * PayStatus : S
     * CustID : 680123
     * PaymentUrl :
     * IsFoc : 0
     * POSPName : NIDHI SINGH
     * POSEmail : xyz@gmail.com
     * POSPMobile : 0909090909
     * SuccessStatus : 1
     * POSPInfo : NIDHI SINGH~0909090909~xyz@gmail.com
     * FSM : Jitendra Patel~finmartlive@gmail.com~8286115005~Your FinMart sales manager
     */
    @PrimaryKey
    private int FBAId;
    private String FullName;
    private String UserName;
    private String EmailID;
    private String MobiNumb1;
    private String LiveURL;
    private String LastloginDate;
    private String Validfrom;
    private String RewardPoint;
    private int IsFirstLogin;
    private int IsDemo;
    private String FSMFullname;
    private String FSMEmail;
    private String FSMMobile;
    private String FBACode;
    private String Designation;
    private String ProfPictName;
    private String FSMDesig;
    private String RegMACAddr;
    private String SuppMobiNumb;
    private String SuppEmailId;
    private String FBAStatus;
    private String POSPStatus;
    private String UserType;
    private String SuppAgenId;
    private String EditEmailId;
    private String EditMobiNumb;
    private String EditDesig;
    private String EditProfPictName;
    private String LoanId;
    private String PayStatus;
    private String CustID;
    private String PaymentUrl;
    private String IsFoc;
    private String POSPName;
    private String POSEmail;
    private String POSPMobile;
    private String SuccessStatus;
    private String POSPInfo;
    private String FSM;
    /**
     * RewardPoint : null
     * FSMFullname : null
     * FSMEmail : null
     * FSMMobile : null
     * FBACode : null
     * ProfPictName : null
     * FSMDesig : null
     * SuppMobiNumb : null
     * SuppEmailId : null
     * SuppAgenId : null
     * EditProfPictName : null
     * PaymentUrl : null
     * rm_id : 0
     * referraid :
     * POSPNo :
     * POSPProfileUrl : http://api.magicfinmart.com/uploads/1519725969630.jpg
     * FBAProfileUrl : http://api.magicfinmart.com/uploads/1519728916249.jpg
     */

    private int rm_id;
    private String referraid;
    private String POSPNo;

    public String getReferer_code() {
        return referer_code;
    }

    public void setReferer_code(String referer_code) {
        this.referer_code = referer_code;
    }

    private String POSPProfileUrl;
    private String FBAProfileUrl;
    private String referer_code;

    public int getFBAId() {
        return FBAId;
    }

    public void setFBAId(int FBAId) {
        this.FBAId = FBAId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public String getMobiNumb1() {
        return MobiNumb1;
    }

    public void setMobiNumb1(String MobiNumb1) {
        this.MobiNumb1 = MobiNumb1;
    }

    public String getLiveURL() {
        return LiveURL;
    }

    public void setLiveURL(String LiveURL) {
        this.LiveURL = LiveURL;
    }

    public String getLastloginDate() {
        return LastloginDate;
    }

    public void setLastloginDate(String LastloginDate) {
        this.LastloginDate = LastloginDate;
    }

    public String getValidfrom() {
        return Validfrom;
    }

    public void setValidfrom(String Validfrom) {
        this.Validfrom = Validfrom;
    }

    public String getRewardPoint() {
        return RewardPoint;
    }

    public void setRewardPoint(String RewardPoint) {
        this.RewardPoint = RewardPoint;
    }

    public int getIsFirstLogin() {
        return IsFirstLogin;
    }

    public void setIsFirstLogin(int IsFirstLogin) {
        this.IsFirstLogin = IsFirstLogin;
    }

    public int getIsDemo() {
        return IsDemo;
    }

    public void setIsDemo(int IsDemo) {
        this.IsDemo = IsDemo;
    }

    public String getFSMFullname() {
        return FSMFullname;
    }

    public void setFSMFullname(String FSMFullname) {
        this.FSMFullname = FSMFullname;
    }

    public String getFSMEmail() {
        return FSMEmail;
    }

    public void setFSMEmail(String FSMEmail) {
        this.FSMEmail = FSMEmail;
    }

    public String getFSMMobile() {
        return FSMMobile;
    }

    public void setFSMMobile(String FSMMobile) {
        this.FSMMobile = FSMMobile;
    }

    public String getFBACode() {
        return FBACode;
    }

    public void setFBACode(String FBACode) {
        this.FBACode = FBACode;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getProfPictName() {
        return ProfPictName;
    }

    public void setProfPictName(String ProfPictName) {
        this.ProfPictName = ProfPictName;
    }

    public String getFSMDesig() {
        return FSMDesig;
    }

    public void setFSMDesig(String FSMDesig) {
        this.FSMDesig = FSMDesig;
    }

    public String getRegMACAddr() {
        return RegMACAddr;
    }

    public void setRegMACAddr(String RegMACAddr) {
        this.RegMACAddr = RegMACAddr;
    }

    public String getSuppMobiNumb() {
        return SuppMobiNumb;
    }

    public void setSuppMobiNumb(String SuppMobiNumb) {
        this.SuppMobiNumb = SuppMobiNumb;
    }

    public String getSuppEmailId() {
        return SuppEmailId;
    }

    public void setSuppEmailId(String SuppEmailId) {
        this.SuppEmailId = SuppEmailId;
    }

    public String getFBAStatus() {
        return FBAStatus;
    }

    public void setFBAStatus(String FBAStatus) {
        this.FBAStatus = FBAStatus;
    }

    public String getPOSPStatus() {
        return POSPStatus;
    }

    public void setPOSPStatus(String POSPStatus) {
        this.POSPStatus = POSPStatus;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public String getSuppAgenId() {
        return SuppAgenId;
    }

    public void setSuppAgenId(String SuppAgenId) {
        this.SuppAgenId = SuppAgenId;
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

    public String getEditDesig() {
        return EditDesig;
    }

    public void setEditDesig(String EditDesig) {
        this.EditDesig = EditDesig;
    }

    public String getEditProfPictName() {
        return EditProfPictName;
    }

    public void setEditProfPictName(String EditProfPictName) {
        this.EditProfPictName = EditProfPictName;
    }

    public String getLoanId() {
        return LoanId;
    }

    public void setLoanId(String LoanId) {
        this.LoanId = LoanId;
    }

    public String getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(String PayStatus) {
        this.PayStatus = PayStatus;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String CustID) {
        this.CustID = CustID;
    }

    public String getPaymentUrl() {
        return PaymentUrl;
    }

    public void setPaymentUrl(String PaymentUrl) {
        this.PaymentUrl = PaymentUrl;
    }

    public String getIsFoc() {
        return IsFoc;
    }

    public void setIsFoc(String IsFoc) {
        this.IsFoc = IsFoc;
    }

    public String getPOSPName() {
        return POSPName;
    }

    public void setPOSPName(String POSPName) {
        this.POSPName = POSPName;
    }

    public String getPOSEmail() {
        return POSEmail;
    }

    public void setPOSEmail(String POSEmail) {
        this.POSEmail = POSEmail;
    }

    public String getPOSPMobile() {
        return POSPMobile;
    }

    public void setPOSPMobile(String POSPMobile) {
        this.POSPMobile = POSPMobile;
    }

    public String getSuccessStatus() {
        return SuccessStatus;
    }

    public void setSuccessStatus(String SuccessStatus) {
        this.SuccessStatus = SuccessStatus;
    }

    public String getPOSPInfo() {
        return POSPInfo;
    }

    public void setPOSPInfo(String POSPInfo) {
        this.POSPInfo = POSPInfo;
    }

    public String getFSM() {
        return FSM;
    }

    public void setFSM(String FSM) {
        this.FSM = FSM;
    }

    public int getRm_id() {
        return rm_id;
    }

    public void setRm_id(int rm_id) {
        this.rm_id = rm_id;
    }

    public String getReferraid() {
        return referraid;
    }

    public void setReferraid(String referraid) {
        this.referraid = referraid;
    }

    public String getPOSPNo() {
        return POSPNo;
    }

    public void setPOSPNo(String POSPNo) {
        this.POSPNo = POSPNo;
    }

    public String getPOSPProfileUrl() {
        return POSPProfileUrl;
    }

    public void setPOSPProfileUrl(String POSPProfileUrl) {
        this.POSPProfileUrl = POSPProfileUrl;
    }

    public String getFBAProfileUrl() {
        return FBAProfileUrl;
    }

    public void setFBAProfileUrl(String FBAProfileUrl) {
        this.FBAProfileUrl = FBAProfileUrl;
    }
}