package magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.HdfcPers_SaveRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.KotakPersonalSaveRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.RBLPesonalLoanReqEntity;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.requestentity.SaveExpressLoanRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

/**
 * Created by IN-RB on 03-04-2018.
 */

public interface IExpressLoan {

    void getExpressQuoteList(String FBAID,  IResponseSubcriber iResponseSubcriber);

    void getExpressLoanList(  IResponseSubcriber iResponseSubcriber);

    void saveExpressLoan(SaveExpressLoanRequestEntity saveExpressLoanRequestEntity, IResponseSubcriber iResponseSubcriber);

    /////  RBL ///////

    void saveRblPersonalLoan(RBLPesonalLoanReqEntity rblPesonalLoanReqEntity, IResponseSubcriber iResponseSubcriber);

    void getRblCalc(String LnAmt, String TnrMths, IResponseSubcriber iResponseSubcriber);
    //HDFC Personal

    void saveHDFCPersonalLoan(HdfcPers_SaveRequestEntity hdfcPers_SaveRequestEntity, IResponseSubcriber iResponseSubcriber);
    //kotak
    void savekotakPersonalLoan(KotakPersonalSaveRequestEntity kotakPersonalSaveRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getKotakPlEmployerName( IResponseSubcriber iResponseSubcriber);

    void getKotakROICalList(String NMI, String Organization, String LnAmt,  IResponseSubcriber iResponseSubcriber);



}
