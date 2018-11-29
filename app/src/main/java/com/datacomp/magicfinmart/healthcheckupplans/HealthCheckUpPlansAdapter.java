package com.datacomp.magicfinmart.healthcheckupplans;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthCEntity;

public class HealthCheckUpPlansAdapter extends RecyclerView.Adapter<HealthCheckUpPlansAdapter.ViewHolder>
        implements Filterable {

    private LayoutInflater mInflater;
    Context mContext;
    List<HealthCEntity> listHealthCheck;
    List<HealthCEntity> listHealthCheckFiltered;

    // data is passed into the constructor
    HealthCheckUpPlansAdapter(Context context, List<HealthCEntity> listHealthCheck) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.listHealthCheck = listHealthCheck;
        this.listHealthCheckFiltered = listHealthCheck;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_health_checkup_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        HealthCEntity entity = listHealthCheckFiltered.get(position);

        holder.txtName.setText(entity.getFirstName() + " " + entity.getLastName());
        holder.txtMobileNo.setText("" + entity.getMobile());
        holder.txtPackageName.setText(entity.getPackName());

        holder.txtMRP.setText("\u20B9" + String.valueOf(entity.getMRP()));

        holder.txtStatus.setText(entity.getStatus());


    }


    @Override
    public int getItemCount() {
        if (listHealthCheckFiltered != null)
            return listHealthCheckFiltered.size();
        else
            return 0;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtMobileNo, txtPackageName, txtMRP, txtStatus;

        ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMobileNo = itemView.findViewById(R.id.txtMobileNo);
            txtPackageName = itemView.findViewById(R.id.txtPackageName);
            txtMRP = itemView.findViewById(R.id.txtMRP);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listHealthCheckFiltered = listHealthCheck;
                } else {

                    List<HealthCEntity> filteredList = new ArrayList<>();
                    for (HealthCEntity row : listHealthCheck) {
                        if (row.getFirstName() != null) {
                            if (row.getFirstName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        if (row.getLastName() != null) {
                            if (row.getLastName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }

                        if (row.getMobile() != null) {
                            if (row.getMobile().toString().contains(charString)) {
                                filteredList.add(row);
                            }
                        }
                    }
                    listHealthCheckFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listHealthCheckFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                listHealthCheckFiltered = (ArrayList<HealthCEntity>) filterResults.values;
                if (listHealthCheckFiltered != null)
                    notifyDataSetChanged();
            }
        }

                ;
    }

}