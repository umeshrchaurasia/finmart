package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SavingBLEntity implements Parcelable {
    /**
     * amount : 6498.83
     * new_amount : 6186.53
     * drop_emi : 312.3
     * drop_in_int : 1.01
     * savings : 48718.58
     * emiperlacs : 0.061865317624333
     */

    private double amount;
    private double new_amount;
    private double drop_emi;
    private double drop_in_int;
    private double savings;
    private double emiperlacs;

    protected SavingBLEntity(Parcel in) {
        amount = in.readDouble();
        new_amount = in.readDouble();
        drop_emi = in.readDouble();
        drop_in_int = in.readDouble();
        savings = in.readDouble();
        emiperlacs = in.readDouble();
    }

    public static final Creator<SavingBLEntity> CREATOR = new Creator<SavingBLEntity>() {
        @Override
        public SavingBLEntity createFromParcel(Parcel in) {
            return new SavingBLEntity(in);
        }

        @Override
        public SavingBLEntity[] newArray(int size) {
            return new SavingBLEntity[size];
        }
    };

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getNew_amount() {
        return new_amount;
    }

    public void setNew_amount(double new_amount) {
        this.new_amount = new_amount;
    }

    public double getDrop_emi() {
        return drop_emi;
    }

    public void setDrop_emi(double drop_emi) {
        this.drop_emi = drop_emi;
    }

    public double getDrop_in_int() {
        return drop_in_int;
    }

    public void setDrop_in_int(double drop_in_int) {
        this.drop_in_int = drop_in_int;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getEmiperlacs() {
        return emiperlacs;
    }

    public void setEmiperlacs(double emiperlacs) {
        this.emiperlacs = emiperlacs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(amount);
        dest.writeDouble(new_amount);
        dest.writeDouble(drop_emi);
        dest.writeDouble(drop_in_int);
        dest.writeDouble(savings);
        dest.writeDouble(emiperlacs);
    }
}