package com.datacomp.magicfinmart.motor.privatecar.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.health.fragment.HealthQuoteFragment;
import com.datacomp.magicfinmart.motor.privatecar.fragment.QuoteFragment;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.response.BikePremiumResponse;

public class CarQuoteAdapter extends RecyclerView.Adapter<CarQuoteAdapter.BikeQuoteItem> {


    Fragment mContext;
    BikePremiumResponse response;
    boolean isRecyclerListnerSet = false;
    List<ResponseEntity> listQuotes;
    DBPersistanceController dbPersistanceController;

    public CarQuoteAdapter(Fragment mContext, BikePremiumResponse response) {
        this.mContext = mContext;
        this.response = response;
        dbPersistanceController = new DBPersistanceController(mContext.getContext());
        if (response.getResponse() != null)
            this.listQuotes = response.getResponse();
        else
            this.listQuotes = null;
    }




    @Override
    public BikeQuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bikequote_item_new, parent, false);
        return new CarQuoteAdapter.BikeQuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(BikeQuoteItem holder, final int position) {

        final ResponseEntity responseEntity = listQuotes.get(position);

        holder.chkSelected.setTag(R.id.chkSelected, responseEntity);

        if (responseEntity.isSelected()) {
            holder.chkSelected.setChecked(true);
        } else {
            holder.chkSelected.setChecked(false);
        }

        holder.chkSelected.setOnCheckedChangeListener(checkedChangeListener);

        holder.rvAddOn.setTag(R.id.rvAddOn, responseEntity);
        holder.viewDisableLayout.setTag(R.id.viewDisableLayout, responseEntity);
        holder.txtInsurerName.setText(responseEntity.getInsurer().getInsurer_Name());
        // holder.txtIDV.setText(responseEntity);
        try {
            if (responseEntity.getPremium_Breakup() != null) {
                if (responseEntity.isAddonApplied()) {
                    holder.txtFinalPremium.setText("\u20B9" + " " + Math.round(Double.parseDouble(responseEntity.getFinal_premium_with_addon())));
                } else {
                    holder.txtFinalPremium.setText("\u20B9" + " " + Math.round(Double.parseDouble(responseEntity.getPremium_Breakup().getFinal_premium())));
                }

            } else {
                holder.txtFinalPremium.setText("");
            }

        } catch (Exception e) {
            Toast.makeText(mContext.getActivity(), "Magna Insurer final premium Null", Toast.LENGTH_SHORT).show();
        }

        holder.txtIDV.setText(String.valueOf(responseEntity.getLM_Custom_Request().getVehicle_expected_idv()));

        try {

            /*int logo = new DBPersistanceController(mContext.getActivity())
                    .getInsurerLogo(Integer.parseInt(responseEntity.getInsurer_Id()));

            Glide.with(mContext).load(logo)
                    .into(holder.imgInsurerLogo);*/

            Glide.with(mContext).load(Utility.getInsurerImage(responseEntity.getInsurer_Id()))
                    .into(holder.imgInsurerLogo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //holder.imgInsurerLogo.setImageResource(dbPersistanceController.getInsImage(Integer.parseInt(responseEntity.getInsurer().getInsurer_ID())));
        /*Glide.with(mContext)
                //.load(dbgetProfessionalID1(Integer.parseInt(responseEntity.getInsurer().getInsurer_ID())))
                .load("http://www.policyboss.com/Images/insurer_logo/" + responseEntity.getInsurer().getInsurer_Logo_Name())
                .into(holder.imgInsurerLogo);*/

        holder.txtFinalPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((QuoteFragment) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });

        holder.viewDisableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResponseEntity responseEntity = (ResponseEntity) view.getTag(R.id.viewDisableLayout);
                ((QuoteFragment) mContext).
                        redirectToPopUpPremium(responseEntity, response.getSummary(),
                                responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
                //Toast.makeText(mContext.getActivity(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });


        holder.txtPremiumBreakUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((QuoteFragment) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });

        holder.imgInsurerLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((QuoteFragment) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });
        holder.llIdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((QuoteFragment) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });

        holder.txtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((QuoteFragment) mContext).redirectToBuy(responseEntity);
            }
        });

        if (responseEntity.getListAppliedAddons() != null) {
            if (responseEntity.getListAppliedAddons().size() != 0) {
                holder.rvAddOn.setVisibility(View.VISIBLE);
                holder.rvAddOn.setHasFixedSize(true);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext.getActivity(), 2);
                holder.rvAddOn.setLayoutManager(mLayoutManager);
                GridAddonAdapter adapter = new GridAddonAdapter(mContext.getActivity(),
                        responseEntity.getListAppliedAddons()
                        , responseEntity);
                holder.rvAddOn.setAdapter(adapter);
            } else {
                holder.rvAddOn.setVisibility(View.GONE);
            }
        } else {
            holder.rvAddOn.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        if (listQuotes != null) {
            return listQuotes.size();
        } else {
            return 0;
        }
    }

    public void setQuoteResponse(BikePremiumResponse response) {
        this.response = response;
        if (response.getResponse() != null)
            this.listQuotes = response.getResponse();
    }

    public class BikeQuoteItem extends RecyclerView.ViewHolder {
        public TextView txtInsurerName, txtIDV, txtFinalPremium, txtPremiumBreakUp;
        LinearLayout txtBuy;
        ImageView imgInsurerLogo;
        LinearLayout llIdv;
        RelativeLayout llAddonList;
        View viewDisableLayout;
        RecyclerView rvAddOn;
        CheckBox chkSelected;

        public BikeQuoteItem(View itemView) {
            super(itemView);
            llIdv = (LinearLayout) itemView.findViewById(R.id.llIdv);
            llAddonList = (RelativeLayout) itemView.findViewById(R.id.llAddonList);
            viewDisableLayout = (View) itemView.findViewById(R.id.viewDisableLayout);
            // llAddon = (LinearLayout) itemView.findViewById(R.id.llAddon);
            rvAddOn = (RecyclerView) itemView.findViewById(R.id.rvAddOn);
            txtInsurerName = (TextView) itemView.findViewById(R.id.txtInsuranceCompName);
            txtIDV = (TextView) itemView.findViewById(R.id.txtIDV);
            txtBuy = (LinearLayout) itemView.findViewById(R.id.txtBuy);
            txtFinalPremium = (TextView) itemView.findViewById(R.id.txtFinalPremium);
            imgInsurerLogo = (ImageView) itemView.findViewById(R.id.imgInsurerLogo);
            txtPremiumBreakUp = (TextView) itemView.findViewById(R.id.txtPremiumBreakUp);
            chkSelected = (CheckBox) itemView.findViewById(R.id.chkSelected);
        }
    }


    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            ResponseEntity entity = (ResponseEntity) compoundButton.getTag(R.id.chkSelected);

            if (b) {
                entity.setSelected(true);
            } else {
                entity.setSelected(false);
            }
            ((QuoteFragment) mContext).addRemoveShare(entity, b,compoundButton.getId());

            /*if (checkCount <= 4) {
                if (b) {
                    checkCount = checkCount + 1;
                    entity.setCompare(b);
                    ((HealthQuoteFragment) mContext).addRemoveShare(entity, b);
                } else {
                    checkCount = checkCount - 1;
                    entity.setCompare(b);
                    ((HealthQuoteFragment) mContext).addRemoveShare(entity, b);
                }
            } else {
                if (b) {
                    ((HealthQuoteFragment) mContext).showAlert("You can select only four plans to compare.");
                } else {
                    checkCount = checkCount - 1;
                    entity.setCompare(b);
                    ((HealthQuoteFragment) mContext).addRemoveCompare(entity, b);
                }
                entity.setCompare(false);
            }

            updateCheckBox(entity);*/

        }
    };

}
