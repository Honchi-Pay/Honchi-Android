package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.sign.Data.User;

import java.util.List;
import java.util.Observable;

import retrofit2.http.POST;

public interface HonchipayApi {
    @POST("user")
    Observable<List<User>> signUp();
}
