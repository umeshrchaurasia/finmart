package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;

/**
 * Created by Rajeev Ranjan on 25/01/2018.
 */

public class LoginResponse extends APIResponse {

    /**
     * HealthMasterData : {"FBAId":3,"FullName":"Jitendra Patel","UserName":"finmartlive@gmail.com","EmailID":"finmartlive@gmail.com","MobiNumb1":"8286115005","LiveURL":"1","LastloginDate":"2018-01-22T13:34:43.000Z","Validfrom":"2099-03-31T00:00:00.000Z","RewardPoint":"","IsFirstLogin":0,"IsDemo":0,"FSMFullname":"Jitendra Patel","FSMEmail":"finmartlive@gmail.com","FSMMobile":"8286115005","FBACode":"","Designation":"YOUR COMPLETE FINANCIAL ADVISOR","ProfPictName":null,"FSMDesig":"Your FinMart sales manager","RegMACAddr":"70:0b:c0:eb:79:89","SuppMobiNumb":"","SuppEmailId":"","FBAStatus":"R","POSPStatus":"6","UserType":"FBA","SuppAgenId":"1682","EditEmailId":"finmartlive@gmail.com","EditMobiNumb":"8286115005","EditDesig":"YOUR COMPLETE FINANCIAL ADVISOR","EditProfPictName":"","LoanId":"23335","PayStatus":"S","CustID":"680123","PaymentUrl":"","IsFoc":0,"POSPName":"NIDHI SINGH","POSEmail":"xyz@gmail.com","POSPMobile":"0909090909","SuccessStatus":"1","POSPInfo":"NIDHI SINGH~0909090909~xyz@gmail.com","FSM":"Jitendra Patel~finmartlive@gmail.com~8286115005~Your FinMart sales manager"}
     */

    private LoginResponseEntity MasterData;

    public LoginResponseEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(LoginResponseEntity MasterData) {
        this.MasterData = MasterData;
    }
}
