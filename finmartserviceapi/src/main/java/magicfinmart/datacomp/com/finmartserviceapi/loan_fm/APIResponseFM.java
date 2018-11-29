package magicfinmart.datacomp.com.finmartserviceapi.loan_fm;

/**
 * Created by IN-RB on 31-01-2018.
 */

public class APIResponseFM  {

    private String Message;
    private String Status;
    private int StatusNo;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getStatusNo() {
        return StatusNo;
    }

    public void setStatusNo(int StatusNo) {
        this.StatusNo = StatusNo;
    }
}
