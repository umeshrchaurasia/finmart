package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponseFM;

/**
 * Created by IN-RB on 09-02-2018.
 */

public class FmSaveQuotePersonalLoanResponse extends APIResponseFM {

    private List<MasterData> MasterData;

    public List<MasterData> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<MasterData> MasterData) {
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
        private int loan_requestID;

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
            return loan_requestID;
        }

        public void setLoanRequestID(int loan_requestID) {
            this.loan_requestID = loan_requestID;
        }
    }
}
