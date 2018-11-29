package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.model;

public class LeadEntity {
    /**
     * Remark :
     * Status :
     * followupDate : 06/26/2018
     * followuptime : null
     * statusId : 15
     * updatedBy : Bhupinder Kaur Sethi
     * updatedDate : 6/26/2018 1:25:41 PM
     */
    private String followuptime;

    private int statusId;
    private String updatedBy;
    private String updatedDate;
    private String Remark;
    private String Status;
    private String followupDate;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFollowupDate() {
        return followupDate;
    }

    public void setFollowupDate(String followupDate) {
        this.followupDate = followupDate;
    }

    public String getFollowuptime() {
        return followuptime;
    }

    public void setFollowuptime(String followuptime) {
        this.followuptime = followuptime;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }


}