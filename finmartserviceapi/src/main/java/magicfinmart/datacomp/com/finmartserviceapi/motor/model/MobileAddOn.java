package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MobileAddOn implements Parcelable {

    public String AddonName;
    public double min;
    public double max;
    public String AddonKey;
    public boolean isSelected;

    public String getAddonName() {
        return AddonName;
    }

    public void setAddonName(String addonName) {
        AddonName = addonName;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getAddonKey() {
        return AddonKey;
    }

    public void setAddonKey(String addonKey) {
        AddonKey = addonKey;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AddonName);
        dest.writeDouble(this.min);
        dest.writeDouble(this.max);
        dest.writeString(this.AddonKey);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public MobileAddOn() {
    }

    protected MobileAddOn(Parcel in) {
        this.AddonName = in.readString();
        this.min = in.readDouble();
        this.max = in.readDouble();
        this.AddonKey = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MobileAddOn> CREATOR = new Parcelable.Creator<MobileAddOn>() {
        @Override
        public MobileAddOn createFromParcel(Parcel source) {
            return new MobileAddOn(source);
        }

        @Override
        public MobileAddOn[] newArray(int size) {
            return new MobileAddOn[size];
        }
    };
}