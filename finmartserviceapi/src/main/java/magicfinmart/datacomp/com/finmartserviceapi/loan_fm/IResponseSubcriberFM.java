package magicfinmart.datacomp.com.finmartserviceapi.loan_fm;



/**
 * Created by IN-RB on 31-01-2018.
 */

public interface IResponseSubcriberFM {

    void OnSuccessFM(APIResponseFM response, String message);

    void OnFailure(Throwable t);
}
