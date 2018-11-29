package com.datacomp.magicfinmart.quicklead;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quicklead.QuickLeadController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.QuickLeadRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuickLeadResponse;

public class QuickLeadActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etName, etEmail, etMobile, etFollowupDate, etMonthlyIncome, etLoanAmount, etRemark;
    Spinner spProduct;
    Button btnSubmit;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
    ArrayAdapter<String> productTypeAdapter;
    WebView webView;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_lead);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        spinnerProductBinding();
    }

    private void init() {
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etFollowupDate = (EditText) findViewById(R.id.etFollowupDate);
        etMonthlyIncome = (EditText) findViewById(R.id.etMonthlyIncome);
        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);
        etRemark = (EditText) findViewById(R.id.etRemark);

        spProduct = (Spinner) findViewById(R.id.spProduct);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        etFollowupDate.setOnClickListener(datePicker);
        webView = findViewById(R.id.webView);
        url = "http://erp.rupeeboss.com/loansrepository/Loans-repository.html";
        settingWebview();
    }

    private void spinnerProductBinding() {
        productTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.quicklead_product)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(QuickLeadActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.quicklead_product);
                tv.setText(items[position]);
                tv.setTextColor(Color.BLACK);
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spProduct.setAdapter(productTypeAdapter);
    }

    protected View.OnClickListener datePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, QuickLeadActivity.this);
            DateTimePicker.currentDateAndForward(QuickLeadActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    if (datePicker.isShown()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String currentDay = simpleDateFormat.format(calendar.getTime());
                        etFollowupDate.setText(currentDay);
                    }
                }
            });
        }
    };

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSubmit) {

            if (!isEmpty(etName)) {
                etName.setError("Enter Name");
                etName.setFocusable(true);
                return;
            } else {
                etName.setError(null);
            }

            if (!isValideEmailID(etEmail)) {
                etEmail.setError("Invalid Email ID");
                etEmail.setFocusable(true);
                return;
            }

            if (!isEmpty(etMobile)) {
                etMobile.setError("Enter Mobile Number");
                etMobile.setFocusable(true);
                return;
            } else {
                etMobile.setError(null);
            }
            if (!isEmpty(etFollowupDate)) {
                etFollowupDate.setError("Invalid Follow up date");
                etFollowupDate.setFocusable(true);
                return;
            } else {
                etFollowupDate.setError(null);
            }

            if (!isEmpty(etMonthlyIncome)) {
                etMonthlyIncome.setError("Enter Monthly income");
                etMonthlyIncome.setFocusable(true);
                return;
            } else {
                etMonthlyIncome.setError(null);
            }

            if (!isEmpty(etLoanAmount)) {
                etLoanAmount.setError("Enter Loan Amount");
                etLoanAmount.setFocusable(true);
                return;
            } else {
                etLoanAmount.setError(null);
            }

            if (!isEmpty(etRemark)) {
                etRemark.setError("Enter Remark");
                etRemark.setFocusable(true);
                return;
            } else {
                etRemark.setError(null);
            }

            if (spProduct.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Select product", Toast.LENGTH_SHORT).show();
                return;
            }

            QuickLeadRequestEntity requestEntity = new QuickLeadRequestEntity();
            requestEntity.setBrokerId(new DBPersistanceController(this).getUserData().getLoanId());
            requestEntity.setEMail(etEmail.getText().toString());
            requestEntity.setFBA_Id(String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()));
            requestEntity.setFollowupDate(etFollowupDate.getText().toString());
            requestEntity.setLoan_amt(etLoanAmount.getText().toString());
            requestEntity.setMobile(etMobile.getText().toString());
            requestEntity.setMonthly_income(etMonthlyIncome.getText().toString());
            requestEntity.setProductId(String.valueOf(spProduct.getSelectedItemPosition()));
            requestEntity.setRemark(etRemark.getText().toString());
            requestEntity.setName(etName.getText().toString());

            showDialog();
            new QuickLeadController(this).saveQuickLead(requestEntity, this);
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof QuickLeadResponse) {
            dialogMessage(true, ((QuickLeadResponse) response).getMasterData().getLead_Id(), response.getMessage());
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        dialogMessage(false, t.getMessage(), "");
    }


    private void dialogMessage(final boolean isSuccess, String AppNo, String displayMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        StringBuilder Message = new StringBuilder();
        if (isSuccess) {
            builder.setTitle("Lead Saved..!");
            String strMessage = "Lead ID:" + AppNo + "\n\n";
            String success = displayMessage;
            Message.append(strMessage + success);

        } else {
            builder.setTitle("Failed");
            String failure = AppNo;
            Message.append(failure);
        }
        builder.setMessage(Message.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (isSuccess) {
                            finish();
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView msgTxt = (TextView) dialog.findViewById(android.R.id.message);
        msgTxt.setTextSize(14.0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);

            finish();

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        supportFinishAfterTransition();
        super.onBackPressed();
    }

    private void settingWebview() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);


        /*MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);*/
        webView.setWebViewClient(new WebViewClient() {
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        String googleDocs = "https://docs.google.com/viewer?url=";
                        webView.loadUrl(googleDocs + url);
                    }
                }
                return false;
            }
        });
        webView.getSettings().setBuiltInZoomControls(true);
        Log.d("URL", url);
        //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        webView.loadUrl(url);
    }

}
