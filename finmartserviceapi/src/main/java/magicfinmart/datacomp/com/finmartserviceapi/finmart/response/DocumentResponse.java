package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by IN-RB on 20-02-2018.
 */

public class DocumentResponse extends APIResponse {


    private List<MasterDataEntity> MasterData;

    public List<MasterDataEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<MasterDataEntity> MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataEntity {
        /**
         * SavedStatus : 0
         * Message : Saved Successfully
         */

        private int SavedStatus;
        private String Message;
        /**
         * RowUpdated : 1
         * prv_file : uploads/39774/POSPPhotograph.jpg
         */

        private int RowUpdated;
        private String prv_file;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getMessageX() {
            return Message;
        }

        public void setMessageX(String Message) {
            this.Message = Message;
        }

        public int getRowUpdated() {
            return RowUpdated;
        }

        public void setRowUpdated(int RowUpdated) {
            this.RowUpdated = RowUpdated;
        }

        public String getPrv_file() {
            return prv_file;
        }

        public void setPrv_file(String prv_file) {
            this.prv_file = prv_file;
        }
    }
}
