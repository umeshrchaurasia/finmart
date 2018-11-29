package com.datacomp.magicfinmart.mps;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MpsDataEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MpsResponse;

public class MPSFragment extends BaseFragment implements IResponseSubcriber {
    Button btnPayNow, btnApplyPromo;
    CheckBox chkAgree;
    TextView txtTermsCondition, txtMessage, txtSubscriptionAmount, txtGSTAmount, txtTotalAmount, txtPromoCode;
    LinearLayout llPromo;
    EditText etPromoCode;

    public MPSFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mps, container, false);
        init(view);
        setListener();
        MpsDataEntity mpsDataEntity = new PrefManager(getActivity()).getMps();
        bindData(mpsDataEntity);
        btnPayNow.setEnabled(false);
        txtPromoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llPromo.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void bindData(MpsDataEntity mpsDataEntity) {

        btnPayNow.setText("PAY ₹ " + mpsDataEntity.getTotalAmt());
        txtSubscriptionAmount.setText("₹ " + mpsDataEntity.getMRP());
        txtGSTAmount.setText("₹ " + mpsDataEntity.getServTaxAmt());
        txtTotalAmount.setText("₹ " + mpsDataEntity.getTotalAmt());

    }

    private void setListener() {
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnPayNow.setEnabled(true);
                    btnPayNow.setTextColor(Color.WHITE);
                    btnPayNow.setBackgroundColor(getResources().getColor(Utility.getPrimaryColor(getActivity())));
                } else {
                    btnPayNow.setEnabled(false);
                    btnPayNow.setTextColor(Color.BLACK);
                    btnPayNow.setBackgroundColor(getResources().getColor(R.color.bg));
                }
            }
        });

        btnPayNow.setOnClickListener(payNow);
        btnApplyPromo.setOnClickListener(applyNow);
        txtTermsCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTermsCondition();
            }
        });
    }

    View.OnClickListener applyNow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (etPromoCode.getText().toString().equalsIgnoreCase("")) {
                etPromoCode.setError("Enter Promo Code");
                etPromoCode.setFocusable(true);
                return;
            } else {
                showDialog("Verifying Promo Code");

                new MasterController(getActivity()).
                        applyMPSPromoCode(etPromoCode.getText().toString(), MPSFragment.this);
            }
        }
    };

    private void showTermsCondition() {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setCancelable(true);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.layout_mps_terms_condition, null);
        WebView wv = (WebView) dialogView.findViewById(R.id.wvTerms);

        wv.loadUrl("file:///android_asset/MpsTerm.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });
        alert.setView(dialogView);
        alert.show().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1000);


    }

    View.OnClickListener payNow = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                    .putExtra("URL", new PrefManager(getActivity()).getMps().getPaymentURL())
                    .putExtra("NAME", "MAGIC PLATINUM SUBS.")
                    .putExtra("TITLE", "MAGIC PLATINUM SUBS."));
        }
    };

    private void init(View view) {
        llPromo = (LinearLayout) view.findViewById(R.id.llPromo);
        txtPromoCode = (TextView) view.findViewById(R.id.txtPromoCode);
        txtTermsCondition = (TextView) view.findViewById(R.id.txtTermsCondition);
        txtSubscriptionAmount = (TextView) view.findViewById(R.id.txtSubscriptionAmount);
        txtGSTAmount = (TextView) view.findViewById(R.id.txtGSTAmount);
        txtTotalAmount = (TextView) view.findViewById(R.id.txtTotalAmount);
        etPromoCode = (EditText) view.findViewById(R.id.etPromoCode);
        chkAgree = (CheckBox) view.findViewById(R.id.chkAgree);
        btnPayNow = (Button) view.findViewById(R.id.btnPayNow);
        btnApplyPromo = (Button) view.findViewById(R.id.btnApplyPromo);
        txtMessage = (TextView) view.findViewById(R.id.txtMessage);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        Constants.hideKeyBoard(btnApplyPromo, getActivity());
        if (response instanceof MpsResponse) {
            if (response.getStatusNo() == 0) {
                new PrefManager(getActivity()).removeMps();
                new PrefManager(getActivity()).setMPS(((MpsResponse) response).getMasterData());
                bindData(((MpsResponse) response).getMasterData());
                txtMessage.setVisibility(View.VISIBLE);
                llPromo.setVisibility(View.GONE);
                txtPromoCode.setVisibility(View.GONE);
            } else {
                llPromo.setVisibility(View.VISIBLE);
                txtPromoCode.setVisibility(View.VISIBLE);
                txtMessage.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        txtMessage.setVisibility(View.GONE);
        llPromo.setVisibility(View.VISIBLE);
        txtPromoCode.setVisibility(View.VISIBLE);
        Constants.hideKeyBoard(btnApplyPromo, getActivity());
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
