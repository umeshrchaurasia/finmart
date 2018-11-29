package com.datacomp.magicfinmart.loan_fm.personalloan.addquote;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.myaccount.MyAccountActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;
import com.viewpagerindicator.CirclePageIndicator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PincodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmPersonalLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.PersonalLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetPersonalLoanResponse;


/**
 * Created by Rahul on 24/01/2018.
 */

public class InputFragment_pl extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener ,IResponseSubcriber {

    DBPersistanceController databaseController;

    LoginResponseEntity loginEntity;
    GetPersonalLoanResponse getPersonalLoanResponse;
    PersonalLoanRequest personalLoanRequest;
    FmPersonalLoanRequest fmPersonalLoanRequest;

    //display format
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    //server conversion date format
    SimpleDateFormat formatServer = new SimpleDateFormat("yyyy-MM-dd");

    Button btnGetQuote;
    EditText etNameOfApplicant, et_DOB, etMonthlyInc, etEMI, etPAN, etCostOfProp, etcontact,etPincode,etState,etCity,etaddress,etEmail;

    LinearLayout llSalaried, llSelfEmployeed;

    TextView etTenureInYear, txtrbimgMale, txtrbimgFemale,txtrbimgsingle,txtrbimgmarried,txtrbimgother;
    TextView txtrbimgPersent,txtrbimgPermanent,txtrbimgOfficer,txtrbimgOther_addr;
    TextView txtDispalayMinTenureYear, txtDispalayMaxTenureYear;
    SeekBar sbTenure;
    Context mContext;
    String GenderApplicantSource = "M";

    String StatusApplicantSource = "single";
    String AddressTypeSource = "C";

    String plbannerurl="";
    String plbannnerActive="1";
    ImageView plbannerimg;

    ViewPager viewPager;
    List<Integer> banners;
    CustomPagerAdapter mBannerAdapter;
    CirclePageIndicator circlePageIndicator;

    public InputFragment_pl() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_add_plquote, container, false);
        init_widgets(view);
        databaseController = new DBPersistanceController(getActivity());
        loginEntity = databaseController.getUserData();
        plbannerurl=databaseController.getUserConstantsData().getPlbanner();
        plbannnerActive=databaseController.getUserConstantsData().getPlactive();

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.titles);

 //       plbannerimg.setVisibility(View.GONE);
