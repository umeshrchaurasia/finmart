package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ConstantEntity;

/**
 * Created by Rajeev Ranjan on 16/03/2018.
 */

public class ConstantsResponse extends APIResponse {
    /**
     * MasterData : {"VersionCode":0,"IsForceUpdate":0,"PBNoOfHits":5,"PBHitTime":6000,"ROIHLBL":8.3,"ROIPLBL":11.49,"ROILABL":8.75}
     */

    private ConstantEntity MasterData;

    public ConstantEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(ConstantEntity MasterData) {
        this.MasterData = MasterData;
    }


}
