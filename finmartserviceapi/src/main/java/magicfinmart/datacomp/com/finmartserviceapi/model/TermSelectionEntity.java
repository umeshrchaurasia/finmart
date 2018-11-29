package magicfinmart.datacomp.com.finmartserviceapi.model;

/**
 * Created by Nilesh Birhade on 05-04-2018.
 */

public class TermSelectionEntity {

    private String companyName;
    private int compantID;
    private String companyLogo;

    public TermSelectionEntity(String companyName, int compantID, String companyLogo) {
        this.companyName = companyName;
        this.compantID = compantID;
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompantID() {
        return compantID;
    }

    public void setCompantID(int compantID) {
        this.compantID = compantID;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }
}
