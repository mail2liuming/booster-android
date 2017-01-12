package booster.mingliu.boostertest.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuming on 17/1/12.
 */
public class InvestorTypeFragment extends BasicFragment {
    public static final String INVESTOR_TYPE_TAG = "investor_type";

    @BindView(R.id.piechart)
    PieChart mPieChart;

    public static InvestorTypeFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(INVESTOR_TYPE_TAG,type);
        InvestorTypeFragment fragment = new InvestorTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investortype,container,false);
        ButterKnife.bind(this, view);
//        mPieChart = (PieChart)view.findViewById(R.id.piechart);
        setupPieChart();
        return view;
    }

    private void setupPieChart(){
        List<String> xValues = new ArrayList<String>();
        List<PieEntry> yValues = new ArrayList<PieEntry>();
        List<Integer> colors = new ArrayList<Integer>();

        xValues.add("new");
        xValues.add("load");
        xValues.add("old");
        xValues.add("none");

        yValues.add(new PieEntry(15, "new"));
        yValues.add(new PieEntry(15, "load"));
        yValues.add(new PieEntry(25, "old"));
        yValues.add(new PieEntry(45, "none"));

        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        PieDataSet pieDataSet = new PieDataSet(yValues, "Quarterly Revenue 2014");
        pieDataSet.setSliceSpace(0f);

        pieDataSet.setColors(colors);
        PieData pieData = new PieData( pieDataSet);

        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(11f);
        pieData.setValueTextColor(Color.WHITE);
        mPieChart.setData(pieData);
        mPieChart.setRotationEnabled(false);
    }
}
