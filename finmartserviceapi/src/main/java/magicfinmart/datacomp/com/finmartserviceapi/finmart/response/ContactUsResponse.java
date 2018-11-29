package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ContactUsEntity;

/**
 * Created by Rajeev Ranjan on 22/02/2018.
 */

public class ContactUsResponse extends APIResponse {

    private List<ContactUsEntity> MasterData;

    public List<ContactUsEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<ContactUsEntity> MasterData) {
        this.MasterData = MasterData;
    }


}
