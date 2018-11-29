package com.datacomp.magicfinmart.health.compare;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.Utility;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BenefitsEntity;

public class ScrollingBenefitsAdapter extends PagerAdapter {

    // 1.Take list of banner product
    // 2.assign count as size.
    // 3. pass data one by one to fragmetheaderbanner
    Context mContext;
    List<BenefitsEntity> listBenefits;
    LayoutInflater inflater;

    public ScrollingBenefitsAdapter(Context context, List<BenefitsEntity> listbanner) {
        this.mContext = context;
        this.listBenefits = listbanner;
    }

    @Override
    public int getCount() {
        return listBenefits.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {


        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater
                .inflate(R.layout.layout_benefits_nine, container, false);
        TextView txtBenefitsName = (TextView) itemView.findViewById(R.id.txtBenefitsName);
        ImageView imgBenefits = (ImageView) itemView.findViewById(R.id.imgBenefits);

        TextView txtSelect = (TextView) itemView.findViewById(R.id.txtSelect);
        LinearLayout llBenefits = (LinearLayout) itemView.findViewById(R.id.llBenefits);


        txtBenefitsName.setText(listBenefits.get(position).getBeneDesc());

        if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("room rent limit")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.room_rent));
            txtBenefitsName.setText("ROOM RENT");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("icu daily rent limit")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icu_rent));
            txtBenefitsName.setText("ICU RENT");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("pre-hospitalization expenses")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pre_hosp));
            txtBenefitsName.setText("PRE-HOSP");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("post hospitalization expenses")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.post_hosp));
            txtBenefitsName.setText("POST-HOSP");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("day care procedure coverage")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.day_care));
            txtBenefitsName.setText("DAY-CARE");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("automatic restoration of sum insured")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.auto_restore));
            txtBenefitsName.setText("AUTO-RESTORE");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("free health checkup")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.health_check));
            txtBenefitsName.setText("HEALTH CHECK");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("ambulance expenses")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ambulance));
            txtBenefitsName.setText("AMBULANCE");
        } else if (listBenefits.get(position).getBeneDesc().trim().toLowerCase().equals("no claim bonus")) {
            imgBenefits.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ncb));
            txtBenefitsName.setText("NCB");
        }

        if (listBenefits.get(position).isSelected()) {
            txtSelect.setBackgroundResource(R.color.tab_color);
            imgBenefits.setBackgroundResource(Utility.getPrimaryColor(mContext));
            //holder.llBenefits.performClick();
        } else {
            txtSelect.setBackgroundResource(R.color.seperator);
            imgBenefits.setBackgroundResource(R.color.bg2);
        }

        llBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HealthCompareActivity) mContext).updateBenefits(listBenefits.get(position).getBeneDesc());
                // updateList(position);

            }
        });

        container.addView(itemView);
        return itemView;

    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        container.removeView((CardView) object);
    }


    @Override
    public float getPageWidth(int position) {
        return 0.25f;
    }
}