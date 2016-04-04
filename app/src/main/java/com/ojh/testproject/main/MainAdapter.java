package com.ojh.testproject.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by INNO-09 on 2016-04-04.
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    private final static int NUM_OF_PAGE= 3;

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment resultFragment = null;

        switch (position) {
            case 0:
                resultFragment = HomeFragment.newInstance();
                break;
            case 1:
                resultFragment = WriteFragment.newInstance();
                break;
            case 2:
                resultFragment = ExtraFragment.newInstance();
                break;
        }

        return resultFragment;
    }

    @Override
    public int getCount() {
        return NUM_OF_PAGE;
    }
}
