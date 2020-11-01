package com.honchipay.honchi_android.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private final String MY_APP_PREFERENCES = "Honchipay-Android";
    private final String IS_LOGIN = "isLogin";
    private final String SAVE_TOKEN = "accessToken";
    private final String SAVE_REFRESH = "refreshToken";
    private static SharedPreferencesManager instance = null;

    static SharedPreferencesManager getInstance() {
        if (instance == null) instance = new SharedPreferencesManager();
        return instance;
    }

    private final SharedPreferences sharedPrefs = HonchipayApplication.context.getSharedPreferences(
            MY_APP_PREFERENCES,
            Context.MODE_PRIVATE
    );

    String getAccessToken() {
        return sharedPrefs.getString(SAVE_TOKEN, null);
    }

    void setAccessToken(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SAVE_TOKEN, value);
        editor.apply();
    }

    String getRefreshToken() {
        return sharedPrefs.getString(SAVE_REFRESH, null);
    }

    void setRefreshToken(String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SAVE_REFRESH, value);
        editor.apply();
    }

    Boolean getIsLogin() {
        return sharedPrefs.getBoolean(IS_LOGIN, false);
    }

    void setIsLogin(Boolean value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_LOGIN, value);
        editor.apply();
    }
}
