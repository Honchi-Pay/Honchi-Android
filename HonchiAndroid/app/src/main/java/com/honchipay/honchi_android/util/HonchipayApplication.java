package com.honchipay.honchi_android.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class HonchipayApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    static public Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.getInstance().init(getApplicationContext());
        context = getApplicationContext();
    }
}