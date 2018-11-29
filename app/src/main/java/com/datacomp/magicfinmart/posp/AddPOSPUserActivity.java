package com.datacomp.magicfinmart.posp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.RegisterRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PincodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RegisterFbaResponse;

public class AddPOSPUserActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {

    EditText etFirstName, etLastName, etDob, etMobile1, etMobile2, etEmail, etPincode, etCity, etState;
    TextView txtMale, txtFemale;
    Button btnAddUser;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat passdateFormat = new SimpleDateFormat("ddMMyyyy");
    boolean isMale = false, isFemale = false;
    Boolean isValidPersonalInfo = false, isMobileValid = false;
    RegisterRequestEntity registerRequestEntity;
    String pass = "";
    DBPersistanceController dbPersistanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pospuser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerRequestEntity = new RegisterRequestEntity();
        dbPersistanceController = new DBPersistanceController(this);
        init_widget();
    }

    private void init_widget() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etDob = findViewById(R.id.etDob);
        etMobile1 = findViewById(R.id.etMobile1);
        etMobile2 = findViewById(R.id.etMobile2);
        etEmail = findViewById(R.id.etEmail);
        etPincode = findViewById(R.id.etPincode);
        etCity = findViewById(R.id.etCity);
        etState = findViewById(R.id.etState);
        txtMale = findViewById(R.id.txtMale);
        txtFemale = findViewById(R.id.txtFemale);
        etPincode.addTextChangedListener(pincodeTextWatcher);

        txtMale.setOnClickListener(this);
        txtFemale.setOnClickListener(this);
        btnAddUser = findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(this);
        etDob.setOnClickListener(datePickerDialog);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.txtMale) {
            isFemale = false;
            isMale = true;
            setGender(txtMale, txtFemale);

        } else if (i == R.id.txtFemale) {
            isFemale = true;
            isMale = false;
            setGender(txtFemale, txtMale);

        } else if (i == R.id.btnAddUser) {
            isValidPersonalInfo = validateRegister();
            if (isValidPersonalInfo) {
                setRegisterPersonalRequest();
                showDialog();
                new RegisterController(this).addChildPosp(registerRequestEntity, this);
            }

        }
    }

    private void setRegisterPersonalRequest() {
        registerRequestEntity.setFirstName("" + etFirstName.getText().toString());
        registerRequestEntity.setLastName("" + etLastName.getText().toString());
        registerRequestEntity.setDOB("" + etDob.getText().toString());
        registerRequestEntity.setMobile_1("" + etMobile1.getText().toString());
        registerRequestEntity.setMobile_2("" + etMobile2.getText().toString());
        registerRequestEntity.setEmailId("" + etEmail.getText().toString());
        registerRequestEntity.setPinCode("" + etPincode.getText().toString());
        if (isMale) {
            registerRequestEntity.setGender("M");
        } else {
            registerRequestEntity.setGender("F");
        }

        //password setting null
        if (!pass.equalsIgnoreCase("")) {
            registerRequestEntity.setPassword(pass);
        } else {
            Date date = (Date) etDob.getTag(R.id.etDob);
            pass = passdateFormat.format(date.getTime());
            registerRequestEntity.setPassword(pass);
        }
        registerRequestEntity.setParentId("" + dbPersistanceController.getUserData().getFBAId());
        //registerRequestEntity.setAppSource(String.valueOf(spSource.getSelectedItemPosition() + 1));
    }

    private Boolean validateRegister() {
        if (!isEmpty(etFirstName)) {
            etFirstName.requestFocus();
            etFirstName.setError("Enter First Name");
            return false;
        }

        if (!isEmpty(etLastName)) {
            etLastName.requestFocus();
            etLastName.setError("Enter Last Name");
            return false;
        }
        if (!isEmpty(etDob)) {
            etDob.requestFocus();
            etDob.setError("Enter Dob");
            return false;
        }
        if (!isValidePhoneNumber(etMobile1)) {
            etMobile1.requestFocus();
            etMobile1.setError("Enter Mobile ");
            return false;
        }
        if (!isValideEmailID(etEmail)) {
            etEmail.requestFocus();
            etEmail.setError("Enter Email");
            return false;
        }

        if (!isEmpty(etPincode)) {
            etPincode.requestFocus();
            etPincode.setError("Enter Pincode");
            return false;
        }
        if (!etPincode.getText().toString().equals("")) {
            if (etPincode.getText().toString().length() != 6) {
                etPincode.requestFocus();
                etPincode.setError("Enter Valid Pincode");
                return false;
            }
        }
        if (!isEmpty(etCity)) {
            etCity.requestFocus();
            etCity.setError("Enter City");
            return false;
        }
        if (!isEmpty(etState)) {
            etState.requestFocus();
            etState.setError("Enter State");
            return false;
        }

      /*  if (!isVAlidPromo) {
            etRefererCode.requestFocus();
            etRefererCode.setError("Enter Valid Promo Code");
            return false;
        }*/
        return true;
    }


    private void setGender(TextView clickedText, TextView textView1) {
        clickedText.setBackgroundResource(R.drawable.customeborder_blue);
        clickedText.setTextColor(ContextCompat.getColor(this, Utility.getPrimaryColor(this)));

        textView1.setBackgroundResource(R.drawable.customeborder);
        textView1.setTextColor(ContextCompat.getColor(this, R.color.description_text));
    }


    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, AddPOSPUserActivity.this);

            if (view.getId() == R.id.etDob) {
                DateTimePicker.showHealthAgeDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            //store selected date in TAG
                            etDob.setTag(R.id.etDob, calendar.getTime());
                            etDob.setText(currentDay);
                        }
                    }
                });
            }
        }
    };

    //region textwatcher
    TextWatcher pincodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (start == 5) {
                etCity.setText("");
                etState.setText("");
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 6) {
                showDialog("Fetching City...");
                new RegisterController(AddPOSPUserActivity.this)
                        .getCityState(etPincode.getText().toString(), AddPOSPUserActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof PincodeResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                Constants.hideKeyBoard(etPincode, this);
                etState.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                etCity.setText("" + ((PincodeResponse) response).getMasterData().getCityname());

                registerRequestEntity.setCity("" + ((PincodeResponse) response).getMasterData().getCityname());
                registerRequestEntity.setState("" + ((PincodeResponse) response).getMasterData().getState_name());
                registerRequestEntity.setStateID("" + ((PincodeResponse) response).getMasterData().getStateid());

            }
        } else if (response instanceof RegisterFbaResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                //resultIntent.putExtra("MODIFY", motorRequestEntity);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
