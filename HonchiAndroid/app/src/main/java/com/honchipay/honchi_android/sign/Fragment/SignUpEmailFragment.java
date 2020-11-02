package com.honchipay.honchi_android.sign.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentSignUpEmailBinding;
import com.honchipay.honchi_android.sign.SignActivity;
import com.honchipay.honchi_android.sign.ViewModel.SignUpViewModel;

import org.jetbrains.annotations.NotNull;

public class SignUpEmailFragment extends Fragment {
    String inputUserEmail = null;
    FragmentSignUpEmailBinding binding;
    SignUpViewModel signUpViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_email, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        signUpViewModel.haveToNextPageLiveData.observe(getViewLifecycleOwner(), signUpProcess -> {
            switch (signUpProcess) {
                case FIRST:
                    signUpViewModel.checkDuplicatedEmail(inputUserEmail);
                    break;
                case EMAIL:
                    ((SignActivity) requireActivity()).replaceFragment(new SignUpPasswordFragment());
                    break;
                default:
                    binding.signUpEmailAuthButton.setClickable(true);
            }
        });

        binding.signUpEmailAuthButton.setOnClickListener(v -> {
            inputUserEmail = binding.signUpEmailEmailEditText.getText().toString();
            signUpViewModel.checkFirstUser(inputUserEmail);
            v.setClickable(false);
        });

        binding.signUpEmailBackButton.setOnClickListener(v -> requireActivity().finish());
    }
}