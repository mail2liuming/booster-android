package booster.mingliu.boostertest.restapi;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import booster.mingliu.boostertest.BoosterApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuming on 17/1/12.
 */
public class RestModule {
    private static final String BASE_URL = "https://booster/test/";
    private static RestApis apis = null;

    static {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build();

        apis = retrofit.create(RestApis.class);

    }

    public static RestApis getApis() {
        return apis;
    }

    private static OkHttpClient okHttpClient() {


        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .cache(providesCache())
                .build();
    }

    private static Cache providesCache() {
        File cacheFile = new File(BoosterApplication.get().getCacheDir(), "okhttp");
        return new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
    }

}
