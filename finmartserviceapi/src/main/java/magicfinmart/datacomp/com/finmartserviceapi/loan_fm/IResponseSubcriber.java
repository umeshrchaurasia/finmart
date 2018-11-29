package magicfinmart.datacomp.com.finmartserviceapi.loan_fm;

/**
 * Created by Rajeev Ranjan on 24/05/2017.
 */
public interface IResponseSubcriber {

    void OnSuccess(APIResponse response, String message);

    void OnFailure(Throwable t);
}