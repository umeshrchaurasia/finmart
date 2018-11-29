package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;
import java.util.Map;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.CCRblRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CCRblResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CreateQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.DocumentResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.OfflineInputResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.OfflineQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Nilesh birhade on 23/02/2018.
 */

public class OfflineQuoteRequestBuilder extends FinmartRetroRequestBuilder {

    public OfflineQuoteNetworkService getService() {

        return super.build().create(OfflineQuoteNetworkService.class);
    }

    public interface OfflineQuoteNetworkService {

        @Headers("token:" + token)
        @POST("/api/credit-card-rbl")
        Call<CCRblResponse> applyRbl(@Body CCRblRequestEntity ccRblRequestEntity);

        @Headers("token:" + token)
        @POST("/api/get-offline-quote-material")
        Call<OfflineInputResponse> getOfflineInput();

        @Headers("token:" + token)
        @POST("/api/offline-quotes-request")
        Call<CreateQuoteResponse> createQuote(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @Multipart
        @POST("/api/upload-doc-offline-quotes ")
        Call<DocumentResponse> uploadDocumentOfflineQuotes(@Part() MultipartBody.Part doc, @PartMap() Map<String, String> partMap);

        @Headers("token:" + token)
        @POST("/api/get-offline-quotes")
        Call<OfflineQuoteResponse> getOfflineQuote(@Body HashMap<String, String> body);



    }
}
