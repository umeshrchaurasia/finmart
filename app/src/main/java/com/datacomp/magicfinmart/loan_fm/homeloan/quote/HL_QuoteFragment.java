package com.datacomp.magicfinmart.loan_fm.homeloan.quote;

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
import com.datacomp.magicfinmart.loan_fm.homeloan.ActivityTabsPagerAdapter_HL;
import com.datacomp.magicfinmart.loan_fm.homeloan.HomeLoan_QuoteAdapter;
import com.datacomp.magicfinmart.loan_fm.homeloan.addquote.HLMainActivity;
import com.datacomp.magicfinmart.utility.Constants;

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
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmSaveQuoteHomeLoanResponse;


/**
 * Created by IN-RB on 19-01-2018.
 */

public class HL_QuoteFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriberFM {

    public static final String FROM_QUOTE = "hl_from_quote";
    FloatingActionButton hlAddQuote;
    RecyclerView rvhlQuoteList;
    HomeLoan_QuoteAdapter homeLoan_QuoteAdapter;
    List<FmHomeLoanRequest> mQuoteList;

    FmHomeLoanRequest removeQuoteEntity;

    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;

    public HL_QuoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hl_quote, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        mQuoteList = new ArrayList<>();
        if (getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_HL.QUOTE_LIST) != null) {
            mQuoteList = getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_HL.QUOTE_LIST);

        }
        homeLoan_QuoteAdapter = new HomeLoan_QuoteAdapter(HL_QuoteFragment.this, mQuoteList);
        rvhlQuoteList.setAdapter(homeLoan_QuoteAdapter);

        rvhlQuoteList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                "HML", this);

    }

    private void initView(View view) {

        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        tvSearch = (TextView) view.findViewById(R.id.tvSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        hlAddQuote = (FloatingActionButton) view.findViewById(R.id.hlAddQuote);

        rvhlQuoteList = (RecyclerView) view.findViewById(R.id.rvQuoteList);
        rvhlQuoteList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvhlQuoteList.setLayoutManager(layoutManager);


        hlAddQuote.setOnClickListener(this);
    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    public void redirectQuoteHL(FmHomeLoanRequest request) {

        new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("HOME LOAN : HOME LOAN QUOTES  DISPLAY"), Constants.HOME_LOAN_QUOTES), null);

        MyApplication.getInstance().trackEvent( Constants.HOME_LOAN_QUOTES,"Clicked","HOME LOAN QUOTES DISPLAY");



        Intent intent = new Intent(getActivity(), HLMainActivity.class);
        intent.putExtra(FROM_QUOTE, request);
        startActivity(intent);

    }

    public void removeQuoteHL(FmHomeLoanRequest entity) {

        removeQuoteEntity = entity;
        showDialog("Please wait.. removing quote..");
        new MainLoanController(getContext()).getdelete_loanrequest("" + entity.getLoan_requestID(), this);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.hlAddQuote) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("HOME LOAN : HOME LOAN QUOTES ADD WITH FLAOTING BUTTON"), Constants.HOME_LOAN), null);

            MyApplication.getInstance().trackEvent(Constants.HOME_LOAN, "Clicked", "HOME LOAN QUOTES ADD WITH FLAOTING BUTTON");


            startActivity(new Intent(getActivity(), HLMainActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("HOME LOAN : HOME LOAN QUOTES  SEARCH"), Constants.HOME_LOAN), null);

            MyApplication.getInstance().trackEvent(Constants.HOME_LOAN, "Clicked", "HOME LOAN QUOTES SEARCH");


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
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("HOME LOAN : HOME LOAN QUOTES WITH TEXT BUTTON"), Constants.HOME_LOAN), null);

            MyApplication.getInstance().trackEvent(Constants.HOME_LOAN, "Clicked", "HOME LOAN QUOTES WITH TEXT BUTTON");

            startActivity(new Intent(getActivity(), HLMainActivity.class));

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
                homeLoan_QuoteAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {

        cancelDialog();
        if (response instanceof FmSaveQuoteHomeLoanResponse) {
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

        homeLoan_QuoteAdapter.refreshAdapter(mQuoteList);
        homeLoan_QuoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }
}
