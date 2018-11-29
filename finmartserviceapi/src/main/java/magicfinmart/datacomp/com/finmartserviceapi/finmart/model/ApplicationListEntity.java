package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;

public class ApplicationListEntity implements Parcelable {
    /**
     * SRN : SRN-123456-PPFG-AQVO-IIEV-4S9LXQIYTSLQ
     * VehicleRequestID : 4
     * fba_id : 35779
     * isActive : 1
     * motorRequestEntity : {"VehicleRequestID":4,"birth_date":"1992-01-01T00:00:00.000Z","fba_id":35779,"product_id":1,"vehicle_id":328,"rto_id":579,"vehicle_insurance_type":"renew","vehicle_manf_date":"2017-01-01T00:00:00.000Z","vehicle_registration_date":"2018-01-25T00:00:00.000Z","policy_expiry_date":"2018-01-30T00:00:00.000Z","prev_insurer_id":2,"vehicle_registration_type":"individual","vehicle_ncb_current":"","is_claim_exists":"yes","method_type":"Premium","execution_async":"yes","electrical_accessory":"0","non_electrical_accessory":"0","registration_no":0,"is_llpd":"no","is_antitheft_fit":"no","voluntary_deductible":"0","is_external_bifuel":"no","external_bifuel_value":"","pa_owner_driver_si":"100000","pa_named_passenger_si":"0","pa_unnamed_passenger_si":"0","pa_paid_driver_si":"0","vehicle_expected_idv":0,"first_name":"nilesh","middle_name":"","last_name":"","mobile":"9930089095","email":"test@test.com","crn":1234,"ip_address":"","secret_key":"SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL","client_key":"CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T","is_aai_member":0,"external_bifuel_type":"","ss_id":0,"geo_lat":0,"geo_long":0,"isTwentyfour":1,"isActive":1,"created_date":"29/01/2018","type":"Application","conversiondate":"2018-01-31T12:47:19.000Z","srn":"SRN-123456-PPFG-AQVO-IIEV-4S9LXQIYTSLQ"}
     */

    private String SRN;
    private int VehicleRequestID;
    private int fba_id;
    private int isActive;
    private int selectedPrevInsID;
    private String insImage;
    private MotorRequestEntity motorRequestEntity;


    public String getInsImage() {
        return insImage;
    }

    public void setInsImage(String insImage) {
        this.insImage = insImage;
    }

    public int getSelectedPrevInsID() {
        return selectedPrevInsID;
    }

    public void setSelectedPrevInsID(int selectedPrevInsID) {
        this.selectedPrevInsID = selectedPrevInsID;
    }

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
        dest.writeString(this.SRN);
        dest.writeInt(this.VehicleRequestID);
        dest.writeInt(this.fba_id);
        dest.writeInt(this.isActive);
        dest.writeInt(this.selectedPrevInsID);
        dest.writeString(this.insImage);
        dest.writeParcelable(this.motorRequestEntity, flags);
    }

    public ApplicationListEntity() {
    }

    protected ApplicationListEntity(Parcel in) {
        this.SRN = in.readString();
        this.VehicleRequestID = in.readInt();
        this.fba_id = in.readInt();
        this.isActive = in.readInt();
        this.selectedPrevInsID = in.readInt();
        this.insImage = in.readString();
        this.motorRequestEntity = in.readParcelable(MotorRequestEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<ApplicationListEntity> CREATOR = new Parcelable.Creator<ApplicationListEntity>() {
        @Override
        public ApplicationListEntity createFromParcel(Parcel source) {
            return new ApplicationListEntity(source);
        }

        @Override
        public ApplicationListEntity[] newArray(int size) {
            return new ApplicationListEntity[size];
        }
    };
}