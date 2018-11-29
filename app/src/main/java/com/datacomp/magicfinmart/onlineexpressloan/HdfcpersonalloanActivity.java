package com.datacomp.magicfinmart.onlineexpressloan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller.ExpressLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.HdfcPers_SaveRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.HdfcPers_SaveResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;


public class HdfcpersonalloanActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {


    CardView ccPersonal, ccCurrentAddress;
    CheckBox chkTermsCondition, chkSameAsAbove;
    Button btnhdfc;
    //personal detail
    EditText etCustomerName, etEmailPersContInfo, etDOB, etLoanAmount,
            etPincode,etPancard;
    EditText etCompany, etNetIncome,etyrs_of_emp ;
    EditText etMobileNo, etaltmobile, etLandline1, etLandline2,etOngoingEMI
            ,etcurrentaddress,etPermanentaddress;


    Spinner speducationList, spIncomeSource;
    TextView tvBankbranch;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");


    ArrayAdapter<String> branchListAdapter;
    List<String> branchList;

    Spinner acCitybranchList;
    HdfcPers_SaveRequestEntity requestEntity;
    int BankID = 0;
    String LoanType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdfcpersonalloan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        requestEntity = new HdfcPers_SaveRequestEntity();

        branchList = new ArrayList<>();
        branchList = new DBPersistanceController(this).gethdfcpersonalloanbankbranchlist();

