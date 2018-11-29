package com.datacomp.magicfinmart.onlineexpressloan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller.ExpressLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.EarlySalaryRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.EarlySalaryLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

public class EarlySalaryActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    PrefManager prefManager;
    DBPersistanceController dbPersistanceController;

    CardView ccPersonal;

    Button btnEarlySalarySubmit;

    EditText etFirstName, etLastName, etMobileNumber, etEmail,etAge,etRefCode,etLoanAmount,etTakeHomeSal;
    Spinner spEarlySal;
    RadioButton rbSal, rbSelfEmployed;
    RadioGroup rgSalaried;


    int BankID = 0;
    String LoanType = "";


    EarlySalaryRequestEntity requestEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_early_salary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        requestEntity = new EarlySalaryRequestEntity();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            BankID = extras.getInt("BANK_ID",0);
            LoanType = extras.getString("LOAN_TYPE","");
            //The key argument here must match that used in the other activity
        }
        setListener();

    }


    private  void init(){

        ccPersonal = (CardView) findViewById(R.id.ccPersonal);

        btnEarlySalarySubmit = (Button) findViewById(R.id.btnEarlySalarySubmit);



        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etAge = (EditText) findViewById(R.id.etAge);
        etRefCode = (EditText) findViewById(R.id.etRefCode);

        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);
        etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etTakeHomeSal = (EditText) findViewById(R.id.etTakeHomeSal);
        rgSalaried = (RadioGroup) findViewById(R.id.rgSalaried);

        rbSal = (RadioButton) findViewById(R.id.rbSal);

        rbSelfEmployed = (RadioButton) findViewById(R.id.rbSelfEmployed);
        spEarlySal = (Spinner) findViewById(R.id.spEarlySal);
        etTakeHomeSal.setVisibility(View.GONE);

    }

    private void setListener() {

        btnEarlySalarySubmit.setOnClickListener(this);
        rbSal.setOnCheckedChangeListener(haveCC);
    }

    CompoundButton.OnCheckedChangeListener haveCC = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                //etTakeHomeSal.setFocusable(true);
                etTakeHomeSal.setVisibility(View.VISIBLE);

            } else {

                etTakeHomeSal.setVisibility(View.GONE);
            }
        }
    };


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnEarlySalarySubmit) {


            //region Validation
            if (spEarlySal.getSelectedItemPosition() == 0) {

                Toast.makeText(this, "Select  City", Toast.LENGTH_SHORT).show();
                return;
            }


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


            if (!isEmpty(etMobileNumber)) {
                etMobileNumber.setError("Invalid mobile number");
                etMobileNumber.setFocusable(true);
                return;
            } else {
                etMobileNumber.setError(null);
            }

            if (isValidePhoneNumber(etMobileNumber)) {
                etMobileNumber.setError(null);
            } else {

                etMobileNumber.setError("Invalid  mobile number");
                etMobileNumber.setFocusable(true);
                return;

            }

            if (etMobileNumber.getText().toString().length() < 10) {
                etMobileNumber.setError("Invalid mobile number");
                etMobileNumber.setFocusable(true);
                return;
            } else {
                etMobileNumber.setError(null);
            }

            if (!isEmpty(etEmail)) {
                etEmail.setError("Invalid email id");
                etEmail.setFocusable(true);
                return;
            } else {
                etEmail.setError(null);
            }

            if (isValideEmailID(etEmail)) {
                etEmail.setError(null);
            } else {

                etEmail.setError("Invalid emailid");
                etEmail.setFocusable(true);
                return;
            }

            if (!isEmpty(etAge)) {
                etAge.setError("Invalid age");
                etAge.setFocusable(true);
                return;
            } else {
                etAge.setError(null);
            }

            if((Integer.valueOf(etAge.getText().toString()) <= 21))
            {
                showAlert("Age should be equal or greater than 21 ");
                return;
            }
            if (!isEmpty(etLoanAmount)) {
                etLoanAmount.setError("Invalid loan amount");
                etLoanAmount.setFocusable(true);
                return;
            } else {
                etLoanAmount.setError(null);
            }


            if((Double.valueOf(etLoanAmount.getText().toString()) > 100000 ))
            {
                showAlert("Maximum loan amount allowed only 1 lacs");
                return;
            }
            if (rbSal.isChecked()) {


            if (!isEmpty(etTakeHomeSal)) {
                etTakeHomeSal.setError("Invalid salary");
                etTakeHomeSal.setFocusable(true);
                return;
            } else {
                etTakeHomeSal.setError(null);
            }

                if((Double.valueOf(etTakeHomeSal.getText().toString()) < 20000))
                {
                    showAlert("Take Home Salary should be equal or greater than 20 thousands");
                    return;
                }
        }
            //endregion
            //region Request Entity


            requestEntity.setFirstName(etFirstName.getText().toString());
            requestEntity.setLastName(etLastName.getText().toString());
            requestEntity.setEmail(etEmail.getText().toString());
            if (rbSal.isChecked()) {
                requestEntity.setEmployment("Salary");
                requestEntity.setMonthlySalary(etTakeHomeSal.getText().toString());
            } else {
                requestEntity.setEmployment("Self Employed");
                requestEntity.setMonthlySalary("0");
            }
            requestEntity.setPhoneNumber(etMobileNumber.getText().toString());
            requestEntity.setAge(etAge.getText().toString());
            requestEntity.setRefferalCode(etRefCode.getText().toString());
            requestEntity.setLoanAmount(etLoanAmount.getText().toString());

            requestEntity.setFBAID(String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()));
            requestEntity.setBrokerid(new DBPersistanceController(this).getUserData().getLoanId());

            requestEntity.setApplicationID("0");
            requestEntity.setBankId(String.valueOf(BankID));
            requestEntity.setLoanType(LoanType);
            requestEntity.setCity(spEarlySal.getSelectedItem().toString());
            //endregion
            showDialog();
            new ExpressLoanController(this).saveEarlySalaryLoan(requestEntity, this);
        }

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof EarlySalaryLoanResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
            //    dialogMessage(true, ((EarlySalaryLoanResponse) response).getMessage());
                dialogMessage(true, "", ((EarlySalaryLoanResponse) response).getMessage());

            }
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
            builder.setTitle("Applied Successfully..!");
            String strMessage = "Application No:" + AppNo + "\n\n";
            String success = displayMessage;
            Message.append(strMessage + success);

        } else {
            builder.setTitle("Failed ");
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
        msgTxt.setTextSize(12.0f);
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

}
