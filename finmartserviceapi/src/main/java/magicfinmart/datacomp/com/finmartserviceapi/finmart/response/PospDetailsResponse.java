package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PospDetailsEntity;

/**
 * Created by Rajeev Ranjan on 21/02/2018.
 */

public class PospDetailsResponse extends APIResponse {
    private List<PospDetailsEntity> MasterData;

    public List<PospDetailsEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<PospDetailsEntity> MasterData) {
        this.MasterData = MasterData;
    }
}
