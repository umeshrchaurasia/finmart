package magicfinmart.datacomp.com.finmartserviceapi.loan_fm;

/**
 * Created by IN-RB on 04-03-2018.
 */

public class APIResponseERP  {


    /**
     * message :
     * result : 0
     * status : Fail
     * statusId : 1
     */

    private String message;
    private String status;
    private int statusId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
