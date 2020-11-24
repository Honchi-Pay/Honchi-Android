package com.honchipay.honchi_android.sign.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentSignUpEmailBinding;
import com.honchipay.honchi_android.sign.SignActivity;
import com.honchipay.honchi_android.sign.viewModel.SignUpViewModel;

import org.jetbrains.annotations.NotNull;

public class SignUpEmailFragment extends Fragment {
    FragmentSignUpEmailBinding binding;
    SignUpViewModel signUpViewModel;
    final int gone = View.GONE;
    final int visibility = View.VISIBLE;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_email, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        binding.setSignViewModel(signUpViewModel);
        binding.setLifecycleOwner(this);

        signUpViewModel.haveToNextPageLiveData.observe(getViewLifecycleOwner(), signUpProcess -> {
            switch (signUpProcess) {
                case FIRST:
                    signUpViewModel.checkDuplicatedEmail();
                    break;
                case EMAIL:
                    Toast.makeText(getContext(), "메일을 보냈습니다. 인증코드를 3분 안에 인증해 주시길 바랍니다.", Toast.LENGTH_LONG).show();
                    setVisibility(true);
                    binding.signUpEmailAuthButton.setText("다음");
                    binding.signUpEmailAuthButton.setOnClickListener(v -> signUpViewModel.checkAuthCode());
                    break;
                case CODE:
                    ((SignActivity) requireActivity()).replaceFragment(new SignUpPasswordFragment());
                    break;
                case REJECT:
                    Toast.makeText(getContext(), "인증에 실패하였습니다.", Toast.LENGTH_LONG).show();
                    setRejectAuthCode();
                    break;
                default:
                    binding.signUpEmailAuthButton.setClickable(true);
            }
        });

        binding.signUpEmailAuthButton.setOnClickListener(v -> {
            signUpViewModel.checkFirstUser();
            v.setClickable(false);
        });
    }

    void setRejectAuthCode() {
        setVisibility(false);
        binding.signUpEmailAuthButton.setText("인증번호 보내기");
        binding.signUpEmailAuthButton.setOnClickListener(v -> {
            signUpViewModel.checkFirstUser();
            v.setClickable(false);
        });
    }

    void setVisibility(boolean isVisibility) {
        if (isVisibility) {
            binding.signUpEmailEmailTextView.setVisibility(gone);
            binding.signUpEmailEmailEditText.setVisibility(gone);
            binding.signUpEmailAuthCodeTextView.setVisibility(visibility);
            binding.signUpEmailAuthCodeEditText.setVisibility(visibility);
            binding.signUpEmailAuthCodeEditText.setText(null);
        } else {
            binding.signUpEmailEmailTextView.setVisibility(visibility);
            binding.signUpEmailEmailEditText.setVisibility(visibility);
            binding.signUpEmailAuthCodeTextView.setVisibility(gone);
            binding.signUpEmailAuthCodeEditText.setVisibility(gone);
            binding.signUpEmailEmailEditText.setText(null);
        }
    }
}