package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public  class IfscEntity {
        /**
         * IFSCCode : IDFB0010201
         * MICRCode : null
         * BankBran : INTERNET BANKINGCPU
         * BankCity : 9149
         * CityName : THANE
         * BankName : IDFC BANK LIMITED
         */

        private String IFSCCode;
        private Object MICRCode;
        private String BankBran;
        private int BankCity;
        private String CityName;
        private String BankName;

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
        }

        public Object getMICRCode() {
            return MICRCode;
        }

        public void setMICRCode(Object MICRCode) {
            this.MICRCode = MICRCode;
        }

        public String getBankBran() {
            return BankBran;
        }

        public void setBankBran(String BankBran) {
            this.BankBran = BankBran;
        }

        public int getBankCity() {
            return BankCity;
        }

        public void setBankCity(int BankCity) {
            this.BankCity = BankCity;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }
    }