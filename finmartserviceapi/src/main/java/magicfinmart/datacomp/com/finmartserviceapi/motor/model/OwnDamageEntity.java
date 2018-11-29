package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class OwnDamageEntity implements Parcelable {
    public static final Creator<OwnDamageEntity> CREATOR = new Creator<OwnDamageEntity>() {
        @Override
        public OwnDamageEntity createFromParcel(Parcel in) {
            return new OwnDamageEntity(in);
        }

        @Override
        public OwnDamageEntity[] newArray(int size) {
            return new OwnDamageEntity[size];
        }
    };
    /**
     * od_basic : 11490.07
     * od_elect_access : 0
     * od_non_elect_access : 0
     * od_cng_lpg : 0
     * od_disc_ncb : 2298.01
     * od_disc_vol_deduct : 0
     * od_disc_anti_theft : 0
     * od_disc_aai : 0
     * od_loading : 0
     * od_disc : 0
     * od_final_premium : 9192.06
     */

    private String od_basic;
    private String od_elect_access;
    private String od_non_elect_access;
    private String od_cng_lpg;
    private String od_disc_ncb;
    private String od_disc_vol_deduct;
    private String od_disc_anti_theft;
    private String od_disc_aai;
    private String od_loading;
    private String od_disc;
    private String od_final_premium;

    protected OwnDamageEntity(Parcel in) {
        od_basic = in.readString();
        od_elect_access = in.readString();
        od_non_elect_access = in.readString();
        od_cng_lpg = in.readString();
        od_disc_ncb = in.readString();
        od_disc_vol_deduct = in.readString();
        od_disc_anti_theft = in.readString();
        od_disc_aai = in.readString();
        od_loading = in.readString();
        od_disc = in.readString();
        od_final_premium = in.readString();
    }

    public String getOd_basic() {
        return od_basic;
    }

    public void setOd_basic(String od_basic) {
        this.od_basic = od_basic;
    }

    public String getOd_elect_access() {
        return od_elect_access;
    }

    public void setOd_elect_access(String od_elect_access) {
        this.od_elect_access = od_elect_access;
    }

    public String getOd_non_elect_access() {
        return od_non_elect_access;
    }

    public void setOd_non_elect_access(String od_non_elect_access) {
        this.od_non_elect_access = od_non_elect_access;
    }

    public String getOd_cng_lpg() {
        return od_cng_lpg;
    }

    public void setOd_cng_lpg(String od_cng_lpg) {
        this.od_cng_lpg = od_cng_lpg;
    }

    public String getOd_disc_ncb() {
        return od_disc_ncb;
    }

    public void setOd_disc_ncb(String od_disc_ncb) {
        this.od_disc_ncb = od_disc_ncb;
    }

    public String getOd_disc_vol_deduct() {
        return od_disc_vol_deduct;
    }

    public void setOd_disc_vol_deduct(String od_disc_vol_deduct) {
        this.od_disc_vol_deduct = od_disc_vol_deduct;
    }

    public String getOd_disc_anti_theft() {
        return od_disc_anti_theft;
    }

    public void setOd_disc_anti_theft(String od_disc_anti_theft) {
        this.od_disc_anti_theft = od_disc_anti_theft;
    }

    public String getOd_disc_aai() {
        return od_disc_aai;
    }

    public void setOd_disc_aai(String od_disc_aai) {
        this.od_disc_aai = od_disc_aai;
    }

    public String getOd_loading() {
        return od_loading;
    }

    public void setOd_loading(String od_loading) {
        this.od_loading = od_loading;
    }

    public String getOd_disc() {
        return od_disc;
    }

    public void setOd_disc(String od_disc) {
        this.od_disc = od_disc;
    }

    public String getOd_final_premium() {
        return od_final_premium;
    }

    public void setOd_final_premium(String od_final_premium) {
        this.od_final_premium = od_final_premium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(od_basic);
        dest.writeString(od_elect_access);
        dest.writeString(od_non_elect_access);
        dest.writeString(od_cng_lpg);
        dest.writeString(od_disc_ncb);
        dest.writeString(od_disc_vol_deduct);
        dest.writeString(od_disc_anti_theft);
        dest.writeString(od_disc_aai);
        dest.writeString(od_loading);
        dest.writeString(od_disc);
        dest.writeString(od_final_premium);
    }
}
