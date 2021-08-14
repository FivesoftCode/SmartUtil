package com.fivesoft.smartutildemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fivesoft.smartutil.Screen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Screen.setNavigationBarVisibility(this, true);
        Screen.setStatusBarVisibility(this, true);
    }

    @Override
    public void onBackPressed() {
        Screen.setNavigationBarVisibility(this, false);
        Screen.setStatusBarVisibility(this, false);
    }
}