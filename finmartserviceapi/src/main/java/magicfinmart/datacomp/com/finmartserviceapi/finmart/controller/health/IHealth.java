package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.health;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuote;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.HealthCompareRequestEntity;

/**
 * Created by Nilesh Birhade 09/02/18
 */

public interface IHealth {

    void getShortLink(String Name, magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber iResponseSubcriber);

    void getHealthQuote(HealthQuote quote, IResponseSubcriber iResponseSubcriber);

    void getHealthQuoteExp(HealthQuote quote, IResponseSubcriber iResponseSubcriber);

    void getHealthQuoteApplicationList(int count,int type,String fbaID, IResponseSubcriber iResponseSubcriber);

    void convertQuoteToApp(String healthRequestID, String insurerID, String insImage, IResponseSubcriber iResponseSubcriber);

    void deleteQuote(String healthRequestID, IResponseSubcriber iResponseSubcriber);

    void compareQuote(HealthCompareRequestEntity compareRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getlistBenefits(String ProdBeneID, IResponseSubcriber iResponseSubcriber);
}
