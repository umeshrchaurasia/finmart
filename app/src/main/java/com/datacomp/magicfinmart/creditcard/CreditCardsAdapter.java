package com.datacomp.magicfinmart.creditcard;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.CreditCardEntity;

public class CreditCardsAdapter extends RecyclerView.Adapter<CreditCardsAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    Context mContext;
    List<CreditCardEntity> listCreditCards;

    // data is passed into the constructor
    CreditCardsAdapter(Context context, List<CreditCardEntity> list) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        listCreditCards = list;
    }


    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_credit_card_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ViewHolder hold = (ViewHolder) holder;
        final CreditCardEntity entity = listCreditCards.get(position);
        hold.txtCardbankName.setText("" + entity.getBankName());
        hold.txtCardType.setText(entity.getCreditCardType());
        if (entity.getDisplaycardname() == null) {
            hold.txtDisplayCardName.setVisibility(View.GONE);
        } else {
            hold.txtDisplayCardName.setVisibility(View.VISIBLE);
            hold.txtDisplayCardName.setText(entity.getDisplaycardname());
        }
        if (entity.getImagePath() != null) {
            Glide.with(mContext).load(entity.getImagePath()).into(hold.imgCard);
        }

        String desc[] = entity.getDescription().split("\\|");
        final StringBuilder sb = new StringBuilder();
        for (String s : desc) {
            sb.append("*  " + s.trim() + "\n");
        }
        hold.txtCCDesc.setText("" + sb.toString());

        hold.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogMessage(sb.toString(), entity.getDisplaycardname());
            }
        });

        hold.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CreditCardActivity) mContext).redirectToApply(entity);
            }
        });
    }


    // total number of cells
    @Override
    public int getItemCount() {
        return listCreditCards.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCCDesc, txtCardType, txtCardbankName, txtDisplayCardName;
        ImageView imgCard;
        CardView cvCCItem;
        Button btnApply, btnInfo;

        ViewHolder(View v) {
            super(v);
            txtCCDesc = (TextView) v.findViewById(R.id.txtCCDesc);
            txtCardType = (TextView) v.findViewById(R.id.txtCardType);
            txtCardbankName = (TextView) v.findViewById(R.id.txtCardbankName);
            txtDisplayCardName = (TextView) v.findViewById(R.id.txtDisplayCardName);
            imgCard = (ImageView) v.findViewById(R.id.imgCard);
            cvCCItem = (CardView) v.findViewById(R.id.cvCCItem);
            btnInfo = (Button) v.findViewById(R.id.btnInfo);
            btnApply = (Button) v.findViewById(R.id.btnApply);

        }
    }

    public void refreshCreditCards(List<CreditCardEntity> list) {
        listCreditCards = list;
        notifyDataSetChanged();
    }

    private void dialogMessage(String displayMessage, String cardType) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setTitle(cardType);

        builder.setMessage(displayMessage)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        TextView msgTxt = (TextView) dialog.findViewById(android.R.id.message);
        msgTxt.setTextSize(12.0f);
    }
}