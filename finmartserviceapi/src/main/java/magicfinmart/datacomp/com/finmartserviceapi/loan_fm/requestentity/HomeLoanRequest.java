package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rahul on 12-01-2018.
 */

public class HomeLoanRequest implements Parcelable {


    /**
     * loan_requestID : 1
     * ID : null
     * PropertyID : 1
     * PropertyCost : 15800000
     * LoanTenure : 24
     * LoanRequired : 12640000
     * City : Mumbai
     * ApplicantNme : Umesh test
     * Email : null
     * Contact : null
     * ApplicantGender : M
     * ApplicantSource : 1
     * ApplicantIncome : 580000
     * ApplicantObligations :
     * ApplicantDOB : 1989-02-05T00:00:00.000Z
     * CoApplicantYes : N
     * CoApplicantGender :
     * CoApplicantSource :
     * CoApplicantIncome :
     * CoApplicantObligations :
     * CoApplicantDOB : 0000-00-00
     * Turnover :
     * ProfitAfterTax :
     * Depreciation :
     * DirectorRemuneration :
     * CoApplicantTurnover :
     * CoApplicantProfitAfterTax :
     * CoApplicantDepreciation :
     * CoApplicantDirectorRemuneration :
     * empcode :
     * BrokerId : 0
     * ProductId : 12
     * bank_id : null
     * roi_type : null
     * loan_eligible : null
     * processing_fee : null
     * api_source : Finmart
     * created_at : null
     * updated_at : null

     * LoaniD : 0
     * Type : HML
     * row_created_date : 2018-02-06T11:12:14.000Z
     * row_updateddate : null
     * quote_application_type : Quote
     * conversiondate : null
     * quote_id : 17007
     * TranType : 5
     * "RBStatus": null,
     * "RBStatusDate": null,
     * "ApplNumb": null,
     * "ApplDate": null,
     * <p>
     * "StatusPercent": null
     */

    private int loan_requestID;

    private String PropertyID;
    private String PropertyCost;
    private String LoanTenure;
    private String LoanRequired;
    private String City;
    private String ApplicantNme;
    private String Email;
    private String Contact;
    private String ApplicantGender;
    private String ApplicantSource;
    private String ApplicantIncome;
    private String ApplicantObligations;
    private String ApplicantDOB;
    private String CoApplicantYes;
    private String CoApplicantGender;
    private String CoApplicantSource;
    private String CoApplicantIncome;
    private String CoApplicantObligations;
    private String CoApplicantDOB;
    private String Turnover;
    private String ProfitAfterTax;
    private String Depreciation;
    private String DirectorRemuneration;
    private String CoApplicantTurnover;
    private String CoApplicantProfitAfterTax;
    private String CoApplicantDepreciation;
    private String CoApplicantDirectorRemuneration;
    private String empcode;
    private String BrokerId;
    private String ProductId;
    private String bank_id;
    private String roi_type;
    private String loan_eligible;
    private String processing_fee;
    private String api_source;

    private int LoaniD;
    private String Type;
    private String row_created_date;
    private String row_updateddate;
    private String quote_application_type;
    private String conversiondate;
    private int quote_id;
    private int TranType;
    private String RBStatus;
    private String RBStatusDate;
    private String ApplNumb;
    private String ApplDate;
    private String StatusPercent;
    private String CoApplicantName;
    private String CoApplicantRelation;
    private  String bank_image;
    private  String progress_image;

