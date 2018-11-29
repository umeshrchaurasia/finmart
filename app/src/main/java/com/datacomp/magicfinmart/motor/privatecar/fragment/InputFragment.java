package com.datacomp.magicfinmart.motor.privatecar.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.MyApplication;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;
import com.datacomp.magicfinmart.location.ILocationStateListener;
import com.datacomp.magicfinmart.location.LocationTracker;
import com.datacomp.magicfinmart.motor.privatecar.activity.InputQuoteBottmActivity;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;
import com.datacomp.magicfinmart.utility.GenericTextWatcher;
import com.google.gson.Gson;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.fastlane.FastLaneController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CarMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.FastLaneDataEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.InsuranceSubtypeEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.CarMasterResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.FastLaneDataResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.motor.controller.MotorController;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikeUniqueResponse;

import static com.datacomp.magicfinmart.utility.DateTimePicker.getDiffYears;

/**
 * Created by Rajeev Ranjan on 29/01/2018.
 */

public class InputFragment extends BaseFragment implements BaseFragment.PopUpListener, ILocationStateListener, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener, GenericTextWatcher.iVehicle, IResponseSubcriber, magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber {
    Gson gson = new Gson();
    private static final String TAG = "AddNewQuoteActivity";
    TextView tvNew, tvRenew, tvOr;
    LinearLayout cvNcb;
    LinearLayout llNoClaim, llVerifyCarDetails, llDontKnow;
    DiscreteSeekBar sbNoClaimBonus;
    CardView cvNewRenew, cvRegNo, cvIndividual;
    View cvInput;
    Button btnGetQuote, btnGo;
    TextView tvDontKnow;
    EditText etreg1, etreg2, etreg3, etreg4;
    String regNo = "";
    Switch switchNewRenew;

    MotorRequestEntity motorRequestEntity;
    FastLaneDataEntity fastLaneResponseEntity;
    ConstantEntity constantEntity;
    UserConstantEntity userConstantEntity;
    LoginResponseEntity loginResponseEntity;

    //region inputs
    Spinner spFuel, spVarient, spPrevIns;
    TextInputLayout tilExt;
    EditText etExtValue, etRegDate, etMfgDate, etExpDate, etCustomerName, etMobile, etCC;
    AutoCompleteTextView acMakeModel, acRto;
    TextView tvCarNo, tvProgress, tvClaimYes, tvClaimNo;
    Switch swIndividual, swClaim;
    Spinner spNcbPercent;
    //endregion
    SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat policyBossDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat fastLaneDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    DBPersistanceController dbController;
    Realm realm;
    List<String> makeModelList, fuelList, variantList, cityList, prevInsurerList;
    ArrayAdapter<String> makeModelAdapter, varientAdapter, fuelAdapter, cityAdapter, prevInsAdapter, ncbPerctAdapter;
    String modelId, varientId;
    String regplace, makeModel = "";
    boolean isClaimExist = true;

    LocationTracker locationTracker;
    Location location;

    Spinner spMonth, spYear;
    ArrayAdapter<String> MonthAdapter, YearAdapter;
    ArrayList<String> yearList, monthList;
    View view;
    RadioGroup rgNewRenew, rgExpiry;
    RadioButton rbNew, rbReNew, rbExpired,
            rbDontHAve, rbWithIn, rbBeyond;

    Spinner spInsSubTYpe;
    //ArrayAdapter<String> subTypeAdapter;
    List<InsuranceSubtypeEntity> insuranceSubtypeEntities;
    ArrayAdapter<InsuranceSubtypeEntity> subTypeAdapter;

