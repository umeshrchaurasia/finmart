package com.datacomp.magicfinmart.loan_fm.personalloan;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.datacomp.magicfinmart.loan_fm.personalloan.application.PL_ApplicationFragment;
import com.datacomp.magicfinmart.loan_fm.personalloan.quote.PL_QuoteFragment;

import java.util.ArrayList;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalMainEntity;

/**
 * Created by IN-RB on 12-01-2018.
 */

public class ActivityTabsPagerAdapter_PL extends FragmentStatePagerAdapter {
    private static final int TOTAL = 2;
    public final static String QUOTE_LIST = "LIST_QUOTE";
    public final static String APPLICATION_LIST = "LIST_APPLICATION";
    PersonalMainEntity mMasterData;

    private String[] tabTitles = new String[]{"QUOTES", "APPLICATION"};

    public ActivityTabsPagerAdapter_PL(FragmentManager fm,PersonalMainEntity masterData) {
        super(fm);
        mMasterData = masterData;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        return tabTitles[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Salary fragment activity

                PL_QuoteFragment Qfragment = new PL_QuoteFragment();
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
                PL_ApplicationFragment Afragment = new PL_ApplicationFragment();

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
