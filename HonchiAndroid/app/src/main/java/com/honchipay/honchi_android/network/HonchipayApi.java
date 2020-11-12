package com.honchipay.honchi_android.network;

import com.honchipay.honchi_android.buyList.Model.DetailBuyList;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface HonchipayApi {
    @GET("/post/buyList")
    Single<Response<DetailBuyList>> getBuyList(@Field("Authorization") String authorization);
}