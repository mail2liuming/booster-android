package booster.mingliu.boostertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicActivity;
import booster.mingliu.boostertest.basic.BasicFragment;
import booster.mingliu.boostertest.common.utils.FragmentManagerUtil;
import booster.mingliu.boostertest.events.QuestionCheckedEvent;
import booster.mingliu.boostertest.models.QuestionModelManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuming on 17/1/12.
 */
public class QuestionFragment extends BasicFragment {

    @BindView(R.id.question_content)
    TextView mQuestionContentTV;
    @BindView(R.id.question_list)
    RecyclerView mQuestionList;
    @BindView(R.id.question_next)
    Button mQuestionNextBtn;

    public static QuestionFragment newInstance() {
        Bundle args = new Bundle();
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionare, container, false);
        ButterKnife.bind(this, view);

        int curQuestion = QuestionModelManager.getsInstance().getCurQuestionIdx();
        mQuestionContentTV.setText(QuestionModelManager.getsInstance().getQuestion());
        mQuestionList.setAdapter(new QuestionAdapter());
        mQuestionNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
        updateNextBtn();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(QuestionCheckedEvent event) {
        updateNextBtn();
    }

    private void updateNextBtn() {
        int curOption = QuestionModelManager.getsInstance().getCurOption();
        if (curOption >= 0) {
            mQuestionNextBtn.setEnabled(true);
        } else {
            mQuestionNextBtn.setEnabled(false);
        }
    }

    private void next() {
        getFragmentManager().popBackStack();
        if (QuestionModelManager.getsInstance().isLastQuestion()) {
            FragmentManagerUtil.addFragment((BasicActivity)getActivity(), QuestionResultFragment.newInstance(), "", FragmentManagerUtil.Animation.NONE);
        } else {
            FragmentManagerUtil.addFragment((BasicActivity)getActivity(), QuestionFragment.newInstance(), "", FragmentManagerUtil.Animation.NONE);
        }
    }

    public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionVH> {

        @Override
        public QuestionVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
            return new QuestionVH(view);
        }

        @Override
        public void onBindViewHolder(QuestionVH holder, int position) {
            List<String> options = QuestionModelManager.getsInstance().getOptions();
            int curOption = QuestionModelManager.getsInstance().getCurOption();
            if (null != options && position >= 0 && position < options.size()) {
                holder.mQuestionTV.setText(options.get(position));
                if (position == curOption) {
                    holder.mOptionCheckBox.setChecked(true);
                } else {
                    holder.mOptionCheckBox.setChecked(false);
                }
                final int finalPos = position;
                holder.mOptionCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        QuestionModelManager.getsInstance().setCurOption(finalPos);
                        notifyDataSetChanged();
                        EventBus.getDefault().post(new QuestionCheckedEvent());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return QuestionModelManager.getsInstance().getOptionsCount();
        }

        class QuestionVH extends RecyclerView.ViewHolder {

            @BindView(R.id.question)
            TextView mQuestionTV;
            @BindView(R.id.question_option)
            AppCompatCheckBox mOptionCheckBox;

            public QuestionVH(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }
}