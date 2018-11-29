package com.datacomp.magicfinmart.loan_fm.balancetransfer.addquote;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.balancetransfer.loan_apply.BalanceTransferLoanApplyActivity;
import com.datacomp.magicfinmart.loan_fm.balancetransfer.loan_apply.BalanceTransferPersonalApplyActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.ShareQuoteActivity;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan.MainLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.personalloan.PersonalLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BLEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BLSavingEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BuyLoanQuerystring;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.SavingBLEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BLLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BankSaveRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.BankForNodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmSaveQuoteBLResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetBLDispalyResponse;

public class QuoteFragment_bl extends BaseFragment implements View.OnClickListener, IResponseSubcriber, IResponseSubcriberFM, BaseFragment.PopUpListener {

    TextView txtAppName, txtLoanAmnt, txtLoanTenure, txtInputSummry, txtCount, txtType, txtcurrRate;
    TextView txtcurrloanemi, txtdropemi, txtnewemi, txtdropinterestrate, txtreducedintrest;
    BLQuoteAdapter mAdapter;
    List<BLEntity> BlListdata;
    List<BLSavingEntity> BlsavingEntity;
    CardView cvInputSummary;

    RecyclerView rvBLQuotes;
    int LoanRequireID = 0;
    int BalanceTransferId = 0;
    BankSaveRequest bankSaveRequest;

    GetBLDispalyResponse getblDispalyResponse;
    BLLoanRequest blLoanRequest;
    FmBalanceLoanRequest fmBalanceLoanRequest;
    LinearLayout ivllEdit, llgraph, llshare;

    List<SavingBLEntity> savingBlList;
    ImageView ivShare;
    BuyLoanQuerystring buyLoanQuerystring;
    int QuoteID = 0;

    public QuoteFragment_bl() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_bl_loan_quote, container, false);
        registerPopUp(this);
        initialise_widget(view);
        if (getArguments() != null) {
            fmBalanceLoanRequest = getArguments().getParcelable(BLMainActivity.BL_QUOTE_REQUEST);
            blLoanRequest = fmBalanceLoanRequest.getBLLoanRequest();

            Gson gson = new Gson();

            String json = gson.toJson(blLoanRequest);
            int i = json.length();
            showDialog("Please wait.. fetching quote");
            new PersonalLoanController(getActivity()).getBLQuote(blLoanRequest, this);
        }
        return view;
    }

    public void redirectToApplyBank(BLEntity entity) {
        setFmBankRequest(entity);
    }

    private void initialise_widget(View view) {
        ivShare = (ImageView) view.findViewById(R.id.ivShare);
        ivShare.setOnClickListener(this);
        cvInputSummary = (CardView) view.findViewById(R.id.cvInputSummary);
        llgraph = (LinearLayout) view.findViewById(R.id.llgraph);
        llshare = (LinearLayout) view.findViewById(R.id.llshare);
        txtInputSummry = (TextView) view.findViewById(R.id.txtInputSummry);
        txtAppName = (TextView) view.findViewById(R.id.txtAppName);
        txtLoanAmnt = (TextView) view.findViewById(R.id.txtLoanAmnt);
        txtLoanTenure = (TextView) view.findViewById(R.id.txtLoanTenure);
        txtCount = (TextView) view.findViewById(R.id.txtCount);
        txtType = (TextView) view.findViewById(R.id.txtType);
        txtcurrRate = (TextView) view.findViewById(R.id.txtblcurrRate);

        txtcurrloanemi = (TextView) view.findViewById(R.id.txtcurrloanemi);
        txtdropemi = (TextView) view.findViewById(R.id.txtdropemi);
        txtnewemi = (TextView) view.findViewById(R.id.txtnewemi);
        txtdropinterestrate = (TextView) view.findViewById(R.id.txtdropinterestrate);
        txtreducedintrest = (TextView) view.findViewById(R.id.txtreducedintrest);


        ivllEdit = (LinearLayout) view.findViewById(R.id.ivllEdit);
        ivllEdit.setOnClickListener(this);
        rvBLQuotes = (RecyclerView) view.findViewById(R.id.rvbLQuotes);
        rvBLQuotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBLQuotes.setNestedScrollingEnabled(false);

    }

    public void quoteToApp() {
        //quote to application conversion
        //TODO : USE : LoanRequireID and "A"
    }

