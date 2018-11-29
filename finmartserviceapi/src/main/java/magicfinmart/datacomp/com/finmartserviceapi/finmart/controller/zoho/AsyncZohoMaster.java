package magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.zoho;

import android.content.Context;
import android.os.AsyncTask;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ZohoTicketCategoryEntity;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncZohoMaster extends AsyncTask<Void, Void, Void> {


    PrefManager prefManager;
    Context mContext;
    ZohoTicketCategoryEntity zohoTicketCategoryEntity;

    public AsyncZohoMaster(Context context, ZohoTicketCategoryEntity list) {
        this.zohoTicketCategoryEntity = list;
        mContext = context;
        prefManager = new PrefManager(mContext);
    }


    @Override
    protected Void doInBackground(Void... voids) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(zohoTicketCategoryEntity);
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
        prefManager.setIsZohoMaster(false);
    }
}
