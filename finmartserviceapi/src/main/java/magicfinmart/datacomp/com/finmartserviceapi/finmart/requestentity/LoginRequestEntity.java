package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

/**
 * Created by Rajeev Ranjan on 25/01/2018.
 */

public class LoginRequestEntity {

    /**
     * UserName : finmartlive@gmail.com
     * Password : 123456781
     * OldPassword :
     * FBAId : 0
     * DeviceId : ffffffff-e77a-774f-162a-16fe162a16fe
     * DeviceOS :
     * DeviceName :
     * IpAdd : 02:00:00:00:00:00
     * LastLog :
     * EmailId :
     * TokenId :
     * MobileNo :
     * UserType :
     * UserId : 0
     * VersionNo :
     * AppID :
     * AppUSERID :
     * AppPASSWORD :
     */

    private String UserName;
    private String Password;
    private String OldPassword;
    private int FBAId;
    private String DeviceId;
    private String DeviceOS;
    private String DeviceName;
    private String IpAdd;

    public LoginRequestEntity() {
        UserName = "";
        Password = "";
        OldPassword = "";
        this.FBAId = 0;
        DeviceId = "";
        DeviceOS = "";
        DeviceName = "";
        IpAdd = "";
        LastLog = "";
        EmailId = "";
        TokenId = "";
        MobileNo = "";
        UserType = "";
        UserId = 0;
        VersionNo = "";
        AppID = "";
        AppUSERID = "";
        AppPASSWORD = "";
    }

    private String LastLog;
    private String EmailId;
    private String TokenId;
    private String MobileNo;
    private String UserType;
    private int UserId;
    private String VersionNo;
    private String AppID;
    private String AppUSERID;
    private String AppPASSWORD;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String OldPassword) {
        this.OldPassword = OldPassword;
    }

    public int getFBAId() {
        return FBAId;
    }

    public void setFBAId(int FBAId) {
        this.FBAId = FBAId;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.DeviceId = DeviceId;
    }

    public String getDeviceOS() {
        return DeviceOS;
    }

    public void setDeviceOS(String DeviceOS) {
        this.DeviceOS = DeviceOS;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String DeviceName) {
        this.DeviceName = DeviceName;
    }

    public String getIpAdd() {
        return IpAdd;
    }

    public void setIpAdd(String IpAdd) {
        this.IpAdd = IpAdd;
    }

    public String getLastLog() {
        return LastLog;
    }

    public void setLastLog(String LastLog) {
        this.LastLog = LastLog;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public String getTokenId() {
        return TokenId;
    }

    public void setTokenId(String TokenId) {
        this.TokenId = TokenId;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String MobileNo) {
        this.MobileNo = MobileNo;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getVersionNo() {
        return VersionNo;
    }

    public void setVersionNo(String VersionNo) {
        this.VersionNo = VersionNo;
    }

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String AppID) {
        this.AppID = AppID;
    }

    public String getAppUSERID() {
        return AppUSERID;
    }

    public void setAppUSERID(String AppUSERID) {
        this.AppUSERID = AppUSERID;
    }

    public String getAppPASSWORD() {
        return AppPASSWORD;
    }

    public void setAppPASSWORD(String AppPASSWORD) {
        this.AppPASSWORD = AppPASSWORD;
    }
}
