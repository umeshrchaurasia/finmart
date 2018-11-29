package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

/**
 * Created by Rajeev Ranjan on 01/03/2018.
 */

public class CreateTicketrequest {
    /**
     * CategoryId : 1
     * SubCategoryId : 2
     * classification : 10
     * Message : ;lkuydtfghjkl
     * FBAID : 1
     */

    private int CategoryId;
    private int SubCategoryId;
    private int classification;
    private String Message;
    private int FBAID;

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public int getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(int SubCategoryId) {
        this.SubCategoryId = SubCategoryId;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getFBAID() {
        return FBAID;
    }

    public void setFBAID(int FBAID) {
        this.FBAID = FBAID;
    }
}
