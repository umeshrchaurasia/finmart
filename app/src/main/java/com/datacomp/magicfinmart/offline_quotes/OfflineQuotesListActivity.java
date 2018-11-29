package com.datacomp.magicfinmart.offline_quotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.notification.NotificationActivity;
import com.datacomp.magicfinmart.offline_quotes.OfflineQuoteListAdapter.QuoteListRowAdapter;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.offline_quotes.OfflineQuotesController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.DocumentsOfflineEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.OfflineQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.OfflineInputResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.OfflineQuoteResponse;

public class OfflineQuotesListActivity extends BaseActivity implements IResponseSubcriber {

    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginEntity;
    RecyclerView rvOffline;
    List<OfflineQuoteEntity> offlineQuoteList;
    QuoteListRowAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_quotes_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dbPersistanceController = new DBPersistanceController(this);
        loginEntity = dbPersistanceController.getUserData();

        initialize();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OfflineQuotesListActivity.this, AddOfflineQuotesActivity.class));
            }
        });

        showDialog();
        new OfflineQuotesController(this).getOfflineQuote(OfflineQuotesListActivity.this);

    }

    private void initialize() {


        rvOffline = (RecyclerView) findViewById(R.id.rvOffline);
        rvOffline.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(OfflineQuotesListActivity.this);
        rvOffline.setLayoutManager(layoutManager);


    }

    public void redirectToQuoteList(DocumentsOfflineEntity docuEntity)
    {
      //  Toast.makeText(this,""+strName,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CommonWebViewActivity.class)
                .putExtra("URL", docuEntity.getDocument_path())
                .putExtra("NAME", "" + docuEntity.getDocument_name())
                .putExtra("TITLE", "" +docuEntity.getDocument_name()));

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof OfflineQuoteResponse) {

            if (response.getStatusNo() == 0) {

                offlineQuoteList = ((OfflineQuoteResponse) response).getMasterData();

                mAdapter = new QuoteListRowAdapter(OfflineQuotesListActivity.this, offlineQuoteList);
                rvOffline.setAdapter(mAdapter);

            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
