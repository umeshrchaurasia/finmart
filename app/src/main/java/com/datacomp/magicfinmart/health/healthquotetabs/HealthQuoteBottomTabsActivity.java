package com.datacomp.magicfinmart.health.healthquotetabs;

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
import com.datacomp.magicfinmart.health.fragment.HealthInputFragment;
import com.datacomp.magicfinmart.health.fragment.HealthQuoteFragment;
import com.datacomp.magicfinmart.health.quoappfragment.HealthQuoteListFragment;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;

public class HealthQuoteBottomTabsActivity extends BaseActivity {

    private static String INPUT_FRAGMENT = "input_health";
    private static String QUOTE_FRAGMENT = "quote_health";


    public static String INPUT_DATA = "input_health_data";
    public static String QUOTE_DATA = "quote_health_data";

    private static String BUY_FRAGMENT = "buy";

    BottomNavigationView bottomNavigationView;
    Bundle quoteBundle;
    Fragment tabFragment = null;
    FragmentTransaction transactionSim;
    HealthQuote healthQuote;
    ImageView ivHdrInput, ivHdrQuote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_quote_bottm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("HEALTH INSURANCE");
        ivHdrInput = (ImageView) findViewById(R.id.ivHdrInput);
        ivHdrQuote = (ImageView) findViewById(R.id.ivHdrQuote);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        if (getIntent().getParcelableExtra(HealthQuoteListFragment.FROM_QUOTE) != null) {
            healthQuote = getIntent().getParcelableExtra(HealthQuoteListFragment.FROM_QUOTE);

            // reassign age to Member list
            List<MemberListEntity> member = new ArrayList<>();
            if (healthQuote.getHealthRequest().getMemberList() != null) {

                for (int i = 0; i < healthQuote.getHealthRequest().getMemberList().size(); i++) {
                    MemberListEntity entity = healthQuote.getHealthRequest().getMemberList().get(i);
                    entity.setAge(getAgeFromDate(entity.getMemberDOB()));
                    member.add(entity);
                }
            }

            healthQuote.getHealthRequest().setMemberList(member);

            quoteBundle = new Bundle();
            quoteBundle.putParcelable(QUOTE_DATA, healthQuote);
            bottomNavigationView.setSelectedItemId(R.id.navigation_quote);

        } else if (getIntent().getParcelableExtra(HealthQuoteListFragment.HEALTH_INPUT_FRAGMENT) != null) {
            healthQuote = getIntent().getParcelableExtra(HealthQuoteListFragment.HEALTH_INPUT_FRAGMENT);
            // reassign age to Member list
            List<MemberListEntity> member = new ArrayList<>();

            if (healthQuote.getHealthRequest().getMemberList() != null) {
                for (int i = 0; i < healthQuote.getHealthRequest().getMemberList().size(); i++) {
                    MemberListEntity entity = healthQuote.getHealthRequest().getMemberList().get(i);
                    entity.setAge(getAgeFromDate(entity.getMemberDOB()));
                    member.add(entity);
                }
            }
            healthQuote.getHealthRequest().setMemberList(member);

            quoteBundle = new Bundle();
            quoteBundle.putParcelable(INPUT_DATA, healthQuote);

            bottomNavigationView.setSelectedItemId(R.id.navigation_input);

        } else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        }


    }

    private void loadFragment(Fragment fragment, String TAG) {
        transactionSim = getSupportFragmentManager().beginTransaction();
        transactionSim.replace(R.id.frame_layout, fragment, TAG);
        transactionSim.addToBackStack(TAG);
        transactionSim.show(fragment);
        transactionSim.commit();
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
                tabFragment = getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT);

                if (healthQuote != null) {
                    quoteBundle = new Bundle();
                    quoteBundle.putParcelable(INPUT_DATA, healthQuote);
                }

//                    if (tabFragment != null) {
//                        tabFragment.setArguments(quoteBundle);
//                        loadFragment(tabFragment, INPUT_FRAGMENT);
//                    } else {
//                        HealthInputFragment inputFragment = new HealthInputFragment();
//                        inputFragment.setArguments(quoteBundle);
//                        loadFragment(inputFragment, INPUT_FRAGMENT);
//                    }
                highlighInput();
                HealthInputFragment inputFragment = new HealthInputFragment();
                inputFragment.setArguments(quoteBundle);
                loadFragment(inputFragment, INPUT_FRAGMENT);

                return true;
            } else if (i == R.id.navigation_quote) {
                tabFragment = getSupportFragmentManager().findFragmentByTag(QUOTE_FRAGMENT);

                if (healthQuote != null) {
                    quoteBundle = new Bundle();
                    quoteBundle.putParcelable(QUOTE_DATA, healthQuote);
                }

                if (tabFragment != null) {
                    highlighQuote();
                    tabFragment.setArguments(quoteBundle);
                    loadFragment(tabFragment, QUOTE_FRAGMENT);

                } else {
                    if (quoteBundle != null) {
                        if (quoteBundle.getParcelable(QUOTE_DATA) != null) {
                            HealthQuoteFragment quoteFragment = new HealthQuoteFragment();
                            quoteFragment.setArguments(quoteBundle);
                            loadFragment(quoteFragment, QUOTE_FRAGMENT);
                            highlighQuote();
                        } else {

                            Toast.makeText(HealthQuoteBottomTabsActivity.this, "Tap get quote", Toast.LENGTH_SHORT).show();

                            return false;
                        }
                    } else {

                        Toast.makeText(HealthQuoteBottomTabsActivity.this, "Tap get  quote", Toast.LENGTH_SHORT).show();

                        return false;
                    }
                }

                return true;
            } else if (i == R.id.navigation_buy) {
                return false;
            }

            return false;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //  HealthQuoteBottomTabsActivity.this.finish();
        if (R.id.navigation_quote == bottomNavigationView.getSelectedItemId()) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        } else {
            HealthQuoteBottomTabsActivity.this.finish();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void redirectToQuote(HealthQuote healthQuote) {
        this.healthQuote = healthQuote;
        quoteBundle = new Bundle();
        quoteBundle.putParcelable(QUOTE_DATA, healthQuote);
        bottomNavigationView.setSelectedItemId(R.id.navigation_quote);
    }

    public void redirectToInput() {
        quoteBundle = new Bundle();
        quoteBundle.putParcelable(INPUT_DATA, healthQuote);
        bottomNavigationView.setSelectedItemId(R.id.navigation_input);
    }

    public void updateRequestID(int healthRequestID) {
        healthQuote.setHealthRequestId(healthRequestID);
    }


}
