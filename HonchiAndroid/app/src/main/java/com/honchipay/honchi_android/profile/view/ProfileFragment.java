package com.honchipay.honchi_android.profile.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentProfileBinding;
import com.honchipay.honchi_android.profile.viewmodel.ProfileViewModel;
import com.honchipay.honchi_android.splash.SplashActivity;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    ProfileViewModel profileViewModel;
    String image = "";
    int id = 0;
    float rating = 0f;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        setBindingAttribute();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        profileViewModel.getProfile(SharedPreferencesManager.getInstance().getUserName());
        profileViewModel.profileLiveData.observe(getViewLifecycleOwner(), userProfileResponse -> {
            image = userProfileResponse.getEmail();
            id = userProfileResponse.getUserId();
        });

        profileViewModel.signOutLiveData.observe(getViewLifecycleOwner(), isSignOut -> {
            Context context = getContext();

            if (isSignOut) {
                Intent intent = new Intent(context, SplashActivity.class);
                context.startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(context, "실패하였습니다.", Toast.LENGTH_LONG).show();
            }
        });

        profileViewModel.successStarLiveData.observe(getViewLifecycleOwner(), isStared -> {
            if (isStared) {
                binding.profileConfidenceRatingBar.setRating(rating);
                binding.profileRatingRateTextView.setText(String.valueOf(rating));
            }
        });
    }

    private void setBindingAttribute() {
        binding.setProfileFragment(this);
        binding.setProfileViewModel(profileViewModel);
    }

    public void moveToEditActivity(String value) {
        Intent intent = new Intent(getContext(), EditPrivateInfoActivity.class);
        intent.putExtra("whereToEdit", value);

        if (value.equals("profile")) {
            intent.putExtra("userProfileItem", image);
        }

        startActivity(intent);
    }

    public void showSignOutDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        ((TextView) dialog.findViewById(R.id.dialog_title_textView)).setText("정말 탈퇴하시겠습니까?");
        dialog.show();

        Button cancelButton = dialog.findViewById(R.id.dialog_cancel_button);
        Button okButton = dialog.findViewById(R.id.dialog_ok_button);

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            profileViewModel.signOutFromService();
        });
    }

    public void showLogOutDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        ((TextView) dialog.findViewById(R.id.dialog_title_textView)).setText("정말 로그아웃하시겠습니까?");
        dialog.show();

        Button cancelButton = dialog.findViewById(R.id.dialog_cancel_button);
        Button okButton = dialog.findViewById(R.id.dialog_ok_button);

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            profileViewModel.signOutFromLogin();
        });
    }

    public void showRatingBar() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_rating_bar_dialog);
        RatingBar ratingBar = dialog.findViewById(R.id.dialog_title_ratingBar);
        dialog.show();

        Button cancelButton = dialog.findViewById(R.id.dialog_cancel_button);
        Button okButton = dialog.findViewById(R.id.dialog_ok_button);

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        okButton.setOnClickListener(v -> {
            dialog.dismiss();
            rating = ratingBar.getRating();
            profileViewModel.setUserRating((int) rating, id);
        });
    }
}