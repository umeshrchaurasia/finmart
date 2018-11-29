package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.MemberListEntity;

/**
 * Created by Nilesh Birhade on 08-03-2018.
 */

public class HealthCompareRequestEntity {



    private String HealthRequestId;
    private String PlanID;
    private String insImage;
    private int selectedPrevInsID;
    private String ProdID;
    List<MemberListEntity> MemberList;

    public List<MemberListEntity> getMemberlist() {
        return MemberList;
    }

    public void setMemberlist(List<MemberListEntity> memberlist) {
        this.MemberList = memberlist;
    }

    public String getProdID() {
        return ProdID;
    }

    public void setProdID(String prodID) {
        ProdID = prodID;
    }

    public String getPlanID() {
        return PlanID;
    }

    public int getSelectedPrevInsID() {
        return selectedPrevInsID;
    }

    public void setSelectedPrevInsID(int selectedPrevInsID) {
        this.selectedPrevInsID = selectedPrevInsID;
    }

    public String getInsImage() {

        return insImage;
    }

    public void setInsImage(String insImage) {
        this.insImage = insImage;
    }

    public void setPlanID(String planID) {
        PlanID = planID;
    }

    public String getHealthRequestId() {
        return HealthRequestId;
    }

    public void setHealthRequestId(String HealthRequestId) {
        this.HealthRequestId = HealthRequestId;
    }



}
