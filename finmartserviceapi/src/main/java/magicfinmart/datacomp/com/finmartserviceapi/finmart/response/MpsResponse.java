package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MpsDataEntity;

/**
 * Created by Rajeev Ranjan on 17/03/2018.
 */

public class MpsResponse extends APIResponse {
    /**
     * MasterData : {"ErrorCode":"","ErrorDescription":"","MSG":"Record found","MSGID":"","Status":"1","PaymRefeID":18317122913357780,"PaymentURL":"https://goo.gl/gnjL2E"}
     */

    private MpsDataEntity MasterData;

    public MpsDataEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(MpsDataEntity MasterData) {
        this.MasterData = MasterData;
    }


}
