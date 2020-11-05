package com.honchipay.honchi_android.sign.Fragment;

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
import com.honchipay.honchi_android.sign.ViewModel.SignUpViewModel;

import org.jetbrains.annotations.NotNull;

public class SignUpEmailFragment extends Fragment {
    String inputUserEmail = null;
    String inputAuthCode = null;
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
                    Toast.makeText(getContext(), "메일을 보냈습니다. 인증코드를 3분 안에 인증해 주시길 바랍니다.", Toast.LENGTH_LONG).show();
                    binding.signUpEmailEmailTextView.setText("인증코드");
                    binding.signUpEmailEmailEditText.setText("");
                    binding.signUpEmailAuthButton.setText("다음");
                    binding.signUpEmailAuthButton.setOnClickListener(v -> {
                        inputAuthCode = binding.signUpEmailEmailEditText.getText().toString();
                        signUpViewModel.checkAuthCode(inputUserEmail, inputAuthCode);
                    });
                    break;
                case CODE:
                    SignUpPasswordFragment fragment = new SignUpPasswordFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("email", inputUserEmail);
                    fragment.setArguments(bundle);
                    ((SignActivity) requireActivity()).replaceFragment(fragment);
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
            inputUserEmail = binding.signUpEmailEmailEditText.getText().toString();
            signUpViewModel.checkFirstUser(inputUserEmail);
            v.setClickable(false);
        });

        binding.signUpEmailBackButton.setOnClickListener(v -> requireActivity().finish());
    }

    void setRejectAuthCode() {
        binding.signUpEmailEmailEditText.setText("");
        binding.signUpEmailAuthButton.setText("인증번호 보내기");
        binding.signUpEmailAuthButton.setOnClickListener(v -> {
            inputUserEmail = binding.signUpEmailEmailEditText.getText().toString();
            signUpViewModel.checkFirstUser(inputUserEmail);
            v.setClickable(false);
        });
    }
}