package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.controller.healthcheckup;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.BuildConfig;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestbuilder.HealthCheckUpRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksDetailsRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthCheckUpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthPackDetailsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthPackResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public class HealthCheckUPController implements IHealthCheckUp {
    HealthCheckUpRequestBuilder.HealthCheckNetworkService healthCheckNetworkService;
    Context mContext;

    public HealthCheckUPController(Context context) {
        healthCheckNetworkService = new HealthCheckUpRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getHealthCheckList(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("FBAID", String.valueOf(new DBPersistanceController(mContext).getUserData().getFBAId()));

        String strUrl = BuildConfig.FINMART_URL + "/api/health-assure-configure";

        healthCheckNetworkService.getHealthCheckUpList(strUrl, body).enqueue(new Callback<HealthCheckUpResponse>() {
            @Override
            public void onResponse(Call<HealthCheckUpResponse> call, Response<HealthCheckUpResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.message());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                }

            }

            @Override
            public void onFailure(Call<HealthCheckUpResponse> call, Throwable t) {
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
    public void getHealthPacks(HealthPacksRequestEntity healthPacksRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        healthCheckNetworkService.getHealthPacks(healthPacksRequestEntity).enqueue(new Callback<HealthPackResponse>() {
            @Override
            public void onResponse(Call<HealthPackResponse> call, Response<HealthPackResponse> response) {

                if (response.body() != null) {
                    if (response.body().getD().getStatus().equals("Success")) {
                        new AsyncHealthPacks(mContext, response.body().getD()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getD().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getD().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }

            }

            @Override
            public void onFailure(Call<HealthPackResponse> call, Throwable t) {
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
    public void getHealthPacksDetails(final HealthPacksDetailsRequestEntity healthPacksDetailsRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        healthCheckNetworkService.getHealthPacksDetails(healthPacksDetailsRequestEntity).enqueue(new Callback<HealthPackDetailsResponse>() {
            @Override
            public void onResponse(Call<HealthPackDetailsResponse> call, Response<HealthPackDetailsResponse> response) {

                if (response.body() != null) {
                    if (response.body().getD().getStatus().equals("Success")) {
                        if (healthPacksDetailsRequestEntity != null)
                            response.body().getD().setPackcode(healthPacksDetailsRequestEntity.getPack_param().getPackcode());
                        new AsyncHealthPacksDetails(mContext, response.body().getD()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getD().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getD().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }

            }

            @Override
            public void onFailure(Call<HealthPackDetailsResponse> call, Throwable t) {
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
