package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.RblCalEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 17-04-2018.
 */

public class ExpressRblCalResponse  extends APIResponse {


    /**
     * MasterData : {"emi":76985,"fee":100000}
     */

    private RblCalEntity MasterData;

    public RblCalEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(RblCalEntity MasterData) {
        this.MasterData = MasterData;
    }


}
