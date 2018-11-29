package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;

/**
 * Created by Rajeev Ranjan on 21/03/2018.
 */

public interface ITracking {
    void sendData(TrackingRequestEntity trackingRequestEntity, IResponseSubcriber iResponseSubcriber);

    void saveVehicleInfo(int Type, String vehNoMobNo, String responseJson);
}
