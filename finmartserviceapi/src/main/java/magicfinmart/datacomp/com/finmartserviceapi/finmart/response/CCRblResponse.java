package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import com.google.gson.annotations.SerializedName;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 26-02-2018.
 */

public class CCRblResponse extends APIResponse {

    /**
     * MasterDataEntity : {"Status":3,"ReferenceCode":"#CCD9ODIL1T","EligibleCard":"0","Errorcode":0,"Errorinfo":"","RequestIP":"49.50.95.141"}
     */

    private RblCreditEntity MasterData;

    public RblCreditEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(RblCreditEntity MasterData) {
        this.MasterData = MasterData;
    }

    public static class RblCreditEntity {
        /**
         * Status : 3
         * ReferenceCode : #CCD9ODIL1T
         * EligibleCard : 0
         * Errorcode : 0
         * Errorinfo :
         * RequestIP : 49.50.95.141
         */

        @SerializedName("Status")
        private String StatusX;
        private String ReferenceCode;
        private String EligibleCard;
        private int Errorcode;
        private String Errorinfo;
        private String RequestIP;

        public String getStatusX() {
            return StatusX;
        }

        public void setStatusX(String StatusX) {
            this.StatusX = StatusX;
        }

        public String getReferenceCode() {
            return ReferenceCode;
        }

        public void setReferenceCode(String ReferenceCode) {
            this.ReferenceCode = ReferenceCode;
        }

        public String getEligibleCard() {
            return EligibleCard;
        }

        public void setEligibleCard(String EligibleCard) {
            this.EligibleCard = EligibleCard;
        }

        public int getErrorcode() {
            return Errorcode;
        }

        public void setErrorcode(int Errorcode) {
            this.Errorcode = Errorcode;
        }

        public String getErrorinfo() {
            return Errorinfo;
        }

        public void setErrorinfo(String Errorinfo) {
            this.Errorinfo = Errorinfo;
        }

        public String getRequestIP() {
            return RequestIP;
        }

        public void setRequestIP(String RequestIP) {
            this.RequestIP = RequestIP;
        }
    }
}
