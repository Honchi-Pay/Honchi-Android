package com.honchipay.honchi_android.sign.fragment;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentSignUpPasswordBinding;
import com.honchipay.honchi_android.sign.SignActivity;
import com.honchipay.honchi_android.sign.viewModel.SignUpViewModel;
import com.honchipay.honchi_android.util.CustomTextWatcher;

import org.jetbrains.annotations.NotNull;

public class SignUpPasswordFragment extends Fragment {
    FragmentSignUpPasswordBinding binding;
    SignUpViewModel signUpViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_password, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        binding.setSignUpViewModel(signUpViewModel);

        binding.signUpPasswordConfirmEditText.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!binding.signUpPasswordPasswordEditText.getText().toString().equals(s.toString())) {
                    binding.signUpPasswordConfirmEditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#DB0000")));
                    binding.signUpPasswordErrorTextView.setVisibility(View.VISIBLE);
                } else {
                    binding.signUpPasswordConfirmEditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8D8D8D")));
                    binding.signUpPasswordErrorTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

        binding.signUpPasswordNextButton.setOnClickListener(v -> {
            String inputPassword = binding.signUpPasswordPasswordEditText.getText().toString();
            String inputConfirm = binding.signUpPasswordConfirmEditText.getText().toString();

            if (inputPassword.equals(inputConfirm)) {
                ((SignActivity) requireActivity()).replaceFragment(new SignUpUserInfoFragment());
            }
        });

        binding.signUpPasswordBackButton.setOnClickListener(v -> requireActivity().finish());
    }
}