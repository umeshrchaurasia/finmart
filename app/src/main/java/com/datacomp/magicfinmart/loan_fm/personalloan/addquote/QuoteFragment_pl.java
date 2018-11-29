package com.datacomp.magicfinmart.loan_fm.personalloan.addquote;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;
import com.datacomp.magicfinmart.webviews.ShareQuoteActivity;

import java.math.BigDecimal;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan.MainLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.personalloan.PersonalLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.BuyLoanQuerystring;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BankSaveRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmPersonalLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.PersonalLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.equifax_personalloan_request;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.BankForNodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmSaveQuotePersonalLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetPersonalLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.equifax_personalloan_response;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by Rahul on 24/01/2018.
 */

public class QuoteFragment_pl extends BaseFragment implements View.OnClickListener, IResponseSubcriber, IResponseSubcriberFM {
    private static String INPUT_FRAGMENT = "input";

    TextView txtAppName, txtCostOfProp, txtLoanTenure, txtOccupation, txtMonthlyIncome, txtExistEmi, txtCount, txtInputSummary, txtCreditscore;
    CardView cvInputSummary;

    RecyclerView rvPLQuotes;

    PLQuoteAdapter mAdapter;
    int LoanRequireID = 0;
    BankSaveRequest bankSaveRequest;
    GetPersonalLoanResponse getPersonalLoanResponse;
    equifax_personalloan_response equifax_personalloan_response_res;
    FmPersonalLoanRequest fmPersonalLoanRequest;
    PersonalLoanRequest personalLoanRequest;
    BuyLoanQuerystring buyLoanQuerystring;
    LinearLayout ivllEdit, llcreditscore;
    int QuoteID = 0;
    String Leadid = "";
    ImageView ivShare, ivdownload;
    String url_score = "", score = "";
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginResponseEntity;

    public QuoteFragment_pl() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_personal_loan_quote, container, false);
        dbPersistanceController = new DBPersistanceController(getActivity());
        loginResponseEntity = dbPersistanceController.getUserData();
        initialise_widget(view);

        if (getArguments() != null) {
            fmPersonalLoanRequest = getArguments().getParcelable(PLMainActivity.PL_QUOTE_REQUEST);
            personalLoanRequest = fmPersonalLoanRequest.getPersonalLoanRequest();
            showDialog("Please wait.. fetching quote");
            new PersonalLoanController(getActivity()).getPersonalLoan(personalLoanRequest, this);
        }
        return view;
    }

    public void redirectToApplyBank(PersonalQuoteEntity entity) {
        setFmBankRequest(entity);
    }


    private void initialise_widget(View view) {
        ivShare = (ImageView) view.findViewById(R.id.ivShare);
        ivShare.setOnClickListener(this);
        cvInputSummary = (CardView) view.findViewById(R.id.cvInputSummary);

        txtInputSummary = (TextView) view.findViewById(R.id.txtInputSummary);
        txtAppName = (TextView) view.findViewById(R.id.txtAppName);
        txtCostOfProp = (TextView) view.findViewById(R.id.txtCostOfProp);
        txtLoanTenure = (TextView) view.findViewById(R.id.txtLoanTenure);
        txtOccupation = (TextView) view.findViewById(R.id.txtOccupation);
        txtMonthlyIncome = (TextView) view.findViewById(R.id.txtMonthlyIncome);
        txtExistEmi = (TextView) view.findViewById(R.id.txtExistEmi);
        txtCount = (TextView) view.findViewById(R.id.txtCount);

        ivllEdit = (LinearLayout) view.findViewById(R.id.ivllEdit);
        ivllEdit.setOnClickListener(this);
        rvPLQuotes = (RecyclerView) view.findViewById(R.id.rvQuotes);
        rvPLQuotes.setLayoutManager(new LinearLayoutManager(getActivity()));

        llcreditscore = (LinearLayout) view.findViewById(R.id.llcreditscore);
        txtCreditscore = (TextView) view.findViewById(R.id.txtCreditscore);
        ivdownload = (ImageView) view.findViewById(R.id.ivdownload);
        ivdownload.setOnClickListener(this);

        txtCreditscore.setVisibility(View.GONE);
        ivdownload.setVisibility(View.GONE);
        llcreditscore.setVisibility(View.GONE);
// bundle.putParcelable(Constants.PL_REQUEST, personalLoanRequest);

    }

    public void quoteToApp() {
        //quote to application conversion
        //TODO : USE : LoanRequireID and "A"
    }

