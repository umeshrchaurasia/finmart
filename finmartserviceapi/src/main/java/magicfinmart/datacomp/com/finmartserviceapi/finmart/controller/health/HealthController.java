package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.health;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder.HealthRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.HealthCompareRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.BenefitsListResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteAppResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteCompareResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteExpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuotetoAppResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthShortLinkResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 09/02/2018.
 */

public class HealthController implements IHealth {

    HealthRequestBuilder.HealthNetworkService healthNetworkService;
    Context mContext;

    public HealthController(Context context) {
        healthNetworkService = new HealthRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getShortLink(String Name, final magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("fbaid", String.valueOf(new DBPersistanceController(mContext).getUserData().getFBAId()));
        body.put("name", Name);
        body.put("phoneno", String.valueOf(new DBPersistanceController(mContext).getUserData().getMobiNumb1()));

        healthNetworkService.getShortLink(body).enqueue(new Callback<HealthShortLinkResponse>() {
            @Override
            public void onResponse(Call<HealthShortLinkResponse> call, Response<HealthShortLinkResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<HealthShortLinkResponse> call, Throwable t) {
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
    public void getHealthQuoteExp(HealthQuote quote, final IResponseSubcriber iResponseSubcriber) {
        healthNetworkService.getHealthQuoteExp(quote).enqueue(new Callback<HealthQuoteExpResponse>() {
            @Override
            public void onResponse(Call<HealthQuoteExpResponse> call, Response<HealthQuoteExpResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }

            }

            @Override
            public void onFailure(Call<HealthQuoteExpResponse> call, Throwable t) {
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
    public void getHealthQuote(HealthQuote quote, final IResponseSubcriber iResponseSubcriber) {

        healthNetworkService.getHealthQuote(quote).enqueue(new Callback<HealthQuoteResponse>() {
            @Override
            public void onResponse(Call<HealthQuoteResponse> call, Response<HealthQuoteResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }

            }

            @Override
            public void onFailure(Call<HealthQuoteResponse> call, Throwable t) {
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
    public void getHealthQuoteApplicationList(int count, int type, String fbaID, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("fba_id", fbaID);
        body.put("count", "" + count);
        body.put("type", "" + type);

        healthNetworkService.getHealthQuoteAppList(body).enqueue(new Callback<HealthQuoteAppResponse>() {
            @Override
            public void onResponse(Call<HealthQuoteAppResponse> call, Response<HealthQuoteAppResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<HealthQuoteAppResponse> call, Throwable t) {
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
    public void convertQuoteToApp(String healthRequestID, String insurerID, String insImage, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("HealthRequestId", healthRequestID);
        body.put("selectedPrevInsID", insurerID);
        body.put("insImage", insImage);

        healthNetworkService.convertHealthQuoteToApp(body).enqueue(new Callback<HealthQuotetoAppResponse>() {
            @Override
            public void onResponse(Call<HealthQuotetoAppResponse> call, Response<HealthQuotetoAppResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<HealthQuotetoAppResponse> call, Throwable t) {
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
    public void deleteQuote(String healthRequestID, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<String, String>();
        body.put("HealthRequestId", healthRequestID);


        healthNetworkService.deleteQuote(body).enqueue(new Callback<HealthDeleteResponse>() {
            @Override
            public void onResponse(Call<HealthDeleteResponse> call, Response<HealthDeleteResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<HealthDeleteResponse> call, Throwable t) {
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
    public void compareQuote(HealthCompareRequestEntity compareRequestEntity, final IResponseSubcriber iResponseSubcriber) {

       /* healthNetworkService.comparePHPQuotes("http://bo.mgfm.in/api/compare-premium",
                compareRequestEntity)
                .enqueue(new Callback<HealthQuoteCompareResponse>() {
                    @Override
                    public void onResponse(Call<HealthQuoteCompareResponse> call, Response<HealthQuoteCompareResponse> response) {
                        if (response.body() != null) {
                            if (response.body().getStatusNo() == 0) {
                                iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                            } else {
                                iResponseSubcriber.OnFailure(new RuntimeException("FAILURE"));
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                        }
                    }

                    @Override
                    public void onFailure(Call<HealthQuoteCompareResponse> call, Throwable t) {
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
                });*/
        healthNetworkService.compareQuotes(compareRequestEntity).enqueue(new Callback<HealthQuoteCompareResponse>() {
            @Override
            public void onResponse(Call<HealthQuoteCompareResponse> call, Response<HealthQuoteCompareResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException("FAILURE"));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }

            }

            @Override
            public void onFailure(Call<HealthQuoteCompareResponse> call, Throwable t) {
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
    public void getlistBenefits(String ProdBeneID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("StrProdBeneID", ProdBeneID);

        healthNetworkService.getBenefits(body).enqueue(new Callback<BenefitsListResponse>() {
            @Override
            public void onResponse(Call<BenefitsListResponse> call, Response<BenefitsListResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }

            }

            @Override
            public void onFailure(Call<BenefitsListResponse> call, Throwable t) {
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
