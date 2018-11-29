package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quoteapplication;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.SaveMotorRequestEntity;

/**
 * Created by Nilesh Birhade on 25-01-2018.
 */

public interface IQuoteApp {


    void getQuoteAppList(int count, int type, String firstname, String vehicleReqID, int fbaID, int productID, String crn, IResponseSubcriber iResponseSubcriber);

    void saveQuote(SaveMotorRequestEntity entity, IResponseSubcriber iResponseSubcriber);

    void convertQuoteToApp(String vehicleRequestID, String selectedPrevInsID, String insImage, IResponseSubcriber iResponseSubcriber);

    void deleteQuote(String vehicleRequestID, IResponseSubcriber iResponseSubcriber);
}
