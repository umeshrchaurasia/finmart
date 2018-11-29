package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FastLaneDataEntity implements Parcelable {

    /**
     * Variant_Name : 1.2 GLX
     * RTO_Name : Thane
     * RTO_Code : 582
     * VehicleCity_Id : 582
     * Purchase_Date : Invalid date
     * FastLaneId : 207
     * Registration_Date : 30/10/2010
     * Engin_Number : 0
     * Chassis_Number : 0
     * Registration_Number : MH04EQ2620
     * Manufacture_Year : 2008
     * Cubic_Capacity : 1193
     * Seating_Capacity : 5
     * Fuel_Type : Petrol
     * Model_Name : Indigo CS
     * Make_Name : Tata
     * Fuel_ID : 1
     * Model_ID : 202
     * Variant_Id : 4659
     * FastlaneResponse : {"status":100,"found_by":"regn_no","timestamp":"Wed Nov 22 12:24:43 IST 2017","error_code":null,"description":"Record found","results":[{"vehicle":{"regn_no":null,"state_cd":null,"rto_cd":null,"rto_name":null,"chasi_no":null,"eng_no":null,"regn_dt":"30/10/2010","purchase_dt":null,"vh_class_desc":null,"fla_vh_class_desc":null,"owner_sr":null,"owner_cd_desc":null,"regn_type":null,"regn_type_desc":null,"c_city":null,"c_pincode":null,"p_city":null,"p_pincode":null,"vehicle_cd":"138004030108","maker_desc":"TML    TATA MOTORS LTD","fla_maker_desc":"TATA","series":"NOT APPLICABLE","maker_model":"INDIGO CS EGLX MPFI BSIV","fla_model_desc":"INDIGO","fla_variant":null,"fla_sub_variant":null,"color":"S GOLD","fuel_type_desc":"PETROL","fla_fuel_type_desc":"PETROL","cubic_cap":"1193","fla_cubic_cap":"1193","manu_yr":2010,"seat_cap":5,"fla_seat_cap":5},"hypth":null,"insurance":null}]}
     * Vehicle_Detail_Id : 381
     * _id : 5a741cadfa8eb9195ce0ae02
     */

    private String Variant_Name;
    private String RTO_Name;
    private String RTO_Code;
    private String VehicleCity_Id;
    private String Purchase_Date;
    private int FastLaneId;
    private String Registration_Date;
    private String Engin_Number;
    private String Chassis_Number;
    private String Registration_Number;
    private String Manufacture_Year;
    private String Cubic_Capacity;
    private String Seating_Capacity;
    private String Fuel_Type;
    private String Model_Name;
    private String Make_Name;
    private String Fuel_ID;
    private String Model_ID;
    private String Variant_Id;
    private String FastlaneResponse;
    private int Vehicle_Detail_Id;
    private String _id;
    /**
     * __v : 0
     * Purchase_Date : null
     * Color : C WHITE
     * Created_On : 2018-02-07T10:40:55.573Z
     */

    private int __v;
    private String Color;
    private String Created_On;

    public String getVariant_Name() {
        return Variant_Name;
    }

    public void setVariant_Name(String Variant_Name) {
        this.Variant_Name = Variant_Name;
    }

    public String getRTO_Name() {
        return RTO_Name;
    }

    public void setRTO_Name(String RTO_Name) {
        this.RTO_Name = RTO_Name;
    }

    public String getRTO_Code() {
        return RTO_Code;
    }

    public void setRTO_Code(String RTO_Code) {
        this.RTO_Code = RTO_Code;
    }

    public String getVehicleCity_Id() {
        return VehicleCity_Id;
    }

    public void setVehicleCity_Id(String VehicleCity_Id) {
        this.VehicleCity_Id = VehicleCity_Id;
    }

    public String getPurchase_Date() {
        return Purchase_Date;
    }

    public void setPurchase_Date(String Purchase_Date) {
        this.Purchase_Date = Purchase_Date;
    }

    public int getFastLaneId() {
        return FastLaneId;
    }

    public void setFastLaneId(int FastLaneId) {
        this.FastLaneId = FastLaneId;
    }

    public String getRegistration_Date() {
        return Registration_Date;
    }

    public void setRegistration_Date(String Registration_Date) {
        this.Registration_Date = Registration_Date;
    }

    public String getEngin_Number() {
        return Engin_Number;
    }

    public void setEngin_Number(String Engin_Number) {
        this.Engin_Number = Engin_Number;
    }

    public String getChassis_Number() {
        return Chassis_Number;
    }

    public void setChassis_Number(String Chassis_Number) {
        this.Chassis_Number = Chassis_Number;
    }

    public String getRegistration_Number() {
        return Registration_Number;
    }

    public void setRegistration_Number(String Registration_Number) {
        this.Registration_Number = Registration_Number;
    }

    public String getManufacture_Year() {
        return Manufacture_Year;
    }

    public void setManufacture_Year(String Manufacture_Year) {
        this.Manufacture_Year = Manufacture_Year;
    }

    public String getCubic_Capacity() {
        return Cubic_Capacity;
    }

    public void setCubic_Capacity(String Cubic_Capacity) {
        this.Cubic_Capacity = Cubic_Capacity;
    }

    public String getSeating_Capacity() {
        return Seating_Capacity;
    }

    public void setSeating_Capacity(String Seating_Capacity) {
        this.Seating_Capacity = Seating_Capacity;
    }

    public String getFuel_Type() {
        return Fuel_Type;
    }

    public void setFuel_Type(String Fuel_Type) {
        this.Fuel_Type = Fuel_Type;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String Model_Name) {
        this.Model_Name = Model_Name;
    }

    public String getMake_Name() {
        return Make_Name;
    }

    public void setMake_Name(String Make_Name) {
        this.Make_Name = Make_Name;
    }

    public String getFuel_ID() {
        return Fuel_ID;
    }

    public void setFuel_ID(String Fuel_ID) {
        this.Fuel_ID = Fuel_ID;
    }

    public String getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(String Model_ID) {
        this.Model_ID = Model_ID;
    }

    public String getVariant_Id() {
        return Variant_Id;
    }

    public void setVariant_Id(String Variant_Id) {
        this.Variant_Id = Variant_Id;
    }

    public String getFastlaneResponse() {
        return FastlaneResponse;
    }

    public void setFastlaneResponse(String FastlaneResponse) {
        this.FastlaneResponse = FastlaneResponse;
    }

    public int getVehicle_Detail_Id() {
        return Vehicle_Detail_Id;
    }

    public void setVehicle_Detail_Id(int Vehicle_Detail_Id) {
        this.Vehicle_Detail_Id = Vehicle_Detail_Id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getCreated_On() {
        return Created_On;
    }

    public void setCreated_On(String Created_On) {
        this.Created_On = Created_On;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Variant_Name);
        dest.writeString(this.RTO_Name);
        dest.writeString(this.RTO_Code);
        dest.writeString(this.VehicleCity_Id);
        dest.writeString(this.Purchase_Date);
        dest.writeInt(this.FastLaneId);
        dest.writeString(this.Registration_Date);
        dest.writeString(this.Engin_Number);
        dest.writeString(this.Chassis_Number);
        dest.writeString(this.Registration_Number);
        dest.writeString(this.Manufacture_Year);
        dest.writeString(this.Cubic_Capacity);
        dest.writeString(this.Seating_Capacity);
        dest.writeString(this.Fuel_Type);
        dest.writeString(this.Model_Name);
        dest.writeString(this.Make_Name);
        dest.writeString(this.Fuel_ID);
        dest.writeString(this.Model_ID);
        dest.writeString(this.Variant_Id);
        dest.writeString(this.FastlaneResponse);
        dest.writeInt(this.Vehicle_Detail_Id);
        dest.writeString(this._id);
        dest.writeInt(this.__v);
        dest.writeString(this.Color);
        dest.writeString(this.Created_On);
    }

    public FastLaneDataEntity() {
    }

    protected FastLaneDataEntity(Parcel in) {
        this.Variant_Name = in.readString();
        this.RTO_Name = in.readString();
        this.RTO_Code = in.readString();
        this.VehicleCity_Id = in.readString();
        this.Purchase_Date = in.readString();
        this.FastLaneId = in.readInt();
        this.Registration_Date = in.readString();
        this.Engin_Number = in.readString();
        this.Chassis_Number = in.readString();
        this.Registration_Number = in.readString();
        this.Manufacture_Year = in.readString();
        this.Cubic_Capacity = in.readString();
        this.Seating_Capacity = in.readString();
        this.Fuel_Type = in.readString();
        this.Model_Name = in.readString();
        this.Make_Name = in.readString();
        this.Fuel_ID = in.readString();
        this.Model_ID = in.readString();
        this.Variant_Id = in.readString();
        this.FastlaneResponse = in.readString();
        this.Vehicle_Detail_Id = in.readInt();
        this._id = in.readString();
        this.__v = in.readInt();
        this.Color = in.readString();
        this.Created_On = in.readString();
    }

    public static final Parcelable.Creator<FastLaneDataEntity> CREATOR = new Parcelable.Creator<FastLaneDataEntity>() {
        @Override
        public FastLaneDataEntity createFromParcel(Parcel source) {
            return new FastLaneDataEntity(source);
        }

        @Override
        public FastLaneDataEntity[] newArray(int size) {
            return new FastLaneDataEntity[size];
        }
    };
}