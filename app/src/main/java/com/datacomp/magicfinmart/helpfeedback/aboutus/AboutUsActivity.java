package com.datacomp.magicfinmart.helpfeedback.aboutus;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;

public class AboutUsActivity extends BaseActivity implements IResponseSubcriber {

    TextView tvAppVersion, tvNAme, tvFbaCode, tvPospNo, tvLoginId, tvPospStatus, tvManagerMobile,
            tvManagerEmail, tvSupportNo, tvSupportEmail;
    LoginResponseEntity loginResponseEntity;
    DBPersistanceController dbPersistanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbPersistanceController = new DBPersistanceController(this);
        loginResponseEntity = dbPersistanceController.getUserData();
        init_widgets();

        bindData();
    }

    private void bindData() {
        tvAppVersion.setText("v" + Utility.getVersionName(this) + " Release Date " + Utility.RELEASE_DATE);
        tvNAme.setText(loginResponseEntity.getFullName());
        tvFbaCode.setText("" + loginResponseEntity.getFBAId());
        if (loginResponseEntity.getPOSPNo() != null) {
            tvPospNo.setText("" + loginResponseEntity.getPOSPNo());
        }
        tvLoginId.setText("" + loginResponseEntity.getUserName());
        if (Utility.checkShareStatus(this) == 1) {
            tvPospStatus.setText("ACTIVE");
            tvPospStatus.setTextColor(getResources().getColor(R.color.green_descent));
        } else {
            tvPospStatus.setText("INACTIVE");
            tvPospStatus.setTextColor(getResources().getColor(R.color.holo_red_dark));
        }
        /*if (loginResponseEntity.getPOSPStatus() != null && !loginResponseEntity.getPOSPStatus().equals("") && loginResponseEntity.getPOSPStatus().equals("6")) {

            tvPospStatus.setText("ACTIVE");
            tvPospStatus.setTextColor(getResources().getColor(R.color.green_descent));
        } else {
            tvPospStatus.setText("INACTIVE");
            tvPospStatus.setTextColor(getResources().getColor(R.color.holo_red_dark));
        }*/
    }

    private void init_widgets() {
        tvAppVersion = (TextView) findViewById(R.id.tvAppVersion);
        tvNAme = (TextView) findViewById(R.id.tvNAme);
        tvFbaCode = (TextView) findViewById(R.id.tvFbaCode);
        tvPospNo = (TextView) findViewById(R.id.tvPospNo);
        tvLoginId = (TextView) findViewById(R.id.tvLoginId);
        tvPospStatus = (TextView) findViewById(R.id.tvPospStatus);
        tvManagerMobile = (TextView) findViewById(R.id.tvManagerMobile);
        tvManagerEmail = (TextView) findViewById(R.id.tvManagerEmail);
        tvSupportNo = (TextView) findViewById(R.id.tvSupportNo);
        tvSupportEmail = (TextView) findViewById(R.id.tvSupportEmail);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

    }

    @Override
    public void OnFailure(Throwable t) {

    }
}
