package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.fastlane;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public interface IFastLane {
    void getVechileDetails(String RegistrationNumber, IResponseSubcriber iResponseSubcriber);
}
