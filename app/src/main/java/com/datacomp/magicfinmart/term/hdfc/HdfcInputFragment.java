package com.datacomp.magicfinmart.term.hdfc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.term.compareterm.CompareTermActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;
import com.datacomp.magicfinmart.webviews.ShareQuoteActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.term.TermInsuranceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TermCompareResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermFinmartRequest;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermCompareQuoteResponse;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

/**
 * Created by Rajeev Ranjan on 17/05/2018.
 */

public class HdfcInputFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener, IResponseSubcriber {

    //region variables

    //region header views
    LinearLayout llGender, llSmoker;
    EditText etFirstName, etLastName, etMobile, etDOB;
    TextView tvMale, tvFemale, tvYes, tvNo;
    boolean isMale, isSmoker;
    //endregion

    //region headers
    TextView tvSum, tvGender, tvSmoker, tvAge, tvPolicyTerm, tvCrn;
    ImageView ivEdit, ivInfo;
    TermCompareResponseEntity termCompareResponseEntity;
    CardView cvInputDetails, cvQuoteDetails;
    LinearLayout layoutCompare;
    ScrollView mainScroll;
    TextView txtPlanNAme, txtCover, txtFinalPremium, txtPolicyTerm, txtAge;
    ImageView imgInsurerLogo, ivBuy, ivPdf;
    LinearLayout llAddon;
    RecyclerView rvAddOn;

    Button btnGetQuote;
    TextInputLayout tilPincode;
    EditText etPincode, etSumAssured;
    TermRequestEntity termRequestEntity;
    TermFinmartRequest termFinmartRequest;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private PopupWindow mPopupWindow, mPopupWindowSelection;
    View customView, customViewSelection;
    RecyclerView rvIprotectSmart;
    HdfcIProtectAdapter adapter;
    Gson gson = new Gson();
    String responseJson = "";
    //endregion

    //region hdfc specific
    LinearLayout llHDFCSAInc, llIncDeath, llIncPeriod, llINCREASING, llAdb;
    Button minusICICISum, plusICICISum, minusICICIPTerm, plusICICIPTerm,
            minusICICIPreTerm, plusICICIPreTerm, minusHDFCSAInc, plusHDFCSAInc,
            minusIncDeath, plusIncDeath, minusIncPeriod, plusIncPeriod,
            minusINCREASING, plusINCREASING, minusAdb, plusAdb;
    EditText etICICISumAssured, etICICIPolicyTerm, etICICIPremiumTerm, etHDFCSAInc,
            etIncDeath, etIncPeriod, etINCREASING, etAdb;

    long hfLumsumPayOutOnDeath, hfLumsumAmt;
    //endregion

