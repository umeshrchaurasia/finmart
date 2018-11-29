package magicfinmart.datacomp.com.finmartserviceapi.motor;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class APIResponse {

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
