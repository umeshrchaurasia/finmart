package com.datacomp.magicfinmart.loan_fm.personalloan.loan_apply;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.loan_fm.personalloan.PersonalLoanDetailActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PincodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseERP;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberERP;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.erploan.ErpLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.homeloan.HomeLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BuyLoanQuerystring;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalLoanApplyAppliEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.RBCustomerEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.ErpPersonLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.ERPSaveResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.PersonalLoanApplicationResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.RBCustomerResponse;

import static com.datacomp.magicfinmart.BaseFragment.stringToDate;

public class PersonalLoanApplyActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, IResponseSubcriber, magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber, IResponseSubcriberERP {

    // region Entity Declaration
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginEntity;
    RBCustomerEntity rbCustomerEntity;
    ErpPersonLoanRequest erpLoanRequest;
    BuyLoanQuerystring buyLoanQuerystring;
    PersonalLoanApplyAppliEntity personalLoanApplyAppliEntity;
    //endregion

    // region Control Declaration
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    //server conversion date format
    SimpleDateFormat formatServer = new SimpleDateFormat("yyyy-MM-dd");


    Spinner spTitle, spNatureOfOrg, spNatureOfBus, spResidence;
    RelativeLayout rlPLInfo, rlAddress, rlEmployment, rlFinancial;

    LinearLayout llPlInfo, llAddress, llEmployment, llFinancial;

    ImageView ivMale, ivFemale, ivPLInfo, ivAddress, ivEmploy, ivFinancial,
            ivFinancialPending, ivFinancialDone, ivEmployDone, ivEmployPending, ivAddressDone, ivAddressPending, ivPLInfoDone, ivPLInfoPending;


    CheckBox chkPresent;
    Button btnSubmit;

    EditText etFirstName, etLastName, etDob, etFatherName, etPan, etNationality, etUniversity, etMoMaidenName, etSpouceName, etNoOfDepen, etIDNumber,
    // Address
    etEmailPersContInfo, etEmailOffContInfo, etMobNo1ContInfo, etMobNo2ContInfo,
            etAddress1ContInfoRAP, etAddress2ContInfoRAP, etAddress3ContInfoRAP,
            etLandmakContInfoRAP, etPincodeContInfoRAP, etCityContInfoRAP, etNoOfYrsAtOffContInfoRAP,
            etStateContInfoRAP, etCountryPlRAP, etLandlineNoContInfoRAP, etAddress1ContInfoPA, etAddress2ContInfoPA, etAddress3ContInfoPA, etLandmakContInfoPA,
            etPincodeContInfoPA, etCityContInfoPA, etStateContInfoPA, etCountryPA,
            etLandlineNoContInfoPA, etNoOfYrsAtOffContInfoPA,

    // Employment
    etDesig, etCurrJob, etTotalExp, etNameOfOrg, etTurnOver, etDeprec, etDirRem, etProfAftTax,
            etAddress1ED, etAddress2ED, etAddress3ED, etLandmakED, etPincodeED, etCityED, etStateED, etCountryED, etLandlineNoED,

    // Finacial
    etGrossIncome, etNetIncome, etOtherIncome, etTotalIncome;


    TextView txtMarried, txtSingle, txtIdType, txtRES, txtNRI, txtPIO, txtOCR, txtFOR,
            txtGEN, txtSC, txtST, txtOBC, txtOTH,
            txtPORT, txtVOTER, txtDRV,
            txtMATR, txtUGRAD, txtGRAD, txtPGRAD, txteducatOTH,
            txtEmpNatureSalaried, txtEmpNatureSelfEmp;

    TextInputLayout textInpLayCurrJob, textInpLayTotalExp,
            textInpLayTurnOver, textInpLayDepreciation, textInpLayDirRem, textInpLayProfAftTax, textInpLaySpouseName;
    //endregion

    // region Variable Declaration

    String Gender = "Male";
    String EmpNature = "Salaried";
    String MaritalStatus = "MARRIED";
    String PL_STATUS = "RES", PL_CATEGORY = "GEN", PL_IDTYPE = "", PL_EDUCATION = "GRAD";
    int StatusType = 1, CategoryType = 2, EducationType = 3;
    Boolean isDataUploaded = true;
    //  Boolean isPAN_RES = false;
    int PAN_type = 1;
    boolean validatePA = false;
    boolean isSubmit = false;

    boolean isAppliction = false;
    String AppID = "0";

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_loan_apply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        dbPersistanceController = new DBPersistanceController(this);
        loginEntity = dbPersistanceController.getUserData();
        buyLoanQuerystring = new BuyLoanQuerystring();
        personalLoanApplyAppliEntity = new PersonalLoanApplyAppliEntity();
        initialize();
        setListener();
        initLayouts();
        setDefaultCheckList();

        // region getIntent Data
        if (getIntent().hasExtra("BuyLoanQuery")) {
            buyLoanQuerystring = getIntent().getExtras().getParcelable("BuyLoanQuery");
            isAppliction = false;
            setRbCustomerData2();

            showDialog("Please Wait...");
            new HomeLoanController(this).getRBCustomerData(String.valueOf(buyLoanQuerystring.getQuote_id()), PersonalLoanApplyActivity.this);


        } else if (getIntent().hasExtra(Utility.PLLOAN_APPLICATION)) {
            AppID = getIntent().getExtras().getString(Utility.PLLOAN_APPLICATION, "");

            isAppliction = true;
            if (!AppID.equals("")) {
                showDialog("Please Wait...");
                new ErpLoanController(this).getPersonalLoanApplication(AppID, PersonalLoanApplyActivity.this);
            }
        }

