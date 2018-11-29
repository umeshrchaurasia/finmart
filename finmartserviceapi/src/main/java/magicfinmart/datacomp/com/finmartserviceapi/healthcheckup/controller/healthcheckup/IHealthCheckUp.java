package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.controller.healthcheckup;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksDetailsRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksRequestEntity;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public interface IHealthCheckUp {


    void getHealthPacks(HealthPacksRequestEntity healthPacksRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getHealthPacksDetails(HealthPacksDetailsRequestEntity healthPacksDetailsRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getHealthCheckList(IResponseSubcriber iResponseSubcriber);
}
