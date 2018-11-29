package com.datacomp.magicfinmart.motor.twowheeler.activity;

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
import com.datacomp.magicfinmart.motor.twowheeler.adapter.BikeActivityTabsPagerAdapter;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quoteapplication.QuoteApplicationController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.BikeMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.QuoteApplicationResponse;

public class TwoWheelerQuoteAppActivity extends BaseActivity implements IResponseSubcriber {
    Toolbar toolbar;
    ViewPager viewPager;
    BikeActivityTabsPagerAdapter mAdapter;
    /*PrefManager prefManager;*/
    DBPersistanceController dbPersistanceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_wheeler_quote_app);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
//        tabLayout.addTab(tabLayout.newTab().setText("QUOTES"));
//        tabLayout.addTab(tabLayout.newTab().setText("APPLICATION"));
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
        /*prefManager = new PrefManager(this);*/
        dbPersistanceController = new DBPersistanceController(this);

        if (dbPersistanceController.getBikeMakeModel() != null && dbPersistanceController.getBikeMakeModel().size() <= 0) {
            new MasterController(this).getBikeMaster(this);
        }
        if (dbPersistanceController.getRTOListNames() != null && dbPersistanceController.getRTOListNames().size() <= 0) {
            new MasterController(this).getRTOMaster(this);
        }
        /*if (prefManager.IsBikeMasterUpdate())
            new MasterController(this).getBikeMaster(this);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setAdapter(null);
        fetchQuoteApplication();

    }

    private void fetchQuoteApplication() {

        showDialog("Fetching.. Please wait.!");
        new QuoteApplicationController(this).getQuoteAppList(0, 0, "", "",
                new DBPersistanceController(this).getUserData().getFBAId(),
                10,
                "",
                this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof QuoteApplicationResponse) {
            if (((QuoteApplicationResponse) response).getMasterData() != null) {

                if (((QuoteApplicationResponse) response).getMasterData().getQuote().size() != 0
                        || ((QuoteApplicationResponse) response).getMasterData().getApplication().size() != 0) {
                    mAdapter = new BikeActivityTabsPagerAdapter(getSupportFragmentManager(),
                            ((QuoteApplicationResponse) response).getMasterData());
                    viewPager.setAdapter(mAdapter);
                } else {
                    finish();
                    startActivity(new Intent(this, BikeAddQuoteActivity.class));
                }
            }

        } else if (response instanceof BikeMasterResponse) {
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        mAdapter = new BikeActivityTabsPagerAdapter(getSupportFragmentManager(), null);
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

               /* Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("MarkTYPE", "FROM_HOME");
                startActivity(intent);

                finish();*/

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
        // return super.onSupportNavigateUp();
    }
}
