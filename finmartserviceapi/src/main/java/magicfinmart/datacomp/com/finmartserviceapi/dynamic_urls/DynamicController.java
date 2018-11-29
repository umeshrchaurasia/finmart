package magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls;

import android.content.Context;

import com.google.gson.Gson;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import magicfinmart.datacomp.com.finmartserviceapi.BuildConfig;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 06-08-2018.
 */

public class DynamicController implements IDynamic {

    DynamicUrlBuilder.GenericUrlNetworkService genericUrlNetworkService;
    Context mContext;

    public DynamicController(Context context) {
        genericUrlNetworkService = new DynamicUrlBuilder().getService();
        mContext = context;
    }

    @Override
    public void getVehicleByVehicleNo(final String vehicleNo, final IResponseSubcriber iResponseSubcriber) {
        String url = "http://inspection.policyboss.com/api/vehicle-info?v=" + vehicleNo;
        //MH43BE6262
        //String url = "http://202.131.96.98:8041/PolicyBossRegNoService.svc/GetRegNoData?v=" + vehicleNo;

        genericUrlNetworkService.getVehicleByVehicleNo(url).enqueue(new Callback<VehicleInfoEntity>() {
            @Override
            public void onResponse(Call<VehicleInfoEntity> call, Response<VehicleInfoEntity> response) {
                if (response.body() != null) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    new TrackingController(mContext).saveVehicleInfo(1, vehicleNo,
                            new Gson().toJson(response));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Vehicle detail not found."));
                }
            }

            @Override
            public void onFailure(Call<VehicleInfoEntity> call, Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }

            }
        });
    }

    @Override
    public void getVehicleByMobileNo(final String mobileNo, final IResponseSubcriber iResponseSubcriber) {

        String url = "http://inspection.policyboss.com/api/generic-info?m=" + mobileNo;

        genericUrlNetworkService.getVehicleByMobNo(url).enqueue(new Callback<VehicleMobileResponse>() {
            @Override
            public void onResponse(Call<VehicleMobileResponse> call, Response<VehicleMobileResponse> response) {

                if (response.body() != null) {
                    iResponseSubcriber.OnSuccess(response.body(), "Success");
                    new TrackingController(mContext).saveVehicleInfo(2, mobileNo,
                            new Gson().toJson(response));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Detail not found"));
                }
            }

            @Override
            public void onFailure(Call<VehicleMobileResponse> call, Throwable t) {

                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void saveGenerateLead(GenerateLeadRequestEntity entity, final IResponseSubcriber iResponseSubcriber) {

        String url = BuildConfig.FINMART_URL + "/api/save-moter-lead-details";
        genericUrlNetworkService.saveGenerateLead(url, entity).enqueue(new Callback<GenerateLeadResponse>() {
            @Override
            public void onResponse(Call<GenerateLeadResponse> call, Response<GenerateLeadResponse> response) {
                if (response.body() != null) {
                    iResponseSubcriber.OnSuccess(response.body(), "Success");
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Detail not found"));
                }
            }

            @Override
            public void onFailure(Call<GenerateLeadResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}
