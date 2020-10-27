package com.honchipay.honchi_android.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.Sign.SignActivity;

public class SplashActivity extends AppCompatActivity {
    Button login_btn;
    Button signUp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        login_btn = findViewById(R.id.splash_login_button);
        signUp_btn = findViewById(R.id.splash_signUp_button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignActivity.class);
                intent.putExtra("splash","login");
                startActivity(intent);
            }
        });

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignActivity.class);
                intent.putExtra("splash","signUp");
                startActivity(intent);
            }
        });

    }
}