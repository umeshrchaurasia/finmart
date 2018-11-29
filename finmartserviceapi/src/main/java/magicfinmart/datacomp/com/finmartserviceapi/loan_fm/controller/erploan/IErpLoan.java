package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.controller.erploan;


import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.IResponseSubcriberERP;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.ErpHomeLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.ErpPersonLoanRequest;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.HomeLoanApplyRequestEntity;

/**
 * Created by IN-RB on 04-03-2018.
 */

public interface IErpLoan {

    void getHomeLoanApplication(String ApplnId, IResponseSubcriberERP iResponseSubcriber);

    void saveERPHomeLoan(ErpHomeLoanRequest erpLoanRequest, IResponseSubcriberERP iResponseSubcriber);

    void saveERPLoanAgainstProperty(ErpHomeLoanRequest erpLoanRequest, IResponseSubcriberERP iResponseSubcriber);

    void getPersonalLoanApplication(String ApplnId, IResponseSubcriberERP iResponseSubcriber);

    void saveERPPersonalLoan(ErpPersonLoanRequest erpLoanRequest, IResponseSubcriberERP iResponseSubcriber);

    void getShareData(final IResponseSubcriber iResponseSubcriber);

    void getLeadDetails(String LeadID, IResponseSubcriberERP iResponseSubcriber);

    void generateLead(HomeLoanApplyRequestEntity homeLoanApplyRequestEntity, IResponseSubcriberERP iResponseSubcriber);
}
