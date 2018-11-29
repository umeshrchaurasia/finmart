package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.WhatsNewEntity;

/**
 * Created by Rajeev Ranjan on 23/02/2018.
 */

public class WhatsNewResponse extends APIResponse {
    private List<WhatsNewEntity> MasterData;

    public List<WhatsNewEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<WhatsNewEntity> MasterData) {
        this.MasterData = MasterData;
    }
}
