package com.datacomp.magicfinmart.loan_fm.personalloan.addquote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;

/**
 * Created by Rajeev Ranjan on 24/01/2018.
 */

public class BuyFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pl_quote, container, false);
        //initView(view);
        return view;
    }
}
