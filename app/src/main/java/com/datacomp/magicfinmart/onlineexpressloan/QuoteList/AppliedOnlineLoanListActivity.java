package com.datacomp.magicfinmart.onlineexpressloan.QuoteList;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.onlineexpressloan.BankLoanList.BankLoanListActivity.BankLoanListActivity;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller.ExpressLoanController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.ExpressQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.response.ExpressQuoteListResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;


public class AppliedOnlineLoanListActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    RecyclerView rvAppliedCreditCards;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    ImageView ivSearch;
    FloatingActionButton fbAddCreditCard;
    List<ExpressQuoteEntity> mExpressQuoteEntityList;
    AppliedOnlineAdapter mAdapter;
    DBPersistanceController dbPersistanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_online_loan_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbPersistanceController = new DBPersistanceController(this);
        init();
        setListener();
        setTextWatcher();
        mExpressQuoteEntityList = new ArrayList<>();
    }

    private void fetchCreditCards() {
        showDialog();
        new ExpressLoanController(this).getExpressQuoteList(String.valueOf(dbPersistanceController.getUserData().getFBAId()),this);
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
            startActivity(new Intent(this, BankLoanListActivity.class));

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
        if (response instanceof ExpressQuoteListResponse) {
            if (response.getStatusNo() == 0) {
                mExpressQuoteEntityList = ((ExpressQuoteListResponse) response).getMasterData();

                if(mExpressQuoteEntityList.size() != 0) {
                    mAdapter = new AppliedOnlineAdapter(this, mExpressQuoteEntityList);
                    rvAppliedCreditCards.setAdapter(mAdapter);
                }else{
                    finish();
                    startActivity(new Intent(this, BankLoanListActivity.class));
                }
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);

            finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
