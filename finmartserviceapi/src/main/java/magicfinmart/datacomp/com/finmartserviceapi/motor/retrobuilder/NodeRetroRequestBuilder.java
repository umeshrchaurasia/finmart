package magicfinmart.datacomp.com.finmartserviceapi.motor.retrobuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import magicfinmart.datacomp.com.finmartserviceapi.BuildConfig;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class NodeRetroRequestBuilder {

    //protected String url = "http://services.rupeeboss.com/LoginDtls.svc/";
    static Retrofit restAdapter = null;

    // test url
   /* public static String URL = "http://qa-horizon.policyboss.com:3000";
    public static String PROPOSAL_BASE_URL = "http://qa.policyboss.com/";*/


    // live url

    //public static String URL = "http://horizon.policyboss.com:5000";
    //public static String PROPOSAL_BASE_URL = "http://www.policyboss.com/";


    protected Retrofit build() {
        if (restAdapter == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();

            restAdapter = new Retrofit.Builder()
                    .baseUrl(BuildConfig.NODE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return restAdapter;
    }


}