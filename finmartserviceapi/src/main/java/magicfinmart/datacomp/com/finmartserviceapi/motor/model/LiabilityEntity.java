package magicfinmart.datacomp.com.finmartserviceapi.motor.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LiabilityEntity implements Parcelable {
    public static final Creator<LiabilityEntity> CREATOR = new Creator<LiabilityEntity>() {
        @Override
        public LiabilityEntity createFromParcel(Parcel in) {
            return new LiabilityEntity(in);
        }

        @Override
        public LiabilityEntity[] newArray(int size) {
            return new LiabilityEntity[size];
        }
    };
    /**
     * tp_basic : 2863
     * tp_cover_owner_driver_pa : 100
     * tp_cover_unnamed_passenger_pa : 0
     * tp_cover_named_passenger_pa : 0
     * tp_cover_paid_driver_pa : 0
     * tp_cover_paid_driver_ll : 0
     * tp_cng_lpg : 0
     * tp_final_premium : 2963
     */

    private String tp_basic;
    private String tp_cover_owner_driver_pa;
    private String tp_cover_unnamed_passenger_pa;
    private String tp_cover_named_passenger_pa;
    private String tp_cover_paid_driver_pa;
    private String tp_cover_paid_driver_ll;
    private String tp_cng_lpg;
    private String tp_final_premium;

    protected LiabilityEntity(Parcel in) {
        tp_basic = in.readString();
        tp_cover_owner_driver_pa = in.readString();
        tp_cover_unnamed_passenger_pa = in.readString();
        tp_cover_named_passenger_pa = in.readString();
        tp_cover_paid_driver_pa = in.readString();
        tp_cover_paid_driver_ll = in.readString();
        tp_cng_lpg = in.readString();
        tp_final_premium = in.readString();
    }

    public String getTp_basic() {
        return tp_basic;
    }

    public void setTp_basic(String tp_basic) {
        this.tp_basic = tp_basic;
    }

    public String getTp_cover_owner_driver_pa() {
        return tp_cover_owner_driver_pa;
    }

    public void setTp_cover_owner_driver_pa(String tp_cover_owner_driver_pa) {
        this.tp_cover_owner_driver_pa = tp_cover_owner_driver_pa;
    }

    public String getTp_cover_unnamed_passenger_pa() {
        return tp_cover_unnamed_passenger_pa;
    }

    public void setTp_cover_unnamed_passenger_pa(String tp_cover_unnamed_passenger_pa) {
        this.tp_cover_unnamed_passenger_pa = tp_cover_unnamed_passenger_pa;
    }

    public String getTp_cover_named_passenger_pa() {
        return tp_cover_named_passenger_pa;
    }

    public void setTp_cover_named_passenger_pa(String tp_cover_named_passenger_pa) {
        this.tp_cover_named_passenger_pa = tp_cover_named_passenger_pa;
    }

    public String getTp_cover_paid_driver_pa() {
        return tp_cover_paid_driver_pa;
    }

    public void setTp_cover_paid_driver_pa(String tp_cover_paid_driver_pa) {
        this.tp_cover_paid_driver_pa = tp_cover_paid_driver_pa;
    }

    public String getTp_cover_paid_driver_ll() {
        return tp_cover_paid_driver_ll;
    }

    public void setTp_cover_paid_driver_ll(String tp_cover_paid_driver_ll) {
        this.tp_cover_paid_driver_ll = tp_cover_paid_driver_ll;
    }

    public String getTp_cng_lpg() {
        return tp_cng_lpg;
    }

    public void setTp_cng_lpg(String tp_cng_lpg) {
        this.tp_cng_lpg = tp_cng_lpg;
    }

    public String getTp_final_premium() {
        return tp_final_premium;
    }

    public void setTp_final_premium(String tp_final_premium) {
        this.tp_final_premium = tp_final_premium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tp_basic);
        dest.writeString(tp_cover_owner_driver_pa);
        dest.writeString(tp_cover_unnamed_passenger_pa);
        dest.writeString(tp_cover_named_passenger_pa);
        dest.writeString(tp_cover_paid_driver_pa);
        dest.writeString(tp_cover_paid_driver_ll);
        dest.writeString(tp_cng_lpg);
        dest.writeString(tp_final_premium);
    }
}
