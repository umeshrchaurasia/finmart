package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.MasterDataExpressLoan;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Vinit Chindarkar on 04-19-2018.
 */

public class kotakPers_SaveResponse extends APIResponse {

    /**
     * MasterData : {"ReferenceCode":"#PLT52TQPTT"}
     */

    private MasterDataExpressLoan MasterData;

    public MasterDataExpressLoan getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataExpressLoan MasterData) {
        this.MasterData = MasterData;
    }

}
