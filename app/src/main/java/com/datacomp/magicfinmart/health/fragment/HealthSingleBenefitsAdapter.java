package com.datacomp.magicfinmart.health.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BenefitsEntity;

public class HealthSingleBenefitsAdapter extends RecyclerView.Adapter<HealthSingleBenefitsAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    Context mContext;
    List<BenefitsEntity> listBenefits;

    // data is passed into the constructor
    HealthSingleBenefitsAdapter(Context context, List<BenefitsEntity> list) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        listBenefits = list;
    }


    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_single_benefits, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.txtBenefitsDesc.setText(listBenefits.get(position).getBeneDesc());
        holder.txtBenefitsName.setText(listBenefits.get(position).getBenefit());

    }

    @Override
    public int getItemCount() {
        return listBenefits.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtBenefitsDesc, txtBenefitsName;

        ViewHolder(View itemView) {
            super(itemView);
            txtBenefitsName = (TextView) itemView.findViewById(R.id.txtBenefitsName);
            txtBenefitsDesc = (TextView) itemView.findViewById(R.id.txtBenefitsDesc);

        }


    }


}