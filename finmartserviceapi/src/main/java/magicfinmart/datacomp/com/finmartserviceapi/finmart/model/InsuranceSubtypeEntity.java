package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class InsuranceSubtypeEntity extends RealmObject {
    /**
     * vehicleinsubtypeid : 1
     * sub_type_name : T.P. Only (3 Yrs)
     * vehicle_id : 1
     * neworrenew : new
     * code : 0CH_3TP
     */

    private int vehicleinsubtypeid;
    private String sub_type_name;
    private int vehicle_id;
    private String neworrenew;
    private String code;

    public int getVehicleinsubtypeid() {
        return vehicleinsubtypeid;
    }

    public void setVehicleinsubtypeid(int vehicleinsubtypeid) {
        this.vehicleinsubtypeid = vehicleinsubtypeid;
    }

    public String getSub_type_name() {
        return sub_type_name;
    }

    public void setSub_type_name(String sub_type_name) {
        this.sub_type_name = sub_type_name;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getNeworrenew() {
        return neworrenew;
    }

    public void setNeworrenew(String neworrenew) {
        this.neworrenew = neworrenew;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.sub_type_name;            // What to display in the Spinner list.
    }
}