package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.QuoteEntity;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class GetQuoteResponse extends APIResponse implements Parcelable {


    private List<QuoteEntity> data;
    /**
     * quote_id : 1806
     * url : http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx
     */

    private int quote_id;
    private String url;

    public List<QuoteEntity> getData() {
        return data;
    }

    public void setData(List<QuoteEntity> data) {
        this.data = data;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.quote_id);
        dest.writeTypedList(this.data);
    }

    public GetQuoteResponse() {
    }

    protected GetQuoteResponse(Parcel in) {
        this.url = in.readString();
        this.quote_id = in.readInt();
        this.data = in.createTypedArrayList(QuoteEntity.CREATOR);
    }

    public static final Parcelable.Creator<GetQuoteResponse> CREATOR = new Parcelable.Creator<GetQuoteResponse>() {
        @Override
        public GetQuoteResponse createFromParcel(Parcel source) {
            return new GetQuoteResponse(source);
        }

        @Override
        public GetQuoteResponse[] newArray(int size) {
            return new GetQuoteResponse[size];
        }
    };
}
