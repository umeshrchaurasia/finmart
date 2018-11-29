package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.InsuranceSubtypeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MenuMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestbuilder.MasterRequestBuilder;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.BikeMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CarMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CityMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ConstantsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ContactUsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.InsuranceMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MpsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.UserConstatntResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.WhatsNewResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class MasterController implements IMasterFetch {

    MasterRequestBuilder.MasterNetworkService masterNetworkService;
    Context mContext;
    DBPersistanceController dbPersistanceController;

    public MasterController(Context context) {
        mContext = context;
        masterNetworkService = new MasterRequestBuilder().getService();
        dbPersistanceController = new DBPersistanceController(mContext);
    }

    @Override
    public void getCarMaster(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ProductId", "1");

        masterNetworkService.getCarMaster(hashMap).enqueue(new Callback<CarMasterResponse>() {
            @Override
            public void onResponse(Call<CarMasterResponse> call, Response<CarMasterResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        if (response.body().getMasterData() != null || response.body().getMasterData().size() != 0)
                            new AsyncCarMaster(mContext, response.body().getMasterData()).execute();
                        if (iResponseSubcriber != null)
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());

                    } else {
                        if (iResponseSubcriber != null)
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    if (iResponseSubcriber != null)
                        iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<CarMasterResponse> call, Throwable t) {
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
    public void getBikeMaster(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("ProductId", "10");

        masterNetworkService.getBikeMaster(hashMap).enqueue(new Callback<BikeMasterResponse>() {
            @Override
            public void onResponse(Call<BikeMasterResponse> call, Response<BikeMasterResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        if (response.body().getMasterData() != null && response.body().getMasterData().size() != 0)
                            new AsyncBikeMaster(mContext, response.body().getMasterData()).execute();

                        if (iResponseSubcriber != null)
                            iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        if (iResponseSubcriber != null)
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    if (iResponseSubcriber != null)
                        iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<BikeMasterResponse> call, Throwable t) {
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
    public void getRTOMaster(final IResponseSubcriber iResponseSubcriber) {

        masterNetworkService.getAllCity().enqueue(new Callback<CityMasterResponse>() {
            @Override
            public void onResponse(Call<CityMasterResponse> call, Response<CityMasterResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        if (response.body().getMasterData() != null && response.body().getMasterData().size() != 0)
                            new AsyncCityMaster(mContext, response.body().getMasterData()).execute();

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<CityMasterResponse> call, Throwable t) {
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
    public void getInsuranceMaster(final IResponseSubcriber iResponseSubcriber) {
        masterNetworkService.getInsuranceMasters().enqueue(new Callback<InsuranceMasterResponse>() {
            @Override
            public void onResponse(Call<InsuranceMasterResponse> call, Response<InsuranceMasterResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        if (response.body().getMasterData() != null
                                && response.body().getMasterData().getGeneralinsurance().size() != 0
                                && response.body().getMasterData().getHealthinsurance().size() != 0
                                && response.body().getMasterData().getLifeinsurance().size() != 0)

                            new AsyncInsuranceMaster(mContext, response.body().getMasterData()).execute();

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<InsuranceMasterResponse> call, Throwable t) {
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
    public void getContactList(final IResponseSubcriber iResponseSubcriber) {
        masterNetworkService.getContactUs().enqueue(new Callback<ContactUsResponse>() {
            @Override
            public void onResponse(Call<ContactUsResponse> call, Response<ContactUsResponse> response) {

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
            public void onFailure(Call<ContactUsResponse> call, Throwable t) {
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
    public void getWhatsNew(String app_version, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("app_version", app_version);

        masterNetworkService.getWhatsNew(body).enqueue(new Callback<WhatsNewResponse>() {
            @Override
            public void onResponse(Call<WhatsNewResponse> call, Response<WhatsNewResponse> response) {

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
            public void onFailure(Call<WhatsNewResponse> call, Throwable t) {
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
    public void getConstants(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("FBAID", "" + dbPersistanceController.getUserData().getFBAId());
        body.put("VersionCode", Utility.getVersionName(mContext));
        masterNetworkService.getConstantsData(body).enqueue(new Callback<ConstantsResponse>() {
            @Override
            public void onResponse(Call<ConstantsResponse> call, Response<ConstantsResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {

                        new AsyncConstants(mContext, response.body().getMasterData()).execute();

                        // new PrefManager(mContext).updateMotorVersion("1");


                        //existing master version
                        int motorVersion = Integer.parseInt(new PrefManager(mContext).getMotorVersion());

                        //new version available hit to master
                        if (Integer.parseInt(response.body().getMasterData().getUpdateMaster())
                                > motorVersion) {

                            //clear all master tags
                            new PrefManager(mContext).clearMotorMaster();

                            new PrefManager(mContext).updateMotorVersion
                                    (response.body().getMasterData().getUpdateMaster());

                            getCarMaster(null);
                            getBikeMaster(null);
                        }

                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<ConstantsResponse> call, Throwable t) {
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
    public void applyMPSPromoCode(String promoCode, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("FBAID", "" + dbPersistanceController.getUserData().getFBAId());
        body.put("PromoCode", "" + promoCode);

        masterNetworkService.applyPromoCode(body).enqueue(new Callback<MpsResponse>() {
            @Override
            public void onResponse(Call<MpsResponse> call, Response<MpsResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        //new AsyncConstants(mContext, response.body().getMasterData()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<MpsResponse> call, Throwable t) {
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
    public void getMpsData(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("FBAID", "" + dbPersistanceController.getUserData().getFBAId());
        masterNetworkService.getMpsData(body).enqueue(new Callback<MpsResponse>() {
            @Override
            public void onResponse(Call<MpsResponse> call, Response<MpsResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        //new AsyncConstants(mContext, response.body().getMasterData()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<MpsResponse> call, Throwable t) {
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
    public void geUserConstant(final int type, final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("fbaid", "" + dbPersistanceController.getUserData().getFBAId());
        masterNetworkService.getUserConstatnt(body).enqueue(new Callback<UserConstatntResponse>() {
            @Override
            public void onResponse(Call<UserConstatntResponse> call, Response<UserConstatntResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        new AsyncUserConstatnt(mContext, response.body().getMasterData()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<UserConstatntResponse> call, Throwable t) {
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
    public void getMenuMaster(final IResponseSubcriber iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("fbaid", "" + dbPersistanceController.getUserData().getFBAId());
        masterNetworkService.getMenuMaster(body).enqueue(new Callback<MenuMasterResponse>() {
            @Override
            public void onResponse(Call<MenuMasterResponse> call, Response<MenuMasterResponse> response) {
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
            public void onFailure(Call<MenuMasterResponse> call, Throwable t) {
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
    public void getInsuranceSubType(final IResponseSubcriber iResponseSubcriber) {

        masterNetworkService.getInsuranceSubtype().enqueue(new Callback<InsuranceSubtypeResponse>() {
            @Override
            public void onResponse(Call<InsuranceSubtypeResponse> call, Response<InsuranceSubtypeResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatusNo() == 0) {
                        new AsyncSubType(mContext, response.body().getMasterData()).execute();
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                    }
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException("Failed to fetch information."));
                }
            }

            @Override
            public void onFailure(Call<InsuranceSubtypeResponse> call, Throwable t) {
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
