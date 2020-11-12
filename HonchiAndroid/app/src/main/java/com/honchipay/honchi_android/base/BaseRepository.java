package com.honchipay.honchi_android.base;

import com.honchipay.honchi_android.util.SharedPreferencesManager;

public class BaseRepository {
    public String token = SharedPreferencesManager.getInstance().getAccessToken();
}
