package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.CityEntity;

public class CityResponse extends APIResponse {


    private List<CityEntity> data;

    public List<CityEntity> getData() {
        return data;
    }

    public void setData(List<CityEntity> data) {
        this.data = data;
    }


}