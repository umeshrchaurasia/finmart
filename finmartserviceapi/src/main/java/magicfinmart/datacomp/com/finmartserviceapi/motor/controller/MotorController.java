package magicfinmart.datacomp.com.finmartserviceapi.motor.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestbuilder.MotorQuotesRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.BikePremiumRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.SaveAddOnRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikePremiumResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikeUniqueResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.SaveAddOnResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 11/01/2018.
 */

public class MotorController implements IMotor {

    public static String MOTOR_QUOTE_RESPONSE = "MOTOR QUOTE RESPONSE";
    public static String MOTOR_ADDON_RESPONSE = "MOTOR ADDON RESPONSE";
    public static int SLEEP_DELAY = 3000; // 5 seconds delay.
    public static int NO_OF_SERVER_HITS = 8;
    public static int MIN_NO_OF_SERVER_HITS = 5;
    MotorQuotesRequestBuilder.MotorQuotesNetworkService motorQuotesNetworkService;
    Context mContext;
    Handler handler;
    IResponseSubcriber iResponseSubcriber;
    DBPersistanceController dbPersistanceController;
    ConstantEntity constantEntity;

    public MotorController(Context context) {
        motorQuotesNetworkService = new MotorQuotesRequestBuilder().getService();
        mContext = context;
        dbPersistanceController = new DBPersistanceController(mContext);
        constantEntity = dbPersistanceController.getConstantsData();
        try {
            SLEEP_DELAY = Integer.parseInt(constantEntity.getPBHitTime());
            NO_OF_SERVER_HITS = Integer.parseInt(constantEntity.getPBNoOfHits());
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler = new Handler();
    }

    private class MotorRunnable implements Runnable {
        private int pID = 0;

        public MotorRunnable(int pID) {
            this.pID = pID;
        }

        @Override
        public void run() {
            new MotorController(mContext).getMotorQuote(pID, iResponseSubcriber);
        }
    }


    @Override
    public void getMotorPremiumInitiate(final MotorRequestEntity motorRequestEntity, final IResponseSubcriber iResponseSubcriber) {

        motorQuotesNetworkService.premiumInitiateUniqueID(motorRequestEntity).enqueue(new Callback<BikeUniqueResponse>() {
            @Override
            public void onResponse(Call<BikeUniqueResponse> call, Response<BikeUniqueResponse> response) {
                if (response.body() != null) {

                    if (response.body().getSummary() == null) {

                        //RuntimeException exception = new RuntimeException(response.body().getDetails()[0]);
                        if (response.body().getDetails() != null) {
                            if (response.body().getDetails().length > 0)
                                iResponseSubcriber.OnFailure(new RuntimeException(response.body().getDetails()[0]));
                            else
                                iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                        }else{
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                        }
                        //iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                        return;
                    }
                    //for every new premium initiate counter should be 0
                    Utility.getSharedPreferenceEditor(mContext).remove(Utility.QUOTE_COUNTER).commit();

                    String UNIQUE = response.body().getSummary().getRequest_Unique_Id();

                    SharedPreferences.Editor edit = Utility.getSharedPreferenceEditor(mContext);

                    //car quote
                    if (motorRequestEntity.getProduct_id() == 1) {
                        edit.putString(Utility.CARQUOTE_UNIQUEID,
                                UNIQUE);
                    } else if (motorRequestEntity.getProduct_id() == 10) {
                        //bike quote
                        edit.putString(Utility.BIKEQUOTE_UNIQUEID,
                                UNIQUE);
                    }
                    edit.commit();

                    //callback of data

                    iResponseSubcriber.OnSuccess(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<BikeUniqueResponse> call, Throwable t) {
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
    public void getMotorQuote(final int productID, final IResponseSubcriber iResponseSubcriber) {
        this.iResponseSubcriber = iResponseSubcriber;
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Utility.SECRET_KEY);
        entity.setClient_key(Utility.CLIENT_KEY);
        if (constantEntity != null && constantEntity.getHorizonVersion() != null && !constantEntity.getHorizonVersion().equals(""))
            entity.setResponse_version("" + constantEntity.getHorizonVersion());
        else
            entity.setResponse_version("2.0");
        //entity.setExecution_async("no");
        if (productID == 10) {
            entity.setSearch_reference_number(Utility.getSharedPreference(mContext).getString(Utility.BIKEQUOTE_UNIQUEID, ""));
        } else if (productID == 1) {
            entity.setSearch_reference_number(Utility.getSharedPreference(mContext).getString(Utility.CARQUOTE_UNIQUEID, ""));
        }

        //TODO  remove this line
        //entity.setSearch_reference_number("SRN-QNTUQYKE-N9MM-3QDH-VE3C-DGLYMOEWERPY");

        if (Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) <= NO_OF_SERVER_HITS) {
            Utility.getSharedPreferenceEditor(mContext).putInt(Utility.QUOTE_COUNTER,
                    Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) + 1)
                    .commit();
        }

        motorQuotesNetworkService.getPremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Call<BikePremiumResponse> call, Response<BikePremiumResponse> response) {
                if (response.body() != null && response.body().getResponse() != null) {


                    if (constantEntity.getLogtracking().equals("0"))
                        new PolicybossTrackingQuoteeResponse((BikePremiumResponse) response.body()).execute();

                    BikePremiumResponse bikePremiumResponse = new BikePremiumResponse();
                    if (response.body() != null) {
                        List<ResponseEntity> list = new ArrayList<ResponseEntity>();
                        for (int i = 0; i < response.body().getResponse().size(); i++) {
                            ResponseEntity responseEntity = response.body().getResponse().get(i);
                            if (responseEntity.getError_Code().equals("")) {
                                list.add(responseEntity);
                            }
                        }
                        bikePremiumResponse.setResponse(list);
                        bikePremiumResponse.setSummary(response.body().getSummary());
                    }

                    iResponseSubcriber.OnSuccess(bikePremiumResponse, response.body().getMessage());

                    MotorRunnable runnable = new MotorRunnable(productID);

                   /* if (response.body().getSummary().getStatusX().equals("complete") &&
                            Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) > MIN_NO_OF_SERVER_HITS) {

                        Utility.getSharedPreferenceEditor(mContext).remove(Utility.QUOTE_COUNTER).commit();
                        handler.removeCallbacks(runnable);
                    } else {
                        if (Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) < NO_OF_SERVER_HITS) {
                            //server request for pending quotes
                            Log.d("COUNTER", "" + Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0));
                            handler.postDelayed(runnable, SLEEP_DELAY);
                        } else {
                            //remove handler
                            handler.removeCallbacks(runnable);
                            //remove stored counters
                            Utility.getSharedPreferenceEditor(mContext).remove(Utility.QUOTE_COUNTER).commit();
                        }


                    }*/

                    if (Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0) <= NO_OF_SERVER_HITS) {
                        //server request for pending quotes
                        Log.d("COUNTER", "" + Utility.getSharedPreference(mContext).getInt(Utility.QUOTE_COUNTER, 0));
                        handler.postDelayed(runnable, SLEEP_DELAY);
                    } else {
                        //remove handler
                        handler.removeCallbacks(runnable);
                        //remove stored counters
                        Utility.getSharedPreferenceEditor(mContext).remove(Utility.QUOTE_COUNTER).commit();
                    }

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("No Quote found"));
                }
            }

            @Override
            public void onFailure(Call<BikePremiumResponse> call, Throwable t) {
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
    public void saveAddOn(SaveAddOnRequestEntity saveAddOnRequestEntity, final IResponseSubcriber iResponseSubcriber) {
        saveAddOnRequestEntity.setSecret_key(Utility.SECRET_KEY);
        saveAddOnRequestEntity.setClient_key(Utility.CLIENT_KEY);

        motorQuotesNetworkService.saveAddOn(saveAddOnRequestEntity).enqueue(new Callback<SaveAddOnResponse>() {
            @Override
            public void onResponse(Call<SaveAddOnResponse> call, Response<SaveAddOnResponse> response) {
                if (response.body() != null) {
                    if (constantEntity.getLogtracking().equals("0"))
                        new PolicybossTrackingAddonResponse((SaveAddOnResponse) response.body()).execute();
                    iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<SaveAddOnResponse> call, Throwable t) {
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

    class PolicybossTrackingQuoteeResponse extends AsyncTask<Void, Void, String> {
        BikePremiumResponse bikePremiumResponse;
        String response = "";

        public PolicybossTrackingQuoteeResponse(BikePremiumResponse bikePremiumResponse) {
            this.bikePremiumResponse = bikePremiumResponse;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Gson gson = new Gson();
            response = gson.toJson(bikePremiumResponse);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (constantEntity.getLogtracking().equals("0"))
                new TrackingController(mContext).sendData(new TrackingRequestEntity(new TrackingData(s), MOTOR_QUOTE_RESPONSE), null);
        }
    }

    class PolicybossTrackingAddonResponse extends AsyncTask<Void, Void, String> {
        SaveAddOnResponse saveAddOnResponse;
        String response = "";

        public PolicybossTrackingAddonResponse(SaveAddOnResponse saveAddOnResponse) {
            this.saveAddOnResponse = saveAddOnResponse;
        }

        @Override
        protected String doInBackground(Void... voids) {
            Gson gson = new Gson();
            response = gson.toJson(saveAddOnResponse);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (constantEntity.getLogtracking().equals("0"))
                new TrackingController(mContext).sendData(new TrackingRequestEntity(new TrackingData(s), MOTOR_ADDON_RESPONSE), null);
        }
    }

    @Override
    public void getMotorQuoteOneTime(final int productID, final IResponseSubcriber iResponseSubcriber) {
        this.iResponseSubcriber = iResponseSubcriber;
        BikePremiumRequestEntity entity = new BikePremiumRequestEntity();
        entity.setSecret_key(Utility.SECRET_KEY);
        entity.setClient_key(Utility.CLIENT_KEY);
        if (constantEntity != null && constantEntity.getHorizonVersion() != null && !constantEntity.getHorizonVersion().equals(""))
            entity.setResponse_version("" + constantEntity.getHorizonVersion());
        else
            entity.setResponse_version("2.0");
        //entity.setExecution_async("no");
        if (productID == 10) {
            entity.setSearch_reference_number(Utility.getSharedPreference(mContext).getString(Utility.BIKEQUOTE_UNIQUEID, ""));
        } else if (productID == 1) {
            entity.setSearch_reference_number(Utility.getSharedPreference(mContext).getString(Utility.CARQUOTE_UNIQUEID, ""));
        }

        //TODO  remove this line
        //entity.setSearch_reference_number("SRN-QNTUQYKE-N9MM-3QDH-VE3C-DGLYMOEWERPY");

        motorQuotesNetworkService.getPremiumList(entity).enqueue(new Callback<BikePremiumResponse>() {
            @Override
            public void onResponse(Call<BikePremiumResponse> call, Response<BikePremiumResponse> response) {
                if (response.body() != null && response.body().getResponse() != null) {


                    if (constantEntity.getLogtracking().equals("0"))
                        new PolicybossTrackingQuoteeResponse((BikePremiumResponse) response.body()).execute();

                    BikePremiumResponse bikePremiumResponse = new BikePremiumResponse();
                    if (response.body() != null) {
                        List<ResponseEntity> list = new ArrayList<ResponseEntity>();
                        for (int i = 0; i < response.body().getResponse().size(); i++) {
                            ResponseEntity responseEntity = response.body().getResponse().get(i);
                            if (responseEntity.getError_Code().equals("")) {
                                list.add(responseEntity);
                            }
                        }
                        bikePremiumResponse.setResponse(list);
                        bikePremiumResponse.setSummary(response.body().getSummary());
                    }

                    iResponseSubcriber.OnSuccess(bikePremiumResponse, response.body().getMessage());
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("No Quote found"));
                }
            }

            @Override
            public void onFailure(Call<BikePremiumResponse> call, Throwable t) {
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
