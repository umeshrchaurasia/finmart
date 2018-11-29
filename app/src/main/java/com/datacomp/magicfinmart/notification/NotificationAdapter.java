package com.datacomp.magicfinmart.notification;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.NotificationEntity;

/**
 * Created by IN-RB on 22-02-2018.
 */

public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.NotificationItem> {

    Activity mContext;
    List<NotificationEntity>  NotificationLst;

    public NotificationAdapter(Activity mContext, List<NotificationEntity> notificationLst) {
        this.mContext = mContext;
        NotificationLst = notificationLst;
    }

    public class NotificationItem extends RecyclerView.ViewHolder
    {
        public TextView txtTitle , txtMessage,txtDate ,txtStatus,txtbar;
        public ImageView ivNotify;
        public LinearLayout lyParent;
        public NotificationItem(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtMessage = (TextView)itemView.findViewById(R.id.txtMessage);
            txtDate = (TextView)itemView.findViewById(R.id.txtDate);
            txtStatus = (TextView)itemView.findViewById(R.id.txtStatus);
            txtbar = (TextView)itemView.findViewById(R.id.txtbar);
            ivNotify = (ImageView) itemView.findViewById(R.id.ivNotify);
            lyParent = (LinearLayout) itemView.findViewById(R.id.lyParent);

        }
    }


    @Override
    public NotificationAdapter.NotificationItem onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.push_notify_item, parent, false);

        return new NotificationAdapter.NotificationItem(itemView);

    }

    @Override
    public void onBindViewHolder(NotificationItem holder, int position) {

        final NotificationEntity notificationEntity = NotificationLst.get(position);
        holder.txtTitle.setText( "" +notificationEntity.getTitle());
        holder.txtMessage.setText( "" +notificationEntity.getBody());
        holder.txtDate.setText( "" +notificationEntity.getDate());
        Glide.with(mContext)
                .load(notificationEntity.getImg_url())
                .placeholder(R.drawable.notification_ic) // can also be a drawable
                .into(holder.ivNotify);

        holder.lyParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((NotificationActivity)mContext).redirectToApplyLoan(notificationEntity);
            }

        });
    }


    @Override
    public int getItemCount() {
        return NotificationLst.size();
    }
}
