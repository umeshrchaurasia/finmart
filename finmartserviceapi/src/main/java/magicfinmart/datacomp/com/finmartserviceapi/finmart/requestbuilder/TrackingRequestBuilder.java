package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TrackingResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class TrackingRequestBuilder extends FinmartRetroRequestBuilder {
    public TrackingRequestBuilder.TrackingNetworkService getService() {

        return super.build().create(TrackingRequestBuilder.TrackingNetworkService.class);
    }

    public interface TrackingNetworkService {

        @Headers("token:" + token)
        @POST("/api/insert-tracking")
        Call<TrackingResponse> sendTracking(@Body TrackingRequestEntity body);

        @Headers("token:" + token)
        @POST("/api/save-vehicle-info-details-log")
        Call<TrackingResponse> saveVehicleInfo(@Body HashMap<String, String> body);
    }
}
