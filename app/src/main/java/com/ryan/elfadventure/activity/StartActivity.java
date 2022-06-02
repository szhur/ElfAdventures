package com.ryan.elfadventure.activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ryan.elfadventure.R;

public class StartActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
