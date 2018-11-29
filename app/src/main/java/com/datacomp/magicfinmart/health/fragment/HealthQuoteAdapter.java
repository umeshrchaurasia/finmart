package com.datacomp.magicfinmart.health.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.utility.SortbyInsurer;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BenefitsEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;

public class HealthQuoteAdapter extends RecyclerView.Adapter<HealthQuoteAdapter.ViewHolder> implements View.OnClickListener {


    public static final String HIDE_OPTIONS = "Hide Options";
    private LayoutInflater mInflater;
    Fragment mContext;
    List<HealthQuoteEntity> listHealthQuotes;
    int checkCount = 1;


    // data is passed into the constructor
    HealthQuoteAdapter(Fragment context, List<HealthQuoteEntity> listQuotes) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext.getActivity());
        Collections.sort(listQuotes, new SortbyInsurer());
        this.listHealthQuotes = listQuotes;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_health_quote, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final HealthQuoteEntity entity = listHealthQuotes.get(position);
        holder.txtSumAssured.setText("" + Math.round(entity.getSumInsured()));
        holder.txtDeductible.setText("" + entity.getDeductible_Amount());
        holder.txtPlanName.setText("" + entity.getPlanName());
        holder.txtProductName.setText("" + entity.getProductName());

        /*int finalPremium = 0;
        if (entity.getServicetaxincl().toLowerCase().equals("e")) {
            finalPremium = (int) Math.round(entity.getNetPremium());
        } else if (entity.getServicetaxincl().toLowerCase().equals("i")) {
            finalPremium = (int) Math.round(entity.getGrossPremium());
        }*/

        holder.txtFinalPremium.setText("\u20B9 " + (int) Math.round(entity.getDisplayPremium()) + "/Year");

        Glide.with(mContext).load(entity.getInsurerLogoName())
                .into(holder.imgInsurer);

        holder.llCount.setTag(R.id.llCount, entity);
        holder.txtNoOfInsurer.setTag(R.id.txtNoOfInsurer, entity);
        holder.chkCompare.setTag(R.id.chkCompare, entity);

        holder.llBenefits.setTag(R.id.llBenefits, entity);
        holder.llBenefits.setOnClickListener(this);

        holder.txtBuy.setTag(R.id.txtBuy, entity);
        holder.txtBuy.setOnClickListener(this);

        // to stop triggering event every time
        holder.chkCompare.setOnCheckedChangeListener(null);

        if (entity.isCompare()) {
            holder.chkCompare.setChecked(true);
        } else {
            holder.chkCompare.setChecked(false);
        }

        holder.chkCompare.setOnCheckedChangeListener(checkedChangeListener);

        if (!entity.getIsMore() && entity.getTotalChilds() > 0) {
            //  holder.txtNoOfInsurer.setText(" + \n" + String.valueOf(entity.getTotalChilds() + " \nMore"));
            holder.llCount.setVisibility(View.VISIBLE);
            holder.txtNoOfInsurer.setText(" + \n" + String.valueOf(entity.getTotalChilds() + " More"));
            holder.imgDropDown.setVisibility(View.VISIBLE);
            holder.txtNoOfInsurer.setOnClickListener(this);
            holder.llCount.setOnClickListener(this);
        } else if (!entity.getIsMore() && entity.getTotalChilds() == 0) {
            holder.llCount.setVisibility(View.GONE);
            holder.txtNoOfInsurer.setText("");
            holder.imgDropDown.setVisibility(View.GONE);
            holder.txtNoOfInsurer.setOnClickListener(null);
            holder.llCount.setOnClickListener(null);
        } else {
            holder.llCount.setVisibility(View.VISIBLE);
            holder.txtNoOfInsurer.setText(HIDE_OPTIONS);
            holder.imgDropDown.setVisibility(View.VISIBLE);
            holder.txtNoOfInsurer.setOnClickListener(this);
            holder.llCount.setOnClickListener(this);
        }

        if (entity.getLstbenfitsFive() != null) {

            if (entity.getLstbenfitsFive().size() == 0) {
                holder.txtRoomRent.setText("");
                holder.txtIcuRent.setText("");
                holder.txtPreHosp.setText("");
                holder.txtPostHosp.setText("");
            } else {

                for (int i = 0; i < entity.getLstbenfitsFive().size(); i++) {
                    BenefitsEntity benefit = entity.getLstbenfitsFive().get(i);
                    if (benefit.getBeneID() == 1) { //room rent
                        holder.txtRoomRent.setText(benefit.getBenefit());
                    } else if (benefit.getBeneID() == 2) { //icu
                        holder.txtIcuRent.setText(benefit.getBenefit());
                    } else if (benefit.getBeneID() == 3) { //pre hosp
                        holder.txtPreHosp.setText(benefit.getBenefit());
                    } else if (benefit.getBeneID() == 4) { //post
                        holder.txtPostHosp.setText(benefit.getBenefit());
                    }
                }
            }
        }
    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            HealthQuoteEntity entity = (HealthQuoteEntity) compoundButton.getTag(R.id.chkCompare);

            if (checkCount <= 4) {
                if (b) {
                    checkCount = checkCount + 1;
                    entity.setCompare(b);
                    ((HealthQuoteFragment) mContext).addRemoveCompare(entity, b);
                } else {
                    checkCount = checkCount - 1;
                    entity.setCompare(b);
                    ((HealthQuoteFragment) mContext).addRemoveCompare(entity, b);
                }
            } else {
                if (b) {
                    ((HealthQuoteFragment) mContext).showAlert("You can select only four plans to compare.");
                } else {
                    checkCount = checkCount - 1;
                    entity.setCompare(b);
                    ((HealthQuoteFragment) mContext).addRemoveCompare(entity, b);
                }
                entity.setCompare(false);
            }

            updateCheckBox(entity);

        }
    };

    @Override
    public int getItemCount() {
        return listHealthQuotes.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        //CardView cvHealthQuote;
        TextView txtSumAssured, txtDeductible, txtPlanName, txtFinalPremium, txtBuy, txtProductName;
        TextView txtRoomRent, txtIcuRent, txtPreHosp, txtPostHosp, txtNoOfInsurer;
        CheckBox chkCompare;
        ImageView imgInsurer, imgDropDown;
        LinearLayout llBenefits, llCount;

        ViewHolder(View itemView) {
            super(itemView);
            llCount = (LinearLayout) itemView.findViewById(R.id.llCount);
            txtSumAssured = (TextView) itemView.findViewById(R.id.txtSumAssured);
            txtDeductible = (TextView) itemView.findViewById(R.id.txtDeductible);
            txtPlanName = (TextView) itemView.findViewById(R.id.txtPlanName);
            txtFinalPremium = (TextView) itemView.findViewById(R.id.txtFinalPremium);
            txtBuy = (TextView) itemView.findViewById(R.id.txtBuy);
            txtRoomRent = (TextView) itemView.findViewById(R.id.txtRoomRent);
            txtIcuRent = (TextView) itemView.findViewById(R.id.txtIcuRent);
            txtPreHosp = (TextView) itemView.findViewById(R.id.txtPreHosp);
            txtPostHosp = (TextView) itemView.findViewById(R.id.txtPostHosp);
            txtNoOfInsurer = (TextView) itemView.findViewById(R.id.txtNoOfInsurer);
            txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
            chkCompare = (CheckBox) itemView.findViewById(R.id.chkCompare);
            imgInsurer = (ImageView) itemView.findViewById(R.id.imgInsurer);
            imgDropDown = (ImageView) itemView.findViewById(R.id.imgDropDown);

            llBenefits = (LinearLayout) itemView.findViewById(R.id.llBenefits);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.txtNoOfInsurer) {
            if (((TextView) view).getText() != HIDE_OPTIONS) {
                ((HealthQuoteFragment) mContext).addMoreQuote(((HealthQuoteEntity) view.getTag(R.id.txtNoOfInsurer)));
                ((HealthQuoteEntity) view.getTag(R.id.txtNoOfInsurer)).setIsMore(true);
                ((TextView) view).setText(HIDE_OPTIONS);

            } else {
                ((TextView) view).setText(" + \n" + String.valueOf(((HealthQuoteEntity) view.getTag(R.id.txtNoOfInsurer)).getTotalChilds() + " \nMore"));
                ((HealthQuoteEntity) view.getTag(R.id.txtNoOfInsurer)).setIsMore(false);
                //remove all added insurer + check no of childs
                removeInsurer(((HealthQuoteEntity) view.getTag(R.id.txtNoOfInsurer)));
            }

        } else if (i == R.id.txtBuy) {//  ((HealthQuoteFragment) mContext).redirectToBuy(((HealthQuoteEntity) view.getTag(R.id.txtBuy)));
            ((HealthQuoteFragment) mContext).getgoogleTrackingHealthBuy();
            ((HealthQuoteFragment) mContext).popUpHealthMemberDetails(((HealthQuoteEntity) view.getTag(R.id.txtBuy)));


        } else if (i == R.id.llBenefits) {
            ((HealthQuoteFragment) mContext).redirectToDetail(((HealthQuoteEntity) view.getTag(R.id.llBenefits)));

        }
    }

    public void refreshNewQuote(List<HealthQuoteEntity> list) {
        listHealthQuotes.addAll(list);
        Collections.sort(listHealthQuotes, new SortbyInsurer());
        notifyDataSetChanged();
    }

    public void updateMainQuote(List<HealthQuoteEntity> list) {

        listHealthQuotes = list;
        checkCount = 1;
        notifyDataSetChanged();
    }


    public void removeRefresh(List<HealthQuoteEntity> list) {

        listHealthQuotes = list;
        Collections.sort(listHealthQuotes, new SortbyInsurer());
        notifyDataSetChanged();
    }

    private void removeInsurer(HealthQuoteEntity entity) {

        int totalRemoved = 0;
        List<HealthQuoteEntity> list = listHealthQuotes;
        for (Iterator<HealthQuoteEntity> iter = list.listIterator(); iter.hasNext(); ) {
            HealthQuoteEntity a = iter.next();
            if (a.getInsurerId() == entity.getInsurerId()) {
                if (a.getTotalChilds() == 0) {
                    totalRemoved++;
                    iter.remove();
                }
            }
        }

        removeRefresh(list);

        //TODO: Reduce count of total quote display

        ((HealthQuoteFragment) mContext).shareTextCount(totalRemoved, false);
    }

    private void updateCheckBox(HealthQuoteEntity entity) {
        for (int i = 0; i < listHealthQuotes.size(); i++) {
            if (listHealthQuotes.get(i).getPlanID() == entity.getPlanID()
                    && listHealthQuotes.get(i).getProductName() == entity.getProductName()
                    && listHealthQuotes.get(i).getSumInsured() == entity.getSumInsured()) {
                listHealthQuotes.get(i).setCompare(entity.isCompare());
            }
        }

        notifyDataSetChanged();
    }
}