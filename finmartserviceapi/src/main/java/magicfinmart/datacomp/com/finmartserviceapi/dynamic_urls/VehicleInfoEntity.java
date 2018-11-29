package magicfinmart.datacomp.com.finmartserviceapi.dynamic_urls;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

public class VehicleInfoEntity extends APIResponse{

    private List<VehicleInfo> GetRegNoDataResult;

    public List<VehicleInfo> getGetRegNoDataResult() {
        return GetRegNoDataResult;
    }

    public void setGetRegNoDataResult(List<VehicleInfo> GetRegNoDataResult) {
        this.GetRegNoDataResult = GetRegNoDataResult;
    }

    public static class VehicleInfo {
        /**
         * ChasisNo : 370045
         * City : NAVI MUMBAI
         * ClaimNo : 3118076771
         * ClaimSattlementType : CASHLESS
         * ClaimStatus : Close
         * ClientName : BHAGWAN M KHANDEKAR
         * DOB : 30 May 1983
         * EngineNo : 43275
         * ExpiryDate : 27 Nov 2018
         * FuelType : Diesel
         * Gender :
         * HolderPincode : 400708
         * InceptionDate : 28 Nov 2017
         * IsCustomer : Yes
         * Make : TOYOTA
         * Mfgyear : 2016
         * NoClaimBonus : 0
         * POSPCode :
         * POSPName :
         * RTOCity : Navi Mumbai
         * RTOState : MAHARASHTRA
         * RegistrationDate :
         * RegistrationNo : MH43BE6262
         * SubModel : 3.0 L 2WD
         * holderaddress : E-1/02,ASHIRWAD CHS,SECTOR-15,AIROLI,NAVI MUMBAI
         * model : FORTUNER
         */

        private String ChasisNo;
        private String City;
        private String ClaimNo;
        private String ClaimSattlementType;
        private String ClaimStatus;
        private String ClientName;
        private String DOB;
        private String EngineNo;
        private String ExpiryDate;
        private String FuelType;
        private String Gender;
        private String HolderPincode;
        private String InceptionDate;
        private String IsCustomer;
        private String Make;
        private int Mfgyear;
        private String NoClaimBonus;
        private String POSPCode;
        private String POSPName;
        private String RTOCity;
        private String RTOState;
        private String RegistrationDate;
        private String RegistrationNo;
        private String SubModel;
        private String holderaddress;
        private String model;

        public String getChasisNo() {
            return ChasisNo;
        }

        public void setChasisNo(String ChasisNo) {
            this.ChasisNo = ChasisNo;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getClaimNo() {
            return ClaimNo;
        }

        public void setClaimNo(String ClaimNo) {
            this.ClaimNo = ClaimNo;
        }

        public String getClaimSattlementType() {
            return ClaimSattlementType;
        }

        public void setClaimSattlementType(String ClaimSattlementType) {
            this.ClaimSattlementType = ClaimSattlementType;
        }

        public String getClaimStatus() {
            return ClaimStatus;
        }

        public void setClaimStatus(String ClaimStatus) {
            this.ClaimStatus = ClaimStatus;
        }

        public String getClientName() {
            return ClientName;
        }

        public void setClientName(String ClientName) {
            this.ClientName = ClientName;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getEngineNo() {
            return EngineNo;
        }

        public void setEngineNo(String EngineNo) {
            this.EngineNo = EngineNo;
        }

        public String getExpiryDate() {
            return ExpiryDate;
        }

        public void setExpiryDate(String ExpiryDate) {
            this.ExpiryDate = ExpiryDate;
        }

        public String getFuelType() {
            return FuelType;
        }

        public void setFuelType(String FuelType) {
            this.FuelType = FuelType;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getHolderPincode() {
            return HolderPincode;
        }

        public void setHolderPincode(String HolderPincode) {
            this.HolderPincode = HolderPincode;
        }

        public String getInceptionDate() {
            return InceptionDate;
        }

        public void setInceptionDate(String InceptionDate) {
            this.InceptionDate = InceptionDate;
        }

        public String getIsCustomer() {
            return IsCustomer;
        }

        public void setIsCustomer(String IsCustomer) {
            this.IsCustomer = IsCustomer;
        }

        public String getMake() {
            return Make;
        }

        public void setMake(String Make) {
            this.Make = Make;
        }

        public int getMfgyear() {
            return Mfgyear;
        }

        public void setMfgyear(int Mfgyear) {
            this.Mfgyear = Mfgyear;
        }

        public String getNoClaimBonus() {
            return NoClaimBonus;
        }

        public void setNoClaimBonus(String NoClaimBonus) {
            this.NoClaimBonus = NoClaimBonus;
        }

        public String getPOSPCode() {
            return POSPCode;
        }

        public void setPOSPCode(String POSPCode) {
            this.POSPCode = POSPCode;
        }

        public String getPOSPName() {
            return POSPName;
        }

        public void setPOSPName(String POSPName) {
            this.POSPName = POSPName;
        }

        public String getRTOCity() {
            return RTOCity;
        }

        public void setRTOCity(String RTOCity) {
            this.RTOCity = RTOCity;
        }

        public String getRTOState() {
            return RTOState;
        }

        public void setRTOState(String RTOState) {
            this.RTOState = RTOState;
        }

        public String getRegistrationDate() {
            return RegistrationDate;
        }

        public void setRegistrationDate(String RegistrationDate) {
            this.RegistrationDate = RegistrationDate;
        }

        public String getRegistrationNo() {
            return RegistrationNo;
        }

        public void setRegistrationNo(String RegistrationNo) {
            this.RegistrationNo = RegistrationNo;
        }

        public String getSubModel() {
            return SubModel;
        }

        public void setSubModel(String SubModel) {
            this.SubModel = SubModel;
        }

        public String getHolderaddress() {
            return holderaddress;
        }

        public void setHolderaddress(String holderaddress) {
            this.holderaddress = holderaddress;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
}