package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CarMasterEntity extends RealmObject implements Parcelable {
    /**
     * Make_ID : 1
     * Make_Name : Aston Martin
     * Model_ID : 1
     * Model_Name : DB9
     * Variant_ID : 1
     * Variant_Name : Coupe
     * Model_ID1 : 1
     * Cubic_Capacity : 5935
     * Fuel_ID : 1
     * Seating_Capacity : 2
     * ExShoroomPrice : 19000000
     * IsActive : true
     * CreatedOn : 2012-02-21T20:29:47.67+05:30
     * ModifyOn : 2012-02-21T20:29:47.67+05:30
     * Product_Id_New : 1
     * Make_Name1 : Aston Martin
     * Model_Name1 : DB9
     * Fuel_Name : Petrol
     * Description : Aston Martin DB9 Coupe
     * Make_ID1 : 1
     */

    private String Make_ID;
    private String Make_Name;
    private String Model_ID;
    private String Model_Name;
    @PrimaryKey
    private String Variant_ID;
    private String Variant_Name;
    private String Model_ID1;
    private String Cubic_Capacity;
    private String Fuel_ID;
    private String Seating_Capacity;
    private String ExShoroomPrice;
    private String IsActive;
    private String CreatedOn;
    private String ModifyOn;
    private String Product_Id_New;
    private String Make_Name1;
    private String Model_Name1;
    private String Fuel_Name;
    private String Description;
    private String Make_ID1;

    public String getMake_ID() {
        return Make_ID;
    }

    public void setMake_ID(String Make_ID) {
        this.Make_ID = Make_ID;
    }

    public String getMake_Name() {
        return Make_Name;
    }

    public void setMake_Name(String Make_Name) {
        this.Make_Name = Make_Name;
    }

    public String getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(String Model_ID) {
        this.Model_ID = Model_ID;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    public void setModel_Name(String Model_Name) {
        this.Model_Name = Model_Name;
    }

    public String getVariant_ID() {
        return Variant_ID;
    }

    public void setVariant_ID(String Variant_ID) {
        this.Variant_ID = Variant_ID;
    }

    public String getVariant_Name() {
        return Variant_Name;
    }

    public void setVariant_Name(String Variant_Name) {
        this.Variant_Name = Variant_Name;
    }

    public String getModel_ID1() {
        return Model_ID1;
    }

    public void setModel_ID1(String Model_ID1) {
        this.Model_ID1 = Model_ID1;
    }

    public String getCubic_Capacity() {
        return Cubic_Capacity;
    }

    public void setCubic_Capacity(String Cubic_Capacity) {
        this.Cubic_Capacity = Cubic_Capacity;
    }

    public String getFuel_ID() {
        return Fuel_ID;
    }

    public void setFuel_ID(String Fuel_ID) {
        this.Fuel_ID = Fuel_ID;
    }

    public String getSeating_Capacity() {
        return Seating_Capacity;
    }

    public void setSeating_Capacity(String Seating_Capacity) {
        this.Seating_Capacity = Seating_Capacity;
    }

    public String getExShoroomPrice() {
        return ExShoroomPrice;
    }

    public void setExShoroomPrice(String ExShoroomPrice) {
        this.ExShoroomPrice = ExShoroomPrice;
    }

    public String getIsActive() {
        return IsActive;
    }

    public void setIsActive(String IsActive) {
        this.IsActive = IsActive;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String CreatedOn) {
        this.CreatedOn = CreatedOn;
    }

    public String getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(String ModifyOn) {
        this.ModifyOn = ModifyOn;
    }

    public String getProduct_Id_New() {
        return Product_Id_New;
    }

    public void setProduct_Id_New(String Product_Id_New) {
        this.Product_Id_New = Product_Id_New;
    }

    public String getMake_Name1() {
        return Make_Name1;
    }

    public void setMake_Name1(String Make_Name1) {
        this.Make_Name1 = Make_Name1;
    }

    public String getModel_Name1() {
        return Model_Name1;
    }

    public void setModel_Name1(String Model_Name1) {
        this.Model_Name1 = Model_Name1;
    }

    public String getFuel_Name() {
        return Fuel_Name;
    }

    public void setFuel_Name(String Fuel_Name) {
        this.Fuel_Name = Fuel_Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getMake_ID1() {
        return Make_ID1;
    }

    public void setMake_ID1(String Make_ID1) {
        this.Make_ID1 = Make_ID1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Make_ID);
        dest.writeString(this.Make_Name);
        dest.writeString(this.Model_ID);
        dest.writeString(this.Model_Name);
        dest.writeString(this.Variant_ID);
        dest.writeString(this.Variant_Name);
        dest.writeString(this.Model_ID1);
        dest.writeString(this.Cubic_Capacity);
        dest.writeString(this.Fuel_ID);
        dest.writeString(this.Seating_Capacity);
        dest.writeString(this.ExShoroomPrice);
        dest.writeString(this.IsActive);
        dest.writeString(this.CreatedOn);
        dest.writeString(this.ModifyOn);
        dest.writeString(this.Product_Id_New);
        dest.writeString(this.Make_Name1);
        dest.writeString(this.Model_Name1);
        dest.writeString(this.Fuel_Name);
        dest.writeString(this.Description);
        dest.writeString(this.Make_ID1);
    }

    public CarMasterEntity() {
    }

    protected CarMasterEntity(Parcel in) {
        this.Make_ID = in.readString();
        this.Make_Name = in.readString();
        this.Model_ID = in.readString();
        this.Model_Name = in.readString();
        this.Variant_ID = in.readString();
        this.Variant_Name = in.readString();
        this.Model_ID1 = in.readString();
        this.Cubic_Capacity = in.readString();
        this.Fuel_ID = in.readString();
        this.Seating_Capacity = in.readString();
        this.ExShoroomPrice = in.readString();
        this.IsActive = in.readString();
        this.CreatedOn = in.readString();
        this.ModifyOn = in.readString();
        this.Product_Id_New = in.readString();
        this.Make_Name1 = in.readString();
        this.Model_Name1 = in.readString();
        this.Fuel_Name = in.readString();
        this.Description = in.readString();
        this.Make_ID1 = in.readString();
    }

    public static final Parcelable.Creator<CarMasterEntity> CREATOR = new Parcelable.Creator<CarMasterEntity>() {
        @Override
        public CarMasterEntity createFromParcel(Parcel source) {
            return new CarMasterEntity(source);
        }

        @Override
        public CarMasterEntity[] newArray(int size) {
            return new CarMasterEntity[size];
        }
    };
}