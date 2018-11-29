package com.datacomp.magicfinmart.loan_fm.balancetransfer;

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
import com.datacomp.magicfinmart.loan_fm.balancetransfer.quote.BL_QuoteFragment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;

/**
 * Created by IN-RB on 26-01-2018.
 */

public class BalanceTransfer_QuoteAdapter extends RecyclerView.Adapter<BalanceTransfer_QuoteAdapter.QuoteItem>  implements View.OnClickListener ,Filterable {

    Fragment mFrament;
    List<FmBalanceLoanRequest> mQuoteList;
    List<FmBalanceLoanRequest> mQuoteListFiltered;


    public BalanceTransfer_QuoteAdapter(Fragment mFrament,List<FmBalanceLoanRequest> list) {
        this.mFrament = mFrament;
        this.mQuoteList = list;
        mQuoteListFiltered = list;
    }
    @Override
    public QuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_quote_bl, parent, false);
        return new BalanceTransfer_QuoteAdapter.QuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {


        if (holder instanceof BalanceTransfer_QuoteAdapter.QuoteItem) {
            final FmBalanceLoanRequest entity = mQuoteListFiltered.get(position);

            try {
                if (entity.getBLLoanRequest().getApplicantName() != null) {
                    holder.txtPersonName.setText("" + entity.getBLLoanRequest().getApplicantName());
                }
                else
                {
                    holder.txtPersonName.setText("");
                }
                if (entity.getBLLoanRequest().getRow_createddate() != null) {
                    holder.txtQuoteDate.setText("" + entity.getBLLoanRequest().getRow_createddate().split("T")[0].toString());

                }
                else
                {
                    holder.txtQuoteDate.setText("");
                }


                if (Integer.toString(entity.getBLLoanRequest().getProduct_id()).matches("5")) {
                    holder.txttype.setText("HOME LOAN");
                } else if (Integer.toString(entity.getBLLoanRequest().getProduct_id()).matches("14")) {
                    holder.txttype.setText("PERSONAL LOAN");
                } else if (Integer.toString(entity.getBLLoanRequest().getProduct_id()).matches("2")) {
                    holder.txttype.setText("LAP LOAN");
                }
                holder.txtloanamount.setText("" +  BigDecimal.valueOf(Math.ceil(entity.getBLLoanRequest().getLoanamount())).setScale(0, BigDecimal.ROUND_HALF_UP));



            holder.txtPersonName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });
            holder.txtQuoteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });
            holder.txtloanamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });
            holder.tvloanamount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });
            holder.tvQuoteDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });
            holder.txttype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });
            holder.tvtype.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BL_QuoteFragment)mFrament).redirectQuoteBL(entity);
                }
            });

            holder.txtOverflowMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openPopUp(view, entity);
                }
            });

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
    }


    private void openPopUp(View v, final FmBalanceLoanRequest entity) {
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
                    ((BL_QuoteFragment) mFrament).callnumber(entity.getBLLoanRequest().getContact());
                    // Toast.makeText(mFrament.getActivity(), "WIP " + entity.getBLLoanRequest().getContact(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {
                    ((BL_QuoteFragment) mFrament).sendSms(entity.getBLLoanRequest().getContact());


                } else if (i == R.id.menuDelete) {
                    ((BL_QuoteFragment) mFrament).removeQuoteBL(entity);
                    ((BL_QuoteFragment) mFrament).removeQuoteBL(entity);


                }
                return false;
            }
        });
        //displaying the popup
        popupMenu.show();
    }


    public void refreshAdapter(List<FmBalanceLoanRequest> list) {
        mQuoteListFiltered = list;
        notifyDataSetChanged();
    }

    public class QuoteItem extends RecyclerView.ViewHolder {

        public TextView txtPersonName,    txtloanamount , txtQuoteDate,tvQuoteDate,tvloanamount,txttype,tvtype;
        public ImageView txtOverflowMenu;


        public QuoteItem(View itemView) {
            super(itemView);
            txtQuoteDate = (TextView) itemView.findViewById(R.id.txtQuoteDate);
            txtloanamount = (TextView) itemView.findViewById(R.id.txtloanamount);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);

            tvloanamount = (TextView) itemView.findViewById(R.id.tvloanamount);
            tvQuoteDate = (TextView) itemView.findViewById(R.id.tvQuoteDate);
            txttype = (TextView)itemView.findViewById(R.id.txttype);
            tvtype=(TextView)itemView.findViewById(R.id.tvtype);
        }
    }

    @Override
    public int getItemCount() {
        return mQuoteListFiltered.size();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.txtloanamount) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtQuoteDate) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvQuoteDate) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvloanamount) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtPersonName) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txttype) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.tvtype) {
            ((BL_QuoteFragment) mFrament).redirectQuoteBL((FmBalanceLoanRequest) view.getTag(view.getId()));

        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (FmBalanceLoanRequest) view.getTag(view.getId()));

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
                    List<FmBalanceLoanRequest> filteredList = new ArrayList<>();
                    for (FmBalanceLoanRequest row : mQuoteList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getBLLoanRequest().getApplicantName().toLowerCase().contains(charString.toLowerCase())) {
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
                mQuoteListFiltered = (ArrayList<FmBalanceLoanRequest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
