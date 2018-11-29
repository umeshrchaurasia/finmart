package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;


/**
 * Created by IN-RB on 30-10-2018.
 */

public class TransctionHistoryResponse extends APIResponse {
    private List<TransctionHistory> MasterData;

    public List<TransctionHistory> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<TransctionHistory> MasterData) {
        this.MasterData = MasterData;
    }



//    private List<TransctionHistory> transctionHistory;
//
//    public List<TransctionHistory> getMasterData() {
//        return transctionHistory;
//    }
//
//    public void setMasterData(List<TransctionHistory> transctionHistory) {
//        this.transctionHistory = transctionHistory;
//    }


}
