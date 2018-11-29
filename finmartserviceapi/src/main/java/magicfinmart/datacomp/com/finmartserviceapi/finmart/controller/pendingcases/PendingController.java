package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.pendingcases;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder.PendingCasesRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseInsLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCasesResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TransctionHistory;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TransctionHistoryResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberERP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 09/02/2018.
 */

public class PendingController implements IPendingCases {

    PendingCasesRequestBuilder.PendingNetworkService pendingNetworkService;
    Context mContext;

    public PendingController(Context context) {
        pendingNetworkService = new PendingCasesRequestBuilder().getService();
        mContext = context;
    }


    @Override
    public void getPendingCases(int count,int type,String fbaID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("FBAID", fbaID);
        body.put("count",""+count);
        //body.put("type",""+type)

        pendingNetworkService.getPendingCases(body).enqueue(new Callback<PendingCasesResponse>() {
            @Override
            public void onResponse(Call<PendingCasesResponse> call, Response<PendingCasesResponse> response) {
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
            public void onFailure(Call<PendingCasesResponse> call, Throwable t) {
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
    public void deletePending(String quoteType, int pendingID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("quotetype", quoteType);
        body.put("id", String.valueOf(pendingID));

        pendingNetworkService.deletePendingCase(body).enqueue(new Callback<PendingCaseDeleteResponse>() {
            @Override
            public void onResponse(Call<PendingCaseDeleteResponse> call, Response<PendingCaseDeleteResponse> response) {

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
            public void onFailure(Call<PendingCaseDeleteResponse> call, Throwable t) {
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
    public void getPendingCasesWithType(int count,int Type,String fbaID, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("FBAID", fbaID);
        body.put("count",""+count);
        body.put("Type",""+Type);

        pendingNetworkService.getPendingCasesWithType(body).enqueue(new Callback<PendingCaseInsLoanResponse>() {
            @Override
            public void onResponse(Call<PendingCaseInsLoanResponse> call, Response<PendingCaseInsLoanResponse> response) {
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
            public void onFailure(Call<PendingCaseInsLoanResponse> call, Throwable t) {
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

    //Transaction History

    @Override
    public void gettransactionhistory(String empCode, String pgNo,final IResponseSubcriber iResponseSubcriber) {


        HashMap<String, String> body = new HashMap<String, String>();
        body.put("fbaid", empCode);
        body.put("pageno", pgNo);


        pendingNetworkService.gettransctionHistory(body).enqueue(new Callback<TransctionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransctionHistoryResponse> call, Response<TransctionHistoryResponse> response) {
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
            public void onFailure(Call<TransctionHistoryResponse> call, Throwable t) {
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
