package booster.mingliu.boostertest.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import booster.mingliu.boostertest.MainActivity;
import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuming on 17/1/11.
 */
public class LaunchActivity extends BasicActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        //Show launch screen for 2 seconds
        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
