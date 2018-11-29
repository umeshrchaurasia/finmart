package com.datacomp.magicfinmart.loan_fm.personalloan.addquote;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.loan_fm.personalloan.quote.PL_QuoteFragment;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmPersonalLoanRequest;

public class PLMainActivity extends BaseActivity  {

    private static String INPUT_FRAGMENT_PL = "inputpl";
    private static String QUOTE_FRAGMENT_PL = "quotepl";
    private static String BUY_FRAGMENT_PL = "buypl";

    public static String PL_INPUT_REQUEST = "input_request_entitypl";
    public static String PL_QUOTE_REQUEST = "quote_request_entitypl";

    BottomNavigationView bottomNavigationView;
    Bundle quoteBundle;
    Fragment tabFragment = null;
    FragmentTransaction transactionSim;

    FmPersonalLoanRequest fmPersonalLoanRequest;

    ImageView ivHdrInput,  ivHdrQuote;

    boolean isQuoteVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivHdrInput = (ImageView) findViewById(R.id.ivHdrInput);
        ivHdrQuote = (ImageView) findViewById(R.id.ivHdrQuote);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (getIntent().getParcelableExtra(PL_QuoteFragment.FROM_QUOTEPL) != null) {
            fmPersonalLoanRequest = getIntent().getParcelableExtra(PL_QuoteFragment.FROM_QUOTEPL);
            Bundle bundle = new Bundle();
            bundle.putParcelable(PL_QUOTE_REQUEST, fmPersonalLoanRequest);
            quoteBundle = bundle;

            bottomNavigationView.setSelectedItemId(R.id.navigation_quote);


        } else {
            //first input fragment load
            bottomNavigationView.setSelectedItemId(R.id.navigation_input);
           
        }

        quoteBundle = null;

    }

    public void highlighInput()
    {
        ivHdrInput.setVisibility(View.VISIBLE);
        ivHdrQuote.setVisibility(View.GONE);
    }

    public void highlighQuote()
    {
        ivHdrQuote.setVisibility(View.VISIBLE);
        ivHdrInput.setVisibility(View.GONE);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_input) {
                tabFragment = getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT_PL);

                if (fmPersonalLoanRequest != null) {
                    quoteBundle = new Bundle();
                    quoteBundle.putParcelable(PLMainActivity.PL_INPUT_REQUEST, fmPersonalLoanRequest);

                }
                if (tabFragment != null) {
                    highlighInput();
                    tabFragment.setArguments(quoteBundle);
                    loadFragment(tabFragment, INPUT_FRAGMENT_PL);

                } else {
                    highlighInput();
                    InputFragment_pl inputFragment = new InputFragment_pl();
                    inputFragment.setArguments(quoteBundle);
                    loadFragment(inputFragment, INPUT_FRAGMENT_PL);
                }

                return true;
            } else if (i == R.id.navigation_quote) {
                tabFragment = getSupportFragmentManager().findFragmentByTag(QUOTE_FRAGMENT_PL);
                if (tabFragment != null) {
                    highlighQuote();
                    loadFragment(tabFragment, QUOTE_FRAGMENT_PL);


                } else {
                    if (quoteBundle != null) {
                        highlighQuote();
                        QuoteFragment_pl quoteFragment = new QuoteFragment_pl();
                        quoteFragment.setArguments(quoteBundle);
                        loadFragment(quoteFragment, QUOTE_FRAGMENT_PL);
                    } else {

                        Toast.makeText(PLMainActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                return true;
//
            } else if (i == R.id.navigation_buy) {
                return false;
            }

            return false;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (R.id.navigation_quote == bottomNavigationView.getSelectedItemId())
        {
            bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        } else {
            PLMainActivity.this.finish();
        }
    }

    private void loadFragment(Fragment fragment, String TAG) {
        transactionSim = getSupportFragmentManager().beginTransaction();
        transactionSim.replace(R.id.frame_layout, fragment, TAG);
        transactionSim.addToBackStack(TAG);
        transactionSim.show(fragment);
        transactionSim.commit();
    }
    private void CheckAllBottomMenu() {
        int size = bottomNavigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            bottomNavigationView.getMenu().getItem(i).setCheckable(true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void redirectInput(FmPersonalLoanRequest entity) {
        if (isQuoteVisible) {
            fmPersonalLoanRequest = entity;
            quoteBundle = new Bundle();
            quoteBundle.putParcelable(PLMainActivity.PL_INPUT_REQUEST, fmPersonalLoanRequest);

            if (fmPersonalLoanRequest == null)
                Toast.makeText(PLMainActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
            else
                bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        } else {
            Toast.makeText(PLMainActivity.this, "Fetching all quotes", Toast.LENGTH_SHORT).show();
        }

    }

//    public void redirectToApplyLoan(PersonalQuoteEntity entity,String url, int id) {
//        startActivity(new Intent(PLMainActivity.this, PersonalLoanApplyWebView.class)
//                .putExtra("PL", entity)
//                .putExtra("PL_URL", url)
//                .putExtra("PL_QUOTE_ID", id));
//    }


    public void getQuoteParameterBundle(FmPersonalLoanRequest entity) {

        fmPersonalLoanRequest = entity;
        quoteBundle = new Bundle();
        quoteBundle.putParcelable(PLMainActivity.PL_QUOTE_REQUEST, fmPersonalLoanRequest);

        if (fmPersonalLoanRequest == null)
            Toast.makeText(PLMainActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
        else
            bottomNavigationView.setSelectedItemId(R.id.navigation_quote);

    }

    public void updateRequest(FmPersonalLoanRequest entity, boolean isQuoteVisible) {
        fmPersonalLoanRequest = entity;
        this.isQuoteVisible = isQuoteVisible;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            if (isQuoteVisible) {
                finish();
                return true;
            } else {
                Toast.makeText(PLMainActivity.this, "Please wait.., Fetching all quotes", Toast.LENGTH_SHORT).show();
                return false;
            }
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

    // Implementation the Interface for Communication of Fragment Input and Quote

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
}
