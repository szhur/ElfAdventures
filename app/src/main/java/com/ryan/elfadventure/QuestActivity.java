package com.ryan.elfadventure;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class QuestActivity extends AppCompatActivity {
    @SuppressLint("ClickableViewAccessibility")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questbook);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        OnSwipeTouchListener ostListener = new OnSwipeTouchListener(QuestActivity.this){
            public void onSwipeRight() {
                goToMain();
            }
            public void onSwipeLeft() {
                goToMain();
            }
        };

        Globals globals = Globals.getInstance();
        TextView questTxt = findViewById(R.id.questTxt);
        questTxt.setText(globals.getQuestLog());

        this.findViewById(android.R.id.content).setOnTouchListener(ostListener);
        questTxt.setOnTouchListener(ostListener);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
