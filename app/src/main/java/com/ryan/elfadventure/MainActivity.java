package com.ryan.elfadventure;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId =
            getResources().getIdentifier("activity_main", "layout", getPackageName());
        setContentView(layoutId);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        try {
            XmlPullParser parser = getResources().getXml(R.xml.hero_house);
            parser.next();
            parser.next();

            mLevelManager = new LevelManager(parser);
            setStage(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void setStage(int _index) {
        TextView stageTxt = findViewById(R.id.stageTxt);

        Button moveBtn1 = findViewById(R.id.moveBtn1);
        Button moveBtn2 = findViewById(R.id.moveBtn2);
        Button moveBtn3 = findViewById(R.id.moveBtn3);

        Stage stage = mLevelManager.getStage(0);
        System.out.println(stage.size());
        stageTxt.setText(stage.getText());

        moveBtn1.setText(stage.getMove(0).getText());
        moveBtn2.setText(stage.getMove(1).getText());
        moveBtn3.setText(stage.getMove(2).getText());
    }

    private LevelManager mLevelManager;
}