package com.datacomp.magicfinmart.transactionhistory;

import android.os.Bundle;

import android.support.design.widget.Snackbar;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.pendingcases.PendingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TransctionHistory;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TransctionHistoryResponse;

public class nav_transactionhistoryActivity extends BaseActivity implements IResponseSubcriber {

    DBPersistanceController databaseController;

    RecyclerView rvMyLead;
    Mytransactionhistorydapter mAdapter;
    List<TransctionHistory> myLeadList;LoginResponseEntity loginEntity;
    UserConstantEntity userConstantEntity;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    View incl_nav;
    int page = 1;
    long totalItems;
    String empCode;
    String POSPNO="";
    TextView txtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_transactionhistory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Transaction History");

        init_widgets();
        databaseController = new DBPersistanceController(nav_transactionhistoryActivity.this);
        loginEntity = databaseController.getUserData();
        userConstantEntity = databaseController.getUserConstantsData();
        empCode= ""+loginEntity.getFBAId();

        POSPNO=""+userConstantEntity.getPospsendid();
     //   empCode="232";
        if(POSPNO.equals("5"))
        {
            txtMessage.setVisibility(View.VISIBLE);
            incl_nav.setVisibility(View.GONE);
            txtMessage.setText("This Utility  is only available to Registered POSP");
        }else {
            txtMessage.setVisibility(View.GONE);
            txtMessage.setText("");
            incl_nav.setVisibility(View.VISIBLE);
            new PendingController(this).gettransactionhistory(empCode, "1", this);
        }


    }

    private void init_widgets() {
        incl_nav = (View)findViewById(R.id.incl_nav);
        txtMessage=(TextView)findViewById(R.id.txtMessage);
        rvMyLead = (RecyclerView) findViewById(R.id.rvhistory);
        rvMyLead.setHasFixedSize(true);
        myLeadList = new ArrayList<TransctionHistory>();
        mAdapter = new Mytransactionhistorydapter(nav_transactionhistoryActivity.this, myLeadList);
        rvMyLead.setAdapter(mAdapter);

       addListener();
    }
    private void addListener() {


        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(nav_transactionhistoryActivity.this);
        rvMyLead.setLayoutManager(mLayoutManager);

        rvMyLead.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findLastVisibleItemPosition();
                    Log.v("onScrolled", "visibleItemCount = " + visibleItemCount + " pastVisiblesItems = " + pastVisiblesItems + " totalItemCount =" + totalItemCount);
                    if (loading) {
                        if (totalItemCount <= (pastVisiblesItems + visibleItemCount)) {
                            loading = false;
                            Log.v("onScrolled", "Last Item Wow !");

                            String strPage = String.valueOf(++page);

                            if (totalItemCount <= totalItems) {
                                Toast.makeText(nav_transactionhistoryActivity.this, totalItemCount + "/" + totalItems, Toast.LENGTH_SHORT).show();
                                showDialog();
                            //    new LeadTransferControl().getLeadTransferDetails(empCode, strPage, MyLeadFragment.this);
                                new PendingController(nav_transactionhistoryActivity.this).gettransactionhistory(empCode,strPage, nav_transactionhistoryActivity.this);


                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof TransctionHistoryResponse) {

            if (response.getStatusNo() == 0) {
                if (((TransctionHistoryResponse) response).getMasterData() != null) {
                    myLeadList.addAll(((TransctionHistoryResponse) response).getMasterData());
                    mAdapter.notifyDataSetChanged();
                    loading = true;
                    if (page == 1) {

                        totalItems = ((TransctionHistoryResponse) response).getMasterData().size();
                      //  empTeamRptLstEntities = ((LeadTransferDispResponse) response).getResult().getEmpTeamRptLst();
                    }
                }

            } else {
                loading = false;
                rvMyLead.setAdapter(null);
                Snackbar.make(rvMyLead, "No data available", Snackbar.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Snackbar.make(rvMyLead, t.getMessage(), Snackbar.LENGTH_SHORT).show();

    }
}
