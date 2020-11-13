package com.honchipay.honchi_android.chat.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.chat.model.ChatListItem;
import com.honchipay.honchi_android.chat.model.ChatRepository;
import com.honchipay.honchi_android.sign.Data.TokenResponseData;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChatViewModel extends BaseViewModel {
    private final ChatRepository repository = new ChatRepository();
    public MutableLiveData<List<ChatListItem>> chatRoomListLiveData = new MutableLiveData<>();

    public void getParticipatingChatRooms() {
        addDisposable(repository.getChatList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<ChatListItem>>>() {
                    @Override
                    public void onSuccess(@NonNull Response<List<ChatListItem>> chatListItemResponse) {
                        if (chatListItemResponse.isSuccessful() && chatListItemResponse.code() == 200) {
                            chatRoomListLiveData.postValue(chatListItemResponse.body());
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("ChatViewModel", e.getMessage());
                    }
                })
        );
    }
}
