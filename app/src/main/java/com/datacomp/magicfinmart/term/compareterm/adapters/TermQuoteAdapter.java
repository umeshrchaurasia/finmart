package com.datacomp.magicfinmart.term.compareterm.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.term.compareterm.TermInputFragment;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TermCompareResponseEntity;

public class TermQuoteAdapter extends RecyclerView.Adapter<TermQuoteAdapter.TermQuoteItem> {


    Fragment mContext;
    String age;
    List<TermCompareResponseEntity> listQuotes;
    DBPersistanceController dbPersistanceController;

    public TermQuoteAdapter(Fragment mContext, List<TermCompareResponseEntity> listQuotes, String age) {
        this.mContext = mContext;
        this.listQuotes = listQuotes;
        this.age = age;
        dbPersistanceController = new DBPersistanceController(mContext.getContext());

    }

    @Override
    public TermQuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_termquote_item, parent, false);
        return new TermQuoteAdapter.TermQuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(final TermQuoteItem holder, int position) {
        final TermCompareResponseEntity responseEntity = listQuotes.get(position);

        holder.txtPlanNAme.setText("" + responseEntity.getProductPlanName());
        holder.txtCover.setText("" + responseEntity.getSumAssured());
        holder.txtAge.setText("" + age + " Yrs.");
        holder.txtPolicyTerm.setText(responseEntity.getPolicyTermYear() + " Yrs.");
        holder.txtFinalPremium.setText("\u20B9 " + responseEntity.getNetPremium());
        // holder.txtFinalPremium.setText("\u20B9 " + Math.round(Double.parseDouble(responseEntity.getFinal_premium_with_addon())));

        /*if(responseEntity.getInsurerId()==39){
            holder.imgInsurerLogo.setImageDrawable();
        }*/
        if (responseEntity.getInsurerId() == 39)
            holder.imgInsurerLogo.setImageResource(R.drawable.icici_life_icon);
        else if (responseEntity.getInsurerId() == 28)
            holder.imgInsurerLogo.setImageResource(R.drawable.hdfc_life_icon);
        else if (responseEntity.getInsurerId() == 0)
            holder.imgInsurerLogo.setImageResource(R.drawable.icici_life_icon);
        else {
            Glide.with(mContext)
                    .load("http://www.policyboss.com/Images/insurer_logo/" + responseEntity.getInsurerLogoName())
                    .into(holder.imgInsurerLogo);
        }

        holder.txtCustomise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TermInputFragment) mContext).redirectToCustomize(responseEntity);
            }
        });
        holder.tvBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.llAddon.setVisibility(View.VISIBLE);

                holder.tvBenefits.setVisibility(View.GONE);
                holder.ivDownArrow.setVisibility(View.GONE);

                holder.ivUpArrow.setVisibility(View.VISIBLE);
                holder.tvBenefitsBelow.setVisibility(View.VISIBLE);
                //((TermQuoteFragment) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });
        holder.tvBenefitsBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.llAddon.setVisibility(View.GONE);

                holder.tvBenefits.setVisibility(View.VISIBLE);
                holder.ivDownArrow.setVisibility(View.VISIBLE);

                holder.ivUpArrow.setVisibility(View.GONE);
                holder.tvBenefitsBelow.setVisibility(View.GONE);
                //((TermQuoteFragment) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });
        holder.txtFinalPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TermInputFragment) mContext).redirectToBuy(responseEntity);
            }
        });

        if (responseEntity.getKeyFeatures() != null) {
            //holder.llAddon.setVisibility(View.VISIBLE);
            holder.rvAddOn.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext.getActivity(), 2);
            holder.rvAddOn.setLayoutManager(mLayoutManager);
            GridTermAdapter adapter = new GridTermAdapter(mContext.getActivity(), responseEntity.getKeyFeatures().split("\\|"));
            holder.rvAddOn.setAdapter(adapter);

        } else {
            holder.llAddon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if (listQuotes != null) {
            return listQuotes.size();
        } else {
            return 0;
        }
    }

    public class TermQuoteItem extends RecyclerView.ViewHolder {
        public TextView txtPlanNAme, txtCover, txtFinalPremium, txtPolicyTerm, txtAge, txtCustomise, tvBenefits, tvBenefitsBelow;
        ImageView imgInsurerLogo, ivUpArrow, ivDownArrow;
        LinearLayout llAddon;
        RecyclerView rvAddOn;

        public TermQuoteItem(View itemView) {
            super(itemView);
            llAddon = (LinearLayout) itemView.findViewById(R.id.llAddon);
            rvAddOn = (RecyclerView) itemView.findViewById(R.id.rvAddOn);
            txtAge = (TextView) itemView.findViewById(R.id.txtAge);
            txtCustomise = (TextView) itemView.findViewById(R.id.txtCustomise);
            tvBenefits = (TextView) itemView.findViewById(R.id.tvBenefits);
            tvBenefitsBelow = (TextView) itemView.findViewById(R.id.tvBenefitsBelow);
            txtPlanNAme = (TextView) itemView.findViewById(R.id.txtPlanNAme);
            txtCover = (TextView) itemView.findViewById(R.id.txtCover);
            txtFinalPremium = (TextView) itemView.findViewById(R.id.txtFinalPremium);
            imgInsurerLogo = (ImageView) itemView.findViewById(R.id.imgInsurerLogo);
            ivUpArrow = (ImageView) itemView.findViewById(R.id.ivUpArrow);
            ivDownArrow = (ImageView) itemView.findViewById(R.id.ivDownArrow);
            txtPolicyTerm = (TextView) itemView.findViewById(R.id.txtPolicyTerm);
        }
    }


}
