package magicfinmart.datacomp.com.finmartserviceapi.loan_fm.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.loan_fm.APIResponse;

public class ShareMessageResponse extends APIResponse {


    /**
     * result : {"lstMsgLnkDtls":[{"MsgBody":"test Msg","link":"rupeeboss.com/bt.php?empCode=","title":"test Msg"},{"MsgBody":"This is a test Message","link":"rupeeboss.com/mt.php?empCode=","title":"Msg"}]}
     */

    private ResultEntity result;

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public static class ResultEntity {
        private List<LstShareMessageEntity> lstMsgLnkDtls;

        public List<LstShareMessageEntity> getLstMsgLnkDtls() {
            return lstMsgLnkDtls;
        }

        public void setLstMsgLnkDtls(List<LstShareMessageEntity> lstMsgLnkDtls) {
            this.lstMsgLnkDtls = lstMsgLnkDtls;
        }


    }

    public  class LstShareMessageEntity {
        /**
         * MsgBody : test Msg
         * link : rupeeboss.com/bt.php?empCode=
         * title : test Msg
         */

        private String MsgBody;
        private String link;
        private String title;

        public String getMsgBody() {
            return MsgBody;
        }

        public void setMsgBody(String MsgBody) {
            this.MsgBody = MsgBody;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}