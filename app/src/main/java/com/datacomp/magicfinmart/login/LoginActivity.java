package com.datacomp.magicfinmart.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.register.RegisterActivity;
import com.datacomp.magicfinmart.utility.ReadDeviceID;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.login.LoginController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.LoginRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ForgotResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.LoginResponse;

public class LoginActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {
    PrefManager prefManager;
    EditText etEmail, etPassword;
    LoginRequestEntity loginRequestEntity;
    TextView tvSignUp, tvForgotPass;
    Button btnSignIn;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 1111;

    private static int PERMISSION_DENIED = 0;

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.SEND_SMS",
            "android.permission.READ_SMS",
            "android.permission.RECEIVE_SMS",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.CALL_PHONE",
            "android.permission.RECORD_AUDIO",
            "android.permission.READ_CONTACTS",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        loginRequestEntity = new LoginRequestEntity();
        initWidgets();
        setListener();
        realm = Realm.getDefaultInstance();
        prefManager = new PrefManager(this);


        if (!checkPermission()) {
            requestPermission();
        }
    }

    @Override
    public void onBackPressed() {
        dialogExit();
    }


    //region permission

    private boolean checkPermission() {

        int camera = ContextCompat.checkSelfPermission(getApplicationContext(), perms[0]);
        int fineLocation = ContextCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int sendSms = ContextCompat.checkSelfPermission(getApplicationContext(), perms[2]);
        int readSms = ContextCompat.checkSelfPermission(getApplicationContext(), perms[3]);
        int receiveSms = ContextCompat.checkSelfPermission(getApplicationContext(), perms[4]);
        int WRITE_EXTERNAL = ContextCompat.checkSelfPermission(getApplicationContext(), perms[5]);
        int READ_EXTERNAL = ContextCompat.checkSelfPermission(getApplicationContext(), perms[6]);
        int callPhone = ContextCompat.checkSelfPermission(getApplicationContext(), perms[7]);
        int recordAudio = ContextCompat.checkSelfPermission(getApplicationContext(), perms[8]);
        int readContact = ContextCompat.checkSelfPermission(getApplicationContext(), perms[9]);
        return camera == PackageManager.PERMISSION_GRANTED
                && fineLocation == PackageManager.PERMISSION_GRANTED
                && sendSms == PackageManager.PERMISSION_GRANTED
                && readSms == PackageManager.PERMISSION_GRANTED
                && receiveSms == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL == PackageManager.PERMISSION_GRANTED
                && READ_EXTERNAL == PackageManager.PERMISSION_GRANTED
                && callPhone == PackageManager.PERMISSION_GRANTED
                && recordAudio == PackageManager.PERMISSION_GRANTED
                && readContact == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean fineLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean sendSms = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean readSms = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean receiveSms = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternal = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternal = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    boolean callPhone = grantResults[7] == PackageManager.PERMISSION_GRANTED;
                    boolean recordAudio = grantResults[8] == PackageManager.PERMISSION_GRANTED;
                    boolean readContact = grantResults[8] == PackageManager.PERMISSION_GRANTED;

                    if (camera && fineLocation && sendSms && readSms && receiveSms && writeExternal && readExternal && callPhone && recordAudio && readContact) {

                        // Toast.makeText(this, "All permission granted", Toast.LENGTH_SHORT).show();
                    } else {

                        //Permission Denied, You cannot access location data and camera
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                            showMessageOKCancel("Required permissions to proceed Magic-finmart..!",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            // finish();
                                            if (2 > PERMISSION_DENIED) {
                                                PERMISSION_DENIED++;
                                                requestPermission();
                                            } else {
                                                dialogInterface.dismiss();

                                            }
                                        }
                                    });
                        } else {
                            //  requestPermission();
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialog_Theme)

                .setTitle("Retry")
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                //.setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    //endregion

    private void setListener() {
        tvSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);
    }

    private void initWidgets() {
        tvSignUp = (TextView) findViewById(R.id.tvSignUp);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvForgotPass) {
            dialogForgotPassword();

        } else if (i == R.id.tvSignUp) {
            startActivity(new Intent(this, RegisterActivity.class));

        } else if (i == R.id.btnSignIn) {
            if (!isEmpty(etEmail)) {
                etEmail.requestFocus();
                Toast.makeText(this, "Enter Valid Email/User Id", Toast.LENGTH_SHORT).show();
                //etEmail.setError("Enter Valid Email");
                return;
            }
            if (!isEmpty(etPassword)) {
                etPassword.requestFocus();
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                //etPassword.setError("Enter Password");
                return;
            }
            //   Toast.makeText(this,prefManager.getToken(),Toast.LENGTH_LONG).show();
            loginRequestEntity.setUserName(etEmail.getText().toString());
            loginRequestEntity.setPassword(etPassword.getText().toString());
            loginRequestEntity.setDeviceId("" + new ReadDeviceID(this).getAndroidID());
            loginRequestEntity.setTokenId(prefManager.getToken());
            showDialog();
            new LoginController(this).login(loginRequestEntity, this);

        }
    }

    private void dialogForgotPassword() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        // builder.setTitle("FORGOT PASSWORD");
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_forgot_password, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        final EditText etEmail = (EditText) view.findViewById(R.id.etEmail);
        Button btnReset = (Button) view.findViewById(R.id.btnReset);
       /* Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
       btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });*/

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isValideEmailID(etEmail)) {
                    etEmail.setError("Invalid Email ID");
                    etEmail.setFocusable(true);
                    //return;
                } else {
                    dialog.dismiss();
                    forgotPasswrod(etEmail);
                }
            }
        });

    }

    private void forgotPasswrod(EditText etEmail) {
        showDialog("Retrieving password...");
        new LoginController(LoginActivity.this)
                .forgotPassword(etEmail.getText().toString(), LoginActivity.this);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof LoginResponse) {
            if (response.getStatusNo() == 0) {

                // prefManager.setIsUserLogin(true);
                if (!prefManager.getSharePushType().equals("")) {

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra(Utility.PUSH_LOGIN_PAGE, "555");
                    startActivity(intent);

                } else {
                    startActivity(new Intent(this, HomeActivity.class));
                }

                finish();
            } else {
                Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (response instanceof ForgotResponse) {
            if (response.getStatusNo() == 0) {
                Toast.makeText(this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
            }
            new TrackingController(this).sendData(new TrackingRequestEntity(new TrackingData("Login Success : " + response.getMessage()), "Login"), null);
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        new TrackingController(this).sendData(new TrackingRequestEntity(new TrackingData("Login Failure : " + t.getMessage()), "Login"), null);
    }
}
