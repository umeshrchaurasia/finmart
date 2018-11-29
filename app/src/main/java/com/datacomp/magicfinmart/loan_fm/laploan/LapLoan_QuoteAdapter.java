package com.datacomp.magicfinmart.loan_fm.laploan;

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
import android.widget.PopupMenu;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.loan_fm.laploan.quote.LAP_QuoteFragment;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;

/**
 * Created by IN-RB on 22-01-2018.
 */

public class LapLoan_QuoteAdapter extends RecyclerView.Adapter<LapLoan_QuoteAdapter.QuoteItem> implements View.OnClickListener,Filterable {

    Fragment mFrament;
    List<FmHomeLoanRequest> mQuoteList;
    List<FmHomeLoanRequest> mQuoteListFiltered;

    public LapLoan_QuoteAdapter(Fragment mFrament, List<FmHomeLoanRequest> list) {
        this.mFrament = mFrament;
        this.mQuoteList = list;
        mQuoteListFiltered = list;
    }




    public class QuoteItem extends RecyclerView.ViewHolder {

        public TextView txtPersonName,  txtloanamount , txtQuoteDate,tvQuoteDate,tvloanamount;
        public ImageView txtOverflowMenu;

        public QuoteItem(View itemView) {
            super(itemView);
            txtQuoteDate = (TextView) itemView.findViewById(R.id.txtQuoteDate);
            txtloanamount = (TextView) itemView.findViewById(R.id.txtloanamount);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);

            tvloanamount = (TextView) itemView.findViewById(R.id.tvloanamount);
            tvQuoteDate = (TextView) itemView.findViewById(R.id.tvQuoteDate);
        }
    }
    @Override
    public QuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_quote_loan, parent, false);
        return new LapLoan_QuoteAdapter.QuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {


        if (holder instanceof LapLoan_QuoteAdapter.QuoteItem) {
            final FmHomeLoanRequest entity = mQuoteListFiltered.get(position);

            holder.txtPersonName.setText(""+ entity.getHomeLoanRequest().getApplicantNme());
            holder.txtQuoteDate.setText("" + entity.getHomeLoanRequest().getRow_created_date().split("T")[0].toString());

            holder.txtloanamount.setText("" + entity.getHomeLoanRequest().getLoanRequired());


            //click listener

            holder.txtPersonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LAP_QuoteFragment)mFrament).redirectQuoteLAP(entity);
                }
            });
            holder.txtQuoteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LAP_QuoteFragment)mFrament).redirectQuoteLAP(entity);
                }
            });
            holder.txtloanamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LAP_QuoteFragment)mFrament).redirectQuoteLAP(entity);
                }
            });
            holder.tvloanamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LAP_QuoteFragment)mFrament).redirectQuoteLAP(entity);
                }
            });
            holder.tvQuoteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LAP_QuoteFragment)mFrament).redirectQuoteLAP(entity);
                }
            });
            holder.txtOverflowMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openPopUp(view, entity);
                }
            });

        }
    }

    private void openPopUp(View v, final FmHomeLoanRequest entity) {
        //creating a popup menu
        //creating a popup menu
        final PopupMenu popupMenu = new PopupMenu(mFrament.getActivity(), v);
        final Menu menu = popupMenu.getMenu();
        //inflating menu from xml resource
        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.menuCall) {
                    ((LAP_QuoteFragment) mFrament).callnumber(entity.getHomeLoanRequest().getContact());
                    //  Toast.makeText(mFrament.getActivity(), "WIP " + entity.getHomeLoanRequest().getContact(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {
                    ((LAP_QuoteFragment) mFrament).sendSms(entity.getHomeLoanRequest().getContact());

                } else if (i == R.id.menuDelete) {
                    ((LAP_QuoteFragment) mFrament).removeQuoteLAP(entity);

                }
                return false;
            }
        });
        //displaying the popup
        popupMenu.show();
    }

    public void refreshAdapter(List<FmHomeLoanRequest> list) {
        mQuoteListFiltered = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mQuoteListFiltered.size();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.txtloanamount) {
            ((LAP_QuoteFragment) mFrament).redirectQuoteLAP((FmHomeLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtQuoteDate) {
            ((LAP_QuoteFragment) mFrament).redirectQuoteLAP((FmHomeLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtPersonName) {
            ((LAP_QuoteFragment) mFrament).redirectQuoteLAP((FmHomeLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvloanamount) {
            ((LAP_QuoteFragment) mFrament).redirectQuoteLAP((FmHomeLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvQuoteDate) {
            ((LAP_QuoteFragment) mFrament).redirectQuoteLAP((FmHomeLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (FmHomeLoanRequest) view.getTag(view.getId()));

        }
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
                    List<FmHomeLoanRequest> filteredList = new ArrayList<>();
                    for (FmHomeLoanRequest row : mQuoteList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getHomeLoanRequest().getApplicantNme().toLowerCase().contains(charString.toLowerCase())) {
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
                mQuoteListFiltered = (ArrayList<FmHomeLoanRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}

