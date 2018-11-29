package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;


import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalMainEntity;

/**
 * Created by IN-RB on 01-02-2018.
 */

public class FmPersonalLoanResponse extends APIResponseFM {
    private PersonalMainEntity MasterData;

    public PersonalMainEntity getMasterData() {
        return MasterData;
    }
    public void setMasterData(PersonalMainEntity MasterData) {
        this.MasterData = MasterData;
    }

}
