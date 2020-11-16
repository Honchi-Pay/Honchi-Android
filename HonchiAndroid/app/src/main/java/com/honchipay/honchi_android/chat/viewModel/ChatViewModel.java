package com.honchipay.honchi_android.chat.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.chat.model.ChatListItem;
import com.honchipay.honchi_android.chat.model.ChatRepository;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;

import java.util.List;
import java.util.function.Function;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class ChatViewModel extends BaseViewModel {
    private final ChatRepository repository = new ChatRepository();
    public MutableLiveData<List<ChatListItem>> chatRoomListLiveData = new MutableLiveData<>();
    private final String TAG = ChatViewModel.class.getSimpleName();

    public void getParticipatingChatRooms() {
        DisposableSingleObserver<Response<List<ChatListItem>>> chatListItemObserver =
                new CustomDisposableSingleObserver<Response<List<ChatListItem>>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<List<ChatListItem>> listResponse) {
                if (listResponse.isSuccessful() && listResponse.code() == 200) {
                    chatRoomListLiveData.postValue(listResponse.body());
                }
            }
        };

        addSingle(repository.getChatList(), chatListItemObserver);
    }

    public void changeChatRoomTitle(String title) {
        DisposableSingleObserver<Response<Void>> roomTitleObserver =
                new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                    @Override
                    public void onSuccess(@NonNull Response<Void> listResponse) {
                    }
                };

        addSingle(repository.changeRoomTitle(title), roomTitleObserver);
    }
}