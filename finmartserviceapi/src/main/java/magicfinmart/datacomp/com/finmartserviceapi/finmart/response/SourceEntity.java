package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

public class SourceEntity {
    public SourceEntity( String source_name,int id) {
        this.id = id;
        Source_name = source_name;
    }

    /**
     * id : 1
     * Source_name : Fin-Mart
     */

    private int id;
    private String Source_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_name() {
        return Source_name;
    }

    public void setSource_name(String Source_name) {
        this.Source_name = Source_name;
    }

    @Override
    public String toString() {
        return this.Source_name;
    }
}