package com.honchipay.honchi_android.buyList;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public class BuyListRepository {
    public Single<Response<List<BuyListResponse>>> getBuyList(){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getBuyList(token);
    }
}
