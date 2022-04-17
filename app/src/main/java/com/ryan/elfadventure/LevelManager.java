package com.ryan.elfadventure;

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
                skip(_parser);
            }
        }
    }

    public int size() {
        return mStages.size();
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
            if (name.equals("text")) {
                stage.setText(readText(_parser));
            } else if (name.equals("move")) {
                stage.addMove(readMove(_parser));
                _parser.nextTag();
            } else if (name.equals("quest")) {
                stage.setQuest(readText(_parser));
            } else {
                skip(_parser);
            }
        }
        mStages.add(stage);
    }

    private String readText(XmlPullParser _parser) throws IOException, XmlPullParserException {
        String result = null;
        if (_parser.next() == XmlPullParser.TEXT) {
            result = _parser.getText();
            _parser.nextTag();
        }
        return result;
    }

    private Move readMove(XmlPullParser _parser) throws IOException, XmlPullParserException {
        Move move = new Move();

        move.ifInner(_parser.getAttributeName(0).equals("toId"));
        move.setId(
                Integer.parseInt(move.isInner() ?
                    _parser.getAttributeValue(null, "toId") :
                    _parser.getAttributeValue(null, "toLoc")
                )
        );
        if (_parser.next() == XmlPullParser.TEXT) {
            move.setText(_parser.getText());
        } else {
            _parser.nextTag();
        }
        return move;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


    private ArrayList<Stage> mStages = new ArrayList<>();
}
