package booster.mingliu.boostertest;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by liuming on 17/1/11.
 */
public class BoosterApplication extends Application {

    private static BoosterApplication sInstance;

    public static BoosterApplication get(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CircularStd-Book.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}



