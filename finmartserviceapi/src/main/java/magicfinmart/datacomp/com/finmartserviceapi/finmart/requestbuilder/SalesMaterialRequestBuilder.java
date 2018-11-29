package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SalesMaterialProductResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SalesPromotionResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Nilesh birhade on 22/02/2018.
 */

public class SalesMaterialRequestBuilder extends FinmartRetroRequestBuilder {
    public SalesMaterialRequestBuilder.SalesMaterialNetworkService getService() {

        return super.build().create(SalesMaterialRequestBuilder.SalesMaterialNetworkService.class);
    }

    public interface SalesMaterialNetworkService {

        @Headers("token:" + token)
        @POST("/api/sales-material-product")
        Call<SalesMaterialProductResponse> getSalesProducts();


        @Headers("token:" + token)
        @POST("/api/sales-material-product-details")
        Call<SalesPromotionResponse> getProductPromotions(@Body HashMap<String, String> body);
    }
}
