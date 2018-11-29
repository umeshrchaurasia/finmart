package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmPersonalLoanRequest;

/**
 * Created by IN-RB on 08-02-2018.
 */

public class PersonalMainEntity implements Parcelable {


    private List<FmPersonalLoanRequest> quote;
    private List<FmPersonalLoanRequest> application;

    protected PersonalMainEntity(Parcel in) {
        quote = in.createTypedArrayList(FmPersonalLoanRequest.CREATOR);
        application = in.createTypedArrayList(FmPersonalLoanRequest.CREATOR);
    }

    public static final Creator<PersonalMainEntity> CREATOR = new Creator<PersonalMainEntity>() {
        @Override
        public PersonalMainEntity createFromParcel(Parcel in) {
            return new PersonalMainEntity(in);
        }

        @Override
        public PersonalMainEntity[] newArray(int size) {
            return new PersonalMainEntity[size];
        }
    };

    public List<FmPersonalLoanRequest> getQuote() {
        return quote;
    }

    public void setQuote(List<FmPersonalLoanRequest> quote) {
        this.quote = quote;
    }

    public List<FmPersonalLoanRequest> getApplication() {
        return application;
    }

    public void setApplication(List<FmPersonalLoanRequest> application) {
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
