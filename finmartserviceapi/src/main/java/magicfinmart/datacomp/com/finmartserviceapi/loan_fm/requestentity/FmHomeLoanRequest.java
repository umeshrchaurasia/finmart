package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 01-02-2018.
 */

public class FmHomeLoanRequest  implements Parcelable {


    /**
     * loan_requestID : 5
     * fba_id : 35779
     * HomeLoanRequest : {"ApplicantDOB":"1997-01-02","ApplicantGender":"M","ApplicantIncome":"","ApplicantNme":"test Verma","ApplicantObligations":"700","ApplicantSource":"2","BrokerId":"0","City":"Mumbai","CoApplicantDOB":"1997-01-16","CoApplicantDepreciation":"5444","CoApplicantDirectorRemuneration":"5000","CoApplicantGender":"M","CoApplicantIncome":"","CoApplicantObligations":"","CoApplicantProfitAfterTax":"200","CoApplicantSource":"2","CoApplicantTurnover":"54000","CoApplicantYes":"Y","Depreciation":"2888","DirectorRemuneration":"500","LoanRequired":"5600000","LoanTenure":"22","ProductId":"12","ProfitAfterTax":"400","PropertyCost":"7000000","PropertyID":"1","Turnover":"858655","api_source":"Finmart","empcode":""}
     */

    private int loan_requestID;
    private int FBA_id;
    private HomeLoanRequest HomeLoanRequest;

    protected FmHomeLoanRequest(Parcel in) {
        loan_requestID = in.readInt();
        FBA_id = in.readInt();
        HomeLoanRequest = in.readParcelable(magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.HomeLoanRequest.class.getClassLoader());
    }

    public static final Creator<FmHomeLoanRequest> CREATOR = new Creator<FmHomeLoanRequest>() {
        @Override
        public FmHomeLoanRequest createFromParcel(Parcel in) {
            return new FmHomeLoanRequest(in);
        }

        @Override
        public FmHomeLoanRequest[] newArray(int size) {
            return new FmHomeLoanRequest[size];
        }
    };

    public FmHomeLoanRequest() {
    }

    public int getLoan_requestID() {
        return loan_requestID;
    }

    public void setLoan_requestID(int loan_requestID) {
        this.loan_requestID = loan_requestID;
    }

    public int getFba_id() {
        return FBA_id;
    }

    public void setFba_id(int FBA_id) {
        this.FBA_id = FBA_id;
    }

    public HomeLoanRequest getHomeLoanRequest() {
        return HomeLoanRequest;
    }

    public void setHomeLoanRequest(HomeLoanRequest HomeLoanRequest) {
        this.HomeLoanRequest = HomeLoanRequest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(loan_requestID);
        dest.writeInt(FBA_id);
        dest.writeParcelable(HomeLoanRequest, flags);
    }
}
