package com.ryan.elfadventure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final int MAX_COLS = 6;
        final int INV_SIZE = 12;

        TableLayout invTable = findViewById(R.id.invTable);
        TableRow tableRow = null;
        int count = 0;
        for (int i = 0; i < INV_SIZE; ++i) {
            if (count == 0)
                tableRow = new TableRow(this);

            ImageView imgView = new ImageView(this);

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
                } else
                    imgView.setImageResource(R.drawable.inv_cell);
            }
            else
                imgView.setImageResource(R.drawable.inv_cell);

            tableRow.addView(imgView);
            if (++count == MAX_COLS) {
                invTable.addView(tableRow);
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
