package com.honchipay.honchi_android.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

@SuppressLint("StaticFieldLeak")
public class HonchipayApplication extends Application {
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    static HonchipayApplication instance = null;
    static Context context = instance;
}
