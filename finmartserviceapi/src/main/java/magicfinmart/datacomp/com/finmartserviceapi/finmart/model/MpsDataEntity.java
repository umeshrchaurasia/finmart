package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class MpsDataEntity {

    /**
     * PaymentURL : https://goo.gl/Por9hs
     * Amount : 1150
     * ProdID : 512
     * MRP : 500
     * Discount : 0
     * ServTaxAmt : 90
     * VATAmt : 0
     * TotalAmt : 1150
     * BalanceAmt : 0
     */

    private String PaymentURL;
    private int Amount;
    private int ProdID;
    private int MRP;
    private int Discount;
    private int ServTaxAmt;
    private int VATAmt;
    private int TotalAmt;
    private int BalanceAmt;

    public String getPaymentURL() {
        return PaymentURL;
    }

    public void setPaymentURL(String PaymentURL) {
        this.PaymentURL = PaymentURL;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getProdID() {
        return ProdID;
    }

    public void setProdID(int ProdID) {
        this.ProdID = ProdID;
    }

    public int getMRP() {
        return MRP;
    }

    public void setMRP(int MRP) {
        this.MRP = MRP;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public int getServTaxAmt() {
        return ServTaxAmt;
    }

    public void setServTaxAmt(int ServTaxAmt) {
        this.ServTaxAmt = ServTaxAmt;
    }

    public int getVATAmt() {
        return VATAmt;
    }

    public void setVATAmt(int VATAmt) {
        this.VATAmt = VATAmt;
    }

    public int getTotalAmt() {
        return TotalAmt;
    }

    public void setTotalAmt(int TotalAmt) {
        this.TotalAmt = TotalAmt;
    }

    public int getBalanceAmt() {
        return BalanceAmt;
    }

    public void setBalanceAmt(int BalanceAmt) {
        this.BalanceAmt = BalanceAmt;
    }
}