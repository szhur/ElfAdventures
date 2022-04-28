package com.ryan.elfadventure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ryan.elfadventure.entity.Level;
import com.ryan.elfadventure.entity.Move;
import com.ryan.elfadventure.manager.XmlManager;
import com.ryan.elfadventure.util.OnSwipeTouchListener;
import com.ryan.elfadventure.R;
import com.ryan.elfadventure.entity.Stage;
import com.ryan.elfadventure.global.Globals;
import com.ryan.elfadventure.global.LevelState;
import com.ryan.elfadventure.manager.LevelManager;
import com.ryan.elfadventure.manager.MapManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlManager.init(getResources());

        OnSwipeTouchListener ostListener = new OnSwipeTouchListener(MainActivity.this){
            public void onSwipeRight() {
                goToInventory();
            }
            public void onSwipeLeft() {
                goToQuest();
            }
        };

        this.findViewById(android.R.id.content).setOnTouchListener(ostListener);

        TextView stageTxt = findViewById(R.id.stageTxt);
        stageTxt.setOnTouchListener(ostListener);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        try {
            LevelState levelState = Globals.getInstance().getLevelState();
            setLevel(levelState.getLevel(), true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void goToQuest() {
        Intent intent = new Intent(this, QuestActivity.class);
        startActivity(intent);
    }

    private void goToInventory() {
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }

    private void setLevel(int _index, boolean _useGlobals)
            throws IOException, XmlPullParserException {
        LevelState levelState = Globals.getInstance().getLevelState();
        Level level = XmlManager.getInstance().getMapManager().getLevel(_index);
        levelState.setLevel(_index);

        XmlManager.getInstance().initLevelManager(getResources());

        setStage(_useGlobals ?
                levelState.getStage() :
                level.isVisited() ? 0 : 1
        );
        level.visit();
    }

    private void setStage(int _index) {
        LevelState levelState = Globals.getInstance().getLevelState();
        final Stage stage = XmlManager.getInstance().getLevelManager().getStage(_index);

        TextView stageTxt = findViewById(R.id.stageTxt);
        String txt = stage.getText();
        if (txt != null) {
            levelState.setStage(_index);
            stageTxt.setText(txt);
        }
        String quest = stage.getQuest();
        if (quest != null)
            Globals.getInstance().setQuestLog(quest);

        if (stage.size() == 1) {
            setStage(stage.getMove(0).getId());
            return;
        }

        LinearLayout btnLayout = findViewById(R.id.btnLayout);
        for (int i = 0; i < stage.size(); ++i)
        {
            Button newBtn = new Button(this);
            newBtn.setText(stage.getMove(i).getText());
            int finalI = i;
            newBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        move(stage, finalI);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                }
            });

            btnLayout.addView(newBtn);
        }
    }

    private void move(Stage _stage, int _index) throws IOException, XmlPullParserException {
        Move move = _stage.getMove(_index);
        if (move.isInner())
            setStage(move.getId());
        else
            setLevel(move.getId(), false);
    }
}