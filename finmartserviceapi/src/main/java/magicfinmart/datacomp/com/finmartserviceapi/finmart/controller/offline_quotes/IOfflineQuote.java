package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.offline_quotes;

import java.util.HashMap;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import okhttp3.MultipartBody;

/**
 * Created by Rajeev Ranjan on 05/11/2018.
 */

public interface IOfflineQuote {

    void getOfflineInput(IResponseSubcriber iResponseSubcriber);

    void createQuote(String ProductName, String ProductDiscription, IResponseSubcriber iResponseSubcriber);

    void uploadDocuments(MultipartBody.Part document, HashMap<String, String> body,  IResponseSubcriber iResponseSubcriber);

    void getOfflineQuote( IResponseSubcriber iResponseSubcriber);
}

