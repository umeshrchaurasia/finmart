package magicfinmart.datacomp.com.finmartserviceapi.finmart;

/**
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public interface IResponseSubcriber {

    void OnSuccess(APIResponse response, String message);

    void OnFailure(Throwable t);

}