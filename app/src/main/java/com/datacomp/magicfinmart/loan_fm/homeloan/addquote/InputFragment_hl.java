package com.datacomp.magicfinmart.loan_fm.homeloan.addquote;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.HomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.model.PropertyInfoEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment_hl extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {


    DBPersistanceController databaseController;   //DB declare
    LoginResponseEntity loginEntity;
    GetQuoteResponse getQuoteResponse;

    ScrollView scroll;
    TextView txtPropertyInfo, txtCoApplicantDetail, txtApplicantDetail;
    LinearLayout llPropertyInfo, llApplicantDetail, llCoApplicantDetail;
    Toolbar toolbar;
    HomeLoanRequest homeLoanRequest;
    FmHomeLoanRequest fmHomeLoanRequest;

    //display format
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    //server conversion date format
    SimpleDateFormat formatServer = new SimpleDateFormat("yyyy-MM-dd");


    boolean isPropertyInfoVisible = false;
    boolean isApplicantVisible = true;
    boolean isCoApplicantVisible = true;

    Button btnGetQuote;
    // region Applicant Details
    EditText etNameOfApplicant, et_DOB, etMonthlyInc, etEMI, etTurnOver, etProfitAtTax, etDepreciation, etDirecPartRemuntion,etContact;

    ArrayAdapter<String> salaryTypeAdapter;
    LinearLayout llSalaried, llSelfEmployeed, lyParent_CoAppDetail, coApp_llView1MothlyIncome;

    CheckBox chkCoApplicant;

    RadioButton rbReady, rbUnder, rbSearching, rbResale, rbForcons, rbOther;

    String propTyp = "1";
    String CoApplicantSource = "1";
    String ApplicantSource = "1";
    String GenderApplicantSource = "M";
    String GenderCoApplicantSource = "F";
    //endregion

    //region PropertyIndo
    EditText etCostOfProp, txtMaxLoanAmntAllow;
    TextView txtDispalayMinCostProp, txtDispalayMaxCostProp, txtDispalayMinTenureYear, txtDispalayMaxTenureYear;
    TextView textCoApplicant, txtCoSalaried, txtCoSelfEMp, txtSalaried, txtSelfEMp,txtco_app_rbimgMale,txtco_app_rbimgFemale,txtrbimgMale,txtrbimgFemale;

    ArrayList<String> arrayNewLoan, arrayPreferedCity;

    ArrayAdapter<String> preferedCityAdapter;
    SeekBar sbTenure;

    TextView etTenureInYear;


    //endregion

    //region Co-Applicant Details
    EditText coApp_etNameOfApplicant, coApp_et_DOB, coApp_etMonthlyInc, coApp_etEMI, coApp_etTurnOver, coApp_etProfitAtTax, coApp_etDepreciation, coApp_etDirecPartRemuntion;
    Spinner coApp_sbRelation;
    ArrayAdapter<String> coApp_salaryTypeAdapter, coApp_relationTypeAdapter;
    LinearLayout coApp_llSalaried, coApp_llSelfEmployeed;


    private RadioGroup rgProperty1;
    private RadioGroup rgProperty2;
    AutoCompleteTextView acCity;
    DBPersistanceController mReal;
    List<String> cityList;
    //endregion
    Context mContext;
    int LoanRequireID = 0;
    long  int_etTurnOver=0,int_etProfitAtTax=0,int_etDirecPartRemuntion=0,int_etDepreciation=0,int_etMonthlyInc=0,totalmonthlucalc_app;
    long int_coApp_etTurnOver=0,int_coApp_etProfitAtTax=0,int_coApp_etDirecPartRemuntion=0,int_coApp_etDepreciation=0,int_coApp_etMonthlyInc=0,totalmonthlucalc_coapp;

    public InputFragment_hl() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_home_loan, container, false);
        init_widgets(view);

        mReal = new DBPersistanceController(getActivity());

        databaseController = new DBPersistanceController(getActivity());
        loginEntity = databaseController.getUserData();
        cityList = databaseController.getHealthCity();
        setListener();
        loadSpinner();
        setSalaried("E");
        setCoAppSalaried("E");

        setApp_Male_gender();
        setCo_App_FeMale_gender();

        if (getArguments() != null) {
            if (getArguments().getParcelable(HLMainActivity.HL_INPUT_REQUEST) != null) {
                fmHomeLoanRequest = getArguments().getParcelable(HLMainActivity.HL_INPUT_REQUEST);
                homeLoanRequest = fmHomeLoanRequest.getHomeLoanRequest();
                fillCustomerDetails();

                visiblePropertyInfo(View.VISIBLE);
            }
        } else {
            fmHomeLoanRequest = new FmHomeLoanRequest();
            fmHomeLoanRequest.setFba_id(new DBPersistanceController(getContext()).getUserData().getFBAId());
            fmHomeLoanRequest.setLoan_requestID(0);
            homeLoanRequest = new HomeLoanRequest();
            fmHomeLoanRequest.setHomeLoanRequest(homeLoanRequest);
        }

        return view;
    }


    private void fillCustomerDetails() {
        Log.d("DETAILS", homeLoanRequest.toString());

        try {
            //  homeLoanRequest

            if (homeLoanRequest.getPropertyID() != null)

                if (homeLoanRequest.getPropertyID().matches("1")) {
                    rbReady.setChecked(true);
                } else if (homeLoanRequest.getPropertyID().matches("2")) {
                    rbUnder.setChecked(true);
                } else if (homeLoanRequest.getPropertyID().matches("3")) {
                    rbSearching.setChecked(true);
                } else if (homeLoanRequest.getPropertyID().matches("4")) {
                    rbResale.setChecked(true);
                } else if (homeLoanRequest.getPropertyID().matches("5")) {
                    rbForcons.setChecked(true);
                } else if (homeLoanRequest.getPropertyID().matches("6")) {
                    rbOther.setChecked(true);
                }
            // spNewLoan.setSelection(newLoanAdapter.getPosition(homeLoanRequest.getPropertyID()));
            if (homeLoanRequest.getCoApplicantYes().matches("Y")) {
                chkCoApplicant.setChecked(true);
            } else {

            }


//            if (homeLoanRequest.getLoanRequired() != null)
            etCostOfProp.setText(homeLoanRequest.getPropertyCost());
            txtMaxLoanAmntAllow.setText(homeLoanRequest.getLoanRequired());

            if (homeLoanRequest.getLoanTenure() != null)
                etTenureInYear.setText(homeLoanRequest.getLoanTenure());


            etContact.setText(homeLoanRequest.getContact());


            int tenureInYear = Integer.parseInt(homeLoanRequest.getLoanTenure());
            sbTenure.setProgress(tenureInYear-5);
            if (homeLoanRequest.getCity() != null) {

                acCity.setText(homeLoanRequest.getCity());
                acCity.performCompletion();
            }

            if (homeLoanRequest.getApplicantNme() != null)
                etNameOfApplicant.setText(homeLoanRequest.getApplicantNme());


            if (homeLoanRequest.getApplicantSource().matches("1")) {

                setSalaried("E");


            } else {

                setSelfEmplyoee("E");
            }

            if (homeLoanRequest.getApplicantGender().matches("M")) {
                setApp_Male_gender();
            } else {
                setApp_FeMale_gender();
            }



            if (homeLoanRequest.getApplicantDOB() != null) {
                et_DOB.setTag(R.id.et_DOB, dateToCalendar(stringToDate(formatServer, homeLoanRequest.getApplicantDOB())));
                et_DOB.setText(getDDMMYYYPattern(homeLoanRequest.getApplicantDOB(), "yyyy-MM-dd"));


            }


//            if (homeLoanRequest.getApplicantSource() != null) {
//                sbSalary.setSelection(Integer.parseInt(homeLoanRequest.getApplicantSource()) - 1);
//            }
            if (homeLoanRequest.getApplicantIncome() != null)
                etMonthlyInc.setText(homeLoanRequest.getApplicantIncome());

            if (homeLoanRequest.getApplicantObligations() != null)
                etEMI.setText(homeLoanRequest.getApplicantObligations());

            if (homeLoanRequest.getTurnover() != null)
                etTurnOver.setText(homeLoanRequest.getTurnover());

            if (homeLoanRequest.getProfitAfterTax() != null)
                etProfitAtTax.setText(homeLoanRequest.getProfitAfterTax());

            if (homeLoanRequest.getDepreciation() != null)
                etDepreciation.setText(homeLoanRequest.getDepreciation());

            if (homeLoanRequest.getDirectorRemuneration() != null)
                etDirecPartRemuntion.setText(homeLoanRequest.getDirectorRemuneration());

            if (homeLoanRequest.getCoApplicantYes() != null) {
                if (homeLoanRequest.getCoApplicantYes().matches("Y")) {


                    if (homeLoanRequest.getCoApplicantName() != null)
                        coApp_etNameOfApplicant.setText(homeLoanRequest.getCoApplicantName());


                    coApp_sbRelation.setSelection(getSelectedRelation(homeLoanRequest.getCoApplicantRelationt()));

                    if (homeLoanRequest.getCoApplicantGender().matches("M")) {
                        setCo_App_Male_gender();
                    } else {
                        setCo_App_FeMale_gender();
                    }

//                    if (homeLoanRequest.getCoApplicantIncome() != null)
//                        coApp_et_DOB.setText(simpleDateFormat.format(simpleDateFormat.parse(homeLoanRequest.getCoApplicantDOB())));
//



                    if (homeLoanRequest.getCoApplicantDOB() != null) {

                        coApp_et_DOB.setTag(R.id.coApp_et_DOB, dateToCalendar(stringToDate(formatServer, homeLoanRequest.getCoApplicantDOB())));
                        coApp_et_DOB.setText(getDDMMYYYPattern(homeLoanRequest.getCoApplicantDOB(), "yyyy-MM-dd"));
                    }

                    if (homeLoanRequest.getCoApplicantIncome() != null)

                        coApp_etMonthlyInc.setText(homeLoanRequest.getCoApplicantIncome());


                    if (homeLoanRequest.getCoApplicantObligations() != null)
                        coApp_etEMI.setText(homeLoanRequest.getCoApplicantObligations());

                    if (homeLoanRequest.getCoApplicantTurnover() != null)
                        coApp_etTurnOver.setText(homeLoanRequest.getCoApplicantTurnover());

                    if (homeLoanRequest.getCoApplicantProfitAfterTax() != null)
                        coApp_etProfitAtTax.setText(homeLoanRequest.getCoApplicantProfitAfterTax());

                    if (homeLoanRequest.getCoApplicantDepreciation() != null)
                        coApp_etDepreciation.setText(homeLoanRequest.getCoApplicantDepreciation());

                    if (homeLoanRequest.getCoApplicantDirectorRemuneration() != null)
                        coApp_etDirecPartRemuntion.setText(homeLoanRequest.getCoApplicantDirectorRemuneration());

                    /////////////// Co- Applicant//////////////////
                    if (homeLoanRequest.getCoApplicantSource().matches("1")) {

                        setCoAppSalaried("E");

                    } else if (homeLoanRequest.getCoApplicantSource().matches("2")) {

                        setCoAppSelfEmplyoee("E");

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private int getSelectedRelation(String relation) {
        String[] list = getResources().getStringArray(R.array.RelationType);
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(relation)) {
                return i;
            }
        }
        return 0;
    }


    private void init_widgets(View view) {

        //region Main Initialize
        scroll = (ScrollView) view.findViewById(R.id.scroll);
        txtPropertyInfo = (TextView) view.findViewById(R.id.txtPropertyInfo);
        txtCoApplicantDetail = (TextView) view.findViewById(R.id.txtCoApplicantDetail);
        txtApplicantDetail = (TextView) view.findViewById(R.id.txtApplicantDetail);
        llPropertyInfo = (LinearLayout) view.findViewById(R.id.llPropertyInfo);
        llApplicantDetail = (LinearLayout) view.findViewById(R.id.llApplicantDetail);
        llCoApplicantDetail = (LinearLayout) view.findViewById(R.id.llCoApplicantDetail);
        btnGetQuote = (Button) view.findViewById(R.id.btnGetQuote);

        rgProperty1 = (RadioGroup) view.findViewById(R.id.rgProperty1);
        rgProperty2 = (RadioGroup) view.findViewById(R.id.rgProperty2);

        rbReady = (RadioButton) rgProperty1.findViewById(R.id.rbReady);
        rbUnder = (RadioButton) rgProperty1.findViewById(R.id.rbUnder);
        rbSearching = (RadioButton) rgProperty1.findViewById(R.id.rbSearching);

        rbResale = (RadioButton) rgProperty2.findViewById(R.id.rbResale);
        rbForcons = (RadioButton) rgProperty2.findViewById(R.id.rbForcons);
        rbOther = (RadioButton) rgProperty2.findViewById(R.id.rbOther);

        rgProperty1.clearCheck(); // this is so we can start fresh, with no selection on both RadioGroups
        rgProperty1.clearCheck();
        rbReady.setChecked(true);
        rgProperty1.setOnCheckedChangeListener(rgProp1Listener);
        rgProperty2.setOnCheckedChangeListener(rgProp2Listener);


        //endregion

        //region Property Initialize


        etCostOfProp = (EditText) view.findViewById(R.id.etCostOfProp);
        etContact = (EditText) view.findViewById(R.id.etContact);


        txtMaxLoanAmntAllow = (EditText) view.findViewById(R.id.txtMaxLoanAmntAllow);


        txtDispalayMinTenureYear = (TextView) view.findViewById(R.id.txtDispalayMinTenureYear);
        txtDispalayMaxTenureYear = (TextView) view.findViewById(R.id.txtDispalayMaxTenureYear);
        etTenureInYear = (TextView) view.findViewById(R.id.etTenureInYear);

        sbTenure = (SeekBar) view.findViewById(R.id.sbTenure);

//
        sbTenure.setMax(25);

        sbTenure.setProgress(15);//////
        etTenureInYear.setText("20");//for Home loan default
        acCity = (AutoCompleteTextView) view.findViewById(R.id.acCity);

        //endregion

        //region Applicant Initialize
        llSelfEmployeed = (LinearLayout) view.findViewById(R.id.llSelfEmployeed);
        llSalaried = (LinearLayout) view.findViewById(R.id.llSalaried);
        lyParent_CoAppDetail = (LinearLayout) view.findViewById(R.id.lyParent_CoAppDetail);
        etNameOfApplicant = (EditText) view.findViewById(R.id.etNameOfApplicant);
        etTurnOver = (EditText) view.findViewById(R.id.etTurnOver);
        etProfitAtTax = (EditText) view.findViewById(R.id.etProfitAtTax);
        etDepreciation = (EditText) view.findViewById(R.id.etDepreciation);
        etDirecPartRemuntion = (EditText) view.findViewById(R.id.etDirecPartRemuntion);
        et_DOB = (EditText) view.findViewById(R.id.et_DOB);

        etMonthlyInc = (EditText) view.findViewById(R.id.etMonthlyInc);
        etEMI = (EditText) view.findViewById(R.id.etEMI);
        chkCoApplicant = (CheckBox) view.findViewById(R.id.chkCoApplicant);



        //endregion

        //region Co-Applicant Initialize
        coApp_llSelfEmployeed = (LinearLayout) view.findViewById(R.id.coApp_llSelfEmployeed);
        coApp_llSalaried = (LinearLayout) view.findViewById(R.id.coApp_llSalaried);

        txtSalaried = (TextView) view.findViewById(R.id.txtSalaried);
        txtSelfEMp = (TextView) view.findViewById(R.id.txtSelfEMp);

        textCoApplicant = (TextView) view.findViewById(R.id.textCoApplicant);
        txtCoSalaried = (TextView) view.findViewById(R.id.txtCoSalaried);
        txtCoSelfEMp = (TextView) view.findViewById(R.id.txtCoSelfEMp);

//radio male female

        txtrbimgMale = (TextView) view.findViewById(R.id.txtrbimgMale);
        txtrbimgFemale = (TextView) view.findViewById(R.id.txtrbimgFemale);
        txtco_app_rbimgMale = (TextView) view.findViewById(R.id.txtco_app_rbimgMale);
        txtco_app_rbimgFemale = (TextView) view.findViewById(R.id.txtco_app_rbimgFemale);


        coApp_llView1MothlyIncome = (LinearLayout) view.findViewById(R.id.coApp_llView1MothlyIncome);

        coApp_etNameOfApplicant = (EditText) view.findViewById(R.id.coApp_etNameOfApplicant);
        coApp_etTurnOver = (EditText) view.findViewById(R.id.coApp_etTurnOver);
        coApp_etProfitAtTax = (EditText) view.findViewById(R.id.coApp_etProfitAtTax);
        coApp_etDepreciation = (EditText) view.findViewById(R.id.coApp_etDepreciation);
        coApp_etDirecPartRemuntion = (EditText) view.findViewById(R.id.coApp_etDirecPartRemuntion);
        coApp_et_DOB = (EditText) view.findViewById(R.id.coApp_et_DOB);

        coApp_sbRelation = (Spinner) view.findViewById(R.id.coApp_sbRelation);

        coApp_etMonthlyInc = (EditText) view.findViewById(R.id.coApp_etMonthlyInc);
        coApp_etEMI = (EditText) view.findViewById(R.id.coApp_etEMI);

        //TODO:set tag to DOB

        et_DOB.setTag(R.id.et_DOB, dateToCalendar(stringToDate(simpleDateFormat, "01-01-1980")));
        et_DOB.setText("01-01-1980");

        coApp_et_DOB.setTag(R.id.coApp_et_DOB, dateToCalendar(stringToDate(simpleDateFormat, "01-01-1980")));
        coApp_et_DOB.setText("01-01-1980");

        //endregion


    }


    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogApplicant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, getActivity());
            if (view.getId() == R.id.et_DOB) {
                DateTimePicker.showDataPickerDialogBeforeTwentyOneTest(view.getContext(), (Calendar) view.getTag(R.id.et_DOB),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                //TODO:set tag to DOB -- nilesh
                                //Calendar calSelectedPrev = Calendar.getInstance();

                                calendar.set(year, monthOfYear, dayOfMonth);
                                //calSelectedPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                et_DOB.setText(currentDay);
                                //TODO:set tag to DOB -- nilesh
                                et_DOB.setTag(R.id.et_DOB, calendar);
                                //etDate.setTag(R.id.et_date, calendar.getTime());
                            }
                        });
            }
        }
    };
    //endregion



    //region datePickerDialog Applicant
    protected View.OnClickListener datePickerDialogCoApplicant = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Constants.hideKeyBoard(view, getActivity());
            if (view.getId() == R.id.coApp_et_DOB) {
                DateTimePicker.showDataPickerDialogBeforeTwentyOneTest(view.getContext(), (Calendar) view.getTag(R.id.coApp_et_DOB),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                Calendar calendar = Calendar.getInstance();
                                //TODO:set tag to DOB -- nilesh
                                //Calendar calSelectedPrev = Calendar.getInstance();

                                calendar.set(year, monthOfYear, dayOfMonth);
                                //calSelectedPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = simpleDateFormat.format(calendar.getTime());
                                coApp_et_DOB.setText(currentDay);
                                //TODO:set tag to DOB -- nilesh
                                coApp_et_DOB.setTag(R.id.coApp_et_DOB, calendar);
                                //etDate.setTag(R.id.et_date, calendar.getTime());
                            }
                        });
            }
        }
    };
    //endregion


    private void setListener() {

        sbTenure.setOnSeekBarChangeListener(this);
        txtSalaried.setOnClickListener(this);
        txtSelfEMp.setOnClickListener(this);
        txtCoSalaried.setOnClickListener(this);
        txtCoSelfEMp.setOnClickListener(this);

        txtrbimgMale.setOnClickListener(this);
        txtrbimgFemale.setOnClickListener(this);
        txtco_app_rbimgFemale.setOnClickListener(this);
        txtco_app_rbimgMale.setOnClickListener(this);


        btnGetQuote.setOnClickListener(this);
        et_DOB.setOnClickListener(datePickerDialogApplicant);
        coApp_et_DOB.setOnClickListener(datePickerDialogCoApplicant);

        //region CheckBox  Co-Applicant  Listener

        chkCoApplicant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    lyParent_CoAppDetail.setVisibility(View.VISIBLE);
                    textCoApplicant.setBackgroundResource(R.color.button_color);

                    //  coApp_etEMI.requestFocus();
                    Constants.hideKeyBoard(buttonView, getActivity());


                    scroll.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scroll.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    }, 1000);

                } else {

                    lyParent_CoAppDetail.setVisibility(View.GONE);
                    textCoApplicant.setBackgroundResource(R.color.secondary_text_color);

                }
            }
        });

        etCostOfProp.addTextChangedListener(this);
        etProfitAtTax.addTextChangedListener(this);
        etDirecPartRemuntion.addTextChangedListener(this);
        etDepreciation.addTextChangedListener(this);

        coApp_etDepreciation.addTextChangedListener(this);
        coApp_etDirecPartRemuntion.addTextChangedListener(this);
        coApp_etProfitAtTax.addTextChangedListener(this);