    public HomeLoanRequest() {
        this.loan_requestID = 0;
        this.PropertyID = "";
        this.PropertyCost = "";
        this.LoanTenure = "";
        this.LoanRequired = "";
        this.City = "";
        this.ApplicantNme = "";
        this.Email = "";
        this.Contact = "";
        this.ApplicantGender = "";
        this.ApplicantSource = "";
        this.ApplicantIncome = "";
        this.ApplicantObligations = "";
        this.ApplicantDOB = "";
        this.CoApplicantYes = "";
        this.CoApplicantGender = "";
        this.CoApplicantSource = "";
        this.CoApplicantIncome = "";
        this.CoApplicantObligations = "";
        this.CoApplicantDOB = "";
        this.Turnover = "";
        this.ProfitAfterTax = "";
        this.Depreciation = "";
        this.DirectorRemuneration = "";
        this.CoApplicantTurnover = "";
        this.CoApplicantProfitAfterTax = "";
        this.CoApplicantDepreciation = "";
        this.CoApplicantDirectorRemuneration = "";
        this.empcode = "";
        this.BrokerId = "";
        this.ProductId = "";
        this.bank_id = "";
        this.roi_type = "";
        this.loan_eligible = "";
        this.processing_fee = "";
        this.api_source = "";

        this.LoaniD = 0;
        this.Type = "";
        this.row_created_date = "";
        this.row_updateddate = "";
        this.quote_application_type = "";
        this.conversiondate = "";
        this.quote_id = 0;
        this.TranType = 0;
        this.RBStatus = "";
        this.RBStatusDate = "";
        this.ApplNumb = "";
        this.ApplDate = "";
        this.StatusPercent = "";
        this.CoApplicantName="";
        this.CoApplicantRelation="";
        this.bank_image="";
        this.progress_image="";
    }


    protected HomeLoanRequest(Parcel in) {
        loan_requestID = in.readInt();
        PropertyID = in.readString();
        PropertyCost = in.readString();
        LoanTenure = in.readString();
        LoanRequired = in.readString();
        City = in.readString();
        ApplicantNme = in.readString();
        Email = in.readString();
        Contact = in.readString();
        ApplicantGender = in.readString();
        ApplicantSource = in.readString();
        ApplicantIncome = in.readString();
        ApplicantObligations = in.readString();
        ApplicantDOB = in.readString();
        CoApplicantYes = in.readString();
        CoApplicantGender = in.readString();
        CoApplicantSource = in.readString();
        CoApplicantIncome = in.readString();
        CoApplicantObligations = in.readString();
        CoApplicantDOB = in.readString();
        Turnover = in.readString();
        ProfitAfterTax = in.readString();
        Depreciation = in.readString();
        DirectorRemuneration = in.readString();
        CoApplicantTurnover = in.readString();
        CoApplicantProfitAfterTax = in.readString();
        CoApplicantDepreciation = in.readString();
        CoApplicantDirectorRemuneration = in.readString();
        empcode = in.readString();
        BrokerId = in.readString();
        ProductId = in.readString();
        bank_id = in.readString();
        roi_type = in.readString();
        loan_eligible = in.readString();
        processing_fee = in.readString();
        api_source = in.readString();

        LoaniD = in.readInt();
        Type = in.readString();
        row_created_date = in.readString();
        row_updateddate = in.readString();
        quote_application_type = in.readString();
        conversiondate = in.readString();
        quote_id = in.readInt();
        TranType = in.readInt();
        RBStatus = in.readString();
        RBStatusDate = in.readString();
        ApplNumb = in.readString();
        ApplDate = in.readString();
        StatusPercent = in.readString();
        CoApplicantName = in.readString();
        CoApplicantRelation = in.readString();
        bank_image = in.readString();
        progress_image=  in.readString();
    }

    public static final Creator<HomeLoanRequest> CREATOR = new Creator<HomeLoanRequest>() {
        @Override
        public HomeLoanRequest createFromParcel(Parcel in) {
            return new HomeLoanRequest(in);
        }

        @Override
        public HomeLoanRequest[] newArray(int size) {
            return new HomeLoanRequest[size];
        }
    };

    public int getLoan_requestID() {
        return loan_requestID;
    }

