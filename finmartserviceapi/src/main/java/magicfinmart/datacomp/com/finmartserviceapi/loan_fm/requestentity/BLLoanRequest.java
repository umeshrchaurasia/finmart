package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.requestentity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by IN-RB on 27-01-2018.
 */

public class BLLoanRequest implements Parcelable {
    /**
     * BalanceTransferId : 36
     * ApplicantName : test
     * loanamount : 500000
     * loaninterest : 12.5
     * loanterm : 10
     * LoanType : BLPL
     * product_id : 9
     * fbaid : 35779
     * LoanID : 21312
     * quote_application_type : Q
     * conversiondate :
     * row_updateddate :
     * row_createddate : 2018-02-24T09:23:49.000Z
     * RBStatus :
     * RBStatusDate :
     * ApplNumb :
     * ApplDate :
     * email : test@test.com
     * contact : 1234567890
     * quote_id : 567
     * source : Demo APP
     * isActive : 1
     * StatusPercent :
     * bank_id : 33
     * bank_image : http://erp.rupeeboss.com/Banklogo/kotak.png
     * progress_image :
     */

    private int BalanceTransferId;
    private String applicantname;
    private long loanamount;
    private double loaninterest;
    private double loanterm;
    private String Type;
    private int product_id;
    private String fbaid;
    private int LoanID;
    private String quote_application_type;
    private String conversiondate;
    private String row_updateddate;
    private String row_createddate;
    private String RBStatus;
    private String RBStatusDate;
    private String ApplNumb;
    private String ApplDate;
    private String email;
    private String contact;
    private int quote_id;
    private String source;
    private int isActive;
    private String StatusPercent;
    private int bank_id;
    private String bank_image;
    private String progress_image;
    private int brokerid;


    public BLLoanRequest() {
        BalanceTransferId = 0;
        applicantname = "";
        this.loanamount = 0;
        this.loaninterest = 0;
        this.loanterm = 0;
        Type = "";
        this.product_id = 0;
        this.fbaid = "";
        LoanID = 0;
        this.quote_application_type = "";
        this.conversiondate = "";
        this.row_updateddate = "";
        this.row_createddate = "";
        this.RBStatus = "";
        this.RBStatusDate = "";
        ApplNumb = "";
        ApplDate = "";
        this.email = "";
        this.contact = "";
        this.quote_id = 0;
        this.source = "";
        this.isActive = 0;
        StatusPercent = "";
        this.bank_id = 0;
        this.bank_image = "";
        this.progress_image = "";
        this.brokerid=0;
    }

    public int getBalanceTransferId() {
        return BalanceTransferId;
    }

    public void setBalanceTransferId(int BalanceTransferId) {
        this.BalanceTransferId = BalanceTransferId;
    }

    public String getApplicantName() {
        return applicantname;
    }

    public void setApplicantName(String ApplicantName) {
        this.applicantname = ApplicantName;
    }

    public long getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(long loanamount) {
        this.loanamount = loanamount;
    }

    public double getLoaninterest() {
        return loaninterest;
    }

    public void setLoaninterest(double loaninterest) {
        this.loaninterest = loaninterest;
    }

    public double getLoanterm() {
        return loanterm;
    }

    public void setLoanterm(double loanterm) {
        this.loanterm = loanterm;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getFbaid() {
        return fbaid;
    }

    public void setFbaid(String fbaid) {
        this.fbaid = fbaid;
    }

    public int getLoanID() {
        return LoanID;
    }

    public void setLoanID(int LoanID) {
        this.LoanID = LoanID;
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

    public String getRow_updateddate() {
        return row_updateddate;
    }

    public void setRow_updateddate(String row_updateddate) {
        this.row_updateddate = row_updateddate;
    }

    public String getRow_createddate() {
        return row_createddate;
    }

    public void setRow_createddate(String row_createddate) {
        this.row_createddate = row_createddate;
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

    public void setApplNumb(String ApplNumb) {
        this.ApplNumb = ApplNumb;
    }

    public String getApplDate() {
        return ApplDate;
    }

    public void setApplDate(String ApplDate) {
        this.ApplDate = ApplDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getStatusPercent() {
        return StatusPercent;
    }

    public void setStatusPercent(String StatusPercent) {
        this.StatusPercent = StatusPercent;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_image() {
        return bank_image;
    }

    public void setBank_image(String bank_image) {
        this.bank_image = bank_image;
    }

    public String getProgress_image() {
        return progress_image;
    }

    public void setProgress_image(String progress_image) {
        this.progress_image = progress_image;
    }

    public int getbrokerid() {
        return brokerid;
    }

    public void setbrokerid(int brokerid) {
        this.brokerid = brokerid;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.BalanceTransferId);
        dest.writeString(this.applicantname);
        dest.writeLong(this.loanamount);
        dest.writeDouble(this.loaninterest);
        dest.writeDouble(this.loanterm);
        dest.writeString(this.Type);
        dest.writeInt(this.product_id);
        dest.writeString(this.fbaid);
        dest.writeInt(this.LoanID);
        dest.writeString(this.quote_application_type);
        dest.writeString(this.conversiondate);
        dest.writeString(this.row_updateddate);
        dest.writeString(this.row_createddate);
        dest.writeString(this.RBStatus);
        dest.writeString(this.RBStatusDate);
        dest.writeString(this.ApplNumb);
        dest.writeString(this.ApplDate);
        dest.writeString(this.email);
        dest.writeString(this.contact);
        dest.writeInt(this.quote_id);
        dest.writeString(this.source);
        dest.writeInt(this.isActive);
        dest.writeString(this.StatusPercent);
        dest.writeInt(this.bank_id);
        dest.writeString(this.bank_image);
        dest.writeString(this.progress_image);
        dest.writeInt(this.brokerid);
    }



    protected BLLoanRequest(Parcel in) {
        this.BalanceTransferId = in.readInt();
        this.applicantname = in.readString();
        this.loanamount = in.readLong();
        this.loaninterest = in.readDouble();
        this.loanterm = in.readDouble();
        this.Type = in.readString();
        this.product_id = in.readInt();
        this.fbaid = in.readString();
        this.LoanID = in.readInt();
        this.quote_application_type = in.readString();
        this.conversiondate = in.readString();
        this.row_updateddate = in.readString();
        this.row_createddate = in.readString();
        this.RBStatus = in.readString();
        this.RBStatusDate = in.readString();
        this.ApplNumb = in.readString();
        this.ApplDate = in.readString();
        this.email = in.readString();
        this.contact = in.readString();
        this.quote_id = in.readInt();
        this.source = in.readString();
        this.isActive = in.readInt();
        this.StatusPercent = in.readString();
        this.bank_id = in.readInt();
        this.bank_image = in.readString();
        this.progress_image = in.readString();
        this.brokerid = in.readInt();
    }

    public static final Parcelable.Creator<BLLoanRequest> CREATOR = new Parcelable.Creator<BLLoanRequest>() {
        @Override
        public BLLoanRequest createFromParcel(Parcel source) {
            return new BLLoanRequest(source);
        }

        @Override
        public BLLoanRequest[] newArray(int size) {
            return new BLLoanRequest[size];
        }
    };
}
