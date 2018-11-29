package com.datacomp.magicfinmart.health.compare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BenefitsEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;

public class HealthCompareViewAdapter extends RecyclerView.Adapter<HealthCompareViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    Context mContext;
    List<HealthQuoteEntity> listBenefits;

    // data is passed into the constructor
    HealthCompareViewAdapter(Context context, List<HealthQuoteEntity> list) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        listBenefits = list;
    }


    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_benefits, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        HealthQuoteEntity healthQuoteEntity = listBenefits.get(position);

        holder.txtInsurerName.setText(healthQuoteEntity.getInsurerName());
        holder.txtPlanName.setText(healthQuoteEntity.getProductName());

        Glide.with(mContext).load(healthQuoteEntity.getInsurerLogoName())
                .into(holder.imgInsurerLogo);

        for (int i = 0; i < healthQuoteEntity.getLstbenfitsFive().size(); i++) {
            BenefitsEntity entity = healthQuoteEntity.getLstbenfitsFive().get(i);
            if (entity.isSelected()) {
                String benefitsName = entity.getBenefit().replace("\n", "");
                holder.txtBenefitsName.setText(benefitsName);
            }
        }

    }

    @Override
    public int getItemCount() {
        return listBenefits.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBenefitsName, txtInsurerName, txtPlanName;
        ImageView imgInsurerLogo;

        ViewHolder(View itemView) {
            super(itemView);
            txtBenefitsName = (TextView) itemView.findViewById(R.id.txtBenefitsName);
            txtInsurerName = (TextView) itemView.findViewById(R.id.txtInsurerName);
            txtPlanName = (TextView) itemView.findViewById(R.id.txtPlanName);
            imgInsurerLogo = (ImageView) itemView.findViewById(R.id.imgInsurerLogo);
        }


    }

    public void refreshSelection(List<HealthQuoteEntity> list) {
        listBenefits = list;
        notifyDataSetChanged();
    }

}