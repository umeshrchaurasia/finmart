package com.datacomp.magicfinmart.salesmaterial;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.TouchImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.AccountDtlEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.DocsEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.SalesProductEntity;

public class SalesShareActivity extends BaseActivity implements BaseActivity.PopUpListener {

    DocsEntity docsEntity;
    TouchImageView ivProduct;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginResponseEntity;
    Bitmap salesPhoto;
    Bitmap pospPhoto;
    Bitmap pospDetails;
    Bitmap combinedImage;
    String pospNAme, pospDesg = "LandMark POSP", pospEmail, PospMobNo;
    SalesProductEntity salesProductEntity;
    AccountDtlEntity accountDtlEntity;
    URL pospPhotoUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerPopUp(this);
        dbPersistanceController = new DBPersistanceController(this);
        loginResponseEntity = dbPersistanceController.getUserData();
        accountDtlEntity = dbPersistanceController.getAccountData();

//        Toast.makeText(this, accountDtlEntity.getDesignation() + "\n" +
//                accountDtlEntity.getEditMobiNumb() + "\n" +
//                accountDtlEntity.getEditEmailId() + "\n" +
//                accountDtlEntity.getDisplayDesignation() + "\n" +
//                accountDtlEntity.getDisplayPhoneNo() + "\n" +
//                accountDtlEntity.getDisplayEmail(), Toast.LENGTH_LONG).show();

        if (getIntent().hasExtra(Constants.PRODUCT_ID)) {
            salesProductEntity = getIntent().getExtras().getParcelable(Constants.PRODUCT_ID);
            //The key argument here must match that used in the other activity
            /*switch (salesProductEntity.getProduct_Id()) {
                case 1:
                case 2:
                    //setPospDetails();
                    try {
                        combinedImage = BitmapFactory.decodeStream(new FileInputStream(getImageFromStorage("pospSalesMaterialDetails")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                case 4:
                case 5:
                    //setOtherDetails();
                    try {
                        combinedImage = BitmapFactory.decodeStream(new FileInputStream(getImageFromStorage("fbaSalesMaterialDetails")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }*/
        }


        initialize();

        /*BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        salesPhoto = BitmapFactory.decodeFile(docsEntity.getImage_path(), options);*/
        //salesPhoto = ((BitmapDrawable) ivProduct.getDrawable()).getBitmap();

