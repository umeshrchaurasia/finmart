package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.CreateTicketrequest;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CreateTicketResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TicketCategoryResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TicketListResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Rajeev Ranjan on 23/01/2018.
 */

public class ZohoRequestBuilder extends FinmartRetroRequestBuilder {



    public ZohoRequestBuilder.ZohoNetworkService getService() {

        return super.build().create(ZohoRequestBuilder.ZohoNetworkService.class);
    }

    public interface ZohoNetworkService {


        @Headers("token:" + token)
        @POST("/api/get-ticket-categories")
        Call<TicketCategoryResponse> getTicketCategories();

        @Headers("token:" + token)
        @POST("/api/create-ticket")
        Call<CreateTicketResponse> createTicket(@Body CreateTicketrequest body);

        @Headers("token:" + token)
        @POST("/api/get-ticket-request")
        Call<TicketListResponse> getListOfTickets(@Body HashMap<String, String> body);
    }
}
