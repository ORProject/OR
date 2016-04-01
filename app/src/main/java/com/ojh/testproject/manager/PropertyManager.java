package com.ojh.testproject.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ojh.testproject.common.MyApplication;

import java.util.HashSet;

public class PropertyManager {

    SharedPreferences mPref;
    SharedPreferences.Editor mEditor;

    public static final String KEY_ID = "key_id";
    public static final String KEY_PASSWORD = "key_password";
    public static final String KEY_COOKIE = "kie_cookie";

    private PropertyManager(){
        mPref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPref.edit();
    }
    // singleton holder pattern : thread safe, lazy class initialization, memory saving.
    public static class InstanceHolder{ private static final PropertyManager INSTANCE = new PropertyManager();}
    public static PropertyManager getInstance(){ return InstanceHolder.INSTANCE; }

    //setter
    public void setId(String id){
        mEditor.putString(KEY_ID, id);
        mEditor.commit();
    }

    public void setPassword(String password){
        mEditor.putString(KEY_PASSWORD, password);
        mEditor.commit();
    }

    public void setCookie(HashSet cookie){
        mEditor.putStringSet(KEY_COOKIE, cookie);
        mEditor.commit();
    }

    //getter
    public String getId(){
        return mPref.getString(KEY_ID, "");
    }

    public String getPassword(){
        return mPref.getString(KEY_PASSWORD, "");
    }

    public HashSet getCookie(){
        return (HashSet)mPref.getStringSet(KEY_COOKIE, new HashSet());
    }
}