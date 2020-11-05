package com.honchipay.honchi_android.splash;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.honchipay.honchi_android.R;
import com.honchipay.honchi_android.sign.SignActivity;
import com.honchipay.honchi_android.util.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    int PERMISSION_CODE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkPermission();
        checkAutoLogin();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SignActivity.class);

        if (v.getId() == R.id.splash_login_button) {
            intent.putExtra("splash", "login");
        } else if (v.getId() == R.id.splash_signUp_button) {
            intent.putExtra("splash", "signUp");
        }

        startActivity(intent);
    }

    private void checkPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

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
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
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

    private void checkAutoLogin() {
        if (SharedPreferencesManager.getInstance().getIsLogin()) {
//            Intent intent = new Intent(this, );
//            startActivity(intent);
        }
    }
}