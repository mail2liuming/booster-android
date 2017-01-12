package booster.mingliu.boostertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicActivity;
import booster.mingliu.boostertest.basic.BasicFragment;
import booster.mingliu.boostertest.common.utils.FragmentManagerUtil;
import booster.mingliu.boostertest.models.QuestionModelManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuming on 17/1/12.
 */
public class QuestionResultFragment extends BasicFragment {
    @BindView(R.id.question_result_score)
    TextView mScoreTV;
    @BindView(R.id.question_result_type)
    TextView mTypeTV;
    @BindView(R.id.question_result_show)
    Button mShowBtn;

    public static QuestionResultFragment newInstance() {
        Bundle args = new Bundle();
        QuestionResultFragment fragment = new QuestionResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionResultFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_result, container, false);
        ButterKnife.bind(this, view);
        mScoreTV.setText(QuestionModelManager.getsInstance().getScore());
        mTypeTV.setText("");
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                FragmentManagerUtil.addFragment((BasicActivity) getActivity(), FundTypesFragment.newInstance(QuestionModelManager.getsInstance().getCurType()), "", FragmentManagerUtil.Animation.NONE);
            }
        });
        return view;
    }
}