//
        //for validating auto complete city
        acCity.setOnFocusChangeListener(acCityFocusChange);

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
            }
        }
    };

    private void visiblePropertyInfo(int visibility) {
        if (visibility == View.VISIBLE) {
            isPropertyInfoVisible = true;
        } else {
            isPropertyInfoVisible = false;
        }
        if (visibility == View.GONE) {
          //  txtPropertyInfo.setText(" Property Information");
           // txtPropertyInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow_bas_screen, 0);
           // txtPropertyInfo.setBackgroundResource(R.color.lightGrey);//umesh
            llPropertyInfo.setVisibility(visibility);
            //isPropertyInfoVisible = false;
        } else {
           // txtPropertyInfo.setText(" Property Information");
           // txtPropertyInfo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.right_arrow_bas_screen, 0);
            //txtPropertyInfo.setBackgroundResource(R.color.lightGrey);//umesh
            llPropertyInfo.setVisibility(visibility);
            //isPropertyInfoVisible = true;
        }
    }



    private void setSalaried(String edit) {
        ApplicantSource = "1";
        txtSalaried.setBackgroundResource(R.drawable.customeborder_blue);
        txtSalaried.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtSelfEMp.setBackgroundResource(R.drawable.customeborder);
        txtSelfEMp.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        llSelfEmployeed.setVisibility(View.GONE);
        etMonthlyInc.setEnabled(true);
        if(edit.matches("E")) {

        }else {
            etMonthlyInc.setText("");
            etProfitAtTax.setText("");
            etDirecPartRemuntion.setText("");
            etDepreciation.setText("");
            etTurnOver.setText("");
        }


    }

    private void setSelfEmplyoee(String edit) {
        ApplicantSource = "2";
        txtSelfEMp.setBackgroundResource(R.drawable.customeborder_blue);
        txtSelfEMp.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtSalaried.setBackgroundResource(R.drawable.customeborder);
        txtSalaried.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        llSelfEmployeed.setVisibility(View.VISIBLE);
        etMonthlyInc.setEnabled(false);

        if(edit.matches("E")) {

        }else {
            etMonthlyInc.setText("");
            etProfitAtTax.setText("");
            etDirecPartRemuntion.setText("");
            etDepreciation.setText("");
            etTurnOver.setText("");
        }
    }


    private void setCoAppSalaried(String edit) {
        CoApplicantSource = "1";
        txtCoSalaried.setBackgroundResource(R.drawable.customeborder_blue);
        txtCoSalaried.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtCoSelfEMp.setBackgroundResource(R.drawable.customeborder);
        txtCoSelfEMp.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));
        coApp_etMonthlyInc.setEnabled(true);
        coApp_llSelfEmployeed.setVisibility(View.GONE);

        if(edit.matches("E")) {

        }else {
            coApp_etMonthlyInc.setText("");
            coApp_etProfitAtTax.setText("");
            coApp_etDirecPartRemuntion.setText("");
            coApp_etDepreciation.setText("");
            coApp_etTurnOver.setText("");
        }
    }

    private void setCoAppSelfEmplyoee(String edit) {
        CoApplicantSource = "2";
        txtCoSelfEMp.setBackgroundResource(R.drawable.customeborder_blue);
        txtCoSelfEMp.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtCoSalaried.setBackgroundResource(R.drawable.customeborder);
        txtCoSalaried.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        coApp_llSelfEmployeed.setVisibility(View.VISIBLE);
        coApp_etMonthlyInc.setEnabled(false);

        if(edit.matches("E")) {

        }else {
            coApp_etMonthlyInc.setText("");
            coApp_etProfitAtTax.setText("");
            coApp_etDirecPartRemuntion.setText("");
            coApp_etDepreciation.setText("");
            coApp_etTurnOver.setText("");
        }
    }

    //radio gender
 //   txtco_app_rbimgMale,txtco_app_rbimgFemale,txtrbimgMale,txtrbimgFemale
    private void setApp_Male_gender() {
        GenderApplicantSource = "M";
        txtrbimgMale.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgMale.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtrbimgFemale.setBackgroundResource(R.drawable.customeborder);
        txtrbimgFemale.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));


    }

    private void setApp_FeMale_gender() {
        GenderApplicantSource = "F";
        txtrbimgFemale.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgFemale.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtrbimgMale.setBackgroundResource(R.drawable.customeborder);
        txtrbimgMale.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));


    }

    private void setCo_App_Male_gender() {
        GenderCoApplicantSource = "M";
        txtco_app_rbimgMale.setBackgroundResource(R.drawable.customeborder_blue);
        txtco_app_rbimgMale.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtco_app_rbimgFemale.setBackgroundResource(R.drawable.customeborder);
        txtco_app_rbimgFemale.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));


    }

    private void setCo_App_FeMale_gender() {
        GenderCoApplicantSource = "F";
        txtco_app_rbimgFemale.setBackgroundResource(R.drawable.customeborder_blue);
        txtco_app_rbimgFemale.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtco_app_rbimgMale.setBackgroundResource(R.drawable.customeborder);
        txtco_app_rbimgMale.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));


    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.txtSalaried) {

            setSalaried("");


        } else if (v.getId() == R.id.txtSelfEMp) {

            setSelfEmplyoee("");
        }
        /////////////// Co- Applicant//////////////////
        else if (v.getId() == R.id.txtCoSalaried) {

            setCoAppSalaried("");

        } else if (v.getId() == R.id.txtCoSelfEMp) {

            setCoAppSelfEmplyoee("");

        }
        //gender txtco_app_rbimgMale,txtco_app_rbimgFemale,txtrbimgMale,txtrbimgFemale
        else if (v.getId() == R.id.txtrbimgMale) {

            setApp_Male_gender();
        }
        else if (v.getId() == R.id.txtrbimgFemale) {

            setApp_FeMale_gender();
        }
        else if (v.getId() == R.id.txtco_app_rbimgMale) {

            setCo_App_Male_gender();
        }
        else if (v.getId() == R.id.txtco_app_rbimgFemale) {

            setCo_App_FeMale_gender();
        }

        else if (v.getId() == R.id.btnGetQuote) {
            //region Validation
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Get quote HL : Get quote button for hl"), Constants.HOME_LOAN), null);

            MyApplication.getInstance().trackEvent( Constants.HOME_LOAN_QUOTES,"Clicked","Get quote HL : Get quote button for hl");

            //region Property Validation
            String CostOfProp = etCostOfProp.getText().toString();
            String TenureInYear = etTenureInYear.getText().toString();
            String MaxLoanAmntAllow = txtMaxLoanAmntAllow.getText().toString();
            String Contact  = etContact.getText().toString();

            if (TextUtils.isEmpty(CostOfProp)) {

                etCostOfProp.setError("Please Enter Cost Of Property.");
                etCostOfProp.requestFocus();
                return;

            }
            if (CostOfProp.length() < 6) {

                etCostOfProp.setError("Please Enter Cost Of Property six digit value.");
                etCostOfProp.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(MaxLoanAmntAllow)) {

                txtMaxLoanAmntAllow.setError("Please Enter  Required Loan.");
                txtMaxLoanAmntAllow.requestFocus();
                return;

            }

            if (Long.valueOf(MaxLoanAmntAllow) > Long.valueOf(CostOfProp) ) {

                txtMaxLoanAmntAllow.setError("Required Loan should not be greater than Cost Of Property.");
                txtMaxLoanAmntAllow.requestFocus();
                return;

            }

            if (acCity.getText().toString().equals("") || acCity.getText().toString().length() == 0) {
                acCity.setError("Please Enter city.");
                acCity.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(Contact)) {

                etContact.setError("Please Enter  Mobile Number.");
                etContact.requestFocus();
                return;
            }
            else {
                if (Contact.length()<10) {

                    etContact.setError("Please Enter 10 digit Mobile Number.");
                    etContact.requestFocus();
                    return;

                }

            }
            if (TextUtils.isEmpty(TenureInYear)) {

                etTenureInYear.setError("Please Enter Tenure.");
                etTenureInYear.requestFocus();
                return;

            }
            //endregion

            //region Applicant Validation
            String NameOfApplicant = etNameOfApplicant.getText().toString();
            String DOB = getYYYYMMDDPattern(et_DOB.getText().toString());

            String MonthlyInc = etMonthlyInc.getText().toString();
            String TurnOver = etTurnOver.getText().toString();
            String ProfitAtTax = etProfitAtTax.getText().toString();
            String Depreciation = etDepreciation.getText().toString();
            String DirecPartRemuntion = etDirecPartRemuntion.getText().toString();


            if (TextUtils.isEmpty(NameOfApplicant)) {

                etNameOfApplicant.setError("Please Enter Name Of Applicant.");
                etNameOfApplicant.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(DOB)) {

                et_DOB.setError("Please Enter DOB.");
                et_DOB.requestFocus();
                return;

            }
            if (ApplicantSource.equals("1")) {
                if (TextUtils.isEmpty(MonthlyInc)) {

                    etMonthlyInc.setError("Please Enter Monthly Income.");
                    etMonthlyInc.requestFocus();
                    return;

                }
            } else {
                if (TextUtils.isEmpty(TurnOver)) {

                    etTurnOver.setError("Please Enter Turnover.");
                    etTurnOver.requestFocus();
                    return;

                }

                if (TextUtils.isEmpty(ProfitAtTax)) {

                    etProfitAtTax.setError("Please Enter Profit After Tax.");
                    etProfitAtTax.requestFocus();
                    return;

                }

                if (TextUtils.isEmpty(Depreciation)) {

                    etDepreciation.setError("Please Enter Depreciation.");
                    etDepreciation.requestFocus();
                    return;

                }

                if (TextUtils.isEmpty(DirecPartRemuntion)) {

                    etDirecPartRemuntion.setError("Please Enter Director/Partner Remuneration.");
                    etDirecPartRemuntion.requestFocus();
                    return;

                }
            }// End Applicant


            if (etMonthlyInc.getText() != null && !etMonthlyInc.getText().toString().equals("")) {
                if (etEMI.getText() != null && !etEMI.getText().toString().equals("")) {
                    {
                        if (Long.valueOf(etEMI.getText().toString()) > Long.valueOf(etMonthlyInc.getText().toString())) {

                            etEMI.setError("EMI should be less than monthly income.");
                            etEMI.requestFocus();
                            return;

                        }
                    }
                }
            }



            //endregion

            //region Co-Applicant Validation

            if (chkCoApplicant.isChecked()) {
                String coAppNameOfApplicant = coApp_etNameOfApplicant.getText().toString();
                String coAppDOB = getYYYYMMDDPattern(coApp_et_DOB.getText().toString());
                String coAppMonthlyInc = coApp_etMonthlyInc.getText().toString();
                String coAppTurnOver = coApp_etTurnOver.getText().toString();
                String coAppProfitAtTax = coApp_etProfitAtTax.getText().toString();
                String coAppDepreciation = coApp_etDepreciation.getText().toString();
                String coAppDirecPartRemuntion = coApp_etDirecPartRemuntion.getText().toString();

                if (TextUtils.isEmpty(coAppNameOfApplicant)) {

                    coApp_etNameOfApplicant.setError("Enter Name Of Co-Applicant.");
                    coApp_etNameOfApplicant.requestFocus();
                    return;

                }
                if (TextUtils.isEmpty(coAppDOB)) {

                    coApp_et_DOB.setError("Enter DOB.");
                    coApp_et_DOB.requestFocus();
                    return;

                }

                if (CoApplicantSource.equals("1")) {
                    if (TextUtils.isEmpty(coAppMonthlyInc)) {

                        coApp_etMonthlyInc.setError("Enter Monthly Income.");
                        coApp_etMonthlyInc.requestFocus();
                        return;

                    }
                } else {
                    if (TextUtils.isEmpty(coAppTurnOver)) {

                        coApp_etTurnOver.setError("Enter Turnover.");
                        coApp_etTurnOver.requestFocus();
                        return;

                    }

                    if (TextUtils.isEmpty(coAppProfitAtTax)) {

                        coApp_etProfitAtTax.setError("Enter Profit After Tax.");
                        coApp_etProfitAtTax.requestFocus();
                        return;

                    }

                    if (TextUtils.isEmpty(coAppDepreciation)) {

                        coApp_etDepreciation.setError("Enter Depreciation.");
                        coApp_etDepreciation.requestFocus();
                        return;

                    }

                    if (TextUtils.isEmpty(coAppDirecPartRemuntion)) {

                        coApp_etDirecPartRemuntion.setError("Enter Director/Partner Remuneration.");
                        coApp_etDirecPartRemuntion.requestFocus();
                        return;

                    }
                }


                if (etMonthlyInc.getText() != null && !etMonthlyInc.getText().toString().equals("")) {
                    if (etEMI.getText() != null && !etEMI.getText().toString().equals("")) {
                        if (Long.valueOf(coApp_etEMI.getText().toString()) > Long.valueOf(coApp_etMonthlyInc.getText().toString())) {

                            coApp_etEMI.setError("EMI should be less than monthly income.");
                            coApp_etEMI.requestFocus();
                            return;

                        }
                    }
                    // End Co-Applicant
                }
            }


            //endregion

            setApplicantDetails();



            ((HLMainActivity) getActivity()).getQuoteParameterBundle(fmHomeLoanRequest);

        }


    }

    private void loadSpinner() {


        //region Relation Type Adapter
        coApp_relationTypeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.RelationType));
        coApp_relationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coApp_sbRelation.setAdapter(coApp_relationTypeAdapter);
        //endregion


        //region Preferred City Adapter
        arrayPreferedCity = new ArrayList<String>();
        preferedCityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, cityList);

        acCity.setAdapter(preferedCityAdapter);
        acCity.setThreshold(1);
        //endregion

    }

    // delete below method ... Not in Used
    private ArrayList<String> getNewLoanList() {
        List<PropertyInfoEntity> listLoan = mReal.getLoanPropertyInfoList();
        if (listLoan != null) {
            for (int i = 0; i <= listLoan.size() - 1; i++) {
                arrayNewLoan.add(listLoan.get(i).getProperty_Type());
            }
        }

        return arrayNewLoan;
    }


    private void setApplicantDetails() {
        // region  HomeLoanRequest Binding

        homeLoanRequest = fmHomeLoanRequest.getHomeLoanRequest();
        //  homeLoanRequest.setPropertyID("" + new PropertyFacade(getActivity()).getPropertyId(spNewLoan.getSelectedItem().toString()));

        homeLoanRequest.setPropertyID("" + propTyp);
        homeLoanRequest.setPropertyCost(etCostOfProp.getText().toString());


        homeLoanRequest.setContact(""+etContact.getText().toString());
        homeLoanRequest.setLoanTenure(etTenureInYear.getText().toString());
        homeLoanRequest.setLoanRequired(txtMaxLoanAmntAllow.getText().toString());
        homeLoanRequest.setCity("" + acCity.getText().toString());
        homeLoanRequest.setApplicantNme(etNameOfApplicant.getText().toString().trim());

        homeLoanRequest.setApplicantGender(GenderApplicantSource);
        if (homeLoanRequest.getApplicantGender()=="M") {
            homeLoanRequest.setApplicantGender("M");
        } else  if (homeLoanRequest.getApplicantGender()=="F") {
            homeLoanRequest.setApplicantGender("F");
        }

        homeLoanRequest.setApplicantSource(ApplicantSource);

        if (homeLoanRequest.getApplicantSource() == "1") {
            homeLoanRequest.setApplicantIncome(etMonthlyInc.getText().toString());

            homeLoanRequest.setTurnover("0");
            homeLoanRequest.setProfitAfterTax("0");
            homeLoanRequest.setDepreciation("0");
            homeLoanRequest.setDirectorRemuneration("0");
        } else if (homeLoanRequest.getApplicantSource() == "2") {
            homeLoanRequest.setApplicantIncome(etMonthlyInc.getText().toString());
            homeLoanRequest.setTurnover(etTurnOver.getText().toString());
            homeLoanRequest.setProfitAfterTax(etProfitAtTax.getText().toString());
            homeLoanRequest.setDepreciation(etDepreciation.getText().toString());
            homeLoanRequest.setDirectorRemuneration(etDirecPartRemuntion.getText().toString());
        }


        if (etEMI.getText().equals("")) {
            homeLoanRequest.setApplicantObligations("0");
        } else {
            homeLoanRequest.setApplicantObligations(etEMI.getText().toString());
        }
        homeLoanRequest.setApplicantDOB(getYYYYMMDDPattern(et_DOB.getText().toString()));


        if (chkCoApplicant.isChecked()) {
            homeLoanRequest.setCoApplicantYes("Y");

            homeLoanRequest.setCoApplicantName(coApp_etNameOfApplicant.getText().toString());

            homeLoanRequest.setCoApplicantGender(GenderCoApplicantSource);

            if (homeLoanRequest.getCoApplicantGender()=="M") {
                homeLoanRequest.setCoApplicantGender("M");
            } else  if (homeLoanRequest.getCoApplicantGender()=="F") {
                homeLoanRequest.setCoApplicantGender("F");
            }

            homeLoanRequest.setCoApplicantRelation(coApp_sbRelation.getSelectedItem().toString());


            homeLoanRequest.setCoApplicantSource(CoApplicantSource);

            if (homeLoanRequest.getCoApplicantSource() == "1") {
                homeLoanRequest.setCoApplicantIncome(coApp_etMonthlyInc.getText().toString());
            } else if (homeLoanRequest.getCoApplicantSource() == "2") {
                homeLoanRequest.setCoApplicantTurnover(coApp_etTurnOver.getText().toString());
                homeLoanRequest.setCoApplicantProfitAfterTax(coApp_etProfitAtTax.getText().toString());
                homeLoanRequest.setCoApplicantDepreciation(coApp_etDepreciation.getText().toString());
                homeLoanRequest.setCoApplicantDirectorRemuneration(coApp_etDirecPartRemuntion.getText().toString());


                homeLoanRequest.setCoApplicantIncome(coApp_etMonthlyInc.getText().toString());//calculation
            }


            if (coApp_etEMI.getText().equals("")) {
                homeLoanRequest.setCoApplicantObligations("0");
            } else {
                homeLoanRequest.setCoApplicantObligations(coApp_etEMI.getText().toString());
            }
            homeLoanRequest.setCoApplicantDOB(getYYYYMMDDPattern(coApp_et_DOB.getText().toString()));


        } else {
            homeLoanRequest.setCoApplicantYes("N");
        }

        homeLoanRequest.setBrokerId("" + loginEntity.getLoanId());
        homeLoanRequest.setLoaniD(Integer.parseInt(loginEntity.getLoanId()));
        homeLoanRequest.setEmpcode("");
        homeLoanRequest.setProductId("12");//HomeLoan
        homeLoanRequest.setApi_source("Finmart");
        // Below two For Node JS Maintainance
        homeLoanRequest.setType("HML");
      //  homeLoanRequest.setQuote_id(0);// RupeeBoss error
        homeLoanRequest.setQuote_id(fmHomeLoanRequest.getHomeLoanRequest().getQuote_id());
        //   homeLoanRequest.setLoaniD(Integer.valueOf(loginEntity.getLoanId()));


        //endregion
    }


    //region SeekBar ChangeListener
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int i = seekBar.getId();
        if (i == R.id.sbTenure) {
            int MIN = 0;

            if (progress >= (MIN)) {
                if (fromUser) {
                    // progress = ((int) Math.round(progress / seekBarTenureProgress)) * seekBarTenureProgress;
                    etTenureInYear.setText(String.valueOf(progress + 5));
                }
            } else {
                sbTenure.setProgress(MIN);
                etTenureInYear.setText(String.valueOf(MIN));
            }

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //  Log.d("progressSeek", "onStart 1");

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }


    private BigDecimal getMaxLoanAmount(String value) {
        long loanAmount = Long.valueOf(value);
        return BigDecimal.valueOf(Math.ceil(loanAmount * .8)).setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (etCostOfProp.getText().hashCode() == s.hashCode()) {

            if (!etCostOfProp.getText().toString().equals("") && !etCostOfProp.getText().toString().equals(null)) {

                long costOfProperty = Long.valueOf(etCostOfProp.getText().toString());
                txtMaxLoanAmntAllow.setText("" + getMaxLoanAmount("" + costOfProperty));
            } else {
                txtMaxLoanAmntAllow.setText("");
            }
            // monthly income calc in Applicant
        }  else if ( etProfitAtTax.getText().hashCode() == s.hashCode()) {

            if (!etProfitAtTax.getText().toString().equals("") && !etProfitAtTax.getText().toString().equals(null)) {
                int_etProfitAtTax =  Long.valueOf(etProfitAtTax.getText().toString());

                monthlycalc_applicant(int_etProfitAtTax,int_etDirecPartRemuntion,int_etDepreciation);
            }

        } else if (etDirecPartRemuntion.getText().hashCode() == s.hashCode()) {
            if (!etDirecPartRemuntion.getText().toString().equals("") && !etDirecPartRemuntion.getText().toString().equals(null)) {
                int_etDirecPartRemuntion =  Long.valueOf(etDirecPartRemuntion.getText().toString());
                monthlycalc_applicant(int_etProfitAtTax,int_etDirecPartRemuntion,int_etDepreciation);
            }
        }else if (etDepreciation.getText().hashCode() == s.hashCode()) {
            if (!etDepreciation.getText().toString().equals("") && !etDepreciation.getText().toString().equals(null)) {
                int_etDepreciation =  Long.valueOf(etDepreciation.getText().toString());
                monthlycalc_applicant(int_etProfitAtTax,int_etDirecPartRemuntion,int_etDepreciation);
            }


// monthly income calc in Co Applicant
        }  else if (coApp_etProfitAtTax.getText().hashCode() == s.hashCode()) {
            if (!coApp_etProfitAtTax.getText().toString().equals("") && !coApp_etProfitAtTax.getText().toString().equals(null)) {
                int_coApp_etProfitAtTax = Long.valueOf(coApp_etProfitAtTax.getText().toString());
                monthlycalc_coapplicant(int_coApp_etDepreciation,int_coApp_etProfitAtTax,int_coApp_etDirecPartRemuntion);
            }

        } else if (coApp_etDepreciation.getText().hashCode() == s.hashCode()) {
            if (!coApp_etDepreciation.getText().toString().equals("") && !coApp_etDepreciation.getText().toString().equals(null)) {
                int_coApp_etDepreciation = Long.valueOf(coApp_etDepreciation.getText().toString());
                monthlycalc_coapplicant(int_coApp_etDepreciation,int_coApp_etProfitAtTax,int_coApp_etDirecPartRemuntion);
            }

        } else if (coApp_etDirecPartRemuntion.getText().hashCode() == s.hashCode()) {
            if (!coApp_etDirecPartRemuntion.getText().toString().equals("") && !coApp_etDirecPartRemuntion.getText().toString().equals(null)) {
                int_coApp_etDirecPartRemuntion = Long.valueOf(coApp_etDirecPartRemuntion.getText().toString());
                monthlycalc_coapplicant(int_coApp_etDepreciation,int_coApp_etProfitAtTax,int_coApp_etDirecPartRemuntion);
            }

        }
    }

    public void monthlycalc_applicant(long int_etProfitAtTax,long int_etDirecPartRemuntion,long int_etDepreciation)
    {
        if (ApplicantSource== "1") {

        } else if (ApplicantSource == "2") {

            double total = int_etProfitAtTax+int_etDirecPartRemuntion+int_etDepreciation;
            if (total > 0) {
                totalmonthlucalc_app = Math.round((total) / 12);
                etMonthlyInc.setText(""+totalmonthlucalc_app);
            }

        }

    }

    public void monthlycalc_coapplicant(long int_coApp_etDepreciation,long int_coApp_etProfitAtTax,long int_coApp_etDirecPartRemuntion)
    {
        if (CoApplicantSource == "1") {

            } else if (CoApplicantSource== "2") {
                double totalcoapp = int_coApp_etDepreciation+int_coApp_etProfitAtTax+int_coApp_etDirecPartRemuntion;
                if (totalcoapp > 0) {
                    totalmonthlucalc_coapp = Math.round((totalcoapp) / 12);
                    coApp_etMonthlyInc.setText(""+totalmonthlucalc_coapp);
                }
            }

    }
    @Override
    public void afterTextChanged(Editable s) {
    }
    // endregion


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    private RadioGroup.OnCheckedChangeListener rgProp1Listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rgProperty2.setOnCheckedChangeListener(null); // remove the listener before clearing
                rgProperty2.clearCheck(); // clear the second RadioGroup!
                rgProperty2.setOnCheckedChangeListener(rgProp2Listener); //reset the listener

                if (checkedId == R.id.rbReady) {
                    propTyp = "1";
                } else if (checkedId == R.id.rbUnder) {
                    propTyp = "2";
                } else if (checkedId == R.id.rbSearching) {
                    propTyp = "3";
                }
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener rgProp2Listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                rgProperty1.setOnCheckedChangeListener(null);
                rgProperty1.clearCheck();
                rgProperty1.setOnCheckedChangeListener(rgProp1Listener);
                if (checkedId == R.id.rbResale) {
                    propTyp = "4";
                } else if (checkedId == R.id.rbForcons) {
                    propTyp = "5";
                } else if (checkedId == R.id.rbOther) {
                    propTyp = "6";
                }
            }
        }
    };


}
