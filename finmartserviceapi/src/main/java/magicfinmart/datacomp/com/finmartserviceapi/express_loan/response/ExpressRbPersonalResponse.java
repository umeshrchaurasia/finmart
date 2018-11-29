package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.MasterDataExpressLoan;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 17-04-2018.
 */

public class ExpressRbPersonalResponse extends APIResponse {
    /**
     * MasterData : {"ReferenceCode":"#PLQER293F"}
     */

    private MasterDataExpressLoan MasterData;

    public MasterDataExpressLoan getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataExpressLoan MasterData) {
        this.MasterData = MasterData;
    }



}
