package booster.mingliu.boostertest.models;

import java.util.List;

/**
 * Created by liuming on 17/1/12.
 */
public class QuestionModelManager {
    public static final int SUBMIT_STATUS_NONE = 0;
    public static final int SUBMIT_STATUS_ONGING = 1;
    public static final int SUBMIT_STATUS_DONE = 2;

    public static final int OPTIONS_TOTAL=5;

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

    public void reset() {
        mCurOption = -1;
        mTotalScore = 0;
        mCurQuestionIdx = 0;
    }

    public int getScore(){
        return mTotalScore;
    }

    public void setCurOption(int option) {

    }

    public int getCurOption() {
        return mCurOption;
    }

    public  int getCurQuestionIdx(){
        return mCurQuestionIdx;
    }

    public void setCurQuestionIdx(int idx){
        mCurQuestionIdx = idx;
    }

    public void nextQuestion() {
        mCurOption = -1;
    }

    public String getQuestion() {
        return null;
    }

    public List<String> getOptions() {
        return null;
    }

    public int getOptionsCount(){
        return OPTIONS_TOTAL;
    }

    public int getQuestionCount(){
        return 6;
    }

    public boolean isLastQuestion(){
        return mCurQuestionIdx == getQuestionCount()-1;
    }

    public int getCurType(){
        return 0;
    }
}

