package com.datacomp.magicfinmart.term.quoteapp;


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
import com.datacomp.magicfinmart.term.termselection.TermActivityTabsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.term.TermInsuranceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermFinmartRequest;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermQuoteApplicationResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermApplicationListFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriber {

    List<TermFinmartRequest> listApplication;
    TermApplicationAdapter mAdapter;
    RecyclerView rvTermApplication;
    int compId = 1000;
    ImageView ivSearch, ivAdd;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    boolean isHit = false;

    public TermApplicationListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_term_application, container, false);
        initView(view);
        setListener();
        setTextWatcher();
        listApplication = new ArrayList<>();
        if (getArguments().getParcelableArrayList(TermActivityTabsPagerAdapter.TERM_APPLICATION_LIST) != null) {
            listApplication = getArguments().getParcelableArrayList(TermActivityTabsPagerAdapter.TERM_APPLICATION_LIST);
        }
        compId = ((TermQuoteApplicationActivity) getActivity()).getCompId();
        mAdapter = new TermApplicationAdapter(this, listApplication);
        rvTermApplication.setAdapter(mAdapter);
        rvTermApplication.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();


                if (lastCompletelyVisibleItemPosition == listApplication.size() - 1) {
                    if (!isHit) {
                        isHit = true;
                        fetchMoreQuotes(listApplication.size());

                    }
                }
            }
        });
        return view;
    }

    private void initView(View view) {

        ivSearch = (ImageView) view.findViewById(R.id.ivSearch);
        ivAdd = (ImageView) view.findViewById(R.id.ivAdd);
        tvAdd = (TextView) view.findViewById(R.id.tvAdd);
        tvSearch = (TextView) view.findViewById(R.id.tvSearch);
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        rvTermApplication = (RecyclerView) view.findViewById(R.id.rvTermApplication);
        rvTermApplication.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvTermApplication.setLayoutManager(layoutManager);
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

    public void fetchMoreQuotes(int count) {
        //showDialog("Fetching.., Please wait.!");

        if (compId == 39) {
            new TermInsuranceController(getActivity()).getTermQuoteApplicationList(39, count, "2", this);
        } else if (compId == 28) {
            new TermInsuranceController(getActivity()).getTermQuoteApplicationList(28, count, "2", this);
        } else if (compId == 0) {
            new TermInsuranceController(getActivity()).getTermQuoteApplicationList(0, count, "2", this);
        } else if (compId == 1) {
            new TermInsuranceController(getActivity()).getTermQuoteApplicationList(1, count, "2", this);
        } else if (compId == 43) {
            new TermInsuranceController(getActivity()).getTermQuoteApplicationList(43, count, "2", this);
        }
        //new TermInsuranceController(getActivity()).getTermQuoteApplicationList(39, count, "2", this);
    }

    private void setTextWatcher() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof TermQuoteApplicationResponse) {
            List<TermFinmartRequest> list = ((TermQuoteApplicationResponse) response).getMasterData().getApplication();
            if (list.size() > 0) {
                isHit = false;
                //Toast.makeText(getActivity(), "fetching more...", Toast.LENGTH_SHORT).show();

                for (TermFinmartRequest entity : list) {
                    if (!listApplication.contains(entity)) {
                        listApplication.add(entity);
                    }
                }
            }


        }

        mAdapter.refreshAdapter(listApplication);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {

    }
}


