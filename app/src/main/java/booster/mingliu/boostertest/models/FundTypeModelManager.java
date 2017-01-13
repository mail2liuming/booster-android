package booster.mingliu.boostertest.models;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import booster.mingliu.boostertest.BoosterApplication;
import booster.mingliu.boostertest.R;

/**
 * Created by liuming on 17/1/12.
 */
public class FundTypeModelManager {
    private static final int MAX_FUND_TYPES = 6;

    private String[] mFundTypesTitle;
    private String[] mFundTypesContent;
    private List<String[]> mFundTypePieWords = new ArrayList<>();
    private List<int[]> mFundTypePiePercents = new ArrayList<>();
    private List<int[]> mFundTypePieColors = new ArrayList<>();
    private List<int[]> mFundTypePieGrowths = new ArrayList<>();

    private static FundTypeModelManager sInstance = new FundTypeModelManager();

    public static FundTypeModelManager getsInstance() {
        return sInstance;
    }

    private FundTypeModelManager() {
    }

    public void load() {
        Context context = BoosterApplication.get();
        Resources res = context.getResources();

        mFundTypesTitle = res.getStringArray(R.array.fund_types);
        mFundTypesContent = res.getStringArray(R.array.fund_types_object);

        mFundTypePieWords.add(res.getStringArray(R.array.pie_words_capital_fund));
        mFundTypePieWords.add(res.getStringArray(R.array.pie_words_default_fund));
        mFundTypePieWords.add(res.getStringArray(R.array.pie_words_moderate_fund));
        mFundTypePieWords.add(res.getStringArray(R.array.pie_words_balance_fund));
        mFundTypePieWords.add(res.getStringArray(R.array.pie_words_balance_growth_fund));
        mFundTypePieWords.add(res.getStringArray(R.array.pie_words_high_growth_fund));

        mFundTypePiePercents.add(res.getIntArray(R.array.pie_percents_capital_fund));
        mFundTypePiePercents.add(res.getIntArray(R.array.pie_percents_default_fund));
        mFundTypePiePercents.add(res.getIntArray(R.array.pie_percents_moderate_fund));
        mFundTypePiePercents.add(res.getIntArray(R.array.pie_percents_balance_fund));
        mFundTypePiePercents.add(res.getIntArray(R.array.pie_percents_balance_growth_fund));
        mFundTypePiePercents.add(res.getIntArray(R.array.pie_percents_high_growth_fund));

        mFundTypePieColors.add(res.getIntArray(R.array.pie_colors_capital_fund));
        mFundTypePieColors.add(res.getIntArray(R.array.pie_colors_default_fund));
        mFundTypePieColors.add(res.getIntArray(R.array.pie_colors_moderate_fund));
        mFundTypePieColors.add(res.getIntArray(R.array.pie_colors_balance_fund));
        mFundTypePieColors.add(res.getIntArray(R.array.pie_colors_balance_growth_fund));
        mFundTypePieColors.add(res.getIntArray(R.array.pie_colors_high_growth_fund));

        mFundTypePieGrowths.add(res.getIntArray(R.array.pie_growth_capital_fund));
        mFundTypePieGrowths.add(res.getIntArray(R.array.pie_growth_default_fund));
        mFundTypePieGrowths.add(res.getIntArray(R.array.pie_growth_moderate_fund));
        mFundTypePieGrowths.add(res.getIntArray(R.array.pie_growth_balance_fund));
        mFundTypePieGrowths.add(res.getIntArray(R.array.pie_growth_balance_growth_fund));
        mFundTypePieGrowths.add(res.getIntArray(R.array.pie_growth_high_growth_fund));
    }

    private boolean checkIdx(int type) {
        return type >= 0 && type < MAX_FUND_TYPES;
    }

    public String getFundTypeTitle(int type) {
        if (checkIdx(type)) {
            return mFundTypesTitle[type];
        }
        return null;
    }

    public String getFundTypeContent(int type) {
        if (checkIdx(type)) {
            return mFundTypesContent[type];
        }
        return null;
    }

    public String[] getFundTypesWords(int type) {
        if (checkIdx(type)) {
            return mFundTypePieWords.get(type);
        }
        return null;
    }

    public int[] getFundTypesColors(int type) {
        if (checkIdx(type)) {
            return mFundTypePieColors.get(type);
        }
        return null;
    }

    public int[] getFundTypesPercents(int type) {
        if (checkIdx(type)) {
            return mFundTypePiePercents.get(type);
        }
        return null;
    }

    public int[] getFundTypesGrowths(int type) {
        if (checkIdx(type)) {
            return mFundTypePieGrowths.get(type);
        }
        return null;
    }
}

