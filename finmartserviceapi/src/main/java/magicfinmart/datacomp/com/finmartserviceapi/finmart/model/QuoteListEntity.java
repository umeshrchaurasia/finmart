package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;

public class QuoteListEntity implements Parcelable {

    private String vehicle_insurance_type;
    private String SRN;
    private int VehicleRequestID;

    private int fba_id;
    private int isActive;
    private MotorRequestEntity motorRequestEntity;

    public String getSRN() {
        return SRN;
    }

    public void setSRN(String SRN) {
        this.SRN = SRN;
    }

    public int getVehicleRequestID() {
        return VehicleRequestID;
    }

    public void setVehicleRequestID(int VehicleRequestID) {
        this.VehicleRequestID = VehicleRequestID;
    }
    public String getVehicle_insurance_type() {
        return vehicle_insurance_type;
    }

    public void setVehicle_insurance_type(String vehicle_insurance_type) {
        this.vehicle_insurance_type = vehicle_insurance_type;
    }

    public int getFba_id() {
        return fba_id;
    }

    public void setFba_id(int fba_id) {
        this.fba_id = fba_id;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public MotorRequestEntity getMotorRequestEntity() {
        return motorRequestEntity;
    }

    public void setMotorRequestEntity(MotorRequestEntity motorRequestEntity) {
        this.motorRequestEntity = motorRequestEntity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vehicle_insurance_type);
        dest.writeString(this.SRN);
        dest.writeInt(this.VehicleRequestID);
        dest.writeInt(this.fba_id);
        dest.writeInt(this.isActive);
        dest.writeParcelable(this.motorRequestEntity, flags);
    }

    public QuoteListEntity() {
    }

    protected QuoteListEntity(Parcel in) {
        this.vehicle_insurance_type = in.readString();
        this.SRN = in.readString();
        this.VehicleRequestID = in.readInt();
        this.fba_id = in.readInt();
        this.isActive = in.readInt();
        this.motorRequestEntity = in.readParcelable(MotorRequestEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<QuoteListEntity> CREATOR = new Parcelable.Creator<QuoteListEntity>() {
        @Override
        public QuoteListEntity createFromParcel(Parcel source) {
            return new QuoteListEntity(source);
        }

        @Override
        public QuoteListEntity[] newArray(int size) {
            return new QuoteListEntity[size];
        }
    };
}