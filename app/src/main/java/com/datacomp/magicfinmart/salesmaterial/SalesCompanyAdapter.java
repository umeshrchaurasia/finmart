package com.datacomp.magicfinmart.salesmaterial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CompanyEntity;


/**
 * Created by IN-RB on 23-02-2018.
 */

public class SalesCompanyAdapter  extends RecyclerView.Adapter<SalesCompanyAdapter.SalesCompanyItem>{

    Context mContex;
    List<CompanyEntity> companyLst;

    public SalesCompanyAdapter(Context mContex, List<CompanyEntity> companyLst) {
        this.mContex = mContex;
        this.companyLst = companyLst;
    }



    public class SalesCompanyItem extends RecyclerView.ViewHolder {


        TextView  txtCompName,txtSelect;
        LinearLayout lyParent;

        public SalesCompanyItem(View itemView) {
            super(itemView);

            txtCompName = (TextView) itemView.findViewById(R.id.txtCompName);
            txtSelect = (TextView) itemView.findViewById(R.id.txtSelect);
            lyParent  = (LinearLayout) itemView.findViewById(R.id.lyParent);


        }
    }



    @Override
    public SalesCompanyAdapter.SalesCompanyItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comp_item, parent, false);
        return new SalesCompanyAdapter.SalesCompanyItem(itemView);
    }

    @Override
    public void onBindViewHolder(final SalesCompanyItem holder, final int position) {

        final CompanyEntity companyEntity = companyLst.get(position);
        holder.txtCompName.setText( "" +companyEntity.getCompany_Name());

        if(companyEntity.isSelected())
        {
            holder.txtSelect.setBackgroundResource(R.color.colorAccent);
        }else{
            holder.txtSelect.setBackgroundResource(R.color.seperator);
        }


        holder.lyParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SalesDetailActivity)mContex).filterDocViaCompID(companyEntity.getCompany_id());
                  companyEntity.setSelected(true);
                  updateList(position);

            }

        });
    }

    public void updateList(int position)
    {
        for(int i = 0; i< companyLst.size(); i++ )
        {
            companyLst.get(i).setSelected(false);
        }
       // companyLst.set(position,companyEntity);     // Direct Update Using Entity
        // notifyItemChanged(position,companyEntity);
        companyLst.get(position).setSelected(true);
        notifyDataSetChanged();


    }


    @Override
    public int getItemCount() {
        return companyLst.size();
    }
}
