package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BenefitsEntity;

/**
 * Created by Nilesh Birhade on 09-03-2018.
 */

public class BenefitsListResponse extends APIResponse {


    private List<BenefitsEntity> MasterData;

    public List<BenefitsEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<BenefitsEntity> MasterData) {
        this.MasterData = MasterData;
    }

}
