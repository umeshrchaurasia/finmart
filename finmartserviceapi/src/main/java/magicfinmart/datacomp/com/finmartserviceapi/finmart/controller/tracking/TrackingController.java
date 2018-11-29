package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking;

import android.content.Context;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder.TrackingRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TrackingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajeev Ranjan on 21/03/2018.
 */

public class TrackingController implements ITracking {
    TrackingRequestBuilder.TrackingNetworkService trackingNetworkService;
    Context mContext;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginResponseEntity;

    public TrackingController(Context context) {
        trackingNetworkService = new TrackingRequestBuilder().getService();
        mContext = context;
        dbPersistanceController = new DBPersistanceController(context);
        loginResponseEntity = dbPersistanceController.getUserData();
    }

    @Override
    public void saveVehicleInfo(int Type, String vehNoMobNo, String responseJson) {

        HashMap<String, String> body = new HashMap<>();
        body.put("fbaid", String.valueOf(new DBPersistanceController(mContext).getUserData().getFBAId()));
        body.put("type", String.valueOf(Type));
        body.put("detail", String.valueOf(vehNoMobNo));
        body.put("data", responseJson);

        trackingNetworkService.saveVehicleInfo(body).enqueue(new Callback<TrackingResponse>() {
            @Override
            public void onResponse(Call<TrackingResponse> call, Response<TrackingResponse> response) {

            }

            @Override
            public void onFailure(Call<TrackingResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void sendData(TrackingRequestEntity trackingRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        if (loginResponseEntity != null) {
            trackingRequestEntity.setFBAID(String.valueOf(loginResponseEntity.getFBAId()));
        } else {
            trackingRequestEntity.setFBAID("0");
        }

        trackingNetworkService.sendTracking(trackingRequestEntity).enqueue(new Callback<TrackingResponse>() {
            @Override
            public void onResponse(Call<TrackingResponse> call, Response<TrackingResponse> response) {
               /* if (response.body() != null && iResponseSubcriber != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }*/
            }

            @Override
            public void onFailure(Call<TrackingResponse> call, Throwable t) {
               /* if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }*/
            }
        });
    }
}
