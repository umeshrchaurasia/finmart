package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public class CompanyEntity  {
    /**
     * company_id : 1
     * Company_Name : Bharti
     */

    private String company_id;
    private String Company_Name;
    private boolean selected;

    public CompanyEntity() {
        this.company_id = "0";
        this.Company_Name = "";
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



    public CompanyEntity(String company_id, String company_Name) {
        this.company_id = company_id;
        Company_Name = company_Name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String Company_Name) {
        this.Company_Name = Company_Name;
    }
}