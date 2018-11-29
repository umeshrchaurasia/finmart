package com.datacomp.magicfinmart.health.compare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.health.fragment.HealthQuoteFragment;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.SortbyInsurer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BenefitsEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;

public class HealthCompareActivity extends BaseActivity {

    RecyclerView rvBenefits;//, rvBenefitsOptions;
    List<HealthQuoteEntity> listHealthQuote;
    HealthCompareViewAdapter mAdapter;
    Spinner spBenefits;
    Button btnBack;
    ArrayList<String> listBenefits;
    ArrayList<BenefitsEntity> list9Benefits;
    ArrayAdapter<String> benefitsAdapter;
    //HealthNineBenefitsViewAdapter mBenefitsAdapter;
    TextView txtSelectedBenefits;

    ViewPager vpBenefits;
    ScrollingBenefitsAdapter scrollingBenefitsAdapter;
    ScrollView svContainer;
    Timer timer;
    int page = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_compare);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(false);
        this.setTitle("COMPARE");

        init();
        listBenefits = new ArrayList<>();
        list9Benefits = new ArrayList<BenefitsEntity>();
        //listHealthQuote = new ArrayList<>();
        if (getIntent().getParcelableArrayListExtra(HealthQuoteFragment.HEALTH_COMPARE) != null) {
            List<HealthQuoteEntity> list = getIntent().getParcelableArrayListExtra(HealthQuoteFragment.HEALTH_COMPARE);

            listHealthQuote = new ArrayList<>(removeDuplicate(list));
            fillBenefits();
            bindBenefits();

        }

        // spBenefits.setSelection(0);
        updateBenefits("Room Rent Limit");
        // mBenefitsAdapter.updateList(0);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private List<HealthQuoteEntity> removeDuplicate1(List<HealthQuoteEntity> list) {
        List<HealthQuoteEntity> quoteList = new ArrayList<>();

        boolean isAdd = true;

        for (int i = 0; i < list.size(); i++) {
            HealthQuoteEntity entity = list.get(i);
            for (int j = 0; j < quoteList.size(); j++) {
                HealthQuoteEntity en = quoteList.get(j);
              //  if (en.getInsurerId() == entity.getInsurerId() && en.getProductName() != entity.getProductName()) {
                if ((en.getInsurerId() == entity.getInsurerId()) && (en.getProductName().equals(entity.getProductName()))) {
                    isAdd = false;
                } else {
                    isAdd = true;
                }
            }
            if (isAdd) {
                quoteList.add(entity);
            }
        }


        return quoteList;
    }

    public List<HealthQuoteEntity>  removeDuplicate(List<HealthQuoteEntity> list) {

        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {

                //    if (en.getInsurerId() == entity.getInsurerId() && en.getProductName() != entity.getProductName()) {
                if( (list.get(i).getInsurerId() == (list.get(j).getInsurerId()))  &&  (list.get(i).getProductName().equals(list.get(j).getProductName()))){
                    list.remove(j);
                    j--;
                }
            }
        }
        return list;
    }


    private void bindBenefits() {

        Collections.sort(listHealthQuote, new SortbyInsurer());
        mAdapter = new HealthCompareViewAdapter(this, listHealthQuote);
        rvBenefits.setAdapter(mAdapter);


        benefitsAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, listBenefits) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }
                };
        spBenefits.setAdapter(benefitsAdapter);

        spBenefits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position != 0) {
                    updateBenefits(spBenefits.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void updateBenefits(String benefits) {
        txtSelectedBenefits.setText(benefits);
        resetBenefits();
        for (int i = 0; i < listHealthQuote.size(); i++) {
            HealthQuoteEntity entity = listHealthQuote.get(i);

            for (int j = 0; j < entity.getLstbenfitsFive().size(); j++) {
                BenefitsEntity benefitsEntity = entity.getLstbenfitsFive().get(j);
                if (benefitsEntity.getBeneDesc().equals(benefits)) {
                    benefitsEntity.setSelected(true);
                    break;
                }
            }
        }

        mAdapter.refreshSelection(listHealthQuote);
        scrollingBenefitsAdapter.notifyDataSetChanged();

        //to Scroll up
        svContainer.smoothScrollBy(0, 0);

    }

    private void resetBenefits() {
        for (int i = 0; i < listHealthQuote.size(); i++) {
            HealthQuoteEntity entity = listHealthQuote.get(i);

            for (int j = 0; j < entity.getLstbenfitsFive().size(); j++) {
                BenefitsEntity benefitsEntity = entity.getLstbenfitsFive().get(j);
                benefitsEntity.setSelected(false);
            }
        }
    }

    private void init() {
        svContainer = (ScrollView) findViewById(R.id.svContainer);

        vpBenefits = (ViewPager) findViewById(R.id.vpBenefits);
        // vpBenefits.setPageMargin(-700);

        btnBack = (Button) findViewById(R.id.btnBack);
        txtSelectedBenefits = (TextView) findViewById(R.id.txtSelectedBenefits);
        spBenefits = (Spinner) findViewById(R.id.spBenefits);
        rvBenefits = (RecyclerView) findViewById(R.id.rvBenefits);
        rvBenefits.setLayoutManager(new LinearLayoutManager(this));

        //rvBenefitsOptions = (RecyclerView) findViewById(R.id.rvBenefitsOptions);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(HealthCompareActivity.this) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(HealthCompareActivity.this) {
                    private static final float SPEED = 4000f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //rvBenefitsOptions.setLayoutManager(layoutManager);

    }

    private void fillBenefits() {


        if (listHealthQuote != null) {
            if (listHealthQuote.get(0) != null) {

                int benefitsFoundIndex = 0;
                for (int i = 0; i < listHealthQuote.size(); i++) {
                    if (listHealthQuote.get(i).getLstbenfitsFive().size() > 0) {
                        benefitsFoundIndex = i;
                        break;
                    }
                }

                for (int i = 0; i < listHealthQuote.get(benefitsFoundIndex).getLstbenfitsFive().size(); i++) {
                    BenefitsEntity entity = listHealthQuote.get(benefitsFoundIndex).getLstbenfitsFive().get(i);
                    if (entity.getBeneID() == 1
                            || entity.getBeneID() == 2
                            || entity.getBeneID() == 3
                            || entity.getBeneID() == 4
                            || entity.getBeneID() == 6
                            || entity.getBeneID() == 8
                            || entity.getBeneID() == 13
                            || entity.getBeneID() == 14
                            || entity.getBeneID() == 22) {
                        if (entity.getBeneID() == 1) {
                            entity.setSelected(true);
                        }
                        list9Benefits.add(entity);

                    } else {
                        listBenefits.add(entity.getBeneDesc());
                    }
                }

                listBenefits.add(0, "Select Other Benefits");

                //mBenefitsAdapter = new HealthNineBenefitsViewAdapter(this, list9Benefits);
                //rvBenefitsOptions.setAdapter(mBenefitsAdapter);

                scrollingBenefitsAdapter = new ScrollingBenefitsAdapter(this,
                        list9Benefits);
                vpBenefits.setAdapter(scrollingBenefitsAdapter);
                pageSwitcher(3);
            }
        }
    }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page > list9Benefits.size()) { // In my case the number of pages are 5
                        //timer.cancel();

                        page = 0;
                        vpBenefits.setCurrentItem(page);
                        page++;
                    } else {
                        vpBenefits.setCurrentItem(page++);
                    }
                }
            });

        }
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


}
