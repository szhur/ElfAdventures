package com.ryan.elfadventure.manager;

import com.ryan.elfadventure.entity.Level;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MapManager {
    public MapManager(XmlPullParser _parser) throws IOException, XmlPullParserException {
        _parser.require(XmlPullParser.START_TAG, null, "map");
        while (_parser.next() != XmlPullParser.END_TAG) {
            if (_parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            if (_parser.getName().equals("level")) {
                mLevels.add(new Level(readLevel(_parser)));
            }
        }
    }

    public Level getLevel(int _index) {
        return mLevels.get(_index);
    }

    private String readLevel(XmlPullParser _parser) throws IOException, XmlPullParserException {
        String result = null;
        if (_parser.next() == XmlPullParser.TEXT) {
            result = _parser.getText();
            _parser.nextTag();
        }
        return result;
    }

    private ArrayList<Level> mLevels = new ArrayList<>();

}
