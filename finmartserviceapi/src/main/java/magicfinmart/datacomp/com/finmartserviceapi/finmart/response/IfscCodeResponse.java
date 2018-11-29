package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.IfscEntity;

/**
 * Created by IN-RB on 20-02-2018.
 */

public class IfscCodeResponse extends APIResponse {


    private List<IfscEntity> MasterData;

    public List<IfscEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<IfscEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
