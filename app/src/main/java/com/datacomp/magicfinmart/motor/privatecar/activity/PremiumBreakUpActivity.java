package com.datacomp.magicfinmart.motor.privatecar.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.motor.privatecar.adapter.PremiumBreakUpAdapter;
import com.datacomp.magicfinmart.motor.privatecar.adapter.PremiumBreakUpAdapterEntity;
import com.datacomp.magicfinmart.motor.privatecar.adapter.PremiumBreakUpAddonAdapter;
import com.datacomp.magicfinmart.motor.privatecar.fragment.QuoteFragment;
import com.datacomp.magicfinmart.motor.twowheeler.fragment.BikeQuoteFragment;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;
import com.datacomp.magicfinmart.webviews.ShareQuoteActivity;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quoteapplication.QuoteApplicationController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BikeMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CarMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.controller.MotorController;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.AppliedAddonsPremiumBreakup;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.LiabilityEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.MobileAddOn;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.OwnDamageEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.PremiumBreakUpAddonEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.SummaryEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.SaveAddOnRequestEntity;

public class PremiumBreakUpActivity extends BaseActivity implements View.OnClickListener, BaseActivity.PopUpListener, IResponseSubcriber, magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber {
    ResponseEntity responseEntity;
    RecyclerView rvOwnDamage, rvLiability, rvAddonPremium;
    PremiumBreakUpAdapter damageAdapter, liabilityAdapter, addonAdapter;
    PremiumBreakUpAddonAdapter addonAdapterNew;
    TextView tvTotalPremium, tvGst, tvNetPremium, txtIDV, txtFinalPremium, tvAddonTotal;
    LinearLayout btnBuy;
    ImageView ivCross, ivShare;
    Button btnBackToQuote;
    CardView cvAddon;
    List<PremiumBreakUpAdapterEntity> damageList, liabilityList, addonList;
    List<PremiumBreakUpAddonEntity> addonListNew;
    List<MobileAddOn> listMobileAddOn;
    DBPersistanceController dbPersistanceController;
    BikeMasterEntity bikeMasterEntity;
    CarMasterEntity carMasterEntity;
    SummaryEntity summaryEntity;
    String jsonShareString, responseJson;
    Gson gson = new Gson();
    Double addOnTotal = 0.0;
    String vechileRequestId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_premium_break_up);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        dbPersistanceController = new DBPersistanceController(this);
        registerPopUp(this);
        if (getIntent().hasExtra("SUMMARY")) {
            summaryEntity = getIntent().getParcelableExtra("SUMMARY");
        }
        if (getIntent().hasExtra("RESPONSE_CAR")) {
            responseEntity = getIntent().getParcelableExtra("RESPONSE_CAR");
            carMasterEntity = dbPersistanceController.getVarientDetails("" + summaryEntity.getRequest_Core().getVehicle_id());
        }
        if (getIntent().hasExtra("MOBILE_ADDON")) {
            listMobileAddOn = getIntent().getParcelableArrayListExtra("MOBILE_ADDON");

        }
        if (getIntent().hasExtra("RESPONSE_BIKE")) {
            responseEntity = getIntent().getParcelableExtra("RESPONSE_BIKE");
            bikeMasterEntity = dbPersistanceController.getBikeVarientDetails("" + summaryEntity.getRequest_Core().getVehicle_id());
        }
        if (getIntent().hasExtra("VEHICLE_REQUEST_ID")) {
            vechileRequestId = getIntent().getStringExtra("VEHICLE_REQUEST_ID");
        }
        initViews();

        damageList = getDamageList();
        liabilityList = getLiabilityList();
        //addonList = getAddonList();
        if (responseEntity != null && responseEntity.getPremiumBreakUpAddonEntities() != null && responseEntity.getPremiumBreakUpAddonEntities().size() > 0)
            addonListNew = responseEntity.getPremiumBreakUpAddonEntities();
        else {
            addonListNew = getAddonListNew();
            responseEntity.setPremiumBreakUpAddonEntities(addonListNew);
        }


        initRecyclers();
        setListeners();
        /*if (listMobileAddOn != null)
            addOnTotal = applyPositiveAddons(listMobileAddOn);*/
        if (responseEntity.getTotalAddonAplied() != null && !responseEntity.getTotalAddonAplied().equals(""))
            addOnTotal = Double.parseDouble(responseEntity.getTotalAddonAplied());
        bindData(responseEntity);
        /*new AsyncShareJson().execute();*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        QuoteFragment.isShowing = false;
        BikeQuoteFragment.isShowing = false;
    }

    private void bindData(ResponseEntity responseEntity) {
        if (responseEntity != null) {
            txtIDV.setText("" + responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            // txtPlanName.setText("" + responseEntity.getInsurer().getInsurer_Code());
            if (responseEntity.getFinal_premium_without_addon() != null && !responseEntity.getFinal_premium_without_addon().equals("")) {
                tvTotalPremium.setText("\u20B9 " + String.valueOf(getDigitPrecision(Double.parseDouble(responseEntity.getFinal_premium_without_addon()))));
                tvGst.setText("\u20B9 " + String.valueOf(getDigitPrecision(Double.parseDouble(responseEntity.getTotalGST()))));
                tvNetPremium.setText(getRupeesRound(responseEntity.getFinal_premium_with_addon()));
                txtFinalPremium.setText(getRupeesRound(responseEntity.getFinal_premium_with_addon()));
                //tvTotalPremium.setText(getRupeesRound(responseEntity.getFinal_premium_without_addon()));
                //tvGst.setText(getRupeesRound(responseEntity.getTotalGST()));
            } else {

                tvTotalPremium.setText("\u20B9 " + String.valueOf(getDigitPrecision(Double.parseDouble(responseEntity.getPremium_Breakup().getNet_premium()))));
                tvGst.setText("\u20B9 " + String.valueOf(getDigitPrecision(Double.parseDouble(responseEntity.getPremium_Breakup().getService_tax()))));
                tvNetPremium.setText(getRupeesRound(responseEntity.getPremium_Breakup().getFinal_premium()));

                //tvTotalPremium.setText(getRupeesRound(responseEntity.getPremium_Breakup().getFinal_premium()));
                //tvGst.setText(getRupeesRound(responseEntity.getPremium_Breakup().getService_tax()));
                txtFinalPremium.setText(getRupeesRound(responseEntity.getPremium_Breakup().getFinal_premium()));
            }
            tvAddonTotal.setText("" + getRound("" + addOnTotal));
        }
    }

    private void setListeners() {
        btnBuy.setOnClickListener(this);
        btnBackToQuote.setOnClickListener(this);
        // ivCross.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    private void initRecyclers() {
        rvOwnDamage.setHasFixedSize(true);
        rvLiability.setHasFixedSize(true);
        rvAddonPremium.setHasFixedSize(true);

        rvOwnDamage.setLayoutManager(new LinearLayoutManager(this));
        damageAdapter = new PremiumBreakUpAdapter(this, damageList);
        rvOwnDamage.setAdapter(damageAdapter);

        rvLiability.setLayoutManager(new LinearLayoutManager(this));
        liabilityAdapter = new PremiumBreakUpAdapter(this, liabilityList);
        rvLiability.setAdapter(liabilityAdapter);

       /* if (addonList != null && addonList.size() > 0) {
            cvAddon.setVisibility(View.VISIBLE);
            rvAddonPremium.setLayoutManager(new LinearLayoutManager(this));
            addonAdapter = new PremiumBreakUpAdapter(this, addonList);
            rvAddonPremium.setAdapter(addonAdapter);
        } else {
            cvAddon.setVisibility(View.GONE);
        }*/

        if (addonListNew != null && addonListNew.size() > 0) {
            cvAddon.setVisibility(View.VISIBLE);
            rvAddonPremium.setLayoutManager(new LinearLayoutManager(this));
            addonAdapterNew = new PremiumBreakUpAddonAdapter(this, addonListNew, listMobileAddOn);
            rvAddonPremium.setAdapter(addonAdapterNew);
        } else {
            cvAddon.setVisibility(View.GONE);
        }

    }

    private void initViews() {
        rvOwnDamage = (RecyclerView) findViewById(R.id.rvOwnDamage);
        rvLiability = (RecyclerView) findViewById(R.id.rvLiability);
        rvAddonPremium = (RecyclerView) findViewById(R.id.rvAddonPremium);

        //txtPlanName = (TextView) findViewById(R.id.txtPlanName);
        tvTotalPremium = (TextView) findViewById(R.id.tvTotalPremium);
        tvGst = (TextView) findViewById(R.id.tvGst);
        tvNetPremium = (TextView) findViewById(R.id.tvNetPremium);
        ivCross = (ImageView) findViewById(R.id.ivCross);
        ivShare = (ImageView) findViewById(R.id.ivShare);
        btnBuy = (LinearLayout) findViewById(R.id.btnBuy);
        btnBackToQuote = (Button) findViewById(R.id.btnBackToQuote);
        cvAddon = (CardView) findViewById(R.id.cvAddon);
        txtIDV = (TextView) findViewById(R.id.txtIDV);
        txtFinalPremium = (TextView) findViewById(R.id.txtFinalPremium);
        tvAddonTotal = (TextView) findViewById(R.id.tvAddonTotal);
        //ivCross.setImageResource(dbPersistanceController.getInsurerImage(Integer.parseInt(responseEntity.getInsurer().getInsurer_ID())));
        /*Glide.with(this)
                //.load(dbgetProfessionalID1(Integer.parseInt(responseEntity.getInsurer().getInsurer_ID())))
                .load("http://www.policyboss.com/Images/insurer_logo/" + responseEntity.getInsurer().getInsurer_Logo_Name())
                .into(ivCross);*/
        try {

            /*int logo = new DBPersistanceController(this)
                    .getInsurerLogo(Integer.parseInt(responseEntity.getInsurer_Id()));

            Glide.with(this).load(logo)
                    .into(ivCross);*/

            Glide.with(this).load(Utility.getInsurerImage(responseEntity.getInsurer_Id()))
                    .into(ivCross);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<PremiumBreakUpAdapterEntity> getDamageList() {
        List<PremiumBreakUpAdapterEntity> damageList = new ArrayList<PremiumBreakUpAdapterEntity>();
        OwnDamageEntity ownDamageEntity = responseEntity.getPremium_Breakup().getOwn_damage();
        if (!ownDamageEntity.getOd_basic().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("Basic OD", ownDamageEntity.getOd_basic()));
        }
        if (!ownDamageEntity.getOd_disc().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("OD Discount", "-" + ownDamageEntity.getOd_disc()));
        }
        if (!ownDamageEntity.getOd_non_elect_access().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("NEA Premium", ownDamageEntity.getOd_non_elect_access()));
        }
        if (!ownDamageEntity.getOd_elect_access().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("EA Premium", ownDamageEntity.getOd_elect_access()));
        }
        if (!ownDamageEntity.getOd_cng_lpg().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("Bi Fuel Kit", ownDamageEntity.getOd_cng_lpg()));
        }
        if (!ownDamageEntity.getOd_disc_anti_theft().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("Anti Theft Disc.", ownDamageEntity.getOd_disc_anti_theft()));
        }
        if (!ownDamageEntity.getOd_disc_vol_deduct().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("Voluntary Disc.", ownDamageEntity.getOd_disc_vol_deduct()));
        }
        if (!ownDamageEntity.getOd_disc_aai().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("AAI Discount", ownDamageEntity.getOd_disc_aai()));
        }
        if (!ownDamageEntity.getOd_disc_ncb().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("NCB", "-" + ownDamageEntity.getOd_disc_ncb()));
        }
        if (!ownDamageEntity.getOd_loading().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("Underwriter Loading", ownDamageEntity.getOd_loading()));
        }
        if (!ownDamageEntity.getOd_final_premium().equals("0")) {
            damageList.add(new PremiumBreakUpAdapterEntity("Total OD Premium", ownDamageEntity.getOd_final_premium()));
        }

        return damageList;
    }

    public List<PremiumBreakUpAdapterEntity> getLiabilityList() {
        List<PremiumBreakUpAdapterEntity> liabilityList = new ArrayList<PremiumBreakUpAdapterEntity>();
        LiabilityEntity liabilityEntity = responseEntity.getPremium_Breakup().getLiability();

        if (!liabilityEntity.getTp_basic().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("Basic 3rd Party Premium", liabilityEntity.getTp_basic()));
        }
        if (!liabilityEntity.getTp_cover_owner_driver_pa().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("PA Cover for Owner Driver", liabilityEntity.getTp_cover_owner_driver_pa()));
        }
        if (!liabilityEntity.getTp_cover_unnamed_passenger_pa().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("Unnamed PA Cover for Passenger", liabilityEntity.getTp_cover_unnamed_passenger_pa()));
        }
        if (!liabilityEntity.getTp_cover_named_passenger_pa().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("Named PA Cover for Passenger", liabilityEntity.getTp_cover_named_passenger_pa()));
        }
        if (!liabilityEntity.getTp_cover_paid_driver_pa().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("PA Cover for Paid Driver", liabilityEntity.getTp_cover_paid_driver_pa()));
        }
        if (!liabilityEntity.getTp_cover_paid_driver_ll().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("Legal Liability to Paid Driver", liabilityEntity.getTp_cover_paid_driver_ll()));
        }
        if (!liabilityEntity.getTp_cng_lpg().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("Bi Fuel Kit Liability", liabilityEntity.getTp_cng_lpg()));
        }
        if (!liabilityEntity.getTp_final_premium().equals("0")) {
            liabilityList.add(new PremiumBreakUpAdapterEntity("Total Liability Premium", liabilityEntity.getTp_final_premium()));
        }
        return liabilityList;
    }

    public List<PremiumBreakUpAdapterEntity> getAddonList() {
        List<PremiumBreakUpAdapterEntity> addonList = new ArrayList<PremiumBreakUpAdapterEntity>();

        if (responseEntity.getListAppliedAddons() != null && responseEntity.getListAppliedAddons().size() > 0) {
            List<AppliedAddonsPremiumBreakup> appliedAddonsPremiumBreakups = responseEntity.getListAppliedAddons();
            for (AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup : appliedAddonsPremiumBreakups) {
                addonList.add(new PremiumBreakUpAdapterEntity(appliedAddonsPremiumBreakup.getAddonName(), "" + appliedAddonsPremiumBreakup.getPriceAddon()));
            }
            //addonList.add(new PremiumBreakUpAdapterEntity("Total Addon Premium", responseEntity.getTotalAddonAplied()));
        }
        return addonList;
    }

    public List<PremiumBreakUpAddonEntity> getAddonListNew() {
        List<PremiumBreakUpAddonEntity> addonList = new ArrayList<PremiumBreakUpAddonEntity>();

        if (responseEntity.getListAppliedAddons() != null && responseEntity.getListAppliedAddons().size() > 0) {
            List<AppliedAddonsPremiumBreakup> appliedAddonsPremiumBreakups = responseEntity.getListAppliedAddons();
            for (AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup : appliedAddonsPremiumBreakups) {
                addonList.add(new PremiumBreakUpAddonEntity(appliedAddonsPremiumBreakup.getAddonName(), "" + appliedAddonsPremiumBreakup.getPriceAddon()));
            }
            //addonList.add(new PremiumBreakUpAddonEntity("Total Addon Premium", responseEntity.getTotalAddonAplied()));
        }
        return addonList;
    }

    @Override
    public void onClick(View view) {
        QuoteFragment.isShowing = false;
        BikeQuoteFragment.isShowing = false;

        int i = view.getId();
        if (i == R.id.ivCross) {
            finish();

        } else if (i == R.id.btnBuy) {
            Intent resultIntent1 = new Intent();
            resultIntent1.putExtra("PREMIUM", responseEntity);
            setResult(Activity.RESULT_OK, resultIntent1);
            // finish();
            redirectToBuy(responseEntity.getService_Log_Unique_Id());

        } else if (i == R.id.btnBackToQuote) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("PREMIUM", responseEntity);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();

        } else if (i == R.id.ivShare) {/*if (Utility.checkShareStatus(this) == 1) {
                    jsonShareString = getShareData();
                    if (jsonShareString != null && responseJson != null) {


                        if (getIntent().hasExtra("RESPONSE_BIKE")) {
                            Intent intent = new Intent(this, ShareQuoteActivity.class);
                            intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "BIKE_SINGLE_QUOTE");
                            intent.putExtra("RESPONSE", responseJson);
                            intent.putExtra("OTHER", jsonShareString);
                            startActivity(intent);
                        } else if (getIntent().hasExtra("RESPONSE_CAR")) {
                            Intent intent = new Intent(this, ShareQuoteActivity.class);
                            intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "CAR_SINGLE_QUOTE");
                            intent.putExtra("RESPONSE", responseJson);
                            intent.putExtra("OTHER", jsonShareString);
                            startActivity(intent);
                        }
                    }
                } else {
                    openPopUp(ivShare, "Message", "Your POSP status is INACTIVE", "OK", true);
                }*/
            new AsyncShareJson().execute();

        }
    }

    private String getShareData() {
        JSONObject jsonObject = new JSONObject();
        if (getIntent().hasExtra("RESPONSE_CAR")) {
            if (summaryEntity != null && carMasterEntity != null) {

                //return gson.toJson(carMasterEntity);
                try {
                    jsonObject.put("NAME", summaryEntity.getRequest_Core().getFirst_name());
                    if (summaryEntity.getRequest_Core().getLast_name() != null && !summaryEntity.getRequest_Core().getLast_name().equals(""))
                        jsonObject.put("NAME", summaryEntity.getRequest_Core().getFirst_name() + " " + summaryEntity.getRequest_Core().getLast_name());
                    if (!summaryEntity.getRequest_Core().getRegistration_no().endsWith("-AA-1234"))
                        jsonObject.put("VECHILE_NAME", carMasterEntity.getMake_Name() + " ," + carMasterEntity.getModel_Name() + " -  " + summaryEntity.getRequest_Core().getRegistration_no());
                    else
                        jsonObject.put("VECHILE_NAME", carMasterEntity.getMake_Name() + " " + carMasterEntity.getModel_Name());
                    jsonObject.put("POLICY_EXP", summaryEntity.getRequest_Core().getPolicy_expiry_date());
                    jsonObject.put("MFG_DATE", summaryEntity.getRequest_Core().getVehicle_manf_date());
                    jsonObject.put("NCB", summaryEntity.getRequest_Core().getVehicle_ncb_current());
                    jsonObject.put("CLAIM", summaryEntity.getRequest_Core().getIs_claim_exists());
                    jsonObject.put("VECHILE_CC", carMasterEntity.getCubic_Capacity() + "CC");
                    jsonObject.put("vehicle_insurance_type", summaryEntity.getRequest_Core().getVehicle_insurance_type());
                    jsonObject.put("vehicle_registration_date", summaryEntity.getRequest_Core().getVehicle_registration_date());

                    return jsonObject.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (getIntent().hasExtra("RESPONSE_BIKE")) {
            if (summaryEntity != null && bikeMasterEntity != null) {

                try {

                    jsonObject.put("NAME", summaryEntity.getRequest_Core().getFirst_name());
                    if (summaryEntity.getRequest_Core().getLast_name() != null && !summaryEntity.getRequest_Core().getLast_name().equals(""))
                        jsonObject.put("NAME", summaryEntity.getRequest_Core().getFirst_name() + " " + summaryEntity.getRequest_Core().getLast_name());

                    if (!summaryEntity.getRequest_Core().getRegistration_no().endsWith("-AA-1234"))
                        jsonObject.put("VECHILE_NAME", bikeMasterEntity.getMake_Name() + " ," + bikeMasterEntity.getModel_Name() + " -  " + summaryEntity.getRequest_Core().getRegistration_no());
                    else
                        jsonObject.put("VECHILE_NAME", bikeMasterEntity.getMake_Name() + " " + bikeMasterEntity.getModel_Name());


                    // jsonObject.put("VECHILE_NAME", bikeMasterEntity.getMake_Name() + " " + bikeMasterEntity.getModel_Name() + " - " + bikeMasterEntity.getCubic_Capacity() + "CC");
                    jsonObject.put("POLICY_EXP", summaryEntity.getRequest_Core().getPolicy_expiry_date());
                    jsonObject.put("MFG_DATE", summaryEntity.getRequest_Core().getVehicle_manf_date());
                    jsonObject.put("NCB", summaryEntity.getRequest_Core().getVehicle_ncb_current());
                    jsonObject.put("CLAIM", summaryEntity.getRequest_Core().getIs_claim_exists());
                    jsonObject.put("VECHILE_CC", bikeMasterEntity.getCubic_Capacity() + "CC");
                    jsonObject.put("vehicle_insurance_type", summaryEntity.getRequest_Core().getVehicle_insurance_type());
                    jsonObject.put("vehicle_registration_date", summaryEntity.getRequest_Core().getVehicle_registration_date());
                    return jsonObject.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /*public void redirectToBuy(String Service_Log_Unique_Id) {
        String URL = "http://qa.policyboss.com/buynowprivatecar/2/arn-5vsdcdks-ifxf-lbo7-imvr-ycc3axgrfrwe/nonposp/0";
        String url = "http://qa.policyboss.com/";
        //String url = "http://policyboss.com/";
        String title = "";
        String name = "";
        url = url + "buynowprivatecar/4/" + Service_Log_Unique_Id + "/nonposp/0";
        title = "Motor Insurance";

        if (getIntent().hasExtra("RESPONSE_BIKE")) {
            startActivity(new Intent(this, CommonWebViewActivity.class)
                    .putExtra("URL", Utility.getTwoWheelerUrl(this, Service_Log_Unique_Id))
                    .putExtra("NAME", name)
                    .putExtra("TITLE", title));
        }
        if (getIntent().hasExtra("RESPONSE_CAR")) {
            startActivity(new Intent(this, CommonWebViewActivity.class)
                    .putExtra("URL", Utility.getMotorUrl(this, Service_Log_Unique_Id))
                    .putExtra("NAME", name)
                    .putExtra("TITLE", title));
        }
    }*/

    public void redirectToBuy(String Service_Log_Unique_Id) {
        if (Utility.checkShareStatus(this) == 1) {
            String imgPath = "http://qa.policyboss.com/Images/insurer_logo/" + responseEntity.getInsurer().getInsurer_Logo_Name();
            //convert quote to application server
            new QuoteApplicationController(this).convertQuoteToApp(
                    "" + vechileRequestId,
                    responseEntity.getInsurer_Id(),
                    imgPath,
                    this);

            if (getIntent().hasExtra("RESPONSE_CAR")) {
                startActivity(new Intent(this, CommonWebViewActivity.class)
                        .putExtra("URL", Utility.getMotorUrl(this, Service_Log_Unique_Id))
                        .putExtra("NAME", "MOTOR INSURANCE")
                        .putExtra("TITLE", "MOTOR INSURANCE"));
            }
            if (getIntent().hasExtra("RESPONSE_BIKE")) {
                startActivity(new Intent(this, CommonWebViewActivity.class)
                        .putExtra("URL", Utility.getTwoWheelerUrl(this, Service_Log_Unique_Id))
                        .putExtra("NAME", "TWO WHEELER")
                        .putExtra("TITLE", "TWO WHEELER"));
            }

        } else {
            openPopUp(btnBuy, "Message", "Your POSP status is INACTIVE", "OK", true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
            return true;
        } else if (i == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    private String getRupeesRound(String strText) {
        return "\u20B9 " + Math.round(Double.parseDouble(strText));
    }

    private double getDigitPrecision(double value) {
        return Double.parseDouble(new DecimalFormat("##.##").format(value));
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

    }

    @Override
    public void OnSuccess(magicfinmart.datacomp.com.finmartserviceapi.motor.APIResponse response, String message) {

    }

    @Override
    public void OnFailure(Throwable t) {

    }


    class AsyncShareJson extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            responseJson = gson.toJson(responseEntity);
            return responseJson;
        }

        @Override
        protected void onPostExecute(String s) {
            responseJson = s;

            if (Utility.checkShareStatus(PremiumBreakUpActivity.this) == 1) {
                jsonShareString = getShareData();
                if (jsonShareString != null && responseJson != null) {


                    if (getIntent().hasExtra("RESPONSE_BIKE")) {
                        Intent intent = new Intent(PremiumBreakUpActivity.this, ShareQuoteActivity.class);
                        intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "BIKE_SINGLE_QUOTE");
                        intent.putExtra("RESPONSE", responseJson);
                        intent.putExtra("OTHER", jsonShareString);
                        startActivity(intent);
                    } else if (getIntent().hasExtra("RESPONSE_CAR")) {
                        Intent intent = new Intent(PremiumBreakUpActivity.this, ShareQuoteActivity.class);
                        intent.putExtra(Constants.SHARE_ACTIVITY_NAME, "CAR_SINGLE_QUOTE");
                        intent.putExtra("RESPONSE", responseJson);
                        intent.putExtra("OTHER", jsonShareString);
                        startActivity(intent);
                    }
                }
            } else {
                openPopUp(ivShare, "Message", "Your POSP status is INACTIVE", "OK", true);
            }
        }

    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        dialog.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    private double getRound(String strText) {
        double value = Double.parseDouble(strText);
        return Double.parseDouble(new DecimalFormat("##.##").format(value));
    }

    public void updateAddonToserver(List<MobileAddOn> addOnList) {
        SaveAddOnRequestEntity entity = new SaveAddOnRequestEntity();
        for (int i = 0; i < addOnList.size(); i++) {
            MobileAddOn mobileAddOn = addOnList.get(i);

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
        if (getIntent().hasExtra("RESPONSE_CAR"))
            entity.setSearch_reference_number(Constants.getSharedPreference(this).getString(Utility.CARQUOTE_UNIQUEID, ""));
        if (getIntent().hasExtra("RESPONSE_BIKE"))
            entity.setSearch_reference_number(Constants.getSharedPreference(this).getString(Utility.BIKEQUOTE_UNIQUEID, ""));

        new MotorController(this).saveAddOn(entity, this);
    }

    public double applyPositiveAddons(List<MobileAddOn> addOnList) {
        ResponseEntity entity = null;
        try {
            entity = (ResponseEntity) responseEntity.clone();
        } catch (CloneNotSupportedException e) {
            entity = responseEntity;
            e.printStackTrace();
        }

        double addonValue = 0;
        entity.setAddonApplied(false);
        entity.setListAppliedAddons(null);

        if (entity.getAddon_List() != null) {
            List<AppliedAddonsPremiumBreakup> listAppliedAddonPremium =
                    new ArrayList<AppliedAddonsPremiumBreakup>();// list of applied addon

            //region list of available addons
            for (int i = 0; i < addOnList.size(); i++) {

                MobileAddOn mobileAddOn = addOnList.get(i);
                // check if addon is selected
                if (!mobileAddOn.isSelected()) {
                    continue;
                }

                //region addon_zero_dep_cover
                if (mobileAddOn.getAddonKey().matches("addon_zero_dep_cover")) {

                    if (entity.getAddon_List().getAddon_zero_dep_cover() != 0) {
                        entity.setAddonApplied(true);
                        AppliedAddonsPremiumBreakup appliedAddonsPremiumBreakup = new AppliedAddonsPremiumBreakup();
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_zero_dep_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_road_assist_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_ncb_protection_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_engine_protector_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_invoice_price_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_key_lock_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_consumable_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_daily_allowance_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_windshield_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_passenger_assistance_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_tyre_coverage_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_personal_belonging_loss_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_inconvenience_allowance_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_medical_expense_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_hospital_cash_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_ambulance_charge_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_rodent_bite_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_losstime_protection_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_hydrostatic_lock_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_guaranteed_auto_protection_cover"));
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
                        appliedAddonsPremiumBreakup.setAddonName(dbPersistanceController.getAddonName("addon_final_premium"));
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
            //entity.setListAppliedAddons(listAppliedAddonPremium);
            entity.setPremiumBreakUpAddonEntities(addonListNew);
            responseEntity = entity;
            //endregion
        }

        addOnTotal = addonValue;
        bindData(entity);
        return addonValue;
    }


    public void updateAddonList(List<PremiumBreakUpAddonEntity> premiumBreakupEntities) {
        this.addonListNew = premiumBreakupEntities;
    }
}


