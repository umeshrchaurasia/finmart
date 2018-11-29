package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;

public  class HomeLoanRequestMainEntity  implements Parcelable{


    private List<FmHomeLoanRequest> quote;
    private List<FmHomeLoanRequest> application;


    protected HomeLoanRequestMainEntity(Parcel in) {
        quote = in.createTypedArrayList(FmHomeLoanRequest.CREATOR);
        application = in.createTypedArrayList(FmHomeLoanRequest.CREATOR);
    }

    public static final Creator<HomeLoanRequestMainEntity> CREATOR = new Creator<HomeLoanRequestMainEntity>() {
        @Override
        public HomeLoanRequestMainEntity createFromParcel(Parcel in) {
            return new HomeLoanRequestMainEntity(in);
        }

        @Override
        public HomeLoanRequestMainEntity[] newArray(int size) {
            return new HomeLoanRequestMainEntity[size];
        }
    };

    public List<FmHomeLoanRequest> getQuote() {
        return quote;
    }

    public void setQuote(List<FmHomeLoanRequest> quote) {
        this.quote = quote;
    }

    public List<FmHomeLoanRequest> getApplication() {
        return application;
    }

    public void setApplication(List<FmHomeLoanRequest> application) {
        this.application = application;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(quote);
        dest.writeTypedList(application);
    }
}