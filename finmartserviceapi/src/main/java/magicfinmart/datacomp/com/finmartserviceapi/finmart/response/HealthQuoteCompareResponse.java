package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 08-03-2018.
 */

public class HealthQuoteCompareResponse extends APIResponse {

    /**
     * MasterData : {"NetPremium":7594.48,"ProposerPageUrl":"http://qa.policyboss.com/HealthInsuranceIndia/ContactDetails?CustomerReferenceNumber=172878&SelectedQuoteId=16&SupportsAgentID=2&CallingSource=POSPAPP&IsCustomer=0"}
     */

    private MasterDataCompare MasterData;

    public MasterDataCompare getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataCompare MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataCompare {
        /**
         * NetPremium : 7594.48
         * ProposerPageUrl : http://qa.policyboss.com/HealthInsuranceIndia/ContactDetails?CustomerReferenceNumber=172878&SelectedQuoteId=16&SupportsAgentID=2&CallingSource=POSPAPP&IsCustomer=0
         */

        private double NetPremium;
        private String ProposerPageUrl;

        public double getNetPremium() {
            return NetPremium;
        }

        public void setNetPremium(double NetPremium) {
            this.NetPremium = NetPremium;
        }

        public String getProposerPageUrl() {
            return ProposerPageUrl;
        }

        public void setProposerPageUrl(String ProposerPageUrl) {
            this.ProposerPageUrl = ProposerPageUrl;
        }
    }
}
