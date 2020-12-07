package com.honchipay.honchi_android.sign.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentLoginBinding;
import com.honchipay.honchi_android.home.ui.homeActivity;
import com.honchipay.honchi_android.sign.viewModel.LoginViewModel;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    LoginViewModel loginViewModel;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        changeToFindPassWordViewsByLayout();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);

        loginViewModel.loginSuccess.observe(getViewLifecycleOwner(), loginIsSuccess -> {
            if (binding.loginAutoLoginCheckBox.isChecked()) {
                SharedPreferencesManager.getInstance().setIsLogin(true);
            }

            Intent intent = new Intent(getContext(), homeActivity.class);
            requireActivity().startActivity(intent);
        });
    }

    private void changeToFindPassWordViewsByLayout() {
        binding.loginFindPasswordLayout.setOnClickListener(v -> {
            binding.loginPasswordEditText.setHint("변경하실 비밀번호를 입력해주세요");
            binding.loginAutoLoginCheckBox.setVisibility(View.GONE);
            binding.loginLoginButton.setText("비밀번호 변경하기");
            binding.loginLoginButton.setOnClickListener(v1 -> loginViewModel.forgotPassword());
            v.setVisibility(View.GONE);
        });
    }
}