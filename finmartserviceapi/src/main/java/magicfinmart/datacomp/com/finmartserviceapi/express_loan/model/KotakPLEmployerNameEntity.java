package magicfinmart.datacomp.com.finmartserviceapi.express_loan.model;

import io.realm.RealmObject;

public  class KotakPLEmployerNameEntity extends RealmObject{
        /**
         * employername : L AND T - MHI PVT LTD
         * final_category : CAT C
         */

        private String employername;
        private String final_category;

        public String getEmployername() {
            return employername;
        }

        public void setEmployername(String employername) {
            this.employername = employername;
        }

        public String getFinal_category() {
            return final_category;
        }

        public void setFinal_category(String final_category) {
            this.final_category = final_category;
        }
    }