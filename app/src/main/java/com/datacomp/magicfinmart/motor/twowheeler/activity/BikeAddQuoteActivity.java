package com.datacomp.magicfinmart.motor.twowheeler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.motor.twowheeler.fragment.BikeApplicationTabFragment;
import com.datacomp.magicfinmart.motor.twowheeler.fragment.BikeInputFragment;
import com.datacomp.magicfinmart.motor.twowheeler.fragment.BikeQuoteFragment;
import com.datacomp.magicfinmart.motor.twowheeler.fragment.BikeQuoteTabFragment;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ApplicationListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.QuoteListEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.controller.MotorController;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;

public class BikeAddQuoteActivity extends BaseActivity {


    private static String BIKE_INPUT_FRAGMENT = "bike_input";
    private static String BIKE_QUOTE_FRAGMENT = "bike_quote";
    private static String BUY_FRAGMENT = "buy";


    public static String BIKE_INPUT_REQUEST = "bike_input_request_entity";
    public static String BIKE_QUOTE_REQUEST = "bike_quote_request_entity";

    BottomNavigationView bottomNavigationView;
    Bundle quoteBundle;
    Fragment tabFragment = null;
    FragmentTransaction transactionSim;
    MotorRequestEntity motorRequestEntity;
    boolean isQuoteVisible = true;
    ImageView ivHdrInput, ivHdrQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_add_quote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivHdrInput = (ImageView) findViewById(R.id.ivHdrInput);
        ivHdrQuote = (ImageView) findViewById(R.id.ivHdrQuote);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (getIntent().getParcelableExtra(BikeApplicationTabFragment.FROM_BIKE_APPLICATION) != null) {
            ApplicationListEntity entity = getIntent().getParcelableExtra(BikeApplicationTabFragment.FROM_BIKE_APPLICATION);
            if (entity.getMotorRequestEntity().getIsTwentyfour() == 0) {

                //update counetr to hit  two times only to manage multiple hits
                Utility.getSharedPreferenceEditor(this).putInt(Utility.QUOTE_COUNTER,
                        MotorController.NO_OF_SERVER_HITS - 1)
                        .commit();
                //1. update srn in preference
                Utility.getSharedPreferenceEditor(this).
                        putString(Utility.CARQUOTE_UNIQUEID, entity.getSRN()).commit();


                //2. create bundle
                Bundle bundle = new Bundle();
                bundle.putParcelable(BIKE_QUOTE_REQUEST, entity.getMotorRequestEntity());
                quoteBundle = bundle;

                bottomNavigationView.setSelectedItemId(R.id.navigation_quote);
            } else {
                //send to Input
                //modify
                quoteBundle = new Bundle();
                quoteBundle.putParcelable(BIKE_QUOTE_REQUEST, entity.getMotorRequestEntity());

                bottomNavigationView.setSelectedItemId(R.id.navigation_input);
            }

        } else if (getIntent().getParcelableExtra(BikeQuoteTabFragment.FROM_QUOTE_BIKE) != null) {
            QuoteListEntity entity = getIntent().getParcelableExtra(BikeQuoteTabFragment.FROM_QUOTE_BIKE);
            if (entity.getMotorRequestEntity().getIsTwentyfour() == 0) {

                //update counetr to hit  two times only to manage multiple hits
                Utility.getSharedPreferenceEditor(this).putInt(Utility.QUOTE_COUNTER,
                        MotorController.NO_OF_SERVER_HITS - 1)
                        .commit();
                //1. update srn in preference
                Utility.getSharedPreferenceEditor(this).
                        putString(Utility.BIKEQUOTE_UNIQUEID, entity.getSRN()).commit();

                //2. create bundle
                Bundle bundle = new Bundle();
                bundle.putParcelable(BIKE_QUOTE_REQUEST, entity.getMotorRequestEntity());
                quoteBundle = bundle;

                bottomNavigationView.setSelectedItemId(R.id.navigation_quote);
            } else {
                //send to Input
                //modify
                quoteBundle = new Bundle();
                quoteBundle.putParcelable(BIKE_INPUT_REQUEST, entity.getMotorRequestEntity());

                bottomNavigationView.setSelectedItemId(R.id.navigation_input);
            }
        } else {
            //first input fragment load
            bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        }

