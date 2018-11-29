package com.datacomp.magicfinmart.home;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.IncomeCalculator.IncomeCalculatorActivity;
import com.datacomp.magicfinmart.IncomeCalculator.IncomePotentialActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.change_password.ChangePasswordFragment;
import com.datacomp.magicfinmart.contact_lead.ContactLeadActivity;
import com.datacomp.magicfinmart.crnpolicy.crnpolicyActivity;
import com.datacomp.magicfinmart.dashboard.DashboardFragment;
import com.datacomp.magicfinmart.generatelead.GenerateLeadActivity;
import com.datacomp.magicfinmart.health.healthquotetabs.HealthQuoteBottomTabsActivity;
import com.datacomp.magicfinmart.healthcheckupplans.HealthCheckUpPlansActivity;
import com.datacomp.magicfinmart.helpfeedback.HelpFeedBackActivity;
import com.datacomp.magicfinmart.helpfeedback.raiseticket.RaiseTicketActivity;
import com.datacomp.magicfinmart.knowledgeguru.KnowledgeGuruActivity;
import com.datacomp.magicfinmart.loan_fm.balancetransfer.addquote.BLMainActivity;
import com.datacomp.magicfinmart.loan_fm.homeloan.addquote.HLMainActivity;
import com.datacomp.magicfinmart.loan_fm.homeloan.loan_apply.HomeLoanApplyActivity;
import com.datacomp.magicfinmart.loan_fm.laploan.addquote.LAPMainActivity;
import com.datacomp.magicfinmart.loan_fm.personalloan.addquote.PLMainActivity;
import com.datacomp.magicfinmart.messagecenter.messagecenteractivity;
import com.datacomp.magicfinmart.motor.privatecar.activity.InputQuoteBottmActivity;
import com.datacomp.magicfinmart.motor.twowheeler.activity.BikeAddQuoteActivity;
import com.datacomp.magicfinmart.mps.KnowMoreMPSFragment;
import com.datacomp.magicfinmart.mps.MPSFragment;
import com.datacomp.magicfinmart.myaccount.MyAccountActivity;
import com.datacomp.magicfinmart.notification.NotificationActivity;
import com.datacomp.magicfinmart.notification.NotificationSmsActivity;
import com.datacomp.magicfinmart.offline_quotes.OfflineQuotesListActivity;
import com.datacomp.magicfinmart.onlineexpressloan.QuoteList.AppliedOnlineLoanListActivity;
import com.datacomp.magicfinmart.pendingcases.PendingCasesActivity;
import com.datacomp.magicfinmart.posp.POSPListFragment;
import com.datacomp.magicfinmart.posp.PospEnrollment;
import com.datacomp.magicfinmart.salesmaterial.SalesMaterialActivity;
import com.datacomp.magicfinmart.scan_vehicle.VehicleScanActivity;
import com.datacomp.magicfinmart.share_data.ShareDataFragment;
import com.datacomp.magicfinmart.splashscreen.SplashScreenActivity;
import com.datacomp.magicfinmart.term.compareterm.CompareTermActivity;
import com.datacomp.magicfinmart.transactionhistory.nav_transactionhistoryActivity;
import com.datacomp.magicfinmart.underconstruction.UnderConstructionActivity;
import com.datacomp.magicfinmart.utility.CircleTransform;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.vehicle_details.VehicleDetailFragment;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;
import com.datacomp.magicfinmart.whatsnew.WhatsNewActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MenuItemEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MenuMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.NotifyEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ConstantsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MpsResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MyAcctDtlResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.UserConstatntResponse;

