package com.honchipay.honchi_android.profile.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentEditPasswordBinding;
import com.honchipay.honchi_android.profile.viewmodel.EditProfileViewModel;

import org.jetbrains.annotations.NotNull;

public class EditPasswordFragment extends Fragment {
    FragmentEditPasswordBinding binding;
    EditProfileViewModel editProfileViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_password, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editProfileViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);
        binding.setEditProfileViewModel(editProfileViewModel);
        binding.setLifecycleOwner(this);

        editProfileViewModel.changeSuccess.observe(getViewLifecycleOwner(), success -> {
            if (success) {
                requireActivity().finish();
            }
        });
    }
}