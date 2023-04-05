package com.atulparida.spacemate.login_assets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.atulparida.spacemate.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
    // Create a new event for the activity.
     new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            // Start the main activity and close the splash screen activity
            Intent intent = new Intent(SplashActivity.this, AppCompatActivity.class);
            startActivity(intent);
            finish();
        }
    }, SPLASH_TIME_OUT);

    }
