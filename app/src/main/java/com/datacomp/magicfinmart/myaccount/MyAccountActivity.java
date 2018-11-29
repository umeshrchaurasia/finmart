package com.datacomp.magicfinmart.myaccount;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.utility.CircleTransform;
import com.datacomp.magicfinmart.utility.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.AccountDtlEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.DocAvailableEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.IfscEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.RegisterRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.DocumentResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.IfscCodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MyAccountResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.MyAcctDtlResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PincodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.UserConstatntResponse;
import okhttp3.MultipartBody;


/**
 * Created by daniyalshaikh on 10/01/18.
 */

public class MyAccountActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener, IResponseSubcriber, BaseActivity.PopUpListener {

    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PICTURE = 1800;
    PrefManager prefManager;
    int type;
    LinearLayout llMyProfile, llAddress, llBankDetail, llDocumentUpload, llPosp, llAbout, llNotify;
    ImageView ivMyProfile, ivAddress, ivBankDetail, ivDocumentUpload, ivPOSP, ivProfile, ivAbout,
            ivPhotoCam, ivPhotoGallery, ivPanCam, ivPanGallery, ivCancelCam, ivCancelGallery, ivAadharCam, ivAadharGallery,
            ivAadhar, ivCancel, ivPan, ivPhoto, ivUser, ivNotify;
    RelativeLayout rlMyProfile, rlAddress, rlBankDetail, rlDocumentUpload, rlPOSP, rlAbout, rlNotify;

    EditText etSubHeading, etMobileNo, etEmailId, etAddress1, etAddress2, etAddress3, etPincode,
            etCity, etState, etAccountHolderName, etAadhaar, etPAN, etBankAcNo, etIfscCode,
            etMicrCode, etBankName, etBankBranch, etBankCity, etSubHeading_posp, etMobileNo_posp, etEmailId_posp;
    TextView txtSaving, txtCurrent, tvName, txtManagerName, tvFbaCode, tvPospNo, tvLoginId, tvPospStatus, txtManagerMobile, txtManagerEmail, txtSupportMobile, txtSupportEmail;

    AppCompatImageView ivManagerMobile, ivManagerEmail, ivSupportMobile, ivSupportEmail;
    ScrollView mainScrollView;
    Switch swNotify;

    Button btnSave;
    RegisterRequestEntity registerRequestEntity;
    DBPersistanceController dbPersistanceController;
    LoginResponseEntity loginEntity;
    AccountDtlEntity accountDtlEntity;
    public String ACCOUNT_TYPE = "SAVING";

    HashMap<String, String> body;
    MultipartBody.Part part;
    File Docfile;
    File file;
    Uri imageUri;
    InputStream inputStream;
    ExifInterface ei;
    // private String PROFILE = "1", PHOTO = "2", PAN = "3", CANCEL_CHQ = "4", AADHAR = "5";

    private int PROFILE = 1, PHOTO = 2, PAN = 3, CANCEL_CHQ = 4, AADHAR = 5;

    private String PHOTO_File = "FBAPhotograph", PAN_File = "LoanRepPanCard", CANCEL_CHQ_File = "LoanRepCancelChq", AADHAR_File = "OtherAadharCard";

    private String PHOTO_EXT = "FBAPhotograph.jpg", PAN_EXT = "LoanRepPanCard.jpg", CANCEL_CHQ_EXT = "LoanRepCancelChq.jpg", AADHAR_EXT = "OtherAadharCard.jpg";

    Boolean isDataUploaded = true;
    Bitmap bitmapPhoto = null;
    LoginResponseEntity loginResponseEntity;
    String[] permissionsRequired = new String[]{Manifest.permission.CALL_PHONE};

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbPersistanceController = new DBPersistanceController(this);
        loginResponseEntity = dbPersistanceController.getUserData();
        loginEntity = dbPersistanceController.getUserData();

        registerPopUp(this);
        registerRequestEntity = new RegisterRequestEntity();
        registerRequestEntity.setFBAID(loginEntity.getFBAId());
        prefManager = new PrefManager(this);
        initWidgets();
        setListener();
        initLayouts();


        if (dbPersistanceController.getUserConstantsData() != null) {
            bindAboutMe();

        } else {

            new MasterController(this).geUserConstant(1, this);
        }


