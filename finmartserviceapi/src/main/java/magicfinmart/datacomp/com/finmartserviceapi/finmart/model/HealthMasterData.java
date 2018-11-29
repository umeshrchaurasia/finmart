package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class HealthMasterData implements Parcelable {

    private List<HealthQuote> quote;
    private List<HealthApplication> application;

    public List<HealthQuote> getQuote() {
        return quote;
    }

    public void setQuote(List<HealthQuote> quote) {
        this.quote = quote;
    }

    public List<HealthApplication> getApplication() {
        return application;
    }

    public void setApplication(List<HealthApplication> application) {
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

    public HealthMasterData() {
    }

    protected HealthMasterData(Parcel in) {
        this.quote = new ArrayList<HealthQuote>();
        in.readList(this.quote, HealthQuote.class.getClassLoader());
        this.application = new ArrayList<HealthApplication>();
        in.readList(this.application, HealthApplication.class.getClassLoader());
    }

    public static final Parcelable.Creator<HealthMasterData> CREATOR = new Parcelable.Creator<HealthMasterData>() {
        @Override
        public HealthMasterData createFromParcel(Parcel source) {
            return new HealthMasterData(source);
        }

        @Override
        public HealthMasterData[] newArray(int size) {
            return new HealthMasterData[size];
        }
    };
}