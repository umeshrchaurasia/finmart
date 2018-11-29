package magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Nilesh Birhade on 03-07-2017.
 */

public class MotorRequestEntity implements Parcelable {

    private String birth_date;
    private int product_id;
    private int vehicle_id;
    private int rto_id;
    private String vehicle_insurance_type;
    private String vehicle_manf_date;
    private String vehicle_registration_date;
    private String policy_expiry_date;
    private int prev_insurer_id;
    private String vehicle_registration_type;
    private String vehicle_ncb_current;
    private String is_claim_exists;
    private String method_type;
    private String execution_async;
    private String electrical_accessory;
    private String non_electrical_accessory;
    private String registration_no;
    private String is_llpd;
    private String is_antitheft_fit;
    private int voluntary_deductible;
    private String is_external_bifuel;
    private int external_bifuel_value;
    private String pa_owner_driver_si;
    private String pa_named_passenger_si;
    private String pa_unnamed_passenger_si;
    private String pa_paid_driver_si;
    private long vehicle_expected_idv;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String mobile;
    private String email;
    private String crn;
    private String ip_address;
    private String secret_key;
    private String client_key;


    private String is_aai_member;
    private String external_bifuel_type;
    private int ss_id;
    private double geo_lat;
    private double geo_long;


    private String agent_source;
    private String app_version;
    private String device_id;
    private String erp_source;
    private String mac_address;

    private String PBStatus;
    private int StatusPercent;
    private int varid;
    private String vehicle_insurance_subtype;


    //mysql extra parameter
    private int isTwentyfour;
    private int isActive;
    private String created_date;
    private String type;
    private String conversiondate;
    private int VehicleRequestID;
    private int fba_id;
    private String srn;
    private int selectedPrevInsID;

    public String getVehicle_insurance_subtype() {
        return vehicle_insurance_subtype;
    }

    public void setVehicle_insurance_subtype(String vehicle_insurance_subtype) {
        this.vehicle_insurance_subtype = vehicle_insurance_subtype;
    }

    public String getPBStatus() {
        return PBStatus;
    }

    public void setPBStatus(String PBStatus) {
        this.PBStatus = PBStatus;
    }

    public int getStatusPercent() {
        return StatusPercent;
    }

    public void setStatusPercent(int statusPercent) {
        StatusPercent = statusPercent;
    }

    public int getVarid() {
        return varid;
    }

    public void setVarid(int varid) {
        this.varid = varid;
    }

    public int getIsTwentyfour() {
        return isTwentyfour;
    }

