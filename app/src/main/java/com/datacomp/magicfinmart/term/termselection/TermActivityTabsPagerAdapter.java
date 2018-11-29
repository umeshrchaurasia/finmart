package com.datacomp.magicfinmart.term.termselection;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.datacomp.magicfinmart.term.quoteapp.TermApplicationListFragment;
import com.datacomp.magicfinmart.term.quoteapp.TermQuoteListFragment;

import java.util.ArrayList;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.TermQuoteApplicationResponse;


public class TermActivityTabsPagerAdapter extends FragmentStatePagerAdapter {

    public final static String TERM_QUOTE_LIST = "TERM_LIST_QUOTE";
    public final static String TERM_APPLICATION_LIST = "TERM_LIST_APPLICATION";
    TermQuoteApplicationResponse mMasterData;
    private String[] pageTitle = new String[]{"QUOTES", "APPLICATION"};

    public TermActivityTabsPagerAdapter(FragmentManager fm, TermQuoteApplicationResponse masterData) {
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

                TermQuoteListFragment fragment = new TermQuoteListFragment();
                Bundle bundle = new Bundle();
                if (mMasterData == null) {
                    bundle.putParcelableArrayList(TERM_QUOTE_LIST, null);
                } else {
                    bundle.putParcelableArrayList(TERM_QUOTE_LIST, (ArrayList<? extends Parcelable>) mMasterData.getMasterData().getQuote());
                }
                fragment.setArguments(bundle);
                return fragment;

            case 1:

                TermApplicationListFragment Afragment = new TermApplicationListFragment();
                Bundle Abundle = new Bundle();
                if (mMasterData == null) {
                    Abundle.putParcelableArrayList(TERM_APPLICATION_LIST, null);
                } else {
                    Abundle.putParcelableArrayList(TERM_APPLICATION_LIST, (ArrayList<? extends Parcelable>) mMasterData.getMasterData().getApplication());
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