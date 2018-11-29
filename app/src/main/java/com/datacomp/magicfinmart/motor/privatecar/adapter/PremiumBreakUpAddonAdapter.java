package com.datacomp.magicfinmart.motor.privatecar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.motor.privatecar.activity.PremiumBreakUpActivity;

import java.text.DecimalFormat;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.motor.model.MobileAddOn;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.PremiumBreakUpAddonEntity;

/**
 * Created by Rajeev Ranjan on 19/01/2018.
 */

public class PremiumBreakUpAddonAdapter extends RecyclerView.Adapter<PremiumBreakUpAddonAdapter.BreakUpItem> {
    Context context;

    List<PremiumBreakUpAddonEntity> premiumBreakupEntities;
    List<MobileAddOn> listMobileAddOn;

    public PremiumBreakUpAddonAdapter(Context context, List<PremiumBreakUpAddonEntity> premiumBreakupEntities, List<MobileAddOn> listMobileAddOn) {
        this.context = context;
        this.premiumBreakupEntities = premiumBreakupEntities;
        this.listMobileAddOn = listMobileAddOn;
    }

    @Override
    public BreakUpItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_breakup_addon_item, parent, false);
        return new PremiumBreakUpAddonAdapter.BreakUpItem(itemView);
    }

    @Override
    public void onBindViewHolder(final BreakUpItem holder, final int position) {

        final PremiumBreakUpAddonEntity premiumBreakUpAdapterEntity = premiumBreakupEntities.get(position);
        final MobileAddOn mobileAddOn = listMobileAddOn.get(position);
        /*if (position == (premiumBreakupEntities.size() - 1)) {
            holder.tvValue.setTypeface(null, Typeface.BOLD);
            holder.tvName.setTypeface(null, Typeface.BOLD);
            holder.tvName.setText("" + premiumBreakUpAdapterEntity.getName());
            holder.tvValue.setText("" + getRound(premiumBreakUpAdapterEntity.getValue()));
        } else {
            holder.tvName.setText("" + premiumBreakUpAdapterEntity.getName());
            holder.tvValue.setText("" + getRound(premiumBreakUpAdapterEntity.getValue()));
        }*/

        holder.tvName.setText("" + premiumBreakUpAdapterEntity.getName());
        holder.tvValue.setText("" + getRound(premiumBreakUpAdapterEntity.getValue()));

        /*if (position == (premiumBreakupEntities.size() - 1)) {
            holder.chbxAddon.setVisibility(View.INVISIBLE);
        } else {
            holder.chbxAddon.setVisibility(View.VISIBLE);
        }*/
        if (premiumBreakUpAdapterEntity.isSelected) {
            holder.chbxAddon.setChecked(true);
        } else {
            holder.chbxAddon.setChecked(false);
        }
        holder.chbxAddon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (int i = 0; i < listMobileAddOn.size(); i++) {
                        if (premiumBreakUpAdapterEntity.getName().toLowerCase()
                                .equals(listMobileAddOn.get(i).getAddonName().toLowerCase())) {
                            listMobileAddOn.get(i).setSelected(true);
                            premiumBreakupEntities.get(position).setSelected(true);
                        }
                    }

                    //mobileAddOn.setSelected(true);
                    ((PremiumBreakUpActivity) context).updateAddonList(premiumBreakupEntities);
                    ((PremiumBreakUpActivity) context).applyPositiveAddons(listMobileAddOn);
                    ((PremiumBreakUpActivity) context).updateAddonToserver(listMobileAddOn);
                } else {
                    for (int i = 0; i < listMobileAddOn.size(); i++) {
                        if (premiumBreakUpAdapterEntity.getName().toLowerCase()
                                .equals(listMobileAddOn.get(i).getAddonName().toLowerCase())) {
                            listMobileAddOn.get(i).setSelected(false);
                            premiumBreakupEntities.get(position).setSelected(false);
                        }
                    }
                    //mobileAddOn.setSelected(false);
                    ((PremiumBreakUpActivity) context).updateAddonList(premiumBreakupEntities);
                    ((PremiumBreakUpActivity) context).applyPositiveAddons(listMobileAddOn);
                    ((PremiumBreakUpActivity) context).updateAddonToserver(listMobileAddOn);

                }
            }
        });

    }

    public List<MobileAddOn> getUpdateMobileAddonList() {
        return listMobileAddOn;
    }


    @Override
    public int getItemCount() {
        return premiumBreakupEntities.size();
    }

    public class BreakUpItem extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvValue;
        CheckBox chbxAddon;

        public BreakUpItem(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvValue = (TextView) itemView.findViewById(R.id.tvValue);
            chbxAddon = (CheckBox) itemView.findViewById(R.id.chbxAddon);
        }
    }

    private double getRound(String strText) {
        double value =Double.parseDouble(strText);
        return Double.parseDouble(new DecimalFormat("##.##").format(value));
    }
}
