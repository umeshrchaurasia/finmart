package com.datacomp.magicfinmart.dashboard;


import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.knowledgeguru.KnowledgeGuruActivity;
import com.datacomp.magicfinmart.pendingcases.PendingCasesActivity;
import com.datacomp.magicfinmart.salesmaterial.SalesMaterialActivity;
import com.datacomp.magicfinmart.utility.Constants;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MenuMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ConstantsResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriber, /*ILocationStateListener,*/ BaseFragment.PopUpListener {

    RecyclerView rvHome;
    DashboardRowAdapter mAdapter;
    BottomNavigationView navigation;
    PackageInfo pinfo;
    View view;
    ConstantEntity constantEntity;
    PrefManager prefManager;
    int forceUpdate;
    //LocationTracker locationTracker;
    //Location location;
    TextView tvKnowledge, tvPendingCAses, tvSalesMat;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initialise(view);

        setListener();
        registerPopUp(this);
        prefManager = new PrefManager(getActivity());
        try {
            pinfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mAdapter = new DashboardRowAdapter(DashboardFragment.this);
        this.rvHome.setAdapter(mAdapter);
        //new MasterController(getActivity()).getConstants(this);
        return view;
    }

    private void setListener() {
        tvKnowledge.setOnClickListener(this);
        tvPendingCAses.setOnClickListener(this);
        tvSalesMat.setOnClickListener(this);
    }

    private void initialise(View view) {

        tvKnowledge = (TextView) view.findViewById(R.id.tvKnowledge);
        tvPendingCAses = (TextView) view.findViewById(R.id.tvPendingCAses);
        tvSalesMat = (TextView) view.findViewById(R.id.tvSalesMat);

        rvHome = (RecyclerView) view.findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.nav_sales) {//redirect to sales
                startActivity(new Intent(getContext(), SalesMaterialActivity.class));
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Sales Material : Sales Material From Dashboard "), Constants.SALES_MATERIAL), null);
                return true;
            } else if (i == R.id.nav_pending) {//redirect to pending status
                startActivity(new Intent(getContext(), PendingCasesActivity.class));
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Pending Cases : Pending Cases From Dashboard "), Constants.PENDING_CASES), null);
                return true;
            } else if (i == R.id.nav_knowledge) {//redirect to knowledge guru
                startActivity(new Intent(getActivity(), KnowledgeGuruActivity.class));
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Knowledge Guru : Knowledge Guru From Dashboard "), Constants.KNOWLEDGE_GURU), null);
                return true;
            }
            return false;
        }
    };

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof ConstantsResponse) {
            constantEntity = ((ConstantsResponse) response).getMasterData();
            if (response.getStatusNo() == 0) {

                //region check for new vwesion
                int serverVersionCode = Integer.parseInt(((ConstantsResponse) response).getMasterData().getVersionCode());
                if (pinfo != null && pinfo.versionCode < serverVersionCode) {
                    forceUpdate = Integer.parseInt(((ConstantsResponse) response).getMasterData().getIsForceUpdate());
                    if (forceUpdate == 1) {
                        // forced update app
                        openPopUp(view, "UPDATE", "New version available on play store!!!! Please update.", "OK", false);
                    } else {
                        // aap with less version but not forced update
                        if (prefManager.getUpdateShown()) {
                            prefManager.setIsUpdateShown(false);
                            openPopUp(view, "UPDATE", "New version available on play store!!!! Please update.", "OK", true);
                        }
                    }
                } else if (((ConstantsResponse) response).getMasterData().
                        getMPSStatus().toLowerCase().equalsIgnoreCase("p")) {
                    if (getActivity() != null && prefManager.getMps() != null) {

                        //  ((HomeActivity) getActivity()).DialogMPS();
                    }
                }
                //endregion
                if (getActivity() != null)
                    ((HomeActivity) getActivity()).hideNavigationItem();
            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        dialog.cancel();
        openAppMarketPlace();
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {

        if (forceUpdate == 1) {

        } else {
            dialog.cancel();
        }
    }

    private void openAppMarketPlace() {
        final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Update : User open marketplace  "), "Update"), null);
    }

    /*@Override
    public void onLocationChanged(Location location) {
        location = locationTracker.mLocation;
    }

    @Override
    public void onConnected() {
        location = locationTracker.mLocation;
    }

    @Override
    public void onConnectionFailed() {
        location = null;
    }*/

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tvKnowledge) {//redirect to knowledge guru
            startActivity(new Intent(getActivity(), KnowledgeGuruActivity.class));
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Knowledge Guru : Knowledge Guru From Dashboard "), Constants.KNOWLEDGE_GURU), null);
            MyApplication.getInstance().trackEvent(Constants.KNOWLEDGE_GURU, "Clicked", "Knowledge Guru From Dashboard");

        } else if (i == R.id.tvPendingCAses) {//redirect to pending status
            startActivity(new Intent(getContext(), PendingCasesActivity.class));
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Pending Cases : Pending Cases From Dashboard "), Constants.PENDING_CASES), null);
            MyApplication.getInstance().trackEvent(Constants.PENDING_CASES, "Clicked", "Pending Cases From Dashboard");

        } else if (i == R.id.tvSalesMat) {//redirect to sales
            startActivity(new Intent(getContext(), SalesMaterialActivity.class));
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Sales Material : Sales Material From Dashboard "), Constants.SALES_MATERIAL), null);
            MyApplication.getInstance().trackEvent(Constants.SALES_MATERIAL, "Clicked", "Sales Material From Dashboard");

        }
    }

    public void refreshAdapter() {
        mAdapter = new DashboardRowAdapter(this);
        rvHome.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mHandleMessageReceiver,
                        new IntentFilter(Utility.USER_DASHBOARD));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mHandleMessageReceiver);
    }

    //region broadcast receiver
    public BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null) {
                if (intent.getAction().equalsIgnoreCase(Utility.USER_DASHBOARD)) {
                    //MenuMasterEntity menuMasterEntity = intent.getParcelableExtra("USER_DASHBOARD");
                    refreshAdapter();
                }
            }
        }
    };

    //endregion
}
