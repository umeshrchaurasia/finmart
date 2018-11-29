package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajeev Ranjan on 07/04/2018.
 */

public class TermFinmartRequest implements Parcelable {

    int termRequestId;
    String insImage;
    int statusProgress;
    private String NetPremium;

    public int getStatusProgress() {
        return statusProgress;
    }

    public void setStatusProgress(int statusProgress) {
        this.statusProgress = statusProgress;
    }

    public int getFba_id() {
        return fba_id;
    }

    public void setFba_id(int fba_id) {
        this.fba_id = fba_id;
    }

    int fba_id;

    TermRequestEntity termRequestEntity;

    public int getTermRequestId() {
        return termRequestId;
    }

    public void setTermRequestId(int termRequestId) {
        this.termRequestId = termRequestId;
    }

    public TermRequestEntity getTermRequestEntity() {
        return termRequestEntity;
    }

    public void setTermRequestEntity(TermRequestEntity termRequestEntity) {
        this.termRequestEntity = termRequestEntity;
    }

    public String getInsImage() {
        return insImage;
    }

    public void setInsImage(String insImage) {
        this.insImage = insImage;
    }

    public int getStatus_progress() {
        return statusProgress;
    }

    public void setStatus_progress(int status_progress) {
        this.statusProgress = status_progress;
    }

    public String getNetPremium() {
        return NetPremium;
    }

    public void setNetPremium(String NetPremium) {
        this.NetPremium = NetPremium;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.termRequestId);
        dest.writeString(this.insImage);
        dest.writeInt(this.statusProgress);
        dest.writeString(this.NetPremium);
        dest.writeInt(this.fba_id);
        dest.writeParcelable(this.termRequestEntity, flags);
    }

    public TermFinmartRequest() {
    }

    protected TermFinmartRequest(Parcel in) {
        this.termRequestId = in.readInt();
        this.insImage = in.readString();
        this.statusProgress = in.readInt();
        this.NetPremium = in.readString();
        this.fba_id = in.readInt();
        this.termRequestEntity = in.readParcelable(TermRequestEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<TermFinmartRequest> CREATOR = new Parcelable.Creator<TermFinmartRequest>() {
        @Override
        public TermFinmartRequest createFromParcel(Parcel source) {
            return new TermFinmartRequest(source);
        }

        @Override
        public TermFinmartRequest[] newArray(int size) {
            return new TermFinmartRequest[size];
        }
    };
}
