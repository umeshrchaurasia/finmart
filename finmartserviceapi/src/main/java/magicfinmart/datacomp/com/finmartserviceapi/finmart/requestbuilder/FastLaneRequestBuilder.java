package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.FastLaneDataResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class FastLaneRequestBuilder extends FinmartRetroRequestBuilder {
    public FastLaneRequestBuilder.FastLaneNetworkService getService() {

        return super.build().create(FastLaneRequestBuilder.FastLaneNetworkService.class);
    }

    public interface FastLaneNetworkService {

        @Headers("token:" + token)
        @POST("/api/vehicle-info")
        Call<FastLaneDataResponse> getFastLaneData(@Body HashMap<String, String> body);
    }
}
