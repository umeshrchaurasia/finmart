package magicfinmart.datacomp.com.finmartserviceapi.loan_fm;

/**
 * Created by IN-RB on 04-03-2018.
 */

public interface IResponseSubcriberERP {

    void OnSuccessERP(APIResponseERP response, String message);

    void OnFailure(Throwable t);
}
