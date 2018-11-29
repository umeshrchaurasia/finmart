package magicfinmart.datacomp.com.finmartserviceapi.express_loan.response;

import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.ExpressLoanEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 03-04-2018.
 */

public class ExpressLoanListResponse extends APIResponse {


    /**
     * MasterData : {"PersonalLoan":[{"Bank_Id":43,"Bank_Name":"RATNAKAR BANK LTD","Bank_Code":"RBL","Document1":"http://erp.rupeeboss.com/Banklogo/rbl_bank.jpg","WebView":0},{"Bank_Id":33,"Bank_Name":"KOTAK MAHINDRA BANK","Bank_Code":"KOTAK MAHINDRA","Document1":"http://erp.rupeeboss.com/Banklogo/kotak.png","WebView":0},{"Bank_Id":27,"Bank_Name":"IIFL","Bank_Code":"IIFL","Document1":"http://erp.rupeeboss.com/Banklogo/iifl.png","WebView":1},{"Bank_Id":20,"Bank_Name":"HDFC BANK LTD","Bank_Code":"HDFC","Document1":"http://erp.rupeeboss.com/Banklogo/hdfc.png","WebView":0}],"ShortTermPersonalLoan":[{"Bank_Id":999,"Bank_Name":"SHORT TERM PERSONAL LOAN","Bank_Code":"STPL","Document1":"http://www.rupeeboss.com/images/express/early.png","WebView":0}]}
     */

    private ExpressLoanEntity MasterData;

    public ExpressLoanEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(ExpressLoanEntity MasterData) {
        this.MasterData = MasterData;
    }


}
