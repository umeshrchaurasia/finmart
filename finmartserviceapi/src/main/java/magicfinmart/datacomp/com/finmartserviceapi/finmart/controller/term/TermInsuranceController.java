package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.term;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder.TermRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermFinmartRequest;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.DeleteTermQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermCompareQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermQuoteApplicationResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermQuoteToAppResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.UpdateCRNResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 09/02/2018.
 */

public class TermInsuranceController implements ITermInsurance {
    TermRequestBuilder.TermNetworkService termNetworkService;
    Context mContext;

    public TermInsuranceController(Context context) {
        termNetworkService = new TermRequestBuilder().getService();
        mContext = context;
    }

    @Override
    public void getTermQuoteApplicationList(int insurerID, int count, String type, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("InsurerId", String.valueOf(insurerID));

        body.put("count", String.valueOf(count));
        body.put("type", String.valueOf(type));
        body.put("fba_id", String.valueOf(new DBPersistanceController(mContext).getUserData().getFBAId()));

        termNetworkService.getTermQuoteApplication(body).enqueue(new Callback<TermQuoteApplicationResponse>() {
            @Override
            public void onResponse(Call<TermQuoteApplicationResponse> call, Response<TermQuoteApplicationResponse> response) {
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
            public void onFailure(Call<TermQuoteApplicationResponse> call, Throwable t) {
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
    public void getTermInsurer(TermFinmartRequest entity, final IResponseSubcriber iResponseSubcriber) {
        termNetworkService.getTermCompareQuotes(entity).enqueue(new Callback<TermCompareQuoteResponse>() {
            @Override
            public void onResponse(Call<TermCompareQuoteResponse> call, Response<TermCompareQuoteResponse> response) {
                if (iResponseSubcriber != null) {
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

            }

            @Override
            public void onFailure(Call<TermCompareQuoteResponse> call, Throwable t) {
                if (iResponseSubcriber != null) {
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
            }
        });
    }

    @Override
    public void deleteTermQuote(String termRequestId, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("termRequestId", termRequestId);
        termNetworkService.deleteTermInsurance(body).enqueue(new Callback<DeleteTermQuoteResponse>() {
            @Override
            public void onResponse(Call<DeleteTermQuoteResponse> call, Response<DeleteTermQuoteResponse> response) {

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
            public void onFailure(Call<DeleteTermQuoteResponse> call, Throwable t) {
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
    public void convertQuoteToApp(String termRequestId, String InsurerId, String fba_id, String NetPremium, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("termRequestId", termRequestId);
        body.put("InsurerId", InsurerId);
        body.put("fba_id", fba_id);
        body.put("NetPremium", NetPremium);

        termNetworkService.convertQuoteToApp(body).enqueue(new Callback<TermQuoteToAppResponse>() {
            @Override
            public void onResponse(Call<TermQuoteToAppResponse> call, Response<TermQuoteToAppResponse> response) {

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
            public void onFailure(Call<TermQuoteToAppResponse> call, Throwable t) {
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

    /*
    * {"termRequestId":"47","fba_id":"39774","Existing_ProductInsuranceMapping_Id":"255"}
    * */
    @Override
    public void updateCRN(int termRequestID, int crn, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("termRequestId", "" + termRequestID);
        body.put("Existing_ProductInsuranceMapping_Id", "" + crn);
        body.put("fba_id", "" + new DBPersistanceController(mContext).getUserData().getFBAId());

        termNetworkService.updateCRN(body).enqueue(new Callback<UpdateCRNResponse>() {
            @Override
            public void onResponse(Call<UpdateCRNResponse> call, Response<UpdateCRNResponse> response) {
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
            public void onFailure(Call<UpdateCRNResponse> call, Throwable t) {
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
