package booster.mingliu.boostertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicFragment;
import booster.mingliu.boostertest.events.SubmitScoreEvent;
import booster.mingliu.boostertest.models.SubmitDataModel;
import booster.mingliu.boostertest.restapi.RestModule;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuming on 17/1/12.
 */
public class SubmitFragment extends BasicFragment {

    @BindView(R.id.submit_name)
    EditText mNameET;
    @BindView(R.id.submit_email)
    EditText mEmailET;
    @BindView(R.id.submit_phone)
    EditText mPhoneET;
    @BindView(R.id.submit_button)
    Button mSubmitBtn;

    private SubmitDataModel mData = new SubmitDataModel();

    public static final String TAG = "SubmitFragment";

    public static SubmitFragment newInstance() {
        Bundle args = new Bundle();
        SubmitFragment fragment = new SubmitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SubmitFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private boolean validate() {
        return false;
    }

    private void submit() {
        request();
    }

    @Override
    protected Subscription doRequest() {
        return RestModule.getApis().submitInfoWithScore(mData).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                hideLoadingView();
                EventBus.getDefault().post(new SubmitScoreEvent());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                hideLoadingView();
            }
        });
    }
}
