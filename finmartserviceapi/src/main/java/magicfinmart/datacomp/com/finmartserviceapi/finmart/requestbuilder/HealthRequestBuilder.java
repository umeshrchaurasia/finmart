package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.HealthCompareRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.BenefitsListResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteAppResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteCompareResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteExpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuotetoAppResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthShortLinkResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Rajeev Ranjan on 25/01/2018.
 */

public class HealthRequestBuilder extends FinmartRetroRequestBuilder {

    public HealthNetworkService getService() {

        return super.build().create(HealthNetworkService.class);
    }

    public interface HealthNetworkService {

        @Headers("token:" + token)
        @POST("/api/get-smart-health")
        Call<HealthQuoteAppResponse> getHealthQuoteAppList(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/smart-health")
        Call<HealthQuoteResponse> getHealthQuote(@Body HealthQuote body);

        @Headers("token:" + token)
        @POST("/api/smart-health")
        Call<HealthQuoteExpResponse> getHealthQuoteExp(@Body HealthQuote body);


        @Headers("token:" + token)
        @POST("/api/set-quote-application-smart-health")
        Call<HealthQuotetoAppResponse> convertHealthQuoteToApp(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/delete-smart-health")
        Call<HealthDeleteResponse> deleteQuote(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/compare-premium")
        Call<HealthQuoteCompareResponse> compareQuotes(@Body HealthCompareRequestEntity entity);

        @Headers("token:" + token)
        @POST("/api/GetCompareBenefits")
        Call<BenefitsListResponse> getBenefits(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/short-url")
        Call<HealthShortLinkResponse> getShortLink(@Body HashMap<String, String> bo);

        @POST
        Call<HealthQuoteCompareResponse> comparePHPQuotes(@Url String url, @Body HealthCompareRequestEntity entity);

    }
}
