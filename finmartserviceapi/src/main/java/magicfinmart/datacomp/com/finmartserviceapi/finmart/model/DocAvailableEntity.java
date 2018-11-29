package magicfinmart.datacomp.com.finmartserviceapi.finmart.model;

import io.realm.RealmObject;

public class DocAvailableEntity  extends RealmObject {
    /**
     * DocType : 1
     * FileName : 1519378976465.png
     * DocName : Profile
     */

    private int DocType;
    private String FileName;
    private String DocName;

    public DocAvailableEntity() {
        DocType = 0;
        FileName = "";
        DocName = "";
    }

    public int getDocType() {
        return DocType;
    }

    public void setDocType(int DocType) {
        this.DocType = DocType;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String DocName) {
        this.DocName = DocName;
    }
}