package com.datacomp.magicfinmart.pendingcases;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.popup.LeadInfoPopupActivity;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.pendingcases.PendingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PendingCasesEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseInsLoanResponse;

/**
 * Created by Rajeev Ranjan on 03/09/2018.
 */

public class PendingCaseFragment extends BaseFragment implements IResponseSubcriber {


    public static String TYPE = "case_type";
    RecyclerView rvPendingCasesList;
    PendingCasesAdapter mAdapter;
    PendingCasesEntity removePendingCasesEntity;
    List<PendingCasesEntity> mPendingList;
    boolean isHit = false;
    int type = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pendingcase, container, false);
        init_widgets(view);
        //fetchPendingCases(0);
        if (getArguments().getInt(TYPE) != 0) {
            type = getArguments().getInt(TYPE);
        }
        if (getArguments().getParcelableArrayList(PendingPagerAdapter.INSURANCE_LIST) != null) {
            mPendingList = getArguments().getParcelableArrayList(PendingPagerAdapter.INSURANCE_LIST);
        }
        if (getArguments().getParcelableArrayList(PendingPagerAdapter.LOAN_LIST) != null) {
            mPendingList = getArguments().getParcelableArrayList(PendingPagerAdapter.LOAN_LIST);
        }
        mAdapter = new PendingCasesAdapter(PendingCaseFragment.this, mPendingList,type);
        rvPendingCasesList.setAdapter(mAdapter);

        rvPendingCasesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();


                if (lastCompletelyVisibleItemPosition == mPendingList.size() - 1) {
                    if (!isHit) {
                        isHit = true;
                        fetchPendingCases(mPendingList.size());

                    }
                }
            }
        });
        return view;
    }

    private void init_widgets(View view) {
        rvPendingCasesList = (RecyclerView) view.findViewById(R.id.rvPendingCasesList);
        rvPendingCasesList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvPendingCasesList.setLayoutManager(layoutManager);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        List<PendingCasesEntity> list = new ArrayList<>();
        if (response instanceof PendingCaseInsLoanResponse) {
            if (type == 1) {
                list = ((PendingCaseInsLoanResponse) response).getMasterData().getInsurance();
            } else if (type == 2) {
                list = ((PendingCaseInsLoanResponse) response).getMasterData().getLoan();
            }
            if (list.size() > 0) {
                isHit = false;
                // Toast.makeText(this, "fetching more...", Toast.LENGTH_SHORT).show();

                for (PendingCasesEntity entity : list) {
                    if (!mPendingList.contains(entity)) {
                        mPendingList.add(entity);
                    }
                }
            }
            //mAdapter = new VehicleDetailsAdapter(this, mPendingList);
            //rvPendingCasesList.setAdapter(mAdapter);
        } else if (response instanceof PendingCaseDeleteResponse) {
            mPendingList.remove(removePendingCasesEntity);
            //list=mPendingList;
            // mAdapter.refreshAdapter(mPendingList);
            // mAdapter.notifyDataSetChanged();
        }

        //mPendingList =list;
        mAdapter.refreshAdapter(mPendingList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void fetchPendingCases(int count) {
        if (count == 0)
            showDialog("Please wait.. loading cases");


        new PendingController(getActivity()).getPendingCasesWithType(count, type,
                String.valueOf(new DBPersistanceController(getActivity()).getUserData().getFBAId()), this);
    }

    public void deletePendingcases(PendingCasesEntity entity) {
        removePendingCasesEntity = entity;
        showDialog("Please wait,Removing case..");
        new PendingController(getActivity()).deletePending(entity.getQuotetype(), entity.getId(), this);
    }

    public void openLeadDetailPopUp(String AppNumb) {
        Intent intent = new Intent(getActivity(), LeadInfoPopupActivity.class);
        intent.putExtra("APPLICATION_NUMBER",AppNumb);
        startActivityForResult(intent, Utility.LEAD_REQUEST_CODE);
    }
}
