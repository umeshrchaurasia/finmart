package com.datacomp.magicfinmart.loan_fm.homeloan;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.homeloan.application.HL_ApplicationFragment;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;

/**
 * Created by IN-RB on 19-01-2018.
 */

public class HomeLoanApplicationAdapter extends RecyclerView.Adapter<HomeLoanApplicationAdapter.ApplicationItem> implements Filterable {

    Fragment fragment;
    List<FmHomeLoanRequest> mAppList;
    List<FmHomeLoanRequest> mAppListFiltered;

    public HomeLoanApplicationAdapter(Fragment context, List<FmHomeLoanRequest> mApplicationList) {
        this.fragment = context;
        mAppList = mApplicationList;
        mAppListFiltered = mApplicationList;
    }

    @Override
    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_application_loan, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {
        if (holder instanceof ApplicationItem) {

            final FmHomeLoanRequest entity = mAppListFiltered.get(position);
           // entity.getHomeLoanRequest().setApplNumb("18072");
            if (entity.getHomeLoanRequest().getApplNumb() != null) {
                holder.txtApplicationNumber.setText("" + String.valueOf(entity.getHomeLoanRequest().getApplNumb()));
            } else {
                holder.txtApplicationNumber.setText("0");
            }
            holder.txtPersonName.setText(entity.getHomeLoanRequest().getApplicantNme());

            if (entity.getHomeLoanRequest().getApplDate() != null) {
                holder.txtApplicationDate.setText("" + entity.getHomeLoanRequest().getApplDate());
            } else {
                holder.txtApplicationDate.setText("");
            }

            holder.txtloanamount.setText("" + String.valueOf(entity.getHomeLoanRequest().getLoanRequired()));

            if (entity.getHomeLoanRequest().getRBStatus() != null) {

                if (entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("LS")|| entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("DU")
                        || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("AF") || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("MS")
                        || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("NE") || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("DP")
                        || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BL")|| entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BS")
                        || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BR")|| entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BD"))
                {
                    holder.txtApplicationNumber.setVisibility(View.VISIBLE);
                    if(entity.getHomeLoanRequest().getApplNumb().isEmpty())
                    {
                        holder.ivLeadInfo.setVisibility(View.GONE);

                    }else
                    {
                        holder.ivLeadInfo.setVisibility(View.VISIBLE);
                    }

                } else {
                    holder.txtApplicationNumber.setVisibility(View.GONE);
                    holder.ivLeadInfo.setVisibility(View.GONE);

                }
            } else {
                holder.txtApplicationNumber.setVisibility(View.GONE);
                holder.ivLeadInfo.setVisibility(View.GONE);
            }

            holder.lyParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (entity.getHomeLoanRequest().getRBStatus() != null) {

                        if (entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("LS")|| entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("DU")
                                || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("AF") || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("MS")
                                || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("NE") || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("DP")
                                || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BL")|| entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BS")
                                || entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BR")|| entity.getHomeLoanRequest().getRBStatus().toUpperCase().equals("BD"))
                        {
                            Toast.makeText(fragment.getActivity(),"Application Number Already Generated",Toast.LENGTH_SHORT).show();

                        }else{
                            if(entity.getHomeLoanRequest().getApplNumb() != null) {
                                ((HL_ApplicationFragment) fragment).redirectHomeLoanApply(entity.getHomeLoanRequest().getApplNumb());
                            }else{
                                Toast.makeText(fragment.getActivity(),"Application Number Not Found",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                          if(entity.getHomeLoanRequest().getApplNumb() != null) {
                              ((HL_ApplicationFragment) fragment).redirectHomeLoanApply(entity.getHomeLoanRequest().getApplNumb());
                          }else{
                              Toast.makeText(fragment.getActivity(),"Application Number Not Found",Toast.LENGTH_SHORT).show();
                          }
                    }

                }
            });

            holder.txtOverflowMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openPopUp(view, entity);
                }
            });

            holder.ivLeadInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    (( HL_ApplicationFragment)fragment).openLeadDetailPopUp(entity.getHomeLoanRequest().getApplNumb());

                }
            });

            try {
                Glide.with(fragment)
                        .load(entity.getHomeLoanRequest().getbank_image())
                        .into(holder.imgbankLogo);
                //change Fresco
                Glide.with(fragment)
                        .load(entity.getHomeLoanRequest().getProgress_image())
                        .into(holder.imgStatus);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void openPopUp(View v, final FmHomeLoanRequest entity) {
        //creating a popup menu
        PopupMenu popup = new PopupMenu(fragment.getActivity(), v);
        //inflating menu from xml resource
        popup.inflate(R.menu.recycler_menu_application);
        //adding click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.menuCall) {
                    ((HL_ApplicationFragment) fragment).callnumber(entity.getHomeLoanRequest().getContact());
                    //  Toast.makeText(fragment.getActivity(), "WIP " + entity.getHomeLoanRequest().getContact(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {// Toast.makeText(fragment.getActivity(), "WIP SMS ", Toast.LENGTH_SHORT).show();
                    ((HL_ApplicationFragment) fragment).sendSms(entity.getHomeLoanRequest().getContact());

                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }

    @Override
    public int getItemCount() {
        if (mAppListFiltered == null) {
            return 0;
        } else {
            return mAppListFiltered.size();
        }
    }

    public class ApplicationItem extends RecyclerView.ViewHolder {

        TextView txtOverflowMenu, txtApplicationDate, txtApplicationNumber, txtloanamount, txtPersonName;
        ImageView imgbankLogo, imgStatus,ivLeadInfo;
        LinearLayout lyParent;
        View view1,view2,view3;

        public ApplicationItem(View itemView) {
            super(itemView);
            txtOverflowMenu = (TextView) itemView.findViewById(R.id.txtOverflowMenu);
            txtApplicationDate = (TextView) itemView.findViewById(R.id.txtApplicationDate);
            txtApplicationNumber = (TextView) itemView.findViewById(R.id.txtApplicationNumber);
            txtloanamount = (TextView) itemView.findViewById(R.id.txtloanamount);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            imgbankLogo = (ImageView) itemView.findViewById(R.id.imgbankLogo);
            imgStatus = (ImageView) itemView.findViewById(R.id.imgStatus);
            lyParent = (LinearLayout) itemView.findViewById(R.id.lyParent);
            ivLeadInfo  = (ImageView) itemView.findViewById(R.id.ivLeadInfo);

            view1 = (View) itemView.findViewById(R.id.view1);
            view2 = (View) itemView.findViewById(R.id.view2);
            view3 = (View) itemView.findViewById(R.id.view3);

        }
    }
    public void refreshAdapter(List<FmHomeLoanRequest> list) {
        mAppListFiltered = list;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mAppListFiltered = mAppList;
                } else {
                    try {
                        List<FmHomeLoanRequest> filteredList = new ArrayList<>();
                        for (FmHomeLoanRequest row : mAppList) {
                            if (row.getHomeLoanRequest().getApplicantNme().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        mAppListFiltered = filteredList;
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mAppListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mAppListFiltered = (ArrayList<FmHomeLoanRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

