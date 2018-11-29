package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 01-02-2018.
 */

public class FmPersonalLoanRequest implements Parcelable {


    /**
     * loan_requestID : 5
     * fba_id : 35779
     * PersonalLoanRequest : {"ApplicantDOB":"1997-01-15","ApplicantGender":"M","ApplicantIncome":"19200","ApplicantNme":"test","ApplicantObligations":"400","ApplicantSource":"1","BrokerId":"0","LoanRequired":"500000","LoanTenure":"4","api_source":"","empcode":"","loanid":"1"}
     */

    private int loan_requestID;
    private int FBA_id;
    private PersonalLoanRequest PersonalLoanRequest;

    protected FmPersonalLoanRequest(Parcel in) {
        loan_requestID = in.readInt();
        FBA_id = in.readInt();
        PersonalLoanRequest = in.readParcelable(magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.PersonalLoanRequest.class.getClassLoader());
    }

    public static final Creator<FmPersonalLoanRequest> CREATOR = new Creator<FmPersonalLoanRequest>() {
        @Override
        public FmPersonalLoanRequest createFromParcel(Parcel in) {
            return new FmPersonalLoanRequest(in);
        }

        @Override
        public FmPersonalLoanRequest[] newArray(int size) {
            return new FmPersonalLoanRequest[size];
        }
    };

    public FmPersonalLoanRequest() {

    }

    public int getLoan_requestID() {
        return loan_requestID;
    }

    public void setLoan_requestID(int loan_requestID) {
        this.loan_requestID = loan_requestID;
    }

    public int getFBA_id() {
        return FBA_id;
    }

    public void setFBA_id(int FBA_id) {
        this.FBA_id = FBA_id;
    }

    public PersonalLoanRequest getPersonalLoanRequest() {
        return PersonalLoanRequest;
    }

    public void setPersonalLoanRequest(PersonalLoanRequest PersonalLoanRequest) {
        this.PersonalLoanRequest = PersonalLoanRequest;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(loan_requestID);
        dest.writeInt(FBA_id);
        dest.writeParcelable(PersonalLoanRequest, flags);
    }
}
