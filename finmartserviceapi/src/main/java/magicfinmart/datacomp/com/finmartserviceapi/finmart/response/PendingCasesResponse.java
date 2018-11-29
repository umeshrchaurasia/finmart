package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PendingCasesEntity;

/**
 * Created by Nilesh Birhade on 19-02-2018.
 */

public class PendingCasesResponse extends APIResponse {

    private List<PendingCasesEntity> MasterData;

    public List<PendingCasesEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<PendingCasesEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
