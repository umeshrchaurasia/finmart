package com.datacomp.magicfinmart.loan_fm.popup;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.LeadEntity;

/**
 * Created by IN-RB on 26-06-2018.
 */

public class LeadInfoPopupAdapter  extends RecyclerView.Adapter<LeadInfoPopupAdapter.LeadItem>{
    Activity mContext;
    List<LeadEntity> LeadLst;

    public LeadInfoPopupAdapter(Activity mContext, List<LeadEntity> leadLst) {
        this.mContext = mContext;
        LeadLst = leadLst;
    }

    public class LeadItem extends RecyclerView.ViewHolder{

        public TextView txtUpdatedBy , txtStatus,txtFollowUpDate ,txtUpdatepDate,txtRemark;

        public LeadItem(View itemView) {
            super(itemView);

            txtUpdatedBy = (TextView) itemView.findViewById(R.id.txtUpdatedBy);
            txtStatus = (TextView)itemView.findViewById(R.id.txtStatus);
            txtFollowUpDate = (TextView)itemView.findViewById(R.id.txtFollowUpDate);
            txtUpdatepDate = (TextView)itemView.findViewById(R.id.txtUpdatepDate);
            txtRemark = (TextView)itemView.findViewById(R.id.txtRemark);


        }
    }

    @Override
    public LeadInfoPopupAdapter.LeadItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lead_detail_item, parent, false);

        return new LeadInfoPopupAdapter.LeadItem(itemView);

    }

    @Override
    public void onBindViewHolder(LeadItem holder, int position) {

        final LeadEntity leadEntity = LeadLst.get(position);
        holder.txtUpdatedBy.setText( "" +leadEntity.getUpdatedBy());
        holder.txtStatus.setText( "" +leadEntity.getStatus());

        holder.txtFollowUpDate.setText( "" +leadEntity.getFollowupDate());
        holder.txtUpdatepDate.setText( "" +leadEntity.getUpdatedDate());
        holder.txtRemark.setText( "" +leadEntity.getRemark());


    }



    @Override
    public int getItemCount() {
        return LeadLst.size();
    }
}
