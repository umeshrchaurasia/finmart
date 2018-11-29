package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PincodeResponseEntity;

/**
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public class PincodeResponse extends APIResponse {

    /**
     * HealthMasterData : {"map_id":34000,"pincode":"805110","postname":"Nawadah","districtname":"Nawada","stateid":5,"state_name":"BIHAR","cityid":247,"cityname":"Nawada"}
     */

    private PincodeResponseEntity MasterData;

    public PincodeResponseEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(PincodeResponseEntity MasterData) {
        this.MasterData = MasterData;
    }


}
