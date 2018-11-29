package com.datacomp.magicfinmart.pendingcases;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PendingCasesEntity;

/**
 * Created by Nilesh on 05/02/2018.
 */

public class PendingCasesAdapter extends RecyclerView.Adapter<PendingCasesAdapter.ApplicationItem> implements View.OnClickListener {

    PendingCaseFragment mContex;
    List<PendingCasesEntity> mAppList;
    int type;

    public PendingCasesAdapter(PendingCaseFragment context, List<PendingCasesEntity> list, int type) {
        this.mContex = context;
        this.mAppList = list;
        this.type = type;

    }

    @Override
    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pendingcase, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {

        if (holder instanceof ApplicationItem) {
            ApplicationItem item = (ApplicationItem) holder;
            PendingCasesEntity entity = mAppList.get(position);

            item.txtCustName.setText(entity.getCustomerName());
            item.txtCategory.setText(entity.getCategory());
            item.txtPendingDays.setText(String.valueOf(entity.getPendingdays()));
            if(entity.getCategory().equals("Quick Lead"))
            {
                item.txtquickType.setText(entity.getQatype());
                item.txtType.setVisibility(View.GONE);
                item.txtquickType.setVisibility(View.VISIBLE);
            }else {
                item.txtType.setText(entity.getQatype());
                item.txtType.setVisibility(View.VISIBLE);
                item.txtquickType.setVisibility(View.GONE);
            }


            Glide.with(mContex).load(entity.getBankImage())
                    .into(item.imgInsurerLogo);

            item.txtOverflowMenu.setTag(R.id.txtOverflowMenu, entity);
            item.txtOverflowMenu.setOnClickListener(this);

            item.ivCall.setTag(R.id.ivCall, entity);
            item.ivCall.setOnClickListener(this);

            item.ivDelete.setTag(R.id.ivDelete, entity);
            item.ivDelete.setOnClickListener(this);

            item.ivMsg.setTag(R.id.ivMsg, entity);
            item.ivMsg.setOnClickListener(this);


            item.llHistory.setTag(R.id.llHistory, entity);
            item.llHistory.setOnClickListener(this);

            item.llInfo.setTag(R.id.llInfo, entity);
            item.llInfo.setOnClickListener(this);

            if (type == 2) {
                holder.llHistory.setVisibility(View.VISIBLE);
            } else {
                holder.llHistory.setVisibility(View.INVISIBLE);
            }

            try {

                if(entity.getQatype().equals("Q"))
                {
                    holder.llHistory.setVisibility(View.INVISIBLE);
                }else
                {
                    holder.llHistory.setVisibility(View.VISIBLE);
                }
                /*if (Integer.parseInt(entity.getApplnStatus()) == 0) {
                    item.imgStatus.setImageResource(R.mipmap.status_0);
                } else if (Integer.parseInt(entity.getApplnStatus()) == 25) {
                    item.imgStatus.setImageResource(R.mipmap.status_25);
                } else if (Integer.parseInt(entity.getApplnStatus()) == 50) {
                    item.imgStatus.setImageResource(R.mipmap.status_50);
                } else if (Integer.parseInt(entity.getApplnStatus()) == 100) {
                    item.imgStatus.setImageResource(R.mipmap.status_100);
                }*/

                pgStatus(item.imgStatus, Integer.parseInt(entity.getApplnStatus()));
            } catch (Exception e) {
                item.imgStatus.setImageResource(R.mipmap.status_0);
            }
        }

    }

    public void pgStatus(ImageView imgPg, int status) {
        switch (status) {
            case 0:
                imgPg.setImageResource(R.mipmap.status_0);
                break;
            case 10:
                imgPg.setImageResource(R.mipmap.status_10);
                break;
            case 20:
                imgPg.setImageResource(R.mipmap.status_20);
                break;
            case 25:
                imgPg.setImageResource(R.mipmap.status_25);
                break;
            case 30:
                imgPg.setImageResource(R.mipmap.status_30);
                break;
            case 40:
                imgPg.setImageResource(R.mipmap.status_40);
                break;
            case 50:
                imgPg.setImageResource(R.mipmap.status_50);
                break;
            case 60:
                imgPg.setImageResource(R.mipmap.status_60);
                break;
            case 70:
                imgPg.setImageResource(R.mipmap.status_70);
                break;
            case 80:
                imgPg.setImageResource(R.mipmap.status_80);
                break;
            case 90:
                imgPg.setImageResource(R.mipmap.status_90);
                break;
            case 100:
                imgPg.setImageResource(R.mipmap.status_100);
                break;
            default:
                imgPg.setImageResource(R.mipmap.status_0);
                break;

        }
    }