//    public void redirectToApplyLoan(PersonalQuoteEntity entity, String url, int id) {
//        startActivity(new Intent(getActivity(), PersonalLoanApplyWebView.class)
//                .putExtra("PL", entity)
//                .putExtra("PL_URL", url)
//                .putExtra("PL_QUOTE_ID", id));
//    }


    public void redirectToApplyLoan() {

        new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Buy PL : Buy button for PL"), Constants.PERSONA_LOAN), null);
        MyApplication.getInstance().trackEvent(Constants.PERSONA_LOAN, "Clicked", "Buy PL : Buy button for PL");

//              startActivity(new Intent(getContext(), PersonalLoanApplyActivity.class)
//                .putExtra("BuyLoanQuery", buyLoanQuerystring));

        //bank_web_url
        String urltobank = "" + buyLoanQuerystring.getBank_web_url() + "?quoteid=" + buyLoanQuerystring.getQuote_id() + "&leadid=" + buyLoanQuerystring.getLead_id() +
                "&fbaid=" + loginResponseEntity.getFBAId() + "&loanid=" + loginResponseEntity.getLoanId() + "&type=finmart";
        startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                .putExtra("URL", urltobank)
                .putExtra("NAME", buyLoanQuerystring.getBank_name())
                .putExtra("TITLE", buyLoanQuerystring.getBank_name()));
    }


    private void bindQuotes() {

        if (getPersonalLoanResponse != null) {
            txtInputSummary.setVisibility(View.VISIBLE);
            cvInputSummary.setVisibility(View.VISIBLE);
            ivShare.setVisibility(View.VISIBLE);

            mAdapter = new PLQuoteAdapter(this, getPersonalLoanResponse.getData(), getPersonalLoanResponse);
            rvPLQuotes.setAdapter(mAdapter);

            if (getPersonalLoanResponse.getData().size() > 0) {
                txtCount.setText("" + getPersonalLoanResponse.getData().size() + " Results from www.rupeeboss.com");
                txtCount.setVisibility(View.VISIBLE);
            } else {
                txtCount.setText("");
                txtCount.setVisibility(View.GONE);
            }


            if (personalLoanRequest != null) {
                try {
                    txtAppName.setText("" + personalLoanRequest.getApplicantNme().toUpperCase());
                    txtCostOfProp.setText("" + personalLoanRequest.getLoanRequired());
                    txtLoanTenure.setText("" + personalLoanRequest.getLoanTenure() + " Years");


                    txtOccupation.setText("SALARIED");

                    txtMonthlyIncome.setText("" + personalLoanRequest.getApplicantIncome());
                    txtExistEmi.setText("" + personalLoanRequest.getApplicantObligations());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void bindQuotes_NoData() {


        txtInputSummary.setVisibility(View.VISIBLE);
        cvInputSummary.setVisibility(View.VISIBLE);
        //  ivShare.setVisibility(View.VISIBLE);

        //   mAdapter = new PLQuoteAdapter(this, getPersonalLoanResponse.getData(), getPersonalLoanResponse);
        //  rvPLQuotes.setAdapter(mAdapter);

//            if (getPersonalLoanResponse.getData().size() > 0) {
//                txtCount.setText("" + getPersonalLoanResponse.getData().size() + " Results from www.rupeeboss.com");
//                txtCount.setVisibility(View.VISIBLE);
//            } else {
//                txtCount.setText("");
//                txtCount.setVisibility(View.GONE);
//            }

        if (personalLoanRequest != null) {
            try {
                txtAppName.setText("" + personalLoanRequest.getApplicantNme().toUpperCase());
                txtCostOfProp.setText("" + personalLoanRequest.getLoanRequired());
                txtLoanTenure.setText("" + personalLoanRequest.getLoanTenure() + " Years");


                txtOccupation.setText("SALARIED");

                txtMonthlyIncome.setText("" + personalLoanRequest.getApplicantIncome());
                txtExistEmi.setText("" + personalLoanRequest.getApplicantObligations());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void setFmPeronalLoanRequest(int tempQuoteID, String tempLeadid) {
        QuoteID = tempQuoteID;
        Leadid = tempLeadid;
        showDialog();


        personalLoanRequest.setQuote_id(QuoteID);
        fmPersonalLoanRequest.setPersonalLoanRequest(personalLoanRequest);
        new MainLoanController(getActivity()).savePLQuoteData(fmPersonalLoanRequest, this);

    }

    private void setFmBankRequest(PersonalQuoteEntity entity) {


        try {
            bankSaveRequest = new BankSaveRequest();
            bankSaveRequest.setLoan_requestID(fmPersonalLoanRequest.getLoan_requestID());
            bankSaveRequest.setBank_id((entity.getBank_Id()));
            bankSaveRequest.setType("PSL");

            buyLoanQuerystring = new BuyLoanQuerystring();
            buyLoanQuerystring.setBankId(entity.getBank_Id());

            buyLoanQuerystring.setProp_Loan_Eligible(BigDecimal.valueOf(entity.getLoan_eligible()).toPlainString());
            buyLoanQuerystring.setProp_Processing_Fee(BigDecimal.valueOf(entity.getProcessingfee()).toPlainString());
            // buyLoanQuerystring.setProp_Processing_Fee(String.valueOf(entity.getProcessingfee()));
            buyLoanQuerystring.setQuote_id(QuoteID);
            buyLoanQuerystring.setLead_id(Leadid);
            buyLoanQuerystring.setProp_type(entity.getRoi_type());
            buyLoanQuerystring.setMobileNo(fmPersonalLoanRequest.getPersonalLoanRequest().getContact());
            buyLoanQuerystring.setPan(fmPersonalLoanRequest.getPersonalLoanRequest().getpanno());
            buyLoanQuerystring.setCity(fmPersonalLoanRequest.getPersonalLoanRequest().getCity());
            buyLoanQuerystring.setBank_web_url(entity.getBank_web_url());
            buyLoanQuerystring.setBank_name(entity.getBank_Name());

            new MainLoanController(getActivity()).savebankFbABuyData(bankSaveRequest, this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {
        cancelDialog();
        if (response instanceof FmSaveQuotePersonalLoanResponse) {
            if (response.getStatusNo() == 0) {
                LoanRequireID = ((FmSaveQuotePersonalLoanResponse) response).getMasterData().get(0).getLoanRequestID();
                fmPersonalLoanRequest.setLoan_requestID(LoanRequireID);
                ((PLMainActivity) getActivity()).updateRequest(fmPersonalLoanRequest, true);

            }
        } else if (response instanceof BankForNodeResponse) {
            if (response.getStatusNo() == 0) {
                //  ((PLMainActivity) getActivity()).redirectInput(fmPersonalLoanRequest);

                redirectToApplyLoan();
            }
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof GetPersonalLoanResponse) {
            if (response.getStatus_Id() == 0) {

                getPersonalLoanResponse = ((GetPersonalLoanResponse) response);

                bindQuotes();
                setFmPeronalLoanRequest(getPersonalLoanResponse.getQuote_id(), getPersonalLoanResponse.getLead_Id());
                if (personalLoanRequest.getPostal().length() > 1) {
                    getequifax();
                } else {
                    txtCreditscore.setVisibility(View.GONE);
                    ivdownload.setVisibility(View.GONE);
                    llcreditscore.setVisibility(View.GONE);
                }

            } else {
                Toast.makeText(getActivity(), response.getMsg(), Toast.LENGTH_SHORT).show();
            }
        } else if (response instanceof equifax_personalloan_response) {
            equifax_personalloan_response_res = ((equifax_personalloan_response) response);
            score = equifax_personalloan_response_res.getResult().getScore();
            url_score = equifax_personalloan_response_res.getResult().getName();

            txtCreditscore.setVisibility(View.VISIBLE);
            ivdownload.setVisibility(View.VISIBLE);
            llcreditscore.setVisibility(View.VISIBLE);


            if (score.isEmpty()) {
                txtCreditscore.setText("");
                txtCreditscore.setVisibility(View.GONE);

                ivdownload.setVisibility(View.GONE);
                llcreditscore.setVisibility(View.GONE);
            } else {


                txtCreditscore.setText("Your Credit Score  " + equifax_personalloan_response_res.getResult().getScore());

                txtCreditscore.setVisibility(View.VISIBLE);
                ivdownload.setVisibility(View.VISIBLE);
                llcreditscore.setVisibility(View.VISIBLE);

                ivdownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                        downloadPdf(url_score, "Credit Score");
                    }
                });
            }

        }

    }

    private void downloadPdf(String url, String name) {
        Toast.makeText(getActivity(), "Download started..", Toast.LENGTH_LONG).show();
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(url));
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + ".pdf");
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        r.setMimeType(MimeTypeMap.getFileExtensionFromUrl(url));
        DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
        dm.enqueue(r);
    }

    private void getequifax() {

        equifax_personalloan_request equifaxrequest = new equifax_personalloan_request();

        equifaxrequest.setFirstName("");
        equifaxrequest.setMiddleName("");
        equifaxrequest.setLastName("");
        String[] name = personalLoanRequest.getApplicantNme().toUpperCase().split(" ");

        if (name.length > 2) {
            for (int i = 0; i < name.length; i++) {
                if (i == 0) {
                    equifaxrequest.setFirstName(name[i]);
                }
                if (i == 1) {
                    equifaxrequest.setMiddleName(name[i]);
                }
                if (i == name.length - 1) {
                    equifaxrequest.setLastName(name[i]);
                }
            }
        } else {
            for (int i = 0; i < name.length; i++) {
                if (i == 0) {
                    equifaxrequest.setFirstName(name[i]);
                }
                if (i == 1) {
                    equifaxrequest.setLastName(name[i]);
                }
            }
        }


        equifaxrequest.setMobilePhone(personalLoanRequest.getContact());//

        if (personalLoanRequest.getApplicantDOB() != null) {

            equifaxrequest.setDOB(getYYYYMMDDPattern(personalLoanRequest.getApplicantDOB()));
        }
        equifaxrequest.setDOB(personalLoanRequest.getApplicantDOB());
        equifaxrequest.setPANId(personalLoanRequest.getpanno());
        equifaxrequest.setMaritalStatus(personalLoanRequest.getMaritalStatus());//

        if (personalLoanRequest.getApplicantGender() != null) {

            if (personalLoanRequest.getApplicantGender().toUpperCase().equals("F")) {
                equifaxrequest.setGender("2");
            } else {
                equifaxrequest.setGender("1");
            }

        }


        equifaxrequest.setAddressLine(personalLoanRequest.getAddressLine());

        equifaxrequest.setAddressType(personalLoanRequest.getAddressType());
        equifaxrequest.setState(personalLoanRequest.getState());
        equifaxrequest.setCity(personalLoanRequest.getCity());
        equifaxrequest.setLocality1(personalLoanRequest.getLocality1());
        equifaxrequest.setPostal(personalLoanRequest.getPostal());
        equifaxrequest.setPhoneType("M");
        equifaxrequest.setAccountNumber("");

        new PersonalLoanController(this.getActivity()).getPLequifax(equifaxrequest, this);
    }

    /*
        private void setRBCustomerData() {




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

        }*/
    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        if (getActivity() != null) {
            if (t.getMessage() == null || t.getMessage().isEmpty()) {
            } else {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        bindQuotes_NoData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivllEdit) {


            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("PERSONAL LOAN : PERSONAL LOAN QUOTES  EDIT"), Constants.PERSONA_LOAN), null);

            MyApplication.getInstance().trackEvent(Constants.PERSONA_LOAN, "Clicked", "PERSONAL LOAN QUOTES EDIT");


            ((PLMainActivity) getActivity()).redirectInput(fmPersonalLoanRequest);
        } else if (v.getId() == R.id.ivShare) {
            if (getPersonalLoanResponse != null) {

                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("PERSONAL LOAN : PERSONAL LOAN QUOTES  SHARE"), Constants.PERSONA_LOAN), null);

                MyApplication.getInstance().trackEvent(Constants.PERSONA_LOAN, "Clicked", "PERSONAL LOAN QUOTES SHARE");

                Intent intent = new Intent(getActivity(), ShareQuoteActivity.class);
                intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "PL_ALL_QUOTE");
                intent.putExtra("RESPONSE", getPersonalLoanResponse);
                intent.putExtra("NAME", personalLoanRequest.getApplicantNme());
                startActivity(intent);
            }
        }
    }
}
