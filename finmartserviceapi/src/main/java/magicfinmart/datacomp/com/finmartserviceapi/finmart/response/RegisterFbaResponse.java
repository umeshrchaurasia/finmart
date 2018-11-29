package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.RegisterResponseEntity;

/**
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public class RegisterFbaResponse extends APIResponse {

    /**
     * HealthMasterData : {"SavedStatus":0,"Message":"Record saved successfully.","FBAID":35770}
     */

    private RegisterResponseEntity MasterData;

    public RegisterResponseEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(RegisterResponseEntity MasterData) {
        this.MasterData = MasterData;
    }


}
