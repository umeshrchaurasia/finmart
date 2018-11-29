package com.datacomp.magicfinmart.loan_fm.laploan;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.datacomp.magicfinmart.loan_fm.laploan.application.LAP_ApplicationFragment;
import com.datacomp.magicfinmart.loan_fm.laploan.quote.LAP_QuoteFragment;

import java.util.ArrayList;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.HomeLoanRequestMainEntity;

/**
 * Created by IN-RB on 22-01-2018.
 */

public class ActivityTabsPagerAdapter_LAP extends FragmentStatePagerAdapter {
    private static final int TOTAL = 2;
    public final static String QUOTE_LIST = "LIST_QUOTE";
    public final static String APPLICATION_LIST = "LIST_APPLICATION";
    HomeLoanRequestMainEntity mMasterData;

    private String[] tabTitles = new String[]{"QUOTES", "APPLICATION"};

    public ActivityTabsPagerAdapter_LAP(FragmentManager fm, HomeLoanRequestMainEntity masterData) {
        super(fm);
        mMasterData = masterData;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        return tabTitles[position];
    }


    //    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Salary fragment activity

                LAP_QuoteFragment Qfragment = new LAP_QuoteFragment();
                Bundle bundle = new Bundle();

                if (mMasterData == null) {
                    bundle.putParcelableArrayList(QUOTE_LIST, null);

                }else{
                    bundle.putParcelableArrayList(QUOTE_LIST, (ArrayList<? extends Parcelable>) mMasterData.getQuote());
                }
                Qfragment.setArguments(bundle);
                return Qfragment;


            case 1:
                // ABN fragment activity
                LAP_ApplicationFragment Afragment = new LAP_ApplicationFragment();

                Bundle Abundle = new Bundle();
                if (mMasterData == null) {
                    Abundle.putParcelableArrayList(APPLICATION_LIST, null);
                } else {
                    Abundle.putParcelableArrayList(APPLICATION_LIST, (ArrayList<? extends Parcelable>) mMasterData.getApplication());
                }
                Afragment.setArguments(Abundle);
                return Afragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return TOTAL;
    }
}
