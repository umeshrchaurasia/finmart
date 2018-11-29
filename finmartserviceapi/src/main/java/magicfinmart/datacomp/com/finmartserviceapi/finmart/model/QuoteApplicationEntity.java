package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuoteApplicationEntity implements Parcelable {

    private List<QuoteListEntity> quote;
    private List<ApplicationListEntity> application;

    public List<QuoteListEntity> getQuote() {
        return quote;
    }

    public void setQuote(List<QuoteListEntity> quote) {
        this.quote = quote;
    }

    public List<ApplicationListEntity> getApplication() {
        return application;
    }

    public void setApplication(List<ApplicationListEntity> application) {
        this.application = application;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.quote);
        dest.writeList(this.application);
    }

    public QuoteApplicationEntity() {
    }

    protected QuoteApplicationEntity(Parcel in) {
        this.quote = new ArrayList<QuoteListEntity>();
        in.readList(this.quote, QuoteListEntity.class.getClassLoader());
        this.application = new ArrayList<ApplicationListEntity>();
        in.readList(this.application, ApplicationListEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<QuoteApplicationEntity> CREATOR = new Parcelable.Creator<QuoteApplicationEntity>() {
        @Override
        public QuoteApplicationEntity createFromParcel(Parcel source) {
            return new QuoteApplicationEntity(source);
        }

        @Override
        public QuoteApplicationEntity[] newArray(int size) {
            return new QuoteApplicationEntity[size];
        }
    };
}
