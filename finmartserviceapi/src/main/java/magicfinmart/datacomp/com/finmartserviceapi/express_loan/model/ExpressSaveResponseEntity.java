package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

public  class ExpressSaveResponseEntity {
        /**
         * SavedStatus : 0
         * Message : Record saved successfully.
         */

        private int SavedStatus;

        private String Message;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getMessageX() {
            return Message;
        }

        public void setMessageX(String MessageX) {
            this.Message = MessageX;
        }
    }