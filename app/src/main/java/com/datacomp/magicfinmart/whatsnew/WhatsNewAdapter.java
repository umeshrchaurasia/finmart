package com.datacomp.magicfinmart.whatsnew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.WhatsNewEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class WhatsNewAdapter extends RecyclerView.Adapter<WhatsNewAdapter.WhatsNewItem> implements View.OnClickListener {
    Context context;
    List<WhatsNewEntity> whatsNewEntities;

    public WhatsNewAdapter(Context context, List<WhatsNewEntity> list) {
        this.context = context;
        whatsNewEntities = list;
    }

    @Override

    public WhatsNewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_whats_new_item, parent, false);
        return new WhatsNewAdapter.WhatsNewItem(itemView);


    }

    @Override
    public void onBindViewHolder(WhatsNewItem holder, int position) {

        if (holder instanceof WhatsNewItem) {
            final WhatsNewEntity entity = whatsNewEntities.get(position);
            holder.tvTitle.setText("" + entity.getTitle());
            holder.tvDesc.setText("" + entity.getDiscription());
        }
    }


    @Override
    public int getItemCount() {
        return whatsNewEntities.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }

    public class WhatsNewItem extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;

        public WhatsNewItem(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
        }
    }

}
