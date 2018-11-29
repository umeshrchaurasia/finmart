package com.datacomp.magicfinmart.motor.privatecar.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.motor.privatecar.activity.InputQuoteBottmActivity;
import com.datacomp.magicfinmart.motor.privatecar.adapter.ActivityTabsPagerAdapter;
import com.datacomp.magicfinmart.motor.privatecar.adapter.MotorApplicationAdapter;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quoteapplication.QuoteApplicationController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ApplicationListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteApplicationResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotorApplicationFragment extends BaseFragment implements View.OnClickListener, BaseFragment.PopUpListener, IResponseSubcriber {
    public static final String FROM_APPLICATION = "from_application";

    RecyclerView rvApplicationList;
    MotorApplicationAdapter motorApplicationAdapter;
    List<ApplicationListEntity> mApplicationList;
    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;

    public MotorApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_motor_application, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        registerPopUp(this);
        mApplicationList = new ArrayList<>();

        if (getArguments().getParcelableArrayList(ActivityTabsPagerAdapter.APPLICATION_LIST) != null) {
            mApplicationList = getArguments().getParcelableArrayList(ActivityTabsPagerAdapter.APPLICATION_LIST);
        }
        rvApplicationList.setAdapter(null);
        motorApplicationAdapter = new MotorApplicationAdapter(MotorApplicationFragment.this, mApplicationList);
        rvApplicationList.setAdapter(motorApplicationAdapter);


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

    public void redirectApplication(ApplicationListEntity entity) {
        if (entity.getMotorRequestEntity().getPBStatus().toLowerCase().equals("a")) {
            startActivity(new Intent(getActivity(), InputQuoteBottmActivity.class).putExtra(FROM_APPLICATION, entity));
        } else if (entity.getMotorRequestEntity().getPBStatus().toLowerCase().equals("am")) {
            openPopUp(etSearch, "Message", "Payment link is already sent to customer", "OK", true);
        } else if (entity.getMotorRequestEntity().getPBStatus().toLowerCase().equals("ps")) {
            openPopUp(etSearch, "Message", "Already payment done for this crn.", "OK", true);
        } else if (entity.getMotorRequestEntity().getPBStatus().toLowerCase().equals("pf")) {
            openPopUp(etSearch, "Message", "Payment link is already sent to customer", "OK", true);
        } else {
            openPopUp(etSearch, "Message", "", "OK", true);
        }
    }

    public void fetchMoreQuotes(int count) {

        new QuoteApplicationController(getActivity()).getQuoteAppList(count, 2, "", "",
                new DBPersistanceController(getActivity()).getUserData().getFBAId(),
                1,
                "",
                this);
    }

    //region server response

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof QuoteApplicationResponse) {
            List<ApplicationListEntity> list = ((QuoteApplicationResponse) response).getMasterData().getApplication();

            if (list.size() > 0) {
                isHit = false;

                for (ApplicationListEntity entity : list) {
                    if (!mApplicationList.contains(entity)) {
                        mApplicationList.add(entity);
                    }
                }
            }

            motorApplicationAdapter.refreshAdapter(mApplicationList);
            motorApplicationAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }

    //endregion

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
                motorApplicationAdapter.getFilter().filter(s);
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
            if (etSearch.getVisibility() == View.INVISIBLE) {
                etSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            }

        }
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }
}
