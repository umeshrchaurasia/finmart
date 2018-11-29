package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.AppliedCreditCardEntity;

/**
 * Created by Nilesh Birhade on 27-02-2018.
 */

public class AppliedCreditCardResponse extends APIResponse {

    private List<AppliedCreditCardEntity> MasterData;

    public List<AppliedCreditCardEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<AppliedCreditCardEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
