package com.datacomp.magicfinmart.health.quoappfragment;


import android.app.Dialog;
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
import com.datacomp.magicfinmart.health.HealthActivityTabsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.health.HealthController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthApplication;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteAppResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthApplicationFragment extends BaseFragment implements View.OnClickListener,IResponseSubcriber, BaseFragment.PopUpListener {
    RecyclerView rvHealthApplicationList;
    HealthApplicationAdapter healthApplicationAdapter;
    List<HealthApplication> mApplicationList;

    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;

    public HealthApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_application, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        registerPopUp(this);
        mApplicationList = new ArrayList<>();

        if (getArguments().getParcelableArrayList(HealthActivityTabsPagerAdapter.HEALTH_APPLICATION_LIST) != null) {
            mApplicationList = getArguments().getParcelableArrayList(HealthActivityTabsPagerAdapter.HEALTH_APPLICATION_LIST);
        }
        healthApplicationAdapter = new HealthApplicationAdapter(HealthApplicationFragment.this, mApplicationList);
        rvHealthApplicationList.setAdapter(healthApplicationAdapter);

        rvHealthApplicationList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        //  showDialog("Fetching.., Please wait.!");
        new HealthController(getActivity()).getHealthQuoteApplicationList(size,2,
                String.valueOf(new DBPersistanceController(getActivity()).getUserData().getFBAId()),
                this);

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof HealthQuoteAppResponse) {
            List<HealthApplication> list = ((HealthQuoteAppResponse) response).getMasterData().getApplication();

            if (list.size() > 0) {
                isHit = false;
               // Toast.makeText(getActivity(), "fetching more...", Toast.LENGTH_SHORT).show();


                for (HealthApplication entity : list) {
                    if (!mApplicationList.contains(entity)) {
                        mApplicationList.add(entity);
                    }
                }
            }

            healthApplicationAdapter.refreshAdapter(mApplicationList);
            healthApplicationAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }

    private void initView(View view) {

        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        tvSearch = (TextView) view.findViewById(R.id.tvSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        rvHealthApplicationList = (RecyclerView) view.findViewById(R.id.rvHealthApplicationList);
        rvHealthApplicationList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvHealthApplicationList.setLayoutManager(layoutManager);
    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
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

    private void setTextWatcher() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                healthApplicationAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void redirectToQuote(HealthApplication entity) {

        if (entity.getHealthRequest().getPBStatus().toLowerCase().equals("a")) {
      /*      HealthQuote healthQuote = new HealthQuote();
            healthQuote.setFBAID(entity.getFBAID());
            healthQuote.setHealthRequest(entity.getHealthRequest());
            healthQuote.setCrn(entity.getCrn());
            healthQuote.setAgent_source(entity.getAgent_source());
            Intent intent = new Intent(getActivity(), HealthQuoteBottomTabsActivity.class);
            intent.putExtra(HealthQuoteListFragment.HEALTH_INPUT_FRAGMENT, healthQuote);
            startActivity(intent);*/
        } else if (entity.getHealthRequest().getPBStatus().toLowerCase().equals("am")) {
            openPopUp(etSearch, "Message", "Payment link is already sent to customer", "OK", true);
        } else if (entity.getHealthRequest().getPBStatus().toLowerCase().equals("ps")) {
            openPopUp(etSearch, "Message", "Already payment done for this crn.", "OK", true);
        } else if (entity.getHealthRequest().getPBStatus().toLowerCase().equals("pf")) {
            openPopUp(etSearch, "Message", "Payment link is already sent to customer", "OK", true);
        } else {
            openPopUp(etSearch, "Message", "Kindly contact customer care.", "OK", true);
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


