package magicfinmart.datacomp.com.finmartserviceapi.finmart.response;

import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.finmart.APIResponse;

/**
 * Created by Nilesh Birhade on 29-01-2018.
 */

public class SaveQuoteResponse extends APIResponse {

    private List<SaveQuoteEntity> MasterData;

    public List<SaveQuoteEntity> getMasterData() {
        return MasterData;
    }

    public void setMasterData(List<SaveQuoteEntity> MasterData) {
        this.MasterData = MasterData;
    }

    public static class SaveQuoteEntity {
        /**
         * VehicleRequestID : 12
         * SavedStatus : 0
         */

        private int VehicleRequestID;
        private int SavedStatus;

        public int getVehicleRequestID() {
            return VehicleRequestID;
        }

        public void setVehicleRequestID(int VehicleRequestID) {
            this.VehicleRequestID = VehicleRequestID;
        }

        public int getSavedStatus() {
            return SavedStatus;
        }

        public void setSavedStatus(int SavedStatus) {
            this.SavedStatus = SavedStatus;
        }
    }
}
