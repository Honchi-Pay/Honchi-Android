package com.honchipay.honchi_android.sign.viewModel;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.sign.data.Gender;
import com.honchipay.honchi_android.sign.data.SignRepository;
import com.honchipay.honchi_android.sign.data.SignUpProcess;
import com.honchipay.honchi_android.sign.data.SignUpRequest;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

import static com.honchipay.honchi_android.sign.data.SignUpProcess.EMAIL;

public class SignUpViewModel extends BaseViewModel {
    private final String TAG = SignUpViewModel.class.getSimpleName();
    private final SignRepository repository = new SignRepository();
    public final MutableLiveData<SignUpProcess> haveToNextPageLiveData = new MutableLiveData<>();
    public final ObservableField<String> inputEmail = new ObservableField<>();
    public final ObservableField<String> inputAuthCode = new ObservableField<>();
    public final ObservableField<String> inputPassword = new ObservableField<>();
    public final ObservableField<String> inputPhoneNumber = new ObservableField<>();
    public final ObservableField<String> inputNickName = new ObservableField<>();
    public Location location;
    public Gender gender;

    public void checkFirstUser() {
        DisposableSingleObserver<Response<Void>> checkFirstUserObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@io.reactivex.annotations.NonNull Response<Void> voidResponse) {
                if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                    haveToNextPageLiveData.postValue(EMAIL);
                } else {
                    haveToNextPageLiveData.postValue(SignUpProcess.FIRST);
                }
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                super.onError(e);
                haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
            }
        };

        addDisposable(repository.checkFirstTimeUser(inputEmail.get(), checkFirstUserObserver));
    }

    public void checkDuplicatedEmail() {
        DisposableSingleObserver<Response<Void>> checkDuplicatedEmailObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> voidResponse) {
                if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                    haveToNextPageLiveData.postValue(EMAIL);
                } else {
                    haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
                }
            }

            @Override
            public void onError(@NotNull Throwable e) {
                super.onError(e);
                haveToNextPageLiveData.postValue(SignUpProcess.REJECT);
            }
        };

        addDisposable(repository.checkDuplicatedEmail(checkDuplicatedEmailObserver));
    }

    public void checkAuthCode() {
        DisposableSingleObserver<Response<Void>> checkAuthCodeObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> voidResponse) {
                if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                    haveToNextPageLiveData.postValue(SignUpProcess.CODE);
                }
            }
        };

        addDisposable(repository.checkAuthCode(inputAuthCode.get(), checkAuthCodeObserver));
    }

    public void signUp() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail(inputEmail.get());
        signUpRequest.setNickName(inputNickName.get());
        signUpRequest.setPassword(inputPassword.get());
        signUpRequest.setPhoneNumber(makePhoneNumber(inputPhoneNumber.get()));
        signUpRequest.setLon(location.getLongitude());
        signUpRequest.setLat(location.getLatitude());
        signUpRequest.setSex(gender);

        DisposableSingleObserver<Response<Void>> signUpObserver = new CustomDisposableSingleObserver<Response<Void>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<Void> voidResponse) {
                if (voidResponse.isSuccessful() && voidResponse.code() == 200) {
                    haveToNextPageLiveData.postValue(SignUpProcess.SIGN_UP);
                    SharedPreferencesManager.getInstance().setUserName(signUpRequest.getNickName());
                }
            }
        };

        addDisposable(repository.signUp(signUpRequest, signUpObserver));
    }

    private String makePhoneNumber(String phoneNumber) {
        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";

        if (!Pattern.matches(regEx, phoneNumber)) {
            return null;
        } else {
            return phoneNumber.replaceAll(regEx, "$1-$2-$3");
        }
    }
}
