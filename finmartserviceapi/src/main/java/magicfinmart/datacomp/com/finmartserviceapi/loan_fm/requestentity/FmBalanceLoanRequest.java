package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 25-02-2018.
 */

public class FmBalanceLoanRequest implements Parcelable {

    private int BalanceTransferId;
    private int FBA_id;
    private BLLoanRequest BLLoanRequest;

    public int getBalanceTransferId() {
        return BalanceTransferId;
    }

    public void setBalanceTransferId(int balanceTransferId) {
        BalanceTransferId = balanceTransferId;
    }

    public int getFBA_id() {
        return FBA_id;
    }

    public void setFBA_id(int FBA_id) {
        this.FBA_id = FBA_id;
    }

    public magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest getBLLoanRequest() {
        return BLLoanRequest;
    }

    public void setBLLoanRequest(magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest BLLoanRequest) {
        this.BLLoanRequest = BLLoanRequest;
    }

    public static Creator<FmBalanceLoanRequest> getCREATOR() {
        return CREATOR;
    }


    public FmBalanceLoanRequest(){
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.BalanceTransferId);
        dest.writeInt(this.FBA_id);
        dest.writeParcelable(this.BLLoanRequest, flags);
    }

    protected FmBalanceLoanRequest(Parcel in) {
        this.BalanceTransferId = in.readInt();
        this.FBA_id = in.readInt();
        this.BLLoanRequest = in.readParcelable(magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest.class.getClassLoader());
    }

    public static final Parcelable.Creator<FmBalanceLoanRequest> CREATOR = new Parcelable.Creator<FmBalanceLoanRequest>() {
        @Override
        public FmBalanceLoanRequest createFromParcel(Parcel source) {
            return new FmBalanceLoanRequest(source);
        }

        @Override
        public FmBalanceLoanRequest[] newArray(int size) {
            return new FmBalanceLoanRequest[size];
        }
    };
}
