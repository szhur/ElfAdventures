package com.ryan.elfadventure.manager;

import com.ryan.elfadventure.entity.map.Action;
import com.ryan.elfadventure.entity.map.Stage;
import com.ryan.elfadventure.util.XmlUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class LevelManager {
    public LevelManager(XmlPullParser _parser) throws IOException, XmlPullParserException {
        _parser.require(XmlPullParser.START_TAG, null, "level");
        while (_parser.next() != XmlPullParser.END_TAG) {
            if (_parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = _parser.getName();
            if (name.equals("stage")) {
                readStage(_parser);
            } else {
                XmlUtil.skip(_parser);
            }
        }
    }

    public Stage getStage(int _index) {
        return mStages.get(_index);
    }

    private void readStage(XmlPullParser _parser) throws IOException, XmlPullParserException {
        Stage stage = new Stage();
        _parser.require(XmlPullParser.START_TAG, null, "stage");
        while (_parser.next() != XmlPullParser.END_TAG) {
            if (_parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = _parser.getName();
            switch (name) {
                case "text":
                    stage.setText(XmlUtil.readText(_parser));
                    break;
                case "action":
                    stage.addAction(readAction(_parser));
                    break;
                case "quest":
                    stage.setQuest(XmlUtil.readText(_parser));
                    break;
                default:
                    XmlUtil.skip(_parser);
                    break;
            }
        }
        mStages.add(stage);
    }

    private Action readAction(XmlPullParser _parser) throws IOException, XmlPullParserException {
        Action action = new Action();
        _parser.require(XmlPullParser.START_TAG, null, "action");

        while (_parser.next() != XmlPullParser.END_TAG) {
            if (_parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = _parser.getName();
            switch (name) {
                case "moveTo":
                case "moveToLoc":
                    action.ifInner(name.equals("moveTo"));
                    action.setId(Integer.parseInt(
                            XmlUtil.readText(_parser)
                    ));
                    break;
                case "text":
                    action.setText(XmlUtil.readText(_parser));
                    break;
                case "item":
                    action.setItemId(Integer.parseInt(
                            _parser.getAttributeValue(null, "id")
                            )
                    );
                    XmlUtil.skip(_parser);
                    break;
                default:
                    XmlUtil.skip(_parser);
                    break;
            }
        }

        return action;
    }

    private ArrayList<Stage> mStages = new ArrayList<>();
}
