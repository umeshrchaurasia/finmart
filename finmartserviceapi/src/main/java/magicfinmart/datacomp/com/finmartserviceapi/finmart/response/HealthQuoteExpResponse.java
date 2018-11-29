package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.HealthQuoteEntity;

/**
 * Created by Nilesh Birhade on 17-02-2018.
 */

public class HealthQuoteExpResponse extends APIResponse {


    private MasterDataEntity MasterData;

    public MasterDataEntity getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterDataEntity MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataEntity {

        private int HealthRequestId;
        private HealthQuoteBean health_quote;

        public int getHealthRequestId() {
            return HealthRequestId;
        }

        public void setHealthRequestId(int HealthRequestId) {
            this.HealthRequestId = HealthRequestId;
        }

        public HealthQuoteBean getHealth_quote() {
            return health_quote;
        }

        public void setHealth_quote(HealthQuoteBean health_quote) {
            this.health_quote = health_quote;
        }

        public static class HealthQuoteBean {
            private List<HealthQuoteEntity> header;
            private List<HealthQuoteEntity> child;

            public List<HealthQuoteEntity> getHeader() {
                return header;
            }

            public void setHeader(List<HealthQuoteEntity> header) {
                this.header = header;
            }

            public List<HealthQuoteEntity> getChild() {
                return child;
            }

            public void setChild(List<HealthQuoteEntity> child) {
                this.child = child;
            }

        }
    }
}
