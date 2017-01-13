package booster.mingliu.boostertest.models;

import android.content.Context;
import android.content.res.Resources;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import booster.mingliu.boostertest.BoosterApplication;
import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.common.utils.PreferenceUtils;
import booster.mingliu.boostertest.events.SubmitScoreEvent;

/**
 * Created by liuming on 17/1/12.
 */
public class QuestionModelManager {
    public static final int SUBMIT_STATUS_NONE = 0;
    public static final int SUBMIT_STATUS_ONGING = 1;
    public static final int SUBMIT_STATUS_DONE = 2;

    public static final int OPTIONS_TOTAL = 5;
    public static final int QUESTION_TOTAL = 5;

    private String[] mQuestions;
    private String[] mTypeMaps;

    private List<String[]> mQuestionOptions;

    private int[] mScoreMap = {1, 3, 5, 7, 10};


    private int mCurOption = -1;
    private int mTotalScore = 0;
    private int mCurQuestionIdx = 0;
    private int mCurStatus = SUBMIT_STATUS_NONE;

    private static QuestionModelManager sInstance = new QuestionModelManager();

    public static QuestionModelManager getsInstance() {
        return sInstance;
    }

    private QuestionModelManager() {
        reset();
    }

    public void load() {
        Resources res = BoosterApplication.get().getResources();
        mQuestions = res.getStringArray(R.array.questions);
        mTypeMaps = res.getStringArray(R.array.investor_type);
        mQuestionOptions = new ArrayList<>();
        mQuestionOptions.add(res.getStringArray(R.array.options_1));
        mQuestionOptions.add(res.getStringArray(R.array.options_2));
        mQuestionOptions.add(res.getStringArray(R.array.options_3));
        mQuestionOptions.add(res.getStringArray(R.array.options_4));
        mQuestionOptions.add(res.getStringArray(R.array.options_5));

    }

    public void reset() {
        mCurOption = -1;
        mTotalScore = 0;
        mCurQuestionIdx = 0;
    }

    public int getScore() {
        return mTotalScore;
    }

    public void setCurOption(int option) {
        mCurOption = option;
    }

    public int getCurOption() {
        return mCurOption;
    }

    public int getCurQuestionIdx() {
        return mCurQuestionIdx;
    }

    public void setCurQuestionIdx(int idx) {
        mCurQuestionIdx = idx;
    }

    public void nextQuestion() {
        mCurQuestionIdx++;

        caculate();
        saveIfNeeded();
        mCurOption = -1;

    }

    public String getQuestion() {
        if (mCurQuestionIdx >= 0 && mCurQuestionIdx < QUESTION_TOTAL) {
            return mQuestions[mCurQuestionIdx];
        }
        return null;
    }

    public String[] getOptions() {
        if (mCurQuestionIdx >= 0 && mCurQuestionIdx < QUESTION_TOTAL) {
            return mQuestionOptions.get(mCurQuestionIdx);
        }
        return null;
    }

    public int getOptionsCount() {
        return OPTIONS_TOTAL;
    }

    public int getQuestionCount() {
        return QUESTION_TOTAL;
    }

    public boolean isLastQuestionFinished() {
        return mCurQuestionIdx == getQuestionCount();
    }

    public int getCurType() {
        if (mTotalScore <= 12) {
            return 0;
        } else if (mTotalScore <= 20) {
            return 1;
        } else if (mTotalScore <= 29) {
            return 3;

        } else if (mTotalScore <= 37) {
            return 4;
        } else if (mTotalScore <= 44) {
            return 5;
        } else if (mTotalScore <= 50) {
            //NOTE it's true that it is the same as above
            return 5;
        }

        return 0;
    }

    public String getCurTypeString() {
        return mTypeMaps[getCurType()];
    }

    private void caculate() {
        mTotalScore += getScoreByOption(mCurOption);

    }

    private void saveIfNeeded() {
        if (isLastQuestionFinished()) {
            Context context = BoosterApplication.get();
            PreferenceUtils.saveInt(context, PreferenceUtils.KEY_SCORE, mTotalScore);
            PreferenceUtils.saveInt(context, PreferenceUtils.KEY_SUBMIT_STATUS, SUBMIT_STATUS_ONGING);
            EventBus.getDefault().post(new SubmitScoreEvent());
        }
    }

    private int getScoreByOption(int option) {
        if (option >= 0 && option < OPTIONS_TOTAL) {
            return mScoreMap[option];
        }
        return 0;
    }
}

