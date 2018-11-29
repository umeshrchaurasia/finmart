package com.datacomp.magicfinmart.share_data;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.erploan.ErpLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.ShareMessageResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareDataFragment extends BaseFragment implements IResponseSubcriber {

    RecyclerView recyclerShareMessage;
    ShareMessageAdapter mAdapter;
    List<ShareMessageResponse.LstShareMessageEntity> listShareMessag;

    public ShareDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_message, container, false);
        initilize(view);
        return view;
    }

    private void initilize(View view) {


        recyclerShareMessage = (RecyclerView) view.findViewById(R.id.recyclerShareMSG);
        recyclerShareMessage.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerShareMessage.setLayoutManager(mLayoutManager);
        //  listShareMessag = new ArrayList<DisplayEmployeePlanEntity>();
    }

    @Override
    public void onResume() {
        super.onResume();
        //server hit to fetch latest data

        showDialog();
        new ErpLoanController(getActivity()).getShareData(this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof ShareMessageResponse) {
            if (((ShareMessageResponse) response).getStatus_Id() == 0) {
                if (((ShareMessageResponse) response).getResult().getLstMsgLnkDtls() != null) {
                    listShareMessag = ((ShareMessageResponse) response).getResult().getLstMsgLnkDtls();
                    mAdapter = new ShareMessageAdapter(ShareDataFragment.this, listShareMessag);
                    recyclerShareMessage.setAdapter(mAdapter);
                } else {
                    recyclerShareMessage.setAdapter(null);
                }
            } else {
                recyclerShareMessage.setAdapter(null);
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        recyclerShareMessage.setAdapter(null);
    }

    /*@Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof ShareMessageResponse) {
            if (((ShareMessageResponse) response).getStatus_Id() == 0) {
                if (((ShareMessageResponse) response).getResult().getLstMsgLnkDtls() != null) {
                    listShareMessag = ((ShareMessageResponse) response).getResult().getLstMsgLnkDtls();
                    mAdapter = new ShareMessageAdapter(ShareDataFragment.this, listShareMessag);
                    recyclerShareMessage.setAdapter(mAdapter);
                }
            } else {
                recyclerShareMessage.setAdapter(null);
                //Snackbar.make(et_date, "No data available", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        recyclerShareMessage.setAdapter(null);
    }*/
}
