package com.datacomp.magicfinmart.loan_fm.balancetransfer.addquote;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetBLDispalyResponse;

public class InputFragment_bl extends BaseFragment implements View.OnClickListener {


    DBPersistanceController databaseController;

    EditText etOutstanding, etCurrInc, ettenureyrs, etNameOfApplicant, etcontact;
    RadioGroup rgloantype;
    RadioButton rbimghl, rbimgpl, rbimglap;

    LoginResponseEntity loginEntity;
    GetBLDispalyResponse getblDispalyLoanResponse;
    BLLoanRequest blLoanRequest;
    FmBalanceLoanRequest fmBalanceLoanRequest;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Button btnGetQuote;

    Context mContext;
    double ROIHLBL = 0, ROILABL = 0, ROIPLBL = 0;

    public InputFragment_bl() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_add_blquote, container, false);

        init_widgets(view);
        databaseController = new DBPersistanceController(getActivity());
        loginEntity = databaseController.getUserData();
        try {
            ROIHLBL = Double.parseDouble(databaseController.getConstantsData().getROIHLBL());
            ROILABL = Double.parseDouble(databaseController.getConstantsData().getROILABL());
            ROIPLBL = Double.parseDouble(databaseController.getConstantsData().getROIPLBL());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setListener();

        if (getArguments() != null) {
            if (getArguments().getParcelable(BLMainActivity.BL_INPUT_REQUEST) != null) {
                fmBalanceLoanRequest = getArguments().getParcelable(BLMainActivity.BL_INPUT_REQUEST);
                blLoanRequest = fmBalanceLoanRequest.getBLLoanRequest();
                fillCustomerDetails();


            }
        } else {
            fmBalanceLoanRequest = new FmBalanceLoanRequest();
            fmBalanceLoanRequest.setFBA_id(new DBPersistanceController(getContext()).getUserData().getFBAId());
            fmBalanceLoanRequest.setBalanceTransferId(0);
            blLoanRequest = new BLLoanRequest();
            fmBalanceLoanRequest.setBLLoanRequest(blLoanRequest);
        }
        return view;
    }

    private void init_widgets(View view) {

        btnGetQuote = (Button) view.findViewById(R.id.btnGetQuote);
        //endregion
        //region Property Initialize
        etOutstanding = (EditText) view.findViewById(R.id.etOutstanding);

        etCurrInc = (EditText) view.findViewById(R.id.etCurrInc);

        ettenureyrs = (EditText) view.findViewById(R.id.ettenureyrs);

        etNameOfApplicant = (EditText) view.findViewById(R.id.etNameOfApplicant);
        etcontact = (EditText) view.findViewById(R.id.etcontact);


//        llSalaried = (LinearLayout) view.findViewById(R.id.llSalaried);

        rgloantype = (RadioGroup) view.findViewById(R.id.rgloantype);
        rbimghl = (RadioButton) view.findViewById(R.id.rbimghl);
        rbimgpl = (RadioButton) view.findViewById(R.id.rbimgpl);
        rbimglap = (RadioButton) view.findViewById(R.id.rbimglap);

    }

    private void setApplicantDetails() {
        // region  HomeLoanRequest Binding

        blLoanRequest = fmBalanceLoanRequest.getBLLoanRequest();
        blLoanRequest.setLoanamount(Long.valueOf(etOutstanding.getText().toString()));



        blLoanRequest.setLoanterm(Double.parseDouble(ettenureyrs.getText().toString()));
        blLoanRequest.setLoaninterest(Double.parseDouble(etCurrInc.getText().toString()));
        blLoanRequest.setApplicantName(etNameOfApplicant.getText().toString().trim());

        blLoanRequest.setContact(etcontact.getText().toString());


        if (rbimghl.isChecked()) {
            blLoanRequest.setProduct_id(5);//hl
            blLoanRequest.setType("HLBT");
        } else if (rbimgpl.isChecked()) {
            blLoanRequest.setProduct_id(14);//pl
            blLoanRequest.setType("PLBT");
        } else if (rbimglap.isChecked()) {
            blLoanRequest.setProduct_id(2);//lap
            blLoanRequest.setType("LAPBT");
        }
        blLoanRequest.setbrokerid(Integer.parseInt(loginEntity.getLoanId()));
        blLoanRequest.setLoanID(Integer.parseInt(loginEntity.getLoanId()));

        blLoanRequest.setSource("Demo APP");
        blLoanRequest.setEmail("");

        //endregion
    }

    private void fillCustomerDetails() {

        Log.d("DETAILS", blLoanRequest.toString());

        try {


            etOutstanding.setText("" + BigDecimal.valueOf(Math.ceil(blLoanRequest.getLoanamount())).setScale(0, BigDecimal.ROUND_HALF_UP));

            ettenureyrs.setText("" + blLoanRequest.getLoanterm());

            etCurrInc.setText("" + blLoanRequest.getLoaninterest());


            if (blLoanRequest.getApplicantName() != null)
                etNameOfApplicant.setText(blLoanRequest.getApplicantName());

            if (blLoanRequest.getContact() != null)
                etcontact.setText(blLoanRequest.getContact());

            if (Integer.toString(blLoanRequest.getProduct_id()).matches("12")) {
                rbimghl.setChecked(true);
            } else if (Integer.toString(blLoanRequest.getProduct_id()).matches("9")) {
                rbimgpl.setChecked(true);
            } else if (Integer.toString(blLoanRequest.getProduct_id()).matches("7")) {
                rbimglap.setChecked(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setListener() {
        btnGetQuote.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnGetQuote) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Get quote BL : Get quote button for BL APPLY"), Constants.BALANCE_TRANSFER), null);

            MyApplication.getInstance().trackEvent( Constants.BALANCE_TRANSFER,"Clicked","Get quote BL : Get quote button for BL APPLY");

            //region Validation
            //region Property Validation
            String Outstanding = etOutstanding.getText().toString();
            String CurrInc = etCurrInc.getText().toString();
            String TenureInYear = ettenureyrs.getText().toString();
            String Name = etNameOfApplicant.getText().toString();
            String Contact = etcontact.getText().toString();
            if (TextUtils.isEmpty(Name)) {

                etNameOfApplicant.setError("Please Enter Name Of Applicant.");
                etNameOfApplicant.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(Contact)) {


            } else {
                if (Contact.length() < 10) {

                    etcontact.setError("Please Enter 10 digit Mobile Number.");
                    etcontact.requestFocus();
                    return;

                }

            }


            if (TextUtils.isEmpty(Outstanding)) {

                etOutstanding.setError("Please Enter Outstanding Amount.");
                etOutstanding.requestFocus();
                return;

            }


            if (TextUtils.isEmpty(CurrInc)) {

                etCurrInc.setError("Please Enter Curr Int Rate.");
                etCurrInc.requestFocus();
                return;

            }
            if (Double.parseDouble(CurrInc) > 100) {

                etCurrInc.setError("Please Check  Curr Int Rate.");
                etCurrInc.requestFocus();
                return;

            }
            if (TextUtils.isEmpty(TenureInYear)) {

                ettenureyrs.setError("Please Enter Tenure (yrs).");
                ettenureyrs.requestFocus();
                return;

            }
            if (rbimghl.isChecked()) {
                if (Double.parseDouble(TenureInYear) > 30) {

                    ettenureyrs.setError("Please Check Loan Tenure Year.");
                    ettenureyrs.requestFocus();
                    return;

                }

                if (Double.parseDouble(TenureInYear) < 1 ) {

                    ettenureyrs.setError("Please Check Loan Tenure Year.");
                    ettenureyrs.requestFocus();
                    return;

                }
            } else if (rbimgpl.isChecked()) {
                if (Double.parseDouble(TenureInYear) > 7) {

                    ettenureyrs.setError("Please Check Loan Tenure Year.");
                    ettenureyrs.requestFocus();
                    return;

                }
                if (Double.parseDouble(TenureInYear) < 1 ) {

                    ettenureyrs.setError("Please Check Loan Tenure Year.");
                    ettenureyrs.requestFocus();
                    return;

                }
            } else if (rbimglap.isChecked()) {
                if (Double.parseDouble(TenureInYear) > 15) {

                    ettenureyrs.setError("Please Check Loan Tenure Year.");
                    ettenureyrs.requestFocus();
                    return;

                } if (Double.parseDouble(TenureInYear) < 1 ) {

                    ettenureyrs.setError("Please Check Loan Tenure Year.");
                    ettenureyrs.requestFocus();
                    return;

                }
            }


            if (rbimghl.isChecked()) {
                if (Double.parseDouble(Outstanding) < 500000) {

                    etOutstanding.setError("Outstanding Amount SHOULD BE GREATER THAN 500000.");
                    etOutstanding.requestFocus();
                    return;

                }
                if (ROIHLBL >= Double.parseDouble(etCurrInc.getText().toString())) {

                    etCurrInc.setError("INTEREST SHOULD BE GREATER THAN " + ROIHLBL + "");
                    etCurrInc.requestFocus();
                    return;

                }
            } else if (rbimglap.isChecked()) {

                if (Double.parseDouble(Outstanding) < 500000) {

                    etOutstanding.setError("Outstanding Amount SHOULD BE GREATER THAN 500000.");
                    etOutstanding.requestFocus();
                    return;

                }
                if (ROILABL >= Double.parseDouble(etCurrInc.getText().toString())) {

                    etCurrInc.setError("INTEREST SHOULD BE GREATER THAN (OR)EQUAL TO " + ROILABL + " ");
                    etCurrInc.requestFocus();
                    return;

                }
            } else if (rbimgpl.isChecked()) {

                if (Double.parseDouble(Outstanding) < 100000) {

                    etOutstanding.setError("Outstanding Amount SHOULD BE GREATER THAN 100000.");
                    etOutstanding.requestFocus();
                    return;

                }

                if (ROIPLBL >= Double.parseDouble(etCurrInc.getText().toString())) {

                    etCurrInc.setError("INTEREST SHOULD BE GREATER THAN (OR)EQUAL TO " + ROIPLBL + " ");
                    etCurrInc.requestFocus();
                    return;

                }
            }
            //endregion


            // endregion
            setApplicantDetails();
            // showDialog();
            //  new PersonalLoanController(getActivity()).getBLQuote(blLoanRequest, this);
            ((BLMainActivity) getActivity()).getQuoteParameterBundle(fmBalanceLoanRequest);

        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


}
