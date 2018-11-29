package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 05-03-2018.
 */

public class CCICICIResponse extends APIResponse{


    /**
     * MasterDataEntity : {"$id":"1","ApplicationId":"","Decision":"","ErrorMessage":"Sorry! we are unable to process your application. Please contact administrator.","Reason":""}
     */

    private MasterDataBean MasterData;

    public MasterDataBean getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataBean MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        /**
         * $id : 1
         * ApplicationId :
         * Decision :
         * ErrorMessage : Sorry! we are unable to process your application. Please contact administrator.
         * Reason :
         */

        private String $id;
        private String ApplicationId;
        private String Decision;
        private String ErrorMessage;
        private String Reason;

        public String get$id() {
            return $id;
        }

        public void set$id(String $id) {
            this.$id = $id;
        }

        public String getApplicationId() {
            return ApplicationId;
        }

        public void setApplicationId(String ApplicationId) {
            this.ApplicationId = ApplicationId;
        }

        public String getDecision() {
            return Decision;
        }

        public void setDecision(String Decision) {
            this.Decision = Decision;
        }

        public String getErrorMessage() {
            return ErrorMessage;
        }

        public void setErrorMessage(String ErrorMessage) {
            this.ErrorMessage = ErrorMessage;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }
    }
}
