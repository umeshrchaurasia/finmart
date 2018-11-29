package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.MasterDataExpressLoan;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 12-04-2018.
 */

public class HdfcPers_SaveResponse extends APIResponse {
    /**
     * MasterData : {"Lead_Id":"920419"}
     */

    private MasterDataExpressLoan MasterData;

    public MasterDataExpressLoan getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataExpressLoan MasterData) {
        this.MasterData = MasterData;
    }



    /**
     * MasterData : {"Lead_Id":"920256"}
     */

  /*  private HealthCEntity MasterData;

    public HealthCEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(HealthCEntity MasterData) {
        this.MasterData = MasterData;
    }*/







}
