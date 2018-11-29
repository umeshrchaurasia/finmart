package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public class HealthPacksDetailsRequestEntity {


    /**
     * pack_param : {"username":"Datacomp","pass":"Health@1234","packcode":71}
     */

    private PackParamEntity pack_param;

    public PackParamEntity getPack_param() {
        return pack_param;
    }

    public void setPack_param(PackParamEntity pack_param) {
        this.pack_param = pack_param;
    }


}
