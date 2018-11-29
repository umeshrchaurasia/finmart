package com.datacomp.magicfinmart.motor.twowheeler.fragment;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.motor.twowheeler.activity.BikeAddQuoteActivity;
import com.datacomp.magicfinmart.motor.twowheeler.adapter.BikeActivityTabsPagerAdapter;
import com.datacomp.magicfinmart.motor.twowheeler.adapter.BikeQuoteTabAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quoteapplication.QuoteApplicationController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.QuoteListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteAppUpdateDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteApplicationResponse;

/**
 * Created by Rajeev Ranjan on 02/02/2018.
 */

public class BikeQuoteTabFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriber {

    public static final String FROM_QUOTE_BIKE = "from_quote";
    FloatingActionButton btnAddQuote;
    RecyclerView rvQuoteList;
    BikeQuoteTabAdapter bikeQuoteTabAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<QuoteListEntity> mQuoteList;
    QuoteListEntity removeQuoteEntity;
    ImageView ivSearch;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;


    public BikeQuoteTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bike_fragment_quote_tab, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        mQuoteList = new ArrayList<>();
        if (getArguments().getParcelableArrayList(BikeActivityTabsPagerAdapter.QUOTE_LIST) != null) {
            mQuoteList = getArguments().getParcelableArrayList(BikeActivityTabsPagerAdapter.QUOTE_LIST);
        }

        rvQuoteList.setAdapter(null);
        bikeQuoteTabAdapter = new BikeQuoteTabAdapter(BikeQuoteTabFragment.this, mQuoteList);
        rvQuoteList.setAdapter(bikeQuoteTabAdapter);

        rvQuoteList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();


                if (lastCompletelyVisibleItemPosition == mQuoteList.size() - 1) {
                    if (!isHit) {
                        isHit = true;
                        fetchMoreQuotes(mQuoteList.size());

                    }
                }
            }
        });

        return view;
    }


    public void fetchMoreQuotes(int count) {
        //showDialog("Fetching.., Please wait.!");


        new QuoteApplicationController(getActivity()).getQuoteAppList(count, 1, "", "",
                new DBPersistanceController(getActivity()).getUserData().getFBAId(),
                10,
                "",
                this);
    }

    //redirect to input quote bottom
    public void redirectToInputQuote(QuoteListEntity entity) {
        startActivity(new Intent(getActivity(), BikeAddQuoteActivity.class).putExtra(FROM_QUOTE_BIKE, entity));
    }

    private void initView(View view) {
        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        //ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        tvSearch = (TextView) view.findViewById(R.id.tvSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        btnAddQuote = (FloatingActionButton) view.findViewById(R.id.fbAddQuote);
        rvQuoteList = (RecyclerView) view.findViewById(R.id.rvQuoteList);
        rvQuoteList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvQuoteList.setLayoutManager(layoutManager);
        btnAddQuote.setOnClickListener(this);

    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        // ivAdd.setOnClickListener(this);
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
                bikeQuoteTabAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void removeQuote(QuoteListEntity entity) {

        removeQuoteEntity = entity;
        showDialog("Please wait.. removing quote");
        new QuoteApplicationController(getContext()).deleteQuote("" + entity.getVehicleRequestID(),
                this);

    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.fbAddQuote) {
            startActivity(new Intent(getActivity(), BikeAddQuoteActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            if (etSearch.getVisibility() == View.INVISIBLE) {
                etSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            }


        } else if (i == R.id.ivAdd || i == R.id.tvAdd) {
            startActivity(new Intent(getActivity(), BikeAddQuoteActivity.class));

        }
    }


    //region server response

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof QuoteAppUpdateDeleteResponse) {
            if (response.getStatusNo() == 0) {
                mQuoteList.remove(removeQuoteEntity);
                bikeQuoteTabAdapter.refreshAdapter(mQuoteList);
                bikeQuoteTabAdapter.notifyDataSetChanged();
            }
        } else if (response instanceof QuoteApplicationResponse) {
            List<QuoteListEntity> list = ((QuoteApplicationResponse) response).getMasterData().getQuote();
            if (list.size() > 0) {
                isHit = false;
                for (QuoteListEntity entity : list) {
                    if (!mQuoteList.contains(entity)) {
                        mQuoteList.add(entity);
                    }
                }
            }


        }

        bikeQuoteTabAdapter.refreshAdapter(mQuoteList);
        bikeQuoteTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }

    //endregion
}
