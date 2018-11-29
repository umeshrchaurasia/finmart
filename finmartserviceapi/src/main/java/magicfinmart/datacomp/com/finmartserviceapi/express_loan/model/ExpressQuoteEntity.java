package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

public  class ExpressQuoteEntity {
        /**
         * ExpressLoanRequestId : 78
         * FullName : Test
         * MobileNo : 8647777546
         * City : Mumbai
         * LoanAmount : 500000
         * BankId : 1
         * LoanType : car loan
         * CreatedDate : 06/04/2018
         * FBAID : 12
         * IsActive : 1
         * ApplicationID : 796295
         * Bank_Id : 1
         * Bank_Name : ADITYA BIRLA FINANCE LIMITED
         * Bank_Code : ADITYA BIRLA
         * Document1 : http://erp.rupeeboss.com/Banklogo/a-birla.png
         */

        private int ExpressLoanRequestId;
        private String FullName;
        private String MobileNo;
        private String City;
        private String LoanAmount;
        private int BankId;
        private String LoanType;
        private String CreatedDate;
        private int FBAID;
        private int IsActive;
        private String ApplicationID;
        private int Bank_Id;
        private String Bank_Name;
        private String Bank_Code;
        private String Document1;

        public int getExpressLoanRequestId() {
            return ExpressLoanRequestId;
        }

        public void setExpressLoanRequestId(int ExpressLoanRequestId) {
            this.ExpressLoanRequestId = ExpressLoanRequestId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String FullName) {
            this.FullName = FullName;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getLoanAmount() {
            return LoanAmount;
        }

        public void setLoanAmount(String LoanAmount) {
            this.LoanAmount = LoanAmount;
        }

        public int getBankId() {
            return BankId;
        }

        public void setBankId(int BankId) {
            this.BankId = BankId;
        }

        public String getLoanType() {
            return LoanType;
        }

        public void setLoanType(String LoanType) {
            this.LoanType = LoanType;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public int getFBAID() {
            return FBAID;
        }

        public void setFBAID(int FBAID) {
            this.FBAID = FBAID;
        }

        public int getIsActive() {
            return IsActive;
        }

        public void setIsActive(int IsActive) {
            this.IsActive = IsActive;
        }

        public String getApplicationID() {
            return ApplicationID;
        }

        public void setApplicationID(String ApplicationID) {
            this.ApplicationID = ApplicationID;
        }

        public int getBank_Id() {
            return Bank_Id;
        }

        public void setBank_Id(int Bank_Id) {
            this.Bank_Id = Bank_Id;
        }

        public String getBank_Name() {
            return Bank_Name;
        }

        public void setBank_Name(String Bank_Name) {
            this.Bank_Name = Bank_Name;
        }

        public String getBank_Code() {
            return Bank_Code;
        }

        public void setBank_Code(String Bank_Code) {
            this.Bank_Code = Bank_Code;
        }

        public String getDocument1() {
            return Document1;
        }

        public void setDocument1(String Document1) {
            this.Document1 = Document1;
        }
    }