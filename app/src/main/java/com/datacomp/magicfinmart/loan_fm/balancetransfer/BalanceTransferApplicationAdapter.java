package com.datacomp.magicfinmart.loan_fm.balancetransfer;

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
import com.datacomp.magicfinmart.loan_fm.balancetransfer.application.BL_ApplicationFragment;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;

/**
 * Created by IN-RB on 26-01-2018.
 */

public class BalanceTransferApplicationAdapter  extends RecyclerView.Adapter<BalanceTransferApplicationAdapter.ApplicationItem>implements Filterable {
    Fragment fragment;

    List<FmBalanceLoanRequest> mAppList;
    List<FmBalanceLoanRequest> mAppListFiltered;

    public BalanceTransferApplicationAdapter(Fragment context, List<FmBalanceLoanRequest> mApplicationList) {
        this.fragment = context;
        mAppList = mApplicationList;
        mAppListFiltered = mApplicationList;
    }
    @Override
    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_application_bl, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {
        if (holder instanceof ApplicationItem) {

            final FmBalanceLoanRequest entity = mAppListFiltered.get(position);

            if (entity.getBLLoanRequest().getApplNumb() != null) {
                holder.txtApplicationNumber.setText(""+String.valueOf(entity.getBLLoanRequest().getApplNumb()));
            }else
            {
                holder.txtApplicationNumber.setText("");
            }
            holder.txtPersonName.setText(entity.getBLLoanRequest().getApplicantName());

            if (entity.getBLLoanRequest().getApplDate() != null) {
                holder.txtApplicationDate.setText("" + entity.getBLLoanRequest().getApplDate());
            }else
            {
                holder.txtApplicationDate.setText("");
            }

            holder.txtloanamount.setText(""+String.valueOf(entity.getBLLoanRequest().getLoanamount()));

            if (entity.getBLLoanRequest().getRBStatus() != null) {

                if (entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("LS")|| entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("DU")
                        || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("AF") || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("MS")
                        || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("NE") || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("DP")
                        || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BL")|| entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BS")
                        || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BR")|| entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BD"))
                {
                    holder.txtApplicationNumber.setVisibility(View.VISIBLE);
                    if(entity.getBLLoanRequest().getApplNumb().isEmpty())
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


                    if (entity.getBLLoanRequest().getRBStatus() != null) {

                        if (entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("LS")|| entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("DU")
                                || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("AF") || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("MS")
                                || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("NE") || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("DP")
                                || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BL")|| entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BS")
                                || entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BR")|| entity.getBLLoanRequest().getRBStatus().toUpperCase().equals("BD"))
                        {



                            Toast.makeText(fragment.getActivity(),"Application Number Already Generated",Toast.LENGTH_SHORT).show();

                        }else{
                            if(entity.getBLLoanRequest().getApplNumb() != null) {
                                ((BL_ApplicationFragment) fragment).redirectBLLoanApply(entity.getBLLoanRequest().getApplNumb(),entity.getBLLoanRequest().getProduct_id());
                            }else{
                                Toast.makeText(fragment.getActivity(),"Application Number Not Found",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        if(entity.getBLLoanRequest().getApplNumb() != null) {
                            ((BL_ApplicationFragment) fragment).redirectBLLoanApply(entity.getBLLoanRequest().getApplNumb(),entity.getBLLoanRequest().getProduct_id());
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

                    (( BL_ApplicationFragment)fragment).openLeadDetailPopUp(entity.getBLLoanRequest().getApplNumb());

                }
            });
            try {
                Glide.with(fragment)
                        .load(entity.getBLLoanRequest().getBank_image())
                        .into(holder.imgbankLogo);

                Glide.with(fragment)
                        .load(entity.getBLLoanRequest().getProgress_image())
                        .into(holder.imgStatus);
                //change Fresco

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void openPopUp(View v, final FmBalanceLoanRequest entity) {
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
                    ((BL_ApplicationFragment) fragment).callnumber(entity.getBLLoanRequest().getContact());
                    //    Toast.makeText(fragment.getActivity(), "WIP " + entity.getBLLoanRequest().getContact(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {
                    ((BL_ApplicationFragment) fragment).sendSms(entity.getBLLoanRequest().getContact());

                    // Toast.makeText(fragment.getActivity(), "WIP SMS ", Toast.LENGTH_SHORT).show();

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
        ImageView imgbankLogo,imgStatus,ivLeadInfo;
        LinearLayout lyParent;

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
        }
    }

    public void refreshAdapter(List<FmBalanceLoanRequest> list) {
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
                        List<FmBalanceLoanRequest> filteredList = new ArrayList<>();
                        for (FmBalanceLoanRequest row : mAppList) {
                            if (row.getBLLoanRequest().getApplicantName().toLowerCase().contains(charString.toLowerCase())) {
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
                mAppListFiltered = (ArrayList<FmBalanceLoanRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