    public void setLoan_requestID(int loan_requestID) {
        this.loan_requestID = loan_requestID;
    }

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String propertyID) {
        PropertyID = propertyID;
    }

    public String getPropertyCost() {
        return PropertyCost;
    }

    public void setPropertyCost(String propertyCost) {
        PropertyCost = propertyCost;
    }

    public String getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(String loanTenure) {
        LoanTenure = loanTenure;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String loanRequired) {
        LoanRequired = loanRequired;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getApplicantNme() {
        return ApplicantNme;
    }

    public void setApplicantNme(String applicantNme) {
        ApplicantNme = applicantNme;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getApplicantGender() {
        return ApplicantGender;
    }

    public void setApplicantGender(String applicantGender) {
        ApplicantGender = applicantGender;
    }

    public String getApplicantSource() {
        return ApplicantSource;
    }

    public void setApplicantSource(String applicantSource) {
        ApplicantSource = applicantSource;
    }

    public String getApplicantIncome() {
        return ApplicantIncome;
    }

    public void setApplicantIncome(String applicantIncome) {
        ApplicantIncome = applicantIncome;
    }

    public String getApplicantObligations() {
        return ApplicantObligations;
    }

    public void setApplicantObligations(String applicantObligations) {
        ApplicantObligations = applicantObligations;
    }

    public String getApplicantDOB() {
        return ApplicantDOB;
    }

    public void setApplicantDOB(String applicantDOB) {
        ApplicantDOB = applicantDOB;
    }

    public String getCoApplicantYes() {
        return CoApplicantYes;
    }

    public void setCoApplicantYes(String coApplicantYes) {
        CoApplicantYes = coApplicantYes;
    }

    public String getCoApplicantGender() {
        return CoApplicantGender;
    }

    public void setCoApplicantGender(String coApplicantGender) {
        CoApplicantGender = coApplicantGender;
    }

    public String getCoApplicantSource() {
        return CoApplicantSource;
    }

    public void setCoApplicantSource(String coApplicantSource) {
        CoApplicantSource = coApplicantSource;
    }

    public String getCoApplicantIncome() {
        return CoApplicantIncome;
    }

    public void setCoApplicantIncome(String coApplicantIncome) {
        CoApplicantIncome = coApplicantIncome;
    }

    public String getCoApplicantObligations() {
        return CoApplicantObligations;
    }

    public void setCoApplicantObligations(String coApplicantObligations) {
        CoApplicantObligations = coApplicantObligations;
    }

    public String getCoApplicantDOB() {
        return CoApplicantDOB;
    }

    public void setCoApplicantDOB(String coApplicantDOB) {
        CoApplicantDOB = coApplicantDOB;
    }

    public String getTurnover() {
        return Turnover;
    }

    public void setTurnover(String turnover) {
        Turnover = turnover;
    }

    public String getProfitAfterTax() {
        return ProfitAfterTax;
    }

    public void setProfitAfterTax(String profitAfterTax) {
        ProfitAfterTax = profitAfterTax;
    }

    public String getDepreciation() {
        return Depreciation;
    }

    public void setDepreciation(String depreciation) {
        Depreciation = depreciation;
    }

    public String getDirectorRemuneration() {
        return DirectorRemuneration;
    }

    public void setDirectorRemuneration(String directorRemuneration) {
        DirectorRemuneration = directorRemuneration;
    }

    public String getCoApplicantTurnover() {
        return CoApplicantTurnover;
    }

    public void setCoApplicantTurnover(String coApplicantTurnover) {
        CoApplicantTurnover = coApplicantTurnover;
    }

    public String getCoApplicantProfitAfterTax() {
        return CoApplicantProfitAfterTax;
    }

    public void setCoApplicantProfitAfterTax(String coApplicantProfitAfterTax) {
        CoApplicantProfitAfterTax = coApplicantProfitAfterTax;
    }

    public String getCoApplicantDepreciation() {
        return CoApplicantDepreciation;
    }

    public void setCoApplicantDepreciation(String coApplicantDepreciation) {
        CoApplicantDepreciation = coApplicantDepreciation;
    }

    public String getCoApplicantDirectorRemuneration() {
        return CoApplicantDirectorRemuneration;
    }

    public void setCoApplicantDirectorRemuneration(String coApplicantDirectorRemuneration) {
        CoApplicantDirectorRemuneration = coApplicantDirectorRemuneration;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getBrokerId() {
        return BrokerId;
    }

    public void setBrokerId(String brokerId) {
        BrokerId = brokerId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getRoi_type() {
        return roi_type;
    }

    public void setRoi_type(String roi_type) {
        this.roi_type = roi_type;
    }

    public String getLoan_eligible() {
        return loan_eligible;
    }

    public void setLoan_eligible(String loan_eligible) {
        this.loan_eligible = loan_eligible;
    }

    public String getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(String processing_fee) {
        this.processing_fee = processing_fee;
    }

    public String getApi_source() {
        return api_source;
    }

    public void setApi_source(String api_source) {
        this.api_source = api_source;
    }


    public int getLoaniD() {
        return LoaniD;
    }

    public void setLoaniD(int loaniD) {
        LoaniD = loaniD;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRow_created_date() {
        return row_created_date;
    }

    public void setRow_created_date(String row_created_date) {
        this.row_created_date = row_created_date;
    }

    public String getRow_updateddate() {
        return row_updateddate;
    }

    public void setRow_updateddate(String row_updateddate) {
        this.row_updateddate = row_updateddate;
    }

    public String getQuote_application_type() {
        return quote_application_type;
    }

    public void setQuote_application_type(String quote_application_type) {
        this.quote_application_type = quote_application_type;
    }

    public String getConversiondate() {
        return conversiondate;
    }

    public void setConversiondate(String conversiondate) {
        this.conversiondate = conversiondate;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public int getTranType() {
        return TranType;
    }

    public void setTranType(int tranType) {
        TranType = tranType;
    }

    public String getRBStatus() {
        return RBStatus;
    }

    public void setRBStatus(String RBStatus) {
        this.RBStatus = RBStatus;
    }

    public String getRBStatusDate() {
        return RBStatusDate;
    }

    public void setRBStatusDate(String RBStatusDate) {
        this.RBStatusDate = RBStatusDate;
    }

    public String getApplNumb() {
        return ApplNumb;
    }

    public void setApplNumb(String applNumb) {
        ApplNumb = applNumb;
    }

    public String getApplDate() {
        return ApplDate;
    }

    public void setApplDate(String applDate) {
        ApplDate = applDate;
    }

    public String getStatusPercent() {
        return StatusPercent;
    }

    public void setStatusPercent(String statusPercent) {
        StatusPercent = statusPercent;
    }


    public String getCoApplicantName() {
        return CoApplicantName;
    }

    public void setCoApplicantName(String coApplicantName) {
        CoApplicantName = coApplicantName;
    }

    public String getCoApplicantRelationt() {
        return CoApplicantRelation;
    }

    public void setCoApplicantRelation(String coApplicantRelation) {
        CoApplicantRelation = coApplicantRelation;
    }

    public String getbank_image() {
        return bank_image;
    }

    public void setbank_image(String bank_image) {
        bank_image = bank_image;
    }

    public String getProgress_image() {
        return progress_image;
    }

    public void setProgress_image(String progress_image) {
        this.progress_image = progress_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(loan_requestID);
        dest.writeString(PropertyID);
        dest.writeString(PropertyCost);
        dest.writeString(LoanTenure);
        dest.writeString(LoanRequired);
        dest.writeString(City);
        dest.writeString(ApplicantNme);
        dest.writeString(Email);
        dest.writeString(Contact);
        dest.writeString(ApplicantGender);
        dest.writeString(ApplicantSource);
        dest.writeString(ApplicantIncome);
        dest.writeString(ApplicantObligations);
        dest.writeString(ApplicantDOB);
        dest.writeString(CoApplicantYes);
        dest.writeString(CoApplicantGender);
        dest.writeString(CoApplicantSource);
        dest.writeString(CoApplicantIncome);
        dest.writeString(CoApplicantObligations);
        dest.writeString(CoApplicantDOB);
        dest.writeString(Turnover);
        dest.writeString(ProfitAfterTax);
        dest.writeString(Depreciation);
        dest.writeString(DirectorRemuneration);
        dest.writeString(CoApplicantTurnover);
        dest.writeString(CoApplicantProfitAfterTax);
        dest.writeString(CoApplicantDepreciation);
        dest.writeString(CoApplicantDirectorRemuneration);
        dest.writeString(empcode);
        dest.writeString(BrokerId);
        dest.writeString(ProductId);
        dest.writeString(bank_id);
        dest.writeString(roi_type);
        dest.writeString(loan_eligible);
        dest.writeString(processing_fee);
        dest.writeString(api_source);

        dest.writeInt(LoaniD);
        dest.writeString(Type);
        dest.writeString(row_created_date);
        dest.writeString(row_updateddate);
        dest.writeString(quote_application_type);
        dest.writeString(conversiondate);
        dest.writeInt(quote_id);
        dest.writeInt(TranType);
        dest.writeString(RBStatus);
        dest.writeString(RBStatusDate);
        dest.writeString(ApplNumb);
        dest.writeString(ApplDate);
        dest.writeString(StatusPercent);
        dest.writeString(CoApplicantName);
        dest.writeString(CoApplicantRelation);
        dest.writeString(bank_image);
        dest.writeString(progress_image);
    }
}
