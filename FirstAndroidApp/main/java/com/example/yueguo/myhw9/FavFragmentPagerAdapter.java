package com.example.yueguo.myhw9;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by YueGuo on 16/11/29.
 */

public class FavFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles = new String[]{"LEGISLATORS","BILLS","COMMITTEES"};

    public FavFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        if(position == 1){
            return new FavBillFragment();
        }else if(position == 2){
            return new FavCommFragment();
        }else{
            return new FavLegisFragment();
        }
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