        quoteBundle = null;


    }


    private void loadFragment(Fragment fragment, String TAG) {
        transactionSim = getSupportFragmentManager().beginTransaction();
        transactionSim.replace(R.id.frame_layout, fragment, TAG);
        transactionSim.addToBackStack(TAG);
        transactionSim.show(fragment);
        transactionSim.commit();
        //transactionSim.commitAllowingStateLoss();
    }

    public void highlighInput() {
        ivHdrInput.setVisibility(View.VISIBLE);
        ivHdrQuote.setVisibility(View.GONE);
    }

    public void highlighQuote() {
        ivHdrQuote.setVisibility(View.VISIBLE);
        ivHdrInput.setVisibility(View.GONE);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_input) {
                if (isQuoteVisible) {
                    tabFragment = getSupportFragmentManager().findFragmentByTag(BIKE_INPUT_FRAGMENT);
                    if (motorRequestEntity != null) {
                        quoteBundle = new Bundle();
                        quoteBundle.putParcelable(BikeAddQuoteActivity.BIKE_INPUT_REQUEST, motorRequestEntity);
                    }

                    if (tabFragment != null) {
                        highlighInput();
                        tabFragment.setArguments(quoteBundle);
                        loadFragment(tabFragment, BIKE_INPUT_FRAGMENT);

                    } else {
                        highlighInput();
                        BikeInputFragment inputFragment = new BikeInputFragment();
                        inputFragment.setArguments(quoteBundle);
                        loadFragment(inputFragment, BIKE_INPUT_FRAGMENT);
                    }
                } else {
                    Toast.makeText(BikeAddQuoteActivity.this, "Please wait.., Fetching all quotes", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            } else if (i == R.id.navigation_quote) {
                tabFragment = getSupportFragmentManager().findFragmentByTag(BIKE_QUOTE_FRAGMENT);
                if (tabFragment != null) {
                    highlighQuote();
                    loadFragment(tabFragment, BIKE_QUOTE_FRAGMENT);

                } else {
                    if (quoteBundle != null) {
                        highlighQuote();
                        BikeQuoteFragment quoteFragment = new BikeQuoteFragment();
                        quoteFragment.setArguments(quoteBundle);
                        loadFragment(quoteFragment, BIKE_QUOTE_FRAGMENT);
                    } else {

                        BikeInputFragment fragment = (BikeInputFragment) getSupportFragmentManager().findFragmentByTag(BIKE_INPUT_FRAGMENT);
                        if (fragment != null)
                            fragment.getQuote();
                        // Toast.makeText(InputQuoteBottmActivity.this, "Tap get Quotes ", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }

                return true;
            } else if (i == R.id.navigation_buy) {//                    tabFragment = getSupportFragmentManager().findFragmentByTag("BUY");
//                    if (tabFragment != null) {
//                        loadFragment(tabFragment, INPUT_FRAGMENT);
//
//                    } else {
//                        loadFragment(new BuyFragment(), INPUT_FRAGMENT);
//                    }

                return false;
            }

            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (isQuoteVisible) {
            if (R.id.navigation_quote == bottomNavigationView.getSelectedItemId()) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_input);
            } else {
                finish();
            }
        } else {
            Toast.makeText(BikeAddQuoteActivity.this, "Please Wait fetching Quotes!!!", Toast.LENGTH_SHORT).show();
        }


//        if (R.id.navigation_quote == bottomNavigationView.getSelectedItemId())
//
//        {
//            redirectToInput();
//        } else {
//            HealthQuoteBottomTabsActivity.this.finish();
//        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void modifyQuote(MotorRequestEntity entity) {
        motorRequestEntity = entity;
        /*startActivityForResult(new Intent(this, ModifyQuoteActivity.class)
                .putExtra("CAR_REQUEST", motorRequestEntity), 1000);*/

    }

    public void redirectInput(MotorRequestEntity entity) {

        if (isQuoteVisible) {
            motorRequestEntity = entity;
            quoteBundle = new Bundle();
            quoteBundle.putParcelable(BikeAddQuoteActivity.BIKE_INPUT_REQUEST, motorRequestEntity);

            if (motorRequestEntity == null)
                Toast.makeText(BikeAddQuoteActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
            else
                bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        } else {
            Toast.makeText(BikeAddQuoteActivity.this, "Fetching all quotes", Toast.LENGTH_SHORT).show();
        }
    }

    public void getQuoteParameterBundle(MotorRequestEntity entity) {

        motorRequestEntity = entity;
        quoteBundle = new Bundle();
        quoteBundle.putParcelable(BikeAddQuoteActivity.BIKE_QUOTE_REQUEST, motorRequestEntity);

        if (motorRequestEntity == null)
            Toast.makeText(BikeAddQuoteActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
        else
            bottomNavigationView.setSelectedItemId(R.id.navigation_quote);

    }

    public void updateRequest(MotorRequestEntity entity, boolean isQuoteVisible) {
        motorRequestEntity = entity;
        this.isQuoteVisible = isQuoteVisible;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (isQuoteVisible) {
                    finish();
                    return true;
                } else {
                    Toast.makeText(BikeAddQuoteActivity.this, "Please Wait fetching Quotes!!!", Toast.LENGTH_SHORT).show();
                    return false;
                }


            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
