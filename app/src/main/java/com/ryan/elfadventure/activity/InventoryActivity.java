package com.ryan.elfadventure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.ryan.elfadventure.R;
import com.ryan.elfadventure.global.Cell;
import com.ryan.elfadventure.global.Globals;
import com.ryan.elfadventure.global.InventoryState;
import com.ryan.elfadventure.manager.InvertoryManager;
import com.ryan.elfadventure.manager.XmlManager;
import com.ryan.elfadventure.util.OnSwipeTouchListener;

public class InventoryActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        OnSwipeTouchListener ostListener = new OnSwipeTouchListener(InventoryActivity.this){
            public void onSwipeRight() {
                goToQuest();
            }
            public void onSwipeLeft() {
                goToMain();
            }
        };

        this.findViewById(android.R.id.content).setOnTouchListener(ostListener);

        FrameLayout backpackBtn = findViewById(R.id.backpackBtn);
        FrameLayout charBtn = findViewById(R.id.charBtn);

        setLayout(R.layout.full_inventory);
        backpackBtn.setAlpha(0.75F);

        backpackBtn.setOnClickListener(view -> {
            backpackBtn.setAlpha(0.75F);
            charBtn.setAlpha(1F);
            setLayout(R.layout.full_inventory);
        });

        charBtn.setOnClickListener(view -> {
            charBtn.setAlpha(0.75F);
            backpackBtn.setAlpha(1F);
            setLayout(R.layout.char_inventory);
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        getWindow().setStatusBarColor(
                ContextCompat.getColor(this, R.color.black)
        );
    }

    private void setLayout(int _id) {
        LinearLayout invLayout = findViewById(R.id.invLayout);
        LinearLayout inventory = (LinearLayout) getLayoutInflater().inflate(
                _id, null, false
        );
        inventory.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        if (_id == R.layout.char_inventory)
            initTable(inventory.findViewById(R.id.invTable), 2, 6);
        else
            initTable(inventory.findViewById(R.id.invTable), 8, 6);

        invLayout.removeAllViews();
        invLayout.addView(inventory);
    }

    private void initTable(LinearLayout _layout, int _rowCount, int _columnCount)
    {
        final int invSize = _rowCount * _columnCount;

        TableRow tableRow = null;
        int count = 0;

        // TODO: Remake into double loop
        for (int i = 0; i < invSize; ++i) {
            if (count == 0) {
                tableRow = new TableRow(this);
                tableRow.setLayoutParams(
                        new TableLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                0,
                                1
                        )
                );
            }

            AppCompatImageView imgView = new AppCompatImageView(this);

            InventoryState state = Globals.getInstance().getInvState();
            InvertoryManager manager = XmlManager.getInstance().getInventoryManager();
            if (state.size() > i)
            {
                Cell cell = state.get(i);
                if (cell.isValid()) {
                    int drawableResourceId = getResources().getIdentifier(
                            manager.getItem(cell.getItemId()-1).getImagePath(),
                            "drawable",
                            this.getPackageName()
                    );
                    imgView.setImageResource(drawableResourceId);
                }
            }
            else
                imgView.setImageResource(R.drawable.border);

            tableRow.addView(imgView);
            imgView.setLayoutParams(
                    new TableRow.LayoutParams(
                            0,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            1
                    )
            );
            if (++count == _columnCount) {
                _layout.addView(tableRow);
                count = 0;
            }
        }
    }

    private void goToQuest() {
        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
