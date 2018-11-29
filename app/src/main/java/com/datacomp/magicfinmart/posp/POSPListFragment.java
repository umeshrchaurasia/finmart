package com.datacomp.magicfinmart.posp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ChildPospEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ChildPospResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class POSPListFragment extends BaseFragment implements IResponseSubcriber {

    RecyclerView rvPOSPList;
    FloatingActionButton fbAddPosp;
    List<ChildPospEntity> childPospEntityList;
    ChildPospAdapter childPospAdapter;

    public static final int REQUEST_CODE = 8765;

    public POSPListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posplist, container, false);
        init(view);
        fbAddPosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), AddPOSPUserActivity.class), REQUEST_CODE);
            }
        });
        getChildPospList();
        return view;
    }

    private void getChildPospList() {

        showDialog();
        new RegisterController(getActivity()).getChildPosp(this);
    }

    private void init(View view) {
        rvPOSPList = view.findViewById(R.id.rvPOSPList);
        rvPOSPList.setLayoutManager(new LinearLayoutManager(getActivity()));
        fbAddPosp = view.findViewById(R.id.fbAddPosp);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof ChildPospResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                childPospEntityList = ((ChildPospResponse) response).getMasterData();
                childPospAdapter = new ChildPospAdapter(this, childPospEntityList);
                rvPOSPList.setAdapter(childPospAdapter);
            }
        } else {
            Toast.makeText(getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            getChildPospList();
        }
    }
}
