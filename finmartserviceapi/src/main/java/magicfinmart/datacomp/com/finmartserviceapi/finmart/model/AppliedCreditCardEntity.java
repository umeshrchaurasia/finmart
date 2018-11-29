package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import com.google.gson.annotations.SerializedName;

public class AppliedCreditCardEntity {
        /**
         * CreditCardRequestId : 6
         * FullName : testing pls corporate test
         * ApplicationNo : #CCT1ZQKM6T
         * Status : 
         * Email : daniyal6.shaikh@gmail.com
         * MobileNo : 9702911180
         * fba_id : 123
         * CardType : RBL
         * CreditCardName : Titanium Delight Card
         * CreatedDate : 2018-02-27T10:46:33.000Z
         */

        private int CreditCardRequestId;
        private String FullName;
        private String ApplicationNo;
        @SerializedName("Status")
        private String StatusX;
        private String Email;
        private String MobileNo;
        private String fba_id;
        private String CardType;
        private String CreditCardName;
        private String CreatedDate;

        public int getCreditCardRequestId() {
            return CreditCardRequestId;
        }

        public void setCreditCardRequestId(int CreditCardRequestId) {
            this.CreditCardRequestId = CreditCardRequestId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getApplicationNo() {
            return ApplicationNo;
        }

        public void setApplicationNo(String ApplicationNo) {
            this.ApplicationNo = ApplicationNo;
        }

        public String getStatusX() {
            return StatusX;
        }

        public void setStatusX(String StatusX) {
            this.StatusX = StatusX;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getFba_id() {
            return fba_id;
        }

        public void setFba_id(String fba_id) {
            this.fba_id = fba_id;
        }

        public String getCardType() {
            return CardType;
        }

        public void setCardType(String CardType) {
            this.CardType = CardType;
        }

        public String getCreditCardName() {
            return CreditCardName;
        }

        public void setCreditCardName(String CreditCardName) {
            this.CreditCardName = CreditCardName;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }
    }