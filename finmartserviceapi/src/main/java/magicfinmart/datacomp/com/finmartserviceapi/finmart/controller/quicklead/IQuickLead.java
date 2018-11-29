package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.quicklead;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.QuickLeadRequestEntity;

/**
 * Created by Nilesh Birhade 09/02/18
 */

public interface IQuickLead {

    void saveQuickLead(QuickLeadRequestEntity quickLeadRequestEntity, IResponseSubcriber iResponseSubcriber);

}
