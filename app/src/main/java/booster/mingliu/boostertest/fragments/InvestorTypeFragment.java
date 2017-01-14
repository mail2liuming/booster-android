package booster.mingliu.boostertest.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicFragment;
import booster.mingliu.boostertest.models.FundTypeModelManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuming on 17/1/12.
 */
public class InvestorTypeFragment extends BasicFragment {
    public static final String INVESTOR_TYPE_TAG = "investor_type";

    @BindView(R.id.piechart)
    PieChart mPieChart;

    @BindView(R.id.piechart_title)
    TextView mPieTitle;

    @BindView(R.id.pie_description_text)
    TextView mPieText;

    private int mType = -1;

    public static InvestorTypeFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(INVESTOR_TYPE_TAG, type);
        InvestorTypeFragment fragment = new InvestorTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            mType = args.getInt(INVESTOR_TYPE_TAG, -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investortype, container, false);
        ButterKnife.bind(this, view);

        Log.d("InvestorTypeFragment",""+mType);
        if (mType >= 0) {
            mPieTitle.setText(FundTypeModelManager.getsInstance().getFundTypeTitle(mType));
            mPieText.setText(FundTypeModelManager.getsInstance().getFundTypeContent(mType));
            setupPieChart();
        }
        return view;
    }

    private void setupPieChart() {

        List<PieEntry> values = new ArrayList<PieEntry>();
        List<Integer> colors = new ArrayList<Integer>();

        String[] pieWords = FundTypeModelManager.getsInstance().getFundTypesWords(mType);
        int[] pieColors = FundTypeModelManager.getsInstance().getFundTypesColors(mType);
        int[] piePercents = FundTypeModelManager.getsInstance().getFundTypesPercents(mType);

        for (int i = 0; i < pieWords.length; i++) {
            values.add(new PieEntry(piePercents[i], pieWords[i]));
            colors.add(pieColors[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(values, getString(R.string.target_investment_mix));
        pieDataSet.setSliceSpace(0f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLineVariableLength(true);


        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);

        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);

        pieData.setValueTextColors(colors);

        mPieChart.setData(pieData);
        //Disable the rotate of the pie
        mPieChart.setRotationEnabled(false);
        //Set the label appearance
        mPieChart.setEntryLabelColor(Color.BLACK);
        mPieChart.setEntryLabelTextSize(8f);
        mPieChart.getDescription().setEnabled(false);
    }
}
