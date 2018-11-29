package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.RblCityEntity;

/**
 * Created by Nilesh Birhade on 28-02-2018.
 */

public class RblCityMasterResponse extends APIResponse {

    private List<RblCityEntity> MasterData;

    public List<RblCityEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<RblCityEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
