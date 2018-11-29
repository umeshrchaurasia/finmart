package com.datacomp.magicfinmart.salesmaterial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.salesmaterial.SalesMaterialController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CompanyEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.SalesProductEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MyAcctDtlResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SalesMaterialProductResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SalesPromotionResponse;

public class SalesMaterialActivity extends BaseActivity implements IResponseSubcriber {

    RecyclerView rvSalesMaterial;
    SalesMaterialAdapter mAdapter;
    List<SalesProductEntity> mlistSalesProduct;
    DBPersistanceController dbPersistanceController;
    List<CompanyEntity> companyLst;

    //LoginResponseEntity loginResponseEntity;
    String pospNAme, pospDesg = "LandMark POSP", pospEmail, PospMobNo;
    String fbaNAme, fbaDesg = "FBA SUPPORT ASSISTANT", fbaEmail, fbaMobNo;
    //AccountDtlEntity accountDtlEntity;
    UserConstantEntity userConstantEntity;
    URL pospPhotoUrl = null, fbaPhotoUrl = null;
    SharePospDetailsEntity sharePospDetailsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_material);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbPersistanceController = new DBPersistanceController(SalesMaterialActivity.this);
        //loginResponseEntity = dbPersistanceController.getUserData();
        //accountDtlEntity = dbPersistanceController.getAccountData();
        userConstantEntity = dbPersistanceController.getUserConstantsData();
        init();
        fetchProducts();

        if (userConstantEntity != null) {
            try {
                setOtherDetails();
                setPospDetails();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            new createBitmapFromURLPosp(pospPhotoUrl).execute();
            new createBitmapFromURFba(fbaPhotoUrl).execute();
        } else {

        }
    }

    private void fetchProducts() {
        showDialog();
        new SalesMaterialController(this).getSalesProducts(this);
    }

    private void init() {
        companyLst = new ArrayList<CompanyEntity>();
        rvSalesMaterial = (RecyclerView) findViewById(R.id.rvSalesMaterial);
        rvSalesMaterial.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSalesMaterial.setLayoutManager(layoutManager);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        boolean isUpdate = false;
        if (response instanceof SalesMaterialProductResponse) {
            mlistSalesProduct = ((SalesMaterialProductResponse) response).getMasterData();
            //mlistSalesProduct = getProducttList();

            List<SalesProductEntity> compLst = dbPersistanceController.getCompanyList();
            if (compLst != null) {
                if (compLst.size() > 0) {
                    for (int i = 0; i < mlistSalesProduct.size(); i++) {
                        for (SalesProductEntity oldComEntiy : compLst) {
                            if (mlistSalesProduct.get(i).getProduct_Id() == oldComEntiy.getProduct_Id()) {

                                if (oldComEntiy.getOldCount() > mlistSalesProduct.get(i).getCount()) {
                                    isUpdate = true;
                                    break;
                                }

                                mlistSalesProduct.get(i).setOldCount(oldComEntiy.getOldCount());

                            }
                        }
                    }
                }


            }


            // case 1 : for first time when db data is empty OR Server list size is change ie product is increased / decreased
            // case 2 : if sever product count is lesss than db product count than db should update

            if (compLst.size() != mlistSalesProduct.size() || isUpdate) {
                for (int i = 0; i < mlistSalesProduct.size(); i++) {

                    mlistSalesProduct.get(i).setOldCount(0);
                }

                dbPersistanceController.storeCompanyList(mlistSalesProduct);
            }

            // region comment
//            Gson gson = new Gson();
//
//            String listString = gson.toJson(
//                    mlistSalesProduct,
//                    new TypeToken<ArrayList<SalesProductEntity>>() {}.getType());
//
//            try {
//                JSONArray jsonArray = new JSONArray(listString);
//            }catch (Exception ex){
//
//            }

            // endregion

            mAdapter = new SalesMaterialAdapter(this, mlistSalesProduct);
            rvSalesMaterial.setAdapter(mAdapter);
        } else if (response instanceof SalesPromotionResponse) {
            companyLst = ((SalesPromotionResponse) response).getMasterData().getCompany();

        } else if (response instanceof MyAcctDtlResponse) {
            if (response.getStatusNo() == 0) {
                //accountDtlEntity = ((MyAcctDtlResponse) response).getMasterData().get(0);  // 05
                /*if (accountDtlEntity != null) {
                    setOtherDetails();
                    setPospDetails();
                    new createBitmapFromURLPosp(pospPhotoUrl).execute();
                    new createBitmapFromURFba(fbaPhotoUrl).execute();
                }*/
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void redirectToApplyMain(SalesProductEntity entity, int pos) {

        if (entity.getCount() > entity.getOldCount()) {
            alertCount(entity, pos);
        } else {

            Intent intent = new Intent(SalesMaterialActivity.this, SalesDetailActivity.class);
            intent.putExtra(Constants.PRODUCT_ID, entity);
            startActivity(intent);

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
            onBackPressed();
//                Intent intent = new Intent(this, HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.putExtra("MarkTYPE", "FROM_HOME");
//                startActivity(intent);
//
//                finish();

        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//
//        supportFinishAfterTransition();
//        super.onBackPressed();
//    }
//
@Override
public boolean onSupportNavigateUp() {
    onBackPressed();
    return false;
    // return super.onSupportNavigateUp();
}

    public List<SalesProductEntity> getProducttList() {
        List<SalesProductEntity> EmploymentEntityList = new ArrayList<SalesProductEntity>();

        EmploymentEntityList.add(new SalesProductEntity(1, "Health Insurance", "http://bo.mgfm.in/uploads/sales_material/440Your-Search.jpg", 5));
        EmploymentEntityList.add(new SalesProductEntity(2, "Motor Insurance", "http://qa.mgfm.in/uploads/salesmaterial/motor.png", 5));
        EmploymentEntityList.add(new SalesProductEntity(3, "Health Assure", "http://qa.mgfm.in/uploads/salesmaterial/healthassure.png", 2));
        EmploymentEntityList.add(new SalesProductEntity(4, "Loans", "http://qa.mgfm.in/uploads/salesmaterial/loans.png", 2));
        EmploymentEntityList.add(new SalesProductEntity(5, "Health Insurance", "http://qa.mgfm.in/uploads/salesmaterial/finpeace.png", 4));

        return EmploymentEntityList;
    }


    private void alertCount(final SalesProductEntity salesProductEntity, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        int count = (salesProductEntity.getCount() - salesProductEntity.getOldCount());
        ImageView ivCross;
        Button btnLater, btnDownload;
        TextView txtMessage;

        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_material_count_popup, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        txtMessage = (TextView) dialogView.findViewById(R.id.txtMessage);
        btnLater = (Button) dialogView.findViewById(R.id.btnLater);
        btnDownload = (Button) dialogView.findViewById(R.id.btnDownload);
        ivCross = (ImageView) dialogView.findViewById(R.id.ivCross);

        txtMessage.setText(count + " " + getString(R.string.materail_count));


        ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });


        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                mAdapter.updateList(salesProductEntity, position);

                Intent intent = new Intent(SalesMaterialActivity.this, SalesDetailActivity.class);
                intent.putExtra(Constants.PRODUCT_ID, salesProductEntity);
                startActivity(intent);

            }
        });


        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    public class createBitmapFromURLPosp extends AsyncTask<Void, Void, Bitmap> {
        URL url;

        public createBitmapFromURLPosp(URL url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap networkBitmap = null;
            try {
                if (url == null) {
                    Drawable d = getResources().getDrawable(R.drawable.profile_pic);
                    networkBitmap = ((BitmapDrawable) d).getBitmap();
                } else {
                    networkBitmap = BitmapFactory.decodeStream(
                            url.openConnection().getInputStream());
                }


            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAG", "Could not load Bitmap from: " + url);
            } finally {
                if (networkBitmap == null) {
                    Drawable d = getResources().getDrawable(R.drawable.profile_pic);
                    networkBitmap = ((BitmapDrawable) d).getBitmap();
                }
            }

            return networkBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                Bitmap pospDetails = createBitmap(result, pospNAme, pospDesg, PospMobNo, pospEmail);
                saveImageToStorage(pospDetails, "pospSalesMaterialDetails");
            }
            // bitmap_image = result;
        }
    }

    public class createBitmapFromURFba extends AsyncTask<Void, Void, Bitmap> {
        URL url;

        public createBitmapFromURFba(URL url) {
            this.url = url;
        }


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap networkBitmap = null;
            try {

                if (url == null) {
                    Drawable d = getResources().getDrawable(R.drawable.profile_pic);
                    networkBitmap = ((BitmapDrawable) d).getBitmap();
                } else {
                    networkBitmap = BitmapFactory.decodeStream(
                            url.openConnection().getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAG", "Could not load Bitmap from: " + url);
            } finally {
                if (networkBitmap == null) {
                    Drawable d = getResources().getDrawable(R.drawable.profile_pic);
                    networkBitmap = ((BitmapDrawable) d).getBitmap();
                }
            }

            return networkBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                Bitmap fbaDetails = createBitmap(result, fbaNAme, fbaDesg, fbaMobNo, fbaEmail);
                saveImageToStorage(fbaDetails, "fbaSalesMaterialDetails");
            }
        }
    }

    private void setPospDetails() throws MalformedURLException {


        pospNAme = "POSP Name";
        pospEmail = "XXXXXX@finmart.com";
        pospDesg = "LandMark POSP";
        PospMobNo = "98XXXXXXXX";
        pospPhotoUrl = new URL("http://qa.mgfm.in/images/profile_pic.png");
        if (userConstantEntity != null) {
            if (userConstantEntity.getPospsendname() != null && !userConstantEntity.getPospsendname().equals("")) {
                pospNAme = userConstantEntity.getPospsendname();
            }
            if (userConstantEntity.getPospsendemail() != null && !userConstantEntity.getPospsendemail().equals("")) {
                pospEmail = userConstantEntity.getPospsendemail();
            }
            if (userConstantEntity.getPospsendmobile() != null && !userConstantEntity.getPospsendmobile().equals("")) {
                PospMobNo = userConstantEntity.getPospsendmobile();
            }
            if (userConstantEntity.getPospsenddesignation() != null && !userConstantEntity.getPospsenddesignation().equals("")) {
                pospDesg = userConstantEntity.getPospsenddesignation();
            }
            if (userConstantEntity.getPospsendphoto() != null && !userConstantEntity.getPospsendphoto().equals("")) {
                pospPhotoUrl = new URL(userConstantEntity.getPospsendphoto());
            }
        }
    }

    private void setOtherDetails() throws MalformedURLException {


        fbaNAme = "FBA Name";
        fbaEmail = "XXXXXX@finmart.com";
        fbaDesg = "FBA SUPPORT ASSISTANT";
        fbaMobNo = "98XXXXXXXX";
        fbaPhotoUrl = new URL("http://qa.mgfm.in/images/profile_pic.png");
        if (userConstantEntity != null) {
            if (userConstantEntity.getLoansendname() != null && !userConstantEntity.getLoansendname().equals("")) {
                fbaNAme = userConstantEntity.getLoansendname();
            }
            if (userConstantEntity.getLoansendemail() != null && !userConstantEntity.getLoansendemail().equals("")) {
                fbaEmail = userConstantEntity.getLoansendemail();
            }
            if (userConstantEntity.getLoansendmobile() != null && !userConstantEntity.getLoansendmobile().equals("")) {
                fbaMobNo = userConstantEntity.getLoansendmobile();
            }
            if (userConstantEntity.getLoansenddesignation() != null && !userConstantEntity.getLoansenddesignation().equals("")) {
                fbaDesg = userConstantEntity.getLoansenddesignation();
            }
            if (userConstantEntity.getLoansendphoto() != null && !userConstantEntity.getLoansendphoto().equals("")) {
                fbaPhotoUrl = new URL(userConstantEntity.getLoansendphoto());
            }
        }
    }

    public class downloadBitmapFromUrl extends AsyncTask<String, Void, Void> {

        Bitmap networkBitmap;

        @Override
        protected void onPreExecute() {
            showDialog("Downloading...");
        }

        @Override
        protected Void doInBackground(String... strings) {

            for (String string : strings) {

                try {
                    URL url = new URL(string);
                    HttpURLConnection.setFollowRedirects(false);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("HEAD");
                    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        networkBitmap = BitmapFactory.decodeStream(
                                url.openConnection().getInputStream());
                        saveImageToStorage(networkBitmap, "fbaSalesMaterialDetails");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            cancelDialog();
            Toast.makeText(SalesMaterialActivity.this, "Image Downloaded", Toast.LENGTH_SHORT).show();
        }
    }

    public void setSharePospDetails() {
        sharePospDetailsEntity = new SharePospDetailsEntity();
        /*if (loginResponseEntity != null) {

        }*/
    }
}
