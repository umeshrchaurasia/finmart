package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HealthQuote implements Parcelable {
    /**
     * fba_id : 123
     * HealthRequestId : 13
     * agent_source : App
     * crn : 2132132
     * HealthRequest : {"HealthRequestId":13,"crn":"2132132","CityID":1020,"ContactEmail":"pramod.parit@rupeeboss.com","ContactMobile":"9930089092","ContactName":"pramod parit","DeductibleAmount":0,"ExistingCustomerReferenceID":0,"HealthType":"Health","MaritalStatusID":1,"PolicyFor":"Family","PolicyTermYear":1,"ProductID":2,"SessionID":0,"SourceType":"APP","SumInsured":"300000","SupportsAgentID":2,"fba_id":123,"agent_source":"App","Quote_Application_Status":"Q","conversion_date":null,"created_date":"2018-02-09T05:23:51.000Z","updated_date":null,"isActive":1,"MemberList":[{"HealthMemberListId":"37","MemberDOB":"07-06-1984","MemberGender":"M","MemberNumber":"1","MemberTypeID":"1","HealthRequestId":"13"},{"HealthMemberListId":"38","MemberDOB":"07-06-1989","MemberGender":"F","MemberNumber":"2","MemberTypeID":"2","HealthRequestId":"13"},{"HealthMemberListId":"39","MemberDOB":"29-04-2017","MemberGender":"F","MemberNumber":"5","MemberTypeID":"3","HealthRequestId":"13"}]}
     */

    private int fba_id;
    private int HealthRequestId;
    private String agent_source;
    private String crn;
    private HealthRequestEntity HealthRequest;

    public int getFba_id() {
        return fba_id;
    }

    public void setFba_id(int fba_id) {
        this.fba_id = fba_id;
    }

    public int getHealthRequestId() {
        return HealthRequestId;
    }

    public void setHealthRequestId(int HealthRequestId) {
        this.HealthRequestId = HealthRequestId;
    }

    public String getAgent_source() {
        return agent_source;
    }

    public void setAgent_source(String agent_source) {
        this.agent_source = agent_source;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    public HealthRequestEntity getHealthRequest() {
        return HealthRequest;
    }

    public void setHealthRequest(HealthRequestEntity HealthRequest) {
        this.HealthRequest = HealthRequest;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.fba_id);
        dest.writeInt(this.HealthRequestId);
        dest.writeString(this.agent_source);
        dest.writeString(this.crn);
        dest.writeParcelable(this.HealthRequest, flags);
    }

    public HealthQuote() {
    }

    protected HealthQuote(Parcel in) {
        this.fba_id = in.readInt();
        this.HealthRequestId = in.readInt();
        this.agent_source = in.readString();
        this.crn = in.readString();
        this.HealthRequest = in.readParcelable(HealthRequestEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<HealthQuote> CREATOR = new Parcelable.Creator<HealthQuote>() {
        @Override
        public HealthQuote createFromParcel(Parcel source) {
            return new HealthQuote(source);
        }

        @Override
        public HealthQuote[] newArray(int size) {
            return new HealthQuote[size];
        }
    };
}
