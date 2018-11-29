package com.datacomp.magicfinmart.loan_fm.balancetransfer.addquote;

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
import com.datacomp.magicfinmart.loan_fm.balancetransfer.quote.BL_QuoteFragment;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;

public class BLMainActivity extends BaseActivity {


    private static String INPUT_FRAGMENT_BL = "inputbl";
    private static String QUOTE_FRAGMENT_BL = "quotebl";
    private static String BUY_FRAGMENT_BL = "buybl";

    public static String BL_INPUT_REQUEST = "input_request_entitybl";
    public static String BL_QUOTE_REQUEST = "quote_request_entitybl";

    BottomNavigationView bottomNavigationView;
    Bundle quoteBundle;
    Fragment tabFragment = null;
    FragmentTransaction transactionSim;

    FmBalanceLoanRequest fmBalanceLoanRequest;
    boolean isQuoteVisible = true;
    ImageView ivHdrInput,  ivHdrQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivHdrInput = (ImageView) findViewById(R.id.ivHdrInput);
        ivHdrQuote = (ImageView) findViewById(R.id.ivHdrQuote);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (getIntent().getParcelableExtra(BL_QuoteFragment.FROM_QUOTEBL) != null) {
            fmBalanceLoanRequest = getIntent().getParcelableExtra(BL_QuoteFragment.FROM_QUOTEBL);
            Bundle bundle = new Bundle();
            bundle.putParcelable(BL_QUOTE_REQUEST, fmBalanceLoanRequest);
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
                tabFragment = getSupportFragmentManager().findFragmentByTag(INPUT_FRAGMENT_BL);

                if (fmBalanceLoanRequest != null) {
                    quoteBundle = new Bundle();
                    quoteBundle.putParcelable(BLMainActivity.BL_INPUT_REQUEST, fmBalanceLoanRequest);

                }
                if (tabFragment != null) {
                    highlighInput();
                    tabFragment.setArguments(quoteBundle);
                    loadFragment(tabFragment, INPUT_FRAGMENT_BL);

                } else {
                    highlighInput();
                    InputFragment_bl inputFragment = new InputFragment_bl();
                    inputFragment.setArguments(quoteBundle);
                    loadFragment(inputFragment, INPUT_FRAGMENT_BL);
                }

                return true;
            } else if (i == R.id.navigation_quote) {
                tabFragment = getSupportFragmentManager().findFragmentByTag(QUOTE_FRAGMENT_BL);
                if (tabFragment != null) {
                    highlighQuote();
                    loadFragment(tabFragment, QUOTE_FRAGMENT_BL);

                } else {
                    if (quoteBundle != null) {
                        highlighQuote();
                        QuoteFragment_bl quoteFragment = new QuoteFragment_bl();
                        quoteFragment.setArguments(quoteBundle);
                        loadFragment(quoteFragment, QUOTE_FRAGMENT_BL);
                    } else {

                        Toast.makeText(BLMainActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
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
            BLMainActivity.this.finish();
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

    public void redirectInput(FmBalanceLoanRequest entity) {
        if (isQuoteVisible) {
            fmBalanceLoanRequest = entity;
            quoteBundle = new Bundle();
            quoteBundle.putParcelable(BLMainActivity.BL_INPUT_REQUEST, fmBalanceLoanRequest);

            if (fmBalanceLoanRequest == null)
                Toast.makeText(BLMainActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
            else
                bottomNavigationView.setSelectedItemId(R.id.navigation_input);
        } else {
            Toast.makeText(BLMainActivity.this, "Fetching all quotes", Toast.LENGTH_SHORT).show();
        }

    }


//    public void redirectToApplyLoan(BLEntity entity, BLLoanRequest blLoanRequest, int id) {
//        startActivity(new Intent(BLMainActivity.this, BTLoanApplyWebView.class)
//                .putExtra("BL", entity)
//                .putExtra("BL_Req", blLoanRequest)
//                .putExtra("BL_QUOTE_ID", id));
//    }

    // Implementation the Interface for Communication of Fragment Input and Quote

    public void getQuoteParameterBundle(FmBalanceLoanRequest entity) {

        fmBalanceLoanRequest = entity;
        quoteBundle = new Bundle();
        quoteBundle.putParcelable(BLMainActivity.BL_QUOTE_REQUEST, fmBalanceLoanRequest);

        if (fmBalanceLoanRequest == null)
            Toast.makeText(BLMainActivity.this, "Please fill all inputs", Toast.LENGTH_SHORT).show();
        else
            bottomNavigationView.setSelectedItemId(R.id.navigation_quote);

    }

    public void updateRequest(FmBalanceLoanRequest entity, boolean isQuoteVisible) {
        fmBalanceLoanRequest = entity;
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
                Toast.makeText(BLMainActivity.this, "Please wait.., Fetching all quotes", Toast.LENGTH_SHORT).show();
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
