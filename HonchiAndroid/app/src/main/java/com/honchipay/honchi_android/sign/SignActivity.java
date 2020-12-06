package com.honchipay.honchi_android.sign;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.sign.fragment.LoginFragment;
import com.honchipay.honchi_android.sign.fragment.SignUpEmailFragment;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        setbackButton();
        navigateRoute();
    }

    private void setbackButton() {
        findViewById(R.id.sign_back_button).setOnClickListener(v -> finish());
    }

    private void navigateRoute() {
        String division = getIntent().getStringExtra("splash");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (division) {
            case "login":
                transaction.add(R.id.sign_fragment, new LoginFragment()).commit();
                break;
            case "signUp":
                transaction.add(R.id.sign_fragment, new SignUpEmailFragment()).commit();
                break;
            default:
                Toast.makeText(this, "올바르지 않은 요청입니다.", Toast.LENGTH_LONG).show();
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.sign_fragment, fragment);
        transaction.commit();
    }
}