//    public void redirectToApplyLoan(BLEntity entity, String url, int id) {
//        startActivity(new Intent(getActivity(), BTLoanApplyWebView.class)
//                .putExtra("BL", entity)
//                .putExtra("BL_Req", blLoanRequest)
//                .putExtra("BL_QUOTE_ID", id));
//    }

    public void redirectToApplyLoanBT(FmBalanceLoanRequest entity) {
        new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Buy BL : Buy button for BL"), Constants.BALANCE_TRANSFER), null);
        MyApplication.getInstance().trackEvent( Constants.BALANCE_TRANSFER,"Clicked","Buy BL : Buy button for BL");


        if (Integer.toString(entity.getBLLoanRequest().getProduct_id()).matches("5")) {
//home
            startActivity(new Intent(getContext(), BalanceTransferLoanApplyActivity.class)
                    .putExtra("BuyLoanQuery", buyLoanQuerystring));

        } else if (Integer.toString(entity.getBLLoanRequest().getProduct_id()).matches("14")) {
            //personal
            startActivity(new Intent(getContext(), BalanceTransferPersonalApplyActivity.class)
                    .putExtra("BuyLoanQuery", buyLoanQuerystring));

        } else if (Integer.toString(entity.getBLLoanRequest().getProduct_id()).matches("2")) {
            //lap
            startActivity(new Intent(getContext(), BalanceTransferLoanApplyActivity.class)
                    .putExtra("BuyLoanQuery", buyLoanQuerystring));

        }
         //

    }

    private void bindQuotes() {

        if (getblDispalyResponse != null) {
            txtInputSummry.setVisibility(View.VISIBLE);
            cvInputSummary.setVisibility(View.VISIBLE);
            llgraph.setVisibility(View.VISIBLE);
            llshare.setVisibility(View.VISIBLE);
            mAdapter = new BLQuoteAdapter(this, getblDispalyResponse.getData().getBank_data(), getblDispalyResponse,Long.valueOf(blLoanRequest.getLoanamount()));
            rvBLQuotes.setAdapter(mAdapter);

            savingBlList = getblDispalyResponse.getData().getSavings();

            if (getblDispalyResponse.getData().getBank_data().size() > 0) {
                txtCount.setText("" + getblDispalyResponse.getData().getBank_data().size() + " Results from www.rupeeboss.com");
                txtCount.setVisibility(View.VISIBLE);
            } else {
                txtCount.setText("");
                txtCount.setVisibility(View.GONE);
            }

            if (blLoanRequest != null) {
                try {
                    txtAppName.setText("" + blLoanRequest.getApplicantName().toUpperCase());
                    txtLoanAmnt.setText("" + BigDecimal.valueOf(Math.ceil(blLoanRequest.getLoanamount())).setScale(0, BigDecimal.ROUND_HALF_UP));

                    //  txtLoanTenure.setText("" + Double.toString(blLoanRequest.getLoanterm()) + " Years");
                    txtLoanTenure.setText("" + getDigitPrecision(blLoanRequest.getLoanterm()) + " Years");

                    if (Integer.toString(blLoanRequest.getProduct_id()).matches("5")) {
                        txtType.setText("HOME LOAN");
                    } else if (Integer.toString(blLoanRequest.getProduct_id()).matches("14")) {
                        txtType.setText("PERSONAL LOAN");
                    } else if (Integer.toString(blLoanRequest.getProduct_id()).matches("2")) {
                        txtType.setText("LAP");
                    }

                    txtcurrRate.setText("" + blLoanRequest.getLoaninterest());

                    if (savingBlList != null) {

                        // txtcurrloanemi.setText("" + "\u20B9" + BigDecimal.valueOf(savingBlList.get(0).getAmount()).toPlainString());
                        // txtdropemi.setText("" + "\u20B9"+" " +String.format("%.0f", savingBlList.get(0).getDrop_emi()));
                        // txtcurrloanemi.setText("" + savingBlList.get(0).getAmount());

                        txtcurrloanemi.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getAmount()));
                        txtdropemi.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getDrop_emi()));
                        txtnewemi.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getNew_amount()));
                        txtdropinterestrate.setText("" + savingBlList.get(0).getDrop_in_int() + " %");
                        txtreducedintrest.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getSavings()));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void bindQuotes_NoData() {


        txtInputSummry.setVisibility(View.VISIBLE);
        cvInputSummary.setVisibility(View.VISIBLE);
//            llgraph.setVisibility(View.VISIBLE);
//            llshare.setVisibility(View.VISIBLE);
//            mAdapter = new BLQuoteAdapter(this, getblDispalyResponse.getData().getBank_data(), getblDispalyResponse, blLoanRequest.getLoanamount());
//            rvBLQuotes.setAdapter(mAdapter);
//
//            savingBlList = getblDispalyResponse.getData().getSavings();

//            if (getblDispalyResponse.getData().getBank_data().size() > 0) {
//                txtCount.setText("" + getblDispalyResponse.getData().getBank_data().size() + " Results from www.rupeeboss.com");
//                txtCount.setVisibility(View.VISIBLE);
//            } else {
//                txtCount.setText("");
//                txtCount.setVisibility(View.GONE);
//            }

        if (blLoanRequest != null) {
            try {
                txtAppName.setText("" + blLoanRequest.getApplicantName().toUpperCase());
                txtLoanAmnt.setText("" + BigDecimal.valueOf(Math.ceil(blLoanRequest.getLoanamount())).setScale(0, BigDecimal.ROUND_HALF_UP));
                txtLoanTenure.setText("" + getDigitPrecision(blLoanRequest.getLoanterm()) + " Years");

                if (Integer.toString(blLoanRequest.getProduct_id()).matches("5")) {
                    txtType.setText("HOME LOAN");
                } else if (Integer.toString(blLoanRequest.getProduct_id()).matches("14")) {
                    txtType.setText("PERSONAL LOAN");
                } else if (Integer.toString(blLoanRequest.getProduct_id()).matches("2")) {
                    txtType.setText("LAP");
                }

                txtcurrRate.setText("" + blLoanRequest.getLoaninterest());

//                    if (savingBlList != null) {
//
//                        // txtcurrloanemi.setText("" + "\u20B9" + BigDecimal.valueOf(savingBlList.get(0).getAmount()).toPlainString());
//                        // txtdropemi.setText("" + "\u20B9"+" " +String.format("%.0f", savingBlList.get(0).getDrop_emi()));
//                        // txtcurrloanemi.setText("" + savingBlList.get(0).getAmount());
//
//                        txtcurrloanemi.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getAmount()));
//                        txtdropemi.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getDrop_emi()));
//                        txtnewemi.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getNew_amount()));
//                        txtdropinterestrate.setText("" + savingBlList.get(0).getDrop_in_int() + " %");
//                        txtreducedintrest.setText("" + "\u20B9" + Math.round(savingBlList.get(0).getSavings()));
//
//                    }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void setFmBLoanRequest(int tempQuoteID) {
        QuoteID = tempQuoteID;
        showDialog();
        blLoanRequest.setQuote_id(QuoteID);
        fmBalanceLoanRequest.setBLLoanRequest(blLoanRequest);
        new MainLoanController(getActivity()).saveBLQuoteData(fmBalanceLoanRequest, this);

    }

    private void setFmBankRequest(BLEntity entity) {


        try {
            bankSaveRequest = new BankSaveRequest();
            bankSaveRequest.setLoan_requestID(fmBalanceLoanRequest.getBalanceTransferId());
            bankSaveRequest.setBank_id((entity.getBank_Id()));
            bankSaveRequest.setType("BT");

            ///

            buyLoanQuerystring = new BuyLoanQuerystring();

            buyLoanQuerystring.setBankId(entity.getBank_Id());

            buyLoanQuerystring.setProp_Loan_Eligible(BigDecimal.valueOf(fmBalanceLoanRequest.getBLLoanRequest().getLoanamount()).toPlainString());
            buyLoanQuerystring.setProp_Processing_Fee(BigDecimal.valueOf(entity.getProcessingfee()).toPlainString());

           // buyLoanQuerystring.setProp_Processing_Fee(String.valueOf(entity.getProcessingfee()));
            buyLoanQuerystring.setQuote_id(QuoteID);
            buyLoanQuerystring.setProp_type(entity.getRoi_type());
            buyLoanQuerystring.setMobileNo(fmBalanceLoanRequest.getBLLoanRequest().getContact());
            //  buyLoanQuerystring.setCity(fmBalanceLoanRequest.getBLLoanRequest().getCity());

            if (Integer.toString(fmBalanceLoanRequest.getBLLoanRequest().getProduct_id()).matches("5")) {
                buyLoanQuerystring.setType("HLBT");

            } else if (Integer.toString(fmBalanceLoanRequest.getBLLoanRequest().getProduct_id()).matches("14")) {
                buyLoanQuerystring.setType("PLBT");
            } else if (Integer.toString(fmBalanceLoanRequest.getBLLoanRequest().getProduct_id()).matches("2")) {
                buyLoanQuerystring.setType("LAPBT");
            }

            new MainLoanController(getActivity()).savebankFbABuyData(bankSaveRequest, this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {
        cancelDialog();
        if (response instanceof FmSaveQuoteBLResponse) {
            if (response.getStatusNo() == 0) {
                LoanRequireID = ((FmSaveQuoteBLResponse) response).getMasterData().get(0).getLoanRequestID();
                fmBalanceLoanRequest.setBalanceTransferId(LoanRequireID);
                ((BLMainActivity) getActivity()).updateRequest(fmBalanceLoanRequest, true);

            }
        } else if (response instanceof BankForNodeResponse) {
            if (response.getStatusNo() == 0) {
                //   ((BLMainActivity) getActivity()).redirectInput(fmBalanceLoanRequest);
                redirectToApplyLoanBT(fmBalanceLoanRequest);

            }
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof GetBLDispalyResponse) {
            if (response.getStatus_Id() == 0) {

                getblDispalyResponse = ((GetBLDispalyResponse) response);

                bindQuotes();
                setFmBLoanRequest(getblDispalyResponse.getQuote_id());

            } else {
                Toast.makeText(getActivity(), response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        if (getActivity() != null)
            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

        bindQuotes_NoData();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivllEdit) {

            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("BALANCE TRANSFER : BALANCE TRANSFER QUOTES  EDIT"), Constants.BALANCE_TRANSFER), null);

            MyApplication.getInstance().trackEvent( Constants.BALANCE_TRANSFER,"Clicked","BALANCE TRANSFER QUOTES EDIT");

            ((BLMainActivity) getActivity()).redirectInput(fmBalanceLoanRequest);
        } else if (v.getId() == R.id.ivShare) {

            if (getblDispalyResponse != null) {

                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("BALANCE TRANSFER : BALANCE TRANSFER QUOTES  SEARCH"), Constants.BALANCE_TRANSFER), null);

                MyApplication.getInstance().trackEvent( Constants.BALANCE_TRANSFER,"Clicked","BALANCE TRANSFER QUOTES SEARCH");


                Intent intent = new Intent(getActivity(), ShareQuoteActivity.class);
                intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "BL_ALL_QUOTE");
                intent.putExtra("RESPONSE", getblDispalyResponse);
                intent.putExtra("NAME", fmBalanceLoanRequest.getBLLoanRequest().getApplicantName());
                intent.putExtra("LOAN_REQUIRED", "" + fmBalanceLoanRequest.getBLLoanRequest().getLoanamount());
                intent.putExtra("PRODUCT_ID", fmBalanceLoanRequest.getBLLoanRequest().getProduct_id());
                startActivity(intent);
            }


        }
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        if (view.getId() == R.id.ivShare) {
            dialog.cancel();
        }
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        if (view.getId() == R.id.ivShare) {
            dialog.cancel();
        }
    }

    private String getDigitPrecision(double value) {
        //  return Double.parseDouble(new DecimalFormat("##").format(value));
        return new DecimalFormat("##").format(value);
    }
}
