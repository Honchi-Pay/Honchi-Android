package com.honchipay.honchi_android.sign.data;

import com.honchipay.honchi_android.base.BaseRepository;
import com.honchipay.honchi_android.network.HonchipayConnector;

import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class LoginRepository extends BaseRepository {
    public Disposable doLogin(String email, String password, DisposableSingleObserver<Response<TokenResponseData>> loginObserver) {
        HashMap<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().tryDoLogin(body), loginObserver);
    }

    public Disposable findUserPassword(String email, String password, DisposableSingleObserver<Response<Void>> findUserObserver) {
        HashMap<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        return wrappingSingle(HonchipayConnector.getInstance().getApi().findPassword(body), findUserObserver);
    }
}