package com.ojh.testproject.intro;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ojh.testproject.R;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {

    @Bind(R.id.introViewPager)
    ViewPager viewPager;

    @Bind(R.id.indicator)
    CirclePageIndicator indicator;

    IntroPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        mAdapter = new IntroPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        indicator.setViewPager(viewPager);

    }
}
