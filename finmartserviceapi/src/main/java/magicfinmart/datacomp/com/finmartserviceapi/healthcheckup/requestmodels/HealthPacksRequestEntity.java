package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels;

/**
 * Created by Rajeev Ranjan on 15/02/2018.
 */

public class HealthPacksRequestEntity {
    /**
     * pack_details : {"username":"Datacomp","pass":"Health@1234","fromamt":0,"toamt":0,"fromage":0,"toage":0,"gender":"B"}
     */

    private PackDetailsEntity pack_details;

    public PackDetailsEntity getPack_details() {
        return pack_details;
    }

    public void setPack_details(PackDetailsEntity pack_details) {
        this.pack_details = pack_details;
    }


}
