package com.datacomp.magicfinmart.utility;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.datacomp.magicfinmart.R;

/**
 * Created by Rajeev Ranjan on 10/01/2018.
 */

public class GenericTextWatcher implements TextWatcher {

    View view;
    iVehicle ivehicle;

    View stateCode;

    public interface iVehicle {

        void getVehicleNumber(View view, String vehicleNo);

        void cancelVehicleNumber(View view);
    }

    public GenericTextWatcher(View view, iVehicle vehicle) {
        this.view = view;
        this.ivehicle = vehicle;
    }

    public GenericTextWatcher(View stateCode, View view, iVehicle vehicle) {
        this.view = view;
        this.stateCode = stateCode;
        this.ivehicle = vehicle;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("GenericTextWatcher", "beforeTextChanged");
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("GenericTextWatcher", "onTextChanged");
        int i3 = view.getId();
        if (i3 == R.id.etreg1) {
            if (charSequence.length() == 0) {
                ivehicle.cancelVehicleNumber(view);
            } else if (charSequence.length() == 2) {
                ivehicle.getVehicleNumber(view, charSequence.toString());
            }

        } else if (i3 == R.id.etreg2) {
            if (charSequence.length() == 0) {
                ivehicle.cancelVehicleNumber(view);
            } else if (charSequence.length() == 2) {
                ivehicle.getVehicleNumber(view, charSequence.toString());
            }

        } else if (i3 == R.id.etreg3) {
            if (charSequence.length() == 0) {
                ivehicle.cancelVehicleNumber(view);
            } else if (((EditText) stateCode).getText().toString().toLowerCase().equalsIgnoreCase("dl")) {
                InputFilter[] FilterArray = new InputFilter[2];
                FilterArray[0] = new InputFilter.LengthFilter(3);
                FilterArray[1] = new InputFilter.AllCaps();
                ((EditText) view).setFilters(FilterArray);
            } else if (charSequence.length() == 2) {
                ivehicle.getVehicleNumber(view, charSequence.toString());
            }

        } else if (i3 == R.id.etreg4) {
            if (charSequence.length() == 0) {
                ivehicle.cancelVehicleNumber(view);
            } else if (charSequence.length() == 4) {
                ivehicle.getVehicleNumber(view, charSequence.toString());
            }

        } else if (i3 == R.id.acRto) {
            ivehicle.cancelVehicleNumber(view);

        } else if (i3 == R.id.etMobile1) {
            if (charSequence.length() == 10) {
                ivehicle.getVehicleNumber(view, charSequence.toString());
            }

        } else if (i3 == R.id.etPincode) {
            if (charSequence.length() == 6) {
                ivehicle.getVehicleNumber(view, charSequence.toString());
            }

        } else if (i3 == R.id.acMakeModel) {
            ivehicle.cancelVehicleNumber(view);

        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.d("GenericTextWatcher", "afterTextChanged");
    }
}
