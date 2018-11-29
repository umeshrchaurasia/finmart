package com.datacomp.magicfinmart.crnpolicy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;

public class crnpolicyActivity extends BaseActivity implements View.OnClickListener{

    TextView etcrn;
    Button btncrn;

    String mobNumber="";

    UserConstantEntity userConstantEntity;
    DBPersistanceController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crnpolicy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().setTitle("CRN POLICY");


        init();
        db = new DBPersistanceController(this);
        userConstantEntity = db.getUserConstantsData();
    }

    private void init() {


        etcrn = (TextView) findViewById(R.id.etcrn);


        btncrn = (Button) findViewById(R.id.btncrn);

        btncrn.setOnClickListener(this);

  }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btncrn) {
            mobNumber=  ""+userConstantEntity.getCrnmobileno();
                try {
                    mobNumber = mobNumber.replaceAll("\\s", "");
                    mobNumber = mobNumber.replaceAll("\\+", "");
                    mobNumber = mobNumber.replaceAll("-", "");
                    mobNumber = mobNumber.replaceAll(",", "");

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", mobNumber, null));
                    intent.putExtra("sms_body", "policy "+etcrn.getText().toString());
                    startActivity(intent);
                //    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", mobNumber, null)));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
                }


        }
    }
}
