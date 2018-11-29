package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters;


import magicfinmart.datacomp.com.finmartserviceapi.finmart.IResponseSubcriber;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public interface IMasterFetch {

    public void getCarMaster(IResponseSubcriber iResponseSubcriber);

    public void getBikeMaster(IResponseSubcriber iResponseSubcriber);

    public void getRTOMaster(IResponseSubcriber iResponseSubcriber);

    public void getInsuranceMaster(IResponseSubcriber iResponseSubcriber);

    public void getContactList(IResponseSubcriber iResponseSubcriber);

    public void getWhatsNew(String app_version, IResponseSubcriber iResponseSubcriber);

    public void getConstants(IResponseSubcriber iResponseSubcriber);

    public void getMpsData(IResponseSubcriber iResponseSubcriber);

    public void applyMPSPromoCode(String promoCode, IResponseSubcriber iResponseSubcriber);

    public void geUserConstant( int type, IResponseSubcriber iResponseSubcriber);

    public void getMenuMaster(IResponseSubcriber iResponseSubcriber);

    public void getInsuranceSubType(IResponseSubcriber iResponseSubcriber);

}
