package com.datacomp.magicfinmart.term.icici;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ArrayAdapter;
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
import com.datacomp.magicfinmart.term.hdfc.HdfcIProtectAdapter;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class IciciTermInputFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener, BaseFragment.PopUpListener, IResponseSubcriber {

    private PopupWindow mPopupWindow, mPopupWindowSelection;
    View customView, customViewSelection;
    RecyclerView rvIprotectSmart;
    HdfcIProtectAdapter adapter;

    // quote
    TextView tvSum, tvGender, tvSmoker, tvAge, tvPolicyTerm, tvCrn, filter;
    ImageView ivEdit, ivInfo;
    TermCompareResponseEntity termCompareResponseEntity;
    CardView cvInputDetails, cvQuoteDetails;
    View layoutCompare;

    TextView txtPlanNAme, txtCover, txtFinalPremium, txtPolicyTerm, txtAge, txtCustomise, txtRiders;
    ImageView imgInsurerLogo, ivBuy, ivPdf;
    LinearLayout llAddon;
    RecyclerView rvAddOn;

    Button btnGetQuote;
    EditText etFirstName, etLastName, etMobile, etDOB;
    TextView tvMale, tvFemale, tvYes, tvNo;
    boolean isMale, isSmoker;
    LinearLayout llGender, llSmoker;


    EditText etPincode, etSumAssured;
    TextInputLayout tilPincode;
    List<String> policyYear;
    DBPersistanceController dbPersistanceController;
    Spinner spPolicyTerm, spPremTerm;
    ArrayAdapter<String> PolicyTermAdapter, PremTermAdapter;
    TermRequestEntity termRequestEntity;
    TermFinmartRequest termFinmartRequest;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    LinearLayout llCompareAll;
    ScrollView mainScroll;

    //region icici form
    Spinner spICICIOptions, spICICIPremiumTerm, spICICIPremiumFrequency, spICICIPremiumPayment;
    ArrayAdapter<String> ICICIOptionsAdapter, ICICIPremiumTermAdapter, ICICIPremiumFrequencyAdapter, ICICIPremiumPaymentAdapter;
    List<String> optionList, premiumTermList, frequenctList, premiumPaymentList;
    TextView txtICICILumpSum, txtICICIRegularIncome, txtICICIIncreasingIncome, txtICICILumpSumRegular;
    EditText etSumICICIAssured, etICICIPolicyTerm, etICICIPremiumTerm,
            etICICICriticalIllness, etICICIAccidentalBenefits, etICICILumpSumpPerc;
    Button minusICICISum, plusICICISum, minusICICIPTerm, plusICICIPTerm,
            minusICICIPreTerm, plusICICIPreTerm, minusICICICritical, plusICICICritical,
            minusICICIAcc, plusICICIAcc, minusICICILumpSumpPerc, plusICICILumpSumpPerc;
    LinearLayout llICICIAccidental, llICICICritical, llICICILumpSumpPerc;
    TextInputLayout tilPremiumPayment;
    //endregion

    boolean isEdit = false, canChangePremiumTerm = true, canChangePolicyTerm = true;
    int termRequestId = 0, insurerID, age = 0;
    String crn = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_icici_term_input, container, false);
        init(view);
        setListener();
        setPopUpInfo();
        // set initial values
        dbPersistanceController = new DBPersistanceController(getActivity());
        policyYear = dbPersistanceController.getPremYearList();
        termRequestEntity = new TermRequestEntity(getActivity());
        termFinmartRequest = new TermFinmartRequest();
        init_adapters();

        adapter_listener();
        setDefaultsIcici();

        if (getArguments() != null) {
            if (getArguments().getParcelable(IciciTermActivity.QUOTE_DATA) != null) {
                termFinmartRequest = getArguments().getParcelable(IciciTermActivity.QUOTE_DATA);
                termRequestEntity = termFinmartRequest.getTermRequestEntity();
                termRequestId = termFinmartRequest.getTermRequestId();
                int fba_id = new DBPersistanceController(getActivity()).getUserData().getFBAId();
                termFinmartRequest.setFba_id(fba_id);
                showDialog("Please Wait..");
                fetchWithDelay();
            } else if (getArguments().getParcelable(IciciTermActivity.INPUT_DATA) != null) {
                termFinmartRequest = getArguments().getParcelable(IciciTermActivity.INPUT_DATA);
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
                bindInputFromOther(termFinmartRequest);
                fromCompare();
            } else {
                changeInputQuote(true);
                tvNo.performClick();
                tvMale.performClick();
                txtICICILumpSum.performClick();
            }
            //bindICICI();
            if (termFinmartRequest != null && termFinmartRequest.getTermRequestEntity() != null && getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA) == null)
                bindInput(termFinmartRequest);
        }


        return view;
    }

    private void fetchWithDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                btnGetQuote.performClick();
            }
        }, 2000);
    }

    private void setDefaultsIcici() {
        etSumICICIAssured.setText("10000000");
        etICICICriticalIllness.setText("100000");
        etICICIAccidentalBenefits.setText("10000000");
        etICICIPolicyTerm.setText("20");
        etICICIPremiumTerm.setText("20");
        etICICILumpSumpPerc.setText("50");
        //by default Regular pay selected.
        //spICICIPremiumTerm.setSelection(0);

    }


    private void setDefaultValues(String strOptions) {
        if (strOptions.equals("LIFE AND HEALTH")) {
            etICICICriticalIllness.setText("100000");
        } else if (strOptions.equals("LIFE PLUS")) {

        } else if (strOptions.equals("ALL IN ONE")) {

        }
    }

    private void bindHeaders() {
        if (termRequestEntity != null && getActivity() != null) {

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
            SpannableString CRN = new SpannableString(crn);
            CRN.setSpan(new StyleSpan(Typeface.BOLD), 0, crn.length(), 0);
            CRN.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.header_dark_text)), 0, crn.length(), 0);
            tvCrn.append(CRN);
            termRequestEntity.setExisting_ProductInsuranceMapping_Id(crn);
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
                ((IciciTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
            btnGetQuote.setText("UPDATE QUOTE");
            tilPincode.setVisibility(View.GONE);
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

    private void bindInput(TermFinmartRequest termFinmartRequest) {
        try {
            TermRequestEntity termRequestEntity = termFinmartRequest.getTermRequestEntity();
            if (termRequestEntity != null) {

                //spICICIPremiumTerm.setOnItemSelectedListener(null);
                //spICICIPremiumFrequency.setOnItemSelectedListener(null);
                //spICICIOptions.setOnItemSelectedListener(null);
                isEdit = true;
                if (termRequestEntity.getPremiumPaymentOption().equals("Regular Pay")) {
                    tilPremiumPayment.setVisibility(View.INVISIBLE);
                    spICICIPremiumTerm.setSelection(0);
                } else if (termRequestEntity.getPremiumPaymentOption().equals("Single Pay")) {
                    tilPremiumPayment.setVisibility(View.INVISIBLE);
                    spICICIPremiumTerm.setSelection(1);
                } else if (termRequestEntity.getPremiumPaymentOption().equals("Limited Pay")) {
                    tilPremiumPayment.setVisibility(View.VISIBLE);
                    spICICIPremiumTerm.setSelection(2);
                } else if (termRequestEntity.getPremiumPaymentOption().equals("Regular Whole Life")) {
                    tilPremiumPayment.setVisibility(View.VISIBLE);
                    spICICIPremiumTerm.setSelection(3);
                }
                age = caluclateAge("" + termRequestEntity.getInsuredDOB());
                bindPremiumPayment(getPremiumPaymentId(termRequestEntity.getPPT(), age));

                String[] listOption = getActivity().getResources().getStringArray(R.array.icici_options);
                final List<String> optionsList = new ArrayList<>(Arrays.asList(listOption));
                ArrayAdapter<String> spAdapterOptions = new ArrayAdapter<String>(getActivity()
                        , android.R.layout.simple_spinner_item
                        , optionsList);

                spAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spICICIOptions.setAdapter(spAdapterOptions);
                if (termRequestEntity.getPlanTaken().equals("Life")) {
                    spICICIOptions.setSelection(0);
                } else if (termRequestEntity.getPlanTaken().equals("Life Plus")) {
                    spICICIOptions.setSelection(1);
                } else if (termRequestEntity.getPlanTaken().equals("Life and Health")) {
                    spICICIOptions.setSelection(2);
                } else if (termRequestEntity.getPlanTaken().equals("All in One")) {
                    spICICIOptions.setSelection(3);
                }

                if (termRequestEntity.getFrequency().equals("yearly")) {
                    spICICIPremiumFrequency.setSelection(0);
                } else if (termRequestEntity.getFrequency().equals("Half Yearly")) {
                    spICICIPremiumFrequency.setSelection(1);
                } else if (termRequestEntity.getFrequency().equals("Monthly")) {
                    spICICIPremiumFrequency.setSelection(2);
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

                if (termRequestEntity.getDeathBenefitOption().equals("lump-sum")) {
                    incomeSelection("LUMP SUM");
                } else if (termRequestEntity.getDeathBenefitOption().equals("income")) {
                    incomeSelection("REGULAR INCOME");
                } else if (termRequestEntity.getDeathBenefitOption().equals("increasing income")) {
                    incomeSelection("INCREASING INCOME");
                } else if (termRequestEntity.getDeathBenefitOption().equals("lump-sum-income")) {
                    incomeSelection("LUMP SUM + REGULAR INCOME");
                }

                if (termRequestEntity.getCIBenefit() != null)
                    etICICICriticalIllness.setText("" + termRequestEntity.getCIBenefit());

                if (termRequestEntity.getADHB() != null)
                    etICICIAccidentalBenefits.setText("" + termRequestEntity.getADHB());

                if (termRequestEntity.getLumpsumPercentage() != null)
                    etICICILumpSumpPerc.setText("" + termRequestEntity.getLumpsumPercentage());

                etSumAssured.setText("" + termRequestEntity.getSumAssured());
                etICICIPolicyTerm.setText("" + termRequestEntity.getPolicyTerm());
                etICICIPremiumTerm.setText("" + termRequestEntity.getPPT());


                //spICICIPremiumTerm.setOnItemSelectedListener(optionSelected);
                //spICICIPremiumFrequency.setOnItemSelectedListener(optionSelected);
                //spICICIOptions.setOnItemSelectedListener(optionSelected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getPremiumPaymentId(String ppt, int age) {
        int temp = 0;
        int type = spICICIPremiumTerm.getSelectedItemPosition();
        if (ppt != null && !ppt.equals("")) {
            try {
                temp = Integer.parseInt(ppt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (type == 2) { // limited pay
            switch (temp) {
                case 5:
                    return 3;
                case 7:
                    return 2;
                case 10:
                    return 1;
                default:
                    return 0;
            }

        } else if (type == 3) { // whole life

            if (temp == 10)
                return 2;
            else {
                if ((temp + age) <= 60)
                    return 1;
                else if ((temp + age) > 60)
                    return 0;
            }
        } else {
            return 0;
        }
        return temp;
    }

    private void bindInputFromOther(TermFinmartRequest termFinmartRequest) {
        try {
            TermRequestEntity termRequestEntity = termFinmartRequest.getTermRequestEntity();
            if (termRequestEntity != null) {

                isEdit = true;
                spICICIPremiumTerm.setSelection(0);

                String[] listOption = getActivity().getResources().getStringArray(R.array.icici_options);
                final List<String> optionsList = new ArrayList<>(Arrays.asList(listOption));
                ArrayAdapter<String> spAdapterOptions = new ArrayAdapter<String>(getActivity()
                        , android.R.layout.simple_spinner_item
                        , optionsList);

                spAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spICICIOptions.setAdapter(spAdapterOptions);
                spICICIOptions.setSelection(0);
                spICICIPremiumFrequency.setSelection(0);

                String[] splitStr = termRequestEntity.getContactName().split("\\s+");
                etFirstName.setText("" + splitStr[0]);
                etLastName.setText("" + splitStr[1]);
                etMobile.setText("" + termRequestEntity.getContactMobile());
                etDOB.setText("" + termRequestEntity.getInsuredDOB());
                age = caluclateAge("" + termRequestEntity.getInsuredDOB());
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
                incomeSelection("LUMP SUM");


                if (termRequestEntity.getCIBenefit() != null)
                    etICICICriticalIllness.setText("" + termRequestEntity.getCIBenefit());

                if (termRequestEntity.getADHB() != null)
                    etICICIAccidentalBenefits.setText("" + termRequestEntity.getADHB());

                if (termRequestEntity.getLumpsumPercentage() != null)
                    etICICILumpSumpPerc.setText("" + termRequestEntity.getLumpsumPercentage());

                etSumAssured.setText("" + termRequestEntity.getSumAssured());
                etICICIPolicyTerm.setText("" + termRequestEntity.getPolicyTerm());
                etICICIPremiumTerm.setText("" + termRequestEntity.getPPT());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        etDOB.setOnClickListener(datePickerDialog);

        txtICICILumpSum.setOnClickListener(this);
        txtICICILumpSumRegular.setOnClickListener(this);
        txtICICIRegularIncome.setOnClickListener(this);
        txtICICIIncreasingIncome.setOnClickListener(this);

        minusICICISum.setOnClickListener(this);
        plusICICISum.setOnClickListener(this);
        minusICICIPTerm.setOnClickListener(this);
        plusICICIPTerm.setOnClickListener(this);
        minusICICIPreTerm.setOnClickListener(this);
        plusICICIPreTerm.setOnClickListener(this);
        minusICICICritical.setOnClickListener(this);
        plusICICICritical.setOnClickListener(this);
        minusICICIAcc.setOnClickListener(this);
        plusICICIAcc.setOnClickListener(this);
        minusICICILumpSumpPerc.setOnClickListener(this);
        plusICICILumpSumpPerc.setOnClickListener(this);
        //spICICIOptions.setOnItemSelectedListener(optionSelected);
        //spICICIPremiumTerm.setOnItemSelectedListener(optionSelected);
        // spICICIPremiumFrequency.setOnItemSelectedListener(optionSelected);

        etICICIAccidentalBenefits.setOnFocusChangeListener(this);
        etICICICriticalIllness.setOnFocusChangeListener(this);
        etSumICICIAssured.setOnFocusChangeListener(this);
        etICICIPolicyTerm.setOnFocusChangeListener(this);
        etICICIPremiumTerm.setOnFocusChangeListener(this);
    }

    private void init_adapters() {

        String[] listOption = getActivity().getResources().getStringArray(R.array.icici_options);
        optionList = new ArrayList<>(Arrays.asList(listOption));

        ICICIOptionsAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, optionList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                return view;
            }
        };
        spICICIOptions.setAdapter(ICICIOptionsAdapter);


        String[] listPremiumTerm = getActivity().getResources().getStringArray(R.array.icici_payment_term);
        premiumTermList = new ArrayList<>(Arrays.asList(listPremiumTerm));

        ICICIPremiumTermAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, premiumTermList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                return view;
            }
        };
        spICICIPremiumTerm.setAdapter(ICICIPremiumTermAdapter);


        String[] listPremiumFreq = getActivity().getResources().getStringArray(R.array.icici_premium_frequency);
        frequenctList = new ArrayList<>(Arrays.asList(listPremiumFreq));

        ICICIPremiumFrequencyAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, frequenctList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                return view;
            }
        };
        spICICIPremiumFrequency.setAdapter(ICICIPremiumFrequencyAdapter);


        String[] listPremiumPay = getActivity().getResources().getStringArray(R.array.icici_premium_payment_whole_life);
        premiumPaymentList = new ArrayList<>(Arrays.asList(listPremiumPay));

        ICICIPremiumPaymentAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, premiumPaymentList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                return view;
            }
        };
        spICICIPremiumPayment.setAdapter(ICICIPremiumPaymentAdapter);

    }

    private void adapter_listener() {

        //region Options
        spICICIOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manipulateOptions(spICICIOptions.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //endregion


        //region Premium Frequency
        spICICIPremiumFrequency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manipulateFrequency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //endregion


        //region Premium Term
        spICICIPremiumTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manipulatePremiumTerm();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //endregion

        //region Premium Term
        spICICIPremiumPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (age != 0)
                    manipulatePremiumPolicyPayment(age);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //endregion
    }

    public void bindPremiumPayment(int selection) {
        String[] listPremiumPayWhole = getActivity().getResources().getStringArray(R.array.icici_premium_payment_whole_life);
        String[] listPremiumPayLimited = getActivity().getResources().getStringArray(R.array.icici_premium_payment_limited_pay);

        if (spICICIPremiumTerm.getSelectedItemPosition() == 2) {

            premiumPaymentList = new ArrayList<>(Arrays.asList(listPremiumPayLimited));

        } else if (spICICIPremiumTerm.getSelectedItemPosition() == 3) {
            premiumPaymentList = new ArrayList<>(Arrays.asList(listPremiumPayWhole));
        }

        ICICIPremiumPaymentAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, premiumPaymentList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                return view;
            }
        };
        spICICIPremiumPayment.setAdapter(ICICIPremiumPaymentAdapter);
        spICICIPremiumPayment.setSelection(selection);
    }

    private void manipulatePremiumPolicyPayment(int age) {

        int policyTerm = 0;
        int premiumTerm = 0;
        if (spICICIPremiumTerm.getSelectedItemPosition() == 2) { // limited pay
            policyTerm = 60 - age;
            switch (spICICIPremiumPayment.getSelectedItem().toString()) {
                case "PAY TILL AGE 60":
                    premiumTerm = 60 - age;
                    break;
                case "10 PAY":
                    premiumTerm = 10;
                    break;
                case "7 PAY":
                    premiumTerm = 7;
                    break;
                case "5 PAY":
                    premiumTerm = 5;
                    break;
            }

        } else if (spICICIPremiumTerm.getSelectedItemPosition() == 3) { // whole life
            policyTerm = 99 - age;
            switch (spICICIPremiumPayment.getSelectedItemPosition()) {
                case 0:
                    premiumTerm = 99 - age;
                    break;
                case 1:
                    premiumTerm = 60 - age;
                    break;
                case 2:
                    premiumTerm = 10;
                    break;
            }
        }
        if (policyTerm != 0)
            setPolicyTerm(policyTerm);
        if (premiumTerm != 0)
            etICICIPremiumTerm.setText("" + premiumTerm);

    }

    private void manipulatePremiumTerm() {


        String[] listOption = getActivity().getResources().getStringArray(R.array.icici_options);
        String[] listPremiumFrequency = getActivity().getResources()
                .getStringArray(R.array.icici_premium_frequency);

        final List<String> optionsList = new ArrayList<>(Arrays.asList(listOption));
        final List<String> premiumFreqList = new ArrayList<>(Arrays.asList(listPremiumFrequency));

        //String[] listPremiumPayWhole = getActivity().getResources().getStringArray(R.array.icici_premium_payment_whole_life);
        //String[] listPremiumPayLimited = getActivity().getResources().getStringArray(R.array.icici_premium_payment_limited_pay);


        switch (spICICIPremiumTerm.getSelectedItemPosition()) {
            case 0://regular pay
                tilPremiumPayment.setVisibility(View.INVISIBLE);
                termRequestEntity.setPremiumPaymentOption("Regular Pay");
                if (etICICIPremiumTerm.getText().toString().equals("1")) {
                    etICICIPremiumTerm.setText("20");
                    etICICIPolicyTerm.setText("20");
                }
                etICICIPolicyTerm.setText(etICICIPremiumTerm.getText().toString());
                canChangePremiumTerm = true;
                canChangePolicyTerm = true;
                etICICIPremiumTerm.setEnabled(true);
                etICICIPolicyTerm.setEnabled(true);
                calculatePremiumTerm();
                break;
            case 1:// single pay
                tilPremiumPayment.setVisibility(View.INVISIBLE);
                termRequestEntity.setPremiumPaymentOption("Single Pay");
                optionsList.remove("LIFE AND HEALTH");
                optionsList.remove("ALL IN ONE");

                premiumFreqList.remove("HALF YEARLY");
                premiumFreqList.remove("MONTHLY");
                etICICIPremiumTerm.setText("1");


                canChangePremiumTerm = false;
                canChangePolicyTerm = true;
                etICICIPremiumTerm.setEnabled(false);
                etICICIPolicyTerm.setEnabled(true);
                //setPolicyTerm((75 - age));
                break;
            case 2://limited pay
                //premiumPaymentList = new ArrayList<>(Arrays.asList(listPremiumPayLimited));
                if (age > 55) {
                    premiumPaymentList.remove(0);
                }
                ICICIPremiumPaymentAdapter.notifyDataSetChanged();
                tilPremiumPayment.setVisibility(View.VISIBLE);
                termRequestEntity.setPremiumPaymentOption("Limited Pay");

                //setPolicyTerm((75 - age));
                        /*if ((75 - age) > 30) {
                            optionsList.remove("LIFE AND HEALTH");
                            optionsList.remove("ALL IN ONE");
                        }*/
                if (etICICIPremiumTerm.getText().toString().equals("1")) {
                    etICICIPremiumTerm.setText("20");
                }

                canChangePremiumTerm = false;
                canChangePolicyTerm = true;
                etICICIPremiumTerm.setEnabled(false);
                etICICIPolicyTerm.setEnabled(true);
                calculatePremiumTerm();
                break;


            case 3://Whole Life
                //premiumPaymentList = new ArrayList<>(Arrays.asList(listPremiumPayWhole));
                ICICIPremiumPaymentAdapter.notifyDataSetChanged();
                tilPremiumPayment.setVisibility(View.VISIBLE);
                termRequestEntity.setPremiumPaymentOption("Regular Whole Life");

                canChangePremiumTerm = false;
                canChangePolicyTerm = false;
                etICICIPolicyTerm.setEnabled(false);
                etICICIPremiumTerm.setEnabled(false);

                calculatePremiumTerm();
                break;

        }
        if (!isEdit) {
            ArrayAdapter<String> spAdapterOptions = new ArrayAdapter<String>(getActivity()
                    , android.R.layout.simple_spinner_item
                    , optionsList);

            spAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spICICIOptions.setAdapter(spAdapterOptions);

            ArrayAdapter<String> spAdapterPremiumFreq = new ArrayAdapter<String>(getActivity()
                    , android.R.layout.simple_spinner_item
                    , premiumFreqList);

            spAdapterPremiumFreq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spICICIPremiumFrequency.setAdapter(spAdapterPremiumFreq);


           /* ICICIPremiumPaymentAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, premiumPaymentList) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                    return view;
                }
            };*/
            //spICICIPremiumPayment.setAdapter(ICICIPremiumPaymentAdapter);
            bindPremiumPayment(0);
        } else {
            isEdit = false;
        }


    }

    private void manipulateFrequency() {
        switch (spICICIPremiumFrequency.getSelectedItemPosition()) {
            case 0:
                termRequestEntity.setFrequency("yearly");
                break;
            case 1:
                termRequestEntity.setFrequency("Half Yearly");
                break;
            case 2:
                termRequestEntity.setFrequency("Monthly");
                break;
        }
    }

    private void manipulateOptions(String s) {
        switch (s) {
            case "LIFE":
                termRequestEntity.setPlanTaken("Life");
                llICICICritical.setVisibility(View.GONE);
                llICICIAccidental.setVisibility(View.GONE);
                break;
            case "LIFE PLUS":
                termRequestEntity.setPlanTaken("Life Plus");
                llICICICritical.setVisibility(View.GONE);
                llICICIAccidental.setVisibility(View.VISIBLE);
                if (etICICIAccidentalBenefits.getText().toString().equals(""))
                    etICICIAccidentalBenefits.setText("10000000");
                calculateAccidental();
                break;
            case "LIFE AND HEALTH":
                termRequestEntity.setPlanTaken("Life and Health");
                llICICICritical.setVisibility(View.VISIBLE);
                llICICIAccidental.setVisibility(View.GONE);
                if (etICICICriticalIllness.getText().toString().equals(""))
                    etICICIAccidentalBenefits.setText("10000000");
                setDefaultValues("LIFE AND HEALTH");
                calculateCritical();
                break;
            case "ALL IN ONE":
                termRequestEntity.setPlanTaken("All in One");
                llICICICritical.setVisibility(View.VISIBLE);
                llICICIAccidental.setVisibility(View.VISIBLE);
                if (etICICIAccidentalBenefits.getText().toString().equals(""))
                    etICICIAccidentalBenefits.setText("10000000");
                calculateAccidental();
                calculateCritical();
                break;


        }
    }

    //region adapter listener
    AdapterView.OnItemSelectedListener optionSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent.getId() == R.id.spICICIOptions) {
                manipulateOptions(spICICIOptions.getSelectedItem().toString());

            } else if (parent.getId() == R.id.spICICIPremiumFrequency) {
                switch (spICICIPremiumFrequency.getSelectedItemPosition()) {
                    case 0:
                        termRequestEntity.setFrequency("yearly");
                        break;
                    case 1:
                        termRequestEntity.setFrequency("Half Yearly");
                        break;
                    case 2:
                        termRequestEntity.setFrequency("Monthly");
                        break;
                }

            } else if (parent.getId() == R.id.spICICIPremiumTerm) {

                String[] listOption = getActivity().getResources().getStringArray(R.array.icici_options);
                String[] listPremiumFrequency = getActivity().getResources()
                        .getStringArray(R.array.icici_premium_frequency);

                final List<String> optionsList = new ArrayList<>(Arrays.asList(listOption));
                final List<String> premiumFreqList = new ArrayList<>(Arrays.asList(listPremiumFrequency));

                switch (spICICIPremiumTerm.getSelectedItemPosition()) {
                    case 0://regular pay
                        canChangePremiumTerm = true;
                        termRequestEntity.setPremiumPaymentOption("Regular Pay");
                        if (etICICIPremiumTerm.getText().toString().equals("1")) {
                            etICICIPremiumTerm.setText("20");
                        }
                        break;
                    case 1:// single pay
                        termRequestEntity.setPremiumPaymentOption("Single Pay");
                        optionsList.remove("LIFE AND HEALTH");
                        optionsList.remove("ALL IN ONE");

                        premiumFreqList.remove("HALF YEARLY");
                        premiumFreqList.remove("MONTHLY");
                        canChangePremiumTerm = false;
                        etICICIPremiumTerm.setText("1");
                        etICICIPremiumTerm.setEnabled(false);
                        //setPolicyTerm((75 - age));
                        break;
                    case 2://limited pay
                        termRequestEntity.setPremiumPaymentOption("Limited Pay");
                        canChangePremiumTerm = true;
                        //setPolicyTerm((75 - age));
                        /*if ((75 - age) > 30) {
                            optionsList.remove("LIFE AND HEALTH");
                            optionsList.remove("ALL IN ONE");
                        }*/
                        if (etICICIPremiumTerm.getText().toString().equals("1")) {
                            etICICIPremiumTerm.setText("20");
                        }
                        break;

                }
                /*if (!isEdit) {*/
                ArrayAdapter<String> spAdapterOptions = new ArrayAdapter<String>(getActivity()
                        , android.R.layout.simple_spinner_item
                        , optionsList);

                spAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spICICIOptions.setAdapter(spAdapterOptions);

                ArrayAdapter<String> spAdapterPremiumFreq = new ArrayAdapter<String>(getActivity()
                        , android.R.layout.simple_spinner_item
                        , premiumFreqList);

                spAdapterPremiumFreq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spICICIPremiumFrequency.setAdapter(spAdapterPremiumFreq);
                    /*isEdit = false;
                }*/

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    //endregion


    private void init(View view) {


        //quote page
        cvInputDetails = (CardView) view.findViewById(R.id.cvInputDetails);
        cvQuoteDetails = (CardView) view.findViewById(R.id.cvQuoteDetails);
        layoutCompare = (View) view.findViewById(R.id.layoutCompare);
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
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etDOB = (EditText) view.findViewById(R.id.etDateofBirth);
        tvMale = (TextView) view.findViewById(R.id.tvMale);
        tvFemale = (TextView) view.findViewById(R.id.tvFemale);
        tvYes = (TextView) view.findViewById(R.id.tvYes);
        tvNo = (TextView) view.findViewById(R.id.tvNo);
        llGender = (LinearLayout) view.findViewById(R.id.llGender);
        llSmoker = (LinearLayout) view.findViewById(R.id.llSmoker);

        etPincode = (EditText) view.findViewById(R.id.etPincode);
        etSumAssured = (EditText) view.findViewById(R.id.etICICISumAssured);
        spPolicyTerm = (Spinner) view.findViewById(R.id.spPolicyTerm);
        spPremTerm = (Spinner) view.findViewById(R.id.spPremTerm);
        spICICIPremiumTerm = view.findViewById(R.id.spICICIPremiumTerm);


        //Compare All
        llCompareAll = (LinearLayout) view.findViewById(R.id.llCompareAll);
        mainScroll = (ScrollView) view.findViewById(R.id.mainScroll);
        //lllayoutICICI = (View) view.findViewById(R.id.layoutICICI);

        //region icici id
        llICICIAccidental = (LinearLayout) view.findViewById(R.id.llAccidental);
        llICICICritical = (LinearLayout) view.findViewById(R.id.llCritical);
        llICICILumpSumpPerc = (LinearLayout) view.findViewById(R.id.llICICILumpSumpPerc);
        spICICIOptions = (Spinner) view.findViewById(R.id.spICICIOptions);
        spICICIPremiumTerm = (Spinner) view.findViewById(R.id.spICICIPremiumTerm);
        spICICIPremiumFrequency = (Spinner) view.findViewById(R.id.spICICIPremiumFrequency);

        txtICICILumpSum = (TextView) view.findViewById(R.id.txtICICILumpSum);
        txtICICIRegularIncome = (TextView) view.findViewById(R.id.txtICICIRegularIncome);
        txtICICIIncreasingIncome = (TextView) view.findViewById(R.id.txtICICIIncreasingIncome);
        txtICICILumpSumRegular = (TextView) view.findViewById(R.id.txtICICILumpSumRegular);
        etSumICICIAssured = (EditText) view.findViewById(R.id.etICICISumAssured);
        etICICIPolicyTerm = (EditText) view.findViewById(R.id.etICICIPolicyTerm);
        etICICIPremiumTerm = (EditText) view.findViewById(R.id.etICICIPremiumTerm);
        etICICICriticalIllness = (EditText) view.findViewById(R.id.etICICICriticalIllness);
        etICICIAccidentalBenefits = (EditText) view.findViewById(R.id.etICICIAccidentalBenefits);
        etICICILumpSumpPerc = (EditText) view.findViewById(R.id.etICICILumpSumpPerc);
        minusICICISum = (Button) view.findViewById(R.id.minusICICISum);
        plusICICISum = (Button) view.findViewById(R.id.plusICICISum);
        minusICICIPTerm = (Button) view.findViewById(R.id.minusICICIPTerm);
        plusICICIPTerm = (Button) view.findViewById(R.id.plusICICIPTerm);
        minusICICIPreTerm = (Button) view.findViewById(R.id.minusICICIPreTerm);
        plusICICIPreTerm = (Button) view.findViewById(R.id.plusICICIPreTerm);
        minusICICICritical = (Button) view.findViewById(R.id.minusICICICritical);
        plusICICICritical = (Button) view.findViewById(R.id.plusICICICritical);
        minusICICIAcc = (Button) view.findViewById(R.id.minusICICIAcc);
        plusICICIAcc = (Button) view.findViewById(R.id.plusICICIAcc);
        minusICICILumpSumpPerc = (Button) view.findViewById(R.id.minusICICILumpSumpPerc);
        plusICICILumpSumpPerc = (Button) view.findViewById(R.id.plusICICILumpSumpPerc);
        tilPremiumPayment = view.findViewById(R.id.tilPremiumPayment);
        spICICIPremiumPayment = view.findViewById(R.id.spICICIPremiumPayment);
        //end region
    }

    @Override
    public void onClick(View view) {
        Constants.hideKeyBoard(view, getActivity());
        btnGetQuote.requestFocus();
        int i = view.getId();
        if (i == R.id.tvMale) {
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

        } else if (i == R.id.ivBuy) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "ICICI BUY TERM INSURANCE", "ICICI BUY TERM INSURANCE");
            new TermInsuranceController(getActivity()).convertQuoteToApp("" + termFinmartRequest.getTermRequestId(), "39", "" + dbPersistanceController.getUserData().getFBAId(), "" + termCompareResponseEntity.getNetPremium(), this);
            startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                    .putExtra("URL", termCompareResponseEntity.getProposerPageUrl())
                    .putExtra("NAME", "ICICI PRUDENTIAL")
                    .putExtra("TITLE", "ICICI PRUDENTIAL"));
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Life Ins Buy"), Constants.LIFE_INS), null);


        } else if (i == R.id.ivPdf) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "ICICI PDF  TERM INSURANCE", "ICICI PDF TERM INSURANCE");
            if (termCompareResponseEntity != null && termCompareResponseEntity.getPdfUrl().equals("")) {
                Toast.makeText(getActivity(), "Pdf Not Available", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                        .putExtra("URL", termCompareResponseEntity.getPdfUrl())
                        .putExtra("NAME", "ICICI PRUDENTIAL DOWNLOAD")
                        .putExtra("TITLE", "ICICI PRUDENTIAL"));

            }


            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "ICICI GET QUOTE  TERM INSURANCE", "ICICI GET QUOTE TERM INSURANCE");
            if (isValidInput()) {
                setTermRequest();
                //((IciciTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
                fetchQuotes();
            }

        } else if (i == R.id.btnGetQuote) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "ICICI GET QUOTE  TERM INSURANCE", "ICICI GET QUOTE TERM INSURANCE");
            if (isValidInput()) {
                setTermRequest();
                //((IciciTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
                fetchQuotes();
            }

        } else if (i == R.id.ivEdit) {
            if (getArguments().getParcelable(CompareTermActivity.OTHER_QUOTE_DATA) != null)
                ((CompareTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            else
                ((IciciTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            changeInputQuote(true);

        } else if (i == R.id.ivInfo) {//((IciciTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            OpenPoupWnidow(termCompareResponseEntity.getKeyFeatures().split("\\|"));

        } else if (i == R.id.txtICICILumpSum || i == R.id.txtICICIRegularIncome || i == R.id.txtICICIIncreasingIncome) {
            incomeSelection(((TextView) view).getText().toString());

        } else if (i == R.id.txtICICILumpSumRegular) {
            if (etICICILumpSumpPerc.getText().toString().equals("0") ||
                    etICICILumpSumpPerc.getText().toString().equals(""))
                etICICILumpSumpPerc.setText("50");
            incomeSelection(((TextView) view).getText().toString());

        } else if (i == R.id.plusICICISum) {
            changeSumAssured(true);

        } else if (i == R.id.minusICICISum) {
            changeSumAssured(false);

        } else if (i == R.id.plusICICIPTerm) {
            changePolicyTerm(true);

        } else if (i == R.id.minusICICIPTerm) {
            changePolicyTerm(false);

        } else if (i == R.id.plusICICIPreTerm) {
            changePremiumTerm(true);

        } else if (i == R.id.minusICICIPreTerm) {
            changePremiumTerm(false);

        } else if (i == R.id.plusICICICritical) {
            changeCriticalIllness(true);

        } else if (i == R.id.minusICICICritical) {
            changeCriticalIllness(false);

        } else if (i == R.id.plusICICIAcc) {
            changeAccidentalDeath(true);

        } else if (i == R.id.minusICICIAcc) {
            changeAccidentalDeath(false);

        } else if (i == R.id.plusICICILumpSumpPerc) {
            changeLumpsumPercent(true);

        } else if (i == R.id.minusICICILumpSumpPerc) {
            changeLumpsumPercent(false);

        }
    }

    private void fetchQuotes() {
        showDialog("Please Wait..");
        new TermInsuranceController(getActivity()).getTermInsurer(termFinmartRequest, this);
    }

    private void updateCrnToServer() {
        if (termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id() != null && !termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id().equals(""))
            new TermInsuranceController(getActivity()).updateCRN(termFinmartRequest.getTermRequestId(), Integer.parseInt(termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id()), this);
    }

    private void changeLumpsumPercent(boolean b) {
        int maxLumpsumPercent = 95;
        int minLumpsumPercent = 5;
        int step = 5;
        long LumpsumPercent = 0;
        if (!etICICILumpSumpPerc.getText().toString().equals(""))
            LumpsumPercent = Long.parseLong(etICICILumpSumpPerc.getText().toString());

        if (b) {
            if (LumpsumPercent < maxLumpsumPercent) {
                LumpsumPercent += step;
            }
        } else {
            if (LumpsumPercent > minLumpsumPercent) {
                LumpsumPercent -= step;
            }
        }
        etICICILumpSumpPerc.setText("" + LumpsumPercent);
    }

    private void changeCriticalIllness(boolean b) {
        int maxCriticalIllness = 10000000;
        int minCriticalIllness = 100000;
        long sumInsured = 10000000;
        int step = 500000;
        int Smallstep = 100000;
        long ccIllness = 0;
        if (!etICICICriticalIllness.getText().toString().equals(""))
            ccIllness = Long.parseLong(etICICICriticalIllness.getText().toString().replaceAll("\\,", ""));

        if (!etSumICICIAssured.getText().toString().equals(""))
            sumInsured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));


        if (b) {
            if (ccIllness < maxCriticalIllness && ccIllness < sumInsured) {
                if (ccIllness < 1000000)
                    ccIllness += Smallstep;
                else
                    ccIllness += step;
            }
        } else {
            if (ccIllness > minCriticalIllness) {
                if (ccIllness <= 1000000)
                    ccIllness -= Smallstep;
                else
                    ccIllness -= step;
            }
        }
        etICICICriticalIllness.setText("" + (ccIllness));
    }

    private void changeAccidentalDeath(boolean b) {
        int maxAccidentalDeath = 20000000;
        int minAccidentalDeath = 100000;
        long sumInsured = 10000000;
        int step = 500000;
        int Smallstep = 100000;
        long AccidentalDeath = 0;
        if (!etICICIAccidentalBenefits.getText().toString().equals(""))
            AccidentalDeath = Long.parseLong(etICICIAccidentalBenefits.getText().toString().replaceAll("\\,", ""));

        if (!etSumICICIAssured.getText().toString().equals(""))
            sumInsured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));


        if (b) {
            if (AccidentalDeath < maxAccidentalDeath && AccidentalDeath < sumInsured) {
                if (AccidentalDeath < 1000000)
                    AccidentalDeath += Smallstep;
                else
                    AccidentalDeath += step;
            }
        } else {
            if (AccidentalDeath > minAccidentalDeath) {
                if (AccidentalDeath <= 1000000)
                    AccidentalDeath -= Smallstep;
                else
                    AccidentalDeath -= step;
            }
        }

        etICICIAccidentalBenefits.setText("" + (AccidentalDeath));
    }

    private void changePolicyTerm(boolean b) {

        if (canChangePolicyTerm) {
            int policyTerm = 0;
            if (!etICICIPolicyTerm.getText().toString().equals(""))
                policyTerm = Integer.parseInt(etICICIPolicyTerm.getText().toString());
            int ppt = 0;
            if (!etICICIPremiumTerm.getText().toString().equals(""))
                ppt = Integer.parseInt(etICICIPremiumTerm.getText().toString());
            if (b) {
                if (policyTerm < (85 - age))
                    policyTerm = policyTerm + 1;
            } else {
                if (policyTerm > 5) {
                    if (ppt >= policyTerm)
                        ppt = policyTerm - 1;
                    policyTerm = policyTerm - 1;

                }

            }

            setPolicyTerm(policyTerm);
            etICICIPremiumTerm.setText("" + ppt);
        }

        // remove life and health ,all in one
        //region hide
        /*String[] listOption = getActivity().getResources().getStringArray(R.array.icici_options);
        final List<String> optionsList = new ArrayList<>(Arrays.asList(listOption));
        if (spICICIPremiumTerm.getSelectedItemPosition() == 2 && policyTerm > 30) {
            optionsList.remove("LIFE AND HEALTH");
            optionsList.remove("ALL IN ONE");

            ArrayAdapter<String> spAdapterOptions = new ArrayAdapter<String>(getActivity()
                    , android.R.layout.simple_spinner_item
                    , optionsList);

            spAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spICICIOptions.setAdapter(spAdapterOptions);
        } else {
            ArrayAdapter<String> spAdapterOptions = new ArrayAdapter<String>(getActivity()
                    , android.R.layout.simple_spinner_item
                    , optionsList);

            spAdapterOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spICICIOptions.setAdapter(spAdapterOptions);
        }*/
        //endregion
    }

    private void setPolicyTerm(int policyTerm) {
        switch (spICICIPremiumTerm.getSelectedItemPosition()) {
            case 0:
                if (policyTerm > (85 - age))
                    etICICIPolicyTerm.setText("" + (85 - age));
                else
                    etICICIPolicyTerm.setText("" + policyTerm);
                break;
            case 1://single pay can't be grater than 20
                if (policyTerm > 20)
                    etICICIPolicyTerm.setText("20");
                else
                    etICICIPolicyTerm.setText("" + policyTerm);
                break;
            case 2://limited pay can't be grater than 40
                if (policyTerm > (85 - age))
                    etICICIPolicyTerm.setText("" + (85 - age));
                else
                    etICICIPolicyTerm.setText("" + policyTerm);
                break;
            case 3:
                etICICIPolicyTerm.setText("" + policyTerm);
                break;

        }
    }

    private void changePremiumTerm(boolean b) {
        if (canChangePremiumTerm) {
            long premiumTerm = 0;
            if (!etICICIPremiumTerm.getText().toString().equals(""))
                premiumTerm = Long.parseLong(etICICIPremiumTerm.getText().toString());
            long policyTerm = 0;
            if (!etICICIPolicyTerm.getText().toString().equals(""))
                policyTerm = Long.parseLong(etICICIPolicyTerm.getText().toString());
            if (b) {
                if (premiumTerm < policyTerm)
                    premiumTerm = premiumTerm + 1;
            } else {
                if (premiumTerm > 1)
                    premiumTerm = premiumTerm - 1;
                else
                    premiumTerm = 1;
            }

            etICICIPremiumTerm.setText("" + premiumTerm);
        }

    }

    private void changeSumAssured(boolean b) {

        long sumAssured = 0;
        if (!etSumICICIAssured.getText().toString().equals(""))
            sumAssured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));
        if (b) {
            if (sumAssured >= 100000 && sumAssured < 1000000)
                sumAssured = sumAssured + 50000;
            else
                sumAssured = sumAssured + 500000;


        } else {
            if (sumAssured > 1000000)
                sumAssured = sumAssured - 500000;
            else if (sumAssured > 100000 && sumAssured <= 1000000)
                sumAssured = sumAssured - 50000;

            if (llICICIAccidental.getVisibility() == View.VISIBLE) {
                long AccidentalDeath = 0;
                if (!etICICIAccidentalBenefits.getText().toString().equals(""))
                    AccidentalDeath = Long.parseLong(etICICIAccidentalBenefits.getText().toString().replaceAll("\\,", ""));
                if (AccidentalDeath >= sumAssured) {
                    etICICIAccidentalBenefits.setText("" + (sumAssured));
                }
            }

            if (llICICICritical.getVisibility() == View.VISIBLE) {
                long AccidentalDeath = 0;
                if (!etICICICriticalIllness.getText().toString().equals(""))
                    AccidentalDeath = Long.parseLong(etICICICriticalIllness.getText().toString().replaceAll("\\,", ""));
                if (AccidentalDeath >= sumAssured) {
                    etICICICriticalIllness.setText("" + (sumAssured));
                }
            }

        }
        // (sumAssured);
        etSumICICIAssured.setText("" + (sumAssured));
    }

    private void incomeSelection(String s) {
        switch (s) {
            case "LUMP SUM":
                txtICICILumpSum.setBackgroundResource(R.drawable.customeborder_blue);
                txtICICIIncreasingIncome.setBackgroundResource(R.drawable.customeborder);
                txtICICIRegularIncome.setBackgroundResource(R.drawable.customeborder);
                txtICICILumpSumRegular.setBackgroundResource(R.drawable.customeborder);
                llICICILumpSumpPerc.setVisibility(View.GONE);
                termRequestEntity.setDeathBenefitOption("lump-sum");
                break;
            case "REGULAR INCOME":
                txtICICILumpSum.setBackgroundResource(R.drawable.customeborder);
                txtICICIIncreasingIncome.setBackgroundResource(R.drawable.customeborder);
                txtICICIRegularIncome.setBackgroundResource(R.drawable.customeborder_blue);
                txtICICILumpSumRegular.setBackgroundResource(R.drawable.customeborder);
                llICICILumpSumpPerc.setVisibility(View.GONE);
                termRequestEntity.setDeathBenefitOption("income");
                break;
            case "INCREASING INCOME":
                txtICICILumpSum.setBackgroundResource(R.drawable.customeborder);
                txtICICIIncreasingIncome.setBackgroundResource(R.drawable.customeborder_blue);
                txtICICIRegularIncome.setBackgroundResource(R.drawable.customeborder);
                txtICICILumpSumRegular.setBackgroundResource(R.drawable.customeborder);
                llICICILumpSumpPerc.setVisibility(View.GONE);
                termRequestEntity.setDeathBenefitOption("increasing income");
                break;
            case "LUMP SUM + REGULAR INCOME":
                txtICICILumpSum.setBackgroundResource(R.drawable.customeborder);
                txtICICIIncreasingIncome.setBackgroundResource(R.drawable.customeborder);
                txtICICIRegularIncome.setBackgroundResource(R.drawable.customeborder);
                txtICICILumpSumRegular.setBackgroundResource(R.drawable.customeborder_blue);
                llICICILumpSumpPerc.setVisibility(View.VISIBLE);
                termRequestEntity.setDeathBenefitOption("lump-sum-income");
                break;


        }
    }

    private void setTermRequest() {
        //termRequestEntity.setLumpsumPercentage("0");
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

        if (llICICICritical.getVisibility() == View.VISIBLE)
            termRequestEntity.setCIBenefit("" + etICICICriticalIllness.getText().toString().replaceAll("\\,", ""));
        else
            termRequestEntity.setCIBenefit("");

        if (llICICIAccidental.getVisibility() == View.VISIBLE)
            termRequestEntity.setADHB("" + etICICIAccidentalBenefits.getText().toString().replaceAll("\\,", ""));
        else
            termRequestEntity.setADHB("");

        if (llICICILumpSumpPerc.getVisibility() == View.VISIBLE)
            termRequestEntity.setLumpsumPercentage(etICICILumpSumpPerc.getText().toString());
        else
            termRequestEntity.setLumpsumPercentage("0");


        termRequestEntity.setPolicyTerm("" + etICICIPolicyTerm.getText().toString());
        termRequestEntity.setInsurerId(39);
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

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }

    //region date picker

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
       /* if (!isValidePhoneNumber(etMobile)) {
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

        if (etSumAssured.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etSumAssured.requestFocus();
                etSumAssured.setError("Enter Sum Assured");
                etSumAssured.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etSumAssured.requestFocus();
                etSumAssured.setError("Enter Sum Assured");
                return false;
            }

        }


        if (etSumICICIAssured.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etSumICICIAssured.requestFocus();
                etSumICICIAssured.setError("Enter Sum Assured");
                etSumICICIAssured.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etSumICICIAssured.requestFocus();
                etSumICICIAssured.setError("Enter Sum Assured");
                return false;
            }

        }


        if (etICICILumpSumpPerc.getText().toString().isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etICICILumpSumpPerc.requestFocus();
                etICICILumpSumpPerc.setError("Enter Lump Sump (%)");
                etICICILumpSumpPerc.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                return false;
            } else {
                etICICILumpSumpPerc.requestFocus();
                etICICILumpSumpPerc.setError("Enter Lump Sump (%)");
                return false;
            }

        } else {
            long lumpsump = Long.parseLong(etICICILumpSumpPerc.getText().toString().replaceAll("\\,", ""));
            if (lumpsump < 5 && lumpsump > 95) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etICICILumpSumpPerc.requestFocus();
                    etICICILumpSumpPerc.setError("Enter valid Lump Sump (%)");
                    etICICILumpSumpPerc.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etICICILumpSumpPerc.requestFocus();
                    etICICILumpSumpPerc.setError("Enter valid Lump Sump (%)");
                    return false;
                }

            }
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

        } else {
            long lumpsump = Long.parseLong(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));
            if (lumpsump < 5 || lumpsump > 100) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etICICIPolicyTerm.requestFocus();
                    etICICIPolicyTerm.setError("Enter valid Policy Term ");
                    etICICIPolicyTerm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etICICIPolicyTerm.requestFocus();
                    etICICIPolicyTerm.setError("Enter valid  Policy Term ");
                    return false;
                }

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

        } else {
            long lumpsump = Long.parseLong(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));
            if (lumpsump < 0 || lumpsump > 100) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etICICIPremiumTerm.requestFocus();
                    etICICIPremiumTerm.setError("Enter valid Premium Term ");
                    etICICIPremiumTerm.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etICICIPremiumTerm.requestFocus();
                    etICICIPremiumTerm.setError("Enter valid  Premium Term ");
                    return false;
                }

            }
        }
        if (llICICIAccidental.getVisibility() == View.VISIBLE) {
            if (etICICIAccidentalBenefits.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etICICIAccidentalBenefits.requestFocus();
                    etICICIAccidentalBenefits.setError("Enter Accidental Benefits");
                    etICICIAccidentalBenefits.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etICICIAccidentalBenefits.requestFocus();
                    etICICIAccidentalBenefits.setError("Enter Accidental Benefits");
                    return false;
                }

            } else {
                /*long lumpsump = Long.parseLong(etICICIAccidentalBenefits.getText().toString().replaceAll("\\,", ""));
                long sumInsured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));
                if (lumpsump < 100000 || lumpsump > sumInsured) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        etICICIAccidentalBenefits.requestFocus();
                        etICICIAccidentalBenefits.setError("Enter valid Accidental Benefits");
                        etICICIAccidentalBenefits.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        return false;
                    } else {
                        etICICIAccidentalBenefits.requestFocus();
                        etICICIAccidentalBenefits.setError("Enter valid  Accidental Benefits");
                        return false;
                    }

                }*/
                calculateAccidental();
            }
        }
        if (llICICICritical.getVisibility() == View.VISIBLE) {
            if (etICICICriticalIllness.getText().toString().isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    etICICICriticalIllness.requestFocus();
                    etICICICriticalIllness.setError("Enter Critical illness");
                    etICICICriticalIllness.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    return false;
                } else {
                    etICICICriticalIllness.requestFocus();
                    etICICICriticalIllness.setError("Enter Critical illness");
                    return false;
                }

            } else {
                calculateCritical();
                /*long lumpsump = Long.parseLong(etICICICriticalIllness.getText().toString().replaceAll("\\,", ""));
                long sumInsured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));
                if (lumpsump < 100000 || lumpsump > sumInsured) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        etICICICriticalIllness.requestFocus();
                        etICICICriticalIllness.setError("Enter valid Critical illness");
                        etICICICriticalIllness.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        return false;
                    } else {
                        etICICICriticalIllness.requestFocus();
                        etICICICriticalIllness.setError("Enter valid  Critical illness");
                        return false;
                    }

                }*/
            }
        }

        return true;
    }
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

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();

        if (response instanceof TermCompareQuoteResponse) {
            processResponse((TermCompareQuoteResponse) response);
            /*mainScroll.fullScroll(ScrollView.FOCUS_UP);
            this.termCompareQuoteResponse = (TermCompareQuoteResponse) response;
            this.cvInputDetails.requestFocus();
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
            hideShowLayout(false);*/
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        changeInputQuote(true);
    }

    private void processResponse(TermCompareQuoteResponse termCompareQuoteResponse) {

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
                    changeInputQuote(true);
                    Toast.makeText(getActivity(), "" + termCompareResponseEntity.getQuoteStatus(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "No Quotes Found.", Toast.LENGTH_SHORT).show();
            }
        }

        mainScroll.scrollTo(0, 0);
        //mainScroll.fullScroll(ScrollView.FOCUS_UP);
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

    //region dob
    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, getActivity());


            DateTimePicker.showHealthAgeDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                    if (view1.isShown()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        String currentDay = simpleDateFormat.format(calendar.getTime());
                        etDOB.setText(currentDay);
                        age = caluclateAge(calendar);


                        //region remove pay till 60 if age > 55
                        /*String[] listPremiumPayLimited = getActivity().getResources().getStringArray(R.array.icici_premium_payment_limited_pay);
                        premiumPaymentList = new ArrayList<>(Arrays.asList(listPremiumPayLimited));
                        if (age > 55) {
                            premiumPaymentList.remove(0);
                        }
                        ICICIPremiumPaymentAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, premiumPaymentList) {
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());
                                return view;
                            }
                        };
                        spICICIPremiumPayment.setAdapter(ICICIPremiumPaymentAdapter);*/

                        //endregion

                        manipulatePremiumPolicyPayment(age);

                    }
                }
            });


        }
    };

