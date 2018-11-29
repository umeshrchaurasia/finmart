package com.datacomp.magicfinmart.loan_fm.laploan.quote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.laploan.ActivityTabsPagerAdapter_LAP;
import com.datacomp.magicfinmart.loan_fm.laploan.LapLoan_QuoteAdapter;
import com.datacomp.magicfinmart.loan_fm.laploan.addquote.LAPMainActivity;
import com.datacomp.magicfinmart.utility.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan.MainLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmHomelLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmSaveQuotePersonalLoanResponse;

/**
 * Created by IN-RB on 22-01-2018.
 */

public class LAP_QuoteFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriberFM {
    public static final String FROM_QUOTE = "hl_from_quote";
    FloatingActionButton lapAddQuote;

    RecyclerView rvlapQuoteList;
    LapLoan_QuoteAdapter lapLoan_QuoteAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<FmHomeLoanRequest> mQuoteList;
    FmHomeLoanRequest removeQuoteEntity;

    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;


    public LAP_QuoteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lap_quote, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        mQuoteList = new ArrayList<>();
        if (getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_LAP.QUOTE_LIST) != null) {
            mQuoteList = getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_LAP.QUOTE_LIST);

        }

        lapLoan_QuoteAdapter = new LapLoan_QuoteAdapter(LAP_QuoteFragment.this, mQuoteList);
        rvlapQuoteList.setAdapter(lapLoan_QuoteAdapter);

        rvlapQuoteList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();


                if (lastCompletelyVisibleItemPosition == mQuoteList.size() - 1) {
                    if (!isHit) {
                        isHit = true;
                        fetchQuoteApplication(mQuoteList.size());

                    }
                }
            }
        });
        return view;
    }

    private void fetchQuoteApplication(int count) {
        new MainLoanController(getActivity()).getHLQuoteApplicationData(count, 1, String.valueOf(
                new DBPersistanceController(getActivity()).getUserData().getFBAId()),
                "LAP", this);

    }

    private void initView(View view) {

        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        tvSearch = (TextView) view.findViewById(R.id.tvSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        lapAddQuote = (FloatingActionButton) view.findViewById(R.id.lapAddQuote);

        rvlapQuoteList = (RecyclerView) view.findViewById(R.id.rvQuoteList);
        rvlapQuoteList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvlapQuoteList.setLayoutManager(layoutManager);


        lapAddQuote.setOnClickListener(this);
    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    public void redirectQuoteLAP(FmHomeLoanRequest request) {
        Intent intent = new Intent(getActivity(), LAPMainActivity.class);
        intent.putExtra(FROM_QUOTE, request);
        startActivity(intent);

    }

    public void removeQuoteLAP(FmHomeLoanRequest entity) {

        removeQuoteEntity = entity;
        showDialog("Please wait.. removing quote..");
        new MainLoanController(getContext()).getdelete_loanrequest("" + entity.getLoan_requestID(), this);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.lapAddQuote) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("LAP LOAN : LAP LOAN QUOTES ADD WITH FLAOTING BUTTON"), Constants.LAP), null);
            MyApplication.getInstance().trackEvent(Constants.LAP, "Clicked", "LAP LOAN QUOTES ADD WITH FLAOTING BUTTON");

            startActivity(new Intent(getActivity(), LAPMainActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("LAP LOAN : LAP LOAN QUOTES SEARCH"), Constants.LAP), null);
            MyApplication.getInstance().trackEvent(Constants.LAP, "Clicked", "LAP LOAN QUOTES SEARCH");


            InputMethodManager inputMethodManager =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    etSearch.getApplicationWindowToken(),
                    InputMethodManager.SHOW_FORCED, 0);
            if (etSearch.getVisibility() == View.INVISIBLE) {
                etSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            }


        } else if (i == R.id.ivAdd || i == R.id.tvAdd) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("LAP LOAN : LAP LOAN QUOTES ADD WITH TEXT BUTTON"), Constants.LAP), null);
            MyApplication.getInstance().trackEvent(Constants.LAP, "Clicked", "LAP LOAN QUOTES ADD WITH TEXT BUTTON");


            startActivity(new Intent(getActivity(), LAPMainActivity.class));

        }
    }

    public void callnumber(String mobNumber) {
        dialNumber(mobNumber);
    }


    private void setTextWatcher() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lapLoan_QuoteAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {
        cancelDialog();
        if (response instanceof FmSaveQuotePersonalLoanResponse) {
            if (response.getStatusNo() == 0) {
                mQuoteList.remove(removeQuoteEntity);

            }
        } else if (response instanceof FmHomelLoanResponse) {
            List<FmHomeLoanRequest> list = ((FmHomelLoanResponse) response).getMasterData().getQuote();
            if (list.size() > 0) {
                isHit = false;

                for (FmHomeLoanRequest entity : list) {
                    if (!mQuoteList.contains(entity)) {
                        mQuoteList.add(entity);
                    }
                }
            }
        }

        lapLoan_QuoteAdapter.refreshAdapter(mQuoteList);
        lapLoan_QuoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }
}
