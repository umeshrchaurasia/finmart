package com.datacomp.magicfinmart.health;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.datacomp.magicfinmart.health.quoappfragment.HealthApplicationFragment;
import com.datacomp.magicfinmart.health.quoappfragment.HealthQuoteListFragment;

import java.util.ArrayList;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.HealthQuoteAppResponse;


public class HealthActivityTabsPagerAdapter extends FragmentStatePagerAdapter {

    public final static String HEALTH_QUOTE_LIST = "HEALTH_LIST_QUOTE";
    public final static String HEALTH_APPLICATION_LIST = "HEALTH_LIST_APPLICATION";
    HealthQuoteAppResponse mMasterData;
    private String[] pageTitle = new String[]{"QUOTES", "APPLICATION"};

    public HealthActivityTabsPagerAdapter(FragmentManager fm, HealthQuoteAppResponse masterData) {
        super(fm);
        mMasterData = masterData;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Salary fragment activity
                HealthQuoteListFragment Qfragment = new HealthQuoteListFragment();
                Bundle bundle = new Bundle();
                if (mMasterData == null) {
                    bundle.putParcelableArrayList(HEALTH_QUOTE_LIST, null);
                } else {
                    bundle.putParcelableArrayList(HEALTH_QUOTE_LIST, (ArrayList<? extends Parcelable>) mMasterData.getMasterData().getQuote());
                }
                Qfragment.setArguments(bundle);
                return Qfragment;
            case 1:
                // ABN fragment activity
                HealthApplicationFragment Afragment = new HealthApplicationFragment();
                Bundle Abundle = new Bundle();
                if (mMasterData == null) {
                    Abundle.putParcelableArrayList(HEALTH_APPLICATION_LIST, null);
                } else {
                    Abundle.putParcelableArrayList(HEALTH_APPLICATION_LIST, (ArrayList<? extends Parcelable>) mMasterData.getMasterData().getApplication());
                }
                Afragment.setArguments(Abundle);
                return Afragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
//
//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }
}