package com.datacomp.magicfinmart.creditcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.creditcard.CreditCardController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CreditCardEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.FilterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CreditCardMasterResponse;

public class CreditCardActivity extends BaseActivity implements IResponseSubcriber {

    public static final String SELECTED_CREDIT_CARD = "credit_card_detail";

    List<String> strFilterList;
    List<FilterEntity> listFilterEntity;
    List<CreditCardEntity> listCreditCardEntity;

    Spinner spIncome;
    ArrayAdapter<String> incomeAdapter;
    RecyclerView rvCreditCards;
    CreditCardsAdapter mAdapter;
    FloatingActionButton fabFilter;
    int EmploymentType = 1;

    FilterEntity selectedFilterEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listFilterEntity = new ArrayList<>();
        listCreditCardEntity = new ArrayList<>();
        strFilterList = new ArrayList<>();

        init();

        fetchCreditCards();

        setListener();
    }

    private void setListener() {
        rvCreditCards.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fabFilter.getVisibility() == View.VISIBLE) {
                    fabFilter.hide();
                } else if (dy < 0 && fabFilter.getVisibility() != View.VISIBLE) {
                    fabFilter.show();
                }
            }
        });
    }

    public void selectedIncome(FilterEntity entity) {
        selectedFilterEntity = entity;
        //mAdapter.refreshCreditCards(filterCreditCardsbtIncome(entity));
    }


    private void alertIncome() {
        //strFilterList
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_dialog_creditcard, null);

        RecyclerView rvIncomeSlabs = alertLayout.findViewById(R.id.rvIncomeSlabs);
        final RadioButton rbSalaried = alertLayout.findViewById(R.id.rbSalaried);
        final RadioButton rbSelf = alertLayout.findViewById(R.id.rbSelf);

        int numberOfColumns = 2;
        rvIncomeSlabs.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        rvIncomeSlabs.setAdapter(new CreditCardIncomeAdapter(this, listFilterEntity));


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Personal Information");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (rbSalaried.isChecked()) {
                    EmploymentType = 1;
                } else if (rbSelf.isChecked()) {
                    EmploymentType = 2;
                }

                if (selectedFilterEntity != null) {
                    mAdapter = new CreditCardsAdapter(CreditCardActivity.this, filterCreditCardsbtIncome(selectedFilterEntity));
                    rvCreditCards.setAdapter(mAdapter);

                    dialog.dismiss();
                } else {
                    Toast.makeText(CreditCardActivity.this, "Select Income", Toast.LENGTH_SHORT).show();
                }
                fabFilter.setVisibility(View.VISIBLE);
            }
        });
        AlertDialog dialog = alert.create();
        if (!dialog.isShowing())
            dialog.show();
        else
            dialog.dismiss();

    }


    private void init() {
        spIncome = (Spinner) findViewById(R.id.spIncome);
        rvCreditCards = (RecyclerView) findViewById(R.id.rvCreditCards);
        rvCreditCards.setLayoutManager(new LinearLayoutManager(this));
        fabFilter = findViewById(R.id.fabFilter);
        fabFilter.setVisibility(View.GONE);
        fabFilter.setOnClickListener(fabFilterListener);
        findViewById(R.id.CardStyle).setVisibility(View.GONE);//spinner cardview
    }

    View.OnClickListener fabFilterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            alertIncome();
        }
    };

    private void bindSpinner() {

        incomeAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strFilterList) {
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
        spIncome.setAdapter(incomeAdapter);


        spIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //1. income selection filer credit cards
                int priority = getIncomeAmountID(spIncome.getSelectedItem().toString());
                //2. display credit cards
                // mAdapter.refreshCreditCards(filterCreditCardsbtIncome(priority));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mAdapter = new CreditCardsAdapter(this, listCreditCardEntity);
        rvCreditCards.setAdapter(mAdapter);
    }

    private int getIncomeAmountID(String amountValue) {
        for (int i = 0; i < listFilterEntity.size(); i++) {
            if (amountValue.equals(listFilterEntity.get(i).getAmount())) {
                //return listFilterEntity.get(i).getCreditCardAmountFilterId();
                return listFilterEntity.get(i).getPriority();
            }
        }
        return 0;
    }

    private void fetchCreditCards() {
        showDialog("Please wait.., Fetching credit cards");
        new CreditCardController(this).getAllCreditCards(this);
    }

    private void incomeFilterData() {
        strFilterList.clear();
        for (int i = 0; i < listFilterEntity.size(); i++) {
            strFilterList.add(listFilterEntity.get(i).getAmount());
        }

        // strFilterList.add(0, "Select net annual income");
        //bindSpinner();
        spIncome.setVisibility(View.GONE);

       /* mAdapter = new CreditCardsAdapter(this, listCreditCardEntity);
        rvCreditCards.setAdapter(mAdapter);
        fabFilter.setVisibility(View.VISIBLE);*/
        alertIncome();
    }

    private List<CreditCardEntity> filterCreditCardsbtIncome(FilterEntity incomeType) {
        List<CreditCardEntity> list = new ArrayList<>();

        if (incomeType.getPriority() == 0) {
            return listCreditCardEntity;
        }

        for (int i = 0; i < listCreditCardEntity.size(); i++) {

            if ((listCreditCardEntity.get(i).getSalarytype().equalsIgnoreCase(String.valueOf(EmploymentType))
                    && listCreditCardEntity.get(i).getiPriority() <= incomeType.getPriority())
                    || listCreditCardEntity.get(i).getSalarytype().equalsIgnoreCase("3")) {
                list.add(listCreditCardEntity.get(i));
            }
        }


       /* for (int i = 0; i < listCreditCardEntity.size(); i++) {

            if ((listCreditCardEntity.get(i).getiPriority() <= incomeType.getPriority()
                    || listCreditCardEntity.get(i).getSalarytype().equalsIgnoreCase(String.valueOf("3"))

                    && listCreditCardEntity.get(i).getSalarytype().equalsIgnoreCase(String.valueOf(EmploymentType)))
                    || listCreditCardEntity.get(i).getSalarytype().equalsIgnoreCase(String.valueOf("4"))) {
                list.add(listCreditCardEntity.get(i));
            }
        }*/

        return list;
    }

    public void redirectToApply(CreditCardEntity entity) {
        //if (spIncome.getSelectedItemPosition() != 0) {
        // redirect to apply
        // 1- RBL, 2- ICICI


        if (entity.getCreditCardId() == 1) {
            Intent intent = new Intent(this, RBLCreditApplyActivity.class);
            intent.putExtra(SELECTED_CREDIT_CARD, entity);
            intent.putExtra("EmploymentType", EmploymentType);
            startActivity(intent);
        } else if (entity.getCreditCardId() == 2) {
            Intent intent = new Intent(this, ICICICreditApplyActivity.class);
            intent.putExtra(SELECTED_CREDIT_CARD, entity);
            intent.putExtra("EmploymentType", EmploymentType);
            startActivity(intent);
        }
        //} else {
        //    Toast.makeText(this, "Select net annual income", Toast.LENGTH_SHORT).show();
        //}
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof CreditCardMasterResponse) {
            if (response.getStatusNo() == 0) {
                listFilterEntity = ((CreditCardMasterResponse) response).getMasterData().getFilter();
                listCreditCardEntity = ((CreditCardMasterResponse) response).getMasterData().getFilterdata();
                incomeFilterData();

            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
