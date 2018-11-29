package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.ExpressSaveResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 03-04-2018.
 */

public class ExpressSaveResponse extends APIResponse {


    private List<ExpressSaveResponseEntity> MasterData;

    public List<ExpressSaveResponseEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<ExpressSaveResponseEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
