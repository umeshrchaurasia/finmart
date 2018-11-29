package com.datacomp.magicfinmart.helpfeedback.raiseticket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TicketEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class RaiseTicketAdapter extends RecyclerView.Adapter<RaiseTicketAdapter.RaiseTicketItem> implements View.OnClickListener, Filterable {
    Context context;
    List<TicketEntity> mQuoteList;
    List<TicketEntity> mQuoteListFiltered;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public RaiseTicketAdapter(Context context, List<TicketEntity> list) {
        this.context = context;
        mQuoteList = list;
        mQuoteListFiltered = list;

    }

    @Override

    public RaiseTicketItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_raise_ticket, parent, false);
        return new RaiseTicketAdapter.RaiseTicketItem(itemView);


    }

    @Override
    public void onBindViewHolder(RaiseTicketItem holder, int position) {

        if (holder instanceof RaiseTicketItem) {
            final TicketEntity entity = mQuoteListFiltered.get(position);


            holder.txtTicketId.setText("" + entity.getTicketRequestId());
            holder.txtTicketStatus.setText("" + entity.getStatus());
            holder.txtTicketCategory.setText(entity.getCateName());
            holder.txtTicketDesc.setText("" + entity.getMessage());



           /* holder.txtCrnNo.setTag(R.id.txtCrnNo, entity);
            holder.txtQuoteDate.setTag(R.id.txtQuoteDate, entity);
            holder.txtVehicleName.setTag(R.id.txtVehicleName, entity);
            holder.txtPersonName.setTag(R.id.txtPersonName, entity);
            holder.txtOverflowMenu.setTag(R.id.txtOverflowMenu, entity);

            //click listener
            holder.txtCrnNo.setOnClickListener(this);
            holder.txtQuoteDate.setOnClickListener(this);
            holder.txtVehicleName.setOnClickListener(this);
            holder.txtPersonName.setOnClickListener(this);
            holder.txtOverflowMenu.setOnClickListener(this);*/

        }
    }

    /*private void openPopUp(View v, final QuoteListEntity entity) {
        final PopupMenu popupMenu = new PopupMenu(context, v);
        final Menu menu = popupMenu.getMenu();

        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuCall:
                        ((MotorQuoteFragment) context).dialNumber(entity.getMotorRequestEntity().getMobile());
                        //Toast.makeText(mFrament.getActivity(), "WIP " + entity.getMotorRequestEntity().getMobile(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuSms:
                        ((MotorQuoteFragment) context).sendSms(entity.getMotorRequestEntity().getMobile());
                        Toast.makeText(context.getActivity(), "WIP SMS ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menuDelete:
                        ((MotorQuoteFragment) context).removeQuote(entity);
                        break;
                }
                return false;
            }
        });

        popupMenu.show();
    }*/

    @Override
    public int getItemCount() {
        if (mQuoteListFiltered != null)
            return mQuoteListFiltered.size();
        else
            return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           /* case R.id.txtCrnNo:
            case R.id.txtQuoteDate:
            case R.id.txtVehicleName:
            case R.id.llDetails:
            case R.id.txtPersonName:
                ((MotorQuoteFragment) context).redirectToInputQuote((QuoteListEntity) view.getTag(view.getId()));
                break;
            case R.id.txtOverflowMenu:
                openPopUp(view, (QuoteListEntity) view.getTag(view.getId()));
                break;
*/
        }
    }

    public class RaiseTicketItem extends RecyclerView.ViewHolder {

        public ImageView ticketPhoto;
        public TextView txtTicketId, txtTicketStatus, txtTicketCategory, txtTicketDesc;

        public RaiseTicketItem(View itemView) {
            super(itemView);
            txtTicketId = (TextView) itemView.findViewById(R.id.txtTicketId);
            txtTicketStatus = (TextView) itemView.findViewById(R.id.txtTicketStatus);
            txtTicketCategory = (TextView) itemView.findViewById(R.id.txtTicketCategory);
            txtTicketDesc = (TextView) itemView.findViewById(R.id.txtTicketDesc);
            ticketPhoto = (ImageView) itemView.findViewById(R.id.ticketPhoto);
        }
    }

    public void refreshAdapter(List<TicketEntity> list) {
        mQuoteListFiltered = list;
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
                    List<TicketEntity> filteredList = new ArrayList<>();
                    for (TicketEntity row : mQuoteList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(row.getTicketRequestId()).toLowerCase().contains(charString.toLowerCase())
                                || row.getMessage().toLowerCase().contains(charString.toLowerCase())) {
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
                mQuoteListFiltered = (ArrayList<TicketEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
