package com.datacomp.magicfinmart.IncomeCalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.datacomp.magicfinmart.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.setAutoFocus(true);
        mScannerView.startCamera();
        // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }



    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("Barcode", rawResult.getText()); // Prints scan results
//        Log.v("Barcode", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
//        Toast.makeText(this, "" + rawResult.getText(), Toast.LENGTH_SHORT).show();
//        // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("RAWRESULT", rawResult.getText());
        resultIntent.putExtra("TYPE", rawResult.getBarcodeFormat().toString());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}