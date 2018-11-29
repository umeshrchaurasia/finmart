package com.datacomp.magicfinmart.posp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.ChildPospEntity;

/**
 * Created by Nilesh on 05/02/2018.
 */

public class ChildPospAdapter extends RecyclerView.Adapter<ChildPospAdapter.ApplicationItem> {

    POSPListFragment mContex;
    List<ChildPospEntity> mAppList;
    int type;

    public ChildPospAdapter(POSPListFragment context, List<ChildPospEntity> list) {
        this.mContex = context;
        this.mAppList = list;
    }

    @Override
    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_childposp, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {

        if (holder instanceof ApplicationItem) {
            ApplicationItem item = (ApplicationItem) holder;
            ChildPospEntity entity = mAppList.get(position);

            item.txtCustName.setText(entity.getFullName());
            item.txtEmail.setText(entity.getEmailID());
            item.txtFbaID.setText("" + entity.getFBAID());
            item.txtmobile.setText(entity.getMobiNumb1());

        }

    }


    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public class ApplicationItem extends RecyclerView.ViewHolder {

        TextView txtCustName, txtFbaID, txtmobile, txtEmail;

        public ApplicationItem(View itemView) {
            super(itemView);
            txtCustName = (TextView) itemView.findViewById(R.id.txtCustName);
            txtFbaID = (TextView) itemView.findViewById(R.id.txtFbaID);
            txtmobile = (TextView) itemView.findViewById(R.id.txtmobile);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);

        }
    }

}
