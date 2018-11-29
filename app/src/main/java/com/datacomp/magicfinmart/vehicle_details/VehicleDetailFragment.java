package com.datacomp.magicfinmart.vehicle_details;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.DynamicController;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.GenerateLeadRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.GenerateLeadResponse;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.VehicleInfoEntity;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.VehicleMobileResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.fastlane.FastLaneController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.FastLaneDataEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.FastLaneDataResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleDetailFragment extends BaseFragment implements View.OnClickListener {

    SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");

    RadioButton rbCarNumber, rbMobileNumber;
    EditText etVehicleDetail;
    Button btnVehicleDetails, btnGenerateLead;
    TextView txtClientName, txtDOB, txtMfg, txtClaimNo, txtClaimSattlementType, txtClaimStatus,
            txtAddress, txtRegistrationNo, txtCarDetail, txtChasisNo, txtEngineNo, txtRTO, txtFuel;

    EditText etVehicleExpiryDate, etName, etMobileNo;

    CardView cvVehicleDetail;


    TextView txtMobileData;

    RecyclerView rvMobile;
    VehicleDetailsAdapter mAdapter;
    List<VehicleMobileResponse.CustomerDetailsEntity> listCustDetails;

    VehicleInfoEntity.VehicleInfo mVehicleInfo;
    FastLaneDataEntity entity;

    public VehicleDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle_detail, container, false);
        Constants.hideKeyBoard(rbCarNumber, getActivity());
        listCustDetails = new ArrayList<>();
        init(view);

        etVehicleExpiryDate.setOnClickListener(datePickerDialog);
        btnGenerateLead.setVisibility(View.GONE);
        cvVehicleDetail.setVisibility(View.GONE);
        txtMobileData.setVisibility(View.GONE);

        btnGenerateLead.setOnClickListener(this);
        return view;
    }

    //region date picker

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            Constants.hideKeyBoard(view, getActivity());


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

                        }
                    }
                });


            }
            //endregion


        }
    };
    //endregion

    private void init(View view) {


        rvMobile = view.findViewById(R.id.rvMobile);
        rvMobile.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMobile.setLayoutManager(layoutManager);
        mAdapter = new VehicleDetailsAdapter(VehicleDetailFragment.this, listCustDetails);
        rvMobile.setAdapter(mAdapter);

        etVehicleExpiryDate = view.findViewById(R.id.etVehicleExpiryDate);
        etName = view.findViewById(R.id.etName);
        etMobileNo = view.findViewById(R.id.etMobileNo);


        txtMobileData = view.findViewById(R.id.txtMobileData);
        rbCarNumber = view.findViewById(R.id.rbCarNumber);
        rbMobileNumber = view.findViewById(R.id.rbMobileNumber);
        etVehicleDetail = view.findViewById(R.id.etVehicleDetail);
        btnVehicleDetails = view.findViewById(R.id.btnVehicleDetails);
        cvVehicleDetail = view.findViewById(R.id.cvVehicleDetail);


        txtClientName = view.findViewById(R.id.txtClientName);
        txtDOB = view.findViewById(R.id.txtDOB);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtRegistrationNo = view.findViewById(R.id.txtRegistrationNo);
        txtCarDetail = view.findViewById(R.id.txtCarDetail);
       // txtChasisNo = view.findViewById(R.id.txtChasisNo);
       // txtEngineNo = view.findViewById(R.id.txtEngineNo);
       // txtRTO = view.findViewById(R.id.txtRTO);
       // txtFuel = view.findViewById(R.id.txtFuel);
       // txtMfg = view.findViewById(R.id.txtMfg);

        btnGenerateLead = view.findViewById(R.id.btnGenerateLead);

       // txtClaimNo = view.findViewById(R.id.txtClaimNo);
       // txtClaimSattlementType = view.findViewById(R.id.txtClaimSattlementType);
        txtClaimStatus = view.findViewById(R.id.txtClaimStatus);

        btnVehicleDetails.setOnClickListener(this);

        if (rbCarNumber.isChecked())
            etVehicleDetail.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        else if (rbMobileNumber.isChecked())
            etVehicleDetail.setInputType(InputType.TYPE_CLASS_NUMBER);

        rbCarNumber.setOnCheckedChangeListener(changeListener);
        rbMobileNumber.setOnCheckedChangeListener(changeListener);
    }


    CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.rbCarNumber) {
                if (isChecked) {
                    Constants.hideKeyBoard(buttonView, getActivity());
                    etVehicleDetail.setInputType(InputType.TYPE_CLASS_TEXT);
                    etVehicleDetail.setText("");
                    rvMobile.setVisibility(View.GONE);
                    cvVehicleDetail.setVisibility(View.GONE);
                    btnGenerateLead.setVisibility(View.GONE);
                }
            } else if (buttonView.getId() == R.id.rbMobileNumber) {
                if (isChecked) {
                    Constants.hideKeyBoard(buttonView, getActivity());
                    etVehicleDetail.setInputType(InputType.TYPE_CLASS_NUMBER);
                    etVehicleDetail.setText("");
                    rvMobile.setVisibility(View.VISIBLE);
                    listCustDetails.clear();
                    mAdapter.refreshAdapter(listCustDetails);
                    cvVehicleDetail.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnVehicleDetails) {

            //Hide keyboard
            Constants.hideKeyBoard(btnVehicleDetails, getActivity());

            //validation
            if (rbCarNumber.isChecked()) {

                if (etVehicleDetail.getText().toString().equalsIgnoreCase("")
                        || etVehicleDetail.getText().toString().length() < 9) {
                    etVehicleDetail.setError("Invalid vehicle number");
                    etVehicleDetail.requestFocus();
                    return;
                }
            } else if (rbMobileNumber.isChecked()) {
                if (!isValidePhoneNumber(etVehicleDetail)) {
                    etVehicleDetail.setError("Invalid phone number");
                    etVehicleDetail.requestFocus();
                    return;
                }
            }

            //server hit
            showDialog();

            if (rbCarNumber.isChecked()) {

                //1.vehicle

              /*  showDialog();
                new DynamicController(getActivity()).getVehicleByVehicleNo(etVehicleDetail.getText().toString(),
                        new IResponseSubcriber() {

                            @Override
                            public void OnSuccess(APIResponse response, String message) {
                                cancelDialog();
                                if (response instanceof magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.VehicleInfoEntity) {
                                    //bind vehicle

                                    new PrefManager(getActivity()).setVehicleCarVehicleLog();

                                    if (((VehicleInfoEntity) response).getGetRegNoDataResult() == null) {
                                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else if (((VehicleInfoEntity) response).getGetRegNoDataResult().size() == 0) {
                                        Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    mVehicleInfo = ((VehicleInfoEntity) response).getGetRegNoDataResult().get(0);
                                    bindVehicle(mVehicleInfo);
                                    btnGenerateLead.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void OnFailure(Throwable t) {
                                cancelDialog();
                                cvVehicleDetail.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });*/


                showDialog();
                new FastLaneController(getActivity()).getVechileDetails(etVehicleDetail.getText().toString().replaceAll("\\s", ""), new IResponseSubcriber() {

                    @Override
                    public void OnSuccess(APIResponse response, String message) {

                        cancelDialog();
                        if (response instanceof FastLaneDataResponse) {

                            if (response.getStatusNo() == 0) {
                                btnGenerateLead.setVisibility(View.VISIBLE);
                                bindFastlaneVehicle(((FastLaneDataResponse) response).getMasterData());
                            }
                        }
                    }

                    @Override
                    public void OnFailure(Throwable t) {
                        cancelDialog();
                        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                //2.mobile
                new DynamicController(getActivity()).getVehicleByMobileNo(etVehicleDetail.getText().toString(), new IResponseSubcriber() {
                    @Override
                    public void OnSuccess(APIResponse response, String message) {
                        cancelDialog();
                        if ((VehicleMobileResponse) response != null) {
                            if (((VehicleMobileResponse) response).getCustomerDetails().size() > 0) {
                                //bind recycler
                                new PrefManager(getActivity()).setVehicleCarMobileLog();
                                rvMobile.setVisibility(View.VISIBLE);
                                cvVehicleDetail.setVisibility(View.GONE);
                                mAdapter.refreshAdapter(((VehicleMobileResponse) response).getCustomerDetails());
                            } else {
                                rvMobile.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void OnFailure(Throwable t) {
                        cancelDialog();
                        rvMobile.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });

            }
        } else if (v.getId() == R.id.btnGenerateLead) {

            if (etName.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(getActivity(), "" + "Enter Name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!etVehicleExpiryDate.getText().toString().equalsIgnoreCase("")) {
                if (rbCarNumber.isChecked()) {
                    GenerateLead(entity);
                } else if (rbMobileNumber.isChecked()) {
                    GenerateLead(mVehicleInfo);
                }
            } else {
                Toast.makeText(getActivity(), "" + "Invalid expiry date", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void GenerateLead(FastLaneDataEntity vehicleInfo) {

        GenerateLeadRequestEntity entity = new GenerateLeadRequestEntity();

        entity.setFBAID(String.valueOf(new DBPersistanceController(getActivity()).getUserData().getFBAId()));

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
        entity.setMobileNo(etMobileNo.getText().toString());
        entity.setExpiryDate(etVehicleExpiryDate.getText().toString());


        new DynamicController(getActivity()).saveGenerateLead(entity, new IResponseSubcriber() {
            @Override
            public void OnSuccess(APIResponse response, String message) {
                cancelDialog();
                if (response instanceof GenerateLeadResponse) {
                    if (response.getStatusNo() == 0) {
                        Toast.makeText(getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void OnFailure(Throwable t) {
                cancelDialog();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void bindFastlaneVehicle(FastLaneDataEntity v) {


        cvVehicleDetail.setVisibility(View.VISIBLE);
        entity = v;
        try {
            txtRegistrationNo.setText(v.getRegistration_Number());
            txtCarDetail.setText(v.getMake_Name() + "," + v.getModel_Name() + "," + v.getVariant_Name());
            etVehicleExpiryDate.setText("");

        } catch (Exception e) {

        }
    }

    private void GenerateLead(VehicleInfoEntity.VehicleInfo vehicleInfo) {

        GenerateLeadRequestEntity entity = new GenerateLeadRequestEntity();

        entity.setFBAID(String.valueOf(new DBPersistanceController(getContext()).getUserData().getFBAId()));

        entity.setChasisNo(vehicleInfo.getChasisNo());
        entity.setCity(vehicleInfo.getCity());
        entity.setClaimNo(vehicleInfo.getClaimNo());
        entity.setClaimSattlementType(vehicleInfo.getClaimSattlementType());
        entity.setClaimStatus(vehicleInfo.getClaimStatus());
        entity.setClientName(vehicleInfo.getClientName());
        entity.setDOB(vehicleInfo.getDOB());
        entity.setEngineNo(vehicleInfo.getEngineNo());

        entity.setFuelType(vehicleInfo.getFuelType());
        entity.setGender(vehicleInfo.getGender());
        entity.setHolderPincode(vehicleInfo.getHolderPincode());
        entity.setInceptionDate(vehicleInfo.getInceptionDate());
        entity.setIsCustomer(vehicleInfo.getIsCustomer());
        entity.setMake(vehicleInfo.getMake());
        entity.setMfgyear(String.valueOf(vehicleInfo.getMfgyear()));
        entity.setNoClaimBonus(vehicleInfo.getNoClaimBonus());
        entity.setPOSPCode(vehicleInfo.getPOSPCode());
        entity.setPOSPName(vehicleInfo.getPOSPName());
        entity.setRTOCity(vehicleInfo.getRTOCity());
        entity.setRTOState(vehicleInfo.getRTOState());
        entity.setRegistrationDate(vehicleInfo.getRegistrationDate());
        entity.setRegistrationNo(vehicleInfo.getRegistrationNo());
        entity.setSubModel(vehicleInfo.getSubModel());
        entity.setHolderaddress(vehicleInfo.getHolderaddress());
        entity.setModel(vehicleInfo.getModel());

        entity.setQT_Entry_Number("");
        entity.setExpiryDate(etVehicleExpiryDate.getText().toString());
        //entity.setMobileNo();

        new DynamicController(getActivity()).saveGenerateLead(entity, new IResponseSubcriber() {
            @Override
            public void OnSuccess(APIResponse response, String message) {
                cancelDialog();
                if (response instanceof GenerateLeadResponse) {
                    if (response.getStatusNo() == 0) {
                        Toast.makeText(getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void OnFailure(Throwable t) {
                cancelDialog();
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindVehicle(VehicleInfoEntity.VehicleInfo entity) {


        cvVehicleDetail.setVisibility(View.VISIBLE);

        VehicleInfoEntity.VehicleInfo v = entity;
        try {
            txtClientName.setText(v.getClientName());
            txtDOB.setText(v.getDOB());
            txtAddress.setText(v.getHolderaddress() + " " + v.getPOSPCode());
            txtRegistrationNo.setText(v.getRegistrationNo());
            txtCarDetail.setText(v.getMake() + "," + v.getModel() + "," + v.getSubModel());
            txtChasisNo.setText(v.getChasisNo());
            txtEngineNo.setText(v.getEngineNo());
            txtRTO.setText(v.getRTOCity() + "," + v.getRTOState());
            txtFuel.setText(v.getFuelType());
            txtMfg.setText("" + v.getMfgyear());
            etVehicleExpiryDate.setText("" + v.getExpiryDate());
            txtClaimNo.setText("" + v.getClaimNo());
            txtClaimSattlementType.setText("" + v.getClaimSattlementType());
            txtClaimStatus.setText("" + v.getClaimStatus());

        } catch (Exception e) {

        }
    }

    private void bindNumber(String strMobileNumber) {
        txtMobileData.setVisibility(View.VISIBLE);
    }
}

