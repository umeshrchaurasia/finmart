package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.personalloan;

import android.content.Context;

import com.google.gson.JsonParseException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import magicfinmart.datacomp.com.finmartserviceapi.R;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestbuilder.PersonalloanRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.PersonalLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.equifax_personalloan_request;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetBLDispalyResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetPersonalLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.equifax_personalloan_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IN-RB on 15-01-2018.
 */

public class PersonalLoanController  implements IPersonalLoan {

    Context mContext;
    PersonalloanRequestBuilder.PersonalloanNetworkService personalloanNetworkService;

    public PersonalLoanController(Context context) {
        this.mContext = context;
        personalloanNetworkService = new PersonalloanRequestBuilder().getService();
    }

    @Override
    public void getPersonalLoan(PersonalLoanRequest personalLoanRequest, final IResponseSubcriber iResponseSubcriber) {

        personalloanNetworkService.getPersonalQuotes(personalLoanRequest).enqueue(new Callback<GetPersonalLoanResponse>() {
            @Override
            public void onResponse(Call<GetPersonalLoanResponse> call, Response<GetPersonalLoanResponse> response) {
                if (response.body().getStatus_Id() == 0) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                }

            }

            @Override
            public void onFailure(Call<GetPersonalLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });
    }

    @Override
    public void getBLQuote(BLLoanRequest blLoanRequest, final IResponseSubcriber iResponseSubcriber) {
        personalloanNetworkService.getBLDispalyResponseQuotes(blLoanRequest).enqueue(new Callback<GetBLDispalyResponse>() {
            @Override
            public void onResponse(Call<GetBLDispalyResponse> call, Response<GetBLDispalyResponse> response) {
                if (response.body().getStatus_Id() == 0) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                }

            }

            @Override
            public void onFailure(Call<GetBLDispalyResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });

    }

    @Override
    public void getPLequifax(equifax_personalloan_request equifax_personalloan_requestlist,final IResponseSubcriber iResponseSubcriber) {
        personalloanNetworkService.getequifaxQuotes(equifax_personalloan_requestlist).enqueue(new Callback<equifax_personalloan_response>() {
            @Override
            public void onResponse(Call<equifax_personalloan_response> call, Response<equifax_personalloan_response> response) {
                if (response.body().getStatus_Id() == 0) {
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                }

            }

            @Override
            public void onFailure(Call<equifax_personalloan_response> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException(mContext.getResources().getString(R.string.net_connection)));
                } else if (t instanceof JsonParseException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Invalid Json"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Please Try after sometime.."));
                }
            }
        });
    }


}
