package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

public class ChildPospEntity {
    /**
     * FBAID : 52641
     * FullName : Testadd Addposp
     * DOB : 01-Oct-1982
     * MobiNumb1 : 9999999902
     * EmailID : testfbav@gmail.com
     * statuscode : 0
     */

    private int FBAID;
    private String FullName;
    private String DOB;
    private String MobiNumb1;
    private String EmailID;
    private String statuscode;

    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getMobiNumb1() {
        return MobiNumb1;
    }

    public void setMobiNumb1(String MobiNumb1) {
        this.MobiNumb1 = MobiNumb1;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }
}