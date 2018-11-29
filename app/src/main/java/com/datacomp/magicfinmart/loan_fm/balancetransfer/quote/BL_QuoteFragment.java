package com.datacomp.magicfinmart.loan_fm.balancetransfer.quote;


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
import com.datacomp.magicfinmart.loan_fm.balancetransfer.ActivityTabsPagerAdapter_BL;
import com.datacomp.magicfinmart.loan_fm.balancetransfer.BalanceTransfer_QuoteAdapter;
import com.datacomp.magicfinmart.loan_fm.balancetransfer.addquote.BLMainActivity;
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
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmBalanceLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmSaveQuoteBLResponse;


public class BL_QuoteFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriberFM {
    public static final String FROM_QUOTEBL = "bl_from_quote";
    FloatingActionButton blAddQuote;

    RecyclerView rvBTQuoteList;
    BalanceTransfer_QuoteAdapter balanceTransfer_QuoteAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    List<FmBalanceLoanRequest> mQuoteList;

    FmBalanceLoanRequest removeQuoteEntity;

    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;

    public BL_QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bl__quote, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        mQuoteList = new ArrayList<>();
        if (getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_BL.QUOTE_LIST) != null) {
            mQuoteList = getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_BL.QUOTE_LIST);

        }
        balanceTransfer_QuoteAdapter = new BalanceTransfer_QuoteAdapter(BL_QuoteFragment.this, mQuoteList);
        rvBTQuoteList.setAdapter(balanceTransfer_QuoteAdapter);

        rvBTQuoteList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    private void fetchQuoteApplication(int size) {
        new MainLoanController(getContext()).getBLQuoteApplication(size, 1, String.valueOf(
                new DBPersistanceController(getActivity()).getUserData().getFBAId()),
                this);
    }

    private void initView(View view) {
        blAddQuote = (FloatingActionButton) view.findViewById(R.id.blAddQuote);

        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        tvSearch = (TextView) view.findViewById(R.id.tvSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);


        rvBTQuoteList = (RecyclerView) view.findViewById(R.id.rvQuoteList);
        rvBTQuoteList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvBTQuoteList.setLayoutManager(layoutManager);


        blAddQuote.setOnClickListener(this);
    }


    private void setListener() {
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    public void redirectQuoteBL(FmBalanceLoanRequest request) {
        Intent intent = new Intent(getActivity(), BLMainActivity.class);
        intent.putExtra(FROM_QUOTEBL, request);
        startActivity(intent);

    }

    public void removeQuoteBL(FmBalanceLoanRequest entity) {

        removeQuoteEntity = entity;
        showDialog("Please wait.. removing quote");
        new MainLoanController(getContext()).getdelete_balancerequest("" + entity.getBalanceTransferId(), this);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.blAddQuote) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("BALANCE TRANSFER LOAN : BALANCE TRANSFER LOAN QUOTES ADD WITH FLAOTING BUTTON"), Constants.BALANCE_TRANSFER), null);

            MyApplication.getInstance().trackEvent(Constants.BALANCE_TRANSFER, "Clicked", "BALANCE TRANSFER LOAN QUOTES ADD WITH FLAOTING BUTTON");

            startActivity(new Intent(getActivity(), BLMainActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("BALANCE TRANSFER LOAN : BALANCE TRANSFER LOAN QUOTES SEARCH"), Constants.BALANCE_TRANSFER), null);

            MyApplication.getInstance().trackEvent(Constants.BALANCE_TRANSFER, "Clicked", "BALANCE TRANSFER LOAN QUOTES SEARCH");

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
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("BALANCE TRANSFER LOAN : BALANCE TRANSFER LOAN QUOTES ADD WITH TEXT BUTTON"), Constants.BALANCE_TRANSFER), null);

            MyApplication.getInstance().trackEvent(Constants.BALANCE_TRANSFER, "Clicked", "BALANCE TRANSFER LOAN QUOTES ADD WITH TEXT BUTTON");

            startActivity(new Intent(getActivity(), BLMainActivity.class));

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
                balanceTransfer_QuoteAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {

        cancelDialog();
        if (response instanceof FmSaveQuoteBLResponse) {
            if (response.getStatusNo() == 0) {
                mQuoteList.remove(removeQuoteEntity);

            }
        } else if (response instanceof FmBalanceLoanResponse) {
            if (((FmBalanceLoanResponse) response).getMasterData() != null) {

                List<FmBalanceLoanRequest> list = ((FmBalanceLoanResponse) response).getMasterData().getQuote();

                if (list.size() > 0) {
                    isHit = false;
                    for (FmBalanceLoanRequest entity : list) {
                        if (!mQuoteList.contains(entity)) {
                            mQuoteList.add(entity);
                        }
                    }
                }

            }

        }

        balanceTransfer_QuoteAdapter.refreshAdapter(mQuoteList);
        balanceTransfer_QuoteAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }
}
