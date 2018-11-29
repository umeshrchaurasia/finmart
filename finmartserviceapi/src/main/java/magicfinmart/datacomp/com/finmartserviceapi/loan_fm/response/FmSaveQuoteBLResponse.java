package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;

/**
 * Created by IN-RB on 25-02-2018.
 */

public class FmSaveQuoteBLResponse  extends APIResponseFM {
    private List<FmSaveQuoteBLResponse.MasterData> MasterData;

    public List<FmSaveQuoteBLResponse.MasterData> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<FmSaveQuoteBLResponse.MasterData> MasterData) {
        this.MasterData = MasterData;
    }

    public  class MasterData {
        /**
         * SavedStatus : 0
         * Message : Saved Successfully
         * LoanRequestID : 8
         */

        private int SavedStatus;

        private String Message;
        private int LoanRequestID;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getMessageFm() {
            return Message;
        }

        public void setMessageFm(String MessageX) {
            this.Message = MessageX;
        }

        public int getLoanRequestID() {
            return LoanRequestID;
        }

        public void setLoanRequestID(int LoanRequestID) {
            this.LoanRequestID = LoanRequestID;
        }
    }
}
