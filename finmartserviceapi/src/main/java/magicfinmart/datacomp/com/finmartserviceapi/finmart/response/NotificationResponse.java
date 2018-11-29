package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.NotificationEntity;

/**
 * Created by IN-RB on 22-02-2018.
 */

public class NotificationResponse extends APIResponse {


    private List<NotificationEntity> MasterData;

    public List<NotificationEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<NotificationEntity> MasterData) {
        this.MasterData = MasterData;
    }

}
