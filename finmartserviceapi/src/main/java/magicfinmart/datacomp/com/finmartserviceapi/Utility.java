package magicfinmart.datacomp.com.finmartserviceapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.text.format.Formatter;
import android.util.Log;
import android.util.TypedValue;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import magicfinmart.datacomp.com.finmartserviceapi.database.DBPersistanceController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.controller.tracking.TrackingController;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.ConstantEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.LoginResponseEntity;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.model.TrackingData;
import magicfinmart.datacomp.com.finmartserviceapi.finmart.requestentity.TrackingRequestEntity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.WIFI_SERVICE;

/**
 * Created by Nilesh Birhade on 11-01-2018.
 */

public class Utility {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
   /* public static final String HORIZON_URL = "http://qa-horizon.policyboss.com:3000";
    public static final String QUOTE_BASE_URL = "http://qa.policyboss.com/";
    public static final String SECRET_KEY = "SECRET-ODARQ6JP-9V2Q-7BIM-0NNM-DNRTXRWMRTAL";
    public static final String CLIENT_KEY = "CLIENT-GLF2SRA5-CFIF-4X2T-HC1Z-CXV4ZWQTFQ3T";
    public static final int CLIENT_ID = 4;*/

    //public static final String HORIZON_URL = "http://horizon.policyboss.com:5000";
    //public static final String QUOTE_BASE_URL = "http://www.policyboss.com/";
    //public static final String HORIZON_URL = "http://qa-horizon.policyboss.com:3000";
    //public static final String QUOTE_BASE_URL = "http://qa.policyboss.com/";
    public static final String SECRET_KEY = "SECRET-VG9N6EVV-MIK3-1GFC-ZRBV-PE7XIQ8DV4GY";
    public static final String CLIENT_KEY = "CLIENT-WF4GWODI-HMEB-Q7M6-CLES-DEJCRF7XLRVI";
    public static final int CLIENT_ID = 3;
    public static final int LEAD_REQUEST_CODE = 22;

    public static final String VERSION_CODE = "2.0";
    public static final String RELEASE_DATE = "22/08/2018";
    public static final String BIKEQUOTE_UNIQUEID = "bike_quote_uniqueid";
    public static final String CARQUOTE_UNIQUEID = "car_quote_uniqueid";
    public static final String QUOTE_COUNTER = "quote_counter";
    public static final String SHARED_PREFERENCE_POLICYBOSS = "shared_finmart";
    public static final String HMLOAN_APPLICATION = "hmLoan_Application_LoanApply";
    public static final String PLLOAN_APPLICATION = "plLoan_Application_LoanApply";
    public static final String BTLOAN_APPLICATION = "btLoan_Application_LoanApply";
    public static String PUSH_BROADCAST_ACTION = "Finmart_Push_BroadCast_Action";
    public static String PUSH_NOTIFY = "notifyFlag";
    public static String PUSH_LOGIN_PAGE = "pushloginPage";

    public static String USER_PROFILE_ACTION = "Finmart_User_Profile_Action";

    public static String USER_DASHBOARD = "user_dashboard";


    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCE_POLICYBOSS, MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getSharedPreferenceEditor(Context context) {
        return getSharedPreference(context).edit();
    }

    public static String getCurrentMobileDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public static MultipartBody.Part getMultipartImage(File file) {
        RequestBody imgBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part imgFile = MultipartBody.Part.createFormData("DocFile", file.getName(), imgBody);
        return imgFile;
    }

    public static MultipartBody.Part getMultipartVideo(File file) {
        RequestBody imgBody = RequestBody.create(MediaType.parse("video/*"), file);
        MultipartBody.Part imgFile = MultipartBody.Part.createFormData("video", file.getName(), imgBody);
        return imgFile;
    }

//    public static HashMap<String, String> getBody(Context context,int FbaID,String DocTyp, String docName, String DocExt) {
//        HashMap<String, String> body = new HashMap<String, String>();
//
//
//        body.put("FBAID", String.valueOf(FbaID) );
//        body.put("DocType",DocTyp);
//        body.put("DocName", docName);
//        body.put("DocExt",DocExt);
//
//        return body;
//    }


    public static HashMap<String, String> getBody(Context context, int FbaID, int DocTyp, String DocName) {
        HashMap<String, String> body = new HashMap<String, String>();


        body.put("FBAID", String.valueOf(FbaID));
        body.put("DocType", String.valueOf(DocTyp));
        body.put("DocName", DocName);


        return body;
    }


