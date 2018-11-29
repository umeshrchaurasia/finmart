package com.datacomp.magicfinmart.share_data;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.ShareMessageResponse;

public class ShareMessageAdapter extends RecyclerView.Adapter<ShareMessageAdapter.MyViewHolder> {

    Fragment mContext;
    List<ShareMessageResponse.LstShareMessageEntity> lstShareMessageEntities;
    String lnk = "";

    public ShareMessageAdapter(Fragment context, List<ShareMessageResponse.LstShareMessageEntity> lstShareMessageEntities) {
        this.mContext = context;
        this.lstShareMessageEntities = lstShareMessageEntities;
        //listHistory = list;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtStatus, txtRemark;
        ImageView btnCall;
        //LinearLayout linearLayout;


        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) itemView.findViewById(R.id.txtFollowupName);
            txtStatus = (TextView) itemView.findViewById(R.id.txtFollowupStatus);
            txtRemark = (TextView) itemView.findViewById(R.id.txtFollowupRemark);
            btnCall = (ImageView) itemView.findViewById(R.id.btnCall);
            //linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_followup_item);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sharemsg_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ShareMessageAdapter.MyViewHolder holder, int position) {
        final ShareMessageResponse.LstShareMessageEntity lstShareMessageEntity = lstShareMessageEntities.get(position);
        // lnk = "http://staging.rupeeboss.com/balance-transfer?empcode="+ Utility.EmpCode;
        holder.txtName.setText(lstShareMessageEntity.getTitle());
        holder.txtStatus.setText(lstShareMessageEntity.getMsgBody());
        holder.txtRemark.setText(lstShareMessageEntity.getLink());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datashareList(lstShareMessageEntity.getTitle(), lstShareMessageEntity.getMsgBody(), lstShareMessageEntity.getLink());
            }
        });


    }

    @Override
    public int getItemCount() {
        return lstShareMessageEntities.size();
    }

    private void datashareList(String Title, String Bodymsg, String link) {


        String Deeplink;
        //"Look! This can make you look gorgeous from Nykaa";
        Deeplink = Bodymsg + "\n" + link;
        String prdSubject = "Magic Finmart";

        String prdDetail = Deeplink;


        try {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);

            shareIntent.setType("text/plain");

            PackageManager pm = mContext.getActivity().getPackageManager();


            List<ResolveInfo> resInfo = pm.queryIntentActivities(shareIntent, 0);
            List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
            ///////////
            for (int i = 0; i < resInfo.size(); i++) {
                // Extract the label, append it, and repackage it in a LabeledIntent
                ResolveInfo ri = resInfo.get(i);
                String packageName = ri.activityInfo.packageName;
                String processName = ri.activityInfo.processName;
                String AppName = ri.activityInfo.name;

                if ((packageName.contains("android.email") || packageName.contains("mms") || packageName.contains("twitter") || (packageName.contains("whatsapp")) || (packageName.contains("facebook.katana")) || (packageName.contains("facebook.orca")) || packageName.contains("messaging") || packageName.contains("android.gm") || packageName.contains("com.google.android.apps.plus")) || (packageName.contains("apps.docs")) && processName.contains("android.apps.docs:Clipboard") || (packageName.contains("android.talk")) && AppName.contains("hangouts")) {

                    shareIntent.setComponent(new ComponentName(packageName, ri.activityInfo.name));

                    if (packageName.contains("android.email")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("twitter")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("facebook.katana")) {

                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage("com.facebook.katana");

                    } else if (packageName.contains("facebook.orca")) {

                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage("com.facebook.orca");

                    } else if (packageName.contains("mms")) {

                        shareIntent.setPackage(packageName);

                    } else if (packageName.contains("whatsapp")) {

                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prdDetail);
                        shareIntent.setPackage(packageName);


                    } else if (packageName.contains("messaging")) {
                        shareIntent.setPackage(packageName);
                    } else if (packageName.contains("com.google.android.apps.plus")) {
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    }
//                    else if (packageName.contains("android.talk")) {
//                        if (AppName.contains("hangouts")) {
//
//                            shareIntent.setPackage(packageName);
//                        }
//
//                    }
                    else if (packageName.contains("apps.docs")) {
                        if (processName.contains("android.apps.docs:Clipboard")) {

                            shareIntent.setPackage(packageName);
                        }

                    } else if (packageName.contains("android.gm")) {
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, prdSubject);
                        shareIntent.setPackage(packageName);

                    }

                    intentList.add(new LabeledIntent(shareIntent, packageName, ri.loadLabel(pm), ri.icon));

                }
            }


            if (intentList.size() > 1) {
                intentList.remove(intentList.size() - 1);
            }

            Intent openInChooser = Intent.createChooser(shareIntent, "Share Via");

            // convert intentList to array
            LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
            openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);

            mContext.startActivity(openInChooser);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}