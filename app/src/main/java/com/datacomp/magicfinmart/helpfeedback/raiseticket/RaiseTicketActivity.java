package com.datacomp.magicfinmart.helpfeedback.raiseticket;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.helpfeedback.raiseticket.adapter.RaiseTicketAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.zoho.ZohoController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.QuoteListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TicketEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TicketListResponse;

public class RaiseTicketActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {

    FloatingActionButton btnAddTicket;
    RecyclerView rvTicketList;
    RaiseTicketAdapter raiseTicketAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<QuoteListEntity> mQuoteList;
    QuoteListEntity removeQuoteEntity;
    ImageView ivSearch;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    List<TicketEntity> ticketEntities;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginResponseEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_ticket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbPersistanceController = new DBPersistanceController(this);
        loginResponseEntity = dbPersistanceController.getUserData();
        initView();
        setListener();
        setTextWatcher();
    }

    private void initView() {
        ivSearch = (ImageView) findViewById(R.id.ivSearch);

        tvAdd = (TextView) findViewById(R.id.tvAdd);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        btnAddTicket = (FloatingActionButton) findViewById(R.id.btnAddTicket);
        rvTicketList = (RecyclerView) findViewById(R.id.rvTicketList);
        rvTicketList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTicketList.setLayoutManager(layoutManager);
        btnAddTicket.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showDialog("Fetching Tickets..");
        new ZohoController(this).getListOfTickets("" + loginResponseEntity.getFBAId(), this);

    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof TicketListResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                ticketEntities = ((TicketListResponse) response).getMasterData();
                raiseTicketAdapter = new RaiseTicketAdapter(this, ticketEntities);
                rvTicketList.setAdapter(raiseTicketAdapter);
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvAdd || i == R.id.btnAddTicket) {
            startActivity(new Intent(this, AddTicketActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    etSearch.getApplicationWindowToken(),
                    InputMethodManager.SHOW_FORCED, 0);
                /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etSearch, InputMethodManager.SHOW_IMPLICIT);*/
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
                raiseTicketAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