        showDialog("Fetching Detail...");
        new RegisterController(MyAccountActivity.this).getMyAcctDtl(String.valueOf(loginEntity.getFBAId()), MyAccountActivity.this);


    }


    private void initLayouts() {
        llMyProfile.setVisibility(View.GONE);
        llAddress.setVisibility(View.GONE);
        llBankDetail.setVisibility(View.GONE);
        llDocumentUpload.setVisibility(View.GONE);
        hideAllLayouts(llMyProfile, ivMyProfile);
    }

    private void setListener() {
        ivAddress.setOnClickListener(this);
        ivMyProfile.setOnClickListener(this);
        rlMyProfile.setOnClickListener(this);
        rlAddress.setOnClickListener(this);

        rlBankDetail.setOnClickListener(this);
        ivBankDetail.setOnClickListener(this);

        rlDocumentUpload.setOnClickListener(this);
        ivDocumentUpload.setOnClickListener(this);

        rlPOSP.setOnClickListener(this);
        ivPOSP.setOnClickListener(this);

        rlAbout.setOnClickListener(this);
        ivAbout.setOnClickListener(this);

        rlNotify.setOnClickListener(this);
        ivNotify.setOnClickListener(this);

        ivProfile.setOnClickListener(this);
        ivPhotoCam.setOnClickListener(this);
        ivPhotoGallery.setOnClickListener(this);
        ivPanCam.setOnClickListener(this);
        ivPanGallery.setOnClickListener(this);

        ivCancelCam.setOnClickListener(this);
        ivCancelGallery.setOnClickListener(this);
        ivAadharCam.setOnClickListener(this);
        ivAadharGallery.setOnClickListener(this);

        ivManagerMobile.setOnClickListener(this);
        ivManagerEmail.setOnClickListener(this);
        ivSupportMobile.setOnClickListener(this);
        ivSupportEmail.setOnClickListener(this);


        btnSave.setOnClickListener(this);
        txtSaving.setOnClickListener(this);
        txtCurrent.setOnClickListener(this);


        etPincode.addTextChangedListener(pincodeTextWatcher);
        etIfscCode.setOnFocusChangeListener(this);

        etAadhaar.setOnFocusChangeListener(acAdhaarFocusChange);

        swNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    prefManager.updateNotificationsetting("0");
                  //  Toast.makeText(getApplicationContext(), "Switch is on", Toast.LENGTH_LONG).show();
                } else {
                   // Toast.makeText(getApplicationContext(), "Switch is off", Toast.LENGTH_LONG).show();
                    prefManager.updateNotificationsetting("1");
                }
            }
        });

    }

    private void initWidgets() {
        mainScrollView = (ScrollView) findViewById(R.id.mainScrollView);
        ivAddress = (ImageView) findViewById(R.id.ivAddress);
        ivMyProfile = (ImageView) findViewById(R.id.ivMyProfile);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        llMyProfile = (LinearLayout) findViewById(R.id.llMyProfile);
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        llDocumentUpload = (LinearLayout) findViewById(R.id.llDocumentUpload);
        llBankDetail = (LinearLayout) findViewById(R.id.llBankDetail);
        llPosp = (LinearLayout) findViewById(R.id.llPosp);
        llAbout = (LinearLayout) findViewById(R.id.llAbout);
        llNotify = (LinearLayout) findViewById(R.id.llNotify);

        rlMyProfile = (RelativeLayout) findViewById(R.id.rlMyProfile);
        rlAddress = (RelativeLayout) findViewById(R.id.rlAddress);
        rlBankDetail = (RelativeLayout) findViewById(R.id.rlBankDetail);
        ivBankDetail = (ImageView) findViewById(R.id.ivBankDetail);
        rlDocumentUpload = (RelativeLayout) findViewById(R.id.rlDocumentUpload);
        rlPOSP = (RelativeLayout) findViewById(R.id.rlPOSP);
        rlAbout = (RelativeLayout) findViewById(R.id.rlAbout);
        rlNotify = (RelativeLayout) findViewById(R.id.rlNotify);

        ivDocumentUpload = (ImageView) findViewById(R.id.ivDocumentUpload);
        ivPOSP = (ImageView) findViewById(R.id.ivPOSP);
        ivAbout = (ImageView) findViewById(R.id.ivAbout);


        etSubHeading = (EditText) findViewById(R.id.etSubHeading);
        etMobileNo = (EditText) findViewById(R.id.etMobileNo);
        etEmailId = (EditText) findViewById(R.id.etEmailId);
        etAddress1 = (EditText) findViewById(R.id.etAddress1);
        etAddress2 = (EditText) findViewById(R.id.etAddress2);

        etAddress3 = (EditText) findViewById(R.id.etAddress3);
        etPincode = (EditText) findViewById(R.id.etPincode);
        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);
        etAccountHolderName = (EditText) findViewById(R.id.etAccountHolderName);

        etAadhaar = (EditText) findViewById(R.id.etAadhaar);
        etPAN = (EditText) findViewById(R.id.etPAN);
        etBankAcNo = (EditText) findViewById(R.id.etBankAcNo);
        etIfscCode = (EditText) findViewById(R.id.etIfscCode);

        etMicrCode = (EditText) findViewById(R.id.etMicrCode);
        etBankName = (EditText) findViewById(R.id.etBankName);
        etBankBranch = (EditText) findViewById(R.id.etBankBranch);
        etBankCity = (EditText) findViewById(R.id.etBankCity);

        etSubHeading_posp = (EditText) findViewById(R.id.etSubHeading_posp);
        etMobileNo_posp = (EditText) findViewById(R.id.etMobileNo_posp);
        etEmailId_posp = (EditText) findViewById(R.id.etEmailId_posp);


        txtSaving = (TextView) findViewById(R.id.txtSaving);
        txtCurrent = (TextView) findViewById(R.id.txtCurrent);

        ivUser = (ImageView) findViewById(R.id.ivUser);
        ivPhotoCam = (ImageView) findViewById(R.id.ivPhotoCam);
        ivPhotoGallery = (ImageView) findViewById(R.id.ivPhotoGallery);
        ivPanCam = (ImageView) findViewById(R.id.ivPanCam);
        ivPanGallery = (ImageView) findViewById(R.id.ivPanGallery);

        ivCancelCam = (ImageView) findViewById(R.id.ivCancelCam);
        ivCancelGallery = (ImageView) findViewById(R.id.ivCancelGallery);
        ivAadharCam = (ImageView) findViewById(R.id.ivAadharCam);
        ivAadharGallery = (ImageView) findViewById(R.id.ivAadharGallery);

        ivAadhar = (ImageView) findViewById(R.id.ivAadhar);
        ivCancel = (ImageView) findViewById(R.id.ivCancel);
        ivPan = (ImageView) findViewById(R.id.ivPan);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);


        // region About Me
        ivManagerMobile = (AppCompatImageView) findViewById(R.id.ivManagerMobile);
        ivManagerEmail = (AppCompatImageView) findViewById(R.id.ivManagerEmail);
        ivSupportMobile = (AppCompatImageView) findViewById(R.id.ivSupportMobile);
        ivSupportEmail = (AppCompatImageView) findViewById(R.id.ivSupportEmail);

        tvName = (TextView) findViewById(R.id.tvName);
        txtManagerName = (TextView) findViewById(R.id.txtManagerName);
        tvFbaCode = (TextView) findViewById(R.id.tvFbaCode);
        tvPospNo = (TextView) findViewById(R.id.tvPospNo);
        tvLoginId = (TextView) findViewById(R.id.tvLoginId);

        tvPospStatus = (TextView) findViewById(R.id.tvPospStatus);
        txtManagerMobile = (TextView) findViewById(R.id.txtManagerMobile);
        txtManagerEmail = (TextView) findViewById(R.id.txtManagerEmail);
        txtSupportMobile = (TextView) findViewById(R.id.txtSupportMobile);
        txtSupportEmail = (TextView) findViewById(R.id.txtSupportEmail);
        //endregion

        // regionNotify Setting
        ivNotify = (ImageView) findViewById(R.id.ivNotify);
        swNotify = (Switch) findViewById(R.id.swNotify);
        // region About Me

        btnSave = (Button) findViewById(R.id.btnSave);


    }

    private void bindAboutMe() {
        UserConstantEntity userConstantEntity = dbPersistanceController.getUserConstantsData();

        tvName.setText(loginResponseEntity.getFullName());
        tvFbaCode.setText("" + userConstantEntity.getFBAId());
        if (userConstantEntity.getPOSPNo() != null) {
            tvPospNo.setText("" + userConstantEntity.getPOSPNo());
        }
        tvLoginId.setText("" + userConstantEntity.getLoginID());
        tvPospStatus.setText(userConstantEntity.getPOSP_STATUS());

        txtManagerName.setText(userConstantEntity.getManagName());
        txtManagerMobile.setText(userConstantEntity.getMangMobile());
        txtManagerEmail.setText(userConstantEntity.getMangEmail());

        txtSupportMobile.setText(userConstantEntity.getSuppMobile());
        txtSupportEmail.setText(userConstantEntity.getSuppEmail());
    }

    @Override
    public void onClick(View view) {
        Constants.hideKeyBoard(view, this);

        mainScrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }, 600);
        int i = view.getId();
        if (i == R.id.ivMyProfile || i == R.id.rlMyProfile) {
            manageMainLayouts(llMyProfile, llAddress, llBankDetail, llDocumentUpload, llPosp, llAbout, llNotify);
            manageImages(llMyProfile, ivMyProfile, ivAddress, ivBankDetail, ivDocumentUpload, ivPOSP, ivAbout, ivNotify);

        } else if (i == R.id.ivAddress || i == R.id.rlAddress) {
            manageMainLayouts(llAddress, llMyProfile, llBankDetail, llDocumentUpload, llPosp, llAbout, llNotify);
            manageImages(llAddress, ivAddress, ivMyProfile, ivBankDetail, ivDocumentUpload, ivPOSP, ivAbout, ivNotify);
            saveProfile();

        } else if (i == R.id.ivBankDetail || i == R.id.rlBankDetail) {
            manageMainLayouts(llBankDetail, llMyProfile, llAddress, llDocumentUpload, llPosp, llAbout, llNotify);
            manageImages(llBankDetail, ivBankDetail, ivAddress, ivMyProfile, ivDocumentUpload, ivPOSP, ivAbout, ivNotify);
            saveAddress();

        } else if (i == R.id.ivDocumentUpload || i == R.id.rlDocumentUpload) {
            manageMainLayouts(llDocumentUpload, llBankDetail, llMyProfile, llAddress, llPosp, llAbout, llNotify);
            manageImages(llDocumentUpload, ivDocumentUpload, ivBankDetail, ivAddress, ivMyProfile, ivPOSP, ivAbout, ivNotify);
            saveBankDtl();

        } else if (i == R.id.ivPOSP || i == R.id.rlPOSP) {
            manageMainLayouts(llPosp, llDocumentUpload, llBankDetail, llMyProfile, llAddress, llAbout, llNotify);
            manageImages(llPosp, ivPOSP, ivDocumentUpload, ivBankDetail, ivAddress, ivMyProfile, ivAbout, ivNotify);
            savePOSP();

        } else if (i == R.id.ivAbout || i == R.id.rlAbout) {
            manageMainLayouts(llAbout, llPosp, llDocumentUpload, llBankDetail, llMyProfile, llAddress, llNotify);
            manageImages(llAbout, ivAbout, ivBankDetail, ivAddress, ivMyProfile, ivDocumentUpload, ivPOSP, ivNotify);

        } else if (i == R.id.ivNotify || i == R.id.rlNotify) {
            manageMainLayouts(llNotify, llAbout, llPosp, llDocumentUpload, llBankDetail, llMyProfile, llAddress);
            manageImages(llNotify, ivNotify, ivAbout, ivBankDetail, ivAddress, ivMyProfile, ivDocumentUpload, ivPOSP);


            setSavingAcc();

        } else if (i == R.id.txtSaving) {
            setSavingAcc();

        } else if (i == R.id.txtCurrent) {
            setCurrentAcc();

        } else if (i == R.id.ivProfile) {
            type = 1;
            galleryCamPopUp();

        } else if (i == R.id.ivPhotoCam) {
            type = 2;
            // launchCamera();
            galleryCamPopUp();

        } else if (i == R.id.ivPhotoGallery) {
            type = 2;
            openGallery();  // Not in Used


        } else if (i == R.id.ivPanCam) {
            type = 3;
            // launchCamera();
            galleryCamPopUp();

        } else if (i == R.id.ivPanGallery) {
            type = 3;
            openGallery();  // Not in Used

        } else if (i == R.id.ivCancelCam) {
            type = 4;
            //launchCamera();
            galleryCamPopUp();

        } else if (i == R.id.ivCancelGallery) {
            type = 4;
            openGallery();   // Not in Used

        } else if (i == R.id.ivAadharCam) {
            type = 5;
//                launchCamera();
            galleryCamPopUp();

        } else if (i == R.id.ivAadharGallery) {
            type = 5;
            openGallery();        // Not in Used

        } else if (i == R.id.ivManagerMobile) {
            if (dbPersistanceController.getUserConstantsData().getManagName() != null) {

                if (ActivityCompat.checkSelfPermission(MyAccountActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MyAccountActivity.this, permissionsRequired[0])) {
                        //Show Information about why you need the permission
                        ActivityCompat.requestPermissions(MyAccountActivity.this, permissionsRequired, Constants.PERMISSION_CALLBACK_CONSTANT);

                    } else {

                        openPopUp(ivManagerMobile, "Need  Permission", "This app needs all permissions.", "GRANT", true);


                    }
                } else {

                    ConfirmAlert("Calling", getResources().getString(R.string.RM_Calling) + " " + dbPersistanceController.getUserConstantsData().getManagName(), dbPersistanceController.getUserConstantsData().getMangMobile());
                }

            }


        } else if (i == R.id.ivManagerEmail) {
            if (dbPersistanceController.getUserConstantsData().getMangEmail() != null) {
                composeEmail(dbPersistanceController.getUserConstantsData().getMangEmail(), "");
            }

        } else if (i == R.id.ivSupportMobile) {
            if (dbPersistanceController.getUserConstantsData().getManagName() != null) {

                if (ActivityCompat.checkSelfPermission(MyAccountActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MyAccountActivity.this, permissionsRequired[0])) {
                        //Show Information about why you need the permission
                        ActivityCompat.requestPermissions(MyAccountActivity.this, permissionsRequired, Constants.PERMISSION_CALLBACK_SUPPORT);

                    } else {

                        openPopUp(ivManagerMobile, "Need  Permission", "This app needs all permissions.", "GRANT", true);

                    }
                } else {
                    ConfirmAlert("Calling", getResources().getString(R.string.Support_Calling), dbPersistanceController.getUserConstantsData().getSuppMobile());

                }

            }


        } else if (i == R.id.ivSupportEmail) {
            if (dbPersistanceController.getUserConstantsData().getSuppEmail() != null) {
                composeEmail(dbPersistanceController.getUserConstantsData().getSuppEmail(), "");
            }


        } else if (i == R.id.btnSave) {
            if (validateProfile() == false) {
                // llMyProfile.setVisibility(View.GONE);
                if (llMyProfile.getVisibility() == View.GONE) {
                    manageMainLayouts(llMyProfile, llAddress, llBankDetail, llDocumentUpload, llPosp, llAbout, llNotify);
                    manageImages(llMyProfile, ivMyProfile, ivAddress, ivBankDetail, ivDocumentUpload, ivPOSP, ivAbout, ivNotify);
                }

            } else {
                showDialog();
                saveMain();
            }


        }


    }

    View.OnFocusChangeListener acAdhaarFocusChange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (!b) {
                if (!isValidAadhar(etAadhaar)) {

                    etAadhaar.setError("Invalid Aadhaar Number");
                    etAadhaar.setFocusable(true);
                }
            }
        }
    };

    //region textwatcher
    TextWatcher pincodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (start == 5) {
                etCity.setText("");
                etState.setText("");
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if ((s.length() == 6) && (isDataUploaded)) {
                showDialog("Fetching City...");
                Toast.makeText(MyAccountActivity.this, "Fetching City...Data", Toast.LENGTH_LONG).show();
                new RegisterController(MyAccountActivity.this).getCityState(etPincode.getText().toString(), MyAccountActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    //endregion


    private void saveProfile() {

        if ((etSubHeading.getText().toString().trim().length() > 0) || (etMobileNo.getText().toString().trim().length() > 0) ||
                (etEmailId.getText().toString().trim().length() > 0)) {

            registerRequestEntity.setFBA_Designation("" + etSubHeading.getText().toString());
            registerRequestEntity.setMobile_1("" + etMobileNo.getText().toString());
            registerRequestEntity.setEmailId("" + etEmailId.getText().toString());
            registerRequestEntity.setType("1");

            new RegisterController(MyAccountActivity.this).saveAccDtl(registerRequestEntity, MyAccountActivity.this);
        }
    }


    private void savePOSP() {

        if ((etSubHeading_posp.getText().toString().trim().length() > 0) || (etMobileNo_posp.getText().toString().trim().length() > 0) ||
                (etEmailId_posp.getText().toString().trim().length() > 0)) {

            registerRequestEntity.setDisplayDesignation("" + etSubHeading_posp.getText().toString().toUpperCase());
            registerRequestEntity.setDisplayPhoneNo("" + etMobileNo_posp.getText().toString());
            registerRequestEntity.setDisplayEmail("" + etEmailId_posp.getText().toString());
            registerRequestEntity.setType("4");

            new RegisterController(MyAccountActivity.this).saveAccDtl(registerRequestEntity, MyAccountActivity.this);
        }
    }


    private void saveAddress() {
        if ((etAddress1.getText().toString().trim().length() > 0) || (etAddress2.getText().toString().trim().length() > 0) ||
                (etAddress3.getText().toString().trim().length() > 0) || (etPAN.getText().toString().trim().length() > 0)) {
            registerRequestEntity.setAddress_1("" + etAddress1.getText().toString());
            registerRequestEntity.setAddress_2("" + etAddress2.getText().toString());
            registerRequestEntity.setAddress_3("" + etAddress3.getText().toString());
            registerRequestEntity.setPinCode("" + etPincode.getText().toString());
            registerRequestEntity.setCity("" + etCity.getText().toString());
            registerRequestEntity.setState("" + etState.getText().toString());
            registerRequestEntity.setType("2");

            new RegisterController(MyAccountActivity.this).saveAccDtl(registerRequestEntity, MyAccountActivity.this);
        }
    }

    private void saveBankDtl() {
        if ((etAddress1.getText().toString().trim().length() > 0) || (etAddress2.getText().toString().trim().length() > 0) ||
                (etAddress3.getText().toString().trim().length() > 0) || (etPAN.getText().toString().trim().length() > 0)) {
            registerRequestEntity.setLoan_FirstName("" + etAccountHolderName.getText().toString());
            registerRequestEntity.setLoan_PAN("" + etPAN.getText().toString());
            registerRequestEntity.setLoan_Aadhaar("" + etAadhaar.getText().toString());
            registerRequestEntity.setLoan_BankAcNo("" + etBankAcNo.getText().toString());
            registerRequestEntity.setLoan_IFSC("" + etIfscCode.getText().toString());
            registerRequestEntity.setLoan_MICR("" + etMicrCode.getText().toString());
            registerRequestEntity.setLoan_BankName("" + etBankName.getText().toString());
            registerRequestEntity.setLoan_BankBranch("" + etBankBranch.getText().toString());
            registerRequestEntity.setLoan_BankCity("" + etBankCity.getText().toString());
            if (ACCOUNT_TYPE.equals("SAVING")) {
                registerRequestEntity.setLoan_Account_Type("SAVING");
            } else {
                registerRequestEntity.setLoan_Account_Type("CURRENT");
            }

            registerRequestEntity.setType("3");

            new RegisterController(MyAccountActivity.this).saveAccDtl(registerRequestEntity, MyAccountActivity.this);
        }
    }

    private void saveMain() {
        registerRequestEntity.setType("0");
        registerRequestEntity.setFBA_Designation("" + etSubHeading.getText().toString());
        registerRequestEntity.setMobile_1("" + etMobileNo.getText().toString());
        registerRequestEntity.setEmailId("" + etEmailId.getText().toString());

        registerRequestEntity.setDisplayDesignation("" + etSubHeading_posp.getText().toString().toUpperCase());
        registerRequestEntity.setDisplayPhoneNo("" + etMobileNo_posp.getText().toString());
        registerRequestEntity.setDisplayEmail("" + etEmailId_posp.getText().toString());

        registerRequestEntity.setAddress_1("" + etAddress1.getText().toString());
        registerRequestEntity.setAddress_2("" + etAddress2.getText().toString());
        registerRequestEntity.setAddress_3("" + etAddress3.getText().toString());
        registerRequestEntity.setPinCode("" + etPincode.getText().toString());
        registerRequestEntity.setCity("" + etCity.getText().toString());
        registerRequestEntity.setState("" + etState.getText().toString());


        registerRequestEntity.setLoan_FirstName("" + etAccountHolderName.getText().toString());
        registerRequestEntity.setLoan_PAN("" + etPAN.getText().toString());
        registerRequestEntity.setLoan_Aadhaar("" + etAadhaar.getText().toString());
        registerRequestEntity.setLoan_BankAcNo("" + etBankAcNo.getText().toString());
        registerRequestEntity.setLoan_IFSC("" + etIfscCode.getText().toString());
        registerRequestEntity.setLoan_MICR("" + etMicrCode.getText().toString());
        registerRequestEntity.setLoan_BankName("" + etBankName.getText().toString());
        registerRequestEntity.setLoan_BankBranch("" + etBankBranch.getText().toString());
        registerRequestEntity.setLoan_BankCity("" + etBankCity.getText().toString());
        if (ACCOUNT_TYPE.equals("SAVING")) {
            registerRequestEntity.setLoan_Account_Type("SAVING");
        } else {
            registerRequestEntity.setLoan_Account_Type("CURRENT");
        }
        new RegisterController(MyAccountActivity.this).saveAccDtl(registerRequestEntity, MyAccountActivity.this);

    }


    private void setSavingAcc() {
        ACCOUNT_TYPE = "SAVING";
        txtSaving.setBackgroundResource(R.drawable.customeborder_blue);
        txtSaving.setTextColor(ContextCompat.getColor(MyAccountActivity.this, Utility.getPrimaryColor(this)));

        txtCurrent.setBackgroundResource(R.drawable.customeborder);
        txtCurrent.setTextColor(ContextCompat.getColor(MyAccountActivity.this, R.color.description_text));


    }

    private void setCurrentAcc() {
        ACCOUNT_TYPE = "CURRENT";
        txtCurrent.setBackgroundResource(R.drawable.customeborder_blue);
        txtCurrent.setTextColor(ContextCompat.getColor(MyAccountActivity.this, Utility.getPrimaryColor(this)));

        txtSaving.setBackgroundResource(R.drawable.customeborder);
        txtSaving.setTextColor(ContextCompat.getColor(MyAccountActivity.this, R.color.description_text));


    }

    private void manageMainLayouts(LinearLayout visibleLayout, LinearLayout hideLayout1, LinearLayout hideLayout2, LinearLayout hideLayout3, LinearLayout hideLayout4, LinearLayout hideLayout5 ,LinearLayout hideLayout6) {

        if (visibleLayout.getVisibility() == View.GONE) {
            visibleLayout.setVisibility(View.VISIBLE);
            hideLayout1.setVisibility(View.GONE);
            hideLayout2.setVisibility(View.GONE);
            hideLayout3.setVisibility(View.GONE);
            hideLayout4.setVisibility(View.GONE);
            hideLayout5.setVisibility(View.GONE);
            hideLayout6.setVisibility(View.GONE);
        } else {
            visibleLayout.setVisibility(View.GONE);
        }
    }

    private void manageImages(LinearLayout clickedLayout, ImageView downImage, ImageView upImage1, ImageView upImage2, ImageView upImage3, ImageView upImage4, ImageView upImage5,ImageView upImage6) {

        if (clickedLayout.getVisibility() == View.GONE) {
            downImage.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage1.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage2.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage3.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage4.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage5.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage6.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        } else {
            downImage.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
            upImage1.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage2.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage3.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage4.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage5.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            upImage5.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        }

    }

    private void hideAllLayouts(LinearLayout linearLayout, ImageView imageView) {

        if (linearLayout.getVisibility() == View.GONE) {

            //region hideall layout
            ivMyProfile.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            llMyProfile.setVisibility(View.GONE);

            ivAddress.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            llAddress.setVisibility(View.GONE);

            ivPOSP.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            llPosp.setVisibility(View.GONE);

            ivAbout.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            llAbout.setVisibility(View.GONE);
            //endregion

            linearLayout.setVisibility(View.GONE);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));

        } else {
            linearLayout.setVisibility(View.GONE);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        }
    }

    private boolean validateProfile() {

        if (!isEmpty(etSubHeading)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etSubHeading.requestFocus();
                etSubHeading.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etSubHeading.setError("Enter Designation");
                return false;
            } else {
                etSubHeading.requestFocus();
                etSubHeading.setError("Enter Designation");
                return false;
            }
        } else if (!isEmpty(etMobileNo)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etMobileNo.requestFocus();
                etMobileNo.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMobileNo.setError("Enter Mobile Number");
                return false;

            } else {
                etMobileNo.requestFocus();
                etMobileNo.setError("Enter Mobile Number");
                return false;

            }
        } else if (etMobileNo.getText().length() < 9) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etMobileNo.requestFocus();
                etMobileNo.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMobileNo.setError("Enter Valid Mobile Number");
                return false;

            } else {
                etMobileNo.requestFocus();
                etMobileNo.setError("Enter Valid Mobile Number");
                return false;
            }
        } else if (!isEmpty(etEmailId)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etEmailId.requestFocus();
                etEmailId.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etEmailId.setError("Enter Email ID");
                return false;
            } else {
                etEmailId.requestFocus();
                etEmailId.setError("Enter Email ID");
                return false;
            }
        } else if (!isValideEmailID(etEmailId)) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etEmailId.requestFocus();
                etEmailId.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etEmailId.setError("Enter Valid Email ID");
                return false;

            } else {
                etEmailId.requestFocus();
                etEmailId.setError("Enter Valid Email ID");
                return false;

            }
        }
        return true;
    }

    private boolean validatePosp() {

        if (!isEmpty(etSubHeading_posp)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etSubHeading_posp.requestFocus();
                etSubHeading_posp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etSubHeading_posp.setError("Enter Designation");
                return false;
            } else {
                etSubHeading_posp.requestFocus();
                etSubHeading_posp.setError("Enter Designation");
                return false;
            }
        } else if (!isEmpty(etMobileNo_posp)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etMobileNo_posp.requestFocus();
                etMobileNo_posp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMobileNo_posp.setError("Enter Mobile Number");
                return false;

            } else {
                etMobileNo_posp.requestFocus();
                etMobileNo_posp.setError("Enter Mobile Number");
                return false;

            }
        } else if (etMobileNo_posp.getText().length() < 9) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etMobileNo_posp.requestFocus();
                etMobileNo_posp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etMobileNo_posp.setError("Enter Valid Mobile Number");
                return false;

            } else {
                etMobileNo_posp.requestFocus();
                etMobileNo_posp.setError("Enter Valid Mobile Number");
                return false;
            }
        } else if (!isEmpty(etEmailId)) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                etEmailId_posp.requestFocus();
                etEmailId_posp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etEmailId_posp.setError("Enter Email ID");
                return false;
            } else {
                etEmailId_posp.requestFocus();
                etEmailId_posp.setError("Enter Email ID");
                return false;
            }
        } else if (!isValideEmailID(etEmailId_posp)) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                etEmailId_posp.requestFocus();
                etEmailId_posp.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                etEmailId_posp.setError("Enter Email ID");
                return false;

            } else {
                etEmailId_posp.requestFocus();
                etEmailId_posp.setError("Enter Email ID");
                return false;

            }
        }
        return true;
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof PincodeResponse) {
            if (response.getStatusNo() == 0) {
                etState.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                etCity.setText("" + ((PincodeResponse) response).getMasterData().getCityname());

                registerRequestEntity.setCity("" + ((PincodeResponse) response).getMasterData().getCityname());
                registerRequestEntity.setState("" + ((PincodeResponse) response).getMasterData().getState_name());
                registerRequestEntity.setStateID("" + ((PincodeResponse) response).getMasterData().getStateid());

            } else {

                etState.setText("");
                etCity.setText("");

                registerRequestEntity.setCity("");
                registerRequestEntity.setState("");
                registerRequestEntity.setStateID("0");

            }
        } else if (response instanceof IfscCodeResponse) {
            if (response.getStatusNo() == 0) {
                Constants.hideKeyBoard(etPincode, this);

                IfscEntity ifscEntity = ((IfscCodeResponse) response).getMasterData().get(0);

                etIfscCode.setText("" + ifscEntity.getIFSCCode());
                etMicrCode.setText("" + ifscEntity.getMICRCode());
                etBankName.setText("" + ifscEntity.getBankName());
                etBankBranch.setText("" + ifscEntity.getBankName());
                etBankCity.setText("" + ifscEntity.getCityName());

                etMicrCode.setSelection(etMicrCode.getText().length());


                registerRequestEntity.setLoan_BankName("" + ifscEntity.getBankName());
                registerRequestEntity.setLoan_BankBranch("" + ifscEntity.getBankBran());
                registerRequestEntity.setLoan_IFSC("" + ifscEntity.getIFSCCode());

            }
        } else if (response instanceof MyAccountResponse) {

            if (registerRequestEntity.getType().equals("0")) {
                Snackbar.make(ivMyProfile, response.getMessage(), Snackbar.LENGTH_SHORT).show();
                saveAcctDtlToDB(0);
            } else if ((registerRequestEntity.getType().equals("1") || registerRequestEntity.getType().equals("4"))) {
                saveAcctDtlToDB(Integer.valueOf(registerRequestEntity.getType()));
            }
        }

        //DocumentResponse
        else if (response instanceof DocumentResponse) {
            if (response.getStatusNo() == 0) {

                // Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
                setDocumentUpload(((DocumentResponse) response).getMasterData().get(0).getPrv_file());
                if (type == 1 || type == 2) {
                    try {
                        updateLoginResponse(((DocumentResponse) response).getMasterData().get(0).getPrv_file());

                        dbPersistanceController.updateUserConstatntProfile(((DocumentResponse) response).getMasterData().get(0).getPrv_file());

                        // Todo : Firing Local BroadCase
                        Intent profileIntent = new Intent(Utility.USER_PROFILE_ACTION);
                        profileIntent.putExtra("PROFILE_PATH", ((DocumentResponse) response).getMasterData().get(0).getPrv_file());

                        LocalBroadcastManager.getInstance(MyAccountActivity.this).sendBroadcast(profileIntent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        } else if (response instanceof MyAcctDtlResponse) {
            if (response.getStatusNo() == 0) {


                accountDtlEntity = ((MyAcctDtlResponse) response).getMasterData().get(0);

                if (accountDtlEntity != null) {
                    isDataUploaded = false;
                    setAcctDtlInfo(accountDtlEntity);
                    dbPersistanceController.updateMyAccountData(accountDtlEntity);
                    isDataUploaded = true;
                }


            }
        } else if (response instanceof UserConstatntResponse) {
            if (response.getStatusNo() == 0) {
                if (((UserConstatntResponse) response).getMasterData() != null) {
                    dbPersistanceController.updateUserConstatntData(((UserConstatntResponse) response).getMasterData());
                    bindAboutMe();
                }
            }
        }

    }

    public void updateLoginResponse(final String fbaProfileUrl) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //loginResponseEntity.setFBAProfileUrl("http://qa.mgfm.in/" + fbaProfileUrl);
                loginResponseEntity.setFBAProfileUrl(fbaProfileUrl);
            }
        });
    }

    private void setAcctDtlInfo(AccountDtlEntity accountDtlEntity) {

        etSubHeading.setText("" + accountDtlEntity.getDesignation());
        etMobileNo.setText("" + accountDtlEntity.getEditMobiNumb());
        etEmailId.setText("" + accountDtlEntity.getEditEmailId());


        etAddress1.setText("" + accountDtlEntity.getAddress_1());
        etAddress2.setText("" + accountDtlEntity.getAddress_2());
        etAddress3.setText("" + accountDtlEntity.getAddress_3());
        etPincode.setText("" + accountDtlEntity.getPinCode());
        etCity.setText("" + accountDtlEntity.getCity());
        etState.setText("" + accountDtlEntity.getStateName());

        etAccountHolderName.setText("" + accountDtlEntity.getLoanName());
        etPAN.setText("" + accountDtlEntity.getLoan_PAN());
        etAadhaar.setText("" + accountDtlEntity.getLoan_Aadhaar());
        etBankAcNo.setText("" + accountDtlEntity.getLoan_BankAcNo());
        etIfscCode.setText("" + accountDtlEntity.getLoan_IFSC());
        etMicrCode.setText("" + accountDtlEntity.getLoan_MICR());
        etBankName.setText("" + accountDtlEntity.getLoan_BankName());
        etBankBranch.setText("" + accountDtlEntity.getLoan_BankBranch());
        etBankCity.setText("" + accountDtlEntity.getLoan_BankCity());
        if (accountDtlEntity.getLoan_Account_Type().equals("SAVING")) {
            setSavingAcc();
        } else if (accountDtlEntity.getLoan_Account_Type().equals("CURRENT")) {
            setCurrentAcc();
        }

        etSubHeading_posp.setText("" + accountDtlEntity.getDisplayDesignation().toString().toUpperCase());
        etMobileNo_posp.setText("" + accountDtlEntity.getDisplayPhoneNo());
        etEmailId_posp.setText("" + accountDtlEntity.getDisplayEmail());

        if (accountDtlEntity.getDoc_available().size() > 0) {
            List<DocAvailableEntity> docList = accountDtlEntity.getDoc_available();


            for (DocAvailableEntity docEntity : docList) {
                if (!docEntity.getFileName().isEmpty()) {

                    setDocumentUpload(docEntity.getDocType(), docEntity.getFileName());
                }
            }

        }


    }

    private void saveAcctDtlToDB(int type) {
        if (accountDtlEntity != null) {
            if (type == 1) {
                accountDtlEntity.setDesignation("" + etSubHeading.getText().toString().toString());
                accountDtlEntity.setEditMobiNumb("" + etMobileNo.getText().toString());
                accountDtlEntity.setEditEmailId("" + etEmailId.getText().toString());
            } else if (type == 4) {
                accountDtlEntity.setDisplayDesignation("" + etSubHeading_posp.getText().toString().toUpperCase());
                accountDtlEntity.setDisplayPhoneNo("" + etMobileNo_posp.getText().toString());
                accountDtlEntity.setDisplayEmail("" + etEmailId_posp.getText().toString());
            } else if (type == 0) {
                accountDtlEntity.setDesignation("" + etSubHeading.getText().toString());
                accountDtlEntity.setEditMobiNumb("" + etMobileNo.getText().toString());
                accountDtlEntity.setEditEmailId("" + etEmailId.getText().toString());

                accountDtlEntity.setDisplayDesignation("" + etSubHeading_posp.getText().toString().toString());
                accountDtlEntity.setDisplayPhoneNo("" + etMobileNo_posp.getText().toString());
                accountDtlEntity.setDisplayEmail("" + etEmailId_posp.getText().toString());
            }
        }

        dbPersistanceController.updateMyAccountData(accountDtlEntity);
    }

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            // do something with the bitmap
            // for demonstration purposes, let's just set it to an ImageView
            ivUser.setImageBitmap(bitmap);
        }
    };


    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void setDocumentUpload(int fileType, String FileName) {
        if (fileType == 1 || fileType == 2) {
            //ProfiePics
            /*Glide.with(MyAccountActivity.this)
                    .load(FileNmae)
                    .asBitmap()
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                    .into(target);*/

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                final Animation zoomAnim = AnimationUtils.loadAnimation(MyAccountActivity.this, R.anim.zoom_out);
//                ivUser.startAnimation(zoomAnim);
//            }


            if (FileName != null && !FileName.equals("")) {
                Glide.with(MyAccountActivity.this)
                        .load(FileName)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.circle_placeholder)
                        .skipMemoryCache(true)
                        .override(120, 120)
                        .transform(new CircleTransform(MyAccountActivity.this)) // applying the image transformer
                        .into(ivUser);


            } else {
                Glide.with(MyAccountActivity.this)
                        .load(R.drawable.finmart_user_icon)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.finmart_user_icon)
                        .skipMemoryCache(true)
                        .override(120, 120)
                        .transform(new CircleTransform(MyAccountActivity.this)) // applying the image transformer
                        .into(ivUser);
            }
        }

        if (fileType == 2) {
            ivPhoto.setImageResource(R.drawable.doc_uploaded);
        } else if (fileType == 3) {
            ivPan.setImageResource(R.drawable.doc_uploaded);
        } else if (fileType == 4) {
            ivCancel.setImageResource(R.drawable.doc_uploaded);
        } else if (fileType == 5) {
            ivAadhar.setImageResource(R.drawable.doc_uploaded);
        }
    }

    private void setDocumentUpload(String URL) {
        if (type == 1) {
            Glide.with(MyAccountActivity.this)
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.circle_placeholder)
                    .skipMemoryCache(true)
                    .override(120, 120)
                    .transform(new CircleTransform(MyAccountActivity.this)) // applying the image transformer
                    .into(ivUser);
        } else if (type == 2) {
            ivPhoto.setImageResource(R.drawable.doc_uploaded);
            Glide.with(MyAccountActivity.this)
                    .load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.circle_placeholder)
                    .skipMemoryCache(true)
                    .override(120, 120)
                    .transform(new CircleTransform(MyAccountActivity.this)) // applying the image transformer
                    .into(ivUser);

        } else if (type == 3) {
            ivPan.setImageResource(R.drawable.doc_uploaded);
        } else if (type == 4) {
            ivCancel.setImageResource(R.drawable.doc_uploaded);
        } else if (type == 5) {
            ivAadhar.setImageResource(R.drawable.doc_uploaded);
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (!hasFocus) {

            if ((etIfscCode.getText().length() > 3) && (isDataUploaded)) {

                Constants.hideKeyBoard(v, MyAccountActivity.this);
                showDialog("Fetching Bank Details...");
                new RegisterController(MyAccountActivity.this).getIFSC(etIfscCode.getText().toString(), MyAccountActivity.this);
            }
        }
    }

    private void galleryCamPopUp() {

        if (!checkPermission()) {

            if (checkRationalePermission()) {
                //Show Information about why you need the permission
                requestPermission();

            } else {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission

                //  permissionAlert(navigationView,"Need Call Permission","This app needs Call permission.");
                openPopUp(btnSave, "Need  Permission", "This app needs all permissions.", "GRANT", true);


            }
        } else {

            showCamerGalleryPopUp();
        }
    }


    private void showCamerGalleryPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog);

        LinearLayout lyCamera, lyGallery;
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_cam_gallery, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        lyCamera = (LinearLayout) dialogView.findViewById(R.id.lyCamera);
        lyGallery = (LinearLayout) dialogView.findViewById(R.id.lyGallery);

        lyCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
                alertDialog.dismiss();

            }
        });

        lyGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                alertDialog.dismiss();

            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();
        //  alertDialog.getWindow().setLayout(900, 600);

        // for user define height and width..
    }

    private void launchCamera() {


        String FileName = "";

        switch (type) {
            case 1:
                FileName = PHOTO_File;
                break;
            case 2:
                FileName = PHOTO_File;
                break;
            case 3:
                FileName = PAN_File;
                break;
            case 4:
                FileName = CANCEL_CHQ_File;
                break;
            case 5:
                FileName = AADHAR_File;
                break;

        }
        Docfile = createFile(FileName);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imageUri = Uri.fromFile(Docfile);
        } else {
            imageUri = FileProvider.getUriForFile(MyAccountActivity.this,
                    getString(R.string.file_provider_authority), Docfile);
        }


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    private void openGallery() {

        String FileName = "";

        switch (type) {
            case 1:
                FileName = PHOTO_File;
                break;
            case 2:
                FileName = PHOTO_File;
                break;
            case 3:
                FileName = PAN_File;
                break;
            case 4:
                FileName = CANCEL_CHQ_File;
                break;
            case 5:
                FileName = AADHAR_File;
                break;

        }
        Docfile = createFile(FileName);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    private void setProfilePhoto(Bitmap mphoto) {

        bitmapPhoto = mphoto;        //original
    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

    }

    private Bitmap rotateImageIfRequired(Context context, Bitmap bitmap, File file) {
        Bitmap rotatedBitmap = null;
        try {

            // region Handling Default Rotation Of Image
            Uri uri = Uri.fromFile(file);
            inputStream = context.getContentResolver().openInputStream(uri);

            if (Build.VERSION.SDK_INT > 23) {
                ei = new ExifInterface(inputStream);
            } else {

                // ei = new ExifInterface("/storage/emulated/0/FINMART/FBAPhotograph.jpg");
                ei = new ExifInterface(file.getAbsolutePath());
            }


            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);


            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;

            }
            //endregion

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rotatedBitmap;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = null;
            try {
                mphoto = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                mphoto = getResizedBitmap(mphoto, 800);
                mphoto = rotateImageIfRequired(this, mphoto, Docfile);

            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (type) {
                case 1:
                    showDialog();
                    file = saveImageToStorage(mphoto, PHOTO_File);
                    setProfilePhoto(mphoto);
                    part = Utility.getMultipartImage(file);
                    body = Utility.getBody(this, loginEntity.getFBAId(), PROFILE, PHOTO_File);

                    new RegisterController(this).uploadDocuments(part, body, this);
                    break;
                case 2:
                    showDialog();
                    file = saveImageToStorage(mphoto, PHOTO_File);
                    setProfilePhoto(mphoto);
                    part = Utility.getMultipartImage(file);
                    body = Utility.getBody(this, loginEntity.getFBAId(), PHOTO, PHOTO_File);
                    new RegisterController(this).uploadDocuments(part, body, this);
                    break;
                case 3:

                    showDialog();
                    file = saveImageToStorage(mphoto, PAN_File);
                    part = Utility.getMultipartImage(file);
                    body = Utility.getBody(this, loginEntity.getFBAId(), PAN, PAN_File);
                    new RegisterController(this).uploadDocuments(part, body, this);
                    break;

                case 4:
                    showDialog();
                    file = saveImageToStorage(mphoto, CANCEL_CHQ_File);
                    part = Utility.getMultipartImage(file);
                    body = Utility.getBody(this, loginEntity.getFBAId(), CANCEL_CHQ, CANCEL_CHQ_File);
                    new RegisterController(this).uploadDocuments(part, body, this);
                    break;
                case 5:
                    showDialog();
                    file = saveImageToStorage(mphoto, AADHAR_File);
                    part = Utility.getMultipartImage(file);
                    body = Utility.getBody(this, loginEntity.getFBAId(), AADHAR, AADHAR_File);
                    new RegisterController(this).uploadDocuments(part, body, this);
                    break;
            }


        } else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            Bitmap mphoto = null;
            try {
                mphoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                mphoto = getResizedBitmap(mphoto, 800);
                mphoto = rotateImageIfRequired(this, mphoto, Docfile);

                switch (type) {
                    case 1:
                        showDialog();
                        setProfilePhoto(mphoto);
                        file = saveImageToStorage(mphoto, "PROFILE");
                        part = Utility.getMultipartImage(file);
                        body = Utility.getBody(this, loginEntity.getFBAId(), PROFILE, PHOTO_File);
                        new RegisterController(this).uploadDocuments(part, body, this);

                        break;
                    case 2:
                        showDialog();
                        file = saveImageToStorage(mphoto, PHOTO_File);
                        part = Utility.getMultipartImage(file);
                        body = Utility.getBody(this, loginEntity.getFBAId(), PHOTO, PHOTO_File);
                        new RegisterController(this).uploadDocuments(part, body, this);
                        break;
                    case 3:

                        showDialog();
                        file = saveImageToStorage(mphoto, PAN_File);
                        part = Utility.getMultipartImage(file);
                        body = Utility.getBody(this, loginEntity.getFBAId(), PAN, PAN_File);
                        new RegisterController(this).uploadDocuments(part, body, this);
                        break;

                    case 4:
                        showDialog();
                        file = saveImageToStorage(mphoto, CANCEL_CHQ_File);
                        part = Utility.getMultipartImage(file);
                        body = Utility.getBody(this, loginEntity.getFBAId(), CANCEL_CHQ, CANCEL_CHQ_File);
                        new RegisterController(this).uploadDocuments(part, body, this);
                        break;
                    case 5:
                        showDialog();
                        file = saveImageToStorage(mphoto, AADHAR_File);
                        part = Utility.getMultipartImage(file);
                        body = Utility.getBody(this, loginEntity.getFBAId(), AADHAR, AADHAR_File);
                        new RegisterController(this).uploadDocuments(part, body, this);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    // region permission
    private boolean checkPermission() {

        int camera = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[0]);

        int WRITE_EXTERNAL = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int READ_EXTERNAL = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[2]);

        return camera == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL == PackageManager.PERMISSION_GRANTED
                && READ_EXTERNAL == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkRationalePermission() {

        boolean camera = ActivityCompat.shouldShowRequestPermissionRationale(MyAccountActivity.this, perms[0]);

        boolean write_external = ActivityCompat.shouldShowRequestPermissionRationale(MyAccountActivity.this, perms[1]);
        boolean read_external = ActivityCompat.shouldShowRequestPermissionRationale(MyAccountActivity.this, perms[2]);

        return camera || write_external || read_external;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, Constants.PERMISSION_CAMERA_STORACGE_CONSTANT);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case Constants.PERMISSION_CAMERA_STORACGE_CONSTANT:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternal = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternal = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (camera && writeExternal && readExternal) {

                        showCamerGalleryPopUp();

                    }

                }
                break;

            case Constants.PERMISSION_CALLBACK_CONSTANT:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean call_phone = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (call_phone) {

                        ConfirmAlert("Calling", getResources().getString(R.string.RM_Calling) + " " + dbPersistanceController.getUserConstantsData().getManagName(), dbPersistanceController.getConstantsData().getHelpNumber());


                    }

                }

                break;


            case Constants.PERMISSION_CALLBACK_SUPPORT:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean call_phone = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (call_phone) {

                        ConfirmAlert("Calling", getResources().getString(R.string.Support_Calling), dbPersistanceController.getUserConstantsData().getSuppMobile());

                    }

                }

                break;
        }
    }

    //endregion
    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {

        dialog.cancel();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, Constants.REQUEST_PERMISSION_SETTING);

    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {

        dialog.cancel();
    }


    public void ConfirmAlert(String Title, String strBody, final String strMobile) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyAccountActivity.this);
            builder.setTitle(Title);

            builder.setMessage(strBody);
            String positiveText = "Call";
            String NegativeText = "Cancel";
            builder.setPositiveButton(positiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            if (ActivityCompat.checkSelfPermission(MyAccountActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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
                            intentCalling.setData(Uri.parse("tel:" + strMobile));
                            startActivity(intentCalling);
                        }
                    });

            builder.setNegativeButton(NegativeText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            final android.support.v7.app.AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (Exception ex) {
            Toast.makeText(this, "Please try again..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        supportFinishAfterTransition();
        super.onBackPressed();
    }


}
