package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.RblCityEntity;

/**
 * Created by Nilesh Birhade on 12-01-2018.
 */

public class AsyncRblCityMaster extends AsyncTask<Void, Void, Void> {
    PrefManager prefManager;
    Context mContext;
    List<RblCityEntity> listRblCityMaster;

    public AsyncRblCityMaster(Context context, List<RblCityEntity> list) {
        listRblCityMaster = list;
        mContext = context;
        prefManager = new PrefManager(mContext);
    }


    @Override
    protected Void doInBackground(Void... voids) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(listRblCityMaster);
                    prefManager.setIsRblCityMaster(false);
                }
            });
//            realm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(listRblCityMaster);
//                    prefManager.setIsRblCityMaster(false);
//                }
//            });


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
        prefManager.setIsRblCityMaster(false);
    }
}
