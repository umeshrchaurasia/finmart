package com.datacomp.magicfinmart.contact_lead;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import io.realm.Realm;
import magicfinmart.datacomp.com.finmartserviceapi.PrefManager;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ContactlistEntity;

/**
 * Created by Nilesh Birhade on 29-11-2017.
 */

public class AsyncContactMaster extends AsyncTask<Void, Void, Void> {
    PrefManager prefManager;
    Context mContext;
    List<ContactlistEntity> listCarMaster;

    public AsyncContactMaster(Context context, List<ContactlistEntity> list) {
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
        ((ContactLeadActivity) mContext).contactSavedToDB();
    }
}
