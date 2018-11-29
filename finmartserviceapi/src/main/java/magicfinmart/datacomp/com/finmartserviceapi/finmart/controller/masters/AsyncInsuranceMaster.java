package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters;

import android.content.Context;
import android.os.AsyncTask;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.InsuranceMasterEntity;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncInsuranceMaster extends AsyncTask<Void, Void, Void> {
    PrefManager prefManager;
    Context mContext;
    InsuranceMasterEntity listCarMaster;

    public AsyncInsuranceMaster(Context context, InsuranceMasterEntity list) {
        listCarMaster = list;
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
                    realm.copyToRealmOrUpdate(listCarMaster.getGeneralinsurance());
                    realm.copyToRealmOrUpdate(listCarMaster.getHealthinsurance());
                    realm.copyToRealmOrUpdate(listCarMaster.getLifeinsurance());
                    prefManager.setIsInsuranceMasterUpdate(false);
                }
            });

//            realm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(listCarMaster.getGeneralinsurance());
//                }
//            });
//            realm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(listCarMaster.getHealthinsurance());
//                }
//            });
//
//
//            realm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(listCarMaster.getLifeinsurance());
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
        // prefManager.setIsInsuranceMasterUpdate(false);
    }
}
