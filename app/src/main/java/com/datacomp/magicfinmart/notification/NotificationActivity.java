package com.datacomp.magicfinmart.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.homeloan.HomeLoanDetailActivity;
import com.datacomp.magicfinmart.loan_fm.personalloan.PersonalLoanDetailActivity;
import com.datacomp.magicfinmart.utility.Constants;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.NotificationEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.NotificationResponse;

public class NotificationActivity extends BaseActivity implements IResponseSubcriber {

    RecyclerView rvNotify;
    List<NotificationEntity>  NotificationLst;
    NotificationAdapter mAdapter;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginEntity;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbPersistanceController = new DBPersistanceController(this);
        loginEntity = dbPersistanceController.getUserData();

        initialize();

       // getNotificationData
        showDialog("Fetching Data...");
        new RegisterController(NotificationActivity.this).getNotificationData(String.valueOf(loginEntity.getFBAId()), NotificationActivity.this);


    }

    private void initialize() {

        prefManager = new PrefManager(NotificationActivity.this);
        NotificationLst = new ArrayList<NotificationEntity>();

        prefManager.setNotificationCounter(0);

        rvNotify = (RecyclerView) findViewById(R.id.rvNotify);
        rvNotify.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        rvNotify.setLayoutManager(layoutManager);



    }


    public void redirectToApplyLoan(NotificationEntity notifyEntity) {

        if(notifyEntity.getAction().equals("HL")) {

            startActivity(new Intent(this, HomeLoanDetailActivity.class));
            finish();
        }
        else if(notifyEntity.getAction().equals("PL")) {

            startActivity(new Intent(this, PersonalLoanDetailActivity.class));
            finish();
        }
    }


    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof NotificationResponse) {

            if (response.getStatusNo() == 0) {
                if ( ((NotificationResponse) response).getMasterData() != null) {

                    NotificationLst = ((NotificationResponse) response).getMasterData();

                    mAdapter = new NotificationAdapter(NotificationActivity.this, NotificationLst);
                    rvNotify.setAdapter(mAdapter);
                } else {
                    rvNotify.setAdapter(null);
                    Snackbar.make(rvNotify, "No Notification  Data Available", Snackbar.LENGTH_SHORT).show();
                }
            }else {
                rvNotify.setAdapter(null);
                Snackbar.make(rvNotify, "No Notification  Data Available", Snackbar.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

       // intent.putExtra("COUNTER", "0");
        setResult(Constants.REQUEST_CODE, intent);
        finish();
        super.onBackPressed();
    }



}
