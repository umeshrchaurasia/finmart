package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

import java.util.List;

public  class ExpressLoanEntity {
        private List<PersonalLoanEntity> PersonalLoan;
      //  private List<ShortTermPersonalLoanEntity> ShortTermPersonalLoan;
        private List<PersonalLoanEntity> HomeLoan;
         private List<PersonalLoanEntity> BusinessLoan;

        public List<PersonalLoanEntity> getPersonalLoan() {
            return PersonalLoan;
        }

        public void setPersonalLoan(List<PersonalLoanEntity> PersonalLoan) {
            this.PersonalLoan = PersonalLoan;
        }

        public List<PersonalLoanEntity> getHomeLoan() {
            return HomeLoan;
        }

        public void setHomeLoan(List<PersonalLoanEntity> HomeLoan) {
            this.HomeLoan = HomeLoan;
        }

        public List<PersonalLoanEntity> getBusinessLoan() {
            return BusinessLoan;
        }

        public void setBusinessLoan(List<PersonalLoanEntity> BusinessLoan) {
            this.BusinessLoan = BusinessLoan;
        }



    }