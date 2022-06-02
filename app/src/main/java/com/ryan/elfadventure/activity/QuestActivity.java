package com.ryan.elfadventure.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ryan.elfadventure.util.OnSwipeTouchListener;
import com.ryan.elfadventure.R;
import com.ryan.elfadventure.global.Globals;

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
                goToInventory();
            }
        };

        Globals globals = Globals.getInstance();
        TextView questTxt = findViewById(R.id.questTxt);
        questTxt.setText(globals.getQuestLog());

        this.findViewById(android.R.id.content).setOnTouchListener(ostListener);
        questTxt.setOnTouchListener(ostListener);

        getWindow().setStatusBarColor(
                ContextCompat.getColor(this, R.color.black)
        );
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToInventory() {
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }
}
