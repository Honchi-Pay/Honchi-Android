package com.honchipay.honchi_android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesManager {
    private static final String MY_APP_PREFERENCES = "Honchipay-Android";
    private final String IS_LOGIN = "isLogin";
    private final String SAVE_TOKEN = "accessToken";
    private final String SAVE_REFRESH = "refreshToken";
    private final String SAVE_USER_NAME = "userNickName";
    private static SharedPreferencesManager instance = null;
    private SharedPreferences sharedPrefs;

    static public SharedPreferencesManager getInstance() {
        if (instance == null) instance = new SharedPreferencesManager();
        return instance;
    }

    public void init(Context context) {
        sharedPrefs = context.getSharedPreferences(MY_APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public String getAccessToken() {
        return sharedPrefs.getString(SAVE_TOKEN, "");
    }

    public void setAccessToken(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SAVE_TOKEN, value);
        editor.apply();
    }

    public String getRefreshToken() {
        return sharedPrefs.getString(SAVE_REFRESH, "");
    }

    public void setRefreshToken(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SAVE_REFRESH, value);
        editor.apply();
    }

    public Boolean getIsLogin() {
        return sharedPrefs.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(Boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_LOGIN, value);
        editor.apply();
    }

    public String getUserName() { return sharedPrefs.getString(SAVE_USER_NAME, ""); }

    public void setUserName(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SAVE_REFRESH, value);
        editor.apply();
    }
}
