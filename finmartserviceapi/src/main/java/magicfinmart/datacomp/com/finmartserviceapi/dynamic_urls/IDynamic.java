package magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

/**
 * Created by Nilesh Birhade on 06-08-2018.
 */

public interface IDynamic {

    void getVehicleByVehicleNo(String vehicleNo, IResponseSubcriber iResponseSubcriber);

    void getVehicleByMobileNo(String mobileNo, IResponseSubcriber iResponseSubcriber);

    void saveGenerateLead(GenerateLeadRequestEntity entity, IResponseSubcriber iResponseSubcriber);

    // qa.mgfm.in/api/save-moter-lead-details
}
