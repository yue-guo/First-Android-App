package com.example.yueguo.myhw9;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;


/**
 * Created by YueGuo on 16/11/24.
 */

public class MyFirstFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles = new String[]{"BY STATES","HOUSE","SENATE"};

    public MyFirstFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    /*public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
       //return super.getItemPosition(object);
    }*/

    public Fragment getItem(int position){
        if(position == 1){
            return new HouseFragment();
        }else if(position == 2){
            return new SenateFragment();
        }
        return new FirstFragment();
    }

    public int getCount(){
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
        //return super.getPageTitle(position);
    }
}
