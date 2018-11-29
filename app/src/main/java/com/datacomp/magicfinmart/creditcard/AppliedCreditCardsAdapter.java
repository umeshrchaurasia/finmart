package com.datacomp.magicfinmart.creditcard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.AppliedCreditCardEntity;

public class AppliedCreditCardsAdapter extends RecyclerView.Adapter<AppliedCreditCardsAdapter.ViewHolder> implements Filterable {


    private LayoutInflater mInflater;
    Context mContext;
    List<AppliedCreditCardEntity> listCreditCards;
    List<AppliedCreditCardEntity> mCreditCardFiltered;

    // data is passed into the constructor
    AppliedCreditCardsAdapter(Context context, List<AppliedCreditCardEntity> list) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        listCreditCards = list;
        mCreditCardFiltered = list;
    }


    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_applied_creditcard_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ViewHolder hold = (ViewHolder) holder;
        AppliedCreditCardEntity entity = mCreditCardFiltered.get(position);

        hold.txtAppNo.setText(entity.getApplicationNo());
        hold.txtBankName.setText(entity.getCardType());
        hold.txtCreditType.setText(entity.getCreditCardName());
        hold.txtEmail.setText(entity.getEmail());
        hold.txtName.setText(entity.getFullName());
        hold.txtMobile.setText(entity.getMobileNo());
        hold.txtStatus.setText(entity.getStatusX());
    }

    public void refreshAdapter(List<AppliedCreditCardEntity> list) {
        mCreditCardFiltered = list;
        notifyDataSetChanged();
    }

    // total number of cells
    @Override
    public int getItemCount() {
        if (mCreditCardFiltered != null)
            return mCreditCardFiltered.size();
        else
            return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mCreditCardFiltered = listCreditCards;
                } else {
                    List<AppliedCreditCardEntity> filerList = new ArrayList<>();

                    for (AppliedCreditCardEntity row : listCreditCards) {
                        if (row.getFullName().toLowerCase().contains(charString.toLowerCase())
                                || row.getMobileNo().toLowerCase().contains(charString.toLowerCase())
                                || row.getEmail().toLowerCase().contains(charSequence.toString())) {
                            filerList.add(row);
                        }
                    }

                    mCreditCardFiltered = filerList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mCreditCardFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mCreditCardFiltered = (ArrayList<AppliedCreditCardEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtEmail, txtMobile, txtBankName, txtCreditType, txtAppNo, txtStatus;

        ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.txtName);
            txtEmail = (TextView) v.findViewById(R.id.txtEmail);
            txtMobile = (TextView) v.findViewById(R.id.txtMobile);
            txtBankName = (TextView) v.findViewById(R.id.txtBankName);
            txtCreditType = (TextView) v.findViewById(R.id.txtCreditType);
            txtAppNo = (TextView) v.findViewById(R.id.txtAppNo);
            txtStatus = (TextView) v.findViewById(R.id.txtStatus);
        }
    }


}