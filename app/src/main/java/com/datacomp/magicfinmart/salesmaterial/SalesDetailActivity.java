package com.datacomp.magicfinmart.salesmaterial;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.salesmaterial.SalesMaterialController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CompanyEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.DocsEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.SalesProductEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SalesPromotionResponse;

public class SalesDetailActivity extends BaseActivity implements IResponseSubcriber {

    RecyclerView rvCompany;
    RecyclerView rvProduct;
    SalesCompanyAdapter comAdapter;
    SalesDocAdapter docAdapter;
    TextView txtEng, txtHindi;
    Switch swlang;
    SalesProductEntity salesProductEntity;

    private List<CompanyEntity> companyLst;
    private List<DocsEntity> docLst;
    int numberOfColumns = 2;
    DBPersistanceController dbPersistanceController;
    private String langType = "English";
    private String companyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbPersistanceController = new DBPersistanceController(SalesDetailActivity.this);

        companyID = "0";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            salesProductEntity = extras.getParcelable(Constants.PRODUCT_ID);
            //The key argument here must match that used in the other activity
        }
        init();
        showDialog();
        new SalesMaterialController(this).getProductPromotions(salesProductEntity.getProduct_Id(), SalesDetailActivity.this);

    }

    private void init() {

        companyLst = new ArrayList<CompanyEntity>();
        docLst = new ArrayList<DocsEntity>();

        rvCompany = (RecyclerView) findViewById(R.id.rvCompany);
        rvCompany.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCompany.setLayoutManager(layoutManager);

        rvProduct = (RecyclerView) findViewById(R.id.rvProduct);
        rvProduct.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, numberOfColumns);
        rvProduct.setLayoutManager(layoutManager1);

        txtEng = (TextView) findViewById(R.id.txtEng);
        txtHindi = (TextView) findViewById(R.id.txtHindi);
        swlang = (Switch) findViewById(R.id.swlang);

        swlang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    langType = "Hindi";

                    txtHindi.setTextColor(ContextCompat.getColor(SalesDetailActivity.this, R.color.colorAccent));
                    // txtHindi.getContext().getResources().getString(R.string.Hindi_bold);;
                    txtHindi.setTypeface(null, Typeface.BOLD);

                    txtEng.setTextColor(ContextCompat.getColor(SalesDetailActivity.this, R.color.seperator_white));
                    //  txtEng.getContext().getResources().getString(R.string.Eng);;
                    txtEng.setTypeface(null, Typeface.NORMAL);


                } else {
                    langType = "English";
                    txtEng.setTextColor(ContextCompat.getColor(SalesDetailActivity.this, R.color.colorAccent));
                    //txtEng.getContext().getResources().getString(R.string.Eng_bold);
                    txtEng.setTypeface(null, Typeface.BOLD);

                    txtHindi.setTextColor(ContextCompat.getColor(SalesDetailActivity.this, R.color.seperator_white));
                    txtHindi.setTypeface(null, Typeface.NORMAL);


                }

                bindDocList(companyID, langType);
            }
        });


    }

    private void bindCompanyList() {

        if (companyLst.size() > 0) {
            companyLst.get(0).setSelected(true);
            comAdapter = new SalesCompanyAdapter(SalesDetailActivity.this, companyLst);
            rvCompany.setAdapter(comAdapter);

            if (swlang.isChecked()) {
                langType = "Hindi";
            } else {
                langType = "English";
            }
            companyID = companyLst.get(0).getCompany_id();
            bindDocList(companyID, langType);
        } else {
            rvCompany.setAdapter(null);

        }
    }

    private void bindDocList(String compID, String langType) {

        List<DocsEntity> docLst = dbPersistanceController.getDocList(compID, langType);
        if (docLst.size() > 0) {
            docAdapter = new SalesDocAdapter(SalesDetailActivity.this, docLst);

            rvProduct.setAdapter(docAdapter);
        } else {
            rvProduct.setAdapter(null);

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof SalesPromotionResponse) {
            companyLst = ((SalesPromotionResponse) response).getMasterData().getCompany();

            bindCompanyList();

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }


    public void filterDocViaCompID(String compID) {
        companyID = compID;
        if (swlang.isChecked()) {
            langType = "Hindi";
        } else {
            langType = "English";
        }
        bindDocList(compID, langType);
    }

    public void redirectToApplyMain(DocsEntity docsEntity) {

        Intent intent = new Intent(this, SalesShareActivity.class);
        intent.putExtra(Constants.DOC_DATA, docsEntity);
        intent.putExtra(Constants.PRODUCT_ID, salesProductEntity);
        startActivity(intent);
    }


    private List<CompanyEntity> getComLst() {
        List<CompanyEntity> companyLst = new ArrayList<>();

        companyLst.add(new CompanyEntity("1", "General"));
        companyLst.add(new CompanyEntity("2", "Bharti"));
        companyLst.add(new CompanyEntity("3", "Liberty Videcon"));
        companyLst.add(new CompanyEntity("4", "SRL Diagno"));
        companyLst.add(new CompanyEntity("5", "TATA"));
        companyLst.add(new CompanyEntity("6", "General 2"));
        return companyLst;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
}