    private void openPopUp(View v, final PendingCasesEntity entity) {
        //creating a popup menu
        PopupMenu popup = new PopupMenu(mContex.getActivity(), v);
        //inflating menu from xml resource
        popup.inflate(R.menu.recycler_menu_quote);
        //adding click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.menuCall) {
                    ((PendingCaseFragment) mContex).dialNumber(entity.getMobile());
                    //Toast.makeText(mContex, "WIP ", Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {
                    ((PendingCaseFragment) mContex).sendSms(entity.getMobile());
                    //Toast.makeText(mContex, "WIP SMS ", Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuDelete) {
                    ((PendingCaseFragment) mContex).deletePendingcases(entity);

                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }

    @Override
    public void onClick(View view) {
        PendingCasesEntity entity;
        int i = view.getId();
        if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (PendingCasesEntity) view.getTag(R.id.txtOverflowMenu));

        } else if (i == R.id.ivCall) {
            entity = (PendingCasesEntity) view.getTag(R.id.ivCall);
            ((PendingCaseFragment) mContex).dialNumber(entity.getMobile());

        } else if (i == R.id.ivDelete) {
            entity = (PendingCasesEntity) view.getTag(R.id.ivDelete);
            ((PendingCaseFragment) mContex).deletePendingcases(entity);

        } else if (i == R.id.ivMsg) {
            entity = (PendingCasesEntity) view.getTag(R.id.ivMsg);
            ((PendingCaseFragment) mContex).sendSms(entity.getMobile());

        } else if (i == R.id.llHistory) {
            entity = (PendingCasesEntity) view.getTag(R.id.llHistory);
            ((PendingCaseFragment) mContex).openLeadDetailPopUp("" + entity.getApplicationNo());

        } else if (i == R.id.llInfo) {
            entity = (PendingCasesEntity) view.getTag(R.id.llInfo);

        }
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    public class ApplicationItem extends RecyclerView.ViewHolder {

        TextView txtOverflowMenu, txtCustName, txtType,txtquickType, txtCategory, txtPendingDays;
        ImageView imgStatus, imgInsurerLogo, ivMsg, ivCall, ivDelete;
        LinearLayout llHistory, llType, llDays, llInfo;

        public ApplicationItem(View itemView) {
            super(itemView);
            txtOverflowMenu = (TextView) itemView.findViewById(R.id.txtOverflowMenu);
            txtCustName = (TextView) itemView.findViewById(R.id.txtCustName);
            txtCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            txtType = (TextView) itemView.findViewById(R.id.txtType);
            txtquickType = (TextView) itemView.findViewById(R.id.txtquickType);
            txtPendingDays = (TextView) itemView.findViewById(R.id.txtPendingDays);
            imgStatus = (ImageView) itemView.findViewById(R.id.imgStatus);
            imgInsurerLogo = (ImageView) itemView.findViewById(R.id.imgInsurerLogo);

            ivMsg = (ImageView) itemView.findViewById(R.id.ivMsg);
            ivCall = (ImageView) itemView.findViewById(R.id.ivCall);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
            llHistory = (LinearLayout) itemView.findViewById(R.id.llHistory);
            llType = (LinearLayout) itemView.findViewById(R.id.llType);
            llDays = (LinearLayout) itemView.findViewById(R.id.llDays);
            llInfo = (LinearLayout) itemView.findViewById(R.id.llInfo);


        }
    }


    public void refreshAdapter(List<PendingCasesEntity> list) {
        mAppList = list;
    }


}
