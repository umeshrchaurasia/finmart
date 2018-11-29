package com.datacomp.magicfinmart.vehicle_details;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.Constants;
import com.datacomp.magicfinmart.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.DynamicController;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.GenerateLeadRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.GenerateLeadResponse;
import magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls.VehicleMobileResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

public class VehicleDetailsAdapter extends RecyclerView.Adapter<VehicleDetailsAdapter.customerInfoItem> {

    Fragment mContex;
    List<VehicleMobileResponse.CustomerDetailsEntity> listCustDetails;
    SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");

    public VehicleDetailsAdapter(Fragment context, List<VehicleMobileResponse.CustomerDetailsEntity> list) {
        this.mContex = context;
        listCustDetails = list;
    }

    @Override
    public customerInfoItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_vehicle_details, parent, false);
        return new customerInfoItem(itemView);
    }

    @Override
    public void onBindViewHolder(final customerInfoItem holder, int position) {

        final VehicleMobileResponse.CustomerDetailsEntity entity = listCustDetails.get(position);
        holder.txtCategory.setText(entity.getCategory());
        holder.txtDOB.setText("" + entity.getDOB());
        holder.txtClaimStatus.setText("" + entity.getClaimStatus());
        holder.txtEmail.setText(entity.getEmail());

        holder.etExpiryDate.setText("" + entity.getExpiryDate());
        holder.txtInsuranceName.setText(entity.getInsuranceName());
        if (entity.getMobileNo().toLowerCase().equalsIgnoreCase("na")) {
            holder.etMobileNo.setText("");
        } else {
            holder.etMobileNo.setText("" + entity.getMobileNo());
        }

        if (entity.getName().toLowerCase().equalsIgnoreCase("na")) {
            holder.etName.setText("");
        } else {
            holder.etName.setText("" + entity.getName());
        }


        holder.txtPincode.setText("" + entity.getPincode());
        holder.txtPolicyNumber.setText("" + entity.getPolicyNumber());
        holder.txtPremium.setText("" + entity.getPremium());
        holder.txtVehicleRegNumber.setText("" + entity.getVehicleRegNumber());

        holder.btnGenerateLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.etName.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(mContex.getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                GenerateLead(holder.etExpiryDate.getText().toString(), holder.etName.getText().toString()
                        , holder.etMobileNo.getText().toString(), entity);
            }
        });

        holder.etExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.hideKeyBoard(v, mContex.getActivity());
                DateTimePicker.scanVehicleExpDatePicker(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int monthOfYear, int dayOfMonth) {
                        if (view1.isShown()) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, monthOfYear, dayOfMonth);
                            String currentDay = displayFormat.format(calendar.getTime());
                            holder.etExpiryDate.setText(currentDay);
                            holder.btnGenerateLead.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }


    private void GenerateLead(String expDate, String name, String mobile, VehicleMobileResponse.CustomerDetailsEntity vehicleInfo) {

        GenerateLeadRequestEntity entity = new GenerateLeadRequestEntity();

        entity.setFBAID(String.valueOf(new DBPersistanceController(mContex.getActivity()).getUserData().getFBAId()));
        entity.setCategory(vehicleInfo.getCategory());
        entity.setDOB(vehicleInfo.getDOB());
        entity.setClaimStatus(vehicleInfo.getClaimStatus());
        entity.setExpiryDate(expDate);
        entity.setInsuranceID(vehicleInfo.getInsuranceID());
        entity.setInsuranceName(vehicleInfo.getInsuranceName());
        entity.setMobileNo(mobile);
        entity.setName(name);
        entity.setPincode(vehicleInfo.getPincode());
        entity.setPolicyNumber(vehicleInfo.getPolicyNumber());
        entity.setPremium(vehicleInfo.getPremium());
        entity.setQT_Entry_Number(vehicleInfo.getQT_Entry_Number());
        entity.setVehicleRegNumber(vehicleInfo.getVehicleRegNumber());
        entity.setRegistrationNo(vehicleInfo.getVehicleRegNumber());

        entity.setChasisNo("");
        entity.setCity("");
        entity.setClaimNo("");
        entity.setClaimSattlementType("");
        entity.setClientName("");
        entity.setEngineNo("");
        entity.setFuelType("");
        entity.setGender("");
        entity.setHolderPincode("");
        entity.setInceptionDate("");
        entity.setIsCustomer("");
        entity.setMake("");
        entity.setMfgyear("");
        entity.setNoClaimBonus("");
        entity.setPOSPCode("");
        entity.setPOSPName("");
        entity.setRTOCity("");
        entity.setRTOState("");
        entity.setRegistrationDate("");

        entity.setSubModel("");
        entity.setHolderaddress("");
        entity.setModel("");


        new DynamicController(mContex.getActivity()).saveGenerateLead(entity, new IResponseSubcriber() {
            @Override
            public void OnSuccess(APIResponse response, String message) {
                ((VehicleDetailFragment) mContex).cancelDialog();
                if (response instanceof GenerateLeadResponse) {
                    if (response.getStatusNo() == 0) {
                        Toast.makeText(mContex.getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void OnFailure(Throwable t) {
                //cancelDialog();
                Toast.makeText(mContex.getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (listCustDetails == null)
            return 0;
        else
            return listCustDetails.size();
    }

    public class customerInfoItem extends RecyclerView.ViewHolder {


        TextView txtCategory, txtDOB, txtClaimStatus, txtEmail, txtInsuranceName,
                txtMobileNo, txtName, txtPincode, txtPolicyNumber, txtPremium, txtVehicleRegNumber;

        EditText etExpiryDate, etName, etMobileNo;

        Button btnGenerateLead;

        public customerInfoItem(View itemView) {
            super(itemView);
            btnGenerateLead = itemView.findViewById(R.id.btnGenerateLead);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtDOB = itemView.findViewById(R.id.txtDOB);
            txtClaimStatus = itemView.findViewById(R.id.txtClaimStatus);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            etExpiryDate = itemView.findViewById(R.id.etExpiryDate);
            etName = itemView.findViewById(R.id.etName);
            etMobileNo = itemView.findViewById(R.id.etMobileNo);
            txtInsuranceName = itemView.findViewById(R.id.txtInsuranceName);
            txtMobileNo = itemView.findViewById(R.id.txtMobileNo);
            txtName = itemView.findViewById(R.id.txtName);
            txtPincode = itemView.findViewById(R.id.txtPincode);
            txtPolicyNumber = itemView.findViewById(R.id.txtPolicyNumber);
            txtPremium = itemView.findViewById(R.id.txtPremium);
            txtVehicleRegNumber = itemView.findViewById(R.id.txtVehicleRegNumber);
        }
    }


    public void refreshAdapter(List<VehicleMobileResponse.CustomerDetailsEntity> list) {
        listCustDetails = list;
        notifyDataSetChanged();
    }


}