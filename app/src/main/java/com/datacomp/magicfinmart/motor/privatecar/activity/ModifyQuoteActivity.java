package com.datacomp.magicfinmart.motor.privatecar.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseActivity;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import magicfinmart.datacomp.com.finmartserviceapi.motor.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.motor.controller.MotorController;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.SummaryEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikeUniqueResponse;

public class ModifyQuoteActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {
    MotorRequestEntity motorRequestEntity;
    SummaryEntity summaryEntity;
    ImageView ivCross;
    Button applyNow;
    EditText etElecAcc, etNonElecAcc, etIdv;
    Spinner spPaCover, spVolExcessAmt;
    DiscreteSeekBar sbIdv;
    ArrayAdapter<String> volAccessAdapter, coverAdapter;
    String[] volAccess, cover;
    Switch swlldriver, swAnti, swMemAto, swPaidPa;
    TextView tvLiabYes, tvLiabNo, tvAntiYes, tvAntiNo, tvMinIdv, tvMaxIdv, tvProgress;
    boolean isLiability = false, isAntiTheft = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_quote);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFinishOnTouchOutside(true);
        if (getIntent().hasExtra("CAR_REQUEST")) {
            motorRequestEntity = getIntent().getParcelableExtra("CAR_REQUEST");
            volAccess = getResources().getStringArray(R.array.voluntary_car);
            cover = getResources().getStringArray(R.array.pa_cover_car);
        }
        if (getIntent().hasExtra("BIKE_REQUEST")) {
            motorRequestEntity = getIntent().getParcelableExtra("BIKE_REQUEST");
            volAccess = getResources().getStringArray(R.array.voluntary_bike);
            cover = getResources().getStringArray(R.array.pa_cover_bike);
        }
        if (getIntent().hasExtra("SUMMARY")) {
            summaryEntity = getIntent().getParcelableExtra("SUMMARY");
        }

        initWidgets();
        bindAdapters();
        setListener();

        filPrevInputs();
    }

    private void filPrevInputs() {
        if (!motorRequestEntity.getElectrical_accessory().matches("0"))
            etElecAcc.setText(motorRequestEntity.getElectrical_accessory());
        if (!motorRequestEntity.getNon_electrical_accessory().matches("0"))
            etNonElecAcc.setText(motorRequestEntity.getNon_electrical_accessory());

        int volAmt = motorRequestEntity.getVoluntary_deductible();
        for (int i = 0; i < volAccess.length; i++) {
            if (volAmt == Integer.parseInt(volAccess[i])) {
                spVolExcessAmt.setSelection(i);
                break;
            }
        }
        if (!motorRequestEntity.getPa_unnamed_passenger_si().equals("")) {
            int coverAmt = Integer.parseInt(motorRequestEntity.getPa_unnamed_passenger_si());
            for (int i = 0; i < cover.length; i++) {
                if (coverAmt == Integer.parseInt(cover[i])) {
                    spPaCover.setSelection(i);
                    break;
                }
            }
        }
        if (motorRequestEntity.getIs_llpd().matches("yes")) {
            tvLiabYes.performClick();
        } else {
            tvLiabNo.performClick();
        }

        if (motorRequestEntity.getIs_antitheft_fit().matches("yes")) {
            tvAntiYes.performClick();
        } else {
            tvAntiNo.performClick();
        }

        if (motorRequestEntity.getVehicle_expected_idv() > 0) {
            etIdv.setText("" + motorRequestEntity.getVehicle_expected_idv());
            sbIdv.setProgress((int)(motorRequestEntity.getVehicle_expected_idv() / 1000));
            tvProgress.setText("DESIRED IDV (" + motorRequestEntity.getVehicle_expected_idv() + ")");
        }
    }

    private void bindAdapters() {
        volAccessAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, volAccess) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(5, 2, 5, 2);
                return view;
            }
        };
        spVolExcessAmt.setAdapter(volAccessAdapter);
        coverAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cover) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(5, 2, 5, 2);
                return view;
            }
        };
        spPaCover.setAdapter(coverAdapter);
    }

    private void setListener() {
        applyNow.setOnClickListener(this);
        ivCross.setOnClickListener(this);
        tvAntiNo.setOnClickListener(this);
        tvAntiYes.setOnClickListener(this);
        tvLiabYes.setOnClickListener(this);
        tvLiabNo.setOnClickListener(this);
        try {
            sbIdv.setMin(Integer.parseInt(summaryEntity.getVehicle_min_idv()) / 1000);
            sbIdv.setMax(Integer.parseInt(summaryEntity.getVehicle_max_idv()) / 1000);
            tvMinIdv.setText("" + summaryEntity.getVehicle_min_idv());
            tvMaxIdv.setText("" + summaryEntity.getVehicle_max_idv());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sbIdv.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return getPercentFromProgress(value);
            }
        });

        sbIdv.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                if (fromUser) {
                    etIdv.setText("" + getPercentFromProgress(value));
                    tvProgress.setText("DESIRED IDV (" + getPercentFromProgress(value) + ")");
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });
    }

    public int getPercentFromProgress(int value) {

      /*  switch (value) {
            case 0:
                return 0;
            case 1:
                return 20;
            case 2:
                return 25;
            case 3:
                return 35;
            case 4:
                return 45;
            case 5:
                return 50;
        }*/
        return value * 1000;
    }

    private void initWidgets() {

        etIdv = (EditText) findViewById(R.id.etIdv);
        sbIdv = (DiscreteSeekBar) findViewById(R.id.sbIdv);
        tvMaxIdv = (TextView) findViewById(R.id.tvMaxIdv);
        tvMinIdv = (TextView) findViewById(R.id.tvMinIdv);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        ivCross = (ImageView) findViewById(R.id.ivCross);
        applyNow = (Button) findViewById(R.id.applyNow);

        etElecAcc = (EditText) findViewById(R.id.etElecAcc);
        etNonElecAcc = (EditText) findViewById(R.id.etNonElecAcc);

        spVolExcessAmt = (Spinner) findViewById(R.id.spVolExcessAmt);
        tvLiabYes = (TextView) findViewById(R.id.tvLiabYes);
        tvLiabNo = (TextView) findViewById(R.id.tvLiabNo);
        tvAntiYes = (TextView) findViewById(R.id.tvAntiYes);
        tvAntiNo = (TextView) findViewById(R.id.tvAntiNo);

        spPaCover = (Spinner) findViewById(R.id.spPaCover);
        if (getIntent().hasExtra("BIKE_REQUEST")) {
            etElecAcc.setVisibility(View.GONE);
            etNonElecAcc.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tvAntiNo) {
            isAntiTheft = false;
            tvAntiNo.setBackgroundResource(R.drawable.customeborder_blue);
            tvAntiYes.setBackgroundResource(R.drawable.customeborder);

        } else if (i == R.id.tvAntiYes) {
            isAntiTheft = true;
            tvAntiNo.setBackgroundResource(R.drawable.customeborder);
            tvAntiYes.setBackgroundResource(R.drawable.customeborder_blue);

        } else if (i == R.id.tvLiabNo) {
            isLiability = false;
            tvLiabNo.setBackgroundResource(R.drawable.customeborder_blue);
            tvLiabYes.setBackgroundResource(R.drawable.customeborder);

        } else if (i == R.id.tvLiabYes) {
            isLiability = true;
            tvLiabNo.setBackgroundResource(R.drawable.customeborder);
            tvLiabYes.setBackgroundResource(R.drawable.customeborder_blue);

        } else if (i == R.id.ivCross) {
            finish();

        } else if (i == R.id.applyNow) {
            if (!etElecAcc.getText().toString().isEmpty() && !etElecAcc.getText().toString().equals("0")) {
                int elec = Integer.parseInt(etElecAcc.getText().toString());
                if (elec < 10000 || elec > 50000) {
                    etElecAcc.requestFocus();
                    etElecAcc.setError("Enter Amount between 10000 & 50000");
                    return;
                }
            }
            if (!etNonElecAcc.getText().toString().isEmpty() && !etNonElecAcc.getText().toString().equals("0")) {
                int nonElec = Integer.parseInt(etNonElecAcc.getText().toString());
                if (nonElec < 10000 || nonElec > 50000) {
                    etNonElecAcc.requestFocus();
                    etNonElecAcc.setError("Enter Amount between 10000 & 50000");
                    return;
                }
            }
            if (!etIdv.getText().toString().isEmpty()) {
                long idv = Long.parseLong(etIdv.getText().toString());
                long minIdv = Long.parseLong(summaryEntity.getVehicle_min_idv());
                long maxIdv = Long.parseLong(summaryEntity.getVehicle_max_idv());
                if (idv < minIdv || idv > maxIdv) {
                    etIdv.requestFocus();
                    etIdv.setError("Enter IDV between " + minIdv + " & " + maxIdv);
                    return;
                }
            }
            addparameters();
            showDialog("Modifying quotes...");
            new MotorController(this).getMotorPremiumInitiate(motorRequestEntity, this);

        }
    }

    private void addparameters() {

        if (isLiability) {
            motorRequestEntity.setIs_llpd("yes");
        } else {
            motorRequestEntity.setIs_llpd("no");
        }

        if (isAntiTheft) {
            motorRequestEntity.setIs_antitheft_fit("yes");
        } else {
            motorRequestEntity.setIs_antitheft_fit("no");
        }

        if (!etElecAcc.getText().toString().isEmpty())
            motorRequestEntity.setElectrical_accessory(etElecAcc.getText().toString());
        if (!etNonElecAcc.getText().toString().isEmpty())
            motorRequestEntity.setNon_electrical_accessory(etNonElecAcc.getText().toString());

        if (!etIdv.getText().toString().isEmpty())
            motorRequestEntity.setVehicle_expected_idv(Long.parseLong(etIdv.getText().toString()));

        motorRequestEntity.setVoluntary_deductible(Integer.parseInt(spVolExcessAmt.getSelectedItem().toString()));


        motorRequestEntity.setPa_unnamed_passenger_si(spPaCover.getSelectedItem().toString());


        /*if (swMemAto.isChecked()) {
            motorRequestEntity.setIs_aai_member("yes");
        } else {
            motorRequestEntity.setIs_aai_member("no");
        }

        if (swPaidPa.isChecked()) {
            motorRequestEntity.setPa_paid_driver_si("yes");
        } else {
            motorRequestEntity.setPa_paid_driver_si("no");
        }*/
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof BikeUniqueResponse) {
            //startActivity(new Intent(this, QuoteActivity.class).putExtra("CAR_REQUEST", motorRequestEntity));

            Intent resultIntent = new Intent();
            resultIntent.putExtra("MODIFY", motorRequestEntity);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }


    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);

            finish();

        }
        return super.onOptionsItemSelected(item);
    }

}
