package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import android.os.Parcel;
import android.os.Parcelable;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MasterTermData;

/**
 * Created by Rajeev Ranjan on 09/04/2018.
 */

public class TermCompareQuoteResponse extends APIResponse implements Parcelable {
    /**
     * MasterData : {"LifeTermRequestID":51,"Response":[{"CustomerReferenceID":177519,"QuoteId":1,"PolicyTermYear":20,"PPT":0,"InsurerName":"ICICI Prudential Life Insurance Pvt. Ltd.","InsurerLogoName":"icicipru-life-logo.png","InsurerId":39,"ProductPlanId":"219","ProductPlanName":"iProtectSmart","Insurer_Plan_Code":null,"NetPremium":6377,"ServiceTax":974,"SumAssured":10000000,"IsOnlinePayment":true,"KeyFeatures":"Coverage against death|terminal illness and disability|Option to choose Accidental Death Benefit and Accelerated Critical Illness Benefit|Special premium rates for non - tobacco users|Option to receive the benefit amount as lump sum or monthly income or combination of both|Flexibility to pay premiums once|for a limited period or throughout the policy term|Tax benefits: on premiums paid and benefits received as per the prevailing tax laws","BroucherDownloadLink":"","IsInsurerGateways":true,"PremiumTakenForNoOfMonth":0,"PaymentModeFactor":0,"ApplicationNumber":"","PdfUrl":"","ProposerPageUrl":"http://qa.policyboss.com/TermInsuranceIndia/Intermediatepagelife?CustomerReferenceNumber=177519&SelectedQuoteId=1&SupportsAgentID=1682&CallingSource=POSPAPP&IsCustomer=0","QuoteStatus":"Success"},{"CustomerReferenceID":177519,"QuoteId":2,"PolicyTermYear":20,"PPT":0,"InsurerName":"Edelweiss Tokio Life Insurance Company Ltd.","InsurerLogoName":"edelweissTokio.png","InsurerId":43,"ProductPlanId":"293","ProductPlanName":"Total Secure+","Insurer_Plan_Code":null,"NetPremium":5546,"ServiceTax":846,"SumAssured":10000000,"IsOnlinePayment":true,"KeyFeatures":"A low cost comprehensive protection plan with option for Critical Illness cover|Flexibility to choose from three death benefit payment modes : lumpsum,monthly income or combination of both|Receive monthly income as an equal monthly sum or an increasing amount depending on your requirements|Accelerated Critical Illness Benefit* up to Rs. 1 crore,Get life cover for as long as up to age 80","BroucherDownloadLink":"","IsInsurerGateways":true,"PremiumTakenForNoOfMonth":0,"PaymentModeFactor":0,"ApplicationNumber":"","PdfUrl":"http://sis.edelweisstokio.in/SISOnline/SISPDF/7957dd5d-d338-4af1-96f8-9cc820ae8284.pdf","ProposerPageUrl":"http://qa.policyboss.com/TermInsuranceIndia/Intermediatepagelife?CustomerReferenceNumber=177519&SelectedQuoteId=2&SupportsAgentID=1682&CallingSource=POSPAPP&IsCustomer=0","QuoteStatus":"Success"}]}
     */

    private MasterTermData MasterData;

    public MasterTermData getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterTermData MasterData) {
        this.MasterData = MasterData;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.MasterData, flags);
    }

    public TermCompareQuoteResponse() {
    }

    protected TermCompareQuoteResponse(Parcel in) {
        this.MasterData = in.readParcelable(MasterTermData.class.getClassLoader());
    }

    public static final Parcelable.Creator<TermCompareQuoteResponse> CREATOR = new Parcelable.Creator<TermCompareQuoteResponse>() {
        @Override
        public TermCompareQuoteResponse createFromParcel(Parcel source) {
            return new TermCompareQuoteResponse(source);
        }

        @Override
        public TermCompareQuoteResponse[] newArray(int size) {
            return new TermCompareQuoteResponse[size];
        }
    };
}
