package com.datacomp.magicfinmart.transactionhistory;

import android.app.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TransctionHistory;

/**
 * Created by IN-RB on 30-10-2018.
 */

public class Mytransactionhistorydapter extends RecyclerView.Adapter<Mytransactionhistorydapter.MytransactionhistoryItem> {

     Activity mContext;
    List<TransctionHistory> myLeadList;

    public Mytransactionhistorydapter(Activity mContext, List<TransctionHistory> myLeadList) {
        mContext = mContext;
        this.myLeadList = myLeadList;

    }


    public class MytransactionhistoryItem extends RecyclerView.ViewHolder{

        public TextView txtCustName,  txtEntryNo, txtCompany ,txtPremium,txtTotal_OD,txtEntryDate;
   //     public TextView txtAddOnPremium ,txtQT_No , txtPOSP_ID,txtCSNo;
        public LinearLayout lyParent;
        public MytransactionhistoryItem(View itemView) {
            super(itemView);
            txtCustName = (TextView)itemView.findViewById(R.id.txtCustName);
            txtEntryNo = (TextView)itemView.findViewById(R.id.txtEntryNo);
            txtCompany = (TextView)itemView.findViewById(R.id.txtCompany);
          //  txtProduct = (TextView)itemView.findViewById(R.id.txtProduct);
            txtPremium =  (TextView)itemView.findViewById(R.id.txtPremium);
        //    txtAddOnPremium = (TextView)itemView.findViewById(R.id.txtAddOnPremium);

            txtEntryDate = (TextView)itemView.findViewById(R.id.txtEntryDate);
        //    txtQT_No = (TextView)itemView.findViewById(R.id.txtQT_No);
            txtTotal_OD = (TextView)itemView.findViewById(R.id.txtTotal_OD);
      //      txtPOSP_ID = (TextView)itemView.findViewById(R.id.txtPOSP_ID);
      //      txtCSNo =  (TextView)itemView.findViewById(R.id.txtCSNo);

        }
    }


    @Override
    public MytransactionhistoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_transactionhistory,parent,false);
        return new Mytransactionhistorydapter.MytransactionhistoryItem(itemView);
    }

    @Override
    public void onBindViewHolder(MytransactionhistoryItem holder, int position) {
        final  TransctionHistory myLeadlstRptEntity = myLeadList.get(position);
        holder.txtCustName.setText(""+myLeadlstRptEntity.getProductName().toUpperCase());
        holder.txtEntryNo.setText(""+ myLeadlstRptEntity.getEntryNo());
        holder.txtCompany.setText(""+myLeadlstRptEntity.getInsCompany());
        holder.txtPremium.setText(""+myLeadlstRptEntity.getPremium());
     //   holder.txtAddOnPremium.setText(""+myLeadlstRptEntity.getAddOnPremium());

        holder.txtEntryDate.setText(""+myLeadlstRptEntity.getEntryDate());
  //      holder.txtQT_No.setText(""+myLeadlstRptEntity.getQT_No());
        holder.txtTotal_OD.setText(""+myLeadlstRptEntity.getTotal_OD());

 //       holder.txtPOSP_ID.setText(""+myLeadlstRptEntity.getPOSP_ID());
   //     holder.txtCSNo.setText(""+myLeadlstRptEntity.getCSNo());
    }

    @Override
    public int getItemCount() {
        return myLeadList.size();
    }

}

