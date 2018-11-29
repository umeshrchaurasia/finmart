package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import android.os.Parcel;
import android.os.Parcelable;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthMasterData;

/**
 * Created by Nilesh Birhade on 09-02-2018.
 */

public class HealthQuoteAppResponse extends APIResponse implements Parcelable {


    /**
     * HealthMasterData : {"quote":[{"fba_id":123,"HealthRequestId":13,"agent_source":"App","crn":"2132132","HealthRequest":{"HealthRequestId":13,"crn":"2132132","CityID":1020,"ContactEmail":"pramod.parit@rupeeboss.com","ContactMobile":"9930089092","ContactName":"pramod parit","DeductibleAmount":0,"ExistingCustomerReferenceID":0,"HealthType":"Health","MaritalStatusID":1,"PolicyFor":"Family","PolicyTermYear":1,"ProductID":2,"SessionID":0,"SourceType":"APP","SumInsured":"300000","SupportsAgentID":2,"fba_id":123,"agent_source":"App","Quote_Application_Status":"Q","conversion_date":null,"created_date":"2018-02-09T05:23:51.000Z","updated_date":null,"isActive":1,"MemberList":[{"HealthMemberListId":"37","MemberDOB":"07-06-1984","MemberGender":"M","MemberNumber":"1","MemberTypeID":"1","HealthRequestId":"13"},{"HealthMemberListId":"38","MemberDOB":"07-06-1989","MemberGender":"F","MemberNumber":"2","MemberTypeID":"2","HealthRequestId":"13"},{"HealthMemberListId":"39","MemberDOB":"29-04-2017","MemberGender":"F","MemberNumber":"5","MemberTypeID":"3","HealthRequestId":"13"}]}}],"application":[{"fba_id":123,"HealthRequestId":14,"agent_source":"App","crn":"2132132","HealthRequest":{"HealthRequestId":14,"crn":"2132132","CityID":1020,"ContactEmail":"nilesh.b@rupeeboss.com","ContactMobile":"9930089452","ContactName":"Nilesh b","DeductibleAmount":0,"ExistingCustomerReferenceID":0,"HealthType":"Health","MaritalStatusID":1,"PolicyFor":"Family","PolicyTermYear":1,"ProductID":2,"SessionID":0,"SourceType":"APP","SumInsured":"300000","SupportsAgentID":2,"fba_id":123,"agent_source":"App","Quote_Application_Status":"A","conversion_date":"2018-02-09T05:28:04.000Z","created_date":"2018-02-09T05:27:49.000Z","updated_date":null,"isActive":1,"MemberList":[{"HealthMemberListId":"40","MemberDOB":"07-06-1984","MemberGender":"M","MemberNumber":"1","MemberTypeID":"1","HealthRequestId":"14"},{"HealthMemberListId":"41","MemberDOB":"07-06-1989","MemberGender":"F","MemberNumber":"2","MemberTypeID":"2","HealthRequestId":"14"},{"HealthMemberListId":"42","MemberDOB":"29-04-2017","MemberGender":"F","MemberNumber":"5","MemberTypeID":"3","HealthRequestId":"14"}]}}]}
     */

    private HealthMasterData MasterData;

    public HealthMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(HealthMasterData MasterData) {
        this.MasterData = MasterData;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.MasterData, flags);
    }

    public HealthQuoteAppResponse() {
    }

    protected HealthQuoteAppResponse(Parcel in) {
        this.MasterData = in.readParcelable(HealthMasterData.class.getClassLoader());
    }

    public static final Parcelable.Creator<HealthQuoteAppResponse> CREATOR = new Parcelable.Creator<HealthQuoteAppResponse>() {
        @Override
        public HealthQuoteAppResponse createFromParcel(Parcel source) {
            return new HealthQuoteAppResponse(source);
        }

        @Override
        public HealthQuoteAppResponse[] newArray(int size) {
            return new HealthQuoteAppResponse[size];
        }
    };
}
