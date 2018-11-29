package magicfinmart.datacomp.com.finmartserviceapi.motor.response;


import magicfinmart.datacomp.com.finmartserviceapi.motor.APIResponse;

/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class BikeUniqueResponse extends APIResponse {

    /**
     * Summary : {"Request_Unique_Id":"SRN-3R2S96OF-FQOZ-UB85-HNED-CWGEWKB3WBEO"}
     * Request : null
     * Master : null
     */

    private SummaryEntity Summary;
    private Object Request;
    private Object Master;

    private String[] Details;
    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String[] getDetails() {
        return Details;

    }

    public void setDetails(String[] details) {
        Details = details;
    }

    public SummaryEntity getSummary() {
        return Summary;
    }

    public void setSummary(SummaryEntity Summary) {
        this.Summary = Summary;
    }

    public Object getRequest() {
        return Request;
    }

    public void setRequest(Object Request) {
        this.Request = Request;
    }

    public Object getMaster() {
        return Master;
    }

    public void setMaster(Object Master) {
        this.Master = Master;
    }

    public static class SummaryEntity {
        /**
         * Request_Unique_Id : SRN-3R2S96OF-FQOZ-UB85-HNED-CWGEWKB3WBEO
         */

        private String Request_Unique_Id;

        public String getRequest_Unique_Id() {
            return Request_Unique_Id;
        }

        public void setRequest_Unique_Id(String Request_Unique_Id) {
            this.Request_Unique_Id = Request_Unique_Id;
        }
    }
}
