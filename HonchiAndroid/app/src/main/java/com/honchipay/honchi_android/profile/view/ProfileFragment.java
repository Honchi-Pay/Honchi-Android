package com.honchipay.honchi_android.profile.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentProfileBinding;
import com.honchipay.honchi_android.profile.data.UserProfileResponse;
import com.honchipay.honchi_android.profile.viewmodel.ProfileViewModel;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setProfileViewModel(profileViewModel);
        profileViewModel.getProfile(SharedPreferencesManager.getInstance().getUserName());
    }
}