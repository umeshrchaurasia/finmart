package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HealthPackDetailsDBean extends RealmObject {
    /**
     * __type : HealthProduct.MobileDAL.packparamresponse
     * lstPackParameter : [{"Name":"CBC","ParamDetails":["Basophils","Eosinophils","Hematocrit (Hct)","Hemoglobin (Hbg)","Lymphocytes","Mean corpuscular hemoglobin (MCH)","Mean corpuscular hemoglobin concentration","Mean corpuscular volume (MCV)","Mean Platelet Volume (MPV)","Monocytes","Neutrophils","Platelet count","Platelet Distribution Width","RBC","Red Blood Cell Count","Red cell distribution width (RDW)","WBC","White blood cell count (WBC or leukocyte count)"]},{"Name":"Fasting Blood Sugar","ParamDetails":[]},{"Name":"ESR","ParamDetails":[]},{"Name":"Physician Consultation","ParamDetails":[]},{"Name":"Urine Analysis","ParamDetails":["Bacteria","Bilirubin","Blood","Casts","Clarity/turbidity","Colour","Crystals","Glucose","Ketones","Leukocyte esterase","Nitrites","pH","Protein","RBCs","Specific gravity","Squamous epithelial cells","Urobilirubin","WBCs","Yeast"]}]
     * status_code : 200
     * status : Success
     * message : Success
     */

    private String __type;
    private int status_code;
    private String status;
    private String message;
    private RealmList<LstPackParameterEntity> lstPackParameter;
    @PrimaryKey
    private int packcode;

    public int getPackcode() {
        return packcode;
    }

    public void setPackcode(int packcode) {
        this.packcode = packcode;
    }

    public String get__type() {
        return __type;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RealmList<LstPackParameterEntity> getLstPackParameter() {
        return lstPackParameter;
    }

    public void setLstPackParameter(RealmList<LstPackParameterEntity> lstPackParameter) {
        this.lstPackParameter = lstPackParameter;
    }


}