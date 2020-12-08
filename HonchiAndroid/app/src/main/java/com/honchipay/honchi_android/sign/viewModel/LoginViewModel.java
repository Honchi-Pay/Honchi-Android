package com.honchipay.honchi_android.sign.viewModel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.data.LoginRepository;
import com.honchipay.honchi_android.sign.data.TokenResponseData;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    private final String TAG = LoginViewModel.class.getSimpleName();
    private final LoginRepository repository = new LoginRepository();
    public final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    public final ObservableField<String> inputUserId = new ObservableField<>();
    public final ObservableField<String> inputUserPW = new ObservableField<>();

    public void login() {
        DisposableSingleObserver<Response<TokenResponseData>> loginObserver = new CustomDisposableSingleObserver<Response<TokenResponseData>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<TokenResponseData> tokenResponseDataResponse) {
                if (tokenResponseDataResponse.isSuccessful() && tokenResponseDataResponse.code() == 200) {
                    TokenResponseData tokens = tokenResponseDataResponse.body();
                    SharedPreferencesManager sharedPreferences = SharedPreferencesManager.getInstance();

                    if (tokens != null) {
                        sharedPreferences.setAccessToken(tokens.getTokenType() + " " + tokens.getAccessToken());
                        sharedPreferences.setRefreshToken(tokens.getTokenType() + " " + tokens.getRefreshToken());
                    }

                    loginSuccess.postValue(true);
                }
            }
        };

        addDisposable(repository.doLogin(inputUserId.get(), inputUserPW.get(), loginObserver));
    }

    public void forgotPassword() {
        DisposableSingleObserver<Response<Void>> findUserObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> findPasswordResponse) {
                if (findPasswordResponse.isSuccessful() && findPasswordResponse.code() == 200) {
                    loginSuccess.postValue(true);
                }
            }
        };

        addDisposable(repository.findUserPassword(inputUserId.get(), inputUserPW.get(), findUserObserver));
    }
}