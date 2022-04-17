package com.ryan.elfadventure;

public class Move {
    public boolean isInner() {
        return mIsInner;
    }

    public void ifInner(boolean _isInner) {
        mIsInner = _isInner;
    }

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

    private boolean mIsInner;
    private int mId;
    private String mText;
}
