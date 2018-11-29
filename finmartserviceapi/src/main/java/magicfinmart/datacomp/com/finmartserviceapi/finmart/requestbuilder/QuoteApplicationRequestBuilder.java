package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;


import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.SaveMotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteAppUpdateDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteApplicationResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SaveQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.QuoteApplicationRequestEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by Nilesh birhade on 25-01-2018.
 */

public class QuoteApplicationRequestBuilder extends FinmartRetroRequestBuilder {


    public QuoteApplicationRequestBuilder.QuoteApplicationNetworkService getService() {

        return super.build().create(QuoteApplicationRequestBuilder.QuoteApplicationNetworkService.class);
    }

    public interface QuoteApplicationNetworkService {

        @Headers("token:" + token)
        @POST("/api/get-vehicle-request")
        Call<QuoteApplicationResponse> getQuoteApplication(@Body QuoteApplicationRequestEntity entity);

        @Headers("token:" + token)
        @POST("/api/manage-vehicle")
        Call<SaveQuoteResponse> saveMotorRequest(@Body SaveMotorRequestEntity entity);

        @Headers("token:" + token)
        @POST("/api/set-quote-to-application-vehicle")
        Call<QuoteAppUpdateDeleteResponse> quoteToApplication(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/delete-vehicle-request")
        Call<QuoteAppUpdateDeleteResponse> deleteQuote(@Body HashMap<String, String> body);

    }

}
