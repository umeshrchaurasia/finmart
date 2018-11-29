package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 07-02-2017.
 */
public class PersonalQuoteEntity implements Parcelable {
    /**
     * Bank_Id : 20
     * Bank_Code : HDFC
     * Bank_Name : HDFC BANK LTD
     * Product_Id : 9.00
     * roi : 11.69
     * loan_eligible : 905464.40
     * processingfee : 10000
     * emi : 20142
     * LoanTenure : 5
     * LoanRequired : 500000
     * Bank_Logo : http://erp.rupeeboss.com/Banklogo/hdfc.png
     * guarantor_required : No
     * Women_roi : null
     * Lock_In_Period : 12.00
     * Balance_Transfer : Yes
     * Top_up : Yes
     * eApproval : Yes
     * Pre_Closer_Fixed : 4.00
     * Part_Pmt_Fixed : null
     * Profession : 1
     */

    private int Bank_Id;
    private String Bank_Code;
    private String Bank_Name;
    private String Product_Id;
    private String roi;
    private double loan_eligible;
    private double processingfee;
    private double emi;
    private int LoanTenure;
    private String LoanRequired;
    private String Bank_Logo;
    private String guarantor_required;
    private String Women_roi;


    private String Lock_In_Period;
    private String Balance_Transfer;
    private String Top_up;
    private String eApproval;
    private String Pre_Closer_Fixed;
    private String Part_Pmt_Fixed;
    private int Profession;
    private String roi_type;
    private boolean iskeyvisible;
    private int Is_Online;
    private String bank_web_url;


    protected PersonalQuoteEntity(Parcel in) {
        Bank_Id = in.readInt();
        Bank_Code = in.readString();
        Bank_Name = in.readString();
        Product_Id = in.readString();
        roi = in.readString();
        loan_eligible = in.readDouble();
        processingfee = in.readDouble();
        emi = in.readDouble();
        LoanTenure = in.readInt();
        LoanRequired = in.readString();
        Bank_Logo = in.readString();
        guarantor_required = in.readString();
        Women_roi = in.readString();
        Lock_In_Period = in.readString();
        Balance_Transfer = in.readString();
        Top_up = in.readString();
        eApproval = in.readString();
        Pre_Closer_Fixed = in.readString();
        Part_Pmt_Fixed = in.readString();
        Profession = in.readInt();
        roi_type = in.readString();
        iskeyvisible = in.readByte() != 0;
        Is_Online = in.readInt();
        bank_web_url = in.readString();
    }

    public static final Creator<PersonalQuoteEntity> CREATOR = new Creator<PersonalQuoteEntity>() {
        @Override
        public PersonalQuoteEntity createFromParcel(Parcel in) {
            return new PersonalQuoteEntity(in);
        }

        @Override
        public PersonalQuoteEntity[] newArray(int size) {
            return new PersonalQuoteEntity[size];
        }
    };

    public int getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(int bank_Id) {
        Bank_Id = bank_Id;
    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String bank_Code) {
        Bank_Code = bank_Code;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String bank_Name) {
        Bank_Name = bank_Name;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String product_Id) {
        Product_Id = product_Id;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public double getLoan_eligible() {
        return loan_eligible;
    }

    public void setLoan_eligible(double loan_eligible) {
        this.loan_eligible = loan_eligible;
    }

    public double getProcessingfee() {
        return processingfee;
    }

    public void setProcessingfee(double processingfee) {
        this.processingfee = processingfee;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public int getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(int loanTenure) {
        LoanTenure = loanTenure;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String loanRequired) {
        LoanRequired = loanRequired;
    }

    public String getBank_Logo() {
        return Bank_Logo;
    }

    public void setBank_Logo(String bank_Logo) {
        Bank_Logo = bank_Logo;
    }

    public String getGuarantor_required() {
        return guarantor_required;
    }

    public void setGuarantor_required(String guarantor_required) {
        this.guarantor_required = guarantor_required;
    }

    public String getWomen_roi() {
        return Women_roi;
    }

    public void setWomen_roi(String women_roi) {
        Women_roi = women_roi;
    }

    public String getLock_In_Period() {
        return Lock_In_Period;
    }

    public void setLock_In_Period(String lock_In_Period) {
        Lock_In_Period = lock_In_Period;
    }

    public String getBalance_Transfer() {
        return Balance_Transfer;
    }

    public void setBalance_Transfer(String balance_Transfer) {
        Balance_Transfer = balance_Transfer;
    }

    public String getTop_up() {
        return Top_up;
    }

    public void setTop_up(String top_up) {
        Top_up = top_up;
    }

    public String geteApproval() {
        return eApproval;
    }

    public void seteApproval(String eApproval) {
        this.eApproval = eApproval;
    }

    public String getPre_Closer_Fixed() {
        return Pre_Closer_Fixed;
    }

    public void setPre_Closer_Fixed(String pre_Closer_Fixed) {
        Pre_Closer_Fixed = pre_Closer_Fixed;
    }

    public String getPart_Pmt_Fixed() {
        return Part_Pmt_Fixed;
    }

    public void setPart_Pmt_Fixed(String part_Pmt_Fixed) {
        Part_Pmt_Fixed = part_Pmt_Fixed;
    }

    public int getProfession() {
        return Profession;
    }

    public void setProfession(int profession) {
        Profession = profession;
    }

    public String getRoi_type() {
        return roi_type;
    }

    public void setRoi_type(String roi_type) {
        this.roi_type = roi_type;
    }

    public boolean isIskeyvisible() {
        return iskeyvisible;
    }

    public void setIskeyvisible(boolean iskeyvisible) {
        this.iskeyvisible = iskeyvisible;
    }

    public int getIs_Online() {
        return Is_Online;
    }

    public void setIs_Online(int is_Online) {
        Is_Online = is_Online;
    }

    public String getBank_web_url() {
        return bank_web_url;
    }

    public void setBank_web_url(String bank_web_url) {
        this.bank_web_url = bank_web_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Bank_Id);
        dest.writeString(Bank_Code);
        dest.writeString(Bank_Name);
        dest.writeString(Product_Id);
        dest.writeString(roi);
        dest.writeDouble(loan_eligible);
        dest.writeDouble(processingfee);
        dest.writeDouble(emi);
        dest.writeInt(LoanTenure);
        dest.writeString(LoanRequired);
        dest.writeString(Bank_Logo);
        dest.writeString(guarantor_required);
        dest.writeString(Women_roi);
        dest.writeString(Lock_In_Period);
        dest.writeString(Balance_Transfer);
        dest.writeString(Top_up);
        dest.writeString(eApproval);
        dest.writeString(Pre_Closer_Fixed);
        dest.writeString(Part_Pmt_Fixed);
        dest.writeInt(Profession);
        dest.writeString(roi_type);
        dest.writeByte((byte) (iskeyvisible ? 1 : 0));
        dest.writeInt(Is_Online);
        dest.writeString(bank_web_url);
    }
}
