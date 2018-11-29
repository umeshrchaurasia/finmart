package magicfinmart.datacomp.com.finmartserviceapi.express_loan.controller;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.express_loan.model.KotakPLEmployerNameEntity;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncEmployerNameMaster extends AsyncTask<Void, Void, Void> {
    PrefManager prefManager;
    Context mContext;
    List<KotakPLEmployerNameEntity> listCarMaster;

    public AsyncEmployerNameMaster(Context context, List<KotakPLEmployerNameEntity> list) {
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
                    realm.copyToRealmOrUpdate(listCarMaster);
                    prefManager.setIsEmployerNAmeUpdate(false);
                }
            });
//            realm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(listCarMaster);
//                    prefManager.setIsBikeMasterUpdate(false);
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
        //prefManager.setIsBikeMasterUpdate(false);
    }
}
