package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PospEnrollEntity;

/**
 * Created by Rajeev Ranjan on 19/02/2018.
 */

public class EnrollPospResponse extends APIResponse {
    /**
     * MasterDataEntity : {"ErrorCode":null,"ErrorDescription":null,"MSG":"Record found","MSGID":null,"Status":"1","PaymRefeID":18223172304767056,"PaymentURL":"https://goo.gl/pYNJLN"}
     */

    private PospEnrollEntity MasterData;

    public PospEnrollEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(PospEnrollEntity MasterData) {
        this.MasterData = MasterData;
    }


}
