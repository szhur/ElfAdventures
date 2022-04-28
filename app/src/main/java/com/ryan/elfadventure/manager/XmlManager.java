package com.ryan.elfadventure.manager;

import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.ryan.elfadventure.R;
import com.ryan.elfadventure.entity.Level;
import com.ryan.elfadventure.global.Globals;
import com.ryan.elfadventure.global.LevelState;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class XmlManager {
    private static XmlManager xmlInstance;

    public static void init(Resources _res) {
        xmlInstance = new XmlManager(_res);
    }

    public static XmlManager getInstance() {
        return xmlInstance;
    }

    public MapManager getMapManager() {
        return mMapManager;
    }

    public LevelManager getLevelManager() {
        return mLevelManager;
    }

    public InvertoryManager getInventoryManager() {
        return mInventoryManager;
    }

    private XmlManager(Resources _res) {
        try {
            initMapManager(_res);
            initLevelManager(_res);
            initInventoryManager(_res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public void initLevelManager(Resources _res) throws IOException, XmlPullParserException {
        LevelState levelState = Globals.getInstance().getLevelState();
        Level level = mMapManager.getLevel(levelState.getLevel());

        int levelId =
                _res.getIdentifier(
                        level.getPath(), "xml", "com.ryan.elfadventure"
                )
                ;
        XmlPullParser parser = _res.getXml(levelId);
        parser.next();
        parser.next();
        mLevelManager = new LevelManager(parser);
    }

    private void initMapManager(Resources _res) throws IOException, XmlPullParserException {
        XmlPullParser parser = _res.getXml(R.xml.level_map);
        parser.next();
        parser.next();
        mMapManager = new MapManager(parser);
    }

    private void initInventoryManager(Resources _res) {

    }

    MapManager mMapManager;
    LevelManager mLevelManager;
    InvertoryManager mInventoryManager;
}
