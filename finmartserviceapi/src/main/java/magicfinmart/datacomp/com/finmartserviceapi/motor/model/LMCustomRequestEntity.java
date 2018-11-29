package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LMCustomRequestEntity implements Parcelable {
    public static final Creator<LMCustomRequestEntity> CREATOR = new Creator<LMCustomRequestEntity>() {
        @Override
        public LMCustomRequestEntity createFromParcel(Parcel in) {
            return new LMCustomRequestEntity(in);
        }

        @Override
        public LMCustomRequestEntity[] newArray(int size) {
            return new LMCustomRequestEntity[size];
        }
    };
    /**
     * dbmaster_insurer_vehicle_exshowroom : 409341
     * vehicle_expected_idv : 349987
     * vehicle_max_idv : 427761
     * vehicle_min_idv : 349987
     * vehicle_ncb_current : 0
     * vehicle_ncb_next : 0
     * vehicle_normal_idv : 388874
     */

    private String dbmaster_insurer_vehicle_exshowroom;
    private String vehicle_expected_idv;
    private String vehicle_max_idv;
    private String vehicle_min_idv;
    private String vehicle_ncb_current;
    private String vehicle_ncb_next;
    private String vehicle_normal_idv;

    protected LMCustomRequestEntity(Parcel in) {
        dbmaster_insurer_vehicle_exshowroom = in.readString();
        vehicle_expected_idv = in.readString();
        vehicle_max_idv = in.readString();
        vehicle_min_idv = in.readString();
        vehicle_ncb_current = in.readString();
        vehicle_ncb_next = in.readString();
        vehicle_normal_idv = in.readString();
    }

    public String getDbmaster_insurer_vehicle_exshowroom() {
        return dbmaster_insurer_vehicle_exshowroom;
    }

    public void setDbmaster_insurer_vehicle_exshowroom(String dbmaster_insurer_vehicle_exshowroom) {
        this.dbmaster_insurer_vehicle_exshowroom = dbmaster_insurer_vehicle_exshowroom;
    }

    public String getVehicle_expected_idv() {
        return vehicle_expected_idv;
    }

    public void setVehicle_expected_idv(String vehicle_expected_idv) {
        this.vehicle_expected_idv = vehicle_expected_idv;
    }

    public String getVehicle_max_idv() {
        return vehicle_max_idv;
    }

    public void setVehicle_max_idv(String vehicle_max_idv) {
        this.vehicle_max_idv = vehicle_max_idv;
    }

    public String getVehicle_min_idv() {
        return vehicle_min_idv;
    }

    public void setVehicle_min_idv(String vehicle_min_idv) {
        this.vehicle_min_idv = vehicle_min_idv;
    }

    public String getVehicle_ncb_current() {
        return vehicle_ncb_current;
    }

    public void setVehicle_ncb_current(String vehicle_ncb_current) {
        this.vehicle_ncb_current = vehicle_ncb_current;
    }

    public String getVehicle_ncb_next() {
        return vehicle_ncb_next;
    }

    public void setVehicle_ncb_next(String vehicle_ncb_next) {
        this.vehicle_ncb_next = vehicle_ncb_next;
    }

    public String getVehicle_normal_idv() {
        return vehicle_normal_idv;
    }

    public void setVehicle_normal_idv(String vehicle_normal_idv) {
        this.vehicle_normal_idv = vehicle_normal_idv;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dbmaster_insurer_vehicle_exshowroom);
        dest.writeString(vehicle_expected_idv);
        dest.writeString(vehicle_max_idv);
        dest.writeString(vehicle_min_idv);
        dest.writeString(vehicle_ncb_current);
        dest.writeString(vehicle_ncb_next);
        dest.writeString(vehicle_normal_idv);
    }
}