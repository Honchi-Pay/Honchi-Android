package com.honchipay.honchi_android.splash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.sign.SignActivity;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    int PERMISSION_CODE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkPermission();
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

    private void checkPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            new AlertDialog.Builder(this)
                    .setTitle("알림")
                    .setMessage("권한이 거부되었습니다.\n사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                    .setNeutralButton("Neutral", (dialog, id) -> {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:$packageName"));
                        startActivity(intent);
                    }).setPositiveButton("확인", (dialog, id) -> finish())
                    .setCancelable(false)
                    .create()
                    .show();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_CODE
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult < 0) {
                    Toast.makeText(this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}