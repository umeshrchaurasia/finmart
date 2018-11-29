package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 20-02-2018.
 */

public class MyAccountResponse  extends APIResponse {


    /**
     * MasterDataEntity : {"SavedStatus":0,"Message":"Updated Successfully"}
     */

    private MasterDataEntity MasterData;

    public MasterDataEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataEntity MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataEntity {
        /**
         * SavedStatus : 0
         * Message : Updated Successfully
         */

        private int SavedStatus;
        private String Message;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getMessageData() {
            return Message;
        }

        public void setMessageX(String Message) {
            this.Message = Message;
        }
    }
}
