package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 18-01-2018.
 */

public class CustomerEntity implements Parcelable {
    /**
     * status : Success
     * ID : 213
     * PropertyID : 6
     * PropertyCost : 6000000
     * LoanTenure : 20
     * LoanRequired : 4000000
     * City : mumbai
     * ApplicantNme : user
     * Email : null
     * Contact : null
     * ApplicantGender : M
     * ApplicantSource : 1
     * ApplicantIncome : 100000
     * ApplicantObligations : 0
     * ApplicantDOB : 1985-12-12
     * CoApplicantYes : N
     * CoApplicantGender :
     * CoApplicantSource :
     * CoApplicantIncome :
     * CoApplicantObligations : 0
     * CoApplicantDOB :
     * Turnover : 0
     * ProfitAfterTax : 0
     * Depreciation : 0
     * DirectorRemuneration : 0
     * CoApplicantTurnover : 0
     * CoApplicantProfitAfterTax : 0
     * CoApplicantDepreciation : 0
     * CoApplicantDirectorRemuneration : 0
     * BrokerId : null
     * ProductId : 7
     * bank_id : null
     * roi_type : null
     * loan_eligible : null
     * processing_fee : null
     * created_at : 2016-12-27 07:38:59
     * updated_at : 2017-01-02 09:47:14
     */

