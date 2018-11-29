package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

/**
 * Created by Nilesh Birhade on 07-03-2018.
 */

public class QuickLeadRequestEntity {

    /**
     * brokerId : 163
     * Name : Test
     * EMail : joeljangam146@gmail.com
     * Mobile : 9789678678
     * ProductId : 9
     * Loan_amt : 500000
     * FBA_Id : 1234
     * Monthly_income : 50000
     * Remark : Okae
     * followupDate : 03-08-2018
     */

    private String brokerId;
    private String Name;
    private String EMail;
    private String Mobile;
    private String ProductId;
    private String Loan_amt;
    private String FBA_Id;
    private String Monthly_income;
    private String Remark;
    private String followupDate;

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public String getLoan_amt() {
        return Loan_amt;
    }

    public void setLoan_amt(String Loan_amt) {
        this.Loan_amt = Loan_amt;
    }

    public String getFBA_Id() {
        return FBA_Id;
    }

    public void setFBA_Id(String FBA_Id) {
        this.FBA_Id = FBA_Id;
    }

    public String getMonthly_income() {
        return Monthly_income;
    }

    public void setMonthly_income(String Monthly_income) {
        this.Monthly_income = Monthly_income;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }
}
