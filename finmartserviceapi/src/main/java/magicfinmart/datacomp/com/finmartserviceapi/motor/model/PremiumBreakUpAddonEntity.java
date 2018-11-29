package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajeev Ranjan on 19/01/2018.
 */

public class PremiumBreakUpAddonEntity implements Parcelable {
    public String name;
    public String value;
    public boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public PremiumBreakUpAddonEntity(String name, String value) {
        this.name = name;
        this.value = value;
        this.isSelected = true;
    }

    public PremiumBreakUpAddonEntity(String name, String value, boolean isSelected) {
        this.name = name;
        this.value = value;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.value);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected PremiumBreakUpAddonEntity(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<PremiumBreakUpAddonEntity> CREATOR = new Parcelable.Creator<PremiumBreakUpAddonEntity>() {
        @Override
        public PremiumBreakUpAddonEntity createFromParcel(Parcel source) {
            return new PremiumBreakUpAddonEntity(source);
        }

        @Override
        public PremiumBreakUpAddonEntity[] newArray(int size) {
            return new PremiumBreakUpAddonEntity[size];
        }
    };
}
