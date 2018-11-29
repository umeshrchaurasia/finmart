package com.datacomp.magicfinmart.mps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.BaseFragment;
import com.datacomp.magicfinmart.R;
import com.datacomp.magicfinmart.home.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowMoreMPSFragment extends BaseFragment implements View.OnClickListener {

    TextView txtVisualMPro, txtTabMagic, txtMMagicGyan, txtMHealthMagic, txtGetMPS, txtLater, txtDesc;

    public KnowMoreMPSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_know_more_mps, container, false);
        init(view);
        bindYear();
        setListener();
        return view;
    }

    private void setListener() {
        txtGetMPS.setOnClickListener(this);
        txtLater.setOnClickListener(this);
    }

    private void bindYear() {
        txtDesc.setText(getResources().getString(R.string.know_more_mps_desc) + new PrefManager(getActivity()).getMps().getTotalAmt()
                + " " + getResources().getString(R.string.know_more_mps_desc_2));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        Date nextYear = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String free = "Free till " + simpleDateFormat.format(nextYear);
        txtVisualMPro.setText("Subscription will be extended till " + simpleDateFormat.format(nextYear));
        // txtVisualMPro.setText(free);
        txtTabMagic.setText(free);
        txtMMagicGyan.setText(free);
        txtMHealthMagic.setText(free);

    }

    private void init(View view) {
        txtVisualMPro = (TextView) view.findViewById(R.id.txtVisualMPro);
        txtTabMagic = (TextView) view.findViewById(R.id.txtTabMagic);
        txtMMagicGyan = (TextView) view.findViewById(R.id.txtMMagicGyan);
        txtMHealthMagic = (TextView) view.findViewById(R.id.txtMHealthMagic);
        txtGetMPS = (TextView) view.findViewById(R.id.txtGetMPS);
        txtLater = (TextView) view.findViewById(R.id.txtLater);
        txtDesc = (TextView) view.findViewById(R.id.txtDesc);


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.txtGetMPS) {
            ((HomeActivity) getActivity()).redirectMPS();
               /* startActivity(new Intent(getActivity(), CommonWebViewActivity.class)
                        .putExtra("URL", new PrefManager(getActivity()).getMps().getPaymentURL())
                        .putExtra("NAME", "MPS")
                        .putExtra("TITLE", "MPS"));*/

        } else if (i == R.id.txtLater) {
            ((HomeActivity) getActivity()).selectHome();

        }
    }
}
