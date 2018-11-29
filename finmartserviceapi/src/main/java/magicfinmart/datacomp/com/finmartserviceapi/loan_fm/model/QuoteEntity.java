package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class QuoteEntity implements Parcelable {
    /**
     * Bank_Id : 42
     * Bank_Name : PNB HOUSING FINANCE LTD
     * Bank_Code : PNB Housing
     * Product_Id : 12.00
     * roi : 9.45
     * netincome : 125000
     * emi : 29414
     * LoanTenure : 15
     * processingfee : 10500
     * LoanRequired : 2900000
     * Bank_Logo : http://erp.rupeeboss.com/Banklogo/pnb-housing.png
     * guarantor_required : No
     * Women_roi : 9.0000
     * Lock_In_Period : 12.00
     * Balance_Transfer : Yes
     * Top_up : Yes
     * eApproval : Yes
     * Pre_Closer_Fixed : 2.00
     * Part_Pmt_Fixed : 2.00
     * Profession : 2
     * fixed_roi : [{"roi":"9.50","years_from":"3.00","years_to":"3.00","emi":92896},{"roi":"9.75","years_from":"5.00","years_to":"5.00","emi":61261},{"roi":"10.00","years_from":"10.00","years_to":"10.00","emi":38324}]
     */

    private int Bank_Id;
    private String Bank_Name;
    private String Bank_Code;
    private String Product_Id;
    private String roi;
    private String netincome;

    private int LoanTenure;
    private double processingfee;
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


    private double loan_eligible;

    private double emi;


    private boolean iskeyvisible;
    private List<FixedRateofInterestEntity> fixed_roi;
    /**
     * loan_eligible : 1181407.80
     * emi : 12696.71
     * foir : 60.00
     * Women_roi : null
     * Part_Pmt_Fixed : null
     * roi_type : Fixed
     */

    private String roi_type;

    /**
     * loan_eligible : 160258.70
     */



    public double getLoan_eligible() {
        return loan_eligible;
    }

    public double getEmi() {
        return emi;
    }

    public String geteApproval() {
        return eApproval;
    }

    public void seteApproval(String eApproval) {
        this.eApproval = eApproval;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public void setLoan_eligible(double loan_eligible) {
        this.loan_eligible = loan_eligible;
    }

    /**
     * loan_eligible : 3287046.7
     * emi : 287000.03
     */


    public int getBank_Id() {
        return Bank_Id;
    }

    public void setBank_Id(int Bank_Id) {
        this.Bank_Id = Bank_Id;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String Bank_Name) {
        this.Bank_Name = Bank_Name;
    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String Bank_Code) {
        this.Bank_Code = Bank_Code;
    }

    public String getProduct_Id() {
        return Product_Id;
    }

    public void setProduct_Id(String Product_Id) {
        this.Product_Id = Product_Id;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getNetincome() {
        return netincome;
    }

    public void setNetincome(String netincome) {
        this.netincome = netincome;
    }


    public int getLoanTenure() {
        return LoanTenure;
    }

    public void setLoanTenure(int LoanTenure) {
        this.LoanTenure = LoanTenure;
    }

    public double getProcessingfee() {
        return processingfee;
    }

    public void setProcessingfee(double processingfee) {
        this.processingfee = processingfee;
    }

    public String getLoanRequired() {
        return LoanRequired;
    }

    public void setLoanRequired(String LoanRequired) {
        this.LoanRequired = LoanRequired;
    }

    public String getBank_Logo() {
        return Bank_Logo;
    }

    public void setBank_Logo(String Bank_Logo) {
        this.Bank_Logo = Bank_Logo;
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

    public void setWomen_roi(String Women_roi) {
        this.Women_roi = Women_roi;
    }

    public String getLock_In_Period() {
        return Lock_In_Period;
    }

    public void setLock_In_Period(String Lock_In_Period) {
        this.Lock_In_Period = Lock_In_Period;
    }

    public String getBalance_Transfer() {
        return Balance_Transfer;
    }

    public void setBalance_Transfer(String Balance_Transfer) {
        this.Balance_Transfer = Balance_Transfer;
    }

    public String getTop_up() {
        return Top_up;
    }

    public void setTop_up(String Top_up) {
        this.Top_up = Top_up;
    }

    public String getEApproval() {
        return eApproval;
    }

    public void setEApproval(String eApproval) {
        this.eApproval = eApproval;
    }

    public String getPre_Closer_Fixed() {
        return Pre_Closer_Fixed;
    }

    public void setPre_Closer_Fixed(String Pre_Closer_Fixed) {
        this.Pre_Closer_Fixed = Pre_Closer_Fixed;
    }

    public String getPart_Pmt_Fixed() {
        return Part_Pmt_Fixed;
    }

    public void setPart_Pmt_Fixed(String Part_Pmt_Fixed) {
        this.Part_Pmt_Fixed = Part_Pmt_Fixed;
    }

    public int getProfession() {
        return Profession;
    }

    public void setProfession(int Profession) {
        this.Profession = Profession;
    }

    public List<FixedRateofInterestEntity> getFixed_roi() {
        return fixed_roi;
    }

    public void setFixed_roi(List<FixedRateofInterestEntity> fixed_roi) {
        this.fixed_roi = fixed_roi;
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



    public QuoteEntity() {
        iskeyvisible=false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Bank_Id);
        dest.writeString(this.Bank_Name);
        dest.writeString(this.Bank_Code);
        dest.writeString(this.Product_Id);
        dest.writeString(this.roi);
        dest.writeString(this.netincome);
        dest.writeInt(this.LoanTenure);
        dest.writeDouble(this.processingfee);
        dest.writeString(this.LoanRequired);
        dest.writeString(this.Bank_Logo);
        dest.writeString(this.guarantor_required);
        dest.writeString(this.Women_roi);
        dest.writeString(this.Lock_In_Period);
        dest.writeString(this.Balance_Transfer);
        dest.writeString(this.Top_up);
        dest.writeString(this.eApproval);
        dest.writeString(this.Pre_Closer_Fixed);
        dest.writeString(this.Part_Pmt_Fixed);
        dest.writeInt(this.Profession);
        dest.writeDouble(this.loan_eligible);
        dest.writeDouble(this.emi);
        dest.writeByte(this.iskeyvisible ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.fixed_roi);
        dest.writeString(this.roi_type);
    }

    protected QuoteEntity(Parcel in) {
        this.Bank_Id = in.readInt();
        this.Bank_Name = in.readString();
        this.Bank_Code = in.readString();
        this.Product_Id = in.readString();
        this.roi = in.readString();
        this.netincome = in.readString();
        this.LoanTenure = in.readInt();
        this.processingfee = in.readDouble();
        this.LoanRequired = in.readString();
        this.Bank_Logo = in.readString();
        this.guarantor_required = in.readString();
        this.Women_roi = in.readString();
        this.Lock_In_Period = in.readString();
        this.Balance_Transfer = in.readString();
        this.Top_up = in.readString();
        this.eApproval = in.readString();
        this.Pre_Closer_Fixed = in.readString();
        this.Part_Pmt_Fixed = in.readString();
        this.Profession = in.readInt();
        this.loan_eligible = in.readDouble();
        this.emi = in.readDouble();
        this.iskeyvisible = in.readByte() != 0;
        this.fixed_roi = in.createTypedArrayList(FixedRateofInterestEntity.CREATOR);
        this.roi_type = in.readString();
    }

    public static final Parcelable.Creator<QuoteEntity> CREATOR = new Parcelable.Creator<QuoteEntity>() {
        @Override
        public QuoteEntity createFromParcel(Parcel source) {
            return new QuoteEntity(source);
        }

        @Override
        public QuoteEntity[] newArray(int size) {
            return new QuoteEntity[size];
        }
    };
}
