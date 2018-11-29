package com.datacomp.magicfinmart.loan_fm.balancetransfer.loan_apply;

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
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BLEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest;

/**
 * Created by IN-RB on 30-01-2018.
 */

public class BTLoanApplyWebView extends AppCompatActivity {

    WebView webView;
    int quoteId;
    BLEntity entity ;
    String url;
    LoginResponseEntity loginEntity;
    BLLoanRequest blLoanRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_apply_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loginEntity   = new DBPersistanceController(BTLoanApplyWebView.this).getUserData();
        webView = (WebView) findViewById(R.id.webView);

        //if (getIntent().getStringExtra("PL_URL") != null) {
            entity = getIntent().getParcelableExtra("BL");
            blLoanRequest = getIntent().getParcelableExtra("BL_Req");
            quoteId = getIntent().getIntExtra("BL_QUOTE_ID",0);

        //}

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

     //  http://erp.rupeeboss.com/BalanceTransfer/PL_BT_Form.aspx?qoutid=0&coapp=0&fname=samuel&lname=dias&dob=01-01-1980&
        // FatherName=&pan=asdfg1234a&Gender=M&Address1=&Address2=&Address3=&Pincode=&
       // bankid=33&productid=9&IDType=Floating&ProcessingFee=10000&LoanAmout=500000&brokerid=25290

        //erp.rupeeboss.com/BalanceTransfer/PL_BT_Form.aspx?qoutid=0&brokerid=0&loanamout=1580000&loaninterest=0.01&loanterm=120
        // &bankid=33&productid=9&idtype=Fixed&processingfee=31600&empcode=&refapp=0&source=&coapp=0&pan=&CampaignName=Rupeeboss%20Online
       if(String.valueOf(blLoanRequest.getProduct_id()).equals("9"))
       {
           url="http://erp.rupeeboss.com/BalanceTransfer/PL_BT_Form.aspx";

       }else  if(String.valueOf(blLoanRequest.getProduct_id()).equals("7"))
       {
           url="http://erp.rupeeboss.com/BalanceTransfer/LAP_BT_Form.aspx";
       }else  if(String.valueOf(blLoanRequest.getProduct_id()).equals("12"))
       {
           url="http://erp.rupeeboss.com/BalanceTransfer/HL_BT_Form.aspx";
       }

        http://erp.rupeeboss.com/BalanceTransfer/PL_BT_Form.aspx?qoutid=0&brokerid=0&
        // loanamout=5000000&loaninterest=11.49&loanterm=144&bankid=33&productid=9&idtype=Fixed&
        // processingfee=100000&empcode=&refapp=0&source=&coapp=0&pan=&CampaignName=Rupeeboss%20Online

        url = url + "?qoutid=" + quoteId
                + "&fname=" + blLoanRequest.getApplicantName()
                + "&brokerid=" + loginEntity.getLoanId()
                + "&productid=" + blLoanRequest.getProduct_id()
                +"&bankid=" + entity.getBank_Id()
                + "&refapp=0"
                + "&coapp=0"
                + "&empcode=" + ""
                + "&loanamout=" + blLoanRequest.getLoanamount()
                + "&idtype=" + entity.getRoi_type()
                + "&processingfee=" + entity.getProcessingfee()
                + "&loaninterest=" + entity.getRoi()
                + "&loanterm=" + (blLoanRequest.getLoanterm()* 12)
                + "&fba_id="+ loginEntity.getFBAId()
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
