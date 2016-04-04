package com.ojh.testproject.manager;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ojh.testproject.common.MyApplication;

import java.util.HashSet;

public class PropertyManager {

    SharedPreferences mPref;
    SharedPreferences.Editor mEditor;

    public static final String KEY_TOKEN = "key_token";
    public static final String KEY_ID = "key_id";
    public static final String KEY_EMAIL = "key_email";

    public static final String KEY_COOKIE = "key_cookie";

    private PropertyManager(){
        mPref = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPref.edit();
    }
    // singleton holder pattern : thread safe, lazy class initialization, memory saving.
    public static class InstanceHolder{ private static final PropertyManager INSTANCE = new PropertyManager();}
    public static PropertyManager getInstance(){ return InstanceHolder.INSTANCE; }

    //setter
    public void setToken(String token) {
        mEditor.putString(KEY_TOKEN, token);
        mEditor.commit();
    }

    public void setEmail(String email){
        mEditor.putString(KEY_EMAIL, email);
        mEditor.commit();
    }

    public void setId(String id){
        mEditor.putString(KEY_ID, id);
        mEditor.commit();
    }

    public void setCookie(HashSet cookie){
        mEditor.putStringSet(KEY_COOKIE, cookie);
        mEditor.commit();
    }

    //getter
    public String getToken() { return mPref.getString(KEY_TOKEN, ""); }

    public String getEmail(){
        return mPref.getString(KEY_EMAIL, "");
    }

    public String getId(){
        return mPref.getString(KEY_ID, "");
    }

    public HashSet getCookie(){
        return (HashSet)mPref.getStringSet(KEY_COOKIE, new HashSet());
    }
}