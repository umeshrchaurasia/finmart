package com.datacomp.magicfinmart.whatsnew;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.WhatsNewEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.WhatsNewResponse;

public class WhatsNewActivity extends BaseActivity implements IResponseSubcriber {
    RecyclerView rvWhatsNew;
    WhatsNewAdapter mAdapter;
    List<WhatsNewEntity> whatsNewEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvWhatsNew = (RecyclerView) findViewById(R.id.rvWhatsNew);
        rvWhatsNew.setHasFixedSize(true);
        rvWhatsNew.setLayoutManager(new LinearLayoutManager(this));
        showDialog();
        new MasterController(this).getWhatsNew("1.0", this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof WhatsNewResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                if (((WhatsNewResponse) response).getMasterData() != null) {
                    if (((WhatsNewResponse) response).getMasterData().size() > 0) {
                        whatsNewEntities = ((WhatsNewResponse) response).getMasterData();
                        mAdapter = new WhatsNewAdapter(this, whatsNewEntities);
                        rvWhatsNew.setAdapter(mAdapter);
                    }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        supportFinishAfterTransition();
        super.onBackPressed();
    }

}
