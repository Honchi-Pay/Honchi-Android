package com.honchipay.honchi_android.sign.Fragment;

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

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.databinding.FragmentSignUpPasswordBinding;
import com.honchipay.honchi_android.sign.SignActivity;

import org.jetbrains.annotations.NotNull;

public class SignUpPasswordFragment extends Fragment {
    FragmentSignUpPasswordBinding binding;
    String inputPassword = null;
    String inputConfirm = null;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_password, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.signUpPasswordConfirmEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.signUpPasswordPasswordEditText.getText().toString() != s) {
                    binding.signUpPasswordConfirmEditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#DB0000")));
                    binding.signUpPasswordErrorTextView.setVisibility(View.VISIBLE);
                } else {
                    binding.signUpPasswordConfirmEditText.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8D8D8D")));
                    binding.signUpPasswordErrorTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

        binding.signUpPasswordNextButton.setOnClickListener(v -> {
            inputPassword = binding.signUpPasswordPasswordEditText.getText().toString();
            inputConfirm = binding.signUpPasswordConfirmEditText.getText().toString();

            if (inputPassword.equals(inputConfirm)) {
                SignUpUserInfoFragment fragment = new SignUpUserInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("password", inputPassword);
                fragment.setArguments(bundle);

                ((SignActivity) requireActivity()).replaceFragment(fragment);
            }
        });

        binding.signUpPasswordBackButton.setOnClickListener(v -> requireActivity().finish());
    }
}