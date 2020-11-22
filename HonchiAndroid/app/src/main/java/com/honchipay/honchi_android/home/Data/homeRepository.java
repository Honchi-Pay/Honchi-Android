package com.honchipay.honchi_android.home.Data;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import io.reactivex.Single;
import retrofit2.Response;

public class homeRepository {

    public Single<Response<newPost>> getNewPost(String category){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getNewPost(token, category);
    }

    public Single<Response<getPost>> getPostList(String category,String item, int dist){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().getPostList(token, category, item, dist);
    }
}