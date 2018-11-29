package com.datacomp.magicfinmart.health.quoappfragment;

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

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;

/**
 * Created by Nilesh birhade on 05/02/2018.
 */

public class HealthQuoteAdapter extends RecyclerView.Adapter<HealthQuoteAdapter.QuoteItem> implements View.OnClickListener, Filterable {
    Fragment mFrament;
    List<HealthQuote> mQuoteList;
    List<HealthQuote> mQuoteListFiltered;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public HealthQuoteAdapter(Fragment context, List<HealthQuote> list) {
        this.mFrament = context;
        mQuoteList = list;
        mQuoteListFiltered = list;
    }

    @Override

    public QuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_health_item_quote, parent, false);
        return new HealthQuoteAdapter.QuoteItem(itemView);


    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {

        if (holder instanceof QuoteItem) {
            final HealthQuote quote = mQuoteListFiltered.get(position);
            holder.txtPersonName.setText(quote.getHealthRequest().getContactName());
            holder.txtSumAssured.setText(quote.getHealthRequest().getSumInsured());
            holder.txtQuoteDate.setText(quote.getHealthRequest().getCreated_date());
            holder.llFooter.setTag(R.id.llFooter, quote);
            holder.txtPersonName.setTag(R.id.txtPersonName, quote);
            holder.txtSumAssured.setTag(R.id.txtSumAssured, quote);
            holder.txtQuoteDate.setTag(R.id.txtQuoteDate, quote);
            holder.txtOverflowMenu.setTag(R.id.txtOverflowMenu, quote);

            holder.llFooter.setOnClickListener(this);
            holder.txtOverflowMenu.setOnClickListener(this);
            holder.txtPersonName.setOnClickListener(this);
            holder.txtQuoteDate.setOnClickListener(this);
            holder.txtSumAssured.setOnClickListener(this);
        }

    }

    private void openPopUp(View v, final HealthQuote entity) {
        final PopupMenu popupMenu = new PopupMenu(mFrament.getActivity(), v);
        final Menu menu = popupMenu.getMenu();

        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int i = menuItem.getItemId();
                if (i == R.id.menuCall) {
                    ((HealthQuoteListFragment) mFrament).dialNumber(entity.getHealthRequest().getContactMobile());

                } else if (i == R.id.menuSms) {
                    ((HealthQuoteListFragment) mFrament).sendSms(entity.getHealthRequest().getContactMobile());

                } else if (i == R.id.menuDelete) {
                    ((HealthQuoteListFragment) mFrament).removeQuote(entity);

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
        if (i == R.id.llFooter || i == R.id.txtPersonName || i == R.id.txtQuoteDate || i == R.id.txtSumAssured) {
            ((HealthQuoteListFragment) mFrament).redirectToInputQuote((HealthQuote) view.getTag(view.getId()));


        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (HealthQuote) view.getTag(view.getId()));

        }
    }

    public class QuoteItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        public TextView txtQuoteDate, txtSumAssured, txtPersonName;
        ImageView txtOverflowMenu;
        LinearLayout llFooter;

        public QuoteItem(View itemView) {
            super(itemView);
            llFooter = (LinearLayout) itemView.findViewById(R.id.llFooter);
            txtQuoteDate = (TextView) itemView.findViewById(R.id.txtQuoteDate);
            txtSumAssured = (TextView) itemView.findViewById(R.id.txtSumAssured);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);
        }
    }

    public void refreshAdapter(List<HealthQuote> list) {
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
                    List<HealthQuote> filteredList = new ArrayList<>();
                    for (HealthQuote row : mQuoteList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getHealthRequest().getContactName().toLowerCase().contains(charString.toLowerCase())) {
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
                mQuoteListFiltered = (ArrayList<HealthQuote>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
