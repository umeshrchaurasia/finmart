package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseEntity implements Cloneable, Parcelable {
    /**
     * Service_Log_Id : 100404
     * Service_Log_Unique_Id : ARN-TB5DBPM1-2SO8-OC26-HCCN-PPWBFPCARHLC
     * Insurer_Transaction_Identifier :
     * Error_Code :
     * Created_On : 2017-11-14T09:17:15.368Z
     * Product_Id : 1
     * Insurer_Id : 4
     * Status : complete
     * Plan_Id : 5
     * Plan_Name : RoadSide
     * LM_Custom_Request : {"dbmaster_insurer_vehicle_exshowroom":409341,"vehicle_expected_idv":349987,"vehicle_max_idv":427761,"vehicle_min_idv":349987,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":388874}
     * Premium_Breakup : {"own_damage":{"od_basic":11490.07,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":2298.01,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":9192.06},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"addon":{"addon_zero_dep_cover":0,"addon_road_assist_cover":500,"addon_ncb_protection_cover":0,"addon_engine_protector_cover":0,"addon_invoice_price_cover":0,"addon_key_lock_cover":0,"addon_consumable_cover":0,"addon_daily_allowance_cover":0,"addon_windshield_cover":0,"addon_passenger_assistance_cover":0,"addon_tyre_coverage_cover":0,"addon_personal_belonging_loss_cover":0,"addon_inconvenience_allowance_cover":0,"addon_medical_expense_cover":0,"addon_hospital_cash_cover":0,"addon_ambulance_charge_cover":0,"addon_rodent_bite_cover":0,"addon_losstime_protection_cover":0,"addon_hydrostatic_lock_cover":0,"addon_guaranteed_auto_protection_cover":0,"addon_final_premium":0},"net_premium":12155.06,"service_tax":2187.91,"final_premium":14342.97}
     * Insurer : {"_id":"59b7d7ad63237647de1f2a83","Insurer_Code":"Future Generali","Insurer_ID":4,"Insurer_Logo_Name":"Future_Generali_General.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"Future Generali India Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":" 6th Floor, Tower - 3, Indiabulls Finance Center, Senapati Bapat Marg, Elphinstone Road, Mumbai","PreviousInsurer_Pincode":400013}
     * Call_Execution_Time : 5.63
     */

    private String Service_Log_Id;
    private String Service_Log_Unique_Id;
    private String Insurer_Transaction_Identifier;
    private String Error_Code;
    private String Created_On;
    private String Product_Id;
    private String Insurer_Id;
    @SerializedName("Status")
    private String StatusX;
    private String Plan_Id;
    private String Plan_Name;
    private LMCustomRequestEntity LM_Custom_Request;
    private PremiumBreakupEntity Premium_Breakup;
    private InsurerEntity Insurer;
    private String Call_Execution_Time;

    //added by Nilesh : Require for applied premium tracking
    private List<AppliedAddonsPremiumBreakup> listAppliedAddons = new ArrayList<AppliedAddonsPremiumBreakup>();
    List<PremiumBreakUpAddonEntity> premiumBreakUpAddonEntities = new ArrayList<PremiumBreakUpAddonEntity>();
    private AddonEntity Addon_List;
    private boolean isAddonApplied;
    private String final_premium_with_addon;

    private boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<PremiumBreakUpAddonEntity> getPremiumBreakUpAddonEntities() {
        return premiumBreakUpAddonEntities;
    }

    public void setPremiumBreakUpAddonEntities(List<PremiumBreakUpAddonEntity> premiumBreakUpAddonEntities) {
        this.premiumBreakUpAddonEntities = premiumBreakUpAddonEntities;
    }

    public String getTotalAddonAplied() {
        return totalAddonAplied;
    }

    public void setTotalAddonAplied(String totalAddonAplied) {
        this.totalAddonAplied = totalAddonAplied;
    }

    public String getTotalGST() {
        return totalGST;
    }

    public void setTotalGST(String totalGST) {
        this.totalGST = totalGST;
    }

    private String totalAddonAplied;
    private String totalGST;
    private String final_premium_without_addon;

    public String getFinal_premium_without_addon() {
        return final_premium_without_addon;
    }

    public void setFinal_premium_without_addon(String final_premium_without_addon) {
        this.final_premium_without_addon = final_premium_without_addon;
    }


    public boolean isAddonApplied() {
        return isAddonApplied;
    }

    public void setAddonApplied(boolean addonApplied) {
        isAddonApplied = addonApplied;
    }

    public String getFinal_premium_with_addon() {
        return final_premium_with_addon;
    }

    public void setFinal_premium_with_addon(String final_premium_with_addon) {
        this.final_premium_with_addon = final_premium_with_addon;
    }

    /**
     * Service_Log_Id : 105851
     * Insurer_Id : 5
     * Insurer : {"_id":"59b7d7ad63237647de1f2a85","Insurer_Code":"HDFC ERGO","Insurer_ID":5,"Insurer_Logo_Name":"hdfc.png","Insurer_Logo_Name_Mobile":"","Insurer_Name":"HDFC ERGO General Insurance Co. Ltd.","IsActive":1,"IsInternal":"","PreviousInsurer_Address":"","PreviousInsurer_Pincode":""}
     * Premium_Breakup : {"own_damage":{"od_basic":7292,"od_elect_access":0,"od_non_elect_access":0,"od_cng_lpg":0,"od_disc_ncb":0,"od_disc_vol_deduct":0,"od_disc_anti_theft":0,"od_disc_aai":0,"od_loading":0,"od_disc":0,"od_final_premium":7292},"liability":{"tp_basic":2863,"tp_cover_owner_driver_pa":100,"tp_cover_unnamed_passenger_pa":0,"tp_cover_named_passenger_pa":0,"tp_cover_paid_driver_pa":0,"tp_cover_paid_driver_ll":0,"tp_cng_lpg":0,"tp_final_premium":2963},"net_premium":10255,"service_tax":1845.8999999999999,"final_premium":12101}
     * Premium_Rate : null
     * Addon_List : {"addon_zero_dep_cover":1333,"addon_road_assist_cover":350,"addon_ncb_protection_cover":237,"addon_engine_protector_cover":355,"addon_consumable_cover":296}
     * Plan_List : [{"Plan_Id":17,"Plan_Name":"Platinum","Service_Log_Id":105851,"Service_Log_Unique_Id":"ARN-IGXFP4KP-R6FD-BTRC-AL0Q-0HUOXK2LLXXO","Insurer_Transaction_Identifier":"8d8b742a-4c17-447d-ba27-38ea7bd9933d","Plan_Addon_Breakup":{"addon_zero_dep_cover":1333,"addon_road_assist_cover":350,"addon_ncb_protection_cover":237,"addon_engine_protector_cover":355},"Plan_Addon_Premium":2275},{"Plan_Id":16,"Plan_Name":"Gold","Service_Log_Id":105850,"Service_Log_Unique_Id":"ARN-JPTKHIIP-Q8P3-L0LC-XPRV-UWMJS2FA8IYL","Insurer_Transaction_Identifier":"475b79f6-40f1-44e9-9044-09eff1087cf9","Plan_Addon_Breakup":{"addon_road_assist_cover":350},"Plan_Addon_Premium":350},{"Plan_Id":14,"Plan_Name":"Basic","Service_Log_Id":105849,"Service_Log_Unique_Id":"ARN-TB48IVYA-A6L4-TRWC-TDIK-LPFPPVIZ785O","Insurer_Transaction_Identifier":"5bbb1e18-e686-4592-878a-b25a19e1a675","Plan_Addon_Breakup":{},"Plan_Addon_Premium":0},{"Plan_Id":15,"Plan_Name":"Silver","Service_Log_Id":105852,"Service_Log_Unique_Id":"ARN-18EU79RL-EUJQ-LHY3-63DT-FMDCAYK7IXIK","Insurer_Transaction_Identifier":"721bbbe5-1f2a-40f5-ba25-91cae5df4cf4","Plan_Addon_Breakup":{"addon_zero_dep_cover":1333,"addon_road_assist_cover":350},"Plan_Addon_Premium":1683},{"Plan_Id":18,"Plan_Name":"Titanium","Service_Log_Id":105853,"Service_Log_Unique_Id":"ARN-ECZAVNEU-MZMF-ZSQE-M0GG-CNMNE0MHKUDP","Insurer_Transaction_Identifier":"5aec4d34-6915-4c97-8d1e-4631ab1c88b5","Plan_Addon_Breakup":{"addon_zero_dep_cover":1333,"addon_road_assist_cover":350,"addon_ncb_protection_cover":237,"addon_engine_protector_cover":355,"addon_consumable_cover":296},"Plan_Addon_Premium":2571}]
     * LM_Custom_Request : {"dbmaster_insurer_vehicle_exshowroom":435585,"vehicle_expected_idv":296198,"vehicle_max_idv":400738,"vehicle_min_idv":296198,"vehicle_ncb_current":"0","vehicle_ncb_next":"0","vehicle_normal_idv":348468}
     * Completion_Summary : {"Total":5,"Completed":5,"Status":"complete"}
     */


    public List<AppliedAddonsPremiumBreakup> getListAppliedAddons() {
        return listAppliedAddons;
    }

    public void setListAppliedAddons(List<AppliedAddonsPremiumBreakup> listAppliedAddons) {
        this.listAppliedAddons = listAppliedAddons;
    }

    public AddonEntity getAddon_List() {
        return Addon_List;
    }

    public void setAddon_List(AddonEntity addon_List) {
        Addon_List = addon_List;
    }

    public String getService_Log_Id() {
        return Service_Log_Id;
    }

    public void setService_Log_Id(String Service_Log_Id) {
        this.Service_Log_Id = Service_Log_Id;
    }

    public String getService_Log_Unique_Id() {
        return Service_Log_Unique_Id;
    }

    public void setService_Log_Unique_Id(String Service_Log_Unique_Id) {
        this.Service_Log_Unique_Id = Service_Log_Unique_Id;
    }

    public String getInsurer_Transaction_Identifier() {
        return Insurer_Transaction_Identifier;
    }

    public void setInsurer_Transaction_Identifier(String Insurer_Transaction_Identifier) {
        this.Insurer_Transaction_Identifier = Insurer_Transaction_Identifier;
    }

    public String getError_Code() {
        return Error_Code;
    }

    public void setError_Code(String Error_Code) {
        this.Error_Code = Error_Code;
    }

    public String getCreated_On() {
        return Created_On;
    }

    public void setCreated_On(String Created_On) {
        this.Created_On = Created_On;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String Product_Id) {
        this.Product_Id = Product_Id;
    }

    public String getInsurer_Id() {
        return Insurer_Id;
    }

    public void setInsurer_Id(String Insurer_Id) {
        this.Insurer_Id = Insurer_Id;
    }

    public String getStatusX() {
        return StatusX;
    }

    public void setStatusX(String StatusX) {
        this.StatusX = StatusX;
    }

    public String getPlan_Id() {
        return Plan_Id;
    }

    public void setPlan_Id(String Plan_Id) {
        this.Plan_Id = Plan_Id;
    }

    public String getPlan_Name() {
        return Plan_Name;
    }

    public void setPlan_Name(String Plan_Name) {
        this.Plan_Name = Plan_Name;
    }

    public LMCustomRequestEntity getLM_Custom_Request() {
        return LM_Custom_Request;
    }

    public void setLM_Custom_Request(LMCustomRequestEntity LM_Custom_Request) {
        this.LM_Custom_Request = LM_Custom_Request;
    }

    public PremiumBreakupEntity getPremium_Breakup() {
        return Premium_Breakup;
    }

    public void setPremium_Breakup(PremiumBreakupEntity Premium_Breakup) {
        this.Premium_Breakup = Premium_Breakup;
    }

    public InsurerEntity getInsurer() {
        return Insurer;
    }

    public void setInsurer(InsurerEntity Insurer) {
        this.Insurer = Insurer;
    }

    public String getCall_Execution_Time() {
        return Call_Execution_Time;
    }

    public void setCall_Execution_Time(String Call_Execution_Time) {
        this.Call_Execution_Time = Call_Execution_Time;
    }


    public ResponseEntity() {
    }

    public Object clone() throws
            CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Service_Log_Id);
        dest.writeString(this.Service_Log_Unique_Id);
        dest.writeString(this.Insurer_Transaction_Identifier);
        dest.writeString(this.Error_Code);
        dest.writeString(this.Created_On);
        dest.writeString(this.Product_Id);
        dest.writeString(this.Insurer_Id);
        dest.writeString(this.StatusX);
        dest.writeString(this.Plan_Id);
        dest.writeString(this.Plan_Name);
        dest.writeParcelable(this.LM_Custom_Request, flags);
        dest.writeParcelable(this.Premium_Breakup, flags);
        dest.writeParcelable(this.Insurer, flags);
        dest.writeString(this.Call_Execution_Time);
        dest.writeTypedList(this.listAppliedAddons);
        dest.writeTypedList(this.premiumBreakUpAddonEntities);
        dest.writeParcelable(this.Addon_List, flags);
        dest.writeByte(this.isAddonApplied ? (byte) 1 : (byte) 0);
        dest.writeString(this.final_premium_with_addon);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        dest.writeString(this.totalAddonAplied);
        dest.writeString(this.totalGST);
        dest.writeString(this.final_premium_without_addon);
    }

    protected ResponseEntity(Parcel in) {
        this.Service_Log_Id = in.readString();
        this.Service_Log_Unique_Id = in.readString();
        this.Insurer_Transaction_Identifier = in.readString();
        this.Error_Code = in.readString();
        this.Created_On = in.readString();
        this.Product_Id = in.readString();
        this.Insurer_Id = in.readString();
        this.StatusX = in.readString();
        this.Plan_Id = in.readString();
        this.Plan_Name = in.readString();
        this.LM_Custom_Request = in.readParcelable(LMCustomRequestEntity.class.getClassLoader());
        this.Premium_Breakup = in.readParcelable(PremiumBreakupEntity.class.getClassLoader());
        this.Insurer = in.readParcelable(InsurerEntity.class.getClassLoader());
        this.Call_Execution_Time = in.readString();
        this.listAppliedAddons = in.createTypedArrayList(AppliedAddonsPremiumBreakup.CREATOR);
        this.premiumBreakUpAddonEntities = in.createTypedArrayList(PremiumBreakUpAddonEntity.CREATOR);
        this.Addon_List = in.readParcelable(AddonEntity.class.getClassLoader());
        this.isAddonApplied = in.readByte() != 0;
        this.final_premium_with_addon = in.readString();
        this.isSelected = in.readByte() != 0;
        this.totalAddonAplied = in.readString();
        this.totalGST = in.readString();
        this.final_premium_without_addon = in.readString();
    }

    public static final Parcelable.Creator<ResponseEntity> CREATOR = new Parcelable.Creator<ResponseEntity>() {
        @Override
        public ResponseEntity createFromParcel(Parcel source) {
            return new ResponseEntity(source);
        }

        @Override
        public ResponseEntity[] newArray(int size) {
            return new ResponseEntity[size];
        }
    };
}