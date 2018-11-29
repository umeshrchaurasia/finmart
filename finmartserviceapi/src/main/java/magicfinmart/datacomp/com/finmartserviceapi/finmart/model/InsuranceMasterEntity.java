package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import java.util.List;

public class InsuranceMasterEntity {
    private List<LifeinsuranceEntity> lifeinsurance;
    private List<GeneralinsuranceEntity> generalinsurance;
    private List<HealthinsuranceEntity> healthinsurance;

    public List<LifeinsuranceEntity> getLifeinsurance() {
        return lifeinsurance;
    }

    public void setLifeinsurance(List<LifeinsuranceEntity> lifeinsurance) {
        this.lifeinsurance = lifeinsurance;
    }

    public List<GeneralinsuranceEntity> getGeneralinsurance() {
        return generalinsurance;
    }

    public void setGeneralinsurance(List<GeneralinsuranceEntity> generalinsurance) {
        this.generalinsurance = generalinsurance;
    }

    public List<HealthinsuranceEntity> getHealthinsurance() {
        return healthinsurance;
    }

    public void setHealthinsurance(List<HealthinsuranceEntity> healthinsurance) {
        this.healthinsurance = healthinsurance;
    }
}