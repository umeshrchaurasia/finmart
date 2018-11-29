package com.datacomp.magicfinmart.scan_vehicle;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Text;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.DynamicController;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.GenerateLeadRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.GenerateLeadResponse;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.VehicleInfoEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.fastlane.FastLaneController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.FastLaneDataEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.FastLaneDataResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.controller.MotorController;

public class VehicleScanActivity extends BaseActivity implements BaseActivity.PopUpListener {

    private static final String LOG_TAG = "VehicleScanActivity";
    // private TextView scanResults;
    ImageView imageView;
    EditText etVehicleNo;
    private Uri imageUri;
    private Uri cropImageUri;
    private TextRecognizer detector;
    private static final int REQUEST_WRITE_PERMISSION = 20;

    private static final int CAMERA_REQUEST = 1000;
    private String PHOTO_File = "ScanPhotograph";
    Button btnVehicleInfo, btnLead;
    FastLaneDataEntity entity;

    File Docfile;

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"

    };


    CardView cvVehicleDetail;
    SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");


    TextView txtRegistrationNo, txtCarDetail;
    EditText etVehicleExpiryDate, etName, etMobileNo;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.imageView);
        btnVehicleInfo = (Button) findViewById(R.id.btnVehicleInfo);
        btnLead = (Button) findViewById(R.id.btnLead);

        txtRegistrationNo = (TextView) findViewById(R.id.txtRegistrationNo);
        txtCarDetail = (TextView) findViewById(R.id.txtCarDetail);
        cvVehicleDetail = (CardView) findViewById(R.id.cvVehicleDetail);
        //card view visible gone
        cvVehicleDetail.setVisibility(View.GONE);
        etVehicleExpiryDate = (EditText) findViewById(R.id.etVehicleExpiryDate);
        etVehicleNo = (EditText) findViewById(R.id.etVehicleNo);

        etName = (EditText) findViewById(R.id.etName);
        etMobileNo = (EditText) findViewById(R.id.etMobileNo);


        etVehicleExpiryDate.setOnClickListener(datePickerDialog);


        detector = new TextRecognizer.Builder(getApplicationContext()).build();

        btnVehicleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.hideKeyBoard(view, VehicleScanActivity.this);
                if (etVehicleNo.getText().toString().length() < 4) {
                    Toast.makeText(VehicleScanActivity.this, "Invalid Vehicle number", Toast.LENGTH_SHORT).show();
                    return;
                }

                showDialog();
                new FastLaneController(VehicleScanActivity.this).getVechileDetails(etVehicleNo.getText().toString().replaceAll("\\s", ""), new IResponseSubcriber() {

                    @Override
                    public void OnSuccess(APIResponse response, String message) {

                        cancelDialog();
                        if (response instanceof FastLaneDataResponse) {

                            if (response.getStatusNo() == 0) {
                                bindFastlaneVehicle(((FastLaneDataResponse) response).getMasterData());
                            }
                        }
                    }

                    @Override
                    public void OnFailure(Throwable t) {
                        cancelDialog();
                        Toast.makeText(VehicleScanActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

              /*  new DynamicController(VehicleScanActivity.this).getVehicleByVehicleNo(etVehicleNo.getText().toString(),
                        new IResponseSubcriber() {

                            @Override
                            public void OnSuccess(APIResponse response, String message) {
                                cancelDialog();
                                if (response instanceof magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.VehicleInfoEntity) {
                                    //bind vehicle

                                    if (((VehicleInfoEntity) response).getGetRegNoDataResult() == null) {
                                        Toast.makeText(VehicleScanActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else if (((VehicleInfoEntity) response).getGetRegNoDataResult().size() == 0) {
                                        Toast.makeText(VehicleScanActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    mVehicleInfo =
                                            ((VehicleInfoEntity) response).getGetRegNoDataResult().get(0);
                                    bindVehicle(mVehicleInfo);
                                }
                            }

                            @Override
                            public void OnFailure(Throwable t) {
                                cancelDialog();
                                cvVehicleDetail.setVisibility(View.GONE);
                                Toast.makeText(VehicleScanActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });*/
            }
        });


        btnLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.hideKeyBoard(v, VehicleScanActivity.this);
                if (etName.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(VehicleScanActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                GenerateLead(entity);
            }
        });
        CameraPopUp();

    }

    private void GenerateLead(FastLaneDataEntity vehicleInfo) {

        GenerateLeadRequestEntity entity = new GenerateLeadRequestEntity();

        entity.setFBAID(String.valueOf(new DBPersistanceController(this).getUserData().getFBAId()));

        entity.setChasisNo(vehicleInfo.getChassis_Number());
        entity.setCity(vehicleInfo.getVehicleCity_Id());
        entity.setClaimNo("");
        entity.setClaimSattlementType("");
        entity.setClaimStatus("");
        entity.setDOB("");
        entity.setEngineNo(vehicleInfo.getEngin_Number());
        entity.setFuelType(vehicleInfo.getFuel_Type());
        entity.setGender("");
        entity.setHolderPincode("");
        entity.setInceptionDate("");
        entity.setIsCustomer("");
        entity.setMake(vehicleInfo.getMake_Name());
        entity.setMfgyear(String.valueOf(vehicleInfo.getManufacture_Year()));
        entity.setNoClaimBonus("");
        entity.setPOSPCode("");
        entity.setPOSPName("");
        entity.setRTOCity(vehicleInfo.getRTO_Name());
        entity.setRTOState("");
        entity.setRegistrationDate(vehicleInfo.getRegistration_Date());
        entity.setRegistrationNo(vehicleInfo.getRegistration_Number());
        entity.setSubModel(vehicleInfo.getModel_Name());
        entity.setHolderaddress("");
        entity.setModel(vehicleInfo.getModel_Name());

        entity.setQT_Entry_Number("");

        entity.setClientName(etName.getText().toString());
        entity.setExpiryDate(etVehicleExpiryDate.getText().toString());
        entity.setMobileNo(etMobileNo.getText().toString());


        new DynamicController(this).saveGenerateLead(entity, new IResponseSubcriber() {
            @Override
            public void OnSuccess(APIResponse response, String message) {
                cancelDialog();
                if (response instanceof GenerateLeadResponse) {
                    if (response.getStatusNo() == 0) {
                        Toast.makeText(VehicleScanActivity.this, "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void OnFailure(Throwable t) {
                cancelDialog();
                Toast.makeText(VehicleScanActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //region date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, VehicleScanActivity.this);


            if (view.getId() == R.id.etVehicleExpiryDate) {

                //region  regdate renew
                DateTimePicker.scanVehicleExpDatePicker(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = displayFormat.format(calendar.getTime());
                            etVehicleExpiryDate.setText(currentDay);
                            btnLead.setVisibility(View.VISIBLE);
                        }
                    }
                });


            }
            //endregion


        }
    };
    //endregion


    private void bindFastlaneVehicle(FastLaneDataEntity v) {

        entity = v;
        cvVehicleDetail.setVisibility(View.VISIBLE);

        try {
            txtRegistrationNo.setText(v.getRegistration_Number());
            txtCarDetail.setText(v.getMake_Name() + "," + v.getModel_Name() + "," + v.getVariant_Name());
            etVehicleExpiryDate.setText("");

        } catch (Exception e) {

        }
    }


    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSION_CAMERA_STORACGE_CONSTANT:
                if (grantResults.length > 0) {
                    boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternal = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternal = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (camera && writeExternal && readExternal) {
                        launchCameras();

                    }
                }
                break;
        }
    }

    // region camera


    private void CameraPopUp() {

        if (!checkPermission()) {

            if (checkRationalePermission()) {
                requestPermission();

            } else {
                openPopUp(btnVehicleInfo, "Need  Permission", "This app needs all permissions.", "GRANT", true);
            }
        } else {
            launchCameras();
        }
    }

    private void launchCameras() {

        String FileName = "";
        FileName = PHOTO_File;

        Docfile = createFile(FileName);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imageUri = Uri.fromFile(Docfile);
        } else {
            imageUri = FileProvider.getUriForFile(VehicleScanActivity.this,
                    getString(R.string.file_provider_authority), Docfile);
        }


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //extractTextFromImage();
            startCropImageActivity(imageUri);
        }
        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    cropImageUri = result.getUri();
                    imageView.setImageBitmap(decodeBitmapUri(this, result.getUri()));//.setImageURI(result.getUri());
                    extractTextFromImage();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    //TODO: Crop image activity to crop capture image.
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    private void extractTextFromImage() {
        Bitmap bitmap;
        try {
            //  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            bitmap = decodeBitmapUri(this, cropImageUri);
            // imageView.setImageBitmap(bitmap);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

            try {
                // Bitmap bitmap = decodeBitmapUri(this, imageUri);
                if (detector.isOperational() && bitmap != null) {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> textBlocks = detector.detect(frame);
                    String blocks = "";
                    String lines = "";
                    String words = "";
                    for (int index = 0; index < textBlocks.size(); index++) {
                        //extract scanned text blocks here
                        TextBlock tBlock = textBlocks.valueAt(index);
                        blocks = blocks + tBlock.getValue() + "\n" + "\n";
                        for (Text line : tBlock.getComponents()) {
                            //extract scanned text lines here
                            lines = lines + line.getValue() + "\n";
                            for (Text element : line.getComponents()) {
                                //extract scanned text words here
                                words = words + element.getValue() + ", ";
                            }
                        }
                    }
                    if (textBlocks.size() == 0) {
                        Toast.makeText(this, "Failed to read vehicle number", Toast.LENGTH_SHORT).show();
                        //  scanResults.setText("Scan Failed: Found nothing to scan");
                    } else {
                        /*scanResults.setText(scanResults.getText() + "Blocks: " + "\n");
                        scanResults.setText(scanResults.getText() + blocks + "\n");
                        scanResults.setText(scanResults.getText() + "---------" + "\n");
                        scanResults.setText(scanResults.getText() + "Lines: " + "\n");
                        scanResults.setText(scanResults.getText() + lines + "\n");
                        scanResults.setText(scanResults.getText() + "---------" + "\n");
                        scanResults.setText(scanResults.getText() + "Words: " + "\n");
                        scanResults.setText(scanResults.getText() + words + "\n");
                        scanResults.setText(scanResults.getText() + "---------" + "\n");*/

                        etVehicleNo.setText(blocks.trim());
                    }
                } else {
                    Toast.makeText(this, "Failed to read vehicle number", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT)
                        .show();
                Log.e(LOG_TAG, e.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
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

        boolean camera = ActivityCompat.shouldShowRequestPermissionRationale(VehicleScanActivity.this, perms[0]);

        boolean write_external = ActivityCompat.shouldShowRequestPermissionRationale(VehicleScanActivity.this, perms[1]);
        boolean read_external = ActivityCompat.shouldShowRequestPermissionRationale(VehicleScanActivity.this, perms[2]);

        return camera || write_external || read_external;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, Constants.PERMISSION_CAMERA_STORACGE_CONSTANT);
    }

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

    //endregion

    //endregion

}
