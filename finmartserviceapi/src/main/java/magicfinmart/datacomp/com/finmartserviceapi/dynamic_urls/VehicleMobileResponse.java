package magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 06-08-2018.
 */

public class VehicleMobileResponse extends APIResponse {

    /**
     * Message : success
     * Status : success
     * StatusNo : 0
     * CustomerDetails : [{"Category":"MOTOR","ClaimStatus":"0","DOB":"5/30/1983 12:00:00 AM","Email":"pradeepkhandekar@gmail.com/pradeep.khandekar@policyboss.com","ExpiryDate":"10/13/2018 12:00:00 AM","InsuranceID":"","InsuranceName":"ROYAL SUNDARAM GENERAL INSURANCE COMPANY LIMITED","MobileNo":"9920298619","Name":"BHAGWAN","Pincode":"421302","PolicyNumber":"VPT0116003000100","Premium":"3555","QT_Entry_Number":"MUM-0886530-01","VehicleRegNumber":"MH04EQ2620"},{"Category":"MOTOR","ClaimStatus":"0","DOB":"5/30/1983 12:00:00 AM","Email":"pradeepkhandekar@gmail.com/pradeep.khandekar@policyboss.com","ExpiryDate":"11/27/2018 12:00:00 AM","InsuranceID":"","InsuranceName":"RELIANCE GENERAL INSURANCE CO LTD","MobileNo":"9920298619","Name":"BHAGWAN","Pincode":"400708","PolicyNumber":"110821723110124695","Premium":"58193","QT_Entry_Number":"MUM-0886530-02","VehicleRegNumber":"MH43BE6262"},{"Category":"MOTOR","ClaimStatus":"0","DOB":"1/1/1900 12:00:00 AM","Email":"pradeep.khandekar@policyboss.com","ExpiryDate":"11/27/2018 12:00:00 AM","InsuranceID":"","InsuranceName":"","MobileNo":"9920298619","Name":"BHAGWAN","Pincode":"400708","PolicyNumber":"","Premium":"0","QT_Entry_Number":"QT12819810","VehicleRegNumber":"MH43BE6262"},{"Category":"LIFE/OTHER","ClaimStatus":"0","DOB":"","Email":"","ExpiryDate":"3/28/2018 12:00:00 AM","InsuranceID":"","InsuranceName":"","MobileNo":"9920298619","Name":"BHAGWAN","Pincode":"400708","PolicyNumber":"","Premium":"0","QT_Entry_Number":"QT15016064","VehicleRegNumber":""},{"Category":"LIFE/OTHER","ClaimStatus":"0","DOB":"","Email":"","ExpiryDate":"3/29/2018 12:00:00 AM","InsuranceID":"","InsuranceName":"","MobileNo":"9920298619","Name":"TECH","Pincode":"400020","PolicyNumber":"","Premium":"0","QT_Entry_Number":"QT15016489","VehicleRegNumber":""}]
     */


    private List<CustomerDetailsEntity> CustomerDetails;



    public List<CustomerDetailsEntity> getCustomerDetails() {
        return CustomerDetails;
    }

    public void setCustomerDetails(List<CustomerDetailsEntity> CustomerDetails) {
        this.CustomerDetails = CustomerDetails;
    }

    public static class CustomerDetailsEntity {
        /**
         * Category : MOTOR
         * ClaimStatus : 0
         * DOB : 5/30/1983 12:00:00 AM
         * Email : pradeepkhandekar@gmail.com/pradeep.khandekar@policyboss.com
         * ExpiryDate : 10/13/2018 12:00:00 AM
         * InsuranceID :
         * InsuranceName : ROYAL SUNDARAM GENERAL INSURANCE COMPANY LIMITED
         * MobileNo : 9920298619
         * Name : BHAGWAN
         * Pincode : 421302
         * PolicyNumber : VPT0116003000100
         * Premium : 3555
         * QT_Entry_Number : MUM-0886530-01
         * VehicleRegNumber : MH04EQ2620
         */

        private String Category;
        private String ClaimStatus;
        private String DOB;
        private String Email;
        private String ExpiryDate;
        private String InsuranceID;
        private String InsuranceName;
        private String MobileNo;
        private String Name;
        private String Pincode;
        private String PolicyNumber;
        private String Premium;
        private String QT_Entry_Number;
        private String VehicleRegNumber;

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getClaimStatus() {
            return ClaimStatus;
        }

        public void setClaimStatus(String ClaimStatus) {
            this.ClaimStatus = ClaimStatus;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getExpiryDate() {
            return ExpiryDate;
        }

        public void setExpiryDate(String ExpiryDate) {
            this.ExpiryDate = ExpiryDate;
        }

        public String getInsuranceID() {
            return InsuranceID;
        }

        public void setInsuranceID(String InsuranceID) {
            this.InsuranceID = InsuranceID;
        }

        public String getInsuranceName() {
            return InsuranceName;
        }

        public void setInsuranceName(String InsuranceName) {
            this.InsuranceName = InsuranceName;
        }

        public String getMobileNo() {
            return MobileNo;
        }

        public void setMobileNo(String MobileNo) {
            this.MobileNo = MobileNo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPincode() {
            return Pincode;
        }

        public void setPincode(String Pincode) {
            this.Pincode = Pincode;
        }

        public String getPolicyNumber() {
            return PolicyNumber;
        }

        public void setPolicyNumber(String PolicyNumber) {
            this.PolicyNumber = PolicyNumber;
        }

        public String getPremium() {
            return Premium;
        }

        public void setPremium(String Premium) {
            this.Premium = Premium;
        }

        public String getQT_Entry_Number() {
            return QT_Entry_Number;
        }

        public void setQT_Entry_Number(String QT_Entry_Number) {
            this.QT_Entry_Number = QT_Entry_Number;
        }

        public String getVehicleRegNumber() {
            return VehicleRegNumber;
        }

        public void setVehicleRegNumber(String VehicleRegNumber) {
            this.VehicleRegNumber = VehicleRegNumber;
        }
    }
}
