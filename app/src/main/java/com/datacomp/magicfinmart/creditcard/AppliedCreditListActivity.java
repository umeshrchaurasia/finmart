package com.datacomp.magicfinmart.creditcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.creditcard.CreditCardController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.AppliedCreditCardEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.AppliedCreditCardResponse;

public class AppliedCreditListActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    RecyclerView rvAppliedCreditCards;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    ImageView ivSearch;
    FloatingActionButton fbAddCreditCard;
    List<AppliedCreditCardEntity> mCreditCardEntityList;
    AppliedCreditCardsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_credit_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (new PrefManager(this).getIsRblCityMaster()) {
            new CreditCardController(this).getRblCityMaster(null);
        }

        init();
        setListener();
        setTextWatcher();
        mCreditCardEntityList = new ArrayList<>();
    }

    private void fetchCreditCards() {
        showDialog();
        new CreditCardController(this).getAppliedCreditCards(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchCreditCards();
    }

    private void init() {
        ivSearch = (ImageView) findViewById(R.id.ivSearch);

        tvAdd = (TextView) findViewById(R.id.tvAdd);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        fbAddCreditCard = (FloatingActionButton) findViewById(R.id.fbAddCreditCard);
        rvAppliedCreditCards = (RecyclerView) findViewById(R.id.rvAppliedCreditCards);
        rvAppliedCreditCards.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvAppliedCreditCards.setLayoutManager(layoutManager);

    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        fbAddCreditCard.setOnClickListener(this);
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
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.tvAdd || i == R.id.fbAddCreditCard) {
            startActivity(new Intent(this, CreditCardActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            if (etSearch.getVisibility() == View.INVISIBLE) {
                etSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            }

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof AppliedCreditCardResponse) {
            if (response.getStatusNo() == 0) {

                if(((AppliedCreditCardResponse) response).getMasterData().size() >0) {
                    mCreditCardEntityList = ((AppliedCreditCardResponse) response).getMasterData();
                    mAdapter = new AppliedCreditCardsAdapter(this, mCreditCardEntityList);
                    rvAppliedCreditCards.setAdapter(mAdapter);
                }else{
                    finish();
                    startActivity(new Intent(this, CreditCardActivity.class));

                }
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
