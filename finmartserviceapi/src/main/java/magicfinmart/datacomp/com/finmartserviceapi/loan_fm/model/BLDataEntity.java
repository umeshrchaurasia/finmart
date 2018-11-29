package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public  class BLDataEntity implements Parcelable {
        private List<BLEntity> bank_data;
        private List<SavingBLEntity> savings;

        public List<BLEntity> getBank_data() {
            return bank_data;
        }

        public void setBank_data(List<BLEntity> bank_data) {
            this.bank_data = bank_data;
        }

        public List<SavingBLEntity> getSavings() {
            return savings;
        }

        public void setSavings(List<SavingBLEntity> savings) {
            this.savings = savings;
        }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.bank_data);
        dest.writeTypedList(this.savings);
    }

    public BLDataEntity() {
    }

    protected BLDataEntity(Parcel in) {
        this.bank_data = in.createTypedArrayList(BLEntity.CREATOR);
        this.savings = in.createTypedArrayList(SavingBLEntity.CREATOR);
    }

    public static final Parcelable.Creator<BLDataEntity> CREATOR = new Parcelable.Creator<BLDataEntity>() {
        @Override
        public BLDataEntity createFromParcel(Parcel source) {
            return new BLDataEntity(source);
        }

        @Override
        public BLDataEntity[] newArray(int size) {
            return new BLDataEntity[size];
        }
    };
}