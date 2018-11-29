package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

public  class DocumentsOfflineEntity {
        /**
         * id : 5
         * quotes_request_id : 28
         * document_path : 1540994210.download 2.jpg
         * Created_date : 
         * Type : 1
         * Document_name : Quote 1
         */

        private String id;
        private String quotes_request_id;
        private String document_path;
        private String Created_date;
        private String Type;
        private String Document_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuotes_request_id() {
            return quotes_request_id;
        }

        public void setQuotes_request_id(String quotes_request_id) {
            this.quotes_request_id = quotes_request_id;
        }

        public String getDocument_path() {
            return document_path;
        }

        public void setDocument_path(String document_path) {
            this.document_path = document_path;
        }

        public String getCreated_date() {
            return Created_date;
        }

        public void setCreated_date(String Created_date) {
            this.Created_date = Created_date;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getDocument_name() {
            return Document_name;
        }

        public void setDocument_name(String Document_name) {
            this.Document_name = Document_name;
        }
    }