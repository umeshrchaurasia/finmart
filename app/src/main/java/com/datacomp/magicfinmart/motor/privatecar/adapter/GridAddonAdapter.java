package com.datacomp.magicfinmart.motor.privatecar.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.motor.model.AppliedAddonsPremiumBreakup;
import magicfinmart.datacomp.com.finmartserviceapi.motor.model.ResponseEntity;

/**
 * Created by IN-RB on 17-11-2017.
 */

public class GridAddonAdapter extends RecyclerView.Adapter<GridAddonAdapter.AddonItem> {

    Activity context;
    ResponseEntity mResponseEntity;
    List<AppliedAddonsPremiumBreakup> listAppliedAddon;

    public GridAddonAdapter(Activity context, List<AppliedAddonsPremiumBreakup> list, ResponseEntity responseEntity) {
        this.context = context;
        this.listAppliedAddon = list;
        this.mResponseEntity = responseEntity;
    }

    @Override
    public AddonItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_grid_addon, parent, false);
        return new GridAddonAdapter.AddonItem(itemView);
    }

    @Override
    public void onBindViewHolder(final AddonItem holder, final int position) {

        if (holder instanceof AddonItem) {
            holder.llAddonName.setTag(R.id.llAddonName, mResponseEntity);
            holder.addonName.setText("" + listAppliedAddon.get(position).getAddonName()
                    + " ( " + "\u20B9" + String.valueOf(Math.round(listAppliedAddon.get(position).getPriceAddon()) + ")"));

        }

    }

    @Override
    public int getItemCount() {
        return listAppliedAddon.size();
    }

    public class AddonItem extends RecyclerView.ViewHolder {

        public TextView addonName;
        LinearLayout llAddonName;

        public AddonItem(View itemView) {
            super(itemView);
            llAddonName = (LinearLayout) itemView.findViewById(R.id.llAddonName);
            addonName = (TextView) itemView.findViewById(R.id.addonName);
        }
    }
}
