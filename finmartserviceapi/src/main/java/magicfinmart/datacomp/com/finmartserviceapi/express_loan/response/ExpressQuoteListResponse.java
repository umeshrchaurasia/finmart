package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.ExpressQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
/**
 * Created by IN-RB on 03-04-2018.
 */

public class ExpressQuoteListResponse extends APIResponse {


    private List<ExpressQuoteEntity> MasterData;

    public List<ExpressQuoteEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<ExpressQuoteEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
