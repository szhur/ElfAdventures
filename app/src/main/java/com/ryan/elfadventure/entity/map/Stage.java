package com.ryan.elfadventure.entity.map;

import java.util.ArrayList;

public class Stage {
    public String getText() {
        return mText;
    }

    public void setText(String _text) {
        mText = _text;
    }

    public String getQuest() {
        return mQuest;
    }

    public void setQuest(String _quest) {
        mQuest = _quest;
    }

    public int size() {
        return mActions.size();
    }

    public void addAction(Action _action) {
        mActions.add(_action);
    }

    public Action getAction(int _index) {
        return mActions.get(_index);
    }

    private String mText;
    private String mQuest;
    ArrayList<Action> mActions = new ArrayList<>();
}
