package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.masters;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.BikeMasterEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.UserConstantEntity;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncUserConstatnt extends AsyncTask<Void, Void, Void> {
    PrefManager prefManager;
    Context mContext;
   UserConstantEntity userConstantEntity;

    public AsyncUserConstatnt(Context context,  UserConstantEntity tempConstantEntity) {
        this.userConstantEntity = tempConstantEntity;
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
                    realm.copyToRealmOrUpdate(userConstantEntity);

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
        //prefManager.setIsBikeMasterUpdate(false);
    }
}
