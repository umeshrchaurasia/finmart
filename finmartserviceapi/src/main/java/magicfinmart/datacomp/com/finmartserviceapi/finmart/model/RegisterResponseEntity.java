package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public  class RegisterResponseEntity {
        /**
         * SavedStatus : 0
         * Message : Record saved successfully.
         * FBAID : 35770
         */

        private int SavedStatus;
        private String Message;
        private int FBAID;

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

        public int getFBAID() {
            return FBAID;
        }

        public void setFBAID(int FBAID) {
            this.FBAID = FBAID;
        }
    }