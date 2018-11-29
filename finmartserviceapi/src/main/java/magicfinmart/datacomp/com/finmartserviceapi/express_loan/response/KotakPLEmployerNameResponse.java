package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.KotakPLEmployerNameEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Vinit Chindarkar on 05-07-2018.
 */

public class KotakPLEmployerNameResponse extends APIResponse {
    private List<KotakPLEmployerNameEntity> MasterData;

    public List<KotakPLEmployerNameEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<KotakPLEmployerNameEntity> MasterData) {
        this.MasterData = MasterData;
    }
}
