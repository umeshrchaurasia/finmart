package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model.PersonalQuoteEntity;

/**
 * Created by IN-RB on 07-02-2017.
 */

public class GetPersonalLoanResponse extends APIResponse implements Parcelable {


    /**
     * data : [{"Bank_Id":20,"Bank_Code":"HDFC","Bank_Name":"HDFC BANK LTD","Product_Id":"9.00","roi":"11.69","loan_eligible":"905464.40","processingfee":10000,"emi":20142,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/hdfc.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"4.00","Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":5,"Bank_Code":"Bajaj Finance","Bank_Name":"BAJAJ FINANCE LIMITED","Product_Id":"9.00","roi":"11.99","loan_eligible":"899305.20","processingfee":15000,"emi":20005,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/bajaj.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"4.00","Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":33,"Bank_Code":"Kotak Mahindra","Bank_Name":"KOTAK MAHINDRA BANK","Product_Id":"9.00","roi":"11.49","loan_eligible":"1137005.00","processingfee":10000,"emi":24721,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/kotak.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":null,"Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":18,"Bank_Code":"FULLETRON","Bank_Name":"FULLERTON INDIA CREDIT COMPANY LIMITED","Product_Id":"9.00","roi":"18.00","loan_eligible":"1181407.80","processingfee":20000,"emi":30000,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/fullerton-india.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"4.00","Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":25,"Bank_Code":"ICICI","Bank_Name":"ICICI BANK LIMITED","Product_Id":"9.00","roi":"11.59","loan_eligible":"907530.80","processingfee":12500,"emi":20188,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/icici.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"5.00","Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":4,"Bank_Code":"AXIS","Bank_Name":"AXIS BANK LTD","Product_Id":"9.00","roi":"15.00","loan_eligible":"840692.00","processingfee":10000,"emi":20000,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/axis.jpg","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"0.00","Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":50,"Bank_Code":"TATA CAPITAL","Bank_Name":"TATA CAPITAL FINANCIAL SERVICES LIMITED","Product_Id":"9.00","roi":"12.50","loan_eligible":"888970.40","processingfee":10000,"emi":20227,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/tata-capital.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"0.00","Part_Pmt_Fixed":null,"Profession":1},{"Bank_Id":6,"Bank_Code":"Capital First","Bank_Name":"CAPITAL FIRST LIMITED","Product_Id":"9.00","roi":"13.00","loan_eligible":"879002.00","processingfee":15000,"emi":20000,"LoanTenure":5,"LoanRequired":"500000","Bank_Logo":"http://erp.rupeeboss.com/Banklogo/capital-first.png","guarantor_required":"No","Women_roi":null,"Lock_In_Period":"12.00","Balance_Transfer":"Yes","Top_up":"Yes","eApproval":"Yes","Pre_Closer_Fixed":"5.00","Part_Pmt_Fixed":null,"Profession":1}]
     * quote_id : 2153
     * url : http://beta.erp.rupeeboss.com/personalloan/personalloan.aspx
     */

    private int quote_id;
    private String url;
    private  String bank_web_url;
    private List<PersonalQuoteEntity> data;
    private  String Lead_Id;

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBank_web_url() {
        return bank_web_url;
    }

    public void setBank_web_url(String bank_web_url) {
        this.bank_web_url = bank_web_url;
    }

    public List<PersonalQuoteEntity> getData() {
        return data;
    }

    public void setData(List<PersonalQuoteEntity> data) {
        this.data = data;
    }


    public String getLead_Id() {
        return Lead_Id;
    }

    public void setLead_Id(String lead_Id) {
        Lead_Id = lead_Id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.quote_id);
        dest.writeString(this.url);
        dest.writeString(this.bank_web_url);
        dest.writeList(this.data);
        dest.writeString(this.Lead_Id);
    }

    public GetPersonalLoanResponse() {
    }

    protected GetPersonalLoanResponse(Parcel in) {
        this.quote_id = in.readInt();
        this.url = in.readString();
        this.bank_web_url = in.readString();
        this.data = new ArrayList<PersonalQuoteEntity>();
        in.readList(this.data, PersonalQuoteEntity.class.getClassLoader());
        this.Lead_Id = in.readString();
    }

    public static final Parcelable.Creator<GetPersonalLoanResponse> CREATOR = new Parcelable.Creator<GetPersonalLoanResponse>() {
        @Override
        public GetPersonalLoanResponse createFromParcel(Parcel source) {
            return new GetPersonalLoanResponse(source);
        }

        @Override
        public GetPersonalLoanResponse[] newArray(int size) {
            return new GetPersonalLoanResponse[size];
        }
    };
}
