package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nilesh Birhade on 17-11-2017.
 */

public class AppliedAddonsPremiumBreakup implements Parcelable {

    private String addonName;
    private double priceAddon;

    public String getAddonName() {
        return addonName;
    }

    public void setAddonName(String addonName) {
        this.addonName = addonName;
    }

    public double getPriceAddon() {
        return priceAddon;
    }

    public void setPriceAddon(double priceAddon) {
        this.priceAddon = priceAddon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addonName);
        dest.writeDouble(this.priceAddon);
    }

    public AppliedAddonsPremiumBreakup() {
    }

    protected AppliedAddonsPremiumBreakup(Parcel in) {
        this.addonName = in.readString();
        this.priceAddon = in.readDouble();
    }

    public static final Parcelable.Creator<AppliedAddonsPremiumBreakup> CREATOR = new Parcelable.Creator<AppliedAddonsPremiumBreakup>() {
        @Override
        public AppliedAddonsPremiumBreakup createFromParcel(Parcel source) {
            return new AppliedAddonsPremiumBreakup(source);
        }

        @Override
        public AppliedAddonsPremiumBreakup[] newArray(int size) {
            return new AppliedAddonsPremiumBreakup[size];
        }
    };
}
