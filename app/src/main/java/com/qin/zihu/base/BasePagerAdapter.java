package com.qin.zihu.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Qin on 2017/3/16.
 */

public class BasePagerAdapter extends FragmentPagerAdapter {
    private String[] mTabTitles;
    private Fragment[] mFragments;

    public BasePagerAdapter(FragmentManager fm, String[] tabTitles, Fragment[] fragments) {
        super(fm);
        this.mTabTitles = tabTitles;
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (position > getCount() - 1) {
            return mFragments[0];
        }
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
