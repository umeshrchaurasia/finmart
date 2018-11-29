package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import java.util.List;

public class MasterSalesMaterialPromotionEntity  {

    private List<CompanyEntity> company;
    private List<DocsEntity> docs;


    public List<CompanyEntity> getCompany() {
        return company;
    }

    public void setCompany(List<CompanyEntity> company) {
        this.company = company;
    }

    public List<DocsEntity> getDocs() {
        return docs;
    }

    public void setDocs(List<DocsEntity> docs) {
        this.docs = docs;
    }


}