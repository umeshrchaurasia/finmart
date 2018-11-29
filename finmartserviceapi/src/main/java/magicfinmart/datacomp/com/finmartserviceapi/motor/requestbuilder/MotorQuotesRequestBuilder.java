package magicfinmart.datacomp.com.finmartserviceapi.motor.requestbuilder;

import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.BikePremiumRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.SaveAddOnRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikePremiumResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikeUniqueResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.SaveAddOnResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.retrobuilder.NodeRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MotorQuotesRequestBuilder extends NodeRetroRequestBuilder {


    public MotorQuotesNetworkService getService() {

        return super.build().create(MotorQuotesNetworkService.class);
    }

    public interface MotorQuotesNetworkService {

        @POST("/quote/premium_initiate")
        Call<BikeUniqueResponse> premiumInitiateUniqueID(@Body MotorRequestEntity body);

        @POST("/quote/premium_list_db")
        Call<BikePremiumResponse> getPremiumList(@Body BikePremiumRequestEntity body);

        @POST("/quote/save_user_data")
        Call<SaveAddOnResponse> saveAddOn(@Body SaveAddOnRequestEntity body);
    }
}