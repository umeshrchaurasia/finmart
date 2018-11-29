package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;


import com.google.gson.annotations.SerializedName;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 05-10-2018.
 */

public class NotificationUpdateResponse extends APIResponse {


    /**
     * MasterData : {"SavedStatus":0,"Message":"Updated Successfully"}
     */

    private UpdateNotifyEntity MasterData;

    public UpdateNotifyEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(UpdateNotifyEntity MasterData) {
        this.MasterData = MasterData;
    }

    public static class UpdateNotifyEntity {
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

        public void setMessageData(String MessageX) {
            this.Message = MessageX;
        }
    }
}
