package com.honchipay.honchi_android.writing;

import com.honchipay.honchi_android.home.Data.newPost;
import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.Response;

public class writingRepository {
    public Single<Response<Void>> writing(HashMap hashMap) {
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().writing(token,hashMap);
    }

    public Single<Response<Void>> modifyPost(){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().modifyPost(token);
    }

    public Single<Response<Void>> deletePost(){
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().deletePost(token);
    }
}