    public void setIsTwentyfour(int isTwentyfour) {
        this.isTwentyfour = isTwentyfour;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConversiondate() {
        return conversiondate;
    }

    public void setConversiondate(String conversiondate) {
        this.conversiondate = conversiondate;
    }



    public int getVehicleRequestID() {
        return VehicleRequestID;
    }

    public void setVehicleRequestID(int vehicleRequestID) {
        VehicleRequestID = vehicleRequestID;
    }

    public MotorRequestEntity(Context context) {
        /*DBPersistanceController dbPersistanceController;
        LoginResponseEntity loginResponseEntity;
        dbPersistanceController = new DBPersistanceController(context);
        loginResponseEntity = dbPersistanceController.getUserData();*/
        // this.birth_date = "1992-01-01";
        this.agent_source = "";
        this.is_aai_member = "no";
        this.external_bifuel_type = "";

        this.product_id = 0;
        this.vehicle_id = 0;
        this.rto_id = 0;
       /* this.secret_key = Utility.SECRET_KEY;
        this.client_key = Utility.CLIENT_KEY;*/
        this.execution_async = "yes";
        this.vehicle_insurance_type = "";
        this.vehicle_manf_date = "";
        this.vehicle_registration_date = "";
        this.policy_expiry_date = "";
        this.prev_insurer_id = 0;
        this.vehicle_registration_type = "";
        this.vehicle_ncb_current = "";
        this.is_claim_exists = "";
        this.method_type = "";
        this.electrical_accessory = "0";
        this.non_electrical_accessory = "0";
        this.registration_no = "";
        this.is_llpd = "";
        this.is_antitheft_fit = "no";
        this.voluntary_deductible = 0;
        this.is_external_bifuel = "";
        this.external_bifuel_value = 0;
        this.pa_owner_driver_si = "";
        this.pa_named_passenger_si = "";
        this.pa_unnamed_passenger_si = "";
        this.pa_paid_driver_si = "";
        this.vehicle_expected_idv = 0;
        this.first_name = "";
        this.middle_name = " ";
        this.last_name = " ";
        this.mobile = "";
        this.email = "finmarttest@gmail.com";
        this.crn = "";
        this.vehicle_insurance_subtype = "";
        /*this.app_version = Utility.getVersionName(context);
        this.device_id = Utility.getTokenId(context);
        this.fba_id = loginResponseEntity.getFBAId();
        try {
            this.mac_address = Utility.getMacAddress(context);
        } catch (IOException e) {
            this.mac_address = "0";
        }

        if (loginResponseEntity.getPOSPNo() != null && !loginResponseEntity.getPOSPNo().equals(""))
            this.ss_id = Integer.parseInt(loginResponseEntity.getPOSPNo());
        else
            this.ss_id = 5;
        this.ip_address = Utility.getLocalIpAddress(context);*/
    }


    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getRto_id() {
        return rto_id;
    }

    public void setRto_id(int rto_id) {
        this.rto_id = rto_id;
    }

    public String getVehicle_insurance_type() {
        return vehicle_insurance_type;
    }

    public void setVehicle_insurance_type(String vehicle_insurance_type) {
        this.vehicle_insurance_type = vehicle_insurance_type;
    }

    public String getVehicle_manf_date() {
        return vehicle_manf_date;
    }

    public void setVehicle_manf_date(String vehicle_manf_date) {
        this.vehicle_manf_date = vehicle_manf_date;
    }

    public String getVehicle_registration_date() {
        return vehicle_registration_date;
    }

    public void setVehicle_registration_date(String vehicle_registration_date) {
        this.vehicle_registration_date = vehicle_registration_date;
    }

    public String getPolicy_expiry_date() {
        return policy_expiry_date;
    }

    public void setPolicy_expiry_date(String policy_expiry_date) {
        this.policy_expiry_date = policy_expiry_date;
    }

    public int getPrev_insurer_id() {
        return prev_insurer_id;
    }

    public void setPrev_insurer_id(int prev_insurer_id) {
        this.prev_insurer_id = prev_insurer_id;
    }

    public String getVehicle_registration_type() {
        return vehicle_registration_type;
    }

    public void setVehicle_registration_type(String vehicle_registration_type) {
        this.vehicle_registration_type = vehicle_registration_type;
    }

    public String getVehicle_ncb_current() {
        return vehicle_ncb_current;
    }

    public void setVehicle_ncb_current(String vehicle_ncb_current) {
        this.vehicle_ncb_current = vehicle_ncb_current;
    }

    public String getIs_claim_exists() {
        return is_claim_exists;
    }

    public void setIs_claim_exists(String is_claim_exists) {
        this.is_claim_exists = is_claim_exists;
    }

    public String getMethod_type() {
        return method_type;
    }

    public void setMethod_type(String method_type) {
        this.method_type = method_type;
    }

    public String getExecution_async() {
        return execution_async;
    }

    public void setExecution_async(String execution_async) {
        this.execution_async = execution_async;
    }

    public String getElectrical_accessory() {
        return electrical_accessory;
    }

    public void setElectrical_accessory(String electrical_accessory) {
        this.electrical_accessory = electrical_accessory;
    }

    public String getNon_electrical_accessory() {
        return non_electrical_accessory;
    }

    public void setNon_electrical_accessory(String non_electrical_accessory) {
        this.non_electrical_accessory = non_electrical_accessory;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getIs_llpd() {
        return is_llpd;
    }

    public void setIs_llpd(String is_llpd) {
        this.is_llpd = is_llpd;
    }

    public String getIs_antitheft_fit() {
        return is_antitheft_fit;
    }

    public void setIs_antitheft_fit(String is_antitheft_fit) {
        this.is_antitheft_fit = is_antitheft_fit;
    }

    public int getVoluntary_deductible() {
        return voluntary_deductible;
    }

    public void setVoluntary_deductible(int voluntary_deductible) {
        this.voluntary_deductible = voluntary_deductible;
    }

    public String getIs_external_bifuel() {
        return is_external_bifuel;
    }

    public void setIs_external_bifuel(String is_external_bifuel) {
        this.is_external_bifuel = is_external_bifuel;
    }

    public int getExternal_bifuel_value() {
        return external_bifuel_value;
    }

    public void setExternal_bifuel_value(int external_bifuel_value) {
        this.external_bifuel_value = external_bifuel_value;
    }

    public String getPa_owner_driver_si() {
        return pa_owner_driver_si;
    }

    public void setPa_owner_driver_si(String pa_owner_driver_si) {
        this.pa_owner_driver_si = pa_owner_driver_si;
    }

    public String getPa_named_passenger_si() {
        return pa_named_passenger_si;
    }

    public void setPa_named_passenger_si(String pa_named_passenger_si) {
        this.pa_named_passenger_si = pa_named_passenger_si;
    }

    public String getPa_unnamed_passenger_si() {
        return pa_unnamed_passenger_si;
    }

    public void setPa_unnamed_passenger_si(String pa_unnamed_passenger_si) {
        this.pa_unnamed_passenger_si = pa_unnamed_passenger_si;
    }

    public String getPa_paid_driver_si() {
        return pa_paid_driver_si;
    }

    public void setPa_paid_driver_si(String pa_paid_driver_si) {
        this.pa_paid_driver_si = pa_paid_driver_si;
    }

    public long getVehicle_expected_idv() {
        return vehicle_expected_idv;
    }

    public void setVehicle_expected_idv(long vehicle_expected_idv) {
        this.vehicle_expected_idv = vehicle_expected_idv;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getClient_key() {
        return client_key;
    }

    public void setClient_key(String client_key) {
        this.client_key = client_key;
    }


    public String getIs_aai_member() {
        return is_aai_member;
    }

    public void setIs_aai_member(String is_aai_member) {
        this.is_aai_member = is_aai_member;
    }

    public String getExternal_bifuel_type() {
        return external_bifuel_type;
    }

    public void setExternal_bifuel_type(String external_bifuel_type) {
        this.external_bifuel_type = external_bifuel_type;
    }

    public int getSs_id() {
        return ss_id;
    }

    public void setSs_id(int ss_id) {
        this.ss_id = ss_id;
    }

    public double getGeo_lat() {
        return geo_lat;
    }

    public void setGeo_lat(double geo_lat) {
        this.geo_lat = geo_lat;
    }

    public double getGeo_long() {
        return geo_long;
    }

    public void setGeo_long(double geo_long) {
        this.geo_long = geo_long;
    }

    public String getAgent_source() {
        return agent_source;
    }

    public void setAgent_source(String agent_source) {
        this.agent_source = agent_source;
    }

    public int getFba_id() {
        return fba_id;
    }

    public void setFba_id(int fba_id) {
        this.fba_id = fba_id;
    }

    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    public int getSelectedPrevInsID() {
        return selectedPrevInsID;
    }

    public void setSelectedPrevInsID(int selectedPrevInsID) {
        this.selectedPrevInsID = selectedPrevInsID;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getErp_source() {
        return erp_source;
    }

    public void setErp_source(String erp_source) {
        this.erp_source = erp_source;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.birth_date);
        dest.writeInt(this.product_id);
        dest.writeInt(this.vehicle_id);
        dest.writeInt(this.rto_id);
        dest.writeString(this.vehicle_insurance_type);
        dest.writeString(this.vehicle_manf_date);
        dest.writeString(this.vehicle_registration_date);
        dest.writeString(this.policy_expiry_date);
        dest.writeInt(this.prev_insurer_id);
        dest.writeString(this.vehicle_registration_type);
        dest.writeString(this.vehicle_ncb_current);
        dest.writeString(this.is_claim_exists);
        dest.writeString(this.method_type);
        dest.writeString(this.execution_async);
        dest.writeString(this.electrical_accessory);
        dest.writeString(this.non_electrical_accessory);
        dest.writeString(this.registration_no);
        dest.writeString(this.is_llpd);
        dest.writeString(this.is_antitheft_fit);
        dest.writeInt(this.voluntary_deductible);
        dest.writeString(this.is_external_bifuel);
        dest.writeInt(this.external_bifuel_value);
        dest.writeString(this.pa_owner_driver_si);
        dest.writeString(this.pa_named_passenger_si);
        dest.writeString(this.pa_unnamed_passenger_si);
        dest.writeString(this.pa_paid_driver_si);
        dest.writeLong(this.vehicle_expected_idv);
        dest.writeString(this.first_name);
        dest.writeString(this.middle_name);
        dest.writeString(this.last_name);
        dest.writeString(this.mobile);
        dest.writeString(this.email);
        dest.writeString(this.crn);
        dest.writeString(this.ip_address);
        dest.writeString(this.secret_key);
        dest.writeString(this.client_key);
        dest.writeString(this.is_aai_member);
        dest.writeString(this.external_bifuel_type);
        dest.writeInt(this.ss_id);
        dest.writeDouble(this.geo_lat);
        dest.writeDouble(this.geo_long);
        dest.writeString(this.agent_source);
        dest.writeString(this.app_version);
        dest.writeString(this.device_id);
        dest.writeString(this.erp_source);
        dest.writeString(this.mac_address);
        dest.writeString(this.PBStatus);
        dest.writeInt(this.StatusPercent);
        dest.writeInt(this.varid);
        dest.writeString(this.vehicle_insurance_subtype);
        dest.writeInt(this.isTwentyfour);
        dest.writeInt(this.isActive);
        dest.writeString(this.created_date);
        dest.writeString(this.type);
        dest.writeString(this.conversiondate);
        dest.writeInt(this.VehicleRequestID);
        dest.writeInt(this.fba_id);
        dest.writeString(this.srn);
        dest.writeInt(this.selectedPrevInsID);
    }

    protected MotorRequestEntity(Parcel in) {
        this.birth_date = in.readString();
        this.product_id = in.readInt();
        this.vehicle_id = in.readInt();
        this.rto_id = in.readInt();
        this.vehicle_insurance_type = in.readString();
        this.vehicle_manf_date = in.readString();
        this.vehicle_registration_date = in.readString();
        this.policy_expiry_date = in.readString();
        this.prev_insurer_id = in.readInt();
        this.vehicle_registration_type = in.readString();
        this.vehicle_ncb_current = in.readString();
        this.is_claim_exists = in.readString();
        this.method_type = in.readString();
        this.execution_async = in.readString();
        this.electrical_accessory = in.readString();
        this.non_electrical_accessory = in.readString();
        this.registration_no = in.readString();
        this.is_llpd = in.readString();
        this.is_antitheft_fit = in.readString();
        this.voluntary_deductible = in.readInt();
        this.is_external_bifuel = in.readString();
        this.external_bifuel_value = in.readInt();
        this.pa_owner_driver_si = in.readString();
        this.pa_named_passenger_si = in.readString();
        this.pa_unnamed_passenger_si = in.readString();
        this.pa_paid_driver_si = in.readString();
        this.vehicle_expected_idv = in.readLong();
        this.first_name = in.readString();
        this.middle_name = in.readString();
        this.last_name = in.readString();
        this.mobile = in.readString();
        this.email = in.readString();
        this.crn = in.readString();
        this.ip_address = in.readString();
        this.secret_key = in.readString();
        this.client_key = in.readString();
        this.is_aai_member = in.readString();
        this.external_bifuel_type = in.readString();
        this.ss_id = in.readInt();
        this.geo_lat = in.readDouble();
        this.geo_long = in.readDouble();
        this.agent_source = in.readString();
        this.app_version = in.readString();
        this.device_id = in.readString();
        this.erp_source = in.readString();
        this.mac_address = in.readString();
        this.PBStatus = in.readString();
        this.StatusPercent = in.readInt();
        this.varid = in.readInt();
        this.vehicle_insurance_subtype = in.readString();
        this.isTwentyfour = in.readInt();
        this.isActive = in.readInt();
        this.created_date = in.readString();
        this.type = in.readString();
        this.conversiondate = in.readString();
        this.VehicleRequestID = in.readInt();
        this.fba_id = in.readInt();
        this.srn = in.readString();
        this.selectedPrevInsID = in.readInt();
    }

    public static final Parcelable.Creator<MotorRequestEntity> CREATOR = new Parcelable.Creator<MotorRequestEntity>() {
        @Override
        public MotorRequestEntity createFromParcel(Parcel source) {
            return new MotorRequestEntity(source);
        }

        @Override
        public MotorRequestEntity[] newArray(int size) {
            return new MotorRequestEntity[size];
        }
    };
}
