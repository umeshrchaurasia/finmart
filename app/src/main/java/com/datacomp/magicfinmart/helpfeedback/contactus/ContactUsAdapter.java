package com.datacomp.magicfinmart.helpfeedback.contactus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.webviews.CommonWebViewActivity;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ContactUsEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.WhatsNewItem> implements View.OnClickListener {
    Context context;
    List<ContactUsEntity> whatsNewEntities;

    public ContactUsAdapter(Context context, List<ContactUsEntity> list) {
        this.context = context;
        whatsNewEntities = list;
    }

    @Override

    public WhatsNewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_contactus_item, parent, false);
        return new ContactUsAdapter.WhatsNewItem(itemView);


    }

    @Override
    public void onBindViewHolder(WhatsNewItem holder, int position) {

        if (holder instanceof WhatsNewItem) {
            final ContactUsEntity entity = whatsNewEntities.get(position);
            holder.tvTitle.setText("" + entity.getHeader());
            holder.tvSupportNo.setText("" + entity.getPhoneNo());
            holder.tvEmail.setText("" + entity.getEmail());
            holder.tvWebsite.setText("" + entity.getWebsite());
            holder.tvDisplayTitle.setText("" + entity.getDisplayTitle());

            holder.tvSupportNo.setTag(R.id.tvSupportNo, entity);
            holder.tvEmail.setTag(R.id.tvEmail, entity);
            holder.tvWebsite.setTag(R.id.tvWebsite, entity);

            holder.tvWebsite.setOnClickListener(this);
            holder.tvEmail.setOnClickListener(this);
            holder.tvSupportNo.setOnClickListener(this);
        }
    }


    @Override
    public int getItemCount() {
        return whatsNewEntities.size();
    }

    @Override
    public void onClick(View view) {
        ContactUsEntity contactUsEntity;
        int i = view.getId();
        if (i == R.id.tvWebsite) {
            contactUsEntity = (ContactUsEntity) view.getTag(R.id.tvWebsite);
            context.startActivity(new Intent(context, CommonWebViewActivity.class)
                    .putExtra("URL", "http://" + contactUsEntity.getWebsite())
                    .putExtra("NAME", "MAGIC FIN-MART")
                    .putExtra("TITLE", contactUsEntity.getHeader()));

        } else if (i == R.id.tvEmail) {
            contactUsEntity = (ContactUsEntity) view.getTag(R.id.tvEmail);
            ((ContactUsActivity) context).composeEmail(contactUsEntity.getEmail(), "");


        } else if (i == R.id.tvSupportNo) {
            contactUsEntity = (ContactUsEntity) view.getTag(R.id.tvSupportNo);
            ((ContactUsActivity) context).dialNumber(contactUsEntity.getPhoneNo());

        }
    }

    public class WhatsNewItem extends RecyclerView.ViewHolder {

        TextView tvTitle, tvSupportNo, tvEmail, tvWebsite, tvDisplayTitle;

        public WhatsNewItem(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvSupportNo = (TextView) itemView.findViewById(R.id.tvSupportNo);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvWebsite = (TextView) itemView.findViewById(R.id.tvWebsite);
            tvDisplayTitle = (TextView) itemView.findViewById(R.id.tvDisplayTitle);
        }
    }

}
