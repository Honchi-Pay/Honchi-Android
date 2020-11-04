package com.honchipay.honchi_android.sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.sign.Fragment.LoginFragment;
import com.honchipay.honchi_android.sign.Fragment.SignUpEmailFragment;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        SharedPreferencesManager sharedPreferences = SharedPreferencesManager.getInstance();
        if (sharedPreferences != null) {
            String token = sharedPreferences.getAccessToken();
            if (!token.equals("")) Log.e("SignActivity", token);
            String refresh = sharedPreferences.getRefreshToken();
            if (!refresh.equals("")) Log.e("SignActivity", refresh);
        }

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