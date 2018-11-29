package com.datacomp.magicfinmart.loan_fm.homeloan.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.homeloan.ActivityTabsPagerAdapter_HL;
import com.datacomp.magicfinmart.loan_fm.homeloan.HomeLoanApplicationAdapter;
import com.datacomp.magicfinmart.loan_fm.homeloan.HomeLoanDetailActivity;
import com.datacomp.magicfinmart.loan_fm.homeloan.loan_apply.HomeLoanApplyActivity;
import com.datacomp.magicfinmart.loan_fm.popup.LeadInfoPopupActivity;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan.MainLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.FmHomelLoanResponse;

/**
 * Created by IN-RB on 19-01-2018.
 */

public class HL_ApplicationFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriberFM {
    RecyclerView rvApplicationList;
    HomeLoanApplicationAdapter homeLoanApplicationAdapter;
    List<FmHomeLoanRequest> mApplicationList;
    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;


    public HL_ApplicationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hl_application, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        mApplicationList = new ArrayList<>();

        if (getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_HL.APPLICATION_LIST) != null) {
            mApplicationList = getArguments().getParcelableArrayList(ActivityTabsPagerAdapter_HL.APPLICATION_LIST);
        }

        homeLoanApplicationAdapter = new HomeLoanApplicationAdapter(HL_ApplicationFragment.this, mApplicationList);
        rvApplicationList.setAdapter(homeLoanApplicationAdapter);

        rvApplicationList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();


                if (lastCompletelyVisibleItemPosition == mApplicationList.size() - 1) {
                    if (!isHit) {
                        isHit = true;
                        fetchMoreQuotes(mApplicationList.size());

                    }
                }
            }
        });

        return view;
    }

    private void fetchMoreQuotes(int size) {

        new MainLoanController(getActivity()).getHLQuoteApplicationData(size, 2, String.valueOf(
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

        rvApplicationList = (RecyclerView) view.findViewById(R.id.rvApplicationList);
        rvApplicationList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvApplicationList.setLayoutManager(layoutManager);


    }

    public void redirectHomeLoanApply(String ApplNum) {
        Intent intent = new Intent(getActivity(), HomeLoanApplyActivity.class);
        intent.putExtra(Utility.HMLOAN_APPLICATION, ApplNum);
        intent.putExtra("TypePage", "HL");
        startActivity(intent);

    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    private void setTextWatcher() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                homeLoanApplicationAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvSearch || i == R.id.ivSearch) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    etSearch.getApplicationWindowToken(),
                    InputMethodManager.SHOW_FORCED, 0);
            if (etSearch.getVisibility() == View.INVISIBLE) {
                etSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            }


        }
    }

    public void callnumber(String mobNumber) {
        dialNumber(mobNumber);
    }

    @Override
    public void OnSuccessFM(APIResponseFM response, String message) {
        cancelDialog();

        if (response instanceof FmHomelLoanResponse) {

            if (response.getStatusNo() == 0) {
                List<FmHomeLoanRequest> list = ((FmHomelLoanResponse) response).getMasterData().getApplication();

                if (list.size() > 0) {
                    isHit = false;
                    for (FmHomeLoanRequest entity : list) {
                        if (!mApplicationList.contains(entity)) {
                            mApplicationList.add(entity);
                        }
                    }
                }

                homeLoanApplicationAdapter.refreshAdapter(mApplicationList);
                homeLoanApplicationAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void openLeadDetailPopUp(String AppNumb)
    {
        Intent intent = new Intent(getActivity(), LeadInfoPopupActivity.class);
        intent.putExtra("APPLICATION_NUMBER",AppNumb);
        startActivityForResult(intent,Utility.LEAD_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Utility.LEAD_REQUEST_CODE)
        {
            ((HomeLoanDetailActivity)getActivity()).infoPopUpVerify();

        }

    }

}
