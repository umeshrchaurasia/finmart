package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder;

import java.util.HashMap;
import java.util.Map;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.ContactLeadRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.RegisterRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ChildPospResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ContactLeadResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.DocumentResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.EnrollPospResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.GenerateOtpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.IfscCodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MyAccountResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MyAcctDtlResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.NotificationResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.NotificationUpdateResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PincodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PospDetailsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RegisterFbaResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RegisterSourceResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.VerifyOtpResponse;
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
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public class RegisterRequestBuilder extends FinmartRetroRequestBuilder {

    public RegisterRequestBuilder.RegisterQuotesNetworkService getService() {

        return super.build().create(RegisterRequestBuilder.RegisterQuotesNetworkService.class);
    }

    public interface RegisterQuotesNetworkService {

        @Headers("token:" + token)
        @POST("/api/generate-otp")
        Call<GenerateOtpResponse> generateOtp(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/retrive-otp")
        Call<VerifyOtpResponse> verifyOtp(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/get-city-and-state")
        Call<PincodeResponse> getCityStateCityPincode(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/insert-fba-registration")
        Call<RegisterFbaResponse> registerFba(@Body RegisterRequestEntity body);

        @Headers("token:" + token)
        @POST("/api/AddChildPosp")
        Call<RegisterFbaResponse> addChildPosp(@Body RegisterRequestEntity body);

        @Headers("token:" + token)
        @POST("/api/get-child-fba")
        Call<ChildPospResponse> getChildPosp(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/posp-registration")
        Call<EnrollPospResponse> enrollPosp(@Body RegisterRequestEntity body);

        @Headers("token:" + token)
        @POST("/api/get-posp-detail")
        Call<PospDetailsResponse> getPospDetails(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/get-ifsc-code")
        Call<IfscCodeResponse> getIfscCode(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/my-account")
        Call<MyAccountResponse> saveAccDtl(@Body RegisterRequestEntity body);


        @Headers("token:" + token)
        @POST("/api/get-my-account")
        Call<MyAcctDtlResponse> getMyAcctDtl(@Body HashMap<String, String> body);


        @Headers("token:" + token)
        @POST("/api/get-registration-source")
        Call<RegisterSourceResponse> getRegSource();


        @Headers("token:" + token)
        @Multipart
        @POST("/api/upload-doc")
        Call<DocumentResponse> uploadDocument(@Part() MultipartBody.Part doc, @PartMap() Map<String, String> partMap);

        @Headers("token:" + token)
        @Multipart
        @POST("/api/upload-doc")
        Call<DocumentResponse> uploadDocumentNew(@Part() MultipartBody.Part doc, @PartMap() Map<String, String> partMapString, @PartMap() Map<String, Integer> partMapInt);


////////////////////// Notification ////////////////////////////////

        @Headers("token:" + token)
        @POST("/api/get-notification-data")
        Call<NotificationResponse> getNotificationData(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/receive-update-notification")
        Call<NotificationUpdateResponse> recieveNotificationData(@Body HashMap<String, String> body);

        @Headers("token:" + token)
        @POST("/api/update-notification")
        Call<NotificationUpdateResponse> userClickActionOnNotification(@Body HashMap<String, String> body);

        ////////////////////// Contact Lead ////////////////////////////////

        @Headers("token:" + token)
        @POST("/api/addcontacts")
        Call<ContactLeadResponse> saveContactLead(@Body ContactLeadRequestEntity body);
    }
}