    boolean sendOldCrn = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.content_add_new_quote, container, false);

        //region init location
        locationTracker = new LocationTracker(getActivity());
        //location callback method
        locationTracker.setLocationStateListener(this);

        //GoogleApiClient initialisation and location update
        locationTracker.init();

        //GoogleApiclient connect
        locationTracker.onResume();
        //endregion

        dbController = new DBPersistanceController(getActivity());
        motorRequestEntity = new MotorRequestEntity(getActivity());

        constantEntity = dbController.getConstantsData();
        userConstantEntity = dbController.getUserConstantsData();
        loginResponseEntity = dbController.getUserData();

        registerPopUp(this);

        init_view(view);

        setListener();

        bind_init_binders();

        initialize_views();


        if (getArguments() != null) {
            if (getArguments().getParcelable(InputQuoteBottmActivity.MOTOR_INPUT_REQUEST) != null) {
                motorRequestEntity = getArguments().getParcelable(InputQuoteBottmActivity.MOTOR_INPUT_REQUEST);
                tvDontKnow.performClick();
                bindInputsQuotes();
            }
        }

        adapter_listeners();

        return view;
    }


    //region binding parameter

    private void bind_init_binders() {

        //fetching initial data

        cityList = dbController.getRTOListNames();
        makeModelList = dbController.getCarMakeModel();
        prevInsurerList = dbController.getInsurerList();
        fuelList = dbController.getFuelTypeByModelId("0");
        variantList = dbController.getVariantbyModelID("0");
        //region Autocomplete Make Model
        makeModelAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, makeModelList);
        acMakeModel.setAdapter(makeModelAdapter);
        acMakeModel.setThreshold(2);
        acMakeModel.setSelection(0);

        //endregion

        //region Autocomplete RTO
        cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cityList) {

        };
        acRto.setAdapter(cityAdapter);

        //endregion

        //region spinner Fuel

        //fuelList = new ArrayList<String>();
        fuelAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, fuelList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
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
                tv.setText(fuelList.get(position));
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(Constants.SPINNER_FONT_SIZE);
                return convertView;
            }
        };
        spFuel.setAdapter(fuelAdapter);

        //endregion

        //region spinner Varient

        // variantList = new ArrayList<String>();
        varientAdapter = new
                ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, variantList) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
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
                        tv.setText(variantList.get(position));
                        tv.setTextColor(Color.BLACK);
                        tv.setTextSize(Constants.SPINNER_FONT_SIZE);
                        return convertView;
                    }
                };
        spVarient.setAdapter(varientAdapter);

        //endregion

        // region prev insurer adapter
        prevInsAdapter = new
                ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prevInsurerList) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
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
                        tv.setText(prevInsurerList.get(position));
                        if (!spPrevIns.isEnabled()) {
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }

                        tv.setTextSize(Constants.SPINNER_FONT_SIZE);
                        return convertView;
                    }
                };
        spPrevIns.setAdapter(prevInsAdapter);

        spPrevIns.setSelection(0);

        //endregion

        // region ncb adapter
        ncbPerctAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.ncb_percent));
        spNcbPercent.setAdapter(ncbPerctAdapter);
        //endregion


        //region year adapter
        yearList = getYearList();
        YearAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, yearList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
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
                tv.setText(yearList.get(position));
                tv.setCompoundDrawablePadding(0);
                if (!spYear.isEnabled()) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }

                convertView.setPadding(8, convertView.getPaddingTop(), 0, convertView.getPaddingBottom());
                tv.setTextSize(12f);
                return convertView;
            }
        };
        spYear.setAdapter(YearAdapter);
        //endregion


        //region year adapter
        monthList = getMonthList(Calendar.getInstance().get(Calendar.MONTH));
        MonthAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, monthList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
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
                tv.setText(monthList.get(position));
                if (!spMonth.isEnabled()) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                convertView.setPadding(8, convertView.getPaddingTop(), 0, convertView.getPaddingBottom());
                tv.setTextColor(Color.BLACK);
                if (position == 0)
                    tv.setTextSize(10f);
                else
                    tv.setTextSize(12f);
                return convertView;
            }
        };
        spMonth.setAdapter(MonthAdapter);
        //endregion

    }


    private void bindInputsQuotes() {


        int vehicleID = motorRequestEntity.getVehicle_id();
        if (vehicleID == 0) {
            vehicleID = motorRequestEntity.getVarid();
        }
        if (motorRequestEntity != null && motorRequestEntity.getVehicle_insurance_type() != null) {
            if (motorRequestEntity.getVehicle_insurance_type().equals("renew")) {
                switchNewRenew.setChecked(true);
            } else if (motorRequestEntity.getVehicle_insurance_type().matches("new")) {
                switchNewRenew.setChecked(false);
            }
        }

        CarMasterEntity carMasterEntity = dbController.getVarientDetails(String.valueOf(vehicleID));
        if (carMasterEntity != null) {


            makeModel = carMasterEntity.getMake_Name() + " , " + carMasterEntity.getModel_Name();

            //region make model

            acMakeModel.setText(makeModel);

            //TODO: Dismiss the Drop down after auto complete set text
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    acMakeModel.dismissDropDown();
                }
            });

            //endregion

            //region varient list

            variantList.clear();
            List<String> varList = dbController.getVariant(carMasterEntity.getMake_Name(),
                    carMasterEntity.getModel_Name(),
                    carMasterEntity.getFuel_Name());
            variantList.addAll(varList);
            varientAdapter.notifyDataSetChanged();


            //endregion

            //region fuel list
            fuelList.clear();
            fuelList.addAll(dbController.getFuelTypeByModelId(carMasterEntity.getModel_ID()));
            fuelAdapter.notifyDataSetChanged();

            //endregion

            //region spinner selection

            int varientIndex = 0;
            for (int i = 0; i < variantList.size(); i++) {

                String variantName = carMasterEntity.getVariant_Name() + " (" + carMasterEntity.getCubic_Capacity() + "cc)";
                String vari = variantList.get(i);
                if (variantName.equalsIgnoreCase(vari)) {
                    varientIndex = i;
                    break;
                }
            }
            spVarient.setSelection(varientIndex);

            int fuelIndex = 0;
            if (motorRequestEntity.getExternal_bifuel_type() != null &&
                    motorRequestEntity.getExternal_bifuel_type().equalsIgnoreCase("")) {
                for (int i = 0; i < fuelList.size(); i++) {
                    if (fuelList.get(i).equalsIgnoreCase(carMasterEntity.getFuel_Name())) {
                        fuelIndex = i;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < fuelList.size(); i++) {

                    if (motorRequestEntity.getExternal_bifuel_type().equalsIgnoreCase("lpg") &&
                            fuelList.get(i).equalsIgnoreCase(DBPersistanceController.EXTERNAL_LPG)) {
                        fuelIndex = i;
                        break;
                    } else if (motorRequestEntity.getExternal_bifuel_type().equalsIgnoreCase("cng") &&
                            fuelList.get(i).equalsIgnoreCase(DBPersistanceController.EXTERNAL_CNG)) {
                        fuelIndex = i;
                        break;
                    }
                }
            }

            spFuel.setSelection(fuelIndex);


            if (motorRequestEntity.getVehicle_insurance_type().matches("renew")) {
                int prevInsurerIndex = 0;
                String insName = dbController.getInsurername(motorRequestEntity.getPrev_insurer_id());
                for (int i = 0; i < prevInsurerList.size(); i++) {
                    if (prevInsurerList.get(i).equalsIgnoreCase(insName)) {
                        prevInsurerIndex = i;
                        break;
                    }
                }
                spPrevIns.setSelection(prevInsurerIndex);
            }


            //endregion

            //region Rto binding

            acRto.setText(dbController.getRTOCityName(String.valueOf(motorRequestEntity.getRto_id())));
            acRto.performCompletion();
            regplace = acRto.getText().toString();

            //endregion

            //region subtype binding
            if (motorRequestEntity.getVehicle_insurance_subtype() != null && insuranceSubtypeEntities != null) {
                int subtypeId = 0;
                for (int i = 0; i < insuranceSubtypeEntities.size(); i++) {

                    String code = motorRequestEntity.getVehicle_insurance_subtype();
                    String vari = insuranceSubtypeEntities.get(i).getCode();
                    if (code.equalsIgnoreCase(vari)) {
                        subtypeId = i;
                        break;
                    }
                }
                spInsSubTYpe.setSelection(subtypeId);
            }
            //endregion

            if (motorRequestEntity.getExternal_bifuel_value() != 0)
                etExtValue.setText(String.valueOf(motorRequestEntity.getExternal_bifuel_value()));

            etCustomerName.setText(motorRequestEntity.getFirst_name() + " " + motorRequestEntity.getLast_name());

            etMobile.setText(motorRequestEntity.getMobile());


        }
        try {


            //commented by Nilesh 12.10.2018

            /*Date ManfDate = policyBossDateFormat.parse(motorRequestEntity.getVehicle_manf_date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ManfDate);
            setYearMonthAdapter(calendar);*/


            etRegDate.setText(getDisplayDateFormat(motorRequestEntity.getVehicle_registration_date()));

            etMfgDate.setText(getDisplayDateFormat(motorRequestEntity.getVehicle_manf_date()));


            //By Nilesh 12.10.2018
            Date regDate = policyBossDateFormat.parse(motorRequestEntity.getVehicle_registration_date());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(regDate);
            setYearMonthAdapter(calendar);


            if (!motorRequestEntity.getPolicy_expiry_date().equals("")) {
                etExpDate.setEnabled(true);
                etExpDate.setText(getDisplayDateFormat(motorRequestEntity.getPolicy_expiry_date()));
                spPrevIns.setEnabled(true);
            } else {
                etExpDate.setEnabled(false);
                //etExpDate.setText(getDisplayDateFormat(motorRequestEntity.getPolicy_expiry_date()));
                spPrevIns.setEnabled(false);
            }


            //etExpDate.setText(displayFormat.format(simpleDateFormat.parse(motorRequestEntity.getPolicy_expiry_date())));


            /*
            etRegDate.setText(simpleDateFormat.format(simpleDateFormat.parse(motorRequestEntity.getVehicle_registration_date())));

            etMfgDate.setText(simpleDateFormat.format(simpleDateFormat.parse(motorRequestEntity.getVehicle_manf_date())));

            etExpDate.setText(simpleDateFormat.format(simpleDateFormat.parse(motorRequestEntity.getPolicy_expiry_date())));*/
            if (motorRequestEntity.getIs_claim_exists().equals("no")) {
                int ncbPercent = 0;
                if (motorRequestEntity.getVehicle_ncb_current() != null && !motorRequestEntity.getVehicle_ncb_current().equals("")) {
                    ncbPercent = Integer.parseInt(motorRequestEntity.getVehicle_ncb_current());
                    setSeekbarProgress(ncbPercent);
                } else {
                    setSeekbarProgress(ncbPercent);
                }
                //setSeekbarProgress(getYearDiffForNCB(etRegDate.getText().toString(), etExpDate.getText().toString()));
            } else {
                tvClaimYes.performClick();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void bindFastLaneData(FastLaneDataEntity masterData) {

        insuranceSubtypeEntities = dbController.getInsuranceSubTypeList(1, "renew");
        /*subTypeAdapter = new ArrayAdapter<InsuranceSubtypeEntity>(getActivity(), android.R.layout.simple_list_item_1,
                insuranceSubtypeEntities);
        spInsSubTYpe.setAdapter(subTypeAdapter);*/
        setSubTypeAdapterView(insuranceSubtypeEntities);
        String vehicleID = masterData.getVariant_Id();
        CarMasterEntity carMasterEntity = dbController.getVarientDetails(vehicleID);
        if (carMasterEntity != null) {
            try {

                makeModel = carMasterEntity.getMake_Name() + " , " + carMasterEntity.getModel_Name();

                //region make model

                acMakeModel.setText(makeModel);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        acMakeModel.dismissDropDown();
                    }
                });


                //endregion

                //region varient list

                variantList.clear();
                List<String> varList = dbController.getVariant(carMasterEntity.getMake_Name(),
                        carMasterEntity.getModel_Name(),
                        carMasterEntity.getFuel_Name());
                variantList.addAll(varList);
                varientAdapter.notifyDataSetChanged();


                //endregion

                //region fuel list
                fuelList.clear();
                fuelList.addAll(dbController.getFuelTypeByModelId(carMasterEntity.getModel_ID()));
                fuelAdapter.notifyDataSetChanged();

                //endregion

                //region spinner selection

                int varientIndex = 0;
                for (int i = 0; i < variantList.size(); i++) {
                    String variantName = carMasterEntity.getVariant_Name() + " (" + carMasterEntity.getCubic_Capacity() + "cc)";

                    if (variantList.get(i).equalsIgnoreCase(variantName)) {
                        varientIndex = i;
                        break;
                    }
                }
                spVarient.setSelection(varientIndex);

                int fuelIndex = 0;
                for (int i = 0; i < fuelList.size(); i++) {
                    if (fuelList.get(i).equalsIgnoreCase(carMasterEntity.getFuel_Name())) {
                        fuelIndex = i;
                        break;
                    }
                }
                spFuel.setSelection(fuelIndex);

                //endregion

                //region Rto binding

                acRto.setText(dbController.getRTOCityName(String.valueOf(masterData.getRTO_Code())));
                acRto.performCompletion();
                regplace = acRto.getText().toString();

                //endregion

                Calendar calendarReg = Calendar.getInstance();
                if (masterData.getRegistration_Date() != null) {
                    calendarReg.setTime(fastLaneDateFormat.parse(masterData.getRegistration_Date()));
                    etRegDate.setText(getDisplayDateFormatFastLane(masterData.getRegistration_Date()));


                    //String reg = changeDateFormat(masterData.getRegistration_Date());
                    //String regDate = displayFormat.format(simpleDateFormat.parse(reg));
                    //calendarReg.setTime(displayFormat.parse(regDate));
                    //etRegDate.setText(changeDateFormat(masterData.getRegistration_Date()));
                }

                if (masterData.getManufacture_Year() != null) {
                    String mfDate = "";
                    int month = calendarReg.get(Calendar.MONTH) + 1;
                    if (month <= 9)
                        mfDate = masterData.getManufacture_Year() + "-0" + month + "-01";
                    else
                        mfDate = masterData.getManufacture_Year() + "-" + month + "-01";
                    calendarReg.setTime(policyBossDateFormat.parse(mfDate));

                    //By Nilesh 12.10.2018
                    setYearMonthAdapter(calendarReg, calendarReg.get(Calendar.YEAR));

                    // setYearMonthAdapterFastlane(calendarReg);
                } else {
                    setYearMonthAdapterFastlane(Calendar.getInstance());
                }

                /*if (masterData.getPurchase_Date() != null) {
                    String mf = changeDateFormat(masterData.getPurchase_Date());
                    String mfDate = displayFormat.format(simpleDateFormat.parse(mf));
                    etMfgDate.setText(mfDate);
                    //etMfgDate.setText(getManufacturingDate(changeDateFormat(masterData.getPurchase_Date())));
                } else {
                    String mf = changeDateFormat(masterData.getRegistration_Date());
                    String mfDate = displayFormat.format(simpleDateFormat.parse(mf));
                    etMfgDate.setText(mfDate);
                    //etMfgDate.setText(getManufacturingDate(changeDateFormat(masterData.getRegistration_Date())));
                }*/
                // etCC.setText("" + masterData.getCubic_Capacity() + "CC");
                etExpDate.setEnabled(true);

                etCC.setText(carMasterEntity.getCubic_Capacity() + "CC");
                //  setSeekbarProgress(getYearDiffForNCB(etRegDate.getText().toString(), etExpDate.getText().toString()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //endregion


    }

    //endregion

    private void adapter_listeners() {

        //region make model

        acMakeModel.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Constants.hideKeyBoard(acMakeModel, getActivity());
                makeModel = makeModelAdapter.getItem(position).toString();

                modelId = dbController.getModelID(getMake(acMakeModel.getText().toString()), getModel(acMakeModel.getText().toString()));
                acMakeModel.setSelection(0);

                if (modelId != "") {
                    fuelList.clear();
                    fuelList.addAll(dbController.getFuelTypeByModelId(modelId));
                    fuelAdapter.notifyDataSetChanged();
                    spFuel.setSelection(0);
                    spVarient.setSelection(0);
                    etCC.setText("");

//                    variantList.clear();
//                    variantList.addAll(dbController.getVariantbyModelID(modelId));
//                    varientAdapter.notifyDataSetChanged();
                } else {
                    acMakeModel.requestFocus();
                    acMakeModel.setError("Enter Make,Model");
                    return;
                }

            }
        });


        /*spFuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (fuelList.get(position).equals(Constants.EXTERNAL_LPG)
                        || fuelList.get(position).equals(Constants.EXTERNAL_CNG)) {
                    etExtValue.setEnabled(true);
                } else {
                    etExtValue.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                etExtValue.setEnabled(false);
            }
        });*/
        //endregion

        //region cubic capacity
        spVarient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (fastLaneResponseEntity == null && spVarient.getSelectedItemPosition() != 0) {
                    //etCC.setText("" + dbController.getVarientCC(getMake(acMakeModel.getText().toString()), getModel(acMakeModel.getText().toString()), spVarient.getSelectedItem().toString()));
                    String strVarient = getVarient(spVarient.getSelectedItem().toString());
                    varientId = dbController.getVariantID(strVarient, getModel(acMakeModel.getText().toString()), getMake(acMakeModel.getText().toString()));

                    if (varientId != null && !varientId.equals("")) {
                        if (motorRequestEntity != null && motorRequestEntity.getVehicle_id() != 0 &&
                                motorRequestEntity.getVehicle_id() != Integer.parseInt(varientId)) {
                            sendOldCrn = false;
                        }
                        motorRequestEntity.setVehicle_id(Integer.parseInt(varientId));
                    }

                    /*if (varientId != null && !varientId.equals(""))
                        motorRequestEntity.setVehicle_id(Integer.parseInt(varientId));*/

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //endregion

        // region city adapter

        acRto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                regplace = cityAdapter.getItem(position).toString();
                Constants.hideKeyBoard(acRto, getActivity());
                acRto.setSelection(0);
                if (regplace != null && !regplace.equals("") && motorRequestEntity != null) {
                    if (motorRequestEntity.getRto_id() != getCityId(acRto.getText().toString())) {
                        sendOldCrn = false;
                    }
                }
            }

        });

        //endregion

        //region fuel adapter

        spFuel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                if (fuelList.get(pos).equals(DBPersistanceController.EXTERNAL_LPG)
                        || fuelList.get(pos).equals(DBPersistanceController.EXTERNAL_CNG)) {
                    etExtValue.setEnabled(true);
                    tilExt.setVisibility(View.VISIBLE);
                } else {
                    tilExt.setVisibility(View.GONE);
                    etExtValue.setText("");
                    etExtValue.setEnabled(false);
                    acMakeModel.requestFocus();
                }

                variantList.clear();
                List<String> varList = dbController.getVariant(getMake(acMakeModel.getText().toString()),
                        getModel(acMakeModel.getText().toString()),
                        spFuel.getSelectedItem().toString());
                variantList.addAll(varList);
                varientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //endregion

        // region year adapter

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (motorRequestEntity != null) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(policyBossDateFormat.parse(motorRequestEntity.getVehicle_registration_date()));
                        if (calendar.get(Calendar.YEAR) != Integer.parseInt(spYear.getSelectedItem().toString())) {
                            sendOldCrn = false;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (position == 1) {
                    int selectedMonth = spMonth.getSelectedItemPosition();
                    try {
                        Date Reg = displayFormat.parse(etRegDate.getText().toString());
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(Reg);
                        monthList.clear();
                        monthList.addAll(getMonthList(calendar.get(Calendar.MONTH)));
                        MonthAdapter.notifyDataSetChanged();
                        spMonth.setSelection(selectedMonth);
                    } catch (ParseException e) {
                        monthList.clear();
                        monthList.addAll(getMonthList(Calendar.getInstance().get(Calendar.MONTH)));
                        MonthAdapter.notifyDataSetChanged();
                        spMonth.setSelection(selectedMonth);
                        e.printStackTrace();
                    }

                } else {
                    int selectedMonth = spMonth.getSelectedItemPosition();
                    monthList.clear();
                    monthList.addAll(getMonthList(12));
                    MonthAdapter.notifyDataSetChanged();
                    spMonth.setSelection(selectedMonth);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //endregion

        //region month adapter
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (motorRequestEntity != null) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(policyBossDateFormat.parse(motorRequestEntity.getVehicle_registration_date()));
                        if (calendar.get(Calendar.MONTH) != getMonthInt(spMonth.getSelectedItem().toString())) {
                            sendOldCrn = false;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //endregion

        //region subtype adapter listener
        spInsSubTYpe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //((TextView) parent.getChildAt(0)).setTextSize(10);
                InsuranceSubtypeEntity insuranceSubtypeEntity = (InsuranceSubtypeEntity) spInsSubTYpe.getSelectedItem();
                if (switchNewRenew.isChecked()) {
                    if (insuranceSubtypeEntity.getCode().equals("0CH_1TP")) {
                        cvNcb.setVisibility(View.GONE);
                        llNoClaim.setVisibility(View.INVISIBLE);
                        tvClaimYes.performClick();
                    } else {
                        cvNcb.setVisibility(View.VISIBLE);
                        llNoClaim.setVisibility(View.VISIBLE);
                        setNcb();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //endregion
    }

    private void setNcb() {
        if (motorRequestEntity != null && motorRequestEntity.getIs_claim_exists() != null) {
            if (motorRequestEntity.getIs_claim_exists().equals("no")) {
                int ncbPercent = 0;
                if (motorRequestEntity.getVehicle_ncb_current() != null && !motorRequestEntity.getVehicle_ncb_current().equals("")) {
                    ncbPercent = Integer.parseInt(motorRequestEntity.getVehicle_ncb_current());
                    setSeekbarProgress(ncbPercent);
                } else {
                    setSeekbarProgress(ncbPercent);
                }
                //setSeekbarProgress(getYearDiffForNCB(etRegDate.getText().toString(), etExpDate.getText().toString()));
            } else if (motorRequestEntity.getIs_claim_exists().equals("yes")) {
                tvClaimYes.performClick();
            }
        }
    }

    private void initialize_views() {
        setSubTypeAdapter();
        cvInput.setVisibility(View.GONE);
        switchNewRenew.setChecked(true);
        tvClaimNo.performClick();
        llVerifyCarDetails.setVisibility(View.GONE);

        spPrevIns.setEnabled(false);
        tilExt.setVisibility(View.GONE);


    }

    private void setSubTypeAdapter() {
        if (switchNewRenew.isChecked()) {
            insuranceSubtypeEntities = dbController.getInsuranceSubTypeList(1, "renew");
            /*subTypeAdapter = new ArrayAdapter<InsuranceSubtypeEntity>(getActivity(), android.R.layout.simple_list_item_1,
                    insuranceSubtypeEntities);
            spInsSubTYpe.setAdapter(subTypeAdapter);*/
            setSubTypeAdapterView(insuranceSubtypeEntities);
        } else {
            insuranceSubtypeEntities = dbController.getInsuranceSubTypeList(1, "new");
            /*subTypeAdapter = new ArrayAdapter<InsuranceSubtypeEntity>(getActivity(), android.R.layout.simple_list_item_1,
                    insuranceSubtypeEntities);
            spInsSubTYpe.setAdapter(subTypeAdapter);*/
            setSubTypeAdapterView(insuranceSubtypeEntities);
        }
    }

    private void setSubTypeAdapterView(List<InsuranceSubtypeEntity> insuranceSubtypeEntities) {
        subTypeAdapter = new ArrayAdapter<InsuranceSubtypeEntity>(getActivity(), android.R.layout.simple_list_item_1,
                insuranceSubtypeEntities) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setPadding(2, view.getPaddingTop(), 0, view.getPaddingBottom());
                return view;
            }
        };
        spInsSubTYpe.setAdapter(subTypeAdapter);
    }

    public int getPercentFromProgress(int value) {
        switch (value) {
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
        }
        return 0;
    }

    public int getProgressFromPercent(int value) {
        switch (value) {
            case 0:
                return 0;
            case 20:
                return 1;
            case 25:
                return 2;
            case 35:
                return 3;
            case 45:
                return 4;
            case 50:
                return 5;
        }
        return 0;
    }

    private void setListener() {
        rgExpiry.setOnCheckedChangeListener(this);
        rgNewRenew.setOnCheckedChangeListener(this);
        btnGo.setOnClickListener(this);
        switchNewRenew.setOnCheckedChangeListener(this);
        swIndividual.setOnCheckedChangeListener(this);
        tvClaimYes.setOnClickListener(this);
        tvClaimNo.setOnClickListener(this);
        btnGetQuote.setOnClickListener(this);
        tvDontKnow.setOnClickListener(this);
        etreg1.addTextChangedListener(new GenericTextWatcher(etreg1, this));
        etreg2.addTextChangedListener(new GenericTextWatcher(etreg2, this));
        etreg3.addTextChangedListener(new GenericTextWatcher(etreg1, etreg3, this));
        etreg4.addTextChangedListener(new GenericTextWatcher(etreg4, this));
//        acMakeModel.addTextChangedListener(new GenericTextWatcher(acMakeModel, this));
//        acRto.addTextChangedListener(new GenericTextWatcher(acRto, this));
        etRegDate.setOnClickListener(datePickerDialog);
        etMfgDate.setOnClickListener(datePickerDialog);
        etExpDate.setOnClickListener(datePickerDialog);

        acRto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String str = acRto.getText().toString();

                ListAdapter listAdapter = acRto.getAdapter();
                for (int i = 0; i < listAdapter.getCount(); i++) {
                    String temp = listAdapter.getItem(i).toString();
                    if (str.compareTo(temp) == 0) {
                        acRto.setError(null);
                        return;
                    }
                }

                acRto.setError("Invalid RTO");
                acRto.setFocusable(true);
                regplace = "";
            }
        });
        // acRto.setOnFocusChangeListener(acRTOFocusChange);

        sbNoClaimBonus.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return getPercentFromProgress(value);
            }
        });
        sbNoClaimBonus.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                if (fromUser) {
                    String ncbBold = "(" + String.valueOf(getPercentFromProgress(value)) + "%)";
                    SpannableString ss1 = new SpannableString(ncbBold);
                    ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
                    String normalText = "Existing NCB ";
                    tvProgress.setText("");
                    tvProgress.append(normalText);
                    tvProgress.append(ss1);
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


    private void init_view(View view) {
        spInsSubTYpe = view.findViewById(R.id.spInsSubTYpe);
        tilExt = (TextInputLayout) view.findViewById(R.id.tilExt);
        btnGo = (Button) view.findViewById(R.id.btnGo);
        tvNew = (TextView) view.findViewById(R.id.tvNew);
        tvRenew = (TextView) view.findViewById(R.id.tvRenew);
        llVerifyCarDetails = (LinearLayout) view.findViewById(R.id.llVerifyCarDetails);
        cvNcb = (LinearLayout) view.findViewById(R.id.cvNcb);
        llNoClaim = (LinearLayout) view.findViewById(R.id.llNoClaim);
        cvNewRenew = (CardView) view.findViewById(R.id.cvNewRenew);
        cvIndividual = (CardView) view.findViewById(R.id.cvIndividual);
        cvRegNo = (CardView) view.findViewById(R.id.cvRegNo);
        tvOr = (TextView) view.findViewById(R.id.tvOr);
        llDontKnow = (LinearLayout) view.findViewById(R.id.llDontKnow);
        cvInput = (View) view.findViewById(R.id.cvInput);
        btnGetQuote = (Button) view.findViewById(R.id.btnGetQuote);
        tvDontKnow = (TextView) view.findViewById(R.id.tvDontKnow);
        tvProgress = (TextView) view.findViewById(R.id.tvProgress);
        tvClaimNo = (TextView) view.findViewById(R.id.tvClaimNo);
        tvClaimYes = (TextView) view.findViewById(R.id.tvClaimYes);
        etCC = (EditText) view.findViewById(R.id.etCC);


        etreg1 = (EditText) view.findViewById(R.id.etreg1);
        etreg1.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(2)});
        etreg2 = (EditText) view.findViewById(R.id.etreg2);
        etreg2.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(2)});
        etreg3 = (EditText) view.findViewById(R.id.etreg3);
        etreg3.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});
        etreg4 = (EditText) view.findViewById(R.id.etreg4);
        etreg4.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(4)});

        switchNewRenew = (Switch) view.findViewById(R.id.switchNewRenew);

        //region init views
        spFuel = (Spinner) view.findViewById(R.id.spFuel);
        spVarient = (Spinner) view.findViewById(R.id.spVarient);
        spPrevIns = (Spinner) view.findViewById(R.id.spPrevIns);
        etExtValue = (EditText) view.findViewById(R.id.etExtValue);
        etRegDate = (EditText) view.findViewById(R.id.etRegDate);
        etMfgDate = (EditText) view.findViewById(R.id.etMfgDate);
        etExpDate = (EditText) view.findViewById(R.id.etExpDate);
        etCustomerName = (EditText) view.findViewById(R.id.etCustomerName);
        // etCustomerName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        acMakeModel = (AutoCompleteTextView) view.findViewById(R.id.acMakeModel);
        acRto = (AutoCompleteTextView) view.findViewById(R.id.acRto);
        tvCarNo = (TextView) view.findViewById(R.id.tvCarNo);
        swIndividual = (Switch) view.findViewById(R.id.swIndividual);
        swClaim = (Switch) view.findViewById(R.id.switchNcb);
        spNcbPercent = (Spinner) view.findViewById(R.id.spNcbPercent);
        //endregion

        sbNoClaimBonus = (DiscreteSeekBar) view.findViewById(R.id.sbNoClaimBonus);

        spMonth = (Spinner) view.findViewById(R.id.spMonth);
        spYear = (Spinner) view.findViewById(R.id.spYear);


        rgNewRenew = view.findViewById(R.id.rgNewRenew);
        rgExpiry = view.findViewById(R.id.rgExpiry);

        rbNew = view.findViewById(R.id.rbNew);
        rbReNew = view.findViewById(R.id.rbReNew);
        rbExpired = view.findViewById(R.id.rbExpired);
        rbDontHAve = view.findViewById(R.id.rbDontHAve);
        rbWithIn = view.findViewById(R.id.rbWithIn);
        rbBeyond = view.findViewById(R.id.rbBeyond);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btnGo) {
            if (etreg1.getText().toString().equals("")) {
                etreg1.requestFocus();
                etreg1.setError("Invalid vehicle Number");
                return;
            }
            if (etreg2.getText().toString().equals("")) {
                etreg2.requestFocus();
                etreg2.setError("Invalid vehicle Number");
                return;
            }
            if (etreg3.getText().toString().equals("")) {
                etreg3.requestFocus();
                etreg3.setError("Invalid vehicle Number");
                return;
            }
            if (etreg4.getText().toString().equals("")) {
                etreg4.requestFocus();
                etreg4.setError("Invalid vehicle Number");
                return;
            }

            regNo = etreg1.getText().toString() + etreg2.getText().toString()
                    + etreg3.getText().toString() + etreg4.getText().toString();
            if (!regNo.equals("")) {
                llVerifyCarDetails.setVisibility(View.VISIBLE);
                tvCarNo.setText("" + regNo);
                Constants.hideKeyBoard(etreg4, getActivity());
                tvDontKnow.performClick();
                btnGetQuote.setVisibility(View.VISIBLE);
                showDialog("Fetching car details...");
                insertFastlaneLog();

                motorRequestEntity.setRegistration_no(getFormattedRegNoFastlane());

                new FastLaneController(getActivity()).getVechileDetails(regNo, this);
            }

        } else if (i == R.id.tvClaimNo) {
            isClaimExist = false;
            tvClaimNo.setBackgroundResource(R.drawable.customeborder_blue);
            tvClaimYes.setBackgroundResource(R.drawable.customeborder);
            cvNcb.setVisibility(View.VISIBLE);
            sbNoClaimBonus.setEnabled(true);

        } else if (i == R.id.tvClaimYes) {
            isClaimExist = true;
            tvClaimNo.setBackgroundResource(R.drawable.customeborder);
            tvClaimYes.setBackgroundResource(R.drawable.customeborder_blue);
            cvNcb.setVisibility(View.GONE);
            sbNoClaimBonus.setEnabled(false);
            tvProgress.setText("Existing NCB");
            sbNoClaimBonus.setProgress(0);

        } else if (i == R.id.btnGetQuote) {
            if (isValidInfo()) {
                setCommonParameters();
                if (switchNewRenew.isChecked()) {  //renew
                    setInputParametersReNewCar();
                } else {
                    setInputParametersNewCAR();
                }
                if (constantEntity.getLogtracking().equals("0"))
                    new PolicybossTrackingRequest(motorRequestEntity).execute();

                MyApplication.getInstance().trackEvent(Constants.PRIVATE_CAR, "GET QUOTE MOTOR", "GET QUOTE MOTOR");
                showDialog(getResources().getString(R.string.fetching_msg));
                new MotorController(getActivity()).getMotorPremiumInitiate(motorRequestEntity, this);
            }


        } else if (i == R.id.tvDontKnow) {
            cvInput.setVisibility(View.VISIBLE);
            cvNewRenew.setVisibility(View.GONE);
            cvIndividual.setVisibility(View.GONE);
            cvRegNo.setVisibility(View.GONE);
            tvOr.setVisibility(View.GONE);
            llDontKnow.setVisibility(View.GONE);
            btnGetQuote.setVisibility(View.VISIBLE);

        }
    }

    private void setCommonParameters() {

        motorRequestEntity.setSecret_key(Utility.SECRET_KEY);
        motorRequestEntity.setClient_key(Utility.CLIENT_KEY);
        motorRequestEntity.setApp_version(Utility.getVersionName(getActivity()));
        motorRequestEntity.setDevice_id(Utility.getTokenId(getActivity()));
        motorRequestEntity.setFba_id(loginResponseEntity.getFBAId());
        try {
            motorRequestEntity.setMac_address(Utility.getMacAddress(getActivity()));
        } catch (IOException e) {
            motorRequestEntity.setMac_address("0");
        }

        if (userConstantEntity.getPospsendid() != null && !userConstantEntity.getPospsendid().equals("")) {
            int ssid = Integer.parseInt(userConstantEntity.getPospsendid());
            motorRequestEntity.setSs_id(ssid);
        } else {
            motorRequestEntity.setSs_id(5);
        }
        motorRequestEntity.setIp_address(Utility.getLocalIpAddress(getActivity()));
        InsuranceSubtypeEntity insuranceSubtypeEntity = (InsuranceSubtypeEntity) spInsSubTYpe.getSelectedItem();
        if (insuranceSubtypeEntity != null)
            motorRequestEntity.setVehicle_insurance_subtype("" + insuranceSubtypeEntity.getCode());
    }


    private void insertFastlaneLog() {
        try {
            if (constantEntity.getLogtracking().equals("0"))
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Motor Fastlane :  " + regNo), Constants.FASTLANE), null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidInfo() {


        //region validations
        if (makeModel == null || makeModel.equals("")) {
            acMakeModel.requestFocus();
            acMakeModel.setError("Enter Make,Model");
            return false;
        }

        if (!isEmpty(etRegDate)) {
            etRegDate.requestFocus();
            etRegDate.setError("Enter Reg Date");
            return false;
        }
                /*if (!isEmpty(etMfgDate)) {
                    etMfgDate.requestFocus();
                    etMfgDate.setError("Enter Mfg Date");
                    return false;
                }*/
        if (spYear.getSelectedItemPosition() == 0) {
            spYear.requestFocus();
            Toast.makeText(getActivity(), "Select Mfg Year", Toast.LENGTH_SHORT).show();
        }

        if (spMonth.getSelectedItemPosition() == 0) {
            spYear.requestFocus();
            Toast.makeText(getActivity(), "Select Mfg Month", Toast.LENGTH_SHORT).show();
        }

        if (regplace == null || regplace.equals("")) {
            acRto.requestFocus();
            acRto.setError("Enter Rto");
            return false;
        }


        if (switchNewRenew.isChecked()) {
            if (!isEmpty(etExpDate)) {
                etExpDate.requestFocus();
                etExpDate.setError("Enter Expiry Date");
                return false;
            }
            if (spPrevIns.getSelectedItemPosition() == 0) {
                spPrevIns.requestFocus();
                Toast.makeText(getActivity(), "Select Present Insurer", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (etCustomerName.getText().toString().equals("")) {
            etCustomerName.requestFocus();
            etCustomerName.setError("Enter Name");
            return false;
        } else {
            String[] fullName = etCustomerName.getText().toString().trim().split(" ");
            if (fullName.length == 1) {
                        /*if (fullName[0].length() < 2) {
                            etCustomerName.requestFocus();
                            etCustomerName.setError("First Name should be greater than 1 character");
                            return false;
                        }*/
                etCustomerName.requestFocus();
                etCustomerName.setError("Enter Last Name");
                return false;
            } else if (fullName.length == 2) {
                if (fullName[0].length() < 2) {
                    etCustomerName.requestFocus();
                    etCustomerName.setError("First Name should be greater than 1 character");
                    return false;
                }
                if (fullName[1].length() < 2) {
                    etCustomerName.requestFocus();
                    etCustomerName.setError("Last Name should be greater than 1 character");
                    return false;
                }
            } else if (fullName.length == 3) {
                if (fullName[0].length() < 2) {
                    etCustomerName.requestFocus();
                    etCustomerName.setError("First Name should be greater than 1 character");
                    return false;
                }
                if (fullName[2].length() < 2) {
                    etCustomerName.requestFocus();
                    etCustomerName.setError("Last Name should be greater than 1 character");
                    return false;
                }
            }

        }
                /*if (!isValidePhoneNumber(etMobile)) {
                    etMobile.requestFocus();
                    etMobile.setError("Enter Mobile");
                    return false;
                }*/

        if (spFuel.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "Select Fuel Type", Toast.LENGTH_SHORT).show();
            spFuel.requestFocus();
            return false;
        }

        if (spVarient.getSelectedItemPosition() == 0) {
            Toast.makeText(getActivity(), "Select Variant", Toast.LENGTH_SHORT).show();
            spVarient.requestFocus();
            return false;
        }

        if (dbController.getVariantID(getVarient(spVarient.getSelectedItem().toString()),
                getModel(acMakeModel.getText().toString()),
                getMake(acMakeModel.getText().toString())) == "") {
            acMakeModel.requestFocus();
            acMakeModel.setError("Enter Make,Model");
            return false;
        }
                /* if (dbController.getCityID(getRtoCity(acRto.getText().toString())) == "") {
                    acRto.requestFocus();
                    acRto.setError("Enter Rto");
                    return false;
                }*/


        if (getCityId(acRto.getText().toString()) == 0) {
            acRto.requestFocus();
            acRto.setError("Enter Rto");
            return false;
        }


        if (spFuel.getSelectedItem().toString().equals(DBPersistanceController.EXTERNAL_LPG)
                || spFuel.getSelectedItem().toString().equals(DBPersistanceController.EXTERNAL_CNG)) {
            if (etExtValue.getText().toString().equals("")) {
                etExtValue.requestFocus();
                etExtValue.setError("Enter Amount");
                return false;
            } else {
                int extval = Integer.parseInt(etExtValue.getText().toString());
                if (extval < 10000 || extval > 60000) {
                    etExtValue.requestFocus();
                    etExtValue.setError("Enter Amount between 10000 & 60000");
                    return false;
                }
            }
        }

        //endregion
        return true;
    }

    public void getQuote() {
        if (cvInput.getVisibility() == View.VISIBLE) {
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Motor Get quote : get quote button for motor "), Constants.PRIVATE_CAR), null);
            btnGetQuote.performClick();
        }

    }

    @Override
    public void getVehicleNumber(View view, String vehicleNo) {
        int i = view.getId();
        if (i == R.id.etreg1) {
            regNo = etreg1.getText().toString();
            etreg2.requestFocus();

        } else if (i == R.id.etreg2) {
            regNo = etreg1.getText().toString() + etreg2.getText().toString();
            etreg3.requestFocus();

        } else if (i == R.id.etreg3) {
            regNo = etreg1.getText().toString() + etreg2.getText().toString() + etreg3.getText().toString();
            etreg4.requestFocus();

        } else if (i == R.id.etreg4) {//regNo += etreg4.getText().toString();
            regNo = etreg1.getText().toString() + etreg2.getText().toString()
                    + etreg3.getText().toString() + etreg4.getText().toString();
            tvCarNo.setText(etreg1.getText().toString() + " " + etreg2.getText().toString()
                    + " " + etreg3.getText().toString() + " " + etreg4.getText().toString());
                /*Constants.hideKeyBoard(etreg4, getActivity());
                tvDontKnow.performClick();
                btnGetQuote.setVisibility(View.VISIBLE);
                showDialog("Fetching Car Details...");
                new FastLaneController(getActivity()).getVechileDetails(regNo, this);*/

        }
    }

    @Override
    public void cancelVehicleNumber(View view) {
        int i = view.getId();
        if (i == R.id.etreg1) {
        } else if (i == R.id.etreg2) {
            etreg1.requestFocus();

        } else if (i == R.id.etreg3) {
            etreg2.requestFocus();

        } else if (i == R.id.etreg4) {
            etreg3.requestFocus();

        } else if (i == R.id.acRto) {//regplace = null;

        } else if (i == R.id.acMakeModel) {// makeModel = null;

        }
    }

    //region imp function

    public ArrayList<String> getYearList() {
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH);
        ArrayList yearList = new ArrayList();
        yearList.add("Year");
        for (int i = 0; i <= 15; i++) {
            yearList.add("" + (currYear - i));
        }
        return yearList;
    }

    public ArrayList<String> getYearList(int minYear) {
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        ArrayList yearList = new ArrayList();
        yearList.add("Year");
        //todo: uncomment to revert
        /*for (int i = 0; i <= 15 - (currYear - minYear); i++) {
            yearList.add("" + (minYear - i));
        }*/

        //todo : uncomment this for new changes by modi
        for (int i = 0; i <= 1; i++) {
            yearList.add("" + (minYear - i));
        }
        return yearList;
    }

    public ArrayList<String> getYearList(int minYear, int maxYear) {
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        ArrayList yearList = new ArrayList();
        yearList.add("Year");
        yearList.add("" + (minYear - 0));
        yearList.add("" + (minYear - 1));
        /*for (int i = 0; i <= 15 - (currYear - minYear); i++) {
            yearList.add("" + (minYear - i));
        }*/
        return yearList;
    }

    public ArrayList<String> getMonthList(int currMonth) {
        ArrayList monthList = new ArrayList();
        monthList.add("Month");
        for (int i = 1; i <= currMonth + 1; i++) {
            switch (i) {
                case 1:
                    monthList.add("JAN");
                    break;
                case 2:
                    monthList.add("FEB");
                    break;
                case 3:
                    monthList.add("MAR");
                    break;
                case 4:
                    monthList.add("APR");
                    break;
                case 5:
                    monthList.add("MAY");
                    break;
                case 6:
                    monthList.add("JUN");
                    break;
                case 7:
                    monthList.add("JUL");
                    break;
                case 8:
                    monthList.add("AUG");
                    break;
                case 9:
                    monthList.add("SEP");
                    break;
                case 10:
                    monthList.add("OCT");
                    break;
                case 11:
                    monthList.add("NOV");
                    break;
                case 12:
                    monthList.add("DEC");
                    break;
            }
        }
        return monthList;
    }

    public int getMonthInt(String currMonth) {


        switch (currMonth) {
            case "JAN":
                return 0;
            case "FEB":
                return 1;

            case "MAR":
                return 2;

            case "APR":
                return 3;

            case "MAY":
                return 4;

            case "JUN":
                return 5;

            case "JUL":
                return 6;

            case "AUG":
                return 7;

            case "SEP":
                return 8;

            case "OCT":
                return 9;

            case "NOV":
                return 10;

            case "DEC":
                return 11;

        }

        return 0;
    }

    public String getModel(String makeModel) {
        String[] parts = makeModel.split(",");
        if (parts.length == 2)
            return parts[1];
        else return "";
    }

    public String getMake(String makeModel) {
        String[] parts = makeModel.split(",");
        if (parts.length == 2)
            return parts[0];
        else return "";
    }

    public String getVarient(String str) {
        int i = str.indexOf('(');
        int k = str.indexOf('(');
        while (k >= 0) {
            k = str.indexOf('(', k + 1);
            if (k == -1)
                k = -1;
            else
                i = k;
        }
        return str.substring(0, i).trim();
    }

    private int getMonth(String date) {
        String mon = "" + date.charAt(5) + date.charAt(6);
        return Integer.parseInt(mon);
    }

    private int getYear(String date) {
        String year = "" + date.charAt(0) + date.charAt(1) + date.charAt(2) + date.charAt(3);
        return Integer.parseInt(year);
    }

    private int getDate(String date) {
        String dat = "" + date.charAt(8) + date.charAt(9);
        return Integer.parseInt(dat);
    }

    private int getYearDiffForNCB(String firstDay, String lastDay) {
        try {
            return getDiffYears(displayFormat.parse(firstDay), displayFormat.parse(lastDay));
            //return getDiffYears(simpleDateFormat.parse(firstDay), simpleDateFormat.parse(lastDay));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String getRegistrationNo() {
        //CityMasterEntity cityMasterEntity = dbController.getVehicleCity_Id(city);
        return formatRegistrationNo(getRtoCode(acRto.getText().toString()) + "ZZ9999");
    }

    private String formatRegistrationNo(String regNo) {
        if (regNo.length() == 10) {
            return "" + regNo.charAt(0) + regNo.charAt(1) + "-"
                    + regNo.charAt(2) + regNo.charAt(3) + "-"
                    + regNo.charAt(4) + regNo.charAt(5) + "-"
                    + regNo.charAt(6) + regNo.charAt(7) + regNo.charAt(8) + regNo.charAt(9);
        } else {
            return "MH-01-ZZ-9999";
        }

    }

    public String getFormattedRegNoFastlane() {
        return etreg1.getText().toString() + "-"
                + etreg2.getText().toString() + "-"
                + etreg3.getText().toString() + "-"
                + etreg4.getText().toString();
    }

    private String getManufacturingDate(String manufac) {
        //final Calendar calendar = Calendar.getInstance();
        //01-04-2017
        return "01" + manufac.charAt(2) + manufac.charAt(3) + manufac.charAt(4) + manufac.charAt(5) + manufac.charAt(6) + manufac.charAt(7) + manufac.charAt(8) + manufac.charAt(9);
        //return  manufac.charAt(0) + manufac.charAt(1) + manufac.charAt(2) + manufac.charAt(3) + manufac.charAt(4) + manufac.charAt(5) + manufac.charAt(6) + manufac.charAt(7) + "01";
        //return manufac + "-" + calendar.getTime().getMonth() + "-" + calendar.getTime().getDate();

    }

    private String getRtoCity(String city) {
        String[] parts = city.split("-");
        if (parts.length > 2) {
            String s = parts[1].trim();
            for (int i = 2; i < parts.length; i++) {
                s = s + "-" + parts[i].trim();
            }
            return s;
        } else {
            return parts[1].trim();
        }
    }

    private String getRtoCode(String city) {
        String[] parts = city.split("-");
        return parts[0].trim();
    }

    private int getCityId(String city) {
        int cityId = 0;
        String cityCode = "";
        String[] parts = city.split("-");
        if (parts.length > 2) {
            String s = parts[1].trim();
            for (int i = 2; i < parts.length; i++) {
                s = s + "-" + parts[i].trim();
            }
            cityCode = dbController.getCityID(s, parts[0].trim());
            if (!cityCode.equals(""))
                cityId = Integer.parseInt(cityCode);

        } else {
            cityCode = dbController.getCityID(parts[1].trim(), parts[0].trim());
            if (!cityCode.equals(""))
                cityId = Integer.parseInt(cityCode);
            return cityId;

        }
        return cityId;

    }
    //endregion

    //region date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, getActivity());

            //region regdate
            if (view.getId() == R.id.etRegDate) {
                if (switchNewRenew.isChecked()) {
                    //region  regdate renew
                    DateTimePicker.invoiceReNewValidation(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                            if (view1.isShown()) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String currentDay = displayFormat.format(calendar.getTime());
                                etRegDate.setText(currentDay);
                                etRegDate.setError(null);
                                calendar.set(year, monthOfYear, 01);
                                String currentDay1 = displayFormat.format(calendar.getTime());
                                etMfgDate.setText(currentDay1);
                                setYearMonthAdapter(calendar, calendar.get(Calendar.YEAR));

                                etRegDate.setTag(R.id.etRegDate, calendar);
                                etRegDate.setError(null);
                                /*Calendar calendar1 = Calendar.getInstance();
                                calendar1.set(calendar1.get(Calendar.YEAR), monthOfYear, dayOfMonth);
                                String expDate = simpleDateFormat.format(calendar1.getTime());
                                etExpDate.setText(expDate);*/
                            }
                        }
                    });

                    /*DateTimePicker.testDatePicker(view.getContext(), (Calendar) view.getTag(R.id.etRegDate), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {

                            if (view1.isShown()) {
                                Calendar calendar = Calendar.getInstance();
                                Calendar calSetPrev = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                calSetPrev.set(year, monthOfYear, dayOfMonth);
                                String currentDay = displayFormat.format(calendar.getTime());
                                etRegDate.setTag(R.id.etRegDate, calSetPrev);
                                etRegDate.setText(currentDay);

                                calendar.set(year, monthOfYear, 01);
                                String currentDay1 = displayFormat.format(calendar.getTime());
                                etMfgDate.setText(currentDay1);
                                setYearMonthAdapter(calendar, calendar.get(Calendar.YEAR));

                            }
                        }
                    });*/

                    //endregion
                } else {
                    //region  new regdate

                    DateTimePicker.invoiceNewValidation(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                            if (view1.isShown()) {
                                /*Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String currentDay = displayFormat.format(calendar.getTime());
                                etRegDate.setText(currentDay);
                                etMfgDate.setText(currentDay);
                                setYearMonthAdapter(calendar, calendar.get(Calendar.YEAR));*/

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String currentDay = displayFormat.format(calendar.getTime());
                                etRegDate.setText(currentDay);
                                etRegDate.setError(null);
                                calendar.set(year, monthOfYear, 01);
                                String currentDay1 = displayFormat.format(calendar.getTime());
                                etMfgDate.setText(currentDay1);
                                setYearMonthAdapter(calendar, calendar.get(Calendar.YEAR));

                                etRegDate.setTag(R.id.etRegDate, calendar);
                            }
                        }
                    });

                    //endregion
                }
            }
            //endregion

            //region policy expirydate
            else if (view.getId() == R.id.etExpDate) {

                Date regDate = new Date();
                if (etRegDate.getText().toString().isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    regDate = calendar.getTime();
                } else {
                    try {
                        regDate = displayFormat.parse(etRegDate.getText().toString());
                    } catch (ParseException e) {
                        Calendar calendar = Calendar.getInstance();
                        regDate = calendar.getTime();
                        e.printStackTrace();
                    }
                }

                DateTimePicker.policyExpValidation(view.getContext(), regDate, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            spPrevIns.setEnabled(true);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = displayFormat.format(calendar.getTime());
                            etExpDate.setText(currentDay);
                            etExpDate.setError(null);
//                            if (etRegDate.getText().toString() != null && !etRegDate.getText().toString().equals("")) {
//                                int yearDiff = getYearDiffForNCB(currentDay, etRegDate.getText().toString());
//                                setSeekbarProgress(yearDiff);
//                            }
                        }
                    }
                });
            }
            //endregion

            //region manufacture date
            else if (view.getId() == R.id.etMfgDate) {

                Date regDate = new Date();
                if (etRegDate.getText().toString().isEmpty()) {
                    Calendar calendar = Calendar.getInstance();
                    regDate = calendar.getTime();
                } else {
                    try {
                        regDate = displayFormat.parse(etRegDate.getText().toString());
                    } catch (ParseException e) {
                        Calendar calendar = Calendar.getInstance();
                        regDate = calendar.getTime();
                        e.printStackTrace();
                    }
                }
                DateTimePicker.mfgYearMonthValidation(view.getContext(), regDate,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                                if (view1.isShown()) {
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.set(year, monthOfYear, 01);
                                    String currentDay = displayFormat.format(calendar.getTime());
                                    etMfgDate.setText(currentDay);
                                    etMfgDate.setError(null);
                                }
                            }
                        });
            }
            //endregion

        }
    };
    //endregion

    //region set parameter

    private void setInputParametersNewCAR() {
        // motorRequestEntity.setBirth_date("1992-01-01");


        motorRequestEntity.setProduct_id(1);
        varientId = dbController.getVariantID(getVarient(spVarient.getSelectedItem().toString()), getModel(acMakeModel.getText().toString()), getMake(acMakeModel.getText().toString()));
        motorRequestEntity.setVehicle_id(Integer.parseInt(varientId));
        //motorRequestEntity.setRto_id(Integer.parseInt(dbController.getCityID(getRtoCity(regplace))));

        motorRequestEntity.setRto_id(getCityId(acRto.getText().toString()));
        motorRequestEntity.setExecution_async("yes");
        motorRequestEntity.setVehicle_insurance_type("new");
        motorRequestEntity.setVehicle_manf_date(getMfgDate());
        //motorRequestEntity.setVehicle_manf_date(getYYYYMMDDPattern(getManufacturingDate(etMfgDate.getText().toString())));
        try {
            //motorRequestEntity.setVehicle_registration_date(getYYYYMMDDPattern(etRegDate.getText().toString()));
            motorRequestEntity.setVehicle_registration_date(getPolicyBossDateFormat(etRegDate.getText().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        motorRequestEntity.setPolicy_expiry_date("");
        motorRequestEntity.setPrev_insurer_id(0);
        motorRequestEntity.setVehicle_registration_type("individual");
        motorRequestEntity.setVehicle_ncb_current("0");
        motorRequestEntity.setIs_claim_exists("yes");
        motorRequestEntity.setMethod_type("Premium");
        motorRequestEntity.setElectrical_accessory("0");
        motorRequestEntity.setNon_electrical_accessory("0");

        motorRequestEntity.setRegistration_no(getRegistrationNo());

        motorRequestEntity.setIs_llpd("no");
        motorRequestEntity.setIs_antitheft_fit("no");
        motorRequestEntity.setVoluntary_deductible(0);
        motorRequestEntity.setIs_external_bifuel("no");
        motorRequestEntity.setPa_owner_driver_si("");
        //motorRequestEntity.setPa_owner_driver_si("");
        motorRequestEntity.setPa_named_passenger_si("0");
        motorRequestEntity.setPa_unnamed_passenger_si("0");
        motorRequestEntity.setPa_paid_driver_si("0");
        motorRequestEntity.setVehicle_expected_idv(0);
        motorRequestEntity.setFirst_name("");
        motorRequestEntity.setMiddle_name(" ");
        motorRequestEntity.setLast_name(" ");
        motorRequestEntity.setMobile("");
        motorRequestEntity.setEmail("finmarttest@gmail.com");
        if (sendOldCrn) {

        } else {
            motorRequestEntity.setCrn("");
        }


        if (spFuel.getSelectedItem().toString().equals(DBPersistanceController.EXTERNAL_LPG)) {
            motorRequestEntity.setExternal_bifuel_type("lpg");
            motorRequestEntity.setIs_external_bifuel("yes");
            if (!etExtValue.getText().toString().equals(""))
                motorRequestEntity.setExternal_bifuel_value(Integer.parseInt(etExtValue.getText().toString()));
        } else if (spFuel.getSelectedItem().toString().equals(DBPersistanceController.EXTERNAL_CNG)) {
            motorRequestEntity.setExternal_bifuel_type("cng");
            motorRequestEntity.setIs_external_bifuel("yes");
            if (!etExtValue.getText().toString().equals(""))
                motorRequestEntity.setExternal_bifuel_value(Integer.parseInt(etExtValue.getText().toString()));

        } else {
            motorRequestEntity.setExternal_bifuel_type("");
            motorRequestEntity.setIs_external_bifuel("no");
            motorRequestEntity.setExternal_bifuel_value(0);
        }

        setCustomerDetails();
    }

    private void setInputParametersReNewCar() {
        if (fastLaneResponseEntity != null) {
            try {
                /*motorRequestEntity.setVehicle_id(Integer.parseInt(fastLaneResponseEntity.getVariant_Id()));
                motorRequestEntity.setRto_id(Integer.parseInt(fastLaneResponseEntity.getVehicleCity_Id()));*/
                varientId = dbController.getVariantID(getVarient(spVarient.getSelectedItem().toString()), getModel(acMakeModel.getText().toString()), getMake(acMakeModel.getText().toString()));
                motorRequestEntity.setVehicle_id(Integer.parseInt(varientId));
                motorRequestEntity.setRto_id(getCityId(acRto.getText().toString()));

                motorRequestEntity.setVehicle_manf_date(getMfgDate());
                motorRequestEntity.setPolicy_expiry_date(getPolicyBossDateFormat(etExpDate.getText().toString()));
                motorRequestEntity.setVehicle_registration_date(getPolicyBossDateFormat(etRegDate.getText().toString()));
                //motorRequestEntity.setVehicle_manf_date(changeDateFormat(fastLaneResponseEntity.getRegistration_Date()));
                //motorRequestEntity.setRegistration_no(formatRegistrationNo(fastLaneResponseEntity.getRegistration_Number()));
                motorRequestEntity.setRegistration_no(getFormattedRegNoFastlane());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            varientId = dbController.getVariantID(getVarient(spVarient.getSelectedItem().toString()), getModel(acMakeModel.getText().toString()), getMake(acMakeModel.getText().toString()));
            motorRequestEntity.setVehicle_id(Integer.parseInt(varientId));
            motorRequestEntity.setRto_id(getCityId(acRto.getText().toString()));
            //motorRequestEntity.setRto_id(Integer.parseInt(dbController.getCityID(getRtoCity(acRto.getText().toString()))));
            try {
                motorRequestEntity.setVehicle_manf_date(getMfgDate());
                //motorRequestEntity.setVehicle_manf_date(getYYYYMMDDPattern(getManufacturingDate(etMfgDate.getText().toString())));
                motorRequestEntity.setVehicle_registration_date(getPolicyBossDateFormat(etRegDate.getText().toString()));
                motorRequestEntity.setPolicy_expiry_date(getPolicyBossDateFormat(etExpDate.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (motorRequestEntity.getRegistration_no().equals(""))
                motorRequestEntity.setRegistration_no(getRegistrationNo());
        }

        motorRequestEntity.setPrev_insurer_id(dbController.getInsurenceID(spPrevIns.getSelectedItem().toString()));
        // motorRequestEntity.setBirth_date("1992-01-01");
        motorRequestEntity.setProduct_id(1);
        motorRequestEntity.setExecution_async("yes");
        motorRequestEntity.setVehicle_insurance_type("renew");
        motorRequestEntity.setVehicle_registration_type("individual");
        motorRequestEntity.setMethod_type("Premium");

        if (isClaimExist) {
            motorRequestEntity.setIs_claim_exists("yes");
            motorRequestEntity.setVehicle_ncb_current("0");
        } else {
            motorRequestEntity.setIs_claim_exists("no");
            motorRequestEntity.setVehicle_ncb_current("" + getPercentFromProgress(sbNoClaimBonus.getProgress()));
        }

        motorRequestEntity.setElectrical_accessory("0");
        motorRequestEntity.setNon_electrical_accessory("0");

        motorRequestEntity.setIs_llpd("no");
        motorRequestEntity.setIs_antitheft_fit("no");
        motorRequestEntity.setVoluntary_deductible(0);
        motorRequestEntity.setIs_external_bifuel("no");
        motorRequestEntity.setPa_owner_driver_si("");
        motorRequestEntity.setPa_named_passenger_si("0");
        motorRequestEntity.setPa_unnamed_passenger_si("0");
        motorRequestEntity.setPa_paid_driver_si("0");
        motorRequestEntity.setVehicle_expected_idv(0);
        motorRequestEntity.setFirst_name("");
        motorRequestEntity.setMiddle_name(" ");
        motorRequestEntity.setLast_name(" ");
        motorRequestEntity.setMobile("");
        motorRequestEntity.setEmail("finmarttest@gmail.com");
        if (sendOldCrn) {

        } else {
            motorRequestEntity.setCrn("");
        }

        if (spFuel.getSelectedItem().toString().equals(DBPersistanceController.EXTERNAL_LPG)) {
            motorRequestEntity.setExternal_bifuel_type("lpg");
            motorRequestEntity.setIs_external_bifuel("yes");
            if (!etExtValue.getText().toString().equals(""))
                motorRequestEntity.setExternal_bifuel_value(Integer.parseInt(etExtValue.getText().toString()));
        } else if (spFuel.getSelectedItem().toString().equals(DBPersistanceController.EXTERNAL_CNG)) {
            motorRequestEntity.setExternal_bifuel_type("cng");
            motorRequestEntity.setIs_external_bifuel("yes");
            if (!etExtValue.getText().toString().equals(""))
                motorRequestEntity.setExternal_bifuel_value(Integer.parseInt(etExtValue.getText().toString()));

        } else {
            motorRequestEntity.setExternal_bifuel_type("");
            motorRequestEntity.setIs_external_bifuel("no");
            motorRequestEntity.setExternal_bifuel_value(0);
        }

        setCustomerDetails();

    }

    //endregion

    void setCustomerDetails() {
        if (location != null) {
            motorRequestEntity.setGeo_lat(location.getLatitude());
            motorRequestEntity.setGeo_long(location.getLongitude());
        }
        String[] fullName = etCustomerName.getText().toString().split(" ");

        if (fullName.length == 1) {
            motorRequestEntity.setFirst_name(fullName[0]);
        } else if (fullName.length == 2) {
            motorRequestEntity.setFirst_name(fullName[0]);
            motorRequestEntity.setLast_name(fullName[1]);

        } else if (fullName.length == 3) {
            motorRequestEntity.setFirst_name(fullName[0]);
            motorRequestEntity.setMiddle_name(fullName[1]);
            motorRequestEntity.setLast_name(fullName[2]);
        }
        motorRequestEntity.setMobile(etMobile.getText().toString());
        motorRequestEntity.setEmail("finmarttest@gmail.com");
    }

    //region api response

    @Override
    public void OnSuccess(APIResponse response, String message) {
        cancelDialog();
        if (response instanceof BikeUniqueResponse) {
            if (constantEntity.getLogtracking().equals("0"))
                new PolicybossTrackingResponse((BikeUniqueResponse) response).execute();
            ((InputQuoteBottmActivity) getActivity()).getQuoteParameterBundle(motorRequestEntity);
        }

    }

    @Override
    public void OnSuccess(magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse response, String message) {

        if (response instanceof FastLaneDataResponse) {

            cancelDialog();
            if (constantEntity.getLogtracking().equals("0"))
                new PolicybossTrackingFastlnaeResponse((FastLaneDataResponse) response).execute();
            if (response.getStatusNo() == 0) {
                if (!((FastLaneDataResponse) response).getMasterData().getVariant_Id().equals("0")) {
                    CarMasterEntity carMasterEntity = dbController.getVarientDetails(String.valueOf(((FastLaneDataResponse) response).getMasterData().getVariant_Id()));
                    if (carMasterEntity != null) {
                        this.fastLaneResponseEntity = ((FastLaneDataResponse) response).getMasterData();
                        bindFastLaneData(((FastLaneDataResponse) response).getMasterData());
                    }
                }
            }
        } else if (response instanceof CarMasterResponse) {

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
        if (t.getMessage().contains("manually")) {
            llVerifyCarDetails.setVisibility(View.GONE);
        }
    }

    //endregion

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            getActivity().finish();
            return true;
        } else if (i == R.id.action_home) {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("MarkTYPE", "FROM_HOME");
            startActivity(intent);
            getActivity().finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    public String getPolicyBossDateFormat(String date) { //dd-MM-YYYY
        Date newDate = null;
        try {
            newDate = displayFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return policyBossDateFormat.format(newDate);
    }

    public String getDisplayDateFormat(String date) {
        Date newDate = null;
        try {
            newDate = policyBossDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (newDate != null)
            return displayFormat.format(newDate);
        else
            return "";
    }

    public String getDisplayDateFormatFastLane(String date) {
        Date newDate = null;
        try {
            newDate = fastLaneDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (newDate != null)
            return displayFormat.format(newDate);
        else
            return "";
    }

    //returns date in policyboss date format
    public String getMfgDate() {
        String mfgDAte = "";
        if (spMonth.getSelectedItemPosition() < 10)
            mfgDAte = spYear.getSelectedItem().toString() + "-0" + spMonth.getSelectedItemPosition() + "-01";
        else
            mfgDAte = spYear.getSelectedItem().toString() + "-" + spMonth.getSelectedItemPosition() + "-01";
        return mfgDAte;
    }

    private void setNcbAdapter(int yearDiff) {
        if (yearDiff >= 5) {
            spNcbPercent.setSelection(5);
        } else {
            spNcbPercent.setSelection(yearDiff);
        }
    }

    private void setSeekbarProgress(int percent) {
        int progress = getProgressFromPercent(percent);

        tvClaimNo.performClick();
        sbNoClaimBonus.setProgress(progress);
        tvProgress.setText("Existing NCB (" + percent + "%)");

         /*if (yearDiff >= 5) {
            tvClaimNo.performClick();
            sbNoClaimBonus.setProgress(5);
            tvProgress.setText("Existing NCB (" + getPercentFromProgress(5) + "%)");
        } else {
            tvClaimNo.performClick();
            sbNoClaimBonus.setProgress(yearDiff);
            tvProgress.setText("Existing NCB (" + getPercentFromProgress(yearDiff) + "%)");
        }*/
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (R.id.switchNewRenew == compoundButton.getId()) {
            if (b) {
                MyApplication.getInstance().trackEvent(Constants.PRIVATE_CAR, "RENEW_QUOTE", "Renew motor quote");
                insuranceSubtypeEntities = dbController.getInsuranceSubTypeList(1, "renew");
                /*subTypeAdapter = new ArrayAdapter<InsuranceSubtypeEntity>(getActivity(), android.R.layout.simple_list_item_1,
                        insuranceSubtypeEntities);
                spInsSubTYpe.setAdapter(subTypeAdapter);*/
                setSubTypeAdapterView(insuranceSubtypeEntities);

                tvRenew.setTextColor(getResources().getColor(R.color.colorAccent));
                tvNew.setTextColor(getResources().getColor(R.color.header_dark_text));
                etExpDate.setEnabled(true);
                spPrevIns.setEnabled(true);
                cvNcb.setVisibility(View.VISIBLE);
                llNoClaim.setVisibility(View.VISIBLE);
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("ReNew : click here button with renew "), Constants.PRIVATE_CAR), null);
            } else {
                MyApplication.getInstance().trackEvent(Constants.PRIVATE_CAR, "NEW_QUOTE", "new motor quote");
                insuranceSubtypeEntities = dbController.getInsuranceSubTypeList(1, "new");
                /*subTypeAdapter = new ArrayAdapter<InsuranceSubtypeEntity>(getActivity(), android.R.layout.simple_list_item_1,
                        insuranceSubtypeEntities);
                spInsSubTYpe.setAdapter(subTypeAdapter);*/
                setSubTypeAdapterView(insuranceSubtypeEntities);
                tvRenew.setTextColor(getResources().getColor(R.color.header_dark_text));
                tvNew.setTextColor(getResources().getColor(R.color.colorAccent));
                etExpDate.setEnabled(false);
//                spPrevIns.setSelection(0);
                spPrevIns.setEnabled(false);

                // spPrevIns.setAlpha(0.5f);
                cvNcb.setVisibility(View.GONE);
                llNoClaim.setVisibility(View.INVISIBLE);
                tvDontKnow.performClick();
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("New : click here button with new "), Constants.PRIVATE_CAR), null);
            }
        } else if (R.id.swIndividual == compoundButton.getId()) {
            if (!b) {
                MyApplication.getInstance().trackEvent(Constants.PRIVATE_CAR, "COMPANY", " motor quote");
                //openPop up
                swIndividual.setChecked(true);
                openPopUp(swIndividual, "MAGIC-FINMART", "CURRENTLY THIS OPTION IS NOT AVAILABLE PLEASE USE RAISE A QUERY", "OK", true);
                //showPopUp("CURRENTLY THIS OPTION IS NOT AVAILABLE PLEASE USE RAISE A QUERY", "", "OK", true);
            } else {
                MyApplication.getInstance().trackEvent(Constants.PRIVATE_CAR, "INDIVDUAL", " motor quote");
            }
        }

    }

    void initializeViews(String policyType) {
        if (policyType.equals("new")) {
            etExpDate.setEnabled(false);

            spPrevIns.setEnabled(false);
            cvNcb.setVisibility(View.GONE);
            llNoClaim.setVisibility(View.INVISIBLE);
            tvDontKnow.performClick();
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("New : click here button with new "), Constants.PRIVATE_CAR), null);
        } else if (policyType.equals("renew")) {
            etExpDate.setEnabled(true);
            spPrevIns.setEnabled(true);
            cvNcb.setVisibility(View.VISIBLE);
            llNoClaim.setVisibility(View.VISIBLE);
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("ReNew : click here button with renew "), Constants.PRIVATE_CAR), null);

        } else if (policyType.equals("expired")) {
            etExpDate.setEnabled(true);
            spPrevIns.setEnabled(true);
            cvNcb.setVisibility(View.VISIBLE);
            llNoClaim.setVisibility(View.VISIBLE);
            new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData("Expired : click here button with renew "), Constants.PRIVATE_CAR), null);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int selectedId;
        RadioButton radioButton;
        int i1 = radioGroup.getId();
        if (i1 == R.id.rgExpiry) {
            selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = view.findViewById(selectedId);
            if (radioButton.getText().toString().contains("existing"))
                initializeViews("existing");
            else if (radioButton.getText().toString().equals("within"))
                initializeViews("within");
            else if (radioButton.getText().toString().equals("beyond"))
                initializeViews("beyond");

        } else if (i1 == R.id.rgNewRenew) {
            selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = view.findViewById(selectedId);
            if (radioButton.getText().toString().equals("NEW"))
                initializeViews("new");
            else if (radioButton.getText().toString().equals("RENEW"))
                initializeViews("renew");
            else if (radioButton.getText().toString().equals("EXPIRED"))
                initializeViews("expired");

        }
    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {
        int i = view.getId();
        if (i == R.id.swIndividual) {
            dialog.dismiss();

        }
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {
        int i = view.getId();
        if (i == R.id.swIndividual) {
            dialog.dismiss();

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        location = locationTracker.mLocation;
    }

    @Override
    public void onConnected() {
        location = locationTracker.mLocation;
    }

    @Override
    public void onConnectionFailed() {
        location = null;
    }

    public void setYearMonthAdapter(Calendar calendar) {

        yearList.clear();
        yearList.addAll(getYearList(calendar.get(Calendar.YEAR)));
        YearAdapter.notifyDataSetChanged();

        int yearIndex = 0;
        for (int i = 0; i < yearList.size(); i++) {
            String year = "" + calendar.get(Calendar.YEAR);
            String vari = yearList.get(i);
            if (year.equalsIgnoreCase(vari)) {
                yearIndex = i;
                break;
            }
        }
        spYear.setSelection(yearIndex);

        monthList.clear();
        monthList.addAll(getMonthList(calendar.get(Calendar.MONTH)));
        MonthAdapter.notifyDataSetChanged();

        spMonth.setSelection(calendar.get(Calendar.MONTH) + 1);
    }

    public void setYearMonthAdapterFastlane(Calendar calendar) {

        /*yearList.clear();
        yearList.addAll(getYearList(calendar.get(Calendar.YEAR)));
        YearAdapter.notifyDataSetChanged();*/

        int yearIndex = 0;
        for (int i = 0; i < yearList.size(); i++) {
            String year = "" + calendar.get(Calendar.YEAR);
            String vari = yearList.get(i);
            if (year.equalsIgnoreCase(vari)) {
                yearIndex = i;
                break;
            }
        }
        spYear.setSelection(yearIndex);

        monthList.clear();
        monthList.addAll(getMonthList(calendar.get(Calendar.MONTH)));
        MonthAdapter.notifyDataSetChanged();

        spMonth.setSelection(calendar.get(Calendar.MONTH) + 1);
    }

    public void setYearMonthAdapter(Calendar calendar, int maxYear) {

        yearList.clear();
        yearList.addAll(getYearList(maxYear));
        YearAdapter.notifyDataSetChanged();

        int yearIndex = 0;
        for (int i = 0; i < yearList.size(); i++) {
            String year = "" + calendar.get(Calendar.YEAR);
            String vari = yearList.get(i);
            if (year.equalsIgnoreCase(vari)) {
                yearIndex = i;
                break;
            }
        }
        spYear.setSelection(yearIndex);

        monthList.clear();
        monthList.addAll(getMonthList(calendar.get(Calendar.MONTH)));
        MonthAdapter.notifyDataSetChanged();

        spMonth.setSelection(calendar.get(Calendar.MONTH) + 1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // TODO Add your menu entries here
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    class PolicybossTrackingRequest extends AsyncTask<Void, Void, String> {
        MotorRequestEntity motorRequestEntity;
        String requestJson = "";

        public PolicybossTrackingRequest(MotorRequestEntity motorRequestEntity) {
            this.motorRequestEntity = motorRequestEntity;
        }

        @Override
        protected String doInBackground(Void... voids) {

            requestJson = gson.toJson(motorRequestEntity);
            return requestJson;
        }

        @Override
        protected void onPostExecute(String s) {
            if (constantEntity.getLogtracking().equals("0"))
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData(s), Constants.PRIVATE_CAR_REQUEST), null);


        }
    }

    class PolicybossTrackingResponse extends AsyncTask<Void, Void, String> {
        BikeUniqueResponse bikeUniqueResponse;
        String response = "";

        public PolicybossTrackingResponse(BikeUniqueResponse bikeUniqueResponse) {
            this.bikeUniqueResponse = bikeUniqueResponse;
        }

        @Override
        protected String doInBackground(Void... voids) {

            response = gson.toJson(bikeUniqueResponse);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (constantEntity.getLogtracking().equals("0"))
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData(s), Constants.PRIVATE_CAR_RESPONSE), null);


        }
    }

    class PolicybossTrackingFastlnaeResponse extends AsyncTask<Void, Void, String> {
        FastLaneDataResponse fastLaneDataResponse;
        String response = "";

        public PolicybossTrackingFastlnaeResponse(FastLaneDataResponse fastLaneDataResponse) {
            this.fastLaneDataResponse = fastLaneDataResponse;
        }

        @Override
        protected String doInBackground(Void... voids) {

            response = gson.toJson(fastLaneDataResponse);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            if (constantEntity.getLogtracking().equals("0"))
                new TrackingController(getActivity()).sendData(new TrackingRequestEntity(new TrackingData(s), Constants.PRIVATE_CAR_FASTLANE_RESPONSE), null);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sendOldCrn = true;
    }
}
