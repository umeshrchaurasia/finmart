package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 03-08-2018.
 */

public class ICICICompanyResponse extends APIResponse {


    private List<CompanyNameEntity> result;

    public List<CompanyNameEntity> getResult() {
        return result;
    }

    public void setResult(List<CompanyNameEntity> result) {
        this.result = result;
    }

    public static class CompanyNameEntity {
        /**
         * company_name : ADVENTIST WOCKHARDT HEART HOSPITAL, SURAT
         */

        private String company_name;

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }
    }
}
