package com.ryan.elfadventure.entity.map;

/* TODO: Refactor to use optionals */
public class Action {
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

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int _itemId) {
        mItemId = _itemId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String _text) {
        mText = _text;
    }

    private boolean mIsInner;
    private int mId;

    private int mItemId;

    private String mText;
}
