package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseERP;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.LeadDataEntity;

/**
 * Created by IN-RB on 26-06-2018.
 */

public class LeadResponse extends APIResponseERP {


    /**
     * result : {"bank_Name":"","bank_id":0,"cust_Name":"FARHAN N BOOTWALA","leadId":0,"lstLeadDtls":[{"Remark":"","Status":"","followupDate":"06/26/2018","followuptime":null,"statusId":15,"updatedBy":"Bhupinder Kaur Sethi","updatedDate":"6/26/2018 1:25:41 PM"},{"Remark":"","Status":"","followupDate":"06/26/2018","followuptime":null,"statusId":15,"updatedBy":"Amit Puri","updatedDate":"1/1/1900 12:00:00 AM"}],"mobileNo":"9172207707","product_Name":"Business Loan","product_id":13}
     */

    private LeadDataEntity result;

    public LeadDataEntity getResult() {
        return result;
    }

    public void setResult(LeadDataEntity result) {
        this.result = result;
    }


}
