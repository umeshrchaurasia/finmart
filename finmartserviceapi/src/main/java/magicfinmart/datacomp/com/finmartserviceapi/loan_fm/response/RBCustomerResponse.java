package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.RBCustomerEntity;

/**
 * Created by IN-RB on 04-03-2018.
 */

public class RBCustomerResponse extends APIResponse {


    /**
     * data : {"status":"Success","ID":7253,"PropertyID":"0","PropertyCost":"2500000","LoanTenure":"15","LoanRequired":"2000000","City":"Mumbai","ApplicantNme":"nisha","Email":null,"Contact":null,"ApplicantGender":"F","ApplicantSource":"1","ApplicantIncome":"250000","ApplicantObligations":"0","ApplicantDOB":"1980-01-01","CoApplicantYes":"N","CoApplicantGender":"","CoApplicantSource":"1","CoApplicantIncome":"0","CoApplicantObligations":"0","CoApplicantDOB":"","Turnover":"0","ProfitAfterTax":"0","Depreciation":"0","DirectorRemuneration":"0","CoApplicantTurnover":"0","CoApplicantProfitAfterTax":"0","CoApplicantDepreciation":"0","CoApplicantDirectorRemuneration":"0","empcode":"0","BrokerId":"25290","ProductId":12,"bank_id":null,"roi_type":null,"loan_eligible":null,"processing_fee":null,"api_source":null,"created_at":"2017-08-09 15:51:33","updated_at":"2017-08-09 15:51:33"}
     */

    private RBCustomerEntity data;

    public RBCustomerEntity getData() {
        return data;
    }

    public void setData(RBCustomerEntity data) {
        this.data = data;
    }


}
