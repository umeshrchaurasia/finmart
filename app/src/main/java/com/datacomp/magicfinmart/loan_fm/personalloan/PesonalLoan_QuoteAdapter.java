package com.datacomp.magicfinmart.loan_fm.personalloan;

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
import com.datacomp.magicfinmart.loan_fm.personalloan.quote.PL_QuoteFragment;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmPersonalLoanRequest;

/**
 * Created by IN-RB on 12-01-2018.
 */

public class PesonalLoan_QuoteAdapter extends RecyclerView.Adapter<PesonalLoan_QuoteAdapter.QuoteItem> implements View.OnClickListener,Filterable {
    Fragment mFrament;
    List<FmPersonalLoanRequest> mQuoteList;
    List<FmPersonalLoanRequest> mQuoteListFiltered;

    public PesonalLoan_QuoteAdapter(Fragment mFrament,List<FmPersonalLoanRequest> list) {
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
        return new PesonalLoan_QuoteAdapter.QuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {


        if (holder instanceof PesonalLoan_QuoteAdapter.QuoteItem) {
            final FmPersonalLoanRequest entity = mQuoteListFiltered.get(position);

            holder.txtPersonName.setText("" + entity.getPersonalLoanRequest().getApplicantNme());
            holder.txtQuoteDate.setText("" + entity.getPersonalLoanRequest().getRow_created_date().split("T")[0].toString());

            holder.txtloanamount.setText("" + entity.getPersonalLoanRequest().getLoanRequired());


            //click listener

//            holder.txtPersonName.setOnClickListener(this);
//            holder.txtQuoteDate.setOnClickListener(this);
//            holder.txtloanamount.setOnClickListener(this);
//            holder.tvloanamount.setOnClickListener(this);
//            holder.tvQuoteDate.setOnClickListener(this);
//            holder.txtOverflowMenu.setOnClickListener(this);

            holder.txtPersonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PL_QuoteFragment)mFrament).redirectQuotePL(entity);
                }
            });
            holder.txtQuoteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PL_QuoteFragment)mFrament).redirectQuotePL(entity);
                }
            });
            holder.txtloanamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PL_QuoteFragment)mFrament).redirectQuotePL(entity);
                }
            });
            holder.tvloanamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PL_QuoteFragment)mFrament).redirectQuotePL(entity);
                }
            });
            holder.tvQuoteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((PL_QuoteFragment)mFrament).redirectQuotePL(entity);
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

    private void openPopUp(View v, final FmPersonalLoanRequest entity) {
        //creating a popup menu
        final PopupMenu popupMenu = new PopupMenu(mFrament.getActivity(), v);
        final Menu menu = popupMenu.getMenu();
        //inflating menu from xml resource
        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        //adding click listener
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.menuCall) {
                    ((PL_QuoteFragment) mFrament).callnumber(entity.getPersonalLoanRequest().getContact());
                    //  Toast.makeText(mFrament.getActivity(),  entity.getPersonalLoanRequest().getContact(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {
                    ((PL_QuoteFragment) mFrament).sendSms(entity.getPersonalLoanRequest().getContact());
                    //  Toast.makeText(mFrament.getActivity(), entity.getPersonalLoanRequest().getContact(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuDelete) {
                    ((PL_QuoteFragment) mFrament).removeQuotePL(entity);

                }
                return false;
            }
        });
        //displaying the popup
        popupMenu.show();
    }
    public void refreshAdapter(List<FmPersonalLoanRequest> list) {
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
            ((PL_QuoteFragment) mFrament).redirectQuotePL((FmPersonalLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtQuoteDate) {
            ((PL_QuoteFragment) mFrament).redirectQuotePL((FmPersonalLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvQuoteDate) {
            ((PL_QuoteFragment) mFrament).redirectQuotePL((FmPersonalLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvloanamount) {
            ((PL_QuoteFragment) mFrament).redirectQuotePL((FmPersonalLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtPersonName) {
            ((PL_QuoteFragment) mFrament).redirectQuotePL((FmPersonalLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (FmPersonalLoanRequest) view.getTag(view.getId()));

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
                    List<FmPersonalLoanRequest> filteredList = new ArrayList<>();
                    for (FmPersonalLoanRequest row : mQuoteList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPersonalLoanRequest().getApplicantNme().toLowerCase().contains(charString.toLowerCase())) {
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
                mQuoteListFiltered = (ArrayList<FmPersonalLoanRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
