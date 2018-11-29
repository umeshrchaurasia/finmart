package com.datacomp.magicfinmart.loan_fm.personalloan.addquote;

/**
 * Created by IN-RB on 18-01-2018.
 */

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalQuoteEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response.GetPersonalLoanResponse;

public class PLQuoteAdapter extends RecyclerView.Adapter<PLQuoteAdapter.PLQuotesItem> {

    Fragment mContext;
    List<PersonalQuoteEntity> quoteEntities;
    GetPersonalLoanResponse getPersonalLoanResponse;
    String bank_web_url="";

    public PLQuoteAdapter(Fragment context, List<PersonalQuoteEntity> quoteEntities , GetPersonalLoanResponse tmpgetPersonalLoanResponse) {
        mContext = context;
        this.quoteEntities = quoteEntities;
        getPersonalLoanResponse = tmpgetPersonalLoanResponse;
    }

    public class PLQuotesItem extends RecyclerView.ViewHolder {

        TextView tvEligibleLoan, tvBestRate, tvBankName, tvBestEmi, tvLoanTenure, tvProcessingFee,tvEmiperlac, btnApply,tvBestRatetype;
        ImageView ivBankLogo,ivArrow;
        LinearLayout rvhlknowmore;
        LinearLayout llivArrow;
        TextView tvDetailsCount1, tvDetailsCount2,tvDetailsCount3,tvDetailsCount4,tvDetailsCount5,tvDetailsCount6;
        LinearLayout llbacklist,llbtnApply;


        public PLQuotesItem(View view) {
            super(view);
            tvEligibleLoan = (TextView) itemView.findViewById(R.id.tvEligibleLoan);
            tvBestRate = (TextView) itemView.findViewById(R.id.tvBestRate);
            tvBankName = (TextView) itemView.findViewById(R.id.tvBankName);
            tvBestEmi = (TextView) itemView.findViewById(R.id.tvBestEmi);
            tvLoanTenure = (TextView) itemView.findViewById(R.id.tvLoanTenure);
            tvProcessingFee = (TextView) itemView.findViewById(R.id.tvProcessingFee);
            btnApply = (TextView) itemView.findViewById(R.id.btnApply);
            ivBankLogo = (ImageView) itemView.findViewById(R.id.ivBankLogo);
           // ivInfo = (ImageView) itemView.findViewById(R.id.ivInfo);
            tvEmiperlac =(TextView)itemView.findViewById(R.id.tvEmiperlac);

            tvBestRatetype = (TextView) itemView.findViewById(R.id.tvBestRatetype);

            rvhlknowmore = (LinearLayout) itemView.findViewById(R.id.rvhlknowmore);

            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
            llivArrow=(LinearLayout) itemView.findViewById(R.id.llivArrow);

            llbacklist = (LinearLayout) itemView.findViewById(R.id.llbacklist);
            llbtnApply=(LinearLayout) itemView.findViewById(R.id.llbtnApply);

            // llkeyhigh = (LinearLayout) itemView.findViewById(R.id.llkeyhigh);
            tvDetailsCount1 = (TextView) itemView.findViewById(R.id.tvDetailsCount1);
            tvDetailsCount2 = (TextView) itemView.findViewById(R.id.tvDetailsCount2);
            tvDetailsCount3 = (TextView) itemView.findViewById(R.id.tvDetailsCount3);
            tvDetailsCount4 = (TextView) itemView.findViewById(R.id.tvDetailsCount4);
            tvDetailsCount5 = (TextView) itemView.findViewById(R.id.tvDetailsCount5);
            tvDetailsCount6 = (TextView) itemView.findViewById(R.id.tvDetailsCount6);
        }
    }

