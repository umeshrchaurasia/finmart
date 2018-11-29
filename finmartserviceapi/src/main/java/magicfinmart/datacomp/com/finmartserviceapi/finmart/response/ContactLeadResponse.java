package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 17-10-2018.
 */

public class ContactLeadResponse extends APIResponse {


    /**
     * MasterData : Sucess
     */

    private String MasterData;

    public String getMasterData() {
        return MasterData;
    }

    public void setMasterData(String MasterData) {
        this.MasterData = MasterData;
    }
}