    public static File createDirIfNotExists() {
        boolean ret = true;

        File file = new File(Environment.getExternalStorageDirectory(), "/FINMART");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Image folder");
                ret = false;
            }
        }
        return file;
    }

    public static File createShareDirIfNotExists() {
        boolean ret = true;

        File file = new File(Environment.getExternalStorageDirectory(), "/FINMART/QUOTES");
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("TravellerLog :: ", "Problem creating Quotes folder");
                ret = false;
            }
        }
        return file;
    }

    public static int checkShareStatus(Context context) {
        int pospStatus;
        /*DBPersistanceController dbPersistanceController = new DBPersistanceController(context);
        UserConstantEntity userConstantEntity = dbPersistanceController.getUserConstantsData();

        if (userConstantEntity != null) {
            pospStatus = Integer.parseInt(userConstantEntity.getPOSP_STATUS());
            if (pospStatus == 6)
                return 1;
        }*/
        return 1;
    }

    public static int checkPospTrainingStatus(Context context) {
        int pospStatus;
        DBPersistanceController dbPersistanceController = new DBPersistanceController(context);
        ConstantEntity constantEntity = dbPersistanceController.getConstantsData();

        if (constantEntity != null) {
            pospStatus = Integer.parseInt(constantEntity.getPOSPTraining());
            if (pospStatus == 1)
                return 1;
        }
        return 0;
    }

    public static String getLocalIpAddress(Context context) {
        String IPaddress;

        boolean WIFI = false;

        boolean MOBILE = false;

        ConnectivityManager CM = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    WIFI = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    MOBILE = true;
        }

        if (WIFI == true) {
            return GetDeviceipWiFiData(context);
        }

        if (MOBILE == true) {
            return GetDeviceipMobileData();

        }


       /* WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());*//*
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }*/
        return "";
    }

    public static String GetDeviceipMobileData() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface networkinterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return Formatter.formatIpAddress(inetAddress.hashCode());
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Current IP", ex.toString());
        }
        return "";
    }

    public static String getMacAddress(Context context) throws IOException {
//        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wInfo = wifiManager.getConnectionInfo();
//        Toast.makeText(context, "" + wInfo.getMacAddress(), Toast.LENGTH_SHORT).show();
//        return wInfo.getMacAddress();
        String address = "";
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            if (wifiManager.isWifiEnabled()) {
                // WIFI ALREADY ENABLED. GRAB THE MAC ADDRESS HERE
                WifiInfo info = wifiManager.getConnectionInfo();
                address = info.getMacAddress();
            } else {

                try {
                    // get all the interfaces
                    List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());

                    //find network interface wlan0
                    for (NetworkInterface networkInterface : all) {
                        if (!networkInterface.getName().equalsIgnoreCase("wlan0")) continue;
                        //get the hardware address (MAC) of the interface
                        byte[] macBytes = networkInterface.getHardwareAddress();
                        if (macBytes == null) {
                            return "";
                        }


                        StringBuilder res1 = new StringBuilder();
                        for (byte b : macBytes) {
                            //gets the last byte of b
                            res1.append(Integer.toHexString(b & 0xFF) + ":");
                        }

                        if (res1.length() > 0) {
                            res1.deleteCharAt(res1.length() - 1);
                        }
                        address = res1.toString();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Toast.makeText(context, "" + address, Toast.LENGTH_SHORT).show();
        return address;
    }

    public static String GetDeviceipWiFiData(Context context) {

        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);

        @SuppressWarnings("deprecation")

        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        return ip;

    }


    public static String getVersionName(Context context) {
        String versionName = "";
        PackageInfo pinfo;
        try {
            pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static int getVersionCode(Context context) {
        int versionCode = 0;
        PackageInfo pinfo;
        try {
            pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pinfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getDeviceId(Context context) {
        String deviceId = "";
        if (context != null)
            return Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        return deviceId;
    }

    public static String getTokenId(Context context) {
        PrefManager prefManager = new PrefManager(context);
        if (prefManager != null)
            return prefManager.getToken();
        return "";
    }

    public static String getMotorUrl(Context context, String Service_Log_Unique_Id) {
        new TrackingController(context).sendData(new TrackingRequestEntity(new TrackingData("Motor buy : buy button for motor"), "MOTOR INSURANCE"), null);
        LoginResponseEntity loginResponseEntity = new DBPersistanceController(context).getUserData();
        String ssid = "5";
        if (loginResponseEntity != null && loginResponseEntity.getPOSPNo() != null && !loginResponseEntity.getPOSPNo().equals(""))
            ssid = loginResponseEntity.getPOSPNo();
        String url = BuildConfig.PROPOSAL_BASE_URL;
        url = url + "buynowprivatecar/" + Utility.CLIENT_ID + "/" + Service_Log_Unique_Id + "/posp/" + ssid;
        return url;
    }

    public static String getTwoWheelerUrl(Context context, String Service_Log_Unique_Id) {
        new TrackingController(context).sendData(new TrackingRequestEntity(new TrackingData("TW buy : buy button for TW"), "TWO WHEELER INSURANCE"), null);
        LoginResponseEntity loginResponseEntity = new DBPersistanceController(context).getUserData();
        String ssid = "5";
        if (loginResponseEntity != null && loginResponseEntity.getPOSPNo() != null && !loginResponseEntity.getPOSPNo().equals(""))
            ssid = loginResponseEntity.getPOSPNo();
        String url = BuildConfig.PROPOSAL_BASE_URL;
        url = url + "buynowTwoWheeler/" + Utility.CLIENT_ID + "/" + Service_Log_Unique_Id + "/posp/" + ssid;
        return url;
    }

    public static String getInsurerImage(String insId) {
        int ID = 0;
        if (insId != null && !insId.equals(""))
            ID = Integer.parseInt(insId);
        return "http://api.magicfinmart.com/InsurerImages/car_" + ID + ".png";
    }

    public static void loadWebViewUrlInBrowser(Context context, String url) {
        Log.d("URL", url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        if (Uri.parse(url) != null) {
            browserIntent.setData(Uri.parse(url));
        }
        context.startActivity(browserIntent);
    }

    public static int getPrimaryColor(Context context) {
        TypedValue typedValue = new TypedValue();

        // I used getActivity() as if you were calling from a fragment.
        // You just want to call getTheme() on the current activity, however you can get it
        context.getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);

        // it's probably a good idea to check if the color wasn't specified as a resource
        if (typedValue.resourceId != 0) {
            return typedValue.resourceId;
        } else {
            // this should work whether there was a resource id or not
            return typedValue.data;
        }
    }
}
