package com.datacomp.magicfinmart.creditcard;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
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
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.CCICICIRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CCICICIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CCRblResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ICICICompanyResponse;

public class ICICICreditApplyActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    CardView ccPersonal, ccCompantDetail, ccCurrentAddress, ccContactDetail;
    CheckBox chkTermsCondition, chkSameAsAbove;
    Button btnICICINext;

    TextInputLayout tlCreditLimit, tlMemberSince, tlBank;

    //personal detail
    EditText etFirstName, etLastName, etDOB, etMotherName, etCardName;
    Spinner spNoOfDependents, spSupplementaryCard, spMailingAddress;
    RadioButton rbmale, rbSingle, rbIndian, rbSalaried;

    //Company detail
    AutoCompleteTextView etCompany;
    EditText etDesignation, etWorkEmail, etIncome, etAreaCode, etPhoneNumber, etTotalExp;
    Spinner spQualification, spICICIRelationShip, spTypeCompany;
    RadioButton rbSavingAccYes;
    EditText etICICINumber;

    //current address
    EditText etFlatNo, etBuildingName, etArea, etPincode;
    AutoCompleteTextView acCity, acState;
    Spinner spResidenceType;

    //permanent address
    EditText etPerFlatNo, etPerBuildingName, etPerArea, etPerPincode, etPerEmail;
    AutoCompleteTextView acPerCity, acPerState;
    Spinner spPerResidenceType;

    //contact detail
    EditText etStdCode, etTelephoneNo, etMobileNo, etBankName;
    EditText etMemberSince, etCreditLimit, etPancard;
    Spinner spSalaryAccountType;
    RadioButton rbHaveCC, rbHaveNoCC;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");


    //spinner Adapters
    ArrayAdapter<String> noOfDependentsAdapter, supplemetryCardAdapter, mailingAddressAdapter;

    ArrayAdapter<String> qualificationAdapter, iciciRelationshipAdapter, typeCompanyAdapter;
    ArrayAdapter<String> residenceTypeAdapter, perResidenceTypeAdapter, salaryAccountTypeAdapter;

    ArrayAdapter<String> companyAdapter;

    String[] stateList = {"ANDAMAN-NICOBAR", "ANDHRA PRADESH", "ARUNACHAL PRADESH", "ASSAM", "BIHAR", "CHANDIGARH", "CHHATTISGARH", "DADRA & NAGAR HAVELI", "DAMAN & DIU", "DELHI", "GOA", "GUJARAT", "HARYANA",
            "HIMACHAL PRADESH", "JAMMU KASHMIR", "JHARKHAND", "KARNATAKA", "KERALA", "LAKSHADWEEP", "MADHYA PRADESH", "MAHARASHTRA", "MANIPUR", "MEGHALAYA", "MIZORAM", "NAGALAND", "ORISSA",
            "PONDICHERRY", "PUNJAB", "RAJASTHAN", "SIKKIM", "TAMILNADU", "TRIPURA", "UTTAR PRADESH", "UTTARAKHAND", "WEST BENGAL"};

    ArrayAdapter<String> cityAdapter;
    List<String> cityList;
    ArrayAdapter<String> stateAdapter;

    CCICICIRequestEntity requestEntity;
    CreditCardEntity creditCardEntity;

    ArrayList<String> companyList;
    int EmploymentType = 1;
    TextView txtEmploymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icici_creditapply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ICICI CREDIT CARD");
        creditCardEntity = new CreditCardEntity();

        requestEntity = new CCICICIRequestEntity();

        if (getIntent().getParcelableExtra(CreditCardActivity.SELECTED_CREDIT_CARD) != null) {
            creditCardEntity = getIntent().getParcelableExtra(CreditCardActivity.SELECTED_CREDIT_CARD);
            EmploymentType = getIntent().getIntExtra("EmploymentType", 0);
        }

        companyList = new ArrayList<>();
        cityList = new ArrayList<>();
        cityList = new DBPersistanceController(this).getHealthCity();
        init();
        setListener();
        bindAllSpinner();

        //for validating auto complete city
        acCity.setOnFocusChangeListener(acCityFocusChange);
        acPerCity.setOnFocusChangeListener(acCityFocusChange);

        acState.setOnFocusChangeListener(acStateFocusChange);
        acPerState.setOnFocusChangeListener(acStateFocusChange);


        cityBinding();
        stateBinding();

        etCompany.addTextChangedListener(companyTextWatcher);
        if (EmploymentType == 1)
            txtEmploymentType.setText("Salaried");
        else
            txtEmploymentType.setText("Self");

    }

    TextWatcher companyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 3) {
                new CreditCardController(ICICICreditApplyActivity.this).getICICICompany(s.toString(), ICICICreditApplyActivity.this);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void cityBinding() {
        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        acCity.setAdapter(cityAdapter);
        acCity.setThreshold(1);

        acPerCity.setAdapter(cityAdapter);
        acPerCity.setThreshold(1);
    }

    private void stateBinding() {
        stateAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, stateList);
        acState.setAdapter(stateAdapter);
        acState.setThreshold(1);

        acPerState.setAdapter(stateAdapter);
        acPerState.setThreshold(1);
    }

    View.OnFocusChangeListener acStateFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (view.getId() == R.id.acState) {
                if (!b) {

                    String str = acState.getText().toString();

                    ListAdapter listAdapter = acState.getAdapter();
                    for (int i = 0; i < listAdapter.getCount(); i++) {
                        String temp = listAdapter.getItem(i).toString();
                        if (str.compareTo(temp) == 0) {
                            return;
                        }
                    }

                    acState.setText("");
                    acState.setError("Invalid State");
                    acState.setFocusable(true);
                } else {
                    acState.setError(null);
                }
            } else if (view.getId() == R.id.acPerState) {
                if (!b) {

                    String str = acPerState.getText().toString();

                    ListAdapter listAdapter = acPerState.getAdapter();
                    for (int i = 0; i < listAdapter.getCount(); i++) {
                        String temp = listAdapter.getItem(i).toString();
                        if (str.compareTo(temp) == 0) {
                            return;
                        }
                    }

                    acPerState.setText("");
                    acPerState.setError("Invalid State");
                    acPerState.setFocusable(true);
                } else {
                    acPerState.setError(null);
                }
            }
        }
    };

    View.OnFocusChangeListener acCityFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (view.getId() == R.id.acCity) {
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
            } else if (view.getId() == R.id.acPerCity) {
                if (!b) {

                    String str = acPerCity.getText().toString();

                    ListAdapter listAdapter = acPerCity.getAdapter();
                    for (int i = 0; i < listAdapter.getCount(); i++) {
                        String temp = listAdapter.getItem(i).toString();
                        if (str.compareTo(temp) == 0) {
                            return;
                        }
                    }

                    acPerCity.setText("");
                    acPerCity.setError("Invalid city");
                    acPerCity.setFocusable(true);
                } else {
                    acPerCity.setError(null);
                }
            }
        }
    };

    private void bindAllSpinner() {

        //region personal
        noOfDependentsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.NoOfDependents)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.NoOfDependents);
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
        spNoOfDependents.setAdapter(noOfDependentsAdapter);

        supplemetryCardAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Supplimentary_card)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.Supplimentary_card);
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
        spSupplementaryCard.setAdapter(supplemetryCardAdapter);

        mailingAddressAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Mailing_Address)) {


            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.Mailing_Address);
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
        spMailingAddress.setAdapter(mailingAddressAdapter);

        //endregion

        qualificationAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.icici_qualification)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.icici_qualification);
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
        spQualification.setAdapter(qualificationAdapter);

        iciciRelationshipAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.icici_relationship)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.icici_relationship);
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
        spICICIRelationShip.setAdapter(iciciRelationshipAdapter);

        typeCompanyAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.icici_type_of_company)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.icici_type_of_company);
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
        spTypeCompany.setAdapter(typeCompanyAdapter);


        residenceTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.icici_residence_type)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.icici_residence_type);
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
        spResidenceType.setAdapter(residenceTypeAdapter);

        perResidenceTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.icici_residence_type)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.icici_residence_type);
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
        spPerResidenceType.setAdapter(perResidenceTypeAdapter);

        salaryAccountTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.icici_salary_account_opened)) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(ICICICreditApplyActivity.this);
                    convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
                }
                // android.R.id.text1 is default text view in resource of the android.
                // android.R.layout.simple_spinner_item is default layout in resources of android.

                TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
                String[] items = getResources().getStringArray(R.array.icici_salary_account_opened);
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
        spSalaryAccountType.setAdapter(salaryAccountTypeAdapter);
    }

    private void setListener() {
        etDOB.setOnClickListener(datePickerDialog);
        etMemberSince.setOnClickListener(datePickerDialog);
        btnICICINext.setOnClickListener(this);

        spICICIRelationShip.setOnItemSelectedListener(onItemSelectedListener);

        chkSameAsAbove.setOnCheckedChangeListener(addressSameAsAbove);

        rbHaveCC.setOnCheckedChangeListener(haveCC);
    }

    CompoundButton.OnCheckedChangeListener addressSameAsAbove = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                bindAddress();
            } else {

                etPerFlatNo.setText("");

                etPerBuildingName.setText("");
                etPerArea.setText("");
                etPerPincode.setText("");

                acPerCity.setText("");
                acPerState.setText("");
                spPerResidenceType.setSelection(0);
            }
        }
    };

    CompoundButton.OnCheckedChangeListener haveCC = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                tlCreditLimit.setVisibility(View.VISIBLE);
                tlMemberSince.setVisibility(View.VISIBLE);
                tlBank.setVisibility(View.VISIBLE);
                etBankName.setVisibility(View.VISIBLE);
                etMemberSince.setVisibility(View.VISIBLE);
                etCreditLimit.setVisibility(View.VISIBLE);
                rbHaveNoCC.setChecked(false);
            } else {
                tlCreditLimit.setVisibility(View.GONE);
                tlMemberSince.setVisibility(View.GONE);
                tlBank.setVisibility(View.GONE);
                etBankName.setVisibility(View.GONE);
                etMemberSince.setVisibility(View.GONE);
                etCreditLimit.setVisibility(View.GONE);
                rbHaveNoCC.setChecked(true);
            }
        }
    };

    protected void bindAddress() {
        etPerFlatNo.setText(etFlatNo.getText().toString());

        etPerBuildingName.setText(etBuildingName.getText().toString());
        etPerArea.setText(etArea.getText().toString());
        etPerPincode.setText(etPincode.getText().toString());

        acPerCity.setText(acCity.getText().toString());
        acPerCity.performCompletion();
        acPerState.setText(acState.getText().toString());
        acPerState.performCompletion();


        spPerResidenceType.setSelection(spResidenceType.getSelectedItemPosition());

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            if (position == 1) {
                etICICINumber.setVisibility(View.VISIBLE);
            } else {
                etICICINumber.setVisibility(View.GONE);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    private void init() {
        txtEmploymentType = findViewById(R.id.txtEmploymentType);
        tlCreditLimit = (TextInputLayout) findViewById(R.id.tlCreditLimit);
        tlMemberSince = (TextInputLayout) findViewById(R.id.tlMemberSince);
        tlBank = (TextInputLayout) findViewById(R.id.tlBank);

        ccPersonal = (CardView) findViewById(R.id.ccPersonal);
        ccCompantDetail = (CardView) findViewById(R.id.ccCompantDetail);
        ccCurrentAddress = (CardView) findViewById(R.id.ccCurrentAddress);
        ccContactDetail = (CardView) findViewById(R.id.ccContactDetail);
        chkTermsCondition = (CheckBox) findViewById(R.id.chkTermsCondition);
        chkSameAsAbove = (CheckBox) findViewById(R.id.chkSameAsAbove);
        btnICICINext = (Button) findViewById(R.id.btnICICINext);
        btnICICINext.setOnClickListener(this);

        //region personal detail
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etMotherName = (EditText) findViewById(R.id.etMotherName);
        etCardName = (EditText) findViewById(R.id.etCardName);


        spNoOfDependents = (Spinner) findViewById(R.id.spNoOfDependents);
        spSupplementaryCard = (Spinner) findViewById(R.id.spSupplementaryCard);
        spMailingAddress = (Spinner) findViewById(R.id.spMailingAddress);

        rbmale = (RadioButton) findViewById(R.id.rbmale);
        rbSingle = (RadioButton) findViewById(R.id.rbSingle);
        rbIndian = (RadioButton) findViewById(R.id.rbIndian);
        rbSalaried = (RadioButton) findViewById(R.id.rbSalaried);
        //endregion

        //region company detail

        etDesignation = (EditText) findViewById(R.id.etDesignation);
        etWorkEmail = (EditText) findViewById(R.id.etWorkEmail);
        etIncome = (EditText) findViewById(R.id.etIncome);
        etAreaCode = (EditText) findViewById(R.id.etAreaCode);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etTotalExp = (EditText) findViewById(R.id.etTotalExp);
        etICICINumber = (EditText) findViewById(R.id.etICICINumber);
        etICICINumber.setVisibility(View.GONE);
        spQualification = (Spinner) findViewById(R.id.spQualification);
        spICICIRelationShip = (Spinner) findViewById(R.id.spICICIRelationShip);
        spTypeCompany = (Spinner) findViewById(R.id.spTypeCompany);

        rbSavingAccYes = (RadioButton) findViewById(R.id.rbSavingAccYes);
        //endregion

        //region current address

        etFlatNo = (EditText) findViewById(R.id.etFlatNo);
        etBuildingName = (EditText) findViewById(R.id.etBuildingName);
        etArea = (EditText) findViewById(R.id.etArea);
        etPincode = (EditText) findViewById(R.id.etPincode);


        etCompany = (AutoCompleteTextView) findViewById(R.id.etCompany);
        etCompany.setThreshold(3);//will start working from first character

        acCity = (AutoCompleteTextView) findViewById(R.id.acCity);
        acState = (AutoCompleteTextView) findViewById(R.id.acState);
        spResidenceType = (Spinner) findViewById(R.id.spResidenceType);
        //endregion

        //region permanent address

        etPerFlatNo = (EditText) findViewById(R.id.etPerFlatNo);
        etPerBuildingName = (EditText) findViewById(R.id.etPerBuildingName);
        etPerArea = (EditText) findViewById(R.id.etPerArea);
        etPerPincode = (EditText) findViewById(R.id.etPerPincode);
        etPerEmail = (EditText) findViewById(R.id.etPerEmail);
        acPerCity = (AutoCompleteTextView) findViewById(R.id.acPerCity);
        acPerState = (AutoCompleteTextView) findViewById(R.id.acPerState);
        spPerResidenceType = (Spinner) findViewById(R.id.spPerResidenceType);

        //endregion

        //region contact detail

        etStdCode = (EditText) findViewById(R.id.etStdCode);
        etTelephoneNo = (EditText) findViewById(R.id.etTelephoneNo);
        etMobileNo = (EditText) findViewById(R.id.etMobileNo);
        etBankName = (EditText) findViewById(R.id.etBankName);
        etMemberSince = (EditText) findViewById(R.id.etMemberSince);
        etCreditLimit = (EditText) findViewById(R.id.etCreditLimit);
        etPancard = (EditText) findViewById(R.id.etPancard);
        etPancard.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});

        etBankName.setVisibility(View.GONE);
        etMemberSince.setVisibility(View.GONE);
        etCreditLimit.setVisibility(View.GONE);

        spSalaryAccountType = (Spinner) findViewById(R.id.spSalaryAccountType);

        rbHaveCC = (RadioButton) findViewById(R.id.rbHaveCC);
        rbHaveNoCC = (RadioButton) findViewById(R.id.rbHaveNoCC);
        rbHaveNoCC.setChecked(true);
        //endregion
    }

    //region datepicker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.etDOB) {
                DateTimePicker.showIciciCreditCardDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etDOB.setText(currentDay);
                        }
                    }
                });
            } else if (view.getId() == R.id.etMemberSince) {
                DateTimePicker.showDatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etMemberSince.setText(currentDay);
                        }
                    }
                });
            }

        }
    };

    //endregion

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnICICINext) {

            if (chkTermsCondition.isChecked()) {

                //region validation personal

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


                if (!isEmpty(etDOB)) {
                    etDOB.setError("Invalid birth date");
                    etDOB.setFocusable(true);
                    return;
                } else {
                    etDOB.setError(null);
                }


                if (!isEmpty(etMotherName)) {
                    etMotherName.setError("Enter Mother name");
                    etMotherName.setFocusable(true);
                    return;
                } else {
                    etMotherName.setError(null);
                }


                if (!isEmpty(etCardName)) {
                    etCardName.setError("Enter Card name");
                    etCardName.setFocusable(true);
                    return;
                } else {
                    etCardName.setError(null);
                }


                if (spNoOfDependents.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select No of Dependents", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spSupplementaryCard.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Supplementary card", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spMailingAddress.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Mailing address", Toast.LENGTH_SHORT).show();
                    return;
                }
                //endregion

                //region validation company

                if (!isEmpty(etCompany)) {
                    etCompany.setError("Enter Company name");
                    etCompany.setFocusable(true);
                    return;
                } else {
                    etCompany.setError(null);
                }


                if (!isEmpty(etDesignation)) {
                    etDesignation.setError("Enter Designation");
                    etDesignation.setFocusable(true);
                    return;
                } else {
                    etDesignation.setError(null);
                }

                if (!isValideEmailID(etWorkEmail)) {
                    etWorkEmail.setError("Invalid Email ID");
                    etWorkEmail.setFocusable(true);
                    return;
                }

                if (!isEmpty(etIncome)) {
                    etIncome.setError("Enter Income");
                    etIncome.setFocusable(true);
                    return;
                } else {
                    etIncome.setError(null);
                }


                if (!isEmpty(etAreaCode)) {
                    etAreaCode.setError("Enter Area code");
                    etAreaCode.setFocusable(true);
                    return;
                } else {
                    etAreaCode.setError(null);
                }


                if (!isEmpty(etPhoneNumber)) {
                    etPhoneNumber.setError("Invalid Mobile number");
                    etPhoneNumber.setFocusable(true);
                    return;
                } else {
                    etPhoneNumber.setError(null);
                }


                if (!isEmpty(etTotalExp)) {
                    etTotalExp.setError("Enter Total Experience");
                    etTotalExp.setFocusable(true);
                    return;
                } else {
                    etTotalExp.setError(null);
                }


                if (spQualification.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Qualification", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spICICIRelationShip.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select ICICI Relationship", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spTypeCompany.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Type of company", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spICICIRelationShip.getSelectedItemPosition() == 1) {
                    if (!isEmpty(etICICINumber)) {
                        etICICINumber.setError("Enter ICICI Relation number");
                        etICICINumber.setFocusable(true);
                        return;
                    }

                    if (etICICINumber.getText().toString().length() < 13) {
                        etICICINumber.setError("Invalid ICICI Relation number");
                        etICICINumber.setFocusable(true);
                        return;
                    }
                }
                //endregion

                //region validation current address


                if (!isEmpty(etFlatNo)) {
                    etFlatNo.setError("Enter Flat No");
                    etFlatNo.setFocusable(true);
                    return;
                } else {
                    etFlatNo.setError(null);
                }


                if (!isEmpty(etBuildingName)) {
                    etBuildingName.setError("Enter Building Name");
                    etBuildingName.setFocusable(true);
                    return;
                } else {
                    etBuildingName.setError(null);
                }
                if (!isEmpty(etArea)) {
                    etArea.setError("Enter Area");
                    etArea.setFocusable(true);
                    return;
                } else {
                    etArea.setError(null);
                }


                if (etPincode.getText().toString().length() < 6) {
                    etPincode.setError("Invalid Pincode");
                    etPincode.setFocusable(true);
                    return;
                } else {
                    etPincode.setError(null);
                }


                if (acCity.getText().toString().length() == 0) {
                    acCity.setError("Invalid City");
                    acCity.setFocusable(true);
                    return;
                } else {
                    acCity.setError(null);
                }


                if (acState.getText().toString().length() == 0) {
                    acState.setError("Invalid State");
                    acState.setFocusable(true);
                    return;
                } else {
                    acState.setError(null);
                }


                if (spResidenceType.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Residence Type", Toast.LENGTH_SHORT).show();
                    return;
                }


                //endregion

                //region validation permanent address


                if (!isEmpty(etPerFlatNo)) {
                    etPerFlatNo.setError("Enter Flat No");
                    etPerFlatNo.setFocusable(true);
                    return;
                } else {
                    etPerFlatNo.setError(null);
                }


                if (!isEmpty(etPerBuildingName)) {
                    etPerBuildingName.setError("Enter Building Name");
                    etPerBuildingName.setFocusable(true);
                    return;
                } else {
                    etPerBuildingName.setError(null);
                }

                if (!isEmpty(etPerArea)) {
                    etPerArea.setError("Enter Area");
                    etPerArea.setFocusable(true);
                    return;
                } else {
                    etPerArea.setError(null);
                }

                if (etPerPincode.getText().toString().length() < 6) {
                    etPerPincode.setError("Invalid Pincode");
                    etPerPincode.setFocusable(true);
                    return;
                } else {
                    etPerPincode.setError(null);
                }


                if (acPerCity.getText().toString().length() == 0) {
                    acPerCity.setError("Invalid City");
                    acPerCity.setFocusable(true);
                    return;
                } else {
                    acPerCity.setError(null);
                }


                if (acPerState.getText().toString().length() == 0) {
                    acPerState.setError("Invalid State");
                    acPerState.setFocusable(true);
                    return;
                } else {
                    acPerState.setError(null);
                }


                if (spPerResidenceType.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Residence Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValideEmailID(etPerEmail)) {
                    etPerEmail.setError("Invalid Email ID");
                    etPerEmail.setFocusable(true);
                    return;
                }

                //endregion

                //region validation contact Detail


                if (!isEmpty(etStdCode)) {
                    etStdCode.setError("Enter Std Code");
                    etStdCode.setFocusable(true);
                    return;
                } else {
                    etStdCode.setError(null);
                }


                if (!isEmpty(etTelephoneNo)) {
                    etTelephoneNo.setError("Enter telephone No");
                    etTelephoneNo.setFocusable(true);
                    return;
                } else {
                    etTelephoneNo.setError(null);
                }


                if (!isValidePhoneNumber(etMobileNo)) {
                    etMobileNo.setError("Invalid Mobile Number");
                    etMobileNo.setFocusable(true);
                    return;
                } else {
                    etMobileNo.setError(null);
                }


                if (rbHaveCC.isChecked()) {
                    if (!isEmpty(etBankName)) {
                        etBankName.setError("Enter Bank Name");
                        etBankName.setFocusable(true);
                        return;
                    } else {
                        etBankName.setError(null);
                    }


                    if (!isEmpty(etCreditLimit)) {
                        etCreditLimit.setError("Enter Credit Limit");
                        etCreditLimit.setFocusable(true);
                        return;
                    } else {
                        etCreditLimit.setError(null);
                    }


                    if (!isEmpty(etMemberSince)) {
                        etMemberSince.setError("Enter Member since");
                        etMemberSince.setFocusable(true);
                        return;
                    } else {
                        etMemberSince.setError(null);
                    }
                }

                if (!isValidPan(etPancard)) {
                    etPancard.setError("Invalid Pan card");
                    etPancard.setFocusable(true);
                    return;
                } else {
                    etPancard.setError(null);
                }

                if (spSalaryAccountType.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Salary Account Type", Toast.LENGTH_SHORT).show();
                    return;
                }


                //endregion

                //region creating request

                requestEntity.setCreditCardDetailId(creditCardEntity.getCreditCardDetailId());
                requestEntity.setFba_id(new DBPersistanceController(this).getUserData().getFBAId());
                requestEntity.setBrokerid(new DBPersistanceController(this).getUserData().getLoanId());
                requestEntity.setProd(creditCardEntity.getRBID());
                requestEntity.setAmount(creditCardEntity.getAmount());
                requestEntity.setInterest(creditCardEntity.getCreditCardType());

                //personal detail
                requestEntity.setPreferred_address(spMailingAddress.getSelectedItem().toString());
                requestEntity.setSupplementary_card(spSupplementaryCard.getSelectedItem().toString());
                requestEntity.setNo_of_dependents(spNoOfDependents.getSelectedItem().toString());
                requestEntity.setNameOnCard(etCardName.getText().toString());
                requestEntity.setMotherName(etMotherName.getText().toString());
                requestEntity.setDateOfBirth(etDOB.getText().toString());
                requestEntity.setApplicantFirstName(etFirstName.getText().toString());
                requestEntity.setApplicantLastName(etLastName.getText().toString());
                requestEntity.setApplicantMiddleName("");

                //company
                requestEntity.setICICIBankRelationship(spICICIRelationShip.getSelectedItem().toString());
                requestEntity.setTotal_Exp(etTotalExp.getText().toString());
                requestEntity.setWork_number(etPhoneNumber.getText().toString());
                requestEntity.setWork_STDCode(etAreaCode.getText().toString());
                requestEntity.setIncome(etIncome.getText().toString());
                requestEntity.setCompanyName(etCompany.getText().toString());
                requestEntity.setDesignation(etDesignation.getText().toString());
                requestEntity.setWork_email(etWorkEmail.getText().toString());
                requestEntity.setHighest_education(spQualification.getSelectedItem().toString());
                requestEntity.setType_of_company(spTypeCompany.getSelectedItem().toString());
                requestEntity.setICICIRelationshipNumber(etICICINumber.getText().toString());

                //address
                requestEntity.setResidenceState(acState.getText().toString());
                requestEntity.setCity(acCity.getText().toString());
                requestEntity.setResidencePincode(etPincode.getText().toString());
                requestEntity.setResidenceAddress2(etBuildingName.getText().toString());
                requestEntity.setResidenceAddress1(etFlatNo.getText().toString());
                requestEntity.setResidenceAddress3(etArea.getText().toString());

                requestEntity.setType_current(spResidenceType.getSelectedItem().toString());
                requestEntity.setSame("on");
                requestEntity.setType("DC");
                requestEntity.setTerms("on");

                //permanant
                requestEntity.setPer_res_type(spPerResidenceType.getSelectedItem().toString());
                requestEntity.setPerCity(acPerCity.getText().toString());
                requestEntity.setPerResidenceAddress1(etPerFlatNo.getText().toString());
                requestEntity.setPerResidenceAddress2(etPerBuildingName.getText().toString());
                requestEntity.setPerResidenceAddress3(etPerArea.getText().toString());
                requestEntity.setPerResidencePincode(etPerPincode.getText().toString());
                requestEntity.setPerResidenceState(acPerState.getText().toString());
                requestEntity.setEmail_id(etPerEmail.getText().toString());
                //contact
                requestEntity.setResidenceMobileNo(etMobileNo.getText().toString());
                requestEntity.setSTDCode(etStdCode.getText().toString());
                requestEntity.setResidencePhoneNumber(etTelephoneNo.getText().toString());
                requestEntity.setPrevious_bank(etBankName.getText().toString());
                requestEntity.setCredit_limit(etCreditLimit.getText().toString());
                requestEntity.setCredit_date(etMemberSince.getText().toString());
                requestEntity.setPanNo(etPancard.getText().toString());

                if (spSalaryAccountType.getSelectedItemPosition() == 1) {
                    requestEntity.setSalaryAccountOpened("Above2Months");
                } else {
                    requestEntity.setSalaryAccountOpened("Below2Months");
                }

                requestEntity.setChannelType("RupeeBoss");
                requestEntity.setCampaignName("Rupeeboss Online");

                if (rbmale.isChecked()) {
                    requestEntity.setGender("Male");
                } else {
                    requestEntity.setGender("Female");
                }

                if (rbSingle.isChecked()) {
                    requestEntity.setMarital_status("Single");
                } else {
                    requestEntity.setMarital_status("Married");
                }

                if (rbIndian.isChecked()) {
                    requestEntity.setResident_status("Indian");
                } else {
                    requestEntity.setResident_status("NRI/Foreign National");
                }

                if (rbSalaried.isChecked()) {
                    requestEntity.setCustomerProfile("Salaried");
                } else {
                    requestEntity.setCustomerProfile("Selfemployed");
                }

                if (rbSavingAccYes.isChecked()) {
                    requestEntity.setSalaryAccountWithOtherBank("Yes");
                } else {
                    requestEntity.setSalaryAccountWithOtherBank("No");
                }

                if (rbHaveCC.isChecked()) {
                    requestEntity.setHave_credit_card("Yes");
                } else {
                    requestEntity.setHave_credit_card("No");
                }

                //endregion

                showDialog();
                new CreditCardController(this).applyICICI(requestEntity, this);
            } else {
                Toast.makeText(this, "Accept Terms and Condtion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof CCICICIResponse) {
            if (response.getStatusNo() == 0) {
                if (((CCICICIResponse) response).getMasterData().getApplicationId().length() > 1) {
                    dialogMessage(true, ((CCICICIResponse) response).getMasterData().getApplicationId(), response.getMessage(), ((CCICICIResponse) response).getMasterData().getDecision());
                } else {
                    dialogMessage(false, "", ((CCRblResponse) response).getMessage(), ((CCICICIResponse) response).getMasterData().getDecision());
                }
            }
        } else if (response instanceof ICICICompanyResponse) {

            if (((ICICICompanyResponse) response).getResult() != null)
                companyList.clear();
            for (int i = 0; i < ((ICICICompanyResponse) response).getResult().size(); i++) {
                companyList.add(((ICICICompanyResponse) response).getResult().get(i).getCompany_name());

            }
            companyAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.select_dialog_item, companyList) {

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    if (convertView == null) {
                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        convertView = inflater.inflate(
                                android.R.layout.simple_spinner_item, parent, false);
                    }


                    TextView tv = (TextView) convertView
                            .findViewById(android.R.id.text1);
                    tv.setText(companyList.get(position));
                    tv.setPadding(8, 4, 8, 4);
                    tv.setTextColor(Color.BLACK);
                    tv.setTextSize(13.5f);
                    return convertView;
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;

                    tv.setTextColor(Color.BLACK);

                    return view;
                }
            };
            etCompany.setAdapter(companyAdapter);
            //companyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        dialogMessage(false, t.getMessage(), "", "");
    }

    private void dialogMessage(final boolean isSuccess, String AppNo, String displayMessage, String Decision) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        StringBuilder Message = new StringBuilder();
        if (isSuccess) {
            builder.setTitle("Applied Successfully..!");
            String strMessage = "Application No: " + AppNo + "\n\n";
            String DescText = "<b>" + Decision + "</b> ";
            String Desc = "Decision: " + Html.fromHtml(DescText) + "\n\n";
            String success = displayMessage;
            Message.append(strMessage + Desc + success);

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
}
