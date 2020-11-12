package com.honchipay.honchi_android.sign.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentLoginBinding;
import com.honchipay.honchi_android.sign.ViewModel.LoginViewModel;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {
    String inputUserId = null;
    String inputUserPW = null;
    FragmentLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.loginLoginButton.setOnClickListener(v -> {
            inputUserId = binding.loginIdEditText.getText().toString();
            inputUserPW = binding.loginPasswordEditText.getText().toString();
            loginViewModel.login(inputUserId, inputUserPW);
        });

        loginViewModel.loginSuccess.observe(getViewLifecycleOwner(), loginIsSuccess -> {
            if (loginIsSuccess) {
                if (binding.loginAutoLoginCheckBox.isChecked()) {
                    SharedPreferencesManager.getInstance().setIsLogin(true);
                }
//                Intent intent = new Intent(getContext(), );
//                requireActivity().startActivity(intent);
            }
        });

        binding.loginBackButton.setOnClickListener(v -> requireActivity().finish());
        binding.loginFindPasswordLayout.setOnClickListener(v -> {
            binding.loginPasswordEditText.setHint("변경하실 비밀번호를 입력해주세요");
            binding.loginAutoLoginCheckBox.setVisibility(View.GONE);
            binding.loginLoginButton.setText("비밀번호 변경하기");
            binding.loginLoginButton.setOnClickListener(v1 -> {
                inputUserId = binding.loginIdEditText.getText().toString();
                inputUserPW = binding.loginPasswordEditText.getText().toString();
                loginViewModel.forgotPassword(inputUserId, inputUserPW);
            });
            v.setVisibility(View.GONE);
        });
    }
}