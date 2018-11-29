package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.LoginRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ChangePasswordResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ForgotResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.LoginResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ReferFriendResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.retrobuilder.FinmartRetroRequestBuilder;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Rajeev Ranjan on 25/01/2018.
 */

public class LoginRequestBuilder extends FinmartRetroRequestBuilder {

    public LoginRequestBuilder.LoginNetworkService getService() {

        return super.build().create(LoginRequestBuilder.LoginNetworkService.class);
    }

    public interface LoginNetworkService {

        @Headers("token:" + token)
        @POST("/api/login")
        Call<LoginResponse> login(@Body LoginRequestEntity body);

        @Headers("token:" + token)
        @POST("/api/forgotPassword")
        Call<ForgotResponse> forgotPassword(@Body HashMap<String, String> body);


        @Headers("token:" + token)
        @POST("/api/change-password")
        Call<ChangePasswordResponse> changePassword(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/validate-refer-code")
        Call<ReferFriendResponse> referFriend(@Body HashMap<String, String> body);
    }
}
