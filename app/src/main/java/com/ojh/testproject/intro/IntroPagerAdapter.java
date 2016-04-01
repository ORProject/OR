package com.ojh.testproject.intro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by INNO-09 on 2016-04-01.
 */
public class IntroPagerAdapter extends FragmentStatePagerAdapter {

    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return IntroFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return IntroFragment.IMG_INTRO.length;
    }
}
