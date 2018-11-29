package com.datacomp.magicfinmart.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.introslider.WelcomeActivity;
import com.datacomp.magicfinmart.login.LoginActivity;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.creditcard.CreditCardController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.BikeMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CarMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CityMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.InsuranceMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthPackDetailsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthPackResponse;

public class SplashScreenActivity extends BaseActivity implements IResponseSubcriber,
        magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber {

    private static final String TAG = "Splashscreen";
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    PrefManager prefManager;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginResponseEntity;
    double iTheme = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        prefManager = new PrefManager(this);
        dbPersistanceController = new DBPersistanceController(this);
        loginResponseEntity = dbPersistanceController.getUserData();
//        Utility.LOGIN_IP = Utility.getLocalIpAddress(this);

        // By default set to 1.
        //prefManager.updateMotorVersion("1");

        // for user constant
        if (loginResponseEntity != null) {
            new MasterController(this).geUserConstant(0, this);
            new MasterController(this).getConstants(this);
        }
       /* if (userConstantEntity != null) {
            new MasterController(this).geUserConstant(0, this);
        }*/
        prefManager.updateCheckMsgFirst("" + 0);
        prefManager.setIsUpdateShown(true);
        if (prefManager.IsBikeMasterUpdate())
            new MasterController(this).getBikeMaster(this);
        if (prefManager.IsCarMasterUpdate())
            new MasterController(this).getCarMaster(this);
        if (prefManager.IsRtoMasterUpdate())
            new MasterController(this).getRTOMaster(this);
        if (prefManager.IsInsuranceMasterUpdate())
            new MasterController(this).getInsuranceMaster(this);

        if (prefManager.isFirstTimeLaunch()) {

            startActivity(new Intent(this, WelcomeActivity.class));
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (checkAllMastersIsUpdate()) {
                        if (loginResponseEntity != null) {
                            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        }
                    } else {

                        if (prefManager.IsBikeMasterUpdate())
                            new MasterController(SplashScreenActivity.this).getBikeMaster(SplashScreenActivity.this);
                        if (prefManager.IsCarMasterUpdate())
                            new MasterController(SplashScreenActivity.this).getCarMaster(SplashScreenActivity.this);
                        if (prefManager.IsRtoMasterUpdate())
                            new MasterController(SplashScreenActivity.this).getRTOMaster(SplashScreenActivity.this);
                        if (prefManager.IsInsuranceMasterUpdate())
                            new MasterController(SplashScreenActivity.this).getInsuranceMaster(SplashScreenActivity.this);
                        if (prefManager.getIsRblCityMaster())
                            new CreditCardController(SplashScreenActivity.this).getRblCityMaster(SplashScreenActivity.this);

                        if (loginResponseEntity != null) {
                            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        } else {
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        }
                    }
                   /* if (loginResponseEntity != null) {
                        //Toast.makeText(SplashScreenActivity.this, "User exist!", Toast.LENGTH_SHORT).show();
                        //TODO Redirect to homeactivity
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    } else {
                        if (checkAllMastersIsUpdate()) {
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        } else {
                            if (prefManager.IsBikeMasterUpdate())
                                new MasterController(SplashScreenActivity.this).getBikeMaster(SplashScreenActivity.this);
                            if (prefManager.IsCarMasterUpdate())
                                new MasterController(SplashScreenActivity.this).getCarMaster(SplashScreenActivity.this);
                            if (prefManager.IsRtoMasterUpdate())
                                new MasterController(SplashScreenActivity.this).getRTOMaster(SplashScreenActivity.this);
                            if (prefManager.IsInsuranceMasterUpdate())
                                new MasterController(SplashScreenActivity.this).getInsuranceMaster(SplashScreenActivity.this);
                            if (prefManager.getIsRblCityMaster())
                                new CreditCardController(SplashScreenActivity.this).getRblCityMaster(SplashScreenActivity.this);
                            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                        }
                    }*/
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof BikeMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
        } else if (response instanceof CarMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
        } else if (response instanceof CityMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
        } else if (response instanceof InsuranceMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
        }
    }

    @Override
    public void OnSuccess(magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse response, String message) {
        if (response instanceof HealthPackResponse) {
            Log.d("Test", "success");
        }
        if (response instanceof HealthPackDetailsResponse) {
            Log.d("Test", "success");
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public boolean checkAllMastersIsUpdate() {
        if (prefManager.IsBikeMasterUpdate())
            return false;
        else if (prefManager.IsCarMasterUpdate())
            return false;
        else if (prefManager.IsRtoMasterUpdate())
            return false;
        else if (prefManager.IsInsuranceMasterUpdate())
            return false;

        return true;
    }
}
