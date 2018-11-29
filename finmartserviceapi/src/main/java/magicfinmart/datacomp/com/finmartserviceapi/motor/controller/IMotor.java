package magicfinmart.datacomp.com.finmartserviceapi.motor.controller;


import magicfinmart.datacomp.com.finmartserviceapi.motor.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.MotorRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.motor.requestentity.SaveAddOnRequestEntity;

/**
 * Created by Nilesh Birhade on 11-01-2018.
 */

public interface IMotor {

    void getMotorPremiumInitiate(MotorRequestEntity entity, IResponseSubcriber iResponseSubcriber);

    void getMotorQuote(int product, IResponseSubcriber iResponseSubcriber);

    void saveAddOn(SaveAddOnRequestEntity saveAddOnRequestEntity, IResponseSubcriber iResponseSubcriber);

    void getMotorQuoteOneTime(int product, IResponseSubcriber iResponseSubcriber);

}
