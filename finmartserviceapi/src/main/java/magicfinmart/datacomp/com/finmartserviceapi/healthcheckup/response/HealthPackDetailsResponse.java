package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthPackDetailsDBean;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public class HealthPackDetailsResponse extends APIResponse {
    /**
     * d : {"__type":"HealthProduct.MobileDAL.packparamresponse","lstPackParameter":[{"Name":"CBC","ParamDetails":["Basophils","Eosinophils","Hematocrit (Hct)","Hemoglobin (Hbg)","Lymphocytes","Mean corpuscular hemoglobin (MCH)","Mean corpuscular hemoglobin concentration","Mean corpuscular volume (MCV)","Mean Platelet Volume (MPV)","Monocytes","Neutrophils","Platelet count","Platelet Distribution Width","RBC","Red Blood Cell Count","Red cell distribution width (RDW)","WBC","White blood cell count (WBC or leukocyte count)"]},{"Name":"Fasting Blood Sugar","ParamDetails":[]},{"Name":"ESR","ParamDetails":[]},{"Name":"Physician Consultation","ParamDetails":[]},{"Name":"Urine Analysis","ParamDetails":["Bacteria","Bilirubin","Blood","Casts","Clarity/turbidity","Colour","Crystals","Glucose","Ketones","Leukocyte esterase","Nitrites","pH","Protein","RBCs","Specific gravity","Squamous epithelial cells","Urobilirubin","WBCs","Yeast"]}],"status_code":200,"status":"Success","message":"Success"}
     */

    private HealthPackDetailsDBean d;

    public HealthPackDetailsDBean getD() {
        return d;
    }

    public void setD(HealthPackDetailsDBean d) {
        this.d = d;
    }


}
