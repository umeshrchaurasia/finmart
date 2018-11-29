package magicfinmart.datacomp.com.finmartserviceapi.loan_fm;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */

public class APIResponse {

    private int status_Id;
    private String msg;
    /**
     * message :
     * status : Success
     * statusId : 0
     */

    private String message;
    private String status;

    private int statusId;

    public int getStatus_Id() {
        return status_Id;
    }

    public void setStatus_Id(int status_Id) {
        this.status_Id = status_Id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
