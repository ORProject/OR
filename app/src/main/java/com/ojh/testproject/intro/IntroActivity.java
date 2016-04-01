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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == IntroFragment.IMG_INTRO.length - 1) {
                    Toast.makeText(getApplicationContext(), "끝", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        indicator.setViewPager(viewPager);

    }
}
