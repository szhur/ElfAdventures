package com.ryan.elfadventure;

public class Move {
    public int getId() {
        return mId;
    }

    public void setId(int _id) {
        mId = _id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String _text) {
        mText = _text;
    }

    int mId;
    String mText;
}
