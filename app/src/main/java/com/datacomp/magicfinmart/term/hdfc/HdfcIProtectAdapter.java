package com.datacomp.magicfinmart.term.hdfc;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

/**
 * Created by IN-RB on 21-05-2018.
 */

public class HdfcIProtectAdapter extends RecyclerView.Adapter<HdfcIProtectAdapter.MyViewHolder> {

    Activity mContext;
    String[] IprotectLst;

    public HdfcIProtectAdapter(Activity mContext, String[] iprotectLst) {
        this.mContext = mContext;
        IprotectLst = iprotectLst;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMessage;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtMessage = (TextView) itemView.findViewById(R.id.txtMessage);
        }
    }


    @Override
    public HdfcIProtectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iprotect_item, parent, false);

        return new HdfcIProtectAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String strMsg = IprotectLst[position];

        holder.txtMessage.setText("" + strMsg);

    }


    @Override
    public int getItemCount() {
        return IprotectLst.length;
    }
}
