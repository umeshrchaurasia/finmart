package com.datacomp.magicfinmart.healthcheckupplans;

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

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.controller.healthcheckup.HealthCheckUPController;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthCEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response.HealthCheckUpResponse;

public class HealthCheckUpListActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    RecyclerView rvHealthCheckUpList;
    ImageView ivSearch;
    TextView tvAdd, tvSearch;
    EditText etSearch;
    FloatingActionButton fbAdd;
    List<HealthCEntity> listHealthCheck;
    HealthCheckUpPlansAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check_up_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();
        setListener();
        setTextWatcher();

        showDialog();
        new HealthCheckUPController(getApplicationContext()).getHealthCheckList(this);

    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof HealthCheckUpResponse) {

            if (((HealthCheckUpResponse) response).getMasterData() != null
                    && ((HealthCheckUpResponse) response).getMasterData().size() > 0)
                listHealthCheck = ((HealthCheckUpResponse) response).getMasterData();
            mAdapter = new HealthCheckUpPlansAdapter(this, listHealthCheck);
            rvHealthCheckUpList.setAdapter(mAdapter);
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        fbAdd = (FloatingActionButton) findViewById(R.id.fbAdd);
        ivSearch = (ImageView) findViewById(R.id.ivSearch);

        tvAdd = (TextView) findViewById(R.id.tvAdd);
        tvSearch = (TextView) findViewById(R.id.tvSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setVisibility(View.INVISIBLE);

        rvHealthCheckUpList = (RecyclerView) findViewById(R.id.rvHealthCheckUpList);
        rvHealthCheckUpList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvHealthCheckUpList.setLayoutManager(layoutManager);
    }

    private void setListener() {
        ivSearch.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        fbAdd.setOnClickListener(this);
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
        if (i == R.id.tvAdd || i == R.id.fbAdd) {
            startActivity(new Intent(this, HealthCheckUpPlansActivity.class));

        } else if (i == R.id.tvSearch || i == R.id.ivSearch) {
            if (etSearch.getVisibility() == View.INVISIBLE) {
                etSearch.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            }


        }
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
            onBackPressed();
//
//                Intent intent = new Intent(this, HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.putExtra("MarkTYPE", "FROM_HOME");
//                startActivity(intent);
//
//                finish();

        }
        return super.onOptionsItemSelected(item);
    }
//    @Override
//    public void onBackPressed() {
//
//        supportFinishAfterTransition();
//        super.onBackPressed();
//    }
@Override
public boolean onSupportNavigateUp() {
    onBackPressed();
    return false;
    // return super.onSupportNavigateUp();
}
}