    @Override
    public PLQuoteAdapter.PLQuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_pl_quote_item, parent, false);
        return new PLQuoteAdapter.PLQuotesItem(view);

    }

    @Override
    public void onBindViewHolder(final PLQuotesItem holder, final int position) {

        final PersonalQuoteEntity quoteEntity = quoteEntities.get(position);
        // textViewloanemi.setText("" + "\u20B9" + BigDecimal.valueOf(((EmiCalculatorResponse)response).getData().getAmount()).toPlainString());
        holder.tvEligibleLoan.setText("" + "\u20B9"+" " +String.format("%.0f", quoteEntity.getLoan_eligible()));
        holder.tvBestRate.setText(""  + quoteEntity.getRoi() + " %");
        holder.tvBestRatetype.setText(""+quoteEntity.getRoi_type());
        holder.tvBankName.setText("" + quoteEntity.getBank_Code());
        holder.tvBestEmi.setText(""+ "\u20B9" +" " +  String.format("%.0f", quoteEntity.getEmi()) );
        holder.tvLoanTenure.setText("" + quoteEntity.getLoanTenure()+ " Years");
        holder.tvProcessingFee.setText(""+ "\u20B9"+" "  + String.format("%.0f", quoteEntity.getProcessingfee()));


        double loanr = quoteEntity.getLoan_eligible();
        double emiperlac = (quoteEntity.getEmi() / loanr) * 100000;
        holder.tvEmiperlac.setText(""+ "\u20B9"+" "  + String.format("%.2f", emiperlac));


        Glide.with(mContext)
                .load(quoteEntity.getBank_Logo())
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
                .into(holder.ivBankLogo);


        if (quoteEntity.isIskeyvisible()) {
            // isVisible=false;
            holder.rvhlknowmore.setVisibility(View.VISIBLE);
            holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.up_arrow_blue));

        } else {
            //isVisible=true;
            holder.rvhlknowmore.setVisibility(View.GONE);
            holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow_grey));

        }
        holder.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //bank
                ((QuoteFragment_pl) mContext).redirectToApplyBank(quoteEntity);
                //quote to app conversion
              //  ((QuoteFragment_pl) mContext).quoteToApp();
              //  ((QuoteFragment_pl) mContext).redirectToApplyLoan(quoteEntity,getPersonalLoanResponse.getUrl(), getPersonalLoanResponse.getQuote_id());

            }
        });

        //linear layout click
        holder.llbtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bank
                ((QuoteFragment_pl) mContext).redirectToApplyBank(quoteEntity);
                //quote to app conversion
                //  ((QuoteFragment_pl) mContext).quoteToApp();
                //  ((QuoteFragment_pl) mContext).redirectToApplyLoan(quoteEntity,getPersonalLoanResponse.getUrl(), getPersonalLoanResponse.getQuote_id());

            }
        });


        holder.llivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mContext.startActivity(new Intent(mContext, QuoteInfoActivity.class).putExtra("QUOTEINFO", quoteEntity));

                if (quoteEntity.isIskeyvisible()) {
                    quoteEntity.setIskeyvisible(false);
                    holder.rvhlknowmore.setVisibility(View.GONE);

                    holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow_grey));


                } else {
                    quoteEntity.setIskeyvisible(true);
                    holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.up_arrow_blue));
                    String pre="",par="",spe="";
                    if(quoteEntity.getPre_Closer_Fixed() != null) {
                         pre = " = " + quoteEntity.getPre_Closer_Fixed() + " %";
                    }else
                    {
                         pre = " = ";
                    }
                    if(quoteEntity.getPart_Pmt_Fixed() != null) {
                         par = " = " + quoteEntity.getPart_Pmt_Fixed() + " %";
                    }else
                    {
                        par = " = ";
                    }
                    if(quoteEntity.getWomen_roi() != null) {

                         spe = " = " + quoteEntity.getWomen_roi() + " %";
                    }else
                    {
                         spe = " = ";
                    }

                    holder.tvDetailsCount1.setText(pre);
                    holder.tvDetailsCount2.setText(par);
                    holder.tvDetailsCount3.setText(" = Yes");
                    holder.tvDetailsCount4.setText(spe);
                    holder.tvDetailsCount5.setText(" = Yes");
                    holder.tvDetailsCount6.setText(" = Yes");



                    holder.rvhlknowmore.setVisibility(View.VISIBLE);


                }
            }
        });

        holder.llbacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (quoteEntity.isIskeyvisible()) {
                    quoteEntity.setIskeyvisible(false);
                    holder.rvhlknowmore.setVisibility(View.GONE);

                    holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.down_arrow_grey));


                } else {
                    quoteEntity.setIskeyvisible(true);
                    holder.ivArrow.setImageDrawable(mContext.getResources().getDrawable(R.drawable.up_arrow_blue));

                    String pre = " = "+ quoteEntity.getPre_Closer_Fixed() + " %";
                    String par = " = "+quoteEntity.getPart_Pmt_Fixed() + " %";

                    String spe = " = "+quoteEntity.getWomen_roi() + " %";

                    holder.tvDetailsCount1.setText(pre);
                    holder.tvDetailsCount2.setText(par);
                    holder.tvDetailsCount3.setText(" = Yes");
                    holder.tvDetailsCount4.setText(spe);
                    holder.tvDetailsCount5.setText(" = Yes");
                    holder.tvDetailsCount6.setText(" = Yes");



                    holder.rvhlknowmore.setVisibility(View.VISIBLE);


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }



}
