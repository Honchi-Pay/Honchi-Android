package com.honchipay.honchi_android.sign.data;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class SignRepository extends BaseRepository {
    String userEmail;

    public Disposable checkFirstTimeUser(String email, DisposableSingleObserver<Response<Void>> checkFirstObserver) {
        userEmail = email;
        return wrappingSingle(HonchipayConnector.getInstance().getApi().checkFirstTimeUser(email), checkFirstObserver);
    }

    public Disposable checkDuplicatedEmail(DisposableSingleObserver<Response<Void>> checkDuplicatedObserver) {
        return wrappingSingle(HonchipayConnector.getInstance().getApi().checkDuplicatedEmail(userEmail), checkDuplicatedObserver);
    }

    public Disposable checkAuthCode(String code, DisposableSingleObserver<Response<Void>> checkAuthCodeObserver) {
        HashMap<String, String> body = new HashMap<>();
        body.put("email", userEmail);
        body.put("code", code);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().checkAuthCode(body), checkAuthCodeObserver);
    }

    public Single<Response<Void>> signUp(SignUpRequest signUpRequest) {
        return HonchipayConnector.getInstance().getApi().singUp(signUpRequest);
    }
}
