package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

/**
 * Created by Rajeev Ranjan on 22/02/2018.
 */

public class ContactUsEntity {

    /**
     * Id : 1
     * Header : Finmart Support Centre
     * DisplayTitle : FBA Support Centre
     * Email : fba.support@magicfinmart.com
     * Website : www.magicfinmart.com
     * PhoneNo : 022 - 66048200
     */

    private int Id;
    private String Header;
    private String DisplayTitle;
    private String Email;
    private String Website;
    private String PhoneNo;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String Header) {
        this.Header = Header;
    }

    public String getDisplayTitle() {
        return DisplayTitle;
    }

    public void setDisplayTitle(String DisplayTitle) {
        this.DisplayTitle = DisplayTitle;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String Website) {
        this.Website = Website;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }
}
