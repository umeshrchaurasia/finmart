package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse;

/**
 * Created by Nilesh Birhade on 03-05-2018.
 */

public class HealthShortLinkResponse extends APIResponse {

    private List<MasterDataBean> MasterData;

    public List<MasterDataBean> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<MasterDataBean> MasterData) {
        this.MasterData = MasterData;
    }

    public static class MasterDataBean {
        /**
         * SavedStatus : 0
         * ShortURL : mgfm.in/l6z9j0
         * LongURL : http://bo.mgfm.in/health-packages?FBAID=0&FBAName=testing&FBAMobile=97809879788&PackCode=71
         */

        private int SavedStatus;
        private String ShortURL;
        private String LongURL;

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }

        public String getShortURL() {
            return ShortURL;
        }

        public void setShortURL(String ShortURL) {
            this.ShortURL = ShortURL;
        }

        public String getLongURL() {
            return LongURL;
        }

        public void setLongURL(String LongURL) {
            this.LongURL = LongURL;
        }
    }
}
