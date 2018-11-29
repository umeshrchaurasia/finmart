package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 05/11/2018.
 */

public class OfflineInputResponse extends APIResponse {


    /**
     * MasterData : {"quotematerail":[{"id":1,"productname":"Motor Private","description":"Type of Policy (Comprehensive / TP only) : \nMake : \nModel : \nVariant : \nYear of Manufacture : \nDate of Registration :  \nRTO (where registered) : \nClaim Status in last policy : \nSum Insured (IDV) in the last policy : \nInsurer : \nAny special cover needed : ","isactive":1},{"id":2,"productname":"Motor Commercial \u2013 Goods Carrying","description":"Type of Policy (Comprehensive / TP only) : \nMake : \nModel : \nVariant : \nYear of Manufacture : \nDate of Registration : \nRTO (where registered) : \nGross Vehicle Weight : \nClaim Status in last policy : \nSum Insured (IDV) in the last policy : \nInsurer : \nAny special cover needed : \nUsage (for eg Petrol Tanker / refrigerated van etc) : ","isactive":1},{"id":3,"productname":"Motor Commercial \u2013 Passenger Carrying","description":"Type of Policy (Comprehensive / TP only) : \nMake : \nModel : \nVariant : \nYear of Manufacture : \nDate of Registration : \nRTO (where registered) : \nSeating Capacity : \nClaim Status in last policy : \nSum Insured (IDV) in the last policy : \nInsurer : \nAny special cover needed : \nUsage (for intercity taxi/ school bus / stage carrier etc) : ","isactive":1},{"id":4,"productname":"Health","description":"Type of policy (individual / floater) : \nCity of residence : \nSum Insured required : \n\nMember1Age (date of birth) : \nMember1Gender : \n\nMember2Age (date of birth) : \nMember2Gender : \n\nMember3Age (date of birth) : \nMember3Gender : \n\nMember4Age (date of birth) : \nMember4Gender : \n\nMember5Age (date of birth) : \nMember6Gender : ","isactive":1},{"id":5,"productname":"Life","description":"Date of Birth : \nGender : \nCity of residence : \nSmoker \u2013 Non Smoker : \nSum Insured : \npremium paying term : \npolicy term : ","isactive":1}],"quotedoc":[{"id":1,"docname":"Last year policy copy","reqid":1},{"id":1,"docname":"RC copy","reqid":2},{"id":2,"docname":"Last year policy copy","reqid":3},{"id":2,"docname":"RC copy","reqid":4},{"id":3,"docname":"Attach Last year policy copy","reqid":5},{"id":3,"docname":"RC copy","reqid":6}]}
     */

    private MasterDataBean MasterData;

    public MasterDataBean getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataBean MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        private List<OfflineInputEntity> quotematerail;
        private List<RequiredDocEntity> quotedoc;

        public List<OfflineInputEntity> getQuotematerail() {
            return quotematerail;
        }

        public void setQuotematerail(List<OfflineInputEntity> quotematerail) {
            this.quotematerail = quotematerail;
        }

        public List<RequiredDocEntity> getQuotedoc() {
            return quotedoc;
        }

        public void setQuotedoc(List<RequiredDocEntity> quotedoc) {
            this.quotedoc = quotedoc;
        }

    }
}
