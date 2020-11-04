package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.sign.Data.SignUpRequest;
import com.honchipay.honchi_android.sign.Data.TokenResponseData;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface HonchipayApi {
    @POST("/auth")
    Single<Response<TokenResponseData>> tryDoLogin(@Body HashMap<String, String> body);

    @FormUrlEncoded
    @POST("/user/alone")
    Single<Response<Void>> checkFirstTimeUser(@Field("email") String email);

    @FormUrlEncoded
    @POST("/email/verify")
    Single<Response<Void>> checkDuplicatedEmail(@Field("email") String email);

    @PUT("/email/verify")
    Single<Response<Void>> checkAuthCode(@Body HashMap<String, String> body);

    @POST("/user")
    Single<Response<Void>> singUp(@Body SignUpRequest body);
}
