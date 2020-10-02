package com.example.diplomnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private Key key = App.getKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        initActivity();
    }

    private void initActivity() {
        if (key.hasPin()) {
            if (key.checkPin("pinOff")) {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, ListNoteActivity.class));
            } else {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        } else {
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, SettingsActivity.class));
        }
        SplashActivity.this.finish();
    }
}