package com.datacomp.magicfinmart.healthcheckupplans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.LstPackParameterEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class HealthCheckUPDetailsAdapter extends RecyclerView.Adapter<HealthCheckUPDetailsAdapter.HealthCheckUPItem> implements View.OnClickListener {
    Context context;
    List<LstPackParameterEntity> lstPackageDetailsEntities;

    public HealthCheckUPDetailsAdapter(Context context, List<LstPackParameterEntity> list) {
        this.context = context;
        lstPackageDetailsEntities = list;
    }

    @Override

    public HealthCheckUPItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.health_checkup_details_item, parent, false);
        return new HealthCheckUPDetailsAdapter.HealthCheckUPItem(itemView);


    }

    @Override
    public void onBindViewHolder(HealthCheckUPItem holder, int position) {

        if (holder instanceof HealthCheckUPItem) {
            final LstPackParameterEntity entity = lstPackageDetailsEntities.get(position);
            final List<String> list = entity.getParamDetails();
            final String[] array = list.toArray(new String[list.size()]);
            if (entity != null) {
                holder.tvDetails.setText(entity.getName());
                if (entity.getParamDetails().size() > 0) {
                    holder.tvDetailsCount.setVisibility(View.VISIBLE);
                    holder.tvDetailsCount.setText(" - " + entity.getParamDetails().size() + " Tests");
                } else {
                    holder.tvDetailsCount.setVisibility(View.GONE);
                }
            }
            //click listener
            holder.tvDetailsCount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, HealthCheckUpPopUpActivity.class).putExtra("TEST_DETAILS", array));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return lstPackageDetailsEntities.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    public class HealthCheckUPItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        TextView tvDetailsCount, tvDetails;

        public HealthCheckUPItem(View itemView) {
            super(itemView);
            tvDetails = (TextView) itemView.findViewById(R.id.tvDetails);
            tvDetailsCount = (TextView) itemView.findViewById(R.id.tvDetailsCount);
        }
    }

}
