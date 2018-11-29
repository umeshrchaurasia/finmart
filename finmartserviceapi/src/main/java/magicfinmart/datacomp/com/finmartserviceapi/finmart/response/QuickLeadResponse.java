package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 26-02-2018.
 */

public class QuickLeadResponse extends APIResponse {

    private MasterDataEntity MasterData;

    public MasterDataEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataEntity MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataEntity {

        private String Lead_Id;


        public String getLead_Id() {
            return Lead_Id;
        }

        public void setLead_Id(String RequestIP) {
            this.Lead_Id = RequestIP;
        }
    }
}
