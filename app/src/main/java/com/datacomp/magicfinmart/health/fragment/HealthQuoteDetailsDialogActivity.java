package com.datacomp.magicfinmart.health.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.ShareQuoteActivity;
import com.google.gson.Gson;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;

public class HealthQuoteDetailsDialogActivity extends BaseActivity implements View.OnClickListener, BaseActivity.PopUpListener {

    HealthQuoteEntity healthQuoteEntity;
    ImageView imgInsurer;
    TextView txtPlanName, txtSumAssured, txtDeductible, txtFinalPremium, txtProductName, txtBuy;
    RecyclerView rvBenefits;
    HealthSingleBenefitsAdapter mAdapter;
    ImageView imgShare;
    Button btnBack;
    String name, responseJson;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_detail_activity);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        init();
        healthQuoteEntity = new HealthQuoteEntity();
        registerPopUp(this);
        if (getIntent().getParcelableExtra("DETAIL") != null) {
            healthQuoteEntity = getIntent().getParcelableExtra("DETAIL");
            bindData();
        }
        if (getIntent().hasExtra("NAME")) {
            name = getIntent().getStringExtra("NAME");
        }
        new AsyncShareJson().execute();
    }

    private void init() {
        txtProductName = (TextView) findViewById(R.id.txtProductName);
        txtSumAssured = (TextView) findViewById(R.id.txtSumAssured);
        txtDeductible = (TextView) findViewById(R.id.txtDeductible);
        txtPlanName = (TextView) findViewById(R.id.txtPlanName);
        txtFinalPremium = (TextView) findViewById(R.id.txtFinalPremium);
        imgInsurer = (ImageView) findViewById(R.id.imgInsurer);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        imgShare = (ImageView) findViewById(R.id.imgShare);
        imgShare.setOnClickListener(this);

        txtBuy = (TextView) findViewById(R.id.txtBuy);
        txtBuy.setOnClickListener(this);

        rvBenefits = (RecyclerView) findViewById(R.id.rvBenefits);
        rvBenefits.setLayoutManager(new LinearLayoutManager(this));
        rvBenefits.setHasFixedSize(true);
    }

    private void bindData() {
        txtSumAssured.setText("" + Math.round(healthQuoteEntity.getSumInsured()));
        txtDeductible.setText("" + healthQuoteEntity.getDeductible_Amount());
        txtPlanName.setText("" + healthQuoteEntity.getPlanName());

        int finalPremium = 0;
        if (healthQuoteEntity.getServicetaxincl().toLowerCase().equals("e")) {
            finalPremium = (int) Math.round(healthQuoteEntity.getNetPremium());
        } else if (healthQuoteEntity.getServicetaxincl().toLowerCase().equals("i")) {
            finalPremium = (int) Math.round(healthQuoteEntity.getGrossPremium());
        }

        txtFinalPremium.setText("\u20B9 " + finalPremium + "/Year");

        txtProductName.setText(healthQuoteEntity.getProductName());

        Glide.with(this).load(healthQuoteEntity.getInsurerLogoName())
                .into(imgInsurer);

        mAdapter = new HealthSingleBenefitsAdapter(this, healthQuoteEntity.getLstbenfitsFive());
        rvBenefits.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgShare) {
            if (Utility.checkShareStatus(this) == 1) {
                if (responseJson != null) {
                    Intent intent = new Intent(this, ShareQuoteActivity.class);
                    intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "HEALTH_SINGLE_QUOTE");
                    intent.putExtra("RESPONSE", responseJson);
                    intent.putExtra("NAME", name);
                    startActivity(intent);
                }
            } else {
                openPopUp(imgShare, "Message", "Your POSP status is INACTIVE", "OK", true);
            }
        } else if (view.getId() == R.id.txtBuy) {
            if (Utility.checkShareStatus(this) == 1) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("BUY", healthQuoteEntity);
                setResult(HealthQuoteFragment.RESULT_COMPARE, resultIntent);
                finish();
            } else {
                openPopUp(imgShare, "Message", "Your POSP status is INACTIVE", "OK", true);
            }
        } else if (view.getId() == R.id.btnBack) {
            finish();
        }
    }

    class AsyncShareJson extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            responseJson = gson.toJson(healthQuoteEntity);
            return responseJson;
        }

        @Override
        protected void onPostExecute(String s) {
            responseJson = s;
        }
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        if (view.getId() == R.id.imgShare) {
            dialog.cancel();
        }
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        if (view.getId() == R.id.imgShare) {
            dialog.cancel();
        }
    }
}
