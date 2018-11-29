package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

/**
 * Created by Rajeev Ranjan on 22/02/2018.
 */

public class WhatsNewEntity {

    /**
     * Id : 1
     * title : demo
     * discription : discription demo
     * appversion : 1.0
     */

    private int Id;
    private String title;
    private String discription;
    private String appversion;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }
}
