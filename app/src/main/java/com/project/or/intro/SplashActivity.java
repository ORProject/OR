package com.project.or.intro;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.project.or.R;
import com.project.or.main.MainActivity;
import com.project.or.manager.PropertyManager;

public class SplashActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        doRealStart();

    }

    private void doRealStart() {

        if(!TextUtils.isEmpty(PropertyManager.getInstance().getToken())) {
//            mHandler.postDelayed(() -> moveToIntro(), 3000);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveToMain();
                }
            },2000);
        } else {
//            mHandler.postDelayed(()->moveToMain(),3000);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveToIntro();
                }
            },2000);
        }
    }


    private void moveToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private void moveToIntro() {
        startActivity(new Intent(SplashActivity.this, IntroActivity.class));
        finish();
    }
}
