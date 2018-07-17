package com.example.yueguo.myhw9;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.*;
import java.util.*;
import android.content.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabs;
    private ViewPager viewPager;
    private List<String> mTitle = new ArrayList<String>();
    private List<Fragment> mFragment = new ArrayList<Fragment>();

    /*private ClipData.Item legis;
    private ClipData.Item bills;
    private ClipData.Item comm;
    private ClipData.Item fav;*/
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    //private TabLayout mTabLayout1;
    //private ViewPager mViewPager1;
    private MyFirstFragmentPagerAdapter myFirstFragmentPagerAdapter;
    private BillFragmentPagerAdapter billFragmentPagerAdapter;
    private CommFragmentPagerAdapter commFragmentPagerAdapter;
    private FavFragmentPagerAdapter favFragmentPagerAdapter;

    /*private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        /*small mail*/
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //getActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_legis) {
            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
            toolbar1.setTitle("Legislators");
            initViews();
            // Handle the camera action
        } else if (id == R.id.nav_bills) {
            initBillViews();
            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
            toolbar1.setTitle("Bills");
            //Log.d("guoyue","111");



        } else if (id == R.id.nav_comm) {
            intiCommViews();
            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
            toolbar1.setTitle("Committees");

        } else if (id == R.id.nav_fav) {
            initFavViews();
            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
            toolbar1.setTitle("Favorites");

        } else if (id == R.id.nav_me) {
            Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
            toolbar1.setTitle("About Me");
            Intent intent = new Intent(this,AboutMeActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initViews(){
        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        myFirstFragmentPagerAdapter = new MyFirstFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFirstFragmentPagerAdapter);
        //Log.d("guoyue",String.valueOf(myFirstFragmentPagerAdapter));

        //将TabLayout和ViewPager绑定在一起

        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        /*one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);*/

    }

    private void initBillViews(){
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        billFragmentPagerAdapter = new BillFragmentPagerAdapter(getSupportFragmentManager());
        //billFragmentPagerAdapter.destroyItem();
        mViewPager.setAdapter(billFragmentPagerAdapter);


        //将TabLayout和ViewPager绑定在一起
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void intiCommViews(){
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        commFragmentPagerAdapter = new CommFragmentPagerAdapter(getSupportFragmentManager());
        //billFragmentPagerAdapter.destroyItem();
        mViewPager.setAdapter(commFragmentPagerAdapter);


        //将TabLayout和ViewPager绑定在一起
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initFavViews(){
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        favFragmentPagerAdapter = new FavFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(favFragmentPagerAdapter);

        mTabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

    }


}
