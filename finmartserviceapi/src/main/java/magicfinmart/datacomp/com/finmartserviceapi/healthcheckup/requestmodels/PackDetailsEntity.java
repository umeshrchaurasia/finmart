package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels;

public class PackDetailsEntity {


    /**
     * username : Datacomp
     * pass : Health@1234
     * fromamt : 0
     * toamt : 0
     * fromage : 0
     * toage : 0
     * gender : B
     */

    private String username;
    private String pass;
    private int fromamt;
    private int toamt;
    private int fromage;
    private int toage;
    private String gender;

    public PackDetailsEntity() {
        this.username = "Datacomp";
        this.pass = "Health@1234";
        this.fromamt = 0;
        this.toamt = 0;
        this.fromage = 0;
        this.toage = 0;
        this.gender = "B";
    }

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

    public int getFromamt() {
        return fromamt;
    }

    public void setFromamt(int fromamt) {
        this.fromamt = fromamt;
    }

    public int getToamt() {
        return toamt;
    }

    public void setToamt(int toamt) {
        this.toamt = toamt;
    }

    public int getFromage() {
        return fromage;
    }

    public void setFromage(int fromage) {
        this.fromage = fromage;
    }

    public int getToage() {
        return toage;
    }

    public void setToage(int toage) {
        this.toage = toage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}