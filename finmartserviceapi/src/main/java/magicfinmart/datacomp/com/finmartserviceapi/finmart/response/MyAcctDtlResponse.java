package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.AccountDtlEntity;

/**
 * Created by IN-RB on 21-02-2018.
 */

public class MyAcctDtlResponse extends APIResponse {


    private List<AccountDtlEntity> MasterData;

    public List<AccountDtlEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<AccountDtlEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
