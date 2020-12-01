package com.honchipay.honchi_android.profile.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentEditProfileBinding;
import com.honchipay.honchi_android.profile.viewmodel.EditProfileViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class EditProfileFragment extends Fragment {
    public String image = "";
    private final int REQUEST_IMAGE = 333;
    FragmentEditProfileBinding binding;
    EditProfileViewModel editProfileViewModel;
    File file = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        image = requireArguments().getString("profileBundle");
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editProfileViewModel = new ViewModelProvider(requireActivity()).get(EditProfileViewModel.class);
        binding.setEditProfileViewModel(editProfileViewModel);
        binding.setEditProfileFragment(this);
        binding.setLifecycleOwner(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditPrivateInfoActivity activity = (EditPrivateInfoActivity) requireActivity();

        binding.editProfileUserImageView.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요"), REQUEST_IMAGE);
        });

        binding.editProfileDoChangeButton.setOnClickListener(v -> editProfileViewModel.uploadUserNewInfo(file));
        editProfileViewModel.changeSuccess.observe(getViewLifecycleOwner(), success -> {
            if (success) {
                activity.finish();
            } else {
                Toast.makeText(getContext(), "프로필을 수정하는데 실패하였습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE) {
            Uri uri = data.getData();
            file = new File(uri.getPath());
            Glide.with(this).load(uri).circleCrop().into(binding.editProfileUserImageView);
        }
    }
}