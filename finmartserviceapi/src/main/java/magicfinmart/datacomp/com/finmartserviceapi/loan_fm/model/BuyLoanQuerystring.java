package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 08-03-2018.
 */

public class BuyLoanQuerystring implements Parcelable  {

    private int BankId;
    private int Quote_id;
    private String Prop_Loan_Eligible;
    private String Prop_Processing_Fee;

    private String Prop_type;
    private String MobileNo;
    private String City;
    private String type;
    private String pan;
    private  String bank_web_url;
    private  String bank_name;

    private  String Lead_id;

    public BuyLoanQuerystring()
    {

    }


    protected BuyLoanQuerystring(Parcel in) {
        BankId = in.readInt();
        Quote_id = in.readInt();
        Prop_Loan_Eligible = in.readString();
        Prop_Processing_Fee = in.readString();
        Prop_type = in.readString();
        MobileNo = in.readString();
        City = in.readString();
        type = in.readString();
        pan = in.readString();
        bank_web_url = in.readString();
        bank_name= in.readString();
        Lead_id = in.readString();
    }

    public static final Creator<BuyLoanQuerystring> CREATOR = new Creator<BuyLoanQuerystring>() {
        @Override
        public BuyLoanQuerystring createFromParcel(Parcel in) {
            return new BuyLoanQuerystring(in);
        }

        @Override
        public BuyLoanQuerystring[] newArray(int size) {
            return new BuyLoanQuerystring[size];
        }
    };

    public int getBankId() {
        return BankId;
    }

    public void setBankId(int bankId) {
        BankId = bankId;
    }

    public int getQuote_id() {
        return Quote_id;
    }

    public void setQuote_id(int quote_id) {
        Quote_id = quote_id;
    }

    public String getProp_Loan_Eligible() {
        return Prop_Loan_Eligible;
    }

    public void setProp_Loan_Eligible(String prop_Loan_Eligible) {
        Prop_Loan_Eligible = prop_Loan_Eligible;
    }

    public String getProp_Processing_Fee() {
        return Prop_Processing_Fee;
    }

    public void setProp_Processing_Fee(String prop_Processing_Fee) {
        Prop_Processing_Fee = prop_Processing_Fee;
    }

    public String getProp_type() {
        return Prop_type;
    }

    public void setProp_type(String prop_type) {
        Prop_type = prop_type;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getBank_web_url() {
        return bank_web_url;
    }
    public void setBank_web_url(String bank_web_url) {
        this.bank_web_url = bank_web_url;
    }


    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getLead_id() {
        return Lead_id;
    }

    public void setLead_id(String lead_id) {
        Lead_id = lead_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(BankId);
        dest.writeInt(Quote_id);
        dest.writeString(Prop_Loan_Eligible);
        dest.writeString(Prop_Processing_Fee);
        dest.writeString(Prop_type);
        dest.writeString(MobileNo);
        dest.writeString(City);
        dest.writeString(type);
        dest.writeString(pan);
        dest.writeString(bank_web_url);
        dest.writeString(bank_name);
        dest.writeString(Lead_id);
    }
}
