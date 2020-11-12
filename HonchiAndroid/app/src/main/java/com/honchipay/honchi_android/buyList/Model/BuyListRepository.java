package com.honchipay.honchi_android.buyList.Model;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import io.reactivex.Single;
import retrofit2.Response;

public class BuyListRepository {
    String token = SharedPreferencesManager.getInstance().getAccessToken();

    public Single<Response<DetailBuyList>> getBuyList() {
        return HonchipayConnector.getInstance().getApi().getBuyList(token);
    }

}