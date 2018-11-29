package com.datacomp.magicfinmart.healthcheckupplans;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthPackDetailsDBean;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.LstPackageDetailsEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class HealthCheckUPAdapter extends RecyclerView.Adapter<HealthCheckUPAdapter.HealthCheckUPItem> implements View.OnClickListener, Filterable {
    Context context;
    List<LstPackageDetailsEntity> lstPackageDetailsEntities;
    List<LstPackageDetailsEntity> lstPackageDetailsEntitiesFiltered;
    DBPersistanceController dbPersistanceController;
    Realm realm;

    public HealthCheckUPAdapter(Context context, List<LstPackageDetailsEntity> list) {
        this.context = context;
        lstPackageDetailsEntities = list;
        lstPackageDetailsEntitiesFiltered = list;
        dbPersistanceController = new DBPersistanceController(context);
        realm = Realm.getDefaultInstance();
    }

    @Override

    public HealthCheckUPItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.health_checkup_item, parent, false);
        return new HealthCheckUPAdapter.HealthCheckUPItem(itemView);


    }

    @Override
    public void onBindViewHolder(final HealthCheckUPItem holder, int position) {

        if (holder instanceof HealthCheckUPItem) {

            final LstPackageDetailsEntity entity = lstPackageDetailsEntitiesFiltered.get(position);
            if (entity != null) {
                holder.tvPackName.setText(entity.getPackName());

                holder.tvCount.setText(entity.getCnt() + " Tests");
                holder.tvMrp.setText("\u20B9 " + entity.getMRP());
                holder.tvMrp.setPaintFlags(holder.tvMrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.tvOfferPrice.setText("\u20B9 " + entity.getOfferPrice());
            }
            if (entity.isVisible()) {
                holder.rvHealthCheckUpDetails.setVisibility(View.VISIBLE);
                holder.ivArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.up_arrow_blue));
                holder.ivShare.setVisibility(View.VISIBLE);
            } else {
                holder.rvHealthCheckUpDetails.setVisibility(View.GONE);
                holder.ivArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow_grey));
                holder.ivShare.setVisibility(View.GONE);
            }

            //click listener
            holder.tvCount.setOnClickListener(this);
            holder.ivArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (entity.isVisible()) {
                        holder.rvHealthCheckUpDetails.setVisibility(View.GONE);
                        updateVisisbility(entity, false);
                        holder.ivArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow_grey));
                        holder.ivShare.setVisibility(View.GONE);

                    } else {
                        holder.ivArrow.setImageDrawable(context.getResources().getDrawable(R.drawable.up_arrow_blue));
                        holder.ivShare.setVisibility(View.VISIBLE);
                        updateVisisbility(entity, true);
                        HealthPackDetailsDBean healthPackDetailsDBean = null;
                        if (!entity.getPackCode().equals("")) {
                            healthPackDetailsDBean = dbPersistanceController.getHealthCheckUPPlansDetails(Integer.parseInt(entity.getPackCode()));
                        }
                        if (healthPackDetailsDBean != null) {
                            holder.rvHealthCheckUpDetails.setVisibility(View.VISIBLE);
                            HealthCheckUPDetailsAdapter healthCheckUPDetailsAdapter = new HealthCheckUPDetailsAdapter(context, healthPackDetailsDBean.getLstPackParameter());
                            holder.rvHealthCheckUpDetails.setAdapter(healthCheckUPDetailsAdapter);
                        }
                    }

                }
            });
            holder.ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((HealthCheckUpPlansActivity) context).shareWhatsApp();
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return lstPackageDetailsEntitiesFiltered.size();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.txtCrnNo || i == R.id.txtQuoteDate || i == R.id.txtVehicleName || i == R.id.llDetails) {
        }
    }

    public class HealthCheckUPItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        TextView tvPackName, tvCount, tvMrp, tvOfferPrice;
        ImageView ivArrow, ivShare;
        RecyclerView rvHealthCheckUpDetails;

        public HealthCheckUPItem(View itemView) {
            super(itemView);
            tvPackName = (TextView) itemView.findViewById(R.id.tvPackName);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            tvMrp = (TextView) itemView.findViewById(R.id.tvMrp);
            tvOfferPrice = (TextView) itemView.findViewById(R.id.tvOfferPrice);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
            ivShare = (ImageView) itemView.findViewById(R.id.ivShare);
            rvHealthCheckUpDetails = (RecyclerView) itemView.findViewById(R.id.rvHealthCheckUpDetails);
            rvHealthCheckUpDetails.setLayoutManager(new GridLayoutManager(context, 2));
        }
    }

    public void refreshAdapter(List<LstPackageDetailsEntity> list) {
        lstPackageDetailsEntities = list;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    lstPackageDetailsEntitiesFiltered = lstPackageDetailsEntities;
                } else {
                    List<LstPackageDetailsEntity> filteredList = new ArrayList<>();
                    for (LstPackageDetailsEntity row : lstPackageDetailsEntities) {
                        /*BikeMasterEntity carMasterEntity = new BikeMasterEntity();
                        try {

                            carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                                    .getBikeVarientDetails(
                                            "" + row.getMotorRequestEntity().getVehicle_id());

                        } catch (Exception e) {

                        }
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMotorRequestEntity().getFirst_name().toLowerCase().contains(charString.toLowerCase())
                                || row.getMotorRequestEntity().getLast_name().toLowerCase().contains(charString.toLowerCase())
                                || carMasterEntity.getMake_Name().toLowerCase().contains(charString.toLowerCase())
                                || carMasterEntity.getModel_Name().toLowerCase().contains(charString.toLowerCase())
                                || String.valueOf(row.getMotorRequestEntity().getCrn()).contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }*/
                    }

                    lstPackageDetailsEntitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = lstPackageDetailsEntitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                lstPackageDetailsEntitiesFiltered = (ArrayList<LstPackageDetailsEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void updateVisisbility(final LstPackageDetailsEntity lstPackageDetailsEntity, final boolean isVisible) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                lstPackageDetailsEntity.setVisible(isVisible);
            }
        });
    }


}
