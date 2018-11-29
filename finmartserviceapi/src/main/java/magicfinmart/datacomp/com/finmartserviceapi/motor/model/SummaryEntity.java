package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SummaryEntity implements Parcelable {
    public static final Creator<SummaryEntity> CREATOR = new Creator<SummaryEntity>() {
        @Override
        public SummaryEntity createFromParcel(Parcel in) {
            return new SummaryEntity(in);
        }

        @Override
        public SummaryEntity[] newArray(int size) {
            return new SummaryEntity[size];
        }
    };
    /**
     * _id : 5a0ab49b1f4d2b19e098bf79
     * Request_Id : 8946
     * Request_Unique_Id : SRN-XP0NTBTF-VH4C-33V4-7JVZ-Z9TCRILNPXFF
     * Client_Id : 4
     * PB_CRN : 103419
     * Request_Core : {"product_id":1,"vehicle_id":681,"rto_id":579,"vehicle_insurance_type":"renew","vehicle_manf_date":"2016-11-01","vehicle_registration_date":"2016-11-15","policy_expiry_date":"2017-11-14","prev_insurer_id":2,"vehicle_registration_type":"individual","vehicle_ncb_current":"0","is_claim_exists":"no","method_type":"Premium","execution_async":"no","electrical_accessory":"0","non_electrical_accessory":"0","registration_no":"MH-01-AA-1234","is_llpd":"no","is_antitheft_fit":"no","voluntary_deductible":0,"is_external_bifuel":"no","is_aai_member":"no","external_bifuel_type":null,"external_bifuel_value":"0","pa_owner_driver_si":100000,"pa_named_passenger_si":"0","pa_unnamed_passenger_si":"0","pa_paid_driver_si":"0","vehicle_expected_idv":0,"first_name":"Ajit","middle_name":"U3IBBVSZDV","last_name":"Kumar","mobile":"7377319978","email":"ajit.kumar@rupeeboss.com","crn":0,"ss_id":0,"secret_key":"SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL","client_key":"CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T","birth_date":"1992-01-01","registration_no_1":"MH","registration_no_2":"01","registration_no_3":"AA","registration_no_4":"1234","posp_posp_id":0,"posp_fba_id":0,"posp_sm_posp_id":0,"posp_sm_posp_name":0,"posp_first_name":0,"posp_middle_name":0,"posp_last_name":0,"posp_email_id":0,"posp_agent_city":0,"posp_mobile_no":0,"posp_pan_no":0,"posp_aadhar":0,"posp_sources":0,"posp_ss_id":0,"posp_erp_id":0,"posp_gender":0,"posp_posp_category":0,"posp_reporting_agent_uid":0,"posp_reporting_agent_name":0,"posp_category":"PolicyBoss","erp_source":"FRESH-MTR"}
     * Request_Product : {"vehicle_insurance_type":"renew","vehicle_registration_type":"individual","vehicle_registration_date":"2016-11-15","vehicle_id":681,"rto_id":579,"prev_insurer_id":2,"is_claim_exists":"no","vehicle_ncb_current":"0","vehicle_manf_year":"2016","vehicle_ncb_next":"20","vehicle_age_year":1,"vehicle_age_month":12,"vehicle_expected_idv":0,"registration_no":"MH-01-AA-1234","registration_no_1":"MH","registration_no_2":"01","registration_no_3":"AA","registration_no_4":"1234","policy_expiry_date":"2017-11-14","policy_start_date":"2017-11-15","pre_policy_start_date":"2016-11-15","policy_end_date":"2018-11-14","vehicle_manf_date":"2016-11-01","is_financed":"","financial_institute_name":"","financial_institute_code":"","financial_institute_city":"","financial_agreement_type":"","previous_policy_number":"","is_external_bifuel":"no","external_bifuel_type":"","external_bifuel_value":"0","electrical_accessory":"0","non_electrical_accessory":"0","engine_number":"","chassis_number":"","vehicle_color":"","voluntary_deductible":"","is_antitheft_fit":"no","is_aai_member":"no","is_llpd":"no","pa_owner_driver_si":100000,"pa_named_passenger_si":"","pa_unnamed_passenger_si":"","pa_paid_driver_si":"","addon_zero_dep_cover":"","addon_road_assist_cover":"","addon_ncb_protection_cover":"","addon_engine_protector_cover":"","addon_invoice_price_cover":"","addon_key_lock_cover":"","addon_consumable_cover":"","addon_passenger_assistance_cover":"","addon_flag":"","addon_package_name":"Titanium","addon_daily_allowance_cover":"yes","addon_windshield_cover":"yes","addon_tyre_coverage_cover":"yes","addon_personal_belonging_loss_cover":"yes","addon_inconvenience_allowance_cover":"yes","addon_medical_expense_cover":"yes","addon_hospital_cash_cover":"yes","addon_ambulance_charge_cover":"yes","addon_rodent_bite_cover":"yes","addon_losstime_protection_cover":"yes","addon_hydrostatic_lock_cover":"yes"}
     * Created_On : 2017-11-14T09:17:15.052Z
     * Status : complete
     * Total : 23
     * Pending : 0
     * Complete : 23
     * Success : 15
     * Fail : 8
     * Total_Execution_Time : 88.37
     * vehicle_min_idv : 319432
     * vehicle_max_idv : 550820
     * Actual_Time : 216.67000000000002
     * Common_Addon : {"addon_road_assist_cover":{"min":350,"max":500},"addon_zero_dep_cover":{"min":1336,"max":3380},"addon_engine_protector_cover":{"min":569,"max":599},"addon_invoice_price_cover":{"min":898,"max":949},"addon_key_lock_cover":{"min":108,"max":683},"addon_consumable_cover":{"min":435,"max":1597},"addon_medical_expense_cover":{"min":375,"max":375},"addon_hospital_cash_cover":{"min":500,"max":500},"addon_ambulance_charge_cover":{"min":100,"max":100},"addon_ncb_protection_cover":{"min":639,"max":1352},"addon_windshield_cover":{"min":270,"max":270}}
     */

    private String _id;
    private String Request_Id;
    private String Request_Unique_Id;
    private String Client_Id;
    private String PB_CRN;
    private RequestCoreEntity Request_Core;
    private RequestProductEntity Request_Product;
    private String Created_On;
    @SerializedName("Status")
    private String StatusX;
    private String Total;
    private String Pending;
    private String Complete;
    private String Success;
    private String Fail;
    private double Total_Execution_Time;
    private String vehicle_min_idv;
    private String vehicle_max_idv;
    private double Actual_Time;
    private CommonAddonEntity Common_Addon;

    protected SummaryEntity(Parcel in) {
        Request_Core = (RequestCoreEntity) in.readParcelable(RequestCoreEntity.class.getClassLoader());
        _id = in.readString();
        Request_Id = in.readString();
        Request_Unique_Id = in.readString();
        Client_Id = in.readString();
        PB_CRN = in.readString();
        Created_On = in.readString();
        StatusX = in.readString();
        Total = in.readString();
        Pending = in.readString();
        Complete = in.readString();
        Success = in.readString();
        Fail = in.readString();
        Total_Execution_Time = in.readDouble();
        vehicle_min_idv = in.readString();
        vehicle_max_idv = in.readString();
        Actual_Time = in.readDouble();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRequest_Id() {
        return Request_Id;
    }

    public void setRequest_Id(String Request_Id) {
        this.Request_Id = Request_Id;
    }

    public String getRequest_Unique_Id() {
        return Request_Unique_Id;
    }

    public void setRequest_Unique_Id(String Request_Unique_Id) {
        this.Request_Unique_Id = Request_Unique_Id;
    }

    public String getClient_Id() {
        return Client_Id;
    }

    public void setClient_Id(String Client_Id) {
        this.Client_Id = Client_Id;
    }

    public String getPB_CRN() {
        return PB_CRN;
    }

    public void setPB_CRN(String PB_CRN) {
        this.PB_CRN = PB_CRN;
    }

    public RequestCoreEntity getRequest_Core() {
        return Request_Core;
    }

    public void setRequest_Core(RequestCoreEntity Request_Core) {
        this.Request_Core = Request_Core;
    }

    public RequestProductEntity getRequest_Product() {
        return Request_Product;
    }

    public void setRequest_Product(RequestProductEntity Request_Product) {
        this.Request_Product = Request_Product;
    }

    public String getCreated_On() {
        return Created_On;
    }

    public void setCreated_On(String Created_On) {
        this.Created_On = Created_On;
    }

    public String getStatusX() {
        return StatusX;
    }

    public void setStatusX(String StatusX) {
        this.StatusX = StatusX;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public String getPending() {
        return Pending;
    }

    public void setPending(String Pending) {
        this.Pending = Pending;
    }

    public String getComplete() {
        return Complete;
    }

    public void setComplete(String Complete) {
        this.Complete = Complete;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public String getFail() {
        return Fail;
    }

    public void setFail(String Fail) {
        this.Fail = Fail;
    }

    public double getTotal_Execution_Time() {
        return Total_Execution_Time;
    }

    public void setTotal_Execution_Time(double Total_Execution_Time) {
        this.Total_Execution_Time = Total_Execution_Time;
    }

    public String getVehicle_min_idv() {
        return vehicle_min_idv;
    }

    public void setVehicle_min_idv(String vehicle_min_idv) {
        this.vehicle_min_idv = vehicle_min_idv;
    }

    public String getVehicle_max_idv() {
        return vehicle_max_idv;
    }

    public void setVehicle_max_idv(String vehicle_max_idv) {
        this.vehicle_max_idv = vehicle_max_idv;
    }

    public double getActual_Time() {
        return Actual_Time;
    }

    public void setActual_Time(double Actual_Time) {
        this.Actual_Time = Actual_Time;
    }

    public CommonAddonEntity getCommon_Addon() {
        return Common_Addon;
    }

    public void setCommon_Addon(CommonAddonEntity Common_Addon) {
        this.Common_Addon = Common_Addon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(Request_Core, flags);
        dest.writeString(_id);
        dest.writeString(Request_Id);
        dest.writeString(Request_Unique_Id);
        dest.writeString(Client_Id);
        dest.writeString(PB_CRN);
        dest.writeString(Created_On);
        dest.writeString(StatusX);
        dest.writeString(Total);
        dest.writeString(Pending);
        dest.writeString(Complete);
        dest.writeString(Success);
        dest.writeString(Fail);
        dest.writeDouble(Total_Execution_Time);
        dest.writeString(vehicle_min_idv);
        dest.writeString(vehicle_max_idv);
        dest.writeDouble(Actual_Time);
    }
}