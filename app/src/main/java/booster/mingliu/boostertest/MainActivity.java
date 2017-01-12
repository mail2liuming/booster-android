package booster.mingliu.boostertest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import booster.mingliu.boostertest.basic.BasicActivity;
import booster.mingliu.boostertest.common.utils.FragmentManagerUtil;
import booster.mingliu.boostertest.fragments.FundTypesFragment;
import booster.mingliu.boostertest.fragments.QuestionFragment;
import booster.mingliu.boostertest.models.QuestionModelManager;

public class MainActivity extends BasicActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            FragmentManagerUtil.addFragment(this, QuestionFragment.newInstance(),"", FragmentManagerUtil.Animation.NONE);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showInvestorType(int type){
        this.getSupportFragmentManager().popBackStack();
        FragmentManagerUtil.addFragment(this, FundTypesFragment.newInstance(type),FundTypesFragment.TAG, FragmentManagerUtil.Animation.NONE);
    }
}
