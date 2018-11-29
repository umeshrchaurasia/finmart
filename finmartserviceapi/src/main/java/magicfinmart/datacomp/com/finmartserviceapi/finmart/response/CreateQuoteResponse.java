package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Rajeev Ranjan on 05/11/2018.
 */

public class CreateQuoteResponse extends APIResponse {


    private List<MasterDataBean> MasterData;

    public List<MasterDataBean> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<MasterDataBean> MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        /**
         * reqid : 8
         * Result : Success
         * Message : Saved successfully!
         * SavedStatus : 0
         */

        private int reqid;
        private String Result;
        @SerializedName("Message")
        private String MessageX;
        private int SavedStatus;

        public int getReqid() {
            return reqid;
        }

        public void setReqid(int reqid) {
            this.reqid = reqid;
        }

        public String getResult() {
            return Result;
        }

        public void setResult(String Result) {
            this.Result = Result;
        }

        public String getMessageX() {
            return MessageX;
        }

        public void setMessageX(String MessageX) {
            this.MessageX = MessageX;
        }

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }
    }
}
