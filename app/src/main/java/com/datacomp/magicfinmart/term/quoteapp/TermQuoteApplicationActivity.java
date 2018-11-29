package com.datacomp.magicfinmart.term.quoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.term.compareterm.CompareTermActivity;
import com.datacomp.magicfinmart.term.hdfc.HdfcTermActivity;
import com.datacomp.magicfinmart.term.icici.IciciTermActivity;
import com.datacomp.magicfinmart.term.termselection.TermActivityTabsPagerAdapter;
import com.datacomp.magicfinmart.utility.Constants;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.term.TermInsuranceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermQuoteApplicationResponse;

public class TermQuoteApplicationActivity extends BaseActivity implements IResponseSubcriber {
    ViewPager viewPager;
    Toolbar toolbar;
    TermActivityTabsPagerAdapter mAdapter;
    int compId = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termquoteapplication_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra(Constants.LIFE_INS)) {
            compId = getIntent().getIntExtra(Constants.LIFE_INS, 0);
            if (compId == 39) {
                getSupportActionBar().setTitle("ICICI PRUDENTIAL");
            } else if (compId == 0) {
                getSupportActionBar().setTitle("COMPARE TERM INSURANCE");
            } else if (compId == 28) {
                getSupportActionBar().setTitle("CLICK TO PROTECT 3D");
            }
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager, true);

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
        //viewPager.setAdapter(null);
        fetchQuoteApplication(compId);

    }

    public int getCompId() {
        return compId;
    }

    private void fetchQuoteApplication(int compId) {

        showDialog(getResources().getString(R.string.fetching_msg));
        new TermInsuranceController(this).getTermQuoteApplicationList(compId, 0, "0", this);
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
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);

            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof TermQuoteApplicationResponse) {
            if (((TermQuoteApplicationResponse) response).getMasterData() != null) {

                /*mAdapter = new TermActivityTabsPagerAdapter(getSupportFragmentManager(),
                        ((TermQuoteApplicationResponse) response));
                viewPager.setAdapter(mAdapter);

               */
                if ((((TermQuoteApplicationResponse) response).getMasterData().getQuote().size() != 0)
                        || ((TermQuoteApplicationResponse) response).getMasterData().getApplication().size() != 0) {

                    mAdapter = new TermActivityTabsPagerAdapter(getSupportFragmentManager(),
                            ((TermQuoteApplicationResponse) response));
                    viewPager.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                } else {
                    finish();
                    startRespectiveActivity();
                    //startActivity(new Intent(this, InputQuoteBottmActivity.class));
                }


            }

        }

    }

    private void startRespectiveActivity() {
        if (compId == 39) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "ICICI TERM INSURANCE", "ICICI TERM INSURANCE");
            startActivity(new Intent(this, IciciTermActivity.class));
        } else if (compId == 0) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "COMPARE TERM INSURANCE", "COMPARE TERM INSURANCE");
            startActivity(new Intent(this, CompareTermActivity.class));
        } else if (compId == 28) {
            MyApplication.getInstance().trackEvent(Constants.LIFE_INS, "HDFC TERM INSURANCE", "HDFC TERM INSURANCE");
            startActivity(new Intent(this, HdfcTermActivity.class));
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        //Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        mAdapter = new TermActivityTabsPagerAdapter(getSupportFragmentManager(), null);
        viewPager.setAdapter(mAdapter);
    }
}