        branchBinding();

    }

    private void init() {
        acCitybranchList = (Spinner) findViewById(R.id.spbranchlocation);
        tvBankbranch = (TextView) findViewById(R.id.tvBankbranch);


        ccPersonal = (CardView) findViewById(R.id.ccPersonal);

        ccCurrentAddress = (CardView) findViewById(R.id.ccCurrentAddress);

        chkTermsCondition = (CheckBox) findViewById(R.id.chkTermsCondition);
        chkSameAsAbove = (CheckBox) findViewById(R.id.chkSameAsAbove);


        //region personal detail
        etCustomerName = (EditText) findViewById(R.id.etCustomerName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etEmailPersContInfo = (EditText) findViewById(R.id.etEmailPersContInfo);
        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);
        etyrs_of_emp = (EditText) findViewById(R.id.etyrs_of_emp);


        speducationList = (Spinner) findViewById(R.id.speducationList);
        spIncomeSource = (Spinner) findViewById(R.id.spIncomeSource);


        etPancard = (EditText) findViewById(R.id.etPancard);
        etPancard.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});

        //endregion

        //region company detail
        etCompany = (EditText) findViewById(R.id.etCompany);
        etNetIncome = (EditText) findViewById(R.id.etNetIncome);

        etMobileNo = (EditText) findViewById(R.id.etMobileNo);
        etaltmobile = (EditText) findViewById(R.id.etaltmobile);
        etLandline1 = (EditText) findViewById(R.id.etLandline1);
        etLandline2 = (EditText) findViewById(R.id.etLandline2);



        etPincode = (EditText) findViewById(R.id.etPincode);

        etOngoingEMI = (EditText) findViewById(R.id.etOngoingEMI);
        etcurrentaddress = (EditText) findViewById(R.id.etcurrentaddress);
        etPermanentaddress = (EditText) findViewById(R.id.etPermanentaddress);

        btnhdfc=(Button)findViewById(R.id.btnhdfc);

        //endregion
    }

    private void branchBinding() {
        branchListAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, branchList);
        acCitybranchList.setAdapter(branchListAdapter);

    }


    private void setListener() {
        etDOB.setOnClickListener(datePickerDialog);

        btnhdfc.setOnClickListener(this);

        chkSameAsAbove.setOnCheckedChangeListener(addressSameAsAbove);
        acCitybranchList.setOnItemSelectedListener(onItemSelectedListener);

    }

    CompoundButton.OnCheckedChangeListener addressSameAsAbove = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                bindAddress();
            } else {

                etPermanentaddress.setText("");

            }
        }
    };

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            if (position > 0) {


               // String s2= gethdfcplbankbranchrListName(acCitybranchList.getSelectedItem().toString());
                tvBankbranch.setText( new DBPersistanceController(HdfcpersonalloanActivity.this).gethdfcplbankbranchrList(acCitybranchList.getSelectedItem().toString()));
            } else {
                tvBankbranch.setText("");
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    protected void bindAddress() {
        etPermanentaddress.setText(etcurrentaddress.getText().toString());
    }
    //region datepicker


    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, HdfcpersonalloanActivity.this);
            if (view.getId() == R.id.etDOB) {
                DateTimePicker.showExpressAgeDatePicker_21(view.getContext(), (Calendar) view.getTag(R.id.etDOB),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                //TODO:set tag to DOB -- nilesh
                                //Calendar calSelectedPrev = Calendar.getInstance();

                                calendar.set(year, monthOfYear, dayOfMonth);
                                //calSelectedPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                etDOB.setText(currentDay);
                                //TODO:set tag to DOB -- nilesh
                                etDOB.setTag(R.id.etDOB, calendar);
                                //etDate.setTag(R.id.et_date, calendar.getTime());
                            }
                        });
            }
        }
    };
    //endregion

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnhdfc) {

            if (chkTermsCondition.isChecked()) {

                //region validation personal

                if (!isEmpty(etCustomerName)) {
                    etCustomerName.setError("Enter Customer name");
                    etCustomerName.setFocusable(true);
                    return;
                } else {
                    etCustomerName.setError(null);
                }


                if (!isEmpty(etDOB)) {
                    etDOB.setError("Invalid birth date");
                    etDOB.setFocusable(true);
                    return;
                } else {
                    etDOB.setError(null);
                }


                if (!isEmpty(etLoanAmount)) {
                    etLoanAmount.setError("Enter Loan Amount");
                    etLoanAmount.setFocusable(true);
                    return;
                } else {
                    etLoanAmount.setError(null);
                }

                double loanAmnt = Double.valueOf(etLoanAmount.getText().toString());
                if (loanAmnt < 75000 || loanAmnt > 8000000) {
                    showAlert("Loan amount should be between 75 Thousands to 80 Lacs");
                    return;
                }

                if (!isValidPan(etPancard)) {
                    etPancard.setError("Invalid Pan card");
                    etPancard.setFocusable(true);
                    return;
                } else {
                    etPancard.setError(null);
                }
                if (!isEmpty(etCompany)) {
                    etCompany.setError("Enter Company name");
                    etCompany.setFocusable(true);
                    return;
                } else {
                    etCompany.setError(null);
                }


                if (!isEmpty(etMobileNo)) {
                    etMobileNo.setError("Enter Mobile No");
                    etMobileNo.setFocusable(true);
                    return;
                } else {
                    etMobileNo.setError(null);
                }


//                if (speducationList.getSelectedItemPosition() == 0) {
//
//                    Toast.makeText(this, "Select No of Dependents", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                if (spIncomeSource.getSelectedItemPosition() == 0) {
//                    Toast.makeText(this, "Select Supplementary card", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                if (spTypeCompany.getSelectedItemPosition() == 0) {
//                    Toast.makeText(this, "Select Mailing address", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                //endregion

                //region validation company


                if (!isValidePhoneNumber(etMobileNo)) {
                    etMobileNo.setError("Enter Mobile No)");
                    etMobileNo.setFocusable(true);
                    return;
                } else {
                    etMobileNo.setError(null);
                }

//                if (!isValidePhoneNumber(etaltmobile)) {
//                    etaltmobile.setError("Enter Designation");
//                    etaltmobile.setFocusable(true);
//                    return;
//                } else {
//                    etaltmobile.setError(null);
//                }



//                if (!isValidePhoneNumber(etLandline1)) {
//                    etLandline1.setError("Invalid Mobile number");
//                    etLandline1.setFocusable(true);
//                    return;
//                } else {
//                    etLandline1.setError(null);
//                }
//                if (!isValidePhoneNumber(etLandline2)) {
//                    etLandline2.setError("Invalid Mobile number");
//                    etLandline2.setFocusable(true);
//                    return;
//                } else {
//                    etLandline2.setError(null);
//                }


//                if (spTypeCompany.getSelectedItemPosition() == 0) {
//                    Toast.makeText(this, "Select Type of company", Toast.LENGTH_SHORT).show();
//                    return;
//                }


                //endregion

                //region validation current address

                if (!isEmpty(etNetIncome)) {
                    etNetIncome.setError("Enter Net Income");
                    etNetIncome.setFocusable(true);
                    return;
                } else {
                    etNetIncome.setError(null);
                }

                if((Double.valueOf(etNetIncome.getText().toString()) < 20000))
                {
                    showAlert("Net Income should be equal or greater than 20 thousands");
                    return;
                }



                if (!isEmpty(etyrs_of_emp)) {
                    etyrs_of_emp.setError("Enter No of Years in Employment");
                    etyrs_of_emp.setFocusable(true);
                    return;
                } else {
                    etyrs_of_emp.setError(null);
                }

                if (etPincode.getText().toString().length() < 6) {
                    etPincode.setError("Invalid Pincode");
                    etPincode.setFocusable(true);
                    return;
                } else {
                    etPincode.setError(null);
                }

                //endregion

                //region validation permanent address

                if (!isEmpty(etEmailPersContInfo)) {
                    etEmailPersContInfo.setError("Enter Email Address");
                    etEmailPersContInfo.setFocusable(true);
                    return;
                } else {
                    etEmailPersContInfo.setError(null);
                }
                if (!isValideEmailID(etEmailPersContInfo)) {
                    etEmailPersContInfo.setError("Invalid Email ID");
                    etEmailPersContInfo.setFocusable(true);
                    return;
                }
                if (!isEmpty(etcurrentaddress)) {
                    etcurrentaddress.setError("Enter Current Address");
                    etcurrentaddress.setFocusable(true);
                    return;
                } else {
                    etcurrentaddress.setError(null);
                }



                //endregion

                //region creating request
                requestEntity.setBranch_location(acCitybranchList.getSelectedItem().toString());
                requestEntity.setBranch_code(tvBankbranch.getText().toString());

                requestEntity.setDob(getYYYYMMDDPattern(etDOB.getText().toString()));
                requestEntity.setCustomer_name(etCustomerName.getText().toString());

                if(etLoanAmount.getText().toString().length() > 0)
                {
                    requestEntity.setLoanamount(etLoanAmount.getText().toString());
                }else
                {
                    requestEntity.setLoanamount("0");
                }
                requestEntity.setQualification(speducationList.getSelectedItem().toString());
                requestEntity.setPancard(etPancard.getText().toString());
                requestEntity.setCompany_name(etCompany.getText().toString());
                requestEntity.setProfile(spIncomeSource.getSelectedItem().toString());


                if(etMobileNo.getText().toString().length() > 0)
                {
                    requestEntity.setMobile_num(etMobileNo.getText().toString());
                }else {
                    requestEntity.setMobile_num("0");
                }

                if(etaltmobile.getText().toString().length() > 0)
                {
                    requestEntity.setAlternate_num(etaltmobile.getText().toString());
                }else {
                    requestEntity.setAlternate_num("0");
                }

                if(etLandline1.getText().toString().length() > 0)
                {
                    requestEntity.setLandline(etLandline1.getText().toString());
                }else {
                    requestEntity.setLandline("0");
                }

                if(etLandline2.getText().toString().length() > 0)
                {
                    requestEntity.setAlt_landline(etLandline2.getText().toString());
                }else {
                    requestEntity.setAlt_landline("0");
                }

                if(etNetIncome.getText().toString().length() > 0)
                {
                    requestEntity.setNet_income(etNetIncome.getText().toString());
                }else {
                    requestEntity.setNet_income("0");
                }


                requestEntity.setPincode(etPincode.getText().toString());


                requestEntity.setFBAID(String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()));
                requestEntity.setBrokerid(new DBPersistanceController(this).getUserData().getLoanId());
                requestEntity.setEmpid("");

                requestEntity.setCampaignName("HDFC PL");
                requestEntity.setSource("");
                requestEntity.setLoanType("Personal Loan");
                requestEntity.setCity("");
                //personal detail

                requestEntity.setEmail(etEmailPersContInfo.getText().toString());


                if(etOngoingEMI.getText().toString().length() > 0)
                {
                    requestEntity.setEmi(etOngoingEMI.getText().toString());
                }else {
                    requestEntity.setEmi("0");
                }
                if(etyrs_of_emp.getText().toString().length() > 0)
                {
                    requestEntity.setYrs_of_emp(etyrs_of_emp.getText().toString());
                }else {
                    requestEntity.setYrs_of_emp("0");
                }

                requestEntity.setSame("on");

                requestEntity.setCurrent_add(etcurrentaddress.getText().toString());
                requestEntity.setPer_add(etPermanentaddress.getText().toString());
                requestEntity.setBankId(String.valueOf(BankID));
                requestEntity.setLoanType(LoanType);

                //endregion

                showDialog();
                new ExpressLoanController(this).saveHDFCPersonalLoan(requestEntity, this);
            } else {
                Toast.makeText(this, "Accept Terms and Condtion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof HdfcPers_SaveResponse) {
            if (response.getStatusNo() == 0) {
                if (((HdfcPers_SaveResponse) response).getMasterData().getLead_Id().length() > 1) {

                    String appid= ((HdfcPers_SaveResponse) response).getMasterData().getLead_Id();

                dialogMessage(true,appid , ((HdfcPers_SaveResponse) response).getMessage());
                } else {
                    dialogMessage(false, "", ((HdfcPers_SaveResponse) response).getMessage());
                }
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

    public static String getYYYYMMDDPattern(String dateCal) {

        String dateSelected = "";
        if (dateCal.equals("")) {
            return "";
        }
        long select_milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        Date d = null;
        try {
            d = f.parse(dateCal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        select_milliseconds = d.getTime();

        Date date = new Date(select_milliseconds); //Another date Formate ie yyyy-mm-dd
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        dateSelected = df2.format(date);
        return dateSelected;
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
