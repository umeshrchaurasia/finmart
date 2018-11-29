package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FixedRateofInterestEntity implements Parcelable {
    /**
     * roi : 9.50
     * years_from : 3.00
     * years_to : 3.00
     * emi : 92896
     */

    private String roi;
    private String years_from;
    private String years_to;
    private double emi;

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getYears_from() {
        return years_from;
    }

    public void setYears_from(String years_from) {
        this.years_from = years_from;
    }

    public String getYears_to() {
        return years_to;
    }

    public void setYears_to(String years_to) {
        this.years_to = years_to;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }


    public FixedRateofInterestEntity() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.roi);
        dest.writeString(this.years_from);
        dest.writeString(this.years_to);
        dest.writeDouble(this.emi);
    }

    protected FixedRateofInterestEntity(Parcel in) {
        this.roi = in.readString();
        this.years_from = in.readString();
        this.years_to = in.readString();
        this.emi = in.readDouble();
    }

    public static final Parcelable.Creator<FixedRateofInterestEntity> CREATOR = new Parcelable.Creator<FixedRateofInterestEntity>() {
        @Override
        public FixedRateofInterestEntity createFromParcel(Parcel source) {
            return new FixedRateofInterestEntity(source);
        }

        @Override
        public FixedRateofInterestEntity[] newArray(int size) {
            return new FixedRateofInterestEntity[size];
        }
    };
}