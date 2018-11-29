package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.zoho;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.CreateTicketrequest;

/**
 * Created by Rajeev Ranjan on 01/03/2018.
 */

public interface IZoho {
    void getTicketCategories(IResponseSubcriber iResponseSubcriber);

    void createTicket(CreateTicketrequest createTicketrequest, IResponseSubcriber iResponseSubcriber);

    void getListOfTickets(String fbaid, IResponseSubcriber iResponseSubcriber);
}
