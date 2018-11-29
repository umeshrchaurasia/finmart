package com.datacomp.magicfinmart.motor.privatecar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.motor.model.MobileAddOn;

/**
 * Created by IN-RB on 17-11-2017.
 */

public class AddonPopUpAdapter extends RecyclerView.Adapter<AddonPopUpAdapter.AddonItem> {

    Activity context;

    List<MobileAddOn> listMobileAddOn;

    public AddonPopUpAdapter(Activity context, List<MobileAddOn> listMobileAddOn) {
        this.context = context;
        this.listMobileAddOn = listMobileAddOn;
    }

    @Override
    public AddonItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_addon_item, parent, false);
        return new AddonPopUpAdapter.AddonItem(itemView);
    }

    @Override
    public void onBindViewHolder(final AddonItem holder, final int position) {

        final MobileAddOn mobileAddOn = listMobileAddOn.get(position);
        holder.txtAddOneName.setText("" + mobileAddOn.getAddonName());

        if (mobileAddOn.isSelected()) {
            holder.chkAddon.setChecked(true);
        } else {
            holder.chkAddon.setChecked(false);
        }

        holder.chkAddon.setOnCheckedChangeListener(null);

        holder.chkAddon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    holder.chkAddon.setChecked(true);
                    mobileAddOn.setSelected(true);

                } else {
                    holder.chkAddon.setChecked(false);
                    mobileAddOn.setSelected(false);
                }
                //  listMobileAddOn.set(position,mobileAddOn);
                // notifyItemChanged(position,mobileAddOn);


            }
        });

    }

    public List<MobileAddOn> getUpdateMobileAddonList() {
        return listMobileAddOn;
    }

    @Override
    public int getItemCount() {
        return listMobileAddOn.size();
    }

    public class AddonItem extends RecyclerView.ViewHolder {

        public TextView txtAddOneName;
        public CheckBox chkAddon;


        public AddonItem(View itemView) {
            super(itemView);
            txtAddOneName = (TextView) itemView.findViewById(R.id.txtAddOneName);
            chkAddon = (CheckBox) itemView.findViewById(R.id.chkAddon);
        }
    }
}
