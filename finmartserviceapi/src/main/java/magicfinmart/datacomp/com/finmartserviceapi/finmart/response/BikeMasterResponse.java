package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BikeMasterEntity;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class BikeMasterResponse extends APIResponse {
    private List<BikeMasterEntity> MasterData;

    public List<BikeMasterEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<BikeMasterEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
