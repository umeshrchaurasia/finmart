package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.HealthCheckUPRetroRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksDetailsRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthCheckUpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthPackDetailsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthPackResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public class HealthCheckUpRequestBuilder extends HealthCheckUPRetroRequestBuilder {
    public HealthCheckNetworkService getService() {

        return super.build().create(HealthCheckNetworkService.class);
    }

    public interface HealthCheckNetworkService {


        @POST("/Products/HAMobileProductService.asmx/PackDetails")
        Call<HealthPackResponse> getHealthPacks(@Body HealthPacksRequestEntity body);

        @POST("/Products/HAMobileProductService.asmx/PackParam")
        Call<HealthPackDetailsResponse> getHealthPacksDetails(@Body HealthPacksDetailsRequestEntity body);

        @Headers("token:" + token)
        @POST
        Call<HealthCheckUpResponse> getHealthCheckUpList(@Url String strUrl, @Body HashMap<String, String> body);


    }
}
