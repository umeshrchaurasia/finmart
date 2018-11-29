package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.PendingCasesEntity;

/**
 * Created by Rajeev Ranjan on 03/09/2018.
 */

public class PendingCaseInsLoanResponse extends APIResponse {

    /**
     * MasterData : {"Insurance":[{"Id":2080,"CustomerName":"Zggs Gsgs","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9895959959","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T18:10:48.000Z","pendingdays":28},{"Id":2079,"CustomerName":"Test Las","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9794994646","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T18:09:21.000Z","pendingdays":28},{"Id":2077,"CustomerName":"Test Ffhgg","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9930979650","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T18:06:55.000Z","pendingdays":28},{"Id":2075,"CustomerName":"Test Ffhgg","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9930979650","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T18:06:27.000Z","pendingdays":28},{"Id":2074,"CustomerName":"Test Ffhgg","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9930979650","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T18:05:55.000Z","pendingdays":28},{"Id":2072,"CustomerName":"Test Ffhgg","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T18:03:32.000Z","pendingdays":28},{"Id":7307,"CustomerName":"Vsd Dd","Category":"Health","qatype":"A","ApplnStatus":"25","mobile":"8959565656","quotetype":"HLI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/Religare.png","cdate":"2018-08-06T16:42:42.000Z","pendingdays":28},{"Id":2030,"CustomerName":" teat tefy test htch","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9767875784","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T15:37:24.000Z","pendingdays":28},{"Id":2026,"CustomerName":"TetLast Last","Category":"ICICI Pru Life","qatype":"Q","ApplnStatus":"0","mobile":"9898995946","quotetype":"LI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/uploads/InsurerLogo/icici_life.jpg","cdate":"2018-08-06T15:03:08.000Z","pendingdays":28},{"Id":16177,"CustomerName":"tests   sgfasgf","Category":"Motor","qatype":"A","ApplnStatus":"25","mobile":"9820098200","quotetype":"MOI","created_date":"06/08/2018","BankImage":"http://api.magicfinmart.com/InsurerImages/car_2.png","cdate":"2018-08-06T14:46:12.000Z","pendingdays":28}],"Loan":[{"Id":2772,"CustomerName":"ggghh hhh","Category":"Personal Loan","qatype":"Q","ApplnStatus":"0","mobile":"","quotetype":"PSL","created_date":"18/08/2018","BankImage":null,"cdate":"2018-08-18 01:17:13","pendingdays":16},{"Id":3700,"CustomerName":"test for  fnlhl","Category":"Home Loan","qatype":"Q","ApplnStatus":"0","mobile":"","quotetype":"HML","created_date":"17/07/2018","BankImage":null,"cdate":"2018-07-17 11:18:42","pendingdays":48},{"Id":922680,"CustomerName":"test new","Category":"Quick Lead","qatype":"922680","ApplnStatus":"0","mobile":"9899898998","quotetype":"QL","created_date":"14/07/2018","BankImage":"","cdate":"2018-07-14 02:55:14","pendingdays":51},{"Id":922679,"CustomerName":"tesyt name","Category":"Quick Lead","qatype":"922679","ApplnStatus":"0","mobile":"9788787575","quotetype":"QL","created_date":"14/07/2018","BankImage":"","cdate":"2018-07-14 02:54:21","pendingdays":51},{"Id":154,"CustomerName":"","Category":"Balance Transfer","qatype":"A","ApplnStatus":"0","mobile":"","quotetype":"BTR","created_date":"10/07/2018","BankImage":"http://erp.rupeeboss.com/Banklogo/magma.png","cdate":"2018-07-10 14:50:35","pendingdays":55},{"Id":140,"CustomerName":"","Category":"Balance Transfer","qatype":"A","ApplnStatus":"0","mobile":"","quotetype":"BTR","created_date":"07/07/2018","BankImage":"http://erp.rupeeboss.com/Banklogo/hdfc.png","cdate":"2018-07-07 12:01:34","pendingdays":58},{"Id":2450,"CustomerName":"test","Category":"LAP","qatype":"Q","ApplnStatus":"0","mobile":"","quotetype":null,"created_date":"05/07/2018","BankImage":null,"cdate":"2018-07-05 00:00:00","pendingdays":60},{"Id":99,"CustomerName":"","Category":"Balance Transfer","qatype":"A","ApplnStatus":"10","mobile":"","quotetype":"BTR","created_date":"30/06/2018","BankImage":"http://erp.rupeeboss.com/Banklogo/axis.png","cdate":"2018-06-30 17:38:58","pendingdays":65},{"Id":98,"CustomerName":"","Category":"Balance Transfer","qatype":"A","ApplnStatus":"10","mobile":"","quotetype":"BTR","created_date":"30/06/2018","BankImage":"http://erp.rupeeboss.com/Banklogo/icici.png","cdate":"2018-06-30 14:47:08","pendingdays":65},{"Id":97,"CustomerName":"","Category":"Balance Transfer","qatype":"A","ApplnStatus":"10","mobile":"","quotetype":"BTR","created_date":"30/06/2018","BankImage":"http://erp.rupeeboss.com/Banklogo/icici.png","cdate":"2018-06-30 12:31:39","pendingdays":65}]}
     */

    private MasterDataBean MasterData;

    public MasterDataBean getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataBean MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        private List<PendingCasesEntity> Insurance;
        private List<PendingCasesEntity> Loan;

        public List<PendingCasesEntity> getInsurance() {
            return Insurance;
        }

        public void setInsurance(List<PendingCasesEntity> Insurance) {
            this.Insurance = Insurance;
        }

        public List<PendingCasesEntity> getLoan() {
            return Loan;
        }

        public void setLoan(List<PendingCasesEntity> Loan) {
            this.Loan = Loan;
        }
    }
}
