package com.datacomp.magicfinmart.motor.privatecar.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.motor.privatecar.adapter.AddonPopUpAdapter;
import com.datacomp.magicfinmart.motor.twowheeler.adapter.BikeQuoteAdapter;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CarMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SaveQuoteResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.motor.controller.MotorController;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.AppliedAddonsPremiumBreakup;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.CommonAddonEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.MobileAddOn;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.SummaryEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.SaveAddOnRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikePremiumResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.SaveAddOnResponse;

public class QuoteActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener, magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber {
    BikePremiumResponse bikePremiumResponse;
    RecyclerView bikeQuoteRecycler;
    BikeQuoteAdapter mAdapter;
    MotorRequestEntity motorRequestEntity;
    Menu menuAddon;
    String[] addOns;
    DBPersistanceController databaseController;
    ImageView webViewLoader;
    List<MobileAddOn> listMobileAddOn;
    TextView tvPolicyExp, tvMakeModel, tvFuel, tvCrn, tvCount, tvRtoName;
    Switch swAddon;
    FloatingActionButton filter;
    ImageView ivEdit;
    CarMasterEntity carMasterEntity;
    String rtoName;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        databaseController = new DBPersistanceController(this);
        if (getIntent().hasExtra("CAR_REQUEST")) {
            motorRequestEntity = getIntent().getParcelableExtra("CAR_REQUEST");
            rtoName = getIntent().getStringExtra("RTO_NAME");
        }
        initView();
        initializeAdapters();
        setListener();
        updateHeader();
        fetchQuotes();


    }



    private void setListener() {
        ivEdit.setOnClickListener(this);
        filter.setOnClickListener(this);
        swAddon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    private void initView() {
        //mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        bikeQuoteRecycler = (RecyclerView) findViewById(R.id.bikeQuoteRecycler);
        webViewLoader = (ImageView) findViewById(R.id.webViewLoader);
        Glide.with(this).load(R.drawable.preloader).into(webViewLoader);
        tvPolicyExp = (TextView) findViewById(R.id.tvPolicyExp);
        tvRtoName = (TextView) findViewById(R.id.tvRtoName);
        tvMakeModel = (TextView) findViewById(R.id.tvMakeModel);
        tvFuel = (TextView) findViewById(R.id.tvFuel);
        tvCrn = (TextView) findViewById(R.id.tvCrn);
        tvCount = (TextView) findViewById(R.id.tvCount);
        swAddon = (Switch) findViewById(R.id.swAddon);
        ivEdit = (ImageView) findViewById(R.id.ivEdit);
        filter = (FloatingActionButton) findViewById(R.id.filter);
    }

    private void initializeAdapters() {
        listMobileAddOn = new ArrayList<MobileAddOn>();
        bikePremiumResponse = new BikePremiumResponse();

        bikeQuoteRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        bikeQuoteRecycler.setLayoutManager(mLayoutManager);
        //mAdapter = new BikeQuoteAdapter(this, bikePremiumResponse);
        //bikeQuoteRecycler.setAdapter(mAdapter);

    }

    private void updateHeader() {
        if (motorRequestEntity != null) {
            carMasterEntity = databaseController.getVarientDetails("" + motorRequestEntity.getVehicle_id());
            tvPolicyExp.setText("" + motorRequestEntity.getPolicy_expiry_date());
            tvRtoName.setText("" + rtoName);
        }

        if (carMasterEntity != null) {

            tvFuel.setText(carMasterEntity.getFuel_Name());
            tvMakeModel.setText(carMasterEntity.getMake_Name() + " , " + carMasterEntity.getModel_Name() + " ," + carMasterEntity.getVariant_Name());
        }
    }

    private void updateCrn() {
        if (bikePremiumResponse != null) {
            if (bikePremiumResponse.getSummary().getPB_CRN() != null) {
                tvCrn.setText("" + bikePremiumResponse.getSummary().getPB_CRN());
                tvCount.setText("" + bikePremiumResponse.getSummary().getSuccess() + " results from qa.policyboss.com");
            }
        }
    }

    public void fetchQuotes() {
        showDialog();
        new MotorController(this).getMotorQuote(1, this);
    }

    public void rebindAdapter(BikePremiumResponse bikePremiumResponse) {
        mAdapter.setQuoteResponse(bikePremiumResponse);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof BikePremiumResponse) {


            bikePremiumResponse = (BikePremiumResponse) response;


            rebindAdapter(bikePremiumResponse);
            updateCrn();
            Log.d("trackIssue", "Summary  = " + bikePremiumResponse.getSummary().getStatusX() +
                    " ,counter = " + Constants.getSharedPreference(this).getInt(Utility.QUOTE_COUNTER, 0));
            if (bikePremiumResponse.getSummary().getStatusX().equals("complete")
                    || Constants.getSharedPreference(this).getInt(Utility.QUOTE_COUNTER, 0) >= MotorController.NO_OF_SERVER_HITS) {

                webViewLoader.setVisibility(View.GONE);

                new AsyncAddon().execute();

//                if (((BikePremiumResponse) response).getResponse().size() != 0)
//                    menuAddon.findItem(R.id.add_on).setVisible(true);
//                else {
//                    menuAddon.findItem(R.id.add_on).setVisible(false);
//                    Toast.makeText(this, "No quotes found.., try later", Toast.LENGTH_SHORT).show();
//                }

            } else {
                webViewLoader.setVisibility(View.VISIBLE);

            }
        } else if (response instanceof SaveAddOnResponse) {

        }
    }


    //finmart API response
    @Override
    public void OnSuccess(magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse response, String message) {
        if (response instanceof SaveQuoteResponse) {
            if (response.getStatusNo() == 0) {
                SaveQuoteResponse.SaveQuoteEntity saveQuoteEntity = ((SaveQuoteResponse) response).getMasterData().get(0);
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(QuoteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    //region Add-on
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_on_menu, menu);
        menuAddon = menu;
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
//            case R.id.add_on:
//                openPopUp();
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //endregion

    private void openPopUp() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Add-on :");

        RecyclerView rvAddOne;
        Button btnOk, btnCancel;

        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_addon_popup, null);
        builder.setView(dialogView);

        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        btnOk = (Button) dialogView.findViewById(R.id.btnOk);
        btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        rvAddOne = (RecyclerView) dialogView.findViewById(R.id.rvAddOne);
        rvAddOne.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(QuoteActivity.this);
        rvAddOne.setLayoutManager(layoutManager);

        final AddonPopUpAdapter popUpAdapter = new AddonPopUpAdapter(QuoteActivity.this, listMobileAddOn);
        rvAddOne.setAdapter(popUpAdapter);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMobileAddOn = popUpAdapter.getUpdateMobileAddonList();
                // applyAddons();
                applyPositiveAddons();
                updateAddonToserver();
                alertDialog.dismiss();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });


        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void updateAddonToserver() {
        SaveAddOnRequestEntity entity = new SaveAddOnRequestEntity();
        for (int i = 0; i < listMobileAddOn.size(); i++) {
            MobileAddOn mobileAddOn = listMobileAddOn.get(i);

            if (mobileAddOn.getAddonKey().matches("addon_zero_dep_cover") && mobileAddOn.isSelected) {
                entity.setAddon_zero_dep_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_road_assist_cover") && mobileAddOn.isSelected) {
                entity.setAddon_road_assist_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_ncb_protection_cover") && mobileAddOn.isSelected) {
                entity.setAddon_ncb_protection_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_engine_protector_cover") && mobileAddOn.isSelected) {
                entity.setAddon_engine_protector_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_invoice_price_cover") && mobileAddOn.isSelected) {
                entity.setAddon_invoice_price_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_key_lock_cover") && mobileAddOn.isSelected) {
                entity.setAddon_key_lock_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_consumable_cover") && mobileAddOn.isSelected) {
                entity.setAddon_consumable_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_daily_allowance_cover") && mobileAddOn.isSelected) {
                entity.setAddon_daily_allowance_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_windshield_cover") && mobileAddOn.isSelected) {
                entity.setAddon_windshield_cover("yes");
            }

            if (mobileAddOn.getAddonKey().matches("addon_passenger_assistance_cover") && mobileAddOn.isSelected) {
                entity.setAddon_passenger_assistance_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_tyre_coverage_cover") && mobileAddOn.isSelected) {
                entity.setAddon_tyre_coverage_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_personal_belonging_loss_cover") && mobileAddOn.isSelected) {
                entity.setAddon_personal_belonging_loss_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_inconvenience_allowance_cover") && mobileAddOn.isSelected) {
                entity.setAddon_inconvenience_allowance_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_medical_expense_cover") && mobileAddOn.isSelected) {
                entity.setAddon_medical_expense_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_hospital_cash_cover") && mobileAddOn.isSelected) {
                entity.setAddon_hospital_cash_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_ambulance_charge_cover") && mobileAddOn.isSelected) {
                entity.setAddon_ambulance_charge_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_rodent_bite_cover") && mobileAddOn.isSelected) {
                entity.setAddon_rodent_bite_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_losstime_protection_cover") && mobileAddOn.isSelected) {
                entity.setAddon_losstime_protection_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_hydrostatic_lock_cover") && mobileAddOn.isSelected) {
                entity.setAddon_hydrostatic_lock_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_guaranteed_auto_protection_cover") && mobileAddOn.isSelected) {
                entity.setAddon_guaranteed_auto_protection_cover("yes");
            }
            if (mobileAddOn.getAddonKey().matches("addon_final_premium") && mobileAddOn.isSelected) {
                entity.setAddon_final_premium("yes");
            }
            /*if (mobileAddOn.getAddonKey().matches("search_reference_number") && mobileAddOn.isSelected) {
                entity.setSearch_reference_number("yes");
            }*/
        }

        entity.setSearch_reference_number(Constants.getSharedPreference(this).getString(Utility.CARQUOTE_UNIQUEID, ""));


        new MotorController(this).saveAddOn(entity, this);
    }


    private void applyPositiveAddons() {

        for (ResponseEntity entity : bikePremiumResponse.getResponse()) { // itrate for each quote
            double addonValue = 0;
            entity.setAddonApplied(false);
            entity.setListAppliedAddons(null);

            if (entity.getAddon_List() != null) {
                List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium =
                        new ArrayList<AppliedAddonsPremiumBreakup>();// list of applied addon

                //region list of available addons
                for (int i = 0; i < listMobileAddOn.size(); i++) {

                    MobileAddOn mobileAddOn = listMobileAddOn.get(i);
                    // check if addon is selected
                    if (!mobileAddOn.isSelected()) {
                        continue;
                    }

                    //region addon_zero_dep_cover
                    if (mobileAddOn.getAddonKey().matches("addon_zero_dep_cover")) {

                        if (entity.getAddon_List().getAddon_zero_dep_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_zero_dep_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_zero_dep_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_zero_dep_cover();
                        }

                    }
                    //endregion

                    //region addon_road_assist_cover

                    if (mobileAddOn.getAddonKey().matches("addon_road_assist_cover")) {

                        if (entity.getAddon_List().getAddon_road_assist_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_road_assist_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_road_assist_cover();
                        }

                    }
                    //endregion

                    //region addon_ncb_protection_cover

                    if (mobileAddOn.getAddonKey().matches("addon_ncb_protection_cover")) {

                        if (entity.getAddon_List().getAddon_ncb_protection_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_ncb_protection_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_ncb_protection_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_ncb_protection_cover();
                        }

                    }
                    //endregion

                    //region addon_engine_protector_cover

                    if (mobileAddOn.getAddonKey().matches("addon_engine_protector_cover")) {

                        if (entity.getAddon_List().getAddon_engine_protector_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_engine_protector_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_engine_protector_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_engine_protector_cover();
                        }

                    }
                    //endregion

                    //region addon_invoice_price_cover

                    if (mobileAddOn.getAddonKey().matches("addon_invoice_price_cover")) {

                        if (entity.getAddon_List().getAddon_invoice_price_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_invoice_price_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_invoice_price_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_invoice_price_cover();
                        }

                    }
                    //endregion

                    //region addon_key_lock_cover

                    if (mobileAddOn.getAddonKey().matches("addon_key_lock_cover")) {

                        if (entity.getAddon_List().getAddon_key_lock_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_key_lock_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_key_lock_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_key_lock_cover();
                        }

                    }
                    //endregion

                    //region addon_consumable_cover

                    if (mobileAddOn.getAddonKey().matches("addon_consumable_cover")) {

                        if (entity.getAddon_List().getAddon_consumable_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_consumable_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_consumable_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_consumable_cover();
                        }

                    }
                    //endregion

                    //region addon_daily_allowance_cover

                    if (mobileAddOn.getAddonKey().matches("addon_daily_allowance_cover")) {

                        if (entity.getAddon_List().getAddon_daily_allowance_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_daily_allowance_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_daily_allowance_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_daily_allowance_cover();
                        }

                    }
                    //endregion

                    //region addon_windshield_cover

                    if (mobileAddOn.getAddonKey().matches("addon_windshield_cover")) {

                        if (entity.getAddon_List().getAddon_windshield_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_windshield_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_windshield_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_windshield_cover();
                        }

                    }
                    //endregion

                    //region addon_passenger_assistance_cover

                    if (mobileAddOn.getAddonKey().matches("addon_passenger_assistance_cover")) {

                        if (entity.getAddon_List().getAddon_passenger_assistance_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_passenger_assistance_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_passenger_assistance_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_passenger_assistance_cover();
                        }

                    }
                    //endregion

                    //region addon_tyre_coverage_cover

                    if (mobileAddOn.getAddonKey().matches("addon_tyre_coverage_cover")) {

                        if (entity.getAddon_List().getAddon_tyre_coverage_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_tyre_coverage_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_tyre_coverage_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_tyre_coverage_cover();
                        }

                    }
                    //endregion

                    //region addon_personal_belonging_loss_cover

                    if (mobileAddOn.getAddonKey().matches("addon_personal_belonging_loss_cover")) {

                        if (entity.getAddon_List().getAddon_personal_belonging_loss_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_personal_belonging_loss_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_personal_belonging_loss_cover();
                        }

                    }
                    //endregion

                    //region addon_inconvenience_allowance_cover

                    if (mobileAddOn.getAddonKey().matches("addon_inconvenience_allowance_cover")) {

                        if (entity.getAddon_List().getAddon_inconvenience_allowance_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_inconvenience_allowance_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_inconvenience_allowance_cover();
                        }

                    }
                    //endregion

                    //region addon_medical_expense_cover

                    if (mobileAddOn.getAddonKey().matches("addon_medical_expense_cover")) {

                        if (entity.getAddon_List().getAddon_medical_expense_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_medical_expense_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_medical_expense_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_medical_expense_cover();
                        }

                    }
                    //endregion

                    //region addon_hospital_cash_cover

                    if (mobileAddOn.getAddonKey().matches("addon_hospital_cash_cover")) {

                        if (entity.getAddon_List().getAddon_hospital_cash_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_hospital_cash_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_hospital_cash_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_hospital_cash_cover();
                        }

                    }
                    //endregion

                    //region addon_ambulance_charge_cover

                    if (mobileAddOn.getAddonKey().matches("addon_ambulance_charge_cover")) {

                        if (entity.getAddon_List().getAddon_ambulance_charge_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_ambulance_charge_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_ambulance_charge_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_ambulance_charge_cover();
                        }

                    }
                    //endregion

                    //region addon_rodent_bite_cover

                    if (mobileAddOn.getAddonKey().matches("addon_rodent_bite_cover")) {

                        if (entity.getAddon_List().getAddon_rodent_bite_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_rodent_bite_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_rodent_bite_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_rodent_bite_cover();
                        }

                    }
                    //endregion

                    //region addon_losstime_protection_cover

                    if (mobileAddOn.getAddonKey().matches("addon_losstime_protection_cover")) {

                        if (entity.getAddon_List().getAddon_losstime_protection_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_losstime_protection_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_losstime_protection_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_losstime_protection_cover();
                        }

                    }
                    //endregion

                    //region addon_hydrostatic_lock_cover

                    if (mobileAddOn.getAddonKey().matches("addon_hydrostatic_lock_cover")) {

                        if (entity.getAddon_List().getAddon_hydrostatic_lock_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_hydrostatic_lock_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_hydrostatic_lock_cover();
                        }

                    }
                    //endregion

                    //region addon_guaranteed_auto_protection_cover

                    if (mobileAddOn.getAddonKey().matches("addon_guaranteed_auto_protection_cover")) {

                        if (entity.getAddon_List().getAddon_guaranteed_auto_protection_cover() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_guaranteed_auto_protection_cover"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_guaranteed_auto_protection_cover());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_guaranteed_auto_protection_cover();
                        }

                    }
                    //endregion

                    //region addon_final_premium

                    if (mobileAddOn.getAddonKey().matches("addon_final_premium")) {

                        if (entity.getAddon_List().getAddon_final_premium() != 0) {
                            entity.setAddonApplied(true);
                            AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                            appliedAddonsPremiumBreakup.setAddonName(databaseController.getAddonName("addon_final_premium"));
                            appliedAddonsPremiumBreakup.setPriceAddon(entity.getAddon_List().getAddon_final_premium());
                            listAppliedAddonPremium.add(appliedAddonsPremiumBreakup);
                            addonValue = addonValue + entity.getAddon_List().getAddon_final_premium();
                        }

                    }
                    //endregion

                }
                //endregion

                //region update response entity
                double finalPremWithoutGST = addonValue + Double.parseDouble(entity.getPremium_Breakup().getNet_premium());
                entity.setFinal_premium_without_addon("" + finalPremWithoutGST);
                entity.setTotalGST("" + finalPremWithoutGST * Constants.GST);
                entity.setTotalAddonAplied("" + addonValue);
                double finalPremWithGST = finalPremWithoutGST + (finalPremWithoutGST * Constants.GST);
                entity.setFinal_premium_with_addon("" + finalPremWithGST);
                entity.setListAppliedAddons(listAppliedAddonPremium);
                //endregion
            }
        }

        rebindAdapter(bikePremiumResponse);
    }


    public void redirectToBuy(String Service_Log_Unique_Id) {
        String URL = "http://qa.policyboss.com/buynowprivatecar/2/arn-5vsdcdks-ifxf-lbo7-imvr-ycc3axgrfrwe/nonposp/0";
        String url = "http://qa.policyboss.com/";
        //String url = "http://policyboss.com/";
        String title = "";
        String name = "";
        url = url + "buynowprivatecar/4/" + Service_Log_Unique_Id + "/nonposp/0";
        title = "Motor Insurance";


        startActivity(new Intent(this, CommonWebViewActivity.class)
                .putExtra("URL", url)
                .putExtra("NAME", name)
                .putExtra("TITLE", title));
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.filter) {/*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            startActivity(new Intent(this, ModifyQuoteActivity.class).putExtra("CAR_REQUEST", motorRequestEntity));

        }
    }

    public void redirectToPopUpPremium(ResponseEntity entity, SummaryEntity summaryEntity, String IDV) {
        startActivity(new Intent(this, PremiumBreakUpActivity.class)
                .putExtra("RESPONSE", entity));


    }

    class AsyncAddon extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            listMobileAddOn.clear();
            CommonAddonEntity entity = bikePremiumResponse.getSummary().getCommon_Addon();
            if (entity.getAddon_ambulance_charge_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_ambulance_charge_cover"));
                mobileAddOn.setMin(entity.getAddon_ambulance_charge_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_ambulance_charge_cover().getMax());
                mobileAddOn.setAddonKey("addon_ambulance_charge_cover");
                // item.add(databaseController.getAddonName("addon_ambulance_charge_cover"));
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_consumable_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_consumable_cover"));
                mobileAddOn.setMin(entity.getAddon_consumable_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_consumable_cover().getMax());
                mobileAddOn.setAddonKey("addon_consumable_cover");
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_daily_allowance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_daily_allowance_cover"));
                mobileAddOn.setMin(entity.getAddon_daily_allowance_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_daily_allowance_cover().getMax());
                mobileAddOn.setAddonKey("addon_daily_allowance_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_engine_protector_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_engine_protector_cover"));
                mobileAddOn.setMin(entity.getAddon_engine_protector_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_engine_protector_cover().getMax());
                mobileAddOn.setAddonKey("addon_engine_protector_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_hospital_cash_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_hospital_cash_cover"));
                mobileAddOn.setMin(entity.getAddon_hospital_cash_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_hospital_cash_cover().getMax());
                mobileAddOn.setAddonKey("addon_hospital_cash_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_hydrostatic_lock_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_hydrostatic_lock_cover"));
                mobileAddOn.setMin(entity.getAddon_hydrostatic_lock_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_hydrostatic_lock_cover().getMax());
                mobileAddOn.setAddonKey("addon_hydrostatic_lock_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_inconvenience_allowance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_inconvenience_allowance_cover"));
                mobileAddOn.setMin(entity.getAddon_inconvenience_allowance_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_inconvenience_allowance_cover().getMax());
                mobileAddOn.setAddonKey("addon_inconvenience_allowance_cover");
                listMobileAddOn.add(mobileAddOn);

            }


            if (entity.getAddon_invoice_price_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_invoice_price_cover"));
                mobileAddOn.setMin(entity.getAddon_invoice_price_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_invoice_price_cover().getMax());
                mobileAddOn.setAddonKey("addon_invoice_price_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_key_lock_cover() != null) {
                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_key_lock_cover"));
                mobileAddOn.setMin(entity.getAddon_key_lock_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_key_lock_cover().getMax());
                mobileAddOn.setAddonKey("addon_key_lock_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_losstime_protection_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_losstime_protection_cover"));
                mobileAddOn.setMin(entity.getAddon_losstime_protection_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_losstime_protection_cover().getMax());
                mobileAddOn.setAddonKey("addon_losstime_protection_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_medical_expense_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_medical_expense_cover"));
                mobileAddOn.setMin(entity.getAddon_medical_expense_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_medical_expense_cover().getMax());
                mobileAddOn.setAddonKey("addon_medical_expense_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_ncb_protection_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_ncb_protection_cover"));
                mobileAddOn.setMin(entity.getAddon_ncb_protection_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_ncb_protection_cover().getMax());
                mobileAddOn.setAddonKey("addon_ncb_protection_cover");
                listMobileAddOn.add(mobileAddOn);


            }

            if (entity.getAddon_passenger_assistance_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_passenger_assistance_cover"));
                mobileAddOn.setMin(entity.getAddon_passenger_assistance_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_passenger_assistance_cover().getMax());
                mobileAddOn.setAddonKey("addon_passenger_assistance_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_personal_belonging_loss_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_personal_belonging_loss_cover"));
                mobileAddOn.setMin(entity.getAddon_personal_belonging_loss_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_personal_belonging_loss_cover().getMax());
                mobileAddOn.setAddonKey("addon_personal_belonging_loss_cover");
                listMobileAddOn.add(mobileAddOn);
            }

            if (entity.getAddon_road_assist_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_road_assist_cover"));
                mobileAddOn.setMin(entity.getAddon_road_assist_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_road_assist_cover().getMax());
                mobileAddOn.setAddonKey("addon_road_assist_cover");
                listMobileAddOn.add(mobileAddOn);
            }
            if (entity.getAddon_rodent_bite_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_rodent_bite_cover"));
                mobileAddOn.setMin(entity.getAddon_rodent_bite_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_rodent_bite_cover().getMax());
                mobileAddOn.setAddonKey("addon_rodent_bite_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_tyre_coverage_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_tyre_coverage_cover"));
                mobileAddOn.setMin(entity.getAddon_tyre_coverage_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_tyre_coverage_cover().getMax());
                mobileAddOn.setAddonKey("addon_tyre_coverage_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_windshield_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_windshield_cover"));
                mobileAddOn.setMin(entity.getAddon_windshield_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_windshield_cover().getMax());
                mobileAddOn.setAddonKey("addon_windshield_cover");
                listMobileAddOn.add(mobileAddOn);

            }

            if (entity.getAddon_zero_dep_cover() != null) {

                MobileAddOn mobileAddOn = new MobileAddOn();
                mobileAddOn.setAddonName(databaseController.getAddonName("addon_zero_dep_cover"));
                mobileAddOn.setMin(entity.getAddon_zero_dep_cover().getMin());
                mobileAddOn.setMax(entity.getAddon_zero_dep_cover().getMax());
                mobileAddOn.setAddonKey("addon_zero_dep_cover");
                listMobileAddOn.add(mobileAddOn);

                //item.add(databaseController.getAddonName("addon_zero_dep_cover"));
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}
