package com.datacomp.magicfinmart.motor.privatecar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.motor.privatecar.adapter.ActivityTabsPagerAdapter;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quoteapplication.QuoteApplicationController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CarMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteApplicationResponse;

public class PrivateCarDetailActivity extends BaseActivity implements IResponseSubcriber {
    Toolbar toolbar;
    ViewPager viewPager;
    ActivityTabsPagerAdapter mAdapter;
    PrefManager prefManager;
    DBPersistanceController dbPersistanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_car_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dbPersistanceController = new DBPersistanceController(this);

        if (dbPersistanceController.getCarMakeModel() != null && dbPersistanceController.getCarMakeModel().size() <= 0) {
            new MasterController(this).getCarMaster(this);
        }
        if (dbPersistanceController.getRTOListNames() != null && dbPersistanceController.getRTOListNames().size() <= 0) {
            new MasterController(this).getRTOMaster(this);
        }
        prefManager = new PrefManager(this);
       /* if (prefManager.IsCarMasterUpdate()) {
            new MasterController(this).getCarMaster(this);
        }

        if (prefManager.IsRtoMasterUpdate()) {
            new MasterController(this).getRTOMaster(this);
        }*/

        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setAdapter(null);
        fetchQuoteApplication();

    }

    private void fetchQuoteApplication() {

        showDialog(getResources().getString(R.string.fetching_msg));
        new QuoteApplicationController(this).getQuoteAppList(0, 0, "", "",
                new DBPersistanceController(this).getUserData().getFBAId(),
                1,
                "",
                this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof QuoteApplicationResponse) {
            if (((QuoteApplicationResponse) response).getMasterData() != null) {

                if ((((QuoteApplicationResponse) response).getMasterData().getQuote().size() != 0)
                        || ((QuoteApplicationResponse) response).getMasterData().getApplication().size() != 0) {

                    mAdapter = new ActivityTabsPagerAdapter(getSupportFragmentManager(),
                            ((QuoteApplicationResponse) response).getMasterData());
                    viewPager.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    finish();
                    startActivity(new Intent(this, InputQuoteBottmActivity.class));
                }
            }
        } else if (response instanceof CarMasterResponse) {
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        mAdapter = new ActivityTabsPagerAdapter(getSupportFragmentManager(), null);
        viewPager.setAdapter(mAdapter);

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
