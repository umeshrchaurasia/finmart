package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

public  class KotakROICAlEntity {
        /**
         * roi : 12.99
         * category : CAT C
         * csc_pf : 0.99
         * non_csc_pf : 1.49
         * ProcFee : 7450
         */

        private String roi;
        private String category;
        private String csc_pf;
        private String non_csc_pf;
        private int ProcFee;

        public String getRoi() {
            return roi;
        }

        public void setRoi(String roi) {
            this.roi = roi;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCsc_pf() {
            return csc_pf;
        }

        public void setCsc_pf(String csc_pf) {
            this.csc_pf = csc_pf;
        }

        public String getNon_csc_pf() {
            return non_csc_pf;
        }

        public void setNon_csc_pf(String non_csc_pf) {
            this.non_csc_pf = non_csc_pf;
        }

        public int getProcFee() {
            return ProcFee;
        }

        public void setProcFee(int ProcFee) {
            this.ProcFee = ProcFee;
        }
    }