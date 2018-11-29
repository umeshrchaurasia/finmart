package com.datacomp.magicfinmart.motor.twowheeler.adapter;

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
import com.datacomp.magicfinmart.motor.twowheeler.fragment.BikeQuoteTabFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BikeMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.QuoteListEntity;

/**
 * Created by Rajeev Ranjan on 11/01/2018.
 */

public class BikeQuoteTabAdapter extends RecyclerView.Adapter<BikeQuoteTabAdapter.QuoteItem> implements View.OnClickListener, Filterable {
    Fragment mFrament;
    List<QuoteListEntity> mQuoteList;
    List<QuoteListEntity> mQuoteListFiltered;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public BikeQuoteTabAdapter(Fragment context, List<QuoteListEntity> list) {
        this.mFrament = context;
        mQuoteList = list;
        mQuoteListFiltered = list;

    }

    @Override

    public QuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_quote, parent, false);
        return new BikeQuoteTabAdapter.QuoteItem(itemView);


    }

    @Override
    public void onBindViewHolder(QuoteItem holder, int position) {

        if (holder instanceof QuoteItem) {
            final QuoteListEntity entity = mQuoteListFiltered.get(position);

            holder.txtPersonName.setText(entity.getMotorRequestEntity().getFirst_name()
                    + " " + entity.getMotorRequestEntity().getLast_name());
            try {
                BikeMasterEntity carMasterEntity = null;
                if (entity.getMotorRequestEntity().getVehicle_id() == 0) {

                    carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                            .getBikeVarientDetails(
                                    "" + entity.getMotorRequestEntity().getVarid());
                } else {
                    carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                            .getBikeVarientDetails(
                                    "" + entity.getMotorRequestEntity().getVehicle_id());
                }
                holder.txtVehicleName.setText(carMasterEntity.getMake_Name() + "," + carMasterEntity.getModel_Name());

            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.txtQuoteDate.setText(entity.getMotorRequestEntity().getCreated_date());
            holder.txtCrnNo.setText("" + entity.getMotorRequestEntity().getCrn());


            //set tag for sharing entity

            holder.txtCrnNo.setTag(R.id.txtCrnNo, entity);
            holder.txtQuoteDate.setTag(R.id.txtQuoteDate, entity);
            holder.txtVehicleName.setTag(R.id.txtVehicleName, entity);
            holder.txtPersonName.setTag(R.id.txtPersonName, entity);
            holder.txtOverflowMenu.setTag(R.id.txtOverflowMenu, entity);
            holder.llDetails.setTag(R.id.llDetails, entity);

            //click listener
            holder.llDetails.setOnClickListener(this);
            holder.txtOverflowMenu.setOnClickListener(this);

        }
    }

    private void openPopUp(View v, final QuoteListEntity entity) {
        final PopupMenu popupMenu = new PopupMenu(mFrament.getActivity(), v);
        final Menu menu = popupMenu.getMenu();

        popupMenu.getMenuInflater().inflate(R.menu.recycler_menu_quote, menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int i = menuItem.getItemId();
                if (i == R.id.menuCall) {
                    ((BikeQuoteTabFragment) mFrament).dialNumber(entity.getMotorRequestEntity().getMobile());
                    //Toast.makeText(mFrament.getActivity(), "WIP " + entity.getMotorRequestEntity().getMobile(), Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuSms) {
                    ((BikeQuoteTabFragment) mFrament).sendSms(entity.getMotorRequestEntity().getMobile());
                    // Toast.makeText(mFrament.getActivity(), "WIP SMS ", Toast.LENGTH_SHORT).show();

                } else if (i == R.id.menuDelete) {
                    ((BikeQuoteTabFragment) mFrament).removeQuote(entity);

                }
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        if (mQuoteListFiltered != null)
            return mQuoteListFiltered.size();
        else
            return 0;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.llDetails || i == R.id.txtPersonName) {
            ((BikeQuoteTabFragment) mFrament).redirectToInputQuote((QuoteListEntity) view.getTag(view.getId()));

        } else if (i == R.id.txtOverflowMenu) {
            openPopUp(view, (QuoteListEntity) view.getTag(view.getId()));

        }
    }

    public class QuoteItem extends RecyclerView.ViewHolder {

        //  public ImageView ivTripleDot;
        public TextView txtQuoteDate, txtVehicleName, txtPersonName, txtCrnNo;
        LinearLayout llDetails;
        ImageView txtOverflowMenu;

        public QuoteItem(View itemView) {
            super(itemView);
            txtQuoteDate = (TextView) itemView.findViewById(R.id.txtQuoteDate);
            txtVehicleName = (TextView) itemView.findViewById(R.id.txtVehicleName);
            txtPersonName = (TextView) itemView.findViewById(R.id.txtPersonName);
            txtOverflowMenu = (ImageView) itemView.findViewById(R.id.txtOverflowMenu);
            txtCrnNo = (TextView) itemView.findViewById(R.id.txtCrnNo);
            llDetails = (LinearLayout) itemView.findViewById(R.id.llDetails);
        }
    }

    public void refreshAdapter(List<QuoteListEntity> list) {
        mQuoteList = list;
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
                    List<QuoteListEntity> filteredList = new ArrayList<>();
                    for (QuoteListEntity row : mQuoteList) {
                        BikeMasterEntity carMasterEntity = new BikeMasterEntity();
                        try {

                            carMasterEntity = new DBPersistanceController(mFrament.getActivity())
                                    .getBikeVarientDetails(
                                            "" + row.getMotorRequestEntity().getVehicle_id());

                        } catch (Exception e) {

                        }
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getMotorRequestEntity().getFirst_name().toLowerCase().contains(charString.toLowerCase())
                                || row.getMotorRequestEntity().getLast_name().toLowerCase().contains(charString.toLowerCase())
                                || String.valueOf(row.getMotorRequestEntity().getCrn()).contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                        if (carMasterEntity != null) {
                            if (carMasterEntity.getMake_Name().toLowerCase().contains(charString.toLowerCase())
                                    || carMasterEntity.getModel_Name().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
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
                mQuoteListFiltered = (ArrayList<QuoteListEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
