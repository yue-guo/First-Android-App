package com.example.yueguo.myhw9;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by YueGuo on 16/11/28.
 */

public class CommFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles = new String[]{"HOUSE","SENATE","JOINT"};

    public CommFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        if(position == 1){
            return new CommSenateFragment();
        }else if(position == 2){
            return new CommJointFragment();
        }else{
            return new CommHouseFragment();
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
