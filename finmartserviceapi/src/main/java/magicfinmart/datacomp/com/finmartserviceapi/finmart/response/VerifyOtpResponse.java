package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public class VerifyOtpResponse extends APIResponse {
    private Object MasterData;

    public Object getMasterData() {
        return MasterData;
    }

    public void setMasterData(Object MasterData) {
        this.MasterData = MasterData;
    }


}
