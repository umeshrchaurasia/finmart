package com.datacomp.magicfinmart.utility;

import android.content.Context;
import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import magicfinmart.datacomp.com.finmartserviceapi.Utility;

/**
 * Created by IN-RB on 11-10-2018.
 */

public class RealmMigrationClass implements RealmMigration {

    Context context;
    int applatestVersion = 0;

    public RealmMigrationClass(Context context) {
        this.context = context;
        applatestVersion = Utility.getVersionCode(context);
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        Log.d("TAG_REAL", "old :" + oldVersion + " latest :" + newVersion);

        if (oldVersion < applatestVersion) {

            if (oldVersion == 27) {
                if (schema.get("UserConstantEntity") != null) {

                    RealmObjectSchema userConstantSchema = schema.get("UserConstantEntity");
                    // userConstantSchema.addField("test", String.class);
                    //  RealmObjectSchema userConstantSchema = schema.get("UserConstantEntity");
//                    userConstantSchema.addField("AddPospVisible", String.class);
                    userConstantSchema.addField("userid", String.class);
                    userConstantSchema.addField("marketinghomepopupid", String.class);
                    userConstantSchema.addField("marketinghometitle", String.class);
                    userConstantSchema.addField("marketinghomedesciption", String.class);
                    userConstantSchema.addField("marketinghomemaxcount", String.class);
                    userConstantSchema.addField("marketinghomeenabled", String.class);
                    userConstantSchema.addField("marketinghometransfertype", String.class);
                    userConstantSchema.addField("marketinghomeurl", String.class);
                }
                // Delete all other data than `ProfileUser`
                for (RealmObjectSchema classSchema : schema.getAll()) {
                    if (classSchema.getClassName().equals("LoginResponseEntity")) {
                        continue;
                    }
                    realm.delete(classSchema.getClassName());
                }

                oldVersion++;
            }
        }

    }
}
