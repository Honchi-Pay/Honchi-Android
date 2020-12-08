package com.honchipay.honchi_android.chat.viewModel;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.honchipay.honchi_android.base.BaseViewModel;
import com.honchipay.honchi_android.chat.HonchiPaySocket;
import com.honchipay.honchi_android.chat.model.ChatRepository;
import com.honchipay.honchi_android.chat.model.ChatRoomItem;
import com.honchipay.honchi_android.chat.model.MessageResponse;
import com.honchipay.honchi_android.chat.model.socket.ChangeTitleRequest;
import com.honchipay.honchi_android.util.CustomDisposableSingleObserver;

import java.io.File;
import java.util.List;
import java.util.Objects;

import io.reactivex.annotations.NonNull;
import retrofit2.Response;

public class ChatViewModel extends BaseViewModel {
    private final String TAG = ChatViewModel.class.getSimpleName();
    private final ChatRepository repository = new ChatRepository();
    public final MutableLiveData<List<ChatRoomItem>> chatRoomListLiveData = new MutableLiveData<>();
    public final MutableLiveData<List<MessageResponse>> messageListLiveData = new MutableLiveData<>();
    private String roomId;

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void changeChatRoomTitle(String preRoomTitle, String title) {
        if (!Objects.requireNonNull(title).equals("") && !Objects.requireNonNull(title).equals(preRoomTitle)) {
            addDisposable(repository.changeRoomTitle(roomId, title, new CustomDisposableSingleObserver<Response<Void>>(TAG) {
                @Override
                public void onSuccess(@NonNull Response<Void> voidResponse) {
                    HonchiPaySocket.getInstance().changeRoomTitle(new ChangeTitleRequest(roomId, title));
                }
            }));
        }
    }

    public void getParticipatingChatRooms() {
        addDisposable(repository.getChatRooms(new CustomDisposableSingleObserver<Response<List<ChatRoomItem>>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<List<ChatRoomItem>> listResponse) {
                if (listResponse.isSuccessful() && listResponse.code() == 200) {
                    chatRoomListLiveData.postValue(listResponse.body());
                }
            }
        }));
    }

    public void getAllMessages() {
        addDisposable(repository.getAllMessages(roomId, new CustomDisposableSingleObserver<Response<List<MessageResponse>>>(TAG) {
            @Override
            public void onSuccess(@NonNull Response<List<MessageResponse>> listResponse) {
                if (listResponse.isSuccessful() && listResponse.code() == 200) {
                    messageListLiveData.postValue(listResponse.body());
                }
            }
        }));
    }

    public void readMessages() {
        addDisposable(repository.readMessages(roomId, new CustomDisposableSingleObserver<>(TAG)));
    }

    public void deleteMessage(int chatId) {
        addDisposable(repository.deleteMessage(chatId, new CustomDisposableSingleObserver<>(TAG)));
    }

    public void uploadImageToServer(String chatId, File file) {
        addDisposable(repository.uploadImage(chatId, file, new CustomDisposableSingleObserver<>(TAG)));
    }
}