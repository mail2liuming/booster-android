package booster.mingliu.boostertest.basic;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import booster.mingliu.boostertest.R;
import butterknife.BindView;
import rx.Subscription;

/**
 * Created by liuming on 17/1/12.
 */
public class BasicFragment extends Fragment {
    @Nullable
    @BindView(R.id.progress)
    View mLoadingView;

    private Subscription mSubscription;

    protected  Subscription doRequest() {return null;}

    public void request() {
        unSubscribe();
        mSubscription = doRequest();
    }

    private void unSubscribe() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    protected void showLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        }
    }

    protected void hideLoadingView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unSubscribe();
    }

}
