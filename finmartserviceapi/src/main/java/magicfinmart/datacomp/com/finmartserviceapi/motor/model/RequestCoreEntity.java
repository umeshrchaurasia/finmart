package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestCoreEntity implements Parcelable {
    public static final Creator<RequestCoreEntity> CREATOR = new Creator<RequestCoreEntity>() {
        @Override
        public RequestCoreEntity createFromParcel(Parcel in) {
            return new RequestCoreEntity(in);
        }

        @Override
        public RequestCoreEntity[] newArray(int size) {
            return new RequestCoreEntity[size];
        }
    };
    /**
     * product_id : 1
     * vehicle_id : 681
     * rto_id : 579
     * vehicle_insurance_type : renew
     * vehicle_manf_date : 2016-11-01
     * vehicle_registration_date : 2016-11-15
     * policy_expiry_date : 2017-11-14
     * prev_insurer_id : 2
     * vehicle_registration_type : individual
     * vehicle_ncb_current : 0
     * is_claim_exists : no
     * method_type : Premium
     * execution_async : no
     * electrical_accessory : 0
     * non_electrical_accessory : 0
     * registration_no : MH-01-AA-1234
     * is_llpd : no
     * is_antitheft_fit : no
     * voluntary_deductible : 0
     * is_external_bifuel : no
     * is_aai_member : no
     * external_bifuel_type : null
     * external_bifuel_value : 0
     * pa_owner_driver_si : 100000
     * pa_named_passenger_si : 0
     * pa_unnamed_passenger_si : 0
     * pa_paid_driver_si : 0
     * vehicle_expected_idv : 0
     * first_name : Ajit
     * middle_name : U3IBBVSZDV
     * last_name : Kumar
     * mobile : 7377319978
     * email : ajit.kumar@rupeeboss.com
     * crn : 0
     * ss_id : 0
     * secret_key : SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL
     * client_key : CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T
     * birth_date : 1992-01-01
     * registration_no_1 : MH
     * registration_no_2 : 01
     * registration_no_3 : AA
     * registration_no_4 : 1234
     * posp_posp_id : 0
     * posp_fba_id : 0
     * posp_sm_posp_id : 0
     * posp_sm_posp_name : 0
     * posp_first_name : 0
     * posp_middle_name : 0
     * posp_last_name : 0
     * posp_email_id : 0
     * posp_agent_city : 0
     * posp_mobile_no : 0
     * posp_pan_no : 0
     * posp_aadhar : 0
     * posp_sources : 0
     * posp_ss_id : 0
     * posp_erp_id : 0
     * posp_gender : 0
     * posp_posp_category : 0
     * posp_reporting_agent_uid : 0
     * posp_reporting_agent_name : 0
     * posp_category : PolicyBoss
     * erp_source : FRESH-MTR
     */

    private String product_id;
    private String vehicle_id;
    private String rto_id;
    private String vehicle_insurance_type;
    private String vehicle_manf_date;
    private String vehicle_registration_date;
    private String policy_expiry_date;
    private String prev_insurer_id;
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
    private String voluntary_deductible;
    private String is_external_bifuel;
    private String is_aai_member;
    private Object external_bifuel_type;
    private String external_bifuel_value;
    private String pa_owner_driver_si;
    private String pa_named_passenger_si;
    private String pa_unnamed_passenger_si;
    private String pa_paid_driver_si;
    private String vehicle_expected_idv;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String mobile;
    private String email;
    private String crn;
    private String ss_id;
    private String secret_key;
    private String client_key;
    private String birth_date;
    private String registration_no_1;
    private String registration_no_2;
    private String registration_no_3;
    private String registration_no_4;
    private String posp_posp_id;
    private String posp_fba_id;
    private String posp_sm_posp_id;
    private String posp_sm_posp_name;
    private String posp_first_name;
    private String posp_middle_name;
    private String posp_last_name;
    private String posp_email_id;
    private String posp_agent_city;
    private String posp_mobile_no;
    private String posp_pan_no;
    private String posp_aadhar;
    private String posp_sources;
    private String posp_ss_id;
    private String posp_erp_id;
    private String posp_gender;
    private String posp_posp_category;
    private String posp_reporting_agent_uid;
    private String posp_reporting_agent_name;
    private String posp_category;
    private String erp_source;

    protected RequestCoreEntity(Parcel in) {
        product_id = in.readString();
        vehicle_id = in.readString();
        rto_id = in.readString();
        vehicle_insurance_type = in.readString();
        vehicle_manf_date = in.readString();
        vehicle_registration_date = in.readString();
        policy_expiry_date = in.readString();
        prev_insurer_id = in.readString();
        vehicle_registration_type = in.readString();
        vehicle_ncb_current = in.readString();
        is_claim_exists = in.readString();
        method_type = in.readString();
        execution_async = in.readString();
        electrical_accessory = in.readString();
        non_electrical_accessory = in.readString();
        registration_no = in.readString();
        is_llpd = in.readString();
        is_antitheft_fit = in.readString();
        voluntary_deductible = in.readString();
        is_external_bifuel = in.readString();
        is_aai_member = in.readString();
        external_bifuel_value = in.readString();
        pa_owner_driver_si = in.readString();
        pa_named_passenger_si = in.readString();
        pa_unnamed_passenger_si = in.readString();
        pa_paid_driver_si = in.readString();
        vehicle_expected_idv = in.readString();
        first_name = in.readString();
        middle_name = in.readString();
        last_name = in.readString();
        mobile = in.readString();
        email = in.readString();
        crn = in.readString();
        ss_id = in.readString();
        secret_key = in.readString();
        client_key = in.readString();
        birth_date = in.readString();
        registration_no_1 = in.readString();
        registration_no_2 = in.readString();
        registration_no_3 = in.readString();
        registration_no_4 = in.readString();
        posp_posp_id = in.readString();
        posp_fba_id = in.readString();
        posp_sm_posp_id = in.readString();
        posp_sm_posp_name = in.readString();
        posp_first_name = in.readString();
        posp_middle_name = in.readString();
        posp_last_name = in.readString();
        posp_email_id = in.readString();
        posp_agent_city = in.readString();
        posp_mobile_no = in.readString();
        posp_pan_no = in.readString();
        posp_aadhar = in.readString();
        posp_sources = in.readString();
        posp_ss_id = in.readString();
        posp_erp_id = in.readString();
        posp_gender = in.readString();
        posp_posp_category = in.readString();
        posp_reporting_agent_uid = in.readString();
        posp_reporting_agent_name = in.readString();
        posp_category = in.readString();
        erp_source = in.readString();
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getRto_id() {
        return rto_id;
    }

    public void setRto_id(String rto_id) {
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

    public String getPrev_insurer_id() {
        return prev_insurer_id;
    }

    public void setPrev_insurer_id(String prev_insurer_id) {
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

    public String getVoluntary_deductible() {
        return voluntary_deductible;
    }

    public void setVoluntary_deductible(String voluntary_deductible) {
        this.voluntary_deductible = voluntary_deductible;
    }

    public String getIs_external_bifuel() {
        return is_external_bifuel;
    }

    public void setIs_external_bifuel(String is_external_bifuel) {
        this.is_external_bifuel = is_external_bifuel;
    }

    public String getIs_aai_member() {
        return is_aai_member;
    }

    public void setIs_aai_member(String is_aai_member) {
        this.is_aai_member = is_aai_member;
    }

    public Object getExternal_bifuel_type() {
        return external_bifuel_type;
    }

    public void setExternal_bifuel_type(Object external_bifuel_type) {
        this.external_bifuel_type = external_bifuel_type;
    }

    public String getExternal_bifuel_value() {
        return external_bifuel_value;
    }

    public void setExternal_bifuel_value(String external_bifuel_value) {
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

    public String getVehicle_expected_idv() {
        return vehicle_expected_idv;
    }

    public void setVehicle_expected_idv(String vehicle_expected_idv) {
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

    public String getSs_id() {
        return ss_id;
    }

    public void setSs_id(String ss_id) {
        this.ss_id = ss_id;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getRegistration_no_1() {
        return registration_no_1;
    }

    public void setRegistration_no_1(String registration_no_1) {
        this.registration_no_1 = registration_no_1;
    }

    public String getRegistration_no_2() {
        return registration_no_2;
    }

    public void setRegistration_no_2(String registration_no_2) {
        this.registration_no_2 = registration_no_2;
    }

    public String getRegistration_no_3() {
        return registration_no_3;
    }

    public void setRegistration_no_3(String registration_no_3) {
        this.registration_no_3 = registration_no_3;
    }

    public String getRegistration_no_4() {
        return registration_no_4;
    }

    public void setRegistration_no_4(String registration_no_4) {
        this.registration_no_4 = registration_no_4;
    }

    public String getPosp_posp_id() {
        return posp_posp_id;
    }

    public void setPosp_posp_id(String posp_posp_id) {
        this.posp_posp_id = posp_posp_id;
    }

    public String getPosp_fba_id() {
        return posp_fba_id;
    }

    public void setPosp_fba_id(String posp_fba_id) {
        this.posp_fba_id = posp_fba_id;
    }

    public String getPosp_sm_posp_id() {
        return posp_sm_posp_id;
    }

    public void setPosp_sm_posp_id(String posp_sm_posp_id) {
        this.posp_sm_posp_id = posp_sm_posp_id;
    }

    public String getPosp_sm_posp_name() {
        return posp_sm_posp_name;
    }

    public void setPosp_sm_posp_name(String posp_sm_posp_name) {
        this.posp_sm_posp_name = posp_sm_posp_name;
    }

    public String getPosp_first_name() {
        return posp_first_name;
    }

    public void setPosp_first_name(String posp_first_name) {
        this.posp_first_name = posp_first_name;
    }

    public String getPosp_middle_name() {
        return posp_middle_name;
    }

    public void setPosp_middle_name(String posp_middle_name) {
        this.posp_middle_name = posp_middle_name;
    }

    public String getPosp_last_name() {
        return posp_last_name;
    }

    public void setPosp_last_name(String posp_last_name) {
        this.posp_last_name = posp_last_name;
    }

    public String getPosp_email_id() {
        return posp_email_id;
    }

    public void setPosp_email_id(String posp_email_id) {
        this.posp_email_id = posp_email_id;
    }

    public String getPosp_agent_city() {
        return posp_agent_city;
    }

    public void setPosp_agent_city(String posp_agent_city) {
        this.posp_agent_city = posp_agent_city;
    }

    public String getPosp_mobile_no() {
        return posp_mobile_no;
    }

    public void setPosp_mobile_no(String posp_mobile_no) {
        this.posp_mobile_no = posp_mobile_no;
    }

    public String getPosp_pan_no() {
        return posp_pan_no;
    }

    public void setPosp_pan_no(String posp_pan_no) {
        this.posp_pan_no = posp_pan_no;
    }

    public String getPosp_aadhar() {
        return posp_aadhar;
    }

    public void setPosp_aadhar(String posp_aadhar) {
        this.posp_aadhar = posp_aadhar;
    }

    public String getPosp_sources() {
        return posp_sources;
    }

    public void setPosp_sources(String posp_sources) {
        this.posp_sources = posp_sources;
    }

    public String getPosp_ss_id() {
        return posp_ss_id;
    }

    public void setPosp_ss_id(String posp_ss_id) {
        this.posp_ss_id = posp_ss_id;
    }

    public String getPosp_erp_id() {
        return posp_erp_id;
    }

    public void setPosp_erp_id(String posp_erp_id) {
        this.posp_erp_id = posp_erp_id;
    }

    public String getPosp_gender() {
        return posp_gender;
    }

    public void setPosp_gender(String posp_gender) {
        this.posp_gender = posp_gender;
    }

    public String getPosp_posp_category() {
        return posp_posp_category;
    }

    public void setPosp_posp_category(String posp_posp_category) {
        this.posp_posp_category = posp_posp_category;
    }

    public String getPosp_reporting_agent_uid() {
        return posp_reporting_agent_uid;
    }

    public void setPosp_reporting_agent_uid(String posp_reporting_agent_uid) {
        this.posp_reporting_agent_uid = posp_reporting_agent_uid;
    }

    public String getPosp_reporting_agent_name() {
        return posp_reporting_agent_name;
    }

    public void setPosp_reporting_agent_name(String posp_reporting_agent_name) {
        this.posp_reporting_agent_name = posp_reporting_agent_name;
    }

    public String getPosp_category() {
        return posp_category;
    }

    public void setPosp_category(String posp_category) {
        this.posp_category = posp_category;
    }

    public String getErp_source() {
        return erp_source;
    }

    public void setErp_source(String erp_source) {
        this.erp_source = erp_source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_id);
        dest.writeString(vehicle_id);
        dest.writeString(rto_id);
        dest.writeString(vehicle_insurance_type);
        dest.writeString(vehicle_manf_date);
        dest.writeString(vehicle_registration_date);
        dest.writeString(policy_expiry_date);
        dest.writeString(prev_insurer_id);
        dest.writeString(vehicle_registration_type);
        dest.writeString(vehicle_ncb_current);
        dest.writeString(is_claim_exists);
        dest.writeString(method_type);
        dest.writeString(execution_async);
        dest.writeString(electrical_accessory);
        dest.writeString(non_electrical_accessory);
        dest.writeString(registration_no);
        dest.writeString(is_llpd);
        dest.writeString(is_antitheft_fit);
        dest.writeString(voluntary_deductible);
        dest.writeString(is_external_bifuel);
        dest.writeString(is_aai_member);
        dest.writeString(external_bifuel_value);
        dest.writeString(pa_owner_driver_si);
        dest.writeString(pa_named_passenger_si);
        dest.writeString(pa_unnamed_passenger_si);
        dest.writeString(pa_paid_driver_si);
        dest.writeString(vehicle_expected_idv);
        dest.writeString(first_name);
        dest.writeString(middle_name);
        dest.writeString(last_name);
        dest.writeString(mobile);
        dest.writeString(email);
        dest.writeString(crn);
        dest.writeString(ss_id);
        dest.writeString(secret_key);
        dest.writeString(client_key);
        dest.writeString(birth_date);
        dest.writeString(registration_no_1);
        dest.writeString(registration_no_2);
        dest.writeString(registration_no_3);
        dest.writeString(registration_no_4);
        dest.writeString(posp_posp_id);
        dest.writeString(posp_fba_id);
        dest.writeString(posp_sm_posp_id);
        dest.writeString(posp_sm_posp_name);
        dest.writeString(posp_first_name);
        dest.writeString(posp_middle_name);
        dest.writeString(posp_last_name);
        dest.writeString(posp_email_id);
        dest.writeString(posp_agent_city);
        dest.writeString(posp_mobile_no);
        dest.writeString(posp_pan_no);
        dest.writeString(posp_aadhar);
        dest.writeString(posp_sources);
        dest.writeString(posp_ss_id);
        dest.writeString(posp_erp_id);
        dest.writeString(posp_gender);
        dest.writeString(posp_posp_category);
        dest.writeString(posp_reporting_agent_uid);
        dest.writeString(posp_reporting_agent_name);
        dest.writeString(posp_category);
        dest.writeString(erp_source);
    }
}
