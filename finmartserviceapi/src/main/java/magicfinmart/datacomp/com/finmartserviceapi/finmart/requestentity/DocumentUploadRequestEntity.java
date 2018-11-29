package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

/**
 * Created by Rajeev Ranjan on 17/03/2018.
 */

public class DocumentUploadRequestEntity {
    int FBAID, DocType;
    String DocName;

    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }

    public int getDocType() {
        return DocType;
    }

    public void setDocType(int docType) {
        DocType = docType;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }
}
