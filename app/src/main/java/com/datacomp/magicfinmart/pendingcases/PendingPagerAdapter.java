package com.datacomp.magicfinmart.pendingcases;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.response.PendingCaseInsLoanResponse;


public class PendingPagerAdapter extends FragmentStatePagerAdapter {

    private static final int TOTAL = 2;
    public final static String INSURANCE_LIST = "LIST_INSURANCE";
    public final static String LOAN_LIST = "LIST_LOAN";
    PendingCaseInsLoanResponse.MasterDataBean mMasterData;

    private String[] pageTitle = new String[]{"INSURANCE", "LOAN"};


    public PendingPagerAdapter(FragmentManager fm, PendingCaseInsLoanResponse.MasterDataBean masterData) {
        super(fm);
        mMasterData = masterData;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
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
                PendingCaseFragment Qfragment = new PendingCaseFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(PendingCaseFragment.TYPE, 1);
                if (mMasterData == null) {
                    bundle.putParcelableArrayList(INSURANCE_LIST, null);
                } else {
                    bundle.putParcelableArrayList(INSURANCE_LIST, (ArrayList<? extends Parcelable>) mMasterData.getInsurance());
                }
                Qfragment.setArguments(bundle);
                return Qfragment;
            case 1:
                // ABN fragment activity
                PendingCaseFragment Afragment = new PendingCaseFragment();
                Bundle Abundle = new Bundle();
                Abundle.putInt(PendingCaseFragment.TYPE, 2);
                if (mMasterData == null) {
                    Abundle.putParcelableArrayList(LOAN_LIST, null);
                } else {
                    Abundle.putParcelableArrayList(LOAN_LIST, (ArrayList<? extends Parcelable>) mMasterData.getLoan());
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