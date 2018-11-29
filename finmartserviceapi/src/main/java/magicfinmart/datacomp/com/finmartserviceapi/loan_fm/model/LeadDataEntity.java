package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import java.util.List;

public  class LeadDataEntity {
        /**
         * bank_Name : 
         * bank_id : 0
         * cust_Name : FARHAN N BOOTWALA
         * leadId : 0
         * lstLeadDtls : [{"Remark":"","Status":"","followupDate":"06/26/2018","followuptime":null,"statusId":15,"updatedBy":"Bhupinder Kaur Sethi","updatedDate":"6/26/2018 1:25:41 PM"},{"Remark":"","Status":"","followupDate":"06/26/2018","followuptime":null,"statusId":15,"updatedBy":"Amit Puri","updatedDate":"1/1/1900 12:00:00 AM"}]
         * mobileNo : 9172207707
         * product_Name : Business Loan
         * product_id : 13
         */

        private String bank_Name;
        private int bank_id;
        private String cust_Name;
        private int leadId;
        private String mobileNo;
        private String product_Name;
        private int product_id;
        private List<LeadEntity> lstLeadDtls;

        public String getBank_Name() {
            return bank_Name;
        }

        public void setBank_Name(String bank_Name) {
            this.bank_Name = bank_Name;
        }

        public int getBank_id() {
            return bank_id;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }

        public String getCust_Name() {
            return cust_Name;
        }

        public void setCust_Name(String cust_Name) {
            this.cust_Name = cust_Name;
        }

        public int getLeadId() {
            return leadId;
        }

        public void setLeadId(int leadId) {
            this.leadId = leadId;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getProduct_Name() {
            return product_Name;
        }

        public void setProduct_Name(String product_Name) {
            this.product_Name = product_Name;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public List<LeadEntity> getLstLeadDtls() {
            return lstLeadDtls;
        }

        public void setLstLeadDtls(List<LeadEntity> lstLeadDtls) {
            this.lstLeadDtls = lstLeadDtls;
        }


    }