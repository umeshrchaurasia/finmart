package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 05/10/2018.
 */

public class InsuranceSubtypeResponse  extends APIResponse{

    private List<InsuranceSubtypeEntity> MasterData;

    public List<InsuranceSubtypeEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<InsuranceSubtypeEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
