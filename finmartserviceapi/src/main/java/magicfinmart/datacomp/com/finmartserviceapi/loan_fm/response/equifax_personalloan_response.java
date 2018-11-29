package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import com.google.gson.annotations.SerializedName;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity.equifax;

/**
 * Created by IN-RB on 18-09-2018.
 */

public class equifax_personalloan_response extends APIResponse {

    /**
     * status : 0
     * result : {"name":"www.rupeeboss.com/uploads/PDF/Hit_CEAPK5523G.pdf","score":"478"}
     */


    private equifax result;

    public equifax getResult() {
        return result;
    }

    public void setResult(equifax result) {
        this.result = result;
    }


}
