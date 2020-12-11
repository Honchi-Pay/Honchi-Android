package com.honchipay.honchi_android.writing;

import com.honchipay.honchi_android.network.HonchipayConnector;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class writingRepository {

    public Single<Response<Void>> writing(String title, String content, File image, String category, String item, int lat, int lan) {
        HashMap<String, RequestBody> requestHashMap = new HashMap<>();

        requestHashMap.put("title",RequestBody.create(title, MediaType.parse("multipart/form-data")));
        requestHashMap.put("content",RequestBody.create(content, MediaType.parse("multipart/form-data")));
        requestHashMap.put("image",RequestBody.create(image, MediaType.parse("multipart/form-data")));
        requestHashMap.put("category",RequestBody.create(category, MediaType.parse("multipart/form-data")));
        requestHashMap.put("item",RequestBody.create(item, MediaType.parse("multipart/form-data")));
        requestHashMap.put("lat",RequestBody.create(String.valueOf(lat), MediaType.parse("multipart/form-data")));
        requestHashMap.put("lan",RequestBody.create(String.valueOf(lan), MediaType.parse("multipart/form-data")));

        String token = SharedPreferencesManager.getInstance().getAccessToken();
        return HonchipayConnector.getInstance().getApi().writing(token,requestHashMap);
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