    DBPersistanceController dbPersistanceController;
    Spinner spHDFCOptions, spHdfcPremFrq;
    int termRequestId = 0;
    String crn = "";
    int age = 0;

    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hdfc_input, container, false);
        init_view(view);
        setListener();
        setPopUpInfo();
        // set initial values
        dbPersistanceController = new DBPersistanceController(getActivity());
        termRequestEntity = new TermRequestEntity(getActivity());
        termFinmartRequest = new TermFinmartRequest();
        setDefaultValues();
        //init_adapters();

        //adapter_listener();
        if (getArguments() != null) {
            if (getArguments().getParcelable(HdfcTermActivity.QUOTE_DATA) != null) {
                termFinmartRequest = getArguments().getParcelable(HdfcTermActivity.QUOTE_DATA);
                termRequestEntity = termFinmartRequest.getTermRequestEntity();
                termRequestId = termFinmartRequest.getTermRequestId();
                int fba_id = new DBPersistanceController(getActivity()).getUserData().getFBAId();
                termFinmartRequest.setFba_id(fba_id);
                fetchQuotes();
            } else if (getArguments().getParcelable(HdfcTermActivity.INPUT_DATA) != null) {
                termFinmartRequest = getArguments().getParcelable(HdfcTermActivity.INPUT_DATA);
                termRequestEntity = termFinmartRequest.getTermRequestEntity();
                termRequestId = termFinmartRequest.getTermRequestId();
                changeInputQuote(true);
            } else if (getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA) != null) {
                termCompareResponseEntity = getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA_RESPONSE);
                termFinmartRequest = getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA);
                termRequestEntity = termFinmartRequest.getTermRequestEntity();
                termRequestId = termFinmartRequest.getTermRequestId();
                bindHeaders();
                bindQuotes();
                fromCompare();
            } else {
                changeInputQuote(true);
                tvNo.performClick();
                tvMale.performClick();
            }
            //bindICICI();
            if (termFinmartRequest != null && termFinmartRequest.getTermRequestEntity() != null)
                bindInput(termFinmartRequest);
        }
        return view;
    }

    private void bindInput(TermFinmartRequest termFinmartRequest) {
        try {
            TermRequestEntity termRequestEntity = termFinmartRequest.getTermRequestEntity();
            if (termRequestEntity != null) {

                if (termRequestEntity.getPlanTaken().equals("Life")) {
                    spHDFCOptions.setSelection(0);
                } else if (termRequestEntity.getPlanTaken().equals("3D Life")) {
                    spHDFCOptions.setSelection(1);
                } else if (termRequestEntity.getPlanTaken().equals("Life Long Protection")) {
                    spHDFCOptions.setSelection(2);
                } else if (termRequestEntity.getPlanTaken().equals("3D Life Long Protection")) {
                    spHDFCOptions.setSelection(3);
                } else if (termRequestEntity.getPlanTaken().equals("Extra Life")) {
                    spHDFCOptions.setSelection(4);
                } else if (termRequestEntity.getPlanTaken().equals("Extra Life Income")) {
                    spHDFCOptions.setSelection(5);
                } else if (termRequestEntity.getPlanTaken().equals("Income Option")) {
                    spHDFCOptions.setSelection(6);
                } else if (termRequestEntity.getPlanTaken().equals("Income Replacement")) {
                    spHDFCOptions.setSelection(7);
                } else if (termRequestEntity.getPlanTaken().equals("Return of Premium")) {
                    spHDFCOptions.setSelection(8);
                }

                if (termRequestEntity.getFrequency().equals("Yearly")) {
                    spHdfcPremFrq.setSelection(0);
                } else if (termRequestEntity.getFrequency().equals("Half Yearly")) {
                    spHdfcPremFrq.setSelection(1);
                } else if (termRequestEntity.getFrequency().equals("Quarterly")) {
                    spHdfcPremFrq.setSelection(2);
                } else if (termRequestEntity.getFrequency().equals("Monthly")) {
                    spHdfcPremFrq.setSelection(3);
                } else if (termRequestEntity.getFrequency().equals("Single")) {
                    spHdfcPremFrq.setSelection(4);
                }


                String[] splitStr = termRequestEntity.getContactName().split("\\s+");
                String firstName = "", lastName = "";
                for (int i = 0; i < splitStr.length; i++) {
                    if (i == ((splitStr.length) - 1)) {
                        lastName = lastName + splitStr[i];
                    } else {
                        firstName = firstName + " " + splitStr[i];
                    }
                }
                etFirstName.setText("" + firstName);
                etLastName.setText("" + lastName);
                etMobile.setText("" + termRequestEntity.getContactMobile());
                etDOB.setText("" + termRequestEntity.getInsuredDOB());
                etPincode.setText("" + termRequestEntity.getPincode());

                if (termRequestEntity.getIs_TabaccoUser().equals("true")) {
                    isSmoker = true;
                    tvYes.setBackgroundResource(R.drawable.customeborder_blue);
                    tvNo.setBackgroundResource(R.drawable.customeborder);
                } else {
                    isSmoker = false;
                    tvNo.setBackgroundResource(R.drawable.customeborder_blue);
                    tvYes.setBackgroundResource(R.drawable.customeborder);
                }

                if (termRequestEntity.getInsuredGender().equals("M")) {
                    isMale = true;
                    tvMale.setBackgroundResource(R.drawable.customeborder_blue);
                    tvFemale.setBackgroundResource(R.drawable.customeborder);
                } else {
                    isMale = false;
                    tvFemale.setBackgroundResource(R.drawable.customeborder_blue);
                    tvMale.setBackgroundResource(R.drawable.customeborder);
                }

                if (termRequestEntity.getMonthlyIncome() != null && !termRequestEntity.getMonthlyIncome().equals(""))
                    etIncDeath.setText("" + termRequestEntity.getMonthlyIncome());

                if (termRequestEntity.getIncomeTerm() != null && !termRequestEntity.getIncomeTerm().equals(""))
                    etIncPeriod.setText("" + termRequestEntity.getIncomeTerm());

                if (termRequestEntity.getIncreaseIncomePercentage() != null && !termRequestEntity.getIncreaseIncomePercentage().equals(""))
                    etINCREASING.setText("" + termRequestEntity.getIncreaseIncomePercentage());

                if (termRequestEntity.getADBPercentage() != null && !termRequestEntity.getADBPercentage().equals(""))
                    etAdb.setText("" + termRequestEntity.getLumpsumPercentage());

                etICICISumAssured.setText("" + termRequestEntity.getSumAssured());
                etICICIPolicyTerm.setText("" + termRequestEntity.getPolicyTerm());
                etICICIPremiumTerm.setText("" + termRequestEntity.getPPT());
                etHDFCSAInc.setText("" + termRequestEntity.getIncreaseSAPercentage());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void bindHeaders() {
        if (termRequestEntity != null) {

            tvSum.setText("");
            tvSum.append("SUM  ");
            SpannableString SUM = new SpannableString(termRequestEntity.getSumAssured());
            SUM.setSpan(new StyleSpan(Typeface.BOLD), 0, termRequestEntity.getSumAssured().length(), 0);
            SUM.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.header_dark_text)), 0, termRequestEntity.getSumAssured().length(), 0);
            tvSum.append(SUM);


            try {
                tvAge.setText("");
                tvAge.append("AGE  ");
                Date ag = simpleDateFormat.parse(termRequestEntity.getInsuredDOB());
                Calendar ageCalender = Calendar.getInstance();
                ageCalender.setTime(ag);
                String age = "" + caluclateAge(ageCalender);
                SpannableString AGE = new SpannableString(age);
                AGE.setSpan(new StyleSpan(Typeface.BOLD), 0, age.length(), 0);
                AGE.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.header_dark_text)), 0, age.length(), 0);
                tvAge.append(AGE);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            tvPolicyTerm.setText("");
            tvPolicyTerm.append("TERM  ");
            SpannableString TERM = new SpannableString(termRequestEntity.getPolicyTerm() + " Years");
            TERM.setSpan(new StyleSpan(Typeface.BOLD), 0, TERM.length(), 0);
            TERM.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.header_dark_text)), 0, TERM.length(), 0);
            tvPolicyTerm.append(TERM);

            if (termRequestEntity.getInsuredGender().equals("M"))
                tvGender.setText("MALE");
            else
                tvGender.setText("FEMALE");
            if (termRequestEntity.getIs_TabaccoUser().equals("true"))
                tvSmoker.setText("SMOKER");
            else
                tvSmoker.setText("NON-SMOKER");


            tvCrn.setText("");
            tvCrn.append("CRN  ");
            String crn = "" + termCompareResponseEntity.getCustomerReferenceID();
            SpannableString CRN = new SpannableString("" + crn);
            CRN.setSpan(new StyleSpan(Typeface.BOLD), 0, crn.length(), 0);
            CRN.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.header_dark_text)), 0, crn.length(), 0);
            tvCrn.append(CRN);
            termRequestEntity.setExisting_ProductInsuranceMapping_Id("" + crn);
            termFinmartRequest.setTermRequestEntity(termRequestEntity);

            // tvAge.setText("" + termRequestEntity.getInsuredDOB());
            //tvPolicyTerm.setText("" + termRequestEntity.getPolicyTerm() + " YEARS");
            //tvCrn.setText("---");
        }
    }

    private void bindQuotes() {
        final TermCompareResponseEntity responseEntity = termCompareResponseEntity;
        txtPlanNAme.setText("" + responseEntity.getProductPlanName());
        txtCover.setText("\u20B9 " + responseEntity.getSumAssured());
        txtPolicyTerm.setText(responseEntity.getPolicyTermYear() + " Yrs.");
        txtFinalPremium.setText("\u20B9 " + responseEntity.getNetPremium());
        int uptoAge = Integer.parseInt(termRequestEntity.getPPT()) + caluclateAge(etDOB.getText().toString());
        txtAge.setText("" + uptoAge + " Yrs.");
        //  txtFinalPremium.setText("\u20B9 " + Math.round(Double.parseDouble(responseEntity.getFinal_premium_with_addon())));

       /* Glide.with(getActivity())
                .load("http://www.policyboss.com/Images/insurer_logo/" + responseEntity.getInsurerLogoName())
                .into(imgInsurerLogo);*/

        txtFinalPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ((TermInputFragment) getActivity()).redirectToBuy(responseEntity);
            }
        });

        /*if (responseEntity.getKeyFeatures() != null) {
            llAddon.setVisibility(View.VISIBLE);
            rvAddOn.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext.getActivity(), 2);
            rvAddOn.setLayoutManager(mLayoutManager);
            GridTermAdapter adapter = new GridTermAdapter(mContext.getActivity(), responseEntity.getKeyFeatures().split("\\|"));
            rvAddOn.setAdapter(adapter);

        } else {
            llAddon.setVisibility(View.GONE);
        }*/
    }

    public boolean isValidInput() {
        if (etFirstName.getText().toString().isEmpty()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etFirstName.requestFocus();
                etFirstName.setError("Enter First Name");
                etFirstName.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etFirstName.requestFocus();
                etFirstName.setError("Enter First Name");
                return false;
            }
        }

        if (etLastName.getText().toString().isEmpty()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etLastName.requestFocus();
                etLastName.setError("Enter Last Name");
                etLastName.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etLastName.requestFocus();
                etLastName.setError("Enter Last Name");
                return false;
            }
        }
        if (etDOB.getText().toString().isEmpty()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etDOB.requestFocus();
                etDOB.setError("Enter Dob");
                etDOB.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etDOB.requestFocus();
                etDOB.setError("Enter Dob");
                return false;
            }
        }
        /*if (!isValidePhoneNumber(etMobile)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etMobile.requestFocus();
                etMobile.setError("Enter Mobile");
                etMobile.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etMobile.requestFocus();
                etMobile.setError("Enter Mobile");
                return false;
            }
        }*/
        if (etPincode.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etPincode.requestFocus();
                etPincode.setError("Enter Pincode");
                etPincode.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etPincode.requestFocus();
                etPincode.setError("Enter Pincode");
                return false;
            }

        }
        if (!etPincode.getText().toString().isEmpty()) {
            if (etPincode.getText().toString().length() != 6) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etPincode.requestFocus();
                    etPincode.setError("Enter Valid Pincode");
                    etPincode.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etPincode.requestFocus();
                    etPincode.setError("Enter Valid Pincode");
                    return false;
                }
            }

        }

        if (etICICISumAssured.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etICICISumAssured.requestFocus();
                etICICISumAssured.setError("Enter Sum Assured");
                etICICISumAssured.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etICICISumAssured.requestFocus();
                etICICISumAssured.setError("Enter Sum Assured");
                return false;
            }

        } else {
            CalculateLumsumAmt();
            ChkSumAssu();
        }

        if (etICICIPolicyTerm.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etICICIPolicyTerm.requestFocus();
                etICICIPolicyTerm.setError("Enter Policy Term ");
                etICICIPolicyTerm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etICICIPolicyTerm.requestFocus();
                etICICIPolicyTerm.setError("Enter Policy Term ");
                return false;
            }

        }

        if (etICICIPremiumTerm.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etICICIPremiumTerm.requestFocus();
                etICICIPremiumTerm.setError("Enter Premium Term ");
                etICICIPremiumTerm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etICICIPremiumTerm.requestFocus();
                etICICIPremiumTerm.setError("Enter Premium Term ");
                return false;
            }

        }
        if (llHDFCSAInc.getVisibility() == View.VISIBLE) {
            if (etHDFCSAInc.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etHDFCSAInc.requestFocus();
                    etHDFCSAInc.setError("Enter SA Increasing@");
                    etHDFCSAInc.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etHDFCSAInc.requestFocus();
                    etHDFCSAInc.setError("Enter SA Increasing@");
                    return false;
                }
            }
        }
        if (llIncDeath.getVisibility() == View.VISIBLE) {
            if (etIncDeath.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etIncDeath.requestFocus();
                    etIncDeath.setError("Enter Income On Death");
                    etIncDeath.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etIncDeath.requestFocus();
                    etIncDeath.setError("Enter Income On Death");
                    return false;
                }
            }
        }
        if (llIncPeriod.getVisibility() == View.VISIBLE) {
            if (etIncPeriod.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etIncPeriod.requestFocus();
                    etIncPeriod.setError("Enter Income Period");
                    etIncPeriod.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etIncPeriod.requestFocus();
                    etIncPeriod.setError("Enter Income Period");
                    return false;
                }
            }
        }
        if (llINCREASING.getVisibility() == View.VISIBLE) {
            if (etINCREASING.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etINCREASING.requestFocus();
                    etINCREASING.setError("Enter Increasing@");
                    etINCREASING.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etINCREASING.requestFocus();
                    etINCREASING.setError("Enter Increasing@");
                    return false;
                }
            }
        }
        if (llAdb.getVisibility() == View.VISIBLE) {
            if (etAdb.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etAdb.requestFocus();
                    etAdb.setError("Enter ADB %");
                    etAdb.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etAdb.requestFocus();
                    etAdb.setError("Enter ADB %");
                    return false;
                }
            }
        }


        return true;
    }

    private void setTermRequest() {
        termRequestEntity.setLumpsumPercentage("0");
        //termRequestEntity.setPolicyTerm("" + dbPersistanceController.getPremYearID(spPolicyTerm.getSelectedItem().toString()));

        if (isMale)
            termRequestEntity.setInsuredGender("M");
        else
            termRequestEntity.setInsuredGender("F");

        if (isSmoker)
            termRequestEntity.setIs_TabaccoUser("true");
        else
            termRequestEntity.setIs_TabaccoUser("false");


        termRequestEntity.setSumAssured(etSumAssured.getText().toString().replaceAll("\\,", ""));
        termRequestEntity.setInsuredDOB(etDOB.getText().toString());
        termRequestEntity.setPaymentModeValue("1");
        termRequestEntity.setPolicyCommencementDate(etDOB.getText().toString());
        termRequestEntity.setCityName("Mumbai");
        termRequestEntity.setState("Maharashtra");
        //termRequestEntity.setPlanTaken("Life");
        // termRequestEntity.setFrequency("Annual");
        //termRequestEntity.setDeathBenefitOption("Lump-Sum");
        //termRequestEntity.setPPT("" + dbPersistanceController.getPremYearID(spPremTerm.getSelectedItem().toString()));
        //termRequestEntity.setIncomeTerm("" + dbPersistanceController.getPremYearID(spPremTerm.getSelectedItem().toString()));

        //termRequestEntity.setInsurerId(0);
        termRequestEntity.setSessionID("");
        termRequestEntity.setContactName(etFirstName.getText().toString() + " " + etLastName.getText().toString());
        termRequestEntity.setContactEmail("finmarttest@gmail.com");
        termRequestEntity.setContactMobile(etMobile.getText().toString());
        //termRequestEntity.setSupportsAgentID("1682");
        termRequestEntity.setPincode(etPincode.getText().toString());


        //icici specific
        termRequestEntity.setMaritalStatus("");
        //termRequestEntity.setPremiumPaymentOption(""); //set in optionSelected
        termRequestEntity.setServiceTaxNotApplicable("");// not known


        if (llHDFCSAInc.getVisibility() == View.VISIBLE)
            termRequestEntity.setIncreaseSAPercentage("" + etHDFCSAInc.getText().toString().replaceAll("\\,", ""));
        else
            termRequestEntity.setIncreaseSAPercentage("0");

        if (llIncDeath.getVisibility() == View.VISIBLE)
            termRequestEntity.setMonthlyIncome("" + etIncDeath.getText().toString().replaceAll("\\,", ""));
        else
            termRequestEntity.setMonthlyIncome("0");

        if (llIncPeriod.getVisibility() == View.VISIBLE)
            termRequestEntity.setIncomeTerm("" + etIncPeriod.getText().toString().replaceAll("\\,", ""));
        else
            termRequestEntity.setIncomeTerm("0");

        if (llINCREASING.getVisibility() == View.VISIBLE)
            termRequestEntity.setIncreaseIncomePercentage(etINCREASING.getText().toString());
        else
            termRequestEntity.setIncreaseIncomePercentage("0");

        if (llAdb.getVisibility() == View.VISIBLE)
            termRequestEntity.setADBPercentage(etAdb.getText().toString());
        else
            termRequestEntity.setADBPercentage("0");

        if (hfLumsumAmt != 0) {
            termRequestEntity.setLumpsumAmount("" + hfLumsumAmt);
        }
        if (hfLumsumPayOutOnDeath != 0) {
            termRequestEntity.setLumpsumAmount("" + hfLumsumPayOutOnDeath);
        }


        termRequestEntity.setPolicyTerm("" + etICICIPolicyTerm.getText().toString());
        termRequestEntity.setInsurerId(28);
        //termRequestEntity.setPlanTaken("Life");// set in manipulateInputs()
        //termRequestEntity.setFrequency("Annual"); //set in optionSelected
        //termRequestEntity.setDeathBenefitOption("Lump-Sum"); //set in incomeSelection()
        termRequestEntity.setPPT("" + etICICIPremiumTerm.getText().toString());

       /* if (termCompareQuoteResponse != null && termCompareQuoteResponse.getMasterData() != null && termCompareQuoteResponse.getMasterData().getLifeTermRequestID() != 0)
            termFinmartRequest.setTermRequestId(termCompareQuoteResponse.getMasterData().getLifeTermRequestID());
        else
            termFinmartRequest.setTermRequestId(0);*/
        termFinmartRequest.setFba_id(new DBPersistanceController(getActivity()).getUserData().getFBAId());
        termFinmartRequest.setTermRequestEntity(termRequestEntity);
    }

    private void setDefaultValues() {
        etICICISumAssured.setText("10000000");
        etICICIPolicyTerm.setText("20");
        etICICIPremiumTerm.setText("20");
        etHDFCSAInc.setText("10");
        etIncDeath.setText("25000");
        etIncPeriod.setText("20");
        etINCREASING.setText("5");
        etAdb.setText("100");
    }


    private void init_view(View view) {

        cvInputDetails = (CardView) view.findViewById(R.id.cvInputDetails);
        cvQuoteDetails = (CardView) view.findViewById(R.id.cvQuoteDetails);
        layoutCompare = (LinearLayout) view.findViewById(R.id.layoutCompare);
        mainScroll = (ScrollView) view.findViewById(R.id.mainScroll);
        tilPincode = (TextInputLayout) view.findViewById(R.id.tilPincode);
        tvSum = (TextView) view.findViewById(R.id.tvSum);
        tvGender = (TextView) view.findViewById(R.id.tvGender);
        tvSmoker = (TextView) view.findViewById(R.id.tvSmoker);
        tvAge = (TextView) view.findViewById(R.id.tvAge);
        tvPolicyTerm = (TextView) view.findViewById(R.id.tvPolicyTerm);
        tvCrn = (TextView) view.findViewById(R.id.tvCrn);
        ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
        ivInfo = (ImageView) view.findViewById(R.id.ivInfo);

        llAddon = (LinearLayout) view.findViewById(R.id.llAddon);
        rvAddOn = (RecyclerView) view.findViewById(R.id.rvAddOn);
        txtAge = (TextView) view.findViewById(R.id.txtAge);
        txtPlanNAme = (TextView) view.findViewById(R.id.txtPlanNAme);
        txtCover = (TextView) view.findViewById(R.id.txtCover);
        txtFinalPremium = (TextView) view.findViewById(R.id.txtFinalPremium);
        imgInsurerLogo = (ImageView) view.findViewById(R.id.imgInsurerLogo);
        ivBuy = (ImageView) view.findViewById(R.id.ivBuy);
        ivPdf = (ImageView) view.findViewById(R.id.ivPdf);
        txtPolicyTerm = (TextView) view.findViewById(R.id.txtPolicyTerm);

        btnGetQuote = (Button) view.findViewById(R.id.btnGetQuote);
        etPincode = (EditText) view.findViewById(R.id.etPincode);
        etSumAssured = (EditText) view.findViewById(R.id.etICICISumAssured);

        // common input
        tvMale = (TextView) view.findViewById(R.id.tvMale);
        tvFemale = (TextView) view.findViewById(R.id.tvFemale);
        tvYes = (TextView) view.findViewById(R.id.tvYes);
        tvNo = (TextView) view.findViewById(R.id.tvNo);
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etDOB = (EditText) view.findViewById(R.id.etDateofBirth);
        llGender = (LinearLayout) view.findViewById(R.id.llGender);
        llSmoker = (LinearLayout) view.findViewById(R.id.llSmoker);

        spHDFCOptions = (Spinner) view.findViewById(R.id.spHDFCOptions);
        spHdfcPremFrq = (Spinner) view.findViewById(R.id.spHdfcPremFrq);

        //region hdfc specifc

        minusICICISum = (Button) view.findViewById(R.id.minusICICISum);
        plusICICISum = (Button) view.findViewById(R.id.plusICICISum);
        etICICISumAssured = (EditText) view.findViewById(R.id.etICICISumAssured);

        minusICICIPTerm = (Button) view.findViewById(R.id.minusICICIPTerm);
        plusICICIPTerm = (Button) view.findViewById(R.id.plusICICIPTerm);
        etICICIPolicyTerm = (EditText) view.findViewById(R.id.etICICIPolicyTerm);

        minusICICIPreTerm = (Button) view.findViewById(R.id.minusICICIPreTerm);
        plusICICIPreTerm = (Button) view.findViewById(R.id.plusICICIPreTerm);
        etICICIPremiumTerm = (EditText) view.findViewById(R.id.etICICIPremiumTerm);

        llHDFCSAInc = (LinearLayout) view.findViewById(R.id.llHDFCSAInc);
        minusHDFCSAInc = (Button) view.findViewById(R.id.minusHDFCSAInc);
        plusHDFCSAInc = (Button) view.findViewById(R.id.plusHDFCSAInc);
        etHDFCSAInc = (EditText) view.findViewById(R.id.etHDFCSAInc);

        llIncDeath = (LinearLayout) view.findViewById(R.id.llIncDeath);
        minusIncDeath = (Button) view.findViewById(R.id.minusIncDeath);
        plusIncDeath = (Button) view.findViewById(R.id.plusIncDeath);
        etIncDeath = (EditText) view.findViewById(R.id.etIncDeath);

        llIncPeriod = (LinearLayout) view.findViewById(R.id.llIncPeriod);
        minusIncPeriod = (Button) view.findViewById(R.id.minusIncPeriod);
        plusIncPeriod = (Button) view.findViewById(R.id.plusIncPeriod);
        etIncPeriod = (EditText) view.findViewById(R.id.etIncPeriod);

        llINCREASING = (LinearLayout) view.findViewById(R.id.llINCREASING);
        minusINCREASING = (Button) view.findViewById(R.id.minusINCREASING);
        plusINCREASING = (Button) view.findViewById(R.id.plusINCREASING);
        etINCREASING = (EditText) view.findViewById(R.id.etINCREASING);

        llAdb = (LinearLayout) view.findViewById(R.id.llAdb);
        minusAdb = (Button) view.findViewById(R.id.minusAdb);
        plusAdb = (Button) view.findViewById(R.id.plusAdb);
        etAdb = (EditText) view.findViewById(R.id.etAdb);


        //endregion

    }

    private void setListener() {
        ivEdit.setOnClickListener(this);
        ivInfo.setOnClickListener(this);
        ivBuy.setOnClickListener(this);
        ivPdf.setOnClickListener(this);
        tvMale.setOnClickListener(this);
        tvFemale.setOnClickListener(this);
        tvYes.setOnClickListener(this);
        tvNo.setOnClickListener(this);
//        filter.setOnClickListener(this);

        btnGetQuote.setOnClickListener(this);
        ivEdit.setOnClickListener(this);
        ivBuy.setOnClickListener(this);
//        filter.setOnClickListener(this);

        btnGetQuote.setOnClickListener(this);
        minusICICISum.setOnClickListener(this);
        plusICICISum.setOnClickListener(this);
        minusICICIPTerm.setOnClickListener(this);
        plusICICIPTerm.setOnClickListener(this);
        minusICICIPreTerm.setOnClickListener(this);
        plusICICIPreTerm.setOnClickListener(this);
        minusHDFCSAInc.setOnClickListener(this);
        plusHDFCSAInc.setOnClickListener(this);
        minusIncDeath.setOnClickListener(this);
        plusIncDeath.setOnClickListener(this);
        minusIncPeriod.setOnClickListener(this);
        plusIncPeriod.setOnClickListener(this);
        minusINCREASING.setOnClickListener(this);
        plusINCREASING.setOnClickListener(this);
        minusAdb.setOnClickListener(this);
        plusAdb.setOnClickListener(this);


        etDOB.setOnClickListener(datePickerDialog);


        etICICISumAssured.setOnFocusChangeListener(this);
        etICICIPolicyTerm.setOnFocusChangeListener(this);
        etICICIPremiumTerm.setOnFocusChangeListener(this);
        etHDFCSAInc.setOnFocusChangeListener(this);
        etIncDeath.setOnFocusChangeListener(this);
        etIncPeriod.setOnFocusChangeListener(this);
        etINCREASING.setOnFocusChangeListener(this);
        etIncDeath.setOnFocusChangeListener(this);
        etAdb.setOnFocusChangeListener(this);


        spHDFCOptions.setOnItemSelectedListener(optionSelected);
        spHdfcPremFrq.setOnItemSelectedListener(optionSelected);
    }

    AdapterView.OnItemSelectedListener optionSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent.getId() == R.id.spHDFCOptions) {
                manipulateInputs(spHDFCOptions.getSelectedItem().toString());

            } else if (parent.getId() == R.id.spHdfcPremFrq) {


                switch (spHdfcPremFrq.getSelectedItemPosition()) {

                    case 0:
                        termRequestEntity.setFrequency("Yearly");
                        etICICIPremiumTerm.setEnabled(true);
                        etHDFCSAInc.setEnabled(true);
                        etICICIPremiumTerm.setText("" + etICICIPolicyTerm.getText().toString());
                        break;
                    case 1:
                        termRequestEntity.setFrequency("Half Yearly");
                        etICICIPremiumTerm.setEnabled(true);
                        etHDFCSAInc.setEnabled(true);
                        etICICIPremiumTerm.setText("" + etICICIPolicyTerm.getText().toString());
                        break;
                    case 2:
                        termRequestEntity.setFrequency("Quarterly");
                        etICICIPremiumTerm.setEnabled(true);
                        etHDFCSAInc.setEnabled(true);
                        etICICIPremiumTerm.setText("" + etICICIPolicyTerm.getText().toString());
                        break;
                    case 3:
                        termRequestEntity.setFrequency("Monthly");
                        etICICIPremiumTerm.setEnabled(true);
                        etHDFCSAInc.setEnabled(true);
                        etICICIPremiumTerm.setText("" + etICICIPolicyTerm.getText().toString());
                        break;
                    case 4:
                        termRequestEntity.setFrequency("Single");
                        if (!spHDFCOptions.getSelectedItem().toString().equals("LIFE LONG PROTECTION") &&
                                !spHDFCOptions.getSelectedItem().toString().equals("3D LIFE LONG PROTECTION")) {

                            etHDFCSAInc.setText("0");
                            etICICIPremiumTerm.setText("1");
                            etICICIPremiumTerm.setEnabled(false);
                            etHDFCSAInc.setEnabled(false);
                        }
                        break;
                }

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onClick(View view) {
        Constants.hideKeyBoard(view, getActivity());
        int i = view.getId();
        if (i == R.id.ivBuy) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "HDFC BUY TERM INSURANCE", "HDFC BUY TERM INSURANCE");
            new TermInsuranceController(getActivity()).convertQuoteToApp("" + termFinmartRequest.getTermRequestId(),
                    "28",
                    "" + dbPersistanceController.getUserData().getFBAId(),
                    "" + termCompareResponseEntity.getNetPremium(), this);
            startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                    .putExtra("URL", termCompareResponseEntity.getProposerPageUrl())
                    .putExtra("NAME", "CLICK TO PROTECT 3D")
                    .putExtra("TITLE", "CLICK TO PROTECT 3D"));
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Life Ins Buy"), Constants.LIFE_INS), null);


        } else if (i == R.id.ivPdf) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "HDFC PDF  TERM INSURANCE", "HDFC PDF TERM INSURANCE");
            shareTermHdfc();
                /*if (termCompareResponseEntity != null && termCompareResponseEntity.getPdfUrl().equals("")) {
                    Toast.makeText(getActivity(), "Pdf Not Available", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                            .putExtra("URL", termCompareResponseEntity.getPdfUrl())
                            .putExtra("NAME", "CLICK TO PROTECT 3D")
                            .putExtra("TITLE", "CLICK TO PROTECT 3D"));

                }*/

        } else if (i == R.id.btnGetQuote) {
            if (isValidInput()) {
                MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "GET QUOTE TERM INSURANCE", "GET QUOTE TERM INSURANCE");
                setTermRequest();
                //((IciciTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
                fetchQuotes();
            }

        } else if (i == R.id.ivEdit) {
            if (getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA) != null)
                ((CompareTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            else
                ((HdfcTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            changeInputQuote(true);

        } else if (i == R.id.ivInfo) {
            OpenPoupWnidow(termCompareResponseEntity.getKeyFeatures().split("\\|"));

        } else if (i == R.id.tvMale) {
            isMale = true;
            tvFemale.setBackgroundResource(R.drawable.customeborder);
            tvMale.setBackgroundResource(R.drawable.customeborder_blue);

        } else if (i == R.id.tvFemale) {
            isMale = false;
            tvMale.setBackgroundResource(R.drawable.customeborder);
            tvFemale.setBackgroundResource(R.drawable.customeborder_blue);

        } else if (i == R.id.tvYes) {
            isSmoker = true;
            tvNo.setBackgroundResource(R.drawable.customeborder);
            tvYes.setBackgroundResource(R.drawable.customeborder_blue);

        } else if (i == R.id.tvNo) {
            isSmoker = false;
            tvYes.setBackgroundResource(R.drawable.customeborder);
            tvNo.setBackgroundResource(R.drawable.customeborder_blue);

        } else if (i == R.id.minusICICISum) {
            changeSumAssured(false);

        } else if (i == R.id.plusICICISum) {
            changeSumAssured(true);

        } else if (i == R.id.minusICICIPTerm) {
            changePolicyTerm(false);

        } else if (i == R.id.plusICICIPTerm) {
            changePolicyTerm(true);

        } else if (i == R.id.minusICICIPreTerm) {
            changePremTerm(false);

        } else if (i == R.id.plusICICIPreTerm) {
            changePremTerm(true);

        } else if (i == R.id.minusHDFCSAInc) {
            changeSAIncreasing(false, etHDFCSAInc, 0);

        } else if (i == R.id.plusHDFCSAInc) {
            changeSAIncreasing(true, etHDFCSAInc, 10);

        } else if (i == R.id.minusIncDeath) {
            changeIncDeath(false);

        } else if (i == R.id.plusIncDeath) {
            changeIncDeath(true);

        } else if (i == R.id.minusIncPeriod) {
            changeSAIncreasing(false, etIncPeriod, 1);

        } else if (i == R.id.plusIncPeriod) {
            changeSAIncreasing(true, etIncPeriod, 20);

        } else if (i == R.id.minusINCREASING) {
            changeSAIncreasing(false, etINCREASING, 1);

        } else if (i == R.id.plusINCREASING) {
            changeSAIncreasing(true, etINCREASING, 100);

        } else if (i == R.id.minusAdb) {
            changeSAIncreasing(false, etAdb, 1);

        } else if (i == R.id.plusAdb) {
            changeSAIncreasing(true, etAdb, 100);

        }
    }


    public void fetchQuotes() {
        showDialog();
        new TermInsuranceController(getActivity()).getTermInsurer(termFinmartRequest, this);
    }
    private void updateCrnToServer() {
        if ( termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id()!=null &&!termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id().equals(""))
            new TermInsuranceController(getActivity()).updateCRN(termFinmartRequest.getTermRequestId(), Integer.parseInt(termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id()), this);
    }
    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof TermCompareQuoteResponse) {
            cancelDialog();
            processResponse((TermCompareQuoteResponse) response);
            new AsyncShareJson().execute();
            /*this.termCompareQuoteResponse = (TermCompareQuoteResponse) response;
            mainScroll.fullScroll(ScrollView.FOCUS_UP);
            //mAdapter = new TermQuoteAdapter(IciciTermQuoteFragment.this, termCompareQuoteResponse);
            //rvTerm.setAdapter(mAdapter);
            String crn = "" + termCompareQuoteResponse.getMasterData().getResponse().get(0).getCustomerReferenceID();
            termRequestEntity.setCrn(crn);
            termFinmartRequest.setTermRequestEntity(termRequestEntity);
            if (((TermCompareQuoteResponse) response).getMasterData().getLifeTermRequestID() != 0)
                termRequestId = ((TermCompareQuoteResponse) response).getMasterData().getLifeTermRequestID();
            termFinmartRequest.setTermRequestId(termRequestId);
            bindHeaders();
            bindQuotes();
            changeInputQuote(false);*/
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        changeInputQuote(true);
    }

    private void processResponse(TermCompareQuoteResponse termCompareQuoteResponse) {
        /*mainScroll.fullScroll(ScrollView.FOCUS_UP);*/

        if (termCompareQuoteResponse.getMasterData() != null && termCompareQuoteResponse.getMasterData().getResponse() != null) {
            if (termCompareQuoteResponse.getMasterData().getResponse().size() != 0) {
                this.termCompareResponseEntity = termCompareQuoteResponse.getMasterData().getResponse().get(0);
                crn = "" + termCompareQuoteResponse.getMasterData().getResponse().get(0).getCustomerReferenceID();
                termRequestEntity.setExisting_ProductInsuranceMapping_Id(crn);
                termFinmartRequest.setTermRequestEntity(termRequestEntity);
                if (termCompareQuoteResponse.getMasterData().getLifeTermRequestID() != 0)
                    termRequestId = termCompareQuoteResponse.getMasterData().getLifeTermRequestID();
                termFinmartRequest.setTermRequestId(termRequestId);
                updateCrnToServer();
                if (termCompareResponseEntity.getQuoteStatus().equals("Success")) {
                    bindHeaders();
                    changeInputQuote(false);
                    bindQuotes();
                } else {
                    Toast.makeText(getActivity(), "" + termCompareResponseEntity.getQuoteStatus(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "No Quotes Found.", Toast.LENGTH_SHORT).show();
            }
        }
        mainScroll.scrollTo(0,0);
    }

    //region datepicker
    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, getActivity());

            //region dob

            DateTimePicker.showHealthAgeDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                    if (view1.isShown()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String currentDay = simpleDateFormat.format(calendar.getTime());
                        etDOB.setText(currentDay);
                        age = caluclateAge(calendar);
                        String options = spHDFCOptions.getSelectedItem().toString();
                        if (options.equals("LIFE LONG PROTECTION") || options.equals("3D LIFE LONG PROTECTION")) {
                            CalculatePolicyAndPremTerm();
                        }
                        //setPolicyTerm((75 - age));
                    }
                }
            });

            //endregion
        }
    };

