package com.datacomp.magicfinmart.onlineexpressloan;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller.ExpressLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.KotakPersonalSaveRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.KotakPLEmployerNameResponse;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.KotakROICalResponse;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.kotakPers_SaveResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

public class KotakpersonalloanActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    PrefManager prefManager;
    DBPersistanceController dbPersistanceController;
    LinearLayout llCoApplicantDetail;
    CardView ccPersonal, ccCompantDetail, ccCurrentAddress, ccContactDetail;
    CheckBox chkSameAsAbove, chkTermsCondition;
    Button btnKotakplNext, btnROICalc;

    TextInputLayout tlProcessingFees, tlPLRateOff;

    //personal detail
    EditText etFirstName, etLastName, etDOB, etPartyId, etMiddleName, etMobileNumber, etEmail, etAadharNumber, etPanCard, etCRNNumber;
    Spinner spExistingCustomer, spCustomerTypeMaster, spQualif;
    RadioButton rbmale, rbfemale;
    RadioGroup rgGender, rgCoApp;

    //Company detail
    EditText etTotalExp, etJoiningDate, etTotalWorkExp, etOfficeAddress1, etOfficeAddress2, etOfficeAddress3, etOfficePincode, etOfficePhone;
    Spinner spEmployementType, spPreferredMailingAddress;
    AutoCompleteTextView acEmployerName;
    ArrayAdapter<String> employerNAmeAdapter;


    //current address
    EditText etAdd1, etAdd2, etAdd3, etCurrentResidence, etResidencePhnNo, etPincode;

    //permanent address
    EditText etPerAdd1, etPerAdd2, etPerAdd3, etPerPincode, etPerResidencePhnNo;

    Spinner spPerResidenceType;

    //contact detail
    EditText etNetMonthIncome, etTotalEMIperMonth, etReqLoanAmnt, etPLRateOff, etProcessingFees;
    EditText etMemberSince, etPancard;
    Spinner spSalaryAccountType, spReqLoanTenure;
    RadioButton rbYes, rbNo;

    //Co-Applicant
    EditText etCoAppEmployerName, etCoAppNrtMonthlyIncome, etCoAppEMICurrentlyPay, etCoAppDOB;
    Spinner spRelationshipWithCoApp, spCoAppEmpType;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    Spinner spResidenceType, spCurrentAddressCity, spOfficeCity, spPerCity;

    //spinner Adapters
    ArrayAdapter<String> CurrentAddressCityAdapter, OfficeCityAdapter, PerCityAdapter;


    List<String> CurrentAddressCityList, OfficecityList, PercityList;

    KotakPersonalSaveRequestEntity requestEntity;
    int BankID = 0;
    String LoanType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kotakpersonalloan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestEntity = new KotakPersonalSaveRequestEntity();
        dbPersistanceController = new DBPersistanceController(this);
        prefManager = new PrefManager(this);
        if (prefManager.IsEmployerNAmeUpdate()) {
            showDialog();
            new ExpressLoanController(this).getKotakPlEmployerName(this);
        }


        init();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            BankID = extras.getInt("BANK_ID", 0);
            LoanType = extras.getString("LOAN_TYPE", "");
            //The key argument here must match that used in the other activity
        }
        setListener();


        CurrentAddressCityList = new ArrayList<>();
        CurrentAddressCityList = new DBPersistanceController(this).getKotakPLCityList();

        OfficecityList = new ArrayList<>();
        OfficecityList = new DBPersistanceController(this).getKotakPLCityList();

        PercityList = new ArrayList<>();
        PercityList = new DBPersistanceController(this).getKotakPLCityList();

        branchBinding();
    }

    private void init() {

        tlProcessingFees = (TextInputLayout) findViewById(R.id.tlProcessingFees);
        tlPLRateOff = (TextInputLayout) findViewById(R.id.tlPLRateOff);

        llCoApplicantDetail = (LinearLayout) findViewById(R.id.llCoApplicantDetail);
        llCoApplicantDetail.setVisibility(View.GONE);
        ccPersonal = (CardView) findViewById(R.id.ccPersonal);
        ccCompantDetail = (CardView) findViewById(R.id.ccCompantDetail);
        ccCurrentAddress = (CardView) findViewById(R.id.ccCurrentAddress);
        ccContactDetail = (CardView) findViewById(R.id.ccContactDetail);

        chkTermsCondition = (CheckBox) findViewById(R.id.chkTermsCondition);
        chkSameAsAbove = (CheckBox) findViewById(R.id.chkSameAsAbove);
        btnKotakplNext = (Button) findViewById(R.id.btnKotakplNext);

        btnROICalc = (Button) findViewById(R.id.btnROICalc);


        //region personal detail
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etDOB = (EditText) findViewById(R.id.etDOB);
        etPartyId = (EditText) findViewById(R.id.etPartyId);
        etPartyId.setVisibility(View.GONE);
        etMiddleName = (EditText) findViewById(R.id.etMiddleName);
        etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAadharNumber = (EditText) findViewById(R.id.etAadharNumber);

        etCRNNumber = (EditText) findViewById(R.id.etCRNNumber);
        etCRNNumber.setVisibility(View.GONE);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        rgCoApp = (RadioGroup) findViewById(R.id.rgCoApp);
        rbmale = (RadioButton) findViewById(R.id.rbmale);

        rbfemale = (RadioButton) findViewById(R.id.rbfemale);
        spExistingCustomer = (Spinner) findViewById(R.id.spExistingCustomer);
        spCustomerTypeMaster = (Spinner) findViewById(R.id.spCustomerTypeMaster);
        //endregion

        //region company detail


        etTotalExp = (EditText) findViewById(R.id.etTotalExp);

        acEmployerName = (AutoCompleteTextView) findViewById(R.id.acEmployerName);
        etJoiningDate = (EditText) findViewById(R.id.etJoiningDate);
        etTotalWorkExp = (EditText) findViewById(R.id.etTotalWorkExp);
        etOfficeAddress1 = (EditText) findViewById(R.id.etOfficeAddress1);
        etOfficeAddress2 = (EditText) findViewById(R.id.etOfficeAddress2);
        etOfficeAddress3 = (EditText) findViewById(R.id.etOfficeAddress3);
        etOfficePincode = (EditText) findViewById(R.id.etOfficePincode);
        etOfficePhone = (EditText) findViewById(R.id.etOfficePhone);
        spEmployementType = (Spinner) findViewById(R.id.spEmployementType);
        spOfficeCity = (Spinner) findViewById(R.id.spOfficeCity);
        spPreferredMailingAddress = (Spinner) findViewById(R.id.spPreferredMailingAddress);

        spQualif = (Spinner) findViewById(R.id.spQualif);

        //endregion

        //region current address


        etPincode = (EditText) findViewById(R.id.etPincode);
        etAdd1 = (EditText) findViewById(R.id.etAdd1);
        etAdd2 = (EditText) findViewById(R.id.etAdd2);
        etAdd3 = (EditText) findViewById(R.id.etAdd3);
        etCurrentResidence = (EditText) findViewById(R.id.etCurrentResidence);
        etResidencePhnNo = (EditText) findViewById(R.id.etResidencePhnNo);

        // acCity = (AutoCompleteTextView) findViewById(R.id.acCity);
        //  acState = (AutoCompleteTextView) findViewById(R.id.acState);
        spResidenceType = (Spinner) findViewById(R.id.spResidenceType);
        //endregion

        //region permanent address


        etPerAdd1 = (EditText) findViewById(R.id.etPerAdd1);
        etPerAdd2 = (EditText) findViewById(R.id.etPerAdd2);
        etPerAdd3 = (EditText) findViewById(R.id.etPerAdd3);
        etPerPincode = (EditText) findViewById(R.id.etPerPincode);
        etPerResidencePhnNo = (EditText) findViewById(R.id.etPerResidencePhnNo);


        //   acPerCity = (AutoCompleteTextView) findViewById(R.id.acPerCity);
        //    acPerState = (AutoCompleteTextView) findViewById(R.id.acPerState);
        spPerResidenceType = (Spinner) findViewById(R.id.spPerResidenceType);

        //endregion

        //region contact detail


        etNetMonthIncome = (EditText) findViewById(R.id.etNetMonthIncome);
        etTotalEMIperMonth = (EditText) findViewById(R.id.etTotalEMIperMonth);
        etReqLoanAmnt = (EditText) findViewById(R.id.etReqLoanAmnt);
        etPLRateOff = (EditText) findViewById(R.id.etPLRateOff);
        etProcessingFees = (EditText) findViewById(R.id.etProcessingFees);
        etPanCard = (EditText) findViewById(R.id.etPanCard);
        spReqLoanTenure = (Spinner) findViewById(R.id.spReqLoanTenure);


        rbYes = (RadioButton) findViewById(R.id.rbYes);

        rbNo = (RadioButton) findViewById(R.id.rbNo);


        spSalaryAccountType = (Spinner) findViewById(R.id.spSalaryAccountType);


        //endregion
        //region Co-Applicant


        etCoAppEmployerName = (EditText) findViewById(R.id.etCoAppEmployerName);
        etCoAppNrtMonthlyIncome = (EditText) findViewById(R.id.etCoAppNrtMonthlyIncome);
        etCoAppEMICurrentlyPay = (EditText) findViewById(R.id.etCoAppEMICurrentlyPay);
        spRelationshipWithCoApp = (Spinner) findViewById(R.id.spRelationshipWithCoApp);
        spCoAppEmpType = (Spinner) findViewById(R.id.spCoAppEmpType);
        etCoAppDOB = (EditText) findViewById(R.id.etCoAppDOB);
        spCurrentAddressCity = (Spinner) findViewById(R.id.spCurrentAddressCity);
        spPerCity = (Spinner) findViewById(R.id.spPerCity);
    }


    private void setListener() {
        etDOB.setOnClickListener(datePickerDialog);
        etJoiningDate.setOnClickListener(datePickerDialog);
        etCurrentResidence.setOnClickListener(datePickerDialog);
        etCoAppDOB.setOnClickListener(datePickerDialog);
        btnKotakplNext.setOnClickListener(this);
        btnROICalc.setOnClickListener(this);
        spExistingCustomer.setOnItemSelectedListener(onItemSelectedListener);
        spCustomerTypeMaster.setOnItemSelectedListener(onItemSelectedListener);

        chkSameAsAbove.setOnCheckedChangeListener(addressSameAsAbove);
        rbYes.setOnCheckedChangeListener(haveCC);
    }

    private void branchBinding() {
        CurrentAddressCityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, CurrentAddressCityList);
        spCurrentAddressCity.setAdapter(CurrentAddressCityAdapter);

        OfficeCityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, OfficecityList);
        spOfficeCity.setAdapter(OfficeCityAdapter);


        PerCityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, PercityList);
        spPerCity.setAdapter(PerCityAdapter);

        employerNAmeAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, dbPersistanceController.getEmplyerNAmeList());
        acEmployerName.setAdapter(employerNAmeAdapter);
        acEmployerName.setThreshold(1);

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            if (adapterView.getId() == R.id.spExistingCustomer) {
                if (position == 1) {
                    //   etICICINumber.setVisibility(View.VISIBLE);
                    spCustomerTypeMaster.setVisibility(View.VISIBLE);
                    etCRNNumber.setVisibility(View.GONE);
                    etPartyId.setVisibility(View.GONE);
                } else {
                    //  etICICINumber.setVisibility(View.GONE);
                    spCustomerTypeMaster.setVisibility(View.GONE);
                    etCRNNumber.setVisibility(View.GONE);
                    etPartyId.setVisibility(View.GONE);
                }
            } else if (adapterView.getId() == R.id.spCustomerTypeMaster) {
                if (position == 1) {
                    etCRNNumber.setVisibility(View.VISIBLE);
                    etPartyId.setVisibility(View.GONE);
                } else if (position == 2) {
                    etCRNNumber.setVisibility(View.GONE);
                    etPartyId.setVisibility(View.VISIBLE);
                } else {
                    etCRNNumber.setVisibility(View.GONE);
                    etPartyId.setVisibility(View.GONE);
                }
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    CompoundButton.OnCheckedChangeListener addressSameAsAbove = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                bindAddress();
            } else {
                emptyAddress();

            }
        }
    };
    CompoundButton.OnCheckedChangeListener haveCC = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                etCoAppEMICurrentlyPay.setFocusable(true);
                llCoApplicantDetail.setVisibility(View.VISIBLE);

            } else {

                llCoApplicantDetail.setVisibility(View.GONE);
            }
        }
    };

    protected void bindAddress() {
        etPerAdd1.setText(etAdd1.getText().toString());
        etPerAdd2.setText(etAdd2.getText().toString());
        etPerAdd3.setText(etAdd3.getText().toString());
        spPerCity.setSelection(spCurrentAddressCity.getSelectedItemPosition());

        etPerResidencePhnNo.setText(etResidencePhnNo.getText().toString());
        etPerPincode.setText(etPincode.getText().toString());

        // acPerCity.setText(acCity.getText().toString());
        // acPerCity.performCompletion();
        //  acPerState.setText(acState.getText().toString());
        //  acPerState.performCompletion();


    }

    protected void emptyAddress() {
        etPerAdd1.setText("");
        etPerAdd2.setText("");
        etPerAdd3.setText("");
        spPerCity.setSelection(0);

        etPerResidencePhnNo.setText("");
        etPerPincode.setText("");

        // acPerCity.setText(acCity.getText().toString());
        // acPerCity.performCompletion();
        //  acPerState.setText(acState.getText().toString());
        //  acPerState.performCompletion();


    }
    //region datepicker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.etDOB) {
                DateTimePicker.showKotakAgeDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
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
            } else if (view.getId() == R.id.etCurrentResidence) {
                DateTimePicker.showDatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etCurrentResidence.setText(currentDay);
                        }
                    }
                });
            } else if (view.getId() == R.id.etJoiningDate) {
                DateTimePicker.showDatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etJoiningDate.setText(currentDay);
                        }
                    }
                });
            } else if (view.getId() == R.id.etCoAppDOB) {
                DateTimePicker.showDatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etCoAppDOB.setText(currentDay);
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
    public void onClick(View v) {
        if (v.getId() == R.id.btnKotakplNext) {

            if (chkTermsCondition.isChecked()) {

                //region validation personal

                if (spExistingCustomer.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select Existing Customer", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spExistingCustomer.getSelectedItemPosition() == 1) {
                    if (spCustomerTypeMaster.getSelectedItemPosition() == 0) {

                        Toast.makeText(this, "Select Existing Customer Type", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (spQualif.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select  Qualification", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (spEmployementType.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select  Employee", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!isEmpty(etFirstName)) {
                    etFirstName.setError("Enter First name");
                    etFirstName.setFocusable(true);
                    return;
                } else {
                    etFirstName.setError(null);
                }

                if (!isEmpty(etMiddleName)) {
                    etMiddleName.setError("Enter tMiddle name");
                    etMiddleName.setFocusable(true);
                    return;
                } else {
                    etMiddleName.setError(null);
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


                if (isValidAadhar(etAadharNumber)) {
                    etAadharNumber.setError(null);
                } else {

                    etAadharNumber.setError("Invalid aadhar no");
                    etAadharNumber.setFocusable(true);
                    return;
                }

                if (!isEmpty(etAadharNumber)) {
                    etAadharNumber.setError("Invalid aadhar no");
                    etAadharNumber.setFocusable(true);
                    return;
                } else {
                    etAadharNumber.setError(null);
                }

                if (!isEmpty(etPanCard)) {
                    etPanCard.setError("Invalid  pancard");
                    etPanCard.setFocusable(true);
                    return;
                } else {
                    etPanCard.setError(null);
                }

                if (isValidPan(etPanCard)) {
                    etPanCard.setError(null);
                } else {

                    etPanCard.setError("Invalid pancard");
                    etPanCard.setFocusable(true);
                    return;

                }
                //endregion

                //region validation office details

                //  if (!isEmpty(etJoiningDate)) {
                //    etJoiningDate.setError("Invalid Date");
                //     etJoiningDate.setFocusable(true);
                //      return;
                //  } else {
                //       etJoiningDate.setError(null);
                //   }


                if (!isEmpty(etTotalWorkExp)) {
                    etTotalWorkExp.setError("Enter work experience");
                    etTotalWorkExp.setFocusable(true);
                    return;
                } else {
                    etTotalWorkExp.setError(null);
                }


                if (!isEmpty(etOfficeAddress1)) {
                    etOfficeAddress1.setError("Enter address");
                    etOfficeAddress1.setFocusable(true);
                    return;
                } else {
                    etOfficeAddress1.setError(null);
                }
                if (!isEmpty(etOfficeAddress2)) {
                    etOfficeAddress2.setError("Enter address");
                    etOfficeAddress2.setFocusable(true);
                    return;
                } else {
                    etOfficeAddress2.setError(null);
                }
                if (!isEmpty(etOfficeAddress3)) {
                    etOfficeAddress3.setError("Enter address");
                    etOfficeAddress3.setFocusable(true);
                    return;
                } else {
                    etOfficeAddress3.setError(null);
                }

                if (spOfficeCity.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select  Office City", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (etOfficePincode.getText().toString().length() < 6) {
                    etOfficePincode.setError("Invalid Pincode");
                    etOfficePincode.setFocusable(true);
                    return;
                } else {
                    etOfficePincode.setError(null);
                }

                if (!isEmpty(etOfficePincode)) {
                    etOfficePincode.setError("Invalid  pincode");
                    etOfficePincode.setFocusable(true);
                    return;
                } else {
                    etOfficePincode.setError(null);
                }

                if (!isEmpty(etOfficePhone)) {
                    etOfficePhone.setError("Invalid office number");
                    etOfficePhone.setFocusable(true);
                    return;
                } else {
                    etOfficePhone.setError(null);
                }
                if (spPreferredMailingAddress.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select mailing address", Toast.LENGTH_SHORT).show();
                    return;
                }
                //endregion
                //region validation current address

                if (!isEmpty(etAdd1)) {
                    etAdd1.setError("Enter address 1");
                    etAdd1.setFocusable(true);
                    return;
                } else {
                    etAdd1.setError(null);
                }
                if (!isEmpty(etAdd2)) {
                    etAdd2.setError("Enter address 2 ");
                    etAdd2.setFocusable(true);
                    return;
                } else {
                    etAdd2.setError(null);
                }
                if (!isEmpty(etAdd3)) {
                    etAdd3.setError("Enter address 3");
                    etAdd3.setFocusable(true);
                    return;
                } else {
                    etAdd3.setError(null);
                }

                if (spCurrentAddressCity.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select city", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (etPincode.getText().toString().length() < 6) {
                    etPincode.setError("Invalid Pincode");
                    etPincode.setFocusable(true);
                    return;
                } else {
                    etPincode.setError(null);
                }

                if (spResidenceType.getSelectedItemPosition() == 0) {

                    Toast.makeText(this, "Select Residence Type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmpty(etPincode)) {
                    etPincode.setError("Invalid  pincode");
                    etPincode.setFocusable(true);
                    return;
                } else {
                    etPincode.setError(null);
                }

                if (!isEmpty(etCurrentResidence)) {
                    etCurrentResidence.setError("Invalid  date");
                    etCurrentResidence.setFocusable(true);
                    return;
                } else {
                    etCurrentResidence.setError(null);
                }

                if (!isEmpty(etResidencePhnNo)) {
                    etResidencePhnNo.setError("Invalid  phone no");
                    etResidencePhnNo.setFocusable(true);
                    return;
                } else {
                    etResidencePhnNo.setError(null);
                }


                //endregion

                //region validation permanent address

//
//                if (etOfficePhone.getText().toString().length() < 10) {
//                    etOfficePhone.setError("Invalid Phone number");
//                    etOfficePhone.setFocusable(true);
//                    return;
//                } else {
//                    etOfficePhone.setError(null);
//                }

//                if (etPerResidencePhnNo.getText().toString().length() < 10) {
//                    etPerResidencePhnNo.setError("Invalid Phone number");
//                    etPerResidencePhnNo.setFocusable(true);
//                    return;
//                } else {
//                    etPerResidencePhnNo.setError(null);
//                }
//
//                if (etResidencePhnNo.getText().toString().length() < 10) {
//                    etResidencePhnNo.setError("Invalid Phone number");
//                    etResidencePhnNo.setFocusable(true);
//                    return;
//                } else {
//                    etResidencePhnNo.setError(null);
//                }


                if (!isEmpty(etPerAdd1)) {
                    etPerAdd1.setError("Enter address");
                    etPerAdd1.setFocusable(true);
                    return;
                } else {
                    etPerAdd1.setError(null);
                }
                if (!isEmpty(etPerAdd2)) {
                    etPerAdd2.setError("Enter address");
                    etPerAdd2.setFocusable(true);
                    return;
                } else {
                    etPerAdd2.setError(null);
                }
                if (!isEmpty(etPerAdd3)) {
                    etPerAdd3.setError("Enter address");
                    etPerAdd3.setFocusable(true);
                    return;
                } else {
                    etPerAdd3.setError(null);
                }


                if (spPerCity.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select city", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmpty(etPerPincode)) {
                    etPerPincode.setError("Invalid  pincode");
                    etPerPincode.setFocusable(true);
                    return;
                } else {
                    etPerPincode.setError(null);
                }

                if (etPerPincode.getText().toString().length() < 6) {
                    etPerPincode.setError("Invalid pin code ");
                    etPerPincode.setFocusable(true);
                    return;
                } else {
                    etPerPincode.setError(null);
                }


                if (!isEmpty(etPerResidencePhnNo)) {
                    etPerResidencePhnNo.setError("Invalid residence phone no");
                    etPerResidencePhnNo.setFocusable(true);
                    return;
                } else {
                    etPerResidencePhnNo.setError(null);
                }

//                if (etPerResidencePhnNo.getText().toString().length() < 10) {
//                    etPerResidencePhnNo.setError("Invalid residence phone no");
//                    etPerResidencePhnNo.setFocusable(true);
//                    return;
//                } else {
//                    etPerResidencePhnNo.setError(null);
//                }

                //endregion
                //region validation financial details

                if (!isEmpty(etNetMonthIncome)) {
                    etNetMonthIncome.setError("Invalid monthly income");
                    etNetMonthIncome.setFocusable(true);
                    return;
                } else {
                    etNetMonthIncome.setError(null);
                }
                int NMI = Integer.parseInt(etNetMonthIncome.getText().toString());
                if (NMI < 18000) {
                    etNetMonthIncome.setError("Monthly Income should be greater than 18000");
                    etNetMonthIncome.setFocusable(true);
                    return;
                } else {
                    etNetMonthIncome.setError(null);
                }


                if (!isEmpty(etTotalEMIperMonth)) {
                    etTotalEMIperMonth.setError("Invalid emi per month");
                    etTotalEMIperMonth.setFocusable(true);
                    return;
                } else {
                    etTotalEMIperMonth.setError(null);
                }

                if (!isEmpty(etReqLoanAmnt)) {
                    etReqLoanAmnt.setError("Invalid amount");
                    etReqLoanAmnt.setFocusable(true);
                    return;
                } else {
                    etReqLoanAmnt.setError(null);
                }


                if (spReqLoanTenure.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select Tenure", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isEmpty(etPLRateOff)) {
                    etPLRateOff.setError("Invalid amount");
                    etPLRateOff.setFocusable(true);
                    return;
                } else {
                    etPLRateOff.setError(null);
                }

                if (!isEmpty(etReqLoanAmnt)) {
                    etReqLoanAmnt.setError("Invalid amount");
                    etReqLoanAmnt.setFocusable(true);
                    return;
                } else {
                    etReqLoanAmnt.setError(null);
                }
                //endregion
                //region validation co-applicant


                // if (!isEmpty(etCoAppDOB)) {
                //       etCoAppDOB.setError("Invalid date");
                //       etCoAppDOB.setFocusable(true);
                //       return;
                //   } else {
                //       etCoAppDOB.setError(null);
                //   }
                if (rbYes.isChecked()) {
                    if (spCoAppEmpType.getSelectedItemPosition() == 0) {
                        Toast.makeText(this, "Select  employee type", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // if (!isEmpty(etCoAppEmployerName)) {
                //        etCoAppEmployerName.setError("Invalid date");
                //      etCoAppEmployerName.setFocusable(true);
                //      return;
                //   } else {
                //       etCoAppEmployerName.setError(null);
                //   }

                if (!isEmpty(etNetMonthIncome)) {
                    etNetMonthIncome.setError("Invalid date");
                    etNetMonthIncome.setFocusable(true);
                    return;
                } else {
                    etNetMonthIncome.setError(null);
                }


                if (acEmployerName.getText().toString().length() == 0) {
                    acEmployerName.setError("Invalid Company");
                    acEmployerName.setFocusable(true);
                    return;
                } else {
                    acEmployerName.setError(null);
                }
                //endregion


                //region creating request

                // requestEntity.setIsExstCust(spExistingCustomer.getSelectedItem().toString());


                if (spExistingCustomer.getSelectedItemPosition() == 1) {
                    requestEntity.setIsExstCust("Y");
                } else {
                    requestEntity.setIsExstCust("N");
                }

                //  requestEntity.setExstCustType(spCustomerTypeMaster.getSelectedItem().toString());

                requestEntity.setExstCustType(String.valueOf(spCustomerTypeMaster.getSelectedItemPosition()));


                if (etCRNNumber.getText().toString().length() > 0) {
                    requestEntity.setCRN(etCRNNumber.getText().toString());
                } else {
                    requestEntity.setCRN("0");
                }

                if (etPartyId.getText().toString().length() > 0) {
                    requestEntity.setPartyID(etPartyId.getText().toString());
                } else {
                    requestEntity.setPartyID("0");
                }

                requestEntity.setFirstName(etFirstName.getText().toString());
                requestEntity.setMiddleName(etMiddleName.getText().toString());
                requestEntity.setLastName(etLastName.getText().toString());
                if (rbmale.isChecked()) {
                    requestEntity.setGender("1");
                } else {
                    requestEntity.setGender("2");
                }

                requestEntity.setQualification(String.valueOf(spQualif.getSelectedItemPosition()));
                requestEntity.setDOB(etDOB.getText().toString());

                requestEntity.setMobile(etMobileNumber.getText().toString());

                requestEntity.setEmail(etEmail.getText().toString());

                requestEntity.setAadhar(etAadharNumber.getText().toString());
                requestEntity.setPAN(etPanCard.getText().toString());

                // requestEntity.setEmpType(spEmployementType.getSelectedItem().toString());

                if (spEmployementType.getSelectedItemPosition() == 1) {
                    requestEntity.setEmpType("1");
                } else {
                    requestEntity.setEmpType("0");
                }


                requestEntity.setCompany_Cat(dbPersistanceController.getEmployerCategory(acEmployerName.getText().toString()));

                requestEntity.setOrganization(acEmployerName.getText().toString());

                requestEntity.setCurCmpnyJoinDt(etJoiningDate.getText().toString());
                requestEntity.setTotWrkExp(etTotalWorkExp.getText().toString());


                requestEntity.setOffCity(String.valueOf(dbPersistanceController.getKotakPLCityCode(spOfficeCity.getSelectedItem().toString())));

                requestEntity.setOffPin(etOfficePincode.getText().toString());
                requestEntity.setOffPhone(etOfficePhone.getText().toString());


                requestEntity.setPrefMailAdd(String.valueOf(spPreferredMailingAddress.getSelectedItemPosition()));

                requestEntity.setResAddress1(etAdd1.getText().toString());
                requestEntity.setResAddress2(etAdd2.getText().toString());
                requestEntity.setResAddress3(etAdd3.getText().toString());

                requestEntity.setOffAddress1(etOfficeAddress1.getText().toString());
                requestEntity.setOffAddress2(etOfficeAddress2.getText().toString());
                requestEntity.setOffAddress3(etOfficeAddress3.getText().toString());

                requestEntity.setPrefMailAdd(String.valueOf(spPreferredMailingAddress.getSelectedItemPosition()));
                ;
                requestEntity.setResCity(String.valueOf(dbPersistanceController.getKotakPLCityCode(spCurrentAddressCity.getSelectedItem().toString())));

                requestEntity.setResPin(etPincode.getText().toString());
                requestEntity.setResType(String.valueOf(spResidenceType.getSelectedItemPosition()));
                requestEntity.setCurResSince(etCurrentResidence.getText().toString());
                requestEntity.setResPhNo(etResidencePhnNo.getText().toString());
                requestEntity.setResAddress3(etAdd3.getText().toString());
                requestEntity.setResAddress3(etAdd3.getText().toString());
                requestEntity.setSame("on");
                requestEntity.setPerAddress1(etPerAdd1.getText().toString());
                requestEntity.setPerAddress2(etPerAdd2.getText().toString());
                requestEntity.setPerAddress3(etPerAdd3.getText().toString());

                requestEntity.setPerCity(String.valueOf(dbPersistanceController.getKotakPLCityCode(spPerCity.getSelectedItem().toString())));

                requestEntity.setPerPin(etPerPincode.getText().toString());
                ;
                requestEntity.setPerResPhNo(etPerResidencePhnNo.getText().toString());

                requestEntity.setNMI(etNetMonthIncome.getText().toString());
                requestEntity.setEmiCurPay(etTotalEMIperMonth.getText().toString());
                requestEntity.setLnAmt(etReqLoanAmnt.getText().toString());
                // requestEntity.setTnrMths(spReqLoanTenure.getSelectedItem().toString());

                if (spReqLoanTenure.getSelectedItemPosition() == 1) {
                    requestEntity.setTnrMths("12");
                } else if (spReqLoanTenure.getSelectedItemPosition() == 2) {
                    requestEntity.setTnrMths("24");
                } else if (spReqLoanTenure.getSelectedItemPosition() == 3) {
                    requestEntity.setTnrMths("36");
                } else if (spReqLoanTenure.getSelectedItemPosition() == 4) {
                    requestEntity.setTnrMths("48");
                } else if (spReqLoanTenure.getSelectedItemPosition() == 5) {
                    requestEntity.setTnrMths("60");
                } else {
                    requestEntity.setTnrMths("0");
                }

                requestEntity.setIRR(etPLRateOff.getText().toString());
                requestEntity.setProcFee(etProcessingFees.getText().toString());


                if (rbYes.isChecked()) {
                    requestEntity.setIsCoApp("Y");
                    if (spRelationshipWithCoApp.getSelectedItemPosition() == 0) {
                        Toast.makeText(this, "Select relationship with co-applicant", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    requestEntity.setCoAppReltn(String.valueOf(spRelationshipWithCoApp.getSelectedItem()));
                    requestEntity.setCoAppDOB(etCoAppDOB.getText().toString());
                    requestEntity.setCoAppEmpType(String.valueOf(spCoAppEmpType.getSelectedItemPosition()));
                    requestEntity.setCoAppOrg(etCoAppEmployerName.getText().toString());
                    requestEntity.setCoAppNMI(etCoAppNrtMonthlyIncome.getText().toString());
                    requestEntity.setCoAppEmiCurPay(etCoAppEMICurrentlyPay.getText().toString());

                } else {
                    requestEntity.setIsCoApp("N");
                    requestEntity.setCoAppReltn("0");
                    requestEntity.setCoAppDOB("0");
                    requestEntity.setCoAppEmpType("0");
                    requestEntity.setCoAppOrg("0");
                    requestEntity.setCoAppNMI("0");
                    requestEntity.setCoAppEmiCurPay("0");
                }


                requestEntity.setVersion("1");
                requestEntity.setFBAID(String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()));
                requestEntity.setBrokerid(new DBPersistanceController(this).getUserData().getLoanId());
                requestEntity.setEmpid("");

                requestEntity.setCampaignName("KOTAK PL");
                requestEntity.setSource("");
                requestEntity.setBankId(String.valueOf(BankID));
                requestEntity.setLoanType(LoanType);

                showDialog();
                new ExpressLoanController(this).savekotakPersonalLoan(requestEntity, this);

            } else {
                Toast.makeText(this, "Accept Terms and Condtion", Toast.LENGTH_SHORT).show();
            }


        } else if (v.getId() == R.id.btnROICalc) {

            if (acEmployerName.getText().toString().length() == 0) {
                acEmployerName.setError("Invalid Company");
                acEmployerName.requestFocus();
                return;
            } else {
                acEmployerName.setError(null);
            }

            if (!isEmpty(etReqLoanAmnt)) {
                etReqLoanAmnt.setError("Invalid amount");
                etReqLoanAmnt.requestFocus();
                return;
            } else {
                etReqLoanAmnt.setError(null);
            }

            if (!isEmpty(etNetMonthIncome)) {
                etNetMonthIncome.setError("Invalid amount");
                etNetMonthIncome.requestFocus();
                return;
            } else {
                etNetMonthIncome.setError(null);
            }

            int NMI = Integer.parseInt(etNetMonthIncome.getText().toString());
            if (NMI < 18000) {
                etNetMonthIncome.setError("Monthly Income should be greater than 18000");
                etNetMonthIncome.requestFocus();
                return;
            } else {
                etNetMonthIncome.setError(null);
            }

            showDialog();
            new ExpressLoanController(this).getKotakROICalList(etNetMonthIncome.getText().toString(), acEmployerName.getText().toString(), etReqLoanAmnt.getText().toString(), this);

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        if (response instanceof kotakPers_SaveResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {


                if (((kotakPers_SaveResponse) response).getMasterData().getLead_Id().length() > 1) {

                    dialogMessage(true, ((kotakPers_SaveResponse) response).getMasterData().getReferenceCode(), ((kotakPers_SaveResponse) response).getMessage());
                } else {
                    dialogMessage(false, "", ((kotakPers_SaveResponse) response).getMessage());
                }
                // dialogMessage(true, ((kotakPers_SaveResponse) response).getMessage(), response.getMessage());

            }


        } else if (response instanceof KotakPLEmployerNameResponse) {
            cancelDialog();
            prefManager.setIsEmployerNAmeUpdate(false);

        } else if (response instanceof KotakROICalResponse) {
            cancelDialog();
            etPLRateOff.setText("" + ((KotakROICalResponse) response).getMasterData().getRoi().toString());
            etProcessingFees.setText("" + ((KotakROICalResponse) response).getMasterData().getProcFee());
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
