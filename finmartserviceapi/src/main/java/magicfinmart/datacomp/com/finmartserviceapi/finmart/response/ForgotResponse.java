package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 27-03-2018.
 */

public class ForgotResponse extends APIResponse {

    /**
     * MasterData : Email has been sent on your registered Email address.
     */

    private String MasterData;

    public String getMasterData() {
        return MasterData;
    }

    public void setMasterData(String MasterData) {
        this.MasterData = MasterData;
    }
}
