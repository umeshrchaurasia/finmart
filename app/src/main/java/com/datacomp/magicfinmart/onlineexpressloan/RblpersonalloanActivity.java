package com.datacomp.magicfinmart.onlineexpressloan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller.ExpressLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.RBLPesonalLoanReqEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.ExpressRbPersonalResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

public class RblpersonalloanActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {
    CardView ccPersonal, ccCompantDetail, ccQuoteDetail;

    Button btnSubmit;

    TextInputLayout tlFirstName, tlMiddleName, tlLastName;

    //personal detail
    EditText etFirstName, etMiddleName, etLastName, etDOB, etMonthlyIncome, etMobile, etEmailPers, etEMI, etLivingSince, etPincode, etAddress1, etAddress2;

    Spinner spResType, spSalaried, spTenure, spOrgCategory, spQualification;
    RadioButton rbmale, rbfemale, rbOther;
    CheckBox chkRblCondition;

    //Quote detail

    //Company detail
    EditText etEmployerName, etLoanAmount, etLoanReq, etRateType, etBestRate, etQuteEMI, etProcessingFees,
            etJoin, etTotWorkExp, etOffAddress1, etOffAddress2, etOffPincode, etOffphoneNo, etOffPancard,etLandmark;

    AutoCompleteTextView acOffCity, acCity;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    //spinner Adapters
    ArrayAdapter<String> residenceAdapter, salariedAdapter, tenureAdapter;
    ArrayAdapter<String> OrgCategoryAdapter, QualificationAdapter;

    ArrayAdapter<String> cityAdapter;
    List<String> cityList;


