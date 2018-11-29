package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthPackDEntity;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public class HealthPackResponse extends APIResponse {
    /**
     * d : {"__type":"HealthProduct.MobileDAL.loginPackDetailsresponse","lstPackageDetails":[{"PackCode":"71","PackName":"Basic Profile ","MRP":913,"OfferPrice":620,"Fasting":"N","VisitType":"CV","cnt":"40","Gender":"B","Age":"0-100"},{"PackCode":"72","PackName":"Express Mini","MRP":1781,"OfferPrice":1210,"Fasting":"N","VisitType":"CV","cnt":"43","Gender":"B","Age":"0-100"},{"PackCode":"73","PackName":"Express Healthy","MRP":2088,"OfferPrice":1420,"Fasting":"N","VisitType":"CV","cnt":"44","Gender":"B","Age":"0-100"},{"PackCode":"75","PackName":"Express Plus","MRP":2294,"OfferPrice":1650,"Fasting":"N","VisitType":"CV","cnt":"47","Gender":"B","Age":"0-100"},{"PackCode":"76","PackName":"Essential","MRP":2988,"OfferPrice":2030,"Fasting":"N","VisitType":"CV","cnt":"50","Gender":"B","Age":"0-100"},{"PackCode":"78","PackName":"Package - Female below 30","MRP":3669,"OfferPrice":2640,"Fasting":"N","VisitType":"CV","cnt":"57","Gender":"F","Age":"0-30"},{"PackCode":"79","PackName":"Package - Male below 30","MRP":4494,"OfferPrice":3060,"Fasting":"N","VisitType":"CV","cnt":"60","Gender":"M","Age":"0-30"},{"PackCode":"82","PackName":"Package - Male 31 - 40","MRP":6994,"OfferPrice":4760,"Fasting":"N","VisitType":"CV","cnt":"59","Gender":"M","Age":"31-40"},{"PackCode":"85","PackName":"Package - Male Above 40","MRP":9706,"OfferPrice":6600,"Fasting":"N","VisitType":"CV","cnt":"62","Gender":"M","Age":"40-100"},{"PackCode":"190","PackName":"Package - Female 31-40","MRP":7244,"OfferPrice":4930,"Fasting":"Y","VisitType":"CV","cnt":"59","Gender":"F","Age":"31-40"},{"PackCode":"191","PackName":"Package - Female Above 40","MRP":9956,"OfferPrice":6770,"Fasting":"Y","VisitType":"CV","cnt":"62","Gender":"F","Age":"40-100"}],"status_code":200,"status":"Success","message":"Success"}
     */

    private HealthPackDEntity d;

    public HealthPackDEntity getD() {
        return d;
    }

    public void setD(HealthPackDEntity d) {
        this.d = d;
    }
}
