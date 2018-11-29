package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.QuickLeadRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuickLeadResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Nilesh birhade on 23/02/2018.
 */

public class QuickLeadRequestBuilder extends FinmartRetroRequestBuilder {

    public QuickLeadNetworkService getService() {

        return super.build().create(QuickLeadNetworkService.class);
    }

    public interface QuickLeadNetworkService {

        @Headers("token:" + token)
        @POST("/api/quick-lead")
        Call<QuickLeadResponse> getAllCreditCards(@Body QuickLeadRequestEntity quickLeadRequestEntity);

    }
}
