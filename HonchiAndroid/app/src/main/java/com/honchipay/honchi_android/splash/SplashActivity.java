package com.honchipay.honchi_android.splash;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.sign.SignActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SignActivity.class);

        switch (v.getId()) {
            case R.id.splash_login_button:
                intent.putExtra("splash", "login");
                break;
            case R.id.splash_signUp_button:
                intent.putExtra("splash", "signUp");
                break;
        }

        startActivity(intent);
    }
}