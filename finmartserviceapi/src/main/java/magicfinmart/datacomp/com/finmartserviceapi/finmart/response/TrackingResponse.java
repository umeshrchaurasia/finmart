package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 21/03/2018.
 */

public class TrackingResponse extends APIResponse {
    /**
     * MasterData : saved
     */

    private String MasterData;

    public String getMasterData() {
        return MasterData;
    }

    public void setMasterData(String MasterData) {
        this.MasterData = MasterData;
    }
}