//        if(plbannerurl !=null)
//        {
//            if(plbannnerActive.equals("1"))
//            {
//                plbannerimg.setVisibility(View.VISIBLE);
//                Glide.with(mContext)
//                        .load(plbannerurl)
//                        .into(plbannerimg);
//
//            }else
//            {
//                plbannerimg.setVisibility(View.GONE);
//            }
//        }
        setBanner();
        setListener();
        setApp_Male_gender();
        setApp_single_Status();
        setApp_Persent_type();

        if (getArguments() != null) {
            if (getArguments().getParcelable(PLMainActivity.PL_INPUT_REQUEST) != null) {
                fmPersonalLoanRequest = getArguments().getParcelable(PLMainActivity.PL_INPUT_REQUEST);
                personalLoanRequest = fmPersonalLoanRequest.getPersonalLoanRequest();
                fillCustomerDetails();


            }
        } else {
            fmPersonalLoanRequest = new FmPersonalLoanRequest();
            fmPersonalLoanRequest.setFBA_id(new DBPersistanceController(getContext()).getUserData().getFBAId());
            fmPersonalLoanRequest.setLoan_requestID(0);
            personalLoanRequest = new PersonalLoanRequest();
            fmPersonalLoanRequest.setPersonalLoanRequest(personalLoanRequest);
        }
        return view;
    }

    private void init_widgets(View view) {

        btnGetQuote = (Button) view.findViewById(R.id.btnGetQuote);

        //region Property Initialize
        etCostOfProp = (EditText) view.findViewById(R.id.etCostOfProp);
        etPAN = (EditText) view.findViewById(R.id.etPAN);
        txtDispalayMinTenureYear = (TextView) view.findViewById(R.id.txtDispalayMinTenureYear);
        txtDispalayMaxTenureYear = (TextView) view.findViewById(R.id.txtDispalayMaxTenureYear);
        etTenureInYear = (TextView) view.findViewById(R.id.etTenureInYear);
        etcontact = (EditText) view.findViewById(R.id.etcontact);

        sbTenure = (SeekBar) view.findViewById(R.id.sbTenure);
        sbTenure.setMax(4);
        sbTenure.setProgress(4);
        etTenureInYear.setText("5");


        //endregion

        //region Applicant Initialize
        llSelfEmployeed = (LinearLayout) view.findViewById(R.id.llSelfEmployeed);
        llSalaried = (LinearLayout) view.findViewById(R.id.llSalaried);
        etNameOfApplicant = (EditText) view.findViewById(R.id.etNameOfApplicant);
        et_DOB = (EditText) view.findViewById(R.id.et_DOB);
        etMonthlyInc = (EditText) view.findViewById(R.id.etMonthlyInc);
        etEMI = (EditText) view.findViewById(R.id.etEMI);

        txtrbimgMale = (TextView) view.findViewById(R.id.txtrbimgMale);
        txtrbimgFemale = (TextView) view.findViewById(R.id.txtrbimgFemale);


        txtrbimgsingle = (TextView) view.findViewById(R.id.txtrbimgsingle);
        txtrbimgmarried = (TextView) view.findViewById(R.id.txtrbimgmarried);
        txtrbimgother = (TextView) view.findViewById(R.id.txtrbimgother);

        txtrbimgPersent = (TextView) view.findViewById(R.id.txtrbimgPersent);
        txtrbimgPermanent = (TextView) view.findViewById(R.id.txtrbimgPermanent);
        txtrbimgOfficer = (TextView) view.findViewById(R.id.txtrbimgOfficer);
        txtrbimgOther_addr = (TextView) view.findViewById(R.id.txtrbimgOther_addr);

        //TODO:set tag to DOB -- nilesh
        et_DOB.setTag(R.id.et_DOB, dateToCalendar(stringToDate(simpleDateFormat, "01-01-1980")));
        et_DOB.setText("01-01-1980");
        etCity  = (EditText) view.findViewById(R.id.etCity);
        etState  = (EditText) view.findViewById(R.id.etState);
        //endregion

        etaddress= (EditText)view.findViewById(R.id.etaddress);
        etPincode = (EditText)view.findViewById(R.id.etPincode);
        etPincode.addTextChangedListener(pincodeTextWatcher);
        etEmail = (EditText)view.findViewById(R.id.etEmail);

        plbannerimg = (ImageView) view.findViewById(R.id.plbannerimg);
    }

    private void setBanner() {
        banners = new ArrayList<>();

        banners.add(R.drawable.pl_banner_1);
        banners.add(R.drawable.pl_banner_2);
        banners.add(R.drawable.pl_banner_3);
        mBannerAdapter = new  CustomPagerAdapter(getContext(), banners);


        if (viewPager != null && circlePageIndicator != null) {
            viewPager.setAdapter(mBannerAdapter);
            circlePageIndicator.setViewPager(viewPager);

            Timer timer = new Timer();
            timer.schedule(new RemindTask(banners.size(), viewPager), 0, 1500);

        }
    }

    class RemindTask extends TimerTask {
        private int numberOfPages;
        private ViewPager mViewPager;
        private int page = 0;

        public RemindTask(int numberOfPages, ViewPager mViewPager) {
            this.numberOfPages = numberOfPages;
            this.mViewPager = mViewPager;
        }

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        if (page > numberOfPages) { // In my case the number of pages are 5
                            mViewPager.setCurrentItem(0);
                            page = 0;
                        } else {
                            mViewPager.setCurrentItem(page++);
                        }
                    }
                });
            }

        }
    }


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
            if ((s.length() == 6) ) {
                showDialog("Fetching City...");
                Toast.makeText(getActivity(), "Fetching City...Data", Toast.LENGTH_SHORT).show();
                new RegisterController(getActivity()).getCityState(etPincode.getText().toString(),InputFragment_pl.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    //endregion


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


    private void setApplicantDetails() {
        // region  PersonaalLoanRequest Binding

        personalLoanRequest = fmPersonalLoanRequest.getPersonalLoanRequest();
        personalLoanRequest.setLoanRequired(etCostOfProp.getText().toString());
        personalLoanRequest.setLoanTenure(etTenureInYear.getText().toString());
        personalLoanRequest.setApplicantNme(etNameOfApplicant.getText().toString().trim());

        personalLoanRequest.setContact(etcontact.getText().toString());
        // region Default Salaried
        personalLoanRequest.setApplicantSource("1");
        personalLoanRequest.setApplicantIncome(etMonthlyInc.getText().toString());

        //endregion
        personalLoanRequest.setApplicantGender(GenderApplicantSource);
        if (personalLoanRequest.getApplicantGender() == "M") {
            personalLoanRequest.setApplicantGender("M");
        } else if (personalLoanRequest.getApplicantGender() == "F") {
            personalLoanRequest.setApplicantGender("F");
        }

        if (etEMI.getText().equals("")) {
            personalLoanRequest.setApplicantObligations("0");
        } else {
            personalLoanRequest.setApplicantObligations(etEMI.getText().toString());
        }

        // personalLoanRequest.setEmi(etEMI.getText().toString());

        personalLoanRequest.setApplicantDOB(getYYYYMMDDPattern(et_DOB.getText().toString()));

        //    personalLoanRequest.setApplicantDOB(et_DOB.getText().toString());
        personalLoanRequest.setBrokerId("" + loginEntity.getLoanId());
        // personalLoanRequest.setLoaniD(Integer.parseInt(loginEntity.getLoanId()));
        personalLoanRequest.setpanno(etPAN.getText().toString());

        personalLoanRequest.setEmpcode("");
        personalLoanRequest.setType("PSL");
        personalLoanRequest.setEmail(etEmail.getText().toString());
        personalLoanRequest.setProduct_name("9");
        personalLoanRequest.setForm("personal_loan");
        personalLoanRequest.setApi_source("Finmart");

        personalLoanRequest.setMaritalStatus(StatusApplicantSource);


        personalLoanRequest.setAddressType(AddressTypeSource);


        personalLoanRequest.setPostal(etPincode.getText().toString());
        personalLoanRequest.setAddress(etaddress.getText().toString());
        personalLoanRequest.setAddressLine(etaddress.getText().toString());
        personalLoanRequest.setLocality1(etaddress.getText().toString());
        personalLoanRequest.setPhoneType("M");

        personalLoanRequest.setQuote_id(fmPersonalLoanRequest.getPersonalLoanRequest().getQuote_id());

    }


    private void fillCustomerDetails() {

        Log.d("DETAILS", personalLoanRequest.toString());

        try {


            if (personalLoanRequest.getLoanRequired() != null)
                etCostOfProp.setText(personalLoanRequest.getLoanRequired());
            if (personalLoanRequest.getLoanTenure() != null)
                etTenureInYear.setText(personalLoanRequest.getLoanTenure());

            if (personalLoanRequest.getApplicantObligations() != null) {
                etEMI.setText(personalLoanRequest.getApplicantObligations());
            }
            if (personalLoanRequest.getApplicantNme() != null)
                etNameOfApplicant.setText(personalLoanRequest.getApplicantNme());

            int tenureInYear = Integer.parseInt(personalLoanRequest.getLoanTenure());
            sbTenure.setProgress(tenureInYear - 1);

            if (personalLoanRequest.getApplicantGender().matches("M")) {
                setApp_Male_gender();
            } else {
                setApp_FeMale_gender();
            }

          try {

              if (personalLoanRequest.getMaritalStatus().equals("single")) {
                  setApp_single_Status();
              } else if (personalLoanRequest.getMaritalStatus().equals("married")) {
                  setApp_married_Status();
              } else if (personalLoanRequest.getMaritalStatus().equals("divorced")) {
                  setApp_other_Status();
              }

              if (personalLoanRequest.getAddressType().equals("C")) {
                  setApp_Persent_type();
              } else if (personalLoanRequest.getAddressType().equals("P")) {
                  setApp_Permanent_type();
              } else if (personalLoanRequest.getAddressType().equals("O")) {
                  setApp_Office_type();
              }
              else if (personalLoanRequest.getAddressType().equals("X")) {
                  setApp_other_type();
              }
          }catch (Exception ref){
                setApp_single_Status();
              setApp_Persent_type();
            }


            if (personalLoanRequest.getApplicantDOB() != null) {
                //setting tag
                et_DOB.setTag(R.id.et_DOB, dateToCalendar(stringToDate(formatServer, personalLoanRequest.getApplicantDOB())));
                et_DOB.setText(getDDMMYYYPattern(personalLoanRequest.getApplicantDOB(), "yyyy-MM-dd"));

            }
            //  et_DOB.setText(personalLoanRequest.getApplicantDOB());

            if (personalLoanRequest.getApplicantIncome() != null)
                etMonthlyInc.setText("" + personalLoanRequest.getApplicantIncome());

            if (personalLoanRequest.getContact() != null)
                etcontact.setText("" + personalLoanRequest.getContact());

            if (personalLoanRequest.getpanno() != null)
                etPAN.setText("" + personalLoanRequest.getpanno());

            if (personalLoanRequest.getPostal() != null)
                etPincode.setText("" + personalLoanRequest.getPostal());

            if (personalLoanRequest.getEmail() != null)
                etEmail.setText("" + personalLoanRequest.getEmail());


            if (personalLoanRequest.getAddress() != null)
                etaddress.setText("" + personalLoanRequest.getAddressLine());


            if (personalLoanRequest.getCity() != null)
                etCity.setText("" + personalLoanRequest.getCity());

            if (personalLoanRequest.getCity() != null)
                etState.setText("" + personalLoanRequest.getState());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        sbTenure.setOnSeekBarChangeListener(this);
        btnGetQuote.setOnClickListener(this);
        et_DOB.setOnClickListener(datePickerDialogApplicant);

        txtrbimgMale.setOnClickListener(this);
        txtrbimgFemale.setOnClickListener(this);
        // etPAN.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        txtrbimgsingle.setOnClickListener(this);
        txtrbimgmarried.setOnClickListener(this);
        txtrbimgother.setOnClickListener(this);

        txtrbimgPersent.setOnClickListener(this);
        txtrbimgPermanent.setOnClickListener(this);
        txtrbimgOfficer.setOnClickListener(this);
        txtrbimgOther_addr.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.txtrbimgMale) {

            setApp_Male_gender();
        } else if (v.getId() == R.id.txtrbimgFemale) {

            setApp_FeMale_gender();
        }
        else if (v.getId() == R.id.txtrbimgsingle) {

            setApp_single_Status();
        }
        else if (v.getId() == R.id.txtrbimgmarried) {

            setApp_married_Status();
        }
        else if (v.getId() == R.id.txtrbimgother) {

            setApp_other_Status();
        }
        else if (v.getId() == R.id.txtrbimgPersent) {

            setApp_Persent_type();
        }
        else if (v.getId() == R.id.txtrbimgPermanent) {

            setApp_Permanent_type();
        }
        else if (v.getId() == R.id.txtrbimgOfficer) {

            setApp_Office_type();
        }
        else if (v.getId() == R.id.txtrbimgOther_addr) {

            setApp_other_type();
        }

        else if (v.getId() == R.id.btnGetQuote) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Get quote PL : Get quote button for PL"), Constants.PERSONA_LOAN), null);

            MyApplication.getInstance().trackEvent( Constants.PERSONA_LOAN,"Clicked","Get quote PL : Get quote button for PL");


            //region Validation
            String NameOfApplicant = etNameOfApplicant.getText().toString();
            String DOB = getYYYYMMDDPattern(et_DOB.getText().toString());
            String MonthlyInc = etMonthlyInc.getText().toString();
            String Pan_no = etPAN.getText().toString();
            String CostOfProp = etCostOfProp.getText().toString();
            String Contact = etcontact.getText().toString();
            String pincode = etPincode.getText().toString();
            String email = etEmail.getText().toString();

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
            if (TextUtils.isEmpty(MonthlyInc)) {

                etMonthlyInc.setError("Please Enter Monthly Income.");
                etMonthlyInc.requestFocus();
                return;

            }

            if (TextUtils.isEmpty(Pan_no)) {
                etPAN.setError("Please Enter PAN Number");
                etPAN.requestFocus();
                return;
            }
            if (!isValidPan(Pan_no)) {
                etPAN.setError("Enter Valid PAN Number");
                etPAN.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(CostOfProp)) {

                etCostOfProp.setError("Please Enter Required Loan Amount.");
                etCostOfProp.requestFocus();
                return;

            }

            if (TextUtils.isEmpty(Contact)) {

                etcontact.setError("Please Enter Mobile Number.");
                etcontact.requestFocus();
                return;
            }
            else {
                if (Contact.length()<10) {

                    etcontact.setError("Please Enter 10 digit Mobile Number.");
                    etcontact.requestFocus();
                    return;

                }

            }

            if (TextUtils.isEmpty(pincode)) {

                etPincode.setError("Please Enter Pincode.");
                etPincode.requestFocus();
                return;

            }
            if (pincode.length()<6) {

                etPincode.setError("Please Enter 6 digit Pincode.");
                etPincode.requestFocus();
                return;

            }

            if (TextUtils.isEmpty(email)) {

                etEmail.setError("Please Enter Email Address.");
                etEmail.requestFocus();
                return;

            }

             if (!isValideEmailID(etEmail)) {

                 etEmail.setError("Please Enter Proper Email Address.");
                 etEmail.requestFocus();
                 return;

            }
            // endregion
            setApplicantDetails();




            //  new PersonalLoanController(getActivity()).getPersonalLoan(personalLoanRequest, this);
            ((PLMainActivity) getActivity()).getQuoteParameterBundle(fmPersonalLoanRequest);

        }

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int i = seekBar.getId();
        if (i == R.id.sbTenure) {
            int MIN = 0;
            if (progress >= MIN) {
                if (fromUser) {
                    // progress = ((int) Math.round(progress / seekBarTenureProgress)) * seekBarTenureProgress;
                    etTenureInYear.setText(String.valueOf(progress + 1));
                }
            } else {
                sbTenure.setProgress(MIN);
                etTenureInYear.setText(String.valueOf(MIN));
            }

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

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

    private void  setApp_single_Status(){
        StatusApplicantSource = "single";
        txtrbimgsingle.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgsingle.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));


        txtrbimgmarried.setBackgroundResource(R.drawable.customeborder);
        txtrbimgmarried.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgother.setBackgroundResource(R.drawable.customeborder);
        txtrbimgother.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }

    private void  setApp_married_Status(){
        StatusApplicantSource = "married";
        txtrbimgmarried.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgmarried.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));


        txtrbimgsingle.setBackgroundResource(R.drawable.customeborder);
        txtrbimgsingle.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgother.setBackgroundResource(R.drawable.customeborder);
        txtrbimgother.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }

    private void  setApp_other_Status(){
        StatusApplicantSource = "divorced";
        txtrbimgother.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgother.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));


        txtrbimgsingle.setBackgroundResource(R.drawable.customeborder);
        txtrbimgsingle.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgmarried.setBackgroundResource(R.drawable.customeborder);
        txtrbimgmarried.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }


    private void  setApp_Persent_type(){
        AddressTypeSource = "C";
        txtrbimgPersent.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgPersent.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));


        txtrbimgPermanent.setBackgroundResource(R.drawable.customeborder);
        txtrbimgPermanent.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgOfficer.setBackgroundResource(R.drawable.customeborder);
        txtrbimgOfficer.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgOther_addr.setBackgroundResource(R.drawable.customeborder);
        txtrbimgOther_addr.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }

    private void  setApp_Permanent_type(){
        AddressTypeSource = "P";


        txtrbimgPermanent.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgPermanent.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));


        txtrbimgPersent.setBackgroundResource(R.drawable.customeborder);
        txtrbimgPersent.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgOfficer.setBackgroundResource(R.drawable.customeborder);
        txtrbimgOfficer.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgOther_addr.setBackgroundResource(R.drawable.customeborder);
        txtrbimgOther_addr.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }
    private void  setApp_Office_type(){
        AddressTypeSource = "O";

        txtrbimgOfficer.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgOfficer.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtrbimgPersent.setBackgroundResource(R.drawable.customeborder);
        txtrbimgPersent.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgPermanent.setBackgroundResource(R.drawable.customeborder);
        txtrbimgPermanent.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));


        txtrbimgOther_addr.setBackgroundResource(R.drawable.customeborder);
        txtrbimgOther_addr.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }

    private void  setApp_other_type(){
        AddressTypeSource = "X";
        txtrbimgOther_addr.setBackgroundResource(R.drawable.customeborder_blue);
        txtrbimgOther_addr.setTextColor(ContextCompat.getColor(getActivity(), Utility.getPrimaryColor(mContext)));

        txtrbimgPersent.setBackgroundResource(R.drawable.customeborder);
        txtrbimgPersent.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgPermanent.setBackgroundResource(R.drawable.customeborder);
        txtrbimgPermanent.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

        txtrbimgOfficer.setBackgroundResource(R.drawable.customeborder);
        txtrbimgOfficer.setTextColor(ContextCompat.getColor(getActivity(), R.color.description_text));

    }
    //endegion


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof PincodeResponse) {
            if (response.getStatusNo() == 0) {
                etState.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                etCity.setText("" + ((PincodeResponse) response).getMasterData().getCityname());

                personalLoanRequest.setCity("" + ((PincodeResponse) response).getMasterData().getCityname());
                personalLoanRequest.setState("" + ((PincodeResponse) response).getMasterData().getState_name());

            } else {

                etState.setText("");
                etCity.setText("");

                personalLoanRequest.setCity("");
                personalLoanRequest.setState("");


            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {

        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
