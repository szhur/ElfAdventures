package com.ryan.elfadventure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId =
            getResources().getIdentifier("activity_main", "layout", getPackageName());
        setContentView(layoutId);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        try {
            XmlPullParser parser = getResources().getXml(R.xml.level_map);
            parser.next();
            parser.next();
            mMapManager = new MapManager(parser);

            setLevel(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void setLevel(int _index) throws IOException, XmlPullParserException {
        Level level = mMapManager.getLevel(_index);

        int levelId =
            getResources().getIdentifier(
                    level.getPath(), "xml", getPackageName()
            )
        ;
        XmlPullParser parser = getResources().getXml(levelId);
        parser.next();
        parser.next();
        mLevelManager = new LevelManager(parser);

        setStage(level.isVisited() ? 0 : 1);
        level.visit();
    }

    private void setStage(int _index) {
        final Stage stage = mLevelManager.getStage(_index);
        TextView stageTxt = findViewById(R.id.stageTxt);
        String txt = stage.getText();
        if (txt != null)
            stageTxt.setText(txt);

        if (stage.size() == 1) {
            setStage(stage.getMove(0).getId());
            return;
        }

        // TODO: Replace with programmatically defined buttons
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(findViewById(R.id.moveBtn1));
        buttons.add(findViewById(R.id.moveBtn2));
        buttons.add(findViewById(R.id.moveBtn3));

        for (int i = 0; i < stage.size(); ++i)
        {
            Button button = buttons.get(i);
            button.setText(stage.getMove(i).getText());
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
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
        }
    }

    private void move(Stage _stage, int _index) throws IOException, XmlPullParserException {
        Move move = _stage.getMove(_index);
        if (move.isInner())
            setStage(move.getId());
        else
            setLevel(move.getId());
    }

    private LevelManager mLevelManager;
    private MapManager mMapManager;
}