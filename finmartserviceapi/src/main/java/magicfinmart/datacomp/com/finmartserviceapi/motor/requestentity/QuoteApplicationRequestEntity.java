package magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity;

/**
 * Created by Nilesh Birhade on 04-05-2018.
 */

public class QuoteApplicationRequestEntity {

    /**
     * VehicleRequestID :
     * product_id : 1
     * crn :
     * first_name :
     * fba_id : 1976
     * count : 0
     * type : 0
     */

    private String VehicleRequestID;
    private String product_id;
    private String crn;
    private String first_name;
    private String fba_id;
    private int count;
    private String type;

    public String getVehicleRequestID() {
        return VehicleRequestID;
    }

    public void setVehicleRequestID(String VehicleRequestID) {
        this.VehicleRequestID = VehicleRequestID;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFba_id() {
        return fba_id;
    }

    public void setFba_id(String fba_id) {
        this.fba_id = fba_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
