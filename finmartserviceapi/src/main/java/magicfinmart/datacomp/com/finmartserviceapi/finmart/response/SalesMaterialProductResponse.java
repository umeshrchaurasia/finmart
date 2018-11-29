package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.SalesProductEntity;

/**
 * Created by Nilesh Birhade on 22-02-2018.
 */

public class SalesMaterialProductResponse extends APIResponse {

    private List<SalesProductEntity> MasterData;

    public List<SalesProductEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<SalesProductEntity> MasterData) {
        this.MasterData = MasterData;
    }


}

