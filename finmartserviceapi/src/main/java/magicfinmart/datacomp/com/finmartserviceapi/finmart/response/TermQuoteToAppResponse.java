package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 08/05/2018.
 */

public class TermQuoteToAppResponse extends APIResponse {

    private List<MasterDataBean> MasterData;

    public List<MasterDataBean> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<MasterDataBean> MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        /**
         * SavedStatus : 0
         * Message : Record updated successfully.
         */

        private int SavedStatus;
        @SerializedName("Message")
        private String MessageX;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getMessageX() {
            return MessageX;
        }

        public void setMessageX(String MessageX) {
            this.MessageX = MessageX;
        }
    }
}
