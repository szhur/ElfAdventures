package com.ryan.elfadventure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ryan.elfadventure.R;

public class StartActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getWindow().setStatusBarColor(
                ContextCompat.getColor(this, R.color.black)
        );

        Button newBtn = findViewById(R.id.newBtn);
        newBtn.setOnClickListener(view -> goToMain());
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
