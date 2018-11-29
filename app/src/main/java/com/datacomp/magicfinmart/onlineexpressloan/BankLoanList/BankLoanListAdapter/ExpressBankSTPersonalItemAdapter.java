package com.datacomp.magicfinmart.onlineexpressloan.BankLoanList.BankLoanListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.onlineexpressloan.EarlySalaryActivity;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.ShortTermPersonalLoanEntity;

/**
 * Created by IN-RB on 05-04-2018.
 */

public class ExpressBankSTPersonalItemAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Activity mContext;
    DBPersistanceController dbPersistanceController;

    List<ShortTermPersonalLoanEntity> stPersonalLoanEntityList;

    public ExpressBankSTPersonalItemAdapter(Activity mContext, List<ShortTermPersonalLoanEntity> tempstPersonalLoanEntityList) {
        this.mContext = mContext;
        this.stPersonalLoanEntityList = tempstPersonalLoanEntityList;
        dbPersistanceController = new DBPersistanceController(mContext);
    }

    public class STPersonLoanItemHolder extends RecyclerView.ViewHolder {
        TextView txtbankName, txtCardType;
        ImageView imgCard;
        CardView card_view;
        Button btnApply;

        public STPersonLoanItemHolder(View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            txtbankName = (TextView) itemView.findViewById(R.id.txtbankName);
          //  txtCardType = (TextView) itemView.findViewById(R.id.txtCardType);

            imgCard = (ImageView) itemView.findViewById(R.id.imgCard);

          //  btnInfo = (Button) itemView.findViewById(R.id.btnInfo);
            btnApply = (Button) itemView.findViewById(R.id.btnApply);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_banlist_onlineexp_item, parent, false);
        return new ExpressBankSTPersonalItemAdapter.STPersonLoanItemHolder(view);


        //layout_banlist_onlineexp_item
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ExpressBankSTPersonalItemAdapter.STPersonLoanItemHolder){
            final ShortTermPersonalLoanEntity shortPLEntity = stPersonalLoanEntityList.get(position);
            ((ExpressBankSTPersonalItemAdapter.STPersonLoanItemHolder) holder).txtbankName.setText(shortPLEntity.getBank_Name());
            Glide.with(mContext)
                    .load(shortPLEntity.getDocument1())
                    .placeholder(R.drawable.finmart_placeholder) // can also be a drawable
                    .into(((ExpressBankSTPersonalItemAdapter.STPersonLoanItemHolder) holder).imgCard);
            ((STPersonLoanItemHolder) holder).btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, EarlySalaryActivity.class).putExtra("BANK_ID", shortPLEntity.getBank_Id())
                            .putExtra("LOAN_TYPE", shortPLEntity.getProductType()));
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return stPersonalLoanEntityList.size();
    }
}
