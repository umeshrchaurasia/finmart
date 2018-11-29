package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.term;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TermFinmartRequest;

/**
 * Created by Nilesh Birhade on 05-04-2018.
 */

public interface ITermInsurance {

    void getTermQuoteApplicationList(int insurerID, int count, String type, IResponseSubcriber iResponseSubcriber);

    void getTermInsurer(TermFinmartRequest termRequestEntity, IResponseSubcriber iResponseSubcriber);

    void deleteTermQuote(String termRequestId, IResponseSubcriber iResponseSubcriber);

    void convertQuoteToApp(String termRequestId, String InsurerId, String fba_id, String NetPremium, IResponseSubcriber iResponseSubcriber);

    void updateCRN(int termRequestID,int crn,IResponseSubcriber iResponseSubcriber);
}
