package com.datacomp.magicfinmart.term.compareterm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.term.compareterm.adapters.TermQuoteAdapter;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class TermInputFragment extends BaseFragment implements View.OnClickListener, BaseFragment.PopUpListener, IResponseSubcriber {

    View layoutCompare;
    Button btnGetQuote;
    EditText etFirstName, etLastName, etMobile;
    //RadioButton rbMale, rbfemale, rbNoSmoker, rbYesSmoker;
    TextView tvMale, tvFemale, tvYes, tvNo;
    boolean isMale, isSmoker;
    EditText etDOB;

    EditText etPincode, etSumAssured;
    List<String> policyYear;
    DBPersistanceController dbPersistanceController;
    Spinner spPolicyTerm, spPremTerm;
    ArrayAdapter<String> PolicyTermAdapter, PremTermAdapter;
    TermRequestEntity termRequestEntity;
    TermFinmartRequest termFinmartRequest;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    NestedScrollView mainScroll;
    LinearLayout llCompareAll;
    RecyclerView rvTerm;
    CardView cvInputDetails;
    List<TermCompareResponseEntity> termCompareResponseEntities;
    int insurerID, termRequestId = 0, age = 0;
    String crn = "";
    TermQuoteAdapter mAdapter;

    //Headers
    TextView tvSum, tvGender, tvSmoker, tvAge, tvPolicyTerm, tvCrn;
    ImageView ivEdit, ivInfo;
    boolean isEdit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_term_compare_input, container, false);
        init(view);
        setListener();
        // set initial values
        dbPersistanceController = new DBPersistanceController(getActivity());
        policyYear = dbPersistanceController.getPremYearList();
        termRequestEntity = new TermRequestEntity(getActivity());
        termFinmartRequest = new TermFinmartRequest();
        termCompareResponseEntities = new ArrayList<TermCompareResponseEntity>();
        init_adapters();

        adapter_listener();
        /*if (getArguments() != null) {
            if (getArguments().getParcelable(CompareTermActivity.INPUT_DATA) != null)
                termFinmartRequest = getArguments().getParcelable(CompareTermActivity.INPUT_DATA);
            insurerID = getArguments().getInt(TermQuoteListFragment.TERM_FOR_INPUT_FRAGMENT);
            bindInput(termFinmartRequest);
        }*/

        if (getArguments() != null) {
            if (getArguments().getParcelable(CompareTermActivity.INPUT_DATA) != null) {
                termFinmartRequest = getArguments().getParcelable(CompareTermActivity.INPUT_DATA);
                termRequestEntity = termFinmartRequest.getTermRequestEntity();
                termRequestId = termFinmartRequest.getTermRequestId();
                changeInputQuote(true);
            } else if (getArguments().getParcelable(CompareTermActivity.QUOTE_DATA) != null) {
                termFinmartRequest = getArguments().getParcelable(CompareTermActivity.QUOTE_DATA);
                termRequestEntity = termFinmartRequest.getTermRequestEntity();
                termRequestId = termFinmartRequest.getTermRequestId();
                int fba_id = new DBPersistanceController(getActivity()).getUserData().getFBAId();
                termFinmartRequest.setFba_id(fba_id);
                fetchQuotes();
            } else {
                setDefaultValues();
                changeInputQuote(true);
            }
            //bindICICI();
            if (termFinmartRequest != null && termFinmartRequest.getTermRequestEntity() != null)
                bindInput(termFinmartRequest);
        }

        return view;
    }

    private void setDefaultValues() {
        tvNo.performClick();
        tvMale.performClick();
    }

    private void bindInput(TermFinmartRequest termFinmartRequest) {
        try {
            TermRequestEntity termRequestEntity = termFinmartRequest.getTermRequestEntity();
            if (termRequestEntity != null) {
                isEdit = true;
                etDOB.setText("" + termRequestEntity.getInsuredDOB());
                etSumAssured.setText("" + termRequestEntity.getSumAssured());
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
                spPolicyTerm.setSelection((Integer.parseInt(termRequestEntity.getPolicyTerm()) - 5));
                spPremTerm.setSelection((Integer.parseInt(termRequestEntity.getPPT()) - 5));
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
            //String crn = "" + termCompareQuoteResponse.getMasterData().getResponse().get(0).getCustomerReferenceID();
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

    private void setListener() {
        ivEdit.setOnClickListener(this);
        tvMale.setOnClickListener(this);
        tvFemale.setOnClickListener(this);
        tvYes.setOnClickListener(this);
        tvNo.setOnClickListener(this);
        btnGetQuote.setOnClickListener(this);
        etDOB.setOnClickListener(datePickerDialog);
    }

    private void adapter_listener() {
        spPolicyTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isEdit) {
                    spPremTerm.setSelection(position);
                } else {
                    isEdit = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void init_adapters() {
        PolicyTermAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, policyYear);
        spPolicyTerm.setAdapter(PolicyTermAdapter);
        spPolicyTerm.setSelection(15);

        PremTermAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, policyYear);
        spPremTerm.setAdapter(PremTermAdapter);
        spPremTerm.setSelection(15);
    }

    private void init(View view) {

        //headers
        tvSum = (TextView) view.findViewById(R.id.tvSum);
        tvGender = (TextView) view.findViewById(R.id.tvGender);
        tvSmoker = (TextView) view.findViewById(R.id.tvSmoker);
        tvAge = (TextView) view.findViewById(R.id.tvAge);
        tvPolicyTerm = (TextView) view.findViewById(R.id.tvPolicyTerm);
        tvCrn = (TextView) view.findViewById(R.id.tvCrn);
        ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
        ivInfo = (ImageView) view.findViewById(R.id.ivInfo);

        btnGetQuote = (Button) view.findViewById(R.id.btnGetQuote);
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etDOB = (EditText) view.findViewById(R.id.etDateofBirth);
        tvMale = (TextView) view.findViewById(R.id.tvMale);
        tvFemale = (TextView) view.findViewById(R.id.tvFemale);
        tvYes = (TextView) view.findViewById(R.id.tvYes);
        tvNo = (TextView) view.findViewById(R.id.tvNo);

        etPincode = (EditText) view.findViewById(R.id.etPincode);
        etSumAssured = (EditText) view.findViewById(R.id.etSumAssured);
        spPolicyTerm = (Spinner) view.findViewById(R.id.spPolicyTerm);
        spPremTerm = (Spinner) view.findViewById(R.id.spPremTerm);


        //Compare All
        llCompareAll = (LinearLayout) view.findViewById(R.id.llCompareAll);
        rvTerm = (RecyclerView) view.findViewById(R.id.rvTerm);
        rvTerm.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTerm.setHasFixedSize(true);
        cvInputDetails = (CardView) view.findViewById(R.id.cvInputDetails);
        mainScroll = (NestedScrollView) view.findViewById(R.id.mainScroll);
        layoutCompare = (View) view.findViewById(R.id.layoutCompare);

    }

    @Override
    public void onClick(View view) {
        Constants.hideKeyBoard(view, getActivity());
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

        } else if (i == R.id.ivEdit) {
            ((CompareTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            changeInputQuote(true);

        } else if (i == R.id.btnGetQuote) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "COMPARE GET QUOTE  TERM INSURANCE", "COMPARE GET QUOTE  TERM INSURANCE");
            if (btnGetQuote.getText().toString().toLowerCase().contains("get")) {
                if (isValidInput()) {
                    setTermRequest();
                    //((IciciTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
                    fetchQuotes();
                }
            } else {
                ((CompareTermActivity) getActivity()).redirectToInput(termFinmartRequest);
                changeInputQuote(true);
            }

               /* if (isValidInput()) {
                    setTermRequest();
                    ((CompareTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
                }*/

        }
    }

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

    public void fetchQuotes() {
        showDialog();
        new TermInsuranceController(getActivity()).getTermInsurer(termFinmartRequest, this);
    }

    private void updateCrnToServer() {
        if (termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id() != null && !termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id().equals(""))
            new TermInsuranceController(getActivity()).updateCRN(termFinmartRequest.getTermRequestId(), Integer.parseInt(termFinmartRequest.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id()), this);
    }

    private void setTermRequest() {
        //termRequestEntity.setExisting_ProductInsuranceMapping_Id("");
        termRequestEntity.setLumpsumPercentage("0");
        termRequestEntity.setPolicyTerm("" + dbPersistanceController.getPremYearID(spPolicyTerm.getSelectedItem().toString()));

        if (isMale)
            termRequestEntity.setInsuredGender("M");
        else
            termRequestEntity.setInsuredGender("F");

        if (isSmoker)
            termRequestEntity.setIs_TabaccoUser("true");
        else
            termRequestEntity.setIs_TabaccoUser("false");


        termRequestEntity.setSumAssured(etSumAssured.getText().toString());
        termRequestEntity.setInsuredDOB(etDOB.getText().toString());
        termRequestEntity.setPaymentModeValue("1");
        termRequestEntity.setPolicyCommencementDate(etDOB.getText().toString());
        termRequestEntity.setCityName("Mumbai");
        termRequestEntity.setState("Maharashtra");
        termRequestEntity.setPlanTaken("Life");
        termRequestEntity.setFrequency("Annual");
        termRequestEntity.setDeathBenefitOption("Lump-Sum");
        termRequestEntity.setPPT("" + dbPersistanceController.getPremYearID(spPremTerm.getSelectedItem().toString()));
        termRequestEntity.setIncomeTerm("" + dbPersistanceController.getPremYearID(spPremTerm.getSelectedItem().toString()));

        termRequestEntity.setInsurerId(0);
        termRequestEntity.setSessionID("");
        termRequestEntity.setContactName(etFirstName.getText().toString() + " " + etLastName.getText().toString());
        termRequestEntity.setContactEmail("finmarttest@gmail.com");
        termRequestEntity.setContactMobile(etMobile.getText().toString());
        //termRequestEntity.setSupportsAgentID("1682");
        termRequestEntity.setPincode(etPincode.getText().toString());

        termFinmartRequest.setFba_id(new DBPersistanceController(getActivity()).getUserData().getFBAId());
        //termFinmartRequest.setTermRequestId(0);
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
        return true;
    }

    //region date picker

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
                    }
                }
            });

            //endregion
        }
    };
    //endregion

    private void changeInputQuote(boolean isInput) {
        if (isInput) {
            //((IciciTermActivity) getActivity()).redirectToInput(termFinmartRequest);
            btnGetQuote.setText("GET QUOTE");
            layoutCompare.setVisibility(View.VISIBLE);
            llCompareAll.setVisibility(View.VISIBLE);
            rvTerm.setVisibility(View.GONE);
            cvInputDetails.setVisibility(View.GONE);
        } else {
            ((CompareTermActivity) getActivity()).redirectToQuote(termFinmartRequest);
            btnGetQuote.setText("Back To Input");
            layoutCompare.setVisibility(View.GONE);
            llCompareAll.setVisibility(View.GONE);
            rvTerm.setVisibility(View.VISIBLE);
            cvInputDetails.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof TermCompareQuoteResponse) {
            cancelDialog();
            processResponse((TermCompareQuoteResponse) response);
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

    private void bindQuotes() {
        int uptoAge = Integer.parseInt(termRequestEntity.getPPT()) + caluclateAge(etDOB.getText().toString());
        mAdapter = new TermQuoteAdapter(TermInputFragment.this, termCompareResponseEntities, "" + uptoAge);
        rvTerm.setAdapter(mAdapter);
        // tvCrn.setText("" + termCompareQuoteResponse.getMasterData().getResponse().get(0).getCustomerReferenceID());
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
                termCompareResponseEntities.clear();
                for (int i = 0; i < termCompareQuoteResponse.getMasterData().getResponse().size(); i++) {
                    TermCompareResponseEntity termCompareResponseEntity = termCompareQuoteResponse.getMasterData().getResponse().get(i);
                    if (termCompareResponseEntity.getQuoteStatus().equals("Success")) {
                        if (termCompareResponseEntity.getInsurerId() != 43)// removing edelwise
                            termCompareResponseEntities.add(termCompareResponseEntity);
                    }
                }

                crn = "" + termCompareQuoteResponse.getMasterData().getResponse().get(0).getCustomerReferenceID();
                termRequestEntity.setExisting_ProductInsuranceMapping_Id(crn);
                termFinmartRequest.setTermRequestEntity(termRequestEntity);
                if (termCompareQuoteResponse.getMasterData().getLifeTermRequestID() != 0)
                    termRequestId = termCompareQuoteResponse.getMasterData().getLifeTermRequestID();
                termFinmartRequest.setTermRequestId(termRequestId);
                updateCrnToServer();
                bindHeaders();
                bindQuotes();
                changeInputQuote(false);
            } else {
                Toast.makeText(getActivity(), "No Quotes Found.", Toast.LENGTH_SHORT).show();
            }
            if (termCompareResponseEntities.size() == 0) {
                Toast.makeText(getActivity(), "No Quotes Found.", Toast.LENGTH_LONG).show();
            }
        }
        mainScroll.scrollTo(0, 0);
    }

    public void redirectToBuy(TermCompareResponseEntity termCompareResponseEntity) {
        new TermInsuranceController(getActivity()).convertQuoteToApp("" + termFinmartRequest.getTermRequestId(),
                "" + termFinmartRequest.getTermRequestEntity().getInsurerId(),
                "" + dbPersistanceController.getUserData().getFBAId(),
                "" + termCompareResponseEntity.getNetPremium(), this);
        startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                .putExtra("URL", termCompareResponseEntity.getProposerPageUrl())
                .putExtra("NAME", "CLICK TO PROTECT 3D")
                .putExtra("TITLE", "CLICK TO PROTECT 3D"));
        new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Life Ins Buy"), Constants.LIFE_INS), null);

    }

    public void redirectToCustomize(TermCompareResponseEntity termCompareResponseEntity) {
        if (termCompareResponseEntity.getInsurerId() == 28)
            ((CompareTermActivity) getActivity()).redirectToHdfcQuote(termCompareResponseEntity);
        else if (termCompareResponseEntity.getInsurerId() == 39)
            ((CompareTermActivity) getActivity()).redirectToIciciQuote(termCompareResponseEntity);
    }
}
