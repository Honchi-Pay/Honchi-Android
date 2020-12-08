package com.honchipay.honchi_android.writing;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.util.HashMap;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class writingRepository {
    public Single<Response<Void>> writing(
            MultipartBody.Part titlePart,
            MultipartBody.Part contentPart,
            MultipartBody.Part imagesPart,
            MultipartBody.Part categoryPart,
            MultipartBody.Part itemPart,
            MultipartBody.Part latPart,
            MultipartBody.Part lonPart
            ) {
        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().writing(token,titlePart, contentPart,imagesPart,categoryPart,itemPart,latPart,lonPart);
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
