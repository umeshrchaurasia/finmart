package com.datacomp.magicfinmart.term.compareterm.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

/**
 * Created by IN-RB on 17-11-2017.
 */

public class GridTermAdapter extends RecyclerView.Adapter<GridTermAdapter.AddonItem> {

    Activity context;

    String[] listAppliedAddon;

    public GridTermAdapter(Activity context, String[] list) {
        this.context = context;
        this.listAppliedAddon = list;
    }

    @Override
    public AddonItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_grid_addon, parent, false);
        return new GridTermAdapter.AddonItem(itemView);
    }

    @Override
    public void onBindViewHolder(final AddonItem holder, final int position) {

        if (holder instanceof AddonItem) {
            holder.addonName.setText("" + listAppliedAddon[position]);
           /* holder.addonName.setText("" + listAppliedAddon.get(position).getAddonName()
                    + " ( " + "\u20B9" + String.valueOf(Math.round(listAppliedAddon.get(position).getPriceAddon()) + ")"));*/
        }

    }

    @Override
    public int getItemCount() {
        return listAppliedAddon.length;
    }

    public class AddonItem extends RecyclerView.ViewHolder {

        public TextView addonName;

        public AddonItem(View itemView) {
            super(itemView);
            addonName = (TextView) itemView.findViewById(R.id.addonName);
        }
    }
}
