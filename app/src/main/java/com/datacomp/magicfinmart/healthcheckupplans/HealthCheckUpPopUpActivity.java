package com.datacomp.magicfinmart.healthcheckupplans;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.Arrays;

public class HealthCheckUpPopUpActivity extends BaseActivity implements View.OnClickListener {
    RecyclerView rvHealthCheckTestDetails;
    Button applyOk;
    HealthCheckUPTestDeatilsAdapter healthCheckUPTestDeatilsAdapter;
    String[] array ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_check_up_pop_up);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(true);
        array = getIntent().getStringArrayExtra("TEST_DETAILS");
        applyOk = (Button) findViewById(R.id.applyOk);
        applyOk.setOnClickListener(this);

        healthCheckUPTestDeatilsAdapter = new HealthCheckUPTestDeatilsAdapter(this, Arrays.asList(array));
        rvHealthCheckTestDetails = (RecyclerView) findViewById(R.id.rvHealthCheckTestDetails);
        rvHealthCheckTestDetails.setHasFixedSize(true);
        rvHealthCheckTestDetails.setLayoutManager(new LinearLayoutManager(this));
        rvHealthCheckTestDetails.setAdapter(healthCheckUPTestDeatilsAdapter);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.applyOk) {
            finish();

        }
    }
}
