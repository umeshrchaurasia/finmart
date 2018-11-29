package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.OfflineQuoteEntity;

/**
 * Created by IN-RB on 09-11-2018.
 */

public class OfflineQuoteResponse extends APIResponse {


    private List<OfflineQuoteEntity> MasterData;

    public List<OfflineQuoteEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<OfflineQuoteEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
