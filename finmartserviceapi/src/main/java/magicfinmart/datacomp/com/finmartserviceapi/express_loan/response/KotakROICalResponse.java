package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.KotakROICAlEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Vinit Chindarkar on 05-08-2018.
 */

public class KotakROICalResponse extends APIResponse {
    /**
     * MasterData : {"roi":"12.99","category":"CAT C","csc_pf":"0.99","non_csc_pf":"1.49","ProcFee":7450}

     */


    private KotakROICAlEntity MasterData;

    public KotakROICAlEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(KotakROICAlEntity MasterData) {
        this.MasterData = MasterData;
    }


}
