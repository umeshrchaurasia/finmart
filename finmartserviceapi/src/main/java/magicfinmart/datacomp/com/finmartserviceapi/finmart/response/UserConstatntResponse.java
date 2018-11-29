package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;

/**
 * Created by IN-RB on 04-09-2018.
 */

public class UserConstatntResponse extends APIResponse {


    /**
     * MasterData : {"pospselfid":"3416","pospparentid":"2345","pospsendid":"3416","pospselfname":"TESTPS POSP","pospparentname":"Teet Tetet","pospsendname":"TESTPS POSP","pospselfemail":"testfl@gmail.com","pospparentemail":"qq@gmail.com","pospsendemail":"testfl@gmail.com","pospselfmobile":"9649679767","pospparentmobile":"8864545484","pospsendmobile":"9649679767","pospselfdesignation":"LANDMARK POSP","pospparentdesignation":"test","pospsenddesignation":"LANDMARK POSP","pospselfphoto":"http://qa.mgfm.in/uploads/37292/POSPPhotograph.jpg","pospparentphoto":"","pospsendphoto":"http://qa.mgfm.in/uploads/37292/POSPPhotograph.jpg","loanselfid":"38054","loanparentid":"333","loansendid":"38054","loanselfname":"TEST TEDT","loanparentname":"nitin test","loansendname":"TEST TEDT","loanselfemail":"testfl@gmail.com","loanparentemail":"tedtgr@fff.vfty","loansendemail":"testfl@gmail.com","loanselfmobile":"9687554545","loanparentmobile":"9625657576","loansendmobile":"9687554545","loanselfdesignation":"Finmart Business Associate","loanparentdesignation":"FINMART BUSINESS ASSOCIATE","loansenddesignation":"Finmart Business Associate","loanselfphoto":"http://qa.mgfm.in/uploads/37292/FBAPhotograph.jpg","loanparentphoto":"","loansendphoto":"http://qa.mgfm.in/uploads/37292/FBAPhotograph.jpg","FullName":"TESTpl Test DATACOMP","FBAId":"37292","POSPNo":"3416","POSP_STATUS":"Certified Agent for GI","MangMobile":"9137850198","MangEmail":"rrm.12@magicfinmart.com","SuppMobile":"022-66048200","SuppEmail":"fba.support@magicfinmart.com"}
     */

    private UserConstantEntity MasterData;

    public UserConstantEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(UserConstantEntity MasterData) {
        this.MasterData = MasterData;
    }


}