//endregion

    public int caluclateAge(Calendar dob) {
        Calendar current = Calendar.getInstance();
        int diff = current.get(YEAR) - dob.get(YEAR);
        if (dob.get(MONTH) > current.get(MONTH) ||
                (dob.get(MONTH) == current.get(MONTH) && dob.get(DATE) > current.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public int caluclateAge(String dateofbirth) {
        Date date = new Date();
        try {

            date = simpleDateFormat.parse(dateofbirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar dob = Calendar.getInstance();
        dob.setTime(date);
        Calendar current = Calendar.getInstance();
        int diff = current.get(YEAR) - dob.get(YEAR);
        if (dob.get(MONTH) > current.get(MONTH) ||
                (dob.get(MONTH) == current.get(MONTH) && dob.get(DATE) > current.get(DATE))) {
            diff--;
        }
        return diff;
    }

    private void changeInputQuote(boolean isInput) {
        if (isInput) {
            //((IciciTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            btnGetQuote.setText("GET QUOTE");
            tilPincode.setVisibility(View.VISIBLE);
            layoutCompare.setVisibility(View.VISIBLE);
            cvInputDetails.setVisibility(View.GONE);
            cvQuoteDetails.setVisibility(View.GONE);
        } else {
            if (getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA) != null)
                ((CompareTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
            else
                ((HdfcTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
            btnGetQuote.setText("UPDATE QUOTE");
            tilPincode.setVisibility(View.INVISIBLE);
            layoutCompare.setVisibility(View.GONE);
            cvInputDetails.setVisibility(View.VISIBLE);
            cvQuoteDetails.setVisibility(View.VISIBLE);
        }
    }

    private void fromCompare() {
        btnGetQuote.setText("UPDATE QUOTE");
        tilPincode.setVisibility(View.INVISIBLE);
        layoutCompare.setVisibility(View.GONE);
        cvInputDetails.setVisibility(View.VISIBLE);
        cvQuoteDetails.setVisibility(View.VISIBLE);
    }

    private void manipulateInputs(String s) {
        switch (s) {
            case "LIFE":
                termRequestEntity.setPlanTaken("Life");
                hideAllLayout();
                clearValues();
                etHDFCSAInc.setText("10");
                SetLumsumPayOutOnDeath();
                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    etICICIPremiumTerm.setEnabled(true);
                    etHDFCSAInc.setEnabled(true);
                } else {
                    etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    etICICIPremiumTerm.setEnabled(false);
                    etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }

                break;
            case "3D LIFE":
                termRequestEntity.setPlanTaken("3D Life");
                hideAllLayout();
                clearValues();

                etHDFCSAInc.setText("10");
                SetLumsumPayOutOnDeath();
                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    etICICIPremiumTerm.setEnabled(true);
                    etHDFCSAInc.setEnabled(true);
                } else {
                    etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    etICICIPremiumTerm.setEnabled(false);
                    etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }

                break;
            case "LIFE LONG PROTECTION":
                termRequestEntity.setPlanTaken("Life Long Protection");
                hideAllLayout();
                clearValues();
                etHDFCSAInc.setText("10");
                SetLumsumPayOutOnDeath();
                CalculatePolicyAndPremTerm();

                break;
            case "3D LIFE LONG PROTECTION":
                termRequestEntity.setPlanTaken("3D Life Long Protection");
                hideAllLayout();
                clearValues();
                etHDFCSAInc.setText("10");
                SetLumsumPayOutOnDeath();
                CalculatePolicyAndPremTerm();

                break;
            case "EXTRA LIFE":
                termRequestEntity.setPlanTaken("Extra Life");
                hideAllLayout();
                clearValues();
                llAdb.setVisibility(View.VISIBLE);

                etAdb.setText("100");
                etHDFCSAInc.setText("10");

                CalculateLumsumAmt();

                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    //etICICIPremiumTerm.setEnabled(true);
                    //etHDFCSAInc.setEnabled(true);
                } else {
                    //etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    //etICICIPremiumTerm.setEnabled(false);
                    //etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }
                break;
            case "EXTRA LIFE INCOME":
                termRequestEntity.setPlanTaken("Extra Life Income");
                hideAllLayout();
                llIncDeath.setVisibility(View.VISIBLE);
                llIncPeriod.setVisibility(View.VISIBLE);
                llINCREASING.setVisibility(View.VISIBLE);
                llAdb.setVisibility(View.VISIBLE);
                clearValues();

                etHDFCSAInc.setText("10");
                etIncDeath.setText("25000");
                etIncPeriod.setText("20");
                etIncPeriod.setEnabled(true);
                etINCREASING.setText("5");
                etINCREASING.setEnabled(true);
                etAdb.setText("100");

                CalculateLumsumAmt();

                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    //etICICIPremiumTerm.setEnabled(true);
                    //etHDFCSAInc.setEnabled(true);
                } else {
                    //etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    //etICICIPremiumTerm.setEnabled(false);
                    //etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }

                break;
            case "INCOME OPTION":
                termRequestEntity.setPlanTaken("Income Option");
                hideAllLayout();
                llIncDeath.setVisibility(View.VISIBLE);
                llIncPeriod.setVisibility(View.VISIBLE);
                llINCREASING.setVisibility(View.VISIBLE);

                clearValues();

                etHDFCSAInc.setText("10");
                etIncDeath.setText("25000");

                etIncPeriod.setText("20");
                etIncPeriod.setEnabled(true);
                etINCREASING.setEnabled(true);
                etINCREASING.setText("5");

                SetLumsumPayOutOnDeath();

                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    //etICICIPremiumTerm.setEnabled(true);
                    //etHDFCSAInc.setEnabled(true);
                } else {
                    //etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    //etICICIPremiumTerm.setEnabled(false);
                    //etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }

                break;
            case "INCOME REPLACEMENT":
                termRequestEntity.setPlanTaken("Income Replacement");
                hideAllLayout();
                llIncDeath.setVisibility(View.VISIBLE);
                llINCREASING.setVisibility(View.VISIBLE);
                clearValues();

                etHDFCSAInc.setText("10");
                etIncDeath.setText("25000");
                etINCREASING.setText("10");

                if (!etIncDeath.getText().toString().equals("")) {
                    long txtMntlyIncomOnDeath = Long.parseLong(etIncDeath.getText().toString());
                    long Lumsum = txtMntlyIncomOnDeath * 12;
                    hfLumsumPayOutOnDeath = Lumsum;
                }

                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    //etICICIPremiumTerm.setEnabled(true);
                    //etHDFCSAInc.setEnabled(true);
                } else {
                    //etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    //etICICIPremiumTerm.setEnabled(false);
                    //etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }

                break;
            case "RETURN OF PREMIUM":
                termRequestEntity.setPlanTaken("Return of Premium");
                hideAllLayout();
                clearValues();

                etHDFCSAInc.setText("10");

                SetLumsumPayOutOnDeath();

                if (spHdfcPremFrq.getSelectedItemPosition() != 4) {// not single
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                    //etICICIPremiumTerm.setEnabled(true);
                    //etHDFCSAInc.setEnabled(true);
                } else {
                    //etHDFCSAInc.setText("0");
                    etICICIPremiumTerm.setText("1");
                    //etICICIPremiumTerm.setEnabled(false);
                    //etHDFCSAInc.setEnabled(false);
                    etICICIPolicyTerm.setText("20");
                }

                break;
        }
    }

    private void hideAllLayout() {
        //llHDFCSAInc.setVisibility(View.GONE);
        llIncDeath.setVisibility(View.GONE);
        llIncPeriod.setVisibility(View.GONE);
        llINCREASING.setVisibility(View.GONE);
        llAdb.setVisibility(View.GONE);
    }

    public void clearValues() {
        etIncDeath.setText("");
        etINCREASING.setText("");
        etIncPeriod.setText("");
        etAdb.setText("");
        etHDFCSAInc.setText("");
    }

    public void CalculatePolicyAndPremTerm() {
        int age = caluclateAge(etDOB.getText().toString());
        int policyTerm = 99 - age;
        int ppt = 65 - age;
        etICICIPolicyTerm.setText("" + policyTerm);
        etICICIPremiumTerm.setText("" + ppt);
    }

    public void CalculateLumsumAmt() {
        long AdbPercentage = 0,txtSumAssu=0;

        long txtMntlyIncomOnDeath = 0;

        if (!etIncDeath.getText().toString().equals("")) {
            txtMntlyIncomOnDeath = Long.parseLong(etIncDeath.getText().toString().replaceAll("\\,", ""));
        }
        if (!etICICISumAssured.getText().toString().equals("")) {
            txtSumAssu = Long.parseLong(etICICISumAssured.getText().toString().replaceAll("\\,", ""));
            hfLumsumPayOutOnDeath = txtSumAssu;
        }


        if (!etAdb.getText().toString().equals("")) {
            AdbPercentage = Long.parseLong(etAdb.getText().toString());
            if (AdbPercentage > 100) {
                AdbPercentage = 100;
                etAdb.setText("100");
            }
        }

        if (!etICICISumAssured.getText().toString().equals("") && !etAdb.getText().toString().equals("")) {
            double Adb = AdbPercentage / 100;
            double LumsumAmt = txtSumAssu * Adb;
            hfLumsumAmt = Math.round(LumsumAmt);
        }

        /*if (!etIncDeath.getText().toString().equals("") && !etAdb.getText().toString().equals("")) {
            //double Adb = AdbPercentage / 100;
            //double MlyIncome = txtMntlyIncomOnDeath * Adb;

            //$('#hfLumsumAmt').val(Math.round(LumsumAmt));
        }*/
    }

    public void SetLumsumPayOutOnDeath() {
        long SumInsu = 0;
        if (!etICICISumAssured.getText().toString().equals("")) {
            SumInsu = Long.parseLong(etICICISumAssured.getText().toString().replaceAll("\\,", ""));
            hfLumsumPayOutOnDeath = SumInsu;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int i = view.getId();
        if (i == R.id.etICICISumAssured) {
            if (!b) {
                CalculateLumsumAmt();
                ChkSumAssu();
            }

        } else if (i == R.id.etICICIPolicyTerm) {
            if (!b) {
                ChkPolicyTerm();
            }

        } else if (i == R.id.etICICIPremiumTerm) {
            if (!b) {
                ChkPremTerm();
            }

        } else if (i == R.id.etHDFCSAInc) {
            if (!b) {
                CheckMinMax(etHDFCSAInc, 10);
            }

        } else if (i == R.id.etIncDeath) {
            if (!b) {
                CheckMntlyIncomOnDeath();
            }

        } else if (i == R.id.etIncPeriod) {
            if (!b) {
                CheckMinMax(etIncPeriod, 20);
            }

        } else if (i == R.id.etINCREASING) {
            if (!b) {
                CheckMinMax(etINCREASING, 100);
            }

        } else if (i == R.id.etAdb) {
            if (!b) {
                CalculateLumsumAmt();
            }

        }
    }

    private void ChkSumAssu() {
        long txtSumAssu = 0;
        if (!etICICISumAssured.getText().toString().equals(""))
            txtSumAssu = Long.parseLong(etICICISumAssured.getText().toString().replaceAll("\\,", ""));
        if (txtSumAssu != 0) {
            if ((txtSumAssu) < 2500000) {
                txtSumAssu = 2500000;
            }

            if (txtSumAssu > 500000000) {
                etHDFCSAInc.setText("0");
                etHDFCSAInc.setEnabled(false);

            } else {
                //etHDFCSAInc.setText("10");
                etHDFCSAInc.setEnabled(true);
            }

            etICICISumAssured.setText("" + (txtSumAssu));
        }

    }

    private void changeSumAssured(boolean b) {
        long SumInsu = 0;

        if (!etICICISumAssured.getText().toString().equals(""))
            SumInsu = Long.parseLong(etICICISumAssured.getText().toString().replaceAll("\\,", ""));

        if (b) {


            if (SumInsu != 0) {
                if (SumInsu < 100000) {
                    SumInsu = SumInsu + 10000;
                } else if ((SumInsu) >= 100000 && (SumInsu) < 1000000) {
                    SumInsu = (SumInsu) + 100000;
                } else if ((SumInsu) >= 1000000 && (SumInsu) < 10000000) {
                    SumInsu = (SumInsu) + 500000;
                } else if ((SumInsu) >= 10000000) {
                    SumInsu = (SumInsu) + 500000;
                }

                if (SumInsu > 500000000) {
                    etHDFCSAInc.setText("0");
                    etHDFCSAInc.setEnabled(false);

                } else {
                    //etHDFCSAInc.setText("10");
                    etHDFCSAInc.setEnabled(true);
                }
            } else {
                SumInsu = 50000;
            }

            if (!spHDFCOptions.getSelectedItem().toString().equals("INCOME REPLACEMENT")) {
                hfLumsumPayOutOnDeath = SumInsu;
            }


        } else {

            if (SumInsu != 0) {
                if (SumInsu <= 2500000) {
                    SumInsu = 2500000;
                } else if (SumInsu > 2500000 && SumInsu <= 10000000) {
                    SumInsu = SumInsu - 500000;
                } else if (SumInsu > 10000000) {
                    SumInsu = SumInsu - 500000;
                }

                if (SumInsu > 500000000) {
                    etHDFCSAInc.setText("0");
                    etHDFCSAInc.setEnabled(false);

                } else {
                    //etHDFCSAInc.setText("10");
                    etHDFCSAInc.setEnabled(true);
                }
            } else {
                SumInsu = 2500000;
            }

            if (!spHDFCOptions.getSelectedItem().toString().equals("INCOME REPLACEMENT")) {
                hfLumsumPayOutOnDeath = SumInsu;
            }


        }

        // (sumAssured);
        etICICISumAssured.setText("" + (SumInsu));
    }

    private void changePolicyTerm(boolean b) {

        int min = 5;
        int term = 0;
        if (!etICICIPolicyTerm.getText().toString().equals(""))
            term = Integer.parseInt(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));

        //var dllPremFreq = $('#dllPremFreq').val();

        if (b) {

            if (term != 0) {
                term = (term) + 1;
                /*if (spHdfcPremFrq.getSelectedItemPosition() == 4) {
                    term = 1;
                }*/
            } else {
                term = 10;
            }
        } else {
            if (term != 0) {
                term = (term) - 1;
                if ((term) < min) {
                    term = min;
                }
                /*if (spHdfcPremFrq.getSelectedItemPosition() == 4) {
                    term = 1;
                }*/

            } else {
                term = 10;
            }
        }
        etICICIPolicyTerm.setText("" + (term));
        if (spHdfcPremFrq.getSelectedItemPosition() != 4) {

            if (!etICICIPolicyTerm.getText().toString().equals("") && !etICICIPremiumTerm.getText().toString().equals("")) {
                int txtPolicyTerm = Integer.parseInt(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));
                int txtPremiumTerm = Integer.parseInt(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

                if ((txtPremiumTerm) > (txtPolicyTerm)) {
                    etICICIPremiumTerm.setText("" + txtPolicyTerm);
                }
            }
        }

    }

    private void ChkPolicyTerm() {
        int min = 5;
        int term = 0;
        if (!etICICIPolicyTerm.getText().toString().equals(""))
            term = Integer.parseInt(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));

        if (term != 0) {

            if ((term) < min) {
                term = min;
            }
        } else {
            term = 10;
        }
        etICICIPolicyTerm.setText("" + (term));

        if (!etICICIPolicyTerm.getText().toString().equals("") && !etICICIPremiumTerm.getText().toString().equals("")) {
            int txtPolicyTerm = Integer.parseInt(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));
            int txtPremiumTerm = Integer.parseInt(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

            if ((txtPremiumTerm) > (txtPolicyTerm)) {
                etICICIPremiumTerm.setText("" + txtPolicyTerm);
            }
        }
    }

    private void changePremTerm(boolean b) {

        int min = 5;
        int term = 0;
        if (!etICICIPremiumTerm.getText().toString().equals(""))
            term = Integer.parseInt(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

        //var dllPremFreq = $('#dllPremFreq').val();

        if (b) {

            if (term != 0) {
                term = (term) + 1;
                if (spHdfcPremFrq.getSelectedItemPosition() == 4) {
                    term = 1;
                }
            } else {
                term = 10;
            }
        } else {
            if (term != 0) {
                term = (term) - 1;
                if ((term) < min) {
                    term = min;
                }
                if (spHdfcPremFrq.getSelectedItemPosition() == 4) {
                    term = 1;
                }

            } else {
                term = 10;
            }
        }
        etICICIPremiumTerm.setText("" + (term));
        if (spHdfcPremFrq.getSelectedItemPosition() != 4) {

            if (!etICICIPolicyTerm.getText().toString().equals("") && !etICICIPremiumTerm.getText().toString().equals("")) {
                int txtPolicyTerm = Integer.parseInt(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));
                int txtPremiumTerm = Integer.parseInt(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

                if ((txtPremiumTerm) > (txtPolicyTerm)) {
                    etICICIPremiumTerm.setText("" + txtPolicyTerm);
                }
            }
        }

    }

    private void ChkPremTerm() {
        int min = 5;
        int term = 0;
        if (!etICICIPremiumTerm.getText().toString().equals(""))
            term = Integer.parseInt(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

        if (term != 0) {

            if ((term) < min) {
                term = min;
            }
        } else {
            term = 10;
        }
        etICICIPremiumTerm.setText("" + (term));

        if (!etICICIPolicyTerm.getText().toString().equals("") && !etICICIPremiumTerm.getText().toString().equals("")) {
            int txtPolicyTerm = Integer.parseInt(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));
            int txtPremiumTerm = Integer.parseInt(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

            if ((txtPremiumTerm) > (txtPolicyTerm)) {
                etICICIPremiumTerm.setText("" + txtPolicyTerm);
            }
        }
    }

    private void changeSAIncreasing(boolean b, EditText editText, int min) {

        long term = 0, SumInsu = 0;
        String dllOption = spHDFCOptions.getSelectedItem().toString();
        if (!etICICISumAssured.getText().toString().equals(""))
            SumInsu = Long.parseLong(etICICISumAssured.getText().toString().replaceAll("\\,", ""));
        if (!editText.getText().toString().equals(""))
            term = Long.parseLong(editText.getText().toString().replaceAll("\\,", ""));

        if (b) {


            if (dllOption == "INCOME REPLACEMENT" && editText.getId() == R.id.etINCREASING) {

            } else {
                if ((SumInsu) > 500000000 && editText.getId() == R.id.etHDFCSAInc) {

                } else if (spHdfcPremFrq.getSelectedItemPosition() == 4 && editText.getId() == R.id.etHDFCSAInc) {

                } else {
                    if (term != 0) {
                        if ((term) >= min) {
                            term = min;
                        } else {
                            term = (term) + 1;
                        }
                    } else {
                        term = 10;
                    }

                    if (editText.getId() == R.id.etAdb) {
                        CalculateLumsumAmt();
                    }

                    editText.setText("" + (term));
                }
            }


        } else {

            if (dllOption == "INCOME REPLACEMENT" && editText.getId() == R.id.etINCREASING) {

            } else {
                if ((SumInsu) > 500000000 && editText.getId() == R.id.etHDFCSAInc) {

                } else if (spHdfcPremFrq.getSelectedItemPosition() == 4 && editText.getId() == R.id.etHDFCSAInc) {

                } else {
                    if (term != 0) {

                        term = (term) - 1;

                        if ((term) <= 0) {
                            term = min;
                        }
                    } else {
                        term = 10;
                    }

                    if (editText.getId() == R.id.etAdb) {
                        CalculateLumsumAmt();
                    }

                    editText.setText("" + (term));
                }
            }
        }
    }

    private void CheckMinMax(EditText editText, int max) {
        long value = 0;
        if (!editText.getText().toString().equals(""))
            value = Long.parseLong(editText.getText().toString().replaceAll("\\,", ""));


        if (value != 0) {
            if ((value) > max) {
                value = max;
            }

            if ((value) <= 0) {
                value = 1;
            }
        } else {
            value = 1;
        }

        editText.setText("" + (value));
    }

    private void changeIncDeath(boolean b) {
        long Income = 0;
        String dllOption = spHDFCOptions.getSelectedItem().toString();
        if (!etIncDeath.getText().toString().equals(""))
            Income = Long.parseLong(etIncDeath.getText().toString().replaceAll("\\,", ""));


        if (b) {
            if (Income != 0) {
                if ((Income) < 1000) {
                    Income = (Income) + 100;
                } else if ((Income) >= 1000 && (Income) < 100000) {
                    Income = (Income) + 1000;
                } else if ((Income) >= 100000 && (Income) < 2500000) {
                    Income = (Income) + 100000;
                } else if ((Income) >= 2500000 && (Income) < 10000000) {
                    Income = (Income) + 500000;
                } else if ((Income) >= 10000000) {
                    Income = (Income) + 500000;
                }
            } else {
                Income = 25000;
            }


        } else {

            if (Income != 0) {
                if ((Income) <= 100) {
                    Income = 0;
                } else if ((Income) <= 1000) {
                    Income = (Income) - 100;
                } else if ((Income) <= 100000) {
                    Income = (Income) - 1000;
                }
                //else if (parseInt(Income) >= 100000) {
                //    Income = parseInt(Income) - 10000;
                //}
                else if ((Income) > 100000 && (Income) <= 1000000) {
                    Income = (Income) - 100000;
                } else if ((Income) > 1000000 && (Income) <= 10000000) {
                    Income = (Income) - 500000;
                } else if ((Income) > 10000000) {
                    Income = (Income) - 500000;
                }
            } else {
                Income = 25000;
            }
        }

        if (dllOption == "INCOME REPLACEMENT") {
            if (Income != 0) {
                long Lumsum = (Income) * 12;
                hfLumsumPayOutOnDeath = Lumsum;
                //$('#hfLumsumPayOutOnDeath').val(Lumsum);
            }
        }
        etIncDeath.setText("" + (Income));


    }

    private void CheckMntlyIncomOnDeath() {
        long Income = 0;
        String dllOption = spHDFCOptions.getSelectedItem().toString();
        if (!etIncDeath.getText().toString().equals(""))
            Income = Long.parseLong(etIncDeath.getText().toString().replaceAll("\\,", ""));
        if (dllOption == "INCOME REPLACEMENT") {
            if (Income != 0) {
                long Lumsum = (Income) * 12;
                hfLumsumPayOutOnDeath = Lumsum;
            }
        }
    }

    private void setPopUpInfo() {

        // region set Default popUp
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        customView = inflater.inflate(R.layout.layout_benefit_iprotect, null);

        // Initialize a new instance of popup window

        mPopupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                700
        );


        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }


        //endregion

        // region set Selection popUp
        LayoutInflater inflaterSelection = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        customViewSelection = inflaterSelection.inflate(R.layout.layout_age_popup_selected, null);

        // Initialize a new instance of popup window
        mPopupWindowSelection = new PopupWindow(
                customViewSelection,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        //endregion

    }

    private void OpenPoupWnidow(String[] strings) {


        // Get a reference for the custom view close button
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.imgClose);
        TextView tvTitle = (TextView) customView.findViewById(R.id.tvTitle);
        tvTitle.setText("BENEFIT OF " + termCompareResponseEntity.getProductPlanName());
        rvIprotectSmart = (RecyclerView) customView.findViewById(R.id.rvIprotectSmart);
        rvIprotectSmart.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvIprotectSmart.setLayoutManager(layoutManager);

        adapter = new HdfcIProtectAdapter(getActivity(), strings);
        rvIprotectSmart.setAdapter(adapter);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mPopupWindowSelection.dismiss();


                }
            }
        });


        mPopupWindowSelection.showAsDropDown(ivInfo, 0, 0);
        mPopupWindowSelection.setTouchable(true);
        mPopupWindowSelection.setFocusable(true);


        // mPopupWindow.setAnimationStyle(R.style.Animation);
        mPopupWindow.showAsDropDown(ivInfo, -40, 40);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);

    }


    class AsyncShareJson extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            if (termCompareResponseEntity != null) {
                int uptoAge = Integer.parseInt(termRequestEntity.getPPT()) + caluclateAge(etDOB.getText().toString());
                termCompareResponseEntity.setPPT(uptoAge);
                responseJson = gson.toJson(termCompareResponseEntity);
            }

            return responseJson;
        }

        @Override
        protected void onPostExecute(String s) {
            responseJson = s;
        }

    }

    private void shareTermHdfc() {
        if (responseJson.equals("") && termCompareResponseEntity != null) {
            new AsyncShareJson().execute();
        } else {
            Intent intent = new Intent(getActivity(), ShareQuoteActivity.class);
            intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "TERM_HDFC_QUOTE");
            intent.putExtra("RESPONSE", responseJson);
            intent.putExtra("NAME", termRequestEntity.getContactName());
            startActivity(intent);
        }
    }
}

