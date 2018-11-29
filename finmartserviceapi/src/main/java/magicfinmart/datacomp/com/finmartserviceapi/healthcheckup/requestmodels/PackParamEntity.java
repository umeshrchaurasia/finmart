package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels;

public class PackParamEntity {
    /**
     * username : Datacomp
     * pass : Health@1234
     * packcode : 71
     */

    private String username;
    private String pass;

    public PackParamEntity() {
        this.username = "Datacomp";
        this.pass = "Health@1234";
        this.packcode = 0;
    }

    private int packcode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPackcode() {
        return packcode;
    }

    public void setPackcode(int packcode) {
        this.packcode = packcode;
    }
}