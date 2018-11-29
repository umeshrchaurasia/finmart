package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MasterTermData implements Parcelable {
    /**
     * LifeTermRequestID : 51
     * Response : [{"CustomerReferenceID":177519,"QuoteId":1,"PolicyTermYear":20,"PPT":0,"InsurerName":"ICICI Prudential Life Insurance Pvt. Ltd.","InsurerLogoName":"icicipru-life-logo.png","InsurerId":39,"ProductPlanId":"219","ProductPlanName":"iProtectSmart","Insurer_Plan_Code":null,"NetPremium":6377,"ServiceTax":974,"SumAssured":10000000,"IsOnlinePayment":true,"KeyFeatures":"Coverage against death|terminal illness and disability|Option to choose Accidental Death Benefit and Accelerated Critical Illness Benefit|Special premium rates for non - tobacco users|Option to receive the benefit amount as lump sum or monthly income or combination of both|Flexibility to pay premiums once|for a limited period or throughout the policy term|Tax benefits: on premiums paid and benefits received as per the prevailing tax laws","BroucherDownloadLink":"","IsInsurerGateways":true,"PremiumTakenForNoOfMonth":0,"PaymentModeFactor":0,"ApplicationNumber":"","PdfUrl":"","ProposerPageUrl":"http://qa.policyboss.com/TermInsuranceIndia/Intermediatepagelife?CustomerReferenceNumber=177519&SelectedQuoteId=1&SupportsAgentID=1682&CallingSource=POSPAPP&IsCustomer=0","QuoteStatus":"Success"},{"CustomerReferenceID":177519,"QuoteId":2,"PolicyTermYear":20,"PPT":0,"InsurerName":"Edelweiss Tokio Life Insurance Company Ltd.","InsurerLogoName":"edelweissTokio.png","InsurerId":43,"ProductPlanId":"293","ProductPlanName":"Total Secure+","Insurer_Plan_Code":null,"NetPremium":5546,"ServiceTax":846,"SumAssured":10000000,"IsOnlinePayment":true,"KeyFeatures":"A low cost comprehensive protection plan with option for Critical Illness cover|Flexibility to choose from three death benefit payment modes : lumpsum,monthly income or combination of both|Receive monthly income as an equal monthly sum or an increasing amount depending on your requirements|Accelerated Critical Illness Benefit* up to Rs. 1 crore,Get life cover for as long as up to age 80","BroucherDownloadLink":"","IsInsurerGateways":true,"PremiumTakenForNoOfMonth":0,"PaymentModeFactor":0,"ApplicationNumber":"","PdfUrl":"http://sis.edelweisstokio.in/SISOnline/SISPDF/7957dd5d-d338-4af1-96f8-9cc820ae8284.pdf","ProposerPageUrl":"http://qa.policyboss.com/TermInsuranceIndia/Intermediatepagelife?CustomerReferenceNumber=177519&SelectedQuoteId=2&SupportsAgentID=1682&CallingSource=POSPAPP&IsCustomer=0","QuoteStatus":"Success"}]
     */

    private int LifeTermRequestID;
    private List<TermCompareResponseEntity> Response;

    public int getLifeTermRequestID() {
        return LifeTermRequestID;
    }

    public void setLifeTermRequestID(int LifeTermRequestID) {
        this.LifeTermRequestID = LifeTermRequestID;
    }

    public List<TermCompareResponseEntity> getResponse() {
        return Response;
    }

    public void setResponse(List<TermCompareResponseEntity> Response) {
        this.Response = Response;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.LifeTermRequestID);
        dest.writeTypedList(this.Response);
    }

    public MasterTermData() {
    }

    protected MasterTermData(Parcel in) {
        this.LifeTermRequestID = in.readInt();
        this.Response = in.createTypedArrayList(TermCompareResponseEntity.CREATOR);
    }

    public static final Parcelable.Creator<MasterTermData> CREATOR = new Parcelable.Creator<MasterTermData>() {
        @Override
        public MasterTermData createFromParcel(Parcel source) {
            return new MasterTermData(source);
        }

        @Override
        public MasterTermData[] newArray(int size) {
            return new MasterTermData[size];
        }
    };
}