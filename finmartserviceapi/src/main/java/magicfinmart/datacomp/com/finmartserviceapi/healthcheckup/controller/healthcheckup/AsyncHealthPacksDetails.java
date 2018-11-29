package magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.controller.healthcheckup;

import android.content.Context;
import android.os.AsyncTask;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.healthcheckup.model.HealthPackDetailsDBean;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncHealthPacksDetails extends AsyncTask<Void, Void, Void> {

    Context mContext;
    HealthPackDetailsDBean lstPackParameterEntities;

    public AsyncHealthPacksDetails(Context context, HealthPackDetailsDBean list) {
        lstPackParameterEntities = list;
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
                    realm.copyToRealmOrUpdate(lstPackParameterEntities);
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

    }
}
