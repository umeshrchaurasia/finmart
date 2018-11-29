package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CityMasterEntity;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class CityMasterResponse extends APIResponse {

    private List<CityMasterEntity> MasterData;

    public List<CityMasterEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<CityMasterEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
