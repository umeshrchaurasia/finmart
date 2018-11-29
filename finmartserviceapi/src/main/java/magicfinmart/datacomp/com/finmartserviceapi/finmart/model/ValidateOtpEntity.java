package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class ValidateOtpEntity {
    /**
     * TranType : V
     * Result : 1
     * Message : Mobile Number and EmailId OTP verified successfully!
     * MobileOTP : 1
     * EmailOTP : 1
     */

    private String TranType;
    private String Result;
    private String Message;
    private int MobileOTP;
    private int EmailOTP;

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String TranType) {
        this.TranType = TranType;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessageX(String Message) {
        this.Message = Message;
    }

    public int getMobileOTP() {
        return MobileOTP;
    }

    public void setMobileOTP(int MobileOTP) {
        this.MobileOTP = MobileOTP;
    }

    public int getEmailOTP() {
        return EmailOTP;
    }

    public void setEmailOTP(int EmailOTP) {
        this.EmailOTP = EmailOTP;
    }
}