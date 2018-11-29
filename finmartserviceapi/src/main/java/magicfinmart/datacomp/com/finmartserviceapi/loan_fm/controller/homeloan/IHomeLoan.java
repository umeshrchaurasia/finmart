package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.homeloan;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberERP;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.HomeLoanApplyRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.HomeLoanRequest;

/**
 * Created by IN-RB on 12-01-2018.
 */

public interface IHomeLoan {

    void getHomeLoan(HomeLoanRequest homeLoanRequest, IResponseSubcriber iResponseSubcriber);

    void getRBCustomerData(String QuoteID, IResponseSubcriber iResponseSubcriber);


}
