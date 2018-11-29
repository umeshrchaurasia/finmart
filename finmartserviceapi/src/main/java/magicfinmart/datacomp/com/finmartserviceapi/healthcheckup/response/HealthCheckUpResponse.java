package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthCEntity;

/**
 * Created by Nilesh Birhade on 26-07-2018.
 */

public class HealthCheckUpResponse extends APIResponse {

    private List<HealthCEntity> MasterData;

    public List<HealthCEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<HealthCEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
