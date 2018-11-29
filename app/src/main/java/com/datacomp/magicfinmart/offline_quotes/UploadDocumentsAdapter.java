package com.datacomp.magicfinmart.offline_quotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.RequiredDocEntity;

/**
 * Created by Nilesh on 05/02/2018.
 */

public class UploadDocumentsAdapter extends RecyclerView.Adapter<UploadDocumentsAdapter.ApplicationItem> {

    Context mContex;
    List<RequiredDocEntity> mAppList;


    public UploadDocumentsAdapter(Context context, List<RequiredDocEntity> list) {
        this.mContex = context;
        this.mAppList = list;
    }

    public void updateList(RequiredDocEntity curEntity) {

        for (int pos = 0; pos < mAppList.size(); pos++) {
            if (mAppList.get(pos).getReqid() == (curEntity.getReqid())) {

                mAppList.set(pos,curEntity);

            }
        }

        notifyDataSetChanged();

        //  refreshAdapter(lstSpecial);
    }

    @Override
    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document_upload, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {

        if (holder instanceof ApplicationItem) {
            final ApplicationItem item = (ApplicationItem) holder;
            final RequiredDocEntity entity = mAppList.get(position);
            item.tvDocName.setText(entity.getDocname());

            if(entity.isUploaded() == false)
            {
                holder.ivPhoto.setImageResource(R.drawable.doc_notuploaded);


            }else{
                holder.ivPhoto.setImageResource(R.drawable.doc_uploaded);

            }
            item.rlParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((AddOfflineQuotesActivity) mContex).galleryCamPopUp(entity);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public class ApplicationItem extends RecyclerView.ViewHolder {

        ImageView ivPhoto, ivPhotoCam;
        TextView tvDocName;
        RelativeLayout rlParent;

        public ApplicationItem(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            ivPhotoCam = itemView.findViewById(R.id.ivPhotoCam);
            tvDocName = itemView.findViewById(R.id.tvDocName);
            rlParent = itemView.findViewById(R.id.rlParent);
        }
    }

    @Override
    public long getItemId(int position) {
        return mAppList.get(position).getId();
    }


}
