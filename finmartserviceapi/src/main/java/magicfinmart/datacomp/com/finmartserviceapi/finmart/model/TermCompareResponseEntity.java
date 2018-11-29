package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TermCompareResponseEntity implements Parcelable {
    /**
     * CustomerReferenceID : 177519
     * QuoteId : 1
     * PolicyTermYear : 20
     * PPT : 0
     * InsurerName : ICICI Prudential Life Insurance Pvt. Ltd.
     * InsurerLogoName : icicipru-life-logo.png
     * InsurerId : 39
     * ProductPlanId : 219
     * ProductPlanName : iProtectSmart
     * Insurer_Plan_Code : null
     * NetPremium : 6377
     * ServiceTax : 974
     * SumAssured : 10000000
     * IsOnlinePayment : true
     * KeyFeatures : Coverage against death|terminal illness and disability|Option to choose Accidental Death Benefit and Accelerated Critical Illness Benefit|Special premium rates for non - tobacco users|Option to receive the benefit amount as lump sum or monthly income or combination of both|Flexibility to pay premiums once|for a limited period or throughout the policy term|Tax benefits: on premiums paid and benefits received as per the prevailing tax laws
     * BroucherDownloadLink :
     * IsInsurerGateways : true
     * PremiumTakenForNoOfMonth : 0
     * PaymentModeFactor : 0
     * ApplicationNumber :
     * PdfUrl :
     * ProposerPageUrl : http://qa.policyboss.com/TermInsuranceIndia/Intermediatepagelife?CustomerReferenceNumber=177519&SelectedQuoteId=1&SupportsAgentID=1682&CallingSource=POSPAPP&IsCustomer=0
     * QuoteStatus : Success
     */

    private int CustomerReferenceID;
    private int QuoteId;
    private int PolicyTermYear;
    private int PPT;
    private String InsurerName;
    private String InsurerLogoName;
    private int InsurerId;
    private String ProductPlanId;
    private String ProductPlanName;
    private String Insurer_Plan_Code;
    private int NetPremium;
    private int ServiceTax;
    private int SumAssured;
    private boolean IsOnlinePayment;
    private String KeyFeatures;
    private String BroucherDownloadLink;
    private boolean IsInsurerGateways;
    private int PremiumTakenForNoOfMonth;
    private int PaymentModeFactor;
    private String ApplicationNumber;
    private String PdfUrl;
    private String ProposerPageUrl;
    private String QuoteStatus;

    public int getCustomerReferenceID() {
        return CustomerReferenceID;
    }

    public void setCustomerReferenceID(int CustomerReferenceID) {
        this.CustomerReferenceID = CustomerReferenceID;
    }

    public int getQuoteId() {
        return QuoteId;
    }

    public void setQuoteId(int QuoteId) {
        this.QuoteId = QuoteId;
    }

    public int getPolicyTermYear() {
        return PolicyTermYear;
    }

    public void setPolicyTermYear(int PolicyTermYear) {
        this.PolicyTermYear = PolicyTermYear;
    }

    public int getPPT() {
        return PPT;
    }

    public void setPPT(int PPT) {
        this.PPT = PPT;
    }

    public String getInsurerName() {
        return InsurerName;
    }

    public void setInsurerName(String InsurerName) {
        this.InsurerName = InsurerName;
    }

    public String getInsurerLogoName() {
        return InsurerLogoName;
    }

    public void setInsurerLogoName(String InsurerLogoName) {
        this.InsurerLogoName = InsurerLogoName;
    }

    public int getInsurerId() {
        return InsurerId;
    }

    public void setInsurerId(int InsurerId) {
        this.InsurerId = InsurerId;
    }

    public String getProductPlanId() {
        return ProductPlanId;
    }

    public void setProductPlanId(String ProductPlanId) {
        this.ProductPlanId = ProductPlanId;
    }

    public String getProductPlanName() {
        return ProductPlanName;
    }

    public void setProductPlanName(String ProductPlanName) {
        this.ProductPlanName = ProductPlanName;
    }

    public String getInsurer_Plan_Code() {
        return Insurer_Plan_Code;
    }

    public void setInsurer_Plan_Code(String Insurer_Plan_Code) {
        this.Insurer_Plan_Code = Insurer_Plan_Code;
    }

    public int getNetPremium() {
        return NetPremium;
    }

    public void setNetPremium(int NetPremium) {
        this.NetPremium = NetPremium;
    }

    public int getServiceTax() {
        return ServiceTax;
    }

    public void setServiceTax(int ServiceTax) {
        this.ServiceTax = ServiceTax;
    }

    public int getSumAssured() {
        return SumAssured;
    }

    public void setSumAssured(int SumAssured) {
        this.SumAssured = SumAssured;
    }

    public boolean isIsOnlinePayment() {
        return IsOnlinePayment;
    }

    public void setIsOnlinePayment(boolean IsOnlinePayment) {
        this.IsOnlinePayment = IsOnlinePayment;
    }

    public String getKeyFeatures() {
        return KeyFeatures;
    }

    public void setKeyFeatures(String KeyFeatures) {
        this.KeyFeatures = KeyFeatures;
    }

    public String getBroucherDownloadLink() {
        return BroucherDownloadLink;
    }

    public void setBroucherDownloadLink(String BroucherDownloadLink) {
        this.BroucherDownloadLink = BroucherDownloadLink;
    }

    public boolean isIsInsurerGateways() {
        return IsInsurerGateways;
    }

    public void setIsInsurerGateways(boolean IsInsurerGateways) {
        this.IsInsurerGateways = IsInsurerGateways;
    }

    public int getPremiumTakenForNoOfMonth() {
        return PremiumTakenForNoOfMonth;
    }

    public void setPremiumTakenForNoOfMonth(int PremiumTakenForNoOfMonth) {
        this.PremiumTakenForNoOfMonth = PremiumTakenForNoOfMonth;
    }

    public int getPaymentModeFactor() {
        return PaymentModeFactor;
    }

    public void setPaymentModeFactor(int PaymentModeFactor) {
        this.PaymentModeFactor = PaymentModeFactor;
    }

    public String getApplicationNumber() {
        return ApplicationNumber;
    }

    public void setApplicationNumber(String ApplicationNumber) {
        this.ApplicationNumber = ApplicationNumber;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String PdfUrl) {
        this.PdfUrl = PdfUrl;
    }

    public String getProposerPageUrl() {
        return ProposerPageUrl;
    }

    public void setProposerPageUrl(String ProposerPageUrl) {
        this.ProposerPageUrl = ProposerPageUrl;
    }

    public String getQuoteStatus() {
        return QuoteStatus;
    }

    public void setQuoteStatus(String QuoteStatus) {
        this.QuoteStatus = QuoteStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.CustomerReferenceID);
        dest.writeInt(this.QuoteId);
        dest.writeInt(this.PolicyTermYear);
        dest.writeInt(this.PPT);
        dest.writeString(this.InsurerName);
        dest.writeString(this.InsurerLogoName);
        dest.writeInt(this.InsurerId);
        dest.writeString(this.ProductPlanId);
        dest.writeString(this.ProductPlanName);
        dest.writeString(this.Insurer_Plan_Code);
        dest.writeInt(this.NetPremium);
        dest.writeInt(this.ServiceTax);
        dest.writeInt(this.SumAssured);
        dest.writeByte(this.IsOnlinePayment ? (byte) 1 : (byte) 0);
        dest.writeString(this.KeyFeatures);
        dest.writeString(this.BroucherDownloadLink);
        dest.writeByte(this.IsInsurerGateways ? (byte) 1 : (byte) 0);
        dest.writeInt(this.PremiumTakenForNoOfMonth);
        dest.writeInt(this.PaymentModeFactor);
        dest.writeString(this.ApplicationNumber);
        dest.writeString(this.PdfUrl);
        dest.writeString(this.ProposerPageUrl);
        dest.writeString(this.QuoteStatus);
    }

    public TermCompareResponseEntity() {
    }

    protected TermCompareResponseEntity(Parcel in) {
        this.CustomerReferenceID = in.readInt();
        this.QuoteId = in.readInt();
        this.PolicyTermYear = in.readInt();
        this.PPT = in.readInt();
        this.InsurerName = in.readString();
        this.InsurerLogoName = in.readString();
        this.InsurerId = in.readInt();
        this.ProductPlanId = in.readString();
        this.ProductPlanName = in.readString();
        this.Insurer_Plan_Code = in.readString();
        this.NetPremium = in.readInt();
        this.ServiceTax = in.readInt();
        this.SumAssured = in.readInt();
        this.IsOnlinePayment = in.readByte() != 0;
        this.KeyFeatures = in.readString();
        this.BroucherDownloadLink = in.readString();
        this.IsInsurerGateways = in.readByte() != 0;
        this.PremiumTakenForNoOfMonth = in.readInt();
        this.PaymentModeFactor = in.readInt();
        this.ApplicationNumber = in.readString();
        this.PdfUrl = in.readString();
        this.ProposerPageUrl = in.readString();
        this.QuoteStatus = in.readString();
    }

    public static final Parcelable.Creator<TermCompareResponseEntity> CREATOR = new Parcelable.Creator<TermCompareResponseEntity>() {
        @Override
        public TermCompareResponseEntity createFromParcel(Parcel source) {
            return new TermCompareResponseEntity(source);
        }

        @Override
        public TermCompareResponseEntity[] newArray(int size) {
            return new TermCompareResponseEntity[size];
        }
    };
}