package com.datacomp.magicfinmart.introslider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.login.LoginActivity;
import com.datacomp.magicfinmart.webviews.MyWebViewClient;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.creditcard.CreditCardController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.BikeMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CarMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CityMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.InsuranceMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RblCityMasterResponse;

public class EulaActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {
    Button btnAgree, btnDisAgree;
    PrefManager prefManager;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eula);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWidgets();
        setListener();
        prefManager = new PrefManager(this);
        if (prefManager.IsBikeMasterUpdate())
            new MasterController(this).getBikeMaster(this);
        if (prefManager.IsCarMasterUpdate())
            new MasterController(this).getCarMaster(this);
        if (prefManager.IsRtoMasterUpdate())
            new MasterController(this).getRTOMaster(this);
        if (prefManager.IsInsuranceMasterUpdate())
            new MasterController(this).getInsuranceMaster(this);
        if (prefManager.getIsRblCityMaster())
            new CreditCardController(this).getRblCityMaster(null);
        settingWebview();
    }

    private void initWidgets() {
        webView = (WebView) findViewById(R.id.webView);
        btnAgree = (Button) findViewById(R.id.btnAgree);
        btnDisAgree = (Button) findViewById(R.id.btnDisAgree);
    }

    private void setListener() {
        btnAgree.setOnClickListener(this);
        btnDisAgree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnAgree) {
            if (checkAllMastersIsUpdate()) {
                prefManager.setFirstTimeLaunch(false);
                startActivity(new Intent(this, LoginActivity.class));
            } else {

                if (prefManager.IsBikeMasterUpdate())
                    new MasterController(this).getBikeMaster(this);
                if (prefManager.IsCarMasterUpdate())
                    new MasterController(this).getCarMaster(this);
                if (prefManager.IsRtoMasterUpdate())
                    new MasterController(this).getRTOMaster(this);
                if (prefManager.IsInsuranceMasterUpdate())
                    new MasterController(this).getInsuranceMaster(this);
                if (prefManager.getIsRblCityMaster())
                    new CreditCardController(this).getRblCityMaster(this);

                prefManager.setFirstTimeLaunch(false);
                startActivity(new Intent(this, LoginActivity.class));
            }

        } else if (i == R.id.btnDisAgree) {
            finish();

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof BikeMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                // startActivity(new Intent(EulaActivity.this, LoginActivity.class));
            }
        } else if (response instanceof CarMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(EulaActivity.this, LoginActivity.class));
            }
        } else if (response instanceof CityMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(EulaActivity.this, LoginActivity.class));
            }
        } else if (response instanceof InsuranceMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(EulaActivity.this, LoginActivity.class));
            }
        } else if (response instanceof RblCityMasterResponse) {
            if (checkAllMastersIsUpdate()) {
                //startActivity(new Intent(EulaActivity.this, LoginActivity.class));
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
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

    private void settingWebview() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);


        MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);
       /* webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO show you progress image
                showDialog();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO hide your progress image
                cancelDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });*/
        webView.getSettings().setBuiltInZoomControls(true);
       /* Log.d("URL", url);
        //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        webView.loadUrl(url);*/
        webView.loadUrl("file:///android_asset/eula.html");
    }
}
