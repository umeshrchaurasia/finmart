package com.datacomp.magicfinmart.term.termselection;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.term.quoteapp.TermQuoteApplicationActivity;
import com.datacomp.magicfinmart.utility.Constants;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.model.TermSelectionEntity;

public class TermSelectionItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<TermSelectionEntity> listInsur;

    public TermSelectionItemAdapter(Context context) {
        mContext = context;
        listInsur = new DBPersistanceController(mContext).getTermCompanyList();
    }

    public class DashboardItemHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtProductName, txtProductDesc;
        CardView card_view;

        public DashboardItemHolder(View view) {
            super(view);
            card_view = (CardView) view.findViewById(R.id.card_view);
            imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            txtProductDesc = (TextView) view.findViewById(R.id.txtProductDesc);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_term_selection_item, parent, false);
        return new DashboardItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DashboardItemHolder) {
            final TermSelectionEntity termSelectionEntity = listInsur.get(position);
            if (termSelectionEntity.getCompantID() == 39)
                ((DashboardItemHolder) holder).imgIcon.setImageResource(R.drawable.icici_life_icon);
            else if (termSelectionEntity.getCompantID() == 28)
                ((DashboardItemHolder) holder).imgIcon.setImageResource(R.drawable.hdfc_life_icon);
            else if (termSelectionEntity.getCompantID() == 0)
                ((DashboardItemHolder) holder).imgIcon.setImageResource(R.drawable.compare_term_insurance_icon);
            else
                ((DashboardItemHolder) holder).imgIcon.setImageResource(R.drawable.icici_life_icon);

            ((DashboardItemHolder) holder).txtProductName.setText(termSelectionEntity.getCompanyName());
            ((DashboardItemHolder) holder).card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, TermQuoteApplicationActivity.class).putExtra(Constants.LIFE_INS, termSelectionEntity.getCompantID()));
                    new TrackingController(mContext).sendData(new TrackingRequestEntity(new TrackingData(termSelectionEntity.getCompanyName() + " Selection"), Constants.LIFE_INS), null);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listInsur.size();
    }
}