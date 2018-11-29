package magicfinmart.datacomp.com.finmartserviceapi.motor.response;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.motor.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.SummaryEntity;

/**
 * Created by Nilesh Birhade on 15-11-2017.
 */

public class BikePremiumResponse extends APIResponse implements Parcelable {

    private SummaryEntity Summary;
    private List<ResponseEntity> Response;

    public SummaryEntity getSummary() {
        return Summary;
    }

    public void setSummary(SummaryEntity Summary) {
        this.Summary = Summary;
    }

    public List<ResponseEntity> getResponse() {
        return Response;
    }

    public void setResponse(List<ResponseEntity> Response) {
        this.Response = Response;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.Summary, flags);
        dest.writeTypedList(this.Response);
    }

    public BikePremiumResponse() {
    }

    protected BikePremiumResponse(Parcel in) {
        this.Summary = in.readParcelable(SummaryEntity.class.getClassLoader());
        this.Response = in.createTypedArrayList(ResponseEntity.CREATOR);
    }

    public static final Parcelable.Creator<BikePremiumResponse> CREATOR = new Parcelable.Creator<BikePremiumResponse>() {
        @Override
        public BikePremiumResponse createFromParcel(Parcel source) {
            return new BikePremiumResponse(source);
        }

        @Override
        public BikePremiumResponse[] newArray(int size) {
            return new BikePremiumResponse[size];
        }
    };
}
