package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HealthQuoteEntity implements Parcelable {
    /**
     * CustomerReferenceID : 0
     * QuoteId : 0
     * PolicyTermYear : 1
     * PlanName : Supreme
     * InsurerName : Liberty Videocon
     * InsurerLogoName :
     * ProductName : Health Connect Floater
     * PlanID : 47
     * ZoneID : 0
     * OtherPlanID :
     * ProdID : 104
     * InsurerId : 26
     * ServiceTax : 0
     * SumInsured : 300000
     * HMBValue :
     * IsOnlinePayment : 0
     * KeyFeatures :
     * BroucherDownloadLink :
     * Discount : 0
     * Deductible_Amount : 0
     * NetPremium : 12166
     * GrossPremium : 0
     * DiscountPercent :
     * Premium :
     * Group_name :
     * QuoteStatus :
     * ProposerPageUrl :
     * pincode : 400095
     * FinalProductID : 104
     * LstbenfitsFive : [{"DisplayName":"","ProdBeneID":0,"BeneID":1,"BeneDesc":"Room Rent Limit","Benefit":"No Limit"}]
     */

    private String servicetaxincl;
    private int CustomerReferenceID;
    private int QuoteId;
    private int PolicyTermYear;
    private String PlanName;
    private String InsurerName;
    private String InsurerLogoName;
    private String ProductName;
    private int PlanID;
    private int ZoneID;
    private String OtherPlanID;
    private int ProdID;
    private int InsurerId;
    private int ServiceTax;
    private double SumInsured;
    private String HMBValue;
    private int IsOnlinePayment;
    private String KeyFeatures;
    private String BroucherDownloadLink;
    private int Discount;
    private int Deductible_Amount;
    private double NetPremium;
    private double GrossPremium;

    private String DiscountPercent;
    private String Premium;
    private String Group_name;
    private String QuoteStatus;
    private String ProposerPageUrl;
    private String pincode;
    private int FinalProductID;
    private List<BenefitsEntity> LstbenfitsFive;


    //for compare check
    private boolean isCompare;
    private boolean isMore;
    private double displayPremium;


    public double getDisplayPremium() {
        return displayPremium;
    }

    public void setDisplayPremium(double displayPremium) {
        this.displayPremium = displayPremium;
    }

    public String getServicetaxincl() {
        return servicetaxincl;
    }

    public void setServicetaxincl(String servicetaxincl) {
        this.servicetaxincl = servicetaxincl;
    }

    public boolean getIsMore() {
        return isMore;
    }

    public void setIsMore(boolean isMore) {
        this.isMore = isMore;
    }

    public boolean isCompare() {
        return isCompare;
    }

    public void setCompare(boolean compare) {
        isCompare = compare;
    }

    //to show no of childs count
    private int totalChilds;

    public int getTotalChilds() {
        return totalChilds;
    }

    public void setTotalChilds(int totalChilds) {
        this.totalChilds = totalChilds;
    }

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

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String PlanName) {
        this.PlanName = PlanName;
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

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getPlanID() {
        return PlanID;
    }

    public void setPlanID(int PlanID) {
        this.PlanID = PlanID;
    }

    public int getZoneID() {
        return ZoneID;
    }

    public void setZoneID(int ZoneID) {
        this.ZoneID = ZoneID;
    }

    public String getOtherPlanID() {
        return OtherPlanID;
    }

    public void setOtherPlanID(String OtherPlanID) {
        this.OtherPlanID = OtherPlanID;
    }

    public int getProdID() {
        return ProdID;
    }

    public void setProdID(int ProdID) {
        this.ProdID = ProdID;
    }

    public int getInsurerId() {
        return InsurerId;
    }

    public void setInsurerId(int InsurerId) {
        this.InsurerId = InsurerId;
    }

    public int getServiceTax() {
        return ServiceTax;
    }

    public void setServiceTax(int ServiceTax) {
        this.ServiceTax = ServiceTax;
    }

    public double getSumInsured() {
        return SumInsured;
    }

    public void setSumInsured(double SumInsured) {
        this.SumInsured = SumInsured;
    }

    public String getHMBValue() {
        return HMBValue;
    }

    public void setHMBValue(String HMBValue) {
        this.HMBValue = HMBValue;
    }

    public int getIsOnlinePayment() {
        return IsOnlinePayment;
    }

    public void setIsOnlinePayment(int IsOnlinePayment) {
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

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }

    public int getDeductible_Amount() {
        return Deductible_Amount;
    }

    public void setDeductible_Amount(int Deductible_Amount) {
        this.Deductible_Amount = Deductible_Amount;
    }

    public double getNetPremium() {
        return NetPremium;
    }

    public void setNetPremium(double NetPremium) {
        this.NetPremium = NetPremium;
    }

    public double getGrossPremium() {
        return GrossPremium;
    }

    public void setGrossPremium(double GrossPremium) {
        this.GrossPremium = GrossPremium;
    }

    public String getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(String DiscountPercent) {
        this.DiscountPercent = DiscountPercent;
    }

    public String getPremium() {
        return Premium;
    }

    public void setPremium(String Premium) {
        this.Premium = Premium;
    }

    public String getGroup_name() {
        return Group_name;
    }

    public void setGroup_name(String Group_name) {
        this.Group_name = Group_name;
    }

    public String getQuoteStatus() {
        return QuoteStatus;
    }

    public void setQuoteStatus(String QuoteStatus) {
        this.QuoteStatus = QuoteStatus;
    }

    public String getProposerPageUrl() {
        return ProposerPageUrl;
    }

    public void setProposerPageUrl(String ProposerPageUrl) {
        this.ProposerPageUrl = ProposerPageUrl;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getFinalProductID() {
        return FinalProductID;
    }

    public void setFinalProductID(int FinalProductID) {
        this.FinalProductID = FinalProductID;
    }

    public List<BenefitsEntity> getLstbenfitsFive() {
        return LstbenfitsFive;
    }

    public void setLstbenfitsFive(List<BenefitsEntity> LstbenfitsFive) {
        this.LstbenfitsFive = LstbenfitsFive;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.servicetaxincl);
        dest.writeInt(this.CustomerReferenceID);
        dest.writeInt(this.QuoteId);
        dest.writeInt(this.PolicyTermYear);
        dest.writeString(this.PlanName);
        dest.writeString(this.InsurerName);
        dest.writeString(this.InsurerLogoName);
        dest.writeString(this.ProductName);
        dest.writeInt(this.PlanID);
        dest.writeInt(this.ZoneID);
        dest.writeString(this.OtherPlanID);
        dest.writeInt(this.ProdID);
        dest.writeInt(this.InsurerId);
        dest.writeInt(this.ServiceTax);
        dest.writeDouble(this.SumInsured);
        dest.writeString(this.HMBValue);
        dest.writeInt(this.IsOnlinePayment);
        dest.writeString(this.KeyFeatures);
        dest.writeString(this.BroucherDownloadLink);
        dest.writeInt(this.Discount);
        dest.writeInt(this.Deductible_Amount);
        dest.writeDouble(this.NetPremium);
        dest.writeDouble(this.GrossPremium);
        dest.writeString(this.DiscountPercent);
        dest.writeString(this.Premium);
        dest.writeString(this.Group_name);
        dest.writeString(this.QuoteStatus);
        dest.writeString(this.ProposerPageUrl);
        dest.writeString(this.pincode);
        dest.writeInt(this.FinalProductID);
        dest.writeTypedList(this.LstbenfitsFive);
        dest.writeByte(this.isCompare ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isMore ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.displayPremium);
        dest.writeInt(this.totalChilds);
    }

    public HealthQuoteEntity() {
    }

    protected HealthQuoteEntity(Parcel in) {
        this.servicetaxincl = in.readString();
        this.CustomerReferenceID = in.readInt();
        this.QuoteId = in.readInt();
        this.PolicyTermYear = in.readInt();
        this.PlanName = in.readString();
        this.InsurerName = in.readString();
        this.InsurerLogoName = in.readString();
        this.ProductName = in.readString();
        this.PlanID = in.readInt();
        this.ZoneID = in.readInt();
        this.OtherPlanID = in.readString();
        this.ProdID = in.readInt();
        this.InsurerId = in.readInt();
        this.ServiceTax = in.readInt();
        this.SumInsured = in.readDouble();
        this.HMBValue = in.readString();
        this.IsOnlinePayment = in.readInt();
        this.KeyFeatures = in.readString();
        this.BroucherDownloadLink = in.readString();
        this.Discount = in.readInt();
        this.Deductible_Amount = in.readInt();
        this.NetPremium = in.readDouble();
        this.GrossPremium = in.readDouble();
        this.DiscountPercent = in.readString();
        this.Premium = in.readString();
        this.Group_name = in.readString();
        this.QuoteStatus = in.readString();
        this.ProposerPageUrl = in.readString();
        this.pincode = in.readString();
        this.FinalProductID = in.readInt();
        this.LstbenfitsFive = in.createTypedArrayList(BenefitsEntity.CREATOR);
        this.isCompare = in.readByte() != 0;
        this.isMore = in.readByte() != 0;
        this.displayPremium = in.readDouble();
        this.totalChilds = in.readInt();
    }

    public static final Parcelable.Creator<HealthQuoteEntity> CREATOR = new Parcelable.Creator<HealthQuoteEntity>() {
        @Override
        public HealthQuoteEntity createFromParcel(Parcel source) {
            return new HealthQuoteEntity(source);
        }

        @Override
        public HealthQuoteEntity[] newArray(int size) {
            return new HealthQuoteEntity[size];
        }
    };
}