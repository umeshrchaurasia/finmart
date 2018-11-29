package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class PincodeResponseEntity {
    /**
     * map_id : 34000
     * pincode : 805110
     * postname : Nawadah
     * districtname : Nawada
     * stateid : 5
     * state_name : BIHAR
     * cityid : 247
     * cityname : Nawada
     */

    private int map_id;
    private String pincode;
    private String postname;
    private String districtname;
    private int stateid;
    private String state_name;
    private int cityid;
    private String cityname;

    public int getMap_id() {
        return map_id;
    }

    public void setMap_id(int map_id) {
        this.map_id = map_id;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}