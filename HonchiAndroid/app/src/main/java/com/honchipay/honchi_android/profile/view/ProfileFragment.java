package com.honchipay.honchi_android.profile.view;

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

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentProfileBinding;
import com.honchipay.honchi_android.profile.viewmodel.ProfileViewModel;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    ProfileViewModel profileViewModel;
    String image = "";

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
        profileViewModel.profileLiveData.observe(getViewLifecycleOwner(), userProfileResponse ->
                image = userProfileResponse.getEmail());
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
}