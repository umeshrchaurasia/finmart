package com.datacomp.magicfinmart.onlineexpressloan.BankLoanList.BankLoanListAdapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datacomp.magicfinmart.R;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.ExpressLoanEntity;

/**
 * Created by IN-RB on 05-04-2018.
 */

public class ExpressBankRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ROW_PERSONAL = 0;
    //  private static final int ROW_STPERSONAL = 1;
    private static final int ROW_homeloan = 1;
    private static final int ROW_BuisnessLoan = 2;

    private static int TOTAL_ROW = 3;

    Activity mContext;
    DBPersistanceController mReal;
    ExpressLoanEntity expressLoanEntity;


    public ExpressBankRowAdapter(Activity mContext, ExpressLoanEntity tempexpressLoanEntity) {
        this.mContext = mContext;

        this.expressLoanEntity = tempexpressLoanEntity;


    }


    public class PersonalHolder extends RecyclerView.ViewHolder {

        RecyclerView rvExpress;
        TextView txtTypeName;

        public PersonalHolder(View view) {
            super(view);

            rvExpress = (RecyclerView) view.findViewById(R.id.rvExpress);
            txtTypeName = (TextView) view.findViewById(R.id.txtTypeName);
        }
    }


    public class STHomeLoanHolder extends RecyclerView.ViewHolder {

        RecyclerView rvExpress;
        TextView txtTypeName;

        public STHomeLoanHolder(View view) {
            super(view);

            rvExpress = (RecyclerView) view.findViewById(R.id.rvExpress);
            txtTypeName = (TextView) view.findViewById(R.id.txtTypeName);
        }
    }

    public class STBusinessLoanlHolder extends RecyclerView.ViewHolder {

        RecyclerView rvExpress;
        TextView txtTypeName;

        public STBusinessLoanlHolder(View view) {
            super(view);

            rvExpress = (RecyclerView) view.findViewById(R.id.rvExpress);
            txtTypeName = (TextView) view.findViewById(R.id.txtTypeName);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {

            case ROW_PERSONAL:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_express_recycler, parent, false);
                return new ExpressBankRowAdapter.PersonalHolder(view);

            case ROW_homeloan:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_express_recycler, parent, false);
                return new ExpressBankRowAdapter.STHomeLoanHolder(view);

            case ROW_BuisnessLoan:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_express_recycler, parent, false);
                return new ExpressBankRowAdapter.STBusinessLoanlHolder(view);


            default:
                break;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof PersonalHolder) {

            ((ExpressBankRowAdapter.PersonalHolder) holder).txtTypeName.setText("PERSONAL LOAN");
            ((ExpressBankRowAdapter.PersonalHolder) holder).rvExpress.setLayoutManager(new LinearLayoutManager(mContext));
            ((ExpressBankRowAdapter.PersonalHolder) holder).rvExpress.setAdapter(new ExpressBankPersonalItemAdapter(mContext, expressLoanEntity.getPersonalLoan()));
        } else if (holder instanceof STHomeLoanHolder) {


            ((ExpressBankRowAdapter.STHomeLoanHolder) holder).txtTypeName.setText("HOME LOAN");
            ((ExpressBankRowAdapter.STHomeLoanHolder) holder).rvExpress.setLayoutManager(new LinearLayoutManager(mContext));
            ((ExpressBankRowAdapter.STHomeLoanHolder) holder).rvExpress.setAdapter(new ExpressBankPersonalItemAdapter(mContext, expressLoanEntity.getHomeLoan()));
        } else if (holder instanceof STBusinessLoanlHolder) {


            ((ExpressBankRowAdapter.STBusinessLoanlHolder) holder).txtTypeName.setText("BUSINESS LOAN");
            ((ExpressBankRowAdapter.STBusinessLoanlHolder) holder).rvExpress.setLayoutManager(new LinearLayoutManager(mContext));
            ((ExpressBankRowAdapter.STBusinessLoanlHolder) holder).rvExpress.setAdapter(new ExpressBankPersonalItemAdapter(mContext, expressLoanEntity.getBusinessLoan()));
        }

    }

    @Override
    public int getItemCount() {
        {
            return TOTAL_ROW;
        }
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {

            case ROW_PERSONAL:
                return ROW_PERSONAL;
            case ROW_homeloan:
                return ROW_homeloan;
            case ROW_BuisnessLoan:
                return ROW_BuisnessLoan;

            default:
                break;
        }
        return position;
    }
}
