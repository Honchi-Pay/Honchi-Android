package com.honchipay.honchi_android.network;

import android.content.Intent;

import com.honchipay.honchi_android.sign.SignActivity;
import com.honchipay.honchi_android.util.HonchipayApplication;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenAuthenticator implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response mainResponse = chain.proceed(chain.request());

        if (mainResponse.code() == 401) {
            SharedPreferencesManager.getInstance().setIsLogin(false);
            SharedPreferencesManager.getInstance().setAccessToken(null);
            Intent intent = new Intent(HonchipayApplication.context, SignActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("splash", "login");
            HonchipayApplication.context.startActivity(intent);
        }

        return mainResponse;
    }
}
