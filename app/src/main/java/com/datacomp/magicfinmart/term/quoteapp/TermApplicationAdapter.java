package com.datacomp.magicfinmart.term.quoteapp;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermFinmartRequest;

/**
 * Created by Nilesh on 05/02/2018.
 */

public class TermApplicationAdapter extends RecyclerView.Adapter<TermApplicationAdapter.ApplicationItem> implements View.OnClickListener, Filterable {
    Fragment fragment;
    List<TermFinmartRequest> mAppList;
    List<TermFinmartRequest> mAppListFiltered;
    SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

    public TermApplicationAdapter(Fragment context, List<TermFinmartRequest> mApplicationList) {
        this.fragment = context;
        mAppList = mApplicationList;
        mAppListFiltered = mApplicationList;
    }

    @Override

    public ApplicationItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_term_item_application, parent, false);
        return new ApplicationItem(itemView);
    }

    @Override
    public void onBindViewHolder(ApplicationItem holder, int position) {
        if (holder instanceof ApplicationItem) {
            TermFinmartRequest entity = mAppListFiltered.get(position);
            if (entity != null) {

                holder.txtCRN.setText("" + entity.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id());

                holder.txtCreatedDate.setText("" + changeDateFormat(entity.getTermRequestEntity().getCreated_date()));
                holder.txtPersonName.setText("" + entity.getTermRequestEntity().getContactName());

                holder.txtTermPpt.setText(entity.getTermRequestEntity().getPolicyTerm() + "/" + entity.getTermRequestEntity().getPPT());
                holder.txtSum.setText("" + entity.getTermRequestEntity().getSumAssured());
                holder.txtStatusDate.setText("" + changeDateFormat(entity.getTermRequestEntity().getCreated_date()));
                if (entity.getTermRequestEntity().getFrequency().toLowerCase().equals("annual"))
                    holder.txtMode.setText("YEARLY");
                else
                    holder.txtMode.setText("" + entity.getTermRequestEntity().getFrequency());
                if (entity.getStatusProgress() == 0)
                    holder.txtStatus.setText("LINK SENT");
                else
                    holder.txtStatus.setText("---");

                holder.txtPremium.setText("" + entity.getNetPremium());


                holder.txtCRN.setTag(R.id.txtCRN, entity);
                holder.txtCreatedDate.setTag(R.id.txtCreatedDate, entity);
                holder.txtPersonName.setTag(R.id.txtPersonName, entity);
                holder.txtOverflowMenu.setTag(R.id.txtOverflowMenu, entity);


                holder.txtCRN.setOnClickListener(this);
                holder.txtCreatedDate.setOnClickListener(this);
                holder.txtPersonName.setOnClickListener(this);
                holder.txtOverflowMenu.setOnClickListener(this);

            }
        }
    }


    private void openPopUp(View v, final TermFinmartRequest entity) {
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
                    ((TermApplicationListFragment) fragment).dialNumber(entity.getTermRequestEntity().getContactMobile());

                } else if (i == R.id.menuSms) {
                    ((TermApplicationListFragment) fragment).sendSms(entity.getTermRequestEntity().getContactMobile());

                }
                return false;
            }
        });
        //displaying the popup
        popup.show();
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.txtCRN || i == R.id.txtCreatedDate || i == R.id.txtPersonName || i == R.id.imgInsurerLogo || i == R.id.llbottom || i == R.id.llApp) {//((HealthApplicationFragment) fragment).redirectToQuote((HealthApplication) view.getTag(view.getId()));

        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (TermFinmartRequest) view.getTag(view.getId()));

        }
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

        TextView txtCreatedDate, txtCRN, txtPersonName, txtTermPpt, txtSum, txtStatusDate, txtMode, txtStatus, txtPremium;
        ImageView txtOverflowMenu;

        public ApplicationItem(View itemView) {
            super(itemView);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);
            txtCreatedDate = (TextView) itemView.findViewById(R.id.txtCreatedDate);
            txtCRN = (TextView) itemView.findViewById(R.id.txtCRN);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);

            txtTermPpt = (TextView) itemView.findViewById(R.id.txtTermPpt);
            txtSum = (TextView) itemView.findViewById(R.id.txtSum);
            txtStatusDate = (TextView) itemView.findViewById(R.id.txtStatusDate);
            txtMode = (TextView) itemView.findViewById(R.id.txtMode);

            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            txtPremium = (TextView) itemView.findViewById(R.id.txtPremium);

        }
    }


    public void refreshAdapter(List<TermFinmartRequest> list) {
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
                    List<TermFinmartRequest> filteredList = new ArrayList<>();
                    for (TermFinmartRequest row : mAppList) {
                        if (row.getTermRequestEntity().getContactName().toLowerCase().contains(charString.toLowerCase())
                                || row.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mAppListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mAppListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mAppListFiltered = (ArrayList<TermFinmartRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public String changeDateFormat(String date) {

        SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy"); // 30/10/2010
        Date newDate = null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return displayDateFormat.format(newDate);
    }
}