        //endregion

    }

    //region Method
    private void initialize() {

        rbCustomerEntity = new RBCustomerEntity();
        erpLoanRequest = new ErpPersonLoanRequest();
        // region MasterLayout
        ivPLInfo = (ImageView) findViewById(R.id.ivPLInfo);
        ivAddress = (ImageView) findViewById(R.id.ivAddress);
        ivEmploy = (ImageView) findViewById(R.id.ivEmploy);
        ivFinancial = (ImageView) findViewById(R.id.ivFinancial);

        rlPLInfo = (RelativeLayout) findViewById(R.id.rlPLInfo);
        rlAddress = (RelativeLayout) findViewById(R.id.rlAddress);
        rlEmployment = (RelativeLayout) findViewById(R.id.rlEmployment);
        rlFinancial = (RelativeLayout) findViewById(R.id.rlFinancial);

        llPlInfo = (LinearLayout) findViewById(R.id.llPlInfo);
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        llEmployment = (LinearLayout) findViewById(R.id.llEmployment);
        llFinancial = (LinearLayout) findViewById(R.id.llFinancial);
        // endregion

        // region Spinner

        spTitle = (Spinner) findViewById(R.id.spTitle);
        spNatureOfOrg = (Spinner) findViewById(R.id.spNatureOfOrg);
        spNatureOfBus = (Spinner) findViewById(R.id.spNatureOfBus);
        spResidence = (Spinner) findViewById(R.id.spResidence);

        //endregion

        // region Persoal Info
        txtMarried = (TextView) findViewById(R.id.txtMarried);
        txtSingle = (TextView) findViewById(R.id.txtSingle);
        txtIdType = (TextView) findViewById(R.id.txtIdType);

        txtRES = (TextView) findViewById(R.id.txtRES);
        txtNRI = (TextView) findViewById(R.id.txtNRI);
        txtPIO = (TextView) findViewById(R.id.txtPIO);
        txtOCR = (TextView) findViewById(R.id.txtOCR);
        txtFOR = (TextView) findViewById(R.id.txtFOR);

        txtGEN = (TextView) findViewById(R.id.txtGEN);
        txtSC = (TextView) findViewById(R.id.txtSC);
        txtST = (TextView) findViewById(R.id.txtST);
        txtOBC = (TextView) findViewById(R.id.txtOBC);
        txtOTH = (TextView) findViewById(R.id.txtOTH);

        txtPORT = (TextView) findViewById(R.id.txtPORT);
        txtVOTER = (TextView) findViewById(R.id.txtVOTER);
        txtDRV = (TextView) findViewById(R.id.txtDRV);

        txtMATR = (TextView) findViewById(R.id.txtMATR);
        txtUGRAD = (TextView) findViewById(R.id.txtUGRAD);
        txtGRAD = (TextView) findViewById(R.id.txtGRAD);
        txtPGRAD = (TextView) findViewById(R.id.txtPGRAD);
        txteducatOTH = (TextView) findViewById(R.id.txteducatOTH);


        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etDob = (EditText) findViewById(R.id.etDob);
        etPan = (EditText) findViewById(R.id.etPan);
        etFatherName = (EditText) findViewById(R.id.etFatherName);

        etNationality = (EditText) findViewById(R.id.etNationality);
        etUniversity = (EditText) findViewById(R.id.etUniversity);
        etMoMaidenName = (EditText) findViewById(R.id.etMoMaidenName);
        etSpouceName = (EditText) findViewById(R.id.etSpouceName);
        etNoOfDepen = (EditText) findViewById(R.id.etNoOfDepen);
        etIDNumber = (EditText) findViewById(R.id.etIDNumber);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        // endregion

        // region Address
        chkPresent = (CheckBox) findViewById(R.id.chkPresent);

        etEmailPersContInfo = (EditText) findViewById(R.id.etEmailPersContInfo);
        etEmailOffContInfo = (EditText) findViewById(R.id.etEmailOffContInfo);
        etMobNo1ContInfo = (EditText) findViewById(R.id.etMobNo1ContInfo);
        etMobNo2ContInfo = (EditText) findViewById(R.id.etMobNo2ContInfo);

        etAddress1ContInfoRAP = (EditText) findViewById(R.id.etAddress1ContInfoRAP);
        etAddress2ContInfoRAP = (EditText) findViewById(R.id.etAddress2ContInfoRAP);
        etAddress3ContInfoRAP = (EditText) findViewById(R.id.etAddress3ContInfoRAP);
        etLandmakContInfoRAP = (EditText) findViewById(R.id.etLandmakContInfoRAP);
        etLandlineNoContInfoRAP = (EditText) findViewById(R.id.etLandlineNoContInfoRAP);

        etPincodeContInfoRAP = (EditText) findViewById(R.id.etPincodeContInfoRAP);
        etCityContInfoRAP = (EditText) findViewById(R.id.etCityContInfoRAP);
        etStateContInfoRAP = (EditText) findViewById(R.id.etStateContInfoRAP);
        etCountryPlRAP = (EditText) findViewById(R.id.etCountryPlRAP);
        etNoOfYrsAtOffContInfoRAP = (EditText) findViewById(R.id.etNoOfYrsAtOffContInfoRAP);

        etAddress1ContInfoPA = (EditText) findViewById(R.id.etAddress1ContInfoPA);
        etAddress2ContInfoPA = (EditText) findViewById(R.id.etAddress2ContInfoPA);
        etAddress3ContInfoPA = (EditText) findViewById(R.id.etAddress3ContInfoPA);
        etLandmakContInfoPA = (EditText) findViewById(R.id.etLandmakContInfoPA);

        etPincodeContInfoPA = (EditText) findViewById(R.id.etPincodeContInfoPA);
        etCityContInfoPA = (EditText) findViewById(R.id.etCityContInfoPA);
        etStateContInfoPA = (EditText) findViewById(R.id.etStateContInfoPA);
        etCountryPA = (EditText) findViewById(R.id.etCountryPA);

        etLandlineNoContInfoPA = (EditText) findViewById(R.id.etLandlineNoContInfoPA);
        etNoOfYrsAtOffContInfoPA = (EditText) findViewById(R.id.etNoOfYrsAtOffContInfoPA);

        txtMarried = (TextView) findViewById(R.id.txtMarried);
        txtSingle = (TextView) findViewById(R.id.txtSingle);
        txtRES = (TextView) findViewById(R.id.txtRES);
        txtNRI = (TextView) findViewById(R.id.txtNRI);
        txtPIO = (TextView) findViewById(R.id.txtPIO);
        txtOCR = (TextView) findViewById(R.id.txtOCR);
        txtFOR = (TextView) findViewById(R.id.txtFOR);

        txtGEN = (TextView) findViewById(R.id.txtGEN);
        txtSC = (TextView) findViewById(R.id.txtSC);
        txtST = (TextView) findViewById(R.id.txtST);
        txtOBC = (TextView) findViewById(R.id.txtOBC);
        txtOTH = (TextView) findViewById(R.id.txtOTH);

        txtPORT = (TextView) findViewById(R.id.txtPORT);
        txtVOTER = (TextView) findViewById(R.id.txtVOTER);
        txtDRV = (TextView) findViewById(R.id.txtDRV);

        txtMATR = (TextView) findViewById(R.id.txtMATR);
        txtUGRAD = (TextView) findViewById(R.id.txtUGRAD);
        txtGRAD = (TextView) findViewById(R.id.txtGRAD);
        txtPGRAD = (TextView) findViewById(R.id.txtPGRAD);
        txteducatOTH = (TextView) findViewById(R.id.txteducatOTH);


        ivMale = (ImageView) findViewById(R.id.ivMale);
        ivFemale = (ImageView) findViewById(R.id.ivFemale);

        ivFinancialPending = (ImageView) findViewById(R.id.ivFinancialPending);
        ivFinancialDone = (ImageView) findViewById(R.id.ivFinancialDone);
        ivEmployDone = (ImageView) findViewById(R.id.ivEmployDone);
        ivEmployPending = (ImageView) findViewById(R.id.ivEmployPending);

        ivAddressDone = (ImageView) findViewById(R.id.ivAddressDone);
        ivAddressPending = (ImageView) findViewById(R.id.ivAddressPending);
        ivPLInfoDone = (ImageView) findViewById(R.id.ivPLInfoDone);
        ivPLInfoPending = (ImageView) findViewById(R.id.ivPLInfoPending);

        //endregion

        // region Employment Dtl

        txtEmpNatureSalaried = (TextView) findViewById(R.id.txtEmpNatureSalaried);
        txtEmpNatureSelfEmp = (TextView) findViewById(R.id.txtEmpNatureSelfEmp);

        textInpLayCurrJob = (TextInputLayout) findViewById(R.id.textInpLayCurrJob);
        textInpLayTotalExp = (TextInputLayout) findViewById(R.id.textInpLayTotalExp);

        textInpLayTurnOver = (TextInputLayout) findViewById(R.id.textInpLayTurnOver);
        textInpLayDepreciation = (TextInputLayout) findViewById(R.id.textInpLayDepreciation);
        textInpLayDirRem = (TextInputLayout) findViewById(R.id.textInpLayDirRem);
        textInpLayProfAftTax = (TextInputLayout) findViewById(R.id.textInpLayProfAftTax);
        textInpLaySpouseName = (TextInputLayout) findViewById(R.id.textInpLaySpouseName);

        etDesig = (EditText) findViewById(R.id.etDesig);
        etCurrJob = (EditText) findViewById(R.id.etCurrJob);
        etTotalExp = (EditText) findViewById(R.id.etTotalExp);
        etNameOfOrg = (EditText) findViewById(R.id.etNameOfOrg);

        etTurnOver = (EditText) findViewById(R.id.etTurnOver);
        etDeprec = (EditText) findViewById(R.id.etDeprec);
        etDirRem = (EditText) findViewById(R.id.etDirRem);
        etProfAftTax = (EditText) findViewById(R.id.etProfAftTax);

        etAddress1ED = (EditText) findViewById(R.id.etAddress1ED);
        etAddress2ED = (EditText) findViewById(R.id.etAddress2ED);
        etAddress3ED = (EditText) findViewById(R.id.etAddress3ED);
        etLandmakED = (EditText) findViewById(R.id.etLandmakED);

        etPincodeED = (EditText) findViewById(R.id.etPincodeED);
        etCityED = (EditText) findViewById(R.id.etCityED);
        etStateED = (EditText) findViewById(R.id.etStateED);
        etCountryED = (EditText) findViewById(R.id.etCountryED);
        etLandlineNoED = (EditText) findViewById(R.id.etLandlineNoED);
        //endregion

        // region Financial Info
        etGrossIncome = (EditText) findViewById(R.id.etGrossIncome);
        etNetIncome = (EditText) findViewById(R.id.etNetIncome);
        etOtherIncome = (EditText) findViewById(R.id.etOtherIncome);
        etTotalIncome = (EditText) findViewById(R.id.etTotalIncome);

        //endregion

    }

    private void setListener() {

        etDob.setOnClickListener(datePickerDialogApplicant);
        btnSubmit.setOnClickListener(this);
        ivMale.setOnClickListener(this);
        ivFemale.setOnClickListener(this);

        ivPLInfo.setOnClickListener(this);
        ivAddress.setOnClickListener(this);
        ivEmploy.setOnClickListener(this);
        ivFinancial.setOnClickListener(this);

        rlPLInfo.setOnClickListener(this);
        rlAddress.setOnClickListener(this);
        rlEmployment.setOnClickListener(this);
        rlFinancial.setOnClickListener(this);

        txtMarried.setOnClickListener(this);
        txtSingle.setOnClickListener(this);

        txtRES.setOnClickListener(this);
        txtNRI.setOnClickListener(this);
        txtPIO.setOnClickListener(this);
        txtOCR.setOnClickListener(this);
        txtFOR.setOnClickListener(this);

        txtGEN.setOnClickListener(this);
        txtSC.setOnClickListener(this);
        txtST.setOnClickListener(this);
        txtOBC.setOnClickListener(this);
        txtOTH.setOnClickListener(this);

        txtPORT.setOnClickListener(this);
        txtVOTER.setOnClickListener(this);
        txtDRV.setOnClickListener(this);

        txtMATR.setOnClickListener(this);
        txtUGRAD.setOnClickListener(this);
        txtGRAD.setOnClickListener(this);
        txtPGRAD.setOnClickListener(this);
        txteducatOTH.setOnClickListener(this);

        txtEmpNatureSalaried.setOnClickListener(this);
        txtEmpNatureSelfEmp.setOnClickListener(this);

        etCityContInfoRAP.setKeyListener(null);
        etStateContInfoRAP.setKeyListener(null);
        chkPresent.setOnCheckedChangeListener(this);

        etPincodeContInfoRAP.addTextChangedListener(pincodeRAPTextWatcher);
        etPincodeContInfoPA.addTextChangedListener(pincodePATextWatcher);
        etPincodeED.addTextChangedListener(pincodeEDTextWatcher);


        etGrossIncome.addTextChangedListener(grossIncomeTextWatcher);
        etNetIncome.addTextChangedListener(grossIncomeTextWatcher);
        etOtherIncome.addTextChangedListener(grossIncomeTextWatcher);

        // region  CAPS Text

        etFirstName.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});
        etLastName.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});
        //  etDob.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        etFatherName.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});

        etPan.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});
        etNationality.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});
        etUniversity.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(100)});
        etMoMaidenName.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});

        etSpouceName.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        //  etNoOfDepen.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});
        etIDNumber.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});

        etAddress1ContInfoRAP.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etAddress2ContInfoRAP.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etAddress3ContInfoRAP.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etCountryPA.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});

        //   etLandlineNoContInfoPA.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});
        //     etLandlineNoContInfoRAP.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});
        etAddress1ContInfoPA.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etAddress2ContInfoPA.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});

        etAddress3ContInfoPA.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etLandmakContInfoPA.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(150)});
        etAddress3ContInfoRAP.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etLandmakContInfoRAP.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(150)});

        etDesig.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});
        //   etCurrJob.setFilters(new InputFilter[] {new InputFilter.AllCaps(), new InputFilter.LengthFilter(10)});
        etNameOfOrg.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(100)});
        etAddress1ED.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});

        etAddress2ED.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etAddress3ED.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(200)});
        etLandmakED.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(150)});
        etCountryED.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(25)});

        //endregion

    }

    private void initLayouts() {
        llPlInfo.setVisibility(View.VISIBLE);
        llAddress.setVisibility(View.GONE);
        llEmployment.setVisibility(View.GONE);
        llFinancial.setVisibility(View.GONE);

    }

    private void setDefaultCheckList() {
        ivFinancialPending.setVisibility(View.VISIBLE);
        ivEmployPending.setVisibility(View.VISIBLE);
        ivAddressPending.setVisibility(View.VISIBLE);
        ivPLInfoPending.setVisibility(View.VISIBLE);

        ivFinancialDone.setVisibility(View.INVISIBLE);
        ivEmployDone.setVisibility(View.INVISIBLE);
        ivAddressDone.setVisibility(View.INVISIBLE);
        ivPLInfoDone.setVisibility(View.INVISIBLE);

        setMale_gender();
        setEmpSalaried("Salaried", false, txtEmpNatureSalaried, txtEmpNatureSelfEmp);
    }

    private void setMale_gender() {
        Gender = "Male";
        ivMale.setImageResource(R.drawable.male_selected);
        ivFemale.setImageResource(R.drawable.female);
    }

    private void setFeMale_gender() {
        Gender = "Female";
        ivFemale.setImageResource(R.drawable.female_selected);
        ivMale.setImageResource(R.drawable.male);


    }

    private void setEmpSalaried(String Type, boolean flag, TextView clickedText, TextView textView1) {
        EmpNature = Type;

        clickedText.setBackgroundResource(R.drawable.customeborder_blue);
        clickedText.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, Utility.getPrimaryColor(this)));

        textView1.setBackgroundResource(R.drawable.customeborder);
        textView1.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));


        if (flag) {
            textInpLayTurnOver.setHint("*Turn Over");
            textInpLayDepreciation.setHint("*Depreciation");
            textInpLayDirRem.setHint("*Director's Remuneration");
            textInpLayProfAftTax.setHint("*Profit After Tax");

            textInpLayCurrJob.setHint("Current Job(YRS)");
            textInpLayTotalExp.setHint("Total Exp(YRS)");

//            etCurrJob.setText("");
//            etTotalExp.setText("");

            etCurrJob.setError(null);
            etTotalExp.setError(null);

            etCurrJob.clearFocus();
            etTotalExp.clearFocus();


        } else {

            textInpLayCurrJob.setHint("*Current Job(YRS)");
            textInpLayTotalExp.setHint("*Total Exp(YRS)");

            textInpLayTurnOver.setHint("Turn Over");
            textInpLayDepreciation.setHint("Depreciation");
            textInpLayDirRem.setHint("Director's Remuneration");
            textInpLayProfAftTax.setHint("Profit After Tax");

//            etTurnOver.setText("");
//            etDeprec.setText("");
//            etDirRem.setText("");
//            etProfAftTax.setText("");

            etTurnOver.setError(null);
            etDeprec.setError(null);
            etDirRem.setError(null);
            etProfAftTax.setError(null);

            etTurnOver.clearFocus();
            etDeprec.clearFocus();
            etDirRem.clearFocus();
            etProfAftTax.clearFocus();


        }

        etTurnOver.setEnabled(flag);
        etDeprec.setEnabled(flag);
        etDirRem.setEnabled(flag);
        etProfAftTax.setEnabled(flag);


        etCurrJob.setEnabled(!flag);
        etTotalExp.setEnabled(!flag);


    }

    private void seMaritalStatus(String Type, TextView clickedText, TextView textView1) {
        MaritalStatus = Type;

        clickedText.setBackgroundResource(R.drawable.customeborder_blue);
        clickedText.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, Utility.getPrimaryColor(this)));

        textView1.setBackgroundResource(R.drawable.customeborder);
        textView1.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));
        if (MaritalStatus.equals("MARRIED")) {
            textInpLaySpouseName.setHint("*Spouse Name");
            etSpouceName.setEnabled(true);

        } else {
            textInpLaySpouseName.setHint("Spouse Name");
            etSpouceName.setText("");
            etSpouceName.setError(null);
            etSpouceName.clearFocus();
            etSpouceName.setEnabled(false);


        }

    }

    private void settotal() {
        long netIncome = 0, othIncome = 0;


        if (!etNetIncome.getText().toString().trim().equals("")) {
            netIncome = Long.valueOf(etNetIncome.getText().toString());
        }

        if (!etOtherIncome.getText().toString().trim().equals("")) {
            othIncome = Long.valueOf(etOtherIncome.getText().toString());
        }

        double total = netIncome + othIncome;

        etTotalIncome.setText("" + BigDecimal.valueOf(total).toPlainString());

    }

    private void getResAddrToPermAddress() {
        etAddress1ContInfoPA.setText(etAddress1ContInfoRAP.getText().toString());
        etAddress2ContInfoPA.setText(etAddress2ContInfoRAP.getText().toString());
        etAddress3ContInfoPA.setText(etAddress3ContInfoRAP.getText().toString());
        etLandmakContInfoPA.setText(etLandmakContInfoRAP.getText().toString());

        etPincodeContInfoPA.setText(etPincodeContInfoRAP.getText().toString());
        etCityContInfoPA.setText(etCityContInfoRAP.getText().toString());
        etStateContInfoPA.setText(etStateContInfoRAP.getText().toString());
        etCountryPA.setText(etCountryPlRAP.getText().toString());

        etLandlineNoContInfoPA.setText(etLandlineNoContInfoRAP.getText().toString());
        etNoOfYrsAtOffContInfoPA.setText(etNoOfYrsAtOffContInfoRAP.getText().toString());
    }

    private void clearPermAddress() {
        etAddress1ContInfoPA.setText("");
        etAddress2ContInfoPA.setText("");
        etAddress3ContInfoPA.setText("");
        etLandmakContInfoPA.setText("");

        etPincodeContInfoPA.setText("");
        etCityContInfoPA.setText("");
        etStateContInfoPA.setText("");
        etCountryPA.setText("");

        etLandlineNoContInfoPA.setText("");
        etNoOfYrsAtOffContInfoPA.setText("");
    }

    private void saveData(int SubmitType) {
        if (isAppliction) {
            //region PL_INFO
            erpLoanRequest.setTitle(spTitle.getSelectedItem().toString());

            erpLoanRequest.setFirst_Name(etFirstName.getText().toString().trim());
            erpLoanRequest.setDOB(etDob.getText().toString().trim());
            // erpLoanRequest.setDOB(getDDMMYYYPattern(etDob.getText().toString().trim(),"dd-MM-yyyy"));
//
            erpLoanRequest.setMiddle_Name(etFatherName.getText().toString().trim());
            erpLoanRequest.setLast_Name(etLastName.getText().toString().trim());
            erpLoanRequest.setGender(Gender);

            erpLoanRequest.setMarital_Status(MaritalStatus);
            erpLoanRequest.setSpouce_Name(etSpouceName.getText().toString().trim());
            erpLoanRequest.setNo_Of_Dependent(etNoOfDepen.getText().toString().trim());
            erpLoanRequest.setPAN_No(etPan.getText().toString().trim());
            erpLoanRequest.setNationality(etNationality.getText().toString().trim());

            erpLoanRequest.setStatus_Id(PL_STATUS);
            erpLoanRequest.setCategory_Id(PL_CATEGORY);
            erpLoanRequest.setId_Type(PL_IDTYPE);
            erpLoanRequest.setEducation_Id(PL_EDUCATION);
            erpLoanRequest.setId_No(etIDNumber.getText().toString().trim());

            erpLoanRequest.setInstitute_University(etUniversity.getText().toString().trim());
            erpLoanRequest.setMothers_Maidan_Name(etMoMaidenName.getText().toString().trim());
            //endregion

            //region Address_INFO
            erpLoanRequest.setPer_Email_Id(etEmailPersContInfo.getText().toString().trim());
            erpLoanRequest.setOfc_Email_Id(etEmailOffContInfo.getText().toString().trim());
            erpLoanRequest.setMobile_No1(etMobNo1ContInfo.getText().toString().trim());
            erpLoanRequest.setMobile_No2(etMobNo2ContInfo.getText().toString().trim());

            // region Address RAP
            erpLoanRequest.setAddress1(etAddress1ContInfoRAP.getText().toString().trim());
            erpLoanRequest.setAddress2(etAddress2ContInfoRAP.getText().toString().trim());
            erpLoanRequest.setAddress3(etAddress3ContInfoRAP.getText().toString().trim());
            erpLoanRequest.setLandmark(etLandmakContInfoRAP.getText().toString().trim());

            erpLoanRequest.setPincode(etPincodeContInfoRAP.getText().toString().trim());
            erpLoanRequest.setCity(etCityContInfoRAP.getText().toString().trim());
            erpLoanRequest.setState(etStateContInfoRAP.getText().toString().trim());
            erpLoanRequest.setCountry(etCountryPlRAP.getText().toString().trim());
            erpLoanRequest.setLandlineNo(etLandlineNoContInfoRAP.getText().toString().trim());
            erpLoanRequest.setAddrsYrs(etNoOfYrsAtOffContInfoRAP.getText().toString().trim());
            //endregion

            // region Address PA  ;
            // 05

            erpLoanRequest.setResidence_Type(spResidence.getSelectedItem().toString());
            erpLoanRequest.setPer_Address1(etAddress1ContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Address2(etAddress2ContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Address3(etAddress3ContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Landmark(etLandmakContInfoPA.getText().toString().trim());

            erpLoanRequest.setPer_Pincode(etPincodeContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_City(etCityContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_State(etStateContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Country(etCountryPA.getText().toString().trim());
            erpLoanRequest.setPer_LandlineNo(etLandlineNoContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_AddrsYrs(etNoOfYrsAtOffContInfoPA.getText().toString().trim());
            //endregion

            //endregion

            //region EmploymentINFO

            erpLoanRequest.setNature_Of_Employer(EmpNature);
            erpLoanRequest.setNature_Of_Organization(spNatureOfOrg.getSelectedItem().toString());
            erpLoanRequest.setNature_Of_Business(spNatureOfBus.getSelectedItem().toString());
            erpLoanRequest.setDesignation(etDesig.getText().toString().trim());
            erpLoanRequest.setCurrnet_Employment_Period(etCurrJob.getText().toString().trim());

            erpLoanRequest.setTotal_Employment_Period(etTotalExp.getText().toString().trim());
            erpLoanRequest.setName_of_Organisation(etNameOfOrg.getText().toString().trim());
            erpLoanRequest.setTurn_Over(etTurnOver.getText().toString().trim());
            erpLoanRequest.setDepreciation(etDeprec.getText().toString().trim());
            erpLoanRequest.setDirector_Remuneration(etDirRem.getText().toString().trim());
            erpLoanRequest.setProfit_Aft_Tax(etProfAftTax.getText().toString().trim());

            erpLoanRequest.setAddress1_of_Organisation(etAddress1ED.getText().toString().trim());
            erpLoanRequest.setAddress2_of_Organisation(etAddress2ED.getText().toString().trim());
            erpLoanRequest.setAddress3_of_Organisation(etAddress3ED.getText().toString().trim());
            erpLoanRequest.setOrganize_Landmark(etLandmakED.getText().toString().trim());
            erpLoanRequest.setOrganize_Pincode(etPincodeED.getText().toString().trim());

            erpLoanRequest.setOrganize_City(etCityED.getText().toString().trim());
            erpLoanRequest.setOrganize_State(etStateED.getText().toString().trim());
            erpLoanRequest.setOrganize_Country(etCountryED.getText().toString().trim());
            erpLoanRequest.setOrganize_LandlineNo(etLandlineNoED.getText().toString().trim());


            //endregion

            //region Financial INFO
            erpLoanRequest.setGross_Income(etGrossIncome.getText().toString().trim());

            if (!etNetIncome.getText().toString().equals("")) {
                erpLoanRequest.setNet_Income(etNetIncome.getText().toString().trim());
            } else {
                erpLoanRequest.setNet_Income("0");

            }

            erpLoanRequest.setOther_Income(etOtherIncome.getText().toString().trim());
            erpLoanRequest.setTotal_Income(etTotalIncome.getText().toString().trim());
            //endregion

            erpLoanRequest.setBankId(personalLoanApplyAppliEntity.getBankId());//qurystring
            erpLoanRequest.setQuote_id(personalLoanApplyAppliEntity.getQuote_id());//qurystring
            erpLoanRequest.setBrokerId(Integer.valueOf(personalLoanApplyAppliEntity.getBrokerId()));//Loanid
            erpLoanRequest.setProductId(personalLoanApplyAppliEntity.getProductId());
            // region For Quote and Query string


            if (!personalLoanApplyAppliEntity.getLoan_Amount().equals("")) {
                erpLoanRequest.setLoan_Amount(personalLoanApplyAppliEntity.getLoan_Amount());

            } else {
                erpLoanRequest.setLoan_Amount("0");
            }

            if (!personalLoanApplyAppliEntity.getLoan_Terms().equals("")) {
                erpLoanRequest.setLoan_Terms(personalLoanApplyAppliEntity.getLoan_Terms());

            } else {
                erpLoanRequest.setLoan_Terms("0");
            }


            erpLoanRequest.setROI_Id_Type(personalLoanApplyAppliEntity.getROI_Id_Type());  /// 05
            erpLoanRequest.setProcessing_Fee(personalLoanApplyAppliEntity.getProcessing_Fee());

            erpLoanRequest.setApplnId(personalLoanApplyAppliEntity.getApplnId());
            erpLoanRequest.setIs_ApplnComplete(SubmitType);//submit final
            erpLoanRequest.setIs_Confirm(0);
            erpLoanRequest.setAppln_Source("PL");
            erpLoanRequest.setDc_fba_reg(String.valueOf(loginEntity.getFBAId()));
            erpLoanRequest.setRBA_Source("Finmart");
            erpLoanRequest.setFBA_Reg_Id(String.valueOf(loginEntity.getFBAId()));

            //endregion

            if (SubmitType == 1) {
                showDialog("Please wait...");
            }
            new ErpLoanController(this).saveERPPersonalLoan(erpLoanRequest, PersonalLoanApplyActivity.this);
        } else {

            //region PL_INFO
            erpLoanRequest.setTitle(spTitle.getSelectedItem().toString());

            erpLoanRequest.setFirst_Name(etFirstName.getText().toString().trim());
            erpLoanRequest.setDOB(etDob.getText().toString().trim());
            //  erpLoanRequest.setDOB(getMMDDYYYPattern(etDob.getText().toString().trim()));
            erpLoanRequest.setMiddle_Name(etFatherName.getText().toString().trim());
            erpLoanRequest.setLast_Name(etLastName.getText().toString().trim());
            erpLoanRequest.setGender(Gender);

            erpLoanRequest.setMarital_Status(MaritalStatus);
            erpLoanRequest.setSpouce_Name(etSpouceName.getText().toString().trim());
            erpLoanRequest.setNo_Of_Dependent(etNoOfDepen.getText().toString().trim());
            erpLoanRequest.setPAN_No(etPan.getText().toString().trim());
            erpLoanRequest.setNationality(etNationality.getText().toString().trim());

            erpLoanRequest.setStatus_Id(PL_STATUS);
            erpLoanRequest.setCategory_Id(PL_CATEGORY);
            erpLoanRequest.setId_Type(PL_IDTYPE);
            erpLoanRequest.setEducation_Id(PL_EDUCATION);
            erpLoanRequest.setId_No(etIDNumber.getText().toString().trim());

            erpLoanRequest.setInstitute_University(etUniversity.getText().toString().trim());
            erpLoanRequest.setMothers_Maidan_Name(etMoMaidenName.getText().toString().trim());
            //endregion

            //region Address_INFO
            erpLoanRequest.setPer_Email_Id(etEmailPersContInfo.getText().toString().trim());
            erpLoanRequest.setOfc_Email_Id(etEmailOffContInfo.getText().toString().trim());
            erpLoanRequest.setMobile_No1(etMobNo1ContInfo.getText().toString().trim());
            erpLoanRequest.setMobile_No2(etMobNo2ContInfo.getText().toString().trim());

            // region Address RAP
            erpLoanRequest.setAddress1(etAddress1ContInfoRAP.getText().toString().trim());
            erpLoanRequest.setAddress2(etAddress2ContInfoRAP.getText().toString().trim());
            erpLoanRequest.setAddress3(etAddress3ContInfoRAP.getText().toString().trim());
            erpLoanRequest.setLandmark(etLandmakContInfoRAP.getText().toString().trim());

            erpLoanRequest.setPincode(etPincodeContInfoRAP.getText().toString().trim());
            erpLoanRequest.setCity(etCityContInfoRAP.getText().toString().trim());
            erpLoanRequest.setState(etStateContInfoRAP.getText().toString().trim());
            erpLoanRequest.setCountry(etCountryPlRAP.getText().toString().trim());
            erpLoanRequest.setLandlineNo(etLandlineNoContInfoRAP.getText().toString().trim());
            erpLoanRequest.setAddrsYrs(etNoOfYrsAtOffContInfoRAP.getText().toString().trim());
            //endregion

            // region Address PA
            //   spResidence..getSelectedItem().toString();
            erpLoanRequest.setResidence_Type(spResidence.getSelectedItem().toString());

            erpLoanRequest.setPer_Address1(etAddress1ContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Address2(etAddress2ContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Address3(etAddress3ContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Landmark(etLandmakContInfoPA.getText().toString().trim());

            erpLoanRequest.setPer_Pincode(etPincodeContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_City(etCityContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_State(etStateContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_Country(etCountryPA.getText().toString().trim());
            erpLoanRequest.setPer_LandlineNo(etLandlineNoContInfoPA.getText().toString().trim());
            erpLoanRequest.setPer_AddrsYrs(etNoOfYrsAtOffContInfoPA.getText().toString().trim());
            //endregion

            //endregion

            //region EmploymentINFO

            erpLoanRequest.setNature_Of_Employer(EmpNature);
            erpLoanRequest.setNature_Of_Organization(spNatureOfOrg.getSelectedItem().toString());
            erpLoanRequest.setNature_Of_Business(spNatureOfBus.getSelectedItem().toString());
            erpLoanRequest.setDesignation(etDesig.getText().toString().trim());
            erpLoanRequest.setCurrnet_Employment_Period(etCurrJob.getText().toString().trim());

            erpLoanRequest.setTotal_Employment_Period(etTotalExp.getText().toString().trim());
            erpLoanRequest.setName_of_Organisation(etNameOfOrg.getText().toString().trim());
            erpLoanRequest.setTurn_Over(etTurnOver.getText().toString().trim());
            erpLoanRequest.setDepreciation(etDeprec.getText().toString().trim());
            erpLoanRequest.setDirector_Remuneration(etDirRem.getText().toString().trim());
            erpLoanRequest.setProfit_Aft_Tax(etProfAftTax.getText().toString().trim());

            erpLoanRequest.setAddress1_of_Organisation(etAddress1ED.getText().toString().trim());
            erpLoanRequest.setAddress2_of_Organisation(etAddress2ED.getText().toString().trim());
            erpLoanRequest.setAddress3_of_Organisation(etAddress3ED.getText().toString().trim());
            erpLoanRequest.setOrganize_Landmark(etLandmakED.getText().toString().trim());
            erpLoanRequest.setOrganize_Pincode(etPincodeED.getText().toString().trim());

            erpLoanRequest.setOrganize_City(etCityED.getText().toString().trim());
            erpLoanRequest.setOrganize_State(etStateED.getText().toString().trim());
            erpLoanRequest.setOrganize_Country(etCountryED.getText().toString().trim());
            erpLoanRequest.setOrganize_LandlineNo(etLandlineNoED.getText().toString().trim());


            //endregion

            //region Financial INFO
            erpLoanRequest.setGross_Income(etGrossIncome.getText().toString().trim());
            erpLoanRequest.setNet_Income(etNetIncome.getText().toString().trim());
            erpLoanRequest.setOther_Income(etOtherIncome.getText().toString().trim());
            erpLoanRequest.setTotal_Income(etTotalIncome.getText().toString().trim());
            //endregion

            // region For Quote and Query string

            erpLoanRequest.setBankId(buyLoanQuerystring.getBankId());//qurystring
            erpLoanRequest.setQuote_id(buyLoanQuerystring.getQuote_id());//qurystring

            if (rbCustomerEntity.getBrokerId() != null) {
                erpLoanRequest.setBrokerId(Integer.valueOf(rbCustomerEntity.getBrokerId()));//Loanid

            } else {
                erpLoanRequest.setBrokerId(0);
            }


            erpLoanRequest.setProductId(rbCustomerEntity.getProductId());
            //  erpLoanRequest.setIsCoApp(0);

            if (!buyLoanQuerystring.getProp_Loan_Eligible().equals("")) {

                erpLoanRequest.setLoan_Amount(buyLoanQuerystring.getProp_Loan_Eligible());

            } else {
                erpLoanRequest.setLoan_Amount("0");
            }

            if (!rbCustomerEntity.getLoanTenure().equals("")) {

                erpLoanRequest.setLoan_Terms(rbCustomerEntity.getLoanTenure());

            } else {
                erpLoanRequest.setLoan_Terms("0");
            }


            // erpLoanRequest.setLoan_Terms(rbCustomerEntity.getLoanTenure());
            erpLoanRequest.setROI_Id_Type(rbCustomerEntity.getRoi_type());  /// 05
            erpLoanRequest.setProcessing_Fee(rbCustomerEntity.getProcessing_fee());
            if (AppID.trim().equals("")) {
                erpLoanRequest.setApplnId(0);
            } else {
                erpLoanRequest.setApplnId(Integer.valueOf(AppID));
            }
            erpLoanRequest.setIs_ApplnComplete(SubmitType);//submit final
            erpLoanRequest.setIs_Confirm(0);
            erpLoanRequest.setAppln_Source("PL");
            erpLoanRequest.setDc_fba_reg(String.valueOf(loginEntity.getFBAId()));
            erpLoanRequest.setRBA_Source("Finmart");
            erpLoanRequest.setFBA_Reg_Id(String.valueOf(loginEntity.getFBAId()));

            //endregion

            if (SubmitType == 1) {
                showDialog("Please wait...");
            }

            new TrackingController(this).sendData(new TrackingRequestEntity(new TrackingData("Personal Loan : Application Save"), Constants.PERSONA_LOAN), null);

            MyApplication.getInstance().trackEvent( Constants.PERSONA_LOAN,"Clicked","Personal Loan : Application Save");

            new ErpLoanController(this).saveERPPersonalLoan(erpLoanRequest, PersonalLoanApplyActivity.this);


        }
    }


    //  region Spiiner Position
    private int getTitlePos(String strTitle) {
        int pos = 0;
        String[] arrTitle = getResources().getStringArray(R.array.title);
        for (int i = 0; i < arrTitle.length; i++) {

            if (arrTitle[i].toLowerCase().equals(strTitle.toLowerCase())) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private int getOrganizationPos(String strOrg) {
        int pos = 0;
        String[] arrOrg = getResources().getStringArray(R.array.organization);
        for (int i = 0; i < arrOrg.length; i++) {

            if (arrOrg[i].toLowerCase().equals(strOrg.toLowerCase())) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private int getBuisnessPos(String strTitle) {
        int pos = 0;
        String[] arrBuss = getResources().getStringArray(R.array.business);
        for (int i = 0; i < arrBuss.length; i++) {

            if (arrBuss[i].toLowerCase().equals(strTitle.toLowerCase())) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private int getResidencePos(String strTitle) {
        int pos = 0;
        String[] arrOwenship = getResources().getStringArray(R.array.ownership);
        for (int i = 0; i < arrOwenship.length; i++) {

            if (arrOwenship[i].toLowerCase().equals(strTitle.toLowerCase())) {
                pos = i;
                break;
            }
        }
        return pos;
    }

//    private int getResidencePos(String strTitle) {
//        String[] list = getResources().getStringArray(R.array.ownership);
//        for (int i = 0; i < list.length; i++) {
//            if (list[i].toLowerCase().equals(strTitle.toLowerCase())) {
//                return i;
//            }
//        }
//        return 0;
//    }
    //endregion

    private void setApplictionData() {
        isDataUploaded = false;
        //region PL_INFO
        etFirstName.setText(personalLoanApplyAppliEntity.getFirst_Name());
        etLastName.setText(personalLoanApplyAppliEntity.getLast_Name());

        etFatherName.setText(personalLoanApplyAppliEntity.getMiddle_Name());
        spTitle.setSelection(getTitlePos(personalLoanApplyAppliEntity.getTitle()));
       // etDob.setText(getDDMMYYYPattern(personalLoanApplyAppliEntity.getDOB(), "MM-dd-yyyy"));
        etDob.setText(personalLoanApplyAppliEntity.getDOB());

        if (personalLoanApplyAppliEntity.getGender().equals("Male")) {
            setMale_gender();
        } else {
            setFeMale_gender();
        }

        if (personalLoanApplyAppliEntity.getMarital_Status().equals("MARRIED")) {
            seMaritalStatus("MARRIED", txtMarried, txtSingle);
        } else {
            seMaritalStatus("SINGLE", txtSingle, txtMarried);
        }


        etSpouceName.setText(personalLoanApplyAppliEntity.getSpouce_Name());
        etNoOfDepen.setText(personalLoanApplyAppliEntity.getNo_Of_Dependent());
        etPan.setText(personalLoanApplyAppliEntity.getPAN_No());
        etNationality.setText(personalLoanApplyAppliEntity.getNationality());

//        managePL_Common(StatusType, personalLoanApplyAppliEntity.getStatus_Id(), txtRES, txtNRI, txtPIO, txtOCR, txtFOR);
//        managePL_Common(CategoryType, personalLoanApplyAppliEntity.getCategory_Id(), txtGEN, txtSC, txtST, txtOBC, txtOTH);
//        managePL_Common(EducationType, personalLoanApplyAppliEntity.getEducation_Id(), txtMATR, txtUGRAD, txtGRAD, txtPGRAD, txteducatOTH);
//        managePL_IDTYPE(personalLoanApplyAppliEntity.getId_Type(), txtPORT, txtVOTER, txtDRV);


        // region PL INFO Status
        if (personalLoanApplyAppliEntity.getStatus_Id().equals("RES")) {
            managePL_Common(StatusType, "RES", txtRES, txtNRI, txtPIO, txtOCR, txtFOR);
        } else if (personalLoanApplyAppliEntity.getStatus_Id().equals("NRI")) {
            managePL_Common(StatusType, "NRI", txtNRI, txtRES, txtPIO, txtOCR, txtFOR);
        }
        if (personalLoanApplyAppliEntity.getStatus_Id().equals("PIO")) {
            managePL_Common(StatusType, "PIO", txtPIO, txtRES, txtNRI, txtOCR, txtFOR);
        }
        if (personalLoanApplyAppliEntity.getStatus_Id().equals("OCR")) {
            managePL_Common(StatusType, "OCR", txtOCR, txtRES, txtNRI, txtPIO, txtFOR);
        }
        if (personalLoanApplyAppliEntity.getStatus_Id().equals("FOR")) {
            managePL_Common(StatusType, "FOR", txtFOR, txtRES, txtNRI, txtPIO, txtOCR);
        }
        //endregion

        // region PL INFO Category
        if (personalLoanApplyAppliEntity.getCategory_Id().equals("GEN")) {
            managePL_Common(CategoryType, "GEN", txtGEN, txtSC, txtST, txtOBC, txtOTH);
        } else if (personalLoanApplyAppliEntity.getCategory_Id().equals("SC")) {
            managePL_Common(CategoryType, "SC", txtSC, txtGEN, txtST, txtOBC, txtOTH);
        }
        if (personalLoanApplyAppliEntity.getCategory_Id().equals("ST")) {
            managePL_Common(CategoryType, "ST", txtST, txtGEN, txtSC, txtOBC, txtOTH);
        }
        if (personalLoanApplyAppliEntity.getCategory_Id().equals("OBC")) {
            managePL_Common(CategoryType, "OBC", txtOBC, txtGEN, txtSC, txtST, txtOTH);
        }
        if (personalLoanApplyAppliEntity.getCategory_Id().equals("OTH")) {
            managePL_Common(CategoryType, "OTH", txtOTH, txtGEN, txtSC, txtST, txtOBC);
        }
        //endregion

        // region PL INFO Education
        if (personalLoanApplyAppliEntity.getEducation_Id().equals("MATR")) {
            managePL_Common(EducationType, "MATR", txtMATR, txtUGRAD, txtGRAD, txtPGRAD, txteducatOTH);
        } else if (personalLoanApplyAppliEntity.getEducation_Id().equals("U-GRAD")) {
            managePL_Common(EducationType, "U-GRAD", txtUGRAD, txtMATR, txtGRAD, txtPGRAD, txteducatOTH);
        }
        if (personalLoanApplyAppliEntity.getEducation_Id().equals("GRAD")) {
            managePL_Common(EducationType, "GRAD", txtGRAD, txtMATR, txtUGRAD, txtPGRAD, txteducatOTH);
        }
        if (personalLoanApplyAppliEntity.getEducation_Id().equals("P-GRAD")) {
            managePL_Common(EducationType, "P-GRAD", txtPGRAD, txtMATR, txtUGRAD, txtGRAD, txteducatOTH);
        }
        if (personalLoanApplyAppliEntity.getEducation_Id().equals("OTH")) {
            managePL_Common(EducationType, "OTH", txteducatOTH, txtPGRAD, txtMATR, txtUGRAD, txtGRAD);
        }
        //endregion

        // region ID Type
        if (personalLoanApplyAppliEntity.getId_Type().equals("P-PORT")) {
            managePL_IDTYPE("P-PORT", txtPORT, txtVOTER, txtDRV);
        } else if (personalLoanApplyAppliEntity.getId_Type().equals("VOTER")) {
            managePL_IDTYPE("VOTER", txtVOTER, txtPORT, txtDRV);
        } else if (personalLoanApplyAppliEntity.getId_Type().equals("DRV-L")) {
            managePL_IDTYPE("DRV-L", txtDRV, txtPORT, txtVOTER);
        }
        //endregion
        etIDNumber.setText(personalLoanApplyAppliEntity.getId_No());
        etUniversity.setText(personalLoanApplyAppliEntity.getInstitute_University());
        etMoMaidenName.setText(personalLoanApplyAppliEntity.getMothers_Maidan_Name());

        //endregion

        //region Address_INFO
        etEmailPersContInfo.setText(personalLoanApplyAppliEntity.getPer_Email_Id());
        etEmailOffContInfo.setText(personalLoanApplyAppliEntity.getOfc_Email_Id());
        etMobNo1ContInfo.setText(personalLoanApplyAppliEntity.getMobile_No1());
        etMobNo2ContInfo.setText(personalLoanApplyAppliEntity.getMobile_No2());

        // region Address RAP
        etAddress1ContInfoRAP.setText(personalLoanApplyAppliEntity.getAddress1());
        etAddress2ContInfoRAP.setText(personalLoanApplyAppliEntity.getAddress2());
        etAddress3ContInfoRAP.setText(personalLoanApplyAppliEntity.getAddress3());
        etLandmakContInfoRAP.setText(personalLoanApplyAppliEntity.getLandmark());

        etPincodeContInfoRAP.setText(personalLoanApplyAppliEntity.getPincode());
        etCityContInfoRAP.setText(personalLoanApplyAppliEntity.getCity());
        etStateContInfoRAP.setText(personalLoanApplyAppliEntity.getState());
        etCountryPlRAP.setText(personalLoanApplyAppliEntity.getCountry());
        etLandlineNoContInfoRAP.setText(personalLoanApplyAppliEntity.getLandlineNo());
        etNoOfYrsAtOffContInfoRAP.setText(personalLoanApplyAppliEntity.getAddrsYrs());
        //endregion

        // region Address PA
        //   spResidence
        spResidence.setSelection(getResidencePos(personalLoanApplyAppliEntity.getResidence_Type()));

        etAddress1ContInfoPA.setText(personalLoanApplyAppliEntity.getPer_Address1());
        etAddress2ContInfoPA.setText(personalLoanApplyAppliEntity.getPer_Address2());
        etAddress3ContInfoPA.setText(personalLoanApplyAppliEntity.getPer_Address3());
        etLandmakContInfoPA.setText(personalLoanApplyAppliEntity.getPer_Landmark());

        etPincodeContInfoPA.setText(personalLoanApplyAppliEntity.getPer_Pincode());
        etCityContInfoPA.setText(personalLoanApplyAppliEntity.getPer_City());
        etStateContInfoPA.setText(personalLoanApplyAppliEntity.getPer_State());
        etCountryPA.setText(personalLoanApplyAppliEntity.getPer_Country());
        etLandlineNoContInfoPA.setText(personalLoanApplyAppliEntity.getPer_LandlineNo());

        //05
        etNoOfYrsAtOffContInfoPA.setText(personalLoanApplyAppliEntity.getPer_AddrsYrs());
        //endregion

        //endregion

        //region EmploymentINFO

        if (personalLoanApplyAppliEntity.getNature_Of_Employer().equals("Salaried")) {
            setEmpSalaried("Salaried", false, txtEmpNatureSalaried, txtEmpNatureSelfEmp);
        } else {
            setEmpSalaried("Self-Emp", true, txtEmpNatureSelfEmp, txtEmpNatureSalaried);
        }


        spNatureOfOrg.setSelection(getOrganizationPos(personalLoanApplyAppliEntity.getNature_Of_Organization()));
        spNatureOfBus.setSelection(getBuisnessPos(personalLoanApplyAppliEntity.getNature_Of_Business()));
        etDesig.setText(personalLoanApplyAppliEntity.getDesignation());
        etCurrJob.setText(personalLoanApplyAppliEntity.getCurrnet_Employment_Period());

        etTotalExp.setText(personalLoanApplyAppliEntity.getTotal_Employment_Period());
        etNameOfOrg.setText(personalLoanApplyAppliEntity.getName_of_Organisation());

        if (personalLoanApplyAppliEntity.getTurn_Over() != null) {
            if (personalLoanApplyAppliEntity.getTurn_Over().toString().trim().equals("0")) {
                etTurnOver.setText("");
            } else {
                etTurnOver.setText(personalLoanApplyAppliEntity.getTurn_Over());
            }
        }

        if (personalLoanApplyAppliEntity.getDepreciation() != null) {
            if (personalLoanApplyAppliEntity.getDepreciation().toString().trim().equals("0")) {
                etDeprec.setText("");
            } else {
                etDeprec.setText(personalLoanApplyAppliEntity.getDepreciation());
            }
        }

        if (personalLoanApplyAppliEntity.getDirector_Remuneration() != null) {
            if (personalLoanApplyAppliEntity.getDirector_Remuneration().toString().trim().equals("0")) {
                etDirRem.setText("");
            } else {
                etDirRem.setText(personalLoanApplyAppliEntity.getDirector_Remuneration());
            }
        }
        if (personalLoanApplyAppliEntity.getProfit_Aft_Tax() != null) {
            if (personalLoanApplyAppliEntity.getProfit_Aft_Tax().toString().trim().equals("0")) {
                etProfAftTax.setText("");
            } else {
                etProfAftTax.setText(personalLoanApplyAppliEntity.getProfit_Aft_Tax());
            }
        }


        etAddress1ED.setText(personalLoanApplyAppliEntity.getAddress1_of_Organisation());
        etAddress2ED.setText(personalLoanApplyAppliEntity.getAddress2_of_Organisation());
        etAddress3ED.setText(personalLoanApplyAppliEntity.getAddress3_of_Organisation());
        etLandmakED.setText(personalLoanApplyAppliEntity.getOrganize_Landmark());
        etPincodeED.setText(personalLoanApplyAppliEntity.getOrganize_Pincode());

        etCityED.setText(personalLoanApplyAppliEntity.getOrganize_City());
        etStateED.setText(personalLoanApplyAppliEntity.getOrganize_State());
        etCountryED.setText(personalLoanApplyAppliEntity.getOrganize_Country());
        etLandlineNoED.setText(personalLoanApplyAppliEntity.getOrganize_LandlineNo());
        //endregion

        //region Financial INFO
        etGrossIncome.setText(personalLoanApplyAppliEntity.getGross_Income());
        etNetIncome.setText(personalLoanApplyAppliEntity.getNet_Income());
        etOtherIncome.setText(personalLoanApplyAppliEntity.getOther_Income());
        etTotalIncome.setText(personalLoanApplyAppliEntity.getTotal_Income());
        //endregion
    }

    // region Manage Layout

    private void managePL_Common(int Type, String Value, TextView clickedText, TextView textView1, TextView textView2, TextView textView3, TextView textView4) {


        if (Type == 1) {
            PL_STATUS = Value;
        } else if (Type == 2) {
            PL_CATEGORY = Value;
        }
        if (Type == 3) {
            PL_EDUCATION = Value;
        }
        clickedText.setBackgroundResource(R.drawable.customeborder_blue);
        clickedText.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, Utility.getPrimaryColor(this)));

        textView1.setBackgroundResource(R.drawable.customeborder);
        textView1.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));

        textView2.setBackgroundResource(R.drawable.customeborder);
        textView2.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));

        textView3.setBackgroundResource(R.drawable.customeborder);
        textView3.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));

        textView4.setBackgroundResource(R.drawable.customeborder);
        textView4.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));


    }

    private void managePL_IDTYPE(String Value, TextView clickedText, TextView textView1, TextView textView2) {

        PL_IDTYPE = Value;
        txtIdType.setVisibility(View.GONE);
        clickedText.setBackgroundResource(R.drawable.customeborder_blue);
        clickedText.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, Utility.getPrimaryColor(this)));

        textView1.setBackgroundResource(R.drawable.customeborder);
        textView1.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));

        textView2.setBackgroundResource(R.drawable.customeborder);
        textView2.setTextColor(ContextCompat.getColor(PersonalLoanApplyActivity.this, R.color.description_text));

    }

    private void manageTaskBar(int type) {
        if (type == 1) {
            if (checkPL_Info()) {
                ivPLInfoDone.setVisibility(View.VISIBLE);
                ivPLInfoPending.setVisibility(View.GONE);
            } else {
                ivPLInfoDone.setVisibility(View.INVISIBLE);
                ivPLInfoPending.setVisibility(View.VISIBLE);
            }
        } else if (type == 2) {
            if (checkAddress_Info()) {
                ivAddressDone.setVisibility(View.VISIBLE);
                ivAddressPending.setVisibility(View.GONE);
            } else {
                ivAddressDone.setVisibility(View.INVISIBLE);
                ivAddressPending.setVisibility(View.VISIBLE);
            }

        } else if (type == 3) {
            if (chkEmployment_Info()) {
                ivEmployDone.setVisibility(View.VISIBLE);
                ivEmployPending.setVisibility(View.GONE);
            } else {
                ivEmployDone.setVisibility(View.INVISIBLE);
                ivEmployPending.setVisibility(View.VISIBLE);
            }

        } else if (type == 4) {
            if (chkFinancial_Info()) {
                ivFinancialDone.setVisibility(View.VISIBLE);
                ivFinancialPending.setVisibility(View.GONE);
            } else {
                ivFinancialDone.setVisibility(View.INVISIBLE);
                ivFinancialPending.setVisibility(View.VISIBLE);
            }
        }
    }

    private void manageTaskBar() {

        if (checkPL_Info()) {
            ivPLInfoDone.setVisibility(View.VISIBLE);
            ivPLInfoPending.setVisibility(View.GONE);
        } else {
            ivPLInfoDone.setVisibility(View.INVISIBLE);
            ivPLInfoPending.setVisibility(View.VISIBLE);
        }
        ///////////////
        if (checkAddress_Info()) {
            ivAddressDone.setVisibility(View.VISIBLE);
            ivAddressPending.setVisibility(View.GONE);
        } else {
            ivAddressDone.setVisibility(View.INVISIBLE);
            ivAddressPending.setVisibility(View.VISIBLE);
        }
        ///////////////

        if (chkEmployment_Info()) {
            ivEmployDone.setVisibility(View.VISIBLE);
            ivEmployPending.setVisibility(View.GONE);
        } else {
            ivEmployDone.setVisibility(View.INVISIBLE);
            ivEmployPending.setVisibility(View.VISIBLE);
        }

        ///////////////
        if (chkFinancial_Info()) {
            ivFinancialDone.setVisibility(View.VISIBLE);
            ivFinancialPending.setVisibility(View.GONE);
        } else {
            ivFinancialDone.setVisibility(View.INVISIBLE);
            ivFinancialPending.setVisibility(View.VISIBLE);
        }

    }

    private void manageMainLayouts(LinearLayout visibleLayout, LinearLayout hideLayout1, LinearLayout hideLayout2, LinearLayout hideLayout3) {

        if (visibleLayout.getVisibility() == View.GONE) {
            visibleLayout.setVisibility(View.VISIBLE);
            hideLayout1.setVisibility(View.GONE);
            hideLayout2.setVisibility(View.GONE);
            hideLayout3.setVisibility(View.GONE);

        } else {
            visibleLayout.setVisibility(View.GONE);
        }
    }

    private void manageImages(LinearLayout clickedLayout, ImageView downImage, ImageView upImage1, ImageView upImage2, ImageView upImage3) {

        if (clickedLayout.getVisibility() == View.GONE) {
            downImage.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));  //up_arrow
            upImage1.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage2.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage3.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));

            isSubmit = false;

        } else {
            downImage.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));  //down_arrow
            upImage1.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage2.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage3.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));


        }

    }

    //endregion

    // region get Date Format

    private String getDDMMYYYPattern(String dateCal, String datePattern) {

        String dateSelected = "";
        if (dateCal.equals("")) {
            return "";
        }
        long select_milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat(datePattern);

        Date d = null;
        try {
            d = f.parse(dateCal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        select_milliseconds = d.getTime();

        Date date = new Date(select_milliseconds); //Another date Formate ie yyyy-mm-dd
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        dateSelected = df2.format(date);
        return dateSelected;
    }

    private String getMMDDYYYPattern(String dateCal) {

        String dateSelected = "";
        if (dateCal.equals("")) {
            return "";
        }
        long select_milliseconds = 0;
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");

        Date d = null;
        try {
            d = f.parse(dateCal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        select_milliseconds = d.getTime();

        Date date = new Date(select_milliseconds); //Another date Formate ie yyyy-mm-dd
        SimpleDateFormat df2 = new SimpleDateFormat("MM-dd-yyyy");
        dateSelected = df2.format(date);
        return dateSelected;
    }
    //endregion

    //region Data Display Method
    private void setRBCustomerData() {

        String[] name = rbCustomerEntity.getApplicantNme().split(" ");

        if(name.length >2)
        {
            for (int i = 0; i < name.length; i++) {
                if (i == 0) {
                    etFirstName.setText(name[i]);
                }
                if (i == 1)
                {
                    etFatherName.setText(name[i]);
                }
                if (i == name.length-1) {
                    etLastName.setText(name[i]);
                }
            }
        }else {
            for (int i = 0; i < name.length; i++) {
                if (i == 0) {
                    etFirstName.setText(name[i]);
                }
                if (i == 1) {
                    etLastName.setText(name[i]);
                }
            }
        }

        if (rbCustomerEntity.getApplicantGender() != null) {

            if (rbCustomerEntity.getApplicantGender().toUpperCase().equals("F")) {
                spTitle.setSelection(2);
            } else {
                spTitle.setSelection(0);
            }

        }
        if (rbCustomerEntity.getApplicantDOB() != null) {
            etDob.setTag(R.id.etDob, dateToCalendar(stringToDate(formatServer, rbCustomerEntity.getApplicantDOB())));

            etDob.setText(getDDMMYYYPattern(rbCustomerEntity.getApplicantDOB(), "yyyy-MM-dd"));
        }


        if (rbCustomerEntity.getApplicantGender().equals("M")) {
            setMale_gender();
        } else if (rbCustomerEntity.getApplicantGender().equals("F")) {
            setFeMale_gender();
        }
        if (rbCustomerEntity.getApplicantSource().equals("1")) {
            setEmpSalaried("Salaried", false, txtEmpNatureSalaried, txtEmpNatureSelfEmp);
        } else if (rbCustomerEntity.getApplicantSource().equals("2")) {
            setEmpSalaried("Self-Emp", true, txtEmpNatureSelfEmp, txtEmpNatureSalaried);
        }


        if (rbCustomerEntity.getApplicantIncome() != null) {
            if (rbCustomerEntity.getApplicantIncome().toString().trim().equals("0")) {
                etGrossIncome.setText("");
            } else {
                etGrossIncome.setText(rbCustomerEntity.getApplicantIncome());

            }
        }


        if (rbCustomerEntity.getTurnover() != null) {
            if (rbCustomerEntity.getTurnover().toString().trim().equals("0")) {
                etTurnOver.setText("");
            } else {
                etTurnOver.setText(rbCustomerEntity.getTurnover());

            }
        }

        if (rbCustomerEntity.getDepreciation() != null) {
            if (rbCustomerEntity.getDepreciation().toString().trim().equals("0")) {
                etDeprec.setText("");
            } else {
                etDeprec.setText(rbCustomerEntity.getDepreciation());
            }
        }

        if (rbCustomerEntity.getDirectorRemuneration() != null) {
            if (rbCustomerEntity.getDirectorRemuneration().toString().trim().equals("0")) {
                etDirRem.setText("");
            } else {
                etDirRem.setText(rbCustomerEntity.getDirectorRemuneration());
            }
        }

        if (rbCustomerEntity.getProfitAfterTax() != null) {
            if (rbCustomerEntity.getProfitAfterTax().toString().trim().equals("0")) {
                etProfAftTax.setText("");
            } else {
                etProfAftTax.setText(rbCustomerEntity.getProfitAfterTax());
            }
        }

    }

    private void setRbCustomerData2() {
        etMobNo1ContInfo.setText(buyLoanQuerystring.getMobileNo());
        etPan.setText(buyLoanQuerystring.getPan());
    }

    //endregion


    //endregion


    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogApplicant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, PersonalLoanApplyActivity.this);
            if (view.getId() == R.id.etDob) {
                DateTimePicker.showDataPickerDialogBeforeTwentyOneTest(view.getContext(), (Calendar) view.getTag(R.id.etDob),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                //TODO:set tag to DOB -- nilesh
                                //Calendar calSelectedPrev = Calendar.getInstance();

                                calendar.set(year, monthOfYear, dayOfMonth);
                                //calSelectedPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                etDob.setText(currentDay);
                                //TODO:set tag to DOB -- nilesh
                                etDob.setTag(R.id.etDob, calendar);
                                //etDate.setTag(R.id.et_date, calendar.getTime());
                            }
                        });
            }
        }
    };
    //endregion
    // region Validate

    //  region Validate Field

    private boolean validatePL_Info() {

        if (!isEmpty(etFirstName)) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etFirstName.requestFocus();
                etFirstName.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etFirstName.setError("Enter First Name");
                return false;
            } else {
                etFirstName.requestFocus();
                etFirstName.setError("Enter First Name");
                return false;
            }
        }

        if (!isEmpty(etLastName)) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etLastName.requestFocus();
                etLastName.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etLastName.setError("Enter Last Name");
                return false;
            } else {
                etLastName.requestFocus();
                etLastName.setError("Enter Last Name");
                return false;
            }
        }
        if (!isEmpty(etDob)) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etDob.requestFocus();
                etDob.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etDob.setError("Enter Date Of Birth");
                return false;
            } else {
                etDob.requestFocus();
                etDob.setError("Enter Date Of Birth");
                return false;
            }
        }

        //

        if (MaritalStatus.equals("MARRIED")) {

            if (!isEmpty(etSpouceName)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etSpouceName.requestFocus();
                    etSpouceName.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etSpouceName.setError("Enter Spouce Name");
                    return false;
                } else {
                    etSpouceName.requestFocus();
                    etSpouceName.setError("Enter Spouce Name");
                    return false;
                }
            }
        }

        if (!isEmpty(etPan)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etPan.requestFocus();
                etPan.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etPan.setError("Enter PAN");
                return false;
            } else {
                etPan.requestFocus();
                etPan.setError("Enter PAN");
                return false;
            }
        }

        if (!isValidPan(etPan)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etPan.requestFocus();
                etPan.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etPan.setError("Enter Valid PAN");
                return false;
            } else {
                etPan.requestFocus();
                etPan.setError("Enter Valid PAN");
                return false;
            }
        }

        if (PL_IDTYPE.equals("")) {
            txtIdType.setVisibility(View.VISIBLE);
            etNoOfDepen.requestFocus();
            return false;
        }

        if (!isEmpty(etMoMaidenName)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etMoMaidenName.requestFocus();
                etMoMaidenName.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMoMaidenName.setError("Enter Mother's Maiden Name");
                return false;
            } else {
                etMoMaidenName.requestFocus();
                etMoMaidenName.setError("Enter Mother's Maiden Name");
                return false;
            }
        }
        return true;
    }

    private boolean validateAddress_Info() {

        if (!isEmpty(etEmailPersContInfo)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etEmailPersContInfo.requestFocus();
                etEmailPersContInfo.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etEmailPersContInfo.setError("Enter Email ID");
                return false;
            } else {
                etEmailPersContInfo.requestFocus();
                etEmailPersContInfo.setError("Enter Email ID");
                return false;
            }
        } else if (!isValideEmailID(etEmailPersContInfo)) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etEmailPersContInfo.requestFocus();
                etEmailPersContInfo.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etEmailPersContInfo.setError("Enter Valid Email ID");
                return false;

            } else {
                etEmailPersContInfo.requestFocus();
                etEmailPersContInfo.setError("Enter Valid Email ID");
                return false;

            }
        } else if (!isEmpty(etMobNo1ContInfo)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etMobNo1ContInfo.requestFocus();
                etMobNo1ContInfo.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMobNo1ContInfo.setError("Enter Mobile Number");
                return false;

            } else {
                etMobNo1ContInfo.requestFocus();
                etMobNo1ContInfo.setError("Enter Mobile Number");
                return false;

            }
        } else if (etMobNo1ContInfo.getText().length() < 10) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etMobNo1ContInfo.requestFocus();
                etMobNo1ContInfo.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMobNo1ContInfo.setError("Enter Valid Mobile Number");
                return false;

            } else {
                etMobNo1ContInfo.requestFocus();
                etMobNo1ContInfo.setError("Enter Valid Mobile Number");
                return false;
            }
        } else if (!isEmpty(etAddress1ContInfoRAP)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etAddress1ContInfoRAP.requestFocus();
                etAddress1ContInfoRAP.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etAddress1ContInfoRAP.setError("Enter Address");
                return false;

            } else {
                etAddress1ContInfoRAP.requestFocus();
                etAddress1ContInfoRAP.setError("Enter Address");
                return false;

            }
        } else if (!isEmpty(etPincodeContInfoRAP)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etPincodeContInfoRAP.requestFocus();
                etPincodeContInfoRAP.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etPincodeContInfoRAP.setError("Enter Pincode");
                return false;

            } else {
                etPincodeContInfoRAP.requestFocus();
                etPincodeContInfoRAP.setError("Enter Pincode");
                return false;

            }
        } else if (etPincodeContInfoRAP.getText().length() < 6) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etPincodeContInfoRAP.requestFocus();
                etPincodeContInfoRAP.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etPincodeContInfoRAP.setError("Enter Valid Pincode");
                return false;

            } else {
                etPincodeContInfoRAP.requestFocus();
                etPincodeContInfoRAP.setError("Enter Valid Pincode");
                return false;
            }
        } else if (!isEmpty(etCityContInfoRAP)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etCityContInfoRAP.requestFocus();
                etCityContInfoRAP.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etCityContInfoRAP.setError("Enter City");
                return false;

            } else {
                etCityContInfoRAP.requestFocus();
                etCityContInfoRAP.setError("Enter City");
                return false;

            }
        } else if (!isEmpty(etStateContInfoRAP)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etStateContInfoRAP.requestFocus();
                etStateContInfoRAP.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etStateContInfoRAP.setError("Enter State");
                return false;

            } else {
                etStateContInfoRAP.requestFocus();
                etStateContInfoRAP.setError("Enter State");
                return false;

            }
        } else if (!isEmpty(etNoOfYrsAtOffContInfoRAP)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etNoOfYrsAtOffContInfoRAP.requestFocus();
                etNoOfYrsAtOffContInfoRAP.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etNoOfYrsAtOffContInfoRAP.setError("Enter No. of Years At This Address");
                return false;

            } else {
                etNoOfYrsAtOffContInfoRAP.requestFocus();
                etNoOfYrsAtOffContInfoRAP.setError("Enter No. of Years At This Address");
                return false;

            }
        }
        return true;
    }

    private boolean validateEmployment_Info() {


//        if (spNatureOfOrg.getSelectedItem().toString().equals("0")) {
//            Snackbar.make(etCurrJob, "Select Nature Of Organisation", Snackbar.LENGTH_SHORT).show();
//            return false;
//
//        } else if (spNatureOfBus.getSelectedItem().toString().equals("0")) {
//            Snackbar.make(etCurrJob, "Select Nature Of Business", Snackbar.LENGTH_SHORT).show();
//            return false;
//        } else


        if (!isEmpty(etDesig)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etDesig.requestFocus();
                etDesig.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etDesig.setError("Enter Designation");
                return false;
            } else {
                etDesig.requestFocus();
                etDesig.setError("Enter Designation");
                return false;
            }
        }

        if (EmpNature.equals("Salaried")) {

            // region Salaried
            if (!isEmpty(etCurrJob)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etCurrJob.requestFocus();
                    etCurrJob.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etCurrJob.setError("Enter Current Job");
                    return false;

                } else {
                    etCurrJob.requestFocus();
                    etCurrJob.setError("Enter Current Job");
                    return false;

                }
            } else if (!isEmpty(etTotalExp)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etTotalExp.requestFocus();
                    etTotalExp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etTotalExp.setError("Enter Total Exp.");
                    return false;

                } else {
                    etTotalExp.requestFocus();
                    etTotalExp.setError("Enter Total Exp.");
                    return false;

                }
            }
            //endregion
        } else {

            //region Self-Employee
            if (!isEmpty(etTurnOver)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etTurnOver.requestFocus();
                    etTurnOver.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etTurnOver.setError("Enter Turn Over");
                    return false;

                } else {
                    etTurnOver.requestFocus();
                    etTurnOver.setError("Enter Turn Over");
                    return false;

                }
            } else if (!isEmpty(etDeprec)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etDeprec.requestFocus();
                    etDeprec.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etDeprec.setError("Enter Depreciation");
                    return false;

                } else {
                    etDeprec.requestFocus();
                    etDeprec.setError("Enter Depreciation");
                    return false;

                }
            } else if (!isEmpty(etDirRem)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etDirRem.requestFocus();
                    etDirRem.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etDirRem.setError("Enter Director's Remuneration");
                    return false;

                } else {
                    etDirRem.requestFocus();
                    etDirRem.setError("Enter Director's Remuneration");
                    return false;

                }
            } else if (!isEmpty(etProfAftTax)) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    etProfAftTax.requestFocus();
                    etProfAftTax.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    etProfAftTax.setError("Enter Profit After Tax");
                    return false;

                } else {
                    etProfAftTax.requestFocus();
                    etProfAftTax.setError("Enter Profit After Tax");
                    return false;

                }
            }

            //endregion
        }

        if (!isEmpty(etNameOfOrg)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etNameOfOrg.requestFocus();
                etNameOfOrg.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etNameOfOrg.setError("Enter Name Of Organisation");
                return false;

            } else {
                etNameOfOrg.requestFocus();
                etNameOfOrg.setError("Enter Name Of Organisation");
                return false;

            }
        }


        if (!isEmpty(etAddress1ED)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etAddress1ED.requestFocus();
                etAddress1ED.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etAddress1ED.setError("Enter Address");
                return false;

            } else {
                etAddress1ED.requestFocus();
                etAddress1ED.setError("Enter Address");
                return false;

            }
        } else if (!isEmpty(etPincodeED)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etPincodeED.requestFocus();
                etPincodeED.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etPincodeED.setError("Enter Pincode");
                return false;

            } else {
                etPincodeED.requestFocus();
                etPincodeED.setError("Enter Pincode");
                return false;

            }
        } else if (etPincodeED.getText().length() < 6) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etPincodeED.requestFocus();
                etPincodeED.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etPincodeED.setError("Enter Valid Pincode");
                return false;

            } else {
                etPincodeED.requestFocus();
                etPincodeED.setError("Enter Valid Pincode");
                return false;
            }
        } else if (!isEmpty(etCityED)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etCityED.requestFocus();
                etCityED.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etCityED.setError("Enter City");
                return false;

            } else {
                etCityED.requestFocus();
                etCityED.setError("Enter City");
                return false;

            }
        } else if (!isEmpty(etStateED)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etStateED.requestFocus();
                etStateED.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etStateED.setError("Enter State");
                return false;

            } else {
                etStateED.requestFocus();
                etStateED.setError("Enter State");
                return false;

            }
        }

        return true;
    }

    private boolean validateFinancial_Info() {

        if (!isEmpty(etNetIncome)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etNetIncome.requestFocus();
                etNetIncome.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etNetIncome.setError("Enter Net Income");
                return false;
            } else {
                etNetIncome.requestFocus();
                etNetIncome.setError("Enter Net Income");
                return false;
            }
        }
        return true;
    }

    //endregion

    // region Check Validation For Pending / Done Status Image

    private boolean checkPL_Info() {

        if (!isEmpty(etFirstName)) {
            return false;
        }

        if (!isEmpty(etLastName)) {
            return false;
        }


        if (MaritalStatus.equals("MARRIED")) {
            if (!isEmpty(etSpouceName)) {

                return false;
            }
        }
        if (!isEmpty(etPan)) {
            return false;
        }

        if (!isValidPan(etPan)) {
            return false;
        }

        if (PL_IDTYPE.equals("")) {
            return false;
        }

        if (!isEmpty(etMoMaidenName)) {
            return false;
        }
        return true;
    }

    private boolean checkAddress_Info() {

        if (!isEmpty(etEmailPersContInfo)) {

            return false;
        } else if (!isValideEmailID(etEmailPersContInfo)) {

            return false;
        } else if (!isEmpty(etMobNo1ContInfo)) {

            return false;
        } else if (etMobNo1ContInfo.getText().length() < 10) {

        } else if (!isEmpty(etAddress1ContInfoRAP)) {

            return false;
        } else if (!isEmpty(etPincodeContInfoRAP)) {
            return false;

        } else if (etPincodeContInfoRAP.getText().length() < 6) {
            return false;

        } else if (!isEmpty(etCityContInfoRAP)) {
            return false;

        } else if (!isEmpty(etStateContInfoRAP)) {
            return false;

        } else if (!isEmpty(etNoOfYrsAtOffContInfoRAP)) {
            return false;

        }
        return true;
    }

    private boolean chkEmployment_Info() {

        if (!isEmpty(etDesig)) {
            return false;

        }
        if (EmpNature.equals("Salaried")) {
            if (!isEmpty(etCurrJob)) {

                return false;
            } else if (!isEmpty(etTotalExp)) {

                return false;
            }
        } else {
            if (!isEmpty(etTurnOver)) {

                return false;
            } else if (!isEmpty(etDeprec)) {

                return false;
            } else if (!isEmpty(etDirRem)) {

                return false;
            } else if (!isEmpty(etProfAftTax)) {

                return false;
            }
        }

        if (!isEmpty(etNameOfOrg)) {

            return false;
        } else if (!isEmpty(etAddress1ED)) {
            return false;

        } else if (!isEmpty(etPincodeED)) {
            return false;

        } else if (etPincodeED.getText().length() < 6) {
            return false;

        } else if (!isEmpty(etCityED)) {
            return false;

        } else if (!isEmpty(etStateED)) {
            return false;

        }

        return true;
    }

    private boolean chkFinancial_Info() {

        if (!isEmpty(etNetIncome)) {

            return false;
        }
        return true;
    }

    //endregion

    //endregion

    //region Event

    @Override
    public void onClick(View view) {
        Constants.hideKeyBoard(view, this);
        int i = view.getId();
        if (i == R.id.ivPLInfo || i == R.id.rlPLInfo) {
            manageMainLayouts(llPlInfo, llAddress, llEmployment, llFinancial);
            manageImages(llPlInfo, ivPLInfo, ivEmploy, ivAddress, ivFinancial);//
            // manageTaskBar(1);
            manageTaskBar();
            saveData(0);

        } else if (i == R.id.ivAddress || i == R.id.rlAddress) {
            manageMainLayouts(llAddress, llPlInfo, llEmployment, llFinancial);
            manageImages(llAddress, ivAddress, ivEmploy, ivPLInfo, ivFinancial);
            manageTaskBar();
            saveData(0);

        } else if (i == R.id.ivEmploy || i == R.id.rlEmployment) {
            manageMainLayouts(llEmployment, llPlInfo, llAddress, llFinancial);
            manageImages(llEmployment, ivEmploy, ivPLInfo, ivAddress, ivFinancial);
            manageTaskBar();
            saveData(0);

        } else if (i == R.id.ivFinancial || i == R.id.rlFinancial) {
            manageMainLayouts(llFinancial, llPlInfo, llAddress, llEmployment);
            manageImages(llFinancial, ivFinancial, ivPLInfo, ivAddress, ivEmploy);
            manageTaskBar();
            saveData(0);

        } else if (i == R.id.ivMale) {
            setMale_gender();

        } else if (i == R.id.ivFemale) {
            setFeMale_gender();

        } else if (i == R.id.txtMarried) {
            seMaritalStatus("MARRIED", txtMarried, txtSingle);


        } else if (i == R.id.txtSingle) {
            seMaritalStatus("SINGLE", txtSingle, txtMarried);


            //seMaritalStatus
            //region PL INFO Status
        } else if (i == R.id.txtRES) {
            managePL_Common(StatusType, "RES", txtRES, txtNRI, txtPIO, txtOCR, txtFOR);

        } else if (i == R.id.txtNRI) {
            managePL_Common(StatusType, "NRI", txtNRI, txtRES, txtPIO, txtOCR, txtFOR);

        } else if (i == R.id.txtPIO) {
            managePL_Common(StatusType, "PIO", txtPIO, txtRES, txtNRI, txtOCR, txtFOR);

        } else if (i == R.id.txtOCR) {
            managePL_Common(StatusType, "OCR", txtOCR, txtRES, txtNRI, txtPIO, txtFOR);

        } else if (i == R.id.txtFOR) {
            managePL_Common(StatusType, "FOR", txtFOR, txtRES, txtNRI, txtPIO, txtOCR);

            //endregion

            //region PL INFO Category
        } else if (i == R.id.txtGEN) {
            managePL_Common(CategoryType, "GEN", txtGEN, txtSC, txtST, txtOBC, txtOTH);

        } else if (i == R.id.txtSC) {
            managePL_Common(CategoryType, "SC", txtSC, txtGEN, txtST, txtOBC, txtOTH);

        } else if (i == R.id.txtST) {
            managePL_Common(CategoryType, "ST", txtST, txtGEN, txtSC, txtOBC, txtOTH);

        } else if (i == R.id.txtOBC) {
            managePL_Common(CategoryType, "OBC", txtOBC, txtGEN, txtSC, txtST, txtOTH);

        } else if (i == R.id.txtOTH) {
            managePL_Common(CategoryType, "OTH", txtOTH, txtGEN, txtSC, txtST, txtOBC);

            //endregion

            // region PL INFO IDType
        } else if (i == R.id.txtPORT) {
            managePL_IDTYPE("P-PORT", txtPORT, txtVOTER, txtDRV);

        } else if (i == R.id.txtVOTER) {
            managePL_IDTYPE("VOTER", txtVOTER, txtPORT, txtDRV);

        } else if (i == R.id.txtDRV) {
            managePL_IDTYPE("DRV-L", txtDRV, txtPORT, txtVOTER);

            //endregion

            // region PL INFO Education
        } else if (i == R.id.txtMATR) {
            managePL_Common(EducationType, "MATR", txtMATR, txtUGRAD, txtGRAD, txtPGRAD, txteducatOTH);

        } else if (i == R.id.txtUGRAD) {
            managePL_Common(EducationType, "U-GRAD", txtUGRAD, txtMATR, txtGRAD, txtPGRAD, txteducatOTH);

        } else if (i == R.id.txtGRAD) {
            managePL_Common(EducationType, "GRAD", txtGRAD, txtMATR, txtUGRAD, txtPGRAD, txteducatOTH);

        } else if (i == R.id.txtPGRAD) {
            managePL_Common(EducationType, "P-GRAD", txtPGRAD, txtMATR, txtUGRAD, txtGRAD, txteducatOTH);

        } else if (i == R.id.txteducatOTH) {
            managePL_Common(EducationType, "OTH", txteducatOTH, txtPGRAD, txtMATR, txtUGRAD, txtGRAD);

            //endregion
        } else if (i == R.id.txtEmpNatureSalaried) {
            setEmpSalaried("Salaried", false, txtEmpNatureSalaried, txtEmpNatureSelfEmp);

        } else if (i == R.id.txtEmpNatureSelfEmp) {
            setEmpSalaried("Self-Emp", true, txtEmpNatureSelfEmp, txtEmpNatureSalaried);


            //setEmpSalaried
        } else if (i == R.id.btnSubmit) {
            manageTaskBar();
            if (validatePL_Info() == false) {
                if (llPlInfo.getVisibility() == View.GONE) {

                    manageMainLayouts(llPlInfo, llAddress, llEmployment, llFinancial);
                    manageImages(llPlInfo, ivPLInfo, ivEmploy, ivAddress, ivFinancial);//

                }
            } else if (validateAddress_Info() == false) {
                if (llAddress.getVisibility() == View.GONE) {

                    manageMainLayouts(llAddress, llPlInfo, llEmployment, llFinancial);
                    manageImages(llAddress, ivAddress, ivPLInfo, ivEmploy, ivFinancial);

                }
            } else if (validateEmployment_Info() == false) {
                if (llEmployment.getVisibility() == View.GONE) {

                    manageMainLayouts(llEmployment, llAddress, llPlInfo, llFinancial);
                    manageImages(llEmployment, ivEmploy, ivAddress, ivPLInfo, ivFinancial);

                }
            } else if (validateFinancial_Info() == false) {
                if (llFinancial.getVisibility() == View.GONE) {

                    manageMainLayouts(llFinancial, llEmployment, llAddress, llPlInfo);
                    manageImages(llFinancial, ivEmploy, ivFinancial, ivAddress, ivPLInfo);

                }
            } else {
                // manageTaskBar();
                isSubmit = true;
                saveData(1);
            }


        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            getResAddrToPermAddress();
        } else {
            clearPermAddress();
        }
    }


    //region Suceess Event

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();

        if (response instanceof RBCustomerResponse) {
            if (response.getStatus_Id() == 0) {

                rbCustomerEntity = ((RBCustomerResponse) response).getData();
                setRBCustomerData();

            }
        }
    }

    @Override
    public void OnSuccess(magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse response, String message) {

        cancelDialog();
        if (response instanceof PincodeResponse) {
            if (response.getStatusNo() == 0) {

                if (PAN_type == 1) {
                    etStateContInfoRAP.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                    etCityContInfoRAP.setText("" + ((PincodeResponse) response).getMasterData().getCityname());
                } else if (PAN_type == 2) {
                    etStateContInfoPA.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                    etCityContInfoPA.setText("" + ((PincodeResponse) response).getMasterData().getCityname());
                } else if (PAN_type == 3) {
                    etStateED.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                    etCityED.setText("" + ((PincodeResponse) response).getMasterData().getCityname());
                }

            } else {

                if (PAN_type == 1) {
                    etStateContInfoRAP.setText("");
                    etCityContInfoRAP.setText("");
                } else if (PAN_type == 2) {
                    etStateContInfoPA.setText("");
                    etCityContInfoPA.setText("");
                } else if (PAN_type == 3) {
                    etStateED.setText("");
                    etCityED.setText("");
                }
            }
        }
    }

    @Override
    public void OnSuccessERP(APIResponseERP response, String message) {

        cancelDialog();
        if (response instanceof ERPSaveResponse) {
            if (response.getStatusId() == 0) {
                AppID = "" + ((ERPSaveResponse) response).getResult();
                if (isSubmit) {
                    Toast.makeText(this, "Data save successfully..", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, PersonalLoanDetailActivity.class));
                    finish();
                }

            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            }
        } else if (response instanceof PersonalLoanApplicationResponse) {

            if (response.getStatusId() == 0) {

                personalLoanApplyAppliEntity = ((PersonalLoanApplicationResponse) response).getData();

                if (personalLoanApplyAppliEntity != null) {
                    setApplictionData();
                    manageTaskBar();
                }

            }

        }

    }


    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();

    }

    //endregion

    //region textwatcher
    TextWatcher pincodeRAPTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if (after < 6) {
                isDataUploaded = true;
            }
            if (start == 5) {
                etCityContInfoRAP.setText("");
                etStateContInfoRAP.setText("");
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ((s.length() == 6) && (isDataUploaded)) {
                PAN_type = 1;
                showDialog("Fetching City...");
                new RegisterController(PersonalLoanApplyActivity.this).getCityState(etPincodeContInfoRAP.getText().toString(), PersonalLoanApplyActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher pincodePATextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if (chkPresent.isChecked() && after == 6) {
                validatePA = false;
            } else {
                validatePA = true;
            }

            if (after < 6) {
                isDataUploaded = true;
            }
            if (start == 5) {
                etCityContInfoPA.setText("");
                etStateContInfoPA.setText("");
            }

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            if ((s.length() == 6) && (isDataUploaded)) {
                if (validatePA) {
                    PAN_type = 2;
                    showDialog("Fetching City...");

                    new RegisterController(PersonalLoanApplyActivity.this).getCityState(etPincodeContInfoPA.getText().toString(), PersonalLoanApplyActivity.this);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher pincodeEDTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (start == 5) {
                etCityED.setText("");
                etStateED.setText("");
            }
            if (after < 6) {
                isDataUploaded = true;
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ((s.length() == 6) && (isDataUploaded)) {
                PAN_type = 3;
                showDialog("Fetching City...");
                new RegisterController(PersonalLoanApplyActivity.this).getCityState(etPincodeED.getText().toString(), PersonalLoanApplyActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher grossIncomeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            settotal();

        }
    };


    //endregion

    // region Back event
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            onBackPressed();

            return true;
        } else if (i == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    //endregion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }


    //endregion


}
