package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 19-02-2018.
 */

public class BankSaveRequest implements Parcelable{

    /**
     * loan_requestID : 1
     * bank_id : 1
     * type : HML
     */

    private int loan_requestID;
    private int bank_id;
    private String type;

    protected BankSaveRequest(Parcel in) {
        loan_requestID = in.readInt();
        bank_id = in.readInt();
        type = in.readString();
    }

    public static final Creator<BankSaveRequest> CREATOR = new Creator<BankSaveRequest>() {
        @Override
        public BankSaveRequest createFromParcel(Parcel in) {
            return new BankSaveRequest(in);
        }

        @Override
        public BankSaveRequest[] newArray(int size) {
            return new BankSaveRequest[size];
        }
    };

    public BankSaveRequest() {
    }

    public int getLoan_requestID() {
        return loan_requestID;
    }

    public void setLoan_requestID(int loan_requestID) {
        this.loan_requestID = loan_requestID;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(loan_requestID);
        dest.writeInt(bank_id);
        dest.writeString(type);
    }
}
