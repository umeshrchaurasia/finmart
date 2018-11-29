package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import io.realm.RealmObject;

public class CityEntity extends RealmObject {
    /**
     * City_Id : 1
     * City_Name : AHMEDABAD
     * state_id : 12
     */

    private int City_Id;
    private String City_Name;
    private int state_id;

    public int getCity_Id() {
        return City_Id;
    }

    public void setCity_Id(int City_Id) {
        this.City_Id = City_Id;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String City_Name) {
        this.City_Name = City_Name;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }
}