package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class GenerateOtpEntity {
    /**
     * TranType : C
     * MobileOTP : 796263
     * EmailOTP : null
     * Result : 1
     * Message : OTP generated
     */

    private String TranType;
    private String MobileOTP;
    private Object EmailOTP;
    private String Result;
    private String Message;

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String TranType) {
        this.TranType = TranType;
    }

    public String getMobileOTP() {
        return MobileOTP;
    }

    public void setMobileOTP(String MobileOTP) {
        this.MobileOTP = MobileOTP;
    }

    public Object getEmailOTP() {
        return EmailOTP;
    }

    public void setEmailOTP(Object EmailOTP) {
        this.EmailOTP = EmailOTP;
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

    public void setMessageX(String MessageX) {
        this.Message = Message;
    }
}