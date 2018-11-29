package com.datacomp.magicfinmart.generatelead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.scan_vehicle.VehicleScanActivity;
import com.datacomp.magicfinmart.share_data.ShareDataFragment;
import com.datacomp.magicfinmart.vehicle_details.VehicleDetailFragment;

public class GenerateLeadActivity extends BaseActivity implements View.OnClickListener {

    Fragment fragment = null;
    LinearLayout llShareData, llVehicleDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_lead);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init_widgets();

        getSupportActionBar().setTitle("VEHICLE DETAIL");
        fragment = new VehicleDetailFragment();
        loadFragment(fragment);
    }

    private void init_widgets() {
        llShareData = findViewById(R.id.llShareData);
        llVehicleDetails = findViewById(R.id.llVehicleDetails);
        llShareData.setOnClickListener(this);
        llVehicleDetails.setOnClickListener(this);

    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();

            return true;
        }
        return false;
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.llVehicleDetails) {
            getSupportActionBar().setTitle("VEHICLE DETAIL");
            fragment = new VehicleDetailFragment();
            loadFragment(fragment);

        } else if (i == R.id.llShareData) {
            startActivity(new Intent(this, VehicleScanActivity.class));
                /*fragment = new ShareDataFragment();
                getSupportActionBar().setTitle("SHARE DATA");
                loadFragment(fragment);*/

        }
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
    @Override
    public void onBackPressed() {

        supportFinishAfterTransition();
        super.onBackPressed();
    }

}
