package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.sign.Data.TokenResponseData;
import com.honchipay.honchi_android.sign.Data.User;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HonchipayApi {
    @POST("/auth")
    Single<Response<TokenResponseData>> tryDoLogin(@Body HashMap<String, String> body);


}
