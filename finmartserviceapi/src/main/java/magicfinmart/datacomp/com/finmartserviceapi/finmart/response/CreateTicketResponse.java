package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CreateTicketEntity;

/**
 * Created by Rajeev Ranjan on 01/03/2018.
 */

public class CreateTicketResponse extends APIResponse {
    private List<CreateTicketEntity> MasterData;

    public List<CreateTicketEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<CreateTicketEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
