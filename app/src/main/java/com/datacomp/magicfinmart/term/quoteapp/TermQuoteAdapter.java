package com.datacomp.magicfinmart.term.quoteapp;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermFinmartRequest;

/**
 * Created by Nilesh birhade on 05/02/2018.
 */

public class TermQuoteAdapter extends RecyclerView.Adapter<TermQuoteAdapter.QuoteItem> implements View.OnClickListener, Filterable {
    Fragment mFrament;
    List<TermFinmartRequest> mQuoteList;
    List<TermFinmartRequest> mQuoteListFiltered;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TermQuoteAdapter(Fragment context, List<TermFinmartRequest> list) {
        this.mFrament = context;
        mQuoteList = list;
        mQuoteListFiltered = list;
    }

    @Override

    public QuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_term_item_quote, parent, false);
        return new TermQuoteAdapter.QuoteItem(itemView);


    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {

        if (holder instanceof QuoteItem) {
            final TermFinmartRequest quote = mQuoteListFiltered.get(position);
            holder.txtPersonName.setText(quote.getTermRequestEntity().getContactName());
            holder.txtCustRefNo.setText(quote.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id());
            holder.txtQuoteDate.setText(quote.getTermRequestEntity().getCreated_date());
            holder.llDetails.setTag(R.id.llDetails, quote);
            holder.txtOverflowMenu.setTag(R.id.txtOverflowMenu, quote);

            holder.llDetails.setOnClickListener(this);
            holder.txtOverflowMenu.setOnClickListener(this);

        }

    }

    private void openPopUp(View v, final TermFinmartRequest entity) {
        final PopupMenu popupMenu = new PopupMenu(mFrament.getActivity(), v);
        final Menu menu = popupMenu.getMenu();

        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int i = menuItem.getItemId();
                if (i == R.id.menuCall) {
                    ((TermQuoteListFragment) mFrament).dialNumber(entity.getTermRequestEntity().getContactMobile());

                } else if (i == R.id.menuSms) {
                    ((TermQuoteListFragment) mFrament).sendSms(entity.getTermRequestEntity().getContactMobile());

                } else if (i == R.id.menuDelete) {
                    ((TermQuoteListFragment) mFrament).removeQuote(entity);

                }
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return mQuoteListFiltered.size();
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        if (i == R.id.llDetails) {
            TermFinmartRequest request = (TermFinmartRequest) view.getTag(view.getId());
            ((TermQuoteListFragment) mFrament).callInputTerm(request.getTermRequestEntity().getInsurerId(), request);

        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (TermFinmartRequest) view.getTag(view.getId()));

        }
    }

    public class QuoteItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        public TextView txtQuoteDate, txtCustRefNo, txtPersonName;
        LinearLayout llDetails;
        ImageView txtOverflowMenu;

        public QuoteItem(View itemView) {
            super(itemView);
            txtQuoteDate = (TextView) itemView.findViewById(R.id.txtQuoteDate);
            txtCustRefNo = (TextView) itemView.findViewById(R.id.txtCustRefNo);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);
            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetails);
        }
    }

    public void refreshAdapter(List<TermFinmartRequest> list) {
        mQuoteListFiltered = list;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mQuoteListFiltered = mQuoteList;
                } else {
                    List<TermFinmartRequest> filteredList = new ArrayList<>();
                    for (TermFinmartRequest row : mQuoteList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTermRequestEntity().getContactName().toLowerCase().contains(charString.toLowerCase())
                                || row.getTermRequestEntity().getExisting_ProductInsuranceMapping_Id().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mQuoteListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mQuoteListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mQuoteListFiltered = (ArrayList<TermFinmartRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