    int BankID = 0;
    String LoanType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rblpersonalloan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            BankID = extras.getInt("BANK_ID",0);
            LoanType = extras.getString("LOAN_TYPE","");
            //The key argument here must match that used in the other activity
        }
        cityList = new ArrayList<>();
        cityList = new DBPersistanceController(this).getRblCity();
        cityBinding();
        cityOffBinding();
        acCity.setOnFocusChangeListener(acCityFocusChange);
        acOffCity.setOnFocusChangeListener(acCityOFFiceFocusChange);
        setListner();
        bindAllSpinner();

    }

    //region method
    private void cityBinding() {
        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        acCity.setAdapter(cityAdapter);
        acCity.setThreshold(1);
    }

    private void cityOffBinding() {
        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        acOffCity.setAdapter(cityAdapter);
        acOffCity.setThreshold(1);
    }

    private void init() {


        ccPersonal = (CardView) findViewById(R.id.ccPersonal);
        ccCompantDetail = (CardView) findViewById(R.id.ccCompantDetail);
        ccQuoteDetail = (CardView) findViewById(R.id.ccQuoteDetail);

        acCity = (AutoCompleteTextView) findViewById(R.id.acCity);
        acOffCity = (AutoCompleteTextView) findViewById(R.id.acOffCity);

        //region personal detail
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etDOB = (EditText) findViewById(R.id.etDOB);

        etMonthlyIncome = (EditText) findViewById(R.id.etMonthlyIncome);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmailPers = (EditText) findViewById(R.id.etEmailPers);
        etEMI = (EditText) findViewById(R.id.etEMI);
        etLivingSince = (EditText) findViewById(R.id.etLivingSince);
        etPincode = (EditText) findViewById(R.id.etPincode);
        etAddress1 = (EditText) findViewById(R.id.etAddress1);
        etAddress2 = (EditText) findViewById(R.id.etAddress2);
        etLandmark = (EditText) findViewById(R.id.etLandmark);


        spResType = (Spinner) findViewById(R.id.spResType);
        spSalaried = (Spinner) findViewById(R.id.spSalaried);
        spTenure = (Spinner) findViewById(R.id.spTenure);


        rbmale = (RadioButton) findViewById(R.id.rbmale);
        rbfemale = (RadioButton) findViewById(R.id.rbfemale);
        rbOther = (RadioButton) findViewById(R.id.rbOther);
        //endregion

        //region company detail
        etEmployerName = (EditText) findViewById(R.id.etEmployerName);
        etLoanAmount = (EditText) findViewById(R.id.etLoanAmount);
        etLoanReq = (EditText) findViewById(R.id.etLoanReq);
        etRateType = (EditText) findViewById(R.id.etRateType);

        etBestRate = (EditText) findViewById(R.id.etBestRate);
        etQuteEMI = (EditText) findViewById(R.id.etQuteEMI);
        etProcessingFees = (EditText) findViewById(R.id.etProcessingFees);

        etJoin = (EditText) findViewById(R.id.etJoin);
        etTotWorkExp = (EditText) findViewById(R.id.etTotWorkExp);
        etOffAddress1 = (EditText) findViewById(R.id.etOffAddress1);
        etOffAddress2 = (EditText) findViewById(R.id.etOffAddress2);

        etOffPincode = (EditText) findViewById(R.id.etOffPincode);
        etOffphoneNo = (EditText) findViewById(R.id.etOffphoneNo);
        etOffPancard = (EditText) findViewById(R.id.etOffPancard);

        spOrgCategory = (Spinner) findViewById(R.id.spOrgCategory);
        spQualification = (Spinner) findViewById(R.id.spQualification);
        chkRblCondition = (CheckBox) findViewById(R.id.chkRblCondition);

        //endregion

        btnSubmit = (Button) findViewById(R.id.btnSubmit);


    }


    private void bindAllSpinner() {

        //region RESIDENCE
        residenceAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.exp_residence_type)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(RblpersonalloanActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.exp_residence_type);
                tv.setText(items[position]);
                tv.setTextColor(Color.BLACK);
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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
        spResType.setAdapter(residenceAdapter);


        //endregion

        //region SALARIED
        salariedAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.exp_employment_salaried)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(RblpersonalloanActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.exp_employment_salaried);
                tv.setText(items[position]);
                tv.setTextColor(Color.BLACK);
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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
        spSalaried.setAdapter(salariedAdapter);


        //endregion

        //region Tenure
        tenureAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.exp_tenure)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(RblpersonalloanActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.exp_tenure);
                tv.setText(items[position]);
                tv.setTextColor(Color.BLACK);
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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
        spTenure.setAdapter(tenureAdapter);


        //endregion

        //region Org Category
        OrgCategoryAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.exp_organization)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(RblpersonalloanActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.exp_organization);
                tv.setText(items[position]);
                tv.setTextColor(Color.BLACK);
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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
        spOrgCategory.setAdapter(OrgCategoryAdapter);


        //endregion


        //region Qualification
        QualificationAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.exp_qualification)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(RblpersonalloanActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.exp_qualification);
                tv.setText(items[position]);
                tv.setTextColor(Color.BLACK);
                tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                return convertView;
            }

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
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
        spQualification.setAdapter(QualificationAdapter);


        //endregion



    }

    private void setListner() {
        etDOB.setOnClickListener(datePickerDialogApplicant);
        etJoin.setOnClickListener(datePickerDialogApplicantjoin);
        etLivingSince.setOnClickListener(datePickerDialogApplicantLivingSince);
        etLoanAmount.addTextChangedListener(loanAmountTextWatcher);
        btnSubmit.setOnClickListener(this);
  //      etOffPancard.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        etOffPancard.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});

        spTenure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != 0){
                    getEmiandProcessingFee();
                }else{
                    clearQuote();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private int getTenure() {
        int pos = 0;
        if (spTenure.getSelectedItemPosition() == 0) {
            pos = 0;
        } else if (spTenure.getSelectedItemPosition() == 1) {
            pos = 12;
        } else if (spTenure.getSelectedItemPosition() == 2) {
            pos = 24;
        } else if (spTenure.getSelectedItemPosition() == 3) {
            pos = 36;
        } else if (spTenure.getSelectedItemPosition() == 4) {
            pos = 48;
        } else if (spTenure.getSelectedItemPosition() == 5) {
            pos = 60;
        }

        return pos;
    }

    private void getEmiandProcessingFee() {
        double emi;
         double roi =0;
        if (etLoanAmount.getText().length() == 0  ||  spTenure.getSelectedItemPosition() == 0) {
            return;
        }

        etLoanReq.setText(etLoanAmount.getText().toString());
        double loanAmnt = Double.valueOf(etLoanAmount.getText().toString());

        roi=   ((double) 16/ 12)/100;
        emi = loanAmnt * roi * (Math.pow(1 + roi, getTenure()) / (Math.pow(1 + roi, getTenure()) - 1));

        etQuteEMI.setText(String.valueOf(getDigitPrecision(emi)));
        etProcessingFees.setText(String.valueOf(getDigitPrecision(loanAmnt * 0.02)));

    }

    private void clearQuote()
    {
        etQuteEMI.setText("");
        etProcessingFees.setText("");
        etLoanReq.setText("");
    }


    private double getDigitPrecision(double value) {
        return Double.parseDouble(new DecimalFormat("##.##").format(value));
    }

    //endregion

    // region Event

    //region textwatcher


    TextWatcher loanAmountTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            if (spTenure.getSelectedItemPosition() > 0) {

                if ((s.length() > 5) && (s.length() < 8)) {
                    getEmiandProcessingFee();
                } else {
                    clearQuote();

                }
            } else {
                clearQuote();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //endregion

    //region AutoCompletTextView Event

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

    View.OnFocusChangeListener acCityOFFiceFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {

                String str = acOffCity.getText().toString();

                ListAdapter listAdapter = acOffCity.getAdapter();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    String temp = listAdapter.getItem(i).toString();
                    if (str.compareTo(temp) == 0) {
                        return;
                    }
                }

                acOffCity.setText("");
                acOffCity.setError("Invalid city");
                acOffCity.setFocusable(true);
            } else {
                acOffCity.setError(null);
            }
        }
    };
    //endregion

    //region datepicker
    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogApplicant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, RblpersonalloanActivity.this);
            if (view.getId() == R.id.etDOB) {
                DateTimePicker.showExpressAgeDatePickerRbl(view.getContext(), (Calendar) view.getTag(R.id.etDOB),
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

    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogApplicantjoin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, RblpersonalloanActivity.this);
            if (view.getId() == R.id.etJoin) {
                DateTimePicker.showExpressCurrentDatePicker(view.getContext(), (Calendar) view.getTag(R.id.etJoin),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                //TODO:set tag to DOB -- nilesh
                                //Calendar calSelectedPrev = Calendar.getInstance();

                                calendar.set(year, monthOfYear, dayOfMonth);
                                //calSelectedPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                etJoin.setText(currentDay);
                                //TODO:set tag to DOB -- nilesh
                                etJoin.setTag(R.id.etJoin, calendar);
                                //etDate.setTag(R.id.et_date, calendar.getTime());
                            }
                        });
            }
        }
    };

    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogApplicantLivingSince = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, RblpersonalloanActivity.this);
            if (view.getId() == R.id.etLivingSince) {
                DateTimePicker.showExpressCurrentDatePicker(view.getContext(), (Calendar) view.getTag(R.id.etLivingSince),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                //TODO:set tag to DOB -- nilesh
                                //Calendar calSelectedPrev = Calendar.getInstance();

                                calendar.set(year, monthOfYear, dayOfMonth);
                                //calSelectedPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                etLivingSince.setText(currentDay);
                                //TODO:set tag to DOB -- nilesh
                                etLivingSince.setTag(R.id.etLivingSince, calendar);
                                //etDate.setTag(R.id.et_date, calendar.getTime());
                            }
                        });
            }
        }
    };
    //endregion




    private boolean validateRbl() {
        // region Personal
        if (!isEmpty(etFirstName)) {
            etFirstName.setError("Enter First Name");
            etFirstName.setFocusable(true);
            return false;
        } else {
            etFirstName.setError(null);
        }

        if (!isEmpty(etMiddleName)) {
            etMiddleName.setError("Enter Middle Name");
            etMiddleName.setFocusable(true);
            return false;
        } else {
            etMiddleName.setError(null);
        }

        if (!isEmpty(etLastName)) {
            etLastName.setError("Enter Last Name");
            etLastName.setFocusable(true);
            return false;
        } else {
            etLastName.setError(null);
        }

        if (!isEmpty(etDOB)) {
            etDOB.setError("Enter Date of Birth");
            etDOB.setFocusable(true);
            return false;
        } else {
            etDOB.setError(null);
        }

        if (!isEmpty(etMonthlyIncome)) {
            etMonthlyIncome.setError("Enter Monthly Income");
            etMonthlyIncome.setFocusable(true);
            return false;
        } else {
            etMonthlyIncome.setError(null);
        }

        if((Double.valueOf(etMonthlyIncome.getText().toString()) < 25000))
        {
            showAlert("Monthly Income should be equal or greater than 25 thousands");
            return false;
        }

        if (!isEmpty(etMobile)) {
            etMobile.setError("Enter  Mobile Number");
            etMobile.setFocusable(true);
            return false;
        } else {
            etMobile.setError(null);
        }

        if (etMobile.getText().length() < 10) {
            etMobile.setError("Invalid  Mobile Number");
            etMobile.setFocusable(true);
            return false;
        } else {
            etMobile.setError(null);
        }



        if (!isEmpty(etEmailPers)) {
            etEmailPers.setError("Enter  Email ID");
            etEmailPers.setFocusable(true);
            return false;
        } else {
            etEmailPers.setError(null);
        }

        if (!isValideEmailID(etEmailPers)) {
            etEmailPers.setError("Invalid Email ID");
            etEmailPers.setFocusable(true);
            return false;
        }

        if (spQualification.getSelectedItemPosition() == 0) {
            showAlert("Select Qualification");
            return false;
        }

        if (spResType.getSelectedItemPosition() == 0) {
            showAlert("Select Residence Type");
            return false;
        }

        if (!isEmpty(etLivingSince)) {
            etLivingSince.setError("Enter Living Since");
            etLivingSince.setFocusable(true);
            return false;
        } else {
            etLivingSince.setError(null);
        }


        if (acCity.getText().toString().length() == 0) {
            acCity.setError("Enter City");
            acCity.setFocusable(true);
            return false;
        } else {
            acCity.setError(null);
        }

        if (!isEmpty(etPincode)) {
            etPincode.setError("Enter Pincode");
            etPincode.setFocusable(true);
            return false;
        } else {
            etPincode.setError(null);
        }


        if (etPincode.getText().toString().length() < 6) {
            etPincode.setError("Invalid Pincode");
            etPincode.setFocusable(true);
            return false;
        } else {
            etPincode.setError(null);
        }

        if (!isEmpty(etAddress1)) {
            etAddress1.setError("Enter Address");
            etAddress1.setFocusable(true);
            return false;
        } else {
            etAddress1.setError(null);
        }

        if (!isEmpty(etLandmark)) {
            etLandmark.setError("Enter Landmark");
            etLandmark.setFocusable(true);
            return false;
        } else {
            etLandmark.setError(null);
        }

        if (spSalaried.getSelectedItemPosition() == 0) {
            showAlert("Select Employment And Mode Of Credit");
            return false;
        }



        // endregion

        // region Quote
        if (!isEmpty(etLoanAmount)) {
            etLoanAmount.setError("Enter Loan Amount");
            etLoanAmount.setFocusable(true);
            return false;
        } else {
            etLoanAmount.setError(null);
        }

        double loanAmnt = Double.valueOf(etLoanAmount.getText().toString());
        if (loanAmnt < 100000 || loanAmnt > 2000000) {
            showAlert("Loan amount should be between 1 Lac to 20 Lacs");
        return false;
    }

        if (spTenure.getSelectedItemPosition() == 0) {
            showAlert("Select Tenure");
            return false;
        }

        // endregion

        // region Employment

        if (!isEmpty(etEmployerName)) {
            etEmployerName.setError("Enter Employer Name");
            etEmployerName.setFocusable(true);
            return false;
        } else {
            etEmployerName.setError(null);
        }

        if (!isEmpty(etJoin)) {
            etJoin.setError("Enter Joining Date");
            etJoin.setFocusable(true);
            return false;
        } else {
            etJoin.setError(null);
        }

        if (!isEmpty(etOffAddress1)) {
            etOffAddress1.setError("Enter Address");
            etOffAddress1.setFocusable(true);
            return false;
        } else {
            etOffAddress1.setError(null);
        }

        if (!isEmpty(etOffphoneNo)) {
            etOffphoneNo.setError("Enter Phone No.");
            etOffphoneNo.setFocusable(true);
            return false;
        } else {
            etOffphoneNo.setError(null);
        }


        if (!isEmpty(etTotWorkExp)) {
            etTotWorkExp.setError("Enter Total Work Experience");
            etTotWorkExp.setFocusable(true);
            return false;
        } else {
            etTotWorkExp.setError(null);
        }

        if (acOffCity.getText().toString().length() == 0) {
            acOffCity.setError("Enter City");
            acOffCity.setFocusable(true);
            return false;
        } else {
            acOffCity.setError(null);
        }

        if (!isEmpty(etOffPincode)) {
            etOffPincode.setError("Enter Pincode");
            etOffPincode.setFocusable(true);
            return false;
        } else {
            etOffPincode.setError(null);
        }


        if (etOffPincode.getText().toString().length() < 6) {
            etOffPincode.setError("Invalid Pincode");
            etOffPincode.setFocusable(true);
            return false;
        } else {
            etOffPincode.setError(null);
        }


        if (!isEmpty(etOffPancard)) {
            etOffPancard.setError("Enter PanCard");
            etOffPancard.setFocusable(true);
            return false;
        } else {
            etOffPancard.setError(null);
        }
        if (!isValidPan(etOffPancard)) {
            etOffPancard.setError("Invalid PanCard");
            etOffPancard.setFocusable(true);
            return false;
        } else {
            etOffPancard.setError(null);
        }

        if (spOrgCategory.getSelectedItemPosition() == 0) {
            showAlert("Select Organization Category");
            return false;
        }

        if (!chkRblCondition.isChecked()) {

            showAlert("Accept Terms and Condtion");
            return false;
        }
        // endregion


        return true;
    }

    private void saveRblPersonalLoan() {
        RBLPesonalLoanReqEntity reqEntity = new RBLPesonalLoanReqEntity();

        reqEntity.setFirstName(etFirstName.getText().toString());
        reqEntity.setMiddleName(etMiddleName.getText().toString());
        reqEntity.setLastName(etLastName.getText().toString());
        if (rbmale.isChecked()) {
            reqEntity.setGender("1");
        } else if (rbfemale.isChecked()) {
            reqEntity.setGender("2");
        } else {
            reqEntity.setGender("3");
        }

        reqEntity.setResAddress1(etAddress1.getText().toString());
        reqEntity.setResAddress2(etAddress2.getText().toString());
        reqEntity.setResLand(etLandmark.getText().toString());
        reqEntity.setDOB(etDOB.getText().toString());

        reqEntity.setResType(String.valueOf(spResType.getSelectedItemPosition()));
        reqEntity.setCurResSince(etLivingSince.getText().toString());
        reqEntity.setResPIN(etPincode.getText().toString());
        reqEntity.setMobile(etMobile.getText().toString());

        reqEntity.setEmail(etEmailPers.getText().toString());
        reqEntity.setEmpType(String.valueOf(spSalaried.getSelectedItemPosition()));
        reqEntity.setLnAmt(etLoanAmount.getText().toString());
        reqEntity.setTnrMths((String.valueOf(getTenure())));

        reqEntity.setIRR("16");
        reqEntity.setProcFee(etProcessingFees.getText().toString());
        reqEntity.setNMI(etMonthlyIncome.getText().toString());
        if(etEMI.getText().toString().trim().equals(""))
        {
            reqEntity.setEmiCurPay("0");
        }else {
            reqEntity.setEmiCurPay(etEMI.getText().toString());
        }

        try {
            reqEntity.setResCity(String.valueOf(new DBPersistanceController(this).getRblCityCode(acCity.getText().toString())));
        } catch (Exception e) {
            reqEntity.setResCity("0");
        }

        try {
            reqEntity.setOffCity(String.valueOf(new DBPersistanceController(this).getRblCityCode(acOffCity.getText().toString())));
        } catch (Exception e) {
            reqEntity.setResCity("0");
        }


        reqEntity.setCompanyName(etEmployerName.getText().toString());
        reqEntity.setCurCmpnyJoinDt(etJoin.getText().toString());
        reqEntity.setTotWrkExp(etTotWorkExp.getText().toString());
        reqEntity.setOffAddress1(etOffAddress1.getText().toString());
        reqEntity.setOffAddress2(etOffAddress2.getText().toString());

        reqEntity.setOrgCategory(String.valueOf((spOrgCategory.getSelectedItemPosition())));
        reqEntity.setOffPIN(etOffPincode.getText().toString());
        reqEntity.setOffPhone(etOffphoneNo.getText().toString());
        reqEntity.setPAN(etOffPancard.getText().toString());
        reqEntity.setQualification(String.valueOf((spQualification.getSelectedItemPosition())));
        reqEntity.setCheck("on");


        reqEntity.setBrokerid(new DBPersistanceController(this).getUserData().getLoanId());
        reqEntity.setEmpid("");

        reqEntity.setSource("Finmart");
        reqEntity.setCampaignName("");
        reqEntity.setBankId(String.valueOf(BankID));
        reqEntity.setLoanType(LoanType);
        reqEntity.setFBAID(String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()));

        showDialog();
      //  String json = new Gson().toJson(reqEntity);

      //  Toast.makeText(this,"Data" + json ,Toast.LENGTH_SHORT).show();

         new ExpressLoanController(this).saveRblPersonalLoan(reqEntity,RblpersonalloanActivity.this);


    }



    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSubmit) {

            if (validateRbl()) {
                saveRblPersonalLoan();
            }

        }

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof ExpressRbPersonalResponse) {
            if (response.getStatusNo() == 0) {

                if (((ExpressRbPersonalResponse) response).getMasterData().getReferenceCode().length() > 1) {

                    dialogMessage(true, ((ExpressRbPersonalResponse) response).getMasterData().getReferenceCode(), ((ExpressRbPersonalResponse) response).getMessage());
                } else {
                    dialogMessage(false, "", ((ExpressRbPersonalResponse) response).getMessage());
                }

            //    dialogMessage(true, "", ((ExpressRbPersonalResponse) response).getMessage());

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
    //endregion


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
