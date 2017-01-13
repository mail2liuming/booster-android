package booster.mingliu.boostertest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import booster.mingliu.boostertest.R;
import booster.mingliu.boostertest.basic.BasicFragment;

/**
 * Created by liuming on 17/1/12.
 */
public class FundTypesFragment extends BasicFragment {
    public static final String TAG = "FundTypeFragment";
    private static final String ENTRY_TYPE = "EntryType";

    private int mEntryType = 0;

    public static FundTypesFragment newInstance(int entryType) {
        Bundle args = new Bundle();
        args.putInt(ENTRY_TYPE, entryType);
        FundTypesFragment fragment = new FundTypesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = this.getArguments();
        if (args != null) {
            mEntryType = args.getInt(ENTRY_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fundtype, container, false);
        setupViewPager(view);
        return view;
    }

    private void setupViewPager(View containerView) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) containerView.findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getChildFragmentManager());
        viewPager.setAdapter(adapter);

        Log.d("FundTypesFragment", "" + mEntryType);
        if (mEntryType > 0) {
            viewPager.setCurrentItem(mEntryType - 1);
        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return InvestorTypeFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}
