package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 09/10/2018.
 */

public class ChildPospResponse extends APIResponse {

    private List<ChildPospEntity> MasterData;

    public List<ChildPospEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<ChildPospEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
