package com.example.yueguo.myhw9;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by YueGuo on 16/11/26.
 */

public class BillFragmentPagerAdapter extends FragmentStatePagerAdapter{
    private String[] mTitles = new String[]{"ACTIVE BILLS","NEW BILLS"};

    public BillFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public Fragment getItem(int position){
        if(position == 1){
            return new NewBillFragment();
        }else{
            return new ActiveBillFragment();
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
