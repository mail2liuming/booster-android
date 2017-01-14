package booster.mingliu.boostertest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import booster.mingliu.boostertest.basic.BasicActivity;
import booster.mingliu.boostertest.common.utils.FragmentManagerUtil;
import booster.mingliu.boostertest.common.utils.PreferenceUtils;
import booster.mingliu.boostertest.events.SubmitScoreEvent;
import booster.mingliu.boostertest.fragments.FundTypesFragment;
import booster.mingliu.boostertest.fragments.QuestionFragment;
import booster.mingliu.boostertest.fragments.SubmitFragment;
import booster.mingliu.boostertest.models.QuestionModelManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BasicActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_content)
    TextView mMainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.booster_logo);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        ButterKnife.bind(this);
        mNavigationView.setNavigationItemSelectedListener(this);

        updateSubmitMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SubmitScoreEvent event){
        updateSubmitMenu();
    }

    private void updateSubmitMenu(){
        int status = PreferenceUtils.getInt(this, PreferenceUtils.KEY_SUBMIT_STATUS,QuestionModelManager.SUBMIT_STATUS_NONE);
        MenuItem menuItem = mNavigationView.getMenu().findItem(R.id.nav_send);
        if(QuestionModelManager.SUBMIT_STATUS_NONE==status){
            menuItem.setEnabled(false);
            menuItem.setTitle(R.string.submit);
        }
        else if(QuestionModelManager.SUBMIT_STATUS_ONGING==status){
            menuItem.setEnabled(true);
            menuItem.setTitle(R.string.submit);
        }else if(QuestionModelManager.SUBMIT_STATUS_DONE == status){
            menuItem.setEnabled(false);
            menuItem.setTitle(R.string.submitted);


            mMainContent.setText(getString(R.string.submit_message));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_defensive) {
            showInvestorType(1);
        } else if (id == R.id.nav_conservative) {
            showInvestorType(2);
        } else if (id == R.id.nav_balanced) {
            showInvestorType(3);
        } else if (id == R.id.nav_balanced_growth) {
            showInvestorType(4);
        } else if (id == R.id.nav_growth) {
            showInvestorType(5);
        } else if (id == R.id.nav_aggressive_growth) {
            showInvestorType(6);
        } else if (id == R.id.nav_share) {
            this.getSupportFragmentManager().popBackStack();
            QuestionModelManager.getsInstance().reset();
            FragmentManagerUtil.addFragmentAndAddToBackStack(this, QuestionFragment.newInstance(), "", FragmentManagerUtil.Animation.NONE);
        } else if (id == R.id.nav_send) {
            this.getSupportFragmentManager().popBackStack();
            FragmentManagerUtil.addFragmentAndAddToBackStack(this, SubmitFragment.newInstance(), "", FragmentManagerUtil.Animation.NONE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showInvestorType(int type){
        this.getSupportFragmentManager().popBackStack();
        FragmentManagerUtil.addFragmentAndAddToBackStack(this, FundTypesFragment.newInstance(type), FundTypesFragment.TAG, FragmentManagerUtil.Animation.NONE);
    }
}
