package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CarMasterEntity;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class CarMasterResponse extends APIResponse {

    private List<CarMasterEntity> MasterData;

    public List<CarMasterEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<CarMasterEntity> MasterData) {
        this.MasterData = MasterData;
    }
}
