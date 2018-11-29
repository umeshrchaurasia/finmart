package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.HomeLoanRequestMainEntity;

/**
 * Created by IN-RB on 01-02-2018.
 */

public class FmHomelLoanResponse extends APIResponseFM {

    /**
     * MasterDataEntity : {"quote":[{"loan_requestID":5,"ID":4170,"PropertyID":1,"PropertyCost":398900000,"LoanTenure":12,"LoanRequired":319120000,"City":"AHMEDABAD","ApplicantNme":"Daniyal","Email":null,"Contact":null,"ApplicantGender":"M","ApplicantSource":1,"ApplicantIncome":235000,"ApplicantObligations":"","ApplicantDOB":"1985-04-12T00:00:00.000Z","CoApplicantYes":"N","CoApplicantGender":"","CoApplicantSource":"","CoApplicantIncome":"","CoApplicantObligations":"","CoApplicantDOB":"0000-00-00","Turnover":"","ProfitAfterTax":"","Depreciation":"","DirectorRemuneration":"","CoApplicantTurnover":"","CoApplicantProfitAfterTax":"","CoApplicantDepreciation":"","CoApplicantDirectorRemuneration":"","empcode":"RB40000428","BrokerId":0,"ProductId":12,"bank_id":null,"roi_type":null,"loan_eligible":null,"processing_fee":null,"api_source":null,"created_at":"2017-04-06T12:26:16.000Z","updated_at":"2017-04-06T12:26:16.000Z","FBA_id":35779,"LoaniD":21312,"Type":"PL","row_created_date":"2018-01-27T06:46:10.000Z","row_updateddate":null,"quote_application_type":"Quote","conversiondate":null},{"loan_requestID":7,"ID":4170,"PropertyID":1,"PropertyCost":398900000,"LoanTenure":12,"LoanRequired":319120000,"City":"AHMEDABAD","ApplicantNme":"Daniyal 123","Email":null,"Contact":null,"ApplicantGender":"M","ApplicantSource":1,"ApplicantIncome":235000,"ApplicantObligations":"","ApplicantDOB":"1985-04-12T00:00:00.000Z","CoApplicantYes":"N","CoApplicantGender":"","CoApplicantSource":"","CoApplicantIncome":"","CoApplicantObligations":"","CoApplicantDOB":"0000-00-00","Turnover":"","ProfitAfterTax":"","Depreciation":"","DirectorRemuneration":"","CoApplicantTurnover":"","CoApplicantProfitAfterTax":"","CoApplicantDepreciation":"","CoApplicantDirectorRemuneration":"","empcode":"RB40000428","BrokerId":0,"ProductId":12,"bank_id":null,"roi_type":null,"loan_eligible":null,"processing_fee":null,"api_source":null,"created_at":"2017-04-06T12:26:16.000Z","updated_at":"2017-04-06T12:26:16.000Z","FBA_id":35779,"LoaniD":21312,"Type":"PL","row_created_date":"2018-01-27T06:56:00.000Z","row_updateddate":null,"quote_application_type":"Quote","conversiondate":null}],"application":[{"loan_requestID":5,"ID":4170,"PropertyID":1,"PropertyCost":398900000,"LoanTenure":12,"LoanRequired":319120000,"City":"AHMEDABAD","ApplicantNme":"Daniyal","Email":null,"Contact":null,"ApplicantGender":"M","ApplicantSource":1,"ApplicantIncome":235000,"ApplicantObligations":"","ApplicantDOB":"1985-04-12T00:00:00.000Z","CoApplicantYes":"N","CoApplicantGender":"","CoApplicantSource":"","CoApplicantIncome":"","CoApplicantObligations":"","CoApplicantDOB":"0000-00-00","Turnover":"","ProfitAfterTax":"","Depreciation":"","DirectorRemuneration":"","CoApplicantTurnover":"","CoApplicantProfitAfterTax":"","CoApplicantDepreciation":"","CoApplicantDirectorRemuneration":"","empcode":"RB40000428","BrokerId":0,"ProductId":12,"bank_id":null,"roi_type":null,"loan_eligible":null,"processing_fee":null,"api_source":null,"created_at":"2017-04-06T12:26:16.000Z","updated_at":"2017-04-06T12:26:16.000Z","FBA_id":35779,"LoaniD":21312,"Type":"PL","row_created_date":"2018-01-27T06:46:10.000Z","row_updateddate":null,"quote_application_type":"Quote","conversiondate":null}]}
     */

    private HomeLoanRequestMainEntity MasterData;

    public HomeLoanRequestMainEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(HomeLoanRequestMainEntity MasterData) {
        this.MasterData = MasterData;
    }

}
