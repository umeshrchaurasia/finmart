package com.datacomp.magicfinmart.motor.privatecar.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Rajeev Ranjan on 19/01/2018.
 */

public class PremiumBreakUpAdapter extends RecyclerView.Adapter<PremiumBreakUpAdapter.BreakUpItem> {
    Context context;

    List<PremiumBreakUpAdapterEntity> premiumBreakupEntities;

    public PremiumBreakUpAdapter(Context context, List<PremiumBreakUpAdapterEntity> premiumBreakupEntities) {
        this.context = context;
        this.premiumBreakupEntities = premiumBreakupEntities;
    }

    @Override
    public BreakUpItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_breakup_item, parent, false);
        return new PremiumBreakUpAdapter.BreakUpItem(itemView);
    }

    @Override
    public void onBindViewHolder(final BreakUpItem holder, final int position) {

        final PremiumBreakUpAdapterEntity premiumBreakUpAdapterEntity = premiumBreakupEntities.get(position);
        if (position == (premiumBreakupEntities.size() - 1)) {
            holder.tvValue.setTypeface(null, Typeface.BOLD);
            holder.tvName.setTypeface(null, Typeface.BOLD);
            holder.tvName.setText("" + premiumBreakUpAdapterEntity.getName());
            holder.tvValue.setText("" + getRound(premiumBreakUpAdapterEntity.getValue()));
        } else {
            holder.tvName.setText("" + premiumBreakUpAdapterEntity.getName());
            holder.tvValue.setText("" + getRound(premiumBreakUpAdapterEntity.getValue()));
        }


    }

    private double getDigitPrecision(double value) {
        return Double.parseDouble(new DecimalFormat("##.##").format(value));
    }

    @Override
    public int getItemCount() {
        return premiumBreakupEntities.size();
    }

    public class BreakUpItem extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvValue;

        public BreakUpItem(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvValue = (TextView) itemView.findViewById(R.id.tvValue);
        }
    }

    private double getRound(String strText) {
        try {
            //strText = strText.replace("-", "");
            //strText = strText.replace("-", "");
            double value = Double.parseDouble(strText);
            return Double.parseDouble(new DecimalFormat("##.##").format(value));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
