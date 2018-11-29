package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TicketEntity;

/**
 * Created by Rajeev Ranjan on 01/03/2018.
 */

public class TicketListResponse extends APIResponse {
    private List<TicketEntity> MasterData;

    public List<TicketEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<TicketEntity> MasterData) {
        this.MasterData = MasterData;
    }
}
