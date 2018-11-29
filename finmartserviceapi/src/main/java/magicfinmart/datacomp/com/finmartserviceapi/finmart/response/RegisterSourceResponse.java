package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 02-08-2018.
 */

public class RegisterSourceResponse extends APIResponse {

    private List<SourceEntity> MasterData;

    public List<SourceEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<SourceEntity> MasterData) {
        this.MasterData = MasterData;
    }
}
