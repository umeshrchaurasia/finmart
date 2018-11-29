package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.salesmaterial;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

/**
 * Created by Nilesh Birhade 09/02/18
 */

public interface ISalesMaterial {

    void getSalesProducts(IResponseSubcriber iResponseSubcriber);

    void getProductPromotions(int productID, IResponseSubcriber iResponseSubcriber);
}
