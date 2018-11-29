package com.datacomp.magicfinmart.creditcard;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.creditcard.CreditCardController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CreditCardEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.CCRblRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CCRblResponse;

public class RBLCreditApplyActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    CreditCardEntity mCreditCardEntity;
    EditText etFirstName, etLastName, etFatherName, etDob, etEmail;
    RadioButton rbmale, rbHadLoan, rbSalaried;
    EditText etCCApplied, etProcessingFees, etMobile, etpancard;
    EditText etAddress1, etAddress2, etLandMark, etPincode, etMonthlyIncome;
    AutoCompleteTextView acCity;
    Button btnSubmit;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    ArrayAdapter<String> cityAdapter;
    List<String> cityList;
    TextView txtEmploymentType;
    Spinner spTitle;

    int EmploymentType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rblcredit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("RBL CREDIT CARD");
        init();
        cityList = new ArrayList<>();
        cityList = new DBPersistanceController(this).getRblCity();
        cityBinding();

        //for validating auto complete city
        acCity.setOnFocusChangeListener(acCityFocusChange);

        if (getIntent().getParcelableExtra(CreditCardActivity.SELECTED_CREDIT_CARD) != null) {
            mCreditCardEntity = getIntent().getParcelableExtra(CreditCardActivity.SELECTED_CREDIT_CARD);
            EmploymentType = getIntent().getIntExtra("EmploymentType", 0);
            bindData();
        }
    }

    private void cityBinding() {
        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        acCity.setAdapter(cityAdapter);
        acCity.setThreshold(1);
    }

    private void init() {
        txtEmploymentType = findViewById(R.id.txtEmploymentType);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etFatherName = (EditText) findViewById(R.id.etFatherName);
        etDob = (EditText) findViewById(R.id.etDob);
        etDob.setOnClickListener(datePickerDialog);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCCApplied = (EditText) findViewById(R.id.etCCApplied);
        etProcessingFees = (EditText) findViewById(R.id.etProcessingFees);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etpancard = (EditText) findViewById(R.id.etpancard);
        etpancard.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});
        etAddress1 = (EditText) findViewById(R.id.etAddress1);
        etAddress2 = (EditText) findViewById(R.id.etAddress2);
        etLandMark = (EditText) findViewById(R.id.etLandMark);
        etPincode = (EditText) findViewById(R.id.etPincode);
        etMonthlyIncome = (EditText) findViewById(R.id.etMonthlyIncome);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        acCity = (AutoCompleteTextView) findViewById(R.id.acCity);

        rbmale = (RadioButton) findViewById(R.id.rbmale);
        rbHadLoan = (RadioButton) findViewById(R.id.rbHadLoan);
        rbSalaried = (RadioButton) findViewById(R.id.rbSalaried);

        spTitle = (Spinner) findViewById(R.id.spTitle);

        btnSubmit.setOnClickListener(this);


    }

    private void bindData() {
        etCCApplied.setText(mCreditCardEntity.getCreditCardType());
        etProcessingFees.setText(mCreditCardEntity.getProcessingFees());
        if (EmploymentType == 1)
            txtEmploymentType.setText("Salaried");
        else
            txtEmploymentType.setText("Self");
    }

    View.OnFocusChangeListener acCityFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {

                String str = acCity.getText().toString();

                ListAdapter listAdapter = acCity.getAdapter();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    String temp = listAdapter.getItem(i).toString();
                    if (str.compareTo(temp) == 0) {
                        return;
                    }
                }

                acCity.setText("");
                acCity.setError("Invalid city");
                acCity.setFocusable(true);
            } else {
                acCity.setError(null);
            }
        }
    };

    //region datepicker
    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, RBLCreditApplyActivity.this);

            if (view.getId() == R.id.etDob) {
                DateTimePicker.showIciciCreditCardDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etDob.setText(currentDay);
                        }
                    }
                });
            }
        }
    };

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSubmit) {

            if (!isEmpty(etFirstName)) {
                etFirstName.setError("Enter First name");
                etFirstName.setFocusable(true);
                return;
            } else {
                etFirstName.setError(null);
            }

            if (!isEmpty(etLastName)) {
                etLastName.setError("Enter Last name");
                etLastName.setFocusable(true);
                return;
            } else {
                etLastName.setError(null);
            }

            if (!isEmpty(etFatherName)) {
                etFatherName.setError("Enter Father name");
                etFatherName.setFocusable(true);
                return;
            } else {
                etFatherName.setError(null);
            }

            if (!isEmpty(etAddress1)) {
                etAddress1.setError("Enter Address");
                etAddress1.setFocusable(true);
                return;
            } else {
                etAddress1.setError(null);
            }

            if (!isEmpty(etAddress2)) {
                etAddress2.setError("Enter Address");
                etAddress2.setFocusable(true);
                return;
            } else {
                etAddress2.setError(null);
            }


            if (!isEmpty(etLandMark)) {
                etLandMark.setError("Enter landmark");
                etLandMark.setFocusable(true);
                return;
            } else {
                etLandMark.setError(null);
            }

            if (etPincode.getText().toString().length() < 6) {
                etPincode.setError("Invalid Pincode");
                etPincode.setFocusable(true);
                return;
            } else {
                etPincode.setError(null);
            }

            if (!isEmpty(etMonthlyIncome)) {
                etMonthlyIncome.setError("Enter monthly income");
                etMonthlyIncome.setFocusable(true);
                return;
            } else {
                etMonthlyIncome.setError(null);
            }

            if (!isValidePhoneNumber(etMobile)) {
                etMobile.setError("Invalid Mobile number");
                etMobile.setFocusable(true);
                return;
            } else {
                etMobile.setError(null);
            }


            if (!isValidPan(etpancard)) {
                etpancard.setError("Invalid pan number");
                etpancard.setFocusable(true);
                return;
            } else {
                etpancard.setError(null);
            }


            if (!isValideEmailID(etEmail)) {
                etEmail.setError("Invalid Email ID");
                etEmail.setFocusable(true);
                return;
            }

            if (acCity.getText().toString().length() == 0) {
                acCity.setError("Invalid City");
                acCity.setFocusable(true);
                return;
            } else {
                acCity.setError(null);
            }

            CCRblRequestEntity rblCCRequest = new CCRblRequestEntity();

            rblCCRequest.setCreditCardDetailId(mCreditCardEntity.getCreditCardDetailId());
            rblCCRequest.setFba_id(new DBPersistanceController(this).getUserData().getFBAId());
            //server hit
            if (spTitle.getSelectedItemPosition() == 0) {
                rblCCRequest.setTitle(1);
                //title = 1;
            } else if (spTitle.getSelectedItemPosition() == 4) {
                //title =99
                rblCCRequest.setTitle(99);
            } else {
                //title =2\
                rblCCRequest.setTitle(2);
            }

            rblCCRequest.setCreditCardApplied(mCreditCardEntity.getCreditCardTypeId());
            rblCCRequest.setFirstName(etFirstName.getText().toString());
            rblCCRequest.setLastName(etLastName.getText().toString());
            rblCCRequest.setFatherName(etFatherName.getText().toString());
            rblCCRequest.setDOB(etDob.getText().toString());
            rblCCRequest.setCardType(etCCApplied.getText().toString());
            rblCCRequest.setProcessingFee(Integer.parseInt(etProcessingFees.getText().toString()));
            rblCCRequest.setResAddress1(etAddress1.getText().toString());
            rblCCRequest.setResAddress2(etAddress2.getText().toString());
            rblCCRequest.setLandmark(etLandMark.getText().toString());
            rblCCRequest.setResPIN(etPincode.getText().toString());
            rblCCRequest.setNMI(etMonthlyIncome.getText().toString());
            rblCCRequest.setMobile(etMobile.getText().toString());
            rblCCRequest.setPAN(etpancard.getText().toString());

            rblCCRequest.setEmail(etEmail.getText().toString());
            rblCCRequest.setCreditCardApplied(String.valueOf(mCreditCardEntity.getCreditCardApplied()));
            rblCCRequest.setBrokerid(new DBPersistanceController(this).getUserData().getLoanId());
            rblCCRequest.setSource("");
            rblCCRequest.setVersion("");
            rblCCRequest.setMiddleName("");
            if (rbSalaried.isChecked()) {
                rblCCRequest.setEmpType(1);
            } else {
                rblCCRequest.setEmpType(2);
            }

            if (rbmale.isChecked()) {
                rblCCRequest.setGender("1");
            } else {
                rblCCRequest.setGender("2");
            }
            if (rbHadLoan.isChecked()) {
                rblCCRequest.setHadLoanOrCreditCardFromAnyBank("Y");
            } else {
                rblCCRequest.setHadLoanOrCreditCardFromAnyBank("N");
            }

            try {
                rblCCRequest.setResCity(new DBPersistanceController(this).getRblCityCode(
                        acCity.getText().toString()));
            } catch (Exception e) {

            }

            showDialog();
            new CreditCardController(this).applyRbl(rblCCRequest, this);
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof CCRblResponse) {
            if (response.getStatusNo() == 0) {
                if (((CCRblResponse) response).getMasterData().getReferenceCode().length() > 1) {
                    dialogMessage(true, ((CCRblResponse) response).getMasterData().getReferenceCode(), response.getMessage(), ((CCRblResponse) response).getMasterData().getStatusX());
                } else {
                    dialogMessage(false, "", ((CCRblResponse) response).getMessage(), ((CCRblResponse) response).getMasterData().getStatusX());
                }
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        dialogMessage(false, "", t.getMessage(), "");
    }

    private void dialogMessage(final boolean isSuccess, String AppNo, String displayMessage, String Decision) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        StringBuilder Message = new StringBuilder();
        if (isSuccess) {
            builder.setTitle("Applied Successfully..!");
            String strMessage = "Application No:" + AppNo + "\n\n";
            String DescText = "<b>" + Decision + "</b> ";
            String Desc = "Decision: " + Html.fromHtml(DescText) + "\n\n";
            String success = displayMessage;
            Message.append(strMessage + Desc + success);

        } else {
            builder.setTitle("Failed ");
            String failure = displayMessage;
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
        msgTxt.setTextSize(12.0f);
    }
}
