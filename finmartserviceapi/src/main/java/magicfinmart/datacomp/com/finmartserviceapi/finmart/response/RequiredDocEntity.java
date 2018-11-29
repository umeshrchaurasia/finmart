package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

public class RequiredDocEntity {
    /**
     * id : 1
     * docname : Last year policy copy
     * reqid : 1
     */

    private int id;
    private String docname;
    private int reqid;


    private boolean isUploaded;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public int getReqid() {
        return reqid;
    }

    public void setReqid(int reqid) {
        this.reqid = reqid;
    }

    @Override
    public String toString() {
        return this.docname;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }
}