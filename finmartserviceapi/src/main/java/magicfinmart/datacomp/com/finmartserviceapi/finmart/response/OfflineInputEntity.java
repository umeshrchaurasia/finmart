package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

public class OfflineInputEntity {
    /**
     * id : 1
     * productname : Motor Private
     * description : Type of Policy (Comprehensive / TP only) :
     * Make :
     * Model :
     * Variant :
     * Year of Manufacture :
     * Date of Registration :
     * RTO (where registered) :
     * Claim Status in last policy :
     * Sum Insured (IDV) in the last policy :
     * Insurer :
     * Any special cover needed :
     * isactive : 1
     */

    private int id;
    private String productname;
    private String description;
    private int isactive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    @Override
    public String toString() {
        return this.productname;
    }
}