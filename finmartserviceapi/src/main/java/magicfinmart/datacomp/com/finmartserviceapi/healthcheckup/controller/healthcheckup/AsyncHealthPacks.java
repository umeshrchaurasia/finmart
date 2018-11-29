package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.controller.healthcheckup;

import android.content.Context;
import android.os.AsyncTask;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.APIResponse;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.IResponseSubcriber;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthPackDEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.HealthPacksDetailsRequestEntity;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.requestmodels.PackParamEntity;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncHealthPacks extends AsyncTask<Void, Void, Void> implements IResponseSubcriber {

    Context mContext;
    HealthPackDEntity lstPackageDetailsEntities;

    public AsyncHealthPacks(Context context, HealthPackDEntity list) {
        lstPackageDetailsEntities = list;
        mContext = context;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(lstPackageDetailsEntities);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (lstPackageDetailsEntities != null) {
            for (int i = 0; i < lstPackageDetailsEntities.getLstPackageDetails().size(); i++) {
                HealthPacksDetailsRequestEntity healthPacksDetailsRequestEntity = new HealthPacksDetailsRequestEntity();
                PackParamEntity packParamEntity = new PackParamEntity();
                if (!lstPackageDetailsEntities.getLstPackageDetails().get(i).getPackCode().equals(""))
                    packParamEntity.setPackcode(Integer.parseInt(lstPackageDetailsEntities.getLstPackageDetails().get(i).getPackCode()));
                healthPacksDetailsRequestEntity.setPack_param(packParamEntity);
                new HealthCheckUPController(mContext).getHealthPacksDetails(healthPacksDetailsRequestEntity, this);
            }
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

    }

    @Override
    public void OnFailure(Throwable t) {

    }
}
