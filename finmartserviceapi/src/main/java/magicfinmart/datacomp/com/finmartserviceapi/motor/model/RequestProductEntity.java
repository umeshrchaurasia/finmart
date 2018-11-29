package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestProductEntity implements Parcelable {
    public static final Creator<RequestProductEntity> CREATOR = new Creator<RequestProductEntity>() {
        @Override
        public RequestProductEntity createFromParcel(Parcel in) {
            return new RequestProductEntity(in);
        }

        @Override
        public RequestProductEntity[] newArray(int size) {
            return new RequestProductEntity[size];
        }
    };
    /**
     * vehicle_insurance_type : renew
     * vehicle_registration_type : individual
     * vehicle_registration_date : 2016-11-15
     * vehicle_id : 681
     * rto_id : 579
     * prev_insurer_id : 2
     * is_claim_exists : no
     * vehicle_ncb_current : 0
     * vehicle_manf_year : 2016
     * vehicle_ncb_next : 20
     * vehicle_age_year : 1
     * vehicle_age_month : 12
     * vehicle_expected_idv : 0
     * registration_no : MH-01-AA-1234
     * registration_no_1 : MH
     * registration_no_2 : 01
     * registration_no_3 : AA
     * registration_no_4 : 1234
     * policy_expiry_date : 2017-11-14
     * policy_start_date : 2017-11-15
     * pre_policy_start_date : 2016-11-15
     * policy_end_date : 2018-11-14
     * vehicle_manf_date : 2016-11-01
     * is_financed :
     * financial_institute_name :
     * financial_institute_code :
     * financial_institute_city :
     * financial_agreement_type :
     * previous_policy_number :
     * is_external_bifuel : no
     * external_bifuel_type :
     * external_bifuel_value : 0
     * electrical_accessory : 0
     * non_electrical_accessory : 0
     * engine_number :
     * chassis_number :
     * vehicle_color :
     * voluntary_deductible :
     * is_antitheft_fit : no
     * is_aai_member : no
     * is_llpd : no
     * pa_owner_driver_si : 100000
     * pa_named_passenger_si :
     * pa_unnamed_passenger_si :
     * pa_paid_driver_si :
     * addon_zero_dep_cover :
     * addon_road_assist_cover :
     * addon_ncb_protection_cover :
     * addon_engine_protector_cover :
     * addon_invoice_price_cover :
     * addon_key_lock_cover :
     * addon_consumable_cover :
     * addon_passenger_assistance_cover :
     * addon_flag :
     * addon_package_name : Titanium
     * addon_daily_allowance_cover : yes
     * addon_windshield_cover : yes
     * addon_tyre_coverage_cover : yes
     * addon_personal_belonging_loss_cover : yes
     * addon_inconvenience_allowance_cover : yes
     * addon_medical_expense_cover : yes
     * addon_hospital_cash_cover : yes
     * addon_ambulance_charge_cover : yes
     * addon_rodent_bite_cover : yes
     * addon_losstime_protection_cover : yes
     * addon_hydrostatic_lock_cover : yes
     */

    private String vehicle_insurance_type;
    private String vehicle_registration_type;
    private String vehicle_registration_date;
    private String vehicle_id;
    private String rto_id;
    private String prev_insurer_id;
    private String is_claim_exists;
    private String vehicle_ncb_current;
    private String vehicle_manf_year;
    private String vehicle_ncb_next;
    private String vehicle_age_year;
    private String vehicle_age_month;
    private String vehicle_expected_idv;
    private String registration_no;
    private String registration_no_1;
    private String registration_no_2;
    private String registration_no_3;
    private String registration_no_4;
    private String policy_expiry_date;
    private String policy_start_date;
    private String pre_policy_start_date;
    private String policy_end_date;
    private String vehicle_manf_date;
    private String is_financed;
    private String financial_institute_name;
    private String financial_institute_code;
    private String financial_institute_city;
    private String financial_agreement_type;
    private String previous_policy_number;
    private String is_external_bifuel;
    private String external_bifuel_type;
    private String external_bifuel_value;
    private String electrical_accessory;
    private String non_electrical_accessory;
    private String engine_number;
    private String chassis_number;
    private String vehicle_color;
    private String voluntary_deductible;
    private String is_antitheft_fit;
    private String is_aai_member;
    private String is_llpd;
    private String pa_owner_driver_si;
    private String pa_named_passenger_si;
    private String pa_unnamed_passenger_si;
    private String pa_paid_driver_si;
    private String addon_zero_dep_cover;
    private String addon_road_assist_cover;
    private String addon_ncb_protection_cover;
    private String addon_engine_protector_cover;
    private String addon_invoice_price_cover;
    private String addon_key_lock_cover;
    private String addon_consumable_cover;
    private String addon_passenger_assistance_cover;
    private String addon_flag;
    private String addon_package_name;
    private String addon_daily_allowance_cover;
    private String addon_windshield_cover;
    private String addon_tyre_coverage_cover;
    private String addon_personal_belonging_loss_cover;
    private String addon_inconvenience_allowance_cover;
    private String addon_medical_expense_cover;
    private String addon_hospital_cash_cover;
    private String addon_ambulance_charge_cover;
    private String addon_rodent_bite_cover;
    private String addon_losstime_protection_cover;
    private String addon_hydrostatic_lock_cover;

    protected RequestProductEntity(Parcel in) {
        vehicle_insurance_type = in.readString();
        vehicle_registration_type = in.readString();
        vehicle_registration_date = in.readString();
        vehicle_id = in.readString();
        rto_id = in.readString();
        prev_insurer_id = in.readString();
        is_claim_exists = in.readString();
        vehicle_ncb_current = in.readString();
        vehicle_manf_year = in.readString();
        vehicle_ncb_next = in.readString();
        vehicle_age_year = in.readString();
        vehicle_age_month = in.readString();
        vehicle_expected_idv = in.readString();
        registration_no = in.readString();
        registration_no_1 = in.readString();
        registration_no_2 = in.readString();
        registration_no_3 = in.readString();
        registration_no_4 = in.readString();
        policy_expiry_date = in.readString();
        policy_start_date = in.readString();
        pre_policy_start_date = in.readString();
        policy_end_date = in.readString();
        vehicle_manf_date = in.readString();
        is_financed = in.readString();
        financial_institute_name = in.readString();
        financial_institute_code = in.readString();
        financial_institute_city = in.readString();
        financial_agreement_type = in.readString();
        previous_policy_number = in.readString();
        is_external_bifuel = in.readString();
        external_bifuel_type = in.readString();
        external_bifuel_value = in.readString();
        electrical_accessory = in.readString();
        non_electrical_accessory = in.readString();
        engine_number = in.readString();
        chassis_number = in.readString();
        vehicle_color = in.readString();
        voluntary_deductible = in.readString();
        is_antitheft_fit = in.readString();
        is_aai_member = in.readString();
        is_llpd = in.readString();
        pa_owner_driver_si = in.readString();
        pa_named_passenger_si = in.readString();
        pa_unnamed_passenger_si = in.readString();
        pa_paid_driver_si = in.readString();
        addon_zero_dep_cover = in.readString();
        addon_road_assist_cover = in.readString();
        addon_ncb_protection_cover = in.readString();
        addon_engine_protector_cover = in.readString();
        addon_invoice_price_cover = in.readString();
        addon_key_lock_cover = in.readString();
        addon_consumable_cover = in.readString();
        addon_passenger_assistance_cover = in.readString();
        addon_flag = in.readString();
        addon_package_name = in.readString();
        addon_daily_allowance_cover = in.readString();
        addon_windshield_cover = in.readString();
        addon_tyre_coverage_cover = in.readString();
        addon_personal_belonging_loss_cover = in.readString();
        addon_inconvenience_allowance_cover = in.readString();
        addon_medical_expense_cover = in.readString();
        addon_hospital_cash_cover = in.readString();
        addon_ambulance_charge_cover = in.readString();
        addon_rodent_bite_cover = in.readString();
        addon_losstime_protection_cover = in.readString();
        addon_hydrostatic_lock_cover = in.readString();
    }

    public String getVehicle_insurance_type() {
        return vehicle_insurance_type;
    }

    public void setVehicle_insurance_type(String vehicle_insurance_type) {
        this.vehicle_insurance_type = vehicle_insurance_type;
    }

    public String getVehicle_registration_type() {
        return vehicle_registration_type;
    }

    public void setVehicle_registration_type(String vehicle_registration_type) {
        this.vehicle_registration_type = vehicle_registration_type;
    }

    public String getVehicle_registration_date() {
        return vehicle_registration_date;
    }

    public void setVehicle_registration_date(String vehicle_registration_date) {
        this.vehicle_registration_date = vehicle_registration_date;
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

    public String getPrev_insurer_id() {
        return prev_insurer_id;
    }

    public void setPrev_insurer_id(String prev_insurer_id) {
        this.prev_insurer_id = prev_insurer_id;
    }

    public String getIs_claim_exists() {
        return is_claim_exists;
    }

    public void setIs_claim_exists(String is_claim_exists) {
        this.is_claim_exists = is_claim_exists;
    }

    public String getVehicle_ncb_current() {
        return vehicle_ncb_current;
    }

    public void setVehicle_ncb_current(String vehicle_ncb_current) {
        this.vehicle_ncb_current = vehicle_ncb_current;
    }

    public String getVehicle_manf_year() {
        return vehicle_manf_year;
    }

    public void setVehicle_manf_year(String vehicle_manf_year) {
        this.vehicle_manf_year = vehicle_manf_year;
    }

    public String getVehicle_ncb_next() {
        return vehicle_ncb_next;
    }

    public void setVehicle_ncb_next(String vehicle_ncb_next) {
        this.vehicle_ncb_next = vehicle_ncb_next;
    }

    public String getVehicle_age_year() {
        return vehicle_age_year;
    }

    public void setVehicle_age_year(String vehicle_age_year) {
        this.vehicle_age_year = vehicle_age_year;
    }

    public String getVehicle_age_month() {
        return vehicle_age_month;
    }

    public void setVehicle_age_month(String vehicle_age_month) {
        this.vehicle_age_month = vehicle_age_month;
    }

    public String getVehicle_expected_idv() {
        return vehicle_expected_idv;
    }

    public void setVehicle_expected_idv(String vehicle_expected_idv) {
        this.vehicle_expected_idv = vehicle_expected_idv;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
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

    public String getPolicy_expiry_date() {
        return policy_expiry_date;
    }

    public void setPolicy_expiry_date(String policy_expiry_date) {
        this.policy_expiry_date = policy_expiry_date;
    }

    public String getPolicy_start_date() {
        return policy_start_date;
    }

    public void setPolicy_start_date(String policy_start_date) {
        this.policy_start_date = policy_start_date;
    }

    public String getPre_policy_start_date() {
        return pre_policy_start_date;
    }

    public void setPre_policy_start_date(String pre_policy_start_date) {
        this.pre_policy_start_date = pre_policy_start_date;
    }

    public String getPolicy_end_date() {
        return policy_end_date;
    }

    public void setPolicy_end_date(String policy_end_date) {
        this.policy_end_date = policy_end_date;
    }

    public String getVehicle_manf_date() {
        return vehicle_manf_date;
    }

    public void setVehicle_manf_date(String vehicle_manf_date) {
        this.vehicle_manf_date = vehicle_manf_date;
    }

    public String getIs_financed() {
        return is_financed;
    }

    public void setIs_financed(String is_financed) {
        this.is_financed = is_financed;
    }

    public String getFinancial_institute_name() {
        return financial_institute_name;
    }

    public void setFinancial_institute_name(String financial_institute_name) {
        this.financial_institute_name = financial_institute_name;
    }

    public String getFinancial_institute_code() {
        return financial_institute_code;
    }

    public void setFinancial_institute_code(String financial_institute_code) {
        this.financial_institute_code = financial_institute_code;
    }

    public String getFinancial_institute_city() {
        return financial_institute_city;
    }

    public void setFinancial_institute_city(String financial_institute_city) {
        this.financial_institute_city = financial_institute_city;
    }

    public String getFinancial_agreement_type() {
        return financial_agreement_type;
    }

    public void setFinancial_agreement_type(String financial_agreement_type) {
        this.financial_agreement_type = financial_agreement_type;
    }

    public String getPrevious_policy_number() {
        return previous_policy_number;
    }

    public void setPrevious_policy_number(String previous_policy_number) {
        this.previous_policy_number = previous_policy_number;
    }

    public String getIs_external_bifuel() {
        return is_external_bifuel;
    }

    public void setIs_external_bifuel(String is_external_bifuel) {
        this.is_external_bifuel = is_external_bifuel;
    }

    public String getExternal_bifuel_type() {
        return external_bifuel_type;
    }

    public void setExternal_bifuel_type(String external_bifuel_type) {
        this.external_bifuel_type = external_bifuel_type;
    }

    public String getExternal_bifuel_value() {
        return external_bifuel_value;
    }

    public void setExternal_bifuel_value(String external_bifuel_value) {
        this.external_bifuel_value = external_bifuel_value;
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

    public String getEngine_number() {
        return engine_number;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public String getChassis_number() {
        return chassis_number;
    }

    public void setChassis_number(String chassis_number) {
        this.chassis_number = chassis_number;
    }

    public String getVehicle_color() {
        return vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }

    public String getVoluntary_deductible() {
        return voluntary_deductible;
    }

    public void setVoluntary_deductible(String voluntary_deductible) {
        this.voluntary_deductible = voluntary_deductible;
    }

    public String getIs_antitheft_fit() {
        return is_antitheft_fit;
    }

    public void setIs_antitheft_fit(String is_antitheft_fit) {
        this.is_antitheft_fit = is_antitheft_fit;
    }

    public String getIs_aai_member() {
        return is_aai_member;
    }

    public void setIs_aai_member(String is_aai_member) {
        this.is_aai_member = is_aai_member;
    }

    public String getIs_llpd() {
        return is_llpd;
    }

    public void setIs_llpd(String is_llpd) {
        this.is_llpd = is_llpd;
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

    public String getAddon_zero_dep_cover() {
        return addon_zero_dep_cover;
    }

    public void setAddon_zero_dep_cover(String addon_zero_dep_cover) {
        this.addon_zero_dep_cover = addon_zero_dep_cover;
    }

    public String getAddon_road_assist_cover() {
        return addon_road_assist_cover;
    }

    public void setAddon_road_assist_cover(String addon_road_assist_cover) {
        this.addon_road_assist_cover = addon_road_assist_cover;
    }

    public String getAddon_ncb_protection_cover() {
        return addon_ncb_protection_cover;
    }

    public void setAddon_ncb_protection_cover(String addon_ncb_protection_cover) {
        this.addon_ncb_protection_cover = addon_ncb_protection_cover;
    }

    public String getAddon_engine_protector_cover() {
        return addon_engine_protector_cover;
    }

    public void setAddon_engine_protector_cover(String addon_engine_protector_cover) {
        this.addon_engine_protector_cover = addon_engine_protector_cover;
    }

    public String getAddon_invoice_price_cover() {
        return addon_invoice_price_cover;
    }

    public void setAddon_invoice_price_cover(String addon_invoice_price_cover) {
        this.addon_invoice_price_cover = addon_invoice_price_cover;
    }

    public String getAddon_key_lock_cover() {
        return addon_key_lock_cover;
    }

    public void setAddon_key_lock_cover(String addon_key_lock_cover) {
        this.addon_key_lock_cover = addon_key_lock_cover;
    }

    public String getAddon_consumable_cover() {
        return addon_consumable_cover;
    }

    public void setAddon_consumable_cover(String addon_consumable_cover) {
        this.addon_consumable_cover = addon_consumable_cover;
    }

    public String getAddon_passenger_assistance_cover() {
        return addon_passenger_assistance_cover;
    }

    public void setAddon_passenger_assistance_cover(String addon_passenger_assistance_cover) {
        this.addon_passenger_assistance_cover = addon_passenger_assistance_cover;
    }

    public String getAddon_flag() {
        return addon_flag;
    }

    public void setAddon_flag(String addon_flag) {
        this.addon_flag = addon_flag;
    }

    public String getAddon_package_name() {
        return addon_package_name;
    }

    public void setAddon_package_name(String addon_package_name) {
        this.addon_package_name = addon_package_name;
    }

    public String getAddon_daily_allowance_cover() {
        return addon_daily_allowance_cover;
    }

    public void setAddon_daily_allowance_cover(String addon_daily_allowance_cover) {
        this.addon_daily_allowance_cover = addon_daily_allowance_cover;
    }

    public String getAddon_windshield_cover() {
        return addon_windshield_cover;
    }

    public void setAddon_windshield_cover(String addon_windshield_cover) {
        this.addon_windshield_cover = addon_windshield_cover;
    }

    public String getAddon_tyre_coverage_cover() {
        return addon_tyre_coverage_cover;
    }

    public void setAddon_tyre_coverage_cover(String addon_tyre_coverage_cover) {
        this.addon_tyre_coverage_cover = addon_tyre_coverage_cover;
    }

    public String getAddon_personal_belonging_loss_cover() {
        return addon_personal_belonging_loss_cover;
    }

    public void setAddon_personal_belonging_loss_cover(String addon_personal_belonging_loss_cover) {
        this.addon_personal_belonging_loss_cover = addon_personal_belonging_loss_cover;
    }

    public String getAddon_inconvenience_allowance_cover() {
        return addon_inconvenience_allowance_cover;
    }

    public void setAddon_inconvenience_allowance_cover(String addon_inconvenience_allowance_cover) {
        this.addon_inconvenience_allowance_cover = addon_inconvenience_allowance_cover;
    }

    public String getAddon_medical_expense_cover() {
        return addon_medical_expense_cover;
    }

    public void setAddon_medical_expense_cover(String addon_medical_expense_cover) {
        this.addon_medical_expense_cover = addon_medical_expense_cover;
    }

    public String getAddon_hospital_cash_cover() {
        return addon_hospital_cash_cover;
    }

    public void setAddon_hospital_cash_cover(String addon_hospital_cash_cover) {
        this.addon_hospital_cash_cover = addon_hospital_cash_cover;
    }

    public String getAddon_ambulance_charge_cover() {
        return addon_ambulance_charge_cover;
    }

    public void setAddon_ambulance_charge_cover(String addon_ambulance_charge_cover) {
        this.addon_ambulance_charge_cover = addon_ambulance_charge_cover;
    }

    public String getAddon_rodent_bite_cover() {
        return addon_rodent_bite_cover;
    }

    public void setAddon_rodent_bite_cover(String addon_rodent_bite_cover) {
        this.addon_rodent_bite_cover = addon_rodent_bite_cover;
    }

    public String getAddon_losstime_protection_cover() {
        return addon_losstime_protection_cover;
    }

    public void setAddon_losstime_protection_cover(String addon_losstime_protection_cover) {
        this.addon_losstime_protection_cover = addon_losstime_protection_cover;
    }

    public String getAddon_hydrostatic_lock_cover() {
        return addon_hydrostatic_lock_cover;
    }

    public void setAddon_hydrostatic_lock_cover(String addon_hydrostatic_lock_cover) {
        this.addon_hydrostatic_lock_cover = addon_hydrostatic_lock_cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(vehicle_insurance_type);
        dest.writeString(vehicle_registration_type);
        dest.writeString(vehicle_registration_date);
        dest.writeString(vehicle_id);
        dest.writeString(rto_id);
        dest.writeString(prev_insurer_id);
        dest.writeString(is_claim_exists);
        dest.writeString(vehicle_ncb_current);
        dest.writeString(vehicle_manf_year);
        dest.writeString(vehicle_ncb_next);
        dest.writeString(vehicle_age_year);
        dest.writeString(vehicle_age_month);
        dest.writeString(vehicle_expected_idv);
        dest.writeString(registration_no);
        dest.writeString(registration_no_1);
        dest.writeString(registration_no_2);
        dest.writeString(registration_no_3);
        dest.writeString(registration_no_4);
        dest.writeString(policy_expiry_date);
        dest.writeString(policy_start_date);
        dest.writeString(pre_policy_start_date);
        dest.writeString(policy_end_date);
        dest.writeString(vehicle_manf_date);
        dest.writeString(is_financed);
        dest.writeString(financial_institute_name);
        dest.writeString(financial_institute_code);
        dest.writeString(financial_institute_city);
        dest.writeString(financial_agreement_type);
        dest.writeString(previous_policy_number);
        dest.writeString(is_external_bifuel);
        dest.writeString(external_bifuel_type);
        dest.writeString(external_bifuel_value);
        dest.writeString(electrical_accessory);
        dest.writeString(non_electrical_accessory);
        dest.writeString(engine_number);
        dest.writeString(chassis_number);
        dest.writeString(vehicle_color);
        dest.writeString(voluntary_deductible);
        dest.writeString(is_antitheft_fit);
        dest.writeString(is_aai_member);
        dest.writeString(is_llpd);
        dest.writeString(pa_owner_driver_si);
        dest.writeString(pa_named_passenger_si);
        dest.writeString(pa_unnamed_passenger_si);
        dest.writeString(pa_paid_driver_si);
        dest.writeString(addon_zero_dep_cover);
        dest.writeString(addon_road_assist_cover);
        dest.writeString(addon_ncb_protection_cover);
        dest.writeString(addon_engine_protector_cover);
        dest.writeString(addon_invoice_price_cover);
        dest.writeString(addon_key_lock_cover);
        dest.writeString(addon_consumable_cover);
        dest.writeString(addon_passenger_assistance_cover);
        dest.writeString(addon_flag);
        dest.writeString(addon_package_name);
        dest.writeString(addon_daily_allowance_cover);
        dest.writeString(addon_windshield_cover);
        dest.writeString(addon_tyre_coverage_cover);
        dest.writeString(addon_personal_belonging_loss_cover);
        dest.writeString(addon_inconvenience_allowance_cover);
        dest.writeString(addon_medical_expense_cover);
        dest.writeString(addon_hospital_cash_cover);
        dest.writeString(addon_ambulance_charge_cover);
        dest.writeString(addon_rodent_bite_cover);
        dest.writeString(addon_losstime_protection_cover);
        dest.writeString(addon_hydrostatic_lock_cover);
    }
}
