package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BLNodeMainEntity;

/**
 * Created by IN-RB on 25-02-2018.
 */

public class FmBalanceLoanResponse extends APIResponseFM {
    private BLNodeMainEntity MasterData;

    public BLNodeMainEntity getMasterData() {
        return MasterData;
    }
    public void setMasterData(BLNodeMainEntity MasterData) {
        this.MasterData = MasterData;
    }
}
