package com.datacomp.magicfinmart.healthcheckupplans;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class HealthCheckUPTestDeatilsAdapter extends RecyclerView.Adapter<HealthCheckUPTestDeatilsAdapter.HealthCheckUPItem> {
    Context context;
    List<String> stringList;

    public HealthCheckUPTestDeatilsAdapter(Context context, List<String> list) {
        this.context = context;
        stringList = list;
    }

    @Override

    public HealthCheckUPItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.health_checkup_testdetails_item, parent, false);
        return new HealthCheckUPTestDeatilsAdapter.HealthCheckUPItem(itemView);


    }

    @Override
    public void onBindViewHolder(HealthCheckUPItem holder, int position) {

        if (holder instanceof HealthCheckUPItem) {
            final String entity = stringList.get(position);
            holder.testName.setText("" + entity);
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class HealthCheckUPItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        TextView testName;

        public HealthCheckUPItem(View itemView) {
            super(itemView);
            testName = (TextView) itemView.findViewById(R.id.testName);
        }
    }

}