public class HomeActivity extends BaseActivity implements IResponseSubcriber, BaseActivity.PopUpListener,
        BaseActivity.WebViewPopUpListener, BaseActivity.PermissionListener {

    final String TAG = "HOME";
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    TextView textNotifyItemCount, txtEntityName, txtDetails, txtReferalCode, txtFbaID, txtPospNo, txtErpID,txtknwyour;
    ImageView ivProfile;
    LoginResponseEntity loginResponseEntity;
    DBPersistanceController db;
    String versionNAme;
    PackageInfo pinfo;
    PrefManager prefManager;
    int forceUpdate, checkfirstmsg_call;
    ConstantEntity constantEntity;
    AlertDialog mpsDialog;
    String[] permissionsRequired = new String[]{Manifest.permission.CALL_PHONE};
    UserConstantEntity userConstantEntity;
    MenuMasterResponse menuMasterResponse;


    //region broadcast receiver
    public BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction() != null) {
                if (intent.getAction().equalsIgnoreCase(Utility.PUSH_BROADCAST_ACTION)) {
                    int notifyCount = prefManager.getNotificationCounter();

                    if (notifyCount == 0) {
                        textNotifyItemCount.setVisibility(View.GONE);
                    } else {
                        textNotifyItemCount.setVisibility(View.VISIBLE);
                        textNotifyItemCount.setText("" + String.valueOf(notifyCount));
                    }
                } else if (intent.getAction().equalsIgnoreCase(Utility.USER_PROFILE_ACTION)) {
                    String PROFILE_PATH = intent.getStringExtra("PROFILE_PATH");

                    Glide.with(HomeActivity.this)
                            .load(Uri.parse(PROFILE_PATH))
                            .placeholder(R.drawable.finmart_user_icon)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .override(64, 64)
                            .transform(new CircleTransform(HomeActivity.this)) // applying the image transformer
                            .into(ivProfile);

                } else if (intent.getAction().equalsIgnoreCase(Utility.USER_DASHBOARD)) {

                }
            }

        }
    };

    //endregion

    @Override
    public void dialogExit() {
        super.dialogExit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        registerPopUp(this);
        registerPermission(this);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);
        toolbar.setTitle("MAGIC FIN-MART");


        try {
            pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionNAme = pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        try {
            Utility.getMacAddress(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
        db = new DBPersistanceController(this);
        loginResponseEntity = db.getUserData();
        userConstantEntity = db.getUserConstantsData();
        prefManager = new PrefManager(this);

        getNotificationAction();

        init_headers();


        if (savedInstanceState == null) {
            selectHome();
        }


        // will be called once when ever app is opened

        if (loginResponseEntity != null) {

            new MasterController(this).getMenuMaster(this);
            new MasterController(this).getInsuranceSubType(this);
        }

        checkfirstmsg_call = Integer.parseInt(prefManager.getCheckMsgFirst());
        if (checkfirstmsg_call == 0) {


            String type = "";
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {

                type = bundle.getString("MarkTYPE");
                if (!type.equals("FROM_HOME")) {
                    showMArketingPopup();
                }
            } else {
                prefManager.updateCheckMsgFirst("" + 1);
                showMArketingPopup();
            }


        }
        //region navigation click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Closing drawer on item click
                drawerLayout.closeDrawers();
                //Check to see which item was being clicked and perform appropriate action

                Constants.hideKeyBoard(drawerLayout, HomeActivity.this);
                Fragment fragment = null;

                //hide keyboard
                Constants.hideKeyBoard(drawerLayout, HomeActivity.this);
                if (menuMasterResponse != null) {
                    for (MenuItemEntity menuItemEntity : menuMasterResponse.getMasterData().getMenu()) {
                        int sequence = Integer.parseInt(menuItemEntity.getSequence());
                        sequence = (sequence * 100) + 1;
                        if (menuItem.getItemId() == sequence) {
                            startActivity(new Intent(HomeActivity.this, CommonWebViewActivity.class)
                                    .putExtra("URL", menuItemEntity.getLink())
                                    .putExtra("NAME", menuItemEntity.getMenuname())
                                    .putExtra("TITLE", menuItemEntity.getMenuname()));
                            return true;
                        }
                    }
                }


                int i = menuItem.getItemId();
                if (i == R.id.nav_generateLead) {
                    startActivity(new Intent(HomeActivity.this, GenerateLeadActivity.class));


                    //added by Nilesh
                } else if (i == R.id.nav_vehicleinfo) {
                    getSupportActionBar().setTitle("VEHICLE DETAIL");
                    fragment = new VehicleDetailFragment();

                } else if (i == R.id.nav_expressLoan) {
                    startActivity(new Intent(HomeActivity.this, AppliedOnlineLoanListActivity.class));

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                } else if (i == R.id.nav_yesbankbot) {
                    startActivity(new Intent(HomeActivity.this, CommonWebViewActivity.class)
                            .putExtra("URL", "https://yesbankbot.buildquickbots.com/chat/rupeeboss/staff/?userid=" + String.valueOf(loginResponseEntity.getFBAId()) + "&usertype=FBA&vkey=b34f02e9-8f1c")
                            .putExtra("NAME", "" + "YES BANK BOT")
                            .putExtra("TITLE", "" + "YES BANK BOT"));


                } else if (i == R.id.nav_home) {
                    fragment = new DashboardFragment();
                    getSupportActionBar().setTitle("MAGIC FIN-MART");
                    //Toast.makeText(HomeActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();

                } else if (i == R.id.nav_sharedata) {
                    fragment = new ShareDataFragment();
                    getSupportActionBar().setTitle("SHARE DATA");

                } else if (i == R.id.nav_changepassword) {
                    fragment = new ChangePasswordFragment();
                    getSupportActionBar().setTitle("CHANGE PASSWORD");

                    // For rest of the options we just show a toast on click .
                } else if (i == R.id.nav_myaccount) {
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("My ACCOUNT : My ACCOUNT button in menu "), Constants.MY_ACCOUNT), null);
                    startActivity(new Intent(HomeActivity.this, MyAccountActivity.class));
                    //  startActivity(new Intent(HomeActivity.this, HomeLoanApplyActivity.class));
                    // fragment = new BasFragment();
                    // getSupportActionBar().setTitle("BAS 2016-17");
                    // Toast.makeText(HomeActivity.this, "my_account", Toast.LENGTH_SHORT).show();

                    //startActivity(new Intent(HomeActivity.this, MyAccountActivity.class));


                } else if (i == R.id.nav_pospenrollment) {
                    startActivity(new Intent(HomeActivity.this, PospEnrollment.class));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("Posp Enrollment : posp enrollment button in menu "), Constants.POSP), null);

                } else if (i == R.id.nav_addposp) {
                    fragment = new POSPListFragment();
                    getSupportActionBar().setTitle("POSP List");

                } else if (i == R.id.nav_homeloanApplication) {
                    startActivity(new Intent(HomeActivity.this, HomeLoanApplyActivity.class));

                } else if (i == R.id.nav_offlineQuotes) {
                    startActivity(new Intent(HomeActivity.this, OfflineQuotesListActivity.class));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("Offline Quotes : Offline Quotes button in menu "), Constants.OFFLINE_QUOTES), null);

                } else if (i == R.id.nav_myBusiness) {
                    startActivity(new Intent(HomeActivity.this, UnderConstructionActivity.class));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("My Business : My Business button in menu "), Constants.MY_BUSINESS), null);

                } else if (i == R.id.nav_referFriend) {
                    startActivity(new Intent(HomeActivity.this, UnderConstructionActivity.class));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("Refer A Friend : Refer A Friend button in menu "), Constants.REFER), null);

                } else if (i == R.id.nav_mps) {// DialogMPS();
                    showDialog();
                    new MasterController(HomeActivity.this).getMpsData(HomeActivity.this);

                    // new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("MPS : MPS button in menu "), Constants.MPS), null);
                    //startActivity(new Intent(HomeActivity.this, UnderConstructionActivity.class));

                } else if (i == R.id.nav_helpfeedback) {
                    startActivity(new Intent(HomeActivity.this, HelpFeedBackActivity.class));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("HELP & FEEDBACK : HELP & FEEDBACK button in menu "), Constants.HELP), null);

                    /*case R.id.nav_posptraining:
                        startActivity(new Intent(HomeActivity.this, com.datacomp.magicfinmart.pospapp.login.LoginActivity.class));
                        new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("POPS TRAINING : POPS TRAINING button in menu "), Constants.POSP_TRAINING), null);
                        break;
                    case R.id.nav_selfinspection:
                        startActivity(new Intent(HomeActivity.this, SplashScreen.class));
                        // startActivity(new Intent(HomeActivity.this, PreviewVideoActivity.class));
                        new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("INSPECTION : INSPECTION button in menu "), Constants.INSPECTION), null);
                        break;*/
                } else if (i == R.id.nav_whatsnew) {
                    startActivity(new Intent(HomeActivity.this, WhatsNewActivity.class));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("Whats New : Whats New button in menu "), Constants.WHATSNEW), null);

                } else if (i == R.id.nav_franchise) {
                    startActivity(new Intent(HomeActivity.this, CommonWebViewActivity.class)
                            .putExtra("URL", "http://erp.rupeeboss.com/FM/Franchise_Agreement.pdf")
                            .putExtra("NAME", "LOAN_AGREEMENT")
                            .putExtra("TITLE", "LOAN AGREEMENT"));
                    new TrackingController(HomeActivity.this).sendData(new TrackingRequestEntity(new TrackingData("Whats New : Whats New button in menu "), Constants.WHATSNEW), null);


                } else if (i == R.id.nav_IncomeCalculator) {
                    startActivity(new Intent(HomeActivity.this, IncomeCalculatorActivity.class));


                } else if (i == R.id.nav_IncomePotential) {
                    startActivity(new Intent(HomeActivity.this, IncomePotentialActivity.class));


                } else if (i == R.id.nav_transactionhistory) {
                    startActivity(new Intent(HomeActivity.this, nav_transactionhistoryActivity.class));


                } else if (i == R.id.nav_contact) {
                    startActivity(new Intent(HomeActivity.this, ContactLeadActivity.class));

                } else if (i == R.id.nav_crnpolicy) {
                    startActivity(new Intent(HomeActivity.this, crnpolicyActivity.class));

                } else if (i == R.id.nav_logout) {
                    dialogLogout(HomeActivity.this);

                } else if (i == R.id.nav_scan_vehicle) {// startActivity(new Intent(HomeActivity.this, ScanVehicleActivity.class));
                    startActivity(new Intent(HomeActivity.this, VehicleScanActivity.class));

                } else if (i == R.id.nav_MessageCentre) {
                    MessageCenter();
                    //   startActivity(new Intent(HomeActivity.this, messagecenteractivity.class));

                } else {
                }

                if (fragment != null) {
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, fragment);
                    fragmentTransaction.commit();

                    return true;
                }
                return false;
            }
        });
        //endregion

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.openDrawer,
                R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }




    private void MessageCenter() {

   String     POSPNO=""+userConstantEntity.getPospsendid();
   String     msgurl=""+userConstantEntity.getMessagesender();
        //   empCode="232";
        if(POSPNO.equals("5"))
        {
            startActivity(new Intent(HomeActivity.this, messagecenteractivity.class));


        }else {

            String ipaddress = "0.0.0.0";
            try {
                ipaddress = Utility.getMacAddress(this);
            } catch (Exception io) {
                ipaddress = "0.0.0.0";
            }

            String append = "&ip_address=" + ipaddress
                    + "&app_version=" + Utility.getVersionName(this)
                    + "&device_id=" + Utility.getDeviceId(this);
            String fullmsgurl = msgurl + append;
            startActivity(new Intent(this, CommonWebViewActivity.class)
                    .putExtra("URL", fullmsgurl)
                    .putExtra("NAME", "Message Center")
                    .putExtra("TITLE", "Message Center"));

            //   incl_nav.setVisibility(View.VISIBLE);
            //  new PendingController(this).gettransactionhistory(empCode, "1", this);
        }
    }





    private void showMArketingPopup() {

        //region popup dashboard

        if (userConstantEntity != null) {
            if (userConstantEntity.getMarketinghomeenabled() != null &&
                    userConstantEntity.getMarketinghomeenabled().equals("1")) {

                int serverPopUpCount = Integer.parseInt(userConstantEntity.getMarketinghomemaxcount());
                int localPopupCount = Integer.parseInt(prefManager.getPopUpCounter());

                int serverId = Integer.parseInt(userConstantEntity.getMarketinghomepopupid());
                int localId = Integer.parseInt(prefManager.getPopUpId());
                if (localId == 0) {
                    prefManager.updatePopUpId("" + serverId);
                }

                Log.d("COUNTER", "localId -" + localId + "counter - " + localPopupCount);

                if (localId == serverId) {
                    prefManager.updatePopUpId("" + serverId);
                    Log.d("COUNTER", "localId -" + localId + "counter - " + localPopupCount);
                    if (localPopupCount < serverPopUpCount) {
                        localPopupCount = localPopupCount + 1;
                        prefManager.updatePopUpCounter("" + localPopupCount);
                        openPopUp(ivProfile, userConstantEntity.getMarketinghometitle(), userConstantEntity.getMarketinghomedesciption(), "OK", true);

                    }

                } else {
                    prefManager.updatePopUpId("" + serverId);
                    prefManager.updatePopUpCounter("0");
                    localPopupCount = Integer.parseInt(prefManager.getPopUpCounter());
                    Log.d("COUNTER-", "localId -" + localId + "counter - " + localPopupCount);
                    if (localPopupCount < serverPopUpCount) {
                        localPopupCount = localPopupCount + 1;
                        prefManager.updatePopUpCounter("" + localPopupCount);
                        openPopUp(ivProfile, userConstantEntity.getMarketinghometitle(), userConstantEntity.getMarketinghomedesciption(), "OK", true);

                    }
                }

            }

        }

        //endregion
    }

    private void addDynamicMenu(List<MenuItemEntity> list) {
        Menu menu = navigationView.getMenu();

        for (int i = 1; i <= list.size() && (list.get(i - 1).getIsActive() == 1); i++) {
            int sequence = Integer.parseInt(list.get(i - 1).getSequence());
            sequence = (sequence * 100) + 1;
            final MenuItem menuItem = menu.add(R.id.dashboard_menu_group, sequence, sequence, list.get(i - 1).getMenuname());
            Glide.with(this)
                    .load(list.get(i - 1).getIconimage())
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            menuItem.setIcon(resource);
                        }
                    });


        }

        /*final MenuItem menuItem = menu.add(R.id.dashboard_menu_group, R.id.nav_myaccount, 0, "itemid");
        Glide.with(this)
                .load("https://cdn0.iconfinder.com/data/icons/small-n-flat/24/678110-sign-info-128.png")
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        menuItem.setIcon(resource);
                    }
                });*/
    }

    public void selectHome() {
        getSupportActionBar().setTitle("MAGIC FIN-MART");
        Fragment fragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
    }

    // endregion


    private void init_headers() {

        View headerView = navigationView.getHeaderView(0);
        txtEntityName = (TextView) headerView.findViewById(R.id.txtEntityName);
        txtknwyour= (TextView) headerView.findViewById(R.id.txtknwyour);
        txtDetails = (TextView) headerView.findViewById(R.id.txtDetails);
        txtReferalCode = (TextView) headerView.findViewById(R.id.txtReferalCode);
        txtFbaID = (TextView) headerView.findViewById(R.id.txtFbaID);
        txtPospNo = (TextView) headerView.findViewById(R.id.txtPospNo);
        txtErpID = (TextView) headerView.findViewById(R.id.txtErpID);
        ivProfile = (ImageView) headerView.findViewById(R.id.ivProfile);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent shareIntent = new Intent(HomeActivity.this, MyAccountActivity.class);
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(ivProfile, "profileTransition");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this, pairs);
                    startActivity(shareIntent, options.toBundle());
                } else {
                    startActivity(new Intent(HomeActivity.this, MyAccountActivity.class));
                }


            }
        });

        txtknwyour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebViewPopUp(txtFbaID, userConstantEntity.getNotificationpopupurl(), true, HomeActivity.this);

            }
        });
        txtEntityName.setText("Ver." + versionNAme);

        if (loginResponseEntity != null) {

            txtDetails.setText("" + loginResponseEntity.getFullName());
            txtFbaID.setText("Fba Id - " + loginResponseEntity.getFBAId());
            txtReferalCode.setText("Referral Code - " + loginResponseEntity.getReferer_code());
        } else {
            txtDetails.setText("");
            txtFbaID.setText("Fba Id - ");
            txtReferalCode.setText("Referral Code - ");
        }
        if (userConstantEntity != null) {
            txtPospNo.setText("Posp No - " + userConstantEntity.getPospselfid());
            txtErpID.setText("Erp Id - " + userConstantEntity.getERPID());
            Glide.with(HomeActivity.this)
                    .load(Uri.parse(userConstantEntity.getLoansendphoto()))
                    .placeholder(R.drawable.circle_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .override(64, 64)
                    .transform(new CircleTransform(HomeActivity.this)) // applying the image transformer
                    .into(ivProfile);
        } else {
            txtPospNo.setText("");
            txtErpID.setText("");
            Glide.with(HomeActivity.this)
                    .load(R.drawable.finmart_user_icon)
                    .placeholder(R.drawable.circle_placeholder)
                    .transform(new CircleTransform(HomeActivity.this)) // applying the image transformer
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .override(64, 64)
                    .into(ivProfile);
        }


    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            dialogExit();
            //super.onBackPressed();
        }
    }

    protected boolean isNavDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void getNotificationAction() {

        // region Activity Open Usnig Notification

        if (getIntent().getExtras() != null) {

            // For getting User Click Action
            if (getIntent().getExtras().getParcelable(Utility.PUSH_NOTIFY) != null) {
                NotifyEntity notifyEntity = getIntent().getExtras().getParcelable(Utility.PUSH_NOTIFY);
                String MESSAGEID = notifyEntity.getMessage_id();

                new RegisterController(HomeActivity.this).getUserClickActionOnNotification(MESSAGEID, null);
            }
            // step1: boolean verifyLogin = prefManager.getIsUserLogin();
            // region verifyUser : when user logout and when Apps in background
            if (loginResponseEntity == null) {

                NotifyEntity notifyEntity = getIntent().getExtras().getParcelable(Utility.PUSH_NOTIFY);
                if (notifyEntity == null) {
                    return;
                }

                prefManager.setPushNotifyPreference(notifyEntity);
                prefManager.setSharePushType(notifyEntity.getNotifyFlag());

                Intent intent = new Intent(this, SplashScreenActivity.class);
                startActivity(intent);
                finish();


            }
            //endregion

            //  region step2: For Notification come via Login for user credential  (step2 perform after step1)
            else if (getIntent().getStringExtra(Utility.PUSH_LOGIN_PAGE) != null) {
                String pushLogin = getIntent().getStringExtra(Utility.PUSH_LOGIN_PAGE);
                if (pushLogin.equals("555")) {

                    NotifyEntity notifyEntity;
                    String type = "", title = "", body = "", web_url = "", web_title = "", web_name = "";
                    if (prefManager.getPushNotifyPreference() != null) {
                        notifyEntity = prefManager.getPushNotifyPreference();

                        type = notifyEntity.getNotifyFlag();
                        title = notifyEntity.getTitle();
                        body = notifyEntity.getBody();
                        web_url = notifyEntity.getWeb_url();
                        web_title = notifyEntity.getWeb_title();

                    }

                    prefManager.clearNotification();

                    if (type.matches("NL")) {
                        Intent intent = new Intent(this, NotificationActivity.class);
                        startActivity(intent);

                    } else if (type.matches("MSG")) {

                        startActivity(new Intent(HomeActivity.this, NotificationSmsActivity.class)
                                .putExtra("NOTIFY_TITLE", title)
                                .putExtra("NOTIFY_BODY", body));

                    } else if (type.matches("WB")) {

                        startActivity(new Intent(HomeActivity.this, CommonWebViewActivity.class)
                                .putExtra("URL", web_url)
                                .putExtra("NAME", web_name)
                                .putExtra("TITLE", web_title));

                    }
                }

            }
            //endregion

            // region user already logged in and app in forground
            else if (getIntent().getExtras().getParcelable(Utility.PUSH_NOTIFY) != null) {
                NotifyEntity notificationEntity = getIntent().getExtras().getParcelable(Utility.PUSH_NOTIFY);
                if (notificationEntity.getNotifyFlag().matches("NL")) {
                    Intent intent = new Intent(this, NotificationActivity.class);
                    startActivity(intent);
                } else if (notificationEntity.getNotifyFlag().matches("MSG")) {

                    String title = notificationEntity.getTitle();
                    String body = notificationEntity.getBody();

                    startActivity(new Intent(HomeActivity.this, NotificationSmsActivity.class)
                            .putExtra("NOTIFY_TITLE", title)
                            .putExtra("NOTIFY_BODY", body));

                } else if (notificationEntity.getNotifyFlag().matches("WB")) {
                    String web_url = notificationEntity.getWeb_url();
                    String web_title = notificationEntity.getWeb_title();
                    String web_name = "";
                    startActivity(new Intent(HomeActivity.this, CommonWebViewActivity.class)
                            .putExtra("URL", web_url)
                            .putExtra("NAME", web_name)
                            .putExtra("TITLE", web_title));

                }
            }
            //endregion

        }

        ///

        //endregion
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_push_notification);

        //  SearchView actionView = (SearchView) menuItem.getActionView();

        View actionView = MenuItemCompat.getActionView(menuItem);
        textNotifyItemCount = (TextView) actionView.findViewById(R.id.notify_badge);
        textNotifyItemCount.setVisibility(View.GONE);

        int PushCount = prefManager.getNotificationCounter();

        if (PushCount == 0) {
            textNotifyItemCount.setVisibility(View.GONE);
        } else {
            textNotifyItemCount.setVisibility(View.VISIBLE);
            textNotifyItemCount.setText("" + String.valueOf(PushCount));
        }

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onOptionsItemSelected(menuItem);


            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        int i = item.getItemId();
        if (i == R.id.action_call) {
            if (userConstantEntity.getMangMobile() != null) {

                if (ActivityCompat.checkSelfPermission(HomeActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[0])) {
                        //Show Information about why you need the permission
                        ActivityCompat.requestPermissions(HomeActivity.this, permissionsRequired, Constants.PERMISSION_CALLBACK_CONSTANT);

                    } else {
                        //Previously Permission Request was cancelled with 'Dont Ask Again',
                        // Redirect to Settings after showing Information about why you need the permission

                        permissionAlert(navigationView, "Need Call Permission", "This app needs Call permission.");


                    }
                } else {
                    if (userConstantEntity.getManagName() != null) {
                        ConfirmAlert("Manager Support", getResources().getString(R.string.RM_Calling) + " " + userConstantEntity.getManagName());
                    }

                }
            }


        } else if (i == R.id.action_push_notification) {
            intent = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivityForResult(intent, Constants.REQUEST_CODE);

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof MpsResponse) {

            if (response.getStatusNo() == 0) {

                prefManager.removeMps();
                prefManager.setMPS(((MpsResponse) response).getMasterData());
                if (loginResponseEntity.getIsFirstLogin() == 1) {
                    DialogMPS();
                } else {
                    DialogMPS();
                }

            }
        } else if (response instanceof MyAcctDtlResponse) {
            if (response.getStatusNo() == 0) {
                if (((MyAcctDtlResponse) response).getMasterData().get(0) != null) {
                    db.updateMyAccountData(((MyAcctDtlResponse) response).getMasterData().get(0));
                }
            }
        } else if (response instanceof UserConstatntResponse) {
            if (response.getStatusNo() == 0) {
                if (((UserConstatntResponse) response).getMasterData() != null) {
                    //db.updateUserConstatntData(((UserConstatntResponse) response).getMasterData());
                    userConstantEntity = ((UserConstatntResponse) response).getMasterData();
                    init_headers();
                    if (prefManager.getPopUpCounter().equals("0")) {
                        showMArketingPopup();
                    }

                    //Notification Url :-1 November
                    int localNotificationenable = Integer.parseInt(prefManager.getNotificationsetting());

                    if (userConstantEntity.getNotificationpopupurltype().toUpperCase().equals("SM")) {
                        if (!userConstantEntity.getNotificationpopupurl().equals("")) {
                            if (prefManager.getIsSeasonal()) {
                                openWebViewPopUp(txtFbaID, userConstantEntity.getNotificationpopupurl(), true, this);
                                prefManager.setIsSeasonal(false);
                            }
                        }
                    } else if (localNotificationenable == 0) {
                        // prefManager.updatePopUpId("" + serverId);
                        if (!userConstantEntity.getNotificationpopupurl().equals("")) {
                            if (prefManager.getIsSeasonal()) {
                                openWebViewPopUp(txtFbaID, userConstantEntity.getNotificationpopupurl(), true, this);
                                prefManager.setIsSeasonal(false);
                            }
                        }

                    }

                    //region birthday and seasonal


                  /*  if (!userConstantEntity.getMarketinghomebirthdayimageurl().equals("")) {

                        if (prefManager.getIsBirthday()) {
                            openWebViewPopUp(txtDetails, userConstantEntity.getMarketinghomebirthdayimageurl(), true, this);
                            prefManager.setIsBirthday(false);
                        }
                    }*/

                    //endregion

                }
            }
        } else if (response instanceof ConstantsResponse) {
            constantEntity = ((ConstantsResponse) response).getMasterData();
            if (response.getStatusNo() == 0) {

                //region check for new vwesion
                int serverVersionCode = Integer.parseInt(((ConstantsResponse) response).getMasterData().getVersionCode());
                if (pinfo != null && pinfo.versionCode < serverVersionCode) {
                    forceUpdate = Integer.parseInt(((ConstantsResponse) response).getMasterData().getIsForceUpdate());
                    if (forceUpdate == 1) {
                        // forced update app
                        openPopUp(navigationView, "UPDATE", "New version available on play store!!!! Please update.", "OK", false);
                    } else {
                        // aap with less version but not forced update
                        if (prefManager.getUpdateShown()) {
                            prefManager.setIsUpdateShown(false);
                            openPopUp(navigationView, "UPDATE", "New version available on play store!!!! Please update.", "OK", true);
                        }
                    }

                    if (new DBPersistanceController(this).getUserData().getIsFirstLogin() == 1) {
                        for (Fragment frg :
                                getSupportFragmentManager().getFragments()) {

                            if (frg instanceof MPSFragment || frg instanceof KnowMoreMPSFragment) {
                                if (!frg.isVisible()) {
                                    Log.d("TAG", "CONSTANTS");
                                    //DialogMPS();
                                }
                            }
                        }
                    }

                } else if (((ConstantsResponse) response).getMasterData().
                        getMPSStatus().toLowerCase().equalsIgnoreCase("p")) {

                    /*for (Fragment frg :
                            getSupportFragmentManager().getFragments()) {

                        if (frg instanceof MPSFragment || frg instanceof KnowMoreMPSFragment) {
                            if (!frg.isVisible()) {
                                if (prefManager.getMps() != null) {
                                    DialogMPS();
                                }
                            }
                        } else {
                            if (prefManager.getMps() != null) {
                                DialogMPS();
                            }
                        }
                    }*/

                }
                //endregion

                hideNavigationItem();
            }
        } else if (response instanceof MenuMasterResponse) {
            if (response.getStatusNo() == 0) {
                menuMasterResponse = (MenuMasterResponse) response;
                prefManager.storeMenuDashboard(menuMasterResponse);
                addDynamicMenu(menuMasterResponse.getMasterData().getMenu());
                //refreshDashboard();


                Intent dashboardIntent = new Intent(Utility.USER_DASHBOARD);
                //dashboardIntent.putExtra("USER_DASHBOARD", ((MenuMasterResponse) response).getMasterData());
                LocalBroadcastManager.getInstance(HomeActivity.this).sendBroadcast(dashboardIntent);
            }
        }


    }


    private void refreshDashboard() {
        /*Intent profileIntent = new Intent(Utility.USER_DASHBOARD);
        profileIntent.putExtra("USER_DASHBOARD", ((MenuMasterResponse) response).getMasterData().get(0).getPrv_file());

        LocalBroadcastManager.getInstance(HomeActivity.this).sendBroadcast(profileIntent);*/

    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        //openPopUp(toolbar, "Message", "" + t.getMessage(), "OK", true);
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        //dialog.cancel();
        if (view.getId() == navigationView.getId()) {
            openAppMarketPlace();
        } else if (view.getId() == ivProfile.getId()) {
            redirectToActivity();
            dialog.cancel();
        }
    }

    private void redirectToActivity() {

        if (userConstantEntity != null && userConstantEntity.getMarketinghometransfertype() != null) {
            int id = Integer.parseInt(userConstantEntity.getMarketinghometransfertype());
            switch (id) {
                case 1:
                    startActivity(new Intent(this, InputQuoteBottmActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(this, BikeAddQuoteActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(this, HealthQuoteBottomTabsActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(this, CompareTermActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(this, HLMainActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(this, PLMainActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(this, LAPMainActivity.class));
                    break;
                case 8:
                    startActivity(new Intent(this, BLMainActivity.class));
                    break;
                case 9:
                    startActivity(new Intent(this, KnowledgeGuruActivity.class));
                    break;
                case 10:
                    startActivity(new Intent(this, SalesMaterialActivity.class));
                    break;
                case 11:
                    startActivity(new Intent(this, PendingCasesActivity.class));
                    break;
                case 12:
                    startActivity(new Intent(this, RaiseTicketActivity.class));
                    break;
                case 13:
                    startActivity(new Intent(this, PospEnrollment.class));
                    break;
                case 14:
                    startActivity(new Intent(this, HealthCheckUpPlansActivity.class));
                    break;
                case 1111:
                    if (userConstantEntity != null && userConstantEntity.getMarketinghomeurl() != null) {
                        startActivity(new Intent(this, CommonWebViewActivity.class)
                                .putExtra("URL", userConstantEntity.getMarketinghomeurl())
                                .putExtra("NAME", userConstantEntity.getMarketinghometitle())
                                .putExtra("TITLE", userConstantEntity.getMarketinghometitle()));
                    }
                    break;

            }
        }
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        if (view.getId() == navigationView.getId()) {
            if (forceUpdate == 1) {

            } else {
                dialog.cancel();
            }
        } else if (view.getId() == ivProfile.getId()) {
            dialog.cancel();
        }

    }

    private void openAppMarketPlace() {
        final String appPackageName = this.getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        new TrackingController(this).sendData(new TrackingRequestEntity(new TrackingData("Update : User open marketplace  "), "Update"), null);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefManager.getMps() == null) {
            //new MasterController(HomeActivity.this).getMpsData(HomeActivity.this);
        }

        // set first fragement selected.
        //selectHome();

        // will be upadte everytyime user comes on dashboard
        if (loginResponseEntity != null) {
            new MasterController(this).geUserConstant(1, this);
            new MasterController(this).getConstants(this);
        }
        LocalBroadcastManager.getInstance(HomeActivity.this).registerReceiver(mHandleMessageReceiver, new IntentFilter(Utility.PUSH_BROADCAST_ACTION));

        LocalBroadcastManager.getInstance(HomeActivity.this)
                .registerReceiver(mHandleMessageReceiver, new IntentFilter(Utility.USER_PROFILE_ACTION));

        LocalBroadcastManager.getInstance(HomeActivity.this)
                .registerReceiver(mHandleMessageReceiver,
                        new IntentFilter(Utility.USER_DASHBOARD));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHandleMessageReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESULT", "Activity");
        if (requestCode == Constants.REQUEST_CODE) {
            if (data != null) {
                int Counter = prefManager.getNotificationCounter();
                textNotifyItemCount.setText("" + Counter);
                textNotifyItemCount.setVisibility(View.GONE);

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case Constants.PERMISSION_CALLBACK_CONSTANT:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean call_phone = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (call_phone) {

                        if (userConstantEntity.getMangMobile() != null && userConstantEntity.getManagName() != null) {
                            ConfirmAlert("Calling", getResources().getString(R.string.RM_Calling) + " " + userConstantEntity.getManagName());
                        }

                    }

                }
                break;
        }
    }

    public void hideNavigationItem() {
        Menu nav_Menu = navigationView.getMenu();
        if (Utility.checkPospTrainingStatus(this) == 1)
            nav_Menu.findItem(R.id.nav_posptraining).setVisible(false);
        else
            nav_Menu.findItem(R.id.nav_posptraining).setVisible(false);

        //todo : check key from userconstant to hide add posp
        if (userConstantEntity != null && userConstantEntity.getAddPospVisible() != null && !userConstantEntity.getAddPospVisible().equals("")) {
            int visibility = Integer.parseInt(userConstantEntity.getAddPospVisible());
            if (visibility == 1)
                nav_Menu.findItem(R.id.nav_addposp).setVisible(true);
            else
                nav_Menu.findItem(R.id.nav_addposp).setVisible(false);
        }

    }


    public void ConfirmAlert(String Title, String strBody) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle(Title);

            builder.setMessage(strBody);
            String positiveText = "Call";
            String NegativeText = "Share";
            String NeutralText = "Cancel";
            builder.setPositiveButton(positiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            Intent intentCalling = new Intent(Intent.ACTION_CALL);
                            intentCalling.setData(Uri.parse("tel:" + userConstantEntity.getMangMobile()));
                            startActivity(intentCalling);
                        }
                    });

            builder.setNegativeButton(NegativeText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (userConstantEntity.getMangEmail() != null) {
                                // composeEmail(userConstantEntity.getMangEmail(), "");
                                shareMailSmsList(HomeActivity.this, "", "Dear Sir/Madam,", userConstantEntity.getMangEmail().toString(), userConstantEntity.getMangMobile().toString());

                            }
                        }
                    });

            builder.setNeutralButton(NeutralText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            final android.support.v7.app.AlertDialog dialog = builder.create();
            //  dialog.setCancelable(false);
            //  dialog.setCanceledOnTouchOutside(false);

            dialog.show();

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.uvv_green));
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.holo_red_dark));
            dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.button_color));

        } catch (Exception ex) {
            Toast.makeText(this, "Please try again..", Toast.LENGTH_SHORT).show();
        }
    }

    //region mps dialog

    public void DialogMPS() {
        if (prefManager.getMps() != null) {
            AlertDialog.Builder mpsAlertBuilder;
            mpsAlertBuilder = new AlertDialog.Builder(this);
            mpsAlertBuilder.setCancelable(true);
            // builder.setTitle("PREMIUM DETAIL");
            LayoutInflater inflater = this.getLayoutInflater();
            View view = inflater.inflate(R.layout.layout_dialog_mps, null);
            mpsAlertBuilder.setView(view);

            if (mpsDialog == null) {
                mpsDialog = mpsAlertBuilder.create();
            }

            TextView txtDesc = (TextView) view.findViewById(R.id.txtDesc);
            TextView txtKnowMore = (TextView) view.findViewById(R.id.txtKnowMore);
            TextView txtGetMPS = (TextView) view.findViewById(R.id.txtGetMPS);
            TextView txtLater = (TextView) view.findViewById(R.id.txtLater);

            txtDesc.setText("");
            txtDesc.append(getResources().getString(R.string.mps_popup_text));

            String amount = " \u20B9" + prefManager.getMps().getTotalAmt() + "/- ";
            SpannableString ss1 = new SpannableString(amount);
            ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
            String normalText = getResources().getString(R.string.mps_popup_text);
            txtDesc.setText("");
            txtDesc.append(normalText);
            txtDesc.append(ss1);
            txtDesc.append("(Incl. GST) only");

            txtLater.setTag(R.id.txtLater, mpsDialog);
            txtGetMPS.setTag(R.id.txtGetMPS, mpsDialog);
            txtKnowMore.setTag(R.id.txtKnowMore, mpsDialog);

            txtKnowMore.setOnClickListener(onClickListener);
            txtGetMPS.setOnClickListener(onClickListener);
            txtLater.setOnClickListener(onClickListener);

            if (!mpsDialog.isShowing())
                mpsDialog.show();
        }

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int i = v.getId();
            if (i == R.id.txtKnowMore) {
                ((AlertDialog) v.getTag(R.id.txtKnowMore)).dismiss();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, new KnowMoreMPSFragment());
                fragmentTransaction.commit();

            } else if (i == R.id.txtGetMPS) {
                ((AlertDialog) v.getTag(R.id.txtGetMPS)).dismiss();
                redirectMPS();


            } else if (i == R.id.txtLater) {
                ((AlertDialog) v.getTag(R.id.txtLater)).dismiss();

            }
        }
    };

    public void redirectMPS() {
        android.support.v4.app.FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, new MPSFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onGrantButtonClick(View view) {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, Constants.REQUEST_PERMISSION_SETTING);

    }
    //endregion


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("COUNTER", "new intent");
    }

    public void shareMailSmsList(Context context, String prdSubject, String prdDetail, String mailTo, String mobileNo) {

        //  String Deeplink = "https://nykaa.ly/P_" + Sharedata_product_id;

        //  String prdSubject = "Look what I found on Nykaa!";
        //  String prdDetail = "Check out " + Sharedata_product_name + " on Nykaa" + "\n" + Deeplink;
        try {


            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = context.getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();

            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("mms") || packageName.contains("messaging") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus"))) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {
                        shareIntent.setType("image/*");
                        shareIntent.setData(Uri.parse("mailto:"));
                        shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mailTo});
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("mms")) {
                        shareIntent.setType("vnd.android-dir/mms-sms");
                        shareIntent.setData(Uri.parse("sms:" + mobileNo));
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("whatsapp")) {
                        String toNumber = mobileNo.replace("+", "").replace(" ", "");
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("messaging")) {
                        shareIntent.setType("vnd.android-dir/mms-sms");
                        shareIntent.setData(Uri.parse("sms:" + mobileNo));
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.setType("image/*");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

                        shareIntent.setPackage(packageName);

                    } else {
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

                    }

                    intentList.add(new LabeledIntent(shareIntent, packageName, ri.loadLabel(pm), ri.icon));

                }
            }


            if (intentList.size() > 1) {
                intentList.remove(intentList.size() - 1);
            }

            Intent openInChooser = Intent.createChooser(shareIntent, "Share Via");
            // convert intentList to array
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            startActivity(openInChooser);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onOkClick(Dialog dialog, View view) {

    }

    @Override
    public void onCancelClick(Dialog dialog, View view) {
        dialog.cancel();
    }
}
