package com.honchipay.honchi_android.network;

import android.content.Intent;
import android.util.Log;

import com.honchipay.honchi_android.sign.Data.TokenResponseData;
import com.honchipay.honchi_android.sign.SignActivity;
import com.honchipay.honchi_android.util.HonchipayApplication;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenAuthenticator implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response mainResponse = chain.proceed(chain.request());

        if (mainResponse.code() == 401) {
            useRefreshToken();
            moveToSplash();
        }

        return mainResponse;
    }

    private void useRefreshToken() {
        String refreshToken = SharedPreferencesManager.getInstance().getRefreshToken();
        Single<retrofit2.Response<TokenResponseData>> tokens = HonchipayConnector.getInstance().getApi().renewToken(refreshToken);

        tokens.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<retrofit2.Response<TokenResponseData>>() {
                    @Override
                    public void onSuccess(@NonNull retrofit2.Response<TokenResponseData> tokenResponse) {
                        if (tokenResponse.isSuccessful() && tokenResponse.code() == 200) {
                            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                            String tokenType = tokenResponse.body().getTokenType();

                            sharedPreferencesManager.setAccessToken(tokenType + " " + tokenResponse.body().getAccessToken());
                            sharedPreferencesManager.setRefreshToken(tokenType + " " + tokenResponse.body().getAccessToken());
                        } else {
                            Log.e("EditProfileViewModel", "");
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("EditProfileViewModel", e.getMessage());
                    }
                });
    }

    private void moveToSplash() {
        SharedPreferencesManager.getInstance().setIsLogin(false);
        Intent intent = new Intent(HonchipayApplication.context, SignActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("splash", "login");
        HonchipayApplication.context.startActivity(intent);
    }
}