//endregion

    @Override
    public void onFocusChange(View view, boolean b) {
        int i = view.getId();
        if (i == R.id.etICICIAccidentalBenefits) {
            if (!b) {
                calculateAccidental();
            }

        } else if (i == R.id.etICICICriticalIllness) {
            if (!b) {
                calculateCritical();
            }

        } else if (i == R.id.etICICISumAssured) {
            if (!b) {
                calculateCritical();
                calculateAccidental();
            }

        } else if (i == R.id.etICICIPolicyTerm) {
            if (!b) {
                calculatePremiumTerm();
            }

        } else if (i == R.id.etICICIPremiumTerm) {
            if (!b) {
                calculatePremiumTerm();
            }

        }
    }

    private void calculateAccidental() {
        int maxAccidentalDeath = 20000000;
        int minAccidentalDeath = 100000;
        long sumInsured = 10000000;
        long AccidentalDeath = 0;
        if (!etICICIAccidentalBenefits.getText().toString().equals(""))
            AccidentalDeath = Long.parseLong(etICICIAccidentalBenefits.getText().toString().replaceAll("\\,", ""));

        if (!etSumICICIAssured.getText().toString().equals(""))
            sumInsured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));


        if (AccidentalDeath >= maxAccidentalDeath) {
            etICICIAccidentalBenefits.setText("" + (maxAccidentalDeath));
        } else {
            if (AccidentalDeath >= sumInsured)
                etICICIAccidentalBenefits.setText("" + (sumInsured));
        }

        if (AccidentalDeath <= minAccidentalDeath) {
            etICICIAccidentalBenefits.setText("" + minAccidentalDeath);
        }
    }

    private void calculateCritical() {
        int maxAccidentalDeath = 10000000;
        int minAccidentalDeath = 100000;
        long sumInsured = 10000000;
        long AccidentalDeath = 0;
        if (!etICICICriticalIllness.getText().toString().equals(""))
            AccidentalDeath = Long.parseLong(etICICICriticalIllness.getText().toString().replaceAll("\\,", ""));

        if (!etSumICICIAssured.getText().toString().equals(""))
            sumInsured = Long.parseLong(etSumICICIAssured.getText().toString().replaceAll("\\,", ""));


        if (AccidentalDeath >= maxAccidentalDeath) {
            etICICICriticalIllness.setText("" + (maxAccidentalDeath));
        } else {
            if (AccidentalDeath >= sumInsured)
                etICICICriticalIllness.setText("" + (sumInsured));
        }

        if (AccidentalDeath <= minAccidentalDeath) {
            etICICICriticalIllness.setText("" + minAccidentalDeath);
        }
    }

    private void calculatePremiumTerm() {
        int maxPremiumTerm = 100;
        int minPremiumTerm = 1;
        long policyTerm = 0;
        long premiumTerm = 0;
        if (!etICICIPremiumTerm.getText().toString().equals(""))
            premiumTerm = Long.parseLong(etICICIPremiumTerm.getText().toString().replaceAll("\\,", ""));

        if (!etICICIPolicyTerm.getText().toString().equals(""))
            policyTerm = Long.parseLong(etICICIPolicyTerm.getText().toString().replaceAll("\\,", ""));


        if (policyTerm >= maxPremiumTerm) {
            etICICIPremiumTerm.setText("" + (maxPremiumTerm));
        } else {
            if (premiumTerm >= policyTerm)
                etICICIPremiumTerm.setText("" + (policyTerm));
        }

        if (premiumTerm <= minPremiumTerm) {
            etICICIPremiumTerm.setText("" + minPremiumTerm);
        }
    }
}





