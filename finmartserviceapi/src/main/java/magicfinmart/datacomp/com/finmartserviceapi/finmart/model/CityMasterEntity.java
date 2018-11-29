package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CityMasterEntity extends RealmObject {
    /**
     * VehicleCity_Id : 1
     * State_Id : 1
     * State_Name : ANDAMAN-NICOBAR
     * RTO_City : Port Blair
     * VehicleCity_RTOCode : AN01
     * RTO_CodeDiscription : (AN01) Port Blair
     * IsActive : true
     * VehicleTariff_Zone : B
     */
    @PrimaryKey
    private String VehicleCity_Id;
    private String State_Id;
    private String State_Name;
    private String RTO_City;
    private String VehicleCity_RTOCode;
    private String RTO_CodeDiscription;
    private String IsActive;
    private String VehicleTariff_Zone;

    public String getVehicleCity_Id() {
        return VehicleCity_Id;
    }

    public void setVehicleCity_Id(String VehicleCity_Id) {
        this.VehicleCity_Id = VehicleCity_Id;
    }

    public String getState_Id() {
        return State_Id;
    }

    public void setState_Id(String State_Id) {
        this.State_Id = State_Id;
    }

    public String getState_Name() {
        return State_Name;
    }

    public void setState_Name(String State_Name) {
        this.State_Name = State_Name;
    }

    public String getRTO_City() {
        return RTO_City;
    }

    public void setRTO_City(String RTO_City) {
        this.RTO_City = RTO_City;
    }

    public String getVehicleCity_RTOCode() {
        return VehicleCity_RTOCode;
    }

    public void setVehicleCity_RTOCode(String VehicleCity_RTOCode) {
        this.VehicleCity_RTOCode = VehicleCity_RTOCode;
    }

    public String getRTO_CodeDiscription() {
        return RTO_CodeDiscription;
    }

    public void setRTO_CodeDiscription(String RTO_CodeDiscription) {
        this.RTO_CodeDiscription = RTO_CodeDiscription;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String IsActive) {
        this.IsActive = IsActive;
    }

    public String getVehicleTariff_Zone() {
        return VehicleTariff_Zone;
    }

    public void setVehicleTariff_Zone(String VehicleTariff_Zone) {
        this.VehicleTariff_Zone = VehicleTariff_Zone;
    }
}