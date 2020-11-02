package com.honchipay.honchi_android.sign.Data;

import com.honchipay.honchi_android.network.HonchipayConnector;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class SignRepository {
    public Single<Response<Void>> checkFirstTimeUser(String email) {
        return HonchipayConnector.getInstance().getApi().checkFirstTimeUser(email);
    }

    public Single<Response<Void>> checkDuplicatedEmail(String email) {
        return HonchipayConnector.getInstance().getApi().checkDuplicatedEmail(email);
    }

    public Single<Response<Void>> checkAuthCode(String email, String code) {
        HashMap<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("code", code);

        return HonchipayConnector.getInstance().getApi().checkAuthCode(body);
    }
}