        //new createBitmapFromURL(pospPhotoUrl).execute();
        new createBitmapFromURLNew().execute();
    }

    private void initialize() {
        ivProduct = (TouchImageView) findViewById(R.id.ivProduct);

        if (getIntent().hasExtra(Constants.DOC_DATA)) {
            docsEntity = getIntent().getExtras().getParcelable(Constants.DOC_DATA);
            /*Glide.with(this)
                    .load(docsEntity.getImage_path())
                    .asBitmap()
                    .placeholder(getResources().getDrawable(R.drawable.ambulance))
                    .into(ivProduct);*/

        }
    }

    private void setPospDetails() {
        if (accountDtlEntity != null) {

            if (loginResponseEntity != null) {
                if (loginResponseEntity.getPOSPName() != null && !loginResponseEntity.getPOSPName().equals("")) {
                    pospNAme = loginResponseEntity.getPOSPName();
                } else {
                    pospNAme = "POSP Name";
                }
            } else {
                pospNAme = "POSP Name";
            }

            if (accountDtlEntity.getDisplayEmail() != null && !accountDtlEntity.getDisplayEmail().equals("")) {
                pospEmail = accountDtlEntity.getDisplayEmail();
            } else {
                if (loginResponseEntity.getPOSEmail() != null && !loginResponseEntity.getPOSEmail().equals("")) {
                    pospEmail = loginResponseEntity.getPOSEmail();
                } else {
                    pospEmail = "XXXXXX@finmart.com";
                }
            }

            if (accountDtlEntity.getDisplayDesignation() != null && !accountDtlEntity.getDisplayDesignation().equals("")) {
                pospDesg = accountDtlEntity.getDisplayDesignation();
            } else {
                pospDesg = "LandMark POSP";
            }

            if (accountDtlEntity.getDisplayPhoneNo() != null && !accountDtlEntity.getDisplayPhoneNo().equals("")) {
                PospMobNo = accountDtlEntity.getDisplayPhoneNo();
            } else {
                if (loginResponseEntity.getPOSPMobile() != null && !loginResponseEntity.getPOSPMobile().equals("")) {
                    PospMobNo = loginResponseEntity.getPOSPMobile();
                } else {
                    PospMobNo = "98XXXXXXXX";
                }

            }
        } else {
            pospNAme = "POSP Name";
            pospEmail = "XXXXXX@finmart.com";
            pospDesg = "LandMark POSP";
            PospMobNo = "98XXXXXXXX";
        }
        //setting photo url
        if (loginResponseEntity != null) {
            if (loginResponseEntity.getPOSPProfileUrl() != null && !loginResponseEntity.getPOSPProfileUrl().equals("")) {
                try {
                    pospPhotoUrl = new URL(loginResponseEntity.getPOSPProfileUrl());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (pospPhotoUrl == null) {
            try {
                pospPhotoUrl = new URL("http://qa.mgfm.in/images/profile_pic.png");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setOtherDetails() {

        if (accountDtlEntity != null) {

            if (loginResponseEntity != null) {
                if (loginResponseEntity.getFullName() != null && !loginResponseEntity.getFullName().equals("")) {
                    pospNAme = loginResponseEntity.getFullName();
                } else {
                    pospNAme = "FBA Name";
                }
            } else {
                pospNAme = "FBA Name";
            }

            if (accountDtlEntity.getEditEmailId() != null && !accountDtlEntity.getEditEmailId().equals("")) {
                pospEmail = accountDtlEntity.getEditEmailId();
            } else {
                pospEmail = "XXXXXX@finmart.com";
            }

            if (accountDtlEntity.getDesignation() != null && !accountDtlEntity.getDesignation().equals("")) {
                pospDesg = accountDtlEntity.getDesignation();
            } else {
                pospDesg = "FBA SUPPORT ASSISTANT";
            }

            if (accountDtlEntity.getEditMobiNumb() != null && !accountDtlEntity.getEditMobiNumb().equals("")) {
                PospMobNo = accountDtlEntity.getEditMobiNumb();
            } else {
                PospMobNo = "98XXXXXXXX";
            }
        } else {
            pospNAme = "FBA Name";
            pospEmail = "XXXXXX@finmart.com";
            pospDesg = "FBA SUPPORT ASSISTANT";
            PospMobNo = "98XXXXXXXX";
        }
        //setting photo url
        if (loginResponseEntity != null) {
            if (loginResponseEntity.getFBAProfileUrl() != null && !loginResponseEntity.getFBAProfileUrl().equals("")) {
                try {
                    pospPhotoUrl = new URL(loginResponseEntity.getFBAProfileUrl());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (pospPhotoUrl == null) {
            try {
                pospPhotoUrl = new URL("http://qa.mgfm.in/images/profile_pic.png");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
            return true;
        } else if (i == R.id.action_share) {
            if (salesProductEntity.getProduct_Id() == 1 || salesProductEntity.getProduct_Id() == 2) {
                if (Utility.checkShareStatus(this) == 1) {
                    showShareProduct();
                } else {
                    openPopUp(ivProduct, "Message", "Your POSP status is INACTIVE", "OK", true);
                }
            } else {
                showShareProduct();
            }


        } else if (i == R.id.action_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    public void showShareProduct() {
        if (combinedImage != null)
            datashareList(SalesShareActivity.this, combinedImage, "Finmart", "");
//        //new shareImageNormal(docsEntity.getImage_path(), "Finmart", "Look what I found on Finmart!").execute();


    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        int i = view.getId();
        if (i == R.id.ivProduct) {
            dialog.cancel();

        }
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        int i = view.getId();
        if (i == R.id.ivProduct) {
            dialog.cancel();

        }
    }

    public class createBitmapFromURL extends AsyncTask<Void, Void, Bitmap> {

        URL url;

        public createBitmapFromURL(URL url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            showDialog();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap networkBitmap = null;

            if (url == null) {
                AssetManager assetManager = getAssets();
                InputStream istr;
                try {
                    istr = assetManager.open("file:///android_asset/profile_pic.png");
                    networkBitmap = BitmapFactory.decodeStream(istr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    networkBitmap = BitmapFactory.decodeStream(
                            pospPhotoUrl.openConnection().getInputStream());
                    URL salePhotoUrl = new URL(docsEntity.getImage_path());
                    salesPhoto = BitmapFactory.decodeStream(
                            salePhotoUrl.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return networkBitmap;
        }

        protected void onPostExecute(Bitmap result) {

            pospPhoto = result;

            try {
                if (pospPhoto != null) {
                    // salesPhoto = ((GlideBitmapDrawable) ivProduct.getDrawable()).getBitmap();
                    /*if (salesPhoto == null) {
                        try {
                            salesPhoto = BitmapFactory.decodeStream(pospPhotoUrl.openConnection().getInputStream());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }*/
                    //datashareList(BaseActivity.this, result);
                    pospDetails = createBitmap(pospPhoto, pospNAme, pospDesg, PospMobNo, pospEmail);
                    combinedImage = combineImages(salesPhoto, pospDetails);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    combinedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Glide.with(SalesShareActivity.this)
                            .load(stream.toByteArray())
                            .asBitmap()
                            .into(ivProduct);
                    cancelDialog();
                }
            } catch (Exception e) {
                cancelDialog();
                e.printStackTrace();
                Log.e("TAG", "Could not load Bitmap from: " + e.getMessage());

            }
        }
    }

    public class createBitmapFromURLNew extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            showDialog();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {

            switch (salesProductEntity.getProduct_Id()) {
                case 1:
                case 2:
                    //setPospDetails();
                    try {
                        combinedImage = BitmapFactory.decodeStream(new FileInputStream(getImageFromStorage("pospSalesMaterialDetails")));
                        URL salePhotoUrl = new URL(docsEntity.getImage_path());
                        salesPhoto = BitmapFactory.decodeStream(
                                salePhotoUrl.openConnection().getInputStream());
                        if (combinedImage != null && salesPhoto != null) {
                            combinedImage = combineImages(salesPhoto, combinedImage);
                            //ivProduct.setImageBitmap(combinedImage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                case 4:
                case 5:
                    //setOtherDetails();
                    try {
                        combinedImage = BitmapFactory.decodeStream(new FileInputStream(getImageFromStorage("fbaSalesMaterialDetails")));
                        URL salePhotoUrl = new URL(docsEntity.getImage_path());
                        salesPhoto = BitmapFactory.decodeStream(
                                salePhotoUrl.openConnection().getInputStream());
                        if (combinedImage != null && salesPhoto != null) {
                            combinedImage = combineImages(salesPhoto, combinedImage);
                            //ivProduct.setImageBitmap(combinedImage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return combinedImage;
        }

        protected void onPostExecute(Bitmap result) {

            cancelDialog();
            if (result == null) {
                Glide.with(SalesShareActivity.this)
                        .load(docsEntity.getImage_path())
                        .asBitmap()
                        .placeholder(getResources().getDrawable(R.drawable.finmart_placeholder))
                        .into(ivProduct);
            } else {
                ivProduct.setImageBitmap(result);
            }


        }
    }

}
