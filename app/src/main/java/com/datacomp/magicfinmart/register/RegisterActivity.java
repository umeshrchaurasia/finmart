package com.datacomp.magicfinmart.register;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.register.adapters.MultiSelectionSpinner;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.login.LoginController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters.MasterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.register.RegisterController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.RegisterRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.GenerateOtpResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.InsuranceMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PincodeResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ReferFriendResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RegisterFbaResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RegisterSourceResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.SourceEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.VerifyOtpResponse;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber, MultiSelectionSpinner.OnMultipleItemsSelectedListener, CompoundButton.OnCheckedChangeListener {

    CardView llPersonalInfo, llProfessionalInfo;
    ImageView ivProfessionalInfo, ivPersonalInfo;
    LinearLayout rlPersonalInfo, rlProfessionalInfo;
    EditText etFirstName, etLastName, etDob, etMobile1, etMobile2, etEmail, etConfirmEmail,
            etPincode, etCity, etState, etOtp;
    // ImageView ivMale, ivFemale;
    Dialog dialog;
    ArrayList<String> healthList, generalList, lifeList;
    DBPersistanceController dbPersistanceController;
    MultiSelectionSpinner spLifeIns, spGenIns, spHealthIns;
    CheckBox chbxLife, chbxGen, chbxHealth, chbxMutual, chbxStocks, chbxPostal, chbxBonds;
    Button btnSubmit;
    RegisterRequestEntity registerRequestEntity;
    Boolean isValidPersonalInfo = false, isMobileValid = false;
    TextView tvOk, txtMale, txtFemale;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    SimpleDateFormat passdateFormat = new SimpleDateFormat("ddMMyyyy");
    boolean isMale = false, isFemale = false;
    String pass = "";
    PrefManager prefManager;
    TrackingRequestEntity trackingRequestEntity;
    Spinner spReferal, spSource;
    EditText etRefererCode;
    TextInputLayout tilReferer;
    boolean isVAlidPromo = false;

    List<SourceEntity> sourceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        trackingRequestEntity = new TrackingRequestEntity();
        dbPersistanceController = new DBPersistanceController(this);
        registerRequestEntity = new RegisterRequestEntity();
        sourceList = new ArrayList<>();


        initWidgets();
        bindSource();
        setListener();
        initLayouts();
        setSpinnerListener();

        prefManager = new PrefManager(this);

        new RegisterController(this).getRegSource(this);

        if (prefManager.IsInsuranceMasterUpdate()) {
            new MasterController(this).getInsuranceMaster(this);
        } else {
            healthList = dbPersistanceController.getHealthListNames();
            generalList = dbPersistanceController.getGeneralListNames();
            lifeList = dbPersistanceController.getLifeListNames();


            if (healthList.size() == 0 || generalList.size() == 0 || lifeList.size() == 0) {
                new MasterController(this).getInsuranceMaster(this);
            } else {
                initMultiSelect();
            }
        }
    }

    private void setSpinnerListener() {
        spReferal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    isVAlidPromo = true;
                    tilReferer.setVisibility(View.GONE);
                } else if (position == 1) {
                    isVAlidPromo = false;
                    tilReferer.setVisibility(View.VISIBLE);
                    tilReferer.setHint("Referrer Code");
                } else {
                    isVAlidPromo = false;
                    tilReferer.setVisibility(View.VISIBLE);
                    tilReferer.setHint("Referrer Code");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initMultiSelect() {

        try {
            if (lifeList != null && lifeList.size() > 0) {
                spLifeIns.setItems(lifeList);
                //spLifeIns.setSelection(new int[]{2, 6});
                spLifeIns.setListener(this);
            }

            if (generalList != null && generalList.size() > 0) {
                spGenIns.setItems(generalList);
                //spLifeIns.setSelection(new int[]{2, 6});
                spGenIns.setListener(this);
            }

            if (healthList != null && healthList.size() > 0) {

                spHealthIns.setItems(healthList);
                //spLifeIns.setSelection(new int[]{2, 6});
                spHealthIns.setListener(this);
            }
        } catch (Exception e) {
            new TrackingController(this)
                    .sendData(new TrackingRequestEntity(new TrackingData(" Multi-select spinner" + e.getMessage()), Constants.REGISTER),
                            null);
        }
    }


    @Override
    protected void onResume() {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    //region Broadcast Receiver
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                String message = intent.getStringExtra("message");
                String otp = extractDigitFromMessage(message);
                if (!otp.equals("")) {
                    etOtp.setText(otp);
                    // tvOk.performClick();
                }
            }
        }
    };
    //endregion

    private void initLayouts() {
        hideAllLayouts(llPersonalInfo, ivPersonalInfo);
        llPersonalInfo.setVisibility(View.VISIBLE);
        llProfessionalInfo.setVisibility(View.GONE);
        spLifeIns.setEnabled(false);
        spGenIns.setEnabled(false);
        spHealthIns.setEnabled(false);
        btnSubmit.setVisibility(View.VISIBLE);

    }

    private void setListener() {
        ivProfessionalInfo.setOnClickListener(this);
        ivPersonalInfo.setOnClickListener(this);
        rlPersonalInfo.setOnClickListener(this);
        rlProfessionalInfo.setOnClickListener(this);
        etMobile1.addTextChangedListener(mobileTextWatcher);
        etPincode.addTextChangedListener(pincodeTextWatcher);
        etRefererCode.addTextChangedListener(refererTextWatcher);
        chbxGen.setOnCheckedChangeListener(this);
        chbxHealth.setOnCheckedChangeListener(this);
        chbxLife.setOnCheckedChangeListener(this);
        btnSubmit.setOnClickListener(this);
        etDob.setOnClickListener(datePickerDialog);
        txtMale.setOnClickListener(this);
        txtFemale.setOnClickListener(this);
        etConfirmEmail.setOnFocusChangeListener(confirmEmailFocus);
    }

    View.OnFocusChangeListener confirmEmailFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                if (!etEmail.getText().toString().equals(etConfirmEmail.getText().toString())) {
                    //etConfirmEmail.requestFocus();
                    etConfirmEmail.setError("Email Mismatch");
                }
            }
        }
    };

    private void initWidgets() {
        spLifeIns = (MultiSelectionSpinner) findViewById(R.id.spLifeIns);
        spGenIns = (MultiSelectionSpinner) findViewById(R.id.spGenIns);
        spHealthIns = (MultiSelectionSpinner) findViewById(R.id.spHealthIns);

        ivProfessionalInfo = (ImageView) findViewById(R.id.ivProfessionalInfo);
        ivPersonalInfo = (ImageView) findViewById(R.id.ivPersonalInfo);
        llPersonalInfo = (CardView) findViewById(R.id.llPersonalInfo);
        llProfessionalInfo = (CardView) findViewById(R.id.llProfessionalInfo);
        rlPersonalInfo = (LinearLayout) findViewById(R.id.rlPersonalInfo);
        rlProfessionalInfo = (LinearLayout) findViewById(R.id.rlProfessionalInfo);


        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etDob = (EditText) findViewById(R.id.etDob);
        etMobile1 = (EditText) findViewById(R.id.etMobile1);
        etMobile2 = (EditText) findViewById(R.id.etMobile2);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etConfirmEmail = (EditText) findViewById(R.id.etConfirmEmail);
        etPincode = (EditText) findViewById(R.id.etPincode);
        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);
        txtMale = (TextView) findViewById(R.id.txtMale);
        txtFemale = (TextView) findViewById(R.id.txtFemale);


        chbxLife = (CheckBox) findViewById(R.id.chbxLife);
        chbxGen = (CheckBox) findViewById(R.id.chbxGen);
        chbxHealth = (CheckBox) findViewById(R.id.chbxHealth);
        chbxMutual = (CheckBox) findViewById(R.id.chbxMutual);
        chbxStocks = (CheckBox) findViewById(R.id.chbxStocks);
        chbxPostal = (CheckBox) findViewById(R.id.chbxPostal);
        chbxBonds = (CheckBox) findViewById(R.id.chbxBonds);

        etRefererCode = (EditText) findViewById(R.id.etRefererCode);
        spReferal = (Spinner) findViewById(R.id.spReferal);
        spSource = (Spinner) findViewById(R.id.spSource);
        tilReferer = (TextInputLayout) findViewById(R.id.tilReferer);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.txtMale) {
            isFemale = false;
            isMale = true;
            setGender(txtMale, txtFemale);

        } else if (i == R.id.txtFemale) {
            isFemale = true;
            isMale = false;
            setGender(txtFemale, txtMale);

        } else if (i == R.id.ivPersonalInfo || i == R.id.rlPersonalInfo) {
            hideAllLayouts(llPersonalInfo, ivPersonalInfo);

        } else if (i == R.id.ivProfessionalInfo || i == R.id.rlProfessionalInfo) {
            isValidPersonalInfo = validateRegister();
            manageTaskBar();
            if (isValidPersonalInfo) {
                setRegisterPersonalRequest();

                if (!isMobileValid) {
                    showDialog("Sending otp...");
                    new RegisterController(this).generateOtp(etMobile1.getText().toString(), this);
                    showOtpAlert();
                } else {
                    hideAllLayouts(llProfessionalInfo, ivProfessionalInfo);
                    btnSubmit.setVisibility(View.VISIBLE);
                }
            } else {

            }


        } else if (i == R.id.btnSubmit) {
            isValidPersonalInfo = validateRegister();
            manageTaskBar();
            if (isValidPersonalInfo) {
                setRegisterPersonalRequest();
                if (!isMobileValid) {
                    showDialog("Sending otp...");
                    new RegisterController(this).generateOtp(etMobile1.getText().toString(), this);
                    showOtpAlert();
                } else {

                    setProfessionInfo();
                    showDialog();
                    new RegisterController(this).registerFba(registerRequestEntity, this);

                }
            }

        }
    }

    private Boolean validateRegister() {
        if (!isEmpty(etFirstName)) {
            etFirstName.requestFocus();
            etFirstName.setError("Enter First Name");
            return false;
        }

        if (!isEmpty(etLastName)) {
            etLastName.requestFocus();
            etLastName.setError("Enter Last Name");
            return false;
        }
        if (!isEmpty(etDob)) {
            etDob.requestFocus();
            etDob.setError("Enter Dob");
            return false;
        }
        if (!isValidePhoneNumber(etMobile1)) {
            etMobile1.requestFocus();
            etMobile1.setError("Enter Mobile ");
            return false;
        }
        if (!isValideEmailID(etEmail)) {
            etEmail.requestFocus();
            etEmail.setError("Enter Email");
            return false;
        }
        if (!isValideEmailID(etConfirmEmail)) {
            etConfirmEmail.requestFocus();
            etConfirmEmail.setError("Confirm Email");
            return false;
        }
        if (!etEmail.getText().toString().equals(etConfirmEmail.getText().toString())) {
            etConfirmEmail.requestFocus();
            etConfirmEmail.setError("Email Mismatch");
            return false;
        }
        if (!isEmpty(etPincode)) {
            etPincode.requestFocus();
            etPincode.setError("Enter Pincode");
            return false;
        }
        if (!etPincode.getText().toString().equals("")) {
            if (etPincode.getText().toString().length() != 6) {
                etPincode.requestFocus();
                etPincode.setError("Enter Valid Pincode");
                return false;
            }
        }
        if (!isEmpty(etCity)) {
            etCity.requestFocus();
            etCity.setError("Enter City");
            return false;
        }
        if (!isEmpty(etState)) {
            etState.requestFocus();
            etState.setError("Enter State");
            return false;
        }
        if (!isEmpty(etFirstName)) {
            etFirstName.requestFocus();
            etFirstName.setError("Enter First Name");
            return false;
        }
      /*  if (!isVAlidPromo) {
            etRefererCode.requestFocus();
            etRefererCode.setError("Enter Valid Promo Code");
            return false;
        }*/
        return true;
    }

    private void setProfessionInfo() {

        if (chbxLife.isChecked()) {
            registerRequestEntity.setIsLic("1");
            if (spLifeIns.getSelectedStrings().size() > 0) {
                registerRequestEntity.setLIC_Comp(dbPersistanceController.getlifeListId(spLifeIns.getSelectedStrings()));
            }

        } else {
            registerRequestEntity.setIsLic("0");
        }


        if (chbxGen.isChecked()) {
            registerRequestEntity.setIsGic("1");
            if (spGenIns.getSelectedStrings().size() > 0) {
                registerRequestEntity.setGIC_Comp(dbPersistanceController.getGeneralListId(spGenIns.getSelectedStrings()));
            }

        } else {
            registerRequestEntity.setIsGic("0");
        }

        if (chbxHealth.isChecked()) {
            registerRequestEntity.setIsHealth("1");
            if (spHealthIns.getSelectedStrings().size() > 0) {
                registerRequestEntity.setHealth_Comp(dbPersistanceController.getHealthListId(spHealthIns.getSelectedStrings()));
            }

        } else {
            registerRequestEntity.setIsHealth("0");
        }

        if (chbxBonds.isChecked()) {
            registerRequestEntity.setBonds("1");
        } else {
            registerRequestEntity.setBonds("0");
        }
        if (chbxMutual.isChecked()) {
            registerRequestEntity.setMF("1");
        } else {
            registerRequestEntity.setMF("0");
        }
        if (chbxPostal.isChecked()) {
            registerRequestEntity.setPostal("1");
        } else {
            registerRequestEntity.setPostal("0");
        }
        if (chbxStocks.isChecked()) {
            registerRequestEntity.setStock("1");
        } else {
            registerRequestEntity.setStock("0");
        }
        registerRequestEntity.setReferedby_code(etRefererCode.getText().toString().trim());
        registerRequestEntity.setVersionCode(Utility.getVersionName(this));
    }

    private void setRegisterPersonalRequest() {
        registerRequestEntity.setFirstName("" + etFirstName.getText().toString());
        registerRequestEntity.setLastName("" + etLastName.getText().toString());
        registerRequestEntity.setDOB("" + etDob.getText().toString());
        registerRequestEntity.setMobile_1("" + etMobile1.getText().toString());
        registerRequestEntity.setMobile_2("" + etMobile2.getText().toString());
        registerRequestEntity.setEmailId("" + etEmail.getText().toString());
        registerRequestEntity.setPinCode("" + etPincode.getText().toString());
        if (isMale) {
            registerRequestEntity.setGender("M");
        } else {
            registerRequestEntity.setGender("F");
        }

        //password setting null 
        if (!pass.equalsIgnoreCase("")) {
            registerRequestEntity.setPassword(pass);
        } else {
            Date date = (Date) etDob.getTag(R.id.etDob);
            pass = passdateFormat.format(date.getTime());
            registerRequestEntity.setPassword(pass);
        }

        SourceEntity sourceEntity = (SourceEntity) spSource.getSelectedItem();
        registerRequestEntity.setAppSource("" + sourceEntity.getId());
    }

    private void hideAllLayouts(CardView linearLayout, ImageView imageView) {

        if (linearLayout.getVisibility() == View.GONE) {

            //region hideall layout
            ivPersonalInfo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            llPersonalInfo.setVisibility(View.GONE);

            ivProfessionalInfo.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
            llProfessionalInfo.setVisibility(View.GONE);
            //endregion

            linearLayout.setVisibility(View.VISIBLE);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));

        } else {
            linearLayout.setVisibility(View.GONE);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
        }
    }


    @Override
    public void OnSuccess(APIResponse response, String message) {

        if (response instanceof RegisterSourceResponse) {

            if (response != null) {
                sourceList = ((RegisterSourceResponse) response).getMasterData();
                bindSource();
            }

        } else if (response instanceof GenerateOtpResponse) {
            cancelDialog();
            Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (response instanceof PincodeResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                Constants.hideKeyBoard(etPincode, this);
                etState.setText("" + ((PincodeResponse) response).getMasterData().getState_name());
                etCity.setText("" + ((PincodeResponse) response).getMasterData().getCityname());

                registerRequestEntity.setCity("" + ((PincodeResponse) response).getMasterData().getCityname());
                registerRequestEntity.setState("" + ((PincodeResponse) response).getMasterData().getState_name());
                registerRequestEntity.setStateID("" + ((PincodeResponse) response).getMasterData().getStateid());

            }
        } else if (response instanceof VerifyOtpResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                if (dialog != null)
                    dialog.dismiss();
                hideAllLayouts(llProfessionalInfo, ivProfessionalInfo);
                btnSubmit.setVisibility(View.VISIBLE);
                isMobileValid = true;
            }
            Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (response instanceof RegisterFbaResponse) {
            cancelDialog();
            trackingRequestEntity.setType("Register");
            trackingRequestEntity.setData(new TrackingData("Submit button for registration Success"));
            new TrackingController(this).sendData(trackingRequestEntity, null);

            if (response.getStatusNo() == 0) {
                Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                showAlert(response.getMessage());
                llPersonalInfo.setVisibility(View.VISIBLE);
                llProfessionalInfo.setVisibility(View.GONE);
            }

        } else if (response instanceof InsuranceMasterResponse) {
            healthList = dbPersistanceController.getHealthListNames();
            generalList = dbPersistanceController.getGeneralListNames();
            lifeList = dbPersistanceController.getLifeListNames();
            initMultiSelect();
        } else if (response instanceof ReferFriendResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                isVAlidPromo = true;
                Snackbar.make(etRefererCode, "" + response.getMessage(), Snackbar.LENGTH_LONG).show();
            } else {
                isVAlidPromo = false;
                Snackbar.make(etRefererCode, "" + response.getMessage(), Snackbar.LENGTH_LONG).show();
                etRefererCode.setText("");
            }
        }
    }

    private void bindSource() {

        if (sourceList == null && sourceList.size() == 0) {
            sourceList.add(new SourceEntity("Fin-Mart", 1));
            sourceList.add(new SourceEntity("Campaign sm", 2));
        }

        // region prev insurer adapter
        ArrayAdapter prevInsAdapter = new
                ArrayAdapter<SourceEntity>(this, android.R.layout.simple_list_item_1, sourceList) {
                    @Override
                    public boolean isEnabled(int position) {
                       /* if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }*/
                        return true;
                    }

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        if (convertView == null) {
                            LayoutInflater inflater = LayoutInflater.from(getContext());
                            convertView = inflater.inflate(
                                    android.R.layout.simple_spinner_item, parent, false);
                        }


                        TextView tv = (TextView) convertView
                                .findViewById(android.R.id.text1);

                        tv.setText(sourceList.get(position).getSource_name());

                        tv.setTextColor(Color.BLACK);

                        tv.setTextSize(Constants.SPINNER_FONT_SIZE);
                        return convertView;
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        tv.setTextColor(Color.BLACK);

                        return view;
                    }
                };
        spSource.setAdapter(prevInsAdapter);
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        trackingRequestEntity.setType("Register");
        trackingRequestEntity.setData(new TrackingData(t.getMessage()));
        new TrackingController(this).sendData(trackingRequestEntity, null);
    }

    private String extractDigitFromMessage(String message) {
        //---This will match any 6 digit number in the message, can use "|" to lookup more possible combinations
        Pattern p = Pattern.compile("(|^)\\d{6}");
        try {
            if (message != null) {
                Matcher m = p.matcher(message);
                if (m.find()) {
                    return m.group(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private void showOtpAlert() {

        try {


            String ncbBold = String.valueOf(etMobile1.getText().toString());
            SpannableString ss1 = new SpannableString(ncbBold);
            ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
            String normalText = "Enter OTP sent on Mobile no ";

            dialog = new Dialog(RegisterActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.otp_dialog);
            tvOk = (TextView) dialog.findViewById(R.id.tvOk);
            TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);

            tvTitle.setText("");
            tvTitle.append(normalText);
            tvTitle.append(ss1);


            TextView resend = (TextView) dialog.findViewById(R.id.tvResend);
            etOtp = (EditText) dialog.findViewById(R.id.etOtp);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);

            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = lp.MATCH_PARENT;
            ; // Width
            lp.height = lp.WRAP_CONTENT; // Height
            dialogWindow.setAttributes(lp);

            dialog.show();
            tvOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    if (etOtp.getText().toString().equals("0000")) {
                        Toast.makeText(RegisterActivity.this, "Otp Verified Success", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        hideAllLayouts(llProfessionalInfo, ivProfessionalInfo);
                        btnSubmit.setVisibility(View.VISIBLE);
                        isMobileValid = true;
                    } else {
                        showDialog("Verifying OTP...");
                        new RegisterController(RegisterActivity.this).validateOtp(etMobile1.getText().toString(), etOtp.getText().toString(), RegisterActivity.this);
                    }

                }
            });

            resend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    etOtp.setText("");
                    showDialog("Re-sending otp...");
                    new RegisterController(RegisterActivity.this).generateOtp(etMobile1.getText().toString(), RegisterActivity.this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int i = compoundButton.getId();
        if (i == R.id.chbxGen) {
            if (b) {
                spGenIns.setEnabled(true);
            } else {
                spGenIns.setEnabled(false);
            }

        } else if (i == R.id.chbxHealth) {
            if (b) {
                spHealthIns.setEnabled(true);
            } else {
                spHealthIns.setEnabled(false);
            }

        } else if (i == R.id.chbxLife) {
            if (b) {
                spLifeIns.setEnabled(true);

            } else {
                spLifeIns.setEnabled(false);
            }

        }
    }

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
            if (s.length() == 6) {
                showDialog("Fetching City...");
                new RegisterController(RegisterActivity.this).getCityState(etPincode.getText().toString(), RegisterActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher mobileTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (start == 9) {
                isMobileValid = false;
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher refererTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 6) {
                showDialog("Validating Promo code...");
                new LoginController(RegisterActivity.this).referFriend("" + spReferal.getSelectedItemPosition(), etRefererCode.getText().toString(), RegisterActivity.this);

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //endregion

    //region datepicker
    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, RegisterActivity.this);

            if (view.getId() == R.id.etDob) {
                DateTimePicker.showHealthAgeDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            pass = passdateFormat.format(calendar.getTime());
                            //store selected date in TAG
                            etDob.setTag(R.id.etDob, calendar.getTime());
                            etDob.setText(currentDay);
                        }
                    }
                });
            }
        }
    };
    //endregion

    private void setGender(TextView clickedText, TextView textView1) {


        clickedText.setBackgroundResource(R.drawable.customeborder_blue);
        clickedText.setTextColor(ContextCompat.getColor(RegisterActivity.this, Utility.getPrimaryColor(this)));

        textView1.setBackgroundResource(R.drawable.customeborder);
        textView1.setTextColor(ContextCompat.getColor(RegisterActivity.this, R.color.description_text));


    }

    private void manageTaskBar() {


        if (isValidPersonalInfo == false) {
            llPersonalInfo.setVisibility(View.VISIBLE);
            llProfessionalInfo.setVisibility(View.GONE);
        }
    }

    private boolean checkPL_Info() {

        if (!isEmpty(etFirstName)) {
            return false;
        }

        if (!isEmpty(etLastName)) {
            return false;
        }
        if (!isEmpty(etDob)) {

            return false;
        }
        if (!isValidePhoneNumber(etMobile1)) {

            return false;
        }
        if (!isValideEmailID(etEmail)) {

            return false;
        }
        if (!isValideEmailID(etConfirmEmail)) {

            return false;
        }
        if (!etEmail.getText().toString().equals(etConfirmEmail.getText().toString())) {
            return false;
        }
        if (!isEmpty(etPincode)) {

            return false;
        }
        if (!isEmpty(etCity)) {

            return false;
        }
        if (!isEmpty(etState)) {

            return false;
        }
        if (!isEmpty(etFirstName)) {

            return false;
        }
        return true;
    }


    private boolean checkPR_Info() {

        return false;
    }
}