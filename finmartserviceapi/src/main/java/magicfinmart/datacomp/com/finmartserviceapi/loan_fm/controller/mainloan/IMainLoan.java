package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.mainloan;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.BankSaveRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmBalanceLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmHomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.FmPersonalLoanRequest;

/**
 * Created by IN-RB on 31-01-2018.
 */

public interface IMainLoan {

    void getHLQuoteApplicationData(int count, int QA, String fbaid, String type, IResponseSubcriberFM iResponseSubcriber);

    void saveHLQuoteData(FmHomeLoanRequest fmHomeLoanRequest, IResponseSubcriberFM iResponseSubcriber);

    void savePLQuoteData(FmPersonalLoanRequest fmPersonalLoanRequest, IResponseSubcriberFM iResponseSubcriber);

    void getPLQuoteApplication(int count,int type,String fbaid, IResponseSubcriberFM iResponseSubcriber);

    void savebankFbABuyData(BankSaveRequest bankSaveRequest, IResponseSubcriberFM iResponseSubcriber);

    void saveBLQuoteData(FmBalanceLoanRequest fmBalanceLoanRequest, IResponseSubcriberFM iResponseSubcriber);

    void getBLQuoteApplication(int count, int type, String fbaid, IResponseSubcriberFM iResponseSubcriber);

    void getdelete_loanrequest(String loan_requestID, IResponseSubcriberFM iResponseSubcriber);

    void getdelete_personalrequest(String loan_requestID, IResponseSubcriberFM iResponseSubcriber);

    void getdelete_balancerequest(String BalanceTransferId, IResponseSubcriberFM iResponseSubcriber);
}
