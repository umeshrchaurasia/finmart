package com.datacomp.magicfinmart.loan_fm.personalloan.loan_apply;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.webviews.MyWebViewClient;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalQuoteEntity;

public class PersonalLoanApplyWebView extends AppCompatActivity {


    WebView webView;
    int quoteId;
    PersonalQuoteEntity entity ;
    String url;
    LoginResponseEntity loginEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_apply_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loginEntity   = new DBPersistanceController(PersonalLoanApplyWebView.this).getUserData();
        webView = (WebView) findViewById(R.id.webView);

        if (getIntent().getStringExtra("PL_URL") != null) {
            entity = getIntent().getParcelableExtra("PL");
            quoteId = getIntent().getIntExtra("PL_QUOTE_ID", 0);
            url = getIntent().getStringExtra("PL_URL");
        }
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
        webView.getSettings().setBuiltInZoomControls(true);

        //  url = url + "?qoutid=" + quoteId + "&bankid=" + bankId + "&productid=9" + "&brokerid=" + loginEntity.getBrokerId() + "&empcode=" + loginEntity.getEmpCode();

        url = url + "?qoutid=" + quoteId + "&bankid=" + entity.getBank_Id()
                + "&productid=9"
                + "&refapp=0"
                + "&brokerid=" + loginEntity.getLoanId()
                + "&empcode=" + ""
                + "&loanamout=" + entity.getLoan_eligible()
                + "&idtype=" + entity.getRoi_type()
                + "&processingfee=" + entity.getProcessingfee()
                +"&fbaid"+loginEntity.getFBAId()//fu
                + "&Lead_Source="+"DC";

        Log.d("PERSONAL_LOAN_URL", url);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
