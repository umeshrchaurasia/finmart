package com.datacomp.magicfinmart.pendingcases;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.pendingcases.PendingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PendingCasesEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseDeleteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseInsLoanResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCasesResponse;

public class PendingCasesActivity extends BaseActivity implements IResponseSubcriber {

    RecyclerView rvPendingCasesList;
    PendingCasesAdapter mAdapter;
    PendingCasesEntity removePendingCasesEntity;
    List<PendingCasesEntity> mPendingList;
    boolean isHit = false;

    ViewPager viewPager;
    PendingPagerAdapter pendingPagerAdapter;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_cases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPendingList = new ArrayList<PendingCasesEntity>();


        init();

        fetchPendingCases(0);

        rvPendingCasesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastCompletelyVisibleItemPosition = 0;

                lastCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition();


                if (lastCompletelyVisibleItemPosition == mPendingList.size() - 1) {
                    if (!isHit) {
                        isHit = true;
                        fetchPendingCases(mPendingList.size());

                    }
                }
            }
        });
    }

    private void fetchPendingCases(int count) {
        if (count == 0)
            showDialog("Please wait.. loading cases");


        new PendingController(this).getPendingCasesWithType(count, 0,
                String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()), this);
    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        //pendingPagerAdapter = new PendingPagerAdapter(getSupportFragmentManager());
        //viewPager.setAdapter(pendingPagerAdapter);


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        rvPendingCasesList = (RecyclerView) findViewById(R.id.rvPendingCasesList);
        rvPendingCasesList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvPendingCasesList.setLayoutManager(layoutManager);
       // mAdapter = new PendingCasesAdapter(this, mPendingList);
        //rvPendingCasesList.setAdapter(mAdapter);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        List<PendingCasesEntity> list = new ArrayList<>();
        if (response instanceof PendingCasesResponse) {
            list = ((PendingCasesResponse) response).getMasterData();
            if (list.size() > 0) {
                isHit = false;
                // Toast.makeText(this, "fetching more...", Toast.LENGTH_SHORT).show();

                for (PendingCasesEntity entity : list) {
                    if (!mPendingList.contains(entity)) {
                        mPendingList.add(entity);
                    }
                }
            }
            //mAdapter = new VehicleDetailsAdapter(this, mPendingList);
            //rvPendingCasesList.setAdapter(mAdapter);
        } else if (response instanceof PendingCaseInsLoanResponse) {
            pendingPagerAdapter = new PendingPagerAdapter(getSupportFragmentManager(),
                    ((PendingCaseInsLoanResponse) response).getMasterData());
            viewPager.setAdapter(pendingPagerAdapter);
            //mAdapter.notifyDataSetChanged();
        } else if (response instanceof PendingCaseDeleteResponse) {
            mPendingList.remove(removePendingCasesEntity);
            //list=mPendingList;
            // mAdapter.refreshAdapter(mPendingList);
            // mAdapter.notifyDataSetChanged();
        }

        //mPendingList =list;
       // mAdapter.refreshAdapter(mPendingList);
        //mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void deletePendingcases(PendingCasesEntity entity) {
        removePendingCasesEntity = entity;
        showDialog("Please wait,Removing case..");
        new PendingController(this).deletePending(entity.getQuotetype(), entity.getId(), this);
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
