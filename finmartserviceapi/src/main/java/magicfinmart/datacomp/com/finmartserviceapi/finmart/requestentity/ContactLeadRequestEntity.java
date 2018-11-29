package magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ContactRequestEntity;

/**
 * Created by IN-RB on 17-10-2018.
 */

public class ContactLeadRequestEntity {


    /**
     * fbaid : 12
     * contactlist : [{"name":"Three","mobileno":"807232323"},{"name":"Four","mobileno":"77232323"}]
     */

    private String fbaid;
    private List<ContactRequestEntity> contactlist;

    public String getFbaid() {
        return fbaid;
    }

    public void setFbaid(String fbaid) {
        this.fbaid = fbaid;
    }

    public List<ContactRequestEntity> getContactlist() {
        return contactlist;
    }

    public void setContactlist(List<ContactRequestEntity> contactlist) {
        this.contactlist = contactlist;
    }


}