    private String status;
    private int ID;
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
    private String BrokerId;
    private int ProductId;
    private String bank_id;
    private String roi_type;
    private String loan_eligible;
    private String processing_fee;
    private String created_at;
    private String updated_at;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String PropertyID) {
        this.PropertyID = PropertyID;
    }

    public String getPropertyCost() {
        return PropertyCost;
    }

    public void setPropertyCost(String PropertyCost) {
        this.PropertyCost = PropertyCost;
    }

    public String getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(String LoanTenure) {
        this.LoanTenure = LoanTenure;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String LoanRequired) {
        this.LoanRequired = LoanRequired;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getApplicantNme() {
        return ApplicantNme;
    }

    public void setApplicantNme(String ApplicantNme) {
        this.ApplicantNme = ApplicantNme;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getApplicantGender() {
        return ApplicantGender;
    }

    public void setApplicantGender(String ApplicantGender) {
        this.ApplicantGender = ApplicantGender;
    }

    public String getApplicantSource() {
        return ApplicantSource;
    }

    public void setApplicantSource(String ApplicantSource) {
        this.ApplicantSource = ApplicantSource;
    }

    public String getApplicantIncome() {
        return ApplicantIncome;
    }

    public void setApplicantIncome(String ApplicantIncome) {
        this.ApplicantIncome = ApplicantIncome;
    }

    public String getApplicantObligations() {
        return ApplicantObligations;
    }

    public void setApplicantObligations(String ApplicantObligations) {
        this.ApplicantObligations = ApplicantObligations;
    }

    public String getApplicantDOB() {
        return ApplicantDOB;
    }

    public void setApplicantDOB(String ApplicantDOB) {
        this.ApplicantDOB = ApplicantDOB;
    }

    public String getCoApplicantYes() {
        return CoApplicantYes;
    }

    public void setCoApplicantYes(String CoApplicantYes) {
        this.CoApplicantYes = CoApplicantYes;
    }

    public String getCoApplicantGender() {
        return CoApplicantGender;
    }

    public void setCoApplicantGender(String CoApplicantGender) {
        this.CoApplicantGender = CoApplicantGender;
    }

    public String getCoApplicantSource() {
        return CoApplicantSource;
    }

    public void setCoApplicantSource(String CoApplicantSource) {
        this.CoApplicantSource = CoApplicantSource;
    }

    public String getCoApplicantIncome() {
        return CoApplicantIncome;
    }

    public void setCoApplicantIncome(String CoApplicantIncome) {
        this.CoApplicantIncome = CoApplicantIncome;
    }

    public String getCoApplicantObligations() {
        return CoApplicantObligations;
    }

    public void setCoApplicantObligations(String CoApplicantObligations) {
        this.CoApplicantObligations = CoApplicantObligations;
    }

    public String getCoApplicantDOB() {
        return CoApplicantDOB;
    }

    public void setCoApplicantDOB(String CoApplicantDOB) {
        this.CoApplicantDOB = CoApplicantDOB;
    }

    public String getTurnover() {
        return Turnover;
    }

    public void setTurnover(String Turnover) {
        this.Turnover = Turnover;
    }

    public String getProfitAfterTax() {
        return ProfitAfterTax;
    }

    public void setProfitAfterTax(String ProfitAfterTax) {
        this.ProfitAfterTax = ProfitAfterTax;
    }

    public String getDepreciation() {
        return Depreciation;
    }

    public void setDepreciation(String Depreciation) {
        this.Depreciation = Depreciation;
    }

    public String getDirectorRemuneration() {
        return DirectorRemuneration;
    }

    public void setDirectorRemuneration(String DirectorRemuneration) {
        this.DirectorRemuneration = DirectorRemuneration;
    }

    public String getCoApplicantTurnover() {
        return CoApplicantTurnover;
    }

    public void setCoApplicantTurnover(String CoApplicantTurnover) {
        this.CoApplicantTurnover = CoApplicantTurnover;
    }

    public String getCoApplicantProfitAfterTax() {
        return CoApplicantProfitAfterTax;
    }

    public void setCoApplicantProfitAfterTax(String CoApplicantProfitAfterTax) {
        this.CoApplicantProfitAfterTax = CoApplicantProfitAfterTax;
    }

    public String getCoApplicantDepreciation() {
        return CoApplicantDepreciation;
    }

    public void setCoApplicantDepreciation(String CoApplicantDepreciation) {
        this.CoApplicantDepreciation = CoApplicantDepreciation;
    }

    public String getCoApplicantDirectorRemuneration() {
        return CoApplicantDirectorRemuneration;
    }

    public void setCoApplicantDirectorRemuneration(String CoApplicantDirectorRemuneration) {
        this.CoApplicantDirectorRemuneration = CoApplicantDirectorRemuneration;
    }

    public String getBrokerId() {
        return BrokerId;
    }

    public void setBrokerId(String BrokerId) {
        this.BrokerId = BrokerId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeInt(this.ID);
        dest.writeString(this.PropertyID);
        dest.writeString(this.PropertyCost);
        dest.writeString(this.LoanTenure);
        dest.writeString(this.LoanRequired);
        dest.writeString(this.City);
        dest.writeString(this.ApplicantNme);
        dest.writeString(this.Email);
        dest.writeString(this.Contact);
        dest.writeString(this.ApplicantGender);
        dest.writeString(this.ApplicantSource);
        dest.writeString(this.ApplicantIncome);
        dest.writeString(this.ApplicantObligations);
        dest.writeString(this.ApplicantDOB);
        dest.writeString(this.CoApplicantYes);
        dest.writeString(this.CoApplicantGender);
        dest.writeString(this.CoApplicantSource);
        dest.writeString(this.CoApplicantIncome);
        dest.writeString(this.CoApplicantObligations);
        dest.writeString(this.CoApplicantDOB);
        dest.writeString(this.Turnover);
        dest.writeString(this.ProfitAfterTax);
        dest.writeString(this.Depreciation);
        dest.writeString(this.DirectorRemuneration);
        dest.writeString(this.CoApplicantTurnover);
        dest.writeString(this.CoApplicantProfitAfterTax);
        dest.writeString(this.CoApplicantDepreciation);
        dest.writeString(this.CoApplicantDirectorRemuneration);
        dest.writeString(this.BrokerId);
        dest.writeInt(this.ProductId);
        dest.writeString(this.bank_id);
        dest.writeString(this.roi_type);
        dest.writeString(this.loan_eligible);
        dest.writeString(this.processing_fee);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
    }

    public CustomerEntity() {
    }

    protected CustomerEntity(Parcel in) {
        this.status = in.readString();
        this.ID = in.readInt();
        this.PropertyID = in.readString();
        this.PropertyCost = in.readString();
        this.LoanTenure = in.readString();
        this.LoanRequired = in.readString();
        this.City = in.readString();
        this.ApplicantNme = in.readString();
        this.Email = in.readString();
        this.Contact = in.readString();
        this.ApplicantGender = in.readString();
        this.ApplicantSource = in.readString();
        this.ApplicantIncome = in.readString();
        this.ApplicantObligations = in.readString();
        this.ApplicantDOB = in.readString();
        this.CoApplicantYes = in.readString();
        this.CoApplicantGender = in.readString();
        this.CoApplicantSource = in.readString();
        this.CoApplicantIncome = in.readString();
        this.CoApplicantObligations = in.readString();
        this.CoApplicantDOB = in.readString();
        this.Turnover = in.readString();
        this.ProfitAfterTax = in.readString();
        this.Depreciation = in.readString();
        this.DirectorRemuneration = in.readString();
        this.CoApplicantTurnover = in.readString();
        this.CoApplicantProfitAfterTax = in.readString();
        this.CoApplicantDepreciation = in.readString();
        this.CoApplicantDirectorRemuneration = in.readString();
        this.BrokerId = in.readString();
        this.ProductId = in.readInt();
        this.bank_id = in.readString();
        this.roi_type = in.readString();
        this.loan_eligible = in.readString();
        this.processing_fee = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
    }

    public static final Parcelable.Creator<CustomerEntity> CREATOR = new Parcelable.Creator<CustomerEntity>() {
        @Override
        public CustomerEntity createFromParcel(Parcel source) {
            return new CustomerEntity(source);
        }

        @Override
        public CustomerEntity[] newArray(int size) {
            return new CustomerEntity[size];
        }
    };

}
