package com.ojh.testproject.common;

import android.app.Application;
import android.content.Context;

import butterknife.ButterKnife;

/**
 * Created by INNO-09 on 2016-04-01.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }

    public static Context getContext() {
        return mContext;
    }


}
